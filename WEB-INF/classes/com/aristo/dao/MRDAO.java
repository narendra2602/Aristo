package com.aristo.dao;

import java.sql.Connection;
import java.util.List;



public interface MRDAO {
	public List getAllMR(Connection con);
	public int updateMR(String typ,List mr,Connection con);
} 
