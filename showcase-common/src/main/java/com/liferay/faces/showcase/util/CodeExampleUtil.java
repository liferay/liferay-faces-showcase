/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.showcase.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liferay.faces.showcase.dto.CodeExample;


/**
 * @author  Neil Griffin
 */
public class CodeExampleUtil {

	// Private Constants
	private static final Pattern BLANK_LINE_PATTERN = Pattern.compile("\\t+$");
	private static final String JAVA = "java";
	private static final String JAVA_EXTENSION = ".java";
	private static final Pattern JAVA_MULTILINE_COMMENTS_PATTERN = Pattern.compile("/[*][*].*[*]/", Pattern.DOTALL);
	private static final String OUTPUTMODEL_MODELVALUE = ":outputModel:modelValue";
	private static final Pattern TEMPLATE_ATTRIBUTE_PATTERN = Pattern.compile("\\s*template=\".*\"");
	private static final String RENDER_EXAMPLE_FORM = "render=\":example:exampleForm:";
	private static final String RENDER_FORM_EXAMPLE = "render=\":exampleForm:example";
	private static final Pattern SHOWCASE_NAMESPACE_PATTERN = Pattern.compile("\\s*xmlns:showcase=\".*\"");
	private static final String RENDERED = "rendered=\"#{showcaseModelBean.selectedComponent.rendered}\"";
	private static final String XML = "xml";

	public static CodeExample read(URL sourceFileURL, String sourceFileName, boolean productionMode)
		throws IOException {

		URLConnection urlConnection = sourceFileURL.openConnection();
		InputStream resourceAsStream = urlConnection.getInputStream();
		StringBuilder stringBuilder = new StringBuilder();

		if (resourceAsStream != null) {

			byte[] bytes = new byte[1024];
			int bytesRead;

			while ((bytesRead = resourceAsStream.read(bytes)) != -1) {
				String bytesAsString = new String(bytes, 0, bytesRead);
				stringBuilder.append(bytesAsString);
			}

			resourceAsStream.close();

			String sourceCodeText = stringBuilder.toString();
			String fileExtension;

			if (sourceFileName.endsWith(JAVA_EXTENSION)) {

				fileExtension = JAVA;

				Matcher matcher = JAVA_MULTILINE_COMMENTS_PATTERN.matcher(sourceCodeText);
				sourceCodeText = matcher.replaceAll("");
			}
			else {

				// Reset the stringBuilder
				stringBuilder.setLength(0);
				fileExtension = XML;
				sourceCodeText = TEMPLATE_ATTRIBUTE_PATTERN.matcher(sourceCodeText).replaceFirst("");
				sourceCodeText = SHOWCASE_NAMESPACE_PATTERN.matcher(sourceCodeText).replaceAll("");

				StringReader stringReader = new StringReader(sourceCodeText);
				BufferedReader bufferedReader = new BufferedReader(stringReader);
				int trimTab = 0;
				String line;
				boolean ignoreNextLine = false;
				int defineCount = 0;

				while ((line = bufferedReader.readLine()) != null) {
					String trimmedLine = line.trim();

					if (ignoreNextLine) {
						ignoreNextLine = !trimmedLine.endsWith(">");
					}
					else {

						boolean defineColOpen = trimmedLine.startsWith("<ui:define name=\"col");

						if (!defineColOpen && trimmedLine.startsWith("<ui:define")) {
							defineCount++;
						}

						boolean defineClose = trimmedLine.startsWith("</ui:define");
						boolean defineColClose = ((defineCount == 0) && defineClose);

						if (defineClose && !defineColClose) {
							defineCount--;
						}

						if (trimmedLine.startsWith("<showcase") || defineColOpen) {
							trimTab++;
							ignoreNextLine = !trimmedLine.endsWith(">");

						}
						else if (trimmedLine.startsWith("</showcase") || defineColClose) {
							trimTab--;
						}
						else {

							for (int i = 0; i < trimTab; i++) {

								if (line.startsWith("\t")) {
									line = line.substring(1);
								}
							}

							int pos = line.indexOf(OUTPUTMODEL_MODELVALUE);

							if (pos > 0) {
								line = line.substring(0, pos) + ":modelValue" +
									line.substring(pos + OUTPUTMODEL_MODELVALUE.length());
							}

							pos = line.indexOf(RENDER_EXAMPLE_FORM);

							if (pos > 0) {
								line = line.substring(0, pos) + "render=\"" +
									line.substring(pos + RENDER_EXAMPLE_FORM.length());
							}

							pos = line.indexOf(RENDER_FORM_EXAMPLE);

							if (pos > 0) {
								line = line.substring(0, pos) + "render=\":exampleForm" +
									line.substring(pos + RENDER_FORM_EXAMPLE.length());
							}

							if (productionMode) {
								pos = line.indexOf(RENDERED);

								if (pos > 0) {
									line = line.substring(0, pos) + line.substring(pos + RENDERED.length());
								}
							}

							// Strip empty lines
							Matcher matcher = BLANK_LINE_PATTERN.matcher(line);

							if (!matcher.matches()) {
								stringBuilder.append(line);
								stringBuilder.append("\n");
							}
						}
					}
				}

				sourceCodeText = stringBuilder.toString();
			}

			sourceCodeText = sourceCodeText.trim();

			return new CodeExample(sourceFileName, fileExtension, sourceFileURL, urlConnection.getLastModified(),
					sourceCodeText);
		}
		else {
			throw new IOException("Unable to locate " + sourceFileURL);
		}
	}
}
