package org.submarine.client;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Extension;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.util.NamespaceHelper;
import org.eclipse.bpmn2.util.XmlExtendedMetadata;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class Bpmn2Resource extends XMLResourceImpl {

    private static HashMap<Object, Object> options = new HashMap<>();
    private static XMLParser parser = GWT.create(XMLParser.class);
    protected Bpmn2OppositeReferenceAdapter oppositeReferenceAdapter = new Bpmn2OppositeReferenceAdapter();

    static {
        EPackage.Registry packageRegistry = EPackage.Registry.INSTANCE;
        packageRegistry.put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);
//                packageRegistry.put("http://www.jboss.org/drools", DroolsPackage.eINSTANCE);

//                options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, true);
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, new XmlExtendedMetadata());
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        options.put(XMLResource.OPTION_DISABLE_NOTIFY, true);
        options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
                    XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
//        options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION,
//                    true);
        options.put(XMLResource.OPTION_ELEMENT_HANDLER,
                                           new ElementHandlerImpl(true));

        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION,
                                         true);
        options.put(XMLResource.OPTION_DISABLE_NOTIFY,
                                         true);
//        this.getDefaultLoadOptions().put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP,
//                                         xmlNameToFeatureMap);


    }

    public Bpmn2Resource() {
        this.eAdapters().add(this.oppositeReferenceAdapter);
    }

    public void load(Node node) throws IOException {

        super.load(node, options);
    }

    public void load(String contents) throws IOException {
        Document doc = parser.parse(contents);
        load(doc);
    }

    public Document save() {
        Document doc = parser.createDocument();
        this.save(doc, options, null);
        return doc;
    }


    public static class Bpmn2OppositeReferenceAdapter extends ECrossReferenceAdapter {
        public static final Map<EReference, EReference> DEFAULT_OBSERVED_REFERENCES = new HashMap();
        protected Map<EReference, EReference> observedRefToOpposite;

        static {
            DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.FLOW_ELEMENT__CATEGORY_VALUE_REF, Bpmn2Package.Literals.CATEGORY_VALUE__CATEGORIZED_FLOW_ELEMENTS);
            DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.CONVERSATION_LINK__SOURCE_REF, Bpmn2Package.Literals.INTERACTION_NODE__OUTGOING_CONVERSATION_LINKS);
            DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.CONVERSATION_LINK__TARGET_REF, Bpmn2Package.Literals.INTERACTION_NODE__INCOMING_CONVERSATION_LINKS);
        }

        public Bpmn2OppositeReferenceAdapter(Map<EReference, EReference> observedRefToOpposite) {
            this.observedRefToOpposite = new HashMap();
            this.observedRefToOpposite.putAll(observedRefToOpposite);
        }

        public Bpmn2OppositeReferenceAdapter() {
            this(DEFAULT_OBSERVED_REFERENCES);
        }

        public EReference putObservedRefToOpposite(EReference key, EReference value) {
            return (EReference)this.observedRefToOpposite.put(key, value);
        }

        public EReference removeObservedRef(Object key) {
            return (EReference)this.observedRefToOpposite.remove(key);
        }

        protected boolean isIncluded(EReference eReference) {
            return this.observedRefToOpposite.containsKey(eReference);
        }

        public <E> List<E> getOppositeList(Class<E> dataClass, InternalEObject owner, EReference reference) {
            EReference opposite = (EReference)this.observedRefToOpposite.get(reference);
            if (opposite == null) {
                throw new IllegalArgumentException("This reference is not observed by this adapter: " + reference.toString());
            } else {
                EObjectEList<E> result = new EObjectEList(dataClass, owner, opposite.getFeatureID());
                Iterator var7 = this.getNonNavigableInverseReferences(owner, false).iterator();

                while(var7.hasNext()) {
                    EStructuralFeature.Setting cur = (EStructuralFeature.Setting)var7.next();
                    if (cur.getEStructuralFeature().equals(reference)) {
                        result.add((E) cur.getEObject());
                    }
                }

                return result;
            }
        }
    }
}
