/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
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

import java.util.regex.Pattern;

import com.liferay.faces.util.component.ComponentUtil;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.faces.util.product.Product;
import com.liferay.faces.util.product.ProductFactory;


/**
 * @author  Neil Griffin
 */
public class ShowcaseUtil {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(ShowcaseUtil.class);

	// Private Constants
	private static final String ANCHOR_ELEMENT_CLOSE = "</a>";
	private static final String ANCHOR_ELEMENT_OPEN_START = "<a href=\"";
	private static final String ANCHOR_ELEMENT_OPEN_FINISH = "\" target=\"_blank\">";
	private static final Pattern BRACKET_DELIMITER_PATTERN = Pattern.compile("[\\[\\]]");
	private static final Pattern COLON_DELIMITER_PATTERN = Pattern.compile(":");
	private static final Product JSF_PRODUCT = ProductFactory.getProduct(Product.Name.JSF);
	private static final String JSF_VERSION = JSF_PRODUCT.getMajorVersion() + "." + JSF_PRODUCT.getMinorVersion();
	private static final String JSF_VDLDOC_VERSION = (JSF_PRODUCT.getMajorVersion() < 2) ? "2.0" : JSF_VERSION;
	private static final String JSF_VDLDOC_BASE_URL = "https://javaserverfaces.java.net/nonav/docs/" +
		JSF_VDLDOC_VERSION + "/vdldocs/facelets/";
	private static final String HTML_EXTENSION = ".html";
	private static final String JAVADOC_PREFIX = "javadoc:";
	private static final String JAVA_PACKAGE_PREFIX = "java.";
	private static final String JAVAX_PACKAGE_PREFIX = "javax.";
	private static final String JAVA_EE_JAVADOC_BASE_URL;
	private static final String JAVA_SE_JAVADOC_BASE_URL;
	private static final Product LIFERAY_FACES_ALLOY_PRODUCT = ProductFactory.getProduct(
			Product.Name.LIFERAY_FACES_ALLOY);
	private static final String LIFERAY_FACES_ALLOY_PACKAGE_PREFIX = "com.liferay.faces.alloy";
	private static final String LIFERAY_FACES_ALLOY_VERSION = LIFERAY_FACES_ALLOY_PRODUCT.getMajorVersion() + "." +
		LIFERAY_FACES_ALLOY_PRODUCT.getMinorVersion();
	private static final String LIFERAY_FACES_ALLOY_JAVADOC_BASE_URL = "http://www.liferayfaces.org/doc/faces/" +
		LIFERAY_FACES_ALLOY_VERSION + "/javadoc/alloy/";
	private static final String LIFERAY_FACES_BRIDGE_PACKAGE_PREFIX = "com.liferay.faces.bridge";
	private static final Product LIFERAY_FACES_BRIDGE_PRODUCT = ProductFactory.getProduct(
			Product.Name.LIFERAY_FACES_BRIDGE);
	private static final String LIFERAY_FACES_BRIDGE_VERSION = LIFERAY_FACES_BRIDGE_PRODUCT.getMajorVersion() + "." +
		LIFERAY_FACES_BRIDGE_PRODUCT.getMinorVersion();
	private static final String LIFERAY_FACES_BRIDGE_JAVADOC_BASE_URL = "http://www.liferayfaces.org/doc/faces/" +
		LIFERAY_FACES_BRIDGE_VERSION + "/javadoc/bridge-api/";
	private static final Product LIFERAY_FACES_PORTAL_PRODUCT = ProductFactory.getProduct(
			Product.Name.LIFERAY_FACES_PORTAL);
	private static final String LIFERAY_FACES_PORTAL_PACKAGE_PREFIX = "com.liferay.faces.portal";
	private static final String LIFERAY_FACES_PORTAL_VERSION = LIFERAY_FACES_PORTAL_PRODUCT.getMajorVersion() + "." +
		LIFERAY_FACES_PORTAL_PRODUCT.getMinorVersion();
	private static final String LIFERAY_FACES_PORTAL_JAVADOC_BASE_URL = "http://www.liferayfaces.org/doc/faces/" +
		LIFERAY_FACES_PORTAL_VERSION + "/javadoc/portal/";
	private static final String LIFERAY_FACES_PORTLET_PACKAGE_PREFIX = "com.liferay.faces.portlet";
	private static final Product LIFERAY_FACES_UTIL_PRODUCT = ProductFactory.getProduct(
			Product.Name.LIFERAY_FACES_UTIL);
	private static final String LIFERAY_FACES_UTIL_PACKAGE_PREFIX = "com.liferay.faces.util";
	private static final String LIFERAY_FACES_UTIL_VERSION = LIFERAY_FACES_UTIL_PRODUCT.getMajorVersion() + "." +
		LIFERAY_FACES_UTIL_PRODUCT.getMinorVersion();
	private static final String LIFERAY_FACES_UTIL_JAVADOC_BASE_URL = "http://www.liferayfaces.org/doc/faces/" +
		LIFERAY_FACES_UTIL_VERSION + "/javadoc/util/";
	private static final String LIFERAY_FACES_VDLDOC_BASE_URL = "http://www.liferayfaces.org/doc/faces/";
	private static final String NAMESPACE_PREFIX_ALLOY = "alloy";
	private static final String NAMESPACE_PREFIX_AUI = "aui";
	private static final String NAMESPACE_PREFIX_BRIDGE = "bridge";
	private static final String NAMESPACE_PREFIX_C = "c";
	private static final String NAMESPACE_PREFIX_F = "f";
	private static final String NAMESPACE_PREFIX_H = "h";
	private static final String NAMESPACE_PREFIX_LIFERAY_UI = "liferay-ui";
	private static final String NAMESPACE_PREFIX_PORTET = "portlet";
	private static final String NAMESPACE_PREFIX_PORTAL = "portal";
	private static final String NAMESPACE_PREFIX_UI = "ui";
	private static final String PORTLET_API_PACKAGE_PREFIX = "javax.portlet";
	private static final String PORTLET_API_JAVADOC_BASE_URL = "http://docs.liferay.com/portlet-api/2.0/javadocs/";
	private static final String REGEX_COLON = "[:]";
	private static final String REGEX_DOT = "[.]";
	private static final String SINGLE_BACKSLASH_COLON = "\\\\:";
	private static final String STRONG_OPEN = "<strong>";
	private static final String STRONG_CLOSE = "</strong>";
	private static final String VDLDOC_PREFIX = "vdldoc:";

