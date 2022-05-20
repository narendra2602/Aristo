package com.aristo.action;
 
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import com.aristo.dao.DAOFactory;
import com.aristo.dao.LoginDAO;
import com.aristo.dao.SQLCheckItemDAO;
import com.aristo.dao.SQLLoginDAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.form.CheckItemForm;
import com.aristo.form.LoginForm;
import com.aristo.form.UploadForm;
import com.aristo.form.UserForm;
import com.aristo.valueobject.LoginFormBean;

public class LoginAction extends DispatchAction {

	  public ActionForward authenticateUser(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
		  	  DataSource datasource = this.getDataSource(req,"userDB"); 
		  	  Connection con=datasource.getConnection();
	  	      LoginForm f = (LoginForm) form;
			  LoginFormBean fb = new LoginFormBean();
			  LoginFormBean fb1 = new LoginFormBean();
			  fb.setLogin_name(f.getLogin_name());
			  fb.setPassword(f.getPassword());
			  LoginDAO ldao = DAOFactory.getLoginDAO(); 
			  fb1= ldao.authenticateUser(fb,con);
			  if (fb1==null)
			  {	  
				  f.setLogin_name(null);
				  f.setPassword(null);
				  return mapping.findForward("fail");
			  }	  
			  else
			  {
				  con=datasource.getConnection();
				  List y = ldao.getAllYear(con);
				  fb1.setYlist(y);
				  
				  // Manually set Type to HO before session
//				   fb1.setType("Both");
//				   fb1.setAccess_t("All");

				  HttpSession session=req.getSession();
				  session.setAttribute("Login", fb1);

				  if (f.getPassword().equals("123"))
				  {	  
					  return mapping.findForward("Cpass");
				  }	  
				  
///////////////////////////////Factory Login ke liye/////////////////////////////////////
                  if (fb1.getType().equals("Factory"))
                  {
                	  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      
                	  con=datasource.getConnection();
                	  String fmsg=ldao.getLastNo(con);                	  
 				    //  session.setAttribute("type",fb1.getAccess_t());
 				      session.setAttribute("nm",fb1.getF_name());
 				      session.setAttribute("all",fb1.getAccess_t());
 				      session.setAttribute("facmsg",fmsg);
 				      
 		///////////////// set divsion list //////////////////////// 29/03/2016	
 					  con=datasource.getConnection();
 					  SQLOptDAO rd= new SQLOptDAO();
 					  int uid = fb1.getId();
 					  List repo=null;
 					  repo=rd.getUserDivision(con,uid);
 					  Iterator it = repo.iterator();
 					  if (it.hasNext())
 					  {
 						  LoginFormBean lf = (LoginFormBean) it.next();
 						  f.setType(lf.getD_type());
 					  }
 					  f.setDivlist(repo);
 	/////////////////////set divsion list //////////////////////////////////
 					 return mapping.findForward("FAll");
 					 /* if (fb1.getAccess_t().equals("All"))
 	                  {
 						  return mapping.findForward("FAll");
 	                  }
 				      return mapping.findForward("Factory");*/
 				      
                  }
                  
///////////////////////////////Central Login ke liye/////////////////////////////////////                  
                  if (fb1.getType().equals("Central"))
                  {
                	  SQLOptDAO rd= new SQLOptDAO();
                	  con=datasource.getConnection();
          			  List tab=rd.getCTabMenu(con,fb1.getId());
          			  con=datasource.getConnection();
          			  List repo=rd.getCRepoMenu(con,fb1.getId());
                	  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "body.css");
 				      session.setAttribute("type",fb1.getAccess_t());
 				      session.setAttribute("nm",fb1.getF_name());
 				      session.setAttribute("all",fb1.getAccess_t());
 				      session.setAttribute("tablist", tab);
 					  session.setAttribute("repolist", repo);
 					  
 					  if (fb1.getAccess_t().equals("All"))
 	                  {
 						  return mapping.findForward("CAll");
 	                  }
 				      return mapping.findForward("Central");
                  }                  
                  
                  
                 
///////////////////////////// Admin Login Condition check///////////////////////////////
                  if (fb1.getType().equals("Admin"))
                  {
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      
 	                  if (fb1.getType().equals("Factory"))
 	                  {
 	                	  con=datasource.getConnection();
 	                	  String fmsg=ldao.getLastNo(con);                	  
 	 				      session.setAttribute("type",fb1.getAccess_t());
 	 				      session.setAttribute("nm",fb1.getF_name());
 	 				      session.setAttribute("all",fb1.getAccess_t());
 	 				      session.setAttribute("facmsg",fmsg);
 	                  }

 				      
 				 		///////////////// set divsion list //////////////////////// 29/03/2016	
 					  con=datasource.getConnection();
 					  SQLOptDAO rd= new SQLOptDAO();
 					  int uid = fb1.getId();
 					  List repo=null;
 					  repo=rd.getUserDivision(con,uid);
 					  Iterator it = repo.iterator();
 					  if (it.hasNext())
 					  {
 						  LoginFormBean lf = (LoginFormBean) it.next();
 						  f.setType(lf.getD_type());
 						  f.setDiv_code(lf.getDiv_code());
 					  }
 					  f.setDivlist(repo);
 					  

 					  
 					  System.out.println("size of div is "+f.getDivlist().size());
 	/////////////////////set divsion list //////////////////////////////////

 				      
 				      return mapping.findForward("FAll");
// 				      return mapping.findForward("Admin");
                  }
///////////////////////////// Admin Login Condition check end///////////////////////////////
                  
///////////////////////////// HO Login Condition check///////////////////////////////
                  if ((fb1.getType().equals("HO0")) && (fb1.getAccess_t().equals("All")))
                  {
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      return mapping.findForward("HO");
                  }
///////////////////////////// HO Login Condition check end///////////////////////////////
                  
///////////////////////////////// MF TF & Genetica Condition check////////////////////////////                  
                  int uid = fb1.getId();
			      List repo=null;
			      SQLOptDAO rd = new SQLOptDAO();
			      con=datasource.getConnection();
				  repo=rd.getUserBranch(con,uid);
				  Iterator it = repo.iterator();
				  if (it.hasNext())
				  {
					  LoginFormBean lf = (LoginFormBean) it.next();
					  f.setCode(lf.getDcode());
				  }
				  f.setTlist(repo);
				  
///////////////// set divsion list //////////////////////// 29/03/2016	
				  con=datasource.getConnection();
				  repo=null;
				  repo=rd.getUserDivision(con,uid);
				  it = repo.iterator();
				  if (it.hasNext())
				  {
					  LoginFormBean lf = (LoginFormBean) it.next();
					  f.setType(lf.getD_type());
					  f.setDiv_code(lf.getDcode());
					  fb1.setDiv_code(lf.getDcode());
				  }
				  f.setDivlist(repo);
				  fb1.setDivlist(repo);
/////////////////////set divsion list //////////////////////////////////
				  

/////////////////////// All (MF/TF/Genetica) Condition check/////////////////////////////////				  
				  
				  if (fb1.getAccess_t().equals("All"))
                  {
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
					  return mapping.findForward("All");
                  }
				  
/////////////////////// All (MF/TF/Genetica) Condition check/////////////////////////////////				  
				  
				  if (fb1.getD_type().equals("A") )
				  {	  
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      session.setAttribute("menucss", "menu.css");
				  }    
				  else if (fb1.getD_type().equals("T"))
				  {	  
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "TF.css");
 				      session.setAttribute("menucss", "tfmenu.css");
				  }    
				  else if (fb1.getD_type().equals("G"))
				  {	  
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "Genetica.css");
 				      session.setAttribute("menucss", "gmenu.css");
				  }    
				  else if (fb1.getD_type().equals("M"))
				  {	  
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      session.setAttribute("menucss", "menu.css");
				  }    
				  else if (fb1.getD_type().equals("B"))
				  {	  
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      session.setAttribute("menucss", "menu.css");
				  }    
				  else
				  {
					  session.setAttribute("footer", "footer.jsp");
 				      session.setAttribute("top", "top.jsp");
 				      session.setAttribute("css", "MF.css");
 				      session.setAttribute("menucss", "menu.css");
				  }

				  if (fb1.getType().equals("HO")) 
                  {
						con=datasource.getConnection();
						fb1.setBranch_name(fb1.getType());
						List tab=rd.getTabMenu(con, fb1.getId());
						con=datasource.getConnection();
						List repo1=rd.getRepoMenu(con, fb1.getId());
						fb1.setMsg("");
						session.setAttribute("tablist", tab);
						session.setAttribute("repolist", repo1);
						session.setAttribute("Login", fb1);
 				        return mapping.findForward("HO");
                  }
				  
				  if (fb1.getType().equals("HQ")) 
                  {
						con=datasource.getConnection();
						fb1.setBranch_name(fb1.getType());
						List tab=rd.getTabMenu(con, fb1.getId());
						con=datasource.getConnection();
						List repo1=rd.getRepoMenu(con, fb1.getId());
						fb1.setMsg("");
						session.setAttribute("tablist", tab);
						session.setAttribute("repolist", repo1);
						session.setAttribute("Login", fb1);
 				        return mapping.findForward("HQ");
                  }				  
				  
				  return mapping.findForward("sucess");

			  }
	  }

