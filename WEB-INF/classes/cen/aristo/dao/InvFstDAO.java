package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface InvFstDAO {   
	
	public int updateInvFst( List inv1,Connection con, String typ);
}
