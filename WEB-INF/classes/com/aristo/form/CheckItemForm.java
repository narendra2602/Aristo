package com.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CheckItemForm extends ActionForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String branch_name;
	private int pcode;
	private int mcode;
	private List rlist;
	
	
	
 
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	
	

}
