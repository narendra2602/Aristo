package com.aristo.valueobject;

import java.io.Serializable;

public class Repo18FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lupdate;
	private String head1;
	private String pname;
	private int mcode;
	private int uv; 
	private int mon;
	private int color;
	private String mname;
	
	private double qty1[];
	private double val1[];
	
	private String uhead[];
	private String vhead[];
	
	
	private String codes[];
	private String codes1[];

	
	public Repo18FormBean()
	{
    	qty1 = new double[15];
    	val1 = new double[15];
    	uhead = new String[15];
    	vhead = new String[15];

	}
	
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getLupdate() {
		return lupdate;
	}
	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}
	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	public String[] getCodes() {
		return codes;
	}
	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	public String[] getCodes1() {
		return codes1;
	}
	public void setCodes1(String[] codes1) {
		this.codes1 = codes1;
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
	
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
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
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}	

	
}
