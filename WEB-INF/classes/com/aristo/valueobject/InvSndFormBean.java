package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class InvSndFormBean implements Serializable 
{   
	private static final long serialVersionUID = 1L;
    
	private int sdepo_code;
	private int sdoc_type;
	private int sinv_no;
	private String sinv_lo;
	private int sinv_yr;
	private Date sinv_dt;
	private String strn_tp;
	private String sprt_cd;
	private int sprd_cd;
	private int spd_gp;
	private int sale_type;
	private String sbatch_no;
	private String smfg_dt;
	private String sexp_dt;
	private int sqty;
	private int sfree_qty;
	private String oct_type;
	private double octroi;
	private String sdel_tg;
	private int smr_cd;
	private int stat_cd;
	private int area_cd;
	private int inv_dsm;
	private int terr_cd;
	private int inv_dist;
	private String br_tag;
	private int ndays;
	private int skno;
	private int sunit_cd;
	
	
	public int getArea_cd() {
		return area_cd;
	}
	public void setArea_cd(int area_cd) {
		this.area_cd = area_cd;
	}
	public String getBr_tag() {
		return br_tag;
	}
	public void setBr_tag(String br_tag) {
		this.br_tag = br_tag;
	}
	public int getInv_dist() {
		return inv_dist;
	}
	public void setInv_dist(int inv_dist) {
		this.inv_dist = inv_dist;
	}
	public int getInv_dsm() {
		return inv_dsm;
	}
	public void setInv_dsm(int inv_dsm) {
		this.inv_dsm = inv_dsm;
	}
	public int getNdays() {
		return ndays;
	}
	public void setNdays(int ndays) {
		this.ndays = ndays;
	}
	public String getOct_type() {
		return oct_type;
	}
	public void setOct_type(String oct_type) {
		this.oct_type = oct_type;
	}
	public double getOctroi() {
		return octroi;
	}
	public void setOctroi(double octroi) {
		this.octroi = octroi;
	}
	public int getSale_type() {
		return sale_type;
	}
	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}
	public String getSbatch_no() {
		return sbatch_no;
	}
	public void setSbatch_no(String sbatch_no) {
		this.sbatch_no = sbatch_no;
	}
	public String getSdel_tg() {
		return sdel_tg;
	}
	public void setSdel_tg(String sdel_tg) {
		this.sdel_tg = sdel_tg;
	}
	public int getSdepo_code() {
		return sdepo_code;
	}
	public void setSdepo_code(int sdepo_code) {
		this.sdepo_code = sdepo_code;
	}
	public int getSdoc_type() {
		return sdoc_type;
	}
	public void setSdoc_type(int sdoc_type) {
		this.sdoc_type = sdoc_type;
	}
	public String getSexp_dt() {
		return sexp_dt;
	}
	public void setSexp_dt(String sexp_dt) {
		this.sexp_dt = sexp_dt;
	}
	public int getSfree_qty() {
		return sfree_qty;
	}
	public void setSfree_qty(int sfree_qty) {
		this.sfree_qty = sfree_qty;
	}
	public Date getSinv_dt() {
		return sinv_dt;
	}
	public void setSinv_dt(Date sinv_dt) {
		this.sinv_dt = sinv_dt;
	}
	public String getSinv_lo() {
		return sinv_lo;
	}
	public void setSinv_lo(String sinv_lo) {
		this.sinv_lo = sinv_lo;
	}
	public int getSinv_no() {
		return sinv_no;
	}
	public void setSinv_no(int sinv_no) {
		this.sinv_no = sinv_no;
	}
	public int getSinv_yr() {
		return sinv_yr;
	}
	public void setSinv_yr(int sinv_yr) {
		this.sinv_yr = sinv_yr;
	}
	public int getSkno() {
		return skno;
	}
	public void setSkno(int skno) {
		this.skno = skno;
	}
	public String getSmfg_dt() {
		return smfg_dt;
	}
	public void setSmfg_dt(String smfg_dt) {
		this.smfg_dt = smfg_dt;
	}
	public int getSmr_cd() {
		return smr_cd;
	}
	public void setSmr_cd(int smr_cd) {
		this.smr_cd = smr_cd;
	}
	public int getSpd_gp() {
		return spd_gp;
	}
	public void setSpd_gp(int spd_gp) {
		this.spd_gp = spd_gp;
	}
	public int getSprd_cd() {
		return sprd_cd;
	}
	public void setSprd_cd(int sprd_cd) {
		this.sprd_cd = sprd_cd;
	}
	public String getSprt_cd() {
		return sprt_cd;
	}
	public void setSprt_cd(String sprt_cd) {
		this.sprt_cd = sprt_cd;
	}
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	public int getStat_cd() {
		return stat_cd;
	}
	public void setStat_cd(int stat_cd) {
		this.stat_cd = stat_cd;
	}
	public String getStrn_tp() {
		return strn_tp;
	}
	public void setStrn_tp(String strn_tp) {
		this.strn_tp = strn_tp;
	}
	public int getTerr_cd() {
		return terr_cd;
	}
	public void setTerr_cd(int terr_cd) {
		this.terr_cd = terr_cd;
	}
	public int getSunit_cd() {
		return sunit_cd;
	}
	public void setSunit_cd(int sunit_cd) {
		this.sunit_cd = sunit_cd;
	}
	
	
	
	   

	
}
