package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.GroupMktFormBean;

public class SQLGroupMktDAO implements GroupMktDAO{
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public List getAllGroupMkt(Connection con, String typ,int eyear) { 
		 
		GroupMktFormBean gmfb;
		ResultSet rst=null;
		PreparedStatement ps=null; 
		List<GroupMktFormBean> data = new ArrayList<GroupMktFormBean>();
		try { 
            String tblnm=null;
        	tblnm=(typ+"_Group_Mkt08").toLowerCase(); 

			String query = "select * from "+ tblnm+" where mkt_year=? " ;
			ps = con.prepareStatement(query);
			ps.setInt(1, eyear);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				gmfb = new GroupMktFormBean();
				gmfb.setGp_code(rst.getInt(8));
				gmfb.setGp_name(rst.getString(2));
				gmfb.setLink1("Edit");
				data.add(gmfb);
			}
			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLGroupMktDAO.getAllGroupMkt " + e);
		}
	     finally 
			{
				try
				{
		            if(rst != null){ rst.close();}
		            if(ps != null){ps.close();}
		            if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("--------Exception in SQLGroupMktDAO.Connection.close "+e);
				}
			}
		return data;
	}


	public int AddGroupMkt(GroupMktFormBean tfb, Connection con, String typ,int eyear) { 
		 
	    int i=0;
	    PreparedStatement ps1=null;
	    ResultSet rs=null;
	    PreparedStatement ps=null;
		try { 
            String tblnm=null;
            tblnm=(typ+"_group_mkt08").toLowerCase();
            
            String queryy = "Select gcode from "+tblnm+ " where gcode=?";
			ps1 = con.prepareStatement(queryy);
			ps1.setInt(1,tfb.getGmain_cd());
			rs = ps1.executeQuery();
			
			if (rs.next())
				return 0;
            
            String query = "insert into  "+ tblnm+" (gcode,gp_name) values(?,?)";
			ps = con.prepareStatement(query);
            ps.setInt(1, tfb.getGmain_cd());
            ps.setString(2, tfb.getGp_name());
                
			i=ps.executeUpdate();	
			System.out.println("No. of Record addesd "+i);
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLGroupMktDAO.getAddGroup " + e);
			i=-1;
		}
		finally {
			try {
	            if(rs != null){ rs.close();}
	            if(ps != null){ps.close();}
	            if(ps1 != null){ps1.close();}	            
	            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLGroupMktDAO.Connection.close "+e);
			  }
		}
		return i;
	}

	public GroupMktFormBean EditGroupMkt(Connection con,String typ, int gp_code)
	{
		GroupMktFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		try {
			String tblnm=null;
			tblnm=(typ+"_group_mkt08").toLowerCase();
			ps = con.prepareStatement("Select gcode,gp_name from "+tblnm+" where gcode=? ");
			ps.setInt(1,gp_code);
			rs = ps.executeQuery();   
			if (rs.next())
			{
                 lf = new GroupMktFormBean();
            	 lf.setGmain_cd(rs.getInt(1));
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
				System.out.println("-------------Exception in SQLGroupMktDAO.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}	
	
	public int UpdateGrpMkt(Connection con,GroupMktFormBean tf, String typ)
	{
		PreparedStatement ps =null;
		int i=0;
		try {
			String tblnm=null;
			tblnm=(typ+"_group_mkt08").toLowerCase();
			ps = con.prepareStatement("update "+tblnm+" set gp_name=? where gp_code=? ");
			ps.setString(1,tf.getGp_name());
			ps.setInt(2,tf.getGmain_cd());
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
				System.out.println("-------------Exception in SQLGroupMktDAO.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}	
	
	

}
