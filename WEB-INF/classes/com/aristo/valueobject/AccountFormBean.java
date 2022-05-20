package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class AccountFormBean  implements Serializable {
	 
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
	private String mbanker;
	private String mbank_add1;
	private String mbank_add2;
	private String mtranspt;
	private String mpst_no;
	private String mcst_no;
	private float mfix_disc1;
	private float mfix_disc2;
	private float mfix_tax1;
	private float mfix_tax2;
	private int mstat_code;
	private int marea_code;
	private int marea_cd1;
	private int mregion_cd;
	private int mregio_cd1;
	private int mterr_code;
	private int mterr_cd1;
	private int mdist_cd;
	private String mtype;
	private int mdays;
	private float octroi1;
	private float octroi2;
	private float octroi3;
	private String cst_type;
	private int msr_code;
	private int msr_code1;
	private float mcurr_bal;
	private String mcurr_db;
	private float mopng_bal;
	private String mopng_db;
	private float mlopng_bal;
	private String mlopng_db;
	private float mlclos_bal;
	private String mlclos_db;
	private float mnth_bal;
	private float mnth_dr;
	private float mnth_cr;
	private String mnth_db;
	private String ptype;
	private int mdist1;
	private String slct;
	private int mkt_year;
	private Date joining_dt;
	private Date resigna_dt;
	
	public int getMkt_year() {
		return mkt_year;
	}
	public void setMkt_year(int mkt_year) {
		this.mkt_year = mkt_year;
	}
	public String getCst_type() {
		return cst_type;
	}
	public void setCst_type(String cst_type) {
		this.cst_type = cst_type;
	}
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
	public int getMarea_cd1() {
		return marea_cd1;
	}
	public void setMarea_cd1(int marea_cd1) {
		this.marea_cd1 = marea_cd1;
	}
	public int getMarea_code() {
		return marea_code;
	}
	public void setMarea_code(int marea_code) {
		this.marea_code = marea_code;
	}
	public String getMbank_add1() {
		return mbank_add1;
	}
	public void setMbank_add1(String mbank_add1) {
		this.mbank_add1 = mbank_add1;
	}
	public String getMbank_add2() {
		return mbank_add2;
	}
	public void setMbank_add2(String mbank_add2) {
		this.mbank_add2 = mbank_add2;
	}
	public String getMbanker() {
		return mbanker;
	}
	public void setMbanker(String mbanker) {
		this.mbanker = mbanker;
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
	public float getMcurr_bal() {
		return mcurr_bal;
	}
	public void setMcurr_bal(float mcurr_bal) {
		this.mcurr_bal = mcurr_bal;
	}
	public String getMcurr_db() {
		return mcurr_db;
	}
	public void setMcurr_db(String mcurr_db) {
		this.mcurr_db = mcurr_db;
	}
	public int getMdays() {
		return mdays;
	}
	public void setMdays(int mdays) {
		this.mdays = mdays;
	}
	public int getMdist_cd() {
		return mdist_cd;
	}
	public void setMdist_cd(int mdist_cd) {
		this.mdist_cd = mdist_cd;
	}
	public int getMdist1() {
		return mdist1;
	}
	public void setMdist1(int mdist1) {
		this.mdist1 = mdist1;
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
	public float getMfix_disc1() {
		return mfix_disc1;
	}
	public void setMfix_disc1(float mfix_disc1) {
		this.mfix_disc1 = mfix_disc1;
	}
	public float getMfix_disc2() {
		return mfix_disc2;
	}
	public void setMfix_disc2(float mfix_disc2) {
		this.mfix_disc2 = mfix_disc2;
	}
	public float getMfix_tax1() {
		return mfix_tax1;
	}
	public void setMfix_tax1(float mfix_tax1) {
		this.mfix_tax1 = mfix_tax1;
	}
	public float getMfix_tax2() {
		return mfix_tax2;
	}
	public void setMfix_tax2(float mfix_tax2) {
		this.mfix_tax2 = mfix_tax2;
	}
	public int getMgrp_code() {
		return mgrp_code;
	}
	public void setMgrp_code(int mgrp_code) {
		this.mgrp_code = mgrp_code;
	}
	public float getMlclos_bal() {
		return mlclos_bal;
	}
	public void setMlclos_bal(float mlclos_bal) {
		this.mlclos_bal = mlclos_bal;
	}
	public String getMlclos_db() {
		return mlclos_db;
	}
	public void setMlclos_db(String mlclos_db) {
		this.mlclos_db = mlclos_db;
	}
	public float getMlopng_bal() {
		return mlopng_bal;
	}
	public void setMlopng_bal(float mlopng_bal) {
		this.mlopng_bal = mlopng_bal;
	}
	public String getMlopng_db() {
		return mlopng_db;
	}
	public void setMlopng_db(String mlopng_db) {
		this.mlopng_db = mlopng_db;
	}
	public float getMnth_bal() {
		return mnth_bal;
	}
	public void setMnth_bal(float mnth_bal) {
		this.mnth_bal = mnth_bal;
	}
	public float getMnth_cr() {
		return mnth_cr;
	}
	public void setMnth_cr(float mnth_cr) {
		this.mnth_cr = mnth_cr;
	}
	public String getMnth_db() {
		return mnth_db;
	}
	public void setMnth_db(String mnth_db) {
		this.mnth_db = mnth_db;
	}
	public float getMnth_dr() {
		return mnth_dr;
	}
	public void setMnth_dr(float mnth_dr) {
		this.mnth_dr = mnth_dr;
	}
	public float getMopng_bal() {
		return mopng_bal;
	}
	public void setMopng_bal(float mopng_bal) {
		this.mopng_bal = mopng_bal;
	}
	public String getMopng_db() {
		return mopng_db;
	}
	public void setMopng_db(String mopng_db) {
		this.mopng_db = mopng_db;
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
	public int getMregio_cd1() {
		return mregio_cd1;
	}
	public void setMregio_cd1(int mregio_cd1) {
		this.mregio_cd1 = mregio_cd1;
	}
	public int getMregion_cd() {
		return mregion_cd;
	}
	public void setMregion_cd(int mregion_cd) {
		this.mregion_cd = mregion_cd;
	}
	public int getMsr_code() {
		return msr_code;
	}
	public void setMsr_code(int msr_code) {
		this.msr_code = msr_code;
	}
	public int getMsr_code1() {
		return msr_code1;
	}
	public void setMsr_code1(int msr_code1) {
		this.msr_code1 = msr_code1;
	}
	public int getMstat_code() {
		return mstat_code;
	}
	public void setMstat_code(int mstat_code) {
		this.mstat_code = mstat_code;
	}
	public int getMterr_cd1() {
		return mterr_cd1;
	}
	public void setMterr_cd1(int mterr_cd1) {
		this.mterr_cd1 = mterr_cd1;
	}
	public int getMterr_code() {
		return mterr_code;
	}
	public void setMterr_code(int mterr_code) {
		this.mterr_code = mterr_code;
	}
	public String getMtranspt() {
		return mtranspt;
	}
	public void setMtranspt(String mtranspt) {
		this.mtranspt = mtranspt;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public int getMusr_cd() {
		return musr_cd;
	}
	public void setMusr_cd(int musr_cd) {
		this.musr_cd = musr_cd;
	}
	public float getOctroi1() {
		return octroi1;
	}
	public void setOctroi1(float octroi1) {
		this.octroi1 = octroi1;
	}
	public float getOctroi2() {
		return octroi2;
	}
	public void setOctroi2(float octroi2) {
		this.octroi2 = octroi2;
	}
	public float getOctroi3() {
		return octroi3;
	}
	public void setOctroi3(float octroi3) {
		this.octroi3 = octroi3;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public Date getJoining_dt() {
		return joining_dt;
	}
	public void setJoining_dt(Date joining_dt) {
		this.joining_dt = joining_dt;
	}
	public Date getResigna_dt() {
		return resigna_dt;
	}
	public void setResigna_dt(Date resigna_dt) {
		this.resigna_dt = resigna_dt;
	}
	
	
	
}
