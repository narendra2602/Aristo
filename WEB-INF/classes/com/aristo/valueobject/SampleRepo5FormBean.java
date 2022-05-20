package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class SampleRepo5FormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String batch;
	private int inv_no; 
	private Date inv_dt;
	private int qty;
	private int fqty;
	private int issue;
	private String pname;
	private String pval;
	private String iname;
	private int ival;
	private int color;
	
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getFqty() {
		return fqty;
	}
	public void setFqty(int fqty) {
		this.fqty = fqty;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
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
	public int getIval() {
		return ival;
	}
	public void setIval(int ival) {
		this.ival = ival;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	
}
