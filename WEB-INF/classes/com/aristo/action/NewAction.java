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

import com.aristo.dao.SQLForm11DAO;
import com.aristo.dao.SQLForm4DAO;
import com.aristo.dao.SQLForm5DAO;
import com.aristo.dao.SQLHOForm3DAO;
import com.aristo.dao.SQLHORepo1DAO;
import com.aristo.dao.SQLHORepo5DAO;
import com.aristo.dao.SQLOptDAO;
import com.aristo.dao.SQLRepo3DAO;
import com.aristo.form.HOForm3Form;
import com.aristo.form.HORepo1Form;
import com.aristo.form.HORepo5Form;
import com.aristo.form.MktForm;
import com.aristo.form.Repo2Form;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.MktFormBean;

public class NewAction extends DispatchAction {

	
	public ActionForward NWOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    
		DataSource datasource = null;
		Connection con= null;

		HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();

     	int uid = lfb.getId();
        int utype = Integer.parseInt(lfb.getOpt());

        
        HORepo1Form af= (HORepo1Form) form;
        
     	SQLOptDAO  ad=new SQLOptDAO(); 

 		List branch = ad.getUserBranch(con, uid);
 		if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
 		{
 			LoginFormBean lf = new LoginFormBean();
 			lf.setDcode(0);
 			lf.setDname("ALL [Br-wise]"); 
 			branch.add(0, lf);
 		}

 		
 		af.setBranchlist(branch);//set branch list in DivList so dont confuse...
        af.setYlist(lfb.getYlist());
	    return mapping.findForward("sucess");
	
}
/////////////////////////////////////HO Option 1 End////////////////////////////////////////////////////	

/////////////////////////////////////HO List 1 Start////////////////////////////////////////////////////    	

public ActionForward NWListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
    System.out.println("Calleed List NewListRepo1 Action class");       

    DataSource datasource = this.getDataSource(req,"newDB"); 
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
    int div_code = lfb.getDiv_code();
    int utype = Integer.parseInt(lfb.getOpt());
    int code = lfb.getCode();
    System.out.println("depo code is "+code);
    
    HORepo1Form af= (HORepo1Form) form;
    
    List repo=null;
    SQLHORepo1DAO rd = new SQLHORepo1DAO();
    con=datasource.getConnection();
    int smon = af.getSmon();
    int emon = af.getEmon();
	int saletp = af.getSale_type();
	int uv = af.getUv();
	int eyear=af.getEyear();
    int rep_type=af.getRep_type();

	String sr=af.getSearch();	
	code = af.getDepo_code();

	if (sr==null)
	{
        session.setAttribute("smon",af.getSmon());
        session.setAttribute("emon",af.getEmon());
        session.setAttribute("saletp",af.getSale_type());
        session.setAttribute("uv", af.getUv());
        session.setAttribute("eyear",af.getEyear());
	}
	else
	{
		uv=(Integer) session.getAttribute("uv");
		smon=(Integer) session.getAttribute("smon");
		emon=(Integer) session.getAttribute("emon");
		saletp=(Integer) session.getAttribute("saletp");
		eyear=(Integer) session.getAttribute("eyear");			
	}		

//     repo =rd.getAllBranchOld(con,uv,smon,emon,saletp,tp,typ,code1,loginid,eyear,sr);
     repo =rd.getAllBranch(con,uv,smon,emon,saletp,div_code,utype,code,loginid,eyear,sr,rep_type);
     af.setRlist(repo);
     req.setAttribute("rlist", repo);

	return mapping.findForward("sucess"); 
	
}

/////////////////////////////////////HO List 1 End////////////////////////////////////////////////////	


