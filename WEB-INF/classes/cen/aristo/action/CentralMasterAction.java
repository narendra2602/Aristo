package cen.aristo.action;   

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

import cen.aristo.dao.AccountCentralDAO;
import cen.aristo.dao.BatchDAO;
import cen.aristo.dao.SQLAccountDAO;
import cen.aristo.dao.SQLBatchDAO;
import cen.aristo.form.AccountMasterForm;
import cen.aristo.form.BatchMasterForm;

import com.aristo.dao.DAOFactory;
import com.aristo.dao.GroupSalesDAO;
import com.aristo.dao.LoginDAO;
import cen.aristo.dao.SQLProductDAO;
import com.aristo.form.GroupSalesForm;
import com.aristo.form.ProductForm;
import com.aristo.form.UploadForm;

import com.aristo.valueobject.LoginFormBean; 

public class CentralMasterAction extends DispatchAction {
	
	public ActionForward ListProduct1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        ProductForm af= (ProductForm) form;
		
		SQLProductDAO  ad= new SQLProductDAO();
		List Product =ad.getAllProduct(con,tp);
		
		af.setPlist(Product);
		
		return mapping.findForward("sucess");
	}	

	public ActionForward ListGroupSales1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
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
        GroupSalesForm af= (GroupSalesForm) form;
        
        int eyear=0;
        
		Iterator itt = lfb.getYlist().iterator();
		if (itt.hasNext())
		{
			lfb=(LoginFormBean)(itt.next());
			eyear=lfb.getYval();
		}
          
		
		GroupSalesDAO  ad= DAOFactory.getGroupSalesDAO();
		List GroupSales =ad.getAllGroupSales(con, tp,eyear);
		
		af.setGpsales(GroupSales);
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward OptBatch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List Batch Master Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession(); 
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        BatchMasterForm af= (BatchMasterForm) form;
		
		BatchDAO  ad=new SQLBatchDAO(); 
		List prd =ad.getAllProduct(con, tp);
		af.setPlist(prd);

		return mapping.findForward("sucess");
	}		
	
	public ActionForward ListBatch(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession(); 
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        BatchMasterForm af= (BatchMasterForm) form;
		int pcode=af.getMan();
		BatchDAO  ad=new SQLBatchDAO(); 
		List bt =ad.getAllbatch(con,tp,pcode);
		con=datasource.getConnection();
		String whead=ad.getProduct(con,tp,pcode);
		af.setBlist(bt);
		req.setAttribute("code",new Integer(pcode));
		req.setAttribute("name",whead);
		
		return mapping.findForward("sucess");
		
	}		
	
	public ActionForward ListAccount1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession(); 
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        
        String tp = lfb.getD_type();
        AccountMasterForm af= (AccountMasterForm) form;

		AccountCentralDAO  ad=new SQLAccountDAO();
		
		List bt =ad.getAllAccount(con, tp);
		af.setBlist(bt);
		
		return mapping.findForward("sucess");
	}			
	
	  public ActionForward CUploadStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	        System.out.println("Calleed List UploadStatus Action class");       
	        
	        DataSource datasource = this.getDataSource(req,"userDB"); 
	  	    Connection con=datasource.getConnection();

	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	        if (lfb==null)

	        {
	        	return mapping.findForward("sfail");
	        }
	        
	        String tp =lfb.getD_type();
	        int lid=lfb.getId();
	        int depo=1;
	        UploadForm af= (UploadForm) form;
	        
			LoginDAO  ad= DAOFactory.getLoginDAO();
	
			List user =null;
 		    user =ad.getAllUpload(con,depo,tp,lid,"S");
			af.setUlist(user);
			
			return mapping.findForward("sucess");
		}	
	
}