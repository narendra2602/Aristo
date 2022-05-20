package com.aristo.valueobject;

import java.io.Serializable;

public class Repo10FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String lupdate;
	private String uhead[];
	private String vhead[];
	private String mcode;
	private String mname;
	private String name;
	private int uv;
	private int mon;
	private double qty1[];
	private double val1[];
	
    public Repo10FormBean()
    {
    	qty1 = new double[15];
    	val1 = new double[15];
    	vhead = new String[15];
    	uhead = new String[15];
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

	public String getHead1() {
		return head1;
	}

	public void setHead1(String head1) {
		this.head1 = head1;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
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

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public String getLupdate() {
		return lupdate;
	}

	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	} 
	
	
}
