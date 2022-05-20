package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo2FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String gname;
	private int gcode;
	private int br;
	private double val1[];
	private String vhead[];
	
	
	public int getBr() {
		return br;
	}

	public void setBr(int br) {
		this.br = br;
	}

	public int getGcode() {
		return gcode;
	}

	public void setGcode(int gcode) {
		this.gcode = gcode;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getHead1() {
		return head1;
	}

	public void setHead1(String head1) {
		this.head1 = head1;
	}

	public HORepo2FormBean() 
    {
    	val1 = new double[50];
    	vhead = new String [50];
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
	
	
}
