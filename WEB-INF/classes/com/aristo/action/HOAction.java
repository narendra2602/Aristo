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

import com.aristo.dao.SQLHOOptDAO;
import com.aristo.dao.SQLHORepo11DAO;
import com.aristo.dao.SQLHORepo1DAO;
import com.aristo.dao.SQLHORepo2DAO;
import com.aristo.dao.SQLHORepo3DAO;
import com.aristo.dao.SQLHORepo5DAO;
import com.aristo.dao.SQLHORepo6DAO;
import com.aristo.dao.SQLHORepo7DAO;
import com.aristo.dao.SQLHORepo8DAO;
import com.aristo.dao.SQLHORepo9DAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.form.HORepo1Form;
import com.aristo.form.HORepo2Form;
import com.aristo.form.HORepo3Form;
import com.aristo.form.HORepo5Form;
import com.aristo.form.HORepo6Form;
import com.aristo.form.HORepo7Form;
import com.aristo.form.HORepo8Form;
import com.aristo.form.HORepo9Form;
import com.aristo.form.Repo18Form;
import com.aristo.valueobject.HORepo6FormBean;
import com.aristo.valueobject.HORepo7FormBean;
import com.aristo.valueobject.HORepo8FormBean;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.Repo18FormBean;

public class HOAction extends DispatchAction {
	
/////////////////////////////////////HO Option 1 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		    

			HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }

	        HORepo1Form af= (HORepo1Form) form;
	        
	        af.setYlist(lfb.getYlist());
		    return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO Option 1 End////////////////////////////////////////////////////	

/////////////////////////////////////HO List 1 Start////////////////////////////////////////////////////    	
	
	public ActionForward HOListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo1 Action class");       

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
        //int div_code = lfb.getDiv_code();
        //int utype = Integer.parseInt(lfb.getOpt());
        int code = lfb.getCode();
        System.out.println("depo code is "+code);
        
        HORepo1Form af= (HORepo1Form) form;
        
        List repo=null;
        SQLHORepo1DAO rd = new SQLHORepo1DAO();
        con=datasource.getConnection();
        int smon = af.getSmon();
        int emon = af.getEmon();
		int saletp = af.getSale_type();
		int uv = af.getUv();
		int eyear=af.getEyear();
		String sr=af.getSearch();	
	//	code = af.getDepo_code();

		if (sr==null)
		{
            session.setAttribute("smon",af.getSmon());
            session.setAttribute("emon",af.getEmon());
            session.setAttribute("saletp",af.getSale_type());
            session.setAttribute("uv", af.getUv());
            session.setAttribute("eyear",af.getEyear());
		}
		else
		{
			uv=(Integer) session.getAttribute("uv");
			smon=(Integer) session.getAttribute("smon");
			emon=(Integer) session.getAttribute("emon");
			saletp=(Integer) session.getAttribute("saletp");
			eyear=(Integer) session.getAttribute("eyear");			
		}		

         repo =rd.getAllBranchOld(con,uv,smon,emon,saletp,tp,typ,code1,loginid,eyear,sr);
//         repo =rd.getAllBranch(con,uv,smon,emon,saletp,div_code,utype,code,loginid,eyear,sr);
	     af.setRlist(repo);
	     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////HO List 1 End////////////////////////////////////////////////////	
	
	
/////////////////////////////////////HO Option 2 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		 HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        HORepo2Form af= (HORepo2Form) form;
	        af.setYlist(lfb.getYlist());

	        return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO Option 2 End////////////////////////////////////////////////////	
	
/////////////////////////////////////HO List 2 Start////////////////////////////////////////////////////    	
	
	public ActionForward HOListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo2 Action class");       

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

         
        HORepo2Form af= (HORepo2Form) form;
        
        List repo=null;
        SQLHORepo2DAO rd = new SQLHORepo2DAO();
        con=datasource.getConnection();
        int smon = af.getSmon();
        int emon = af.getEmon();
		int saletp = af.getSale_type();
		int eyear=af.getEyear();
		
        repo =rd.getAllBranch(con,smon,emon,saletp,tp,typ,code1,loginid,eyear);
        af.setRlist(repo);
        req.setAttribute("rlist", repo); 
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////HO List 2 End////////////////////////////////////////////////////	
	
/////////////////////////////////////HO Option 3 Start//////////////////////////////////////////////////// 
	
	public ActionForward HOOptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		 HttpSession session=req.getSession();
	     LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        HORepo3Form af= (HORepo3Form) form;
	        af.setYlist(lfb.getYlist());

	        return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO Option 3 End////////////////////////////////////////////////////

/////////////////////////////////////HO List 3 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo3 Action class");       

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
         
        HORepo3Form af= (HORepo3Form) form;
        
        List repo=null;
		SQLHORepo3DAO rd = new SQLHORepo3DAO();
		con=datasource.getConnection();
        int emon = af.getEmon();
        int smon = af.getSmon();
        int rs = af.getR_type();
        int eyear=af.getEyear();
      
		   	  repo =rd.getAllBranch(con,smon,emon,rs,tp,typ,code1,loginid,eyear); 
          	  af.setRlist(repo);
  			  req.setAttribute("rlist", repo);
  	    	  return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO List 3 End////////////////////////////////////////////////////	
	
	
