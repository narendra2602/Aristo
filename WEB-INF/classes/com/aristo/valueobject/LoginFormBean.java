package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoginFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String login_name;
	private String password;
	private String c_password;
	private String type;
	private String f_name;
	private String l_name;
	private String desig;
	private String access_t;
	private String opt;
	private int opt1;
	private int code;
	private String old_pass;
	private String new_pass;
	private String last_ldate;
	private String last_ltime;
	private String d_type;
	private String branch_name;
	private int dcode;
	private String dname;
	private String title;
	private String department;
	private String comp_code;
	private String status;
	private String location;
	private String[] checkbox1=new String[25];
	private String[] checkbox2=new String[150];
	private String tab_name;
	private String tab_link;
	private String repo_name;
	private String repo_link;
	private String repo_image;
	private int tab_id;
	private String link1;
	private int tab_width;
	private String email;
	private String msg;
	private String search;
	
	
    private String yname;
    private int yval;
    private List ylist;
	
	// Daily Entery ke liye///////////// 
	
	private double trd_sale;
	private double inst_sale;
	private double gmsd_sale;
	private double pending_ord;
	private double sales_bud;
	private double collc;
	private double remit;
	private double outstnd;
	private Date edate;
	private Date bdate;
	private int per;
	private double def;
	private double lmsale;
	private double lmout;
	private double lmdiff;
	private String mnth;
	private int eyear;

	
	
	private double lysale;
	private double ach;
	private double surdef;
	private double collcumm;
	private double remitcumm;
	private double cn100;
	private List divlist;
	
	private double bud_per;
	private double sale_today;
	
	private List brList;
	private int mnth_code;
	private int div_code;
	
	
	
	
	 
	public String getRepo_image() {
		return repo_image;
	}
	public void setRepo_image(String repo_image) {
		this.repo_image = repo_image;
	}
	public int getDiv_code() {
		return div_code;
	}
	public void setDiv_code(int div_code) {
		this.div_code = div_code;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getMnth_code() {
		return mnth_code;
	}
	public void setMnth_code(int mnth_code) {
		this.mnth_code = mnth_code;
	}
	public List getBrList() {
		return brList;
	}
	public void setBrList(List brList) {
		this.brList = brList;
	}
	public double getBud_per() {
		return bud_per;
	}
	public void setBud_per(double bud_per) {
		this.bud_per = bud_per;
	}
	public double getSale_today() {
		return sale_today;
	}
	public void setSale_today(double sale_today) {
		this.sale_today = sale_today;
	}
	public List getDivlist() {
		return divlist;
	}
	public void setDivlist(List divlist) {
		this.divlist = divlist;
	}
	public double getLysale() {
		return lysale;
	}
	public void setLysale(double lysale) {
		this.lysale = lysale;
	}
	public double getAch() {
		return ach;
	}
	public void setAch(double ach) {
		this.ach = ach;
	}
	public double getSurdef() {
		return surdef;
	}
	public void setSurdef(double surdef) {
		this.surdef = surdef;
	}
	public double getCollcumm() {
		return collcumm;
	}
	public void setCollcumm(double collcumm) {
		this.collcumm = collcumm;
	}
	public double getRemitcumm() {
		return remitcumm;
	}
	public void setRemitcumm(double remitcumm) {
		this.remitcumm = remitcumm;
	}
	public double getCn100() {
		return cn100;
	}
	public void setCn100(double cn100) {
		this.cn100 = cn100;
	}
	public int getEyear() {
		return eyear;
	}
	public void setEyear(int eyear) {
		this.eyear = eyear;
	}
	public String getMnth() {
		return mnth;
	}
	public void setMnth(String mnth) {
		this.mnth = mnth;
	}
	public double getLmdiff() {
		return lmdiff;
	}
	public void setLmdiff(double lmdiff) {
		this.lmdiff = lmdiff;
	}
	public double getLmout() {
		return lmout;
	}
	public void setLmout(double lmout) {
		this.lmout = lmout;
	}
	public double getLmsale() {
		return lmsale;
	}
	public void setLmsale(double lmsale) {
		this.lmsale = lmsale;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public double getCollc() {
		return collc;
	}
	public void setCollc(double collc) {
		this.collc = collc;
	}
	public double getGmsd_sale() {
		return gmsd_sale;
	}
	public void setGmsd_sale(double gmsd_sale) {
		this.gmsd_sale = gmsd_sale;
	}
	public double getInst_sale() {
		return inst_sale;
	}
	public void setInst_sale(double inst_sale) {
		this.inst_sale = inst_sale;
	}
	public double getOutstnd() {
		return outstnd;
	}
	public void setOutstnd(double outstnd) {
		this.outstnd = outstnd;
	}
	public double getPending_ord() {
		return pending_ord;
	}
	public void setPending_ord(double pending_ord) {
		this.pending_ord = pending_ord;
	}
	public double getRemit() {
		return remit;
	}
	public void setRemit(double remit) {
		this.remit = remit;
	}
	public double getSales_bud() {
		return sales_bud;
	}
	public void setSales_bud(double sales_bud) {
		this.sales_bud = sales_bud;
	}
	public double getTrd_sale() {
		return trd_sale;
	}
	public void setTrd_sale(double trd_sale) {
		this.trd_sale = trd_sale;
	}
	public List getYlist() {
		return ylist;
	}
	public void setYlist(List ylist) {
		this.ylist = ylist;
	}
	public String getYname() {
		return yname;
	}
	public void setYname(String yname) {
		this.yname = yname;
	}
	public int getYval() {
		return yval;
	}
	public void setYval(int yval) {
		this.yval = yval;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTab_width() {
		return tab_width;
	}
	public void setTab_width(int tab_width) {
		this.tab_width = tab_width;
	}
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public int getTab_id() {
		return tab_id;
	}
	public void setTab_id(int tab_id) {
		this.tab_id = tab_id;
	}
	public String getRepo_link() {
		return repo_link;
	}
	public void setRepo_link(String repo_link) {
		this.repo_link = repo_link;
	}
	public String getRepo_name() {
		return repo_name;
	}
	public void setRepo_name(String repo_name) {
		this.repo_name = repo_name;
	}
	public String getTab_link() {
		return tab_link;
	}
	public void setTab_link(String tab_link) {
		this.tab_link = tab_link;
	}
	public String getTab_name() {
		return tab_name;
	}
	public void setTab_name(String tab_name) {
		this.tab_name = tab_name;
	}
	public String[] getCheckbox2() {
		return checkbox2;
	}
	public void setCheckbox2(String[] checkbox2) {
		this.checkbox2 = checkbox2;
	}
	public String[] getCheckbox1() {
		return checkbox1;
	}
	public void setCheckbox1(String[] checkbox1) {
		this.checkbox1 = checkbox1;
	}
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDcode() {
		return dcode;
	}
	public void setDcode(int dcode) {
		this.dcode = dcode;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public String getLast_ldate() {
		return last_ldate;
	}
	public void setLast_ldate(String last_ldate) {
		this.last_ldate = last_ldate;
	}
	public String getLast_ltime() {
		return last_ltime;
	}
	public void setLast_ltime(String last_ltime) {
		this.last_ltime = last_ltime;
	}
	public String getAccess_t() {
		return access_t;
	}
	public void setAccess_t(String access_t) {
		this.access_t = access_t;
	}
	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getNew_pass() {
		return new_pass;
	}
	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}
	public String getOld_pass() {
		return old_pass;
	}
	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOpt1() {
		return opt1;
	}
	public void setOpt1(int opt1) {
		this.opt1 = opt1;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public double getDef() {
		return def;
	}
	public void setDef(double def) {
		this.def = def;
	}
	public int getPer() {
		return per;
	}
	public void setPer(int per) {
		this.per = per;
	}

	
	
	
	
}