/////////////////////////////// Option Form No. 4 start here//////////////////////////////////////////////	
public ActionForward NWOptForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWoptForm4 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

 	con=datasource.getConnection();


 	int uid = lfb.getId();
    int utype = Integer.parseInt(lfb.getOpt());
    String tp = lfb.getD_type();
	int depo_code=0;
	MktForm af= (MktForm) form;
	

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);
	LoginFormBean lf1 = (LoginFormBean) branch.get(0);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}

	if (req.getParameter("search")==null)
		depo_code=lf1.getDcode();
	else
		depo_code = Integer.parseInt(req.getParameter("search"));
	
	if(depo_code==0)
		depo_code=lf1.getDcode();
	
	con=datasource.getConnection();
	List hq = null;
	if(utype==4)
		hq = ad.getHQRepoNew(con, depo_code, tp,uid);
	else
		hq = ad.getAllrepo(con, depo_code, tp);
	
	af.setAlist(hq);    // set hq List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...
	af.setYlist(lfb.getYlist());

	req.setAttribute("rlist", hq);
	if (req.getParameter("search")==null)
		return mapping.findForward("sucess");
	else
		return mapping.findForward("find");
	 

}

/////////////////////////////// End of Option Form No. 4 /////////////////////////////////////////////////		

///////////////////////////////  Form No. 4 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm4 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int selectiontp = af.getSale_type(); // Branch/HQ
	int uv = af.getUv();
	int eyear=af.getEyear();
	int depo_code = af.getCode();
	int hq_code = 0;

	if(selectiontp==2)
		hq_code = af.getNo_of_mr();

	List repo=null;
	SQLForm4DAO rd = new SQLForm4DAO();       


	int i=1;
	con=datasource.getConnection();
	repo =rd.getNewBranch(con,uv,hq_code,smon,emon,eyear,depo_code,div_code,uid,utype);

	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 4 Action End here////////////////////////////////////////////////	



/////////////////////////////// Option Form No. 5 start here//////////////////////////////////////////////	
public ActionForward NWOptForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWoptForm4 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	int depo_code=0;
	MktForm af= (MktForm) form;


	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);
	LoginFormBean lf1 = (LoginFormBean) branch.get(0);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}

	if (req.getParameter("search")==null)
		depo_code=lf1.getDcode();
	else
		depo_code = Integer.parseInt(req.getParameter("search"));

	if(depo_code==0)
		depo_code=lf1.getDcode();

	con=datasource.getConnection();
	List hq = null;
	if(utype==4)
		hq = ad.getHQRepoNew(con, depo_code, tp,uid);
	else
		hq = ad.getAllrepo(con, depo_code, tp);

	af.setAlist(hq);    // set hq List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...
	af.setYlist(lfb.getYlist());

	req.setAttribute("rlist", hq);
	if (req.getParameter("search")==null)
		return mapping.findForward("sucess");
	else
		return mapping.findForward("find");


}

/////////////////////////////// End of Option Form No. 5 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 5 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm5 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int selectiontp = af.getSale_type(); // Branch/HQ
	int uv = af.getUv();
	int eyear=af.getEyear();
	int depo_code = af.getCode();
	int hq_code = 0;
	int rep_type = af.getRep_type();
	int pg = af.getPg();

	if(selectiontp==2)
		hq_code = af.getNo_of_mr();

	List repo=null;
	SQLForm5DAO rd = new SQLForm5DAO();       


	int i=1;
	con=datasource.getConnection();
	repo =rd.getNewBranch(con,uv,hq_code,smon,emon,eyear,depo_code,div_code,uid,utype,rep_type,pg);
	

	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 5 Action End here////////////////////////////////////////////////	



/////////////////////////////// Option Form No. 11 start here//////////////////////////////////////////////	
public ActionForward NWOptForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWoptForm11 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	MktForm af= (MktForm) form;

	af.setYlist(lfb.getYlist());

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}


    int eyear=0;
	Iterator itt = lfb.getYlist().iterator();
	if (itt.hasNext())
	{
		lfb=(LoginFormBean)(itt.next());
		eyear=lfb.getYval();
	}
 		

	
	con=datasource.getConnection();
	List group = null;
	group = ad.getAllFormGroup(con, tp, eyear, uid, (utype==3?"PMT":""));

	af.setAlist(group);    // set group List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...


	return mapping.findForward("sucess");


}

