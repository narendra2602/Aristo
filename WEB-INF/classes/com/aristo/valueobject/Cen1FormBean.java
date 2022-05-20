package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class Cen1FormBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sno;
	private int inv_no;
	private String lr_no;
	private double amount;
	private Date inv_date;
	private Date lr_date;
	private String branch;
	private String transporter;
	private String truck_no;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public Date getLr_date() {
		return lr_date;
	}
	public void setLr_date(Date lr_date) {
		this.lr_date = lr_date;
	}
	public String getLr_no() {
		return lr_no;
	}
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransporter() {
		return transporter;
	}
	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}

	
}
