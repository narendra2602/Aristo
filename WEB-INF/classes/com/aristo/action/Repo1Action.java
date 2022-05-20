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

import com.aristo.dao.DAOFactory;
import com.aristo.dao.Repo1DAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.dao.SQLRepo10DAO;
import com.aristo.dao.SQLRepo11DAO;
import com.aristo.dao.SQLRepo12DAO;
import com.aristo.dao.SQLRepo13DAO;
import com.aristo.dao.SQLRepo14DAO;
import com.aristo.dao.SQLRepo15DAO;
import com.aristo.dao.SQLRepo16DAO;
import com.aristo.dao.SQLRepo17DAO;
import com.aristo.dao.SQLRepo18DAO;
import com.aristo.dao.SQLRepo2DAO;
import com.aristo.dao.SQLRepo2aDAO;
import com.aristo.dao.SQLRepo3DAO;
import com.aristo.dao.SQLRepo4DAO;
import com.aristo.dao.SQLRepo5DAO;
import com.aristo.dao.SQLRepo6DAO;
import com.aristo.dao.SQLRepo7DAO;
import com.aristo.dao.SQLRepo8DAO;
import com.aristo.dao.SQLRepo9DAO;
import com.aristo.form.Repo10Form;
import com.aristo.form.Repo11Form;
import com.aristo.form.Repo12Form;
import com.aristo.form.Repo13Form;
import com.aristo.form.Repo14Form;
import com.aristo.form.Repo15Form;
import com.aristo.form.Repo16Form;
import com.aristo.form.Repo17Form;
import com.aristo.form.Repo18Form;
import com.aristo.form.Repo1Form;
import com.aristo.form.Repo2Form;
import com.aristo.form.Repo9Form;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.Repo12FormBean;
import com.aristo.valueobject.Repo13FormBean;
import com.aristo.valueobject.Repo17FormBean;
import com.aristo.valueobject.Repo18FormBean;
import com.aristo.valueobject.Repo2FormBean;


public class Repo1Action extends DispatchAction {
	
	public ActionForward ListRepo(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report Action class");       

        DataSource datasource = null; 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
        Repo1Form af= (Repo1Form) form;
		int eyear=af.getEyear();
		Repo1DAO  rd= DAOFactory.getRepo1();
		try{
        datasource = this.getDataSource(req,"userDB"); 
	    con=datasource.getConnection();

		List repo =rd.getAllrepo(con,tp,loginid,eyear);
		String whead=" BRANCH WISE RUPEES WISE NET SALES FOR THE YEAR "+eyear+"-"+(eyear+1);
		req.setAttribute("head", whead);
		af.setRlist(repo);
		}catch(Exception e)
		{
			System.out.println("Error in get Connection Repo14 "+e);
		}
		finally{
			      try{
			    	   datasource=null;
			    	   con=null;
			    	   
			      }catch(Exception e)
			      {
			    	  System.out.println("Connection & datasource null nahi hue Repo 14"+e);
			      }
		}
		return mapping.findForward("sucess");
		
	} 
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward ListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report2 Action class");       

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

        int opt = af.getRep_type();
        int uv = af.getUv();
		int saletp = af.getSale_type();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int code=af.getCode();
        
        String sr=req.getParameter("search");
        
        if (sr==null)
		{
            session.setAttribute("uv", af.getUv());
            session.setAttribute("smon",af.getMnth());
            session.setAttribute("emon",af.getMnth1());
            session.setAttribute("saletp",af.getSale_type());
            session.setAttribute("opt",af.getRep_type());
            session.setAttribute("eyear",af.getEyear());
            session.setAttribute("code",af.getCode());            
		}
		else
		{
			opt= (Integer) session.getAttribute("opt");
			uv=(Integer) session.getAttribute("uv");
			smon=(Integer) session.getAttribute("smon");
			emon=(Integer) session.getAttribute("emon");
			saletp=(Integer) session.getAttribute("saletp");
			eyear=(Integer) session.getAttribute("eyear");			
			code=(Integer) session.getAttribute("code");			
		}
        
