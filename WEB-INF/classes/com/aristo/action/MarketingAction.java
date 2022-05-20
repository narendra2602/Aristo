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

import com.aristo.dao.SQLForm10DAO;
import com.aristo.dao.SQLForm11DAO;
import com.aristo.dao.SQLForm12DAO;
import com.aristo.dao.SQLForm13DAO;
import com.aristo.dao.SQLForm1DAO;
import com.aristo.dao.SQLForm2DAO;
import com.aristo.dao.SQLForm3DAO;
import com.aristo.dao.SQLForm4DAO;
import com.aristo.dao.SQLForm5DAO;
import com.aristo.dao.SQLForm6DAO;
import com.aristo.dao.SQLForm7DAO;
import com.aristo.dao.SQLForm8DAO;
import com.aristo.dao.SQLForm9DAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.form.MktForm;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.MktFormBean;


public class MarketingAction extends DispatchAction {
///////////////////////////////  Form No. 1 Action ////////////////////////////////////////////////	
	
	public ActionForward ListForm1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form1 Action class");       

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
        MktForm af= (MktForm) form;
        

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
		SQLForm1DAO rd = new SQLForm1DAO();
        
        int opt = af.getRep_type();  

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
             		  repo =rd.getAllHQ(con,cd,emon,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
        		 
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
//        		 		repo =rd.getAllRegion(con,cd,smon,emon,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
//        		 		repo =rd.getAllArea(con,cd,smon,emon,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
//        		 		repo =rd.getAllBranch(con,cd,smon,emon,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
         } 
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}
///////////////////////////////  End of Form No. 1 Action ////////////////////////////////////////////////	
	

///////////////////////////////  Form No. 2 Action ////////////////////////////////////////////////	
	
	public ActionForward ListForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form2 Action class");       

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
        MktForm af= (MktForm) form;

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
		SQLForm2DAO rd = new SQLForm2DAO();

        int emon =af.getMnth1();
        int eyear=af.getEyear();
         
         
            int i=1;
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getForm2(con,cd,emon,eyear,depo_code,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	        }
					  
					af.setRlist(repo);
			        req.setAttribute("rlist", m);
			    	
					return mapping.findForward("sucess");      
		
	}
///////////////////////////////  End of Form No. 2 Action ////////////////////////////////////////////////	
		

///////////////////////////////  Form No. 3 Action ////////////////////////////////////////////////	
	
	public ActionForward ListForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form3 Action class");       

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
         
         MktForm af= (MktForm) form;
        
        List repo=null;
		SQLForm3DAO rd = new SQLForm3DAO();
        con=datasource.getConnection();		
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
             repo =rd.getForm3(con,smon,emon,eyear,depo_code,tp);
             af.setRlist(repo);
             req.setAttribute("rlist", repo);
	 
			 return mapping.findForward("sucess");      
		
	}
///////////////////////////////  End of Form No. 3 Action ////////////////////////////////////////////////	
	
