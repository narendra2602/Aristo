package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aristo.valueobject.LoginFormBean;


public class SQLUserEditDAO {

	public LoginFormBean UserEdit(Connection con,int uid)
	{
		LoginFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		PreparedStatement ps1 =null;
		ResultSet rs1 =null;		
		try {
			ps = con.prepareStatement("Select title,f_name,l_name,desig,location,department,comp_code,login_name,access_t,status,type,email from login where id=? ");
			ps.setInt(1, uid);
			rs = ps.executeQuery();   
			
			if (rs.next())
			{
                 lf = new LoginFormBean();
            	 lf.setTitle(rs.getString(1));
            	 lf.setF_name(rs.getString(2));
            	 lf.setL_name(rs.getString(3));
            	 lf.setDesig(rs.getString(4));
            	 lf.setLocation(rs.getString(5));
            	 lf.setDepartment(rs.getString(6));
            	 lf.setComp_code(rs.getString(7));
            	 lf.setLogin_name(rs.getString(8));
            	 lf.setAccess_t(rs.getString(9));
            	 lf.setStatus(rs.getString(10));
            	 lf.setType(rs.getString(11));
            	 lf.setEmail(rs.getString(12));
			}
			
			rs.close();
			ps.close();	

			ps1 = con.prepareStatement("Select depo_code from user_branch08 where user_id=? and status=? ");
			ps1.setInt(1, uid);
			ps1.setString(2, "Y");
			rs1 = ps1.executeQuery();   
			String depo[]=new String[24];
			int i=0;
			while (rs1.next())
			{
            	depo[i]=rs1.getString(1);
            	i++;
			}
			lf.setCheckbox1(depo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
		           if(ps != null){ps.close();}
		           if(rs != null){ rs.close();}
		           if(ps1 != null){ps1.close();}
		           if(rs1 != null){ rs1.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLUserEditDAO.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}

 
	
	public int UserUpdate(Connection con,LoginFormBean lfb)
	{
		int i=0;
		PreparedStatement ps =null;
		PreparedStatement ps1 =null;		
		PreparedStatement ps2 =null;
		PreparedStatement ps3 =null;
		boolean tag=true;
		try {
			ps = con.prepareStatement("update login set title=?,f_name=?,l_name=?,desig=?,location=?,department=?,comp_code=?,login_name=?,access_t=?,status=?,type=?,email=?,d_type=? where id=? ");
			ps.setString(1, lfb.getTitle());
			ps.setString(2, lfb.getF_name());
			ps.setString(3, lfb.getL_name());
			ps.setString(4, lfb.getDesig());
			ps.setString(5, lfb.getLocation());
			ps.setString(6, lfb.getDepartment());
			ps.setString(7, lfb.getComp_code());
			ps.setString(8, lfb.getLogin_name());
			ps.setString(9, lfb.getAccess_t());
			ps.setString(10, lfb.getStatus());
			ps.setString(11, lfb.getType());
			ps.setString(12, lfb.getEmail());
			ps.setString(13, lfb.getD_type());
			ps.setInt(14, lfb.getId());	
			i=ps.executeUpdate();
			ps.close();

			
		  if((lfb.getType().equals("Central"))|| (lfb.getType().equals("Factory")))
			  tag=false;
		  
		  if(tag)
		  {
////////////////////////////////////////// Set Status "N" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update user_branch08 set status=? where user_id=? ");
			ps1.setString(1, "N");
			ps1.setInt(2, lfb.getId());
			i=ps1.executeUpdate();
			ps1.close();

///////////////////////////////// select query to find record for selected user ////////////////////////////////////////				
			ps2 = con.prepareStatement("select depo_code from user_branch08 where user_id=? and depo_code=? ");
			ResultSet rs2=null;
			
///////////////////////////////// insert query to insert record for selected user ////////////////////////////////////////				
			ps3 = con.prepareStatement("insert into user_branch08 values (?,?,?) " );
	
///////////////////////////////// update query to update status to "Y" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update user_branch08 set status=? where user_id=? and depo_code=? ");

			String depo[]= lfb.getCheckbox1();
			
			for (int k=0; k<depo.length; k++)
			{
				ps2.setInt(1, lfb.getId());
				ps2.setInt(2, Integer.parseInt(depo[k]));
				rs2 = ps2.executeQuery();
				if (rs2.next())
				{
					ps1.setString(1, "Y");
					ps1.setInt(2, lfb.getId());
					ps1.setInt(3, Integer.parseInt(depo[k]));
					i=ps1.executeUpdate();
				}
				else
				{
					ps3.setInt(1, lfb.getId());
					ps3.setInt(2, Integer.parseInt(depo[k]));
					ps3.setString(3, "Y");
					i = ps3.executeUpdate();
					
				}
				rs2.close();
			}
			ps1.close();
			ps2.close();
			ps3.close();
			
		  }	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
	           if(ps != null){ps.close();}
	           if(ps1 != null){ps1.close();}
	           if(ps2 != null){ps2.close();}
	           if(ps3 != null){ps3.close();}	           
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLUSerUpdateDAO.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public LoginFormBean UserRightsEdit(Connection con,int uid)
	{
		String tblnm=null;
		String typ=null;
		String nm=null;
		LoginFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		PreparedStatement ps22 =null;
		ResultSet rs22 =null;
		PreparedStatement ps1 =null;
		ResultSet rs1 =null;				
		
		try {
			ps22 = con.prepareStatement("Select type,login_name from login where id=? ");
			ps22.setInt(1, uid);
			rs22 = ps22.executeQuery();
			if (rs22.next())
			{
			typ=rs22.getString(1);
			nm=rs22.getString(2);
			}

		    if(typ.equals("Central"))
 			    tblnm="user_rights";
			 else
 			    tblnm="user_rights08";
		    
			int index=0;
			ps = con.prepareStatement("Select count(*) from "+tblnm+" where user_id=? ");
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			if (rs.next())
			{
				index=rs.getInt(1);
			}
			rs.close();
			ps.close();

			ps1 = con.prepareStatement("Select repo_id from "+tblnm+" where user_id=? and status=? ");
			ps1.setInt(1, uid);
			ps1.setString(2, "Y");
			rs1 = ps1.executeQuery();   
			String depo[]=new String[index];
			int i=0;
			while (rs1.next())
			{
            	depo[i]=rs1.getString(1);
            	i++;
			}

            lf = new LoginFormBean();
			lf.setCheckbox2(depo);
			lf.setType(typ);
			lf.setLogin_name(nm);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
	           if(ps != null){ps.close();}
	           if(rs != null){ rs.close();}
	           if(ps22 != null){ps22.close();}
	           if(rs22 != null){ rs22.close();}
	           if(ps22 != null){ps22.close();}
	           if(rs22 != null){ rs22.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLUserRightsEditDAO.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int UserRightsUpdate(Connection con,LoginFormBean lfb)
	{
		String tblnm=null;
		String tblnm1=null;
		PreparedStatement ps1 =null;
		PreparedStatement ps2 =null;
		ResultSet rs2 =null;
		PreparedStatement ps4 =null;
		ResultSet rs4 =null;				
		PreparedStatement ps3 =null;
		int i=0;
		try {
			
		    if(lfb.getType().equals("Central"))
		    {
 			    tblnm="user_rights";
		        tblnm1="repo_master";
		    }
			else
			{
 			   tblnm="user_rights08";
		       tblnm1="repo_master08";
			}  
////////////////////////////////////////// Set Status "N" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update "+tblnm+" set status=? where user_id=? ");
			ps1.setString(1, "N");
			ps1.setInt(2, lfb.getId());
			i=ps1.executeUpdate();
			ps1.close();

///////////////////////////////// select query to find record for selected user ////////////////////////////////////////				
			ps2 = con.prepareStatement("select repo_id from "+tblnm+" where user_id=? and repo_id=? ");
			
			ps4 = con.prepareStatement("select tab_id from "+tblnm1+" where repo_id=? ");
			
///////////////////////////////// insert query to insert record for selected user ////////////////////////////////////////				
			ps3 = con.prepareStatement("insert into "+tblnm+" values (?,?,?,?)" );
	
///////////////////////////////// update query to update status to "Y" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update "+tblnm+" set status=? where user_id=? and repo_id=? ");

			String depo[]= lfb.getCheckbox2();
			
			for (int k=0; k<depo.length; k++)
			{
				ps2.setInt(1, lfb.getId());
				ps2.setString(2, depo[k]);
				
				rs2 = ps2.executeQuery();
				if (rs2.next())
				{
					ps1.setString(1,"Y");
					ps1.setInt(2,lfb.getId());
					ps1.setString(3,depo[k]);
					i=ps1.executeUpdate();
				}
				else
				{
					ps4.setString(1, depo[k]);
					rs4=ps4.executeQuery();
					
					ps3.setInt(1, lfb.getId());
					if (rs4.next())
					{
					  ps3.setInt(2, rs4.getInt(1));
					}
					ps3.setString(3,depo[k]);
					ps3.setString(4,"Y");
					i = ps3.executeUpdate();
					rs4.close();
				}
				rs2.close();
			}
			ps1.close();
			ps2.close();
			ps3.close();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		finally {
			try 
			{
		        if(ps1 != null){ps1.close();}
		        if(ps3 != null){ps3.close();}
		        if(ps2 != null){ps2.close();}		        
		        if(rs2 != null){rs2.close();}
		        if(ps4 != null){ps4.close();}
		        if(rs4 != null){rs4.close();}
		        if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLUSerUpdateDAO.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}		

	public LoginFormBean PmtGroupEdit(Connection con,int uid)
	{
		String tblnm=null;
		String typ=null;
		String nm=null;
		LoginFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		PreparedStatement ps22 =null;
		ResultSet rs22 =null;
		PreparedStatement ps1 =null;
		ResultSet rs1 =null;				
		
		try {
			ps22 = con.prepareStatement("Select login_name from login where id=? ");
			ps22.setInt(1, uid);
			rs22 = ps22.executeQuery();
			if (rs22.next())
			{
			nm=rs22.getString(1);
			}

 			tblnm="pmt_group";
		    
			int index=0;
			ps = con.prepareStatement("Select count(*) from "+tblnm+" where user_id=? ");
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			if (rs.next())
			{
				index=rs.getInt(1);
			}
			rs.close();
			ps.close();

			ps1 = con.prepareStatement("Select gp_code from "+tblnm+" where user_id=? and status=? ");
			ps1.setInt(1, uid);
			ps1.setString(2, "Y");
			rs1 = ps1.executeQuery();   
			String depo[]=new String[index];
			int i=0;
			while (rs1.next())
			{
            	depo[i]=rs1.getString(1);
            	i++;
			}

            lf = new LoginFormBean();
			lf.setCheckbox2(depo);
			lf.setType(typ);
			lf.setLogin_name(nm);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
	           if(ps != null){ps.close();}
	           if(rs != null){ rs.close();}
	           if(ps22 != null){ps22.close();}
	           if(rs22 != null){ rs22.close();}
	           if(ps22 != null){ps22.close();}
	           if(rs22 != null){ rs22.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLPmtGroupEditDAO.Connection.close "+e);
			  }
		}	
		return lf ; 
	}	
	
	public int PmtGroupUpdate(Connection con,LoginFormBean lfb)
	{
		String tblnm=null;
		PreparedStatement ps1 =null;
		PreparedStatement ps2 =null;
		ResultSet rs2 =null;
		PreparedStatement ps3 =null;
		int i=0;
		try {
			tblnm="pmt_group";
////////////////////////////////////// Set Status "N" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update "+tblnm+" set status=? where user_id=? ");
			ps1.setString(1, "N");
			ps1.setInt(2, lfb.getId());
			i=ps1.executeUpdate();
			ps1.close();

///////////////////////////////// select query to find record for selected user ////////////////////////////////////////				
			ps2 = con.prepareStatement("select gp_code from "+tblnm+" where user_id=? and gp_code=? ");
			
///////////////////////////////// insert query to insert record for selected user ////////////////////////////////////////				
			ps3 = con.prepareStatement("insert into "+tblnm+" values (?,?,?,?)" );
	
///////////////////////////////// update query to update status to "Y" for selected user ////////////////////////////////////////				
			ps1 = con.prepareStatement("update "+tblnm+" set status=? where user_id=? and gp_code=? ");

			String depo[]= lfb.getCheckbox2();
			
			for (int k=0; k<depo.length; k++)
			{
				ps2.setInt(1,lfb.getId());
				ps2.setInt(2,Integer.parseInt(depo[k]));
				
				rs2 = ps2.executeQuery();
				if (rs2.next())
				{
					ps1.setString(1,"Y");
					ps1.setInt(2,lfb.getId());
					ps1.setInt(3,Integer.parseInt(depo[k]));
					i=ps1.executeUpdate();
				}
				else
				{
					ps3.setInt(1,lfb.getId());
					ps3.setInt(2,Integer.parseInt(depo[k]));
					ps3.setString(3,lfb.getD_type());
					ps3.setString(4,"Y");
					i = ps3.executeUpdate();
				}
				rs2.close();
			}
			ps1.close();
			ps2.close();
			ps3.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			try 
			{
		        if(ps1 != null){ps1.close();}
		        if(ps3 != null){ps3.close();}
		        if(ps2 != null){ps2.close();}		        
		        if(rs2 != null){rs2.close();}
		        if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLPmtGroupUpdateDAO.Connection.close "+e);
			  }
		}	
		return i ; 
	}	
	

	
	
}