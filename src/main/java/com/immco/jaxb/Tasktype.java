//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.12 at 02:04:11 PM MST 
//


package com.immco.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tasktype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tasktype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HUMAN"/>
 *     &lt;enumeration value="SYSTEM"/>
 *     &lt;enumeration value="EVENT"/>
 *     &lt;enumeration value="EMAIL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tasktype")
@XmlEnum
public enum Tasktype {

    HUMAN,
    SYSTEM,
    EVENT,
    EMAIL;

    public String value() {
        return name();
    }

    public static Tasktype fromValue(String v) {
        return valueOf(v);
    }

}
