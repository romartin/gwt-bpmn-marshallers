package org.submarine.client.xmi.util;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.CDATASection;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.ProcessingInstruction;
import com.google.gwt.xml.client.Text;

public class GwtDOMHandler {

    private final Document document;

    public GwtDOMHandler(final Document document) {
        this.document = document;
    }

    public Element createElementNS(final String namespaceURI,
                                   final String qualifiedName) {
        // TODO: Check use of namespaceURI
        return document.createElement(qualifiedName);
    }

    public Attr setAttributeNS(final Node node,
                               final String namespaceURI,
                               final String qualifiedName,
                               final String value) {
        // TODO: Check (use of namespaceURI?)
        Element e = (Element) node;
        // String fqn = namespaceURI + ":" + qualifiedName;
        String fqn = qualifiedName;
        e.setAttribute(fqn, value);
        return e.getAttributeNode(fqn);
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
        return document.appendChild(newChild);
    }

    public Document getDocument() {
        return document;
    }
}
