package fac.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class FacRepo2FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String location;
	private String depo_name;
	private String productname;
	private int quantity;
	private int boxes;
	private String lrno;
	private Date lrdate;
	private String transporterid;
	private String vehicleno;
	private int colour;
	
	private String brval;
	private String brname;
	private String lcval;
	private String lcname;
	private int lcase;
	private int ncase;
	private int invoice;
	
	
	public int getInvoice() {
		return invoice;
	}
	public void setInvoice(int invoice) {
		this.invoice = invoice;
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
	public int getBoxes() {
		return boxes;
	}
	public void setBoxes(int boxes) {
		this.boxes = boxes;
	}
	public String getDepo_name() {
		return depo_name;
	}
	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
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
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTransporterid() {
		return transporterid;
	}
	public void setTransporterid(String transporterid) {
		this.transporterid = transporterid;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public int getLcase() {
		return lcase;
	}
	public void setLcase(int lcase) {
		this.lcase = lcase;
	}
	public int getNcase() {
		return ncase;
	}
	public void setNcase(int ncase) {
		this.ncase = ncase;
	}
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}
	public String getLrno() {
		return lrno;
	}
	
	
}
