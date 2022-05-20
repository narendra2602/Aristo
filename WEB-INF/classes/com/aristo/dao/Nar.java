package com.aristo.dao;

 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.HQDetailFormBean; 

public class Nar {
 
	public static void main(String[] args) throws Exception  {
		HQDetailFormBean hq = new HQDetailFormBean();
		List<HQDetailFormBean> hqlist = new ArrayList<HQDetailFormBean>();
		double k = 0.0f;
		k= (float) 524.10;
		System.out.println(k);
		String s = "21|8715|103700|A|73|0|4|5|0|87|1037|DEC30|524.10|0|0.00";
		
		   String[] data = s.split( "\\|" );
		   
		   	    for(int i=0;i<data.length;i++)
			    {
		   	    	if (i==0){
		   	    		   hq.setDepo_code(Integer.parseInt(data[i]));
		   	    	   }else if (i==1){
		   	    		   hq.setPr_code(Integer.parseInt(data[i]));
		   	    	   }else if (i==2){
		   	    		   hq.setMpr_code(Integer.parseInt(data[i]));
		   	    	   }else if (i==3){
		   	    		   hq.setPr_type(data[i]);
		   	    	   }else if (i==4){
		   	    		   hq.setTr_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==5){
		   	    		   hq.setSt_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==6){
		   	    		   hq.setAr_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==7){
		   	    		   hq.setRg_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==8){
		   	    		   hq.setMr_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==9){
		   	    		   hq.setGrp_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==10){
		   	    		   hq.setMgrp_cd(Integer.parseInt(data[i]));
		   	    	   }else if (i==11){
		   	    		      if (data[i].startsWith("OCT"))
		   	    		    	  hq.setQtyoct(Integer.parseInt(data[i].substring(3)));
		   	    		      if (data[i].startsWith("NOV"))
		   	    		    	  hq.setQtynov(Integer.parseInt(data[i].substring(3)));
		   	    		      if (data[i].startsWith("DEC"))
		   	    		    	  hq.setQtydec(Integer.parseInt(data[i].substring(3)));
		   	    	   }else if (i==12){
		   	    		   hq.setValoct(Float.parseFloat(data[i]));
		   	    	   }else if (i==13){
		   	    		   hq.setFqtyoct(Integer.parseInt(data[i]));
		   	    	   }else if (i==14){
		   	    		   hq.setFvaloct(Float.parseFloat(data[i]));
		   	    	   }
		                  	    	
			    	System.out.print(data[i]+" ");
			    }
		            System.out.println("");
		   	    	hqlist.add(hq);
		   	    	Iterator it = hqlist.iterator();
		   	    	while (it.hasNext())
		   	    	{
	   	    		 hq = (HQDetailFormBean) it.next();
	   	    		 System.out.print(hq.getDepo_code()+" ");
	   	    		 System.out.print(hq.getPr_code()+" ");
	   	    		 System.out.print(hq.getMpr_code()+" ");
	   	    		 System.out.print(hq.getPr_type()+" ");
	   	    		 System.out.print(hq.getTr_cd()+" ");
	   	    		 System.out.print(hq.getSt_cd()+" ");
	   	    		 System.out.print(hq.getAr_cd()+" ");
	   	    		 System.out.print(hq.getRg_cd()+" ");
	   	    		 System.out.print(hq.getMr_cd()+" ");
	   	    		 System.out.print(hq.getGrp_cd()+" ");
	   	    		 System.out.print(hq.getMgrp_cd()+" ");
	   	    		 System.out.print(hq.getQtyoct()+" ");
	   	    		 System.out.print(hq.getValoct()+" ");
	   	    		 System.out.print(hq.getFqtyoct()+" ");
	   	    		 System.out.print(hq.getFvaloct()+" ");
		   	    	}
		   	    	
	}
 
}