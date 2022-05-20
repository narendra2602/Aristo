package com.aristo.valueobject;
import java.io.Serializable;
import java.util.Date;

public class SampleRepo6FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int inv_no;
	private Date inv_dt;
	private String name;
	private String city;
	private String lr_no;
	private Date lr_dt;
	private String transport;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getInv_dt() {
		return inv_dt;
	}
	public void setInv_dt(Date inv_dt) {
		this.inv_dt = inv_dt;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public Date getLr_dt() {
		return lr_dt;
	}
	public void setLr_dt(Date lr_dt) {
		this.lr_dt = lr_dt;
	}
	public String getLr_no() {
		return lr_no;
	}
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	

}