/////////////////////////////// End of Option Form No. 11 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 11 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm11 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int selectiontp = af.getSale_type(); // Groupwise/All
	int eyear=af.getEyear();
	int depo_code = af.getCode();
	int grp_code = 0;

	if(selectiontp==1)
		grp_code = af.getNo_of_mr();

	System.out.println("grp_code "+grp_code);
	List repo=null;


	con=datasource.getConnection();
	SQLForm11DAO rd = new SQLForm11DAO();       
	repo =rd.getNewBranch(con,grp_code,smon,emon,eyear,depo_code,div_code,uid,utype);



	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 11 Action End here////////////////////////////////////////////////	


/////////////////////////////// Option Form No. 3 start here//////////////////////////////////////////////	
public ActionForward NWOptForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWoptForm3 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	HOForm3Form af= (HOForm3Form) form;

	af.setYlist(lfb.getYlist());

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}


	int eyear=0;
	Iterator itt = lfb.getYlist().iterator();
	if (itt.hasNext())
	{
		lfb=(LoginFormBean)(itt.next());
		eyear=lfb.getYval();
	}



	con=datasource.getConnection();
	List group = null;
	group = ad.getAllFormGroup(con, tp, eyear, uid, (utype==3?"PMT":""));

	af.setAlist(group);    // set group List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...


	return mapping.findForward("sucess");


}

/////////////////////////////// End of Option Form No. 11 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 3 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm3 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	HOForm3Form af= (HOForm3Form) form;

	int smon =af.getSmon();
	int emon =af.getEmon();
	int div_code = lfb.getDiv_code();
	int selectiontp = af.getPg(); // Groupwise/All
	int top25 = af.getR_type();    // Branch/ ALL India Top25 HQ
	int rank = af.getSale_type(); // PMT/ACH Rank
	int eyear=af.getEyear();
	int depo_code = af.getDepo_code();
	int grp_code = 0;

	if(depo_code>0)
		utype=1;
	if(selectiontp==1)
		grp_code = af.getGrp_code();

	System.out.println("grp_code "+grp_code);
	List repo=null;
	SQLHOForm3DAO rd = new SQLHOForm3DAO();       

	if(top25==2)
		depo_code=-1;
	
	con=datasource.getConnection();
	repo =rd.getNewBranch(con,grp_code,smon,emon,eyear,depo_code,div_code,uid,utype,rank);


	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 3 Action End here////////////////////////////////////////////////	



/////////////////////////////// Option Form No. 11 start here//////////////////////////////////////////////	
public ActionForward NWOptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWOptRepo5 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	HORepo5Form af= (HORepo5Form) form;

	af.setYlist(lfb.getYlist());

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}


	int eyear=0;
	Iterator itt = lfb.getYlist().iterator();
	if (itt.hasNext())
	{
		lfb=(LoginFormBean)(itt.next());
		eyear=lfb.getYval();
	}



	con=datasource.getConnection();
	List group = null;
	group = ad.getAllFormGroup(con, tp, eyear, uid, (utype==3?"PMT":""));

	af.setAlist(group);    // set group List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...


	return mapping.findForward("sucess");


}

/////////////////////////////// End of Option Form No. 11 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 11 Action Start here////////////////////////////////////////////////	

public ActionForward NWListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListRepo5 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	HORepo5Form af= (HORepo5Form) form;

	int smon =af.getSmon();
	int emon =af.getEmon();
	int div_code = lfb.getDiv_code();
	int selectiontp = af.getR_type(); // Groupwise/All
	int eyear=af.getEyear();
	int depo_code = af.getDepo_code();
	int grp_code = 0;

	if(selectiontp==1)
		grp_code = af.getGrp_code();

	System.out.println("grp_code "+grp_code);
	List repo=null;


	con=datasource.getConnection();
	SQLHORepo5DAO rd = new SQLHORepo5DAO();       
	repo =rd.getNewBranch(con,grp_code,smon,emon,eyear,depo_code,div_code,uid,utype);



	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 11 Action End here////////////////////////////////////////////////	
