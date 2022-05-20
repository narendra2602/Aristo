package cen.aristo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class InvFstForm extends ActionForm 
{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;  
	private int doc_type;
	private String party_code;
	private int inv_no;
	private String inv_lo;   
 	private int inv_yr;
	private Date inv_date;
	private String pinv_no;
	private Date pinv_date;
	private Date entry_dt;
	private String chl_no;
	private Date chl_dt;
	private String mtr_no;
	private Date mtr_dt;
	private int cases;
	private int due_days;
	private Date due_dt;
	private String transport;
	private String bank;
	private String drug_lc1;
	private String drug_lc2;
	private double gross_amt; 
	private double tax_1;
	private double tax_2;
	private int stat_cd;
	private double bill_amt;
	private double lsale1;
	private double lsale2;
	private double lsale3;
	private double ltax1_per;
	private double ltax1_amt;
	private double ltax2_per;
 	private double ltax2_amt;
	private double ltax3_per;
	private double ltax3_amt;
	private double ctax1_per;
	private double ctax1_amt;
	private double ctax2_per;
	private double ctax2_amt;
	private double ctax3_per;
	private double ctax3_amt; 
	private double octroi;
	private double mfg_amt;
	private String inv_type;
	private double mr_mfg;
	private String vehicle_no;
	private String permit_no;
	private Date permit_dt;
	private int trn_cd;
	private double full_truck;
	private double dcm_truck;
	private double rate_perkg;
	private String load_typ;
	private int  tdst_cd;
	private double tot_weight; 
    private double actual_wet;
    
    
    ///////////////////////Getter and Setter method of InvFstForm///////////////////////////////////////////
    
	public double getActual_wet() {
		return actual_wet;
	}
	public void setActual_wet(double actual_wet) {
		this.actual_wet = actual_wet;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public double getBill_amt() {
		return bill_amt;
	}
	public void setBill_amt(double bill_amt) {
		this.bill_amt = bill_amt;
	}
	public int getCases() {
		return cases;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public Date getChl_dt() {
		return chl_dt;
	}
	public void setChl_dt(Date chl_dt) {
		this.chl_dt = chl_dt;
	}
	public String getChl_no() {
		return chl_no;
	}
	public void setChl_no(String chl_no) {
		this.chl_no = chl_no;
	}
	public double getCtax1_amt() {
		return ctax1_amt;
	}
	public void setCtax1_amt(double ctax1_amt) {
		this.ctax1_amt = ctax1_amt;
	}
	public double getCtax1_per() {
		return ctax1_per;
	}
	public void setCtax1_per(double ctax1_per) {
		this.ctax1_per = ctax1_per;
	}
	public double getCtax2_amt() {
		return ctax2_amt;
	}
	public void setCtax2_amt(double ctax2_amt) {
		this.ctax2_amt = ctax2_amt;
	}
	public double getCtax2_per() {
		return ctax2_per;
	}
	public void setCtax2_per(double ctax2_per) {
		this.ctax2_per = ctax2_per;
	}
	public double getCtax3_amt() {
		return ctax3_amt;
	}
	public void setCtax3_amt(double ctax3_amt) {
		this.ctax3_amt = ctax3_amt;
	}
	public double getCtax3_per() {
		return ctax3_per;
	}
	public void setCtax3_per(double ctax3_per) {
		this.ctax3_per = ctax3_per;
	}
	public double getDcm_truck() {
		return dcm_truck;
	}
	public void setDcm_truck(double dcm_truck) {
		this.dcm_truck = dcm_truck;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(int doc_type) {
		this.doc_type = doc_type;
	}
	public String getDrug_lc1() {
		return drug_lc1;
	}
	public void setDrug_lc1(String drug_lc1) {
		this.drug_lc1 = drug_lc1;
	}
	public String getDrug_lc2() {
		return drug_lc2;
	}
	public void setDrug_lc2(String drug_lc2) {
		this.drug_lc2 = drug_lc2;
	}
	public int getDue_days() {
		return due_days;
	}
	public void setDue_days(int due_days) {
		this.due_days = due_days;
	}
	public Date getDue_dt() {
		return due_dt;
	}
	public void setDue_dt(Date due_dt) {
		this.due_dt = due_dt;
	}
	public Date getEntry_dt() {
		return entry_dt;
	}
	public void setEntry_dt(Date entry_dt) {
		this.entry_dt = entry_dt;
	}
	public double getFull_truck() {
		return full_truck;
	}
	public void setFull_truck(double full_truck) {
		this.full_truck = full_truck;
	}
	public double getGross_amt() {
		return gross_amt;
	}
	public void setGross_amt(double gross_amt) {
		this.gross_amt = gross_amt;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public String getInv_lo() {
		return inv_lo;
	}
	public void setInv_lo(String inv_lo) {
		this.inv_lo = inv_lo;
	}
	public int getInv_no() {
		return inv_no;
	}
	public void setInv_no(int inv_no) {
		this.inv_no = inv_no;
	}
	public String getInv_type() {
		return inv_type;
	}
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	public int getInv_yr() {
		return inv_yr;
	}
	public void setInv_yr(int inv_yr) {
		this.inv_yr = inv_yr;
	}
	public String getLoad_typ() {
		return load_typ;
	}
	public void setLoad_typ(String load_typ) {
		this.load_typ = load_typ;
	}
	public double getLsale1() {
		return lsale1;
	}
	public void setLsale1(double lsale1) {
		this.lsale1 = lsale1;
	}
	public double getLsale2() {
		return lsale2;
	}
	public void setLsale2(double lsale2) {
		this.lsale2 = lsale2;
	}
	public double getLsale3() {
		return lsale3;
	}
	public void setLsale3(double lsale3) {
		this.lsale3 = lsale3;
	}
	public double getLtax1_amt() {
		return ltax1_amt;
	}
	public void setLtax1_amt(double ltax1_amt) {
		this.ltax1_amt = ltax1_amt;
	}
	public double getLtax1_per() {
		return ltax1_per;
	}
	public void setLtax1_per(double ltax1_per) {
		this.ltax1_per = ltax1_per;
	}
	public double getLtax2_amt() {
		return ltax2_amt;
	}
	public void setLtax2_amt(double ltax2_amt) {
		this.ltax2_amt = ltax2_amt;
	}
	public double getLtax2_per() {
		return ltax2_per;
	}
	public void setLtax2_per(double ltax2_per) {
		this.ltax2_per = ltax2_per;
	}
	public double getLtax3_amt() {
		return ltax3_amt;
	}
	public void setLtax3_amt(double ltax3_amt) {
		this.ltax3_amt = ltax3_amt;
	}
	public double getLtax3_per() {
		return ltax3_per;
	}
	public void setLtax3_per(double ltax3_per) {
		this.ltax3_per = ltax3_per;
	}
	public double getMfg_amt() {
		return mfg_amt;
	}
	public void setMfg_amt(double mfg_amt) {
		this.mfg_amt = mfg_amt;
	}
	public double getMr_mfg() {
		return mr_mfg;
	}
	public void setMr_mfg(double mr_mfg) {
		this.mr_mfg = mr_mfg;
	}
	public Date getMtr_dt() {
		return mtr_dt;
	}
	public void setMtr_dt(Date mtr_dt) {
		this.mtr_dt = mtr_dt;
	}
	public String getMtr_no() {
		return mtr_no;
	}
	public void setMtr_no(String mtr_no) {
		this.mtr_no = mtr_no;
	}
	public double getOctroi() {
		return octroi;
	}
	public void setOctroi(double octroi) {
		this.octroi = octroi;
	}
	public String getParty_code() {
		return party_code;
	}
	public void setParty_code(String party_code) {
		this.party_code = party_code;
	}
	public Date getPermit_dt() {
		return permit_dt;
	}
	public void setPermit_dt(Date permit_dt) {
		this.permit_dt = permit_dt;
	}
	public String getPermit_no() {
		return permit_no;
	}
	public void setPermit_no(String permit_no) {
		this.permit_no = permit_no;
	}
	public Date getPinv_date() {
		return pinv_date;
	}
	public void setPinv_date(Date pinv_date) {
		this.pinv_date = pinv_date;
	}
	public String getPinv_no() {
		return pinv_no;
	}
	public void setPinv_no(String pinv_no) {
		this.pinv_no = pinv_no;
	}
	public double getRate_perkg() {
		return rate_perkg;
	}
	public void setRate_perkg(double rate_perkg) {
		this.rate_perkg = rate_perkg;
	}
	public int getStat_cd() {
		return stat_cd;
	}
	public void setStat_cd(int stat_cd) {
		this.stat_cd = stat_cd;
	}
	public double getTax_1() {
		return tax_1;
	}
	public void setTax_1(double tax_1) {
		this.tax_1 = tax_1;
	}
	public double getTax_2() { 
		return tax_2;
	}
	public void setTax_2(double tax_2) {
		this.tax_2 = tax_2;
	}
	public int getTdst_cd() {
		return tdst_cd;
	}
	public void setTdst_cd(int tdst_cd) {
		this.tdst_cd = tdst_cd;
	}
	public double getTot_weight() {
		return tot_weight;
	}
	public void setTot_weight(double tot_weight) {
		this.tot_weight = tot_weight;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public int getTrn_cd() {
		return trn_cd;
	}
	public void setTrn_cd(int trn_cd) {
		this.trn_cd = trn_cd;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	} 
  
  	
}
