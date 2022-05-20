package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo8FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mon;
	private int uv=1;

	private double val1[];
	private double qty1[];
	private String uhead[];
	private String vhead[];
	private String name;
	private String head1;
	private String pname;
	private int pcode;
	private int no_of_mr;
	private int color;
	
	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public int getNo_of_mr() {
		return no_of_mr;
	}


	public void setNo_of_mr(int no_of_mr) {
		this.no_of_mr = no_of_mr;
	}


	public HORepo8FormBean() 
    {
    	uhead = new String[15];
    	vhead = new String[15];
    	qty1 = new double[15];
    	val1 = new double[15];
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
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	
	 public double getQty1(int index) { 
	        return qty1[index]; 
	    }
	    
	    public void setQty1(int index, double value) { 
	       qty1[index] = value; 
	    }	
	
	 public double getVal1(int index) { 
	        return val1[index]; 
	    }
		    
     public void setVal1(int index, double value) { 
	       val1[index] = value; 
	    }	

 	public String getUhead(int index) {
		return uhead[index];
	}
	public void setUhead(int index, String value) {
		uhead[index] = value;
	}	

 	public String getVhead(int index) {
		return vhead[index];
	}
	public void setVhead(int index, String value) {
		vhead[index] = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}	



}
