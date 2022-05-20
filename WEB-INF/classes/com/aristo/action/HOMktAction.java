package com.aristo.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.aristo.dao.SQLHOForm1DAO;
import com.aristo.dao.SQLHOForm2DAO;
import com.aristo.dao.SQLHOForm3DAO;
import com.aristo.dao.SQLHOForm4DAO;
import com.aristo.dao.SQLHOMktDAO;
import com.aristo.form.HOForm1Form;
import com.aristo.form.HOForm2Form;
import com.aristo.form.HOForm3Form;
import com.aristo.form.HOForm4Form;
import com.aristo.valueobject.HOForm1FormBean;
import com.aristo.valueobject.HOForm2FormBean;
import com.aristo.valueobject.HOForm4FormBean;
import com.aristo.valueobject.LoginFormBean;

public class HOMktAction extends DispatchAction{
	
/////////////////////////////////////HO Mkt Option 1 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptForm1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptForm1 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
 
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
         
        HOForm1Form af= (HOForm1Form) form;
		af.setYlist(lfb.getYlist());
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
		
        List repo=null;
        List repo1= new ArrayList();
		SQLHOMktDAO rd = new SQLHOMktDAO();
		con=datasource.getConnection();
    	repo =rd.getAllProduct(con,tp,year,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		
		String whead=null;
		HOForm1FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (HOForm1FormBean) (it.next());
		    whead = r.getHead1();
		}
		
	    req.setAttribute("data", whead);
 		return mapping.findForward("sucess"); 
	}
/////////////////////////////////////HO Mkt Option 1 End////////////////////////////////////////////////////
	
//////////////////////////////////// HO Mkt Form 1 Start //////////////////////////////////////////////////	
	
	public ActionForward HOListForm1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListForm1 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
   
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code1 = lfb.getDcode();
        int loginid = lfb.getId();
         
        HOForm1Form af= (HOForm1Form) form;
         
      //      Populate chosen list from hidden field.
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        
        List repo=null;
        SQLHOForm1DAO rd = new SQLHOForm1DAO();
        
        int emon = af.getEmon();
        int eyear=af.getEyear();
        int opt = af.getOpt();
            int i=1; 
		        while (it.hasNext())
		        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
        	         if (opt==1) 
             		  repo =rd.getAllHQ(con,cd,emon,tp,typ,code1,loginid,eyear);
        	         if (opt==2) 
                		  repo =rd.getAllBranch(con,cd,emon,tp,typ,code1,loginid,eyear);
             		  m.put(i, repo);
             		  i++;
		        }		
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
    	
		return mapping.findForward("sucess");      
		
	}
/////////////////////////////////////// HO Mkt Form 1 End ////////////////////////////////////////////////	
	
/////////////////////////////////////HO Mkt Option 2 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptForm2 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
 
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        HOForm2Form af= (HOForm2Form) form;
		af.setYlist(lfb.getYlist());
		
        int eyear=0;
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
		
        List repo=null;
        List repo1= new ArrayList();
		SQLHOMktDAO rd = new SQLHOMktDAO();
    	con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		HOForm2FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (HOForm2FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
		
	}
/////////////////////////////////////HO Mkt Option 2 End////////////////////////////////////////////////////
	public ActionForward HOAjaxForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOAjaxForm2 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
 
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null) 

        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        HOForm2Form af= (HOForm2Form) form;
        int eyear=Integer.parseInt(req.getParameter("q"));
        System.out.println("year in ajax "+eyear);
        List repo=null;
        List repo1= new ArrayList();

		SQLHOMktDAO rd = new SQLHOMktDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo); 

		req.setAttribute("rlist",repo);
		req.setAttribute("opt", "HOpt2");
		
		
		return mapping.findForward("sucess");
		
	}



