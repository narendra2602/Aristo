package com.aristo.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.aristo.dao.DAOFactory;
import com.aristo.dao.LoginDAO;
import com.aristo.dao.SQLUserEditDAO;
import com.aristo.form.LoginForm;
import com.aristo.valueobject.LoginFormBean;


public class EditAction extends DispatchAction {

	
    public ActionForward UserEdit(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con= null;
		con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	    	
	    
	    LoginForm tf = (LoginForm)form;
	    
        SQLUserEditDAO rd = new SQLUserEditDAO();
        LoginFormBean fb = new LoginFormBean();
        
		int uid = tf.getId();

        fb =rd.UserEdit(con,uid);
        tf.setId(uid);
    	tf.setTitle(fb.getTitle());
    	tf.setF_name(fb.getF_name());
    	tf.setL_name(fb.getL_name());
    	tf.setDesig(fb.getDesig());
    	tf.setLocation(fb.getLocation());
    	tf.setDepartment(fb.getDepartment());
    	tf.setComp_code(fb.getComp_code());
    	tf.setLogin_name(fb.getLogin_name());
    	tf.setAccess_t(fb.getAccess_t());
    	tf.setStatus(fb.getStatus());
    	tf.setType(fb.getType());
    	tf.setEmail(fb.getEmail());
    	tf.setCheckbox1(fb.getCheckbox1());
    	
    	req.setAttribute("typ", fb.getType());
	
		return mapping.findForward("sucess");
    
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ActionForward UpdateUser(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	     String tp=null;
	     LoginForm tf = (LoginForm)form;
	    
         SQLUserEditDAO rd = new SQLUserEditDAO();
         LoginFormBean fb = new LoginFormBean();
         if(tf.getAccess_t().equals("MF"))
         {
        	 tp="A";
         }
         if(tf.getAccess_t().equals("TF"))
         {
        	 tp="T";
         }
         if(tf.getAccess_t().equals("Genetica"))
         {
        	 tp="G";
         }
         
         fb.setId(tf.getId());
    	 fb.setTitle(tf.getTitle());
    	 fb.setF_name(tf.getF_name());
    	 fb.setL_name(tf.getL_name());
    	 fb.setDesig(tf.getDesig());
    	 fb.setLocation(tf.getLocation());
    	 fb.setDepartment(tf.getDepartment());
    	 fb.setComp_code(tf.getComp_code());
    	 fb.setLogin_name(tf.getLogin_name());
    	 fb.setAccess_t(tf.getAccess_t());
    	 fb.setStatus(tf.getStatus());
    	 fb.setType(tf.getType());
    	 fb.setEmail(tf.getEmail());
    	 fb.setCheckbox1(tf.getCheckbox1());
    	 fb.setD_type(tp);
    	 
	    
         int j=rd.UserUpdate(con, fb);
         
         if (j>0)
    	 
	     return mapping.findForward("sucess");	    
         else 
         return mapping.findForward("fail");	        	    
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public ActionForward UserRightsEdit(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con= null;
		con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	    LoginForm tf = (LoginForm)form;
        SQLUserEditDAO rd = new SQLUserEditDAO();
        LoginFormBean fb = new LoginFormBean();
		int uid = tf.getId();
		
             fb =rd.UserRightsEdit(con, uid);
             tf.setId(uid);
             tf.setCheckbox2(fb.getCheckbox2());
             String name=fb.getLogin_name();
             req.setAttribute("uname", name);
             req.setAttribute("typ", fb.getType());
        if (fb.getType().equals("Central"))
        {
        return mapping.findForward("Central");	
        }
        if (fb.getType().equals("PMT"))
        {
        return mapping.findForward("PMT");	
        }
        if (fb.getType().equals("HQ"))
        {
        return mapping.findForward("HQ");	
        }
        
		return mapping.findForward("sucess");
    
    }    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ActionForward UpdateUserRights(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	    
	     LoginForm tf = (LoginForm)form;
	     
         SQLUserEditDAO rd = new SQLUserEditDAO();
         LoginFormBean fb = new LoginFormBean();
         String typ= (String) req.getParameter("tp");
         
         fb.setId(tf.getId());
    	 fb.setCheckbox2(tf.getCheckbox2());
	     fb.setType(typ);
         int j=rd.UserRightsUpdate(con, fb);
         
         if (j>0)
    	 
	     return mapping.findForward("sucess");	    
         else 
         return mapping.findForward("fail");	        	    
    }
    
    
    public ActionForward PmtRightsEdit(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con= null;
		
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	    LoginForm tf = (LoginForm)form;
        SQLUserEditDAO rd = new SQLUserEditDAO();
        LoginFormBean fb = new LoginFormBean();
		int uid = tf.getId();
		
		LoginDAO ldao = DAOFactory.getLoginDAO();
		con=datasource.getConnection();
		String tp = ldao.getType(con,uid);
		
		List pmt_gp=null;
		con=datasource.getConnection();		
		pmt_gp=ldao.getAllGroup(con, tp);
		req.setAttribute("pmt", pmt_gp);
		
		con=datasource.getConnection();
        fb =rd.PmtGroupEdit(con, uid);
        tf.setId(uid);
        tf.setCheckbox2(fb.getCheckbox2());
        String name=fb.getLogin_name();
        req.setAttribute("uname", name);
        req.setAttribute("tp", tp);
        req.setAttribute("uid", new Integer(uid));
        
		return mapping.findForward("sucess");
    
    }    
    
    public ActionForward UpdatePmtRights(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
    throws Exception
    {      
		DataSource datasource = this.getDataSource(req,"userDB"); 
		Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	    
	     LoginForm tf = (LoginForm)form;
	     
         SQLUserEditDAO rd = new SQLUserEditDAO();
         LoginFormBean fb = new LoginFormBean();
         String typ= (String) req.getParameter("tp");
         int id = Integer.parseInt(req.getParameter("uid"));
         fb.setId(id);
    	 fb.setCheckbox2(tf.getCheckbox2());
	     fb.setD_type(typ);
         int j=rd.PmtGroupUpdate(con, fb);
         
         if (j>0)
    	 
	     return mapping.findForward("sucess");	    
         else 
         return mapping.findForward("fail");	        	    
    }
    
    
    
    
	
}
