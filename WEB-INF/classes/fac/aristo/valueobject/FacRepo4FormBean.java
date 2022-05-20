package fac.aristo.valueobject;

import java.io.Serializable;

public class FacRepo4FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pname;
	private String pack;
	private int quantity;
	private int colour;
	
	private String brval;
	private String brname;
	private String lcval;
	private String lcname;
	
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
	public String getLcname() {
		return lcname;
	}
	public void setLcname(String lcname) {
		this.lcname = lcname;
	}
	public String getLcval() {
		return lcval;
	}
	public void setLcval(String lcval) {
		this.lcval = lcval;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
