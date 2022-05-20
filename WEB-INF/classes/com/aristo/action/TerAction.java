package com.aristo.action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.aristo.dao.SQLTerDAO;
import com.aristo.dao.TerDAO;
import com.aristo.dao.DAOFactory;
import com.aristo.form.TerForm;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.TerFormBean;

 
public class TerAction extends DispatchAction {
	
	public ActionForward ListTer(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Ter Action class");       
        HttpSession session=req.getSession();
        DataSource datasource = this.getDataSource(req,"userDB"); 
 	    Connection con=datasource.getConnection();
        
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();

        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}        
        TerForm af= (TerForm) form;
		
		TerDAO  ad= DAOFactory.getTer();
		List ter =ad.getAllter(con,depo_code,tp,eyear);
		
		
		af.setTlist(ter);
        		

        return mapping.findForward("sucess");
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
	public ActionForward ListDgm(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List DGM Action class");       

        HttpSession session=req.getSession();
        DataSource datasource = this.getDataSource(req,"userDB"); 
 	    Connection con=datasource.getConnection();
        
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        
        TerForm af= (TerForm) form;
		
		TerDAO  ad= DAOFactory.getTer();
		List ter =ad.getAllDgm(con,tp);
		
		af.setTlist(ter);
        
        return mapping.findForward("sucess");
	}
		
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
	public ActionForward ListZm(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ZM Action class");       

        HttpSession session=req.getSession();
        DataSource datasource = this.getDataSource(req,"userDB"); 
 	    Connection con=datasource.getConnection();
        
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        
        TerForm af= (TerForm) form;
		
		TerDAO  ad= DAOFactory.getTer();
		List ter =ad.getAllZm(con,tp);
		
		af.setTlist(ter);
        
        return mapping.findForward("sucess");
	}
		
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward ListBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed All-Branch TerAction class");       
        HttpSession session=req.getSession();
        DataSource datasource = this.getDataSource(req,"userDB"); 
 	    Connection con=datasource.getConnection();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
         
        String tp = lfb.getD_type();
        TerForm af= (TerForm) form;
		TerDAO  ad= DAOFactory.getTer();
		List ter =ad.getAllBranch(con,tp);
		
		
		af.setTlist(ter);
        		

        return mapping.findForward("sucess");
	}	
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	  public ActionForward AddBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
	      DataSource datasource = this.getDataSource(req,"userDB"); 
	  	  Connection con=datasource.getConnection();
	      TerForm af= (TerForm) form;
          HttpSession session=req.getSession();
	      LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	      if (lfb==null)
	      {
	       	return mapping.findForward("sfail");
	      }
          String tp = lfb.getD_type();
		  TerFormBean fb = new TerFormBean();
		  fb.setDepo_code(af.getDepo_code());
		  fb.setTer_name(af.getTer_name());
		  fb.setNo_of_rep(af.getNo_of_rep());
		  
		  TerDAO  ad= DAOFactory.getTer();
		  int i= ad.AddBranch(fb, con, tp);
		  con = datasource.getConnection();
		  List ter =ad.getAllBranch(con,tp);
		  af.setTlist(ter);
			  
		  if (i==-1)
		     return mapping.findForward("fail");
    		 return mapping.findForward("sucess");
			
	  }	
	
	
    public ActionForward EditBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	        String tp = lfb.getD_type();	        
	        TerForm tf= (TerForm) form;		    
	        SQLTerDAO rd = new SQLTerDAO();
	        TerFormBean fb = new TerFormBean();
			int depo= tf.getDepo_code();
			
			fb =rd.EditBranch(con,depo,tp);
	        
			tf.setDepo_code(depo);
	        tf.setTer_name(fb.getTer_name());
	        tf.setNo_of_rep(fb.getNo_of_rep());
			
			return mapping.findForward("sucess");
	    }

    public ActionForward UpdateBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
        String tp = lfb.getD_type(); 
        TerForm tf = (TerForm)form;
        SQLTerDAO rd = new SQLTerDAO();
        TerFormBean fb = new TerFormBean();
        fb.setDepo_code(tf.getDepo_code());
   	    fb.setTer_name(tf.getTer_name());
    	fb.setNo_of_rep(tf.getNo_of_rep());
	    
        int j=rd.UpdateBranch(con, fb,tp);
        
        if (j>0)
    	    return mapping.findForward("sucess");	    
        else 
            return mapping.findForward("fail");	        	    
    }
    
    
    
    
    
	public ActionForward AddBranchForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed AddBranchForward Action class");       
    
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        DataSource datasource = this.getDataSource(req,"userDB"); 
 	    Connection con=datasource.getConnection();
        
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
		
        String tp = lfb.getD_type();
        TerForm af= (TerForm) form;
		TerDAO  ad= DAOFactory.getTer();
		List ter =ad.getAllDgm(con,tp);
		con=datasource.getConnection();
		List zm=ad.getAllZm(con, tp);
		af.setTlist(ter);
        af.setRlist(zm);
        
		return mapping.findForward("sucess");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward AddDgmForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed AddDgmForward Action class");       
        

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////	
	  public ActionForward AddDgm(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
			  
	       DataSource datasource = this.getDataSource(req,"userDB"); 
	  	   Connection con=datasource.getConnection();
	  	   
	       TerForm af= (TerForm) form;
           HttpSession session=req.getSession();
	       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	  	   
	          String tp = lfb.getD_type();
			  TerFormBean fb = new TerFormBean();
			  
			  fb.setDgm_code(af.getDgm_code());
			  fb.setDgm_name(af.getDgm_name());
			  fb.setBranches(af.getBranches());
			  
			  TerDAO  ad= DAOFactory.getTer();
			  

				  
			  int i= ad.AddDgm(fb, con, tp);
			  con = datasource.getConnection();
			  List ter =ad.getAllDgm(con,tp);
			  af.setTlist(ter);
			  
			  if (i==-1)
				  return mapping.findForward("fail");
			 
				  				  
				  return mapping.findForward("sucess");
			
	  }	
	
