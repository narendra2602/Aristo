package com.aristo.dao;

import java.sql.Connection;
import java.util.List;



public interface MSRDAO {

	public List getAllMSR(Connection con);
	public int updateMSR(String typ,List msr,Connection con);

}