        if ((code==0) && (opt<4))
        {
    	  return mapping.findForward("fail");        	
        }

        String whead=null;
	    con=datasource.getConnection();		
		switch(opt)
         {
        	 case 1:
                   	repo=rd.getAllrepo(con, code, uv, smon, emon, saletp, eyear, depo_code, tp, uid, utype,sr);   
        		 	break;
        	 case 2: 
					repo=rd.getAllregion(con, code, uv, smon, emon, saletp, eyear, depo_code, tp, uid, utype,sr);
        		    break;
        	 case 3:
        		 	repo=rd.getAllArea(con, code, uv, smon, emon, saletp, eyear, depo_code, tp, uid, utype,sr);
        		 	break;
        	 case 4: 
        		    repo =rd.getAllBranch(con,uv,smon,emon,saletp,eyear,depo_code,tp,uid,utype,sr);
			        break;
         }
		
			req.setAttribute("head", whead);
	    	af.setRlist(repo);
			req.setAttribute("rlist", repo);
	    	return mapping.findForward("sucess");
	    	
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////    	
	
	public ActionForward ListRepo2a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report2 Action class");       

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

        int opt = af.getRep_type();
		int saletp = af.getSale_type();
		int rs=af.getR_type();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int code=af.getCode();

        String sr=req.getParameter("search");
        
        if (sr==null)
		{
        	session.setAttribute("opt",af.getRep_type());
        	session.setAttribute("saletp",af.getSale_type());
        	session.setAttribute("rs",af.getR_type());        	
        	session.setAttribute("smon",af.getMnth());
            session.setAttribute("emon",af.getMnth1());
            session.setAttribute("eyear",af.getEyear());
            session.setAttribute("code",af.getCode());            
		}
		else
		{
			opt= (Integer) session.getAttribute("opt");
			saletp=(Integer) session.getAttribute("saletp");
			rs=(Integer) session.getAttribute("rs");			
			smon=(Integer) session.getAttribute("smon");
			emon=(Integer) session.getAttribute("emon");
			eyear=(Integer) session.getAttribute("eyear");			
			code=(Integer) session.getAttribute("code");			
		}
        
        if ((code==0) && (opt<4))
        {
    	  return mapping.findForward("fail");        	
        }        
        
		String whead=null;
		con=datasource.getConnection();
		switch(opt)
         {
        	 case 1:
        		 	repo=rd.getAllrepo(con, code, smon, emon, rs, saletp, eyear, depo_code, tp, uid, utype,sr);
        		 	break;
        	 case 2:
        		 	repo=rd.getAllGRegion(con, code, smon, emon, rs, saletp, eyear, depo_code, tp, uid, utype,sr);
    			    break;
        	 case 3:
        		 	repo=rd.getAllGArea(con, code, smon, emon, rs, saletp, eyear, depo_code, tp, uid, utype,sr);
			        break;
        	 case 4: 
                    repo =rd.getAllGBranch(con,smon,emon,rs,saletp,eyear,depo_code,tp,uid,utype,sr);
			        break;
         }
		
		  req.setAttribute("head", whead);
		  af.setRlist(repo);
		  req.setAttribute("rlist", repo);
		  return mapping.findForward("sucess");
		      
	}

	

	
