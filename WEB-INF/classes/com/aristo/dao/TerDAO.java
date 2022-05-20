package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

import com.aristo.valueobject.TerFormBean;

public interface TerDAO {
	public List getAllter(Connection con,int depo,String typ,int eyear);
	
	public List getAllDgm(Connection con, String typ);
	
	public List getAllZm(Connection con, String typ);
	
	public List getAllBranch(Connection con, String typ);
	
	public int AddDgm (TerFormBean tfb, Connection con, String typ);
	
	public int AddZm (TerFormBean tfb, Connection con, String typ);
	
	public int AddBranch (TerFormBean tfb, Connection con, String typ);
	
	public TerFormBean EditDgm(TerFormBean tfb, Connection con, String typ);
}