	static {

		String eeJavaDocBaseURL = "http://docs.oracle.com/javaee/6/api/";
		String seJavaDocBaseURL = "http://docs.oracle.com/javase/6/docs/api/";

		if (JSF_PRODUCT.getMinorVersion() >= 2) {
			eeJavaDocBaseURL = "http://docs.oracle.com/javaee/7/api/";
			seJavaDocBaseURL = "http://docs.oracle.com/javase/7/docs/api/";
		}

		JAVA_EE_JAVADOC_BASE_URL = eeJavaDocBaseURL;
		JAVA_SE_JAVADOC_BASE_URL = seJavaDocBaseURL;
	}

	public static Integer columnUnitSize(Integer width) {
		return (int) Math.round(12 * ((double) width / 100));
	}

	public static String doubleEscapeClientId(String clientId) {
		return ComponentUtil.escapeClientId(clientId);
	}

	public static final String encodeDescription(String description) {

		String encodedDescription = description;

		if (description != null) {

			StringBuilder stringBuilder = new StringBuilder();
			String[] parts = BRACKET_DELIMITER_PATTERN.split(description);

			for (String part : parts) {

				if (part.startsWith(JAVADOC_PREFIX)) {
					stringBuilder.append(ANCHOR_ELEMENT_OPEN_START);

					JavaDocKey javaDocKey = new JavaDocKey(part);
					String javaDocURL = encodeJavaDocURL(javaDocKey);
					stringBuilder.append(javaDocURL);
					stringBuilder.append(ANCHOR_ELEMENT_OPEN_FINISH);
					stringBuilder.append(javaDocKey.getClassName());

					String fragment = javaDocKey.getFragment();

					if (fragment != null) {
						stringBuilder.append(".");
						stringBuilder.append(fragment);
					}

					stringBuilder.append(ANCHOR_ELEMENT_CLOSE);
				}
				else if (part.startsWith(VDLDOC_PREFIX)) {

					stringBuilder.append(ANCHOR_ELEMENT_OPEN_START);

					VDLDocKey vdlDocKey = new VDLDocKey(part);
					String vdlDocURL = encodeVDLDocURL(vdlDocKey);
					stringBuilder.append(vdlDocURL);
					stringBuilder.append(ANCHOR_ELEMENT_OPEN_FINISH);

					String attributeName = vdlDocKey.getAttributeName();

					if (attributeName == null) {
						stringBuilder.append(vdlDocKey.getTagPrefix());
						stringBuilder.append(":");
						stringBuilder.append(vdlDocKey.getTagName());
					}
					else {
						stringBuilder.append(attributeName);
					}

					stringBuilder.append(ANCHOR_ELEMENT_CLOSE);
				}
				else {
					stringBuilder.append(encodeSourceCode(encodeStrong(encodeWarning(part))));
				}
			}

			encodedDescription = stringBuilder.toString();
		}

		return encodedDescription;
	}

