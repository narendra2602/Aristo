package com.aristo.valueobject;

import java.io.Serializable;
 
import java.util.List;

public class Cen5FormBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private String pcode;
	private String name;
	private String city;
	private String pack;
	private String bat_no;
	private String exp_dt;
	private int sqty;
	private int sfree;
	private String net;
	private String trd;
	private String mrp;
	private String mfg;
	private String exc;
	
	private int colour;
	private List plist;
	private String pname;
	private int pval;
	private int code;
	
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public String getExp_dt() {
		return exp_dt;
	}
	public void setExp_dt(String exp_dt) {
		this.exp_dt = exp_dt;
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
	public int getSfree() {
		return sfree;
	}
	public void setSfree(int sfree) {
		this.sfree = sfree;
	}
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	public String getExc() {
		return exc;
	}
	public void setExc(String exc) {
		this.exc = exc;
	}
	public String getMfg() {
		return mfg;
	}
	public void setMfg(String mfg) {
		this.mfg = mfg;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getTrd() {
		return trd;
	}
	public void setTrd(String trd) {
		this.trd = trd;
	}
	
	
}
