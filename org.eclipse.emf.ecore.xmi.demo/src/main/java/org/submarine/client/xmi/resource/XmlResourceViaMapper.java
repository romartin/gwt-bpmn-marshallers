package org.submarine.client.xmi.resource;

import org.eclipse.emf.common.util.URI;
import org.submarine.client.xmi.map.Mapper;
import org.submarine.client.xmi.map.XmlMapper;

public class XmlResourceViaMapper extends MapperResource {

    public XmlResourceViaMapper(URI uri) {
        super(uri);
    }

    @Override
    protected Mapper getMapper() {
        return new XmlMapper();
    }
}