///////////////////////////////  Form No. 4 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm4 Action class");       

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
        MktForm af= (MktForm) form;

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
		SQLForm4DAO rd = new SQLForm4DAO();       
           
		int opt = (Integer) session.getAttribute("option");
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int uv = af.getUv();
         
            int i=1;
		switch(opt)
         {
        	 case 1:
                     
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllHQ(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	        }
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
       		 		    repo =rd.getMRegion(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
    	    	 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMArea(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
	             	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMBranch(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
  
		return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 4 Action End here////////////////////////////////////////////////	
	
	
///////////////////////////////  Form No. 5 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm5 Action class");       

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
        MktForm af= (MktForm) form;
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
		SQLForm5DAO rd = new SQLForm5DAO();
        
		int opt = (Integer) session.getAttribute("option");
        int uv = af.getUv();
        int tl = af.getPg();
        int eyear= af.getEyear();
         
        int i=1;
		switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             	      repo =rd.getAllHQ(con,uv,cd,tl,eyear,depo_code,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	        }
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
         		 		repo =rd.getMRegion(con,uv,cd,tl,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
    	    	 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMArea(con,uv,cd,tl,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
	             	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMBranch(con,uv,cd,tl,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		
		System.out.println("List me data set ho gya");  
		return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 5 Action End here////////////////////////////////////////////////	
	
///////////////////////////////  Form No. 6 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm6 Action class");       

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
        MktForm af= (MktForm) form;

   
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
		SQLForm6DAO rd = new SQLForm6DAO();
        
        int opt = (Integer) session.getAttribute("option");
        int uv = af.getUv();
        int i=1;
        int eyear=af.getEyear();
		
         switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             	      repo =rd.getAllHQ(con,uv,cd,depo_code,eyear,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	        }
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
         		 		repo =rd.getMRegion(con,uv,cd,depo_code,eyear,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
    	    	 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMArea(con,uv,cd,depo_code,eyear,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
	             	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getMBranch(con,uv,cd,depo_code,eyear,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
  
		return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 6 Action End here////////////////////////////////////////////////

///////////////////////////////  Form No. 7 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form7 Action class");       

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
        MktForm af= (MktForm) form;
        
        List repo=null;
		SQLForm7DAO rd = new SQLForm7DAO();
        con=datasource.getConnection();
        int opt = af.getRep_type(); 
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        
     		switch(opt)
            {
           	 case 1:
    		     SQLOptDAO rd1=new SQLOptDAO();
                 repo =rd1.getMRegion(con, depo_code, tp);
                 session.setAttribute("smon",new Integer (smon));
                 session.setAttribute("emon",new Integer (emon));
                 session.setAttribute("eyear",new Integer (eyear));
                 af.setXlist(repo);
  
           			break; 
           	 case 2:
           		    repo =rd.getAllregion(con, smon, emon,eyear, depo_code, tp,uid,utype); 
           			break; 
           	 case 3:
           		    repo =rd.getAllArea(con, smon, emon,eyear, depo_code, tp,uid,utype);  
        		 		break; 
            }             
             
    	if (opt==1)
   		     return mapping.findForward("option"); 
   	      else 
   	      {
   	    	  af.setRlist(repo);
   			  req.setAttribute("rlist", repo);
   		   	 
   	    	 return mapping.findForward("sucess");
   	      }            
        		
	}
	
///////////////////////////////  End of Form No. 7 Action ////////////////////////////////////////////////	

///////////////////////////////  Form No. 8 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm8 Action class");       

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
        MktForm af= (MktForm) form;

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
		SQLForm8DAO rd = new SQLForm8DAO();
        int opt = af.getRep_type();  
		int uv = af.getUv();
        int st = af.getPg();
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
                	  repo =rd.getAllHQ(con,cd,uv,emon,st,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
        	        }
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
                  	    repo =rd.getAllRegion(con,cd,uv,emon,st,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
    	    	 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,cd,uv,emon,st,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
	             	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,cd,uv,emon,st,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
			        break;
         } 
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 8 Action End here////////////////////////////////////////////////	
	
	
///////////////////////////////  Form No. 9 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form9 Action class");       

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
        MktForm af= (MktForm) form;
        
        List repo=null;
		SQLForm9DAO rd = new SQLForm9DAO();
        con=datasource.getConnection();
        int opt = af.getRep_type(); 

        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        
     		switch(opt)
            {
           	 case 1:
    		     SQLOptDAO rd1=new SQLOptDAO();
                 repo =rd1.getMRegion(con, depo_code, tp);
                 session.setAttribute("smon",new Integer (smon));
                 session.setAttribute("emon",new Integer (emon));
                 session.setAttribute("eyear",new Integer (eyear));
                 af.setXlist(repo);
  
           			break; 
           	 case 2:
           		    repo =rd.getAllRegion(con,smon,emon,eyear,depo_code,tp,uid,utype); 
           			break; 
           	 case 3:
           		    repo =rd.getAllArea(con,smon,emon,eyear,depo_code,tp,uid,utype);  
        		 		break; 
            }             
             
    	if (opt==1)
   		     return mapping.findForward("option"); 
   	      else 
   	      {
   	    	  af.setRlist(repo);
   			  req.setAttribute("rlist", repo);
   		   	 
   	    	 return mapping.findForward("sucess");
   	      }            
        		
	}
	
///////////////////////////////  End of Form No. 9 Action ////////////////////////////////////////////////		
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ActionForward ListForm9HQ(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form9HQ Action class");       

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
        MktForm af= (MktForm) form;
        
        List repo=null;
		SQLForm9DAO rd = new SQLForm9DAO();
        con=datasource.getConnection();
        int smon =(Integer) session.getAttribute("smon");
        int emon =(Integer) session.getAttribute("emon");
        int eyear =(Integer) session.getAttribute("eyear");
        int code =af.getCode();
        
        repo =rd.getAllHQ(con,code,smon,emon,eyear,depo_code,tp,uid,utype);
		
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
   	 
	 	return mapping.findForward("sucess");	
	}

/////////////////////////////////////////List Form 9 HQ End here//////////////////////////////////////////  

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ActionForward ListForm7HQ(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form7HQ Action class");       

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
        MktForm af= (MktForm) form;
        
        List repo=null;
		SQLForm7DAO rd = new SQLForm7DAO();
        con=datasource.getConnection();
        int smon =(Integer) session.getAttribute("smon");
        int emon =(Integer) session.getAttribute("emon");
        int eyear =(Integer) session.getAttribute("eyear");
    
        int code =af.getCode();
        
        repo =rd.getAllHQ(con,code,smon,emon,eyear,depo_code,tp,uid,utype);
		
		af.setRlist(repo);
		req.setAttribute("rlist", repo);
   	 
	 	return mapping.findForward("sucess");	
	}

/////////////////////////////////////////List Form 7 HQ End here//////////////////////////////////////////  
	
	
/////////////////////////////// Option Form No. 1  OptForm1////////////////////////////////////////////////
	
	public ActionForward OptForm1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm1 Action class");       

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
        MktForm af= (MktForm) form;
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
        
    	repo =rd.getFormProduct(con,tp,year,uid,utype); 
        af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		 
		return mapping.findForward("sucess"); 
		

		
	}
/////////////////////////////// End of Option Form No. 1 /////////////////////////////////////////////////	
	
/////////////////////////////// Option Form No. 2  OptForm2////////////////////////////////////////////////	
	public ActionForward OptForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm2 Action class");       

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
        MktForm af= (MktForm) form;
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
    	repo =rd.getAllFormGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
		
	}
/////////////////////////////// End of Option Form No. 2 /////////////////////////////////////////////////	
	public ActionForward AjaxForm2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List AjaxForm2 Action class");       

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
        
        MktForm af= (MktForm) form;
        int eyear=Integer.parseInt(req.getParameter("q"));
        List repo=null;
        List repo1= new ArrayList();

		SQLOptDAO rd = new SQLOptDAO();
        con=datasource.getConnection();
    	repo =rd.getAllFormGroup(con,tp,eyear,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo); 

		req.setAttribute("rlist",repo);
		req.setAttribute("opt", "Opt2");
		return mapping.findForward("sucess");
	}

/////////////////////////////// Option Form No. 3  OptForm3////////////////////////////////////////////////	
	public ActionForward OptForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm3 Action class");       
  
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess");  
				
	}
/////////////////////////////// End of Option Form No. 3 /////////////////////////////////////////////////	


///////////////////////////////Select Option Form No. 4////////////////////////////////////////////////	
	public ActionForward SelectOpt4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List SelectOpt4 Action class");       
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
                
		return mapping.findForward("sucess"); 
				
	}
/////////////////////////////// End of Select Option Form No. 4 /////////////////////////////////////////////////	

///////////////////////////////Select Option Form No. 5////////////////////////////////////////////////	
	public ActionForward SelectOpt5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List SelectOpt4 Action class");       

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
                
		return mapping.findForward("sucess"); 
				
	}
/////////////////////////////// End of Select Option Form No. 5 /////////////////////////////////////////////////	
	
///////////////////////////////Select Option Form No. 6////////////////////////////////////////////////	
	public ActionForward SelectOpt6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List SelectOpt4 Action class");       

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
                
		return mapping.findForward("sucess"); 
				
	}
/////////////////////////////// End of Select Option Form No. 6 /////////////////////////////////////////////////	
	

/////////////////////////////// Option Form No. 4 start here//////////////////////////////////////////////	
	public ActionForward OptForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm4 Action class");       

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
         
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();		
        int opt = af.getRep_type();
        
		switch(opt)
         {
        	 case 1:
        			repo =rd.getAllHQ(con,depo_code,tp); 
        			break; 
        	 case 2:
        		 	repo =rd.getMRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
     		 		repo =rd.getMArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
        		 	repo =rd.getMBranch(con,depo_code,tp);  
			        break;
         }
		
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		af.setRep_type(opt);
        session.setAttribute("option", new Integer(opt));

		req.setAttribute("rlist",repo);
		
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		
		return mapping.findForward("sucess"); 
	
	}

