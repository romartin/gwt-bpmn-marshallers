//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.submarine.compat.org.xml.sax.helpers;

import org.submarine.compat.org.xml.sax.SAXException;
import org.submarine.compat.org.xml.sax.Attributes;
import org.submarine.compat.org.xml.sax.ContentHandler;
import org.submarine.compat.org.xml.sax.DTDHandler;
import org.submarine.compat.org.xml.sax.EntityResolver;

public class DefaultHandler implements EntityResolver, DTDHandler, ContentHandler/*,
                                       ErrorHandler */{
    public DefaultHandler() {
    }
//
//    public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
//        return null;
//    }

    public void notationDecl(String name, String publicId, String systemId)  {
    }

    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)  {
    }

//    public void setDocumentLocator(Locator locator) {
//    }
//
    public void startDocument()  {
    }

    public void endDocument()  {
    }

    public void startPrefixMapping(String prefix, String uri)  {
    }

    public void endPrefixMapping(String prefix)  {
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
    }

    public void endElement(String uri, String localName, String qName)  {
    }

    public void characters(char[] ch, int start, int length)  {
    }

    public void ignorableWhitespace(char[] ch, int start, int length)  {
    }

    public void processingInstruction(String target, String data)  {
    }

    public void skippedEntity(String name)  {
    }

//    public void warning(SAXParseException e)  {
//    }
//
//    public void error(SAXParseException e)  {
//    }
//
//    public void fatalError(SAXParseException e)  {
//        throw e;
//    }
}