////////////// New User Create ki insert wali coding///////////////////////	  
	  
	  public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	  	    LoginForm f = (LoginForm) form;
	  	    String tp=null;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	  	   
			  LoginFormBean fb = new LoginFormBean();
			  fb.setTitle(f.getTitle());
			  fb.setF_name(f.getF_name());
			  fb.setL_name(f.getL_name());
			  fb.setDesig(f.getDesig());
			  fb.setLocation(f.getLocation());
			  fb.setDepartment(f.getDepartment());
			  fb.setComp_code(f.getComp_code());
			  fb.setLogin_name(f.getLogin_name());
			  fb.setPassword(f.getPassword());
			  fb.setC_password(f.getC_password());
			  fb.setAccess_t(f.getAccess_t());
			  fb.setStatus(f.getStatus());
			  fb.setType(f.getType());
			  fb.setEmail(f.getEmail());
			  
			  if (!f.getType().equals("Central"))
			  {
			  fb.setCheckbox1(f.getCheckbox1());  
			  }
			  
			  LoginDAO ldao = DAOFactory.getLoginDAO(); 
			  int k = ldao.LoginCheck(fb, con);
			  
			  if (k>0)
				  return mapping.findForward("User_exist");
				  
		  	  con=datasource.getConnection();
			  int i= ldao.newUser(fb,con);
			  if (i==-1)
				  return mapping.findForward("fail");
			 
			  req.setAttribute("uid", new Integer(i));
			  req.setAttribute("typ", f.getType());
			  
			  
			  if (f.getType().equals("Central"))
			  {
			   return mapping.findForward("Central");
			  }
			  
			  if (f.getType().equals("PMT"))
			  {
				  if (f.getAccess_t().equals("MF"))
					  tp="A";
				  if (f.getAccess_t().equals("TF"))
					  tp="T";
				  if (f.getAccess_t().equals("Genetica"))
					  tp="G";
				  if (f.getAccess_t().equals("COMKT"))
					  tp="D";
				  if (f.getAccess_t().equals("MF2"))
					  tp="M";
				  if (f.getAccess_t().equals("MF3"))
					  tp="B";
				  req.setAttribute("tp", tp);

				  return mapping.findForward("PMT");
			  }
			  
			  if (f.getType().equals("HQ"))
			  {
				  if (f.getAccess_t().equals("MF"))
					  tp="A";
				  else if (f.getAccess_t().equals("TF"))
					  tp="T";
				  else if (f.getAccess_t().equals("Genetica"))
					  tp="G";
				  else if (f.getAccess_t().equals("COMKT"))
					  tp="D";
				  else if (f.getAccess_t().equals("MF2"))
					  tp="M";
				  else if (f.getAccess_t().equals("T1"))
					  tp="N";
				  else if (f.getAccess_t().equals("T2"))
					  tp="O";
				  else if (f.getAccess_t().equals("T3"))
					  tp="P";
				  else if (f.getAccess_t().equals("MF3"))
					  tp="B";
				  req.setAttribute("tp", tp);

				  return mapping.findForward("HQ");
			  }
			  
			  if (f.getType().equals("Factory"))
			  {
				  return mapping.findForward("Factory");
			  }
			  
			  return mapping.findForward("sucess");
			
	  }

