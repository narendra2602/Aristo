package com.aristo.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.aristo.dao.SQLOptDAO;
import com.aristo.dao.SQLSampleRepo1DAO;
import com.aristo.dao.SQLSampleRepo2DAO;
import com.aristo.dao.SQLSampleRepo3DAO;
import com.aristo.dao.SQLSampleRepo4DAO;
import com.aristo.dao.SQLSampleRepo6DAO;
import com.aristo.dao.SQLSampleRepo7DAO;
import com.aristo.dao.SQLSampleRepo9DAO;
import com.aristo.form.SampleRepo2Form;
import com.aristo.form.SampleRepo3Form;
import com.aristo.form.SampleRepo6Form;
import com.aristo.form.SampleRepo7Form;
import com.aristo.form.SampleRepo9Form;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.ProductFormBean;
import com.aristo.valueobject.SampleRepo2FormBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SampleRepoAction extends DispatchAction {

	
	public ActionForward SampleOptRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

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
          
     	String tp = lfb.getD_type();
     	String access_t=lfb.getAccess_t();
     	int uid = lfb.getId();
     	 int div=0;
         String divnm="";
     	SampleRepo2Form rf = (SampleRepo2Form) form;

        ArrayList divList = null;
        if(access_t.equalsIgnoreCase("ALL"))
        {
        	divList= new ArrayList(lfb.getDivlist());
        }
        else
        {  
        	divList = new ArrayList();
        	if(tp.equals("A"))
        	{
        		div=5;  
        		divnm="MF";
        	}
        	if(tp.equals("T"))
        	{
        		div=6;
        		divnm="TF";
        	}
        	if(tp.equals("G"))
        	{
        		div=7;
        		divnm="GEN";
        	}
        	if(tp.equals("M"))
        	{
        		div=11;
        		divnm="MF2";
        	}
        	if(tp.equals("B"))
        	{
        		div=21;
        		divnm="MF3";
        	}
        	LoginFormBean lf = new LoginFormBean();
        	lf.setDcode(div);
        	lf.setDname(divnm); 
        	lf.setD_type(tp);
        	divList.add(lf);
        }
		rf.setDivlist(divList);
        
     	
     	
     	
     	SQLOptDAO  ad=new SQLOptDAO(); 
 		List cat =ad.getSCategory(con, tp);
 		rf.setCatlist(cat);
 		
 		con=datasource.getConnection();
 		List branch = ad.getUserBranch(con, uid);
 		rf.setBranchlist(branch);//set branch list in DivList so dont confuse...
 		rf.setYlist(lfb.getYlist());

        
		return mapping.findForward("sucess");
	}

	
	public ActionForward SampleListRepo1(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
		int choice=0;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	    try
	    {
    	datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	    int uid=lfb.getId();
   	    String type=lfb.getType();
   	    String divnm=rf.getDcode();
   	    String cat = rf.getCcode();
   	    int depo = rf.getCode();
   	    int myear=rf.getMyear();
   	    int mon = rf.getMonno();
   	    choice = rf.getChoice();
   	    int div=5;
   	    if(divnm.equals("A"))
   	    	div=5;
   	    else if(divnm.equals("T"))
   	    	div=6;
   	    else if(divnm.equals("G"))
   	    	div=7;
   	    else if(divnm.equals("M"))
   	    	div=11;
   	    else if(divnm.equals("B"))
   	    	div=21;
   	    
   	    
   	    
   	    
   	    String head=null;
   	    String whead=rf.getPname()+","+rf.getPack();
   	    SQLSampleRepo1DAO repo1 = new SQLSampleRepo1DAO();
		//r = repo4.getRepo4(con, div,depo,cat,mon,myear);
   	    if(choice==1)
   	    	r = repo1.getRepo1(con, div,depo,cat,mon,myear,type,uid,divnm);
   	    else
   	    	r = repo1.getRepo1Trend(con, div,depo,cat,0,myear,type,uid,divnm);
		//head = "SAMPLE STOCK STATEMENT FOR THE MONTH " ;
		
		  System.out.println("whead is "+whead);
		  
		if(r!=null)
		{
			head=((SampleRepo2FormBean) r.get(0)).getBrname();
			mon=((SampleRepo2FormBean) r.get(0)).getFqty();
		}
		rf.setRlist(r);
		req.setAttribute("head", head);
		session.setAttribute("whead", whead);
		session.setAttribute("depo", depo);
		session.setAttribute("monno", mon);
		session.setAttribute("div", div);
		session.setAttribute("myear", myear);
		session.setAttribute("cat", cat);
		 
		

   	    
/*   	    SQLSampleRepo1DAO repo1 = new SQLSampleRepo1DAO();
		r = repo1.getRepo1(con, tp, sdt,edt,dp,ch);
		con=datasource.getConnection();
		String month=repo1.getMonth(con, sdt);
		con=datasource.getConnection();		
		String month2=repo1.getMonth(con, sdt);
		
		rf.setRlist(r);
		req.setAttribute("whead", head);
		req.setAttribute("sdt", month);
		req.setAttribute("edt", month2);
		*/
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	    
	    if(choice==1)
	    	return mapping.findForward("sucess");
	    else
	    	return mapping.findForward("sucess1");
	}
	

	public ActionForward SampleListRepo1a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
		int gpcode=0;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	    try
	    {
    	datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	    int uid=lfb.getId();
   	    String type=lfb.getType();
   	    
   	    gpcode=rf.getCode();
   	    int myear=(Integer) session.getAttribute("myear");
		int div=(Integer) session.getAttribute("div");
   	    int depo = (Integer) session.getAttribute("depo");
		String cat = (String) session.getAttribute("cat");
   	    
   	    
   	    
   	    String head=null;
   	    String whead=rf.getPname()+","+rf.getPack();
   	    SQLSampleRepo1DAO repo1 = new SQLSampleRepo1DAO();
		//r = repo4.getRepo4(con, div,depo,cat,mon,myear);
   	   	r = repo1.getRepo1Trend(con, div,depo,cat,gpcode,myear,type,uid,"");
		//head = "SAMPLE STOCK STATEMENT FOR THE MONTH " ;
   	   	
		  System.out.println("whead is "+whead);
		  
		if(r!=null)
		{
			head=((SampleRepo2FormBean) r.get(0)).getBrname();
		}
		rf.setRlist(r);
		req.setAttribute("head", head);
		
   	    

	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	    if(gpcode==0)
	    	return mapping.findForward("sucess");
	    else
	    	return mapping.findForward("sucess1");
	    
	}

	
	public ActionForward SampleOptRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
        
        String tp = lfb.getD_type();
        String type=lfb.getType();
        String access_t=lfb.getAccess_t();
        int div=0;
        String divnm="";
        int uid=lfb.getId();
        SampleRepo2Form rf = (SampleRepo2Form) form;
        
        
        ArrayList divList = null;
        if(access_t.equalsIgnoreCase("ALL"))
        {
        	divList= new ArrayList(lfb.getDivlist());
        }
        else
        {  
        	divList = new ArrayList();
        	if(tp.equals("A"))
        	{
        		div=5;
        		divnm="MF";
        	}
        	if(tp.equals("T"))
        	{
        		div=6;
        		divnm="TF";
        	}
        	if(tp.equals("G"))
        	{
        		div=7;
        		divnm="GEN";
        	}
        	if(tp.equals("M"))
        	{
        		div=11;
        		divnm="MF2";
        	}
        	if(tp.equals("B"))
        	{
        		div=21;
        		divnm="MF3";
        	}
        	if(tp.equals("F"))
        	{
        		div=31;
        		divnm="MF4";
        	}
        	LoginFormBean lf = new LoginFormBean();
        	lf.setDcode(div);
        	lf.setDname(divnm); 
        	lf.setD_type(tp);
        	divList.add(lf);
        }
		rf.setDivlist(divList);
        int divi = ((LoginFormBean) divList.get(0)).getDcode();
        if(div<4)
        	divi+=4;
        //else
        	//divi++;
		
		SQLOptDAO  ad=new SQLOptDAO();   
		List cat =ad.getSCategory(con, tp);
		rf.setCatlist(cat);
		
		System.out.println("divi value is "+divi+" type "+type+" uid "+uid);
		 
		con=datasource.getConnection();
		List grp =ad.getSGroup(con, divi,type,uid);
		rf.setGrplist(grp);
		int gpcode = ((ProductFormBean) grp.get(0)).getGpcode();

		con=datasource.getConnection();
		List prd =ad.getSampleProduct(con, divi,gpcode,"M","");
		rf.setPlist(prd);
		rf.setYlist(lfb.getYlist());
		

  	    
  	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		return mapping.findForward("sucess");
	}

	public ActionForward SampleOptRepo2Ajax(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
     	String type=lfb.getType();
     	int uid=lfb.getId();
        SampleRepo2Form rf = (SampleRepo2Form) form;
		SQLOptDAO  ad=new SQLOptDAO(); 
		int div=Integer.parseInt(req.getParameter("div"));
		
		
		System.out.println("div ocde is "+div);
		List grp =ad.getSGroup(con, div,type,uid);
		rf.setGrplist(grp);
		
	
		
  	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		return mapping.findForward("sucess");
	}

	
	public ActionForward SampleOptRepo2aAjax(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        res.setContentType("application/json");
        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
        
     	String type=lfb.getType();
     	int uid=lfb.getId();
        SampleRepo2Form rf = (SampleRepo2Form) form;
		SQLOptDAO  ad=new SQLOptDAO(); 
		
		int div=Integer.parseInt(req.getParameter("div"));
		//int gpcode=Integer.parseInt(req.getParameter("grp"));
		String cat= (String) req.getParameter("cat");
		String search= (String) req.getParameter("q");
		int grup = Integer.parseInt(req.getParameter("userInput"));  
		String search2 = req.getParameter("userInput2"); 
		if(search==null)
			search=search2.substring(0,1);

		
		if(grup==0)
		{
			List grp =ad.getSGroup(con, div,type,uid);
			grup = ((ProductFormBean)grp.get(0)).getGpcode();
			
			
			con=datasource.getConnection();
		}
		
		List prd =ad.getSampleProduct(con, div,grup,cat,search);
		rf.setPlist(prd);

		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromJavaArrayList = gsonBuilder.toJson(prd);
		//System.out.println("prodct json "+jsonFromJavaArrayList);
		PrintWriter out = res.getWriter();  
		out.print(jsonFromJavaArrayList.toString()); 
		//System.out.println("size of lis is "+prd.size());
 
  	    
  	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		//  	    return mapping.findForward("sucesss");
  	    return null;
		
	}

	
	public ActionForward SampleListRepo2(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	    String divnm=rf.getDcode();
   	    int pcode=rf.getCode(); 
   	    int myear=rf.getMyear();
   	    int monno=rf.getMonno();
   	    int choice=rf.getChoice();
   	    pcode = Integer.parseInt(req.getParameter("myproduct"));
   	    		
   	    System.out.println("product code is "+pcode);
   	    int div=5;
   	    if (divnm.equals("A"))
   	    	div = 5;
   	    if (divnm.equals("T"))
   	    	div = 6;
   	    if (divnm.equals("G"))
   	    	div = 7;
   	    if (divnm.equals("M"))
   	    	div = 11;
   	    if (divnm.equals("B"))
   	    	div = 21;
   	    if (divnm.equals("F"))
   	    	div = 31;
   	    
   	    String head= " Stock Ledger ";
   	      
   	    SQLSampleRepo2DAO repo2 = new SQLSampleRepo2DAO();
		r = repo2.getRepo2(con, div,myear,monno,pcode,choice);
		int size=0;
		if(r!=null)
		{
			size=r.size(); 
		 head = ((SampleRepo2FormBean) r.get(size-1)).getPname();
		 monno=((SampleRepo2FormBean) r.get(size-1)).getFqty();
		}
		System.out.println("monno in Action is "+monno);
		rf.setRlist(r);
		session.setAttribute("whead", head);
		session.setAttribute("pcode", pcode);
		session.setAttribute("monno", monno);
		session.setAttribute("div", div);
		session.setAttribute("choice", choice);
		session.setAttribute("myear", myear);
		 
		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}
	

	public ActionForward SampleListRepo2a(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	     
   	    int pcode=rf.getCode();
		int depo=rf.getDepo_code();
		int monno = (Integer) session.getAttribute("monno");
		int div=(Integer) session.getAttribute("div");
		int choice=0;
		int myear=(Integer) session.getAttribute("myear");
		try {
			choice = (Integer) session.getAttribute("choice");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			choice=0;
			
		}
		
		
		if(depo==0)
			depo = (Integer) session.getAttribute("depo");
		else
			pcode = (Integer) session.getAttribute("pcode");
   	    
		

   	    SQLSampleRepo2DAO repo2 = new SQLSampleRepo2DAO();
		r = repo2.getRepo2a(con, div,depo,monno,pcode,choice,myear);
		String whead="No Record Found";
		if(r!=null)
		{
			whead = ((SampleRepo2FormBean) r.get(0)).getBrname();
		}
		rf.setRlist(r);
		req.setAttribute("whead",whead);
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}

	public ActionForward SampleOptRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleListRepo3(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	  
        try
        {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
	    String tp = lfb.getD_type();
   	    int dp=lfb.getCode();   		    
   	    SampleRepo3Form rf = (SampleRepo3Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    
   	    String head = "NEAR EXPIRY/EXPIRED BATCHWISE STOCK AS ON "+rf.getSdate();
   	    
   	    SQLSampleRepo3DAO repo3 = new SQLSampleRepo3DAO();
		r = repo3.getRepo3(con, tp, sdt,dp);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleOptRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
          
     	String tp = lfb.getD_type();
     	int uid = lfb.getId();
     	SampleRepo2Form rf = (SampleRepo2Form) form;
 		
 		SQLOptDAO  ad=new SQLOptDAO(); 
 		List cat =ad.getSCategory(con, tp);
 		rf.setCatlist(cat);
 		
 		con=datasource.getConnection();
 		List branch = ad.getUserBranch(con, uid);
 		rf.setDivlist(branch);//set branch list in DivList so dont confuse...
 		rf.setYlist(lfb.getYlist());
     	
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleListRepo4(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");
        if (lfb==null)

        {
        return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	    String divnm=rf.getDcode();
   	    String cat = rf.getCcode();
   	    int depo = rf.getCode();
   	    int myear=rf.getMyear();
   	    int mon = rf.getMonno();
   	    int div=5;
   	    if(divnm.equals("A"))
   	    	div=5;
   	    else if(divnm.equals("T"))
   	    	div=6;
   	    else if(divnm.equals("G"))
   	    	div=7;
   	    else if(divnm.equals("M"))
   	    	div=11;
   	    else if(divnm.equals("B"))
   	    	div=21;
   	    
   	    
   	    
   	    
   	    String head=null;
   	    SQLSampleRepo4DAO repo4 = new SQLSampleRepo4DAO();
		r = repo4.getRepo4(con, div,depo,cat,mon,myear,null,0);
		if(r!=null)
		  head = "ITEMWISE SAMPLE ISSUE STATEMENT FOR THE MONTH "+((SampleRepo2FormBean) r.get(0)).getBat_no() ;
		
		rf.setRlist(r);
		req.setAttribute("whead", head);		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}	
	
	
	public ActionForward SampleOptRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
        int depo = lfb.getCode();
        System.out.println(depo);

		String divnm = lfb.getD_type();
     	int uid = lfb.getId();
     	SampleRepo2Form rf = (SampleRepo2Form) form;

     	int div=5;
   	    if(divnm.equals("A"))
   	    	div=5;
   	    else if(divnm.equals("T"))
   	    	div=6;
   	    else if(divnm.equals("G"))
   	    	div=7;
   	    else if(divnm.equals("M"))
   	    	div=11;
   	    else if(divnm.equals("B"))
   	    	div=21;
        
		SQLOptDAO  ad=new SQLOptDAO(); 
		List prd =ad.getEmployee(con,div,10,2018);
		rf.setPlist(prd);
		
		
 		
     	con=datasource.getConnection();
 		List cat =ad.getSCategory(con, divnm);
 		rf.setCatlist(cat);
 		
 		con=datasource.getConnection();
 		List branch = ad.getUserBranch(con, uid);
 		rf.setDivlist(branch);//set branch list in DivList so dont confuse...
 		rf.setYlist(lfb.getYlist());
		
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		return mapping.findForward("sucess");
	}

	
	public ActionForward SampleOptRepo5aAjax(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {

		DataSource datasource = null;
		Connection con= null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
  	    try
  	    {	
  		datasource = this.getDataSource(req,"userDB"); 
     	con=datasource.getConnection();
        
        SampleRepo2Form rf = (SampleRepo2Form) form;
		SQLOptDAO  ad=new SQLOptDAO(); 	
		
		String divnm=(String) req.getParameter("div");
		int depo=Integer.parseInt(req.getParameter("depo"));
		int year=Integer.parseInt(req.getParameter("myear"));
		 
		int div=5;
   	    if(divnm.equals("A"))
   	    	div=5;
   	    else if(divnm.equals("T"))
   	    	div=6;
   	    else if(divnm.equals("G"))
   	    	div=7;
   	    else if(divnm.equals("M"))
   	    	div=11;
   	    else if(divnm.equals("B"))
   	    	div=21;
		
		
		List prd =ad.getEmployee(con,div,depo,year);
		rf.setPlist(prd);
  	    
  	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }		
        
		return mapping.findForward("sucess");
	}

	
	
	public ActionForward SampleListRepo5(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	    try
	    {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
		
   	    SampleRepo2Form rf = (SampleRepo2Form) form;
   	    String divnm=rf.getDcode();
   	    String cat = rf.getCcode();
   	    int depo = rf.getCode();
   	    int myear=rf.getMyear();
   	    int mon = rf.getMonno();
   	    int choice = rf.getChoice();
   	    String prt = rf.getPcode();
   	    int div=5;
   	    String whead="";
   	    if(divnm.equals("A"))
   	    	div=5;
   	    else if(divnm.equals("T"))
   	    	div=6;
   	    else if(divnm.equals("G"))
   	    	div=7;
   	    else if(divnm.equals("M"))
   	    	div=11;
   	    else if(divnm.equals("B"))
   	    	div=21;
   	    
   	    if(cat.equals("M"))
   	    	whead="MEDICINE (PHY. SAMPLE)";
   	    else if(cat.equals("P"))
   	    	whead="PROM. MATERIAL (GIFT)";
   	    else if(cat.equals("S"))
   	    	whead="STATIONARY (FIELD STAFF)";
   	    else if(cat.equals("O"))
   	    	whead="OTHERS (CONF CME.)";
   	    else if(cat.equals("H"))
   	    	whead="HO ITEMS ";

   	    
   	    System.out.println("in Action mon is "+mon);
   	    
   	    String head=null;
   	    SQLSampleRepo4DAO repo4 = new SQLSampleRepo4DAO();
		r = repo4.getRepo4(con, div,depo,cat,mon,myear,prt,choice);
		int size=0;
		if(r!=null)
		{
			size=r.size();
		//	head = "FS WISE "+whead+ " STATEMENT ";
		  head = "FS WISE "+whead+" STATEMENT "+((SampleRepo2FormBean) r.get(size-1)).getBat_no() ;
		}
		
		rf.setRlist(r);
		req.setAttribute("whead", head);	
		
		 
		
				
	  }catch (Exception e)
	  {
		  e.printStackTrace();
	  }
		return mapping.findForward("sucess");
	}

	public ActionForward SampleOptRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleListRepo6(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	  
        try
        {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
	    String tp = lfb.getD_type();
	    int dp=lfb.getCode();	    
   	    SampleRepo6Form rf = (SampleRepo6Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "TRANSFER REGISTER FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLSampleRepo6DAO repo6 = new SQLSampleRepo6DAO();
		r = repo6.getRepo6(con, tp, sdt,edt,dp);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}
		
	
	public ActionForward SampleOptRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleListRepo7(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	  
        try
        {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
	    String tp = lfb.getD_type();
	    int dp=lfb.getCode();
   	    SampleRepo7Form rf = (SampleRepo7Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "SAMPLE DISPATCH REGISTER FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLSampleRepo7DAO repo7 = new SQLSampleRepo7DAO();
		r = repo7.getRepo7(con, tp, sdt,edt,dp);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}
	
	
	public ActionForward SampleOptRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)
        {
        	return mapping.findForward("sfail");
        }
		
		return mapping.findForward("sucess");
	}
	
	public ActionForward SampleListRepo9(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		DataSource datasource = null;
		Connection con= null;
		List r =null;
        HttpSession session=req.getSession();
        LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

        if (lfb==null)

        {
        	return mapping.findForward("sfail");
        }
	  
        try
        {	
		datasource = this.getDataSource(req,"userDB"); 
   	    con=datasource.getConnection();
	    String tp = lfb.getD_type();
	    int dp=lfb.getCode();
	    
   	    SampleRepo9Form rf = (SampleRepo9Form) form;
   	    
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date sdt=dateformat.parse(rf.getSdate());
   	    Date edt=dateformat.parse(rf.getEdate());
   	    
   	    String head = "PENDING REPORT FOR THE PERIOD "+rf.getSdate()+" TO "+rf.getEdate();
   	    
   	    SQLSampleRepo9DAO repo9 = new SQLSampleRepo9DAO();
		r = repo9.getRepo9(con, tp, sdt,edt,dp);
		rf.setRlist(r);
		req.setAttribute("whead", head);
	    }catch (Exception e)
	    {
		  e.printStackTrace();
	    }
		return mapping.findForward("sucess");
	}


	
	
}
