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
import com.aristo.form.Repo2Form;
import com.aristo.hq.SQLRepo2DAO;
import com.aristo.hq.SQLRepo2aDAO;
import com.aristo.hq.SQLRepo3DAO;
import com.aristo.hq.SQLRepo4DAO;
import com.aristo.hq.SQLRepo6DAO;
import com.aristo.hq.SQLRepo7DAO;
import com.aristo.hq.SQLRepo8DAO;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.MktFormBean;
import com.aristo.valueobject.Repo2FormBean;

public class HQRepoAction extends DispatchAction {

	public ActionForward HQOptRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    int uid=lfb.getId();
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		List repo=null;
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        
		
	    SQLOptDAO rd1=new SQLOptDAO();
	    con=datasource.getConnection();
		repo =rd1.getHQ(con,depo_code,tp,uid);
		
		  Iterator it = repo.iterator();
		  if (it.hasNext())
		  {
			  MktFormBean mk = (MktFormBean) it.next();
			  af.setCode(mk.getQty2());
		  }
        af.setXlist(repo);
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess");
		
	}
	
	public ActionForward HQListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report2 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode(); 
        int uid=lfb.getId();
        String utype = lfb.getType();
        String tp = lfb.getD_type();
        Repo2Form af= (Repo2Form) form;
        List repo=null;
		SQLRepo2DAO rd = new SQLRepo2DAO();

        int uv = af.getUv();
		int saletp = af.getSale_type();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int code=af.getCode();
        int opt = af.getOpt();
        
        String sr=req.getParameter("search");
        
        if (sr==null)
		{
            session.setAttribute("uv", af.getUv());
            session.setAttribute("smon",af.getMnth());
            session.setAttribute("emon",af.getMnth1());
            session.setAttribute("saletp",af.getSale_type());
            session.setAttribute("eyear",af.getEyear());
            session.setAttribute("code",af.getCode()); 
            session.setAttribute("opt",af.getOpt());       
		}
		else
		{
			uv=(Integer) session.getAttribute("uv");
			smon=(Integer) session.getAttribute("smon");
			emon=(Integer) session.getAttribute("emon");
			saletp=(Integer) session.getAttribute("saletp");
			eyear=(Integer) session.getAttribute("eyear");			
			code=(Integer) session.getAttribute("code");			
            opt = (Integer) session.getAttribute("opt");       
		}

        if (code==0)
        {
    	  return mapping.findForward("fail");        	
        }
        String whead=null;
	    con=datasource.getConnection();		
       	repo=rd.getAllrepo(con, code, uv, smon, emon, saletp, eyear, depo_code, tp, uid, utype,sr,opt);   
		
		req.setAttribute("head", whead);
    	af.setRlist(repo);
		req.setAttribute("rlist", repo);
    	return mapping.findForward("sucess");
	    	
	}	
	
	public ActionForward HQOptRepo2a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    int uid=lfb.getId();
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		List repo=null;
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
	    SQLOptDAO rd1=new SQLOptDAO();
	    con=datasource.getConnection();		
        repo =rd1.getHQ(con, depo_code, tp,uid);
        
        Iterator it = repo.iterator();
		  if (it.hasNext())
		  {
			  MktFormBean mk = (MktFormBean) it.next();
			  af.setCode(mk.getQty2());
		  }
        af.setXlist(repo);
        
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess");		
		
	}
		
	public ActionForward HQListRepo2a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report2 Action class");       
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
        int uid=lfb.getId();
        String utype = lfb.getType();        
        Repo2Form af= (Repo2Form) form;
        
        List repo=null;
		SQLRepo2aDAO rd = new SQLRepo2aDAO();

		int saletp = af.getSale_type();
		int rs=af.getR_type();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int code=af.getCode();
        int opt = af.getOpt();
        
        String sr=req.getParameter("search");
        
        if (sr==null)
		{
        	session.setAttribute("saletp",af.getSale_type());
        	session.setAttribute("rs",af.getR_type());        	
        	session.setAttribute("smon",af.getMnth());
            session.setAttribute("emon",af.getMnth1());
            session.setAttribute("eyear",af.getEyear());
            session.setAttribute("code",af.getCode());   
            session.setAttribute("opt",af.getOpt());       
		}
		else
		{
			saletp=(Integer) session.getAttribute("saletp");
			rs=(Integer) session.getAttribute("rs");			
			smon=(Integer) session.getAttribute("smon");
			emon=(Integer) session.getAttribute("emon");
			eyear=(Integer) session.getAttribute("eyear");			
			code=(Integer) session.getAttribute("code");
			opt = (Integer) session.getAttribute("opt");       
		}
        
        if (code==0)
        {
    	  return mapping.findForward("fail");        	
        }        
        
		String whead=null;
		con=datasource.getConnection();
		repo=rd.getAllrepo(con, code, smon, emon, rs, saletp, eyear, depo_code, tp, uid, utype,sr,opt);
        
		req.setAttribute("head", whead);
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess");
	}
	
	public ActionForward HQOptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess");
		
	}	
	
	public ActionForward HQListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report3 Action class");       
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
        int uid=lfb.getId();
        String utype = lfb.getType();
        Repo2Form af= (Repo2Form) form;
        List repo=null;
		SQLRepo3DAO rd = new SQLRepo3DAO();
		con=datasource.getConnection();
        int smon=af.getMnth();
        int emon=af.getMnth1();
        int eyear=af.getEyear();
      
		repo =rd.getAllrepo(con,smon,emon,eyear,depo_code,tp,uid,utype); 
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQOptRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    int uid=lfb.getId();
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
	    List repo=null;
	    List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
	    repo =rd.getHQRepo(con,depo_code,tp,uid); 
			
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);

		return mapping.findForward("sucess");
		
	}
	
	public ActionForward HQListRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report4 Action class");       
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
        int uid=lfb.getId();
        String utype = lfb.getType();
        Repo2Form af= (Repo2Form) form;

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
		SQLRepo4DAO rd = new SQLRepo4DAO();
		int smon =af.getMnth();
		int emon =af.getMnth1();
        int eyear=af.getEyear();
		int bm = af.getPg();
        int i=1;
        while (it.hasNext())
        {
          cd = (Integer)it.next();
          con=datasource.getConnection();
 		  repo =rd.getAllrepo(con,bm,cd,smon,emon,eyear,depo_code,tp,uid,utype);
 		  m.put(i, repo);
 		  i++;
        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
	
	public ActionForward HQOptRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-OptRepo6 Action class");       
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
        
        Repo2Form af= (Repo2Form) form;
        af.setYlist(null);
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
    	repo =rd.getAllProduct(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		Repo2FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo2FormBean) (it.next());
		    whead = r.getNm3();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
		
	}

	public ActionForward HQListRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report6 Action class");       
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
        Repo2Form af= (Repo2Form) form;
         
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
		SQLRepo6DAO rd = new SQLRepo6DAO();
        int smon =af.getMnth();
        int emon =af.getMnth1();
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

	public ActionForward HQOptRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        Repo2Form af= (Repo2Form) form;
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
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		Repo2FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo2FormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}	
	
	public ActionForward HQListRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report7 Action class");       
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
        int uid=lfb.getId();
        String utype = lfb.getType();        
        
        Repo2Form af= (Repo2Form) form;
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
		SQLRepo7DAO rd = new SQLRepo7DAO();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear(); 
        int i=1;
        
        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 		repo =rd.getAllrepo(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
		m.put(i, repo);
 		i++;
        }
        
	 	af.setRlist(repo);
        req.setAttribute("rlist", m);
     	return mapping.findForward("sucess");      
		
	}
		
	public ActionForward HQOptRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        Repo2Form af= (Repo2Form) form;
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
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllProduct(con,tp,year,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		
		String whead=null;
		Repo2FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (Repo2FormBean) (it.next());
		    whead = r.getNm3();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}	

	public ActionForward HQListRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-List Report8 Action class");       
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
        Repo2Form af= (Repo2Form) form;

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
		SQLRepo8DAO rd = new SQLRepo8DAO();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int uv = af.getUv();
        int stype= af.getSale_type();
        int i=1;
        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 		repo =rd.getAllHQ(con,cd,uv,emon,stype,eyear,depo_code,tp,uid);
 		m.put(i, repo);
 		i++;
        }
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}	
	
	
}