package org.submarine.client.xmi.map;

import java.io.IOException;
import java.util.Map;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.ecore.resource.Resource;
import org.submarine.client.xmi.resource.XMLResource;

public class XmlMapper extends AbstractMapper {

    @Override
    public void parse(Resource resource,
                      String content,
                      Map<?, ?> options,
                      Callback<Resource> callback) {

/*
        //options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, true);
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, new XmlExtendedMetadata());
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        options.put(XMLResource.OPTION_DISABLE_NOTIFY, true);
        options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
                    XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
*/

        Document document = XMLParser.parse(content);

        try {
            ((XMLResource) resource).load(document, options);
            callback.onSuccess(resource);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(e);
        }
    }

    @Override
    public String write(Resource resource,
                        Map<?, ?> options) {
        // TODO
        throw new UnsupportedOperationException();
    }

}
