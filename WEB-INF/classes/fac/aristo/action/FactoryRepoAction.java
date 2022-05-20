package fac.aristo.action;

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

import fac.aristo.dao.SQLFOptDAO;
import fac.aristo.dao.SQLRepo1DAO;
import fac.aristo.dao.SQLRepo2DAO;
import fac.aristo.dao.SQLRepo3DAO;
import fac.aristo.dao.SQLRepo4DAO;
import fac.aristo.dao.SQLRepo5DAO;

import com.aristo.valueobject.LoginFormBean;

import fac.aristo.form.FacRepo1Form;
import fac.aristo.form.FacRepo2Form;
import fac.aristo.form.FacRepo3Form;
import fac.aristo.form.FacRepo4Form;
import fac.aristo.form.FacRepo5Form;

public class FactoryRepoAction extends DispatchAction {

	public ActionForward FactoryOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo1Form af= (FacRepo1Form) form;
	    String fac =af.getPchoice();
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List branch =ad.getAllBranch(con, tp);
   	    con=datasource.getConnection();
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);
		af.setBrlist(branch);
		return mapping.findForward("sucess");
	}	
	public ActionForward FactoryAjxRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo1Form af= (FacRepo1Form) form;
	    String fac =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);		
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}		
	public ActionForward FactoryListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    FacRepo1Form rf = (FacRepo1Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    String loc=rf.getLoc();
   	    int chc=rf.getChoice();
   	    String head = "TRANSFER STATEMENT FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo1DAO repo1 = new SQLRepo1DAO();
		r = repo1.getRepo1(con, tp, sdt,edt,br,chc,loc);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward FactoryOptRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo2Form af= (FacRepo2Form) form;
	    String fac =af.getPchoice();	    
		SQLFOptDAO  ad=new SQLFOptDAO(); 
		List branch =ad.getAllBranch(con, tp);
   	    con=datasource.getConnection();
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);
		af.setBrlist(branch);
		return mapping.findForward("sucess");
	}	
	public ActionForward FactoryAjxRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo2Form af= (FacRepo2Form) form;
	    String fac =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);		
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}		
	public ActionForward FactoryListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    FacRepo2Form rf = (FacRepo2Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String loc=rf.getLoc();   	    
   	    String head = "DAILY DISPATCH DETAIL FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo2DAO repo2 = new SQLRepo2DAO();
		r = repo2.getRepo2(con, tp, sdt,edt,br,chc,loc);
		rf.setLlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  
		return mapping.findForward("sucess");
	}	
		
	
	public ActionForward FactoryOptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo3Form af= (FacRepo3Form) form;
	    String fac =af.getPchoice();	    
		SQLFOptDAO  ad=new SQLFOptDAO(); 
		List branch =ad.getAllBranch(con, tp);
   	    con=datasource.getConnection();
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);
		af.setBrlist(branch);
		return mapping.findForward("sucess");
	}	
	public ActionForward FactoryAjxRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo3Form af= (FacRepo3Form) form;
	    String fac =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);		
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}		
	public ActionForward FactoryListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    FacRepo3Form rf = (FacRepo3Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String loc=rf.getLoc();   	    
   	    String head = "LOCATION WISE DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo3DAO repo3 = new SQLRepo3DAO();
		r = repo3.getRepo3(con, tp, sdt,edt,br,chc,loc);
		rf.setLlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  
		return mapping.findForward("sucess");
	}	
			
	
	public ActionForward FactoryOptRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo4Form af= (FacRepo4Form) form;
	    String fac =af.getPchoice();	    
		SQLFOptDAO  ad=new SQLFOptDAO(); 
		List branch =ad.getAllBranch(con, tp);
   	    con=datasource.getConnection();
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);
		af.setBrlist(branch);
		return mapping.findForward("sucess");
	}	
	public ActionForward FactoryAjxRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo4Form af= (FacRepo4Form) form;
	    String fac =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);		
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}		
	public ActionForward FactoryListRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    FacRepo4Form rf = (FacRepo4Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String loc=rf.getLoc();   	    
   	    String head = "DISPATCH SUMMERY FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo4DAO repo4 = new SQLRepo4DAO();
		r = repo4.getRepo4(con, tp, sdt,edt,br,chc,loc);
		rf.setLlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  
		return mapping.findForward("sucess");
	}	
			
	
	public ActionForward FactoryOptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo5Form af= (FacRepo5Form) form;
	    String fac =af.getPchoice();	    
		SQLFOptDAO  ad=new SQLFOptDAO(); 
	    String loc=af.getLcval();
		List branch =ad.getProduct(con, tp,"P",loc);
   	    con=datasource.getConnection();
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);
		af.setBrlist(branch);		
		return mapping.findForward("sucess");		
	}	
	public ActionForward FactoryAjxRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo5Form af= (FacRepo5Form) form;
	    String fac =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getAllLocation(con, tp,fac);		
		af.setLoclist(location);		
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}
	public ActionForward FactoryAjxRepo5a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo5Form af= (FacRepo5Form) form;
	    String fac =req.getParameter("q");
	    String loc=req.getParameter("r");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List location =ad.getProduct(con, tp,fac,loc);		
		af.setLoclist(location);		 
		req.setAttribute("Loclist", location);
		return mapping.findForward("sucess");
	}	
	public ActionForward FactoryAjxRepo5b(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
	    String tp = lfb.getD_type();
	    FacRepo5Form af= (FacRepo5Form) form;
	    String loc =req.getParameter("q");
		SQLFOptDAO ad=new SQLFOptDAO(); 
		List product =ad.getLProduct(con, tp,loc);		
		af.setBrlist(product);		
		req.setAttribute("Loclist", product);

		return mapping.findForward("sucess");
	}			
	
	public ActionForward FactoryListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
   	    FacRepo5Form rf = (FacRepo5Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    int br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String loc=rf.getLoc();   
   	    String sch=rf.getSchoice();
   	    
   	    String head = "PRODUCT WISE DISPATCH FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo5DAO repo5 = new SQLRepo5DAO();
		r = repo5.getRepo5(con, tp, sdt,edt,br,chc,loc,sch);
		rf.setLlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	

	
	
	
}