/////////////////////////////// End of Option Form No. 4 /////////////////////////////////////////////////		
	
/////////////////////////////// Option Form No. 5 start here//////////////////////////////////////////////	
	public ActionForward OptForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm5 Action class");       

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
         
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());

        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
        int opt = af.getRep_type();
        
        switch(opt)
         {
        	 case 1:
        			repo =rd.getAllHQ(con,depo_code,tp); 
        			break; 
        	 case 2:
        		 	repo =rd.getMRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
     		 		repo =rd.getMArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
             	 	repo =rd.getMBranch(con,depo_code,tp);  
			        break;
         }
		
        //Repo1DAO  rd= DAOFactory.getRepo1();
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		af.setRep_type(opt);
        session.setAttribute("option", new Integer(opt));

		req.setAttribute("rlist",repo);
		
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		
		System.out.println("List me data set ho gya");  
		return mapping.findForward("sucess"); 
	
	}

/////////////////////////////// End of Option Form No. 5 /////////////////////////////////////////////////			
	
/////////////////////////////// Option Form No. 6 start here//////////////////////////////////////////////	
	public ActionForward OptForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List optForm6 Action class");       

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
         
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
        int opt = af.getRep_type();
        
		switch(opt)
         {
        	 case 1:
        			repo =rd.getAllHQ(con,depo_code,tp); 
        			break; 
        	 case 2:
        		 	repo =rd.getMRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
     		 		repo =rd.getMArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
             	 	repo =rd.getMBranch(con,depo_code,tp);  
			        break;
         }
		
        //Repo1DAO  rd= DAOFactory.getRepo1();
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		af.setRep_type(opt);
        session.setAttribute("option", new Integer(opt));

		req.setAttribute("rlist",repo);
		
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
	}

