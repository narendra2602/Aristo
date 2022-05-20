package com.aristo.valueobject;

import java.io.Serializable;

public class Inv3FormBean implements Serializable {

	
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
	private double grossqty;
	private double grossval;
	private double netqty;
	private double netval;
	
	private double salqty;
	private double salval;
	private double expqty;
	private double expval;
	private double brkqty;
	private double brkval;
	private double pdqty;
	private double pdval;
	private double insqty;
	private double insval;

	
	
	
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
	public double getGrossqty() {
		return grossqty;
	}
	public void setGrossqty(double grossqty) {
		this.grossqty = grossqty;
	}
	public double getGrossval() {
		return grossval;
	}
	public void setGrossval(double grossval) {
		this.grossval = grossval;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getBrkqty() {
		return brkqty;
	}
	public void setBrkqty(double brkqty) {
		this.brkqty = brkqty;
	}
	public double getBrkval() {
		return brkval;
	}
	public void setBrkval(double brkval) {
		this.brkval = brkval;
	}
	public double getExpqty() {
		return expqty;
	}
	public void setExpqty(double expqty) {
		this.expqty = expqty;
	}
	public double getExpval() {
		return expval;
	}
	public void setExpval(double expval) {
		this.expval = expval;
	}
	public double getInsqty() {
		return insqty;
	}
	public void setInsqty(double insqty) {
		this.insqty = insqty;
	}
	public double getInsval() {
		return insval;
	}
	public void setInsval(double insval) {
		this.insval = insval;
	}
	public double getPdqty() {
		return pdqty;
	}
	public void setPdqty(double pdqty) {
		this.pdqty = pdqty;
	}
	public double getPdval() {
		return pdval;
	}
	public void setPdval(double pdval) {
		this.pdval = pdval;
	}
	public double getSalqty() {
		return salqty;
	}
	public void setSalqty(double salqty) {
		this.salqty = salqty;
	}
	public double getSalval() {
		return salval;
	}
	public void setSalval(double salval) {
		this.salval = salval;
	}
	public String getLupdate() {
		return lupdate;
	}
	public void setLupdate(String lupdate) {
		this.lupdate = lupdate;
	}

	
	
}
