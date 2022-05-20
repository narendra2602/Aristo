package fac.aristo.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import fac.aristo.form.BranchForm;
import fac.aristo.form.LocationForm;
import fac.aristo.form.ProductForm;
import fac.aristo.dao.SQLBranchDAO;
import fac.aristo.dao.SQLLocationDAO;
import fac.aristo.dao.SQLProductDAO;


import com.aristo.valueobject.LoginFormBean;
import fac.aristo.valueobject.ProductFormBean;

public class FactoryMasterAction extends DispatchAction {

	public ActionForward ListFBranch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Factory Branch Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	   	Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        BranchForm af= (BranchForm) form;
		
		SQLBranchDAO  ad= new SQLBranchDAO();
		List Branch =ad.getAllBranch(con,tp);
		
		af.setBlist(Branch);
		
		return mapping.findForward("sucess");
	}	

	
	public ActionForward ListFProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Factory Product Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	   	Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        String sr=req.getParameter("search");
        ProductForm af= (ProductForm) form;
		
		SQLProductDAO  ad= new SQLProductDAO();
		List Product =ad.getAllProduct(con,tp,sr);
		af.setPlist(Product);
		return mapping.findForward("sucess");
	}		
	
    public ActionForward FProdEdit(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	        ProductForm tf= (ProductForm) form;		    
	        SQLProductDAO rd= new SQLProductDAO();        
	        ProductFormBean fb = new ProductFormBean();
			String fpcode= tf.getF_pcode();
			
			fb =rd.EditProduct(con,tp,fpcode);
			
			tf.setF_pcode(fpcode);
			tf.setF_pname(fb.getF_pname());
		    tf.setPcode(fb.getPcode());
			tf.setPname(fb.getPname());
			tf.setPack(fb.getPack());
			tf.setDivision(fb.getDivision());
			tf.setTp(fb.getTp());
	        
			return mapping.findForward("sucess");
    }

    public ActionForward FProdUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	    ProductForm tf = (ProductForm)form;
	    SQLProductDAO rd = new SQLProductDAO();
	    ProductFormBean fb = new ProductFormBean();
	    fb.setF_pcode(tf.getF_pcode());
	    fb.setPcode(tf.getPcode());
		fb.setPname(tf.getPname());
		fb.setPack(tf.getPack());
		fb.setDivision(tf.getDivision());
		fb.setTp(tf.getTp());
	    
	    int j=rd.UpdateProduct(con, fb,tp);
	    
	    if (j>0)
		    return mapping.findForward("sucess");	    
	    else 
	        return mapping.findForward("fail");	        	    
	}  	
	
	
	
	public ActionForward ListFLocation(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Factory Location Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	   	Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        LocationForm af= (LocationForm) form;
		
		SQLLocationDAO  ad= new SQLLocationDAO();
		List Location =ad.getAllLocation(con,tp);
		
		af.setLlist(Location);
		
		return mapping.findForward("sucess");
	}			
	
	
}
