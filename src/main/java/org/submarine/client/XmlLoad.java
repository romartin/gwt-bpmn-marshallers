package org.submarine.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.submarine.client.eclipse.bpmn2.Bpmn2Package;
import org.submarine.client.eclipse.bpmn2.DocumentRoot;
import org.submarine.client.eclipse.bpmn2.impl.Bpmn2PackageImpl;

public class XmlLoad {

    List<String> errors = new ArrayList<>();
    EPackage.Registry registry = EPackage.Registry.INSTANCE;
    Map<String, EStructuralFeature> topLevelMapping;


    public XmlLoad() {
        Bpmn2PackageImpl.init();
        topLevelMapping = getNameTypeMapping(Bpmn2Package.eINSTANCE.getDocumentRoot());
    }

    private Map<String, EStructuralFeature> getNameTypeMapping(EClass eClass) {
        Map<String, EStructuralFeature> allElements = new HashMap<>();
        for (EStructuralFeature feat : eClass.getEAllStructuralFeatures()) {
            try {
//                EClass c = ((EReferenceImpl) feat).getEReferenceType();
                allElements.put(feat.getName(), feat);
                errors.add(feat.getName());
            } catch (Exception e) {
//                errors.add("IGNORED FEAT: " + feat);
            }
        }
//        errors.add(allElements.keySet().toString());
        return allElements;
    }

    public List<EObject> parse(String text) {
        return load(XMLParser.parse(text));
    }

    public List<String> getErrors() {
        return errors;
    }

    List<EObject> load(Document doc) {
        List<EObject> result = new ArrayList<>();
        Element element = doc.getDocumentElement();
        EClass documentRoot = Bpmn2Package.eINSTANCE.getDocumentRoot();
        DocumentRoot docRoot = Bpmn2Package.eINSTANCE.getBpmn2Factory().createDocumentRoot();
        result.addAll(load(element, docRoot));
//        typeMapping.put("ExtensionElements", "ExtensionAttributeValue");
//        typeMapping.put("Incoming", "SequenceFlow");
//        typeMapping.put("Outgoing", "SequenceFlow");
//        typeMapping.put("IoSpecification", "InputOutputSpecification");
        return result;
    }

