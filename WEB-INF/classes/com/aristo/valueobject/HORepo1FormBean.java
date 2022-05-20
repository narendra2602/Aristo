package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo1FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pcode;
	private int br;
	private int uv;
	private String pname;
	private String pack;
	private String head1;
	private String uhead[];
	private String vhead[];
	private double qty1[];
	private double dval0[];
	private String search;
	private int color;
	
	
	
	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public String getSearch() {
		return search;
	}


	public void setSearch(String search) {
		this.search = search;
	}


	public int getUv() {
		return uv;
	}


	public void setUv(int uv) {
		this.uv = uv;
	}


	public HORepo1FormBean() 
    {
    	qty1 = new double[50];
    	dval0 = new double[50];
    	uhead = new String[50];
    	vhead = new String[50];
    }
	
	
	 public double getQty1(int index) { 
	        return qty1[index]; 
	    }
	    
	    public void setQty1(int index, double value) { 
	       qty1[index] = value; 
	    }

		public double getDval0(int index) {
			return dval0[index];
		}

		public void setDval0(int index, double value) {
			dval0[index] = value;
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
	
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
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



	public int getBr() {
		return br;
	}


	public void setBr(int br) {
		this.br = br;
	}
	
	
}
