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
import javax.faces.context.FacesContext;

import com.liferay.faces.util.application.ResourceUtil;
import com.liferay.faces.util.application.ResourceVerifier;
import com.liferay.faces.util.application.ResourceVerifierWrapper;
import com.liferay.faces.util.product.Product;
import com.liferay.faces.util.product.ProductFactory;


/**
 * @author  Kyle Stiemann
 */
public class ResourceVerifierShowcaseImpl extends ResourceVerifierWrapper {

	// Private Constants
	private static final boolean ALLOY_DETECTED = ProductFactory.getProduct(Product.Name.LIFERAY_FACES_ALLOY)
		.isDetected();
	private static final Product LIFERAY_PORTAL = ProductFactory.getProduct(Product.Name.LIFERAY_PORTAL);
	private static final boolean METAL_DETECTED = ProductFactory.getProduct(Product.Name.LIFERAY_FACES_METAL)
		.isDetected();
	private static final boolean BOOTSTRAP_SATISFIED = (ALLOY_DETECTED || LIFERAY_PORTAL.isDetected() ||
			METAL_DETECTED);
	private static final String BOOTSTRAP_RESOURCE_ID = ResourceUtil.getResourceId("bootstrap",
			"css/bootstrap.min.css");
	private static final boolean JQUERY_SATISFIED = (LIFERAY_PORTAL.isDetected() &&
			(LIFERAY_PORTAL.getMajorVersion() >= 7));
	private static final String JQUERY_JS_RESOURCE_ID = ResourceUtil.getResourceId("bootstrap", "js/jquery.min.js");

	// Private Members
	private ResourceVerifier wrappedResourceDependencyHandler;

	public ResourceVerifierShowcaseImpl(ResourceVerifier resourceVerifier) {
		this.wrappedResourceDependencyHandler = resourceVerifier;
	}

	@Override
	public ResourceVerifier getWrapped() {
		return wrappedResourceDependencyHandler;
	}

	@Override
	public boolean isDependencySatisfied(FacesContext facestContext, UIComponent componentResource) {

		String resourceId = ResourceUtil.getResourceId(componentResource);

		if (BOOTSTRAP_SATISFIED && BOOTSTRAP_RESOURCE_ID.equals(resourceId)) {
			return true;
		}
		else if (JQUERY_SATISFIED && JQUERY_JS_RESOURCE_ID.equals(resourceId)) {
			return true;
		}
		else {
			return super.isDependencySatisfied(facestContext, componentResource);
		}
	}
}
