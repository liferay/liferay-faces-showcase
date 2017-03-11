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
package com.liferay.faces.showcase.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.liferay.faces.showcase.dto.UploadedFilePart;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.faces.util.model.UploadedFile;


/**
 * @author  Neil Griffin
 */
@RequestScoped
@ManagedBean
public class HtmlInputFileBackingBean {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(HtmlInputFileBackingBean.class);

	// Injections
	@ManagedProperty(value = "#{htmlInputFileModelBean}")
	private HtmlInputFileModelBean htmlInputFileModelBean;

	// Private Data Members
	private Part uploadedPart;

	public void deleteUploadedFile(ActionEvent actionEvent) {

		UICommand uiCommand = (UICommand) actionEvent.getComponent();
		String fileId = (String) uiCommand.getValue();

		try {
			List<UploadedFile> uploadedFiles = htmlInputFileModelBean.getUploadedFiles();

			UploadedFile uploadedFileToDelete = null;

			for (UploadedFile uploadedFile : uploadedFiles) {

				if (uploadedFile.getId().equals(fileId)) {
					uploadedFileToDelete = uploadedFile;

					break;
				}
			}

			if (uploadedFileToDelete != null) {
				uploadedFileToDelete.delete();
				uploadedFiles.remove(uploadedFileToDelete);
				logger.debug("Deleted file=[{0}]", uploadedFileToDelete.getName());
			}
		}
		catch (Exception e) {
			logger.error(e);
		}
	}

	public Part getUploadedPart() {
		return uploadedPart;
	}

	public void setHtmlInputFileModelBean(HtmlInputFileModelBean htmlInputFileModelBean) {

		// Injected via @ManagedProperty annotation
		this.htmlInputFileModelBean = htmlInputFileModelBean;
	}

	public void setUploadedPart(Part uploadedPart) {
		this.uploadedPart = uploadedPart;

		String id = Long.toString(((long) hashCode()) + System.currentTimeMillis());
		UploadedFile uploadedFile = new UploadedFilePart(uploadedPart, id, UploadedFile.Status.FILE_SAVED);
		htmlInputFileModelBean.getUploadedFiles().add(uploadedFile);
	}
}
