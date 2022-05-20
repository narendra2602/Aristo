package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface DestinationDAO {
	public List getAllDestn(Connection con, String typ); 
	public int updateDestn( List acc,Connection con,String br);
}
