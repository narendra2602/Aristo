package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Inv1Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String product;
	private String pack;
	private int emon=1;
	
	private double opnbal;
	private double dambal;
	private double badbal;
	private double othbal;
	private double bplbal;

	private double salqty;
	private double freqty;
	private double brkqty;
	private double expqty;

	private double sales;
	private double fissue;
	private double salsamp;
	private double woff;

	private double branches;
	private double cnf;

	private double closbal;
	private double mfgval;
	private List rlist;
	
	private int syear;
    private int eyear;
    
    private String yname;
    private int yval;
    private List ylist;
    
    private int code;
    private int pcode;
    private String pname;
    private int opt=1;
    private String search;
    
    
	
    
    
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getOpt() {
		return opt;
	}
	public void setOpt(int opt) {
		this.opt = opt;
	}
	public double getBadbal() {
		return badbal;
	}
	public void setBadbal(double badbal) {
		this.badbal = badbal;
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
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
	this.emon=1;
	this.eyear=0;
	}
	public double getBplbal() {
		return bplbal;
	}
	public void setBplbal(double bplbal) {
		this.bplbal = bplbal;
	}
	public double getBranches() {
		return branches;
	}
	public void setBranches(double branches) {
		this.branches = branches;
	}
	public double getBrkqty() {
		return brkqty;
	}
	public void setBrkqty(double brkqty) {
		this.brkqty = brkqty;
	}
	public double getClosbal() {
		return closbal;
	}
	public void setClosbal(double closbal) {
		this.closbal = closbal;
	}
	public double getCnf() {
		return cnf;
	}
	public void setCnf(double cnf) {
		this.cnf = cnf;
	}
	public double getDambal() {
		return dambal;
	}
	public void setDambal(double dambal) {
		this.dambal = dambal;
	}
	public double getExpqty() {
		return expqty;
	}
	public void setExpqty(double expqty) {
		this.expqty = expqty;
	}
	public double getFissue() {
		return fissue;
	}
	public void setFissue(double fissue) {
		this.fissue = fissue;
	}
	public double getFreqty() {
		return freqty;
	}
	public void setFreqty(double freqty) {
		this.freqty = freqty;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public double getMfgval() {
		return mfgval;
	}
	public void setMfgval(double mfgval) {
		this.mfgval = mfgval;
	}
	public double getOpnbal() {
		return opnbal;
	}
	public void setOpnbal(double opnbal) {
		this.opnbal = opnbal;
	}
	public double getOthbal() {
		return othbal;
	}
	public void setOthbal(double othbal) {
		this.othbal = othbal;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public double getSalqty() {
		return salqty;
	}
	public void setSalqty(double salqty) {
		this.salqty = salqty;
	}
	public double getSalsamp() {
		return salsamp;
	}
	public void setSalsamp(double salsamp) {
		this.salsamp = salsamp;
	}
	public double getWoff() {
		return woff;
	}
	public void setWoff(double woff) {
		this.woff = woff;
	}
	public int getEmon() {
		return emon;
	}
	public void setEmon(int emon) {
		this.emon = emon;
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
