package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface DispatchDAO {
	public List getAllBranch(Connection con,String tp);
}
