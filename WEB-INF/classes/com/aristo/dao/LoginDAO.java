package com.aristo.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.LoginFormBean;

public interface LoginDAO {

	public LoginFormBean authenticateUser(LoginFormBean lfb, Connection con);
	public int newUser( LoginFormBean lfb, Connection con);
	public int addUserRights(LoginFormBean lfb, Connection con);
	public List getAlluser(Connection con,String sr);
	public List getUserRightList(Connection con,String sr);	
	public List getAllDepo(Connection con);
	public int Changepass(LoginFormBean lfb, Connection con);	
	public int LoginCheck (LoginFormBean ldao, Connection con);
	public List getAllUpload(Connection con,int depo, String tp,int lid,String s);
	public List getAllUploadAdmin(Connection con, String tp, int lid,String s);
	public List getAllYear(Connection con);
	public List getAllGroup(Connection con, String tp); 
	public List getAllDTer(Connection con, String tp,int uid);	  
	public int addPmtGrp (LoginFormBean lfb, Connection con);
	public int addTerUser (LoginFormBean lfb, Connection con);	
	public List getPmtRightList(Connection con,String sr);
	public String getType(Connection con,int id);
	public int AddEntry (LoginFormBean lfb, Connection con,int depo);	 
	public List DailyList(Connection con,Date edate,String divi,int id,int opt,List divList,int mon);	
	public List DailyListNew(Connection con,Date edate,String divi,int id,int opt,List divList,int mon);	
	public List DailyListStatus(Connection con,Date edate,String divi,int id,int opt,List divList,int mon);
	public int AddBudSal (LoginFormBean lfb, Connection con);
	public int AddBudCol (LoginFormBean lfb, Connection con);	
	public String getLastNo(Connection con);
}