//////////////////////////////////// HO Mkt Form 2 Start //////////////////////////////////////////////////	
	
	public ActionForward HOListForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListForm1 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
   
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code1 = lfb.getDcode();
        int loginid = lfb.getId();
         
        HOForm2Form af= (HOForm2Form) form;
         
      //      Populate chosen list from hidden field.
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        
        List repo=null;
        SQLHOForm2DAO rd = new SQLHOForm2DAO();
        
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
        int opt = af.getOpt();

            int i=1;
		        while (it.hasNext())
		        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
         	         if (opt==1) 
                		  repo =rd.getAllHQ(con,cd,emon,tp,typ,code1,loginid,eyear);
           	         if (opt==2) 
                   		  repo =rd.getAllBranch(con,cd,emon,tp,typ,code1,loginid,eyear);
             		  m.put(i, repo);
             		  i++;
		        }		
	
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
  
		return mapping.findForward("sucess");      
		
	}
/////////////////////////////////////// HO Mkt Form 2 End ////////////////////////////////////////////////	
	
///////////////////////////////////// HO Mkt Option 3 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        HOForm3Form af= (HOForm3Form) form;
			af.setYlist(lfb.getYlist());
			
		    return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO Option 3 End////////////////////////////////////////////////////	
	
/////////////////////////////////////HO List 3 Start////////////////////////////////////////////////////    	
	
	public ActionForward HOListForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListForm3 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code = lfb.getDcode();
        int loginid = lfb.getId();
         
        HOForm3Form af=(HOForm3Form) form;
        
        List repo=null;
        SQLHOForm3DAO rd = new SQLHOForm3DAO();
        con=datasource.getConnection();
        int smon=af.getSmon();
        int emon=af.getEmon();
		int eyear=af.getEyear();
		
             repo =rd.getAllBranch(con,smon,emon,tp,typ,code,loginid,eyear);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
	}
/////////////////////////////////////HO List 3 End////////////////////////////////////////////////////	
	
/////////////////////////////////////HO Mkt Option 4 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptForm4 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        HOForm4Form af= (HOForm4Form) form;
        af.setYlist(lfb.getYlist());
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        af.setChosenItem(null);
        List repo=null;
        List repo1= new ArrayList();
        SQLHOMktDAO rd = new SQLHOMktDAO();
        con=datasource.getConnection();
    	repo =rd.getHOForm4Product(con, tp,year,uid,utype); 
    	
        //Repo1DAO  rd= DAOFactory.getRepo1();
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		HOForm4FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (HOForm4FormBean) (it.next());
		    whead = r.getHead1();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
/////////////////////////////////////HO Mkt Option 4 End////////////////////////////////////////////////////
		
/////////////////////////////////////HO List 4 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListForm4 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code1 = lfb.getDcode();
        int loginid = lfb.getId();

        HOForm4Form af= (HOForm4Form) form;
   //   Populate chosen list from hidden field.
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist()); 
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
         
        List repo=null;
        SQLHOForm4DAO rd = new SQLHOForm4DAO();
        
		int uv = af.getUv();
        int emon = af.getEmon();
        int st = af.getSt();
        int eyear=af.getEyear();
        
            int i=1;
 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,cd,uv,emon,st,tp,typ,code1,loginid,eyear);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		
		return mapping.findForward("sucess");      
		
	}	
	
/////////////////////////////////////HO List 4 End////////////////////////////////////////////////////	

/////////////////////////////////////HO Mkt Option6 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptForm6 Action class");       

         

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
 
        HOForm2Form af= (HOForm2Form) form;
		af.setYlist(lfb.getYlist());
		
        
		return mapping.findForward("sucess"); 
		
	}
	
	
	public ActionForward HOListForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListForm6 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code = lfb.getDcode();
        int loginid = lfb.getId();
         
        HOForm2Form af=(HOForm2Form) form;
        
        List repo=null;
        SQLHOForm2DAO rd = new SQLHOForm2DAO();
        con=datasource.getConnection();
        int emon=af.getEmon();
		int eyear=af.getEyear();
		
             repo =rd.getAllIndia(con,emon,tp,typ,code,loginid,eyear);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
	}	
/////////////////////////////////////HO Mkt Option 6 End////////////////////////////////////////////////////	
	
}
