package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Inv3Form extends ActionForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int code;
	private String name;
	private String pack;
	private int branch_code;
	private String branch_name;
	
	private double grossqty;
	private double grossval;
	private double netqty;
	private double netval;
	
	private double salqty;
	private double salval;
	private double expqty;
	private double expval;
	private double brkqty;
	private double brkval;
	private double pdqty;
	private double pdval;
	private double insqty;
	private double insval;
	
	
	private int syear;
    private int eyear;
    
    private String yname;
    private int yval;
    private List ylist;
	
	private List rlist ;

	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.smon=1;
	this.emon=1;
	this.eyear=0;
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
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public double getBrkqty() {
		return brkqty;
	}

	public void setBrkqty(double brkqty) {
		this.brkqty = brkqty;
	}

	public double getBrkval() {
		return brkval;
	}

	public void setBrkval(double brkval) {
		this.brkval = brkval;
	}

	public double getExpqty() {
		return expqty;
	}

	public void setExpqty(double expqty) {
		this.expqty = expqty;
	}

	public double getExpval() {
		return expval;
	}

	public void setExpval(double expval) {
		this.expval = expval;
	}

	public double getGrossqty() {
		return grossqty;
	}

	public void setGrossqty(double grossqty) {
		this.grossqty = grossqty;
	}

	public double getGrossval() {
		return grossval;
	}

	public void setGrossval(double grossval) {
		this.grossval = grossval;
	}

	public double getInsqty() {
		return insqty;
	}

	public void setInsqty(double insqty) {
		this.insqty = insqty;
	}

	public double getInsval() {
		return insval;
	}

	public void setInsval(double insval) {
		this.insval = insval;
	}

	public double getNetqty() {
		return netqty;
	}

	public void setNetqty(double netqty) {
		this.netqty = netqty;
	}

	public double getNetval() {
		return netval;
	}

	public void setNetval(double netval) {
		this.netval = netval;
	}

	public double getPdqty() {
		return pdqty;
	}

	public void setPdqty(double pdqty) {
		this.pdqty = pdqty;
	}

	public double getPdval() {
		return pdval;
	}

	public void setPdval(double pdval) {
		this.pdval = pdval;
	}

	public double getSalqty() {
		return salqty;
	}

	public void setSalqty(double salqty) {
		this.salqty = salqty;
	}

	public double getSalval() {
		return salval;
	}

	public void setSalval(double salval) {
		this.salval = salval;
	}

	public int getSmon() {
		return smon;
	}

	public void setSmon(int smon) {
		this.smon = smon;
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
