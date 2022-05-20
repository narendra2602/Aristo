package com.aristo.dao;

 
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
 
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

import com.aristo.valueobject.HQDetailFormBean;

public class HQXML extends DefaultHandler{

	List<HQDetailFormBean> hqList;
	
	private String tempVal;
	private String fl;  

	//to maintain context
	private HQDetailFormBean hq;
	
	
	public HQXML(){
		hqList = new ArrayList<HQDetailFormBean>();
	}
	
	public int runExample(String filepath,String br,Connection con) {
		parseDocument(filepath,br,con);
		int j = printData(fl,con);
		return j;
	}

	private void parseDocument(String filepath,String br,Connection con) {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			  fl = br.substring(0, 1);

			SAXParser sp = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			sp.parse(filepath, this);    
			
		}catch(SAXException se) {   
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents
	 */
	private int printData(String fl,Connection con){
		
		System.out.println("No of Records '" + hqList.size() + "'.");
        SQLHQDetailDAO a = new SQLHQDetailDAO();
		int d = a.updateHQDetail(fl,hqList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("hq")) {
			//create a new instance of HQDetailFormBean
			hq = new HQDetailFormBean();
			//hq.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
		System.out.println(tempVal); 
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("hq")) {
			//add it to the list
			hqList.add(hq);
			
		}else if (qName.equalsIgnoreCase("O")) {
			   data(hq,tempVal.split( "\\|"),"O");
		}else if (qName.equalsIgnoreCase("N")) {
			   data(hq,tempVal.split( "\\|"),"N");
		}else if (qName.equalsIgnoreCase("D")) { 
			   data(hq,tempVal.split( "\\|"),"D");
		}else if (qName.equalsIgnoreCase("J")) {
			   data(hq,tempVal.split( "\\|"),"J");
		}else if (qName.equalsIgnoreCase("F")) {
			   data(hq,tempVal.split( "\\|"),"F");
		}else if (qName.equalsIgnoreCase("M")) {
			   data(hq,tempVal.split( "\\|"),"M");
		}else if (qName.equalsIgnoreCase("A")) {
			   data(hq,tempVal.split( "\\|"),"A");
		}else if (qName.equalsIgnoreCase("Y")) {
			   data(hq,tempVal.split( "\\|"),"Y");
		}else if (qName.equalsIgnoreCase("E")) {
			   data(hq,tempVal.split( "\\|"),"E");
		}else if (qName.equalsIgnoreCase("U")) {
			   data(hq,tempVal.split( "\\|"),"U");
		}else if (qName.equalsIgnoreCase("G")) {
			   data(hq,tempVal.split( "\\|"),"G");
		}else if (qName.equalsIgnoreCase("S")) {
			   data(hq,tempVal.split( "\\|"),"S");
		}	
		
		
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
   	    		    	  hq.setFqtyfeb(Integer.parseInt(dval[i].trim()));
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
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runRxample();
	}
*/	
}
}




