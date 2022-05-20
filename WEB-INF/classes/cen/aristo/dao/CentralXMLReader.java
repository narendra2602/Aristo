package cen.aristo.dao;
import java.io.File;
import java.sql.Connection;
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

 
import cen.aristo.valueobject.AccountMasterFormBean;
import cen.aristo.valueobject.BatchMasterFormBean;
import cen.aristo.valueobject.DestinationMFormBean;
import cen.aristo.valueobject.InvFstFormBean;
import cen.aristo.valueobject.InvSndFormBean;
import cen.aristo.valueobject.ProductOpngFormBean;
import cen.aristo.valueobject.TransportMFormBean;

public class CentralXMLReader {

    private static String ymonth=null;
    
 public static void UpdateXmlAccount1(String filepath,String br,Connection con){

	  try {
		  
		  List<AccountMasterFormBean> AccList = new ArrayList<AccountMasterFormBean>();
	      File file = new File(filepath);

		  String fl = "party";
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName(fl);
		  AccountMasterFormBean account = null;
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	           Element fstElmnt = (Element) fstNode;
	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           account = new AccountMasterFormBean();
	           account.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	           
	            NodeList areacd = fstElmnt.getElementsByTagName("MCMP_CODE"); //Second Element
	            Element arcd = (Element) areacd.item(0);
	            NodeList ar = arcd.getChildNodes();
	            account.setMcmp_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	      
	            
                NodeList areanm = fstElmnt.getElementsByTagName("MUSR_CD"); //Third Element
	            Element arnm = (Element) areanm.item(0);
	            NodeList arn = arnm.getChildNodes();
	            account.setMusr_cd(Integer.parseInt(((Node) arn.item(0)).getNodeValue()));
	      

	            NodeList typ = fstElmnt.getElementsByTagName("MGRP_CODE"); // 4th Element
	            Element typel = (Element) typ.item(0);
	            NodeList ty = typel.getChildNodes();
	            account.setMgrp_code(Integer.parseInt(((Node) ty.item(0)).getNodeValue()));
	  	      

	            NodeList mac = fstElmnt.getElementsByTagName("MAC_CODE"); // 4th Element
	            Element macc = (Element) mac.item(0);
	            NodeList ma = macc.getChildNodes();
	            account.setMac_code((((Node)  ma .item(0)).getNodeValue()));
	  	      

	            NodeList mac1 = fstElmnt.getElementsByTagName("MAC_TYPE"); // 4th Element
	            Element macc1 = (Element)  mac1.item(0);
	            NodeList ma1 = macc1.getChildNodes();
	            account.setMac_type((((Node) ma1.item(0)).getNodeValue()));
	  	      
	            NodeList mac2 = fstElmnt.getElementsByTagName("MAC_PRFX"); // 4th Element
	            Element macc2 = (Element) mac2.item(0);
	            NodeList ma2 = macc2.getChildNodes();
	            account.setMac_prfx((((Node) ma2.item(0)).getNodeValue()));
		  	      
	            NodeList mac3 = fstElmnt.getElementsByTagName("MAC_NAME"); // 4th Element
	            Element macc3 = (Element) mac3.item(0);
	            NodeList ma3 = macc3.getChildNodes();
	            account.setMac_name((((Node) ma3.item(0)).getNodeValue()));
		  	      
	            NodeList mac4 = fstElmnt.getElementsByTagName("MADD1"); // 4th Element
	            Element macc4 = (Element) mac4.item(0);
	            NodeList ma4 = macc4.getChildNodes();
	            account.setMadd1((((Node) ma4.item(0)).getNodeValue()));
		  	      
	            NodeList mac5 = fstElmnt.getElementsByTagName("MADD2"); // 4th Element
	            Element macc5 = (Element) mac5.item(0);
	            NodeList ma5 = macc5.getChildNodes();
	            account.setMadd2((((Node) ma5.item(0)).getNodeValue()));
		  	      
	            NodeList mac6 = fstElmnt.getElementsByTagName("MADD3"); // 4th Element
	            Element macc6 = (Element) mac6.item(0);
	            NodeList ma6 = macc6.getChildNodes();
	            account.setMadd3((((Node) ma6.item(0)).getNodeValue()));
		  	      
	            NodeList mac7 = fstElmnt.getElementsByTagName("MCITY"); // 4th Element
	            Element macc7 = (Element) mac7.item(0);
	            NodeList ma7 = macc7.getChildNodes();
	            account.setMcity((((Node) ma7.item(0)).getNodeValue()));
		  	      
	            NodeList mac8 = fstElmnt.getElementsByTagName("MPIN"); // 4th Element
	            Element macc8 = (Element) mac8.item(0);
	            NodeList ma8 = macc8.getChildNodes();
	            account.setMpin((((Node) ma8.item(0)).getNodeValue()));
		  	      
	            NodeList mac9 = fstElmnt.getElementsByTagName("MPHONE"); // 4th Element
	            Element macc9 = (Element) mac9.item(0);
	            NodeList ma9 = macc9.getChildNodes();
	            account.setMphone((((Node) ma9.item(0)).getNodeValue()));
		  	      
	            NodeList mac10 = fstElmnt.getElementsByTagName("MCONTCT"); // 4th Element
	            Element macc10 = (Element) mac10.item(0);
	            NodeList ma10 = macc10.getChildNodes();
	            account.setMphone((((Node) ma10.item(0)).getNodeValue()));
		  	      
	            NodeList mac11 = fstElmnt.getElementsByTagName("MDLNO1"); // 4th Element
	            Element macc11 = (Element)mac11.item(0);
	            NodeList ma11 = macc11.getChildNodes();
	            account.setMdlno1((((Node)  ma11.item(0)).getNodeValue()));
		  	      
	            NodeList mac12 = fstElmnt.getElementsByTagName("MDLNO2"); // 4th Element
	            Element macc12 = (Element) mac12.item(0);
	            NodeList ma12 = macc12.getChildNodes();
	            account.setMdlno2((((Node) ma12.item(0)).getNodeValue()));
		  	      
	            NodeList mac13 = fstElmnt.getElementsByTagName("MTRANSPT"); // 4th Element
	            Element macc13 = (Element) mac13.item(0);
	            NodeList ma13 = macc13.getChildNodes();
	            account.setMtranspt((((Node) ma13.item(0)).getNodeValue()));
		  	      
	            NodeList mac14 = fstElmnt.getElementsByTagName("MPST_NO"); // 4th Element
	            Element macc14 = (Element) mac14.item(0);
	            NodeList ma14 = macc14.getChildNodes();
	            account.setMdlno2((((Node) ma14.item(0)).getNodeValue()));
		  	      
	            NodeList mac15 = fstElmnt.getElementsByTagName("MCST_NO"); // 4th Element
	            Element macc15 = (Element) mac15.item(0);
	            NodeList ma15 = macc15.getChildNodes();
	            account.setMcst_no((((Node) ma15.item(0)).getNodeValue()));
	            
	            AccList.add(account);
	            
	    }		/////////////// If End////////////////
	  }       ///////////For End///////////////// 
		  
