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
	
	private List ylist;
	private int syear;
	private int eyear;
	private int yval;
	private String yname;

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
