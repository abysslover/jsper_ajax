package com.javawide.book.blackbook1.ch04.webservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ServiceClient {
	private Object[] operands;
	private String method;
	private Service service;
	private Call call;
	private String endpoint = "http://localhost:8088/ch04/services/GreetService";
	
	public static void main(String[] args) {
		ServiceClient client = new ServiceClient();
		client.setOperand(new Object[]{100, 20});
		client.setMethod("minus");
		client.call();
		client.setMethod("add");
		client.call();
		GreetServiceService serviceProxy = new GreetServiceServiceLocator();
		try {
			GreetService service = serviceProxy.getGreetService();
			System.out.println(service.minus((Integer)client.getOperands()[0], (Integer)client.getOperands()[1]));
			System.out.println(service.add((Integer)client.getOperands()[0], (Integer)client.getOperands()[1]));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private Object[] getOperands() {
		return operands;
	}

	private void call() {
		createCall();
		call.setTargetEndpointAddress(endpoint);
		call.setOperationName(method);
		for(int i=0; i<operands.length; ++i) {
			call.addParameter("op"+i, XMLType.XSD_INT, ParameterMode.IN);
		}
		call.setReturnType(XMLType.XSD_INT);
		try {
			System.out.println(call.invoke(operands));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void createCall() {
		this.service = new Service();
		try {
			this.call = (Call) service.createCall();
		} catch (ServiceException e) {
			e.printStackTrace();
		}	
	}

	private void setMethod(String method) {
		this.method = method;
	}

	private void setOperand(Object[] operands) {
		this.operands = operands;
	}
}
