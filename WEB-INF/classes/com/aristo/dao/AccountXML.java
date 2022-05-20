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

import com.aristo.valueobject.AccountFormBean;

public class AccountXML extends DefaultHandler{

	List<AccountFormBean> accList;
	
	private String tempVal;
	private String fl; 
	private String tp;

	//to maintain context
	private AccountFormBean acc;
	
	
	public AccountXML(){
		accList = new ArrayList<AccountFormBean>();
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
			  fl = br+"FAA09";
			  tp = br.substring(0,1);

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
		
		System.out.println("No of Records '" + accList.size() + "'.");
        SQLAccountDAO a = new SQLAccountDAO();
		int d = a.updateAccount(tp,accList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of employee
			acc = new AccountFormBean();
			//are.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			accList.add(acc);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			acc.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MCMP_CODE")) {
			acc.setMcmp_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MUSR_CD")) {
			acc.setMusr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MGRP_CODE")) {
			acc.setMgrp_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MAC_CODE")) {
			acc.setMac_code(tempVal);
		}else if (qName.equalsIgnoreCase("MAC_TYPE")) {
			acc.setMac_type(tempVal);
		}else if (qName.equalsIgnoreCase("MAC_PRFX")) {
			acc.setMac_prfx(tempVal);
		}else if (qName.equalsIgnoreCase("MAC_NAME")) {
			acc.setMac_name(tempVal);
		}else if (qName.equalsIgnoreCase("MADD1")) {
			acc.setMadd1(tempVal);
		}else if (qName.equalsIgnoreCase("MADD2")) {
			acc.setMadd2(tempVal);
		}else if (qName.equalsIgnoreCase("MADD3")) {
			acc.setMadd3(tempVal);
		}else if (qName.equalsIgnoreCase("MCITY")) {
			acc.setMcity(tempVal);
		}else if (qName.equalsIgnoreCase("MPIN")) {
			acc.setMpin(tempVal);
		}else if (qName.equalsIgnoreCase("MPHONE")) {
			acc.setMphone(tempVal);
		}else if (qName.equalsIgnoreCase("MCONTCT")) {
			acc.setMcontct(tempVal);
		}else if (qName.equalsIgnoreCase("MDLNO1")) {
			acc.setMdlno1(tempVal);
		}else if (qName.equalsIgnoreCase("MDLNO2")) {
			acc.setMdlno2(tempVal);
		}else if (qName.equalsIgnoreCase("MBANKER")) {
			acc.setMbanker(tempVal);
		}else if (qName.equalsIgnoreCase("MBANK_ADD1")) {
			acc.setMbank_add1(tempVal);
		}else if (qName.equalsIgnoreCase("MBANK_ADD2")) {
			acc.setMbank_add2(tempVal);
		}else if (qName.equalsIgnoreCase("MTRANSPT")) {
			acc.setMtranspt(tempVal);
		}else if (qName.equalsIgnoreCase("MPST_NO")) {
			acc.setMpst_no(tempVal);
		}else if (qName.equalsIgnoreCase("MCST_NO")) {
			acc.setMcst_no(tempVal);
		}else if (qName.equalsIgnoreCase("MFIX_DISC1")) {
			acc.setMfix_disc1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MFIX_DISC2")) {
			acc.setMfix_disc2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MFIX_TAX1")) {
			acc.setMfix_tax1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MFIX_TAX2")) {
			acc.setMfix_tax2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MSTAT_CODE")) {
			acc.setMstat_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MAREA_CODE")) {
			acc.setMarea_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MAREA_CD1")) {
			acc.setMarea_cd1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MREGION_CD")) {
			acc.setMregion_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MREGIO_CD1")) {
			acc.setMregio_cd1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MTERR_CODE")) {
			acc.setMterr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MTERR_CD1")) {
			acc.setMterr_cd1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MDIST_CD")) {
			acc.setMdist_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MTYPE")) {
			acc.setMtype(tempVal);
		}else if (qName.equalsIgnoreCase("MDAYS")) {
			acc.setMdays(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("OCTROI1")) {
			acc.setOctroi1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("OCTROI2")) {
			acc.setOctroi2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("OCTROI3")) {
			acc.setOctroi3(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("CST_TYPE")) {
			acc.setCst_type(tempVal);
		}else if (qName.equalsIgnoreCase("MSR_CODE")) {
			acc.setMsr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MSR_CODE1")) {
			acc.setMsr_code1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MCURR_BAL")) {
			acc.setMcurr_bal(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MCURR_DB")) {
			acc.setMcurr_db(tempVal);
		}else if (qName.equalsIgnoreCase("MOPNG_BAL")) {
			acc.setMopng_bal(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MOPNG_DB")) {
			acc.setMopng_db(tempVal);
		}else if (qName.equalsIgnoreCase("MLOPNG_BAL")) {
			acc.setMlopng_bal(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MLOPNG_DB")) {
			acc.setMlopng_db(tempVal);
		}else if (qName.equalsIgnoreCase("MLCLOS_BAL")) {
			acc.setMlclos_bal(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MLCLOS_DB")) {
			acc.setMlclos_db(tempVal);
		}else if (qName.equalsIgnoreCase("MNTH_BAL")) {
			acc.setMnth_bal(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MNTH_DR")) {
			acc.setMnth_dr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MNTH_CR")) {
			acc.setMnth_cr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("MNTH_DB")) {
			acc.setMnth_db(tempVal);
		}else if (qName.equalsIgnoreCase("PTYPE")) {
			acc.setPtype(tempVal);
		}else if (qName.equalsIgnoreCase("MDIST1")) {
			acc.setMdist1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("SLCT")) {
			acc.setSlct(tempVal);
		}

		
		

	}
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runExample();
	}
*/	
}




