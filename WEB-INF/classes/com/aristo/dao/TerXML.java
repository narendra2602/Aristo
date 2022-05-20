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

import com.aristo.valueobject.TerFormBean;

public class TerXML extends DefaultHandler{

	List<TerFormBean> terList;
	
	private String tempVal;
	private String fl;  
	private String tp;
	//to maintain context
	private TerFormBean ter;
	
	
	public TerXML(){
		terList = new ArrayList<TerFormBean>();
	}
	
	public int runExample(String filepath,String br,Connection con) {
		parseDocument(filepath,br,con);
		int j = printData(con);
		return j;
	}

	private void parseDocument(String filepath,String br,Connection con) {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
//			  fl = br+"TER09"; 
			  String jj = filepath.substring(filepath.indexOf(br)); 
//			  fl = filepath.substring(46,54);
			  fl = jj.substring(0,8);
			  tp = br.substring(0, 1);
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
	private int printData(Connection con){
		
		System.out.println("No of Records '" + terList.size() + "'.");
        SQLTerDAO a = new SQLTerDAO();
		int d = a.updateTer(tp,terList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of employee
			ter = new TerFormBean();
			//ter.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			terList.add(ter);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			ter.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ter_code")) {
			ter.setTer_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("area_code")) {
			ter.setArea_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("regn_code")) {
			ter.setRegn_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ter_name")) {
			ter.setTer_name(tempVal);
		}else if (qName.equalsIgnoreCase("no_of_rep")) {
			ter.setNo_of_rep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd1")) {
			ter.setRep_cd1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd2")) {
			ter.setRep_cd2(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd3")) {
			ter.setRep_cd3(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd4")) {
			ter.setRep_cd4(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd5")) {
			ter.setRep_cd5(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rep_cd6")) {
			ter.setRep_cd6(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("txt")) {
			ter.setTxt(tempVal);
		}else if (qName.equalsIgnoreCase("ter_pt")) {
			ter.setTer_pt(tempVal);
		}else if (qName.equalsIgnoreCase("oct")) {
			ter.setOct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("nov")) {
			ter.setNov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("dec")) {
			ter.setDec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("jan")) {
			ter.setJan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("feb")) {
			ter.setFeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mar")) {
			ter.setMar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("apr")) {
			ter.setApr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("may")) {
			ter.setMay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("jun")) {
			ter.setJun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("jul")) {
			ter.setJuly(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("aug")) {
			ter.setAug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sep")) {
			ter.setSep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("state_code")) {
			ter.setState_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("slct")) {
			ter.setSlct(tempVal);
		}else if (qName.equalsIgnoreCase("mkt_year")) {
			ter.setMkt_year(Integer.parseInt(tempVal));
		}
		
		
	}
	
}




