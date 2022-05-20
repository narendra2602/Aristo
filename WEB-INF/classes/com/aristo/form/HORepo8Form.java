package com.aristo.form;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aristo.valueobject.HORepo8FormBean;

public class HORepo8Form extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uv=1;
	private int smon=1;
	private int emon=1;
	
	private int r_type=1;
	
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
    private int color;

	
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


	public HORepo8Form() 
    {
    	qty1 = new double[15];
    	val1 = new double[15];
    }

	
	public void move( List sourceList, String[] sourceValues, List<Integer> destList )
	{
		Iterator it = sourceList.iterator();
		HORepo8FormBean r = new HORepo8FormBean();
		while(it.hasNext())
		{
			
			      r = (HORepo8FormBean) it.next();
 
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

public List getRlist() {
	return rlist;
}

public void setRlist(List rlist) {
	this.rlist = rlist;
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

	public void reset(ActionMapping mapping, HttpServletRequest req) { 

    	this.uv=1;
    	this.emon=1;
    	this.chosenItem="";
    	this.r_type=1;
    	this.eyear=0;
    }


	public int getR_type() {
		return r_type;
	}


	public void setR_type(int r_type) {
		this.r_type = r_type;
	}


	public int getSmon() {
		return smon;
	}


	public void setSmon(int smon) {
		this.smon = smon;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}
	
	
	
}
