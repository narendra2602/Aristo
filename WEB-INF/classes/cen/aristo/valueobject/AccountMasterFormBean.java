package cen.aristo.valueobject;

import java.io.Serializable;
//import java.util.Date;

public class AccountMasterFormBean implements Serializable 
{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int mcmp_code;
	private int musr_cd;
	private int mgrp_code;
	private String mac_code;
	private String mac_type;
	private String mac_prfx;
	private String mac_name;
	private String madd1;
	private String madd2;
	private String madd3;  
	private String mcity;
	private String mpin;
	private String mphone;
	private String mcontct;
	private String mdlno1; 
	private String mdlno2; 
	private String mtranspt;
	private String mpst_no;
	private String mcst_no;
    ////////////////////////Getter and Setter method of Form Bean////////////////////////////////////////////////////
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
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
	public String getMac_prfx() {
		return mac_prfx;
	}
	public void setMac_prfx(String mac_prfx) {
		this.mac_prfx = mac_prfx;
	}
	public String getMac_type() {
		return mac_type;
	}
	public void setMac_type(String mac_type) {
		this.mac_type = mac_type;
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
	public String getMadd3() {
		return madd3;
	}
	public void setMadd3(String madd3) {
		this.madd3 = madd3;
	}
	public String getMcity() {
		return mcity;
	}
	public void setMcity(String mcity) {
		this.mcity = mcity;
	}
	public int getMcmp_code() {
		return mcmp_code;
	}
	public void setMcmp_code(int mcmp_code) {
		this.mcmp_code = mcmp_code;
	}
	public String getMcontct() {
		return mcontct;
	}
	public void setMcontct(String mcontct) {
		this.mcontct = mcontct;
	}
	public String getMcst_no() {
		return mcst_no;
	}
	public void setMcst_no(String mcst_no) {
		this.mcst_no = mcst_no;
	}
	public String getMdlno1() {
		return mdlno1;
	}
	public void setMdlno1(String mdlno1) {
		this.mdlno1 = mdlno1;
	}
	public String getMdlno2() {
		return mdlno2;
	}
	public void setMdlno2(String mdlno2) {
		this.mdlno2 = mdlno2;
	}
	public int getMgrp_code() {
		return mgrp_code;
	}
	public void setMgrp_code(int mgrp_code) {
		this.mgrp_code = mgrp_code;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMpin() {
		return mpin;
	}
	public void setMpin(String mpin) {
		this.mpin = mpin;
	}
	public String getMpst_no() {
		return mpst_no;
	}
	public void setMpst_no(String mpst_no) {
		this.mpst_no = mpst_no;
	}
	public String getMtranspt() {
		return mtranspt;
	}
	public void setMtranspt(String mtranspt) {
		this.mtranspt = mtranspt;
	}
	public int getMusr_cd() {
		return musr_cd;
	}
	public void setMusr_cd(int musr_cd) {
		this.musr_cd = musr_cd;
	}
	
	  
	
}
