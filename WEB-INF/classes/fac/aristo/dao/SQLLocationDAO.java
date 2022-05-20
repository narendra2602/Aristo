package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fac.aristo.valueobject.LocationFormBean;

public class SQLLocationDAO {

	public List getAllLocation(Connection con, String typ) { 
		 
		LocationFormBean pfb;
		PreparedStatement ps = null;
		ResultSet rst=null;
		
		List<LocationFormBean> data = new ArrayList<LocationFormBean>();
		try { 
            String tblnm=null;
       	    tblnm="f_location";
		
			String query = "select * from "+tblnm ; 
			
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				pfb = new LocationFormBean(); 
				pfb.setComp_code(rst.getString(1));
				pfb.setLoc_id(rst.getString(2));
				pfb.setLoca_name(rst.getString(3));
				pfb.setLoc_type(rst.getString(4));
				pfb.setLoc_abbr(rst.getString(5));
				data.add(pfb);
			}
			
			rst.close();
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLLocationDAO.getAllLocation " + e);
		}
		finally {
			try {
	             if(rst != null){ rst.close();}
	             if(ps != null){ ps.close();}
	             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLLocationDAO.Connection.close "+e);
			  }
		}	
		return data;
	}
	
	
}
