package cen.aristo.dao;

import java.sql.Connection;
import java.util.List;

public interface TransportDAO {
	public List getAllTransport(Connection con, String typ );
	public int updateTransport( List trp,Connection con, String typ);
}
