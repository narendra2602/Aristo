package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Inv2Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ttype=60;
	private int smon=1;
	private int emon=1;
	private int code;
	private String name;
	private String pack;
	private int branch_code;
	private String branch_name;
	private double tmonqty;
	private double tmonval;
	private double ymonqty;
	private double ymonval;
	private List rlist ;

	
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


	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.smon=1;
	this.emon=1;
	this.ttype=60;
	this.eyear=0;
	}
	
	
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	
	public int getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(int branch_code) {
		this.branch_code = branch_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public double getTmonqty() {
		return tmonqty;
	}
	public void setTmonqty(double tmonqty) {
		this.tmonqty = tmonqty;
	}
	public double getTmonval() {
		return tmonval;
	}
	public void setTmonval(double tmonval) {
		this.tmonval = tmonval;
	}

	public int getTtype() {
		return ttype;
	}


	public void setTtype(int ttype) {
		this.ttype = ttype;
	}


	public double getYmonqty() {
		return ymonqty;
	}
	public void setYmonqty(double ymonqty) {
		this.ymonqty = ymonqty;
	}
	public double getYmonval() {
		return ymonval;
	}
	public void setYmonval(double ymonval) {
		this.ymonval = ymonval;
	}


	public String getPack() {
		return pack;
	}


	public void setPack(String pack) {
		this.pack = pack;
	}


	public int getSmon() {
		return smon;
	}


	public void setSmon(int smon) {
		this.smon = smon;
	}

	
	
	
}