/////////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward ListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report3 Action class");       

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
        int opt = af.getRep_type();
        int smon=af.getMnth();
        int emon=af.getMnth1();
        int eyear=af.getEyear();
      
		switch(opt)
         {
        	 case 1:
        			repo =rd.getAllrepo(con,smon,emon,eyear,depo_code,tp,uid,utype); 
        			break;
        	 case 2:
        			repo =rd.getAllRegion(con,smon,emon,eyear,depo_code,tp,uid,utype); 
        			break; 
        	 case 3:
        		 	repo =rd.getAllArea(con,smon,emon,eyear,depo_code,tp,uid,utype); 
			        break;
        	 case 4: 
        		 	repo =rd.getAllBranch(con,smon,emon,eyear,depo_code,tp,uid,utype); 
			        break;
         }
		
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
		return mapping.findForward("sucess"); 
		
	}
	
	public ActionForward ListRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report4 Action class");       

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
		
		int opt = af.getRep_type();
		int smon =af.getMnth();
		int emon =af.getMnth1();
        int eyear=af.getEyear();
		int bm = af.getPg();
 
        int i=1;
		switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllrepo(con,bm,cd,smon,emon,eyear,depo_code,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	        }
        		 
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllRegion(con,bm,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
    	              	
        		 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,bm,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
	              	
        		 	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,bm,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
              	
        		 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
        
		return mapping.findForward("sucess");      
		
	}

	
	
	
	public ActionForward ListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report5 Action class");       

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
		SQLRepo5DAO rd = new SQLRepo5DAO();
		con=datasource.getConnection();
		int opt = af.getRep_type();
        int smon =af.getMnth();
        int emon =af.getMnth1() ;
        int eyear=af.getEyear();

        switch(opt)
         {
        	 case 1:
        		   SQLOptDAO rd1=new SQLOptDAO();
                   repo =rd1.getAllRegion(con, depo_code, tp);
                   session.setAttribute("smon",new Integer (smon));
                   session.setAttribute("emon",new Integer (emon));
                   session.setAttribute("eyear",new Integer (eyear));
                   af.setXlist(repo);
        		   break; 
        	 case 2:
	        	   SQLOptDAO rd2=new SQLOptDAO();
	               repo =rd2.getAllArea(con, depo_code, tp);
	               session.setAttribute("smon",new Integer (smon));
	               session.setAttribute("emon",new Integer (emon));
                   session.setAttribute("eyear",new Integer (eyear));	               
                   af.setXlist(repo);
       	           break;
        	 case 3: 
        			repo =rd.getAllrepo(con,smon,emon,eyear,depo_code,tp,uid,utype); 
        			break;
         }
				

		if (opt==1)
  		     return mapping.findForward("option"); 
  	      
		if (opt==2)
 		     return mapping.findForward("area"); 

		else
  	    	  
  	      {
  	    	  af.setRlist(repo);
  			  req.setAttribute("rlist", repo);
  		   	 
  	    	 return mapping.findForward("sucess");
  	      }            
		
	}
	
	public ActionForward ListRepo5HQ(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Repo5HQ Action class");       

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
		SQLRepo5DAO rd = new SQLRepo5DAO();
		con=datasource.getConnection();
        int smon =(Integer) session.getAttribute("smon");
        int emon =(Integer) session.getAttribute("emon");
        int eyear =(Integer) session.getAttribute("eyear");
        int code =af.getCode();
        
        repo =rd.getAllRegion(con,code,smon,emon,eyear,depo_code,tp,uid,utype);
		
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
   	 
	 	return mapping.findForward("sucess");	
	}
	
	
	public ActionForward ListRepo5Region(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Repo5Region Action class");       

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
		SQLRepo5DAO rd = new SQLRepo5DAO();
		con=datasource.getConnection();
        int smon =(Integer) session.getAttribute("smon");
        int emon =(Integer) session.getAttribute("emon");
        int eyear =(Integer) session.getAttribute("eyear");
        int code =af.getCode();
        
        repo =rd.getAllArea(con,code,smon,emon,eyear,depo_code,tp,uid,utype);
		
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
   	 
	 	return mapping.findForward("sucess");	
	}	

	
	public ActionForward ListRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report6 Action class");       

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
         
        Repo2Form af= (Repo2Form) form;
        // Populate chosen list from hidden field.
         
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
        int opt = af.getRep_type();  
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();

        int i=1;
		switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllrepo(con,cd,smon,emon,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
        		 
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllRegion(con,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		
		return mapping.findForward("sucess");      
		
	}
	

	
	public ActionForward ListRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report7 Action class");       

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
		SQLRepo7DAO rd = new SQLRepo7DAO();
        
        int opt = af.getRep_type();  
      
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear(); 
        int i=1;
		switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllrepo(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
            		  m.put(i, repo);
             		  i++;
        	        }
        		 
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllRegion(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
         } 
		
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
          
		return mapping.findForward("sucess");      
		
	}
	
	