/////////////////////////////// End of Option Form No. 6 /////////////////////////////////////////////////		
	
/////////////////////////////// Option Form7////////////////////////////////////////////////	
	public ActionForward OptForm7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm7 Action class");       

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
                
		return mapping.findForward("sucess"); 
				
	}
/////////////////////////////// End of Option Form No. 7 /////////////////////////////////////////////////	

///////////////////////////////Option Form No. 8 Start here/////////////////////////////////////////////////	
	public ActionForward OptForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm8 Action class");       

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
        MktForm af= (MktForm) form;
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
    	repo =rd.getFormProduct(con, tp,year,uid,utype); 

		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		 return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////// End of Option Form No. 8 /////////////////////////////////////////////////	
	
///////////////////////////////Option Form No. 9 Start here/////////////////////////////////////////////////	
	public ActionForward OptForm9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		 System.out.println("Calleed List optForm9 Action class");       

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        MktForm af= (MktForm) form;
			af.setYlist(lfb.getYlist());
	                
			return mapping.findForward("sucess"); 
	}
	
/////////////////////////////// End of Option Form No. 9 /////////////////////////////////////////////////		
	
///////////////////////////////Select Option Form No. 10////////////////////////////////////////////////	
	public ActionForward SelectOpt10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List SelectOpt10 Action class");       

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
                
		return mapping.findForward("sucess"); 
				
	}
