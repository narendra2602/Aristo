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

import com.aristo.valueobject.AreaFormBean;

public class AreaXML extends DefaultHandler{

	List<AreaFormBean> areList;
	
	private String tempVal;
	private String fl;  
	private String tp;
	
	//to maintain context
	private AreaFormBean are;
	
	
	public AreaXML(){
		areList = new ArrayList<AreaFormBean>();
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
//			  fl = br+"ARE09";
			  String jj = filepath.substring(filepath.indexOf(br)); 
//			  fl = filepath.substring(46,54);
			  fl = jj.substring(0,8);
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
		
		System.out.println("No of Records '" + areList.size() + "'.");
        SQLAreaDAO a = new SQLAreaDAO();
		int d = a.updateArea(tp,areList,con);
        return d;
   }
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			//create a new instance of employee
			are = new AreaFormBean();
			//are.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			//add it to the list
			areList.add(are);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			are.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("area_cd")) {
			are.setArea_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("area_name")) {
			are.setArea_name(tempVal);
		}else if (qName.equalsIgnoreCase("slct")) {
			are.setSlct(tempVal);
		}else if (qName.equalsIgnoreCase("typ")) {
			are.setTyp(tempVal);
		}else if (qName.equalsIgnoreCase("mkt_year")) {
			are.setMkt_year(Integer.parseInt(tempVal));
		}



		
		
		
	}
	
/*	public static void main(String[] args){
		MySAXParser spe = new MySAXParser();
		spe.runExample();
	}
*/	
}




