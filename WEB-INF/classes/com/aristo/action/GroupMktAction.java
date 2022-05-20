package com.aristo.action;

import java.sql.Connection;
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
import com.aristo.dao.AccountDAO;
import com.aristo.dao.GroupMktDAO;
import com.aristo.dao.DAOFactory;
import com.aristo.dao.GroupSalesDAO;
import com.aristo.dao.ProductDAO;
import com.aristo.dao.RegionDAO;
import com.aristo.dao.SQLGroupMktDAO;
import com.aristo.dao.SQLGroupSalesDAO;
import com.aristo.dao.SQLProductDAO;
import com.aristo.dao.SQLSampleAccountDAO;
import com.aristo.form.AccountForm;
import com.aristo.form.GroupMktForm;
import com.aristo.form.GroupSalesForm;
import com.aristo.form.ProductForm;
import com.aristo.form.RegionForm;
import com.aristo.valueobject.GroupMktFormBean;
import com.aristo.valueobject.GroupSalesFormBean;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.ProductFormBean;


public class GroupMktAction extends DispatchAction {

	
	public ActionForward ListGroupMkt(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List GroupMKT Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

        String tp = lfb.getD_type();
        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}

        
        GroupMktForm af= (GroupMktForm) form;
		
		GroupMktDAO  ad= DAOFactory.getGroupMktDAO();
		List GroupMkt =ad.getAllGroupMkt(con, tp,eyear);
		 
		af.setGplist(GroupMkt);
		
		return mapping.findForward("sucess");
	}
public ActionForward ListGroupSales(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List GroupSales Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	   Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();

        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}        
        GroupSalesForm af= (GroupSalesForm) form;
		
		GroupSalesDAO  ad= DAOFactory.getGroupSalesDAO();
		List GroupSales =ad.getAllGroupSales(con, tp,eyear);
		
		af.setGpsales(GroupSales);
		
		return mapping.findForward("sucess");
	}	

/////////////////////////////////////////////////////////////////////////////////////////////////////	
	public ActionForward ListProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Product Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        int eyear=0;
        Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
        
        ProductForm af= (ProductForm) form;
		ProductDAO  ad= DAOFactory.getProductDAO();
		List Product =ad.getAllProduct(con,tp,eyear);
		
		af.setPlist(Product);
		
		return mapping.findForward("sucess");
	}	

///////////////////////////////////////////////////////////////////////////////////////////
	public ActionForward ListRegion(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Region Action class");       
        
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
        
        RegionForm af= (RegionForm) form;
		
		RegionDAO  ad= DAOFactory.getRegionDAO();
		List Region =ad.getAllRegion(con,depo_code,tp,eyear); 
		 
		af.setRlist(Region);
		
		return mapping.findForward("sucess");
	}
	
	
///////////////////////////////////////////////List Account//////////////////////////////////////	
	public ActionForward ListAccount(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Account Action class");       
        
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
        String utype = lfb.getType();
        int uid = lfb.getId();
        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}        
             
        AccountForm af= (AccountForm) form;
		AccountDAO  ad= DAOFactory.getAccountDAO();
		List Account =ad.getAllAccount(con,depo_code,tp,eyear,utype,uid); 
		 
		af.setRlist(Account);
		if (Account.size()>0)
		{
		return mapping.findForward("sucess");
		}
		else
		return mapping.findForward("norec");
		
	}	

///////////////////////////////////////////////List Account//////////////////////////////////////	
	public ActionForward ListFS(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Field Staff Action class");       
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
        String utype=lfb.getType();
        int uid = lfb.getId();
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}        
        
        AccountForm af= (AccountForm) form;
		SQLSampleAccountDAO  ad = new SQLSampleAccountDAO();
		List Account =ad.getAllAccount(con,depo_code,tp,eyear,utype,uid); 
		af.setRlist(Account);
		
		return mapping.findForward("sucess");
	}	
	
	
