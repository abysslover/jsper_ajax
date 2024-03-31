package com.javawide.book.blackbook1.ch04.webservice;

public class GreetServiceProxy implements com.javawide.book.blackbook1.ch04.webservice.GreetService {
  private String _endpoint = null;
  private com.javawide.book.blackbook1.ch04.webservice.GreetService greetService = null;
  
  public GreetServiceProxy() {
    _initGreetServiceProxy();
  }
  
  public GreetServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initGreetServiceProxy();
  }
  
  private void _initGreetServiceProxy() {
    try {
      greetService = (new com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceLocator()).getGreetService();
      if (greetService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)greetService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)greetService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (greetService != null)
      ((javax.xml.rpc.Stub)greetService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.javawide.book.blackbook1.ch04.webservice.GreetService getGreetService() {
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService;
  }
  
  public int add(int op1, int op2) throws java.rmi.RemoteException{
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService.add(op1, op2);
  }
  
  public double divide(int op1, int op2) throws java.rmi.RemoteException{
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService.divide(op1, op2);
  }
  
  public long multiply(int op1, int op2) throws java.rmi.RemoteException{
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService.multiply(op1, op2);
  }
  
  public int minus(int op1, int op2) throws java.rmi.RemoteException{
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService.minus(op1, op2);
  }
  
  public java.lang.String doGreet(java.lang.String name) throws java.rmi.RemoteException{
    if (greetService == null)
      _initGreetServiceProxy();
    return greetService.doGreet(name);
  }
  
  
}