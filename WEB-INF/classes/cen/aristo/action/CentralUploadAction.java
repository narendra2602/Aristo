package cen.aristo.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aristo.dao.SQLUploadDAO;
import com.aristo.valueobject.UploadFormBean;

import java.sql.Connection;
import cen.aristo.dao.CentralXMLReader;


import  javax.sql.DataSource;


public class CentralUploadAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

/////////////////////////////// Get DataSource from struts-config.xml////////////////////////////////////////
	    DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
	    
	    UploadFormBean ufb = new UploadFormBean();  
	    SQLUploadDAO upd = new SQLUploadDAO();
	    
	    HttpSession session=req.getSession();
	    String filePath1 = null;
	    String s1 = null;
	    String s2 = null;
	    int k=0;
		List l = (List) session.getAttribute("ffdata");
		if (l!=null) ////// check whether List having data or not
		{
		String fname=null;
		Iterator it=l.iterator();
		while(it.hasNext()) ///////// Iterate the List ////////////////////////
			{
			
			fname=(String) it.next();
			
		      filePath1 = getServlet().getServletContext().getRealPath("/") +"central_upload/"+fname;
		      s1 = fname.substring(1);
		      s2 = fname.substring(0,1); 

////////////////////////////////		HQ Detail XML Update////////////////////////////////////	      
				        if (s1.equalsIgnoreCase("DSTMST.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader.UpdateXmlDSTMST(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }
				        
				        if (s1.equalsIgnoreCase("PARTY.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader.UpdateXmlAccount1(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }

				        if (s1.equalsIgnoreCase("BATCH.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader xml = new CentralXMLReader();
				     	    xml.UpdateXmlBatMst(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }
				        
				        if (s1.equalsIgnoreCase("INVFST.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader xml = new CentralXMLReader();
				     	    xml.UpdateXmlInvFst(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }
				        
				        if (s1.equalsIgnoreCase("INVSND.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader xml = new CentralXMLReader();
				     	    xml.UpdateXmlInvSnd(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }
				        
				        if (s1.equalsIgnoreCase("TRNMST.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader.UpdateXmlTrnMst(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }

				        if (s1.equalsIgnoreCase("PRDOPNG.XML"))
				        {  
				     	    con=datasource.getConnection();
				     	    CentralXMLReader xml = new CentralXMLReader();
				     	    xml.UpdateXmlPrdOpng(filePath1, s2, con);
				     	    ufb.setTyp(s2);
				    		ufb.setFile_name(s1);
				 		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    		Date dt =  new Date();
				    		String s = String.valueOf(dt);
				    		ufb.setU_time(s.substring(11,16));
				    		ufb.setU_date(formatter.format(dt));
				    		ufb.setUpload("YES");
				    		con=datasource.getConnection();
				    		int j=upd.updateCWHupload(ufb,con); 
				            k=k+j-j;				     	    
				        }

				        
			}  ///// End of While loop///////////////////////
		}////////////////// End of if condition (l!=null)
		
		return mapping.findForward("success");
	
	}
	
	
}
