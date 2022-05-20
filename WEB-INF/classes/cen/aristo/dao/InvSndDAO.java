package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface InvSndDAO {   
	
	public int updateInvSnd( List inv2,Connection con, String typ,String ymon);
}
