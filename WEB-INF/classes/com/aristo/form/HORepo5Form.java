package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HORepo5Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int r_type=2;
	private String name;
	private double budget;
	private double gross;
	private double credit;
	private double net;
	private double ach;
	private double surp;
	private List rlist;
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;
    private int depo_code;
    private int grp_code;
    
	private List alist; 
    private List blist;

    private double pi;
    
    public HORepo5Form()
    {
    	r_type=2;
    }
    
    
	

	public double getPi() {
		return pi;
	}






	public void setPi(double pi) {
		this.pi = pi;
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
	public double getAch() {
		return ach;
	}
	public void setAch(double ach) {
		this.ach = ach;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getEmon() {
		return emon;
	}
	public void setEmon(int emon) {
		this.emon = emon;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNet() {
		return net;
	}
	public void setNet(double net) {
		this.net = net;
	}
	public int getSmon() {
		return smon;
	}
	public void setSmon(int smon) {
		this.smon = smon;
	}
	public double getSurp() {
		return surp;
	}
	public void setSurp(double surp) {
		this.surp = surp;
	}
	
	
	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    	this.smon=1;
    	this.emon=1;
    	this.r_type=1;
    	this.eyear=0;
    	
    }
	public int getR_type() {
		return r_type;
	}
	public void setR_type(int r_type) {
		this.r_type = r_type;
	}	
	
	
	
}
