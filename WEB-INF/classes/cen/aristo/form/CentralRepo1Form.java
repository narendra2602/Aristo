package cen.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CentralRepo1Form extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private int sno;
	private int inv_no;
	private String lr_no;
	private double amount;
	private Date inv_date;
	private Date lr_date;
	private String sdate;
	private String edate;
	private String branch;
	private String transporter;
	private String truck_no;
	private List rlist;
	
	private String sbranch;
	private List brlist;
	private String brval;
	private String brname;
	private int choice=1;
	
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void setBrval(String brval) {
		this.brval = brval;
	}

	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public Date getLr_date() {
		return lr_date;
	}
	public void setLr_date(Date lr_date) {
		this.lr_date = lr_date;
	}
	public String getLr_no() {
		return lr_no;
	}

	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransporter() {
		return transporter;
	}
	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}


	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
		this.sdate="";
		this.edate="";
		this.choice=1;
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

	public String getBrval() {
		return brval;
	}

	public String getSbranch() {
		return sbranch;
	}


	
	
}