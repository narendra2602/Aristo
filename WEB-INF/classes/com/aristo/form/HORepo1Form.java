package com.aristo.form;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class HORepo1Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private int uv=1;
	private int sale_type=1;
	private int smon=1;
	private int emon=1;
	private int pcode;
	private String pname;
	private String pack;
	private double qty1[];
	private double dval0[];
	private List rlist;
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;
    private String search;
    private List branchlist;
    private int depo_code;
    private int rep_type=1;
 
    
    
    

	public int getRep_type() {
		return rep_type;
	}

	public void setRep_type(int rep_type) {
		this.rep_type = rep_type;
	}

	public int getDepo_code() {
		return depo_code;
	}

	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}

	public List getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List branchlist) {
		this.branchlist = branchlist;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	public List getYlist() {
		return ylist;
	}

	public void setYlist(List ylist) {
		this.ylist = ylist;
	}

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

	public int getYval() {
		return yval;
	}

	public void setYval(int yval) {
		this.yval = yval;
	}

	public HORepo1Form() 
    {
    	qty1 = new double[50];
    	dval0 = new double[50];
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
	
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}


	public int getEmon() {
		return emon;
	}




	public void setEmon(int emon) {
		this.emon = emon;
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




	public int getSale_type() {
		return sale_type;
	}




	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}




	public int getSmon() {
		return smon;
	}




	public void setSmon(int smon) {
		this.smon = smon;
	}




	public int getUv() {
		return uv;
	}




	public void setUv(int uv) {
		this.uv = uv;
	}
	

	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    	this.uv=1;
    	this.sale_type=1;
    	this.smon=1;  
    	this.emon=1; 
    	this.eyear=0;
    	this.search=null;
    	this.rep_type=1;
    	
    }
	
	
	
	
}
