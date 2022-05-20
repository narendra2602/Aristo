package com.aristo.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

 

import com.aristo.dao.SQLHOInv1DAO;
import com.aristo.dao.SQLHOInv2DAO;
import com.aristo.dao.SQLHOInv3DAO;
import com.aristo.dao.SQLHOOptDAO;
import com.aristo.dao.SQLInv1DAO;
import com.aristo.dao.SQLInv2DAO;
import com.aristo.dao.SQLInv3DAO;
import com.aristo.dao.SQLInv5DAO;
import com.aristo.form.Inv1Form;
import com.aristo.form.Inv2Form;
import com.aristo.form.Inv3Form;
import com.aristo.form.SampleRepo3Form;

import com.aristo.valueobject.LoginFormBean;

public class InvAction extends DispatchAction {

////////////////////////////////////////////////Option Inv 1 Start //////////////////////////////////////////
	public ActionForward OptInv1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        Inv1Form af= (Inv1Form) form;
		af.setYlist(lfb.getYlist());
		
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////Option Inv 1 Close //////////////////////////////////////////
	
///////////////////////////////////////////////List Inv 1 Start /////////////////////////////////////////////    	
	
	public ActionForward ListInv1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListInv1 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        int depo = lfb.getCode();
        int uid=lfb.getId();
        String utype = lfb.getType();

        Inv1Form af= (Inv1Form) form;
        
        List repo=null;
        SQLInv1DAO rd = new SQLInv1DAO();

        int emon=af.getEmon();
        int eyear=af.getEyear();
        
             repo =rd.getInv1(con,depo,emon,eyear,tp,uid,utype);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////List Inv 1 End////////////////////////////////////////////////////	
	
////////////////////////////////////////////////Option Inv 2 Start //////////////////////////////////////////
	public ActionForward OptInv2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        Inv2Form af= (Inv2Form) form;
		af.setYlist(lfb.getYlist());
		
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////Option Inv 2 Close //////////////////////////////////////////
	
///////////////////////////////////////////////List Inv 2 Start /////////////////////////////////////////////    	
	
	public ActionForward ListInv2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListInv2 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        int depo = lfb.getCode();
        int uid=lfb.getId();
        String utype = lfb.getType();
        String branch = lfb.getBranch_name();
        Inv2Form af= (Inv2Form) form;
        
        List repo=null;
        SQLInv2DAO rd = new SQLInv2DAO();
        int smon=af.getSmon();
        int emon=af.getEmon();
        int type=af.getTtype();
        int eyear=af.getEyear();
             repo=rd.getInv2(con,branch,depo,type,smon,emon,eyear,tp,uid,utype);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////List Inv 2 End////////////////////////////////////////////////////	
	

////////////////////////////////////////////////Option Inv 3 Start //////////////////////////////////////////
	public ActionForward OptInv3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        Inv3Form af= (Inv3Form) form;
		af.setYlist(lfb.getYlist());
		
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////Option Inv 3 Close //////////////////////////////////////////
	
///////////////////////////////////////////////List Inv 3 Start /////////////////////////////////////////////    	
	
	public ActionForward ListInv3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ListInv3 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        int depo = lfb.getCode();
        int uid=lfb.getId();
        String utype = lfb.getType();
        String branch = lfb.getBranch_name();
        Inv3Form af= (Inv3Form) form;
        
        List repo=null;
        SQLInv3DAO rd = new SQLInv3DAO();

        int emon=af.getEmon();
        int eyear=af.getEyear();
        
             repo =rd.getInv3(con,branch,depo,emon,eyear,tp,uid,utype);
		     af.setRlist(repo);
             
             
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////List Inv 3 End////////////////////////////////////////////////////	
		
	public ActionForward OptInv4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListInv4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    int dp=lfb.getCode();   		    
   	    Iterator itt = lfb.getYlist().iterator();
		int eyear=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			eyear = lfb.getYval();
		}   	    

   	    SampleRepo3Form rf = (SampleRepo3Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    
   	    String head = "NEAR EXPIRY/EXPIRED BATCHWISE STOCK AS ON "+rf.getSdate();
   	    
   	    SQLInv3DAO repo3 = new SQLInv3DAO();
		r = repo3.getInv4(con, tp, sdt,dp,eyear);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}

	public ActionForward OptInv5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListInv5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    int dp=lfb.getCode();   		    
   	    Iterator itt = lfb.getYlist().iterator();
		int eyear=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			eyear = lfb.getYval();
		}   	    

   	    SampleRepo3Form rf = (SampleRepo3Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    
   	    String head = "ANALYSIS FOR STOCKS AVAILABLE AS ON : "+rf.getSdate();
   	    
   	    SQLInv5DAO repo5 = new SQLInv5DAO();
		r = repo5.getInv5(con, tp, sdt,dp,eyear);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward OptInv6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		return mapping.findForward("sucess");
	}
	
	public ActionForward ListInv6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    int dp=lfb.getCode();   		    
   	    Iterator itt = lfb.getYlist().iterator();
		int eyear=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			eyear = lfb.getYval();
		}   	    

   	    SampleRepo3Form rf = (SampleRepo3Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    
   	    String head = "FACTORY WISE STOCKS AVAILABLE AS ON : "+rf.getSdate();
   	    
   	    SQLInv5DAO repo5 = new SQLInv5DAO();
		r = repo5.getInv6(con, tp, sdt,dp,eyear);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}	
	
		
////////////////////////////////////////////////HO Option Inv 1 Start //////////////////////////////////////////
	public ActionForward HOOptInv1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
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

        Inv1Form af= (Inv1Form) form;
		af.setYlist(lfb.getYlist());
		Iterator itt = lfb.getYlist().iterator(); 
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
		
		con=datasource.getConnection();
		SQLHOOptDAO  ad=new SQLHOOptDAO(); 
		List prd =ad.getAllProduct(con, tp,year,uid,utype);
		af.setRlist(prd);
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////Option Inv 1 Close //////////////////////////////////////////
	
