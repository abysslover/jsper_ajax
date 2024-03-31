/**
 * GreetServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.javawide.book.blackbook1.ch04.webservice;

public interface GreetServiceService extends javax.xml.rpc.Service {
    public java.lang.String getGreetServiceAddress();

    public com.javawide.book.blackbook1.ch04.webservice.GreetService getGreetService() throws javax.xml.rpc.ServiceException;

    public com.javawide.book.blackbook1.ch04.webservice.GreetService getGreetService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
