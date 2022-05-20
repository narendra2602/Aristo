package com.aristo.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class AreaForm  extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int area_cd;
	private String area_name;
	private String slct;
	private String typ;
	private List alist;
	
	public List getAlist() {
		return alist;
	}
	public void setAlist(List alist) {
		this.alist = alist;
	}
	public int getArea_cd() {
		return area_cd;
	}
	public void setArea_cd(int area_cd) {
		this.area_cd = area_cd;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	
	

}
