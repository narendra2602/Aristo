package com.aristo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class Repo2OptForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rep_type=1;
	private int pg=1;
	private int uv=1;
	private int sale_type=1;
	private int mnth=10; 
	private int r_type=1;
	
	public int getMnth() {
		return mnth;
	}
	public void setMnth(int mnth) {
		this.mnth = mnth;
	}
	public int getPg() {
		return pg;
	}
	public void setPg(int pg) {
		this.pg = pg;
	}
	public int getRep_type() {
		return rep_type;
	}
	public void setRep_type(int rep_type) {
		this.rep_type = rep_type;
	}
	public int getSale_type() {
		return sale_type;
	}
	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	
    public void reset(ActionMapping mapping, HttpServletRequest req) {

    	this.rep_type=1;
    	this.pg=1;
    	this.uv=1;
    	this.sale_type=1;
    	this.r_type=1;
    	this.mnth=10;  
    
    
    }
	public int getR_type() {
		return r_type;
	}
	public void setR_type(int r_type) {
		this.r_type = r_type;
	}
	
}
