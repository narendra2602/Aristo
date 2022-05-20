package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo9FormBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String head1;
	private double val1[];
	private String vhead[];
	private int mon;
	
	public HORepo9FormBean()
	{
		vhead = new String[13];
		val1 = new double[13];
	}
	
	public double getVal1(int index) {
		return val1[index];
	}

	public void setVal1(int index, double value) {
		val1[index] = value;
	} 	    

	public String getVhead(int index) {
		return vhead[index];
	}

	public void setVhead(int index, String value) {
		vhead[index] = value;
	}

	public String getHead1() {
		return head1;
	}

	public void setHead1(String head1) {
		this.head1 = head1;
	}

	public int getMon() {
		return mon;
	}

	public void setMon(int mon) {
		this.mon = mon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