/////////////////////////////////////HO Option 5//////////////////////////////////////////////////// 
	
	
	public ActionForward HOOptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		 HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        HORepo5Form af= (HORepo5Form) form;
	        af.setYlist(lfb.getYlist());

	        return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO Option 5 End////////////////////////////////////////////////////

/////////////////////////////////////HO List 5 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo5 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo_code=lfb.getCode(); 
        String typ = lfb.getType();
        int code1 = lfb.getDcode();
        int loginid = lfb.getId();
        String tp = lfb.getD_type();
        HORepo5Form af= (HORepo5Form) form;
        
        List repo=null;
		SQLHORepo5DAO rd = new SQLHORepo5DAO();
		con=datasource.getConnection();        
        int smon = af.getSmon();
        int emon = af.getEmon();
        int rs = af.getR_type();
        int eyear=af.getEyear();
		   	  repo =rd.getAllBranch(con,smon,emon,rs,depo_code,tp,typ,code1,loginid,eyear); 
          	  af.setRlist(repo);
  			  req.setAttribute("rlist", repo);
  	    	  return mapping.findForward("sucess");
		
	}
/////////////////////////////////////HO List 5 End////////////////////////////////////////////////////		
	
/////////////////////////////////////HO Option 6 Start//////////////////////////////////////////////////// 
	
	
	public ActionForward HOOptRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptRepo6 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
 
        String tp = lfb.getD_type();

         
        HORepo6Form af= (HORepo6Form) form;
        af.setYlist(lfb.getYlist());
        int uid=lfb.getId();
        String utype = lfb.getType();
        
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllProduct(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo6FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		   r=(HORepo6FormBean) (it.next());
		   whead=r.getHead1();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
		
	}
/////////////////////////////////////HO Option 6 End////////////////////////////////////////////////////

/////////////////////////////////////HO List 6 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport6 Action class");       

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
         
        HORepo6Form af= (HORepo6Form) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo6DAO rd = new SQLHORepo6DAO();
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,cd,smon,emon,tp,typ,code1,loginid,eyear);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}

/////////////////////////////////////HO List 6 End////////////////////////////////////////////////////		
	
/////////////////////////////////////HO Option 7 Start//////////////////////////////////////////////////// 
	
	
	public ActionForward HOOptRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptRepo7 Action class");       

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
         
        HORepo7Form af= (HORepo7Form) form;
        af.setYlist(lfb.getYlist());  
        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
        
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo7FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (HORepo7FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
/////////////////////////////////////HO Option 7 End////////////////////////////////////////////////////
	public ActionForward HOAjaxRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOAjaxRepo7 Action class");       

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

        HORepo7Form af= (HORepo7Form) form;
        int eyear=Integer.parseInt(req.getParameter("q"));
        List repo=null;
        List repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo); 
		req.setAttribute("rlist",repo);
		req.setAttribute("opt", "HOpt7");
		return mapping.findForward("sucess");
	}
/////////////////////////////////////HO List 7 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport7 Action class");       

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
         
        HORepo7Form af= (HORepo7Form) form;
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());

        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo7DAO rd = new SQLHORepo7DAO();
		
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear();
        int rtp=af.getR_type();
        int ropt=af.getRepoptn();
        
        
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		if(ropt==1)
        		 			repo =rd.getAllBranch(con,cd,smon,emon,tp,typ,code1,loginid,eyear,rtp);
        		 		else
        		 			repo =rd.getAllBranchPMT(con,cd,smon,emon,tp,typ,loginid,code1,eyear,rtp);
        		 			
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
     	if (ropt==1)
     		return mapping.findForward("sucess");
     	else
     		return mapping.findForward("sucesstrend");
		
	}
	

/////////////////////////////////////HO List 7 End////////////////////////////////////////////////////		
	
/////////////////////////////////////HO Option 8 Start//////////////////////////////////////////////////// 	

	public ActionForward HOOptRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptRepo8 Action class");       

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
        
        HORepo8Form af= (HORepo8Form) form;
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
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getRepo8Product(con,tp,year,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		HORepo8FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (HORepo8FormBean) (it.next());
		    whead = r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}	
/////////////////////////////////////HO Option 8 End////////////////////////////////////////////////////	

/////////////////////////////////////HO List Repo8 Start////////////////////////////////////////////	
	
	public ActionForward HOListRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo8 Action class");       

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
                 
        HORepo8Form af= (HORepo8Form) form;
        af.getRlist().clear();
        
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());

        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        List<?> repo=null;
		SQLHORepo8DAO rd = new SQLHORepo8DAO();
        int emon = af.getEmon();
        int uv = af.getUv();
        int rs=af.getR_type();    
        int eyear=af.getEyear();
            int i=1;
        	        while (it.hasNext())
        	        {
        	          cd = it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllBranch(con,cd,uv,rs,emon,tp,typ,loginid,code1,eyear);
             		  m.put(i, repo);
             		  i++;
        	        }
			  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		
		return mapping.findForward("sucess");      
		
	}