/////////////////////////////// End of Select Option Form No. 10 /////////////////////////////////////////////////	

/////////////////////////////// Option Form No. 10 start here//////////////////////////////////////////////	
	public ActionForward OptForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List optForm10 Action class");       

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
         
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
        int opt = af.getRep_type();

		switch(opt)
         {
        	 case 1:
        			repo =rd.getAllHQ(con,depo_code,tp); 
        			break; 
        	 case 2:
        		 	repo =rd.getMRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
     		 		repo =rd.getMArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
        		 	repo =rd.getMBranch(con,depo_code,tp);  
			        break;
         }
		
        //Repo1DAO  rd= DAOFactory.getRepo1();
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		af.setRep_type(opt);
        session.setAttribute("option", new Integer(opt));
		req.setAttribute("rlist",repo);
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		 req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
	}

/////////////////////////////// End of Option Form No. 10 /////////////////////////////////////////////////		

///////////////////////////////  Form No. 4 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm10 Action class");       

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
        MktForm af= (MktForm) form;
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
		SQLForm10DAO rd = new SQLForm10DAO();
		int opt = (Integer) session.getAttribute("option");
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
		int uv = af.getUv();
        int sale = af.getSale_type();
         
            int i=1;
		switch(opt)
         {
        	 case 1:
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
             		  repo =rd.getAllHQ(con,uv,sale,cd,smon,emon,eyear,depo_code,tp,uid,utype);
             		  m.put(i, repo);
             		  i++;
        	              	
        	        }
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
      		 		    repo =rd.getAllRegion(con,uv,sale,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
    	    	 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,uv,sale,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
	             	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
          		 		repo =rd.getAllBranch(con,uv,sale,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		 		m.put(i, repo);
        		 		i++;
              	 	}
			        break;
         } 
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 10 Action End here////////////////////////////////////////////////	

///////////////////////////////Option Form No. 11 Start here/////////////////////////////////////////////////	
	public ActionForward OptForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		 System.out.println("Calleed List optForm11 Action class");       

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        MktForm af= (MktForm) form;
			af.setYlist(lfb.getYlist());
     
			return mapping.findForward("sucess"); 
	}
	
/////////////////////////////// End of Option Form No. 11 /////////////////////////////////////////////////	
	
///////////////////////////////  Form No. 11 Action Start here////////////////////////////////////////////////	
	
	public ActionForward ListForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListForm11 Action class");       

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
        MktForm af= (MktForm) form;
        List repo=null;
		
        SQLForm11DAO rd = new SQLForm11DAO();
        con=datasource.getConnection();        
        int opt = af.getRep_type();  

        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        
		switch(opt)
         {
        	 case 1:
        		 	repo =rd.getAllHQ(con,smon,emon,eyear,depo_code,tp);
        		 	break; 
        	 case 2:
        		 	repo =rd.getAllRegion(con,smon,emon,eyear,depo_code,tp);
        		 	break; 
        	 case 3:
        		 	repo =rd.getAllArea(con,smon,emon,eyear,depo_code,tp);
        		 	break;
        	 case 4: 
         		 	repo =rd.getAllBranch(con,smon,emon,eyear,depo_code,tp);
        		 	break;
         }
		
		af.setRlist(repo);
        req.setAttribute("rlist", repo);

        return mapping.findForward("sucess");      
		
	}	