/////////////////////////////// Option Form No. 7 start here//////////////////////////////////////////////	
public ActionForward NWOptForm7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWOptForm7 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	MktForm af= (MktForm) form;

	af.setYlist(lfb.getYlist());

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}


	int eyear=0;
	Iterator itt = lfb.getYlist().iterator();
	if (itt.hasNext())
	{
		lfb=(LoginFormBean)(itt.next());
		eyear=lfb.getYval();
	}



	con=datasource.getConnection();
	List group = null;
	group = ad.getAllFormGroup(con, tp, eyear, uid, (utype==3?"PMT":""));
	if(group!=null && group.size()>1)
	{
		MktFormBean mfb = new MktFormBean();
		mfb.setQty2(0);
		mfb.setNm2("All Group");
		group.add(0, mfb);

	}

	
	
	af.setAlist(group);    // set group List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...


	return mapping.findForward("sucess");


}

/////////////////////////////// End of Option Report No. 7 /////////////////////////////////////////////////		
///////////////////////////////  Form No. 7 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListRepo7 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int eyear=af.getEyear();
	int depo_code = af.getCode();
	int grp_code = af.getNo_of_mr();
	int rep_type = af.getRep_type();

	System.out.println("grp_code "+grp_code);
	List repo=null;


	con=datasource.getConnection();
	SQLForm5DAO rd = new SQLForm5DAO();
	repo =rd.getNewBranch7(con,grp_code,smon,emon,eyear,depo_code,div_code,uid,utype,rep_type);



	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Repor no 7 End here////////////////////////////////////////////////	


/////////////////////////////// Option Form No. 8 start here//////////////////////////////////////////////	
public ActionForward NWOptForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWOptForm8 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	int depo_code=0;
	Repo2Form af= (Repo2Form) form;


	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}



	af.setBlist(branch);//set branch list in bList so dont confuse...
	af.setYlist(lfb.getYlist());


	return mapping.findForward("sucess");

}


////////////////////////////// End of Option Form No. 8 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 8 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm8(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm8 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	Repo2Form af= (Repo2Form) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int eyear=af.getEyear();
	int depo_code = af.getCode();


	List repo=null;
	SQLRepo3DAO rd = new SQLRepo3DAO();
	       


	int i=1;
	con=datasource.getConnection();
	repo =rd.getNewBranch(con,smon,emon,eyear,depo_code,div_code,uid,utype,0);


	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 8 Action End here////////////////////////////////////////////////	




/////////////////////////////// Option Form No. 9 start here//////////////////////////////////////////////	
public ActionForward NWOptForm9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWOptForm9 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	int depo_code=0;
	Repo2Form af= (Repo2Form) form;


	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}



	af.setBlist(branch);//set branch list in bList so dont confuse...
	af.setYlist(lfb.getYlist());


	return mapping.findForward("sucess");

}


//////////////////////////////End of Option Form No. 9 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 9 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm9 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	Repo2Form af= (Repo2Form) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int eyear=af.getEyear();
	int depo_code = af.getCode();


	List repo=null;
	SQLRepo3DAO rd = new SQLRepo3DAO();



	con=datasource.getConnection();
	repo =rd.getNewBranch(con,smon,emon,eyear,depo_code,div_code,uid,utype,1);


	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 9 Action End here////////////////////////////////////////////////	


