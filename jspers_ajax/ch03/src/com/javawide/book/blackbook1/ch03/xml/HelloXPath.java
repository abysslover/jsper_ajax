package com.javawide.book.blackbook1.ch03.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HelloXPath {
	private String xmlString;
	private DocumentBuilder builder;
	private XPath xPath;
	public HelloXPath() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		builder = factory.newDocumentBuilder();
		xPath = XPathFactory.newInstance().newXPath();
	}
	public void setXML(String xmlString) {
		this.xmlString = xmlString;
	}
	public Node selectSingleNode(String xPathString) {
		Node node = null;
		try {
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes())));
			node = (Node)xPath.compile(xPathString).evaluate(doc, XPathConstants.NODE);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return node;
	}
	public static void main(String[] args) {
		HelloXPath xpath;
		StringBuffer xml = new StringBuffer()
		.append("<AAA data=\"1\">")
		.append("	<BBB data=\"2\" />")
		.append("</AAA>");
		try {
			xpath = new HelloXPath();
			xpath.setXML(xml.toString());
			Node node = xpath.selectSingleNode("/AAA/@data");
			if(null == node) return;
			System.out.println(node.getNodeValue());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
