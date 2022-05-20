package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class BatchMasterFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int bat_pcode;
	private String bat_no;
	private String bat_mfgdt;
	private String bat_expdt;
	private double bat_netrt;
	private double bat_trdrt;
	private double bat_mrprt;
	private double bat_mfgrt;
	private double bat_hosrt;
	private double bat_excrt;
	private String bat_indct; 
	private int bat_clos;
	private double bat_purrt;
	private String bat_tag;
	private Date bat_rcpdt;
	private Date expiry;	
	private int gift_code;
	private int fact_code;
	private String remark;
	
	
	public int getFact_code() {
		return fact_code;
	}
	public void setFact_code(int fact_code) {
		this.fact_code = fact_code;
	}
	public int getGift_code() {
		return gift_code;
	}
	public void setGift_code(int gift_code) {
		this.gift_code = gift_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getBat_excrt() {
		return bat_excrt;
	}
	public void setBat_excrt(double bat_excrt) {
		this.bat_excrt = bat_excrt;
	}
	public double getBat_hosrt() {
		return bat_hosrt;
	}
	public void setBat_hosrt(double bat_hosrt) {
		this.bat_hosrt = bat_hosrt;
	}
	public String getBat_mfgdt() {
		return bat_mfgdt;
	}
	public void setBat_mfgdt(String bat_mfgdt) {
		this.bat_mfgdt = bat_mfgdt;
	}
	public double getBat_mfgrt() {
		return bat_mfgrt;
	}
	public void setBat_mfgrt(double bat_mfgrt) {
		this.bat_mfgrt = bat_mfgrt;
	}
	public double getBat_mrprt() {
		return bat_mrprt;
	}
	public void setBat_mrprt(double bat_mrprt) {
		this.bat_mrprt = bat_mrprt;
	}
	public double getBat_netrt() {
		return bat_netrt;
	}
	public void setBat_netrt(double bat_netrt) {
		this.bat_netrt = bat_netrt;
	}
	public double getBat_purrt() {
		return bat_purrt;
	}
	public void setBat_purrt(double bat_purrt) {
		this.bat_purrt = bat_purrt;
	}
	public Date getBat_rcpdt() {
		return bat_rcpdt;
	}
	public void setBat_rcpdt(Date bat_rcpdt) {
		this.bat_rcpdt = bat_rcpdt;
	}
	public double getBat_trdrt() {
		return bat_trdrt;
	}
	public void setBat_trdrt(double bat_trdrt) {
		this.bat_trdrt = bat_trdrt;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public int getBat_clos() {
		return bat_clos;
	}
	public void setBat_clos(int bat_clos) {
		this.bat_clos = bat_clos;
	}
	public String getBat_expdt() {
		return bat_expdt;
	}
	public void setBat_expdt(String bat_expdt) {
		this.bat_expdt = bat_expdt;
	}
	public String getBat_indct() {
		return bat_indct;
	}
	public void setBat_indct(String bat_indct) {
		this.bat_indct = bat_indct;
	}
	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public int getBat_pcode() {
		return bat_pcode;
	}
	public void setBat_pcode(int bat_pcode) {
		this.bat_pcode = bat_pcode;
	}
	public String getBat_tag() {
		return bat_tag;
	}
	public void setBat_tag(String bat_tag) {
		this.bat_tag = bat_tag;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}

	
	
	
}
