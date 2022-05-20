package com.aristo.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import com.aristo.valueobject.HQDetailFormBean;

public class BankXMLContentHandler extends XMLContentHandler
{
	private static List<HQDetailFormBean> customers = new ArrayList<HQDetailFormBean>();
	private static int count=0;
	 
 
	public int runExample(String filepath,String br,Connection con) {
		  int d=0;
		try {
			parseBankXML(filepath);
			System.out.println("No of Records '" + customers.size() + "'.");
	        SQLHQDetailDAO a = new SQLHQDetailDAO();
	        System.out.println(customers.size());
			 d = a.updateHQDetail(br.substring(0,1),customers,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		int j = printData(con);
		return d;
	}
	
	private static void parseBankXML(String filepath) throws Exception
	{
		
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		BankXMLContentHandler handler = new BankXMLContentHandler();
		parser.setProperty("http://xml.org/sax/properties/lexical-handler", handler);		
		parser.parse(new FileInputStream(filepath), handler);
		
		
        
		 System.out.println("value of count is "+count);
		
		 
		
	}
	
	
	protected XMLElement createElement(XMLElement parent, String name, Attributes attributes) throws Exception 
	{		
		XMLElement element = null;
		
		if( name.compareToIgnoreCase("dataroot") == 0 )
			element = newXMLElement(parent, attributes);
		else if( name.compareToIgnoreCase("hq") == 0 )
			element = newXMLElement(parent, createCustomer(attributes));
		else if( name.compareToIgnoreCase("O") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("N") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("D") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("J") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("F") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("M") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("A") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("Y") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("E") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("U") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("G") == 0 )
			element = newXMLElement(parent, createId((HQDetailFormBean)parent.value(), attributes));
		else if( name.compareToIgnoreCase("S") == 0 )
			element = newXMLElement(parent, createAddress((HQDetailFormBean)parent.value(), attributes));
		
		return element;
	}

	protected void processText(XMLElement parentElement, XMLElement element, String name, String str) throws Exception 
	{	
		if( element.value() instanceof HQDetailFormBean )
		{
 
			int c=0;
			HQDetailFormBean cust = (HQDetailFormBean) element.value();
			if (name.equals("O"))
			{	
				data(cust,str.split( "\\|"),"O");
 			    cust.setMonth("oct");
				c++;

			}
			if (name.equals("N"))
			{	
				data(cust,str.split( "\\|"),"N");
 			    cust.setMonth("nov");
				c++;

			}
			if (name.equals("D"))
			{	
				data(cust,str.split( "\\|"),"D");
			    cust.setMonth("dec");
				c++;

			}
			if (name.equals("J"))
			{
				data(cust,str.split( "\\|"),"J");
			    cust.setMonth("jan");
				c++;

			}
			if (name.equals("F"))
			{
				data(cust,str.split( "\\|"),"F");
			    cust.setMonth("feb");
				c++;

			}
			if (name.equals("M"))
			{
				data(cust,str.split( "\\|"),"M");
			    cust.setMonth("mar");
				c++;

			}
			if (name.equals("A"))
			{
				data(cust,str.split( "\\|"),"A");
			    cust.setMonth("apr");
				c++;
				System.out.println("April mein aa gaye hai....");

			}
			if (name.equals("Y"))
			{
				data(cust,str.split( "\\|"),"Y");
			    cust.setMonth("may");
				c++;

			}
			if (name.equals("E"))
			{
				data(cust,str.split( "\\|"),"E");
			    cust.setMonth("jun");
				c++;

			}
			if (name.equals("U"))
			{
				data(cust,str.split( "\\|"),"U");
			    cust.setMonth("jul");
				c++;

			}
			if (name.equals("G"))
			{
				data(cust,str.split( "\\|"),"G");
			    cust.setMonth("aug");
				c++;

			}
			if (name.equals("S"))
			{
				data(cust,str.split( "\\|"),"S");
			    cust.setMonth("sep");
				c++;

			}
			
   			cust.setCount(c);
       
			count++;
		} 
	}
	
	protected void processCDATA(XMLElement parentElement, XMLElement element, String str) throws Exception {		
	}

	
	private HQDetailFormBean createCustomer(Attributes attributes)
	{
		
//		Customer cust = new Customer();
		HQDetailFormBean cust = new HQDetailFormBean();
		return cust;
	}
	
	private HQDetailFormBean createId(HQDetailFormBean cust, Attributes attributes)
	{
 
	     customers.add(cust);
	     return cust;
		
	}


	private HQDetailFormBean createAddress(HQDetailFormBean cust, Attributes attributes)
	{
		customers.add(cust);
		return cust;
	}

	public static void data(HQDetailFormBean hq, String[] dval, String typ)
	{
 
   	    for(int i=0;i<dval.length;i++)
	    {
 
   	    	 
    	    	if (i==0){
    	    	  	 
 
    	    		try {
    	    			System.out.println("convert ho raha hai"+Integer.parseInt(dval[i].trim())+" "+typ+" "+dval[i].length());
    	    			 
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
	 				
						System.out.println("lag gaye kaammmmm ============================>"+typ+" "+dval[i].length());
						//e.printStackTrace();
					}
 
   	    		   hq.setDepo_code(Integer.parseInt(dval[i].trim()));
    	    	   }else if (i==1){
   	    		   hq.setPr_code(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==2){
   	    		   hq.setMpr_code(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==3){
   	    		   hq.setPr_type(dval[i]);
   	    	   }else if (i==4){
   	    		   hq.setTr_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==5){
   	    		   hq.setAr_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==6){
   	    		   hq.setRg_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==7){
   	    		   hq.setMr_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==8){
   	    		   hq.setGrp_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==9){
   	    		   hq.setMgrp_cd(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==10){
   	    		      if (typ.equals("O"))
   	    		    	  hq.setQtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setQtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setQtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setQtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setQtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setQtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setQtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setQtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setQtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setQtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setQtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setQtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==11){
	    		      if (typ.equals("O"))
	    		    	  hq.setValoct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setValnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setValdec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setValjan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setValfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setValmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setValapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setValmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("E"))
	    		    	  hq.setValjun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setValjul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setValaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setValsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==12){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setFqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setFqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setFqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setFqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setFqtyfeb(Integer.parseInt(dval[i].trim().trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setFqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setFqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setFqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setFqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setFqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setFqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setFqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==13){
	    		      if (typ.equals("O"))
	    		    	  hq.setFvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setFvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setFvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setFvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setFvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setFvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setFvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setFvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("E"))
	    		    	  hq.setFvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setFvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setFvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setFvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==14){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setEqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setEqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setEqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setEqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setEqtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setEqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setEqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setEqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setEqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setEqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setEqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setEqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==15){
	    		      if (typ.equals("O"))
	    		    	  hq.setEvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setEvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setEvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setEvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setEvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setEvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setEvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setEvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("E"))
	    		    	  hq.setEvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setEvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setEvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setEvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==16){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setBqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setBqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setBqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setBqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setBqtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setBqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setBqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setBqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("B"))
   	    		    	  hq.setBqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setBqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setBqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setBqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==17){
	    		      if (typ.equals("O"))
	    		    	  hq.setBvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setBvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setBvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setBvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setBvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setBvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setBvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setBvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("B"))
	    		    	  hq.setBvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setBvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setBvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setBvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==18){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setRqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setRqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setRqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setRqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setRqtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setRqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setRqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setRqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("R"))
   	    		    	  hq.setRqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setRqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setRqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setRqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==19){
	    		      if (typ.equals("O"))
	    		    	  hq.setRvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setRvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setRvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setRvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setRvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setRvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setRvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setRvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("R"))
	    		    	  hq.setRvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setRvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setRvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setRvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==20){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setSqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setSqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setSqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setSqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setSqtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setSqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setSqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setSqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setSqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setSqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setSqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setSqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==21){
	    		      if (typ.equals("O"))
	    		    	  hq.setSvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setSvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setSvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setSvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setSvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setSvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setSvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setSvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setSvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setSvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setSvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setSvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }else if (i==22){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setPqtyoct(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setPqtynov(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setPqtydec(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setPqtyjan(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setPqtyfeb(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setPqtymar(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setPqtyapr(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setPqtymay(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("P"))
   	    		    	  hq.setPqtyjun(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setPqtyjul(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setPqtyaug(Integer.parseInt(dval[i].trim()));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setPqtysep(Integer.parseInt(dval[i].trim()));
   	    	   }else if (i==23){
	    		      if (typ.equals("O"))
	    		    	  hq.setPvaloct(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("N"))
	    		    	  hq.setPvalnov(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("D"))
	    		    	  hq.setPvaldec(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("J"))
	    		    	  hq.setPvaljan(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("F"))
	    		    	  hq.setPvalfeb(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("M"))
	    		    	  hq.setPvalmar(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("A"))
	    		    	  hq.setPvalapr(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("Y"))
	    		    	  hq.setPvalmay(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("P"))
	    		    	  hq.setPvaljun(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("U"))
	    		    	  hq.setPvaljul(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("G"))
	    		    	  hq.setPvalaug(Float.parseFloat(dval[i].trim()));
	    		      if (typ.equals("S"))
	    		    	  hq.setPvalsep(Float.parseFloat(dval[i].trim()));
   	    	   }

		
	    }
	
	}	
	



}




