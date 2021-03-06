//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.12 at 02:04:11 PM MST 
//


package com.immco.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subtasktype" type="{}tasktype"/>
 *         &lt;element name="uibean" type="{}uiBean" minOccurs="0"/>
 *         &lt;element name="endpoint" type="{}endPoint" minOccurs="0"/>
 *         &lt;element name="successorid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="predecessorid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sla-exec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sla-metrics" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sla-pickup-min" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phase-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queue-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="concurrent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "id",
    "description",
    "subtasktype",
    "uibean",
    "endpoint",
    "successorid",
    "predecessorid",
    "slaExec",
    "slaMetrics",
    "slaPickupMin",
    "phaseId",
    "queueId",
    "concurrent"
})
@XmlRootElement(name = "subtask")
public class Subtask {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Tasktype subtasktype;
    @XmlSchemaType(name = "string")
    protected UiBean uibean;
    @XmlSchemaType(name = "string")
    protected EndPoint endpoint;
    @XmlElement(required = true)
    protected String successorid;
    @XmlElement(required = true)
    protected String predecessorid;
    @XmlElement(name = "sla-exec")
    protected String slaExec;
    @XmlElement(name = "sla-metrics")
    protected String slaMetrics;
    @XmlElement(name = "sla-pickup-min")
    protected String slaPickupMin;
    @XmlElement(name = "phase-id", required = true)
    protected String phaseId;
    @XmlElement(name = "queue-id", required = true)
    protected String queueId;
    protected boolean concurrent;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the subtasktype property.
     * 
     * @return
     *     possible object is
     *     {@link Tasktype }
     *     
     */
    public Tasktype getSubtasktype() {
        return subtasktype;
    }

    /**
     * Sets the value of the subtasktype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tasktype }
     *     
     */
    public void setSubtasktype(Tasktype value) {
        this.subtasktype = value;
    }

    /**
     * Gets the value of the uibean property.
     * 
     * @return
     *     possible object is
     *     {@link UiBean }
     *     
     */
    public UiBean getUibean() {
        return uibean;
    }

    /**
     * Sets the value of the uibean property.
     * 
     * @param value
     *     allowed object is
     *     {@link UiBean }
     *     
     */
    public void setUibean(UiBean value) {
        this.uibean = value;
    }

    /**
     * Gets the value of the endpoint property.
     * 
     * @return
     *     possible object is
     *     {@link EndPoint }
     *     
     */
    public EndPoint getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the value of the endpoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndPoint }
     *     
     */
    public void setEndpoint(EndPoint value) {
        this.endpoint = value;
    }

    /**
     * Gets the value of the successorid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessorid() {
        return successorid;
    }

    /**
     * Sets the value of the successorid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessorid(String value) {
        this.successorid = value;
    }

    /**
     * Gets the value of the predecessorid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredecessorid() {
        return predecessorid;
    }

    /**
     * Sets the value of the predecessorid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredecessorid(String value) {
        this.predecessorid = value;
    }

    /**
     * Gets the value of the slaExec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaExec() {
        return slaExec;
    }

    /**
     * Sets the value of the slaExec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaExec(String value) {
        this.slaExec = value;
    }

    /**
     * Gets the value of the slaMetrics property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaMetrics() {
        return slaMetrics;
    }

    /**
     * Sets the value of the slaMetrics property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaMetrics(String value) {
        this.slaMetrics = value;
    }

    /**
     * Gets the value of the slaPickupMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaPickupMin() {
        return slaPickupMin;
    }

    /**
     * Sets the value of the slaPickupMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaPickupMin(String value) {
        this.slaPickupMin = value;
    }

    /**
     * Gets the value of the phaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhaseId() {
        return phaseId;
    }

    /**
     * Sets the value of the phaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhaseId(String value) {
        this.phaseId = value;
    }

    /**
     * Gets the value of the queueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueId() {
        return queueId;
    }

    /**
     * Sets the value of the queueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueId(String value) {
        this.queueId = value;
    }

    /**
     * Gets the value of the concurrent property.
     * 
     */
    public boolean isConcurrent() {
        return concurrent;
    }

    /**
     * Sets the value of the concurrent property.
     * 
     */
    public void setConcurrent(boolean value) {
        this.concurrent = value;
    }

}