/////////////////////////////////////////////////Add User Forward//////////////////////////////////////////	  
	  public ActionForward addUserForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
		  return mapping.findForward("sucess");
		}
	  
///////////////////////////////////////////////// User HOME Forward//////////////////////////////////////////
 public ActionForward UserHomeForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		 HttpSession session=req.getSession();
		 LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
		
		 if (lfb==null)
			 {
			 	return mapping.findForward("sfail");
			 }
				return mapping.findForward("sucess");
		}
 
/////////////////////////////////////////////////User HOME Forward Central//////////////////////////////////////////
 public ActionForward UserHomeForward1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		 HttpSession session=req.getSession();
		 LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
		
		 if (lfb==null)
			 {
			 	return mapping.findForward("sfail");
			 }
		 
		 	if (lfb.getType().equals("Factory"))
		 	{
		      return mapping.findForward("Factory");
		 	}
		 
		 	return mapping.findForward("sucess");
		} 
/////////////////////////////////////////////////Add Type User Forward//////////////////////////////////////////
	  
	  public ActionForward addUsertypeForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
			return mapping.findForward("sucess");
		}
	  
/////////////////////////////////////////////////Switch Branches Forward//////////////////////////////////////////	
	  public ActionForward switchUserForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }

	        if ((lfb.getType().equals("Central")) && (lfb.getAccess_t().equals("All")))
	        {
	            return mapping.findForward("Cswitch");
	        }

	        if ((lfb.getType().equals("Factory")) && (lfb.getAccess_t().equals("All")))
	        {
	            return mapping.findForward("Fswitch");
	        }
	        
	        if (lfb.getAccess_t().equals("All"))
	        {
	            return mapping.findForward("All");
	        }
	        
		     return mapping.findForward("sucess");
		}

/////////////////////////////////////////////Admin User Switch Forward	  
	  public ActionForward switchUserForwardAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
		     return mapping.findForward("sucess");
		     
		}
	  
////////////////////////////////////////////////////User Forward/////////////////////////////////////////////	  
	  public ActionForward UserForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
    		LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        
  		    //String typ=f.getAccess_t();
  		    String typ=f.getType();
  		    lfb.setD_type(typ);
  		    
       	    lfb.setDiv_code(1);
  		    
  		    
  		    if (lfb.getType().equals("Central")) 
  		    {
  		    	typ=f.getAccess_t();

  	  		    if (typ.equals("MF"))
  	  		    {
  	  		    	  lfb.setD_type("A");
  	  		    }
  	  		    if (typ.equals("TF"))
  	  		    {
  			    	  lfb.setD_type("T");
  	  		    }
  	  		    if (typ.equals("Genetica"))
  	  		    {
  			    	  lfb.setD_type("G");
  	  		    }
  	     		session.setAttribute("Login",lfb);
			    session.setAttribute("type",typ);
  			    return mapping.findForward("Csucess");
  		    }
  		    
  		    
  		    
		    if (lfb.getType().equals("Factory")) 
		    {
		    	typ=f.getAccess_t();
	  		    if (typ.equals("A"))
	  		    {
	  		    	  lfb.setD_type("A");
	  		    	  typ="MF";
	  		    }
	  		    if (typ.equals("T"))
	  		    {
			    	  lfb.setD_type("T");
	  		    	  typ="TF";
	  		    }
	  		    if (typ.equals("G"))
	  		    {
			    	  lfb.setD_type("G");
	  		    	  typ="GENETICA";
	  		    }
	  		    if (typ.equals("B"))
	  		    {
			    	  lfb.setD_type("B");
	  		    	  typ="MF3";
	  		    }
	  		    if (typ.equals("M"))
	  		    {
			    	  lfb.setD_type("M");
	  		    	  typ="MF2";
	  		    }
	     		session.setAttribute("Login",lfb);
			    session.setAttribute("type",typ);
			    return mapping.findForward("Fsucess");
		    }

		   
		  if (lfb.getType().equals("Admin")) 
		  {
		    if (typ.equals("A"))
		    {
		    	  lfb.setD_type("A");
		    	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "adminmf_top.jsp");
			      session.setAttribute("css", "MF.css");
			      session.setAttribute("menucss", "menu.css");
		    }
		    else if (typ.equals("T"))
		    {
		    	  lfb.setD_type("T");
				  session.setAttribute("footer", "tf_footer.jsp");
			      session.setAttribute("top", "admintf_top.jsp");
			      session.setAttribute("css", "TF.css");
			      session.setAttribute("menucss", "tfmenu.css");
		    }
		    else if (typ.equals("G"))
		    {
		    	  lfb.setD_type("G");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "admingf_top.jsp");
			      session.setAttribute("css", "Genetica.css");
			      session.setAttribute("menucss", "gmenu.css");
		    }
		    
		    else if (typ.equals("M")) // for new layout open comment when new layout implement
		    {
		    	  lfb.setD_type("M");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "admingf_top.jsp");
			      session.setAttribute("css", "MF2.css");
			      session.setAttribute("menucss", "mf2menu.css");
		    }
		    else if (typ.equals("B"))
		    {
		    	  lfb.setD_type("B");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "admingf_top.jsp");
			      session.setAttribute("css", "MF3.css");
			      session.setAttribute("menucss", "mf3menu.css");
		    }
		     
		    else
		    {
		    	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "adminmf_top.jsp");
			      session.setAttribute("css", "MF.css");
			      session.setAttribute("menucss", "menu.css");
		    }

	        	  session.setAttribute("Login", lfb);
	        	  //This is for new Layout Open later for new layout 
	        	  session.setAttribute("top", "AdminTop.jsp"); 
	        	  session.setAttribute("footer", "footer.jsp");
	              return mapping.findForward("adminsucess");
		  }
		  
		  
  		  List repo = f.getTlist(); 
  		  Iterator it = repo.iterator();
		  if (it.hasNext())
		  {
			  LoginFormBean lf = (LoginFormBean) it.next();
			  f.setCode(lf.getDcode());
		  }

		  System.out.println("type selected is "+typ+" lenthg is "+typ.length());
  		    if (typ.equals("A"))
  		    {
  		    	  lfb.setD_type("A");
  		    	  lfb.setDiv_code(1);
			  	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "top.jsp");
			      session.setAttribute("css", "MF.css");
			      session.setAttribute("menucss", "menu.css");
  		    }
  		    else if (typ.equals("T"))
  		    {
		    	  lfb.setD_type("T");
		    	  lfb.setDiv_code(2);
			  	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "top.jsp");
			      session.setAttribute("css", "TF.css");
			      session.setAttribute("menucss", "tfmenu.css");
				  System.out.println("type TF is selected is "+typ+" lenthg is "+typ.length());

  		    }
  		    else if (typ.equals("G"))
  		    {
  		    	lfb.setD_type("G");
  	       	    lfb.setDiv_code(3);

  		    	session.setAttribute("footer", "footer.jsp");
  		    	session.setAttribute("top", "top.jsp");
  		    	session.setAttribute("css", "Genetica.css");
  		    	session.setAttribute("menucss", "gmenu.css");
  		    }
  		    else if (typ.equals("M"))
  		    {	  
  		    	lfb.setD_type("M");
  	       	    lfb.setDiv_code(10);

  		    	session.setAttribute("footer", "footer.jsp");
  		    	session.setAttribute("top", "top.jsp");
  		    	session.setAttribute("css", "MF2.css");
  		    	session.setAttribute("menucss", "mf2menu.css");
  		    }    
  		    else if (typ.equals("B"))
  		    {	  
  		    	lfb.setD_type("B");
  	       	    lfb.setDiv_code(20);

  		    	session.setAttribute("footer", "footer.jsp");
  		    	session.setAttribute("top", "top.jsp");
  		    	session.setAttribute("css", "MF3.css"); 
  		    	session.setAttribute("menucss", "mf3menu.css");
  		    }    
  		    else if (typ.equals("F"))
  		    {	  
  		    	lfb.setD_type("F");
  	       	    lfb.setDiv_code(30);

  		    	session.setAttribute("footer", "footer.jsp");
  		    	session.setAttribute("top", "top.jsp");
  		    	session.setAttribute("css", "MF4.css");
  		    	session.setAttribute("menucss", "mf4menu.css");
  		    	
  		    }    
  		    else
  		    {
  		    	if (typ.equals("N"))
  		    	{
  		    		lfb.setD_type("N");
  		    		lfb.setDiv_code(12);
  		    	}
  		    	else if (typ.equals("O"))
  		    	{
  		    		lfb.setD_type("O");
  		    		lfb.setDiv_code(13);
  		    	}
  		    	else if (typ.equals("P"))
  		    	{
  		    		lfb.setD_type("P");
  		    		lfb.setDiv_code(16);
  		    	}

  		    	session.setAttribute("footer", "footer.jsp");
  		    	session.setAttribute("top", "top.jsp");
  		    	session.setAttribute("css", "MF.css");
  		    	session.setAttribute("menucss", "menu.css");
  		    }
  		    
     		session.setAttribute("Login", lfb);
    			 return mapping.findForward("sucess");

		}

