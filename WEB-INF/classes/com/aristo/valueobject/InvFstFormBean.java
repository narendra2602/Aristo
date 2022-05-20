package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class InvFstFormBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int doc_type;
	private String party_code;
	private int inv_no;
	private String inv_lo;   
 	private int inv_yr;
	private Date inv_date;
	private String pinv_no;
	private Date pinv_date;
	private String chl_no;
	private Date chl_dt;
	private String mtr_no;
	private Date mtr_dt;
	private String order_no;
	private Date order_dt;
	private int cases;
	private int due_days;
	private Date due_dt;
	private String transport;
	private String bank;
	private String drug_lc1;
	private String drug_lc2;
	private int mr_cd;
	private int stat_cd;
	private int area_cd;
	private int regn_cd;
	private int terr_cd;
	private int dist_cd;
	private double bill_amt;
	private String inv_type;
	private String rem1;
	
	public int getArea_cd() {
		return area_cd;
	}
	public void setArea_cd(int area_cd) {
		this.area_cd = area_cd;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public double getBill_amt() {
		return bill_amt;
	}
	public void setBill_amt(double bill_amt) {
		this.bill_amt = bill_amt;
	}
	public int getCases() {
		return cases;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public Date getChl_dt() {
		return chl_dt;
	}
	public void setChl_dt(Date chl_dt) {
		this.chl_dt = chl_dt;
	}
	public String getChl_no() {
		return chl_no;
	}
	public void setChl_no(String chl_no) {
		this.chl_no = chl_no;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getDist_cd() {
		return dist_cd;
	}
	public void setDist_cd(int dist_cd) {
		this.dist_cd = dist_cd;
	}
	public int getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(int doc_type) {
		this.doc_type = doc_type;
	}
	public String getDrug_lc1() {
		return drug_lc1;
	}
	public void setDrug_lc1(String drug_lc1) {
		this.drug_lc1 = drug_lc1;
	}
	public String getDrug_lc2() {
		return drug_lc2;
	}
	public void setDrug_lc2(String drug_lc2) {
		this.drug_lc2 = drug_lc2;
	}
	public int getDue_days() {
		return due_days;
	}
	public void setDue_days(int due_days) {
		this.due_days = due_days;
	}
	public Date getDue_dt() {
		return due_dt;
	}
	public void setDue_dt(Date due_dt) {
		this.due_dt = due_dt;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public String getInv_lo() {
		return inv_lo;
	}
	public void setInv_lo(String inv_lo) {
		this.inv_lo = inv_lo;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public int getInv_yr() {
		return inv_yr;
	}
	public void setInv_yr(int inv_yr) {
		this.inv_yr = inv_yr;
	}
	public int getMr_cd() {
		return mr_cd;
	}
	public void setMr_cd(int mr_cd) {
		this.mr_cd = mr_cd;
	}
	public Date getMtr_dt() {
		return mtr_dt;
	}
	public void setMtr_dt(Date mtr_dt) {
		this.mtr_dt = mtr_dt;
	}
	public String getMtr_no() {
		return mtr_no;
	}
	public void setMtr_no(String mtr_no) {
		this.mtr_no = mtr_no;
	}
	public Date getOrder_dt() {
		return order_dt;
	}
	public void setOrder_dt(Date order_dt) {
		this.order_dt = order_dt;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getParty_code() {
		return party_code;
	}
	public void setParty_code(String party_code) {
		this.party_code = party_code;
	}
	public Date getPinv_date() {
		return pinv_date;
	}
	public void setPinv_date(Date pinv_date) {
		this.pinv_date = pinv_date;
	}
	public String getPinv_no() {
		return pinv_no;
	}
	public void setPinv_no(String pinv_no) {
		this.pinv_no = pinv_no;
	}
	public int getRegn_cd() {
		return regn_cd;
	}
	public void setRegn_cd(int regn_cd) {
		this.regn_cd = regn_cd;
	}
	public String getRem1() {
		return rem1;
	}
	public void setRem1(String rem1) {
		this.rem1 = rem1;
	}
	public int getStat_cd() {
		return stat_cd;
	}
	public void setStat_cd(int stat_cd) {
		this.stat_cd = stat_cd;
	}
	public int getTerr_cd() {
		return terr_cd;
	}
	public void setTerr_cd(int terr_cd) {
		this.terr_cd = terr_cd;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getInv_type() {
		return inv_type;
	}
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
    
 
    
    
}   
