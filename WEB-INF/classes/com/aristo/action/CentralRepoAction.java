package com.aristo.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cen.aristo.dao.BatchDAO;
import cen.aristo.dao.SQLBatchDAO;

import com.aristo.dao.SQLCen1DAO;
import com.aristo.dao.SQLCen2DAO;
import com.aristo.dao.SQLCen3DAO;
import com.aristo.dao.SQLCen4DAO;
import com.aristo.dao.SQLCen5DAO;
import com.aristo.form.Cen1Form;
import com.aristo.form.Cen2Form;
import com.aristo.form.Cen3Form;
import com.aristo.form.Cen4Form;
import com.aristo.form.Cen5Form;
import com.aristo.valueobject.LoginFormBean;

public class CentralRepoAction extends DispatchAction {

	public ActionForward OptCen1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListCen1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
        int depo = lfb.getCode();
	    Cen1Form rf = (Cen1Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "DISPATCH REGISTER FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLCen1DAO repo1 = new SQLCen1DAO();
		r = repo1.getRepo1(con, tp, sdt,edt,depo);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	public ActionForward OptCen2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListCen2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
        int depo = lfb.getCode();
        Cen2Form rf = (Cen2Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());

   	    String head = "BRANCH WISE DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
 	    SQLCen2DAO repo2 = new SQLCen2DAO();
		r = repo2.getRepo2(con, tp, sdt,edt,depo);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward OptCen3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListCen3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListCen3 Action class");       

		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
        int depo = lfb.getCode();
        Cen3Form rf= (Cen3Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String head = "GOODS IN TRANSIT (C.W.H) FROM "+rf.getSdate() + " TO " +rf.getEdate() ;
   	    
   	    SQLCen3DAO inv3 = new SQLCen3DAO();
		r = inv3.getInv3(con, tp, sdt,edt,depo);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");        
	}
	
	public ActionForward OptCen4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListCen4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
        int depo = lfb.getCode();
        Cen4Form rf= (Cen4Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLCen4DAO inv4 = new SQLCen4DAO();
		r = inv4.getRepo4(con, tp, sdt,edt,depo);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
		
	
	public ActionForward OptCen5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    
	    Cen5Form af= (Cen5Form) form;
	    
		BatchDAO  ad=new SQLBatchDAO(); 
		List prd =ad.getAllProduct(con, tp);
		af.setPlist(prd);

		return mapping.findForward("sucess");
	}
	
	public ActionForward ListCen5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
        int depo = lfb.getCode();
        Cen5Form rf= (Cen5Form) form;
     	int pcd=rf.getCode();
   	    int chc=rf.getChoice();
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "PRODUCT WISE DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLCen5DAO inv5 = new SQLCen5DAO();
		r = inv5.getRepo5(con, tp, sdt,edt,depo,chc,pcd);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}		
	
}
