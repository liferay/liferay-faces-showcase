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
package com.liferay.faces.showcase.application.internal;

import javax.faces.component.UIComponent;

import com.liferay.faces.util.application.ResourceDependencyVerifier;
import com.liferay.faces.util.application.ResourceDependencyVerifierWrapper;
import com.liferay.faces.util.product.ProductConstants;
import com.liferay.faces.util.product.ProductMap;


/**
 * @author  Kyle Stiemann
 */
public class ResourceDependencyVerifierShowcaseImpl extends ResourceDependencyVerifierWrapper {

	// Private Constants
	private static final boolean ALLOY_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_FACES_ALLOY)
		.isDetected();
	private static final boolean CRYSTAL_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_FACES_CRYSTAL)
		.isDetected();
	private static final boolean LIFERAY_PORTAL_DETECTED = ProductMap.getInstance().get(ProductConstants.LIFERAY_PORTAL)
		.isDetected();
	private static final String BOOTSTRAP_RESOURCE_ID = getResourceDependencyId("bootstrap", "css/bootstrap.min.css");

	// Private Members
	private ResourceDependencyVerifier wrappedResourceDependencyHandler;

	public ResourceDependencyVerifierShowcaseImpl(ResourceDependencyVerifier wrappedResourceDependencyHandler) {
		this.wrappedResourceDependencyHandler = wrappedResourceDependencyHandler;
	}

	@Override
	public boolean isResourceDependencySatisfied(UIComponent componentResource) {

		boolean resourceDependencySatisfied;

		if ((ALLOY_DETECTED || CRYSTAL_DETECTED || LIFERAY_PORTAL_DETECTED) &&
				BOOTSTRAP_RESOURCE_ID.equals(getResourceDependencyId(componentResource))) {
			resourceDependencySatisfied = true;
		}
		else {
			resourceDependencySatisfied = super.isResourceDependencySatisfied(componentResource);
		}

		return resourceDependencySatisfied;
	}

	@Override
	public ResourceDependencyVerifier getWrapped() {
		return wrappedResourceDependencyHandler;
	}
}
