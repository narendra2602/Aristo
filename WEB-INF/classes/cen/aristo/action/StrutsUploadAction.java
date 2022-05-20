package cen.aristo.action;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.aristo.valueobject.LoginFormBean;

import cen.aristo.form.StrutsUploadForm;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

 public class StrutsUploadAction extends Action {
	 
	     
 public ActionForward execute( ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
  
  {
       int k =0;
	   StrutsUploadForm myForm = (StrutsUploadForm)form;
	   
/////////////////////////////// Get DataSource from struts-config.xml////////////////////////////////////////
//	   DataSource datasource = this.getDataSource(request,"userDB"); 
//	   Connection con=null;
	    
/////////////////////////////////Get the list of files//////////////////////////////////////////////////////
	   ArrayList al = myForm.getFormFiles();
	   List<String> filelist= new ArrayList<String>(); 
	   String fname=null;
	   int s=al.size();
	      
	   for(int i = 0;i<s;i++)    
		 {
//////////////////////////////get file from the bean///////////////////////////////////////////
			FormFile ff = (FormFile) al.get(i);
			fname = ff.getFileName().trim();

			  if (k==0)
			  {	  
 	     	     if (fname.length()>0)
 	     	     {	 
//				  con=datasource.getConnection();
// 	     	      ufb.setTyp(fname.substring(0,3));
//				  upd.updateRenew(ufb, con);
 	     	     } 
			  }
			  
			 if(fname.length()==0)
				{
					continue;
				}
                filelist.add(fname);
                
///////////////////////////// save file in the app server////////////////////////////////////// 
	  File newFile =  new File(getServlet().getServletContext().getRealPath("") + "/central_upload/" + ff.getFileName());
	  FileOutputStream fos = new FileOutputStream(newFile);
	  fos.write(ff.getFileData());
	  fos.close();
 		        
		}  ////////////////// for loop ends/////////////////////////////////////////
	    request.setAttribute("fdata", filelist);
        return mapping.findForward("success");
        
  } ///////////////////execute method ends///////////////////////////



 public ActionForward UploadForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
	{
     HttpSession session=req.getSession();
     LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

     if (lfb==null)

     {
     	return mapping.findForward("sfail");
     }
	  return mapping.findForward("sucess");
	}
  
  
  
  
 } /////////////////////// end of class/////////////////////////
