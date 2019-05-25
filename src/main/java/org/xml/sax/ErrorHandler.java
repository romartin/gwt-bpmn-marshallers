//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.xml.sax;

public interface ErrorHandler {
    void warning(SAXParseException var1) throws SAXException;

    void error(SAXParseException var1) throws SAXException;

    void fatalError(SAXParseException var1) throws SAXException;
}
