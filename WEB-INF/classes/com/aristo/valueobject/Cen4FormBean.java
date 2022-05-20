package com.aristo.valueobject;

import java.io.Serializable;

public class Cen4FormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pname;
	private String pack;
	private String name;
	private int qty;
	private int balqty;
	private int colour;

	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public int getBalqty() {
		return balqty;
	}
	public void setBalqty(int balqty) {
		this.balqty = balqty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
 

	
}
