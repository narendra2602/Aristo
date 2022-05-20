package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 
import com.aristo.valueobject.MRFormBean;

public class SQLMRDAO implements MRDAO{
	
	public List getAllMR(Connection con) { 
		 
		MRFormBean pfb;
        PreparedStatement ps=null; 
        ResultSet rst=null;
		
		List<MRFormBean> data = new ArrayList<MRFormBean>();
		try { 
			
            String typ="P";
            String tblnm=null;
            if (typ.equals("P"))
            	tblnm="a_mr_master08";
            if (typ.equals("T"))
            	tblnm="t_mr_master08";
            if (typ.equals("G"))
            	tblnm="g_mr_master08";

			String query = "select * from " + tblnm;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next())
			{
				pfb = new MRFormBean();
				pfb.setDepo_code(rst.getInt(1));
				pfb.setMr_cd(rst.getInt(2));
				pfb.setMr_name(rst.getString(3));
				pfb.setSlct(rst.getString(4));
				data.add(pfb);
			}
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLMRDAO.getAllMR " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLMRDAO.Connection.close "+e);
			  }
		}			
		return data;
	}


 public int updateMR(String typ,List mr,Connection con)
   {
	   int i=0;
	   MRFormBean p=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null;
	   PreparedStatement ps2=null;
	   ResultSet rs=null;
		try {
            String tblnm=null;
            tblnm=(typ+"_mr_master08").toLowerCase();

      String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and mr_cd=? and mkt_year=?";
  	  ps1 = con.prepareStatement(query1);
      
	  String query ="update "+tblnm+" set MR_NAME=? where DEPO_CODE=? and MR_CD=? and mkt_year=?";
	  ps = con.prepareStatement(query);

	  String query2 ="insert into "+tblnm+" values (?,?,?,?,?)";
	  ps2 = con.prepareStatement(query2);

	  con.setAutoCommit(false);
      Iterator it = mr.iterator();
      while(it.hasNext()) 
      	 {
          	p = (MRFormBean) it.next();        
			ps1.setInt(1,p.getDepo_code());
			ps1.setInt(2, p.getMr_cd());
			ps1.setInt(3, p.getMkt_year());
			
			rs = ps1.executeQuery();
			
			if (rs.next())
			{
				ps.setString(1, p.getMr_name());
				ps.setInt(2,p.getDepo_code());
				ps.setInt(3, p.getMr_cd());
				ps.setInt(4, p.getMkt_year());
				i= i + ps.executeUpdate();
			}
			else
			{
				ps2.setInt(1,p.getDepo_code());
				ps2.setInt(2, p.getMr_cd());
				ps2.setString(3, p.getMr_name());
				ps2.setString(4, p.getSlct());
				ps2.setInt(5, p.getMkt_year());
				i= i + ps2.executeUpdate();
			}
			
			rs.close();

     	 }	
      
 		con.commit();
   		con.setAutoCommit(true);
   		ps.close();
   		ps1.close();
   		ps2.close();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("-------------Exception in SQLMrDAO.update " + ex);
			i=-1;
		}
		finally {
			try {
			     System.out.println("No. of Records Update/Insert : "+i);
	             if(ps != null){ ps.close();}
	             if(ps1 != null){ps1.close();}
	             if(ps2 != null){ps2.close();}
	             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLMrDAO.Connection.close "+e);
			  }
		}
		return i;
   }

}
