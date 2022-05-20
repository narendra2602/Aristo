package com.aristo.valueobject;

import java.io.Serializable;

public class MRFormBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int mr_cd;
	private String mr_name;
	private String slct;
	private int mkt_year;
	
	public int getMkt_year() {
		return mkt_year;
	}
	public void setMkt_year(int mkt_year) {
		this.mkt_year = mkt_year;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getMr_cd() {
		return mr_cd;
	}
	public void setMr_cd(int mr_cd) {
		this.mr_cd = mr_cd;
	}
	public String getMr_name() {
		return mr_name;
	}
	public void setMr_name(String mr_name) {
		this.mr_name = mr_name;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	
}
