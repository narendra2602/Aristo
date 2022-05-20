package com.aristo.valueobject;

import java.io.Serializable;

  
public class GroupMktFormBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gp_code;
	private String gp_name;
	private int depo_code;
	private String gp_type;
	private int gmain_cd;
	private String gmain_nm;
	private String slct;
	private String link1;
	
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getGmain_cd() {
		return gmain_cd;
	}
	public void setGmain_cd(int gmain_cd) {
		this.gmain_cd = gmain_cd;
	}
	public String getGmain_nm() {
		return gmain_nm;
	}
	public void setGmain_nm(String gmain_nm) {
		this.gmain_nm = gmain_nm;
	}
	public int getGp_code() {
		return gp_code;
	}
	public void setGp_code(int gp_code) {
		this.gp_code = gp_code;
	}
	public String getGp_name() {
		return gp_name;
	}
	public void setGp_name(String gp_name) {
		this.gp_name = gp_name;
	}
	public String getGp_type() {
		return gp_type;
	}
	public void setGp_type(String gp_type) {
		this.gp_type = gp_type;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	
	
	
}
