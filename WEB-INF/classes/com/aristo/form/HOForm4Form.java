package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aristo.valueobject.HOForm4FormBean;

public class HOForm4Form extends ActionForm {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uv;
	private int st;
	private int emon;
	private int pcode;
	private String pname;
	private double pmr_u;
	private double pmr_v;

	private int codes[];
	private int codes1[];
	private double val1[];
	private double qty1[];
	private String name;
	
	private List xlist;
	private List rlist;
	private List alist;
	private String chosenItem = "";
	private int no_of_mr;
	
	private int syear;
    private int eyear;
    private String yname;
    private int yval;
    private List ylist;
    private int all;
    
    

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
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

	public HOForm4Form() 
    {
    	qty1 = new double[15];
    	val1 = new double[15];
    }

	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    	this.uv=1;
    	this.emon=1;
    	this.st=1;
    	this.chosenItem="";    	
    }

	
	public void move( List sourceList, String[] sourceValues, List<Integer> destList )
	{
		Iterator it = sourceList.iterator();
		HOForm4FormBean r = new HOForm4FormBean();
		while(it.hasNext())
		{
			
			      r = (HOForm4FormBean) it.next();
 
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getNo_of_mr() {
	return no_of_mr;
}

public void setNo_of_mr(int no_of_mr) {
	this.no_of_mr = no_of_mr;
}

public int getPcode() {
	return pcode;
}

public void setPcode(int pcode) {
	this.pcode = pcode;
}

public double getPmr_u() {
	return pmr_u;
}

public void setPmr_u(double pmr_u) {
	this.pmr_u = pmr_u;
}

public double getPmr_v() {
	return pmr_v;
}

public void setPmr_v(double pmr_v) {
	this.pmr_v = pmr_v;
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

public int getSt() {
	return st;
}

public void setSt(int st) {
	this.st = st;
}

public int getUv() {
	return uv;
}

public void setUv(int uv) {
	this.uv = uv;
}

public List getXlist() {
	return xlist;
}

public void setXlist(List xlist) {
	this.xlist = xlist;
}	
  
	
	
}
