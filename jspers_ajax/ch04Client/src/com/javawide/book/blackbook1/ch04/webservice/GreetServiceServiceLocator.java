/**
 * GreetServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.javawide.book.blackbook1.ch04.webservice;

public class GreetServiceServiceLocator extends org.apache.axis.client.Service implements com.javawide.book.blackbook1.ch04.webservice.GreetServiceService {

    public GreetServiceServiceLocator() {
    }


    public GreetServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GreetServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GreetService
    private java.lang.String GreetService_address = "http://localhost:8088/ch04/services/GreetService";

    public java.lang.String getGreetServiceAddress() {
        return GreetService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GreetServiceWSDDServiceName = "GreetService";

    public java.lang.String getGreetServiceWSDDServiceName() {
        return GreetServiceWSDDServiceName;
    }

    public void setGreetServiceWSDDServiceName(java.lang.String name) {
        GreetServiceWSDDServiceName = name;
    }

    public com.javawide.book.blackbook1.ch04.webservice.GreetService getGreetService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GreetService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGreetService(endpoint);
    }

    public com.javawide.book.blackbook1.ch04.webservice.GreetService getGreetService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.javawide.book.blackbook1.ch04.webservice.GreetServiceSoapBindingStub _stub = new com.javawide.book.blackbook1.ch04.webservice.GreetServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getGreetServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGreetServiceEndpointAddress(java.lang.String address) {
        GreetService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.javawide.book.blackbook1.ch04.webservice.GreetService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.javawide.book.blackbook1.ch04.webservice.GreetServiceSoapBindingStub _stub = new com.javawide.book.blackbook1.ch04.webservice.GreetServiceSoapBindingStub(new java.net.URL(GreetService_address), this);
                _stub.setPortName(getGreetServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GreetService".equals(inputPortName)) {
            return getGreetService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.ch04.blackbook1.book.javawide.com", "GreetServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.ch04.blackbook1.book.javawide.com", "GreetService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GreetService".equals(portName)) {
            setGreetServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