//////////////////////////////////////////HO //////////////////////////////////////////////////
	  public ActionForward UserForwardHO(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{	
  		    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            lfb.setAccess_t(f.getAccess_t()); 
	        	        
		    String typ=f.getAccess_t();
		    System.out.println("switch value "+typ);
		    if (typ.equals("MF"))
		    {
		    	  lfb.setD_type("A");
		    	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "homf_top.jsp");
			      session.setAttribute("css", "MF.css");
		    }
		    if (typ.equals("TF"))
		    {
		    	  lfb.setD_type("T");
				  session.setAttribute("footer", "tf_footer.jsp");
			      session.setAttribute("top", "hotf_top.jsp");
			      session.setAttribute("css", "TF.css");
		    }
		    if (typ.equals("Genetica"))
		    {
		    	  lfb.setD_type("G");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "hogf_top.jsp");
			      session.setAttribute("css", "Genetica.css");
		    }
	        	  session.setAttribute("Login", lfb);
	              return mapping.findForward("sucess");
		}
	  
/////////////////////////////////////////HO Close/////////////////////////////////////////	 

	  
//////////////////////////////////////////Depo ki alll Branch //////////////////////////////////////////////////
	  public ActionForward UserForwardBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{	
  		    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            lfb.setAccess_t(f.getAccess_t()); 
	        	        
		    String typ=f.getAccess_t();
		    if (typ.equals("MF"))
		    {
		    	  lfb.setD_type("A");
		    	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "branchmf_top.jsp");
			      session.setAttribute("css", "MF.css");
		    }
		    if (typ.equals("TF"))
		    {
		    	  lfb.setD_type("T");
				  session.setAttribute("footer", "tf_footer.jsp");
			      session.setAttribute("top", "branchtf_top.jsp");
			      session.setAttribute("css", "TF.css");
		    }
		    if (typ.equals("Genetica"))
		    {
		    	  lfb.setD_type("G");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "branchge_top.jsp");
			      session.setAttribute("css", "Genetica.css");
		    }
	        	  session.setAttribute("Login", lfb);
	              return mapping.findForward("sucess");
		}
	  
/////////////////////////////////////////Depo ki All Branch Close/////////////////////////////////////////	 
	  
	  
//////////////////////////////////////////Admin //////////////////////////////////////////////////
	  public ActionForward UserForwardAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{	
  		    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
            lfb.setAccess_t(f.getAccess_t()); 
            
		    String typ=f.getAccess_t();
		    
		    if (typ.equals("MF"))
		    {
		    	  lfb.setD_type("A");
		    	  session.setAttribute("footer", "footer.jsp");
			      session.setAttribute("top", "adminmf_top.jsp");
			      session.setAttribute("css", "MF.css");
		    }
		    if (typ.equals("TF"))
		    {
		    	  lfb.setD_type("T");
				  session.setAttribute("footer", "tf_footer.jsp");
			      session.setAttribute("top", "admintf_top.jsp");
			      session.setAttribute("css", "TF.css");
		    }
		    if (typ.equals("Genetica"))
		    {
		    	  lfb.setD_type("G");
				  session.setAttribute("footer", "g_footer.jsp");
			      session.setAttribute("top", "admingf_top.jsp");
			      session.setAttribute("css", "Genetica.css");
		    }
	        	  session.setAttribute("Login", lfb);
	              return mapping.findForward("sucess");
		}
	  
