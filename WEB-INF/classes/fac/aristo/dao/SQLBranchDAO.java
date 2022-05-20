package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fac.aristo.valueobject.BranchFormBean;

public class SQLBranchDAO {

	public List getAllBranch(Connection con, String typ) { 
		 
		BranchFormBean pfb;
		PreparedStatement ps = null;
		ResultSet rst=null;
		
		List<BranchFormBean> data = new ArrayList<BranchFormBean>();
		try { 
            String tblnm=null;
       	    tblnm="f_branch";
		
			String query = "select * from "+tblnm; 
			
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				pfb = new BranchFormBean(); 
				pfb.setCompt_code(rst.getString(1));
				pfb.setBrn_id(rst.getString(2));
				pfb.setBrn_desc(rst.getString(3));
				pfb.setDepo_code(rst.getInt(4));
				pfb.setDepo_name(rst.getString(5));
				data.add(pfb);
			}
			
			rst.close();
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLBranchDAO.getAllBranch " + e);
		}
		finally {
			try {
	             if(rst != null){ rst.close();}
	             if(ps != null){ ps.close();}
	             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLBranchDAO.Connection.close "+e);
			  }
		}	
		return data;
	}

	
	
}
