package com.aristo.valueobject;

import java.io.Serializable;

public class HOForm3FormBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; 
  	private double mtarget;
	private double msale;
	private double mach;
	private double cumtarget;
	private double cumsale;
	private double lyear;
	private double cumach;
	private double cumgth;
	private double pmr;
	private double surdeff;
	
	private double per;
	private String nm2;
	private String nm4;
	private String head1;
    private int rank;
    private int fs;


    
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getFs() {
		return fs;
	}
	public void setFs(int fs) {
		this.fs = fs;
	}
	public double getCumach() {
		return cumach;
	}
	public void setCumach(double cumach) {
		this.cumach = cumach;
	}
	public double getCumgth() {
		return cumgth;
	}
	public void setCumgth(double cumgth) {
		this.cumgth = cumgth;
	}
	public double getCumsale() {
		return cumsale;
	}
	public void setCumsale(double cumsale) {
		this.cumsale = cumsale;
	}
	public double getCumtarget() {
		return cumtarget;
	}
	public void setCumtarget(double cumtarget) {
		this.cumtarget = cumtarget;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public double getLyear() {
		return lyear;
	}
	public void setLyear(double lyear) {
		this.lyear = lyear;
	}
	public double getMach() {
		return mach;
	}
	public void setMach(double mach) {
		this.mach = mach;
	}
	public double getMsale() {
		return msale;
	}
	public void setMsale(double msale) {
		this.msale = msale;
	}
	public double getMtarget() {
		return mtarget;
	}
	public void setMtarget(double mtarget) {
		this.mtarget = mtarget;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNm2() {
		return nm2;
	}
	public void setNm2(String nm2) {
		this.nm2 = nm2;
	}

	public String getNm4() {
		return nm4;
	}
	public void setNm4(String nm4) {
		this.nm4 = nm4;
	}

	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
	public double getPmr() {
		return pmr;
	}
	public void setPmr(double pmr) {
		this.pmr = pmr;
	}
	public double getSurdeff() {
		return surdeff;
	}
	public void setSurdeff(double surdeff) {
		this.surdeff = surdeff;
	}
	
	
	
	
	
}
