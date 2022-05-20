package com.aristo.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


import com.aristo.dao.AreaXML;
import com.aristo.dao.MRXML;
import com.aristo.dao.MSRXML;
import com.aristo.dao.RegionXML;
import com.aristo.dao.SQLUploadDAO;
import com.aristo.dao.SampleAreaXML;
import com.aristo.dao.SampleRegionXML;
import com.aristo.dao.SampleTerXML;
import com.aristo.dao.SampleXMLReader;
import com.aristo.dao.TerXML;
import com.aristo.dao.XMLReader;
import com.aristo.valueobject.UploadFormBean;

import  javax.sql.DataSource;


public class NewUploadAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

/////////////////////////////// Get DataSource from struts-config.xml////////////////////////////////////////
	    DataSource datasource = this.getDataSource(req,"user" +	"DB"); 
	    Connection con=null;
		
	    UploadFormBean ufb = new UploadFormBean();  
	    SQLUploadDAO upd = new SQLUploadDAO();
	    HttpSession session=req.getSession();
	    String s1=null;
	    String s2=null;
	    int k=0;
		List l = (List) session.getAttribute("ffdata");
		if (l!=null) ////// check whether List having data or not
		{
		String fname=null;
		Iterator it=l.iterator();
		while(it.hasNext()) ///////// Iterate the List ////////////////////////
			{
			fname=(String) it.next();
			
		      String filePath1 = getServlet().getServletContext().getRealPath("/") +"upload/"+fname;
		      if (fname.length()==12)
		      {
			      s1 = fname.substring(3,6)+fname.substring(8,12);
			      s2 = fname.substring(0,3);
			      if ((fname.substring(3).equalsIgnoreCase("INVFS.XML"))||(fname.substring(3).equalsIgnoreCase("INVSN.XML"))||(fname.substring(3).equalsIgnoreCase("REGIO.XML")) ||(fname.substring(3).equalsIgnoreCase("BATCH.XML")))
			      {
				      s1 = fname.substring(3);
				      s2 = fname.substring(0,3);
			      }
			    	  
		      }
		      else
		      {
			      s1 = fname.substring(3);
			      System.out.println(s1);
			      s2 = fname.substring(0,3);
		      }

		      //if ((fname.startsWith("PAMNDPHAR")) || (fname.startsWith("LADAMANLBBD")))
		      if ((fname.startsWith("PA")) || (fname.startsWith("LA")) || (fname.startsWith("PO")))
		      {
		    	  		s1 = "PAM.xml";
			      	    con=datasource.getConnection();
			     	    XMLReader xml = new XMLReader();
	  	        	    k =xml.UpdateXmlFactory(filePath1,s2,con);
			    		ufb.setTyp(s2);
			    		ufb.setFile_name(s1);
			 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    		Date dt =  new Date();
			    		String s = String.valueOf(dt);
			    		ufb.setU_time(s.substring(11,16));
			    		ufb.setU_date(formatter.format(dt));
			    		ufb.setUpload("YES");
			    		con=datasource.getConnection();
			    		int j=upd.updateupload(ufb,con); 
			            k=k+j-j;
		      }
////////////////////////////////		HQ Detail XML Update////////////////////////////////////	      
				        if (s1.equalsIgnoreCase("HQT.xml"))
				        {  
				     	    con=datasource.getConnection();
//				     	   BankXMLContentHandler hqd = new BankXMLContentHandler();
//				     	   HQXML hqd = new HQXML();
//				    		k = hqd.runExample(filePath1, s2, con);
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlHQDetail(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
				        
////////////////////////////////		Stockiest XML Update////////////////////////////////////		        
				        
				        if (s1.equalsIgnoreCase("STK.xml"))
				        {  
				     	    con=datasource.getConnection();
//				     	   BankXMLContentHandler hqd = new BankXMLContentHandler();
//				     	   StockiestXML hqd = new StockiestXML();
//				    		k = hqd.runExample(filePath1, s2, con);
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlStockiest(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				            
				         }

////////////////////////////////		Stockt XML Update////////////////////////////////////		        
				        
				        if (s1.equalsIgnoreCase("INV.xml"))
				        {  
				     	   con=datasource.getConnection();
//				     	   BankXMLContentHandler hqd = new BankXMLContentHandler();
//				     	   HQXML hqd = new HQXML();
//				    		k = hqd.runExample(filePath1, s2, con);
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlStock(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				            
				         }
				        
////////////////////////////////Trigger XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("ZVI.xml"))
				        {  
				     	    con=datasource.getConnection();
		  	        	    XMLReader.UpdateXmlTrig(filePath1,s2,con);
				         }
				        
////////////////////////////////Target Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("TAR.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlProd(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }

////////////////////////////////Last Year File XML Update////////////////////////////////////				        
				        if (s1.equalsIgnoreCase("LYS.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlBud(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
				       			        
////////////////////////////////Trigger XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("ZVI.xml"))
				        {  
				     	    con=datasource.getConnection();
		  	        	    XMLReader.UpdateXmlTrig(filePath1,s2,con);
				         }


				        
////////////////////////////////Batch Master Main ke liye XML Update////////////////////////////////////				        
				        if (s1.equalsIgnoreCase("BAT.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlBat(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
				        
				        
////////////////////////////////Area Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("ARE.xml")) 
				        {  
				     	    con=datasource.getConnection();
				    		AreaXML area = new AreaXML();
				    		k = area.runExample(filePath1, s2, con);
//				            XMLReader.UpdateXmlArea(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }

////////////////////////////////		HQ Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("TER.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		TerXML ter = new TerXML();
				    		k = ter.runExample(filePath1, s2, con);
//				           k=XMLReader.UpdateXmlTer(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
				        
				        
////////////////////////////////		Region Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("REG.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		RegionXML regn = new RegionXML();
				    		k = regn.runExample(filePath1, s2, con);
//				        	 k =XMLReader.UpdateXmlRegion(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
				        
////////////////////////////////		MR Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("MRM.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    MRXML mrm = new MRXML();
				    		k = mrm.runExample(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
				        
////////////////////////////////		MSR Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("MSR.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    MSRXML msr = new MSRXML();
				    		k = msr.runExample(filePath1, s2, con); 
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
				        
////////////////////////////////		ACCOUNT Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("FAA.xml"))
				        {  
				     	    con=datasource.getConnection();
//				     	    AccountXML acc = new AccountXML();
//				    		k = acc.runExample(filePath1, s2, con); 
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlAccount(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
				   
////////////////////////////////FSTFL XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("FSTFL.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    XMLReader xml = new XMLReader();
		  	        	    k =xml.UpdateXmlFstFl(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
//				    		con=datasource.getConnection();
//				    		int j=upd.updateupload(ufb,con); 
//				            k=k+j-j;
				         }				        
				        
				        
					      s1 = fname.substring(3);
					      s2 = fname.substring(0,3);

				                
////////////////////////////////Sample Area Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("AREA.xml")) 
				        {  
				     	    con=datasource.getConnection();
				    		SampleAreaXML area = new SampleAreaXML();
				    		k = area.runExample(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				    		
				         }
////////////////////////////////Sample Region Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("REGIO.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		SampleRegionXML regn = new SampleRegionXML();
				    		k = regn.runExample(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				           
				         }
////////////////////////////////Sample HQ Master XML Update////////////////////////////////////		        
				        if (s1.equalsIgnoreCase("STER.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		SampleTerXML ter = new SampleTerXML();
				    		k = ter.runExample(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
////////////////////////////////Sample ACCOUNT Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("PARTY.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    SampleXMLReader xml = new SampleXMLReader();
		  	        	    k =xml.UpdateXmlAccount(filePath1,s2,con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }

////////////////////////////////Sample ACCOUNT Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("BATCH.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    SampleXMLReader xml = new SampleXMLReader();
		  	        	    k =xml.UpdateXmlBatMst(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }				        
////////////////////////////////Sample INVFST Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("INVFS.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    SampleXMLReader xml = new SampleXMLReader();
		  	        	    k=xml.UpdateXmlInvFst(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }

				        
////////////////////////////////Sample INVSND Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("INVSN.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    SampleXMLReader xml = new SampleXMLReader();
		  	        	    k=xml.UpdateXmlInvSnd(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
				        
////////////////////////////////Sample PRDOPNG Master XML Update////////////////////////////////////
				        if (s1.equalsIgnoreCase("PRDOP.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    SampleXMLReader xml = new SampleXMLReader();
		  	        	    k=xml.UpdateXmlPrdOpng(filePath1, s2, con);
				    		ufb.setTyp(s2);
				    		ufb.setFile_name(fname);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateupload(ufb,con); 
				            k=k+j-j;
				         }
				        
				        
				        
				         
				        
				        
			}  ///// End of While loop///////////////////////
		}////////////////// End of if condition (l!=null)
		
		return mapping.findForward("success");   
	
	}
	
	
}