/////////////////////////////////////////HO Close/////////////////////////////////////////

///////////////////////////////////////////////Logout User Forward///////////////////////////////	
	  public ActionForward LogoutUserForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
//		  req.getSession().invalidate();	
		  req.getSession(false).invalidate();
		  res.setHeader("Cache-Control","no-cache");  

		  return mapping.findForward("sucess");
		}

//////////////////////////////////////////////////Login Again User Forward///////////////////////////////	  
	  public ActionForward LoginUserForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		  return mapping.findForward("sucess");
		}

	  
 //////////////////////// user list ke liye ////////////////////////////////
	  
	  public ActionForward UserList(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List UserList Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
 	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        UserForm af= (UserForm) form;
	        String sr=req.getParameter("search");	        
			LoginDAO  ad= DAOFactory.getLoginDAO();
			
			List user =ad.getAlluser(con,sr);
			
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}
	  
////////////////////////////////// Change Password ke liye ////////////////////////////////////
	  
	  public ActionForward Changepass(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List Changepass Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        

	        int k=0;
	        
	        LoginFormBean lfb1= new LoginFormBean();
	        LoginForm af= (LoginForm) form;

	        lfb1.setLogin_name(lfb.getLogin_name());
	        lfb1.setPassword(af.getPassword());
	        lfb1.setNew_pass(af.getN_password());
	        lfb1.setC_password(af.getC_password());
	        
	        
	        LoginDAO  ad= DAOFactory.getLoginDAO();
	        
			k= ad.Changepass(lfb1, con);
	        
			
						
			if (k>0)
				req.setAttribute("data", "Your Password has been Changed Sucessfully");
			else
				req.setAttribute("data", "Please Enter Correct Information");
				
			 	if (af.getPassword().equals("123"))
					  return mapping.findForward("Login");
				
				  return mapping.findForward("sucess");

		}	  
////////////////////////////////////////////////Change Password Forward/////////////////////////////////////	  
	  public ActionForward ChangepassForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
		  
		  return mapping.findForward("sucess");
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public ActionForward Changepass1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List Changepass Action class");       
	         
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        int k=0;
	        LoginFormBean lfb1= new LoginFormBean();
	        LoginForm af= (LoginForm) form;
	        lfb1.setLogin_name(lfb.getLogin_name());
	        lfb1.setPassword(af.getPassword());
	        lfb1.setNew_pass(af.getN_password());
	        lfb1.setC_password(af.getC_password());
	        LoginDAO  ad= DAOFactory.getLoginDAO();
			k= ad.Changepass(lfb1, con);
						
			if (k>0)
				req.setAttribute("data", "Your Password has been Changed Sucessfully");
			else
				req.setAttribute("data", "Please Enter Correct Information");

		      if (lfb.getType().equals("Factory"))
		      {
			    return mapping.findForward("Factory");
		      }
	          return mapping.findForward("sucess");

		}	  
////////////////////////////////////////////////Change Password Forward/////////////////////////////////////	  
	  public ActionForward ChangepassForward1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        if (lfb.getType().equals("Factory"))
            {
			    return mapping.findForward("Factory");
            }
		  
		  return mapping.findForward("sucess");
		}
	  
//////////////////////////////// Create user ki screen ka code//////////////////////////////////////////////////
		
	  public ActionForward OptUserType(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List OptUserType Action class");       
	        DataSource datasource = this.getDataSource(req,"userDB"); 
		    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        LoginForm af= (LoginForm) form;
	        
	        List repo=null;
			SQLOptDAO rd = new SQLOptDAO();

	        int opt=af.getOpt();
	        String type=af.getAccess_t();
			switch(opt)
	         {
	        	 case 1:
	        			repo =rd.getAlldepo(con); 
	        			break; 
	        	 case 2:
	        		 	repo =rd.getAllDGMCreate(con); 
	        			break; 
	        	 case 4: 
	        		 	repo =rd.getAllZMCreate(con);  
				        break;
	         }
			
			
	        //Repo1DAO  rd= DAOFactory.getRepo1();
			af.setTlist(repo);
			af.setOpt(opt);
		 
			req.setAttribute("rlist",repo);
			session.setAttribute("typ", type);
			session.setAttribute("option", new Integer(opt));
			 			
			
     if ((opt==3) || (opt==5))
			return mapping.findForward("all");
     else
		   return mapping.findForward("sucess"); 
	}


 /////////////////////////////////// Upload Status ke liye ///////////////////////////////////
	  
	  public ActionForward UploadStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List UploadStatus Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        String tp = lfb.getD_type();
//	        String coloour= lfb.getAccess_t();
	        //String tp =null;
	        int lid=lfb.getId();
	        
	        int depo=lfb.getCode();
/*	        if (coloour.equals("MF"))
	        	tp="A";
	        if (coloour.equals("TF"))
	        	tp="T";
	        if (coloour.equals("Genetica"))
	        	tp="G";
	*/        
	        
	        UploadForm af= (UploadForm) form;
			LoginDAO  ad= DAOFactory.getLoginDAO();
	
			List user =null;
			if (lfb.getType().equals("Admin"))
			{
				tp=lfb.getD_type(); 
				user =ad.getAllUploadAdmin(con,tp,lid,"S");
			}
			else
			{	
			  user =ad.getAllUpload(con,depo,tp,lid,"S");
			}
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}

	  public ActionForward UploadStatusS(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List UploadStatusSample Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        String coloour= lfb.getAccess_t();
	        String tp =null;
	        int lid=lfb.getId();
	        
	        int depo=lfb.getCode();
	        if (coloour.equals("MF"))
	        	tp="A";
	        if (coloour.equals("TF"))
	        	tp="T";
	        if (coloour.equals("Genetica"))
	        	tp="G";
	        
	        UploadForm af= (UploadForm) form;
			LoginDAO  ad= DAOFactory.getLoginDAO();
	
			List user =null;
			if (lfb.getType().equals("Both"))
			{
				tp=lfb.getD_type(); 
				user =ad.getAllUploadAdmin(con,tp,lid,"F");
			}
			else
			{	
			  user =ad.getAllUpload(con,depo,tp,lid,"F");
			}
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}
	  
	  
	  public ActionForward UserStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }

	        LoginForm af= (LoginForm) form;
	        String st=af.getStatus();
	        String sr=af.getSearch();
	        
			SQLLoginDAO  ad=new SQLLoginDAO(); 
			List user =null;
			user =ad.getUserStatus(con,st,sr);
			af.setUlist(user);
			return mapping.findForward("sucess");
		}
	  
