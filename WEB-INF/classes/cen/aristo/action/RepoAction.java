package cen.aristo.action;

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

import com.aristo.valueobject.LoginFormBean;

import cen.aristo.dao.BatchDAO;
import cen.aristo.dao.DispatchDAO;
import cen.aristo.dao.SQLBatchDAO;
import cen.aristo.dao.SQLDispatchDAO;
import cen.aristo.dao.SQLInventoryRepo2DAO;
import cen.aristo.dao.SQLInventoryRepo3DAO;
import cen.aristo.dao.SQLInventoryRepo5DAO;
import cen.aristo.dao.SQLInventoryRepo8DAO;
import cen.aristo.dao.SQLInventoryRepo9DAO;
import cen.aristo.dao.SQLInventoryRepo10DAO;

import cen.aristo.dao.SQLRepo4DAO;
import cen.aristo.dao.SQLRepo5DAO;
import cen.aristo.dao.SQLInventoryRepo1DAO;
import cen.aristo.dao.SQLInventoryRepo6DAO;
import cen.aristo.dao.SQLRepo1DAO;
import cen.aristo.dao.SQLRepo2DAO;
import cen.aristo.dao.SQLRepo3DAO;
import cen.aristo.dao.SQLRepo6DAO;
import cen.aristo.form.CentralRepo1Form;
import cen.aristo.form.CentralRepo2Form;
import cen.aristo.form.CentralRepo3Form;
import cen.aristo.form.CentralRepo4Form;
import cen.aristo.form.CentralRepo5Form;
import cen.aristo.form.CentralRepo6Form;
import cen.aristo.form.InventoryRepo1Form;
import cen.aristo.form.InventoryRepo2Form;
import cen.aristo.form.InventoryRepo5Form;
import cen.aristo.form.InventoryRepo6Form;
import cen.aristo.form.InventoryRepo8Form;
import cen.aristo.form.InventoryRepo9Form;
 
public class RepoAction extends DispatchAction {


