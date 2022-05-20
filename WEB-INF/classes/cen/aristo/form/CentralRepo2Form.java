package cen.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CentralRepo2Form extends ActionForm {

	
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
	private List rlist;
	
	private int sbranch;
	private List brlist;
	private int brval;
	private String brname;
	private int choice=1;
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public List getBrlist() {
		return brlist;
	}
	public void setBrlist(List brlist) {
		this.brlist = brlist;
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
	public int getSbranch() {
		return sbranch;
	}
	public void setSbranch(int sbranch) {
		this.sbranch = sbranch;
	}
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
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	
	
}

