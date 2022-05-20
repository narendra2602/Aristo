package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface AccountDAO {
	public List getAllAccount(Connection con, int depo, String typ,int eyear,String utype,int uid);
	public int updateAccount(String typ,List acc,Connection con);
}
