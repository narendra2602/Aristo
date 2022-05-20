package cen.aristo.valueobject;
import java.io.Serializable;
import java.util.Date;

public class InventoryRepo9FormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String inv_no;
	private Date inv_dt;
	private double inv_val;
	private double cst_amt;
	private double total;
	private int cases;
	private String lr_no;
	private Date lr_dt;
	private String transport;
	private String vehicle_no;

	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public double getCst_amt() {
		return cst_amt;
	}

	public void setCst_amt(double cst_amt) {
		this.cst_amt = cst_amt;
	}



	public Date getInv_dt() {
		return inv_dt;
	}

	public void setInv_dt(Date inv_dt) {
		this.inv_dt = inv_dt;
	}

	public String getInv_no() {
		return inv_no;
	}

	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}

	public double getInv_val() {
		return inv_val;
	}

	public void setInv_val(double inv_val) {
		this.inv_val = inv_val;
	}

	public Date getLr_dt() {
		return lr_dt;
	}

	public void setLr_dt(Date lr_dt) {
		this.lr_dt = lr_dt;
	}

	public String getLr_no() {
		return lr_no;
	}

	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	
	
	
	
}
