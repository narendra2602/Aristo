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

import com.aristo.valueobject.StockiestFormBean;

public class StockiestXML extends DefaultHandler{

	List<StockiestFormBean> stckList;
	
	private String tempVal;
	private String fl;  

	//to maintain context
	private StockiestFormBean stck;
	
	
	public StockiestXML(){
		stckList = new ArrayList<StockiestFormBean>();
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
	private int printData(Connection con){
		
		System.out.println("No of Records '" + stckList.size() + "'.");
        SQLStockiestDAO a = new SQLStockiestDAO();
		int d = a.updateStockiest(fl,stckList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of employee
			stck = new StockiestFormBean();
			//stck.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			stckList.add(stck);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			stck.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("STK_CODE")) {
			stck.setStk_code(tempVal);
		}else if (qName.equalsIgnoreCase("PR_CODE")) {
			stck.setPr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MPR_CODE")) {
			stck.setMpr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("PR_TYPE")) {
			stck.setPr_type(tempVal);
		}else if (qName.equalsIgnoreCase("tr_cd")) {
			stck.setTr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("st_cd")) {
			stck.setSt_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ar_cd")) {
			stck.setAr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rg_cd")) {
			stck.setRg_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mr_cd")) {
			stck.setMr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("grp_cd")) {
			stck.setGrp_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mgrp_cd")) {
			stck.setMgrp_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyoct")) {
			stck.setQtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtynov")) {
			stck.setQtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtydec")) {
			stck.setQtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjan")) {
			stck.setQtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyfeb")) {
			stck.setQtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtymar")) {
			stck.setQtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyapr")) {
			stck.setQtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtymay")) {
			stck.setQtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjun")) {
			stck.setQtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjul")) {
			stck.setQtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyaug")) {
			stck.setQtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtysep")) {
			stck.setQtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("valoct")) {
			stck.setValoct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valnov")) {
			stck.setValnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valdec")) {
			stck.setValdec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjan")) {
			stck.setValjan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valfeb")) {
			stck.setValfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valmar")) {
			stck.setValmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valapr")) {
			stck.setValapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valmay")) {
			stck.setValmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjun")) {
			stck.setValjun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjul")) {
			stck.setValjul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valaug")) {
			stck.setValaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valsep")) {
			stck.setValsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyoct")) {
			stck.setFqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtynov")) {
			stck.setFqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtydec")) {
			stck.setFqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjan")) {
			stck.setFqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyfeb")) {
			stck.setFqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtymar")) {
			stck.setFqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyapr")) {
			stck.setFqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtymay")) {
			stck.setFqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjun")) {
			stck.setFqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjul")) {
			stck.setFqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyaug")) {
			stck.setFqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtysep")) {
			stck.setFqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fvaloct")) {
			stck.setFvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalnov")) {
			stck.setFvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaldec")) {
			stck.setFvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljan")) {
			stck.setFvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalfeb")) {
			stck.setFvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalmar")) {
			stck.setFvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalapr")) {
			stck.setFvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalmay")) {
			stck.setFvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljun")) {
			stck.setFvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljul")) {
			stck.setFvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalaug")) {
			stck.setFvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalsep")) {
			stck.setFvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyoct")) {
			stck.setEqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtynov")) {
			stck.setEqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtydec")) {
			stck.setEqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjan")) {
			stck.setEqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyfeb")) {
			stck.setEqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtymar")) {
			stck.setEqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyapr")) {
			stck.setEqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtymay")) {
			stck.setEqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjun")) {
			stck.setEqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjul")) {
			stck.setEqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyaug")) {
			stck.setEqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtysep")) {
			stck.setEqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("evaloct")) {
			stck.setEvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalnov")) {
			stck.setEvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaldec")) {
			stck.setEvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljan")) {
			stck.setEvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalfeb")) {
			stck.setEvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalmar")) {
			stck.setEvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalapr")) {
			stck.setEvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalmay")) {
			stck.setEvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljun")) {
			stck.setEvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljul")) {
			stck.setEvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalaug")) {
			stck.setEvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalsep")) {
			stck.setEvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyoct")) {
			stck.setBqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtynov")) {
			stck.setBqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtydec")) {
			stck.setBqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjan")) {
			stck.setBqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyfeb")) {
			stck.setBqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtymar")) {
			stck.setBqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyapr")) {
			stck.setBqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtymay")) {
			stck.setBqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjun")) {
			stck.setBqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjul")) {
			stck.setBqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyaug")) {
			stck.setBqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtysep")) {
			stck.setBqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bvaloct")) {
			stck.setBvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalnov")) {
			stck.setBvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaldec")) {
			stck.setBvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljan")) {
			stck.setBvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalfeb")) {
			stck.setBvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalmar")) {
			stck.setBvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalapr")) {
			stck.setBvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalmay")) {
			stck.setBvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljun")) {
			stck.setBvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljul")) {
			stck.setBvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalaug")) {
			stck.setBvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalsep")) {
			stck.setBvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyoct")) {
			stck.setRqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtynov")) {
			stck.setRqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtydec")) {
			stck.setRqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjan")) {
			stck.setRqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyfeb")) {
			stck.setRqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtymar")) {
			stck.setRqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyapr")) {
			stck.setRqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtymay")) {
			stck.setRqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjun")) {
			stck.setRqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjul")) {
			stck.setRqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyaug")) {
			stck.setRqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtysep")) {
			stck.setRqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rvaloct")) {
			stck.setRvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalnov")) {
			stck.setRvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaldec")) {
			stck.setRvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljan")) {
			stck.setRvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalfeb")) {
			stck.setRvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalmar")) {
			stck.setRvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalapr")) {
			stck.setRvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalmay")) {
			stck.setRvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljun")) {
			stck.setRvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljul")) {
			stck.setRvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalaug")) {
			stck.setRvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalsep")) {
			stck.setRvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyoct")) {
			stck.setSqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtynov")) {
			stck.setSqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtydec")) {
			stck.setSqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjan")) {
			stck.setSqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyfeb")) {
			stck.setSqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtymar")) {
			stck.setSqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyapr")) {
			stck.setSqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtymay")) {
			stck.setSqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjun")) {
			stck.setSqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjul")) {
			stck.setSqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyaug")) {
			stck.setSqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtysep")) {
			stck.setSqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("svaloct")) {
			stck.setSvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalnov")) {
			stck.setSvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaldec")) {
			stck.setSvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljan")) {
			stck.setSvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalfeb")) {
			stck.setSvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalmar")) {
			stck.setSvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalapr")) {
			stck.setSvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalmay")) {
			stck.setSvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljun")) {
			stck.setSvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljul")) {
			stck.setSvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalaug")) {
			stck.setSvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalsep")) {
			stck.setSvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyoct")) {
			stck.setPqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtynov")) {
			stck.setPqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtydec")) {
			stck.setPqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjan")) {
			stck.setPqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyfeb")) {
			stck.setPqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtymar")) {
			stck.setPqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyapr")) {
			stck.setPqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtymay")) {
			stck.setPqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjun")) {
			stck.setPqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjul")) {
			stck.setPqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyaug")) {
			stck.setPqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtysep")) {
			stck.setPqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pvaloct")) {
			stck.setPvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalnov")) {
			stck.setPvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaldec")) {
			stck.setPvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljan")) {
			stck.setPvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalfeb")) {
			stck.setPvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalmar")) {
			stck.setPvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalapr")) {
			stck.setPvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalmay")) {
			stck.setPvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljun")) {
			stck.setPvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljul")) {
			stck.setPvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalaug")) {
			stck.setPvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalsep")) {
			stck.setPvalsep(Float.parseFloat(tempVal)); 
		}
		
	}
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runExample();
	}
*/	
}




