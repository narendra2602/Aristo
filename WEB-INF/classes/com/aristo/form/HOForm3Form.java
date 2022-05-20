package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HOForm3Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int r_type=1;
	private int pg=1;
	private int sale_type=1;
	
	private String name; 
    private double mtarget;
	private double msale;
	private double mach;
	private double cumtarget;
	private double cumsale;
	private double lyear;
	private double cumach;
	private double cumgth;
	private double pmr;
	private double surdeff;
	
	private List rlist;
	
	private int syear;
    private int eyear;
    
    private String yname;
    private int yval;
    private List ylist;

	private List alist; 
    private List blist;

    private int depo_code;
    private int grp_code;
    private int rank;
    private int fs;
    
    
    
    
    
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFs() {
		return fs;
	}

	public void setFs(int fs) {
		this.fs = fs;
	}

	public int getDepo_code() {
		return depo_code;
	}

	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}

	public int getGrp_code() {
		return grp_code;
	}

	public void setGrp_code(int grp_code) {
		this.grp_code = grp_code;
	}

	public List getAlist() {
		return alist;
	}

	public void setAlist(List alist) {
		this.alist = alist;
	}

	public List getBlist() {
		return blist;
	}

	public void setBlist(List blist) {
		this.blist = blist;
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getSale_type() {
		return sale_type;
	}

	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
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

	public void reset(ActionMapping mapping, HttpServletRequest req) { 
	    
    	this.smon=1;
    	this.emon=1;
    	this.r_type=1;
    	
    }
	
	public double getCumach() {
		return cumach;
	}
	public void setCumach(double cumach) {
		this.cumach = cumach;
	}
	public double getCumgth() {
		return cumgth;
	}
	public void setCumgth(double cumgth) {
		this.cumgth = cumgth;
	}
	public double getCumsale() {
		return cumsale;
	}
	public void setCumsale(double cumsale) {
		this.cumsale = cumsale;
	}
	public double getCumtarget() {
		return cumtarget;
	}
	public void setCumtarget(double cumtarget) {
		this.cumtarget = cumtarget;
	}
	public int getEmon() {
		return emon;
	}
	public void setEmon(int emon) {
		this.emon = emon;
	}
	public double getLyear() {
		return lyear;
	}
	public void setLyear(double lyear) {
		this.lyear = lyear;
	}
	public double getMach() {
		return mach;
	}
	public void setMach(double mach) {
		this.mach = mach;
	}
	public double getMsale() {
		return msale;
	}
	public void setMsale(double msale) {
		this.msale = msale;
	}
	public double getMtarget() {
		return mtarget;
	}
	public void setMtarget(double mtarget) {
		this.mtarget = mtarget;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getPmr() {
		return pmr;
	}
	public void setPmr(double pmr) {
		this.pmr = pmr;
	}

	public int getSmon() {
		return smon;
	}
	public void setSmon(int smon) {
		this.smon = smon;
	}
	public double getSurdeff() {
		return surdeff;
	}
	public void setSurdeff(double surdeff) {
		this.surdeff = surdeff;
	}




	public List getRlist() {
		return rlist;
	}




	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public int getR_type() {
		return r_type;
	}

	public void setR_type(int r_type) {
		this.r_type = r_type;
	}
	
	
	
}
