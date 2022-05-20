package com.aristo.dao;

public class XMLElement 
{
	private XMLElement parent;
	private Object value;
	
	XMLElement(XMLElement parent, Object value) {
		this.parent = parent;
		this.value = value;
	}
	
	void setParent(XMLElement parent) {
		this.parent = parent;
	}
	
	public XMLElement parent() {
		return parent;
	}
	
	void setValue(Object value) {
		this.value = value;
	}
	
	public Object value() {
		return value;
	}
}