	public static final String encodeJavaDocURL(JavaDocKey javaDocKey) {

		StringBuilder javaDocURL = new StringBuilder();

		String fqcn = javaDocKey.getFQCN();

		if (fqcn.startsWith(JAVA_PACKAGE_PREFIX)) {
			javaDocURL.append(JAVA_SE_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(PORTLET_API_PACKAGE_PREFIX)) {
			javaDocURL.append(PORTLET_API_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(JAVAX_PACKAGE_PREFIX)) {
			javaDocURL.append(JAVA_EE_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(LIFERAY_FACES_ALLOY_PACKAGE_PREFIX)) {
			javaDocURL.append(LIFERAY_FACES_ALLOY_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(LIFERAY_FACES_BRIDGE_PACKAGE_PREFIX) ||
				fqcn.startsWith(LIFERAY_FACES_PORTLET_PACKAGE_PREFIX)) {
			javaDocURL.append(LIFERAY_FACES_BRIDGE_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(LIFERAY_FACES_PORTAL_PACKAGE_PREFIX)) {
			javaDocURL.append(LIFERAY_FACES_PORTAL_JAVADOC_BASE_URL);
		}
		else if (fqcn.startsWith(LIFERAY_FACES_UTIL_PACKAGE_PREFIX)) {
			javaDocURL.append(LIFERAY_FACES_UTIL_JAVADOC_BASE_URL);
		}
		else {
			logger.error("Unknown JavaDoc fqcn=[{0}]", fqcn);
		}

		String javaClassURLPath = fqcn.replaceAll(REGEX_DOT, "/");
		javaDocURL.append(javaClassURLPath);
		javaDocURL.append(HTML_EXTENSION);

		String fragment = javaDocKey.getFragment();

		if (fragment != null) {

			javaDocURL.append("#");
			javaDocURL.append(fragment);
		}

		return javaDocURL.toString();
	}

	public static final String encodeJavaDocURL(String javaDocKey) {
		return encodeJavaDocURL(new JavaDocKey(javaDocKey));
	}

	public static final String encodeSourceCode(String text) {

		if (text != null) {

			boolean openTag = true;

			int pos = text.indexOf("`");

			while (pos >= 0) {

				if (openTag) {
					text = text.substring(0, pos) + "<code>" + text.substring(pos + 1);
				}
				else {
					text = text.substring(0, pos) + "</code>" + text.substring(pos + 1);
				}

				pos = text.indexOf("`");
				openTag = !openTag;
			}
		}

		return text;
	}

	public static final String encodeStrong(String text) {

		if (text != null) {

			boolean openTag = true;

			int pos = text.indexOf("*");

			while (pos >= 0) {

				if (openTag) {
					text = text.substring(0, pos) + STRONG_OPEN + text.substring(pos + 1);
				}
				else {
					text = text.substring(0, pos) + STRONG_CLOSE + text.substring(pos + 1);
				}

				pos = text.indexOf("*");
				openTag = !openTag;
			}
		}

		return text;
	}

	public static final String encodeVDLDocURL(String vdlDocKey) {
		return encodeVDLDocURL(new VDLDocKey(vdlDocKey));
	}

	public static final String encodeVDLDocURL(VDLDocKey vdlDocKey) {

		StringBuilder vdldocURL = new StringBuilder();

		String tagPrefix = vdlDocKey.getTagPrefix();

		if (tagPrefix.equals(NAMESPACE_PREFIX_C) || tagPrefix.equals(NAMESPACE_PREFIX_F) ||
				tagPrefix.equals(NAMESPACE_PREFIX_H) || tagPrefix.equals(NAMESPACE_PREFIX_UI)) {
			vdldocURL.append(JSF_VDLDOC_BASE_URL);
		}
		else if (tagPrefix.equals(NAMESPACE_PREFIX_ALLOY) || tagPrefix.equals(NAMESPACE_PREFIX_AUI) ||
				tagPrefix.equals(NAMESPACE_PREFIX_BRIDGE) || tagPrefix.equals(NAMESPACE_PREFIX_LIFERAY_UI) ||
				tagPrefix.equals(NAMESPACE_PREFIX_PORTET) || tagPrefix.equals(NAMESPACE_PREFIX_PORTAL)) {

			vdldocURL.append(LIFERAY_FACES_VDLDOC_BASE_URL);
			vdldocURL.append("vdldoc/");

			if (tagPrefix.equals(NAMESPACE_PREFIX_ALLOY)) {
				vdldocURL.append(LIFERAY_FACES_ALLOY_VERSION);
			}
			else if (tagPrefix.equals(NAMESPACE_PREFIX_BRIDGE) || tagPrefix.equals(NAMESPACE_PREFIX_PORTET)) {
				vdldocURL.append(LIFERAY_FACES_BRIDGE_VERSION);
			}
			else if (tagPrefix.equals(NAMESPACE_PREFIX_PORTAL)) {
				vdldocURL.append(LIFERAY_FACES_PORTAL_VERSION);
			}

			vdldocURL.append("/");
		}
		else {
			logger.error("Unknown VDLDoc tagPrefix=[{0}]", tagPrefix);
		}

		vdldocURL.append(tagPrefix);
		vdldocURL.append("/");

		String tagName = vdlDocKey.getTagName();
		vdldocURL.append(tagName);
		vdldocURL.append(HTML_EXTENSION);

		String attributeName = vdlDocKey.getAttributeName();

		if (attributeName != null) {
			vdldocURL.append("#");
			vdldocURL.append(attributeName);
		}

		return vdldocURL.toString();
	}

	public static final String encodeWarning(String text) {

		if (text != null) {

			boolean openTag = true;

			int pos = text.indexOf("!");

			while (pos >= 0) {

				if (openTag) {
					text = text.substring(0, pos) + "<span class=\"inline-warning\">" + text.substring(pos + 1);
				}
				else {
					text = text.substring(0, pos) + "</span>" + text.substring(pos + 1);
				}

				pos = text.indexOf("!");
				openTag = !openTag;
			}
		}

		return text;
	}

	public static String singleEscapeClientId(String clientId) {
		String escapedClientId = clientId;

		if (escapedClientId != null) {

			// JSF clientId values contain colons, which must be preceeded by single backslashes in order to have them
			// work with Bootstrap+jQuery functions.
			escapedClientId = escapedClientId.replaceAll(REGEX_COLON, SINGLE_BACKSLASH_COLON);
		}

		return escapedClientId;
	}

	private static class JavaDocKey {

		private String className;
		private String fqcn;
		private String fragment;

		public JavaDocKey(String key) {

			if (key != null) {

				String[] keyParts = COLON_DELIMITER_PATTERN.split(key);

				if (keyParts.length > 1) {

					if (keyParts[1].contains("#")) {

						String[] fqcnAndFragment = keyParts[1].split("#");
						this.fqcn = fqcnAndFragment[0];
						this.fragment = fqcnAndFragment[1];
					}
					else {
						this.fqcn = keyParts[1];
					}

					int pos = this.fqcn.lastIndexOf(".");

					if (pos > 0) {
						className = this.fqcn.substring(pos + 1);
					}
				}
			}
		}

		public String getClassName() {
			return className;
		}

		public String getFQCN() {
			return fqcn;
		}

		public String getFragment() {
			return fragment;
		}
	}

	private static class VDLDocKey {

		private String tagPrefix;
		private String tagName;
		private String attributeName;

		public VDLDocKey(String key) {

			if (key != null) {
				String[] keyParts = COLON_DELIMITER_PATTERN.split(key);

				if (keyParts.length > 1) {
					this.tagPrefix = keyParts[1];
				}

				if (keyParts.length > 2) {
					this.tagName = keyParts[2];
				}

				if (keyParts.length > 3) {
					attributeName = keyParts[3];
				}
			}
		}

		public String getAttributeName() {
			return attributeName;
		}

		public String getTagName() {
			return tagName;
		}

		public String getTagPrefix() {
			return tagPrefix;
		}
	}

}
