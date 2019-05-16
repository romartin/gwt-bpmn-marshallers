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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tInputOutputBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tInputOutputBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement"&gt;
 *       &lt;attribute name="operationRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;attribute name="inputDataRef" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *       &lt;attribute name="outputDataRef" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInputOutputBinding")
public class TInputOutputBinding
    extends TBaseElement
    implements Visitable
{

    @XmlAttribute(name = "operationRef", required = true)
    protected QName operationRef;
    @XmlAttribute(name = "inputDataRef", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object inputDataRef;
    @XmlAttribute(name = "outputDataRef", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object outputDataRef;

    /**
     * Gets the value of the operationRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getOperationRef() {
        return operationRef;
    }

    /**
     * Sets the value of the operationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setOperationRef(QName value) {
        this.operationRef = value;
    }

    /**
     * Gets the value of the inputDataRef property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getInputDataRef() {
        return inputDataRef;
    }

    /**
     * Sets the value of the inputDataRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setInputDataRef(Object value) {
        this.inputDataRef = value;
    }

    /**
     * Gets the value of the outputDataRef property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOutputDataRef() {
        return outputDataRef;
    }

    /**
     * Sets the value of the outputDataRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOutputDataRef(Object value) {
        this.outputDataRef = value;
    }

    public<R, E extends Throwable >R accept(Visitor<R, E> aVisitor)
        throws E
    {
        return aVisitor.visit(this);
    }

}