	      SQLAccountDAO accup = new SQLAccountDAO();
	      accup.updateAccount(AccList, con, br);
		
	  } catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
   }

 
 
/////////////////////////////DESTINATION Master XMl Conversion//////////////////////////////////
 
 public static void UpdateXmlDSTMST(String filepath,String br,Connection con){

	  try {
	  File file = new File(filepath);
	  List<DestinationMFormBean> DesList = new ArrayList<DestinationMFormBean>();
	  
	  String fl = "DSTMST";
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  NodeList nodeLst = doc.getElementsByTagName(fl);

	  DestinationMFormBean dst = null;
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{

	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	  
	           Element fstElmnt = (Element) fstNode;

	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           dst= new DestinationMFormBean();
	           dst.setDepo_code(Integer.parseInt((((Node) id.item(0)).getNodeValue())));
	           
	           NodeList areacd = fstElmnt.getElementsByTagName("DST_CODE"); //Second Element
	           Element arcd = (Element) areacd.item(0);
	           NodeList ar = arcd.getChildNodes();
	           dst.setDst_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	      	            
               NodeList areanm = fstElmnt.getElementsByTagName("DST_NAME"); //Third Element
	           Element arnm = (Element) areanm.item(0);
	           NodeList arn = arnm.getChildNodes();
	           dst.setDst_name((((Node) arn.item(0)).getNodeValue()));
	                  
	           areacd = fstElmnt.getElementsByTagName("DISTANCE"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           dst.setDistance(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
               
	           DesList.add(dst);
	             
	    }	   ////////////////If End////////////////////

	  }       ///////////For End /////////////////////// 
		  
		      SQLDestinationDAO desup = new SQLDestinationDAO();
		      desup.updateDestn(DesList, con,br);
	
		
	  } 
	  catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
  }

 
 
 
 /////////////////////////////////////////////////////////////////////////////////////////////////////
//TRANSPORT Master XMl Conversion
 public static int UpdateXmlTrnMst(String filepath,String br,Connection con){
	   int t=0;

	  try 
	  
	  {
	  List<TransportMFormBean> TrnList = new ArrayList<TransportMFormBean>();
      File file = new File(filepath);
	  String fl ="TRNMST";  

	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  NodeList nodeLst = doc.getElementsByTagName(fl);

	  TransportMFormBean trn = null;
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	           Element fstElmnt = (Element) fstNode;

	           NodeList depoLst = fstElmnt.getElementsByTagName("DEPO_CODE"); //First Element
	           Element depo = (Element) depoLst.item(0);
	           NodeList id = depo.getChildNodes();
	           trn = new TransportMFormBean();
	           trn.setDepo_code(Integer.parseInt(((Node) id.item(0)).getNodeValue()));
	           
	           NodeList areacd = fstElmnt.getElementsByTagName("TR_CODE"); //Second Element
	           Element arcd = (Element) areacd.item(0);
	           NodeList ar = arcd.getChildNodes();
               trn.setTr_code(Integer.parseInt(((Node) ar.item(0)).getNodeValue()));
	      
               areacd = fstElmnt.getElementsByTagName("TR_NAME"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
               trn.setTr_name(((Node) ar.item(0)).getNodeValue());
                
               areacd = fstElmnt.getElementsByTagName("TR_ADD1"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
               trn.setTr_add1(((Node) ar.item(0)).getNodeValue());

               areacd = fstElmnt.getElementsByTagName("TR_ADD2"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
               trn.setTr_add2(((Node) ar.item(0)).getNodeValue());

               areacd = fstElmnt.getElementsByTagName("TR_CITY"); //Second Element
	           arcd = (Element) areacd.item(0);
		       ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
	          	   trn.setTr_city(((Node) ar.item(0)).getNodeValue());
               
               areacd = fstElmnt.getElementsByTagName("TR_PHONE"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
               trn.setTelphone_no(((Node) ar.item(0)).getNodeValue());
                
               areacd = fstElmnt.getElementsByTagName("TR_PERSON"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
               trn.setTr_person(((Node) ar.item(0)).getNodeValue());
               
               areacd = fstElmnt.getElementsByTagName("TIN_NO"); //Second Element
	           arcd = (Element) areacd.item(0);
	           ar = arcd.getChildNodes();
	           if(ar.getLength()>0)
	           trn.setTin_no(((Node) ar.item(0)).getNodeValue());

	           TrnList.add(trn);
	    }
} 
			SQLTransportDAO trnup = new SQLTransportDAO();
			trnup.updateTransport(TrnList, con,br);
		   
		
		 
	  } catch (Exception e) 
	   {
	    e.printStackTrace();
	   }
	  
	  return t;
	  
  }


//////////////////////////////////////INVSND XMl Conversion///////////////////////////////////////////
 public int UpdateXmlInvSnd(String filepath,String br, Connection con){
	   int t=0;
	  try 
	   
	  {
		  List<InvSndFormBean> Inv2List = new ArrayList<InvSndFormBean>();
		  String tempVal;
		  File file = new File(filepath);
     
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("INV2");
		  
		  InvSndFormBean invs = null;
	   
		for (int s = 0; s < nodeLst.getLength(); s++)
		{
	        Node fstNode = nodeLst.item(s);
	    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
			    {
			  
			       Element fstElmnt = (Element) fstNode;
		           invs = new InvSndFormBean();     
	               NodeList depoLst = fstElmnt.getElementsByTagName("I2"); //First Element
		               
		          	 if(depoLst.getLength()>0)
			      		{	
				           Element depo = (Element) depoLst.item(0);
				           NodeList id = depo.getChildNodes();
				           tempVal=((Node) id.item(0)).getNodeValue();
						   Inv2data(invs,tempVal.split( "\\|"));
			      		} 
			   			Inv2List.add(invs);
			    }

	  } 
					SQLInvSndDAO inv2up = new SQLInvSndDAO();
					inv2up.updateInvSnd(Inv2List, con,br,ymonth);
				
			  } catch (Exception e) 
	   {
	    e.printStackTrace();  
	   }
	  
	  return t;
	  
 }

////////////////////////////////////////////////////////////////////////////////////////
	public static void Inv2data(InvSndFormBean inv, String[] dval) throws ParseException
	{
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
   	    
		
		for(int i=0;i<dval.length;i++)
	    {
   	    	if (i==0){
   	    		   inv.setSdepo_code(Integer.parseInt(dval[i]));
   	    	   }else if (i==1){
   	    		   inv.setSdoc_type(Integer.parseInt(dval[i]));
   	    	   }else if (i==2){
   	    		   inv.setSinv_no(Integer.parseInt(dval[i]));
   	    	   }else if (i==3){
   	    		   inv.setSinv_lo((dval[i]));
   	    	   }else if (i==4){
   	    		   inv.setSinv_yr(Integer.parseInt(dval[i]));
   	    	   }else if (i==5){
	   	    	   if (dval[i].charAt(0)!=' ')
   	    		   inv.setSinv_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==6){
   	    		   inv.setSpinv_no((dval[i]));
   	    	   }else if (i==7){
	   	    	   if (dval[i].charAt(0)!=' ')   	    		   
   	    		   inv.setSpinv_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==8){
	   	    	   if (dval[i].charAt(0)!=' ')   	    		   
   	    		   inv.setSentry_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==9){
   	    		   inv.setStrn_tp((dval[i]));
   	    	   }else if (i==10){
   	    		   inv.setSprt_cd((dval[i]));
   	    	   }else if (i==11){
   	    		   inv.setSprd_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==12){
   	    		   inv.setSpd_gp(Integer.parseInt(dval[i]));
   	    	   }else if (i==13){
   	    		   inv.setSale_type(Integer.parseInt(dval[i]));
   	    	   }else if (i==14){
   	    		   inv.setSbatch_no(dval[i]);
   	    	   }else if (i==15){
   	    		   inv.setSmfg_dt(dval[i]);
   	    	   }else if (i==16){
   	    		   inv.setSexp_dt(dval[i]);
   	    	   }else if (i==17){
   	    		   inv.setSqty(Integer.parseInt(dval[i]));
  	    	   }else if (i==18){
   	    		   inv.setSfree_qty(Integer.parseInt(dval[i]));
   	    	   }else if (i==19){
   	    		   inv.setStax_cd1(Double.parseDouble(dval[i]));
   	    	   }else if (i==20){
   	    		   inv.setStax_cd2(Double.parseDouble(dval[i]));
   	    	   }else if (i==21){
   	    		   inv.setOct_type((dval[i]));
   	    	   }else if (i==22){
   	    		   inv.setOctroi(Double.parseDouble(dval[i]));
   	    	   }else if (i==23){
   	    		   inv.setSrate_net(Double.parseDouble(dval[i]));
   	    	   }else if (i==24){
   	    		   inv.setSrate_pur(Double.parseDouble(dval[i]));
   	    	   }else if (i==25){
   	    		   inv.setSrate_trd(Double.parseDouble(dval[i]));
   	    	   }else if (i==26){
   	    		   inv.setSrate_mrp(Double.parseDouble(dval[i]));
   	    	   }else if (i==27){
   	    		   inv.setSrate_hos(Double.parseDouble(dval[i]));
   	    	   }else if (i==28){
   	    		   inv.setSrate_mfg(Double.parseDouble(dval[i]));
   	    	   }else if (i==29){
   	    		   inv.setSrate_exc(Double.parseDouble(dval[i]));
   	    	   }else if (i==30){
   	    		   inv.setSamnt(Double.parseDouble(dval[i]));
   	    	   }else if (i==31){
   	    		   inv.setSdel_tg(dval[i]);
   	    	   }else if (i==32){
   	    		   inv.setSmr_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==33){
   	    		   inv.setStat_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==34){
   	    		   inv.setStax_type(dval[i]);
   	    	   }else if (i==35){
   	    		   inv.setSecess(Double.parseDouble(dval[i]));
   	    	   }else if (i==36){
   	    		   inv.setSrate_net1(Double.parseDouble(dval[i]));
   	    	   }else if (i==37){
   	    		   inv.setSrate_trd1(Double.parseDouble(dval[i]));
   	    	   }else if (i==38){
   	    		   inv.setSrate_mrp1(Double.parseDouble(dval[i]));
   	    	   }else if (i==39){
   	    		   inv.setActual_wet(Double.parseDouble(dval[i]));
   	    	   }else if (i==40){
	    		   inv.setSkno(Integer.parseInt(dval[i]));   	    		   
   	    	   }else if (i==41){
	    		   inv.setYmonth(dval[i]); 
	    		   ymonth=dval[i];
   	    	   } 		

   	    	
	    }
	}
  
 
////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////INVFST XMl Conversion///////////////////////////////////////////
	 public int UpdateXmlInvFst(String filepath,String br, Connection con){
		   int t=0;
		  try 
		   
		  {
			  List<InvFstFormBean> Inv1List = new ArrayList<InvFstFormBean>();
			  String tempVal;
	          File file = new File(filepath);
	     
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
			inv1up.updateInvFst(Inv1List, con,br);
			
			
		  } catch (Exception e) 
		   {
		    e.printStackTrace();  
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
   	    		   inv.setParty_code((dval[i]));
   	    	   }else if (i==3){
   	    		   inv.setInv_no(Integer.parseInt(dval[i]));
   	    	   }else if (i==4){
   	    		   inv.setInv_lo((dval[i]));
   	    	   }else if (i==5){
   	    		   inv.setInv_yr(Integer.parseInt(dval[i]));
   	    	   }else if (i==6){
   	    		 if (dval[i].charAt(0)!=' ')  
   	    		   inv.setInv_date(dateformat.parse(dval[i]));
   	    	   }else if (i==7){
   	    		   inv.setPinv_no((dval[i]));
   	    	   }else if (i==8){
     	    	if (dval[i].charAt(0)!=' ')   	    		   
   	    		   inv.setPinv_date(dateformat.parse(dval[i]));
   	    	   }else if (i==9){
     	    	if (dval[i].charAt(0)!=' ')   	    		  
   	    		   inv.setEntry_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==10){
   	    		   inv.setChl_no((dval[i]));
   	    	   }else if (i==11){
   	    		if (dval[i].charAt(0)!=' ')   	    		 
   	    		   inv.setChl_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==12){
   	    		   inv.setMtr_no((dval[i]));
   	    	   }else if (i==13){
      	    	if (dval[i].charAt(0)!=' ')   	    		   
   	    		   inv.setMtr_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==14){
   	    		   inv.setCases(Integer.parseInt(dval[i]));
   	    	   }else if (i==15){
   	    		   inv.setDue_days(Integer.parseInt(dval[i]));
   	    	   }else if (i==16){
   	    		if (dval[i].charAt(0)!=' ')   	    		 
   	    		   inv.setDue_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==17){
   	    		   inv.setTransport(dval[i]);
   	    	   }else if (i==18){
   	    		   inv.setBank(dval[i]);
   	    	   }else if (i==19){
   	    		   inv.setDrug_lc1(dval[i]);
   	    	   }else if (i==20){
   	    		   inv.setDrug_lc2(dval[i]);
   	    	   }else if (i==21){
   	    		   inv.setGross_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==22){
   	    		   inv.setTax_1(Double.parseDouble(dval[i]));
   	    	   }else if (i==23){
   	    		   inv.setTax_2(Double.parseDouble(dval[i]));
   	    	   }else if (i==24){
   	    		   inv.setStat_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==25){
   	    		   inv.setBill_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==26){
   	    		   inv.setLsale1(Double.parseDouble(dval[i]));
   	    	   }else if (i==27){
   	    		   inv.setLsale2(Double.parseDouble(dval[i]));
   	    	   }else if (i==28){
   	    		   inv.setLsale3(Double.parseDouble(dval[i]));
   	    	   }else if (i==29){
   	    		   inv.setLtax1_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==30){
   	    		   inv.setLtax1_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==31){
   	    		   inv.setLtax2_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==32){
   	    		   inv.setLtax2_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==33){
   	    		   inv.setLtax3_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==34){
   	    		   inv.setLtax3_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==35){
   	    		   inv.setCtax1_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==36){
   	    		   inv.setCtax1_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==37){
   	    		   inv.setCtax2_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==38){
   	    		   inv.setCtax2_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==39){
   	    		   inv.setCtax3_per(Double.parseDouble(dval[i]));
   	    	   }else if (i==40){
   	    		   inv.setCtax3_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==41){
   	    		   inv.setOctroi(Double.parseDouble(dval[i]));
   	    	   }else if (i==42){
   	    		   inv.setMfg_amt(Double.parseDouble(dval[i]));
   	    	   }else if (i==43){
   	    		   inv.setInv_type((dval[i]));
   	    	   }else if (i==44){
   	    		   inv.setMr_mfg(Double.parseDouble(dval[i]));
   	    	   }else if (i==45){
   	    		   inv.setVehicle_no((dval[i]));
   	    	   }else if (i==46){
   	    		   inv.setPermit_no((dval[i]));
   	    	   }else if (i==47){
      	    	if (dval[i].charAt(0)!=' ')
   	    		   inv.setPermit_dt(dateformat.parse(dval[i]));
   	    	   }else if (i==48){
   	    		   inv.setTrn_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==49){
   	    		   inv.setFull_truck(Double.parseDouble(dval[i]));
   	    	   }else if (i==50){
   	    		   inv.setDcm_truck(Double.parseDouble(dval[i]));
   	    	   }else if (i==51){
   	    		   inv.setRate_perkg(Double.parseDouble(dval[i]));
   	    	   }else if (i==52){
   	    		   inv.setLoad_typ(dval[i]);
   	    	   }else if (i==53){
   	    		   inv.setTdst_cd(Integer.parseInt(dval[i]));
   	    	   }else if (i==54){
   	    		   inv.setTot_weight(Double.parseDouble(dval[i]));
   	    	   }else if (i==55){
   	    		   inv.setActual_wet(Double.parseDouble(dval[i]));
   	    	   }
   	    	
	    }
	}
   
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////BATMST XMl Conversion///////////////////////////////////////////
	 public int UpdateXmlBatMst(String filepath,String br, Connection con){
		   int t=0;
		  try 
		   
		  {
		      
		 
	       //File file = new File("D:\\emp.xml");
			  List<BatchMasterFormBean> BatList = new ArrayList<BatchMasterFormBean>();
			  String tempVal;
	  
		 
	     File file = new File(filepath);
//		  String fl = br+"PRO09";

	     
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("batch");

		  BatchMasterFormBean bat = null;
			//List data = new ArrayList();
		   
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
		           //formbean mein data set
		           tempVal=((Node) id.item(0)).getNodeValue();
				   Batdata(bat,tempVal.split( "\\|"));
	          	//formbean mein Prodata set
	      		} 

		           
		   			BatList.add(bat);
			    
//		            int i = produp.updateProd(pro,con);
//		            t=t+i;
			    
		            
		    }

		  } 
			   
			
				SQLBatchDAO batup = new SQLBatchDAO();
				batup.updatebatch(BatList, con,br);
			
			
		  } catch (Exception e) 
		   {
		    e.printStackTrace();  
		   }
		  
		  return t;
		  
	 }

