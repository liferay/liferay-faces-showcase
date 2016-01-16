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

import com.liferay.faces.util.application.ResourceDependencyVerifier;
import com.liferay.faces.util.application.ResourceDependencyVerifierFactory;


/**
 * @author  Kyle Stiemann
 */
public class ResourceDependencyVerifierFactoryShowcaseImpl extends ResourceDependencyVerifierFactory {

	// Private Members
	private ResourceDependencyVerifierFactory wrappedDependencyVerifierFactory;

	public ResourceDependencyVerifierFactoryShowcaseImpl(
		ResourceDependencyVerifierFactory wrappedDependencyVerifierFactory) {
		this.wrappedDependencyVerifierFactory = wrappedDependencyVerifierFactory;
	}

	@Override
	public ResourceDependencyVerifier getResourceDependencyVerifier() {

		ResourceDependencyVerifier resourceDependencyVerifier =
			wrappedDependencyVerifierFactory.getResourceDependencyVerifier();

		return new ResourceDependencyVerifierShowcaseImpl(resourceDependencyVerifier);
	}

	@Override
	public ResourceDependencyVerifierFactory getWrapped() {
		return wrappedDependencyVerifierFactory;
	}
}
