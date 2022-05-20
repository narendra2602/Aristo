package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo7FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; 
	private double sqty;
	private double sval;
	private double slqty;
	private double slval;
	private double expqty;
	private double expval;
	private double netqty;
	private double netval;
	private int gcode;
	private String head1;
	private String gname;
	private double val1[];
	private String vhead[];
	private int mon;
	private int no_of_mr;
	
	
	
	
	public HORepo7FormBean() 
    {
    	 
    	vhead = new String[15];
    	val1 = new double[15];
    }
	
	
	
	public int getNo_of_mr() {
		return no_of_mr;
	}



	public void setNo_of_mr(int no_of_mr) {
		this.no_of_mr = no_of_mr;
	}



	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public String getVhead(int index) {
		return vhead[index];
	}
	public void setVhead(int index, String value) {
		vhead[index] = value;
	}
	 public double getVal1(int index) { 
	        return val1[index]; 
	    }
		    
	 public void setVal1(int index, double value) { 
	       val1[index] = value; 
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
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	} 

	
}
