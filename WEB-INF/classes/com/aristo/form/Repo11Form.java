package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Repo11Form extends ActionForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sale_type=1;
	private int smon=1;
	private int emon=1;
	private String name;
	private double val1[];
	private List rlist;
	
	private List ylist;
	private int syear;
	private int eyear;
	private int yval;
	private String yname;
	
	
	public double getVal1(int index) {
		return val1[index];
	}

	public void setVal1(int index, double value) {
		val1[index] = value;
	} 	    

	public Repo11Form()
	{
		val1 = new double[13];
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	this.sale_type=1;
	this.smon=1;
	this.emon=1;
	this.eyear=0;
	
	}

	public int getEmon() {
		return emon;
	}

	public void setEmon(int emon) {
		this.emon = emon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
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
	
	
	
	
	
	
	
	
}
