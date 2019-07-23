package org.eclipse.emf.ecore.xmi.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.CDATASection;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.ProcessingInstruction;
import com.google.gwt.xml.client.Text;

public class GwtDOMHandler {

    private final Document document;

    public GwtDOMHandler(final Document document) {
        this.document = document;
    }

    // TODO: Check
    public Element createElementNS(final String namespaceURI,
                                   final String qualifiedName) {
        GWT.log("createElementNS(" + namespaceURI + ", " + qualifiedName + ")");
        String tagName = qualifiedName;
        int i = qualifiedName.lastIndexOf(':');
        if (i > -1) {
            String[] qn = new String[] {qualifiedName.substring(0, i), qualifiedName.substring(i + 1)};
            i = qn[0].lastIndexOf(':');
            if (i > -1) {
                // TODO: Logger
                GWT.log("[ERROR]: Fixing NS '" + qualifiedName + "'");
                qn[0] = qn[0].substring(i + 1);
            }
            tagName = qn[0] + ":" + qn[1];
        }
        return document.createElement(tagName);
    }

    // TODO: Check
    public Attr createAttributeNS(final String namespaceURI,
                                  final String qualifiedName) {
        GWT.log("createAttributeNS(" + namespaceURI + ", " + qualifiedName + ")");
        return new RuntimeAttr(namespaceURI, qualifiedName);

    }

    /*
        Replaces Element#setAttributeNS(namespaceURI, qualifiedName, value)
     */
    // TODO: Check
    public Attr setAttributeNS(final Node node,
                               final String namespaceURI,
                               final String qualifiedName,
                               final String value) {
        GWT.log("setAttributeNS(" + namespaceURI + ", " + qualifiedName + ", " + value + ")");
        Element e = (Element) node;
        // String fqn = namespaceURI + ":" + qualifiedName;
        String fqn = qualifiedName;
        e.setAttribute(fqn, value);
        return e.getAttributeNode(fqn);
    }

    // TODO: Check
    public Attr setAttributeNodeNS(final Node element,
                                   final Attr attr) {
        return setAttributeNS(element,
                              attr.getNamespaceURI(),
                              attr.getName(),
                              attr.getValue());
    }

    public Element createElement(final String tagName) {
        return document.createElement(tagName);
    }

    public Text createTextNode(final String data) {
        return document.createTextNode(data);
    }

    public Comment createComment(final String data) {
        return document.createComment(data);
    }

    public CDATASection createCDATASection(final String data) {
        return document.createCDATASection(data);
    }

    public ProcessingInstruction createProcessingInstruction(final String target,
                                                             final String data) {
        return document.createProcessingInstruction(target, data);
    }

    public Node appendChild(final Node newChild) {
        GWT.log("appendChild(" + newChild.getNodeValue() + ")");
        return document.appendChild(newChild);
    }

    public Document getDocument() {
        return document;
    }

    // TODO: Check
    private static class RuntimeAttr implements Attr {

        private final String namespaceURI;
        private final String qualifiedName;
        private String value;

        private RuntimeAttr(String namespaceURI,
                            String qualifiedName) {
            this.namespaceURI = namespaceURI;
            this.qualifiedName = qualifiedName;
        }

        @Override
        public String getName() {
            return qualifiedName;
        }

        @Override
        public boolean getSpecified() {
            return true;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public Node appendChild(Node newChild) {
            return null;
        }

        @Override
        public Node cloneNode(boolean deep) {
            return null;
        }

        @Override
        public NamedNodeMap getAttributes() {
            return null;
        }

        @Override
        public NodeList getChildNodes() {
            return null;
        }

        @Override
        public Node getFirstChild() {
            return null;
        }

        @Override
        public Node getLastChild() {
            return null;
        }

        @Override
        public String getNamespaceURI() {
            return namespaceURI;
        }

        @Override
        public Node getNextSibling() {
            return null;
        }

        @Override
        public String getNodeName() {
            return null;
        }

        @Override
        public short getNodeType() {
            return 0;
        }

        @Override
        public String getNodeValue() {
            return null;
        }

        @Override
        public Document getOwnerDocument() {
            return null;
        }

        @Override
        public Node getParentNode() {
            return null;
        }

        @Override
        public String getPrefix() {
            return null;
        }

        @Override
        public Node getPreviousSibling() {
            return null;
        }

        @Override
        public boolean hasAttributes() {
            return false;
        }

        @Override
        public boolean hasChildNodes() {
            return false;
        }

        @Override
        public Node insertBefore(Node newChild, Node refChild) {
            return null;
        }

        @Override
        public void normalize() {

        }

        @Override
        public Node removeChild(Node oldChild) {
            return null;
        }

        @Override
        public Node replaceChild(Node newChild, Node oldChild) {
            return null;
        }

        @Override
        public void setNodeValue(String nodeValue) {
            this.value = nodeValue;
        }
    }
}
