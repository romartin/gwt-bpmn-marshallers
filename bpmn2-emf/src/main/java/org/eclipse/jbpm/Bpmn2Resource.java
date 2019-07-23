package org.eclipse.jbpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.resource.xml.XMLSave;
import org.eclipse.emf.ecore.xmi.util.ElementHandler;

public class Bpmn2Resource extends XMLResourceImpl {
    static {
        EPackage.Registry packageRegistry = EPackage.Registry.INSTANCE;
        packageRegistry.put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);
//                packageRegistry.put("http://www.jboss.org/drools", DroolsPackage.eINSTANCE);
    }

    public void load(Node node) throws IOException {
        HashMap<Object, Object> options = new HashMap<>();
//                options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, true);
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, new XmlExtendedMetadata());
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        options.put(XMLResource.OPTION_DISABLE_NOTIFY, true);
        options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
                    XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);

        super.load(node, options);
    }

    public void load(String contents) throws IOException {
        XMLParser parser = GWT.create(XMLParser.class);
        Document doc = parser.parse(contents);
        load(doc);
    }



    public String toBPMN2() throws IOException {
        String raw = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            final Map<Object, Object> options = new HashMap<>();
            options.put(XMLResource.OPTION_DECLARE_XML, true);
            options.put(XMLResource.OPTION_EXTENDED_META_DATA, new XmlExtendedMetadata());
            // TODO options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, new OnlyContainmentTypeInfo());
            options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
                        Boolean.TRUE);
            options.put(XMLResource.OPTION_ELEMENT_HANDLER,
                                               new ElementHandler(true));
            options.put(XMLResource.OPTION_ENCODING,
                                               "UTF-8");
            options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE,
                                               new ArrayList<Object>());
            options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION,
                                               true);
            options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
                                               XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
            this.save(outputStream, options);
            raw = outputStream.toString("UTF-8");
        }
        return raw;
    }

    // TODO: Make this work or not?
    public String toBPMN2Test() throws IOException {
        final Map<Object, Object> options = new HashMap<>();
        this.save(options);
        String raw = (String) options.get("RESPONSE");
        return raw;
    }

    @Override
    protected XMLSave createXMLSave() {
        return new JBPMXMLSave(createXMLHelper()) {
            @Override
            protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
                if (Bpmn2Package.eINSTANCE.getDocumentation_Text().equals(f)) {
                    return false;
                }
                if (Bpmn2Package.eINSTANCE.getFormalExpression_Body().equals(f)) {
                    return false;
                }
                return super.shouldSaveFeature(o,
                                               f);
            }
        };
    }
}
