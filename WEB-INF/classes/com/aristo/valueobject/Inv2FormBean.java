package com.aristo.valueobject;

import java.io.Serializable;

public class Inv2FormBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String lupdate;
	private int code;
	private String name;
	private String pack;
	private int branch_code;
	private String branch_name;
	private double tmonqty;
	private double tmonval;
	private double ymonqty;
	private double ymonval;
	
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
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getLupdate() {
		return lupdate;
	}
	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}
	
	
}
