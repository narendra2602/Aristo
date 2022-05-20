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

import com.aristo.dao.SQLOptDAO;
import com.aristo.hq.SQLRepo18DAO;
import com.aristo.hq.SQLRepo17DAO;
import com.aristo.hq.SQLRepo16DAO;
import com.aristo.hq.SQLRepo15DAO;
import com.aristo.hq.SQLRepo14DAO;
import com.aristo.hq.SQLRepo13DAO;
import com.aristo.hq.SQLRepo12DAO;
import com.aristo.hq.SQLRepo11DAO;
import com.aristo.hq.SQLRepo10DAO;
import com.aristo.hq.SQLRepo9DAO;
import com.aristo.form.Repo10Form;
import com.aristo.form.Repo11Form;
import com.aristo.form.Repo12Form;
import com.aristo.form.Repo13Form;
import com.aristo.form.Repo14Form;
import com.aristo.form.Repo15Form;
import com.aristo.form.Repo16Form;
import com.aristo.form.Repo17Form;
import com.aristo.form.Repo18Form;
import com.aristo.form.Repo9Form;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.Repo12FormBean;
import com.aristo.valueobject.Repo13FormBean;
import com.aristo.valueobject.Repo17FormBean;
import com.aristo.valueobject.Repo18FormBean;

public class HQStockiestAction extends DispatchAction {

	public ActionForward HQOptRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo9Form af= (Repo9Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getStk(con,depo,tp,uid); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead="STOCKIEST ";
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report9 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo9Form af= (Repo9Form) form;

        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
        List repo=null;
		SQLRepo9DAO rd = new SQLRepo9DAO();
		
        int uv = af.getUv();
        int rep = af.getRep_type();

        int emon =af.getEmon();
        int eyear=af.getEyear();
        
        int i = 1;
        String is=null;
        Integer e;
	        while (it.hasNext())
	        {
	          cd = (String)it.next();
	          con=datasource.getConnection();
    		  repo =rd.getAllStk(con,cd,uv,rep,emon,eyear,depo_code,tp);
    		  e= new Integer(i);
    		  is= e.toString();
     		  m.put(is, repo);
     		  i++;
	        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}	

	public ActionForward HQOptRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-OptRepo10 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo10Form af= (Repo10Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getStk10(con,depo,tp,uid); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead="STOCKIEST ";
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
		
	}

	public ActionForward HQListRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report10 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo10Form af= (Repo10Form) form;
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
         
        List repo=null;
		SQLRepo10DAO rd = new SQLRepo10DAO();

        int uv = af.getUv();
        int rep = af.getRep_type();
        int emon =af.getEmon();
        int eyear=af.getEyear();

        int i = 1;
        String is=null;
        Integer e;
        
        while (it.hasNext())
        {
          cd = (String)it.next();
          con=datasource.getConnection();
		  repo =rd.getAllStk(con,cd,uv,rep,emon,eyear,depo_code,tp);
		  e= new Integer(i);
		  is= e.toString();
 		  m.put(is, repo);
 		  i++;
        }
        
 		af.setRlist(repo);
        req.setAttribute("rlist", m);
        return mapping.findForward("sucess");      
	}
	
