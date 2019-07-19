package org.eclipse.emf.ecore.xmi.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.map.Mapper;
import org.eclipse.emf.ecore.xmi.map.XmlMapper;

public class XmlResourceViaMapper extends MapperResource {

    public XmlResourceViaMapper(URI uri) {
        super(uri);
    }

    @Override
    protected Mapper getMapper() {
        return new XmlMapper();
    }
}