///////////////////Branch Selection/////////////////////
	  public ActionForward BranchSelect(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
			DataSource datasource = this.getDataSource(req,"userDB"); 
 	  	    Connection con=null;
 	  	    
 	  	    LoginForm f = (LoginForm) form;
	        
 	  	    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }

            lfb.setCode(f.getCode()); 
            int cd=f.getCode();
            String tp=lfb.getD_type();
            
            System.out.println("afer branch selection tp is "+lfb.getD_type());
            
            
            
            SQLOptDAO rd = new SQLOptDAO();
			con=datasource.getConnection();
			String bname = rd.getBranchName(con, cd);
			lfb.setBranch_name(bname);
			
			con=datasource.getConnection();
			String msg = rd.getMsg(con,tp,cd);
			lfb.setMsg(msg);

			
			con=datasource.getConnection();
			List tab=rd.getTabMenu(con, lfb.getId());
			con=datasource.getConnection();
			List repo=rd.getRepoMenu(con, lfb.getId());
			
			session.setAttribute("tablist", tab);
			session.setAttribute("repolist", repo);
			session.setAttribute("Login", lfb);
            
            return mapping.findForward("sucess");
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////
	  
//////////////////////////////////Branch Selection//////////////////////////////////////////////
	  public ActionForward AllBranchSelect(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
			DataSource datasource = this.getDataSource(req,"userDB"); 
 	  	    Connection con=datasource.getConnection();
 	  	    
 	  	    LoginForm f = (LoginForm) form;
	        
 	  	    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }

	        
            lfb.setCode(f.getCode()); 
            int cd=f.getCode();
			SQLOptDAO rd = new SQLOptDAO();
            
			String bname = rd.getBranchName(con, cd);
			lfb.setBranch_name(bname);
            session.setAttribute("Login", lfb);
	        String coloour= lfb.getAccess_t();
	        session.setAttribute("access", coloour);
	        
	        return mapping.findForward("sucess");
		}
	  
	  
/////////////////////////////////////////Branch Forward/////////////////////
	  public ActionForward BranchForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }

		  
		  return mapping.findForward("sucess");
		}

	  
///////////////////CheckItem Forward under Tool windown/////////////////////
	  
	  public ActionForward CheckItemForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
			DataSource datasource = null; 
 	  	    Connection con=null;
 	  	    
 	  	    CheckItemForm f = (CheckItemForm) form;
	        
 	  	    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }

	        String tp = lfb.getD_type();
	        
        try{
	        datasource = this.getDataSource(req,"userDB"); 
 	  	    con=datasource.getConnection();
	        SQLCheckItemDAO chk = new SQLCheckItemDAO();
	        List l = chk.getWrongItem(con, tp);
	        f.setRlist(l);
            }
        catch(Exception e)
	         {
	        	 System.out.println("Error in connection "+e);
	         }
         finally
         {
                try{
            	    if(con!=null)
            	    	con=null;
                   }catch(Exception e)
                   {
                	   System.out.println("Connection is not null "+e);
                   }
         }
	        
		     return mapping.findForward("sucess");
		}
	  
	  
/////////////////End of CheckItem Forward under Tool windown/////////////////////
			  
///////////////////////////////////ReGistration User Forward ////////////////////////////////////////////

	  public ActionForward RegisterForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
 	  	    HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	         return mapping.findForward("sucess");
		}
	  
///////////////////////////////////ReGistration User Forward ////////////////////////////////////////

///////////////////////////////////Add User Rights////////////////////////////////////
	  
	  public ActionForward addUserRights(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
		   DataSource datasource = this.getDataSource(req,"userDB"); 
	  	   Connection con=null;
	  	   LoginForm f = (LoginForm) form;
			 
	  	   HttpSession session=req.getSession();
	       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	       if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	  	   
			  LoginFormBean fb = new LoginFormBean();
			  int uid = Integer.parseInt(req.getParameter("id"));
			  String typ = (String) req.getParameter("tp");
			  String tp=(String)req.getParameter("tp1");
			  fb.setId(uid);
			  fb.setType(typ);
			  fb.setCheckbox2(f.getCheckbox2());
			  List pmt_gp=null;
			  LoginDAO ldao = DAOFactory.getLoginDAO();
			  con=datasource.getConnection();			  
		  	  int i= ldao.addUserRights(fb,con);
			  if (i==-1)
			  return mapping.findForward("fail");
			  
			  if (typ.equals("PMT"))
			  {
				  con=datasource.getConnection();
				  pmt_gp=ldao.getAllGroup(con, tp);
				  req.setAttribute("pmt", pmt_gp);
				  req.setAttribute("uid", new Integer(uid));
				  req.setAttribute("tp", tp);				  
				  req.setAttribute("typ", typ);				  
				  return mapping.findForward("PMT");				  
			  }

			  if (typ.equals("HQ"))
			  {
				  con=datasource.getConnection();
				  pmt_gp=ldao.getAllDTer(con, tp,uid);
				  req.setAttribute("pmt", pmt_gp);
				  req.setAttribute("uid", new Integer(uid));
				  req.setAttribute("tp", tp);
				  req.setAttribute("typ", typ);				  
				  return mapping.findForward("HQ");				  
			  }
			  	  
			  
				 return mapping.findForward("sucess");
			
	  }	  
///////////////////////////////////Add User Rights////////////////////////////////////	  
	  
	  public ActionForward UserRightsList(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List UserList Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        LoginForm af= (LoginForm) form;
	        String sr=af.getSearch();
	        
//	        String sr=req.getParameter("search");	        
			LoginDAO  ad= DAOFactory.getLoginDAO();
			List user =ad.getUserRightList(con,sr);
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}
	  