//////////  Report No. 8 Action Class defined here///////////////////

	public ActionForward ListRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report8 Action class");       

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
         
         Repo2Form af= (Repo2Form) form;

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
		SQLRepo8DAO rd = new SQLRepo8DAO();
        int opt = af.getRep_type();  
        int emon =af.getMnth1();
        int eyear=af.getEyear();

        int uv = af.getUv();
        int stype= af.getSale_type();
         
            int i=1;
		switch(opt)
         {
        	 case 1:
                    while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllHQ(con,cd,uv,emon,stype,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
        			break; 
        	 case 2:
        	       while (it.hasNext())
	       	        {
	       	          cd = (Integer)it.next();
	       	          con=datasource.getConnection();
            		  repo =rd.getAllRegion(con,cd,uv,emon,stype,eyear,depo_code,tp);
            		  m.put(i, repo);
            		  i++;
	       	        }
       		 		break; 
       		 		
        	 case 3:
	      	       while (it.hasNext())
	      	        {
	      	          cd = (Integer)it.next();
	      	          con=datasource.getConnection();
		       		  repo =rd.getAllArea(con,cd,uv,emon,stype,eyear,depo_code,tp);
		       		  m.put(i, repo);
		       		  i++;
	      	        }
			        break;
			        
        	 case 4: 
	      	       while (it.hasNext())
	      	        {
	      	          cd = (Integer)it.next();
	      	          con=datasource.getConnection();
		       		  repo =rd.getAllBranch(con,cd,uv,emon,stype,eyear,depo_code,tp);
		       		  m.put(i, repo);
		       		  i++;
	      	        }
	      	       break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
	
///////////// /// End of  Report No. 8 Action Class defined here///////////////////
	
	public ActionForward OptRepo(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	    if (lfb==null)
	
	    {
	    	return mapping.findForward("sfail");
	    }
	    Repo1Form af= (Repo1Form) form;
		af.setYlist(lfb.getYlist());
	
		return mapping.findForward("sucess");
		
	}
	
	public ActionForward OptSelect(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optSelect Action class");       

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
         
        Repo2Form af= (Repo2Form) form;
//        af.setYlist(lfb.getYlist());

        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
        int opt = af.getRep_type();
		con=datasource.getConnection();
        switch(opt)
         {
        	 case 1:
        			repo =rd.getAllrepo(con,depo_code,tp); 
        			break; 
        	 case 2:
        		 	repo =rd.getAllRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
     		 		repo =rd.getAllArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
        		 	repo =rd.getAllBranch(con,depo_code,tp);  
			        break;
         }
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		af.setRep_type(opt);
        session.setAttribute("option", new Integer(opt));

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

	
	
	public ActionForward OptRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		List repo=null;
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();

		int opt;
		if (req.getParameter("search")==null)
			opt=1;
		else
			opt = Integer.parseInt(req.getParameter("search"));
		
	    SQLOptDAO rd1=new SQLOptDAO();
	    con=datasource.getConnection();		
		switch(opt)
         {
        	 case 1:
                     repo =rd1.getAllrepo(con, depo_code, tp);
                     af.setXlist(repo);
        			 break;
        	 case 2: 
	                 repo =rd1.getAllRegion(con, depo_code, tp);
	                 af.setXlist(repo);
    			     break;
        	 case 3:
	                 repo =rd1.getAllArea(con, depo_code, tp);
	                 af.setXlist(repo);
			         break;
         }
		
		 req.setAttribute("rlist", repo);
		if (req.getParameter("search")==null)
			return mapping.findForward("sucess");
		if (opt<4)
			return mapping.findForward("find");
		else
			return mapping.findForward("sucess");
		
	}

	public ActionForward OptRepo2a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		List repo=null;
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();

		int opt;
		if (req.getParameter("search")==null)
			opt=1;
		else
			opt = Integer.parseInt(req.getParameter("search"));
		
	    SQLOptDAO rd1=new SQLOptDAO();
	    con=datasource.getConnection();		
		switch(opt)
         {
        	 case 1:
                     repo =rd1.getAllrepo(con, depo_code, tp);
                     af.setXlist(repo);
        			 break;
        	 case 2: 
	                 repo =rd1.getAllRegion(con, depo_code, tp);
	                 af.setXlist(repo);
    			     break;
        	 case 3:
	                 repo =rd1.getAllArea(con, depo_code, tp);
	                 af.setXlist(repo);
			         break;
         }
		
		 req.setAttribute("rlist", repo);
		if (req.getParameter("search")==null)
			return mapping.findForward("sucess");
		if (opt<4)
			return mapping.findForward("find");
		else
			return mapping.findForward("sucess");		
		
	}
	
	
	public ActionForward OptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
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


	public ActionForward OptRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        
	    if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        Repo2Form af= (Repo2Form) form;
		af.setYlist(lfb.getYlist());
		int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        int opt = af.getRep_type();
        
		if (req.getParameter("search")==null)
			opt=af.getRep_type();
		else
			opt = Integer.parseInt(req.getParameter("search"));
		
	    List repo=null;
	    List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
	        switch(opt)
	         {
	        	 case 1:
	        			repo =rd.getAllrepo(con,depo_code,tp); 
	        			break; 
	        	 case 2:
	        		 repo =rd.getAllRegion(con,depo_code,tp);
	        			break; 
	        	 case 3:
	     		 		repo =rd.getAllArea(con,depo_code,tp);  
	     		 		break; 
	        	 case 4: 
	        		 	repo =rd.getAllBranch(con,depo_code,tp);  
				        break;
	         }
			
			af.setXlist(repo);
			af.setRlist(repo1);
			af.setAlist(repo);
			req.setAttribute("rlist",repo);

		if (req.getParameter("search")==null)
			return mapping.findForward("sucess");
		else
			return mapping.findForward("find");		
		
	}

	public ActionForward OptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
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
	

	public ActionForward OptRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo6 Action class");       

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


	
	public ActionForward OptRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo7 Action class");       

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
	

	public ActionForward AjaxRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List AjaxRepo7 Action class");       

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
        int eyear=Integer.parseInt(req.getParameter("q"));

        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();  
		con=datasource.getConnection();
    	repo =rd.getAllGroup(con,tp,eyear,uid,utype); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		req.setAttribute("opt", "Opt7");
		 
		return mapping.findForward("sucess");
		
	}
	
	
	public ActionForward OptRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo8 Action class");       

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
//////////////////////////////////////Opt 11 Start/////////////////////////////////////////////////////////
	
	public ActionForward OptRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo11 Action class");       

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
/////////////////////////////////////Opt 11 End//////////////////////////////////////////////////////	
	
	
//////////////////////////////List Repo 11 End /////////////////////////////////

	public ActionForward ListRepo11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report11 Action class");       

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
        Repo11Form af= (Repo11Form) form;
        List repo=null;
		SQLRepo11DAO rd = new SQLRepo11DAO();
		con=datasource.getConnection();
        int smon =af.getSmon();
        int emon =af.getEmon();
        int eyear=af.getEyear();
		int st = af.getSale_type();
        repo =rd.getAllStk(con,smon,emon,st,eyear,depo_code,tp);
		af.setRlist(repo);
        req.setAttribute("rlist", repo);
		
		return mapping.findForward("sucess");      
		
	}
