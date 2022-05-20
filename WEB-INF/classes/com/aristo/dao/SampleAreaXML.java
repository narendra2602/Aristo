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

public class SampleAreaXML extends DefaultHandler{

	List<AreaFormBean> areList;
	
	private String tempVal;
	private String fl;  
	private String tp;

	private AreaFormBean are;
	
	public SampleAreaXML(){
		areList = new ArrayList<AreaFormBean>();
	}
	
	public int runExample(String filepath,String br,Connection con) {
		parseDocument(filepath,br,con);
		int j = printData(con);
		return j;
	}

	private void parseDocument(String filepath,String br,Connection con) {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			  fl ="areamst";
			  tp =br.substring(0,1);

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

	private int printData(Connection con){
		
		System.out.println("No of Records '" + areList.size() + "'.");
        SQLSampleAreaDAO a = new SQLSampleAreaDAO();
		int d = a.updateArea(tp,areList,con);
        return d;
   }
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tempVal = "";
		if(qName.equalsIgnoreCase(fl)) {
			are = new AreaFormBean();
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase(fl)) {
			areList.add(are);
			
		}else if (qName.equalsIgnoreCase("DEPO_CODE")) {
			are.setDepo_code(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("AREA_CD")) {
			are.setArea_cd(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("AREA_NAME")) {
			are.setArea_name(tempVal);
		}else if (qName.equalsIgnoreCase("MKT_YEAR")) {
			are.setMkt_year(Integer.parseInt(tempVal));
		}
	}
	
}