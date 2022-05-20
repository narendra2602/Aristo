package com.aristo.valueobject;

import java.io.Serializable;

public class HORepo3FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head1; 
	private String name;
	private double salable;
	private double expiry;
	private double breakage;
	private double total;
	private double gsale;
	private double spoil;
	private double pd;
	
	
	
	public double getGsale() {
		return gsale;
	}
	public void setGsale(double gsale) {
		this.gsale = gsale;
	}
	public double getPd() {
		return pd;
	}
	public void setPd(double pd) {
		this.pd = pd;
	}
	public double getSpoil() {
		return spoil;
	}
	public void setSpoil(double spoil) {
		this.spoil = spoil;
	}
	public double getBreakage() {
		return breakage;
	}
	public void setBreakage(double breakage) {
		this.breakage = breakage;
	}
	public double getExpiry() {
		return expiry;
	}
	public void setExpiry(double expiry) {
		this.expiry = expiry;
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
	public double getSalable() {
		return salable;
	}
	public void setSalable(double salable) {
		this.salable = salable;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