////////////////////////////////////////////// List Repo 11 End /////////////////////////////////////////////	
	
/////////////////////////////////////////////Opt 12 Start/////////////////////////////////////////////////
	
	public ActionForward OptRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo12 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo12Form af= (Repo12Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();		
    	repo =rd.getAllStk(con,depo,tp); 
		
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
/////////////////////////////////////Opt 12 End//////////////////////////////////////////////////////		
	
//////////////////////////////////////List Repo 12 End /////////////////////////////////////////////	
	public ActionForward ListRepo12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report12 Action class");       

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

//      Populate chosen list from hidden field.
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
	
//////////////////////////////////////List Repo 12 End /////////////////////////////////////////////		
	
/////////////////////////////////////////////Opt 13 Start/////////////////////////////////////////////////
	public ActionForward OptRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo13 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo13Form af= (Repo13Form) form;
        af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllStk13(con,depo,tp); 
		
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
/////////////////////////////////////Opt 13 End//////////////////////////////////////////////////////		
	
//////////////////////////////////////List Repo 13 End /////////////////////////////////////////////	
	public ActionForward ListRepo13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report13 Action class");       

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

//      Populate chosen list from hidden field.
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
	
//////////////////////////////////////List Repo 13 End /////////////////////////////////////////////		
	
/////////////////////////////////////Opt Repo 14 Start//////////////////////////////////////////////////////	
	public ActionForward OptRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
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
	
/////////////////////////////////////Opt Repo 14 End//////////////////////////////////////////////////////		
	
	
//////////////////////////////////////List Repo 14 Start /////////////////////////////////////////////
	public ActionForward ListRepo14(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report14 Action class");       

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
//////////////////////////////////////List Repo 14 Start /////////////////////////////////////////////	
	
	
/////////////////////////////////////////////Opt Repo 9 Start/////////////////////////////////////////////////
	
	public ActionForward OptRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo9 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo9Form af= (Repo9Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllStk9(con,depo,tp); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead="STOCKIEST ";
		req.setAttribute("data", whead);

		 return mapping.findForward("sucess"); 
	}
/////////////////////////////////////Opt 9 End//////////////////////////////////////////////////////		
	
	
//////////////////////////////////////List Repo 9 End /////////////////////////////////////////////	
	public ActionForward ListRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report9 Action class");       

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
	
//////////////////////////////////////List Repo 9 End /////////////////////////////////////////////		
		

/////////////////////////////////////////////Opt Repo 10 Start/////////////////////////////////////////////////
	
	public ActionForward OptRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List OptRepo10 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo10Form af= (Repo10Form) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllStk10(con,depo,tp); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead="STOCKIEST ";
		req.setAttribute("data", whead);
		 
		return mapping.findForward("sucess"); 
	
		
	}
/////////////////////////////////////Opt 10 End//////////////////////////////////////////////////////		
	
	
//////////////////////////////////////List Repo 10 End /////////////////////////////////////////////	
	public ActionForward ListRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report10 Action class");       

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

//      Populate chosen list from hidden field.
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
	
//////////////////////////////////////List Repo 9 End /////////////////////////////////////////////		
			

/////////////////////////////////////////////Opt Repo 15 Start/////////////////////////////////////////////////
	
	public ActionForward OptRepo15(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo15 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo15Form af= (Repo15Form) form;
		af.setYlist(lfb.getYlist());

        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllStk15(con,depo,tp); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		String whead="Stockiest ";
		req.setAttribute("data", whead);
		 
		return mapping.findForward("sucess"); 
		
	}
/////////////////////////////////////Opt Repo 15 End//////////////////////////////////////////////////////		
	
//////////////////////////////////////List Repo 15 End /////////////////////////////////////////////	
	public ActionForward ListRepo15(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report15 Action class");       

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

//      Populate chosen list from hidden field.
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
	
//////////////////////////////////////List Repo 15 End /////////////////////////////////////////////		
		

/////////////////////////////////////////////Opt Repo 16 Start/////////////////////////////////////////////////
	
	public ActionForward OptRepo16(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List OptRepo16 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo = lfb.getCode();
        String tp = lfb.getD_type();
        Repo16Form af= (Repo16Form) form;
		af.setYlist(lfb.getYlist());
        
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getAllStk16(con,depo,tp); 
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead="Stockiest ";
		req.setAttribute("data", whead);
		 
		return mapping.findForward("sucess"); 
	
		
	}
/////////////////////////////////////Opt Repo 16 End//////////////////////////////////////////////////////		
	
	
//////////////////////////////////////List Repo 16 End /////////////////////////////////////////////	
	public ActionForward ListRepo16(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report16 Action class");       

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

//      Populate chosen list from hidden field.
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
	
//////////////////////////////////////List Repo 9 End /////////////////////////////////////////////		
			
/////////////////////////////////////////////Opt 17 Start here////////////////////////////////////////////
	
	public ActionForward OptRepo17(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo17 Action class");       

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
/////////////////////////////////////Opt 17 End here//////////////////////////////////////////////////		
	
//////////////////////////////////////List Repo 17 Start /////////////////////////////////////////////	
	public ActionForward ListRepo17(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report17 Action class");       

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
         
         Repo17Form af= (Repo17Form) form;
        // Populate chosen list from hidden field.+++++++ 
         
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
             		  repo =rd.getAllrepo(con,cd,smon,emon,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
        af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
	
//////////////////////////////////////List Repo 17 End /////////////////////////////////////////////		
		
	
/////////////////////////////////////////////Opt 18 Start here////////////////////////////////////////////
	
	public ActionForward OptRepo18(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optRepo18 Action class");       

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
/////////////////////////////////////Opt 18 End here//////////////////////////////////////////////////		
	
//////////////////////////////////////List Repo 18 Start /////////////////////////////////////////////	

	public ActionForward ListRepo18(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Report18 Action class");       

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
         
        Repo18Form af= (Repo18Form) form;

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
             		  repo =rd.getAllHQ(con,cd,uv,rtype,emon,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		
		return mapping.findForward("sucess");      
		
	}
	
//////////////////////////////////////List Repo 18 End /////////////////////////////////////////////		

	public ActionForward OptCheck(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	
	    if (lfb==null)
	
	    {
	    	return mapping.findForward("sfail");
	    }
	    Repo1Form af= (Repo1Form) form;
		af.setYlist(lfb.getYlist());
	
		return mapping.findForward("sucess");
		
	}	
	
	public ActionForward ListCheck(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Check Repo1Action class");       

        DataSource datasource = null; 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int loginid = lfb.getId();
        Repo1Form af= (Repo1Form) form;
		int eyear=af.getEyear();
		Repo1DAO  rd= DAOFactory.getRepo1();
		try{
        datasource = this.getDataSource(req,"userDB"); 
	    con=datasource.getConnection();

		List repo =rd.getCheckList(con,tp,loginid,eyear);
		String whead=" CHECKLIST (HQT/STK/TAR) FOR THE YEAR "+eyear+"-"+(eyear+1);
		req.setAttribute("head", whead);
		af.setRlist(repo);
		}catch(Exception e)
		{
			System.out.println("Error in get Connection Repo14 "+e);
		}
		finally{
			      try{
			    	   datasource=null;
			    	   con=null;
			    	   
			      }catch(Exception e)
			      {
			    	  System.out.println("Connection & datasource null nahi hue Repo 14"+e);
			      }
		}
		return mapping.findForward("sucess");
		
	} 	
	
}
 