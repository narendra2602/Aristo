package com.aristo.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class SampleRepo7Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private int inv_no;
	private Date inv_dt;
	private String name;
	private String city;
	private String inv_type;
	private String lr_no;
	private Date lr_dt;
	private String transport;
	private List rlist;
	private String sdate;
	private String edate;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
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
	public String getInv_type() {
		return inv_type;
	}
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
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
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}

	
}
