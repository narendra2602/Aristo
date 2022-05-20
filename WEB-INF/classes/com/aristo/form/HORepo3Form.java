package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HORepo3Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int r_type=1;
	private String name;
	private double salable;
	private double expiry;
	private double breakage;
	private double total;
	private double gsale;
	private double spoil;
	private double pd;
	private List rlist;
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;
	
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
	public double getBreakage() {
		return breakage;
	}
	public void setBreakage(double breakage) {
		this.breakage = breakage;
	}
	public int getEmon() {
		return emon;
	}
	public void setEmon(int emon) {
		this.emon = emon;
	}
	public double getExpiry() {
		return expiry;
	}
	public void setExpiry(double expiry) {
		this.expiry = expiry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public double getSalable() {
		return salable;
	}
	public void setSalable(double salable) {
		this.salable = salable;
	}
	public int getSmon() {
		return smon;
	}
	public void setSmon(int smon) {
		this.smon = smon;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest req) { 

	    
    	this.emon=1;
    	this.smon=1;
    	this.r_type=1;
    	this.eyear=0;
    	
    }
	public int getR_type() {
		return r_type;
	}
	public void setR_type(int r_type) {
		this.r_type = r_type;
	}
	public double getGsale() {
		return gsale;
	}
	public void setGsale(double gsale) {
		this.gsale = gsale;
	}
	public double getPd() {
		return pd;
	}
	public void setPd(double pd) {
		this.pd = pd;
	}
	public double getSpoil() {
		return spoil;
	}
	public void setSpoil(double spoil) {
		this.spoil = spoil;
	}	
	
	
}
