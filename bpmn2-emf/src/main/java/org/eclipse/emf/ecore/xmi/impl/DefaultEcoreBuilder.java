package org.eclipse.emf.ecore.xmi.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.EcoreBuilder;

public class DefaultEcoreBuilder implements EcoreBuilder {

    private ExtendedMetaData extendedMetaData;

    public DefaultEcoreBuilder(final ExtendedMetaData extendedMetaData) {
        this.extendedMetaData = extendedMetaData;
    }

    @Override
    public Collection<? extends Resource> generate(URI uri) throws Exception {
        return generate(Collections.singleton(uri));
    }

    @Override
    public Collection<? extends Resource> generate(Collection<URI> uris) throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Collection<? extends Resource> generate(Map<String, URI> targetNamespaceToURI) throws Exception {
        return Collections.emptyList();
    }

    @Override
    public void setExtendedMetaData(ExtendedMetaData extendedMetaData) {
        this.extendedMetaData = extendedMetaData;
    }
}
