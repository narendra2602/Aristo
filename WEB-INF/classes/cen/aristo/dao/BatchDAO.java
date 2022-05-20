package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface BatchDAO {
	public List getAllbatch(Connection con,String typ,int pcode );
	public int updatebatch( List bat,Connection con,String typ);
	public List getAllProduct(Connection con, String tp); 
	public String getProduct(Connection con, String tp,int pcode);	
	
} 
