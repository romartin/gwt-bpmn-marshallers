//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package compat.org.xml.sax;

import org.xml.sax.SAXParseException;

public interface ErrorHandler {
    void warning(SAXParseException var1) throws SAXException;

    void error(SAXParseException var1) throws SAXException;

    void fatalError(SAXParseException var1) throws SAXException;
}