////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void Batdata(BatchMasterFormBean bat, String[] dval) throws ParseException
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
	   	    		   bat.setBat_netrt1(Double.parseDouble(dval[i]));
	   	    	   }else if (i==7){
	   	    		   bat.setBat_trdrt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==8){
	   	    		   bat.setBat_trdrt1(Double.parseDouble(dval[i]));
	   	    	   }else if (i==9){
	   	    		   bat.setBat_mrprt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==10){
	   	    		   bat.setBat_mrprt1(Double.parseDouble(dval[i]));
	   	    	   }else if (i==11){
	   	    		   bat.setBat_mfgrt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==12){
	   	    		   bat.setBat_hosrt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==13){
	   	    		   bat.setBat_excrt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==14){
	   	    		   bat.setBat_indct((dval[i]));
	   	    	   }else if (i==15){
	   	    		   bat.setBat_opng(Integer.parseInt(dval[i]));
	   	    	   }else if (i==16){
	   	    		   bat.setBat_clos(Integer.parseInt(dval[i]));
	   	    	   }else if (i==17){
	   	    		   bat.setBat_purrt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==18){
	   	    		   bat.setBat_clos2(Integer.parseInt(dval[i]));
	   	    	   }else if (i==19){
	   	    		   bat.setBat_tag((dval[i]));
	   	    	   }else if (i==20){
	   	    		   bat.setSlct((dval[i]));
	   	    	   }else if (i==21){
	   	    		   bat.setEcess(Double.parseDouble(dval[i]));
	   	    	   }else if (i==22){
		   	    		   bat.setWeight_box(Double.parseDouble(dval[i]));
	   	    	   }else if (i==23){
		   	    	 if (dval[i].charAt(0)!=' ')  
	   	    		   bat.setBat_recdt(dateformat.parse(dval[i]));
	   	    	   }else if (i==24){
	   	    		   bat.setCase_size(Integer.parseInt(dval[i]));
	   	    	   }else if (i==25){
	   	    		   bat.setBox(Integer.parseInt(dval[i]));
	   	    	   }else if (i==26){
	   	    		 if (dval[i].charAt(0)!=' ')  
	   	    		   bat.setFirst_issu(dateformat.parse(dval[i]));
	   	    	   }else if (i==27){
	   	    		 if (dval[i].charAt(0)!=' ')  
	   	    		   bat.setLast_issu(dateformat.parse(dval[i]));
	   	    	   }else if (i==28){
	   	    		 if (dval[i].charAt(0)!=' ')  
		   	    		   bat.setExpiry(dateformat.parse(dval[i]));
		   	       } 	   	    	
		    }
		}

		
