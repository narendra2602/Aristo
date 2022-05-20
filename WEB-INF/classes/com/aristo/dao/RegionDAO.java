package com.aristo.dao;

import java.sql.Connection;
import java.util.List;



public interface RegionDAO {

	public List getAllRegion(Connection con,int depo,String typ,int eyear);
	public int updateRegion(String typ,List r,Connection con);

}
