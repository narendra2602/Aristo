package fac.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class FacRepo3FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pcode;
	private String pname;
	private String pack;
	private String location;
	private String depo_name;
	private String batchno;
	private int invoiceno;
	private Date despatchdate;
	private int quantity;
	private double mrp;
	private Date expdate;
	private String lrno;
	private Date lrdate;
	private String transportid;
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
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	public String getDepo_name() {
		return depo_name;
	}
	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}
	public Date getDespatchdate() {
		return despatchdate;
	}
	public void setDespatchdate(Date despatchdate) {
		this.despatchdate = despatchdate;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public int getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(int invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getLrdate() {
		return lrdate;
	}
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}
	public String getLrno() {
		return lrno;
	}
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
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
	public String getTransportid() {
		return transportid;
	}
	public void setTransportid(String transportid) {
		this.transportid = transportid;
	}
	
}