//////////////////////////////////////////////HO List Repo8 End////////////////////////////////////////////	
	
//////////////////////////////////////HO Opt 9 Start/////////////////////////////////////////////////////////
	
	public ActionForward HOOptRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptRepo9 Action class");       

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        HORepo9Form af= (HORepo9Form) form;
        af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess"); 
			
	}	
/////////////////////////////////////HO Opt 9 End//////////////////////////////////////////////////////	
		
	
/////////////////////////////////////HO List Repo9 Start////////////////////////////////////////////	
	
	public ActionForward HOListRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport9 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
   
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
         
        HORepo9Form af= (HORepo9Form) form;

        List repo=null;
		SQLHORepo9DAO rd = new SQLHORepo9DAO();
		con=datasource.getConnection();
        int emon = af.getEmon();
        int st = af.getSale_type();
        int rs = af.getR_type();
        int eyear=af.getEyear();
        repo =rd.getAllStk(con,emon,loginid,rs,st,tp,eyear);
          
		af.setRlist(repo);
        req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess");      
		
	}	
//////////////////////////////////////////////HO List Repo9 End////////////////////////////////////////////	
	

/////////////////////////////////////HO Option 10 Start//////////////////////////////////////////////////// 
	public ActionForward HOOptRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOOptRepo10 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        HORepo6Form af= (HORepo6Form) form;
        af.setYlist(lfb.getYlist());
        int uid=lfb.getId();
        String utype = lfb.getType();
        
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllProduct(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo6FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		   r=(HORepo6FormBean) (it.next());
		   whead=r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
/////////////////////////////////////HO Option 10 End////////////////////////////////////////////////////
	
/////////////////////////////////////HO List 10 Start////////////////////////////////////////////////////	
	
	public ActionForward HOListRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport10 Action class");       

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
         
        HORepo6Form af= (HORepo6Form) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo6DAO rd = new SQLHORepo6DAO();
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllHQ(con,cd,smon,emon,tp,typ,code1,loginid,eyear);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}

/////////////////////////////////////HO List 10 End////////////////////////////////////////////////////		
	
	public ActionForward HOOptRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List HOOptRepo11 Action class");       
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        
	    HORepo8Form af= (HORepo8Form) form;
        af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess"); 
	}		
	
	
	public ActionForward HOListRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOListRepo11 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        String whead = null;
        int loginid = lfb.getId();
                 
        HORepo8Form af= (HORepo8Form) form;
        SQLHORepo11DAO rd = new SQLHORepo11DAO();
        con=datasource.getConnection();        
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear();
        whead="HO RUPEE WISE HQ-WISE TREND";  
	    List repo=null;
        repo =rd.getAllBranch(con,smon,emon,tp,loginid,eyear);

	    af.setRlist(repo);
	    req.setAttribute("rlist", repo);
	    req.setAttribute("head", whead);
		return mapping.findForward("sucess");      
		
	}	
	
	public ActionForward HOOptRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOOptRepo12 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        HORepo8Form af= (HORepo8Form) form;
        af.setYlist(lfb.getYlist());
        int uid=lfb.getId();
        String utype = lfb.getType();
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllProduct12(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo8FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		   r=(HORepo8FormBean) (it.next());
		   whead=r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HOListRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport12 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
        HORepo8Form af= (HORepo8Form) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo11DAO rd = new SQLHORepo11DAO();
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
        int uv=af.getUv();
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllHQ(con,cd,smon,emon,tp,loginid,eyear,uv);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}

	public ActionForward HOOptRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOOptRepo13 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        HORepo8Form af= (HORepo8Form) form;
        af.setYlist(lfb.getYlist());
        int uid=lfb.getId();
        String utype = lfb.getType();
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup13(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo8FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		   r=(HORepo8FormBean) (it.next());
		   whead=r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HOListRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HOReport13 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
        HORepo8Form af= (HORepo8Form) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo11DAO rd = new SQLHORepo11DAO();
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllHQGroup(con,cd,smon,emon,tp,loginid,eyear);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}
	
	public ActionForward HOOptRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("Calleed NEW HOOptRepo14 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        HORepo8Form af= (HORepo8Form) form;
        af.setYlist(lfb.getYlist());
        int uid=lfb.getId();
        String utype = lfb.getType();
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List<?> repo1= new ArrayList();
		SQLHOOptDAO rd = new SQLHOOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllGroup13(con,tp,year,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead=null;
		HORepo8FormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		   r=(HORepo8FormBean) (it.next());
		   whead=r.getHead1();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HOListRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed NEW HOReport14 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
        HORepo8Form af= (HORepo8Form) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List<Integer> code = af.getRlist();
        Iterator<Integer> it = code.iterator();
        int cd=0; 
        
        List<?> repo=null;
		SQLHORepo11DAO rd = new SQLHORepo11DAO();
        int smon = af.getSmon();
        int emon = af.getEmon();
        int eyear=af.getEyear(); 
            int i=1;
				 	while (it.hasNext())
        		 	{
        		 		cd = it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllStkGroup(con,cd,smon,emon,tp,loginid,eyear);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
		
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}
	
	
	
	
	
	
	
}
