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

public class SampleTerXML extends DefaultHandler{

	List<TerFormBean> terList;
	private String tempVal;
	private String fl;  
	private String tp;
	private TerFormBean ter;
	
	
	public SampleTerXML()
	{
		terList = new ArrayList<TerFormBean>();
	}
	
	public int runExample(String filepath,String br,Connection con) 
	{
		parseDocument(filepath,br,con);
		int j = printData(con);
		return j;
	}

	private void parseDocument(String filepath,String br,Connection con) 
	{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			  fl ="termst"; 
			  tp =br.substring(0, 1);
			  SAXParser sp = spf.newSAXParser();
			  sp.parse(filepath, this);    
			
		}catch(SAXException se) {   
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	private int printData(Connection con)
	{
		System.out.println("No of Records '" + terList.size() + "'.");
        SQLSampleTerDAO a = new SQLSampleTerDAO();
		int d = a.updateTer(tp,terList,con);
        return d;
   }
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) 
		{
			ter = new TerFormBean();
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) 
		{
		  terList.add(ter);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			ter.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("TER_CODE")) {
			ter.setTer_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("STATE_CODE")) {
			ter.setState_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("AREA_CODE")) {
			ter.setArea_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REGN_CODE")) {
			ter.setRegn_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("TER_NAME")) {
			ter.setTer_name(tempVal);
		}else if (qName.equalsIgnoreCase("NO_OF_REP")) {
			ter.setNo_of_rep(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD1")) {
			ter.setRep_cd1(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD2")) {
			ter.setRep_cd2(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD3")) {
			ter.setRep_cd3(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD4")) {
			ter.setRep_cd4(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD5")) {
			ter.setRep_cd5(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REP_CD6")) {
			ter.setRep_cd6(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("TXT")) {
			ter.setTxt(tempVal);
		}else if (qName.equalsIgnoreCase("TER_PT")) {
			ter.setTer_pt(tempVal);
		}else if (qName.equalsIgnoreCase("MKT_YEAR")) {
			ter.setMkt_year(Integer.parseInt(tempVal));
		}
		
		
	}
	
}




