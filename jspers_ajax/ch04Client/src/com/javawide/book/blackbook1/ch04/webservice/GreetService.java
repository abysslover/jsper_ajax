/**
 * GreetService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.javawide.book.blackbook1.ch04.webservice;

public interface GreetService extends java.rmi.Remote {
    public int add(int op1, int op2) throws java.rmi.RemoteException;
    public double divide(int op1, int op2) throws java.rmi.RemoteException;
    public long multiply(int op1, int op2) throws java.rmi.RemoteException;
    public int minus(int op1, int op2) throws java.rmi.RemoteException;
    public java.lang.String doGreet(java.lang.String name) throws java.rmi.RemoteException;
}
