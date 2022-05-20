package cen.aristo.valueobject;

import java.io.Serializable;

public class InventoryRepo1FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int opng;
	private int recp;
	private int total;
	private int issu;
	private int clos;
	private double closval;
	private double srate_net1;
	
	public double getSrate_net1() {
		return srate_net1;
	}
	public void setSrate_net1(double srate_net1) {
		this.srate_net1 = srate_net1;
	}
	public int getClos() {
		return clos;
	}
	public void setClos(int clos) {
		this.clos = clos;
	}
	public double getClosval() {
		return closval;
	}
	public void setClosval(double closval) {
		this.closval = closval;
	}
	public int getIssu() {
		return issu;
	}
	public void setIssu(int issu) {
		this.issu = issu;
	}
	public int getOpng() {
		return opng;
	}
	public void setOpng(int opng) {
		this.opng = opng;
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
	public int getRecp() {
		return recp;
	}
	public void setRecp(int recp) {
		this.recp = recp;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
	
}
