package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aristo.valueobject.Repo18FormBean;

public class Repo18Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smon=1;
	private int emon=1;
	private int uv=1;
	private int rep_type=1;
	private String pname;
	private double qty1[];
	private double val1[];
	private int mcode;
	private String mname;
	private String codes[];
	private String codes1[];
	private String chosenItem = "";
	private List alist;
	private List rlist;
	private List xlist;
	
	private List ylist;
	private int eyear;
	private int yval;
	private String yname; 	

	
	public int getEyear() {
		return eyear;
	}


	public void setEyear(int eyear) {
		this.eyear = eyear;
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


	public Repo18Form() 
    {
    	qty1 = new double[15];
    	val1 = new double[15];
    }
	
	
	public void move( List sourceList, String[] sourceValues, List<Integer> destList )
	{
		Iterator it = sourceList.iterator();
	    Repo18FormBean r = new Repo18FormBean();
		while(it.hasNext())
		{
			
			      r = (Repo18FormBean) it.next();
 
			int x =  r.getMcode();
		    for(int i=0;i<sourceValues.length;i++)
		    {
		    	if(x== Integer.parseInt((sourceValues[i])))
		    	{	
		    		destList.add(new Integer(x));
		    	}		
		    }
		}
			 
		
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	this.smon=1;
	this.emon=1;
	this.uv=1;
	this.chosenItem = "";
	this.rep_type=1;
	this.eyear=0;
	}

	 public double getQty1(int index) { 
	        return qty1[index]; 
	    }
	    
	    public void setQty1(int index, double value) { 
	       qty1[index] = value; 
	    }	
	
	 public double getVal1(int index) { 
	        return val1[index]; 
	    }
		    
     public void setVal1(int index, double value) { 
	       val1[index] = value; 
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

	public int getMcode() {
		return mcode;
	}

	public void setMcode(int mcode) {
		this.mcode = mcode;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public int getSmon() {
		return smon;
	}

	public void setSmon(int smon) {
		this.smon = smon;
	}

	public List getXlist() {
		return xlist;
	}

	public void setXlist(List xlist) {
		this.xlist = xlist;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}


	public int getRep_type() {
		return rep_type;
	}


	public void setRep_type(int rep_type) {
		this.rep_type = rep_type;
	}

	
}
