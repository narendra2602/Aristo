package cen.aristo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TransDetailMForm extends ActionForm 
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	 private int tr_code;
     private int ds_code;
     private int dst_cd1;
     private double full_1;
     private double dcm_1;  
     private double perkg_1;
     private double chg_1;
     private double other_chg;
     private double credit_lim;
     private Date period_frm;
     private Date period_to;  
     private double tran_name;
     private String tran_add;
     private String tran_phno;
     
     ///////////////////////////Getter and Setter method of Form/////////////////////////////////
     
	public double getChg_1() {
		return chg_1;
	}
	public void setChg_1(double chg_1) {
		this.chg_1 = chg_1;
	}
	public double getCredit_lim() {
		return credit_lim;
	}
	public void setCredit_lim(double credit_lim) {
		this.credit_lim = credit_lim;
	}
	public double getDcm_1() {
		return dcm_1;
	}
	public void setDcm_1(double dcm_1) {
		this.dcm_1 = dcm_1;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getDs_code() {
		return ds_code;
	}
	public void setDs_code(int ds_code) {
		this.ds_code = ds_code;
	}
	public int getDst_cd1() {
		return dst_cd1;
	}
	public void setDst_cd1(int dst_cd1) {
		this.dst_cd1 = dst_cd1;
	}
	public double getFull_1() {
		return full_1;
	}
	public void setFull_1(double full_1) {
		this.full_1 = full_1;
	}
	public double getOther_chg() {
		return other_chg;
	}
	public void setOther_chg(double other_chg) {
		this.other_chg = other_chg;
	}
	public Date getPeriod_frm() {
		return period_frm;
	}
	public void setPeriod_frm(Date period_frm) {
		this.period_frm = period_frm;
	}
	public Date getPeriod_to() {
		return period_to;
	}
	public void setPeriod_to(Date period_to) {
		this.period_to = period_to;
	}
	public double getPerkg_1() {
		return perkg_1;
	}
	public void setPerkg_1(double perkg_1) {
		this.perkg_1 = perkg_1;
	}
	public int getTr_code() {
		return tr_code;
	}
	public void setTr_code(int tr_code) {
		this.tr_code = tr_code;
	}
	public String getTran_add() {
		return tran_add;
	}
	public void setTran_add(String tran_add) {
		this.tran_add = tran_add;
	}
	public double getTran_name() {
		return tran_name;
	}
	public void setTran_name(double tran_name) {
		this.tran_name = tran_name;
	}
	public String getTran_phno() {
		return tran_phno;
	}
	public void setTran_phno(String tran_phno) {
		this.tran_phno = tran_phno;
	}
	
	     
     
}
