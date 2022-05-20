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
import com.aristo.valueobject.RegionFormBean;

public class SampleRegionXML extends DefaultHandler{

	List<RegionFormBean> regList;
	
	private String tempVal;
	private String fl;  
	private String tp;  

	private RegionFormBean reg;
	
	public SampleRegionXML(){
		regList = new ArrayList<RegionFormBean>();
	}
	
	public int runExample(String filepath,String br,Connection con) {
		parseDocument(filepath,br,con);
		int j = printData(con);
		return j;
	}

	private void parseDocument(String filepath,String br,Connection con) {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			  fl ="regmst";
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
        SQLSampleRegionDAO a = new SQLSampleRegionDAO();
		int d = a.updateRegion(tp,regList,con);
        return d;
    }

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) 
		{
		   reg = new RegionFormBean();
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) 
		{
			regList.add(reg);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			reg.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("REG_CODE")) {
			reg.setReg_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("NAME")) {
			reg.setName(tempVal);
		}else if (qName.equalsIgnoreCase("AREA_CODE")) {
			reg.setArea_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("TXT")) {
			reg.setTxt(tempVal);
		}else if (qName.equalsIgnoreCase("MKT_YEAR")) {
			reg.setMkt_year(Integer.parseInt(tempVal));
		}
		
	}

}