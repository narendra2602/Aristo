package com.aristo.action;

import java.io.File;
 
import java.sql.Connection;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
 

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.aristo.dao.AreaXML;
import com.aristo.dao.HQDetailXML;
import com.aristo.dao.MRXML;
import com.aristo.dao.MSRXML;
import com.aristo.dao.ProdXML;
import com.aristo.dao.RegionXML;
import com.aristo.dao.StockiestXML;
import com.aristo.dao.TerXML;
 

 

 
public class UploadAction extends DispatchAction {
	 

	public ActionForward Upload( ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	  
	  {
	       int k =0;
	      /// Get DataSource from struts-config.xml
		   DataSource datasource = this.getDataSource(request,"userDB"); 
		   Connection con=null;
		         
		           // String remoteHost = "//"+request.getRemoteHost()+"/d/xml";
		          //  String remoteaddr = request.getLocalAddr();
		         //   System.out.println(remoteaddr);
		           // System.out.println(remoteHost);
		            
		          //  String fpath = "D:/xml";
					  String fpath = getServlet().getServletContext().getRealPath("") + "/upload/";
					  
		           // String fpath = remoteHost; 
		            File f = new File(fpath);
//		            boolean sucess =false;
		            if (f.isDirectory())
		            {
				      String[] fl = f.list();
				      
				      for(int i=0; i<fl.length; i++)
				      {
				    	  System.out.println(fl[i]);
				    	  String file = f.getPath()+"/"+fl[i];
				          File ff = new File(file);
		            	 
		           System.out.println(f.getAbsoluteFile());
		           System.out.println("Filename print hua shayad......"+ff);
		           

				      String filePath1 = getServlet().getServletContext().getRealPath("/") +"upload/"+fl[i];
				      String s1 = fl[i].substring(3);
				      String s2 = fl[i].substring(0,3); 
				      System.out.println(s2);

				       if (s1.equalsIgnoreCase("ARE09.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		AreaXML area = new AreaXML();
				    		k = area.runExample(filePath1, s2, con);
				         }
        
				        if (s1.equalsIgnoreCase("TER09.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		TerXML ter = new TerXML();
				    		k = ter.runExample(filePath1, s2, con);
				           
				         }
				   
				        if (s1.equalsIgnoreCase("PRO09.xml")) 
				        {  
				     	    con=datasource.getConnection();
				    		ProdXML pro = new ProdXML();
				    		k = pro.runExample(filePath1, s2, con);
				           
				         }
				
				        if (s1.equalsIgnoreCase("STK09.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		StockiestXML spe = new StockiestXML();
				    		k = spe.runExample(filePath1, s2, con);
		 
				           
				         }

				        if (s1.equalsIgnoreCase("HQT09.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		HQDetailXML hqd = new HQDetailXML();
				    		k = hqd.runExample(filePath1, s2, con);
				           
				         }

				        if (s1.equalsIgnoreCase("REG09.xml"))
				        {  
				     	    con=datasource.getConnection();
				    		RegionXML regn = new RegionXML();
				    		k = regn.runExample(filePath1, s2, con);
				           
				         }

				        if (s1.equalsIgnoreCase("MRM09.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    MRXML mrm = new MRXML();
				    		k = mrm.runExample(filePath1, s2, con);
				           
				         }


				        if (s1.equalsIgnoreCase("MSR09.xml"))
				        {  
				     	    con=datasource.getConnection();
				     	    MSRXML msr = new MSRXML();
				    		k = msr.runExample(filePath1, s2, con); 
				           
				         }
		           
//				        sucess=ff.delete();
//				        System.out.println("File has been deleted :"+fl[i]);
	           
				      }
		            	System.out.println("file ka path ..."+f.getPath());
		            	
		            	
		            }  
		            
				
			          Integer z = new Integer(k);
	                   System.out.println(k);
	                   System.out.println(z);
			         request.setAttribute("d", z);

		            return mapping.findForward("success");
	  }

	 
	  public ActionForward UploadForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		  
		  return mapping.findForward("sucess");
		}



}
