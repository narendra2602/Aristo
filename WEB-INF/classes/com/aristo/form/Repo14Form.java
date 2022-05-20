package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Repo14Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private String name;
	
	private double grossval;
	private double salval;
	private double expval;
	private double brkval;
	private double total;
	
	private List ylist;
	private int syear;
	private int eyear;
	private int yval;
	private String yname;
	
	
	public double getGrossval() {
		return grossval;
	}


	public void setGrossval(double grossval) {
		this.grossval = grossval;
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


	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
	this.smon=1;
	this.emon=1;
	}
	
	
	public double getBrkval() {
		return brkval;
	}
	public void setBrkval(double brkval) {
		this.brkval = brkval;
	}
	public int getEmon() {
		return emon;
	}
	public void setEmon(int emon) {
		this.emon = emon;
	}
	public double getExpval() {
		return expval;
	}
	public void setExpval(double expval) {
		this.expval = expval;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