public ActionForward AdminListProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
	{
	    System.out.println("Calleed AdminList Product Action class");       
	    DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	    if (lfb==null)
	    {
	    	return mapping.findForward("sfail");
	    }
	    String tp = lfb.getD_type();
	    int eyear=0;
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
	    ProductForm af= (ProductForm) form;
		ProductDAO  ad= DAOFactory.getProductDAO();
		List Product =ad.getAllProduct(con,tp,eyear);  
		af.setPlist(Product);
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward AddProductForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed AddProductForward Action class");       
		DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int eyear=0;
	    Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
       	         
	    ProductForm af= (ProductForm) form;
		SQLProductDAO  ad= new SQLProductDAO();
		List l = ad.getGlist(con,tp,eyear);
		con=datasource.getConnection();		
		List gl = ad.getGplist(con,tp,eyear);		
		af.setGlist(l);
		af.setGplist(gl);
		
		return mapping.findForward("sucess");
	}
	
	  public ActionForward AddProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
		    System.out.println("Calleed AddProduct Action class");
			DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        String tp = lfb.getD_type();
	        int eyear=0;
		    Iterator itt = lfb.getYlist().iterator();
			if (itt.hasNext())
			{
				lfb=(LoginFormBean)(itt.next());
				eyear=lfb.getYval();
			}
	       	        
	        ProductFormBean b= new ProductFormBean();
		    ProductForm af= (ProductForm) form;
		    b.setPcode(af.getPcode());
			b.setPname(af.getPname());
			b.setPack(af.getPack());
			b.setPd_group(af.getPd_group());
			b.setMcode(af.getMcode());
			b.setMname(af.getMname());
			b.setMgrp(af.getMgrp());
			b.setTn(af.getTn());			
			 
			ProductDAO  ad= DAOFactory.getProductDAO();
			int k =ad.AddProduct(b,con,tp,eyear);
			if (k==2)
				return mapping.findForward("fail");
			else
			    return mapping.findForward("sucess");
			
	  }
	  
    public ActionForward EditProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
			int pcode= tf.getPcode();
			
			fb =rd.EditProduct(con,tp,pcode);
			
		    tf.setPcode(pcode);
			tf.setPname(fb.getPname());
			tf.setPack(fb.getPack());
			tf.setPd_group(fb.getPd_group());
			tf.setMcode(fb.getMcode());
			tf.setMname(fb.getMname());
			tf.setMgrp(fb.getMgrp());
			tf.setTn(fb.getTn());
	        
			return mapping.findForward("sucess");
    }

    public ActionForward UpdateProduct(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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

	    fb.setPcode(tf.getPcode());
		fb.setPname(tf.getPname());
		fb.setPack(tf.getPack());
		fb.setPd_group(tf.getPd_group());
		fb.setMcode(tf.getMcode());
		fb.setMname(tf.getMname());
		fb.setMgrp(tf.getMgrp());
		fb.setTn(tf.getTn());
	    
	    int j=rd.UpdateProduct(con, fb,tp);
	    
	    if (j>0)
		    return mapping.findForward("sucess");	    
	    else 
	        return mapping.findForward("fail");	        	    
	}    
	  
	  
	  public ActionForward AdminListGroupMkt(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("Calleed AdminList GroupMKT Action class");       
	    DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	    if (lfb==null)
	    {
	    	return mapping.findForward("sfail");
	    }
	    String tp = lfb.getD_type();
	    int eyear=0;
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
	    GroupMktForm af= (GroupMktForm) form;
		GroupMktDAO  ad= DAOFactory.getGroupMktDAO();
		List GroupMkt =ad.getAllGroupMkt(con, tp,eyear);
		af.setGplist(GroupMkt);
		
		return mapping.findForward("sucess");
	}
	public ActionForward AddGroupMktForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("Calleed AddGroupMKTForward Action class");       
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	    if (lfb==null)
	    {
	    	return mapping.findForward("sfail");
	    }
		return mapping.findForward("sucess");
	}
	

	  public ActionForward AddGroupMkt(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        GroupMktForm af= (GroupMktForm) form;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int eyear=0;
	    Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}

        GroupMktFormBean fb = new GroupMktFormBean();
        fb.setGmain_cd(af.getGmain_cd());
        fb.setGp_name(af.getGp_name());
	  
        GroupMktDAO  ad= DAOFactory.getGroupMktDAO();
		  
        int i= ad.AddGroupMkt(fb, con, tp,eyear);
        if (i==-1)
		return mapping.findForward("fail");
				  				  
        return mapping.findForward("sucess");
			
	  }	 	

    public ActionForward EditGrpMkt(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
        GroupMktForm tf= (GroupMktForm) form;		    
        SQLGroupMktDAO rd= new SQLGroupMktDAO();        
        GroupMktFormBean fb = new GroupMktFormBean();
		int gp_code= tf.getGp_code();
		
		fb =rd.EditGroupMkt(con,tp,gp_code);
        
        tf.setGmain_cd(gp_code);
        tf.setGp_name(fb.getGp_name());
		
		return mapping.findForward("sucess");
    }

    public ActionForward UpdateGrpMkt(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	    GroupMktForm tf = (GroupMktForm)form;
	    SQLGroupMktDAO rd = new SQLGroupMktDAO();
	    GroupMktFormBean fb = new GroupMktFormBean();
	    fb.setGmain_cd(tf.getGmain_cd());
	    fb.setGp_name(tf.getGp_name());
		
	    int j=rd.UpdateGrpMkt(con, fb,tp);
	    
	    if (j>0)
		    return mapping.findForward("sucess");	    
	    else 
	        return mapping.findForward("fail");	        	    
	}
    
	public ActionForward AdminListGroupSales(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("Calleed AdminList GroupSales Action class");       
	    DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	    if (lfb==null)
	    {
	    	return mapping.findForward("sfail");
	    }
	    String tp = lfb.getD_type();
	    int eyear=0;
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
	    GroupSalesForm af= (GroupSalesForm) form;
		GroupSalesDAO  ad= DAOFactory.getGroupSalesDAO();
		List GroupSales =ad.getAllGroupSales(con, tp,eyear);
		af.setGpsales(GroupSales);
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward AddGroupSalesForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("Calleed AddGroupSalesForward Action class");       
	    HttpSession session=req.getSession();
	    LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	    if (lfb==null)
	    {
	    	return mapping.findForward("sfail");
	    }
		return mapping.findForward("sucess");
	}
	    	
	public ActionForward AddGroupSales(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res ) throws Exception
	  {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
        GroupSalesForm af= (GroupSalesForm) form;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int eyear=0;
	    Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}

        GroupSalesFormBean fb = new GroupSalesFormBean();
        fb.setGp_code(af.getGp_code());
        fb.setGp_name(af.getGp_name());
	    
		GroupSalesDAO  ad= DAOFactory.getGroupSalesDAO();		  
        
		int i= ad.AddGroupSales(fb, con, tp,eyear);
        if (i==-1)
		return mapping.findForward("fail");
				  				  
        return mapping.findForward("sucess");
			
	  }	 	
	
    public ActionForward EditGrpSales(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
        GroupSalesForm tf= (GroupSalesForm) form;		    
        SQLGroupSalesDAO rd= new SQLGroupSalesDAO();        
        GroupSalesFormBean fb = new GroupSalesFormBean();
		int gp_code= tf.getGp_code();
		
		fb =rd.EditGroupSales(con,tp,gp_code);
        
        tf.setGmain_cd(gp_code);
        tf.setGp_name(fb.getGp_name());
		
		return mapping.findForward("sucess");
    }
	
    public ActionForward UpdateGrpSales(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
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
	    GroupSalesForm tf = (GroupSalesForm)form;
	    SQLGroupSalesDAO rd = new SQLGroupSalesDAO();
	    GroupSalesFormBean fb = new GroupSalesFormBean();
	    fb.setGp_code(tf.getGp_code());
	    fb.setGp_name(tf.getGp_name());
		
	    int j=rd.UpdateGrpSales(con, fb,tp);
	    
	    if (j>0)
		    return mapping.findForward("sucess");	    
	    else 
	        return mapping.findForward("fail");	        	    
	}	
	
	
}
 