//////////////////////////////////////PRDOPNG XMl Conversion///////////////////////////////////////////
		 public int UpdateXmlPrdOpng(String filepath,String br, Connection con){
			   int t=0;
			  try 
			   
			  {
			      
			 
		       //File file = new File("D:\\emp.xml");
				  List<ProductOpngFormBean> PopngList = new ArrayList<ProductOpngFormBean>();
				  String tempVal;
		  
			 
		     File file = new File(filepath);
//			  String fl = br+"PRO09";

		     
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  NodeList nodeLst = doc.getElementsByTagName("prdopng");

			  ProductOpngFormBean popng = null;
				//List data = new ArrayList();
			   
				for (int s = 0; s < nodeLst.getLength(); s++)
				{

			        Node fstNode = nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
			    {
			  
			           Element fstElmnt = (Element) fstNode;
		               popng = new ProductOpngFormBean();     
		               
		               NodeList depoLst = fstElmnt.getElementsByTagName("OPNG"); //First Element
		               
		          	 if(depoLst.getLength()>0)
		      		{	
			           Element depo = (Element) depoLst.item(0);
			           NodeList id = depo.getChildNodes();
			           //formbean mein data set
			           tempVal=((Node) id.item(0)).getNodeValue();
					   Opngdata(popng,tempVal.split( "\\|"));
		          	//formbean mein Prodata set
		      		} 

			           
			   			PopngList.add(popng);
				    
//			            int i = produp.updateProd(pro,con);
//			            t=t+i;
				    
			            
			    }

			  } 
				    
					SQLOpngDao opngup = new SQLOpngDao();
					opngup.updateopng(PopngList, con,br);
				
				
			  } catch (Exception e) 
			   {
			    e.printStackTrace();  
			   }
			  
			  return t;
			  
		 }
		

