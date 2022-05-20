package cen.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InventoryRepo6Form extends ActionForm {

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
	private int doc_type;
	private String sdate;
	private String edate;
	private List rlist;
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.doc_type=60;
	this.sdate="";
	this.edate="";
	}
	
	public int getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(int doc_type) {
		this.doc_type = doc_type;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
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
