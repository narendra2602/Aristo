package com.aristo.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SampleRepo2Form extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String sdate;
	private String edate;
	private String pcode;
	private String name;
	private String city;
	private String pack;
	private String bat_no;
	private String exp_dt;
	private String inv_no;
	private Date inv_dt;
	private int rqty;
	private Date inv_idt;	
	private int sqty;
	private int fqty;	
	private double rate;
	private double tvalue;
	private List rlist;
	
	private List plist;
	private List divlist;
	private List catlist;
	private List grplist;
	private String pname;
	private int pval;
	private int code;
	private int choice;
	private int monno;
	private String divname;
	private int div_code;
	private String dcode;
	private String catname;
	private String cat_code;
	private String ccode;
	private String gpname;
	private int gpcode;
	private int gcode;
	private int myear;
	private List ylist;
	private String yname;
	private int yval;
	private int oqty;
	private int iqty;
	private int tqty;
	private int cqty;
	private int depo_code;
	private String brname;
	private int damanqty;
	private int bhopalqty;
	private int baddiqty;
	private int otherqty;
	private String d_type;
	private String dname;
	private List branchlist;  
	 
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
	this.sdate="";
	this.edate="";
	this.choice=1;
	}
	
	
	 
	
	public List getBranchlist() {
		return branchlist;
	}




	public void setBranchlist(List branchlist) {
		this.branchlist = branchlist;
	}




	public String getD_type() {
		return d_type;
	}




	public void setD_type(String d_type) {
		this.d_type = d_type;
	}




	public String getDname() {
		return dname;
	}




	public void setDname(String dname) {
		this.dname = dname;
	}




	public int getDamanqty() {
		return damanqty;
	}




	public void setDamanqty(int damanqty) {
		this.damanqty = damanqty;
	}




	public int getBhopalqty() {
		return bhopalqty;
	}




	public void setBhopalqty(int bhopalqty) {
		this.bhopalqty = bhopalqty;
	}




	public int getBaddiqty() {
		return baddiqty;
	}




	public void setBaddiqty(int baddiqty) {
		this.baddiqty = baddiqty;
	}




	public int getOtherqty() {
		return otherqty;
	}




	public void setOtherqty(int otherqty) {
		this.otherqty = otherqty;
	}




	public String getBrname() {
		return brname;
	}




	public void setBrname(String brname) {
		this.brname = brname;
	}




	public int getDepo_code() {
		return depo_code;
	}




	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}




	public int getOqty() {
		return oqty;
	}




	public void setOqty(int oqty) {
		this.oqty = oqty;
	}




	public int getIqty() {
		return iqty;
	}




	public void setIqty(int iqty) {
		this.iqty = iqty;
	}




	public int getTqty() {
		return tqty;
	}




	public void setTqty(int tqty) {
		this.tqty = tqty;
	}




	public int getCqty() {
		return cqty;
	}




	public void setCqty(int cqty) {
		this.cqty = cqty;
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




	public int getMyear() {
		return myear;
	}




	public void setMyear(int myear) {
		this.myear = myear;
	}



	public String getDcode() {
		return dcode;
	}



	public void setDcode(String dcode) {
		this.dcode = dcode;
	}


	public String getCcode() {
		return ccode;
	}




	public void setCcode(String ccode) {
		this.ccode = ccode;
	}




	public int getGcode() {
		return gcode;
	}




	public void setGcode(int gcode) {
		this.gcode = gcode;
	}




	public String getDivname() {
		return divname;
	}




	public void setDivname(String divname) {
		this.divname = divname;
	}




	public int getDiv_code() {
		return div_code;
	}




	public void setDiv_code(int div_code) {
		this.div_code = div_code;
	}




	public String getCatname() {
		return catname;
	}




	public void setCatname(String catname) {
		this.catname = catname;
	}



	public String getCat_code() {
		return cat_code;
	}




	public void setCat_code(String cat_code) {
		this.cat_code = cat_code;
	}



 



	public String getGpname() {
		return gpname;
	}




	public void setGpname(String gpname) {
		this.gpname = gpname;
	}




	public int getGpcode() {
		return gpcode;
	}




	public void setGpcode(int gpcode) {
		this.gpcode = gpcode;
	}




	public int getMonno() {
		return monno;
	}




	public void setMonno(int monno) {
		this.monno = monno;
	}




	public List getDivlist() {
		return divlist;
	}



	public void setDivlist(List divlist) {
		this.divlist = divlist;
	}



	public List getCatlist() {
		return catlist;
	}



	public void setCatlist(List catlist) {
		this.catlist = catlist;
	}



	public List getGrplist() {
		return grplist;
	}



	public void setGrplist(List grplist) {
		this.grplist = grplist;
	}



	public String getBat_no() {
		return bat_no;
	}
	public void setBat_no(String bat_no) {
		this.bat_no = bat_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getExp_dt() {
		return exp_dt;
	}

	public void setExp_dt(String exp_dt) {
		this.exp_dt = exp_dt;
	}

	public String getInv_no() {
		return inv_no;
	}
	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	public int getRqty() {
		return rqty;
	}
	public void setRqty(int rqty) {
		this.rqty = rqty;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getSqty() {
		return sqty;
	}
	public void setSqty(int sqty) {
		this.sqty = sqty;
	}
	public double getTvalue() {
		return tvalue;
	}
	public void setTvalue(double tvalue) {
		this.tvalue = tvalue;
	}

	public Date getInv_dt() {
		return inv_dt;
	}

	public void setInv_dt(Date inv_dt) {
		this.inv_dt = inv_dt;
	}

	public Date getInv_idt() {
		return inv_idt;
	}

	public void setInv_idt(Date inv_idt) {
		this.inv_idt = inv_idt;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public int getPval() {
		return pval;
	}

	public void setPval(int pval) {
		this.pval = pval;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getFqty() {
		return fqty;
	}

	public void setFqty(int fqty) {
		this.fqty = fqty;
	}
	
	
}
