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
package com.liferay.faces.showcase.servlet;

import java.io.File;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * This class provides the ability to listen for session destroyed events so that temporary files can be deleted.
 *
 * @author  Kyle Stiemann
 */
public class UploadedFileCleanupListener implements HttpSessionListener {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(UploadedFileCleanupListener.class);

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		// no-op
	}

	/**
	 * This method will be called by the servlet container when either the session expires or the user logs out of the
	 * portal. Its purpose is to delete any uploaded temporary files from the file system.
	 */
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

		try {

			String sessionId = httpSessionEvent.getSession().getId();
			String parent = UploadedFileUtil.getTempDir();
			String folderName = sessionId;
			File folder = new File(parent, folderName);
			File[] fileList = folder.listFiles();

			if (fileList != null) {

				fileList = fileList.clone();

				for (File file : fileList) {

					file.delete();
				}
			}
		}
		catch (Exception e) {
			logger.error(e);
		}
	}
}