    List<EObject> load(Element element, EObject instance) {
        Map<String, EStructuralFeature> nameTypeMapping = getNameTypeMapping(instance.eClass());

        List<EObject> result = new ArrayList<>();
        NodeList childNodes = element.getChildNodes();
        EObject lastNode = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            errors.add("Load child of " + element.getTagName() + " :: " + node.getNodeName());
            try {
                switch (node.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element childElement = (Element) node;

                        String namespaceURI = childElement.getNamespaceURI();
                        String nodeName = childElement.getNodeName();
                        String localName = localName(nodeName);



                        EStructuralFeature eFeat = topLevelMapping.getOrDefault(localName, nameTypeMapping.get(localName));
                        if (eFeat == null) {
                            errors.add("ignored node " + localName);
                            continue;
                        }

                        EFactory eFactory = registry.getEFactory(namespaceURI);
//                        EPackage ePackage = eFactory.getEPackage();
//                        EClassifier eClassifier = ePackage.getEClassifier(className);
//                        if (eClassifier == null) {
//                            String map = typeMapping.get(className);
//                            if (map == null) continue;
//                            eClassifier = ePackage.getEClassifier(map);
//                            if (eClassifier == null) continue;
//                        }
//                        EClass eClass = (EClass) eClassifier;
                        if (!(eFeat instanceof EReferenceImpl)) {
                            errors.add(eFeat.getClass().toString());
                            continue;
                        }
                        EObject eObject = eFactory.create(((EReferenceImpl)eFeat).getEReferenceType().eClass());
                        result.add(eObject);//eClassifier.toString();
                        errors.add("created " + eObject);

                        NamedNodeMap attributes = childElement.getAttributes();
                        if (attributes != null) {
                            for (int j = 0; j < attributes.getLength(); j++) {
                                Node attr = attributes.item(j);
//                                parseAttribute(eFeat, eObject, attr);
                            }
                        }

//                        instance.eContents().add(eObject);

                        List<EObject> children = load(childElement, eObject);
//                        eObject.eSet(elementClass.getEStructuralFeature(eClass.getFeatureCount()), eObject);


//                        lastNode = eObject;
                        break;
                    case Node.TEXT_NODE:
                        if (lastNode != null) {
//                            lastNode.eContents().add;
                        }
                        break;
                    case Node.CDATA_SECTION_NODE:
                        break;
                    default:
                        errors.add("Ignored node type: " + node.getNodeType());
                }
            } catch (Exception e) {
                errors.add("Ignored node " + node.getNodeName() + " -- exception " + e);
                e.printStackTrace();
            }


        }
        return result;
    }


    public List<EStructuralFeature> getAllElements(EClass eClass)
    {
        List<EClass> superTypes = eClass.getESuperTypes();
        List<EStructuralFeature> result = null;
        boolean changeable = false;
        for (int i = 0, size = superTypes.size(); i < size; ++i)
        {
            EClass eSuperType = superTypes.get(i);
            List<EStructuralFeature> allElements =  getAllElements(eSuperType);
            if (!allElements.isEmpty())
            {
                if (result == null)
                {
                    result = allElements;
                }
                else
                {
                    if (!changeable)
                    {
                        changeable = true;
                        result = new UniqueEList<EStructuralFeature>(result);
                    }
                    result.addAll(allElements);
                }
            }
        }
        List<EStructuralFeature> elements = getElements(eClass);
        if (!elements.isEmpty())
        {
            if (result == null)
            {
                return elements;
            }
            else
            {
                if (!changeable)
                {
                    result = new UniqueEList<EStructuralFeature>(result);
                }
                result.addAll(elements);
                return result;
            }
        }
        else
        {
            return result == null ? Collections.<EStructuralFeature>emptyList() : result;
        }
    }

    public List<EStructuralFeature> getAttributes(EClass eClass)
    {
        List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
        List<EStructuralFeature> result = null;
        for (int i = 0, size = eStructuralFeatures.size(); i < size; ++i)
        {
            EStructuralFeature eStructuralFeature = eStructuralFeatures.get(i);
//            switch (getFeatureKind(eStructuralFeature))
//            {
//                case ATTRIBUTE_FEATURE:
//                case ATTRIBUTE_WILDCARD_FEATURE:
//                {
                    if (result == null)
                    {
                        result = new ArrayList<EStructuralFeature>();
                    }
                    result.add(eStructuralFeature);
//                }
//            }
        }
        return result == null ? Collections.<EStructuralFeature>emptyList() : result;
    }

    public List<EStructuralFeature> getElements(EClass eClass)
    {
        List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
        List<EStructuralFeature> result = null;
        for (int i = 0, size = eStructuralFeatures.size(); i < size; ++i)
        {
            EStructuralFeature eStructuralFeature = eStructuralFeatures.get(i);
//            switch (getFeatureKind(eStructuralFeature))
//            {
//                case ELEMENT_FEATURE:
//                case ELEMENT_WILDCARD_FEATURE:
//                case GROUP_FEATURE:
                {
                    if (result == null)
                    {
                        result = new ArrayList<EStructuralFeature>();
                    }
                    result.add(eStructuralFeature);
                    break;
                }
//            }
        }

        return result == null ? Collections.<EStructuralFeature>emptyList() : result;
    }


    private void addNodes(List<Node> nodes, NodeList childNodes) {
        for (int i = 0; i < childNodes.getLength(); i++) {
            nodes.add(childNodes.item(i));
        }
    }

    private void parseAttribute(EClass eClass, EObject eObject, Node attr) {
        String name = attr.getNodeName();
        String value = attr.getNodeValue();
        try {
            EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(name);
            if (eStructuralFeature == null) {
                errors.add("IGNORED ATTRIB: " + name);
                return;
            }
            try {
                Object convertedValue = convertValue(value, eStructuralFeature);
                eObject.eSet(eStructuralFeature, convertedValue);
            } catch (ClassCastException cc) {
                errors.add("could not set attribute " + name + " to " + value);
            }
        } catch (Exception e) {
            errors.add("could not get feature " + name + " for eClass" + eClass);
        }
    }

    private Object convertValue(String value, EStructuralFeature eStructuralFeature) {
        EClassifier eClassifier = eStructuralFeature.getEType();
        EDataType eDataType = (EDataType) eClassifier;
        EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
        return eFactory.createFromString(eDataType, value);
    }

    private String localName(String nodeName) {
        return nodeName.split(":")[1];
    }
    private String className(String nodeName) {
        String className = nodeName.split(":")[1];
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }
}
