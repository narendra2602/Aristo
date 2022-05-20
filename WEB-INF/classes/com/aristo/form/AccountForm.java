package com.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class AccountForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mac_code;
	private String mac_name;
	private String madd1;
	private String madd2;
	private String mcity;
	
	private List rlist;

 

	public String getMac_code() {
		return mac_code;
	}

	public void setMac_code(String mac_code) {
		this.mac_code = mac_code;
	}

	public String getMac_name() {
		return mac_name;
	}

	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}

	public String getMcity() {
		return mcity;
	}

	public void setMcity(String mcity) {
		this.mcity = mcity;
	}

 

	public String getMadd1() {
		return madd1;
	}

	public void setMadd1(String madd1) {
		this.madd1 = madd1;
	}

	public String getMadd2() {
		return madd2;
	}

	public void setMadd2(String madd2) {
		this.madd2 = madd2;
	}

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	
	
	
}
