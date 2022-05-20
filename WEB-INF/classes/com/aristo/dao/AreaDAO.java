package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

import com.aristo.valueobject.AreaFormBean;



public interface AreaDAO {
                   
	public List getAllarea(Connection con, int depo, String type,int eyear);
	public int updateArea(String typ,List ar,Connection con);
	public int updateTrig(String typ,AreaFormBean ar,Connection con);
	
}
