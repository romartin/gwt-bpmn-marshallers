/*
 * Copyright (c) 2015 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Guillaume Hillairet - initial API and implementation
 *
 */
package org.submarine.client.xmi.map;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.ecore.resource.Resource;
import org.submarine.client.xmi.util.StreamHelper;

public abstract class AbstractMapper implements Mapper {

	public abstract void parse(Resource resource, String content, Map<?, ?> options, Callback<Resource> callback);

	public void parse(Resource resource, InputStream inputStream, Map<?, ?> options, Callback<Resource> callback) {
		parse(resource, StreamHelper.toString(inputStream), options, callback);
	}

	public abstract String write(Resource resource, Map<?, ?> options);

	public void write(Resource resource, OutputStream stream, Map<?, ?> options) {
		try {
			String value = write(resource, options);
			stream.write(value.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
