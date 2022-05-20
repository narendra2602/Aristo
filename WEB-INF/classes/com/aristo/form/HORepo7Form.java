package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.aristo.valueobject.HORepo7FormBean;

public class HORepo7Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int r_type=1;
	private String name; 
	private double sqty;
	private double sval;
	private double slqty;
	private double slval;
	private double expqty;
	private double expval;
	private double netqty;
	private double netval;
	private int codes[];
	private int codes1[];

	private double val1[];
	private List xlist;
	private List rlist;
	private List alist;
	private String chosenItem = "";
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;
    private int repoptn=1;
	
    
    
    
	public double[] getVal1() {
		return val1;
	}


	public void setVal1(double[] val1) {
		this.val1 = val1;
	}


	public int getRepoptn() {
		return repoptn;
	}


	public void setRepoptn(int repoptn) {
		this.repoptn = repoptn;
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
		HORepo7FormBean r = new HORepo7FormBean();
		while(it.hasNext())
		{
			
			      r = (HORepo7FormBean) it.next();
 
			int x =  r.getGcode();
		    for(int i=0;i<sourceValues.length;i++)
		    {
		    	if(x== Integer.parseInt((sourceValues[i])))
		    	{	
		    		destList.add(new Integer(x));
		    	}		
		    }
		}
			 
		
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


	public int getEmon() {
		return emon;
	}


	public void setEmon(int emon) {
		this.emon = emon;
	}


	public double getExpqty() {
		return expqty;
	}


	public void setExpqty(double expqty) {
		this.expqty = expqty;
	}


	public double getExpval() {
		return expval;
	}


	public void setExpval(double expval) {
		this.expval = expval;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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

	
	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    	this.smon=1;
    	this.emon=1;
    	this.r_type=1;
    	this.repoptn=1;
    	this.chosenItem="";
    	this.eyear=0;
    	
    }


	public int getR_type() {
		return r_type;
	}


	public void setR_type(int r_type) {
		this.r_type = r_type;
	}
	
}