//////////////////////////////////Change Password ke liye ////////////////////////////////////

	  public ActionForward addPmtGroup(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
		   DataSource datasource = this.getDataSource(req,"userDB"); 
	  	   Connection con=null;
	  	   LoginForm f = (LoginForm) form;
			 
	  	   HttpSession session=req.getSession();
	       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	       if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	  	   
		  LoginFormBean fb = new LoginFormBean();
		  int uid = Integer.parseInt(req.getParameter("id"));
		  String tp=(String)req.getParameter("tp1");
		  String typ=(String)req.getParameter("typ");		  

		  fb.setId(uid);
		  fb.setAccess_t(tp);
		  fb.setCheckbox2(f.getCheckbox2());
		  
		  LoginDAO ldao = DAOFactory.getLoginDAO();
		  con=datasource.getConnection();			
		  int i=0;
		  
		  if (typ.equals("PMT"))
		  {
	  	  i= ldao.addPmtGrp(fb, con);
		  }
		  
		  if (typ.equals("HQ"))
		  {
	  	  i= ldao.addTerUser(fb, con);
		  }
			 
		  if (i==-1)
		  return mapping.findForward("fail");
			 
		  return mapping.findForward("sucess");
			
	  }	  	  

	  public ActionForward PmtRightsList(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List PMT USER Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        LoginForm af= (LoginForm) form;
	        String sr=af.getSearch();	        
			LoginDAO  ad= DAOFactory.getLoginDAO();
			List user =ad.getPmtRightList(con,sr);
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}
	  

	  public ActionForward EntryForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
		  
		    DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        int depo = lfb.getCode();
	  	    LoginForm f = (LoginForm) form;
			f.setYlist(lfb.getYlist());
			f.setTrd_sale(0.00);
			f.setSales_bud(0.00);
			f.setAch(0.00);
			f.setSurdef(0.00);
			f.setLmsale(0.00);
			f.setLysale(0.00);
			f.setCn100(0.00);
			f.setCollc(0.00);
			f.setCollcumm(0.00);
			f.setRemit(0.00);
			f.setRemitcumm(0.00);
			f.setOutstnd(0.00);
			f.setSale_today(0.00);
			f.setBud_per(0.00);
			f.setBranch_name(lfb.getBranch_name());
			
			
			DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateformat2 = new SimpleDateFormat("MM");
			f.setEdate(dateformat.format(new Date()));
			
			int mno=Integer.parseInt(dateformat2.format(new Date()));
			int myear = ((LoginFormBean) lfb.getYlist().get(0)).getYval();

			System.out.println("myear "+myear+" mno "+mno);
			
			SQLOptDAO rd= new SQLOptDAO();
			List repo=rd.getDailyEntryMaster(con, 1, depo);
			f.setBrList(repo);

			con=datasource.getConnection();
			LoginFormBean lf = rd.getTarget(con, 1, depo,myear,mno,f.getEdate());
			if(lf!=null)
			{
				f.setBud_per(lf.getBud_per());
				f.setSales_bud(lf.getSales_bud());
			}
			
			
			
	        return mapping.findForward("sucess");
		}
	  	  

	  public ActionForward EntryForwardAjax(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
		  
		    DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        int depo = lfb.getCode();
	  	    LoginForm f = (LoginForm) form;
	  	  
			f.setTrd_sale(0.00);
			f.setSales_bud(0.00);
			f.setAch(0.00);
			f.setSurdef(0.00);
			f.setLmsale(0.00);
			f.setLysale(0.00);
			f.setCn100(0.00);
			f.setCollc(0.00);
			f.setCollcumm(0.00);
			f.setRemit(0.00);
			f.setRemitcumm(0.00);
			f.setOutstnd(0.00);
			f.setSale_today(0.00);
			f.setBud_per(0.00);
			
		 
			
			int myear=Integer.parseInt(req.getParameter("year"));
			int div=Integer.parseInt(req.getParameter("div"));
			int mno=Integer.parseInt(req.getParameter("mon"));
			String edate = (String) req.getParameter("edate");

			System.out.println("div "+div+" myear "+myear+" mno "+mno+" edate "+edate);
			
			SQLOptDAO rd= new SQLOptDAO();

			LoginFormBean lf = rd.getTarget(con, div, depo,myear,mno,edate);
			req.setAttribute("lf", lf);
			String rtval="sucess";
			if(lf!=null)
			{
				f.setBud_per(lf.getBud_per());
				f.setSales_bud(lf.getSales_bud());
				f.setTrd_sale(lf.getTrd_sale());
				f.setAch(lf.getAch());
				f.setSurdef(lf.getSurdef());
				f.setLmsale(lf.getLmsale());
				f.setLysale(lf.getLysale());
				f.setCn100(lf.getCn100());
				f.setCollc(lf.getCollc());
				f.setCollcumm(lf.getCollcumm());
				f.setRemit(lf.getRemit());
				f.setRemitcumm(lf.getRemitcumm());
				f.setOutstnd(lf.getOutstnd());
				f.setSale_today(lf.getSale_today());
				f.setStatus(lf.getStatus());
				if(lf.getStatus()!=null && lf.getStatus().equalsIgnoreCase("Close"))
					rtval="close";  
			}
			
		
			
	        return mapping.findForward(rtval);
		}
	  
	  
	  public ActionForward AddEntry(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
			  
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
			  
	  	   	  int depo = lfb.getCode();
			  LoginFormBean fb = new LoginFormBean();
			  fb.setAccess_t(f.getAccess_t());

		   	  //Date edt=new Date();
		   	  
		   	  DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			  Date edt=dateformat.parse(f.getEdate());
		   	  
			  fb.setCode(depo);
		   	  fb.setEdate(edt);
			  fb.setTrd_sale(f.getTrd_sale());
			  fb.setSales_bud(f.getSales_bud());
			  fb.setCollc(f.getCollc());
			  fb.setRemit(f.getRemit());
			  fb.setCollcumm(f.getCollcumm());
			  fb.setRemitcumm(f.getRemitcumm());
			  fb.setAch(f.getAch());
			  fb.setSurdef(f.getSurdef());
			  fb.setLmsale(f.getLmsale());
			  fb.setLysale(f.getLysale());
			  fb.setCn100(f.getCn100());
			  fb.setOutstnd(f.getOutstnd());
			  fb.setStatus(f.getStatus());
			  fb.setSale_today(f.getSale_today());
			  fb.setBud_per(f.getBud_per());
			  fb.setBranch_name(f.getBranch_name());
			 // fb.setMnth(f.getMnth());
			  fb.setEyear(f.getEyear());
			  
			  
			  LoginDAO ldao = DAOFactory.getLoginDAO(); 
			  int i=ldao.AddEntry(fb, con,depo);
			   
			  
			  
			 
			  ActionRedirect redirect = new ActionRedirect(mapping.findForward("sucessopen"));
			  redirect.addParameter("sucess", i);
			  return redirect;
			  
			  //return mapping.findForward("sucessopen");
			
	  }
	  
	  public ActionForward DailyForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        LoginForm f = (LoginForm) form;
	        ArrayList divList = new ArrayList(lfb.getDivlist());
	        LoginFormBean lf = new LoginFormBean();
	        lf.setDcode(99);
      	  	lf.setDname("ALL"); 
      	  	lf.setD_type("Z");
      	  	divList.add(0, lf);
      	  	//divList.add(lf);
      	  
			f.setDivlist(divList);
			
			
			DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			f.setEdate(dateformat.format(new Date()));
			return mapping.findForward("sucess");
		}	
	  

	  public ActionForward DailyForwardAjax(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
		  	DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=null;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        LoginForm f = (LoginForm) form;
	        int uid = lfb.getId();
		    List repo=null;
		    SQLOptDAO rd = new SQLOptDAO();
		    con=datasource.getConnection();
			repo=rd.getUserBranch(con,uid);
	        LoginFormBean lf = new LoginFormBean();
	        lf.setDcode(99);
    	  	lf.setDname("ALL"); 
    	  	repo.add(0, lf);
    	  
			f.setDivlist(repo);
			
			return mapping.findForward("sucess");
		}	
	  

	  
	  
	  public ActionForward DailyList(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List Daily Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        
	        
	        DateFormat dateformat2 = new SimpleDateFormat("dd/MM/yyyy");
	        DateFormat dateformat3 = new SimpleDateFormat("MMM-yyyy");
			Date edate=dateformat2.parse(f.getEdate());
	       
	       //Date edate=dateformat.parse(req.getParameter("date"));

			
			String typ=f.getType();
		   	String hdiv="";
		   	int uid = lfb.getId();
		   	int opt = f.getOpt();
		   	int mon = Integer.parseInt(f.getMnth());
		   	List divList = lfb.getDivlist();
		   	
		  		
		    
		   	if(divList.size()==0)
		   		typ=lfb.getD_type();
		   		 
		  
			
		   	/*String divi[] =f.getCheckbox2();
		   	String hdiv="";
		   	int uid = lfb.getId();
		   	for (int i=0;i<divi.length;i++)
		   	{
		   		if (divi[i].equals("A"))
		   			hdiv=hdiv+"MF ";
		   		if (divi[i].equals("T"))
		   			hdiv=hdiv+"TF ";
		   		if (divi[i].equals("G"))
		   			hdiv=hdiv+"Genetica ";

		   	}
		   	*/

	   		if (typ.equals("A"))
	   			hdiv=hdiv+"MF ";
	   		if (typ.equals("T"))
	   			hdiv=hdiv+"TF ";
	   		if (typ.equals("G"))
	   			hdiv=hdiv+"Genetica ";

			
//		   	String head = "BILLING AS ON  " +f.getEdate()+" [ "+hdiv+"]";
		   	String head = "BILLING MONTH  " +dateformat3.format(edate).toUpperCase()+" AS ON "+ dateformat2.format(edate)+ " (In Lakhs)";
		   	if(opt==3)
				head = "UPDATION STATUS FOR THE MONTH  " +dateformat3.format(edate).toUpperCase()+ " AS ON "+dateformat2.format(edate);

			LoginDAO ldao = DAOFactory.getLoginDAO(); 
			//List d = ldao.DailyList(con, edate, divi,uid);
			List d = null;
			if(opt==1)
				d=ldao.DailyList(con, edate, typ,uid,opt,divList,mon);
			else if(opt==2)
				d=ldao.DailyListNew(con, edate, typ,uid,opt,divList,mon);
			else if(opt==3)
				d=ldao.DailyListStatus(con, edate, typ,uid,opt,divList,mon);
			
			f.setUlist(d);
			req.setAttribute("whead", head); 
	        
			if(opt==3)
			{
				return mapping.findForward("newsucess");
			}
			else
				return mapping.findForward("sucess");
		}	  

	  public ActionForward BudSalForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
		  	DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=null;
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        int uid = lfb.getId();
		    List repo=null;
		    SQLOptDAO rd = new SQLOptDAO();
		    con=datasource.getConnection();
			repo=rd.getUserBranch(con,uid);
			f.setTlist(repo);
			
			f.setYlist(lfb.getYlist());
			
			return mapping.findForward("sucess");
		}
	  
	  public ActionForward AddBudSal(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
			DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
				  	   	
			LoginFormBean fb = new LoginFormBean();
			fb.setAccess_t(f.getAccess_t());
			fb.setMnth(f.getMnth());
			fb.setEyear(f.getEyear());
			fb.setCode(f.getCode());
			fb.setSales_bud(f.getSales_bud());
			  
			LoginDAO ldao = DAOFactory.getLoginDAO(); 
			ldao.AddBudSal(fb, con);
			  
			return mapping.findForward("sucess");
			
	  }	  

	  public ActionForward BudColForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        
		  	DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=null;
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        int uid = lfb.getId();
		    List repo=null;
		    SQLOptDAO rd = new SQLOptDAO();
		    con=datasource.getConnection();
			repo=rd.getUserBranch(con,uid);
			f.setTlist(repo);
			
			f.setYlist(lfb.getYlist());
			
			return mapping.findForward("sucess");
		}	  
	
	  public ActionForward AddBudCol(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
			DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	  	    LoginForm f = (LoginForm) form;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
				  	   	
			LoginFormBean fb = new LoginFormBean();
			fb.setAccess_t(f.getAccess_t());
			fb.setMnth(f.getMnth());
			fb.setEyear(f.getEyear());
			fb.setCode(f.getCode());
			fb.setCollc(f.getCollc());
			  
			LoginDAO ldao = DAOFactory.getLoginDAO(); 
			ldao.AddBudCol(fb, con);
			  
			return mapping.findForward("sucess");
			
	  }	  
	  
}