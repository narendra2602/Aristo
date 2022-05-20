package com.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SampleRepo5Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String batch;
	private int inv_no; 
	private Date inv_dt;
	private int qty;
	private int fqty;
	private int issue;
	private String sdate;
	private String edate;
	private int itype;
	private int choice;
	private int pchoice;
	private List rlist;
	private List plist;
	private List ilist;
	private String pname;
	private String pval;
	private String iname;
	private int ival;
	private String pcode;
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.choice=1;
	this.sdate="";
	this.edate="";
	}
	
	
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public int getIval() {
		return ival;
	}
	public void setIval(int ival) {
		this.ival = ival;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getFqty() {
		return fqty;
	}
	public void setFqty(int fqty) {
		this.fqty = fqty;
	}
	public Date getInv_dt() {
		return inv_dt;
	}
	public void setInv_dt(Date inv_dt) {
		this.inv_dt = inv_dt;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getItype() {
		return itype;
	}
	public void setItype(int itype) {
		this.itype = itype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPchoice() {
		return pchoice;
	}
	public void setPchoice(int pchoice) {
		this.pchoice = pchoice;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public List getIlist() {
		return ilist;
	}
	public void setIlist(List ilist) {
		this.ilist = ilist;
	}
	public List getPlist() {
		return plist;
	}
	public void setPlist(List plist) {
		this.plist = plist;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPval() {
		return pval;
	}


	public void setPval(String pval) {
		this.pval = pval;
	}


	public String getPcode() {
		return pcode;
	}


	public void setPcode(String pcode) {
		this.pcode = pcode;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCode() {
		return code;
	}

	
	
}
