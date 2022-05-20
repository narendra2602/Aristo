package com.aristo.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProductForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int depo_code;
	private int cmp_cd;
	private int pcode;
	private String pd_type;
	private int type;
	private int group;
	private int pd_group;
	private String pname;
	private String pack;
	private float tax_cd1;
	private float tax_cd2;
	private float tax_cd3;
	private float disc_cd1;
	private float disc_cd2;
	private float excise;
	private float deal_rate;
	private float net_rt1;
	private float net_rt2;
	private float net_rt3;
	private float trd_rt1;
	private float trd_rt2;
	private float trd_rt3;
	private float mrp_rt1;
	private float mrp_rt2;
	private float mrp_rt3;
	private float exc_rate;
	private float hos_rate;
	private float spl_rate;
	private float comp_rate;
	private float mr_rate;
	private int prd_mopng;
	private int prd_popng;
	private int prd_yopng;
	private int schm_onqty;
	private int schm_qty;
	private String batch_no;
	private String exp_dt;
	private int  case1;
	private int box;
	private String box_type;
	private int dum_code;
	private int sale_type;
	private String octroi;
	private String tax_type1;
	private String tax_type2;
	private String tax_type3;
	private int open_man;
	private int open_oth;
	private int mgrp;
	private int mcode;
	private String mname;
	private float sc_tax;
	private String np;
	private String slct;
	private List plist;
	private int pval;
	private String link1;
	private int id;
	private String tn;
	private List glist;
	private List gplist;
	private String gname;
	private String gpname;
	private int gcode;
	private int gpcode;
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGpname() {
		return gpname;
	}
	public void setGpname(String gpname) {
		this.gpname = gpname;
	}
	public List getGlist() {
		return glist;
	}
	public void setGlist(List glist) {
		this.glist = glist;
	}
	public List getGplist() {
		return gplist;
	}
	public void setGplist(List gplist) {
		this.gplist = gplist;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.pcode=0;
	this.pname=null;
	this.pack=null;
	this.pd_group=0;
	this.mcode=0;
	this.mname=null;
	this.mgrp=0;
	this.tn="T";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public int getPval() {
		return pval;
	}
	public void setPval(int pval) {
		this.pval = pval;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
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
	public float getComp_rate() {
		return comp_rate;
	}
	public void setComp_rate(float comp_rate) {
		this.comp_rate = comp_rate;
	}
	public float getDeal_rate() {
		return deal_rate;
	}
	public void setDeal_rate(float deal_rate) {
		this.deal_rate = deal_rate;
	}
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public float getDisc_cd1() {
		return disc_cd1;
	}
	public void setDisc_cd1(float disc_cd1) {
		this.disc_cd1 = disc_cd1;
	}
	public float getDisc_cd2() {
		return disc_cd2;
	}
	public void setDisc_cd2(float disc_cd2) {
		this.disc_cd2 = disc_cd2;
	}
	public int getDum_code() {
		return dum_code;
	}
	public void setDum_code(int dum_code) {
		this.dum_code = dum_code;
	}
	public float getExc_rate() {
		return exc_rate;
	}
	public void setExc_rate(float exc_rate) {
		this.exc_rate = exc_rate;
	}
	public float getExcise() {
		return excise;
	}
	public void setExcise(float excise) {
		this.excise = excise;
	}
	public String getExp_dt() {
		return exp_dt;
	}
	public void setExp_dt(String exp_dt) {
		this.exp_dt = exp_dt;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public float getHos_rate() {
		return hos_rate;
	}
	public void setHos_rate(float hos_rate) {
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
	public float getMr_rate() {
		return mr_rate;
	}
	public void setMr_rate(float mr_rate) {
		this.mr_rate = mr_rate;
	}
	public float getMrp_rt1() {
		return mrp_rt1;
	}
	public void setMrp_rt1(float mrp_rt1) {
		this.mrp_rt1 = mrp_rt1;
	}
	public float getMrp_rt2() {
		return mrp_rt2;
	}
	public void setMrp_rt2(float mrp_rt2) {
		this.mrp_rt2 = mrp_rt2;
	}
	public float getMrp_rt3() {
		return mrp_rt3;
	}
	public void setMrp_rt3(float mrp_rt3) {
		this.mrp_rt3 = mrp_rt3;
	}
	public float getNet_rt1() {
		return net_rt1;
	}
	public void setNet_rt1(float net_rt1) {
		this.net_rt1 = net_rt1;
	}
	public float getNet_rt2() {
		return net_rt2;
	}
	public void setNet_rt2(float net_rt2) {
		this.net_rt2 = net_rt2;
	}
	public float getNet_rt3() {
		return net_rt3;
	}
	public void setNet_rt3(float net_rt3) {
		this.net_rt3 = net_rt3;
	}
	public String getNp() {
		return np;
	}
	public void setNp(String np) {
		this.np = np;
	}
	public String getOctroi() {
		return octroi;
	}
	public void setOctroi(String octroi) {
		this.octroi = octroi;
	}
	public int getOpen_man() {
		return open_man;
	}
	public void setOpen_man(int open_man) {
		this.open_man = open_man;
	}
	public int getOpen_oth() {
		return open_oth;
	}
	public void setOpen_oth(int open_oth) {
		this.open_oth = open_oth;
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
	public String getPd_type() {
		return pd_type;
	}
	public void setPd_type(String pd_type) {
		this.pd_type = pd_type;
	}
	public List getPlist() {
		return plist;
	}
	public void setPlist(List plist) {
		this.plist = plist;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrd_mopng() {
		return prd_mopng;
	}
	public void setPrd_mopng(int prd_mopng) {
		this.prd_mopng = prd_mopng;
	}
	public int getPrd_popng() {
		return prd_popng;
	}
	public void setPrd_popng(int prd_popng) {
		this.prd_popng = prd_popng;
	}
	public int getPrd_yopng() {
		return prd_yopng;
	}
	public void setPrd_yopng(int prd_yopng) {
		this.prd_yopng = prd_yopng;
	}
	public int getSale_type() {
		return sale_type;
	}
	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}
	public float getSc_tax() {
		return sc_tax;
	}
	public void setSc_tax(float sc_tax) {
		this.sc_tax = sc_tax;
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
	public float getSpl_rate() {
		return spl_rate;
	}
	public void setSpl_rate(float spl_rate) {
		this.spl_rate = spl_rate;
	}
	public float getTax_cd1() {
		return tax_cd1;
	}
	public void setTax_cd1(float tax_cd1) {
		this.tax_cd1 = tax_cd1;
	}
	public float getTax_cd2() {
		return tax_cd2;
	}
	public void setTax_cd2(float tax_cd2) {
		this.tax_cd2 = tax_cd2;
	}
	public float getTax_cd3() {
		return tax_cd3;
	}
	public void setTax_cd3(float tax_cd3) {
		this.tax_cd3 = tax_cd3;
	}
	public String getTax_type1() {
		return tax_type1;
	}
	public void setTax_type1(String tax_type1) {
		this.tax_type1 = tax_type1;
	}
	public String getTax_type2() {
		return tax_type2;
	}
	public void setTax_type2(String tax_type2) {
		this.tax_type2 = tax_type2;
	}
	public String getTax_type3() {
		return tax_type3;
	}
	public void setTax_type3(String tax_type3) {
		this.tax_type3 = tax_type3;
	}
	public float getTrd_rt1() {
		return trd_rt1;
	}
	public void setTrd_rt1(float trd_rt1) {
		this.trd_rt1 = trd_rt1;
	}
	public float getTrd_rt2() {
		return trd_rt2;
	}
	public void setTrd_rt2(float trd_rt2) {
		this.trd_rt2 = trd_rt2;
	}
	public float getTrd_rt3() {
		return trd_rt3;
	}
	public void setTrd_rt3(float trd_rt3) {
		this.trd_rt3 = trd_rt3;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGcode() {
		return gcode;
	}
	public void setGcode(int gcode) {
		this.gcode = gcode;
	}
	public int getGpcode() {
		return gpcode;
	}
	public void setGpcode(int gpcode) {
		this.gpcode = gpcode;
	}
	

}
