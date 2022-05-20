package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class Cen3FormBean implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String name;
	private int pcode;
	private String pname;
	private String pack;
	private String sbatch_no;
	private int sinv_no;
	private Date sinv_dt;
	private int qty;
	private double mrp;
	private String expiry_dt;
	private String mtr_no;
	private Date mtr_dt;
	private String transport;
	private int colour;
	
	
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public String getExpiry_dt() {
		return expiry_dt;
	}
	public void setExpiry_dt(String expiry_dt) {
		this.expiry_dt = expiry_dt;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSbatch_no() {
		return sbatch_no;
	}
	public void setSbatch_no(String sbatch_no) {
		this.sbatch_no = sbatch_no;
	}
	public Date getSinv_dt() {
		return sinv_dt;
	}
	public void setSinv_dt(Date sinv_dt) {
		this.sinv_dt = sinv_dt;
	}
	public int getSinv_no() {
		return sinv_no;
	}
	public void setSinv_no(int sinv_no) {
		this.sinv_no = sinv_no;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}	
	
 
	
}