	public ActionForward CentralOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    CentralRepo1Form af= (CentralRepo1Form) form;
		DispatchDAO  ad=new SQLDispatchDAO(); 
		List prd =ad.getAllBranch(con, tp);
		af.setBrlist(prd);
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
	    String tp = lfb.getD_type();
   	    CentralRepo1Form rf = (CentralRepo1Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String head = "TRANSFER STATEMENT FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo1DAO repo1 = new SQLRepo1DAO();
		r = repo1.getRepo1(con, tp, sdt,edt,br,chc);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    CentralRepo2Form rf = (CentralRepo2Form) form;
   	    String head = "PRODUCTWISE BATCHWISE STOCK DETAIL ";
   	    SQLRepo2DAO repo2 = new SQLRepo2DAO();
		r = repo2.getRepo2(con, tp);
		rf.setRlist(r);
		req.setAttribute("whead", head);

	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralOptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    CentralRepo3Form af= (CentralRepo3Form) form;
	    
		DispatchDAO  ad=new SQLDispatchDAO(); 
		List prd =ad.getAllBranch(con, tp);
		af.setBrlist(prd);
			
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    CentralRepo3Form rf = (CentralRepo3Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
   	    String head = "BRANCH WISE DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo3DAO repo3 = new SQLRepo3DAO();
		r = repo3.getRepo3(con, tp, sdt,edt,br,chc);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralOptRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    CentralRepo4Form af= (CentralRepo4Form) form;
	    
		DispatchDAO  ad=new SQLDispatchDAO(); 
		List prd =ad.getAllBranch(con, tp);
		af.setBrlist(prd);
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    CentralRepo4Form rf = (CentralRepo4Form) form;
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String head = "LOCATION WISE DISPATCH DETAILS FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLRepo4DAO repo4 = new SQLRepo4DAO();
		r = repo4.getRepo4(con, tp, sdt,edt,br,chc);
		rf.setRlist(r);
		req.setAttribute("whead", head);
		
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward CentralOptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    
	    CentralRepo5Form af= (CentralRepo5Form) form;
	    
		DispatchDAO  ad=new SQLDispatchDAO(); 
		List prd =ad.getAllBranch(con, tp);
		af.setBrlist(prd);
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
  	  try{	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
     	String tp = lfb.getD_type();
     	CentralRepo5Form rf = (CentralRepo5Form) form;
   	    String br=rf.getSbranch();
   	    int chc=rf.getChoice();
  		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
  		Date sdt=dateformat.parse(rf.getSdate());
     	Date edt=dateformat.parse(rf.getEdate());
     	String head = "DISPATCH SUMMERY FROM "+rf.getSdate()+" TO "+rf.getEdate();
     	    
     	SQLRepo5DAO repo2 = new SQLRepo5DAO();
  		r = repo2.getRepo1(con, tp, sdt,edt,br,chc);
  		rf.setRlist(r);
  		req.setAttribute("whead", head);

	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		
		
		return mapping.findForward("sucess");
	}
	

	public ActionForward CentralOptRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

        DataSource datasource = this.getDataSource(req,"userDB"); 
  	    Connection con=datasource.getConnection();
		
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
	    String tp = lfb.getD_type();
	    
	    CentralRepo6Form af= (CentralRepo6Form) form;
	    
		BatchDAO  ad=new SQLBatchDAO(); 
		List prd =ad.getAllProduct(con, tp);
		af.setPlist(prd);

		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralListRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
  	  try{	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
     	String tp = lfb.getD_type();
     	CentralRepo6Form rf = (CentralRepo6Form) form;
     	int pcd=rf.getCode();
   	    int chc=rf.getChoice();
  		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
  		Date sdt=dateformat.parse(rf.getSdate());
     	Date edt=dateformat.parse(rf.getEdate());
     	String head = "PRODUCT WISE DISPATCH  FROM "+rf.getSdate()+" TO "+rf.getEdate();
     	    
     	SQLRepo6DAO repo6 = new SQLRepo6DAO();
  		r = repo6.getRepo6(con, tp, sdt,edt,chc,pcd);
  		rf.setRlist(r);
  		req.setAttribute("whead", head);

	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		
		
		return mapping.findForward("sucess");
	}

	
	
	public ActionForward CentralOptInv1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo1Form rf = (InventoryRepo1Form) form;
   	    
   	    String opdate=rf.getSdate();
   	    opdate= "01/"+opdate.substring(3);

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    Date odt= dateformat.parse(opdate);
   	    
   	    String head = "PRODUCT STOCK CARD SUMMERY FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo1DAO repo2 = new SQLInventoryRepo1DAO();
		r = repo2.getRepo1(con, tp, sdt,edt,odt);
		rf.setRlist(r);
		
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralOptInv2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo2Form rf = (InventoryRepo2Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());

   	    String head = "NEAR EXPIRY/EXPIRED STOCK LIST AS ON "+rf.getSdate();
   	    
   	    SQLInventoryRepo2DAO repo2 = new SQLInventoryRepo2DAO();
		r = repo2.getRepo2(con, tp, sdt);
		rf.setRlist(r);
		
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward CentralOptInv3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo2Form rf = (InventoryRepo2Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());

   	    String head = "NON MOVING ITEMS BATCHWISE STOCK AS ON "+rf.getSdate();
   	    
   	    SQLInventoryRepo3DAO repo3 = new SQLInventoryRepo3DAO();
		r = repo3.getRepo3(con, tp, sdt);
		rf.setRlist(r);
		
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	public ActionForward CentralOptInv5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	  try{	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
        
        String tp = lfb.getD_type();
        InventoryRepo5Form rf = (InventoryRepo5Form) form;
		
		BatchDAO  ad=new SQLBatchDAO(); 
		List prd =ad.getAllProduct(con, tp);
		rf.setPlist(prd);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo5Form rf = (InventoryRepo5Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    int ch=rf.getChoice();
   	    int pcd=rf.getCode();
   	    String head = "STOCK LEDGER FROM " +rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo5DAO repo5 = new SQLInventoryRepo5DAO();
		r = repo5.getRepo5(con, tp, sdt,edt,ch,pcd);
		rf.setRlist(r);
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	
	
	public ActionForward CentralOptInv6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo6Form rf = (InventoryRepo6Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    int doc_type=rf.getDoc_type();
   	    String name=null;
   	    if(doc_type==60)
   	    	name=" DAMAN"; 
   	    if(doc_type==61)
	    	name=" OTHER";
   	    if(doc_type==62)
	    	name=" BHOPAL ";
   	
   	    String head = "ITEM WISE RECEIPT FROM " +name+ " "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo6DAO repo6 = new SQLInventoryRepo6DAO();
		r = repo6.getRepo6(con, tp, sdt,edt,doc_type);
		rf.setRlist(r);
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
		
	public ActionForward CentralOptInv8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo8Form rf = (InventoryRepo8Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String head = "INWARD STATEMENT FROM " +rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo8DAO repo8 = new SQLInventoryRepo8DAO();
		r = repo8.getRepo8(con, tp, sdt,edt);
		rf.setRlist(r);
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralOptInv9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward CentralInventoryRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo9Form rf = (InventoryRepo9Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    int doc_type=rf.getDoc_type();
   	    String name=null;
   	    if(doc_type==60)
   	    	name=" DAMAN"; 
   	    if(doc_type==61)
	    	name=" OTHER";
   	    if(doc_type==62)
	    	name=" BHOPAL ";
   	
   	    String head = "INWARD STATEMENT FROM " +name+ " "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo9DAO repo9 = new SQLInventoryRepo9DAO();
		r = repo9.getRepo9(con, tp, sdt,edt,doc_type);
		rf.setRlist(r);
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	

    public ActionForward CentralOptInv10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}

	public ActionForward CentralInventoryRepo10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }

	  try{	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    String tp = lfb.getD_type();
   	    InventoryRepo9Form rf = (InventoryRepo9Form) form;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    String head = " DATE WISE STOCK VALUE FROM "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLInventoryRepo10DAO repo10 = new SQLInventoryRepo10DAO();
		r = repo10.getRepo10(con, tp, sdt,edt);
		rf.setRlist(r);
		
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	
	
}
