package cen.aristo.valueobject;

import java.io.Serializable;

public class CentralRepo2FormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String product;
	private String packing;
	private String batch_no;
	private int qty;
	private double mrp;
	private String expiry;
	private int qtypc;
	private double gross_wt;
	private String remark;
	private int colour;
	
	
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public double getGross_wt() {
		return gross_wt;
	}
	public void setGross_wt(double gross_wt) {
		this.gross_wt = gross_wt;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public String getPacking() {
		return packing;
	}
	public void setPacking(String packing) {
		this.packing = packing;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getQtypc() {
		return qtypc;
	}
	public void setQtypc(int qtypc) {
		this.qtypc = qtypc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getColour() {
		return colour;
	}
	public void setColour(int colour) {
		this.colour = colour;
	}
	
}
