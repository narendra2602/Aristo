package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo6FormBean implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String name; 
	
	private double sqty;
	private double sval;
	private double fqty;
	private double fval;
	private double slqty;
	private double slval;
	private double expqty;
	private double expval;
	private double netqty;
	private double netval;
	private int pcode;
	private String phead1;
	private String pname; 
	private int color;
	
	
	
	
	public double getFqty() {
		return fqty;
	}
	public void setFqty(double fqty) {
		this.fqty = fqty;
	}
	public double getFval() {
		return fval;
	}
	public void setFval(double fval) {
		this.fval = fval;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public double getExpqty() {
		return expqty;
	}
	public void setExpqty(double expqty) {
		this.expqty = expqty;
	}
	public double getExpval() {
		return expval;
	}
	public void setExpval(double expval) {
		this.expval = expval;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNetqty() {
		return netqty;
	}
	public void setNetqty(double netqty) {
		this.netqty = netqty;
	}
	public double getNetval() {
		return netval;
	}
	public void setNetval(double netval) {
		this.netval = netval;
	}
	public double getSlqty() {
		return slqty;
	}
	public void setSlqty(double slqty) {
		this.slqty = slqty;
	}
	public double getSlval() {
		return slval;
	}
	public void setSlval(double slval) {
		this.slval = slval;
	}
	public double getSqty() {
		return sqty;
	}
	public void setSqty(double sqty) {
		this.sqty = sqty;
	}
	public double getSval() {
		return sval;
	}
	public void setSval(double sval) {
		this.sval = sval;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPhead1() {
		return phead1;
	}
	public void setPhead1(String phead1) {
		this.phead1 = phead1;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
}
