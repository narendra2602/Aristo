package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface AccountCentralDAO {
	public List getAllAccount(Connection con, String typ );
	public int updateAccount( List acc,Connection con, String typ);
}
