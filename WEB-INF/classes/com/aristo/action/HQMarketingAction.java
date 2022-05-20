package com.aristo.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.aristo.hq.SQLForm4DAO;
import com.aristo.hq.SQLForm5DAO;
import com.aristo.hq.SQLForm6DAO;
import com.aristo.hq.SQLForm8DAO;
import com.aristo.hq.SQLForm10DAO;
import com.aristo.hq.SQLForm11DAO;
import com.aristo.hq.SQLForm12DAO;
import com.aristo.hq.SQLForm13DAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.form.MktForm;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.MktFormBean;

public class HQMarketingAction extends DispatchAction {

	public ActionForward HQOptForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-Form1 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();        
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();		
		
		repo =rd.getHQ(con,depo_code,tp,uid); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);

		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQListForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-List Form4 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;

        af.getRlist().clear();
        if(af.getSl()==1)
        {
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
        }

        int cd=0; 
        List repo=null;
		SQLForm4DAO rd = new SQLForm4DAO();

		int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int uv = af.getUv();
        int reptype=af.getRep_type();
    	Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
    	int i=1;
    	String sucess="sucess";
        if(af.getSl()==1)  // selective hq
        {
        	List code = af.getRlist();
        	Iterator it = code.iterator();

        	while (it.hasNext())
        	{
        		cd = (Integer)it.next();
        		con=datasource.getConnection();
        		//repo =rd.getAllHQ(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		repo =rd.getAllHQNew1(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
        		
        		System.out.println(cd+ "smon "+smon+ " emon"+emon+ " eyear"+eyear+" depo"+depo_code+" tp"+tp+" uid"+uid+" utype "+utype);
        		m.put(i, repo);
        		i++;
        	}  
        }
        else 
        {
    		con=datasource.getConnection();
    		if(uv==3)
    		{
    			switch (reptype)
    			{
	    			case 1 :
	    				repo =rd.getSalesReview(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
	    				sucess="salesreview";
	    				break;
	    			case 2 :
	    				repo =rd.getSalesReviewBP1(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
	    				sucess="salesreviewbp1";
	    				break;
	    			case 3 :
	    				repo =rd.getSalesReviewBP2(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
	    				sucess="salesreviewbp2";
	    				break;
    			}
    			
    		}
    		else 
    			repo =rd.getAllHQNew1(con,uv,cd,smon,emon,eyear,depo_code,tp,uid,utype);
    		m.put(i, repo);

        }
        
        
		af.setRlist(repo);
        req.setAttribute("rlist", m);
   			return mapping.findForward(sucess);      

	}	

	public ActionForward HQOptForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-Form5 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();        
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
		repo =rd.getHQ(con,depo_code,tp,uid);
 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
	}
	
	public ActionForward HQListForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-ListForm5 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist()); 
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        List repo=null;
		SQLForm5DAO rd = new SQLForm5DAO();
        int uv = af.getUv();
        int tl = af.getPg();
        int eyear= af.getEyear();
        int i=1;

        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 	    repo =rd.getAllHQ(con,uv,cd,tl,eyear,depo_code,tp,uid,utype);
 		m.put(i, repo);
 		i++;
        }
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}		

	public ActionForward HQOptForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-Form6 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();        
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
		repo =rd.getHQ(con,depo_code,tp,uid); 

        af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
	}
	public ActionForward HQListForm6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-ListForm6 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist()); 
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        List repo=null;
		SQLForm6DAO rd = new SQLForm6DAO();
        int uv = af.getUv();
        int i=1;
        int eyear=af.getEyear();
		
        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 	    repo =rd.getAllHQ(con,uv,cd,depo_code,eyear,tp,uid,utype);
 		m.put(i, repo);
 		i++;
        }
		  
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}	
	
	public ActionForward HQOptForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-OPTForm8 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        af.setChosenItem(null);
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getFormProduct(con, tp,year,uid,utype); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQListForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-ListForm8 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();        
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        MktForm af= (MktForm) form;
        af.getRlist().clear();
        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist()); 
        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        List repo=null;
		SQLForm8DAO rd = new SQLForm8DAO();
		int uv = af.getUv();
        int st = af.getPg();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int i=1;
        while (it.hasNext())
        {
          cd = (Integer)it.next();
          con=datasource.getConnection();
    	  repo =rd.getAllHQ(con,cd,uv,emon,st,eyear,depo_code,tp,uid);
 		  m.put(i, repo);
 		  i++;
        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}	
	
	public ActionForward HQOptForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-OptForm10 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid = lfb.getId();
        int depo_code=lfb.getCode(); 
        String tp = lfb.getD_type();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
		repo =rd.getHQ(con,depo_code,tp,uid); 
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	
	}
	
	public ActionForward HQListForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed HQ-ListForm10 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
       	return mapping.findForward("sfail");
        }
        
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
        af.getRlist().clear();

        if (af.getChosenItem().length()==0)
        	return mapping.findForward("fail");
        else
        	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());

        
        Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
        List code = af.getRlist();
        Iterator it = code.iterator();
        int cd=0; 
        List repo=null;
		SQLForm10DAO rd = new SQLForm10DAO();
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
		int uv = af.getUv();
        int sale = af.getSale_type();
        int i=1;
        while (it.hasNext())
        {
        cd = (Integer)it.next();
        con=datasource.getConnection();
 		repo =rd.getAllHQ(con,uv,sale,cd,smon,emon,eyear,depo_code,tp,uid,utype);
 		m.put(i, repo);
 		i++;
        }
		af.setRlist(repo);
        req.setAttribute("rlist", m);
		return mapping.findForward("sucess");      
		
	}		
	
	public ActionForward HQOptForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Calleed List HQ-OptForm11 Action class");       
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQListForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("Calleed List HQ-ListForm11 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        int uid=lfb.getId();
        int depo_code=lfb.getCode();   
        String tp = lfb.getD_type();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
        List repo=null;
        SQLForm11DAO rd = new SQLForm11DAO();
        con=datasource.getConnection();        
        int smon =af.getMnth();
        int emon =af.getMnth1();
        int eyear=af.getEyear();
        int option=af.getRep_type();
        int pg=af.getPg();
        String retu="sucess";
        switch (option)
        {
        case 1:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="sales";
        	break;
        case 2:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="target";
        	break;
        case 3:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="lastyear";
        	break;
        case 4:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="achievement";
        	break;
        case 5:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="growth";
        	break;
        case 6:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="pmr";
        	break;
        case 7:
        	repo =rd.getAllHQBrand(con,option,smon,emon,eyear,depo_code,tp,uid,utype,pg);
        	retu="surplus";
        	break;
        case 8:
        	repo =rd.getAllHQ(con,smon,emon,eyear,depo_code,tp,uid);
        	break;

        }
        
		af.setRlist(repo);
        req.setAttribute("rlist", repo);
//        return mapping.findForward("sucess");      
        return mapping.findForward(retu);      
	}		

	public ActionForward HQOptForm12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		    System.out.println("Calleed List HQ-OptForm12 Action class");       
	        DataSource datasource = this.getDataSource(req,"userDB"); 
		    Connection con=null;
	        HttpSession session=req.getSession();
	        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
	        if (lfb==null)
	        {
	        	return mapping.findForward("sfail");
	        }
	        String tp = lfb.getD_type();
	        int uid=lfb.getId();
	        String utype = lfb.getType();
	        MktForm af= (MktForm) form;
			af.setYlist(lfb.getYlist());
	        int eyear=0;
			Iterator itt = lfb.getYlist().iterator();
			if (itt.hasNext())
			{
				lfb=(LoginFormBean)(itt.next());
				eyear=lfb.getYval();
			}
	        List repo=null;
	        List repo1= new ArrayList();
			SQLOptDAO rd = new SQLOptDAO();
			con=datasource.getConnection();
	    	repo =rd.getAllFormGroup(con,tp,eyear,uid,utype); 
			af.setXlist(repo);
			af.setRlist(repo1);
			af.setAlist(repo);

			req.setAttribute("rlist",repo);
			String whead=null;
			MktFormBean r = null;
			List l = (List) repo;
			Iterator it = l.iterator();
			if (it.hasNext())
			{
			    r = (MktFormBean) (it.next());
			    whead = r.getNm3();
			}
			req.setAttribute("data", whead);
			return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQListForm12(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
       System.out.println("Calleed List HQ-Form12 Action class");       
       DataSource datasource = this.getDataSource(req,"userDB"); 
	   Connection con=null;
       HttpSession session=req.getSession();
       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
       if (lfb==null)
       {	
       	return mapping.findForward("sfail");
       }
       int depo_code=lfb.getCode();   
       String tp = lfb.getD_type();
       int uid=lfb.getId();
       String utype = lfb.getType();
       MktForm af= (MktForm) form;
       af.getRlist().clear();
       
       if (af.getChosenItem().length()==0)
       	return mapping.findForward("fail");
       else
       	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
       
       Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
       List code = af.getRlist();
       Iterator it = code.iterator();
       int cd=0; 
       List repo=null;
       SQLForm12DAO rd = new SQLForm12DAO();
       
       int eyear=af.getEyear();
       int smon =af.getMnth();
       int emon =af.getMnth1();
       int i=1;
       while (it.hasNext())
       {
          cd = (Integer)it.next();
          con=datasource.getConnection();
   		  repo =rd.getAllHQ(con,cd,smon,emon,eyear,depo_code,tp,uid,utype);
    	  m.put(i, repo);
    	  i++;
       }
	   af.setRlist(repo);
       req.setAttribute("rlist", m);
	   return mapping.findForward("sucess");      
	}
			
	public ActionForward HQOptForm13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("Calleed List HQ-OptForm13 Action class");       
        DataSource datasource = this.getDataSource(req,"userDB"); 
	    Connection con=null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
        String tp = lfb.getD_type();
        int uid=lfb.getId();
        String utype = lfb.getType();
        MktForm af= (MktForm) form;
		af.setYlist(lfb.getYlist());
		Iterator itt = lfb.getYlist().iterator();
		int year=0;
		if (itt.hasNext())
		{
			lfb = (LoginFormBean) itt.next();
			year = lfb.getYval();
		}
        List repo=null;
        List repo1= new ArrayList();
		SQLOptDAO rd = new SQLOptDAO();
		con=datasource.getConnection();
    	repo =rd.getFormProduct(con, tp,year,uid,utype);
		af.setXlist(repo);
		af.setRlist(repo1);
		af.setAlist(repo);
		req.setAttribute("rlist",repo);
		
		String whead=null;
		MktFormBean r = null;
		List l = (List) repo;
		Iterator it = l.iterator();
		if (it.hasNext())
		{
		    r = (MktFormBean) (it.next());
		    whead = r.getNm3();
		}
		
		req.setAttribute("data", whead);
		return mapping.findForward("sucess"); 
	}
	
	public ActionForward HQListForm13(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
       System.out.println("Calleed List HQ-Form13 Action class");       
       DataSource datasource = this.getDataSource(req,"userDB"); 
	   Connection con=null;
       HttpSession session=req.getSession();
       LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
       if (lfb==null)
       {	
       	return mapping.findForward("sfail");
       }
       int uid=lfb.getId();
       int depo_code=lfb.getCode();   
       String tp = lfb.getD_type();
       MktForm af= (MktForm) form;
       af.getRlist().clear();
       if (af.getChosenItem().length()==0)
       	return mapping.findForward("fail");
       else
       	af.move( af.getAlist(),af.getChosenItem().split( "\\|" ),af.getRlist());
       
       Map<Integer, List<?>> m = new HashMap<Integer, List<?>>();
       List code = af.getRlist();
       Iterator it = code.iterator();
       int cd=0;
       List repo=null;
       SQLForm13DAO rd = new SQLForm13DAO();
       int uv=af.getUv();
       int smon =af.getMnth();
       int emon =af.getMnth1();
       int eyear=af.getEyear();
       int i=1;
   
       while (it.hasNext())
       {
          cd = (Integer)it.next();
          con=datasource.getConnection();
   		  repo =rd.getAllHQ(con,uv,cd,smon,emon,eyear,depo_code,tp,uid);
    	  m.put(i, repo);
    	  i++;
       }
		  
	   af.setRlist(repo);
       req.setAttribute("rlist", m);
	   return mapping.findForward("sucess");      
	}	
	
	
	
}