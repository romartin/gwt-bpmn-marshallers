package org.eclipse.emf.ecore.xmi.resource.xml;

public class XMLInfo {

    public static int UNSPECIFIED = -1;
    public static int ELEMENT = 0;
    public static int ATTRIBUTE = 1;
    public static int CONTENT = 2;

    protected int xmlRepresentation;
    protected String targetNamespace;
    protected String name;

    public XMLInfo()
    {
        xmlRepresentation = UNSPECIFIED;
    }

    /**
     * Returns ELEMENT if the Ecore construct is to be serialized
     * as an XML element; ATTRIBUTE if the Ecore construct is
     * to be serialized as an XML attribute; and CONTENT if the
     * Ecore construct is to be serialized in element content.
     * By default, the value is UNSPECIFIED.
     */
    public int getXMLRepresentation()
    {
        return xmlRepresentation;
    }

    /**
     * Set attribute to true to serialize a feature as an
     * XML attribute.
     */
    public void setXMLRepresentation(int representation)
    {
        xmlRepresentation = representation;
    }

    public String getTargetNamespace()
    {
        return targetNamespace;
    }

    public void setTargetNamespace(String namespaceURI)
    {
        targetNamespace = namespaceURI;
    }

    /**
     * Returns the name to use for the Ecore construct in an
     * XML file.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name to be used in an XML file.
     */
    public void setName(String name)
    {
        this.name = name;
    }


}
