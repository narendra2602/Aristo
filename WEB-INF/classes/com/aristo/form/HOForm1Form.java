package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.aristo.valueobject.HOForm1FormBean;

public class HOForm1Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emon=1;
	private String name; 
	private double mtarget;
	private double msale;
	private double mach;
	private double cumtarget;
	private double cumsale;
	private double lyear;
	private double cumach;
	private double cumgth;
	private double pmr;
	
	private int codes[];
	private int codes1[];

	private List xlist;
	private List rlist;
	private List alist;
	private String chosenItem = "";
	private int syear;
    private int eyear;
    private int opt=1; 

    private String yname;
    private int yval;
    private List ylist;

	
	
	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
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

	public void move( List sourceList, String[] sourceValues, List<Integer> destList )
	{
		Iterator it = sourceList.iterator();
		HOForm1FormBean r = new HOForm1FormBean();
		while(it.hasNext())
		{
			
			      r = (HOForm1FormBean) it.next();
 
			int x =  r.getPcode();
		    for(int i=0;i<sourceValues.length;i++)
		    {
		    	if(x== Integer.parseInt((sourceValues[i])))
		    	{	
		    		destList.add(new Integer(x));
		    	}		
		    }
		}
			 
		
	}
	
	public String getChosenItem() {
		return chosenItem;
	}

	public void setChosenItem(String chosenItem) {
		this.chosenItem = chosenItem;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    
    	this.emon=1;
    	this.chosenItem = "";
    	this.eyear=0;
    	this.opt=1;

    }	
	
	public int[] getCodes() {
		return codes;
	}

	public void setCodes(int[] codes) {
		this.codes = codes;
	}

	public int[] getCodes1() {
		return codes1;
	}

	public void setCodes1(int[] codes1) {
		this.codes1 = codes1;
	}

	public List getAlist() {
		return alist;
	}

	public void setAlist(List alist) {
		this.alist = alist;
	}

	public double getCumach() {
		return cumach;
	}

	public void setCumach(double cumach) {
		this.cumach = cumach;
	}

	public double getCumgth() {
		return cumgth;
	}

	public void setCumgth(double cumgth) {
		this.cumgth = cumgth;
	}

	public double getCumsale() {
		return cumsale;
	}

	public void setCumsale(double cumsale) {
		this.cumsale = cumsale;
	}

	public double getCumtarget() {
		return cumtarget;
	}

	public void setCumtarget(double cumtarget) {
		this.cumtarget = cumtarget;
	}

	public int getEmon() {
		return emon;
	}

	public void setEmon(int emon) {
		this.emon = emon;
	}

	public double getLyear() {
		return lyear;
	}

	public void setLyear(double lyear) {
		this.lyear = lyear;
	}

	public double getMach() {
		return mach;
	}

	public void setMach(double mach) {
		this.mach = mach;
	}

	public double getMsale() {
		return msale;
	}

	public void setMsale(double msale) {
		this.msale = msale;
	}

	public double getMtarget() {
		return mtarget;
	}

	public void setMtarget(double mtarget) {
		this.mtarget = mtarget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPmr() {
		return pmr;
	}

	public void setPmr(double pmr) {
		this.pmr = pmr;
	}

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public List getXlist() {
		return xlist;
	}

	public void setXlist(List xlist) {
		this.xlist = xlist;
	}	
	
	
	
	
}