/////////////////////////////// Option Form No. 10 start here//////////////////////////////////////////////	
public ActionForward NWOptForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NWoptForm10 Action class");       
	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	String tp = lfb.getD_type();
	int depo_code=0;

	MktForm af= (MktForm) form;

	af.setYlist(lfb.getYlist());

	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);
	LoginFormBean lf1 = (LoginFormBean) branch.get(0);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}

	if (req.getParameter("search")==null)
		depo_code=lf1.getDcode();
	else
		depo_code = Integer.parseInt(req.getParameter("search"));

	if(depo_code==0)
		depo_code=lf1.getDcode();

	

	int eyear=0;
	Iterator itt = lfb.getYlist().iterator();
	if (itt.hasNext())
	{
		lfb=(LoginFormBean)(itt.next());
		eyear=lfb.getYval();
	}



	con=datasource.getConnection();
	List group = null;
	group = ad.getAllFormGroup(con, tp, eyear, uid, (utype==3?"PMT":""));
	if(group!=null && group.size()>1)
	{
		MktFormBean mfb = new MktFormBean();
		mfb.setQty2(0);
		mfb.setNm2("All Group");
		group.add(0, mfb);

	}

	
	con=datasource.getConnection();
	List hq = null;
	if(utype==4)
		hq = ad.getHQRepoNew(con, depo_code, tp,uid);
	else
		hq = ad.getAllrepo(con, depo_code, tp);

	af.setAlist(hq);    // set hq List in alist 
	af.setGlist(group);    // set group List in alist 
	af.setBlist(branch);//set branch list in bList so dont confuse...

	
	
	req.setAttribute("rlist", hq);
	if (req.getParameter("search")==null)
		return mapping.findForward("sucess");
	else
		return mapping.findForward("find");






}

/////////////////////////////// End of Option Form No. 10 /////////////////////////////////////////////////		

///////////////////////////////  Form No. 10 Action Start here////////////////////////////////////////////////	

public ActionForward NWListForm10(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NWListForm10 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int eyear=af.getEyear();
	int depo_code = af.getCode();
	int grp_code = af.getGp_code();
	int rep_type = af.getRep_type();
	int hq_code = 0;
	
	if(rep_type==2)
		hq_code = af.getNo_of_mr();


	List repo=null;
	SQLForm5DAO rd = new SQLForm5DAO();       


	con=datasource.getConnection();
	repo =rd.getNewBranch10(con,grp_code,smon,emon,eyear,depo_code,div_code,uid,utype,hq_code);

	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 10 Action End here////////////////////////////////////////////////	


/////////////////////////////// Option Form No. 11 start here//////////////////////////////////////////////	
public ActionForward NEWOptForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("Calleed List NEWOptForm11 Action class");       

	DataSource datasource = this.getDataSource(req,"userDB"); 
	Connection con=null;

	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)

	{
		return mapping.findForward("sfail");
	}

	con=datasource.getConnection();


	int uid = lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());
	MktForm af= (MktForm) form;


	SQLOptDAO  ad=new SQLOptDAO(); 

	List branch = ad.getUserBranch(con, uid);

	if(branch!=null && utype!=4 && (branch.size()>1 || utype==5))
	{
		LoginFormBean lf = new LoginFormBean();
		lf.setDcode(0);
		lf.setDname("ALL"); 
		branch.add(0, lf);
	}



	af.setBlist(branch);//set branch list in bList so dont confuse...
	af.setYlist(lfb.getYlist());


	return mapping.findForward("sucess");

}


//////////////////////////////End of Option Form No. 11 /////////////////////////////////////////////////		


///////////////////////////////  Form No. 11 Action Start here////////////////////////////////////////////////	

public ActionForward NEWListForm11(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	System.out.println("Calleed List NEWListForm11 Action class");       

	DataSource datasource = this.getDataSource(req,"newDB"); 
	Connection con=null;
	HttpSession session=req.getSession();
	LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

	if (lfb==null)
	{
		return mapping.findForward("sfail");
	}

	int uid=lfb.getId();
	int utype = Integer.parseInt(lfb.getOpt());

	MktForm af= (MktForm) form;

	int smon =af.getMnth();
	int emon =af.getMnth1();
	int div_code = lfb.getDiv_code();
	int eyear=af.getEyear();
	int depo_code = af.getCode();



	List repo=null;
	SQLForm5DAO rd = new SQLForm5DAO();       


	con=datasource.getConnection();
	repo =rd.getNewBranch11(con,smon,emon,eyear,depo_code,div_code,uid,utype);

	af.setRlist(repo);
	req.setAttribute("rlist", repo);


	return mapping.findForward("sucess");      

}	
///////////////////////////////  Form No. 11 Action End here////////////////////////////////////////////////	



}
