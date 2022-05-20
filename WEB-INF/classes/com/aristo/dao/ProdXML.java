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

import com.aristo.valueobject.ProdFormBean;

public class ProdXML extends DefaultHandler{

	List<ProdFormBean> prList;
	
	private String tempVal;
	private String fl;  

	//to maintain context
	private ProdFormBean pr;
	
	
	public ProdXML(){
		prList = new ArrayList<ProdFormBean>();
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
		
		System.out.println("No of Records '" + prList.size() + "'."); 
        SQLProdDAO a = new SQLProdDAO();
		int d = a.updateProd(fl,prList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of employee
			pr = new ProdFormBean();
			//pr.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			prList.add(pr);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			pr.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("PR_CODE")) {
			pr.setPr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("tr_cd")) {
			pr.setTr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("st_cd")) {
			pr.setSt_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ar_cd")) {
			pr.setAr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rg_cd")) {
			pr.setRg_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mr_cd")) {
			pr.setMr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("grp_cd")) {
			pr.setGrp_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la10")) {
			pr.setLa10(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la11")) {
			pr.setLa11(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la12")) {
			pr.setLa12(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la1")) {
			pr.setLa1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la2")) {
			pr.setLa2(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la3")) {
			pr.setLa3(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la4")) {
			pr.setLa4(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la5")) {
			pr.setLa5(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la6")) {
			pr.setLa6(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la7")) {
			pr.setLa7(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la8")) {
			pr.setLa8(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("la9")) {
			pr.setLa9(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("lmrp")) {
			pr.setLmrp(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tmrp")) {
			pr.setTmrp(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt10")) {
			pr.setTt10(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt11")) {
			pr.setTt11(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt12")) {
			pr.setTt12(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt1")) {
			pr.setTt1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt2")) {
			pr.setTt2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt3")) {
			pr.setTt3(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt4")) {
			pr.setTt4(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt5")) {
			pr.setTt5(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt6")) {
			pr.setTt6(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt7")) {
			pr.setTt7(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt8")) {
			pr.setTt8(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("tt9")) {
			pr.setTt9(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ta10")) {
			pr.setTa10(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta11")) {
			pr.setTa11(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta12")) {
			pr.setTa12(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta1")) {
			pr.setTa1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta2")) {
			pr.setTa2(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta3")) {
			pr.setTa3(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta4")) {
			pr.setTa4(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta5")) {
			pr.setTa5(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta6")) {
			pr.setTa6(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta7")) {
			pr.setTa7(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta8")) {
			pr.setTa8(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ta9")) {
			pr.setTa9(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ltarget")) {
			pr.setLtarget(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("lvalue")) {
			pr.setLvalue(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ttarget")) {
			pr.setTtarget(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("tvalue")) {
			pr.setTvalue(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra10")) {
			pr.setRa10(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra11")) {
			pr.setRa11(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra12")) {
			pr.setRa12(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra1")) {
			pr.setRa1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra2")) {
			pr.setRa2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra3")) {
			pr.setRa3(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra4")) {
			pr.setRa4(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra5")) {
			pr.setRa5(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra6")) {
			pr.setRa6(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra7")) {
			pr.setRa7(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra8")) {
			pr.setRa8(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ra9")) {
			pr.setRa9(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl10")) {
			pr.setRl10(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl11")) {
			pr.setRl11(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl12")) {
			pr.setRl12(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl1")) {
			pr.setRl1(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl2")) {
			pr.setRl2(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl3")) {
			pr.setRl3(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl4")) {
			pr.setRl4(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl5")) {
			pr.setRl5(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl6")) {
			pr.setRl6(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl7")) {
			pr.setRl7(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl8")) {
			pr.setRl8(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rl9")) {
			pr.setRl9(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("ttemp")) {
			pr.setTtemp(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pttype")) {
			pr.setPttype(tempVal);
		}else if (qName.equalsIgnoreCase("gp_main")) {
			pr.setGp_main(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("flc")) {
			pr.setFlc(tempVal);
		}		
		
		

	
	}
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runExample();
	}
*/	
}




