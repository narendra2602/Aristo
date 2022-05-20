package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aristo.valueobject.GroupSalesFormBean;

public class SQLGroupSalesDAO implements GroupSalesDAO{

	public List getAllGroupSales(Connection con, String typ,int eyear) { 
		 
		GroupSalesFormBean gmfb;
		PreparedStatement ps=null; 
		ResultSet rst=null; 
			
		List<GroupSalesFormBean> data = new ArrayList<GroupSalesFormBean>();
		try { 
            String tblnm=null;
        	tblnm=(typ+"_GroupSales08").toLowerCase();
			String query = "select gp_code,gp_name from "+ tblnm ;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				gmfb = new GroupSalesFormBean();
				gmfb.setGp_code(rst.getInt(1));
				gmfb.setGp_name(rst.getString(2));
				gmfb.setLink1("Edit");
				data.add(gmfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLGroupSalesDAO.getAllGroupSales " + e);
		}
		
		finally {
			try {
	            if(rst != null){ rst.close();}
	            if(ps != null){ps.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLGroupSalesDAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}

	public int AddGroupSales(GroupSalesFormBean tfb, Connection con, String typ,int eyear) { 
		 
	    int i=0;
	    PreparedStatement ps1=null;
	    ResultSet rs=null;
	    PreparedStatement ps=null;
		try { 
            String tblnm=null;
            tblnm=(typ+"_groupsales08").toLowerCase();
            
            String queryy = "Select gp_code from "+tblnm+ " where gp_code=?";
			ps1 = con.prepareStatement(queryy);
			ps1.setInt(1,tfb.getGp_code());
			rs = ps1.executeQuery();
			
			if (rs.next())
				return 0;
            
            String query = "insert into  "+ tblnm+" (gp_code,gp_name) values(?,?)";
			ps = con.prepareStatement(query);
            ps.setInt(1,tfb.getGp_code());
            ps.setString(2,tfb.getGp_name());
                
			i=ps.executeUpdate();	
			System.out.println("No. of Record addesd "+i);
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLGroupSalesDAO.getAddGroupSales " + e);
			i=-1;
		}
		finally {
			try {
	            if(rs != null){ rs.close();}
	            if(ps != null){ps.close();}
	            if(ps1 != null){ps1.close();}	            
	            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLGroupSalesDAO.Connection.close "+e);
			  }
		}
		return i;
	}
	
	
	public GroupSalesFormBean EditGroupSales(Connection con,String typ, int gp_code)
	{
		GroupSalesFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		try {
			String tblnm=null;
			tblnm=(typ+"_groupsales08").toLowerCase();
			ps = con.prepareStatement("Select gp_code,gp_name from "+tblnm+" where gp_code=? ");
			ps.setInt(1,gp_code);
			rs = ps.executeQuery();   
			if (rs.next())
			{
                 lf = new GroupSalesFormBean();
            	 lf.setGp_code(rs.getInt(1));
            	 lf.setGp_name(rs.getString(2));
			}
			rs.close();
			ps.close();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
		           if(ps != null){ps.close();}
		           if(rs != null){ rs.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLGroupSalesDAO.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}		
	
	public int UpdateGrpSales(Connection con,GroupSalesFormBean tf, String typ)
	{
		PreparedStatement ps =null;
		int i=0;
		try {
			String tblnm=null;
			tblnm=(typ+"_groupsales08").toLowerCase();
			ps = con.prepareStatement("update "+tblnm+" set gp_name=? where gp_code=? ");
			ps.setString(1,tf.getGp_name());
			ps.setInt(2,tf.getGp_code());
			i=ps.executeUpdate();
			ps.close();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try 
			{
		           if(ps != null){ps.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLGroupSlesDAO.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}		
	
	
	
}
