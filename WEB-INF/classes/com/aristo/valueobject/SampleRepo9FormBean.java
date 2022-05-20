package com.aristo.valueobject;

import java.io.Serializable;

public class SampleRepo9FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int allot;
	private int issue;
	private int pending;
	private int fs;
	
	public int getAllot() {
		return allot;
	}
	public void setAllot(int allot) {
		this.allot = allot;
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
	public int getFs() {
		return fs;
	}
	public void setFs(int fs) {
		this.fs = fs;
	}
	
}
