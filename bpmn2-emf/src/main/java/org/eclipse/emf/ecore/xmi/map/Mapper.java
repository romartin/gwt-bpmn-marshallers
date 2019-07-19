package org.eclipse.emf.ecore.xmi.map;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.ecore.resource.Resource;

public interface Mapper {

    void parse(Resource resource, InputStream inputStream, Map<?, ?> options, Callback<Resource> callback);

    void write(Resource resource, OutputStream stream, Map<?, ?> options);

}
