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
package com.liferay.faces.showcase.dto;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;

import com.liferay.faces.showcase.util.CodeExampleUtil;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Neil Griffin
 */
public class CodeExample implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 7008163411783803745L;

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(CodeExample.class);

	// Private Data Members
	private String fileExtension;
	private String fileName;
	private long lastModified;
	private String rawText;
	private URL url;

	public CodeExample(String fileName, String fileExtension, URL url, long lastModified, String rawText) {
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.url = url;
		this.lastModified = lastModified;
		this.rawText = rawText;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getFileName() {
		return fileName;
	}

	public long getLastModified() {
		return lastModified;
	}

	public String getRawText() {

		boolean developmentMode = false;
		boolean productionMode = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (facesContext != null) {
			developmentMode = facesContext.isProjectStage(ProjectStage.Development);
			productionMode = facesContext.isProjectStage(ProjectStage.Production);
		}

		if (developmentMode) {

			try {
				URLConnection urlConnection = url.openConnection();
				long lastModified = urlConnection.getLastModified();

				if (lastModified > this.lastModified) {

					CodeExample updatedCodeExample = CodeExampleUtil.read(url, fileName, productionMode);
					this.lastModified = lastModified;
					this.rawText = updatedCodeExample.getRawText();
				}
			}
			catch (IOException e) {
				logger.error(e);
			}
		}

		return rawText;
	}

	public URL getURL() {
		return url;
	}
}
