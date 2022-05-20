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

public class HQDetailXML extends DefaultHandler{

	List<HQDetailFormBean> hqList;
	
	private String tempVal;
	private String fl;  

	//to maintain context
	private HQDetailFormBean hq;
	
	
	public HQDetailXML(){
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
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of HQDetailFormBean
			hq = new HQDetailFormBean();
			//hq.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			hqList.add(hq);
			if ((hq.getPr_code()==3925) || (hq.getPr_code()==3905)) 
				System.out.println("Value of valmay of Pr_code "+hq.getPr_code()+" HQ Code : "+ hq.getTr_cd()+" is "+hq.getValmay());
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			hq.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("PR_CODE")) {
			hq.setPr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("MPR_CODE")) { 
	 		hq.setMpr_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("PR_TYPE")) {
			hq.setPr_type(tempVal);
		}else if (qName.equalsIgnoreCase("tr_cd")) {
			hq.setTr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("st_cd")) {
			hq.setSt_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("ar_cd")) {
			hq.setAr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rg_cd")) {
			hq.setRg_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mr_cd")) {
			hq.setMr_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("grp_cd")) {
			hq.setGrp_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("mgrp_cd")) {
			hq.setMgrp_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyoct")) {
			hq.setQtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtynov")) {
			hq.setQtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtydec")) {
			hq.setQtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjan")) {
			hq.setQtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyfeb")) {
			hq.setQtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtymar")) {
			hq.setQtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyapr")) {
			hq.setQtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtymay")) {
			hq.setQtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjun")) {
			hq.setQtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyjul")) {
			hq.setQtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtyaug")) {
			hq.setQtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("qtysep")) {
			hq.setQtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("valoct")) {
			hq.setValoct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valnov")) {
			hq.setValnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valdec")) {
			hq.setValdec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjan")) {
			hq.setValjan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valfeb")) {
			hq.setValfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valmar")) {
			hq.setValmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valapr")) {
			hq.setValapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valmay")) {
			hq.setValmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjun")) {
			hq.setValjun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valjul")) {
			hq.setValjul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valaug")) {
			hq.setValaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("valsep")) {
			hq.setValsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyoct")) {
			hq.setFqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtynov")) {
			hq.setFqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtydec")) {
			hq.setFqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjan")) {
			hq.setFqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyfeb")) {
			hq.setFqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtymar")) {
			hq.setFqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyapr")) {
			hq.setFqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtymay")) {
			hq.setFqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjun")) {
			hq.setFqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyjul")) {
			hq.setFqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtyaug")) {
			hq.setFqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fqtysep")) {
			hq.setFqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("fvaloct")) {
			hq.setFvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalnov")) {
			hq.setFvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaldec")) {
			hq.setFvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljan")) {
			hq.setFvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalfeb")) {
			hq.setFvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalmar")) {
			hq.setFvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalapr")) {
			hq.setFvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalmay")) {
			hq.setFvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljun")) {
			hq.setFvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvaljul")) {
			hq.setFvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalaug")) {
			hq.setFvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("fvalsep")) {
			hq.setFvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyoct")) {
			hq.setEqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtynov")) {
			hq.setEqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtydec")) {
			hq.setEqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjan")) {
			hq.setEqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyfeb")) {
			hq.setEqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtymar")) {
			hq.setEqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyapr")) {
			hq.setEqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtymay")) {
			hq.setEqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjun")) {
			hq.setEqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyjul")) {
			hq.setEqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtyaug")) {
			hq.setEqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("eqtysep")) {
			hq.setEqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("evaloct")) {
			hq.setEvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalnov")) {
			hq.setEvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaldec")) {
			hq.setEvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljan")) {
			hq.setEvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalfeb")) {
			hq.setEvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalmar")) {
			hq.setEvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalapr")) {
			hq.setEvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalmay")) {
			hq.setEvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljun")) {
			hq.setEvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evaljul")) {
			hq.setEvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalaug")) {
			hq.setEvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("evalsep")) {
			hq.setEvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyoct")) {
			hq.setBqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtynov")) {
			hq.setBqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtydec")) {
			hq.setBqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjan")) {
			hq.setBqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyfeb")) {
			hq.setBqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtymar")) {
			hq.setBqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyapr")) {
			hq.setBqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtymay")) {
			hq.setBqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjun")) {
			hq.setBqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyjul")) {
			hq.setBqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtyaug")) {
			hq.setBqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bqtysep")) {
			hq.setBqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("bvaloct")) {
			hq.setBvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalnov")) {
			hq.setBvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaldec")) {
			hq.setBvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljan")) {
			hq.setBvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalfeb")) {
			hq.setBvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalmar")) {
			hq.setBvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalapr")) {
			hq.setBvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalmay")) {
			hq.setBvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljun")) {
			hq.setBvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvaljul")) {
			hq.setBvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalaug")) {
			hq.setBvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("bvalsep")) {
			hq.setBvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyoct")) {
			hq.setRqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtynov")) {
			hq.setRqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtydec")) {
			hq.setRqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjan")) {
			hq.setRqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyfeb")) {
			hq.setRqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtymar")) {
			hq.setRqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyapr")) {
			hq.setRqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtymay")) {
			hq.setRqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjun")) {
			hq.setRqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyjul")) {
			hq.setRqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtyaug")) {
			hq.setRqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rqtysep")) {
			hq.setRqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("rvaloct")) {
			hq.setRvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalnov")) {
			hq.setRvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaldec")) {
			hq.setRvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljan")) {
			hq.setRvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalfeb")) {
			hq.setRvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalmar")) {
			hq.setRvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalapr")) {
			hq.setRvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalmay")) {
			hq.setRvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljun")) {
			hq.setRvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvaljul")) {
			hq.setRvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalaug")) {
			hq.setRvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("rvalsep")) {
			hq.setRvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyoct")) {
			hq.setSqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtynov")) {
			hq.setSqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtydec")) {
			hq.setSqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjan")) {
			hq.setSqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyfeb")) {
			hq.setSqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtymar")) {
			hq.setSqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyapr")) {
			hq.setSqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtymay")) {
			hq.setSqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjun")) {
			hq.setSqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyjul")) {
			hq.setSqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtyaug")) {
			hq.setSqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("sqtysep")) {
			hq.setSqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("svaloct")) {
			hq.setSvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalnov")) {
			hq.setSvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaldec")) {
			hq.setSvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljan")) {
			hq.setSvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalfeb")) {
			hq.setSvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalmar")) {
			hq.setSvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalapr")) {
			hq.setSvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalmay")) {
			hq.setSvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljun")) {
			hq.setSvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svaljul")) {
			hq.setSvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalaug")) {
			hq.setSvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("svalsep")) {
			hq.setSvalsep(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyoct")) {
			hq.setPqtyoct(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtynov")) {
			hq.setPqtynov(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtydec")) {
			hq.setPqtydec(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjan")) {
			hq.setPqtyjan(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyfeb")) {
			hq.setPqtyfeb(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtymar")) {
			hq.setPqtymar(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyapr")) {
			hq.setPqtyapr(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtymay")) {
			hq.setPqtymay(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjun")) {
			hq.setPqtyjun(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyjul")) {
			hq.setPqtyjul(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtyaug")) {
			hq.setPqtyaug(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pqtysep")) {
			hq.setPqtysep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("pvaloct")) {
			hq.setPvaloct(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalnov")) {
			hq.setPvalnov(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaldec")) {
			hq.setPvaldec(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljan")) {
			hq.setPvaljan(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalfeb")) {
			hq.setPvalfeb(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalmar")) {
			hq.setPvalmar(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalapr")) {
			hq.setPvalapr(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalmay")) {
			hq.setPvalmay(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljun")) {
			hq.setPvaljun(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvaljul")) {
			hq.setPvaljul(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalaug")) {
			hq.setPvalaug(Float.parseFloat(tempVal));
		}else if (qName.equalsIgnoreCase("pvalsep")) {
			hq.setPvalsep(Float.parseFloat(tempVal)); 
		}
		
		
		
	}
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runExample();
	}
*/	
}




