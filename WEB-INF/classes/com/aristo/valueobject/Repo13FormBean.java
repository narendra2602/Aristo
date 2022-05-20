package com.aristo.valueobject;

import java.io.Serializable;

public class Repo13FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1;
	private String lupdate;
	private int pcode;
	private String pname;
	private String mcode;
	private String mname;
	
	private String pack;
	private double rate;
	private double sqty;
	private double sval;
	private double slqty;
	private double slval;
	private double pqty;
	private double ppval;
	private double netqty;
	private double netval;
	
	
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
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
	public double getPpval() {
		return ppval;
	}
	public void setPpval(double ppval) {
		this.ppval = ppval;
	}
	public double getPqty() {
		return pqty;
	}
	public void setPqty(double pqty) {
		this.pqty = pqty;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getSlqty() {
		return slqty;
	}
	public void setSlqty(double slqty) {
		this.slqty = slqty;
	}
	public double getSlval() {
		return slval;
	}
	public void setSlval(double slval) {
		this.slval = slval;
	}
	public double getSqty() {
		return sqty;
	}
	public void setSqty(double sqty) {
		this.sqty = sqty;
	}
	public double getSval() {
		return sval;
	}
	public void setSval(double sval) {
		this.sval = sval;
	}
	public String getLupdate() {
		return lupdate;
	}
	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}
	
	
	
	
}
