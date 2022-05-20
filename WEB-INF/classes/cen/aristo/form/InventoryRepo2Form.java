package cen.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InventoryRepo2Form extends ActionForm {


	private static final long serialVersionUID = 1L;
	private String sdate;
	private int pcode;
	private String pname;
	private String bat_no;
	private double bat_netrt;
	private Date expiry;
	private int estock;
	private int estock90;
	private int estock360;
	private double tvalue;
	private List rlist;
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.sdate="";
	}
	
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public double getBat_netrt() {
		return bat_netrt;
	}
	public void setBat_netrt(double bat_netrt) {
		this.bat_netrt = bat_netrt;
	}
	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public int getEstock() {
		return estock;
	}
	public void setEstock(int estock) {
		this.estock = estock;
	}
	public int getEstock360() {
		return estock360;
	}
	public void setEstock360(int estock360) {
		this.estock360 = estock360;
	}
	public int getEstock90() {
		return estock90;
	}
	public void setEstock90(int estock90) {
		this.estock90 = estock90;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
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
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public double getTvalue() {
		return tvalue;
	}
	public void setTvalue(double tvalue) {
		this.tvalue = tvalue;
	}



}
