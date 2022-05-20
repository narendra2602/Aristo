package cen.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InventoryRepo5FormBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private String pcode;
	private String name;
	private String city;
	private String pack;
	private String bat_no;
	private String exp_dt;
	private String inv_no;
	private Date inv_dt;
	private int rqty;
	private Date inv_idt;	
	private int sqty;
	private int fqty;	
	private double rate;
	private double tvalue;
	private int colour;
	private List plist;
	private String pname;
	private int pval;
	private int code;	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List getPlist() {
		return plist;
	}
	public void setPlist(List plist) {
		this.plist = plist;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPval() {
		return pval;
	}
	public void setPval(int pval) {
		this.pval = pval;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getExp_dt() {
		return exp_dt;
	}
	public void setExp_dt(String exp_dt) {
		this.exp_dt = exp_dt;
	}
	public String getInv_no() {
		return inv_no;
	}
	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
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
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getRqty() {
		return rqty;
	}
	public void setRqty(int rqty) {
		this.rqty = rqty;
	}
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	public double getTvalue() {
		return tvalue;
	}
	public void setTvalue(double tvalue) {
		this.tvalue = tvalue;
	}
	public Date getInv_dt() {
		return inv_dt;
	}
	public void setInv_dt(Date inv_dt) {
		this.inv_dt = inv_dt;
	}
	public Date getInv_idt() {
		return inv_idt;
	}
	public void setInv_idt(Date inv_idt) {
		this.inv_idt = inv_idt;
	}
	public int getFqty() {
		return fqty;
	}
	public void setFqty(int fqty) {
		this.fqty = fqty;
	}

	
}