////////////////////////////////////////////////////////////////////////////////////////////////////////
			public static void Opngdata(ProductOpngFormBean popng, String[] dval) throws ParseException
			{
		   	    for(int i=0;i<dval.length;i++)
			    {
		   	    	if (i==0){
		   	    		   popng.setDepo_code(Integer.parseInt(dval[i]));
		   	    	   }else if (i==1){
		   	    		   popng.setOpcode(Integer.parseInt(dval[i]));
		   	    	   }else if (i==2){
		   	    		   popng.setOpngjan(Integer.parseInt(dval[i]));
		   	    	   }else if (i==3){
		   	    		   popng.setOpngfeb(Integer.parseInt(dval[i]));
		   	    	   }else if (i==4){
		   	    		   popng.setOpngmar(Integer.parseInt(dval[i]));
		   	    	   }else if (i==5){
		   	    		   popng.setOpngapr(Integer.parseInt(dval[i]));
		   	    	   }else if (i==6){
		   	    		   popng.setOpngmay(Integer.parseInt(dval[i]));
		   	    	   }else if (i==7){
		   	    		   popng.setOpngjun(Integer.parseInt(dval[i]));
		   	    	   }else if (i==8){
		   	    		   popng.setOpngjul(Integer.parseInt(dval[i]));
		   	    	   }else if (i==9){
		   	    		   popng.setOpngaug(Integer.parseInt(dval[i]));
		   	    	   }else if (i==10){
		   	    		   popng.setOpngsep(Integer.parseInt(dval[i]));
		   	    	   }else if (i==11){
		   	    		   popng.setOpngoct(Integer.parseInt(dval[i]));
		   	    	   }else if (i==12){
		   	    		   popng.setOpngnov(Integer.parseInt(dval[i]));
		   	    	   }else if (i==13){
		   	    		   popng.setOpngdec(Integer.parseInt(dval[i]));
		   	    	   }else if (i==14){
		   	    		   popng.setOpngyer(Integer.parseInt(dval[i]));
		   	    	   } 	   	    	
			    }
			}		 
}  