///////////////////////////////  Form No. 11 Action End here////////////////////////////////////////////////		
	
///////////////////////////////Option Form No. 12 Start here/////////////////////////////////////////////////	
	public ActionForward OptForm12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		 System.out.println("Calleed List optForm12 Action class");       

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

	        MktForm af= (MktForm) form;
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
	    	repo =rd.getAllFormGroup(con,tp,eyear,uid,utype); 
			af.setXlist(repo);
			af.setRlist(repo1);
			af.setAlist(repo);

			req.setAttribute("rlist",repo);
			
			String whead=null;
			MktFormBean r = null;
			List l = (List) repo;
			Iterator it = l.iterator();
			if (it.hasNext())
			{
			    r = (MktFormBean) (it.next());
			    whead = r.getNm3();
			}
			
			 req.setAttribute("data", whead);
	                
			return mapping.findForward("sucess"); 
	}
	
/////////////////////////////// End of Option Form No. 12 /////////////////////////////////////////////////	
	
////////////////////////////////////Form  No. 12 Action Start Here/////////////////////////////////////
	
	public ActionForward ListForm12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form12 Action class");       

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

         
        MktForm af= (MktForm) form;
          

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
        SQLForm12DAO rd = new SQLForm12DAO();
        
        int opt = af.getRep_type();  
        int eyear=af.getEyear();
        int smon =af.getMnth();
        int emon =af.getMnth1();
         
            int i=1;
		switch(opt)
         {
        	 case 1:
                     
        	        while (it.hasNext())
        	        {
        	          cd = (Integer)it.next();
        	          con=datasource.getConnection();
            		  repo =rd.getAllHQ(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
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
		
////////////////////////////////////Form  No. 12 Action End Here///////////////////////////////////// 	
	
///////////////////////////////Option Form No. 13 Start here/////////////////////////////////////////////////	
	public ActionForward OptForm13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		 System.out.println("Calleed List optForm13 Action class");       

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
	        MktForm af= (MktForm) form;
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
	    	repo =rd.getFormProduct(con, tp,year,uid,utype);
			af.setXlist(repo);
			af.setRlist(repo1);
			af.setAlist(repo);

			req.setAttribute("rlist",repo);
			
			String whead=null;
			MktFormBean r = null;
			List l = (List) repo;
			Iterator it = l.iterator();
			if (it.hasNext())
			{
			    r = (MktFormBean) (it.next());
			    whead = r.getNm3();
			}
			
			 req.setAttribute("data", whead);
	                
			return mapping.findForward("sucess"); 
	}
	
/////////////////////////////// End of Option Form No. 13 /////////////////////////////////////////////////		
	
////////////////////////////////////Form  No. 13 Action Start Here/////////////////////////////////////
	
	public ActionForward ListForm13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Form13 Action class");       

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
        MktForm af= (MktForm) form;
          
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
        SQLForm13DAO rd = new SQLForm13DAO();
        int opt = af.getRep_type();  
        int uv=af.getUv();
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
            		  repo =rd.getAllHQ(con,uv,cd,smon,emon,eyear,depo_code,tp);
             		  m.put(i, repo);
             		  i++;
                    }
        		 
        			break; 
        	 case 2:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
       		 		    repo =rd.getAllRegion(con,uv,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
        			break; 
        	 case 3:
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllArea(con,uv,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
        	 case 4: 
        		 	while (it.hasNext())
        		 	{
        		 		cd = (Integer)it.next();
        		 		con=datasource.getConnection();
        		 		repo =rd.getAllBranch(con,uv,cd,smon,emon,eyear,depo_code,tp);
        		 		m.put(i, repo);
        		 		i++;
        		 	}
			        break;
         } 
			  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
	}
////////////////////////////////////Form  No. 13 Action End Here///////////////////////////////////// 		
	
}
