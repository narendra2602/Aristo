package fac.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class FacRepo5FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int pcode;
	private String pname;
	private String pack;
	private String depo_name;
	private String batchno;
	private Date expdate;
	private int quantity;
	private int colour;
	private int brval;
	private String brname;

	private String lcval;
	private String lcname;
	private Date lrdate;
	
	public Date getLrdate() {
		return lrdate;
	}
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}
	public String getBrname() {
		return brname;
	}
	public void setBrname(String brname) {
		this.brname = brname;
	}
	public int getBrval() {
		return brval;
	}
	public void setBrval(int brval) {
		this.brval = brval;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getDepo_name() {
		return depo_name;
	}
	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	

}
