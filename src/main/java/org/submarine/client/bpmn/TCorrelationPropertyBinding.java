//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.06 at 05:11:55 PM BRT 
//


package org.submarine.client.bpmn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tCorrelationPropertyBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tCorrelationPropertyBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dataPath" type="{http://www.omg.org/spec/BPMN/20100524/MODEL}tFormalExpression"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="correlationPropertyRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCorrelationPropertyBinding", propOrder = {
    "dataPath"
})
public class TCorrelationPropertyBinding
    extends TBaseElement
    implements Visitable
{

    @XmlElement(required = true)
    protected TFormalExpression dataPath;
    @XmlAttribute(name = "correlationPropertyRef", required = true)
    protected QName correlationPropertyRef;

    /**
     * Gets the value of the dataPath property.
     * 
     * @return
     *     possible object is
     *     {@link TFormalExpression }
     *     
     */
    public TFormalExpression getDataPath() {
        return dataPath;
    }

    /**
     * Sets the value of the dataPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFormalExpression }
     *     
     */
    public void setDataPath(TFormalExpression value) {
        this.dataPath = value;
    }

    /**
     * Gets the value of the correlationPropertyRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getCorrelationPropertyRef() {
        return correlationPropertyRef;
    }

    /**
     * Sets the value of the correlationPropertyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setCorrelationPropertyRef(QName value) {
        this.correlationPropertyRef = value;
    }

    public<R, E extends Throwable >R accept(Visitor<R, E> aVisitor)
        throws E
    {
        return aVisitor.visit(this);
    }

}
