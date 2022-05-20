package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HORepo2Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sale_type=1;
	private int smon=1;
	private int emon=1;
	private int gcode;
	private String gname;
	private double val1[];
	private List rlist;
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;

    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) 
    {
    this.emon=1;
    this.smon=1;
    this.sale_type=1;
    this.eyear=0;
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

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public HORepo2Form() 
    {
    	val1 = new double[50];
    }
    
	public double getVal1(int index) {
		return val1[index];
	}

	public void setVal1(int index, double value) {
		val1[index] = value;
	}

	
	public int getEmon() {
		return emon;
	}

	public void setEmon(int emon) {
		this.emon = emon;
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

	
	
	
}
