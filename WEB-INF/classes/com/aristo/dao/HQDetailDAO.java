package com.aristo.dao;

import java.sql.Connection;
import java.util.List;


public interface HQDetailDAO {
	public int updateHQDetail(String typ,List hq,Connection con);
}
