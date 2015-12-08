/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.showcase.application.internal;

import java.util.HashSet;
import java.util.Set;

import com.liferay.faces.util.application.ComponentResource;
import com.liferay.faces.util.application.ComponentResourceWrapper;
import com.liferay.faces.util.product.ProductConstants;
import com.liferay.faces.util.product.ProductMap;


/**
 * This class provides the Liferay Faces Showcase with the opportunity to avoid rendering certain css resources because
 * they will be provided by other libraries.
 *
 * @author  Neil Griffin
 */
public class ComponentResourceShowcaseImpl extends ComponentResourceWrapper {

	// Private Constants
	private static final boolean ALLOY_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_FACES_ALLOY)
		.isDetected();
	private static final boolean CRYSTAL_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_FACES_CRYSTAL)
		.isDetected();
	private static final boolean LIFERAY_PORTAL_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_PORTAL)
		.isDetected();
	private static final Set<String> RESOURCE_IDS = new HashSet<String>();

	static {
		RESOURCE_IDS.add("bootstrap:css/bootstrap.min.css");
	}

	// Private Data Members
	private ComponentResource wrappedComponentResource;

	public ComponentResourceShowcaseImpl(ComponentResource componentResource) {
		this.wrappedComponentResource = componentResource;
	}

	@Override
	public boolean isRenderable() {

		boolean renderable = super.isRenderable();

		if (ALLOY_DETECTED || CRYSTAL_DETECTED || LIFERAY_PORTAL_DETECTED) {
			String id = super.getId();

			if (RESOURCE_IDS.contains(id)) {
				renderable = false;
			}
		}

		return renderable;
	}

	@Override
	public ComponentResource getWrapped() {
		return wrappedComponentResource;
	}
}
