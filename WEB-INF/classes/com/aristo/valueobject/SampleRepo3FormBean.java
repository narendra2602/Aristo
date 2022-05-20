package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class SampleRepo3FormBean implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private int pcode;
	private String pname;
	private String pack;
	private Date rcpdate;
	private String stock;	
	private String bat_no;
	private double bat_netrt;
	private Date expiry;
	private int estock;
	private int estock90;
	private int estock180;
	private int estock360;
	private double tvalue;
	private String fac_name;
	private String gift_name;
	private String remark;
	
	
	
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getGift_name() {
		return gift_name;
	}
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public double getTvalue() {
		return tvalue;
	}
	public void setTvalue(double tvalue) {
		this.tvalue = tvalue;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public Date getRcpdate() {
		return rcpdate;
	}
	public void setRcpdate(Date rcpdate) {
		this.rcpdate = rcpdate;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getEstock180() {
		return estock180;
	}
	public void setEstock180(int estock180) {
		this.estock180 = estock180;
	}



}
