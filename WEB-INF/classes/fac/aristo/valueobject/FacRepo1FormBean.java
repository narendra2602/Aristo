package fac.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class FacRepo1FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sno;
	private int invoiceno;
	private Date despatchdate;
	private String depo_name;
	private double value;
	private String transporteid;
	private String lrno;
	private Date lrdate;
	private String vehicleno ;
	private int depo_code;	
	
	private String brval;
	private String brname;
	private String lcval;
	private String lcname;
	private int boxes;
		
	public int getBoxes() {
		return boxes;
	}
	public void setBoxes(int boxes) {
		this.boxes = boxes;
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
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
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
	public int getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(int invoiceno) {
		this.invoiceno = invoiceno;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransporteid() {
		return transporteid;
	}
	public void setTransporteid(String transporteid) {
		this.transporteid = transporteid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
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
