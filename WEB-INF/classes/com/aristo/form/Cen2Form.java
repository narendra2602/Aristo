package com.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Cen2Form extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int qty;
	private String name;
	private String cases;
	private String mtr_no;
	private Date mtr_dt;
	private String transport;
	private String vehicle_no;
	private String sdate;
	private String edate;
	private List rlist;
	
	private String sbranch;
	private List brlist;
	private String brval;
	private String brname;
	private int choice=1;
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public List getBrlist() {
		return brlist;
	}

	public void setBrlist(List brlist) {
		this.brlist = brlist;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}


	public String getBrval() {
		return brval;
	}

	public void setBrval(String brval) {
		this.brval = brval;
	}

	public String getSbranch() {
		return sbranch;
	}

	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
		this.sdate="";
		this.edate="";
		this.choice=1;
	}
	

	public String getCases() {
		return cases;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
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
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	
	
	
}
