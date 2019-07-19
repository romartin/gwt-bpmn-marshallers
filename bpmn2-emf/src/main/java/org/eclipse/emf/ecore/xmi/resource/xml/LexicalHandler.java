package org.eclipse.emf.ecore.xmi.resource.xml;

// TODO: Remove?

public interface LexicalHandler {

    void startDTD(String var1, String var2, String var3) throws XMLParseException;

    void endDTD() throws XMLParseException;

    void startEntity(String var1) throws XMLParseException;

    void endEntity(String var1) throws XMLParseException;

    void startCDATA() throws XMLParseException;

    void endCDATA() throws XMLParseException;

    void comment(char[] var1, int var2, int var3) throws XMLParseException;

}