///////////////////////////////////////////////HO-List Inv 1 Start /////////////////////////////////////////////    	
	
	public ActionForward HOListInv1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HOListInv1 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        String typ = lfb.getType();
        int code1 = lfb.getDcode();
        int loginid= lfb.getId();
        Inv1Form af= (Inv1Form) form;
        int eyear=af.getEyear();
        int pcode=af.getCode();
        int opt=af.getOpt();
        String sr=af.getSearch();
        List repo=null;
        SQLHOInv1DAO rd = new SQLHOInv1DAO();

        int emon = af.getEmon();
        String forward = "sucess";
        	 if(opt==1)
               repo =rd.getHOInv2(con,emon,tp,typ,code1,loginid,eyear,pcode);
        	 else
        	 {
        	   repo =rd.getAllBranch(con, emon,tp,eyear,loginid,sr);
        	   forward = "newsucess";
        	 }
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward(forward); 
		
	}
	
/////////////////////////////////////List Inv 1 End////////////////////////////////////////////////////	
	
////////////////////////////////////////////////HO Option Inv 2 Start //////////////////////////////////////////
	public ActionForward HOOptInv2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        Inv2Form af= (Inv2Form) form;
		af.setYlist(lfb.getYlist());
		
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////Option Inv 2 Close //////////////////////////////////////////
	
///////////////////////////////////////////////HO List Inv 2 Start /////////////////////////////////////////////    	
	
	public ActionForward HOListInv2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HOListInv2 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

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
        
        Inv2Form af= (Inv2Form) form;
        
        List repo=null;
        SQLHOInv2DAO rd = new SQLHOInv2DAO();

        int smon = af.getSmon();
        int emon = af.getEmon();
        int type = af.getTtype();
        int eyear = af.getEyear();
             repo =rd.getHOInv2(con,type,smon,emon,tp,typ,code1,loginid,eyear);

             af.setRlist(repo);
             
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	
/////////////////////////////////////List Inv 2 End////////////////////////////////////////////////////	
	

////////////////////////////////////////////////HO Option Inv 3 Start //////////////////////////////////////////
	public ActionForward HOOptInv3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        Inv3Form af= (Inv3Form) form;
		af.setYlist(lfb.getYlist());
		
		return mapping.findForward("sucess");
	}
////////////////////////////////////////////////HO Option Inv 3 Close //////////////////////////////////////////
	
///////////////////////////////////////////////HO List Inv 3 Start /////////////////////////////////////////////    	
	
	public ActionForward HOListInv3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HOListInv3 Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

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
        
        Inv3Form af= (Inv3Form) form;
        
        List repo=null;
        SQLHOInv3DAO rd = new SQLHOInv3DAO();

        int emon=af.getEmon();
        int eyear=af.getEyear();
        
             repo =rd.getInv3(con,emon,tp,typ,code1,loginid,eyear);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
  
		return mapping.findForward("sucess"); 
		
	}
	

	public ActionForward HOOptInv4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        SampleRepo3Form af = (SampleRepo3Form) form;
		Iterator itt = lfb.getYlist().iterator(); 
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
		con=datasource.getConnection();
		SQLHOOptDAO  ad =new SQLHOOptDAO(); 
		List prd =ad.getHOProduct(con, tp,year,uid,utype);
		af.setRlist(prd);        
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward HOListInv4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    int dp=lfb.getCode();   	
   	    int uid=lfb.getId();   	    
   	    Iterator itt = lfb.getYlist().iterator();
		int eyear=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			eyear = lfb.getYval();
		}   	    

   	    SampleRepo3Form rf = (SampleRepo3Form) form;
   	    int pcode=rf.getCode();
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
		SQLHOOptDAO  ad =new SQLHOOptDAO(); 
		String prdname =ad.getHOPname(con, tp,eyear,pcode);		

		String head = "[ "+prdname+" ] - NEAR EXPIRY/EXPIRED BATCHWISE STOCK AS ON : "+rf.getSdate();
		
		con=datasource.getConnection();   	    
   	    SQLInv3DAO repo3 = new SQLInv3DAO();
		r = repo3.getHOInv4(con, tp, sdt,dp,eyear,pcode,uid);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}

	public ActionForward HOOptInv5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        SampleRepo3Form af = (SampleRepo3Form) form;
		Iterator itt = lfb.getYlist().iterator(); 
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
		con=datasource.getConnection();
		SQLHOOptDAO  ad =new SQLHOOptDAO(); 
		List prd =ad.getHOProduct(con, tp,year,uid,utype);
		af.setRlist(prd);        
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward HOListInv5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    int dp=lfb.getCode();   	
   	    int uid=lfb.getId();   	    
   	    Iterator itt = lfb.getYlist().iterator();
		int eyear=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			eyear = lfb.getYval();
		}   	    

   	    SampleRepo3Form rf = (SampleRepo3Form) form;
   	    int pcode=rf.getCode();   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
		SQLHOOptDAO  ad =new SQLHOOptDAO(); 
		String prdname =ad.getHOPname(con, tp,eyear,pcode);		

   	    String head = "[ "+prdname+" ] - ANALYSIS FOR STOCKS AVAILABLE AS ON : "+rf.getSdate();
   	    
   	    con=datasource.getConnection();
   	    SQLInv5DAO repo5 = new SQLInv5DAO();
		r = repo5.getHOInv5(con, tp, sdt,dp,eyear,pcode,uid);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}	
	
	
	
	
	
	
}
