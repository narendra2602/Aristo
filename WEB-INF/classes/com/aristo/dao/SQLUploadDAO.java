package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.aristo.valueobject.UploadFormBean;

public class SQLUploadDAO {  

	
/////////////////////////////////////////Upload List//////////////////////////	
	public List getAllupload(Connection con) { 
		 
		UploadFormBean ufb;
		PreparedStatement ps =null;
		ResultSet rst =null;
		List<UploadFormBean> data = new ArrayList<UploadFormBean>();
		try { 
			String query = "select * from upload where depo_code=? and typ=? order by depo_code ";
			ps = con.prepareStatement(query);
			ps.setInt(1, 21);
			ps.setString(2, "P");
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				ufb = new UploadFormBean();
				ufb.setFile_path(rst.getString(3));
				data.add(ufb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLUploadDAO.getAllUpload " + e);
		}
		finally {
			try 
			{
				if(ps != null){ps.close();} 
				if(rst != null){ps.close();}				
	            if(con != null){con.close();}
	        }
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLTerDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////Update Upload//////////////////////////////////////////	
	 public int updateupload(UploadFormBean u,Connection con)
	   {
		   int i=0;
			PreparedStatement ps =null;
			try {
				String query ="update upload set u_date=?,u_time=?,upload=? where concat(substr(file_path,4,3), substr(file_path,9,4))=? and typ=?";
				ps = con.prepareStatement(query);
				 	
				ps.setString(1, u.getU_date());
				ps.setString(2, u.getU_time());
				ps.setString(3, u.getUpload());
				ps.setString(4, u.getFile_name());
				ps.setString(5, u.getTyp());
					
				i=ps.executeUpdate();

				ps.close();
				
			} catch (SQLException ex) {
				System.out.println("-------------Exception in SQLUploadDAO.update "+ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
						if(ps != null){ps.close();} 
			            if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLUploadDAO.Connection.close "+e);
				  }
			}
			
			
			
			return i;
		     
		   
	   }
///////////////////////////////////////////////////////////////////////////////////////////////////
	 

////////////////////////////////////////////Update CWH Files Upload//////////////////////////////////////////	
	 public int updateCWHupload(UploadFormBean u,Connection con)
	   {
		   int i=0;
			PreparedStatement ps =null;
			try {
				String query ="update upload set u_date=?,u_time=?,upload=? where substr(file_path,2)=? and typ=?";
				ps = con.prepareStatement(query);
				ps.setString(1, u.getU_date());
				ps.setString(2, u.getU_time());
				ps.setString(3, u.getUpload());
				ps.setString(4, u.getFile_name());
				ps.setString(5, u.getTyp()); 
					
				i=ps.executeUpdate();

				ps.close();
				
			} catch (SQLException ex) {
				System.out.println("-------------Exception in SQLUploadDAO.update "+ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
						if(ps != null){ps.close();} 
			            if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLUploadDAO.Connection.close "+e);
				  }
			}
			
			
			
			return i;
		     
		   
	   }
///////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 
	 
////////////////////////////////////////////Updatee Rennew////////////////////////////////////////
	 
	 public void  updateRenew(UploadFormBean u,Connection con)
	   {
		   int i=0;
		   PreparedStatement ps1 =null;		   
			try {
				String query1 ="update upload set upload=? where typ=?";
				ps1 = con.prepareStatement(query1);
				ps1.setString(1, "NO");
				ps1.setString(2, u.getTyp());
				
				i=ps1.executeUpdate();
				ps1.close();
				
				
			} catch (SQLException ex) {
				System.out.println("-------------Exception in SQLUploadDAO.Renew "+ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
						if(ps1 != null){ps1.close();} 
			            if(con != null){con.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLUploadDAO.Connection.close "+e);
				  }
			}
		   
	   }

}
