package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

import com.aristo.valueobject.GroupSalesFormBean;

public interface GroupSalesDAO {
	public List getAllGroupSales(Connection con,String typ,int eyear);
	public int AddGroupSales(GroupSalesFormBean tfb, Connection con, String typ,int eyear); 	

}
