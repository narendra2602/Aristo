package cen.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class InvSndFormBean implements Serializable 
{   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int depo_code;
	private int sdepo_code;
	private int sdoc_type;
	private int sinv_no;
	private String sinv_lo;
	private int sinv_yr;
	private Date sinv_dt;
	private String spinv_no;
	private Date spinv_dt;
	private Date sentry_dt;
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
	private double stax_cd1;
	private double stax_cd2;
	private String oct_type;
	private double octroi;
	private double srate_net;
	private double srate_pur;
	private double srate_trd;
	private double srate_mrp;
	private double srate_hos;
	private double srate_mfg;
	private double srate_exc;
	private double samnt;
	private String sdel_tg;
	private int smr_cd;
	private int stat_cd;
	private String stax_type;
	private double secess;
	private double srate_net1;
	private double srate_trd1;
	private double srate_mrp1;
	private double 	actual_wet;
	private int skno;
	private String ymonth;
	
   /////////////////////Getter and Setter method of FormBean.//////////////////
	
	
	
	public String getYmonth() {
		return ymonth;
	}
	public void setYmonth(String ymonth) {
		this.ymonth = ymonth;
	}
	public double getActual_wet() {
		return actual_wet;
	}
	public void setActual_wet(double actual_wet) {
		this.actual_wet = actual_wet;
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
	public double getSamnt() {
		return samnt;
	}
	public void setSamnt(double samnt) {
		this.samnt = samnt;
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
	public double getSecess() {
		return secess;
	}
	public void setSecess(double secess) {
		this.secess = secess;
	}
	public Date getSentry_dt() {
		return sentry_dt;
	}
	public void setSentry_dt(Date sentry_dt) {
		this.sentry_dt = sentry_dt;
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
	public Date getSpinv_dt() {
		return spinv_dt;
	}
	public void setSpinv_dt(Date spinv_dt) {
		this.spinv_dt = spinv_dt;
	}
	public String getSpinv_no() {
		return spinv_no;
	}
	public void setSpinv_no(String spinv_no) {
		this.spinv_no = spinv_no;
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
	public double getSrate_exc() {
		return srate_exc;
	}
	public void setSrate_exc(double srate_exc) {
		this.srate_exc = srate_exc;
	}
	public double getSrate_hos() {
		return srate_hos;
	}
	public void setSrate_hos(double srate_hos) {
		this.srate_hos = srate_hos;
	}
	public double getSrate_mfg() {
		return srate_mfg;
	}
	public void setSrate_mfg(double srate_mfg) {
		this.srate_mfg = srate_mfg;
	}
	public double getSrate_mrp() {
		return srate_mrp;
	}
	public void setSrate_mrp(double srate_mrp) {
		this.srate_mrp = srate_mrp;
	}
	public double getSrate_mrp1() {
		return srate_mrp1;
	}
	public void setSrate_mrp1(double srate_mrp1) {
		this.srate_mrp1 = srate_mrp1;
	}
	public double getSrate_net() {
		return srate_net;
	}
	public void setSrate_net(double srate_net) {
		this.srate_net = srate_net;
	}
	public double getSrate_net1() {
		return srate_net1;
	}
	public void setSrate_net1(double srate_net1) {
		this.srate_net1 = srate_net1;
	}
	public double getSrate_pur() {
		return srate_pur;
	}
	public void setSrate_pur(double srate_pur) {
		this.srate_pur = srate_pur;
	}
	public double getSrate_trd() {
		return srate_trd;
	}
	public void setSrate_trd(double srate_trd) {
		this.srate_trd = srate_trd;
	}
	public double getSrate_trd1() {
		return srate_trd1;
	}
	public void setSrate_trd1(double srate_trd1) {
		this.srate_trd1 = srate_trd1;
	}
	public int getStat_cd() {
		return stat_cd;
	}
	public void setStat_cd(int stat_cd) {
		this.stat_cd = stat_cd;
	}
	public double getStax_cd1() {
		return stax_cd1;
	}
	public void setStax_cd1(double stax_cd1) {
		this.stax_cd1 = stax_cd1;
	}
	public double getStax_cd2() {
		return stax_cd2;
	}
	public void setStax_cd2(double stax_cd2) {
		this.stax_cd2 = stax_cd2;
	}
	public String getStax_type() {
		return stax_type;
	}
	public void setStax_type(String stax_type) {
		this.stax_type = stax_type;
	}
	public String getStrn_tp() {
		return strn_tp;
	}
	public void setStrn_tp(String strn_tp) {
		this.strn_tp = strn_tp;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getSkno() {
		return skno;
	}
	public void setSkno(int skno) {
		this.skno = skno;
	}
	
	
	
	   

	
}
