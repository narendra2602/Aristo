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

import com.aristo.form.AreaForm;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.dao.AreaDAO;
import com.aristo.dao.DAOFactory;
public class AreaAction extends DispatchAction {

	  
	
	public ActionForward ListArea(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List ARea Action class");       
        
        DataSource datasource = this.getDataSource(req,"userDB"); 
  	   Connection con=datasource.getConnection();

        HttpSession session=req.getSession();
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
        AreaForm af= (AreaForm) form;
		
		AreaDAO  ad= DAOFactory.getArea();

		List area =ad.getAllarea(con,depo_code,tp,eyear);
		
		af.setAlist(area);
		
		return mapping.findForward("sucess");
	}
}
