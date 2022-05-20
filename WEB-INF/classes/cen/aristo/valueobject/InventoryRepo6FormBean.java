package cen.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class InventoryRepo6FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int sqty;
	private int sinv_no;
	private Date sinv_dt;
	private String mtr_no;
	private Date mtr_dt;
	private String transport;
	private String pinv_no;
	private Date pinv_date;
	private double gval;
	private String vehicle_no;
	
	public double getGval() {
		return gval;
	}
	public void setGval(double gval) {
		this.gval = gval;
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
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	
	
	
	
}
