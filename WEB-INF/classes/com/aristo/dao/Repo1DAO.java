package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface Repo1DAO {
                   
	public List getAllrepo(Connection con,String tp,int loginid,int eyear);
	public List getCheckList(Connection con,String tp,int loginid,int eyear);	
 
}
