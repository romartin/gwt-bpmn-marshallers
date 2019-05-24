package org.submarine.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.submarine.client.eclipse.bpmn2.impl.Bpmn2PackageImpl;

public class XmlLoad {

    List<String> errors = new ArrayList<>();
    EPackage.Registry registry = EPackage.Registry.INSTANCE;

    public XmlLoad() {
        Bpmn2PackageImpl.init();
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
        result.addAll(load(element));
        return result;
    }

    List<EObject> load(Element element) {
        List<EObject> result = new ArrayList<>();
        NodeList childNodes = element.getChildNodes();
        EObject lastNode = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            try {
                switch (node.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element childElement = (Element) node;

                        String namespaceURI = childElement.getNamespaceURI();
                        String nodeName = childElement.getNodeName();
                        String className = className(nodeName);

                        EFactory eFactory = registry.getEFactory(getNamespaceURI(namespaceURI));
                        EPackage ePackage = eFactory.getEPackage();
                        EClassifier eClassifier = ePackage.getEClassifier(className);
                        EClass eClass = (EClass) eClassifier;
                        EObject eObject = eFactory.create(eClass);

                        NamedNodeMap attributes = childElement.getAttributes();
                        if (attributes != null) {
                            for (int j = 0; j < attributes.getLength(); j++) {
                                Node attr = attributes.item(j);
                                parseAttribute(eClass, eObject, attr);
                            }
                        }

                        List<EObject> children = load(childElement);
                        eObject.eContents().addAll(children);

                        result.add(eObject);//eClassifier.toString();

                        lastNode = eObject;
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
                errors.add("Ignored node " + node + " -- exception " + e);
                e.printStackTrace();
            }


        }
        return result;
    }

    private String getNamespaceURI(String namespaceURI) {
        return namespaceURI + "-XMI";
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

    private String className(String nodeName) {
        String className = nodeName.split(":")[1];
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }
}