////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward AddZmForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed AddZmForward Action class");       
        

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	  public ActionForward AddZm(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
	       DataSource datasource = this.getDataSource(req,"userDB"); 
	  	   Connection con=datasource.getConnection();
	  	   
	       TerForm af= (TerForm) form;
           HttpSession session=req.getSession();
	       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	  	   
	          String tp = lfb.getD_type();
			  TerFormBean fb = new TerFormBean();
			  
			  fb.setZm_code(af.getZm_code());
			  fb.setZm_name(af.getZm_name());
			  fb.setDgm_code(af.getDgm_code());
			  fb.setDgm_name(af.getDgm_name());
			  fb.setBranches(af.getBranches());
			  
			  TerDAO  ad= DAOFactory.getTer();
				  
			  int i= ad.AddZm(fb, con, tp);
			  con = datasource.getConnection();
			  List ter =ad.getAllZm(con,tp);
			  af.setTlist(ter);
			  
			  if (i==-1)
				  return mapping.findForward("fail");
			 
				  				  
				  return mapping.findForward("sucess");
			
	  }	
	
////////////////////////////////////////////////////////////////////////////////////////////////	
	
	    public TerFormBean dgm12 (TerFormBean ldao, Connection con)
		{

			TerFormBean lf = null;
			try {
				
				PreparedStatement ps = con.prepareStatement("Select * from a_dgm_master08 where dgm_code=? ");
				
				ps.setInt(1, ldao.getDgm_code());
				ResultSet rs = ps.executeQuery();   
				
				if (rs.next())
				{
	             lf = new TerFormBean();
	              lf.setDgm_name(rs.getString(2));

	              lf.setBranches(rs.getString(3));
				}
				else
				  
				rs.close();
				ps.close();	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally {
//				enclose this in a finally block to make
//				sure the connection is closed
				try {

			        	con.close();
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
				  }
			}	
			  
			return lf ; 
			
		}
	
		
		
	    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	    	
        TerForm tf = (TerForm)form;
	     
	    TerAction ta= new TerAction();
	    ta.dgm123(tf,con);
	    
	      
	    
        return mapping.findForward("sucess");
        
    }
	
	    
	    
	    
		public void dgm123 (TerForm tr1, Connection con)
		{
			int i=0;
			try {
				
				PreparedStatement ps1 = con.prepareStatement("update a_dgm_master08 set dgm_name=?,branches=? where dgm_code=? ");
                ps1.setString(1, tr1.getDgm_name());
                ps1.setString(2, tr1.getBranches());
                ps1.setInt(3, tr1.getDgm_code());
				
                i=ps1.executeUpdate();
				ps1.close();
				System.out.println(i);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally {
//				enclose this in a finally block to make
//				sure the connection is closed
				try {

			        	con.close();
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
				  }
			}	
			  
			
			
		}
	
	
}
