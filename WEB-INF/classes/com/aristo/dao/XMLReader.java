package com.aristo.dao;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aristo.valueobject.AreaFormBean;
 
import com.aristo.valueobject.AccountFormBean;
import com.aristo.valueobject.BatchMasterFormBean;
import com.aristo.valueobject.FactoryFormBean;
import com.aristo.valueobject.HQDetailFormBean;
import com.aristo.valueobject.InvFstFormBean;
import com.aristo.valueobject.MSRFormBean;
import com.aristo.valueobject.ProdFormBean;
import com.aristo.valueobject.RegionFormBean;
import com.aristo.valueobject.StockFormBean;
import com.aristo.valueobject.StockiestFormBean;
import com.aristo.valueobject.TerFormBean;
public class XMLReader {

	public static void UpdateXmlTrig(String filepath,String br,Connection con){

		  try {
		  File file = new File(filepath);
		  System.out.println(br);
		  String f2 = br.substring(0, 1);
		  System.out.println(f2);
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
		  NodeList nodeLst = doc.getElementsByTagName("trig");

		  
		  AreaFormBean area = null;
			for (int s = 0; s < nodeLst.getLength(); s++)
			{
		        Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
		    {
		           Element fstElmnt = (Element) fstNode;
		           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
		           Element depo = (Element) depoLst.item(0);
		           NodeList id = depo.getChildNodes();
		           //formbean mein data set
		            area = new AreaFormBean();
		            area.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		            //formbean mein data set
		           

	                NodeList areacd = fstElmnt.getElementsByTagName("MKT_YEAR"); //Second Element
		            Element arcd = (Element) areacd.item(0);
		            NodeList ar = arcd.getChildNodes();
	                area.setMkt_year(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
		            
		            areacd = fstElmnt.getElementsByTagName("SEQ"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                area.setArea_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

		      
		            AreaDAO areaup = new SQLAreaDAO();
	    		    
		            int i = areaup.updateTrig(f2,area,con);
//		            t=t+i;

		            System.out.println(i+" Record Added Sucessfully");;

		    }

		  } 
			  
		  } catch (Exception e) 
		   {
		    e.printStackTrace();
		   }
		  
	   }
	

 public static void UpdateXmlArea(String filepath,String br,Connection con){

	  try {
	  File file = new File(filepath);

	  String fl = br+"ARE09";
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  NodeList nodeLst = doc.getElementsByTagName(fl);

	  AreaFormBean area = null;
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	           Element fstElmnt = (Element) fstNode;
	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	            area = new AreaFormBean();
	            area.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	            //formbean mein data set
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("AREA_CD"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
                area.setArea_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	            
                NodeList areanm = fstElmnt.getElementsByTagName("AREA_NAME"); //Third Element
	            Element arnm = (Element) areanm.item(0);
	            NodeList arn = arnm.getChildNodes();
	            area.setArea_name((((Node) arn.item(0)).getNodeValue()));
	      

	            NodeList typ = fstElmnt.getElementsByTagName("TYP"); // 4th Element
	            Element typel = (Element) typ.item(0);
	            NodeList ty = typel.getChildNodes();
	            area.setTyp((((Node) ty.item(0)).getNodeValue()));
	      

	    }

	  } 
		  
	  } catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
   }




/////////////////////////////////// // Region Master XMl Conversion
 public static int UpdateXmlRegion(String filepath,String br,Connection con){

	   int t=0;
	  try {
	 
	  File file = new File(filepath);

	  String fl = br+"REG09";
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName(fl);
	  System.out.println("Information of all employees");

	  
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  RegionFormBean region = null;

	  System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;

	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	            region = new RegionFormBean();
	            region.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	            //formbean mein data set
	           
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("REG_CODE"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
                region.setReg_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	      
	            
               NodeList areanm = fstElmnt.getElementsByTagName("NAME"); //Third Element
	            Element arnm = (Element) areanm.item(0);
	            NodeList arn = arnm.getChildNodes();
	            region.setName((((Node) arn.item(0)).getNodeValue()));
	            
	             areacd = fstElmnt.getElementsByTagName("AREA_CODE"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
                 region.setArea_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
                 areanm = fstElmnt.getElementsByTagName("TXT"); //Third Element
	             arnm = (Element) areanm.item(0);
	             arn = arnm.getChildNodes();
	             region.setTxt((((Node) arn.item(0)).getNodeValue()));
	             
	              areanm = fstElmnt.getElementsByTagName("SLCT"); //Third Element
		          arnm = (Element) areanm.item(0);
		          arn = arnm.getChildNodes();
		          region.setSlct((((Node) arn.item(0)).getNodeValue()));

	            NodeList typ = fstElmnt.getElementsByTagName("TYP"); // 4th Element
	            Element typel = (Element) typ.item(0);
	            NodeList ty = typel.getChildNodes();
	            region.setTyp((((Node) ty.item(0)).getNodeValue()));
	      

//	            RegionDAO regionup = new SQLRegionDAO();
		    
//	            int i = regionup.updateRegion(region,con);
//	            t=t+i;

	            //System.out.println(i+" Record Sucessfully Added");;
	    }

	  } 
		  
		   
		
		
	  } catch (Exception e) 
	   {
	     System.out.println("Null Pointer exception aa raha hai....");
		  e.printStackTrace();
	   }
         return t;	  
  }




 
//MSR Master XMl Conversion
 public static void UpdateXmlMSR(String filepath,String br,Connection con){

	  try {
	 
	  File file = new File(filepath);

	  String fl = br+"MSR09";
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName(fl);
	  System.out.println("Information of all employees");

	  
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  MSRFormBean msr = null;
	  System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;

	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	           msr= new MSRFormBean();
	           msr.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	            //formbean mein data set
	           
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("RM_CD"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
	            msr.setRm_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	      
                areacd = fstElmnt.getElementsByTagName("MSR_CD"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setMsr_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	            
               NodeList areanm = fstElmnt.getElementsByTagName("MSR_NAME"); //Third Element
	            Element arnm = (Element) areanm.item(0);
	            NodeList arn = arnm.getChildNodes();
	            msr.setMsr_name((((Node) arn.item(0)).getNodeValue()));
	            
	           areanm = fstElmnt.getElementsByTagName("SLCT"); //Third Element
	            arnm = (Element) areanm.item(0);
	            arn = arnm.getChildNodes();
	            msr.setSlct((((Node) arn.item(0)).getNodeValue()));
	            
	             areacd = fstElmnt.getElementsByTagName("ST_CD"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setSt_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
                 areacd = fstElmnt.getElementsByTagName("AR_CD"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setAr_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
                 areacd = fstElmnt.getElementsByTagName("RG_CD"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setRg_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
                 
                 areacd = fstElmnt.getElementsByTagName("TR_CD"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setTr_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
                 areacd = fstElmnt.getElementsByTagName("JOINING_MM"); //Second Element
	             arcd = (Element) areacd.item(0);
	             ar = arcd.getChildNodes();
	             msr.setJoining_mm(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
               
                 	      

//	            MSRDAO msrup = new SQLMSRDAO();
		    
//	            int i = msrup.updateMSR(msr,con);
		    
//	            System.out.println(i+" Record Sucessfully Added");;  
	    }

	  } 
		  
		   
		
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
  }

//////////////////////////////////////Account Master XMl Conversion///////////////////////////////

 public int UpdateXmlAccount(String filepath,String br,Connection con){

	  int t=0;
	  try {
	 
	  File file = new File(filepath);
	  List<AccountFormBean> accList = new ArrayList<AccountFormBean>();

	  String jj = filepath.substring(filepath.indexOf(br)); 
	  String fl = jj.substring(0,8); 

//	  String fl = br+"FAA09";
	  String f2 = br.substring(0, 1);

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  NodeList nodeLst = doc.getElementsByTagName(fl);
	  

	  AccountFormBean account = null;

	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
  	            Element fstElmnt = (Element) fstNode;

	            NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	            Element depo = (Element) depoLst.item(0);
	            NodeList id = depo.getChildNodes();
	      
	            account = new AccountFormBean();
	            account.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("MCMP_CODE"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
                account.setMcmp_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            
	            areacd = fstElmnt.getElementsByTagName("MUSR_CD"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMusr_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
	            areacd = fstElmnt.getElementsByTagName("MGRP_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMgrp_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MAC_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMac_code((((Node) ar.item(0)).getNodeValue()));
                
	            areacd = fstElmnt.getElementsByTagName("MAC_TYPE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMac_type((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MAC_PRFX"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMac_prfx((((Node) ar.item(0)).getNodeValue()));
	            
	            areacd = fstElmnt.getElementsByTagName("MAC_NAME"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMac_name((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MADD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMadd1((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MADD2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMadd2((((Node) ar.item(0)).getNodeValue()));

                
	            areacd = fstElmnt.getElementsByTagName("MADD3"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
          		{	
          		  account.setMadd3((((Node) ar.item(0)).getNodeValue()));
          		}

	            areacd = fstElmnt.getElementsByTagName("MCITY"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
          		{	
                account.setMcity((((Node) ar.item(0)).getNodeValue()));
          		}

	            areacd = fstElmnt.getElementsByTagName("MPIN"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
          		{	
                account.setMpin((((Node) ar.item(0)).getNodeValue()));
          		}

	            areacd = fstElmnt.getElementsByTagName("MPHONE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
          			account.setMphone((((Node) ar.item(0)).getNodeValue()));


	            areacd = fstElmnt.getElementsByTagName("MCONTCT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
          			account.setMcontct((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MDLNO1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMdlno1((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MDLNO2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMdlno2((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MBANKER"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMbanker((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MBANK_ADD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMbank_add1((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MBANK_ADD2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMbank_add2((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MTRANSPT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMtranspt((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MPST_NO"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMpst_no((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MCST_NO"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMcst_no((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MFIX_DISC1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMfix_disc1(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));


	            areacd = fstElmnt.getElementsByTagName("MFIX_DISC2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMfix_disc2(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MFIX_TAX1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMfix_tax1(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MFIX_TAX2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMfix_tax2(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("MSTAT_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMstat_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MAREA_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMarea_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MAREA_CD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMarea_cd1(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

                
	            areacd = fstElmnt.getElementsByTagName("MREGION_CD"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMregion_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MREGIO_CD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMregio_cd1(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MTERR_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMterr_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MTERR_CD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMterr_cd1(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MDIST_CD"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMdist_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

                
	            areacd = fstElmnt.getElementsByTagName("MTYPE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMtype(((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MDAYS"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMdays(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
                

	            areacd = fstElmnt.getElementsByTagName("OCTROI1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setOctroi1(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("OCTROI1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setOctroi1(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("OCTROI2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setOctroi2(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("OCTROI3"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setOctroi3(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("CST_TYPE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setCst_type(((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("MSR_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMsr_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("MSR_CODE1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMsr_code1(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("MCURR_BAL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMcurr_bal(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));
                
	            areacd = fstElmnt.getElementsByTagName("MCURR_DB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMcurr_db((((Node) ar.item(0)).getNodeValue()));

	            areacd = fstElmnt.getElementsByTagName("MOPNG_BAL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMopng_bal(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MOPNG_DB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMopng_db(((((Node) ar.item(0)).getNodeValue())));
                
                
	            areacd = fstElmnt.getElementsByTagName("MLOPNG_BAL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMlopng_bal(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MLOPNG_DB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMlopng_db(((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MLCLOS_BAL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMlclos_bal(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MLCLOS_DB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMlclos_db(((((Node) ar.item(0)).getNodeValue())));
                
                
	            areacd = fstElmnt.getElementsByTagName("MNTH_BAL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMnth_bal(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

	            areacd = fstElmnt.getElementsByTagName("MNTH_DR"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMnth_dr(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));

                areacd = fstElmnt.getElementsByTagName("MNTH_CR"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMnth_cr(Float.parseFloat((((Node) ar.item(0)).getNodeValue())));


                areacd = fstElmnt.getElementsByTagName("MNTH_DB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setMnth_db(((((Node) ar.item(0)).getNodeValue())));

                areacd = fstElmnt.getElementsByTagName("PTYPE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setPtype(((((Node) ar.item(0)).getNodeValue())));
                
                areacd = fstElmnt.getElementsByTagName("MDIST1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMdist1(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

                areacd = fstElmnt.getElementsByTagName("SLCT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
          		if(ar.getLength()>0)
                account.setSlct(((((Node) ar.item(0)).getNodeValue())));

          		
	            areacd = fstElmnt.getElementsByTagName("MKT_YEAR"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                account.setMkt_year(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
          		
	   			accList.add(account);
                
	    }

	  } 
		  
		   
        SQLAccountDAO accup = new SQLAccountDAO();
        t = accup.updateAccount(f2,accList,con);  		   
		
		
	  } catch (Exception e)  
	   { 
	     System.out.println("Null Pointer exception aa raha hai....");
		  e.printStackTrace();
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in ReadTimeXML.Connection.close "+e);
			}
		}	

	  
	  return t;	  
  }
 
 
 
 
 
 
 
 /////////////////////////////////////////////////////////////////////////////////////////////////////
//HQ Master XMl Conversion
 public static int UpdateXmlTer(String filepath,String br,Connection con){
	   int t=0;

	  try 
	  
	  {
	 
        //File file = new File("D:\\emp.xml");
	  
	  
      File file = new File(filepath);
	  String fl = br+"TER09";
	  System.out.println(fl);

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName(fl);
	  System.out.println("Information of all Ter");

	  
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  TerFormBean ter = null;
		//List data = new ArrayList();
	   System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;

	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	            ter = new TerFormBean();
	            ter.setDepo_code(Integer.parseInt(((Node) id.item(0)).getNodeValue()));
	            //formbean mein data set
	           
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("TER_CODE"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
                ter.setTer_code(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
	      
                areacd = fstElmnt.getElementsByTagName("STATE_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setState_code(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
                

                areacd = fstElmnt.getElementsByTagName("AREA_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setArea_code(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
                
                areacd = fstElmnt.getElementsByTagName("REGN_CODE"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRegn_code(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
                
                areacd = fstElmnt.getElementsByTagName("TER_NAME"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setTer_name(((Node) ar.item(0)).getNodeValue());
                
                areacd = fstElmnt.getElementsByTagName("NO_OF_REP"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setNo_of_rep(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("REP_CD1"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd1((Integer.parseInt(((Node) ar.item(0)).getNodeValue())));
                
                areacd = fstElmnt.getElementsByTagName("REP_CD2"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd2(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
                 
                areacd = fstElmnt.getElementsByTagName("REP_CD3"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd3(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("REP_CD4"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd4(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("REP_CD5"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd5(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("REP_CD6"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setRep_cd6(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("TXT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setTxt(((Node) ar.item(0)).getNodeValue());

                areacd = fstElmnt.getElementsByTagName("TER_PT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setTer_pt(((Node) ar.item(0)).getNodeValue());
   
                
               
                areacd = fstElmnt.getElementsByTagName("SLCT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setSlct((((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("OCT"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setOct(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("NOV"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setNov(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("DEC"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setDec(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("JAN"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setJan(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("FEB"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setFeb(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("MAR"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setMar(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("APR"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setApr(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("MAY"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setMay(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("JUN"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setJun(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("JUL"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setJuly(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("AUG"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setAug(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

                areacd = fstElmnt.getElementsByTagName("SEP"); //Second Element
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
                ter.setSep(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));

  //              SQLTerDAO terup = new SQLTerDAO();
		    
//	            int i = terup.updateTer(ter,con);
//	            t=t+i;
		    
//	            System.out.println(i+" Record Sucessfully Added");;
	    }

	  } 
		  
		   
		
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
	  return t;
	   
  }


//////////////////////////////////////TARGET Master XMl Conversion///////////////////////////////////////////
 public int UpdateXmlProd(String filepath,String br, Connection con){
	   int t=0;
	  try 
	  
	  {
	      
	 
       //File file = new File("D:\\emp.xml");
		  List<ProdFormBean> proList = new ArrayList<ProdFormBean>();
		  String tempVal;
  
	 
     File file = new File(filepath);
//	  String fl = br+"PRO09";
	  String fl = br.substring(0, 1);
	  System.out.println(fl);

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName("tgt");
	 

	  
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  ProdFormBean pro = null;
		//List data = new ArrayList();
	   System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;
               pro = new ProdFormBean();     
	           int c = 0;
               
               NodeList depoLst = fstElmnt.getElementsByTagName("bud"); //First Element
               
          	 if(depoLst.getLength()>0)
      		{	
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	           tempVal=((Node) id.item(0)).getNodeValue();
			   Prodata(pro,tempVal.split( "\\|"));
			   pro.setMonth("10");
			   c++;
          	//formbean mein Prodata set
      		} 

	           
	   			pro.setCount(c);
	   			proList.add(pro);
	           
 

		    
//	            int i = produp.updateProd(pro,con);
//	            t=t+i;
		    
	            
	    }

	  } 
		   
        SQLProdDAO produp = new SQLProdDAO();
        t = produp.updateProd(fl,proList,con);  		   
		
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in ReadTimeXML.Connection.close "+e);
			}
		}	

	  return t;
	  
 }

 public int UpdateXmlBat(String filepath,String br, Connection con){
	  int t=0;
	  try 
	  {
	  List<BatchMasterFormBean> BatList = new ArrayList<BatchMasterFormBean>();
	  String tempVal;
	  File file = new File(filepath);
	  String fl = br.substring(0, 1);
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  NodeList nodeLst = doc.getElementsByTagName("batch");
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  BatchMasterFormBean bat = null;
	  System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
          Element fstElmnt = (Element) fstNode;
          bat = new BatchMasterFormBean();     
          NodeList depoLst = fstElmnt.getElementsByTagName("BAT"); //First Element
            
       	 if(depoLst.getLength()>0)
     		 {	
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           tempVal=((Node) id.item(0)).getNodeValue();
			   Batdata(bat,tempVal.split( "\\|"));
   		 } 

       	 BatList.add(bat);
		    
	    }

	  } 
		   
     SQLBatchDAO batch = new SQLBatchDAO();
     t = batch.updateBat(BatList, con, fl);  		   
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in Batch Master XML READER.Connection.close "+e);
			}
		}	

	  return t;
	  
}  
 
	public static void Batdata(BatchMasterFormBean bat, String[] dval)throws ParseException
	{
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
   	    for(int i=0;i<dval.length;i++)
	    {
	    	   if (i==0){
   	    		   bat.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   bat.setBat_pcode(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   bat.setBat_no((dval[i]));
   	    	   }else if (i==3){
   	    		   bat.setBat_mfgdt((dval[i]));
   	    	   }else if (i==4){
   	    		   bat.setBat_expdt((dval[i]));
   	    	   }else if (i==5){
   	    		   bat.setBat_netrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==6){
   	    		   bat.setBat_trdrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==7){
   	    		   bat.setBat_mrprt(Double.parseDouble(dval[i]));
   	    	   }else if (i==8){
   	    		   bat.setBat_mfgrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==9){
   	    		   bat.setBat_hosrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==10){
   	    		   bat.setBat_excrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==11){
   	    		   bat.setBat_indct((dval[i]));
   	    	   }else if (i==12){
   	    		   bat.setBat_clos(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
   	    		   bat.setBat_purrt(Double.parseDouble(dval[i]));
   	    	   }else if (i==14){
   	    		   bat.setBat_tag((dval[i]));
   	    	   }else if (i==15){
  	    		 if (dval[i].charAt(0)!=' ')  
	   	    		   bat.setBat_rcpdt(dateformat.parse(dval[i]));   	    		   
   	    	   }else if (i==16){
   	    		 if (dval[i].charAt(0)!=' ')  
	   	    		   bat.setExpiry(dateformat.parse(dval[i]));
   	    	   }else if (i==17){
   	    		   bat.setGift_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==18){
   	    		   bat.setFact_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==19){
   	    		   bat.setRemark((dval[i]));
	   	       }
	    }
	} 
 
 
 public int UpdateXmlBud(String filepath,String br, Connection con){
	  int t=0;
	  try 
	  {
	  List<ProdFormBean> proList = new ArrayList<ProdFormBean>();
	  String tempVal;
	  File file = new File(filepath);
	  String fl = br.substring(0, 1);
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName("lys");
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  ProdFormBean pro = null;
	  System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
           Element fstElmnt = (Element) fstNode;
           pro = new ProdFormBean();     
           NodeList depoLst = fstElmnt.getElementsByTagName("lsale"); //First Element
             
        	 if(depoLst.getLength()>0)
      		 {	
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           tempVal=((Node) id.item(0)).getNodeValue();
			   Buddata(pro,tempVal.split( "\\|"));
    		 } 
 
        	 proList.add(pro);
		    
	    }

	  } 
		   
      SQLProdDAO produp = new SQLProdDAO();
      t = produp.updateBud(fl,proList,con);  		   
		
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in XML READER XMLBUD.Connection.close "+e);
			}
		}	

	  return t;
	  
} 
 
	public static void Buddata(ProdFormBean pro, String[] dval)
	{
   	    for(int i=0;i<dval.length;i++)
	    {
   	    	   if (i==0){
   	    		   pro.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   pro.setPr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   pro.setTr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   pro.setGrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==4){
   	    		   pro.setLa10(Integer.parseInt(dval[i]));
   	    	   }else if (i==5){
   	    		   pro.setLa11(Integer.parseInt(dval[i]));
   	    	   }else if (i==6){
   	    		   pro.setLa12(Integer.parseInt(dval[i]));
   	    	   }else if (i==7){
   	    		   pro.setLa1(Integer.parseInt(dval[i]));
   	    	   }else if (i==8){
   	    		   pro.setLa2(Integer.parseInt(dval[i]));
   	    	   }else if (i==9){
   	    		   pro.setLa3(Integer.parseInt(dval[i]));
   	    	   }else if (i==10){
   	    		   pro.setLa4(Integer.parseInt(dval[i]));
   	    	   }else if (i==11){
   	    		   pro.setLa5(Integer.parseInt(dval[i]));
   	    	   }else if (i==12){
   	    		   pro.setLa6(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
   	    		   pro.setLa7(Integer.parseInt(dval[i]));
   	    	   }else if (i==14){
   	    		   pro.setLa8(Integer.parseInt(dval[i]));
   	    	   }else if (i==15){
   	    		   pro.setLa9(Integer.parseInt(dval[i]));
   	    	   }else if (i==16){
   	    		   pro.setRl10(Float.parseFloat(dval[i]));
   	    	   }else if (i==17){
   	    		   pro.setRl11(Float.parseFloat(dval[i]));
   	    	   }else if (i==18){
   	    		   pro.setRl12(Float.parseFloat(dval[i]));
   	    	   }else if (i==19){
   	    		   pro.setRl1(Float.parseFloat(dval[i]));
   	    	   }else if (i==20){
   	    		   pro.setRl2(Float.parseFloat(dval[i]));
   	    	   }else if (i==21){
   	    		   pro.setRl3(Float.parseFloat(dval[i]));
   	    	   }else if (i==22){
   	    		   pro.setRl4(Float.parseFloat(dval[i]));
   	    	   }else if (i==23){
   	    		   pro.setRl5(Float.parseFloat(dval[i]));
   	    	   }else if (i==24){
   	    		   pro.setRl6(Float.parseFloat(dval[i]));
   	    	   }else if (i==25){
   	    		   pro.setRl7(Float.parseFloat(dval[i]));
   	    	   }else if (i==26){
   	    		   pro.setRl8(Float.parseFloat(dval[i]));
   	    	   }else if (i==27){
   	    		   pro.setRl9(Float.parseFloat(dval[i]));
   	    	   }else if (i==28){
   	    		   pro.setTtemp(Integer.parseInt(dval[i]));
   	    	   }else if (i==29){
   	    		   pro.setMkt_year(Integer.parseInt(dval[i]));
   	    	   }
   	    	   
	    } 
	}
 
////////////////////////////////////////////////////////////////////////////////////////
	public static void Prodata(ProdFormBean pro, String[] dval)
	{
   	    for(int i=0;i<dval.length;i++)
	    {
   	    	if (i==0){
   	    		   pro.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   pro.setPr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   pro.setTr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   pro.setGrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==4){
   	    		   pro.setTmrp(Float.parseFloat(dval[i]));
   	    	   }else if (i==5){
   	    		    	  pro.setTa10(Integer.parseInt(dval[i]));
   	    	   }else if (i==6){
   	    		    	  pro.setTa11(Integer.parseInt(dval[i]));
   	    	   }else if (i==7){  
   	    		    	  pro.setTa12(Integer.parseInt(dval[i]));
   	    	   }else if (i==8){  
    		    	  pro.setTa1(Integer.parseInt(dval[i]));
   	    	   }else if (i==9){  
    		    	  pro.setTa2(Integer.parseInt(dval[i]));
   	    	   }else if (i==10){  
    		    	  pro.setTa3(Integer.parseInt(dval[i]));
   	    	   }else if (i==11){  
    		    	  pro.setTa4(Integer.parseInt(dval[i]));
   	    	   }else if (i==12){  
    		    	  pro.setTa5(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){  
    		    	  pro.setTa6(Integer.parseInt(dval[i]));
   	    	   }else if (i==14){  
    		    	  pro.setTa7(Integer.parseInt(dval[i]));
   	    	   }else if (i==15){  
    		    	  pro.setTa8(Integer.parseInt(dval[i]));
   	    	   }else if (i==16){  
    		    	  pro.setTa9(Integer.parseInt(dval[i]));
   	    	   }else if (i==17){
   	    		   pro.setTtarget(Integer.parseInt(dval[i]));
   	    	   }else if (i==18){
   	    		   pro.setTvalue(Float.parseFloat(dval[i]));
   	    	   }else if (i==19){
    		    	  pro.setRa10(Float.parseFloat(dval[i]));
	    	   }else if (i==20){
	    		    	  pro.setRa11(Float.parseFloat(dval[i]));
	    	   }else if (i==21){  
	    		    	  pro.setRa12(Float.parseFloat(dval[i]));
	    	   }else if (i==22){  
			    	  pro.setRa1(Float.parseFloat(dval[i]));
	    	   }else if (i==23){  
			    	  pro.setRa2(Float.parseFloat(dval[i]));
	    	   }else if (i==24){  
			    	  pro.setRa3(Float.parseFloat(dval[i]));
	    	   }else if (i==25){  
			    	  pro.setRa4(Float.parseFloat(dval[i]));
	    	   }else if (i==26){  
			    	  pro.setRa5(Float.parseFloat(dval[i]));
	    	   }else if (i==27){  
			    	  pro.setRa6(Float.parseFloat(dval[i]));
	    	   }else if (i==28){  
			    	  pro.setRa7(Float.parseFloat(dval[i]));
	    	   }else if (i==29){  
			    	  pro.setRa8(Float.parseFloat(dval[i]));
	    	   }else if (i==30){  
			    	  pro.setRa9(Float.parseFloat(dval[i]));
   	    	   }else if (i==31){
   	    		   pro.setTtemp(Integer.parseInt(dval[i]));
   	    	   }else if (i==32){
   	    		   pro.setMkt_year(Integer.parseInt(dval[i]));
   	    	   }else if (i==33){
   	    		   pro.setTnd(dval[i]);
   	    	   }
   	    	
	    }
	}
 
 
 
 
///////////////////////////////////////HQ DETAIL XMl Conversion/////////////////////////////////////////
 public int UpdateXmlHQDetail(String filepath,String br, Connection con){
	   int t=0;
	  try 
	  
	  {
	      System.out.println("yah hqt.xml xml reader mein aaya "+filepath+" br "+br);
	 
       //File file = new File("D:\\emp.xml");
	  
	  List<HQDetailFormBean> hqList = new ArrayList<HQDetailFormBean>();
	  String tempVal;
	  File file = new File(filepath);
//	  String fl = br+"HQT09";
	  String fl = br.substring(0, 1);
	  System.out.println(fl);

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName("hq");
	  System.out.println("Information of all Ter");
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  
		//List data = new ArrayList();
	   System.out.println(nodeLst.getLength());
	   HQDetailFormBean hq = null;
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	        tempVal= " ";
	  
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;
	           hq = new HQDetailFormBean();
	           int c = 0;
	               
	                NodeList depoLst = fstElmnt.getElementsByTagName("O"); //First Element
	                
	           	 if(depoLst.getLength()>0)
           		{	
		           Element depo = (Element) depoLst.item(0);
		           NodeList id = depo.getChildNodes();
		           //formbean mein data set
		           tempVal=((Node) id.item(0)).getNodeValue();
				   data(hq,tempVal.split( "\\|"),"O");
				   hq.setMonth("oct");
				   c++;
               	//formbean mein data set
           		} 

           		NodeList areacd = fstElmnt.getElementsByTagName("N"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            Element arcd = (Element) areacd.item(0);
		            NodeList ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    data(hq,tempVal.split( "\\|"),"N");
					hq.setMonth("nov");
               		c++;
           		}
	      
                 Element arcd;
                 NodeList ar;
                 
           		areacd = fstElmnt.getElementsByTagName("D"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"D");
					hq.setMonth("dec");
               		c++;
           		}
       
	            areacd = fstElmnt.getElementsByTagName("J"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"J");
	 			    hq.setMonth("jan");
               		c++;
           		}               

           		areacd = fstElmnt.getElementsByTagName("F"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"F");
					hq.setMonth("feb");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("M"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
		            data(hq,tempVal.split( "\\|"),"M");
					hq.setMonth("mar");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("A"); //Second Element
    
 
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"A");
					hq.setMonth("apr");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("Y"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"Y");
					hq.setMonth("may");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("E"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"E");
					hq.setMonth("jun");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("U"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"U");
					hq.setMonth("jul");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("G"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"G");
					hq.setMonth("aug");
               		c++;
           		}

           		areacd = fstElmnt.getElementsByTagName("S"); //Second Element
           		if(areacd.getLength()>0)
           		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
	 			    data(hq,tempVal.split( "\\|"),"S");
					hq.setMonth("sep");
               		c++;
           		} 
////================/////
                  
           			hq.setCount(c);
           			hqList.add(hq);
           			
//               HQDetailDAO hqdup = new SQLHQDetailDAO();
		    
//	            int i = hqdup.updateHQDetail(hqd,con);
	            //t=t+i;
		    
	              
	    }

	  } 
		   
		    
		System.out.println("size of hq list is "+hqList.size());
        SQLHQDetailDAO a = new SQLHQDetailDAO();
		t = a.updateHQDetail(fl,hqList,con);  

		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in ReadTimeXML.Connection.close "+e);
			}
		}	
	  
	  
	  
	  return t;
	  
 }


	public static void data(HQDetailFormBean hq, String[] dval, String typ)
	{
   	    for(int i=0;i<dval.length;i++)
	    {
   	    	if (i==0){
   	    		   hq.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   hq.setPr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   hq.setMpr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   hq.setPr_type(dval[i]);
   	    	   }else if (i==4){
   	    		   hq.setTr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==5){
   	    		   hq.setAr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==6){
   	    		   hq.setRg_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==7){
   	    		   hq.setMr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==8){
   	    		   hq.setGrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==9){
   	    		   hq.setMgrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==10){
   	    		      if (typ.equals("O"))
   	    		    	  hq.setQtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setQtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setQtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setQtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setQtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setQtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setQtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setQtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setQtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setQtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setQtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setQtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==11){
	    		      if (typ.equals("O"))
	    		    	  hq.setValoct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setValnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setValdec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setValjan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setValfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setValmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setValapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setValmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setValjun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setValjul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setValaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setValsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==12){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setFqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setFqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setFqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setFqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setFqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setFqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setFqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setFqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setFqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setFqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setFqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setFqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
	    		      if (typ.equals("O"))
	    		    	  hq.setFvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setFvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setFvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setFvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setFvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setFvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setFvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setFvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setFvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setFvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setFvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setFvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==14){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setEqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setEqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setEqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setEqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setEqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setEqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setEqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setEqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setEqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setEqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setEqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setEqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==15){
	    		      if (typ.equals("O"))
	    		    	  hq.setEvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setEvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setEvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setEvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setEvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setEvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setEvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setEvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setEvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setEvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setEvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setEvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==16){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setBqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setBqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setBqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setBqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setBqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setBqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setBqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setBqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setBqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setBqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setBqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setBqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==17){
	    		      if (typ.equals("O"))
	    		    	  hq.setBvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setBvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setBvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setBvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setBvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setBvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setBvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setBvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setBvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setBvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setBvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setBvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==18){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setRqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setRqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setRqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setRqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setRqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setRqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setRqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setRqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setRqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setRqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setRqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setRqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==19){
	    		      if (typ.equals("O"))
	    		    	  hq.setRvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setRvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setRvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setRvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setRvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setRvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setRvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setRvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setRvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setRvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setRvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setRvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==20){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setSqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setSqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setSqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setSqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setSqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setSqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setSqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setSqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setSqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setSqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setSqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setSqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==21){
	    		      if (typ.equals("O"))
	    		    	  hq.setSvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setSvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setSvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setSvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setSvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setSvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setSvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setSvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setSvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setSvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setSvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setSvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==22){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setPqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setPqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setPqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setPqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setPqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setPqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setPqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setPqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setPqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setPqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setPqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setPqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==23){
	    		      if (typ.equals("O"))
	    		    	  hq.setPvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setPvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setPvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setPvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setPvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setPvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setPvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setPvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setPvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setPvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setPvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setPvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==24){
   	    		   hq.setMkt_year(Integer.parseInt(dval[i]));  
   	    	   }
		
	    }
	}
 
 
 
//Stockiest XMl Conversion
 public int UpdateXmlStockiest(String filepath,String br, Connection con){
	   int t=0;
	  try 
	  
	  {
	      
	 
       //File file = new File("D:\\emp.xml");
		  List<StockiestFormBean> stckList = new ArrayList<StockiestFormBean>();
		  String tempVal;
	  
	  
     File file = new File(filepath);
//	  String fl = br+"STK09";
	  String fl = br.substring(0, 1);
	  System.out.println(fl);

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName("stk");
	  System.out.println("Information of all Ter");

	  
	  
	  System.out.println("Root element " + doc.getDocumentElement().getNodeName());

	  StockiestFormBean stck = null;
		//List data = new ArrayList();
	   System.out.println(nodeLst.getLength());
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;
	           stck = new StockiestFormBean();


	           int c = 0;
               
               NodeList depoLst = fstElmnt.getElementsByTagName("O"); //First Element
               
          	 if(depoLst.getLength()>0)
      		{	
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           //formbean mein data set
	           tempVal=((Node) id.item(0)).getNodeValue();
			   Stkdata(stck,tempVal.split( "\\|"),"O");
			   stck.setMonth("oct");
			   c++;
          	//formbean mein data set
      		} 

      		NodeList areacd = fstElmnt.getElementsByTagName("N"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"N");
				stck.setMonth("nov");
          		c++;
      		}
     
            Element arcd;
            NodeList ar;
            
      		areacd = fstElmnt.getElementsByTagName("D"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"D");
				stck.setMonth("dec");
          		c++;
      		}
  
           areacd = fstElmnt.getElementsByTagName("J"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"J");
			    stck.setMonth("jan");
          		c++;
      		}               

      		areacd = fstElmnt.getElementsByTagName("F"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"F");
				stck.setMonth("feb");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("M"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
	            Stkdata(stck,tempVal.split( "\\|"),"M");
				stck.setMonth("mar");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("A"); //Second Element


      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"A");
				stck.setMonth("apr");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("Y"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"Y");
				stck.setMonth("may");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("E"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"E");
				stck.setMonth("jun");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("U"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"U");
				stck.setMonth("jul");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("G"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"G");
				stck.setMonth("aug");
          		c++;
      		}

      		areacd = fstElmnt.getElementsByTagName("S"); //Second Element
      		if(areacd.getLength()>0)
      		{	
	            arcd = (Element) areacd.item(0);
	            ar = arcd.getChildNodes();
	            tempVal= ((Node) ar.item(0)).getNodeValue();
			    Stkdata(stck,tempVal.split( "\\|"),"S");
				stck.setMonth("sep");
          		c++;
      		} 
////================/////
             
      			stck.setCount(c);
      			stckList.add(stck);
	           
	           
	           
  
                
 //               SQLStockiestDAO stckup = new SQLStockiestDAO();
		    
//	            int i = stckup.updateStockiest(stck,datasource);
//	            t=t+i;
		    
	            
	    }

	  } 
		   
		   
        SQLStockiestDAO stckup = new SQLStockiestDAO();
        t = stckup.updateStockiest(fl,stckList, con);
	
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in ReadTimeXML.Connection.close "+e);
			}
		}	

	  return t;
	  
 }


/////////////////////////////////////////////////////////////////////////////////////////
	public static void Stkdata(StockiestFormBean hq, String[] dval, String typ)
	{
   	    for(int i=0;i<dval.length;i++)
	    {
   	    	if (i==0){
   	    		   hq.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   hq.setStk_code(dval[i]);
   	    	   }else if (i==2){
   	    		   hq.setPr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   hq.setMpr_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==4){
   	    		   hq.setPr_type(dval[i]);
   	    	   }else if (i==5){
   	    		   hq.setTr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==6){
   	    		   hq.setSt_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==7){
   	    		   hq.setAr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==8){
   	    		   hq.setRg_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==9){
   	    		   hq.setMr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==10){
   	    		   hq.setGrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==11){
   	    		   hq.setMgrp_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==12){
   	    		      if (typ.equals("O"))
   	    		    	  hq.setQtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setQtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setQtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setQtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setQtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setQtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setQtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setQtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setQtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setQtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setQtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setQtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
	    		      if (typ.equals("O"))
	    		    	  hq.setValoct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setValnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setValdec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setValjan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setValfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setValmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setValapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setValmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setValjun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setValjul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setValaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setValsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==14){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setFqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setFqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setFqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setFqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setFqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setFqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setFqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setFqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setFqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setFqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setFqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setFqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==15){
	    		      if (typ.equals("O"))
	    		    	  hq.setFvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setFvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setFvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setFvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setFvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setFvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setFvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setFvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setFvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setFvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setFvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setFvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==16){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setEqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setEqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setEqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setEqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setEqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setEqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setEqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setEqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setEqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setEqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setEqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setEqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==17){
	    		      if (typ.equals("O"))
	    		    	  hq.setEvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setEvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setEvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setEvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setEvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setEvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setEvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setEvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setEvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setEvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setEvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setEvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==18){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setBqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setBqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setBqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setBqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setBqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setBqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setBqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setBqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setBqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setBqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setBqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setBqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==19){
	    		      if (typ.equals("O"))
	    		    	  hq.setBvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setBvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setBvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setBvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setBvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setBvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setBvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setBvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setBvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setBvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setBvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setBvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==20){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setRqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setRqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setRqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setRqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setRqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setRqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setRqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setRqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setRqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setRqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setRqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setRqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==21){
	    		      if (typ.equals("O"))
	    		    	  hq.setRvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setRvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setRvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setRvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setRvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setRvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setRvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setRvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setRvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setRvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setRvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setRvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==22){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setSqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setSqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setSqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setSqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setSqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setSqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setSqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setSqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setSqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setSqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setSqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setSqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==23){
	    		      if (typ.equals("O"))
	    		    	  hq.setSvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setSvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setSvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setSvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setSvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setSvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setSvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setSvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setSvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setSvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setSvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setSvalsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==24){  
	    		      if (typ.equals("O"))
   	    		    	  hq.setPqtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  hq.setPqtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  hq.setPqtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  hq.setPqtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  hq.setPqtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  hq.setPqtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  hq.setPqtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  hq.setPqtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  hq.setPqtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  hq.setPqtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  hq.setPqtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  hq.setPqtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==25){
	    		      if (typ.equals("O"))
	    		    	  hq.setPvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  hq.setPvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  hq.setPvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  hq.setPvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  hq.setPvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  hq.setPvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  hq.setPvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  hq.setPvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  hq.setPvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  hq.setPvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  hq.setPvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  hq.setPvalsep(Float.parseFloat(dval[i]));
   	    	     } else if (i==26){
   	    		    hq.setMkt_year(Integer.parseInt(dval[i]));
   	    	       }
		
	    }
	}

 
 
///////////////////////////////////Stock XMl Conversion/////////////////////////////////////
	
   public int UpdateXmlStock(String filepath,String br, Connection con){
	  int t=0;
	  try 
	  
	  {
	  
	      List<StockFormBean> stockList = new ArrayList<StockFormBean>();
	      String tempVal;
		  
	      File file = new File(filepath);
		  String fl = br.substring(0, 1);
		  System.out.println(fl);
		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("inv");
		  StockFormBean stock = null;
		  //List data = new ArrayList();
			for (int s = 0; s < nodeLst.getLength(); s++)
			{

		        Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
		    {
		  
		           Element fstElmnt = (Element) fstNode;
		           stock = new StockFormBean();

		           int c = 0;
	               
	               NodeList depoLst = fstElmnt.getElementsByTagName("O"); //First Element
	               
	          	 if(depoLst.getLength()>0)
	      		{	
		           Element depo = (Element) depoLst.item(0);
		           NodeList id = depo.getChildNodes();
		           //formbean mein data set
		           tempVal=((Node) id.item(0)).getNodeValue();
				   Stockdata(stock,tempVal.split( "\\|"),"O");
				   stock.setMonth("oct");
				   c++;
	          	//formbean mein data set
	      		} 

	      		NodeList areacd = fstElmnt.getElementsByTagName("N"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            Element arcd = (Element) areacd.item(0);
		            NodeList ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"N");
					stock.setMonth("nov");
	          		c++;
	      		}
	     
	            Element arcd;
	            NodeList ar;
	            
	      		areacd = fstElmnt.getElementsByTagName("D"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"D");
					stock.setMonth("dec");
	          		c++;
	      		}
	  
	           areacd = fstElmnt.getElementsByTagName("J"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"J");
				    stock.setMonth("jan");
	          		c++;
	      		}               

	      		areacd = fstElmnt.getElementsByTagName("F"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"F");
					stock.setMonth("feb");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("M"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
		            Stockdata(stock,tempVal.split( "\\|"),"M");
					stock.setMonth("mar");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("A"); //Second Element


	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"A");
					stock.setMonth("apr");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("Y"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"Y");
					stock.setMonth("may");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("E"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"E");
					stock.setMonth("jun");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("U"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"U");
					stock.setMonth("jul");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("G"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"G");
					stock.setMonth("aug");
	          		c++;
	      		}

	      		areacd = fstElmnt.getElementsByTagName("S"); //Second Element
	      		if(areacd.getLength()>0)
	      		{	
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            tempVal= ((Node) ar.item(0)).getNodeValue();
				    Stockdata(stock,tempVal.split( "\\|"),"S");
					stock.setMonth("sep");
	          		c++;
	      		} 

	      			stock.setCount(c);
	      			stockList.add(stock);
	            
	    }

	  } 
		   
	        SQLStockDAO stckup = new SQLStockDAO();
	        t = stckup.updateStock(fl,stockList, con);
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in ReadTimeXML.Connection.close "+e);
			}
		}	

	  return t;
	  
 }

 
//////////////////////////////////////////////////////////////////////////////////////////
	public static void Stockdata(StockFormBean inv, String[] dval, String typ)
	{
   	    for(int i=0;i<dval.length;i++)
	    {
   	    	if (i==0){
   	    		   inv.setDepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   inv.setZm_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   inv.setDgm_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   inv.setDoc_type(Integer.parseInt(dval[i]));
   	    	   }else if (i==4){
   	    		   inv.setItem_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==5){
   	    		   inv.setItem_type(dval[i]);
   	    	   }else if (i==6){
   	    		   inv.setGrp(Integer.parseInt(dval[i]));
   	    	   }else if (i==7){
   	    		   inv.setMgrp(Integer.parseInt(dval[i]));
   	    	   }else if (i==8){
   	    		   inv.setDum_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==9){
   	    		      if (typ.equals("O"))
   	    		    	  inv.setOpngoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  inv.setOpngnov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  inv.setOpngdec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  inv.setOpngjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  inv.setOpngfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  inv.setOpngmar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  inv.setOpngapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  inv.setOpngmay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  inv.setOpngjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  inv.setOpngjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  inv.setOpngaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  inv.setOpngsep(Integer.parseInt(dval[i]));
   	    	   }else if (i==10){
	    		      if (typ.equals("O"))
	    		    	  inv.setClosoct(Integer.parseInt(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  inv.setClosnov(Integer.parseInt(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  inv.setClosdec(Integer.parseInt(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  inv.setClosjan(Integer.parseInt(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  inv.setClosfeb(Integer.parseInt(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  inv.setClosmar(Integer.parseInt(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  inv.setClosapr(Integer.parseInt(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  inv.setClosmay(Integer.parseInt(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  inv.setClosjun(Integer.parseInt(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  inv.setClosjul(Integer.parseInt(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  inv.setClosaug(Integer.parseInt(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  inv.setClossep(Integer.parseInt(dval[i]));
   	    	   }else if (i==11){  
	    		      if (typ.equals("O"))
   	    		    	  inv.setQtyoct(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("N"))
   	    		    	  inv.setQtynov(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("D"))
   	    		    	  inv.setQtydec(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("J"))
   	    		    	  inv.setQtyjan(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("F"))
   	    		    	  inv.setQtyfeb(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("M"))
   	    		    	  inv.setQtymar(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("A"))
   	    		    	  inv.setQtyapr(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("Y"))
   	    		    	  inv.setQtymay(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("E"))
   	    		    	  inv.setQtyjun(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("U"))
   	    		    	  inv.setQtyjul(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("G"))
   	    		    	  inv.setQtyaug(Integer.parseInt(dval[i]));
   	    		      if (typ.equals("S"))
   	    		    	  inv.setQtysep(Integer.parseInt(dval[i]));
   	    	   }else if (i==12){
	    		      if (typ.equals("O"))
	    		    	  inv.setFreeoct(Integer.parseInt(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  inv.setFreenov(Integer.parseInt(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  inv.setFreedec(Integer.parseInt(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  inv.setFreejan(Integer.parseInt(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  inv.setFreefeb(Integer.parseInt(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  inv.setFreemar(Integer.parseInt(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  inv.setFreeapr(Integer.parseInt(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  inv.setFreemay(Integer.parseInt(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  inv.setFreejun(Integer.parseInt(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  inv.setFreejul(Integer.parseInt(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  inv.setFreeaug(Integer.parseInt(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  inv.setFreesep(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
	    		      if (typ.equals("O"))
	    		    	  inv.setValoct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  inv.setValnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  inv.setValdec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  inv.setValjan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  inv.setValfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  inv.setValmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  inv.setValapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  inv.setValmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  inv.setValjun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  inv.setValjul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  inv.setValaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  inv.setValsep(Float.parseFloat(dval[i]));
   	    	   }else if (i==14){
	    		      if (typ.equals("O"))
	    		    	  inv.setFvaloct(Float.parseFloat(dval[i]));
	    		      if (typ.equals("N"))
	    		    	  inv.setFvalnov(Float.parseFloat(dval[i]));
	    		      if (typ.equals("D"))
	    		    	  inv.setFvaldec(Float.parseFloat(dval[i]));
	    		      if (typ.equals("J"))
	    		    	  inv.setFvaljan(Float.parseFloat(dval[i]));
	    		      if (typ.equals("F"))
	    		    	  inv.setFvalfeb(Float.parseFloat(dval[i]));
	    		      if (typ.equals("M"))
	    		    	  inv.setFvalmar(Float.parseFloat(dval[i]));
	    		      if (typ.equals("A"))
	    		    	  inv.setFvalapr(Float.parseFloat(dval[i]));
	    		      if (typ.equals("Y"))
	    		    	  inv.setFvalmay(Float.parseFloat(dval[i]));
	    		      if (typ.equals("E"))
	    		    	  inv.setFvaljun(Float.parseFloat(dval[i]));
	    		      if (typ.equals("U"))
	    		    	  inv.setFvaljul(Float.parseFloat(dval[i]));
	    		      if (typ.equals("G"))
	    		    	  inv.setFvalaug(Float.parseFloat(dval[i]));
	    		      if (typ.equals("S"))
	    		    	  inv.setFvalsep(Float.parseFloat(dval[i]));
	    		      
   	    	   }else if (i==15){
    		   inv.setMkt_year(Integer.parseInt(dval[i]));
    	       }
   	    	
	    }
	}
/////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////SAMPLE INVFST XMl Conversion///////////////////////////////////////////
	 public int UpdateXmlFstFl(String filepath,String br, Connection con){
	 int t=0;
	 try 
	  {
		  List<InvFstFormBean> Inv1List = new ArrayList<InvFstFormBean>();
		  String tempVal;
          File file = new File(filepath);
		  String f2 =br.substring(0, 1);
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("INV1");

		  InvFstFormBean invf = null;
		  for (int s = 0; s < nodeLst.getLength(); s++)
			{
		        Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
		    {
		           Element fstElmnt = (Element) fstNode;
	               invf = new InvFstFormBean();     
	               NodeList depoLst = fstElmnt.getElementsByTagName("I1"); //First Element
	               
	          	 if(depoLst.getLength()>0)
	      	  	 {	
		           Element depo = (Element) depoLst.item(0);
		           NodeList id = depo.getChildNodes();
		           tempVal=((Node) id.item(0)).getNodeValue();
				   Inv1data(invf,tempVal.split( "\\|"));
	      		} 
		   			Inv1List.add(invf);
		    }

		  } 
		SQLInvFstDAO inv1up = new SQLInvFstDAO();
		inv1up.updateFstFl(Inv1List, con,f2,invf.getDepo_code());
			
	  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
		finally {
			try {
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println(" Exception in SampleBatchXML.Connection.close "+e);
			}
		}	
	  return t;
		  
	 }

////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void Inv1data(InvFstFormBean inv, String[] dval) throws ParseException
	{
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
  	    for(int i=0;i<dval.length;i++)
	    {
  	    	    if (i==0){
  	    		   inv.setDepo_code(Integer.parseInt(dval[i]));
  	    	   }else if (i==1){
  	    		   inv.setDoc_type(Integer.parseInt(dval[i]));
  	    	   }else if (i==2){
  	    		   inv.setInv_no(Integer.parseInt(dval[i]));
  	    	   }else if (i==3){
  	    		 if (dval[i].charAt(0)!=' ')  
  	    		   inv.setInv_date(dateformat.parse(dval[i]));
  	    	   }else if (i==4){
  	    		   inv.setPinv_no((dval[i]));
  	    	   }else if (i==5){
    	    	if (dval[i].charAt(0)!=' ')   	    		   
  	    		   inv.setPinv_date(dateformat.parse(dval[i]));
  	    	   }else if (i==6){
  	    		   inv.setMtr_no((dval[i]));
  	    	   }else if (i==7){
     	    	if (dval[i].charAt(0)!=' ')   	    		   
  	    		   inv.setMtr_dt(dateformat.parse(dval[i]));
  	    	   }else if (i==8){
  	    		   inv.setBill_amt(Double.parseDouble(dval[i]));
  	    	   }
  	    	
	    }
	}


	 public int UpdateXmlFactory(String filepath,String br,Connection con){
		  int t=0;
		  try {
	      List<FactoryFormBean> FacList = new ArrayList<FactoryFormBean>();			  
		  File file = new File(filepath);
		  String fl ="row";
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  System.out.println("filoe name is "+file);
		  Document doc = db.parse(file);      
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName(fl);
		  DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		  DateFormat dateformat1 = new SimpleDateFormat("dd-MM-yyyy");
		  DateFormat dateformat2 = new SimpleDateFormat("MM-dd-yyyy");
		  DateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
		  FactoryFormBean fac = null;
			for (int s = 0; s < nodeLst.getLength(); s++)
			{
		        Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
		    {
		           Element fstElmnt = (Element) fstNode;
		           
		           NodeList facLst = fstElmnt.getElementsByTagName("DOCUMENTNO");
		           Element face = (Element) facLst.item(0);
		           NodeList id = face.getChildNodes();
		           if (((Node) id.item(0)).getNodeValue().equals("null"))
		        	   continue;
		           
		           fac = new FactoryFormBean();
		           fac.setDocumentno((((Node) id.item(0)).getNodeValue()));
		           
		           facLst = fstElmnt.getElementsByTagName("INVOICENO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setInvoiceno(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("PREPARATIONDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           try
		           {
		           fac.setPreparationdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
		           }
		           catch(Exception e)
		           {
			          try
			          {  
		        	  fac.setPreparationdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
			          }
			          catch (Exception e1)
			          {
	 			          try
				          {  
	 	 		          fac.setPreparationdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e2)
				          {
		 	 		      fac.setPreparationdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
				          }
			          }
		           }
		           
		           facLst = fstElmnt.getElementsByTagName("PREPARATIONHOURS");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setPreparationhours(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("PREPARATIONMIN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setPreparationmin(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("DESPATCHDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           
		           try
		           {
			       fac.setDespatchdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
		           }
		           catch(Exception e)
		           {
			          try
			          {  
				      fac.setDespatchdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
			          }
			          catch (Exception e1)
			          {
	 			          try
				          {  
			              fac.setDespatchdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e2)
				          {
					      fac.setDespatchdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
				          }
			          }
		           }
		           
		           facLst = fstElmnt.getElementsByTagName("DESPATCHHOURS");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDespatchhours(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("DESPATCHMIN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDespatchmin(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("TRANSPORTEID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTransporteid((((Node) id.item(0)).getNodeValue()));
		           
		           facLst = fstElmnt.getElementsByTagName("VEHICLENO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setVehicleno((((Node) id.item(0)).getNodeValue()));
		           
		           facLst = fstElmnt.getElementsByTagName("FREIGHTSTATUS");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setFreightstatus((((Node) id.item(0)).getNodeValue()));
		           
		           facLst = fstElmnt.getElementsByTagName("DCDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           
		           try
		           {
			           fac.setDcdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
		           }
		           catch(Exception e)
		           {
			          try
			          {  
				           fac.setDcdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
			          }
			          catch (Exception e1)
			          {
	 			          try
				          {  
	 				           fac.setDcdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e2)
				          {
					           fac.setDcdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
				          }
			          }
		           }
		           
		           facLst = fstElmnt.getElementsByTagName("DCTYPE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDctype(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("FOODYN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setFoodyn(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("MEDICINEYN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setMedicineyn(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
		           
		           facLst = fstElmnt.getElementsByTagName("BRANCHID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setBranchid((((Node) id.item(0)).getNodeValue()));
		           
		           facLst = fstElmnt.getElementsByTagName("LRNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setLrno((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("LRDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))	
		           {
			           try
			           {
				           fac.setLrdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
			           }
			           catch(Exception e)
			           {
				          try
				          {  
					           fac.setLrdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e1)
				          {
		 			          try
					          {  
		 				           fac.setLrdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
					          }
					          catch (Exception e2)
					          {
						           fac.setLrdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
					          }
				          }
			           }		        	   
		           }
		           
		           facLst = fstElmnt.getElementsByTagName("FORMNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))
		           fac.setFormno(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("COMMINVOICEYN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setComminvoiceyn(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("COMMINVOICENO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setComminvoiceno(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("REMARKS");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setRemarks((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("LOCATIONID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setLocationid((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("COMPANYID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setCompanyid((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("SALESTAXPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setSalestaxpercent(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("OTHERTAXPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setOthertaxpercent(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("BOXES");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setBoxes(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("WEIGHT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setWeight(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("VALUE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setValue(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("CONTROLSAMPLEYN");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setControlsampleyn((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("ROADPERMNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setRoadpermno((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("EXPORTED");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setExported((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("PRODUCTID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setProductid((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("UNITID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setUnitid((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("UNIT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setUnit(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("BATCHNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setBatchno((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("BATCHSIZE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setBatchsize(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("MFGDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           try
		           {
		        	   fac.setMfgdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
		           }
		           catch(Exception e)
		           {
			          try
			          {  
			        	  fac.setMfgdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
			          }
			          catch (Exception e1)
			          {
	 			          try
				          {  
	 			        	 fac.setMfgdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e2)
				          {
				        	  fac.setMfgdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
				          }
			          }
		           }				           
		           
		           facLst = fstElmnt.getElementsByTagName("EXPDATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           try
		           {
		        	   fac.setExpdate(dateformat.parse((((Node) id.item(0)).getNodeValue())));
		           }
		           catch(Exception e)
		           {
			          try
			          {  
			        	  fac.setExpdate(dateformat1.parse((((Node) id.item(0)).getNodeValue())));
			          }
			          catch (Exception e1)
			          {
	 			          try
				          {  
	 			        	 fac.setExpdate(dateformat2.parse((((Node) id.item(0)).getNodeValue())));
				          }
				          catch (Exception e2)
				          {
				        	  fac.setExpdate(dateformat3.parse((((Node) id.item(0)).getNodeValue())));
				          }
			          }
		           }			           
		           
		           facLst = fstElmnt.getElementsByTagName("LOTID");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setLotid((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("PERCASEQTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setPercaseqty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("NOOFCASE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setNoofcase(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("QUANTITY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setQuantity(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("DETAILREMARKS");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDetailremarks((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("ARNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setArno((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("GROSSWEIGHT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setGrossweight(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("SLIPNO");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setSlipno((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("PERCASELOOSEQTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setPercaselooseqty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("NOOFLOOSECASE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setNoofloosecase(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("LOOSEQUANTITY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))		           
		           fac.setLoosequantity(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("TOTALQTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTotalqty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("MRP");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setMrp(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("STOCKISTINCLUSIVEEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setStockistinclusiveexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("STOCKISTEXCLUSIVEEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setStockistexclusiveexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("INVVALUE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setInvvalue(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("DISCOUNTAMT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDiscountamt(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("DISCOUNTQTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setDiscountqty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("FREEQTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setFreeqty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("MRPINCLUSIVEEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setMrpinclusiveexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("MRPEXCLUSIVEEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setMrpexclusiveexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("RETAILINCLEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setRetailinclexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("RETAILEXCLEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setRetailexclexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("RETAILDISCOUNT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setRetaildiscount(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("COMMINCLEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setComminclexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("COMMENCLEXCISE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setCommenclexcise(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("EXCISEPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setExcisepercent(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("ABATEMENTPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setAbatementpercent(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("ASSESSABLEVALUE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setAssessablevalue(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("TOTALASSESSABLEVALUE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTotalassessablevalue(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("TOTALEXCISEDUTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTotalexciseduty(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("TOTALQUANTITY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTotalquantity(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("ABATEMENTAMOUNT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setAbatementamount(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("MRPRATEPERUNIT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setMrprateperunit(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("DCI_PRL_FREE_QTY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))
		           fac.setDci_prl_free_qty(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("INVOICECESSRATE");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setInvoicecessrate(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("TOTALINVOICECESSPAY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setTotalinvoicecesspay(Double.parseDouble((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("INVPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           if (!((Node) id.item(0)).getNodeValue().equals("null"))
		           fac.setInvpercent(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("CESSPERCENT");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setCesspercent(Integer.parseInt((((Node) id.item(0)).getNodeValue())));

		           facLst = fstElmnt.getElementsByTagName("LOCATION");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setLocation((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("COMPANY");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setCompany((((Node) id.item(0)).getNodeValue()));

		           facLst = fstElmnt.getElementsByTagName("PRODUCTNAME");
		           face = (Element) facLst.item(0);
		           id = face.getChildNodes();
		           fac.setProductname((((Node) id.item(0)).getNodeValue()));
		     
		           FacList.add(fac);		           
		    } //if ka close
		  }  //for ka close
			
			SQLFactoryDAO facupdate = new SQLFactoryDAO();
			facupdate.updateFactory(FacList, con);			
			
			
		  } catch (Exception e) 
		   {
		    e.printStackTrace();  
		   }
			finally {
				try {
			        if(con != null){con.close();
			        }
				} catch (SQLException e) {
					System.out.println(" Exception in Factory XMLReader.Connection.close "+e);
				}
			}	
		  return t;
			  
		 }	
	
	
	
	
}  
