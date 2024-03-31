
/**
 * GreetServiceServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */

    package com.javawide.book.blackbook1.ch04.webservice;

    /**
     *  GreetServiceServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GreetServiceServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GreetServiceServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GreetServiceServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for add method
            * override this method for handling normal response from add operation
            */
           public void receiveResultadd(
                    com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceStub.AddResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from add operation
           */
            public void receiveErroradd(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for divide method
            * override this method for handling normal response from divide operation
            */
           public void receiveResultdivide(
                    com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceStub.DivideResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from divide operation
           */
            public void receiveErrordivide(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for multiply method
            * override this method for handling normal response from multiply operation
            */
           public void receiveResultmultiply(
                    com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceStub.MultiplyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from multiply operation
           */
            public void receiveErrormultiply(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for minus method
            * override this method for handling normal response from minus operation
            */
           public void receiveResultminus(
                    com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceStub.MinusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from minus operation
           */
            public void receiveErrorminus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for doGreet method
            * override this method for handling normal response from doGreet operation
            */
           public void receiveResultdoGreet(
                    com.javawide.book.blackbook1.ch04.webservice.GreetServiceServiceStub.DoGreetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from doGreet operation
           */
            public void receiveErrordoGreet(java.lang.Exception e) {
            }
                


    }
    