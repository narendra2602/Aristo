package com.aristo.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.aristo.form.StrutsUploadAndSaveForm;
import com.aristo.valueobject.UploadFormBean;


import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.aristo.dao.SQLUploadDAO;
import  javax.sql.DataSource;

 public class StrutsUploadAndSaveAction extends Action

 {
  public ActionForward execute( ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
  
  {
       int k =0;
	   StrutsUploadAndSaveForm myForm = (StrutsUploadAndSaveForm)form;
	   
/////////////////////////////// Get DataSource from struts-config.xml////////////////////////////////////////
	   DataSource datasource = this.getDataSource(request,"userDB"); 
	   Connection con=null;
	    
	   UploadFormBean ufb = new UploadFormBean();  
	   SQLUploadDAO upd = new SQLUploadDAO();
/////////////////////////////////Get the list of files//////////////////////////////////////////////////////
	   ArrayList al = myForm.getFormFiles();
	   List<String> filelist= new ArrayList<String>();
	   String fname=null;
	   
	   for(int i = 0;i<al.size();i++) 
		 {
			
//////////////////////////////get file from the bean///////////////////////////////////////////
			FormFile ff = (FormFile) al.get(i);
			fname = ff.getFileName().trim();

			  if (k==0)
			  {	  
 	     	     if (fname.length()>0)
 	     	     {	 
				  con=datasource.getConnection();
 	     	      ufb.setTyp(fname.substring(0,3));
				  upd.updateRenew(ufb, con);
 	     	     } 
			  }
			  
			 if(fname.length()==0)
				{
					continue;
				}
				int size = ff.getFileSize();
                System.out.println("Size of the file is "+size); 
                filelist.add(fname);
                
///////////////////////////// save file in the app server////////////////////////////////////// 
	  File newFile =  new File(getServlet().getServletContext().getRealPath("") + "/upload/" + ff.getFileName());
	  FileOutputStream fos = new FileOutputStream(newFile);
	  fos.write(ff.getFileData());
	  fos.close();
 		        
		}  ////////////////// for loop ends/////////////////////////////////////////
	   
	      System.out.println("List ki size hai.... "+filelist.size());
	   request.setAttribute("fdata", filelist);
        return mapping.findForward("success");
        
  } ///////////////////execute method ends///////////////////////////


  
  
  
  
  
 } /////////////////////// end of class/////////////////////////
