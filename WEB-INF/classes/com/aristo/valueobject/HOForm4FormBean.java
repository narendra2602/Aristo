package com.aristo.valueobject;

import java.io.Serializable;

public class HOForm4FormBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String name; 
	private String uhead[];
	private String vhead[];
	private int no_of_mr;
	private double qty1[];
	private double val1[];
	private double pmr_u;
	private double pmr_v;
	private int br;
	private int uv;
	private int st;
    private int pcode;
    private String pname;
   
	
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


	public HOForm4FormBean() 
    {
    	uhead = new String[15];
    	vhead = new String[15];
    	qty1 = new double[15];
    	val1 = new double[15];
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


	public int getBr() {
		return br;
	}


	public void setBr(int br) {
		this.br = br;
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


	public int getNo_of_mr() {
		return no_of_mr;
	}


	public void setNo_of_mr(int no_of_mr) {
		this.no_of_mr = no_of_mr;
	}


	public double getPmr_u() {
		return pmr_u;
	}


	public void setPmr_u(double pmr_u) {
		this.pmr_u = pmr_u;
	}


	public double getPmr_v() {
		return pmr_v;
	}


	public void setPmr_v(double pmr_v) {
		this.pmr_v = pmr_v;
	}


	public int getSt() {
		return st;
	}


	public void setSt(int st) {
		this.st = st;
	}


	public int getUv() {
		return uv;
	}


	public void setUv(int uv) {
		this.uv = uv;
	}	
	
	
	
	
}
