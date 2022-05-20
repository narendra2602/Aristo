package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Cen4Form extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private String name;
	private int qty;
	private int balqty;
    private List rlist;
    private String sdate;
    private String edate;
    
	private String sbranch;
	private List brlist;
	private String brval;
	private String brname;
	private int choice=1;
    
    public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public List getBrlist() {
		return brlist;
	}
	public void setBrlist(List brlist) {
		this.brlist = brlist;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
    {
    this.sdate="";
    this.edate="";
    this.choice=1;
    }
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public int getBalqty() {
		return balqty;
	}
	public void setBalqty(int balqty) {
		this.balqty = balqty;
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getBrval() {
		return brval;
	}
	public void setBrval(String brval) {
		this.brval = brval;
	}
	public String getSbranch() {
		return sbranch;
	}
	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}
	
}
