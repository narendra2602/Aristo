package com.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class SampleRepo9Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int allot;
	private int issue;
	private int pending;
	private int fs;
	private String sdate;
	private String edate;
	private List rlist;
	public int getAllot() {
		return allot;
	}
	public void setAllot(int allot) {
		this.allot = allot;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public int getFs() {
		return fs;
	}
	public void setFs(int fs) {
		this.fs = fs;
	}

	
}
