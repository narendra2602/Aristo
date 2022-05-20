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
import com.aristo.dao.SQLPmtRepo1DAO;
import com.aristo.form.HORepo1Form;
import com.aristo.valueobject.LoginFormBean;

public class PmtRepoAction extends DispatchAction {

	public ActionForward PmtOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        HORepo1Form af= (HORepo1Form) form;
        af.setYlist(lfb.getYlist());
	    return mapping.findForward("sucess");
}

	public ActionForward PmtListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List PmtListRepo1 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
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
        HORepo1Form af= (HORepo1Form) form;
        List repo=null;
        SQLPmtRepo1DAO rd = new SQLPmtRepo1DAO();
        con=datasource.getConnection();
        int smon = af.getSmon();
        int emon = af.getEmon();
		int saletp = af.getSale_type();
		int uv = af.getUv();
		int eyear=af.getEyear();
             repo =rd.getAllBranch(con,uv,smon,emon,saletp,tp,typ,code1,loginid,eyear);
		     af.setRlist(repo);
		     req.setAttribute("rlist", repo);
		return mapping.findForward("sucess"); 
	}
	
	
	
	
	
	
	
	
	
	
}
