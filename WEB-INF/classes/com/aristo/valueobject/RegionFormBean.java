package com.aristo.valueobject;

import java.io.Serializable;

public class RegionFormBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int reg_code;
	private String name;
	private int area_code;
	private String txt;
	private String slct;
	private String typ;
	private int mkt_year;
	 
	public int getMkt_year() {
		return mkt_year;
	}
	public void setMkt_year(int mkt_year) {
		this.mkt_year = mkt_year;
	}
	public int getArea_code() {
		return area_code;
	}
	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReg_code() {
		return reg_code;
	}
	public void setReg_code(int reg_code) {
		this.reg_code = reg_code;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	} 
	
		
	

}