	public ActionForward HQOptRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ OptRepo11 Action class");       
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        Repo11Form af= (Repo11Form) form;
		af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess"); 
	}	

	public ActionForward HQListRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report11 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo11Form af= (Repo11Form) form;
        List repo=null;
		SQLRepo11DAO rd = new SQLRepo11DAO();
		con=datasource.getConnection();
        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
		int st = af.getSale_type();
        repo =rd.getAllStk(con,smon,emon,st,eyear,depo_code,tp,uid);
		af.setRlist(repo);
        req.setAttribute("rlist", repo);
		
		return mapping.findForward("sucess");      
	}	

	public ActionForward HQOptRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List optRepo12 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo12Form af= (Repo12Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();		
    	repo =rd.getStk12(con,depo,tp,uid); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		session.setAttribute("rlist",repo);
		String whead=null;
		Repo12FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo12FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report12 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo12Form af= (Repo12Form) form;
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
        List repo=null;
		SQLRepo12DAO rd = new SQLRepo12DAO();
        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
		
		int i = 1;
        String is=null;
        Integer e;
        while (it.hasNext())
        {
        cd = (String)it.next();
        con=datasource.getConnection();
		repo =rd.getAllStk(con,cd,smon,emon,eyear,depo_code,tp);
		e= new Integer(i);
		is= e.toString();
 		m.put(is, repo);
 		i++;
	    }
 		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
	
	public ActionForward HQOptRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List optRepo13 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo13Form af= (Repo13Form) form;
        af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getStk13(con,depo,tp,uid); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		Repo13FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo13FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report13 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo13Form af= (Repo13Form) form;
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
         
        List repo=null;
		SQLRepo13DAO rd = new SQLRepo13DAO();
        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
        int i = 1;
        String is=null;
        Integer e;
        while (it.hasNext())
        {
        cd = (String)it.next();
        con=datasource.getConnection();
		repo =rd.getAllStk(con,cd,smon,emon,eyear,depo_code,tp);
		e= new Integer(i);
		is= e.toString();
 		m.put(is, repo);
 		i++;
        }
 		af.setRlist(repo);
        req.setAttribute("rlist", m);
        return mapping.findForward("sucess");      
		
	}
	
	public ActionForward HQOptRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        Repo14Form af= (Repo14Form) form;
        af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess");
		
	}

	public ActionForward HQListRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report14 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        String utype = lfb.getType();
        int uid=lfb.getId();
        Repo14Form af= (Repo14Form) form;
        
        List repo=null;
		SQLRepo14DAO rd = new SQLRepo14DAO();
		con=datasource.getConnection();
        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
        repo =rd.getAllstk(con,smon,emon,eyear,depo_code,tp,utype,uid); 
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess"); 
	}		
	
	public ActionForward HQOptRepo15(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List optRepo15 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();	
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo15Form af= (Repo15Form) form;
		af.setYlist(lfb.getYlist());

        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getStk15(con,depo,tp,uid); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead="Stockiest ";
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo15(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report15 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo15Form af= (Repo15Form) form;
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
         
        List repo=null;
		SQLRepo15DAO rd = new SQLRepo15DAO();
        int rep = af.getRep_type();
        int emon =af.getEmon();
        int eyear=af.getEyear();
	    int i = 1;
	    String is=null;
	    Integer e;
        while (it.hasNext())
        {
        cd = (String)it.next();
        con=datasource.getConnection();
		repo =rd.getAllStk(con,cd,rep,emon,eyear,depo_code,tp);
		e= new Integer(i);
		is= e.toString();
 		m.put(is, repo);
 		i++;
        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
        return mapping.findForward("sucess");      
	}

	public ActionForward HQOptRepo16(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List OptRepo16 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo16Form af= (Repo16Form) form;
		af.setYlist(lfb.getYlist());
        
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getStk16(con,depo,tp,uid); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead="Stockiest ";
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo16(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report16 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo16Form af= (Repo16Form) form;
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<String, List<?>> m = new HashMap<String, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        String cd=null; 
         
        List repo=null;
		SQLRepo16DAO rd = new SQLRepo16DAO();

        int rep = af.getRep_type();
        int emon =af.getEmon();
        int eyear=af.getEyear();
	    int i = 1;
	    String is=null; 
	    Integer e;
        while (it.hasNext())
        {
        cd = (String)it.next();
        con=datasource.getConnection();
		repo =rd.getAllStk(con,cd,rep,emon,eyear,depo_code,tp);
		e= new Integer(i);
		is= e.toString();
 		m.put(is, repo);
 		i++;
        }
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
		
	public ActionForward HQOptRepo17(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List optRepo17 Action class");       
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
        Repo17Form af= (Repo17Form) form;
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
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getRepo17Product(con,tp,uid,utype,year); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		Repo17FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
	    r = (Repo17FormBean) (it.next());
	    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		  
		return mapping.findForward("sucess"); 
		
	}

	public ActionForward HQListRepo17(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report17 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo17Form af= (Repo17Form) form;
         
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
		SQLRepo17DAO rd = new SQLRepo17DAO();

        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
        int i=1;
        while (it.hasNext())
        {
          cd = (Integer)it.next();
          con=datasource.getConnection();
 		  repo =rd.getAllrepo(con,cd,smon,emon,eyear,depo_code,tp,uid);
 		  m.put(i, repo);
 		  i++;
        }
        af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}
	
	public ActionForward HQOptRepo18(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List optRepo18 Action class");       
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
        Repo18Form af= (Repo18Form) form;
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
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getRepo18Product(con,tp,year,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		Repo18FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo18FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}

	public ActionForward HQListRepo18(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report18 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        Repo18Form af= (Repo18Form) form;

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
		SQLRepo18DAO rd = new SQLRepo18DAO();
		int emon =af.getEmon();
        int eyear=af.getEyear();
        int uv = af.getUv();
        int rtype=af.getRep_type();
         
        int i=1;
        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 		repo =rd.getAllHQ(con,cd,uv,rtype,emon,eyear,depo_code,tp,uid);
 		m.put(i, repo);
 		i++;
        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}
	

	
}