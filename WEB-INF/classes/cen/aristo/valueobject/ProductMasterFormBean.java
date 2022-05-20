package cen.aristo.valueobject;

import java.io.Serializable;

public class ProductMasterFormBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int cmp_cd;
	private int pcode;
	private int pd_tpye;
	private int type;
	private int group;
	private int pd_group;
	private String pname;
	private String pack;
	private double tax_cd1;
	private double tax_cd2;
	private double tax_cd3;
	private double disc_cd1;
	private double disc_cd2;
	private double excise;
	private double  deal_rate;
	private double net_rt1;
	private double net_rt2;
	private double net_rt3;
	private double trd_rt1;
	private double trd_rt2;
	private double trd_rt3;
	private double mpr_rt1;
 	private double mpr_rt2;
	private double mpr_rt3; 
	private double exc_rate;  
	private double hos_rate;
	private double spl_rate;
	private double comp_rate;
	private double mr_rate;
	private int schm_onqty;
	private int schm_qty;
	private int case1;
	private int box;
	private String box_type;
	private double weight_box; 
	private int sale_type; 
	private int mgrp;
	private int mcode;
	private String mname;
	private String  slct;
	private int unit;
	
    /////////////////////Gettter and Setter of FormBean/////////////////////////////
	
	
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public String getBox_type() {
		return box_type;
	}
	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}
	public int getCase1() {
		return case1;
	}
	public void setCase1(int case1) {
		this.case1 = case1;
	}
	public int getCmp_cd() {
		return cmp_cd;
	}
	public void setCmp_cd(int cmp_cd) {
		this.cmp_cd = cmp_cd;
	}
	public double getComp_rate() {
		return comp_rate;
	}
	public void setComp_rate(double comp_rate) {
		this.comp_rate = comp_rate;
	}
	public double getDeal_rate() {
		return deal_rate;
	}
	public void setDeal_rate(double deal_rate) {
		this.deal_rate = deal_rate;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public double getDisc_cd1() {
		return disc_cd1;
	}
	public void setDisc_cd1(double disc_cd1) {
		this.disc_cd1 = disc_cd1;
	}
	public double getDisc_cd2() {
		return disc_cd2;
	}
	public void setDisc_cd2(double disc_cd2) {
		this.disc_cd2 = disc_cd2;
	}
	public double getExc_rate() {
		return exc_rate;
	}
	public void setExc_rate(double exc_rate) {
		this.exc_rate = exc_rate;
	}
	public double getExcise() {
		return excise;
	}
	public void setExcise(double excise) {
		this.excise = excise;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public double getHos_rate() {
		return hos_rate;
	}
	public void setHos_rate(double hos_rate) {
		this.hos_rate = hos_rate;
	}
	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public int getMgrp() {
		return mgrp;
	}
	public void setMgrp(int mgrp) {
		this.mgrp = mgrp;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public double getMpr_rt1() {
		return mpr_rt1;
	}
	public void setMpr_rt1(double mpr_rt1) {
		this.mpr_rt1 = mpr_rt1;
	}
	public double getMpr_rt2() {
		return mpr_rt2;
	}
	public void setMpr_rt2(double mpr_rt2) {
		this.mpr_rt2 = mpr_rt2;
	}
	public double getMpr_rt3() {
		return mpr_rt3;
	}
	public void setMpr_rt3(double mpr_rt3) {
		this.mpr_rt3 = mpr_rt3;
	}
	public double getMr_rate() {
		return mr_rate;
	}
	public void setMr_rate(double mr_rate) {
		this.mr_rate = mr_rate;
	}
	public double getNet_rt1() {
		return net_rt1;
	}
	public void setNet_rt1(double net_rt1) {
		this.net_rt1 = net_rt1;
	}
	public double getNet_rt2() {
		return net_rt2;
	}
	public void setNet_rt2(double net_rt2) {
		this.net_rt2 = net_rt2;
	}
	public double getNet_rt3() {
		return net_rt3;
	}
	public void setNet_rt3(double net_rt3) {
		this.net_rt3 = net_rt3;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public int getPd_group() {
		return pd_group;
	}
	public void setPd_group(int pd_group) {
		this.pd_group = pd_group;
	}
	public int getPd_tpye() {
		return pd_tpye;
	}
	public void setPd_tpye(int pd_tpye) {
		this.pd_tpye = pd_tpye;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSale_type() {
		return sale_type;
	}
	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}
	public int getSchm_onqty() {
		return schm_onqty;
	}
	public void setSchm_onqty(int schm_onqty) {
		this.schm_onqty = schm_onqty;
	}
	public int getSchm_qty() {
		return schm_qty;
	}
	public void setSchm_qty(int schm_qty) {
		this.schm_qty = schm_qty;
	}
	public String getSlct() {
		return slct;
	}
	public void setSlct(String slct) {
		this.slct = slct;
	}
	public double getSpl_rate() {
		return spl_rate;
	}
	public void setSpl_rate(double spl_rate) {
		this.spl_rate = spl_rate;
	}
	public double getTax_cd1() {
		return tax_cd1;
	}
	public void setTax_cd1(double tax_cd1) {
		this.tax_cd1 = tax_cd1;
	}
	public double getTax_cd2() {
		return tax_cd2;
	}
	public void setTax_cd2(double tax_cd2) {
		this.tax_cd2 = tax_cd2;
	}
	public double getTax_cd3() {
		return tax_cd3;
	}
	public void setTax_cd3(double tax_cd3) {
		this.tax_cd3 = tax_cd3;
	}
	public double getTrd_rt1() {
		return trd_rt1;
	}
	public void setTrd_rt1(double trd_rt1) {
		this.trd_rt1 = trd_rt1;
	}
	public double getTrd_rt2() {
		return trd_rt2;
	}
	public void setTrd_rt2(double trd_rt2) {
		this.trd_rt2 = trd_rt2;
	}
	public double getTrd_rt3() {
		return trd_rt3;
	}
	public void setTrd_rt3(double trd_rt3) {
		this.trd_rt3 = trd_rt3;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public double getWeight_box() {
		return weight_box;
	}
	public void setWeight_box(double weight_box) {
		this.weight_box = weight_box;
	}
	
	  
}
