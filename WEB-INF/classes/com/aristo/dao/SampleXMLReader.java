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
import com.aristo.dao.SQLOpngDao;
import com.aristo.valueobject.ProductOpngFormBean;
import com.aristo.dao.SQLInvSndDAO;
import com.aristo.valueobject.InvSndFormBean;
import com.aristo.dao.SQLInvFstDAO;
import com.aristo.valueobject.InvFstFormBean;
import com.aristo.dao.SQLBatchDAO;
import com.aristo.valueobject.BatchMasterFormBean;
import com.aristo.valueobject.AccountFormBean;

public class SampleXMLReader {

	 public int UpdateXmlAccount(String filepath,String br,Connection con){

		  int t=0;
		  try {
		 
		  File file = new File(filepath);
		  List<AccountFormBean> accList = new ArrayList<AccountFormBean>();

		  String fl ="faacms2";
		  String f2 =br.substring(0, 1);

		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName(fl);

		  AccountFormBean account = null;
		  DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
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
	               
	                NodeList areacd = fstElmnt.getElementsByTagName("MGRP_CODE"); //Second Element
	                Element arcd = (Element) areacd.item(0);
	                NodeList ar = arcd.getChildNodes();
	                account.setMgrp_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

		            areacd = fstElmnt.getElementsByTagName("MAC_CODE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	          		if(ar.getLength()>0)
	          		{
	          			account.setMac_code((((Node) ar.item(0)).getNodeValue()));
	          		}
	                
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
	                
		            areacd = fstElmnt.getElementsByTagName("MSTAT_CODE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMstat_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

		            areacd = fstElmnt.getElementsByTagName("MAREA_CODE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMarea_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	                
		            areacd = fstElmnt.getElementsByTagName("MREGION_CD"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMregion_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

		            areacd = fstElmnt.getElementsByTagName("MTERR_CODE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMterr_code(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));

		            areacd = fstElmnt.getElementsByTagName("MDIST_CD"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMdist_cd(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	                
		            areacd = fstElmnt.getElementsByTagName("MTYPE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMtype(((((Node) ar.item(0)).getNodeValue())));
	          		
		            areacd = fstElmnt.getElementsByTagName("MKT_YEAR"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setMkt_year(Integer.parseInt((((Node) ar.item(0)).getNodeValue())));
	          		
		            areacd = fstElmnt.getElementsByTagName("MBANKER"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            if(ar.getLength()>0)
	          		{	
		            	account.setMbanker(((Node) ar.item(0)).getNodeValue());
	          		}
	                
	                
		            areacd = fstElmnt.getElementsByTagName("JOINING_DT"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            if(((Node) ar.item(0)).getNodeValue().equals("  /  /    ")) 
	          		{	 
		                account.setJoining_dt(dateformat.parse("01/01/1901"));
	          		}
		            else
		            {
		                account.setJoining_dt(dateformat.parse((((Node) ar.item(0)).getNodeValue())));
		            }
	                
		            areacd = fstElmnt.getElementsByTagName("RESIGNA_DT"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
	                account.setResigna_dt(dateformat.parse((((Node) ar.item(0)).getNodeValue())));

		            areacd = fstElmnt.getElementsByTagName("EMP_CODE"); //Second Element
		            arcd = (Element) areacd.item(0);
		            ar = arcd.getChildNodes();
		            if(ar.getLength()>0)
	          		{	
		            	account.setMbank_add1(((Node) ar.item(0)).getNodeValue());
	          		}

		            accList.add(account);
	                
		    }

		  } 
			   
	        SQLSampleAccountDAO accup = new SQLSampleAccountDAO();
	        t = accup.updateAccount(f2,accList,con);  		   
			
			
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
	
	 
//////////////////////////////////////SAMPLE BATMST XMl Conversion///////////////////////////////////////////
	 public int UpdateXmlBatMst(String filepath,String br, Connection con){
	      int t=0;
		  try 
		  {
			  
		  List<BatchMasterFormBean> BatList = new ArrayList<BatchMasterFormBean>();
		  String tempVal;
	      File file = new File(filepath);
		  String f2 =br.substring(0, 1);
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("batch");

		  BatchMasterFormBean bat = null;
		   
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
			
				SQLBatchDAO batup = new SQLBatchDAO();
				batup.updatebatch(BatList, con,f2);
			
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
	   	    		   bat.setBat_expdt((dval[i]));
	   	    	   }else if (i==4){
	   	    		   bat.setBat_indct((dval[i]));
	   	    	   }else if (i==5){
	   	    		   bat.setBat_clos(Integer.parseInt(dval[i]));
		   	       }else if (i==6){
	   	    		   bat.setBat_tag(dval[i]);
		   	       }else if (i==7){
	   	    		 if (dval[i].charAt(0)!=' ')  
		   	    		   bat.setExpiry(dateformat.parse(dval[i]));
		   	       }
 	   	    	
		    }
		}

//////////////////////////////////////SAMPLE INVFST XMl Conversion///////////////////////////////////////////
		 public int UpdateXmlInvFst(String filepath,String br, Connection con){
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
			inv1up.updateInvFst(Inv1List, con,f2);
				
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
	   	    		   inv.setChl_no((dval[i]));
	   	    	   }else if (i==10){
	   	    		if (dval[i].charAt(0)!=' ')   	    		 
	   	    		   inv.setChl_dt(dateformat.parse(dval[i]));
	   	    	   }else if (i==11){
	   	    		   inv.setMtr_no((dval[i]));
	   	    	   }else if (i==12){
	      	    	if (dval[i].charAt(0)!=' ')   	    		   
	   	    		   inv.setMtr_dt(dateformat.parse(dval[i]));
	   	    	   }else if (i==13){
	   	    		   inv.setOrder_no((dval[i]));
	   	    	   }else if (i==14){
	      	    	if (dval[i].charAt(0)!=' ')   	    		   
	   	    		   inv.setOrder_dt(dateformat.parse(dval[i]));
	   	    	   }else if (i==15){
	   	    		   inv.setCases(Integer.parseInt(dval[i]));
	   	    	   }else if (i==16){
	   	    		   inv.setDue_days(Integer.parseInt(dval[i]));
	   	    	   }else if (i==17){
	   	    		if (dval[i].charAt(0)!=' ')   	    		 
	   	    		   inv.setDue_dt(dateformat.parse(dval[i]));
	   	    	   }else if (i==18){
	   	    		   inv.setTransport(dval[i]);
	   	    	   }else if (i==19){
	   	    		   inv.setBank(dval[i]);
	   	    	   }else if (i==20){
	   	    		   inv.setDrug_lc1(dval[i]);
	   	    	   }else if (i==21){
	   	    		   inv.setDrug_lc2(dval[i]);
	   	    	   }else if (i==22){
	   	    		   inv.setMr_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==23){
	   	    		   inv.setStat_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==24){
	   	    		   inv.setArea_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==25){
	   	    		   inv.setRegn_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==26){
	   	    		   inv.setTerr_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==27){
	   	    		   inv.setDist_cd(Integer.parseInt(dval[i]));
	   	    	   }else if (i==28){
	   	    		   inv.setBill_amt(Double.parseDouble(dval[i]));
	   	    	   }else if (i==29){
	   	    		   inv.setInv_type(dval[i]);
	   	    	   }else if (i==30){
	   	    		   inv.setRem1(dval[i]);
	   	    	   }
	   	    	
		    }
		}
	   		
//////////////////////////////////////INVSND XMl Conversion///////////////////////////////////////////
		 public int UpdateXmlInvSnd(String filepath,String br, Connection con){
		 int t=0;
		 try 
		  {
			  List<InvSndFormBean> Inv2List = new ArrayList<InvSndFormBean>();
			  String tempVal;
			  File file = new File(filepath);
			  String f2 =br.substring(0, 1);	     
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
			inv2up.updateInvSnd(Inv2List, con,f2);
							
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		   	    		   inv.setStrn_tp((dval[i]));
		   	    	   }else if (i==7){
		   	    		   inv.setSprt_cd((dval[i]));
		   	    	   }else if (i==8){
		   	    		   inv.setSprd_cd(Integer.parseInt(dval[i]));
		   	    	   }else if (i==9){
		   	    		   inv.setSpd_gp(Integer.parseInt(dval[i]));
		   	    	   }else if (i==10){
		   	    		   inv.setSale_type(Integer.parseInt(dval[i]));
		   	    	   }else if (i==11){
		   	    		   inv.setSbatch_no(dval[i]);
		   	    	   }else if (i==12){
		   	    		   inv.setSmfg_dt(dval[i]);
		   	    	   }else if (i==13){
		   	    		   inv.setSexp_dt(dval[i]);
		   	    	   }else if (i==14){
		   	    		   inv.setSqty(Integer.parseInt(dval[i]));
		  	    	   }else if (i==15){
		   	    		   inv.setSfree_qty(Integer.parseInt(dval[i]));
		   	    	   }else if (i==16){
		   	    		   inv.setOct_type((dval[i]));
		   	    	   }else if (i==17){
		   	    		   inv.setOctroi(Double.parseDouble(dval[i]));
		   	    	   }else if (i==18){
		   	    		   inv.setSdel_tg(dval[i]);
		   	    	   }else if (i==19){
		   	    		   inv.setSmr_cd(Integer.parseInt(dval[i]));
		   	    	   }else if (i==20){
		   	    		   inv.setStat_cd(Integer.parseInt(dval[i]));
		   	    	   }else if (i==21){
		   	    		   inv.setArea_cd(Integer.parseInt(dval[i]));
		   	    	   }else if (i==22){
		   	    		   inv.setInv_dsm(Integer.parseInt(dval[i]));
		   	    	   }else if (i==23){
		   	    		   inv.setTerr_cd(Integer.parseInt(dval[i]));
		   	    	   }else if (i==24){
		   	    		   inv.setInv_dist(Integer.parseInt(dval[i]));
		   	    	   }else if (i==25){
		   	    		   inv.setBr_tag(dval[i]);
		   	    	   }else if (i==26){
		   	    		   inv.setNdays(Integer.parseInt(dval[i]));
		   	    	   }else if (i==27){
			    		   inv.setSkno(Integer.parseInt(dval[i]));   	    		   
		   	    	   }else if (i==28){
			    		   inv.setSunit_cd(Integer.parseInt(dval[i]));   	    		   
		   	    	   }  		
			    }
			}
		
//////////////////////////////////////SAMPLE PRDOPNG XMl Conversion///////////////////////////////////////////
			 public int UpdateXmlPrdOpng(String filepath,String br, Connection con){
			   int t=0;
			   try 
				 {
				  List<ProductOpngFormBean> PopngList = new ArrayList<ProductOpngFormBean>();
				  String tempVal;
			      File file = new File(filepath);
				  String f2 =br.substring(0, 1);			      
				  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				  DocumentBuilder db = dbf.newDocumentBuilder();
				  Document doc = db.parse(file);
				  doc.getDocumentElement().normalize();
				  NodeList nodeLst = doc.getElementsByTagName("prdopng");

				  ProductOpngFormBean popng = null;
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
				           tempVal=((Node) id.item(0)).getNodeValue();
						   Opngdata(popng,tempVal.split( "\\|"));
			      		 } 
			          	   PopngList.add(popng);
					    
				    }

				  } 
					    
				SQLOpngDao opngup = new SQLOpngDao();
				opngup.updateopng(PopngList, con,f2);
					
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

//////////////////////////////////////////////////////////////////////////////////////////////////////	//
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
