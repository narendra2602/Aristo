package com.aristo.valueobject;

import java.io.Serializable;



public class TerFormBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int ter_code;
	private int area_code;
	private int regn_code;
	private String ter_name;
	private int no_of_rep;
	private int rep_cd1;
	private int rep_cd2;
	private int rep_cd3;
	private int rep_cd4;
	private String txt;
	private String ter_pt;
	private int oct;
	private int nov; 
	private int dec;
	private int jan;
	private int feb;
	private int mar;
	private int apr;
	private int may;
	private int jun;
	private int july;
	private int aug;
	private int sep;
	
	//  New Fields
	
	
	private String slct;
	private int state_code;
	private int rep_cd5;
	private int rep_cd6;
	/////////////////
	
	private int dgm_code;
	private String dgm_name;
	private int zm_code;
	private String zm_name;
	private String branches;
	private int code;
	private int code1; 
	
	
	
	private String link2;
	private String link1;
	private int mkt_year;
	
	
	
	
	public int getMkt_year() {
		return mkt_year;
	}
	public void setMkt_year(int mkt_year) {
		this.mkt_year = mkt_year;
	}
	public int getArea_code() {
		return area_code;
	}
	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getNo_of_rep() {
		return no_of_rep;
	}
	public void setNo_of_rep(int no_of_rep) {
		this.no_of_rep = no_of_rep;
	}
	public int getRegn_code() {
		return regn_code;
	}
	public void setRegn_code(int regn_code) {
		this.regn_code = regn_code;
	}
	public int getRep_cd1() {
		return rep_cd1;
	}
	public void setRep_cd1(int rep_cd1) {
		this.rep_cd1 = rep_cd1;
	}
	public int getRep_cd2() {
		return rep_cd2;
	}
	public void setRep_cd2(int rep_cd2) {
		this.rep_cd2 = rep_cd2;
	}
	public int getRep_cd3() {
		return rep_cd3;
	}
	public void setRep_cd3(int rep_cd3) {
		this.rep_cd3 = rep_cd3;
	}
	public int getRep_cd4() {
		return rep_cd4;
	}
	public void setRep_cd4(int rep_cd4) {
		this.rep_cd4 = rep_cd4;
	}
	public int getRep_cd5() {
		return rep_cd5;
	}
	public void setRep_cd5(int rep_cd5) {
		this.rep_cd5 = rep_cd5;
	}
	public int getRep_cd6() {
		return rep_cd6;
	}
	public void setRep_cd6(int rep_cd6) {
		this.rep_cd6 = rep_cd6;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	
	public int getTer_code() {
		return ter_code;
	}
	public void setTer_code(int ter_code) {
		this.ter_code = ter_code;
	}
	public String getTer_name() {
		return ter_name;
	}
	public void setTer_name(String ter_name) {
		this.ter_name = ter_name;
	}
	public String getTer_pt() {
		return ter_pt;
	}
	public void setTer_pt(String ter_pt) {
		this.ter_pt = ter_pt;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public int getApr() {
		return apr;
	}
	public void setApr(int apr) {
		this.apr = apr;
	}
	public int getAug() {
		return aug;
	}
	public void setAug(int aug) {
		this.aug = aug;
	}
	public int getDec() {
		return dec;
	}
	public void setDec(int dec) {
		this.dec = dec;
	}
	public int getFeb() {
		return feb;
	}
	public void setFeb(int feb) {
		this.feb = feb;
	}
	public int getJan() {
		return jan;
	}
	public void setJan(int jan) {
		this.jan = jan;
	}
	public int getJuly() {
		return july;
	}
	public void setJuly(int july) {
		this.july = july;
	}
	public int getJun() {
		return jun;
	}
	public void setJun(int jun) {
		this.jun = jun;
	}
	public int getMar() {
		return mar;
	}
	public void setMar(int mar) {
		this.mar = mar;
	}
	public int getMay() {
		return may;
	}
	public void setMay(int may) {
		this.may = may;
	}
	public int getNov() {
		return nov;
	}
	public void setNov(int nov) {
		this.nov = nov;
	}
	public int getOct() {
		return oct;
	}
	public void setOct(int oct) {
		this.oct = oct;
	}
	public int getSep() {
		return sep;
	}
	public void setSep(int sep) {
		this.sep = sep;
	}
	public String getBranches() {
		return branches;
	}
	public void setBranches(String branches) {
		this.branches = branches;
	}
	
	public int getDgm_code() {
		return dgm_code;
	}
	public void setDgm_code(int dgm_code) {
		this.dgm_code = dgm_code;
	}
	
	public String getDgm_name() {
		return dgm_name;
	}
	public void setDgm_name(String dgm_name) {
		this.dgm_name = dgm_name;
	}
	public int getZm_code() {
		return zm_code;
	}
	public void setZm_code(int zm_code) {
		this.zm_code = zm_code;
	}
	public String getZm_name() {
		return zm_name;
	}
	public void setZm_name(String zm_name) {
		this.zm_name = zm_name;
	}
	public String getLink2() {
		return link2;
	}
	public void setLink2(String link2) {
		this.link2 = link2;
	}
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode1() {
		return code1;
	}
	public void setCode1(int code1) {
		this.code1 = code1;
	}
	
	

}
