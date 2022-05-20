package com.aristo.action;

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
import com.aristo.dao.SQLOptDAO;
import com.aristo.form.Repo2Form;
import com.aristo.valueobject.LoginFormBean;


public class HOOptAction extends DispatchAction {
	
	public ActionForward OptUserType(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        System.out.println("Calleed List OptUserType Action class");       

        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	System.out.println("Session Expired");
        	return mapping.findForward("sfail");
        }
         
        Repo2Form af= (Repo2Form) form;
        
        List repo=null;
		SQLOptDAO rd = new SQLOptDAO();

        int opt = af.getOpt();
        
         System.out.println("option value in secioddd screen "+opt);
		switch(opt)
         {
        	 case 1:
        			repo =rd.getAlldepo(con); 
        			break; 
        	 case 2:
//        		 	repo =rd.getAllRegion(con,depo_code,tp); 
        			break; 
        	 case 3:
//     		 		repo =rd.getAllArea(con,depo_code,tp);  
     		 		break; 
        	 case 4: 
//        		 	repo =rd.getAllBranch(con,depo_code,tp);  
			        break;
         }

		af.setXlist(repo);
		req.setAttribute("rlist",repo);
		
		System.out.println("List me data set ho gya");  
		return mapping.findForward("sucess"); 
		
	}

	  public ActionForward addUsertypeForward(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
		{
		  
	       System.out.println("Calleed List HOOptAction class");       

	       return mapping.findForward("sucess");
		}
	
	
}
