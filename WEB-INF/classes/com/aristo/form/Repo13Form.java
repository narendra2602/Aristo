package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import com.aristo.valueobject.Repo13FormBean;

public class Repo13Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String typ;
	private String molecule;
	private int rl;
	private int rc;
	private String product;
	private String company;
	private int launch;
	private double vall;
	private double valc;
	private double msl;
	private double msc;
	private double gthl;
	private double gthc;
	private double valql;
	private double valq2;
	private double valq3;
	private double valq4;
	private double msq1;
	private double msq2;
	private double msq3;
	private double msq4;
	private double gthql;
	private double gthq2;
	private double gthq3;
	private double gthq4;
	private int code;
    private String name;  
	private int color;
	private int cyear;
    private int lyear;
	
	
	private int smon=1;
	private int emon=1;

	private int pcode;
	private String pname;
	private String pack;
	private double rate;
	private double sqty;
	private double sval;
	private double slqty;
	private double slval;
	private double pqty;
	private double ppval;
	private double netqty;
	private double netval;
	
	private String mcode;
	private String mname;
	
	private String codes[];
	private String codes1[];
	private String chosenItem = "";
	private List alist;
	private List rlist;
	private List xlist;
	private List blist;
	
	private List ylist;
	private int syear;
	private int eyear;
	private int yval;
	private String yname;
	private String nm2;
	private String nm3;

	private int mnth=1; 
	private int mnth1=1; 

	
	
	public int getMnth() {
		return mnth;
	}

	public void setMnth(int mnth) {
		this.mnth = mnth;
	}

	public int getMnth1() {
		return mnth1;
	}

	public void setMnth1(int mnth1) {
		this.mnth1 = mnth1;
	}

	public String getNm2() {
		return nm2;
	}

	public void setNm2(String nm2) {
		this.nm2 = nm2;
	}

	public String getNm3() {
		return nm3;
	}

	public void setNm3(String nm3) {
		this.nm3 = nm3;
	}

	public List getBlist() {
		return blist;
	}

	public void setBlist(List blist) {
		this.blist = blist;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getMolecule() {
		return molecule;
	}

	public void setMolecule(String molecule) {
		this.molecule = molecule;
	}

	public int getRl() {
		return rl;
	}

	public void setRl(int rl) {
		this.rl = rl;
	}

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getLaunch() {
		return launch;
	}

	public void setLaunch(int launch) {
		this.launch = launch;
	}

	public double getVall() {
		return vall;
	}

	public void setVall(double vall) {
		this.vall = vall;
	}

	public double getValc() {
		return valc;
	}

	public void setValc(double valc) {
		this.valc = valc;
	}

	public double getMsl() {
		return msl;
	}

	public void setMsl(double msl) {
		this.msl = msl;
	}

	public double getMsc() {
		return msc;
	}

	public void setMsc(double msc) {
		this.msc = msc;
	}

	public double getGthl() {
		return gthl;
	}

	public void setGthl(double gthl) {
		this.gthl = gthl;
	}

	public double getGthc() {
		return gthc;
	}

	public void setGthc(double gthc) {
		this.gthc = gthc;
	}

	public double getValql() {
		return valql;
	}

	public void setValql(double valql) {
		this.valql = valql;
	}

	public double getValq2() {
		return valq2;
	}

	public void setValq2(double valq2) {
		this.valq2 = valq2;
	}

	public double getValq3() {
		return valq3;
	}

	public void setValq3(double valq3) {
		this.valq3 = valq3;
	}

	public double getValq4() {
		return valq4;
	}

	public void setValq4(double valq4) {
		this.valq4 = valq4;
	}

	public double getMsq1() {
		return msq1;
	}

	public void setMsq1(double msq1) {
		this.msq1 = msq1;
	}

	public double getMsq2() {
		return msq2;
	}

	public void setMsq2(double msq2) {
		this.msq2 = msq2;
	}

	public double getMsq3() {
		return msq3;
	}

	public void setMsq3(double msq3) {
		this.msq3 = msq3;
	}

	public double getMsq4() {
		return msq4;
	}

	public void setMsq4(double msq4) {
		this.msq4 = msq4;
	}

	public double getGthql() {
		return gthql;
	}

	public void setGthql(double gthql) {
		this.gthql = gthql;
	}

	public double getGthq2() {
		return gthq2;
	}

	public void setGthq2(double gthq2) {
		this.gthq2 = gthq2;
	}

	public double getGthq3() {
		return gthq3;
	}

	public void setGthq3(double gthq3) {
		this.gthq3 = gthq3;
	}

	public double getGthq4() {
		return gthq4;
	}

	public void setGthq4(double gthq4) {
		this.gthq4 = gthq4;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getCyear() {
		return cyear;
	}

	public void setCyear(int cyear) {
		this.cyear = cyear;
	}

	public int getLyear() {
		return lyear;
	}

	public void setLyear(int lyear) {
		this.lyear = lyear;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
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

	public void move( List sourceList, String[] sourceValues, List<String> destList )
	{
		Iterator it = sourceList.iterator();
		Repo13FormBean r = new Repo13FormBean();
		while(it.hasNext())
		{
			
			      r = (Repo13FormBean) it.next();
 
			String x =  r.getMcode();
		    for(int i=0;i<sourceValues.length;i++)
		    {
		    	if(x.equals(sourceValues[i]))
		    	{	
		    		destList.add(new String(x));
		    	}		
		    }
		}
		
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	this.smon=1;
	this.emon=1;
	this.chosenItem = "";
	this.eyear=0;
	}

	public List getAlist() {
		return alist;
	}

	public void setAlist(List alist) {
		this.alist = alist;
	}

	public String getChosenItem() {
		return chosenItem;
	}

	public void setChosenItem(String chosenItem) {
		this.chosenItem = chosenItem;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String[] getCodes1() {
		return codes1;
	}

	public void setCodes1(String[] codes1) {
		this.codes1 = codes1;
	}

	public int getEmon() {
		return emon;
	}

	public void setEmon(int emon) {
		this.emon = emon;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public double getNetqty() {
		return netqty;
	}

	public void setNetqty(double netqty) {
		this.netqty = netqty;
	}

	public double getNetval() {
		return netval;
	}

	public void setNetval(double netval) {
		this.netval = netval;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPpval() {
		return ppval;
	}

	public void setPpval(double ppval) {
		this.ppval = ppval;
	}

	public double getPqty() {
		return pqty;
	}

	public void setPqty(double pqty) {
		this.pqty = pqty;
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

	public double getSlqty() {
		return slqty;
	}

	public void setSlqty(double slqty) {
		this.slqty = slqty;
	}

	public double getSlval() {
		return slval;
	}

	public void setSlval(double slval) {
		this.slval = slval;
	}

	public int getSmon() {
		return smon;
	}

	public void setSmon(int smon) {
		this.smon = smon;
	}

	public double getSqty() {
		return sqty;
	}

	public void setSqty(double sqty) {
		this.sqty = sqty;
	}

	public double getSval() {
		return sval;
	}

	public void setSval(double sval) {
		this.sval = sval;
	}

	public List getXlist() {
		return xlist;
	}

	public void setXlist(List xlist) {
		this.xlist = xlist;
	}

	
	
	
	
	
}
