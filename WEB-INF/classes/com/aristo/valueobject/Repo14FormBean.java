package com.aristo.valueobject;

import java.io.Serializable;

public class Repo14FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String head1;
	private String lupdate;
	private String name;
	private double grossval;
	private double salval;
	private double expval;
	private double brkval;
	private double total;
	private int color;
	
	
	public double getGrossval() {
		return grossval;
	}
	public void setGrossval(double grossval) {
		this.grossval = grossval;
	}
	public double getBrkval() {
		return brkval;
	}
	public void setBrkval(double brkval) {
		this.brkval = brkval;
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
	public double getSalval() {
		return salval;
	}
	public void setSalval(double salval) {
		this.salval = salval;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getLupdate() {
		return lupdate;
	}
	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
}
