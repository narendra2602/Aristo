package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.UploadFormBean;
import com.aristo.valueobject.UserFormBean;

public class SQLLoginDAO implements LoginDAO {

/////////////////////////////User Authenticate coding//////////////////////////////
	public LoginFormBean authenticateUser (LoginFormBean ldao, Connection con)
	{
		LoginFormBean lf = null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs=null;
		String ldate=null;
		String ltime=null;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm a");
			ldate=sdf.format(cal.getTime());
			ltime=sdf1.format(cal.getTime());
			
			ps1 = con.prepareStatement("update login set last_ldate=?,last_ltime=? where login_name=? and password=? ");
			ps = con.prepareStatement("Select id,login_name,type,access_t,opt,code,d_type,branch_name,title,f_name,last_ldate,last_ltime from login where login_name=? and password=? and status=? ");
			ps.setString(1,ldao.getLogin_name());
			ps.setString(2,ldao.getPassword());
			ps.setString(3,"Yes");
			rs = ps.executeQuery();   
			
			if (rs.next())
			{
              lf = new LoginFormBean();
              lf.setId(rs.getInt(1));
              lf.setLogin_name(rs.getString(2));
              lf.setType(rs.getString(3));
		  	  lf.setAccess_t(rs.getString(4));
              lf.setOpt(rs.getString(5));
		  	  lf.setCode(rs.getInt(6));
		  	  lf.setDcode(rs.getInt(6));
		  	  lf.setD_type(rs.getString(7));
		  	  lf.setBranch_name(rs.getString(8));
		  	  lf.setF_name(rs.getString(9)+ " " +rs.getString(10));
		  	  lf.setLast_ldate(rs.getString(11));
		  	  lf.setLast_ltime(rs.getString(12));
		  	 
		  	
			}
		  	  ps1.setString(1,ldate);
		  	  ps1.setString(2,ltime);
 			  ps1.setString(3,ldao.getLogin_name());
			  ps1.setString(4,ldao.getPassword());
			  ps1.executeUpdate();
			
			rs.close();
			ps.close();
			ps1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try {
		           if(rs != null){ rs.close();}
		           if(ps != null){ps.close();}
		           if(ps1 != null){ps1.close();}		           
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}
///////////////// User Authenticate coding end here /////////////////////
	
	
//////////////////////////////////// New User //////////////////////////////////////////////////////
	
	public int newUser (LoginFormBean lfb, Connection con)
	{
		int i=0;
		int uid=0;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;		
		PreparedStatement ps2=null;		
		PreparedStatement ps3=null;		
		ResultSet rst=null; 
		HashMap divMap= new HashMap();
		divMap.put(1, "A");
		divMap.put(2, "T");
		divMap.put(3, "G");
		divMap.put(9, "D");
		divMap.put(10, "M");
		
		try {
			String query ="insert into login (login_name,password,c_password,comp_code,type,title,f_name,l_name,desig,department,location,access_t,status,d_type,created_date,email) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
		    ps.setString(1,lfb.getLogin_name());
            ps.setString(2,lfb.getPassword());
            ps.setString(3,lfb.getC_password());
            ps.setString(4,"AP");
            ps.setString(5,lfb.getType());
            ps.setString(6,lfb.getTitle());
            ps.setString(7,lfb.getF_name());
            ps.setString(8,lfb.getL_name());
            ps.setString(9,lfb.getDesig());
            ps.setString(10,lfb.getDepartment());
            ps.setString(11,lfb.getLocation());
            ps.setString(12,lfb.getAccess_t());
            ps.setString(13,lfb.getStatus());
            if(lfb.getAccess_t().equals("MF"))
               	ps.setString(14,"A");
            if(lfb.getAccess_t().equals("TF"))
               	ps.setString(14,"T");
            if(lfb.getAccess_t().equals("Genetica"))
               	ps.setString(14,"G");
            if(lfb.getAccess_t().equals("COMKT"))
               	ps.setString(14,"D");
            if(lfb.getAccess_t().equals("MF2"))
               	ps.setString(14,"M");
            if(lfb.getAccess_t().equals("MF3"))
               	ps.setString(14,"B");
            if(lfb.getAccess_t().equals("MF4"))
               	ps.setString(14,"F");
            if(lfb.getAccess_t().equals("T1"))
               	ps.setString(14,"N");
            if(lfb.getAccess_t().equals("T2"))
               	ps.setString(14,"O");
            if(lfb.getAccess_t().equals("T3"))
               	ps.setString(14,"P");
            if(lfb.getAccess_t().equals("All")) 
               	ps.setString(14,null);
            java.util.Date today = new Date();
            long tm = today.getTime();
            java.sql.Date dt = new java.sql.Date(tm);
            ps.setDate(15, dt);
            ps.setString(16, lfb.getEmail());
			i=ps.executeUpdate();
			ps.close();
			
			String query1 = "Select id from login where login_name=? and status=?";
			ps1 = con.prepareStatement(query1);
			ps1.setString(1,lfb.getLogin_name());
			ps1.setString(2,lfb.getStatus());
			
			rst = ps1.executeQuery();
			if (rst.next())
			{
			uid= rst.getInt(1);
			}
			rst.close();
			ps1.close();
			
			if ((!lfb.getType().equals("Central"))&&(!lfb.getType().equals("Factory")))
			{
			String query2 ="insert into user_branch08 (user_id,depo_code,Status) values (?,?,?)";
			ps2 = con.prepareStatement(query2);
			if (uid!=0)
			{
				for(int j=0; j<lfb.getCheckbox1().length;j++)
				{
				ps2.setInt(1,uid);
				ps2.setInt(2,Integer.parseInt(lfb.getCheckbox1()[j]));
				ps2.setString(3,"Y");
				i=ps2.executeUpdate();
				}
			}
			
			ps2.close();
			String tp="A";
			int udiv=1;
            if(lfb.getAccess_t().equalsIgnoreCase("All"))
            {
            	tp="ALL";
            	udiv=0;
            }

			String query3 ="insert into userdiv  values (?,?,?,?)";
			ps3 = con.prepareStatement(query3);
			if (uid!=0)
			{
				if(udiv==0)
				{
					for (int j=1;j<=10;j++)
					{
						tp=(String) divMap.get(j);
						System.out.println("value of tp "+tp);
						if(tp!=null)
						{
							System.out.println("value of tp andar ayya "+tp);
							ps3.setInt(1,uid);
							ps3.setInt(2,j);
							ps3.setString(3,tp);
							ps3.setString(4,"Y");
							i=ps3.executeUpdate();
							
						}
					}
				}
				
			}
			
			ps3.close();

			}
			
			System.out.println(i);
		} catch (SQLException e) {
			System.out.println("-------------Exception in SQLLoginDAO.add "+e);
			i=-1;
		}
		finally {
			try {
		           if(rst != null){ rst.close();}
		           if(ps != null){ps.close();}
		           if(ps1 != null){ps1.close();}
		           if(ps2 != null){ps2.close();}		           
		           if(ps3 != null){ps3.close();}		           
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		
		return uid;
	}	
	
///////////////////////////////New User coding end here ////////////////////////////////////	
	
////////////////////////////////////Add User Rights Start here //////////////////////////////
	
	public int addUserRights (LoginFormBean lfb, Connection con)
	{
		int i=0;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;		
		ResultSet rst=null; 
		
		String tblnm=null;
		String tblnm1=null;
		
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
			
			String query ="insert into "+tblnm+" (user_id,tab_id,repo_id,status) values (?,?,?,?)";
			ps = con.prepareStatement(query);
			
			String query1 = "Select tab_id,repo_id from "+tblnm1+" where repo_id=?";
			ps1 = con.prepareStatement(query1);
			
		if (lfb.getId()!=0)
		{
			for(int j=0; j<lfb.getCheckbox2().length;j++)
			{
			    ps1.setString(1,lfb.getCheckbox2()[j]);
			    rst = ps1.executeQuery();
			    if (rst.next())
			    {
				    ps.setInt(1,lfb.getId());
		            ps.setInt(2,rst.getInt(1));
		            ps.setString(3,rst.getString(2));
		            ps.setString(4,"Y");
		            i = ps.executeUpdate();
			    }
			}
		}
			
			rst.close();
			ps1.close();
			ps.close();
			  			
		} catch (SQLException e) {
			System.out.println("-------------Exception in SQLLoginDAO.addUserRights "+e);
			i=-1;
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps1 != null){ps1.close();}
	           if(ps != null){ps.close();}	           
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		
		return i;
	}	
	
////////////////////////////////////Add User Rights end here //////////////////////////////	
	
////////////////HQ list ke liye he ye/////////////////
	
	public List getAllDepo(Connection con) { 
		 
		LoginFormBean lfb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try { 
		    String typ="A";
            String tblnm=null;
            if (typ.equals("A"))
            	tblnm="a_hq_master08";
            if (typ.equals("T"))
            	tblnm="t_hq_master08";
            if (typ.equals("G"))
            	tblnm="g_hq_master08";
			

			String query = "select ter_code,ter_name from " + tblnm;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				lfb = new LoginFormBean();
				lfb.setCode(rst.getInt(1));
				lfb.setL_name(rst.getString(2));
				data.add(lfb);
			}
			ps.close();
			rst.close();
							
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllDepo " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}

////////////// HQ list coding end here /////////////////
	
	
//////////////// User list ke liye he ye/////////////////
		
	public List getAlluser(Connection con,String sr) { 
		 
		UserFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		List<UserFormBean> data = new ArrayList<UserFormBean>();
		try { 
            String tblnm=null;
         	tblnm="login";
         	String query=null; 
         	
         	 if (sr==null)
	         {	
         	  query = "select * from " + tblnm + " where type<>?" ;
	         }
	         else
	         {
	           query = "select * from " + tblnm + " where type<>? and login_name like '"+sr+"%' " ;
	         }         	

			ps = con.prepareStatement(query);
			ps.setString(1, "Admin");
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				afb = new UserFormBean();
				afb.setId(rst.getInt(1));
				afb.setLogin_name(rst.getString(2));
				afb.setPassword(rst.getString(3));
				afb.setF_name(rst.getString(8));
				afb.setL_name(rst.getString(9));
				afb.setDepartment(rst.getString(11));
				afb.setLocation(rst.getString(12));
				afb.setStatus(rst.getString(14));
				afb.setLink1("Edit");
					
				data.add(afb);
			}
		
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllUser " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}
	
//////////////// User list coding end here /////////////////


///////////////// Change password /////////////////////////
	
	public int Changepass (LoginFormBean ldao, Connection con)
	{
		int i=0;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;		
		ResultSet rs=null; 

		try {
			
			String npass = ldao.getNew_pass();
			String cpass = ldao.getC_password();
						
			if (npass.equals(cpass))
			{	
			ps = con.prepareStatement("Select * from login where login_name=? and password=? " );
			ps.setString(1, ldao.getLogin_name());
			ps.setString(2, ldao.getPassword());
			rs = ps.executeQuery();   
					
			if (rs.next())
			{
				ps1 = con.prepareStatement("update login set password=? where login_name=? " );
                ps1.setString(1, npass);
                ps1.setString(2, ldao.getLogin_name());
				i=ps1.executeUpdate();
				ps1.close();
			}
			rs.close();
			ps.close();
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}

		finally {
			try
			{
	           if(rs != null){ rs.close();}
	           if(ps != null){ps.close();}
	           if(ps1 != null){ps1.close();}		           
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return i;
		
	}

///////////Change password coding end here ///////////////////	

	
///////////////// Login Name Availability check coding /////////////////////////
	
	public int LoginCheck (LoginFormBean ldao, Connection con)
	{
		int i=0;
		PreparedStatement ps=null;
		ResultSet rs=null; 
		try {
			ps = con.prepareStatement("Select * from login where login_name=?" );
			ps.setString(1, ldao.getLogin_name());
			rs = ps.executeQuery();   
					
			if (rs.next())
			{
			  i=1;
			}
			  
			rs.close();
			ps.close();
			
  		} catch (SQLException e) {
	
			e.printStackTrace();
		}

		finally {
			try 
			{
	           if(rs != null){ rs.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	  		
  		
		return i;
		
	}

///////////Login Name Availability check coding end here ///////////////////		
	
////////////////Upload List Status list ke liye he ye/////////////////
	
	public List getAllUpload(Connection con, int depo, String tp,int lid,String s) { 
		 
		UploadFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 

		List<UploadFormBean> data = new ArrayList<UploadFormBean>();
		try { 

            String tblnm=null;
            String tblnm1=null;
            String query=null;
            
         	tblnm="upload";
         	tblnm1="user_branch08";
         	
         	if (depo==1)
         	{
         	query = " select * from "+tblnm+" where depo_code=? and typ=? and u_typ=? " ;
         	}
         	else
         	{
         	query = "select u.* from "+tblnm+" as u inner join "+tblnm1+" as ub on " +
         	"u.depo_code=ub.depo_code where ub.user_id=? and ub.status=? and substr(u.typ,1,1)=? " +
         	" and u.u_typ=? order by u.depo_code " ;
		    }   
         	
			ps = con.prepareStatement(query);
			if (depo==1)
			{
			ps.setInt(1,depo);
			ps.setString(2,tp);
			ps.setString(3,s);						
			}
			else
			{
			ps.setInt(1, lid);
			ps.setString(2, "Y");
			ps.setString(3, tp);
			ps.setString(4, s);
			}
			rst = ps.executeQuery();
		
			while (rst.next())
			{
				afb = new UploadFormBean();
				afb.setFile_name(rst.getString(2));
				afb.setFile_path(rst.getString(3));
				afb.setUpload(rst.getString(4));
				afb.setDepo_name(rst.getString(5));
				afb.setU_date(rst.getString(7));
				afb.setU_time(rst.getString(8));
				data.add(afb);			
			}
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllUpload " + e);
		}

		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}
	
//////////////// Upload Status list coding end here /////////////////	
	
	

////////////////Upload Status list for Admin ke liye he ye/////////////////
	
	public List getAllUploadAdmin(Connection con, String tp, int lid,String s) { 
		 
		UploadFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		
		List<UploadFormBean> data = new ArrayList<UploadFormBean>();
		try { 
            String tblnm=null;

            String query=null;
           
         	tblnm="upload";
         	query = " select * from "+tblnm+" where substr(typ,1,1)=? and u_typ=? " ;
			ps = con.prepareStatement(query);
			ps.setString(1,tp);
			ps.setString(2,s);			
			rst = ps.executeQuery();
			
			while (rst.next()) 
			{
				afb = new UploadFormBean();
				afb.setFile_name(rst.getString(2));
				afb.setFile_path(rst.getString(3));
				afb.setUpload(rst.getString(4));
				afb.setDepo_name(rst.getString(5));
				afb.setU_date(rst.getString(7));
				afb.setU_time(rst.getString(8));
				data.add(afb);
			}
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllUploadAdmin " + e);
		}
		
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}
	
//////////////// Upload Status list coding end here /////////////////	
	

/////////////// User list ke liye he ye/////////////////
	
	public List getUserRightList(Connection con,String sr) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try { 
            String tblnm=null;
            String query=null;
         	tblnm="login";
         	
         	 if (sr==null)
	         {	
     		   query = "select * from " + tblnm + " where type<>?" ;
	         }
	         else
	         {
	           query = "select * from " + tblnm + " where type<>? and login_name like '"+sr+"%' " ;
	         }         	
         	
         	ps = con.prepareStatement(query);
			ps.setString(1, "Admin");
			rst = ps.executeQuery();
			while (rst.next())
			{
				afb = new LoginFormBean();
				afb.setId(rst.getInt(1));
				afb.setLogin_name(rst.getString(2));
				afb.setF_name(rst.getString(8));
				afb.setL_name(rst.getString(9));
				afb.setLink1("Edit");
				data.add(afb);
			}
		
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getUserRightList " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}
	
//////////////// User list coding end here /////////////////
	
	public List getAllYear(Connection con) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2=null; 

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            String query2 = "Select distinct(mkt_year),myear from monthfl order by mkt_year desc ";
	        ps2 = con.prepareStatement(query2);
	        rst2 = ps2.executeQuery();
	            while (rst2.next())
				{
					  rfb = new LoginFormBean();
		        	  rfb.setYval(rst2.getInt(1));
		        	  rfb.setYname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllYear " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
	

	public List getAllGroup(Connection con, String tp) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            String tblnm=null;
	        tblnm=(tp+"_groupsales08").toLowerCase();
  	           
            String query2 = "Select distinct(gp_code),gp_name from "+tblnm+" order by gp_name";
	        ps2 = con.prepareStatement(query2);
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
				  rfb = new LoginFormBean();
	        	  rfb.setDcode(rst2.getInt(1));
	        	  rfb.setDname(rst2.getString(2)); 
                  data.add(rfb); 				
			 } 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllGroup " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}
		return data;
	}
	
	public int addPmtGrp (LoginFormBean lfb, Connection con)
	{
		int i=0;
		PreparedStatement ps=null;
		String tblnm=null;
		int gcode = 0;
		
		try {
			tblnm="pmt_group"; 
			String query ="insert into "+tblnm+" (user_id,gp_code,access_t,status) values (?,?,?,?)";
			ps = con.prepareStatement(query);
			
		if (lfb.getId()!=0)
		{
			for(int j=0; j<lfb.getCheckbox2().length;j++)
			{
			    gcode = Integer.parseInt(lfb.getCheckbox2()[j]);
			    ps.setInt(1,lfb.getId());
	            ps.setInt(2,gcode);
	            ps.setString(3,lfb.getAccess_t());
	            ps.setString(4,"Y");
	            i = ps.executeUpdate();
			}
		}
			
			ps.close();
			  			
		} catch (SQLException e) {

			System.out.println("-------------Exception in SQLLoginDAO.addPmtGp "+e);
			i=-1;
		}
		finally {
			try 
			{
	           if(ps != null){ps.close();}	           
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		
		return i;
	}		
	
	public List getPmtRightList(Connection con,String sr) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try { 
            String tblnm=null;
            String query=null;
         	tblnm="login";
         	if (sr==null)
	         {	
        	  query = "select * from " + tblnm + " where type=?" ;
	         }
	         else
	         {
	           query = "select * from " + tblnm + " where type=? and login_name like '"+sr+"%' " ;
	         }   
		
			ps = con.prepareStatement(query);
			ps.setString(1, "PMT");
			rst = ps.executeQuery();
			while (rst.next())
			{
				afb = new LoginFormBean();
				afb.setId(rst.getInt(1));
				afb.setLogin_name(rst.getString(2));
				afb.setF_name(rst.getString(8));
				afb.setL_name(rst.getString(9));
				afb.setLink1("Edit");
				data.add(afb);
			}
		
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getPMTRightList " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}	
	
	public String getType(Connection con,int id) { 
		 
		PreparedStatement ps=null;
		ResultSet rst=null;
		String tp=null;
		try { 
            String tblnm=null;
         	tblnm="login";
			String query = "select d_type from " + tblnm + " where id="+id ;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			if (rst.next())
			{
				tp=rst.getString(1);
			}
		
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getType " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		
		return tp;
	}	
	
	
	
	
	public List getAllDTer(Connection con, String tp,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            String tblnm=null;
	        tblnm=(tp+"_hq_master08").toLowerCase();

            String query2 = "Select distinct(ter_code),ter_name,depo_code from "+tblnm+" where depo_code in " +
            " (select depo_code from user_branch08 where user_id="+uid+" and status='Y') order by depo_code,ter_name";
	        ps2 = con.prepareStatement(query2);
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
				  rfb = new LoginFormBean();
	        	  rfb.setComp_code(rst2.getString(3)+rst2.getString(1));
	        	  rfb.setDname(rst2.getString(2)); 
                  data.add(rfb); 				
			 } 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getAllDepoTer " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}
		return data;
	}

	public int addTerUser (LoginFormBean lfb, Connection con)
	{
		int i=0;
		PreparedStatement ps=null;
		String tblnm=null;
		int ter_code=0;
		int depo_code=0;
		try {
			tblnm="user_ter"; 
			String query ="insert into "+tblnm+" (user_id,depo_code,ter_code,access_t,status) values (?,?,?,?,?)";
			ps = con.prepareStatement(query);
			
		if (lfb.getId()!=0)
		{
			for(int j=0; j<lfb.getCheckbox2().length;j++)
			{
				depo_code=Integer.parseInt(lfb.getCheckbox2()[j].substring(0,2));
				ter_code=Integer.parseInt(lfb.getCheckbox2()[j].substring(2));
			    ps.setInt(1,lfb.getId());
			    ps.setInt(2,depo_code);
			    ps.setInt(3,ter_code);
	            ps.setString(4,lfb.getAccess_t());
	            ps.setString(5,"Y");
	            i = ps.executeUpdate();
			}
		}
			
			ps.close();
			  			
		} catch (SQLException e) {

			System.out.println("-------------Exception in SQLLoginDAO.addPmtGp "+e);
			i=-1;
		}
		finally {
			try 
			{
	           if(ps != null){ps.close();}	           
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		
		return i;
	}			
 
	public int AddEntry (LoginFormBean lfb, Connection con,int depo)
	{
		int i=0;
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		Date javadt = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateformat=null;
        simpleDateformat = new SimpleDateFormat("MMM");
        int mnth_code=0;
        
	    int div=1;
	    if(lfb.getAccess_t().equals("T"))
	    	div=2;
	    else if(lfb.getAccess_t().equals("G"))
	    	div=3;
	    else if(lfb.getAccess_t().equals("M"))
	    	div=10;
	    else if(lfb.getAccess_t().equals("B"))
	    	div=20;
	    else if(lfb.getAccess_t().equals("F"))
	    	div=30;
	    else if(lfb.getAccess_t().equals("N"))
	    	div=12;
	    else if(lfb.getAccess_t().equals("O"))
	    	div=13;
	    else if(lfb.getAccess_t().equals("P"))
	    	div=16;
	    
		try {
			String query ="insert into daily_entry (div_code,depo_code,division,ddate,budget_br,sales_trade," +
			" collection,remit,outstand,status,ent_date,ent_time,bill_mnth,collection_cumm,remit_cumm,name,ach,surdef,cn100,last_month,last_year,budget_per,sales_today,mnth_code,mkt_year) " +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			String month="select mnth_code from monthfl where mkt_year=? and mnth_abbr=? ";
			ps1= con.prepareStatement(month);
			ps1.setInt(1, lfb.getEyear());
            ps1.setString(2,simpleDateformat.format(lfb.getEdate().getTime()));
			rs1=ps1.executeQuery();
			if(rs1.next())
				mnth_code=rs1.getInt(1);
			
			rs1.close();
			ps1.close();
			
			
			ps = con.prepareStatement(query);
			ps.setInt(1, div);
		    ps.setInt(2,lfb.getCode());
		    ps.setString(3,lfb.getAccess_t());
		    
            javadt = lfb.getEdate();
			ps.setDate(4,new java.sql.Date (javadt.getTime()));

            ps.setDouble(5,lfb.getSales_bud());
            ps.setDouble(6,lfb.getTrd_sale());
            ps.setDouble(7,lfb.getCollc());
            ps.setDouble(8,lfb.getRemit());
            ps.setDouble(9,lfb.getOutstnd());
            ps.setString(10,lfb.getStatus());
            
            java.util.Date today = new Date();
            long tm = today.getTime();
            java.sql.Date dt = new java.sql.Date(tm);
            ps.setDate(11,dt);
            
            Time t = new Time(cal.getTimeInMillis());
            ps.setTime(12,t);
            
            ps.setString(13,simpleDateformat.format(javadt));
            ps.setDouble(14,lfb.getCollcumm());
            ps.setDouble(15,lfb.getRemitcumm());
            ps.setString(16,lfb.getBranch_name());
            ps.setDouble(17,lfb.getAch());
            ps.setDouble(18,lfb.getSurdef());
            ps.setDouble(19,lfb.getCn100());
            ps.setDouble(20,lfb.getLmsale());
            ps.setDouble(21,lfb.getLysale());
            ps.setDouble(22,lfb.getBud_per());
            ps.setDouble(23,lfb.getSale_today());
            ps.setInt(24, mnth_code);
            ps.setInt(25, lfb.getEyear());


            
            i=ps.executeUpdate();
			ps.close();
			
			System.out.println(i);
		} catch (SQLException e) {
			System.out.println("-------------Exception in SQLLoginDAO.AddEntry "+e);
			i=-1;
		}
		finally {
			try {
		           if(ps != null){ps.close();}
		           if(rs1 != null){rs1.close();}
		           if(ps1 != null){ps1.close();}
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		return i;
	}	
	
	public List DailyList(Connection con,Date edate,String divi,int id,int opt,List divList,int mon) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		
		Date edt;
		NumberFormat df=new DecimalFormat("0.00");
		int division[] = null;
		String divname[]= null;
		String query=null;
		String query1=null;
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		  int div=1;
	      divname=new String[]{"MF"};
	      division = new int[] {1};
		    if(divi.equals("T"))
		    {
		    	div=2;
		    	divname=new String[]{"TF"};
			      division = new int[] {2};

		    }
		    else if(divi.equals("G"))
		    {
		    	div=3;
	            divname=new String[]{"Genetica"};
   	  	       division = new int[] {3};

		    }
		    else if(divi.equals("M"))
		    {
		    	div=10;
	            divname=new String[]{"MF2"};
	  	        division = new int[] {10};

		    }
		    else if(divi.equals("B"))
		    {
		    	div=20;
	            divname=new String[]{"MF3"};
	  	        division = new int[] {20};
		    }
		    else if(divi.equals("F"))
		    {
		    	div=30;
	            divname=new String[]{"MF4"};
	  	        division = new int[] {30};
		    }
		    else if(divi.equals("N"))
		    {
		    	div=12;
	            divname=new String[]{"T1"};
	  	        division = new int[] {12};
		    }
		    else if(divi.equals("O"))
		    {
		    	div=13;
	            divname=new String[]{"T2"};
	  	        division = new int[] {13};
		    }
		    else if(divi.equals("P"))
		    {
		    	div=16;
	            divname=new String[]{"T3"};
	  	        division = new int[] {16};
		    }
		    else if(divi.equals("Z"))
		    {
		    	div=0;
		    	LoginFormBean lb=null;
		    	int size=divList.size();
		    	divname=new String[size];
		    	division = new int[size];

		    	for(int i=0; i<size;i++)
		    	{
		    		lb = (LoginFormBean) divList.get(i);
		    		division[i]=lb.getDcode();
		    		divname[i]=lb.getDname();
		    		
		    	}
		    }
		   
		    
		try {
			edt =new java.sql.Date(edate.getTime()); 
/*			if (divi.length==1)
			{

			query="select B.TER_NAME,sum(DET.budget_br),sum(det.sales_trade),sum(det.collection),sum(det.outstand)," +
			"det.status,det.ent_date,det.ent_time from (select d.* from daily_entry d," +
			"(select d.depo_code,d.division,d.ddate dd,max(d.ent_time) tt from daily_entry d," +
			"(select depo_code,division,max(ddate) dt from daily_entry,monthfl where '"+edt+"'" +
			" between FR_DATE and TO_DATE AND DDATE <= '"+edt+"' group by depo_code,division) t" +
			" where d.depo_code = t.depo_code  and d.division = t.division and d.ddate = t.dt " +
			"group by d.depo_code,d.division,d.ddate) m where d.depo_code =m.depo_code and d.division = m.division" +
			" and d.ddate = m.dd and d.ent_time = m.tt) det,(Select d.depo_code,t.ter_name from user_branch08 as d, " +
			"a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B " +
			"where DIVISION ='"+divi[0]+"' " +
			" AND DET.DEPO_CODE = B.DEPO_CODE GROUP BY DET.DEPO_CODE,B.TER_NAME";

			}
			
			
			if (divi.length==2)
			{

			query="select B.TER_NAME,sum(DET.budget_br),sum(det.sales_trade),sum(det.collection),sum(det.outstand)," +
			"det.status,det.ent_date,det.ent_time from (select d.* from daily_entry d," +
			"(select d.depo_code,d.division,d.ddate dd,max(d.ent_time) tt from daily_entry d," +
			"(select depo_code,division,max(ddate) dt from daily_entry,monthfl where '"+edt+"'" +
			" between FR_DATE and TO_DATE AND DDATE <= '"+edt+"' group by depo_code,division) t" +
			" where d.depo_code = t.depo_code  and d.division = t.division and d.ddate = t.dt " +
			"group by d.depo_code,d.division,d.ddate) m where d.depo_code =m.depo_code and d.division = m.division" +
			" and d.ddate = m.dd and d.ent_time = m.tt) det,(Select d.depo_code,t.ter_name from user_branch08 as d," +
			" a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B " +
			"where DIVISION in ('"+divi[0]+"','"+divi[1]+"')" +
			" AND DET.DEPO_CODE = B.DEPO_CODE GROUP BY DET.DEPO_CODE,B.TER_NAME";
			
			}

			if (divi.length==3)
			{

			query="select B.TER_NAME,sum(DET.budget_br),sum(det.sales_trade),sum(det.collection),sum(det.outstand)," +
			"det.status,det.ent_date,det.ent_time from (select d.* from daily_entry d," +
			"(select d.depo_code,d.division,d.ddate dd,max(d.ent_time) tt from daily_entry d," +
			"(select depo_code,division,max(ddate) dt from daily_entry,monthfl where '"+edt+"'" +
			" between FR_DATE and TO_DATE AND DDATE <= '"+edt+"' group by depo_code,division) t" +
			" where d.depo_code = t.depo_code  and d.division = t.division and d.ddate = t.dt " +
			"group by d.depo_code,d.division,d.ddate) m where d.depo_code =m.depo_code and d.division = m.division" +
			" and d.ddate = m.dd and d.ent_time = m.tt) det,(Select d.depo_code,t.ter_name from user_branch08 as d," +
			" a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B " +
			"where DIVISION in ('"+divi[0]+"','"+divi[1]+"','"+divi[2]+"')" +
			" AND DET.DEPO_CODE = B.DEPO_CODE GROUP BY DET.DEPO_CODE,B.TER_NAME";
			}
*/			
			if(div==0)
			{
				query="select a.*,c.div_name from (select e.br_name,d.budget_br,d.sales_trade,d.cn100,d.ach,d.surdef,d.last_month,d.last_year,d.collection,d.collection_cumm,d.remit," +
				"d.remit_cumm,d.outstand,d.depo_code,d.div_code,d.budget_per,d.sales_today,e.mgr_code,e.mgr_name,d.ent_date,d.ent_time,d.status,e.ho_seq from daily_entry d ,daily_entry_master e, "+
				"(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B "+ 
				"where d.div_code=? and d.ddate<=? and d.amd_no in (select max(amd_no) from daily_entry where div_code=? and ddate<=? and mnth_code in (select mnth_code from monthfl where mnth_no=? and ? between fr_Date and to_date) group by depo_code,div_code,name) "+
				"and d.depo_Code=b.depo_code and e.div_code=? and d.depo_code=e.depo_code and d.name=e.br_name) a,divmast c where a.div_code=c.div_Code order by a.ho_seq ";
				
				query1="select e.br_name,sum(d.budget_br),sum(d.sales_trade),sum(d.cn100),sum(d.ach),sum(d.surdef),sum(d.last_month),sum(d.last_year),sum(d.collection),sum(d.collection_cumm),sum(d.remit), "+
				"sum(d.remit_cumm),sum(d.outstand),d.depo_code,d.div_code,d.budget_per,sum(d.sales_today),e.mgr_code,e.mgr_name,d.ent_date,d.ent_time,d.status,e.ho_seq from daily_entry d ,daily_entry_master e, "+ 
				"(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B "+
				"where  d.ddate<=? and d.amd_no in (select max(amd_no) from daily_entry where  ddate<=? and mnth_code in (select mnth_code from monthfl where mnth_no=? and ? between fr_Date and to_date)  group by depo_code,div_code,name) "+ 
				"and d.depo_Code=b.depo_code and e.div_code=d.div_code and d.depo_code=e.depo_code and d.name=e.br_name group by e.br_name order by e.ho_seq  ";	

			}
			else
			{
				query="select a.*,c.div_name from (select e.br_name,d.budget_br,d.sales_trade,d.cn100,d.ach,d.surdef,d.last_month,d.last_year,d.collection,d.collection_cumm,d.remit," +
				"d.remit_cumm,d.outstand,d.depo_code,d.div_code,d.budget_per,d.sales_today,e.mgr_code,e.mgr_name,d.ent_date,d.ent_time,d.status,e.ho_seq from daily_entry d ,daily_entry_master e, "+
				"(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B "+ 
				"where d.div_code=? and d.ddate<=? and d.amd_no in (select max(amd_no) from daily_entry where div_code=? and ddate<=? and mnth_code in (select mnth_code from monthfl where mnth_no=? and ? between fr_Date and to_date) group by depo_code,div_code,name) "+
				"and d.depo_Code=b.depo_code and e.div_code=? and d.depo_code=e.depo_code and d.name=e.br_name) a,divmast c where a.div_code=c.div_Code order by a.mgr_code,a.depo_Code,a.div_code ";
			}

			 
			
			/*select b.ter_name,d.budget_br,d.sales_trade,d.cn100,d.ach,d.surdef,d.last_month,d.last_year,d.collection,d.collection_cumm,d.remit, 
			d.remit_cumm,d.outstand,d.depo_code,div_code from daily_entry d , 
			(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id=269 and d.status='Y') B  
			where   d.ddate='2019-05-10' and d.amd_no in (select max(amd_no) from daily_entry where   ddate='2019-05-10' group by depo_code,div_code) 
			and d.depo_Code=b.depo_code ;*/	
			
			
			
			ps = con.prepareStatement(query);
			
			
			double budget=0.00;
			double cn100=0.00;
			double sale=0.00;
			double sale_today=0.00;
			double ach=0.00;
			double surdef=0.00;
			double lmsale=0.00;
			double lysale=0.00;
			double coll=0.00;
			double collcumm=0.00;
			double remit=0.00;
			double cummremit=0.00;
			double out=0.00;

			double mbudget=0.00;
			double mcn100=0.00;
			double msale=0.00;
			double msale_today=0.00;
			double mach=0.00;
			double msurdef=0.00;
			double mlmsale=0.00;
			double mlysale=0.00;
			double mcoll=0.00;
			double mcollcumm=0.00;
			double mremit=0.00;
			double mcummremit=0.00;
			double mout=0.00;

			
			double gbudget=0.00;
			double gcn100=0.00;
			double gsale=0.00;
			double gsale_today=0.00;
			double gach=0.00;
			double gsurdef=0.00;
			double glmsale=0.00;
			double glysale=0.00;
			double gcoll=0.00;
			double gcollcumm=0.00;
			double gremit=0.00;
			double gcummremit=0.00;
			double gout=0.00;
			int count=0;
			boolean first=true;
			int mcode=0 ;
			String mname=null;
			
			for(int j=0;j<division.length;j++)
			{
				
				budget=0.00;
				cn100=0.00;
				sale=0.00;
				sale_today=0.00;
				ach=0.00;
				surdef=0.00;
				lmsale=0.00;
				lysale=0.00;
				coll=0.00;
				collcumm=0.00;
				remit=0.00;
				cummremit=0.00;
				out=0.00;
				count=0;
				first=true;
				ps.setInt(1,division[j]);
				ps.setDate(2,new java.sql.Date(edate.getTime()));
				ps.setInt(3,division[j]);
				ps.setDate(4,new java.sql.Date(edate.getTime()));
				ps.setInt(5,mon);
				ps.setDate(6,new java.sql.Date(edate.getTime()));
				ps.setInt(7,division[j]);
				

				rst = ps.executeQuery();
				while (rst.next())
				{
					
					if(first)
					{
						mcode=rst.getInt(18);
						//mname=rst.getString(19);
						mname="";
						first=false;
					} 
					
					if(mcode!=rst.getInt(18) && div!=0)
					{
						
						afb = new LoginFormBean();
						afb.setF_name(mname);
						afb.setSales_bud(mbudget);
						afb.setTrd_sale(msale);
						if (budget!=0)
							afb.setAch((((msale/mbudget)*100)));
						afb.setDef(msale-mbudget);	
						afb.setCn100(mcn100);
						afb.setLmsale(mlmsale);
						afb.setLysale(mlysale);
						afb.setCollc(mcoll);
						afb.setCollcumm(mcollcumm);
						afb.setRemit(mremit);
						afb.setRemitcumm(mcummremit);
						afb.setOutstnd(mout);
						afb.setSale_today(msale_today);
						afb.setOpt("");
						afb.setAccess_t(""); // depo_code
						afb.setId(4);
						data.add(afb);
						
						mcode=rst.getInt(18);
//						mname=rst.getString(19);
						mname="";

						mbudget=0.00;
						mcn100=0.00;
						msale=0.00;
						msale_today=0.00;
						mach=0.00;
						msurdef=0.00;
						mlmsale=0.00;
						mlysale=0.00;
						mcoll=0.00;
						mcollcumm=0.00;
						mremit=0.00;
						mcummremit=0.00;
						mout=0.00;

					}
					afb = new LoginFormBean();
					afb.setF_name(rst.getString(1)+" "+rst.getString(24));
					afb.setSales_bud(rst.getDouble(2));
					afb.setTrd_sale(rst.getDouble(3));
					if (rst.getDouble(2)!=0)
						afb.setAch((((rst.getDouble(3)/rst.getDouble(2))*100)));
					afb.setDef(rst.getDouble(3)-rst.getDouble(2));	
					afb.setCn100(rst.getDouble(4));
					afb.setLmsale(rst.getDouble(7));
					afb.setLysale(rst.getDouble(8));
					afb.setCollc(rst.getDouble(9));
					afb.setCollcumm(rst.getDouble(10));
					afb.setRemit(rst.getDouble(11));
					afb.setRemitcumm(rst.getDouble(12));
					afb.setOutstnd(rst.getDouble(13));
//					afb.setBud_per(rst.getDouble(16));
					afb.setOpt(df.format(rst.getDouble(16)));
					afb.setSale_today(rst.getDouble(17));
					afb.setEdate(rst.getDate(20));
					afb.setLast_ltime(rst.getString(21));
					afb.setStatus(rst.getString(22));
					//afb.setCode(rst.getInt(14));
					afb.setAccess_t(rst.getString(14)); // depo_code
					
					budget+=rst.getDouble(2);
					cn100+=rst.getDouble(4);
					sale+=rst.getDouble(3);
					lmsale+=rst.getDouble(7);
					lysale+=rst.getDouble(8);
					coll+=rst.getDouble(9);
					collcumm+=rst.getDouble(10);
					remit+=rst.getDouble(11);
					cummremit+=rst.getDouble(12);
					out+=rst.getDouble(13);
					sale_today+=rst.getDouble(17);


					mbudget+=rst.getDouble(2);
					mcn100+=rst.getDouble(4);
					msale+=rst.getDouble(3);
					mlmsale+=rst.getDouble(7);
					mlysale+=rst.getDouble(8);
					mcoll+=rst.getDouble(9);
					mcollcumm+=rst.getDouble(10);
					mremit+=rst.getDouble(11);
					mcummremit+=rst.getDouble(12);
					mout+=rst.getDouble(13);
					msale_today+=rst.getDouble(17);

					gbudget+=rst.getDouble(2);
					gcn100+=rst.getDouble(4);
					gsale+=rst.getDouble(3);
					glmsale+=rst.getDouble(7);
					glysale+=rst.getDouble(8);
					gcoll+=rst.getDouble(9);
					gcollcumm+=rst.getDouble(10);
					gremit+=rst.getDouble(11);
					gcummremit+=rst.getDouble(12);
					gout+=rst.getDouble(13);
					gsale_today+=rst.getDouble(17);

					count++;


					/*				if (divi.length==1)
				{
					afb.setStatus(rst.getString(6));
					afb.setEdate(rst.getDate(7));
					afb.setLast_ltime(rst.getString(8));
					html
					<display:column property="status" title="STATUS" headerClass="r" class="r"/>
					<display:column property="edate" title="DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r"/>
					<display:column property="last_ltime" title="TIME" headerClass="r" class="r"/>

				}
					 */				
					data.add(afb);
				} // end of while 
				rst.close();
				if(count>0)
				{
					// total of mgr_code
					if(div!=0)
					{
						afb = new LoginFormBean();
						afb.setF_name(mname);
						afb.setSales_bud(mbudget);
						afb.setTrd_sale(msale);
						if (budget!=0)
							afb.setAch((((msale/mbudget)*100)));
						afb.setDef(msale-mbudget);	
						afb.setCn100(mcn100);
						afb.setLmsale(mlmsale);
						afb.setLysale(mlysale);
						afb.setCollc(mcoll);
						afb.setCollcumm(mcollcumm);
						afb.setRemit(mremit);
						afb.setRemitcumm(mcummremit);
						afb.setOutstnd(mout);
						afb.setSale_today(msale_today);
						afb.setOpt("");
						afb.setAccess_t(""); // depo_code

						afb.setId(4);
						data.add(afb);
					}
					
					afb = new LoginFormBean();
					afb.setF_name("ALL INDIA "+divname[j]);
					afb.setSales_bud(budget);
					afb.setTrd_sale(sale);
					if (budget!=0)
						afb.setAch((((sale/budget)*100)));
					afb.setDef(sale-budget);	
					afb.setCn100(cn100);
					afb.setLmsale(lmsale);
					afb.setLysale(lysale);
					afb.setCollc(coll);
					afb.setCollcumm(collcumm);
					afb.setRemit(remit);
					afb.setRemitcumm(cummremit);
					afb.setOutstnd(out);
					afb.setSale_today(sale_today);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code

					afb.setId(2);
					data.add(afb);
				}

				
			} // end of for statement 
		
			if(div==0)
			{
				ps1 = con.prepareStatement(query1);
				ps1.setDate(1, new java.sql.Date(edate.getTime()));
				ps1.setDate(2, new java.sql.Date(edate.getTime()));
				ps1.setInt(3,mon);
				ps1.setDate(4, new java.sql.Date(edate.getTime()));

				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					afb = new LoginFormBean();
					afb.setF_name(rs1.getString(1));
					afb.setSales_bud(rs1.getDouble(2));
					afb.setTrd_sale(rs1.getDouble(3));
					if (rs1.getDouble(2)!=0)
						afb.setAch((((rs1.getDouble(3)/rs1.getDouble(2))*100)));
					afb.setDef(rs1.getDouble(3)-rs1.getDouble(2));	
					afb.setCn100(rs1.getDouble(4));
					afb.setLmsale(rs1.getDouble(7));
					afb.setLysale(rs1.getDouble(8));
					afb.setCollc(rs1.getDouble(9));
					afb.setCollcumm(rs1.getDouble(10));
					afb.setRemit(rs1.getDouble(11));
					afb.setRemitcumm(rs1.getDouble(12));
					afb.setOutstnd(rs1.getDouble(13));
//					afb.setBud_per(rs1.getDouble(16));
					afb.setOpt(df.format(rs1.getDouble(16)));
					afb.setSale_today(rs1.getDouble(17));
					afb.setEdate(rs1.getDate(20));
					afb.setLast_ltime(rs1.getString(21));
					afb.setStatus(rs1.getString(22));
					//afb.setCode(rs1.getInt(14));
					afb.setAccess_t(rs1.getString(14)); // depo_code
					data.add(afb);
				}
				rs1.close();
				ps1.close();
			}
			
			
			afb = new LoginFormBean();
			afb.setF_name("ALL INDIA  ");
			afb.setSales_bud(gbudget);
			afb.setTrd_sale(gsale);
			if (gbudget!=0)
				afb.setAch((((gsale/gbudget)*100)));
			afb.setDef(gsale-gbudget);	
			afb.setCn100(gcn100);
			afb.setLmsale(glmsale);
			afb.setLysale(glysale);
			afb.setCollc(gcoll);
			afb.setCollcumm(gcollcumm);
			afb.setRemit(gremit);
			afb.setRemitcumm(gcummremit);
			afb.setOutstnd(gout);
			afb.setSale_today(gsale_today);
			afb.setOpt("");
			afb.setAccess_t(""); // depo_code

			afb.setId(3);
			data.add(afb);
			
			ps.close();
			//rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.DailyList " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(rs1 != null){rs1.close();}
	           if(ps1 != null){ps1.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}		


	public List DailyListNew(Connection con,Date edate,String divi,int id,int opt,List divList,int mon) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null;
		Date edt;
		NumberFormat df=new DecimalFormat("0.00");
		int division[] = null;
		String divname[]= null;
		String query=null;
		StringBuffer divString=new StringBuffer("(1)");
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		  int div=1;
	      divname=new String[]{"MF"};
	      division = new int[] {1};
		    if(divi.equals("T"))
		    {
		    	div=2;
		    	divname=new String[]{"TF"};
			      division = new int[] {2};
			      divString=new StringBuffer("(2)");

		    }
		    else if(divi.equals("G"))
		    {
		    	div=3;
	            divname=new String[]{"Genetica"};
   	  	       division = new int[] {3};
   	  	       divString=new StringBuffer("(3)");

		    }
		    else if(divi.equals("M"))
		    {
		    	div=10;
	            divname=new String[]{"MF2"};
	  	        division = new int[] {10};
	  	      divString=new StringBuffer("(10)");
		    }
		    else if(divi.equals("B"))
		    {
		    	div=20;
	            divname=new String[]{"MF3"};
	  	        division = new int[] {20};
	  	        divString=new StringBuffer("(20)");
		    }
		    else if(divi.equals("F"))
		    {
		    	div=30;
	            divname=new String[]{"MF4"};
	  	        division = new int[] {30};
	  	        divString=new StringBuffer("(30)");
		    }
		    else if(divi.equals("N"))
		    {
		    	div=12;
	            divname=new String[]{"T1"};
	  	        division = new int[] {12};
		    }
		    else if(divi.equals("O"))
		    {
		    	div=13;
	            divname=new String[]{"T2"};
	  	        division = new int[] {13};
		    }
		    else if(divi.equals("P"))
		    {
		    	div=16;
	            divname=new String[]{"T3"};
	  	        division = new int[] {16};
		    }
		    else if(divi.equals("Z"))
		    {
		    	div=0;
		    	LoginFormBean lb=null;
		    	int size=divList.size();
		    	divname=new String[size];
		    	division = new int[size];
		    	divString=new StringBuffer("(");
		    	for(int i=0; i<size;i++)
		    	{
		    		lb = (LoginFormBean) divList.get(i);
		    		division[i]=lb.getDcode();
		    		divname[i]=lb.getDname();
		    		divString.append(lb.getDcode()+",");
		    	}
		    	divString.append(lb.getDcode()+")");
		    }
		   
		    		
		try {
			edt =new java.sql.Date(edate.getTime()); 
		

				query="select a.*,c.div_name from (select d.name,d.budget_br,d.sales_trade,d.cn100,d.ach,d.surdef,d.last_month,d.last_year,d.collection,d.collection_cumm,d.remit," +
				"d.remit_cumm,d.outstand,d.depo_code,d.div_code,d.budget_per,d.sales_today,d.ent_date,d.ent_time,d.status from daily_entry d , "+
				"(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B "+ 
				"where  d.ddate<=? and d.amd_no in (select max(amd_no) from daily_entry where  ddate<=? and name is not null and mnth_code in (select mnth_code from monthfl where mnth_no=? and ? between fr_Date and to_date) group by depo_code,div_code,name) "+
				"and d.depo_Code=b.depo_code and div_code in "+divString.toString()+") a,divmast c where a.div_code=c.div_Code order by a.depo_Code,a.div_code,a.name ";		
			 
			  
			
			
			ps = con.prepareStatement(query);
			
			double budget=0.00;
			double cn100=0.00;
			double sale=0.00;
			double sale_today=0.00;
			double ach=0.00;
			double surdef=0.00;
			double lmsale=0.00;
			double lysale=0.00;
			double coll=0.00;
			double collcumm=0.00;
			double remit=0.00;
			double cummremit=0.00;
			double out=0.00;

			double mbudget=0.00;
			double mcn100=0.00;
			double msale=0.00;
			double msale_today=0.00;
			double mlmsale=0.00;
			double mlysale=0.00;
			double mcoll=0.00;
			double mcollcumm=0.00;
			double mremit=0.00;
			double mcummremit=0.00;
			double mout=0.00;

			double tbudget=0.00;
			double tcn100=0.00;
			double tsale=0.00;
			double tsale_today=0.00;
			double tlmsale=0.00;
			double tlysale=0.00;
			double tcoll=0.00;
			double tcollcumm=0.00;
			double tremit=0.00;
			double tcummremit=0.00;
			double tout=0.00;

			double gebudget=0.00;
			double gecn100=0.00;
			double gesale=0.00;
			double gesale_today=0.00;
			double gelmsale=0.00;
			double gelysale=0.00;
			double gecoll=0.00;
			double gecollcumm=0.00;
			double geremit=0.00;
			double gecummremit=0.00;
			double geout=0.00;

			double m2budget=0.00;
			double m2cn100=0.00;
			double m2sale=0.00;
			double m2sale_today=0.00;
			double m2lmsale=0.00;
			double m2lysale=0.00;
			double m2coll=0.00;
			double m2collcumm=0.00;
			double m2remit=0.00;
			double m2cummremit=0.00;
			double m2out=0.00;

			double m3budget=0.00;
			double m3cn100=0.00;
			double m3sale=0.00;
			double m3sale_today=0.00;
			double m3lmsale=0.00;
			double m3lysale=0.00;
			double m3coll=0.00;
			double m3collcumm=0.00;
			double m3remit=0.00;
			double m3cummremit=0.00;
			double m3out=0.00;


			double m4budget=0.00;
			double m4cn100=0.00;
			double m4sale=0.00;
			double m4sale_today=0.00;
			double m4lmsale=0.00;
			double m4lysale=0.00;
			double m4coll=0.00;
			double m4collcumm=0.00;
			double m4remit=0.00;
			double m4cummremit=0.00;
			double m4out=0.00;

			
			double gbudget=0.00;
			double gcn100=0.00;
			double gsale=0.00;
			double gsale_today=0.00;
			double gach=0.00;
			double gsurdef=0.00;
			double glmsale=0.00;
			double glysale=0.00;
			double gcoll=0.00;
			double gcollcumm=0.00;
			double gremit=0.00;
			double gcummremit=0.00;
			double gout=0.00;
			int count=0;
			int mcount=0;
			int tcount=0;
			int gcount=0;
			int m2count=0;
			int m3count=0;
			int m4count=0;
			
				
				budget=0.00;
				cn100=0.00;
				sale=0.00;
				sale_today=0.00;
				ach=0.00;
				surdef=0.00;
				lmsale=0.00;
				lysale=0.00;
				coll=0.00;
				collcumm=0.00;
				remit=0.00;
				cummremit=0.00;
				out=0.00;
				count=0;
				ps.setDate(1, new java.sql.Date(edate.getTime()));
				ps.setDate(2, new java.sql.Date(edate.getTime()));
				ps.setInt(3,mon);
				ps.setDate(4, new java.sql.Date(edate.getTime()));

				rst = ps.executeQuery();
				boolean first=true;
				int xdiv=0;
				String name=null;
				while (rst.next())
				{
					if(first)
					{
						xdiv=rst.getInt(14);
						name=rst.getString(1);
						first=false;
					}
					
					if(xdiv!=rst.getInt(14))
					{
						afb = new LoginFormBean();
						afb.setF_name("TOTAL "+name);
						afb.setSales_bud(budget);
						afb.setTrd_sale(sale);
						if (budget!=0)
							afb.setAch((sale/budget)*100);
						afb.setDef(sale-budget);	
						afb.setCn100(cn100);
						afb.setLmsale(lmsale);
						afb.setLysale(lysale);
						afb.setCollc(coll);
						afb.setCollcumm(collcumm);
						afb.setRemit(remit);
						afb.setRemitcumm(cummremit);
						afb.setOutstnd(out);
						afb.setSale_today(sale_today);
						afb.setOpt("");
						afb.setAccess_t(""); // depo_code
						afb.setId(2);
						afb.setCode(xdiv);
						data.add(afb);
						
						budget=0.00;
						cn100=0.00;
						sale=0.00;
						sale_today=0.00;
						ach=0.00;
						surdef=0.00;
						lmsale=0.00;
						lysale=0.00;
						coll=0.00;
						collcumm=0.00;
						remit=0.00;
						cummremit=0.00;
						out=0.00;
						xdiv=rst.getInt(14);
						name=rst.getString(1);
					}
					afb = new LoginFormBean();
					afb.setF_name(rst.getString(1)+" "+rst.getString(21));
					afb.setSales_bud(rst.getDouble(2));
					afb.setTrd_sale(rst.getDouble(3));
					if (rst.getDouble(2)!=0)
						afb.setAch((((rst.getDouble(3)/rst.getDouble(2))*100)));
					afb.setDef(rst.getDouble(3)-rst.getDouble(2));	
					afb.setCn100(rst.getDouble(4));
					afb.setLmsale(rst.getDouble(7));
					afb.setLysale(rst.getDouble(8));
					afb.setCollc(rst.getDouble(9));
					afb.setCollcumm(rst.getDouble(10));
					afb.setRemit(rst.getDouble(11));
					afb.setRemitcumm(rst.getDouble(12));
					afb.setOutstnd(rst.getDouble(13));
					afb.setOpt(df.format(rst.getDouble(16)));
					afb.setSale_today(rst.getDouble(17));
					afb.setEdate(rst.getDate(18));
					afb.setLast_ltime(rst.getString(19));
					afb.setStatus(rst.getString(20));
					//afb.setCode(rst.getInt(14));
					afb.setAccess_t(rst.getString(14)); // depo_code


					
					budget+=rst.getDouble(2);
					cn100+=rst.getDouble(4);
					sale+=rst.getDouble(3);
					lmsale+=rst.getDouble(7);
					lysale+=rst.getDouble(8);
					coll+=rst.getDouble(9);
					collcumm+=rst.getDouble(10);
					remit+=rst.getDouble(11);
					cummremit+=rst.getDouble(12);
					out+=rst.getDouble(13);
					sale_today+=rst.getDouble(17);

					if(rst.getInt(15)==1)
					{
						mbudget+=rst.getDouble(2);
						mcn100+=rst.getDouble(4);
						msale+=rst.getDouble(3);
						mlmsale+=rst.getDouble(7);
						mlysale+=rst.getDouble(8);
						mcoll+=rst.getDouble(9);
						mcollcumm+=rst.getDouble(10);
						mremit+=rst.getDouble(11);
						mcummremit+=rst.getDouble(12);
						mout+=rst.getDouble(13);
						msale_today+=rst.getDouble(17);
						mcount++;
					}
					else if(rst.getInt(15)==2)
					{
						tbudget+=rst.getDouble(2);
						tcn100+=rst.getDouble(4);
						tsale+=rst.getDouble(3);
						tlmsale+=rst.getDouble(7);
						tlysale+=rst.getDouble(8);
						tcoll+=rst.getDouble(9);
						tcollcumm+=rst.getDouble(10);
						tremit+=rst.getDouble(11);
						tcummremit+=rst.getDouble(12);
						tout+=rst.getDouble(13);
						tsale_today+=rst.getDouble(17);
						tcount++;
					}
					else if(rst.getInt(15)==3)
					{
						gebudget+=rst.getDouble(2);
						gecn100+=rst.getDouble(4);
						gesale+=rst.getDouble(3);
						gelmsale+=rst.getDouble(7);
						gelysale+=rst.getDouble(8);
						gecoll+=rst.getDouble(9);
						gecollcumm+=rst.getDouble(10);
						geremit+=rst.getDouble(11);
						gecummremit+=rst.getDouble(12);
						geout+=rst.getDouble(13);
						gesale_today+=rst.getDouble(17);
						gcount++;
					}
					else if(rst.getInt(15)==10)
					{
						m2budget+=rst.getDouble(2);
						m2cn100+=rst.getDouble(4);
						m2sale+=rst.getDouble(3);
						m2lmsale+=rst.getDouble(7);
						m2lysale+=rst.getDouble(8);
						m2coll+=rst.getDouble(9);
						m2collcumm+=rst.getDouble(10);
						m2remit+=rst.getDouble(11);
						m2cummremit+=rst.getDouble(12);
						m2out+=rst.getDouble(13);
						m2sale_today+=rst.getDouble(17);
						m2count++;
					}
					else if(rst.getInt(15)==20)
					{
						m3budget+=rst.getDouble(2);
						m3cn100+=rst.getDouble(4);
						m3sale+=rst.getDouble(3);
						m3lmsale+=rst.getDouble(7);
						m3lysale+=rst.getDouble(8);
						m3coll+=rst.getDouble(9);
						m3collcumm+=rst.getDouble(10);
						m3remit+=rst.getDouble(11);
						m3cummremit+=rst.getDouble(12);
						m3out+=rst.getDouble(13);
						m3sale_today+=rst.getDouble(17);
						m3count++;
					}
					else if(rst.getInt(15)==30)
					{
						m4budget+=rst.getDouble(2);
						m4cn100+=rst.getDouble(4);
						m4sale+=rst.getDouble(3);
						m4lmsale+=rst.getDouble(7);
						m4lysale+=rst.getDouble(8);
						m4coll+=rst.getDouble(9);
						m4collcumm+=rst.getDouble(10);
						m4remit+=rst.getDouble(11);
						m4cummremit+=rst.getDouble(12);
						m4out+=rst.getDouble(13);
						m4sale_today+=rst.getDouble(17);
						m4count++;
					}
					 
					gbudget+=rst.getDouble(2);
					gcn100+=rst.getDouble(4);
					gsale+=rst.getDouble(3);
					glmsale+=rst.getDouble(7);
					glysale+=rst.getDouble(8);
					gcoll+=rst.getDouble(9);
					gcollcumm+=rst.getDouble(10);
					gremit+=rst.getDouble(11);
					gcummremit+=rst.getDouble(12);
					gout+=rst.getDouble(13);
					gsale_today+=rst.getDouble(17);

					count++;


					/*				if (divi.length==1)
				{
					afb.setStatus(rst.getString(6));
					afb.setEdate(rst.getDate(7));
					afb.setLast_ltime(rst.getString(8));
					html
					<display:column property="status" title="STATUS" headerClass="r" class="r"/>
					<display:column property="edate" title="DATE" format="{0,date,dd/MM/yyyy}" headerClass="r" class="r"/>
					<display:column property="last_ltime" title="TIME" headerClass="r" class="r"/>

				}
					 */				
					data.add(afb);
				}
				rst.close();
				if(count>0)
				{
					afb = new LoginFormBean();
					afb.setF_name("TOTAL "+name);
					afb.setSales_bud(budget);
					afb.setTrd_sale(sale);
					if (budget!=0)
						afb.setAch((((sale/budget)*100)));
					afb.setDef(sale-budget);	
					afb.setCn100(cn100);
					afb.setLmsale(lmsale);
					afb.setLysale(lysale);
					afb.setCollc(coll);
					afb.setCollcumm(collcumm);
					afb.setRemit(remit);
					afb.setRemitcumm(cummremit);
					afb.setOutstnd(out);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(2);
					afb.setCode(xdiv);
					afb.setSale_today(sale_today);
					data.add(afb);
				}

				if(mcount>1 || tcount>1 || gcount>1 || m2count>1 || m3count>1)
				{
					afb = new LoginFormBean();
					afb.setF_name("TOTAL MF ");
					afb.setSales_bud(mbudget);
					afb.setTrd_sale(msale);
					if (mbudget!=0)
						afb.setAch((((msale/mbudget)*100)));
					afb.setDef(msale-mbudget);	
					afb.setCn100(mcn100);
					afb.setLmsale(mlmsale);
					afb.setLysale(mlysale);
					afb.setCollc(mcoll);
					afb.setCollcumm(mcollcumm);
					afb.setRemit(mremit);
					afb.setRemitcumm(mcummremit);
					afb.setOutstnd(mout);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(msale_today);
					data.add(afb);

					afb = new LoginFormBean();
					afb.setF_name("TOTAL TF ");
					afb.setSales_bud(tbudget);
					afb.setTrd_sale(tsale);
					if (tbudget!=0)
						afb.setAch((((tsale/tbudget)*100)));
					afb.setDef(tsale-tbudget);	
					afb.setCn100(tcn100);
					afb.setLmsale(tlmsale);
					afb.setLysale(tlysale);
					afb.setCollc(tcoll);
					afb.setCollcumm(tcollcumm);
					afb.setRemit(tremit);
					afb.setRemitcumm(tcummremit);
					afb.setOutstnd(tout);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(tsale_today);
					data.add(afb);

					afb = new LoginFormBean();
					afb.setF_name("TOTAL GEN ");
					afb.setSales_bud(gebudget);
					afb.setTrd_sale(gesale);
					if (gebudget!=0)
						afb.setAch((((gesale/gebudget)*100)));
					afb.setDef(gesale-gebudget);	
					afb.setCn100(gecn100);
					afb.setLmsale(gelmsale);
					afb.setLysale(gelysale);
					afb.setCollc(gecoll);
					afb.setCollcumm(gecollcumm);
					afb.setRemit(geremit);
					afb.setRemitcumm(gecummremit);
					afb.setOutstnd(geout);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(gesale_today);
					data.add(afb);

					afb = new LoginFormBean();
					afb.setF_name("TOTAL MF2 ");
					afb.setSales_bud(m2budget);
					afb.setTrd_sale(m2sale);
					if (m2budget!=0)
						afb.setAch((((m2sale/m2budget)*100)));
					afb.setDef(m2sale-m2budget);	
					afb.setCn100(m2cn100);
					afb.setLmsale(m2lmsale);
					afb.setLysale(m2lysale);
					afb.setCollc(m2coll);
					afb.setCollcumm(m2collcumm);
					afb.setRemit(m2remit);
					afb.setRemitcumm(m2cummremit);
					afb.setOutstnd(m2out);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(m2sale_today);
					data.add(afb);

					afb = new LoginFormBean();
					afb.setF_name("TOTAL MF3 ");
					afb.setSales_bud(m3budget);
					afb.setTrd_sale(m3sale);
					if (m3budget!=0)
						afb.setAch((((m3sale/m3budget)*100)));
					afb.setDef(m3sale-m3budget);	
					afb.setCn100(m3cn100);
					afb.setLmsale(m3lmsale);
					afb.setLysale(m3lysale);
					afb.setCollc(m3coll);
					afb.setCollcumm(m3collcumm);
					afb.setRemit(m3remit);
					afb.setRemitcumm(m3cummremit);
					afb.setOutstnd(m3out);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(m3sale_today);
					data.add(afb);

					afb = new LoginFormBean();
					afb.setF_name("TOTAL MF4 ");
					afb.setSales_bud(m4budget);
					afb.setTrd_sale(m4sale);
					if (m3budget!=0)
						afb.setAch((((m4sale/m4budget)*100)));
					afb.setDef(m4sale-m4budget);	
					afb.setCn100(m4cn100);
					afb.setLmsale(m4lmsale);
					afb.setLysale(m4lysale);
					afb.setCollc(m4coll);
					afb.setCollcumm(m4collcumm);
					afb.setRemit(m4remit);
					afb.setRemitcumm(m4cummremit);
					afb.setOutstnd(m4out);
					afb.setOpt("");
					afb.setAccess_t(""); // depo_code
					afb.setId(3);
					afb.setSale_today(m4sale_today);
					data.add(afb);
				}



				afb = new LoginFormBean();
				if(mcount>1)
					afb.setF_name("ALL INDIA ");
				else
					afb.setF_name("GRAND TOTAL ");
				afb.setSales_bud(gbudget);
				afb.setTrd_sale(gsale);
				if (gbudget!=0)
					afb.setAch((((gsale/gbudget)*100)));
				afb.setDef(gsale-gbudget);	
				afb.setCn100(gcn100);
				afb.setLmsale(glmsale);
				afb.setLysale(glysale);
				afb.setCollc(gcoll);
				afb.setCollcumm(gcollcumm);
				afb.setRemit(gremit);
				afb.setRemitcumm(gcummremit);
				afb.setOutstnd(gout);
				afb.setOpt("");
				afb.setAccess_t(""); // depo_code
				afb.setId(3);
				afb.setSale_today(gsale_today);

			data.add(afb);
			
			ps.close();
			//rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.DailyListNew " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}		


	public List DailyListStatus(Connection con,Date edate,String divi,int id,int opt,List divList,int mon) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null;
		 
		int division[] = null;
		String divname[]= null;
		String query=null;
		StringBuffer divString=new StringBuffer("(1)");
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		  int div=1;
	      divname=new String[]{"MF"};
	      division = new int[] {1};
		    if(divi.equals("T"))
		    {
		    	div=2;
		    	divname=new String[]{"TF"};
			      division = new int[] {2};
			      divString=new StringBuffer("(2)");

		    }
		    else if(divi.equals("G"))
		    {
		    	div=3;
	            divname=new String[]{"Genetica"};
   	  	       division = new int[] {3};
   	  	       divString=new StringBuffer("(3)");

		    }
		    else if(divi.equals("M"))
		    {
		    	div=10;
	            divname=new String[]{"MF2"};
	  	        division = new int[] {10};
	  	      divString=new StringBuffer("(10)");
		    }
		    else if(divi.equals("B"))
		    {
		    	div=20;
	            divname=new String[]{"MF3"};
	  	        division = new int[] {20};
	  	        divString=new StringBuffer("(20)");
		    }
		    else if(divi.equals("F"))
		    {
		    	div=30;
	            divname=new String[]{"MF4"};
	  	        division = new int[] {30};
	  	        divString=new StringBuffer("(30)");
		    }
		    else if(divi.equals("N"))
		    {
		    	div=12;
	            divname=new String[]{"T1"};
	  	        division = new int[] {12};
		    }
		    else if(divi.equals("O"))
		    {
		    	div=13;
	            divname=new String[]{"T2"};
	  	        division = new int[] {13};
		    }
		    else if(divi.equals("P"))
		    {
		    	div=16;
	            divname=new String[]{"T3"};
	  	        division = new int[] {16};
		    }
		    else if(divi.equals("Z"))
		    {
		    	div=0;
		    	LoginFormBean lb=null;
		    	int size=divList.size();
		    	divname=new String[size];
		    	division = new int[size];
		    	divString=new StringBuffer("(");
		    	for(int i=0; i<size;i++)
		    	{
		    		lb = (LoginFormBean) divList.get(i);
		    		division[i]=lb.getDcode();
		    		divname[i]=lb.getDname();
		    		divString.append(lb.getDcode()+",");
		    	}
		    		divString.append(lb.getDcode()+")");
		    	}
		   
		    		System.out.println(divString);
		try {
			 
		

/*				query="select a.*,c.div_name from (select d.name,d.budget_br,d.sales_trade,d.cn100,d.ach,d.surdef,d.last_month,d.last_year,d.collection,d.collection_cumm,d.remit," +
				"d.remit_cumm,d.outstand,d.depo_code,d.div_code,d.budget_per,d.sales_today,d.ent_date,d.ent_time,d.status from daily_entry d , "+
				"(Select d.depo_code,t.ter_name from user_branch08 as d, a_branch08 as t where d.depo_code=t.depo_code and d.user_id="+id+" and d.status='Y') B "+ 
				"where  d.ddate=? and d.amd_no in (select max(amd_no) from daily_entry where  ddate=? group by depo_code,div_code,name) "+
				"and d.depo_Code=b.depo_code and div_code in "+divString.toString()+") a,divmast c where a.div_code=c.div_Code order by a.depo_Code,a.div_code ";		
*/				
				query="select d.depo_code,d.name,c.div_name,d.ddate,d.ent_date,d.ent_time,d.status from daily_entry d,divmast c "+ 
				"where d.div_code=c.div_code and d.depo_code in (select depo_code from user_branch08 u where u.user_id="+id+" and u.status='Y') "+
				"and d.mnth_code in (select mnth_code from monthfl where ? between fr_date and to_date) and d.amd_no in (select max(amd_no) from daily_entry where  ddate<=? and mnth_code in (select mnth_code from monthfl where mnth_no=? and ? between fr_Date and to_date) group by depo_code,div_code,name) "+
				"order by status desc,ent_date,ent_time ";
				
			  
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(edate.getTime()));
				ps.setDate(2, new java.sql.Date(edate.getTime()));
				ps.setInt(3,mon);
				ps.setDate(4, new java.sql.Date(edate.getTime()));
				rst = ps.executeQuery();
				while (rst.next())
				{
					afb = new LoginFormBean();
					afb.setCode(rst.getInt(1));
					afb.setF_name(rst.getString(2)+" "+rst.getString(3));
					afb.setBdate(rst.getDate(4));
					afb.setEdate(rst.getDate(5));
					afb.setLast_ltime(rst.getString(6));
					afb.setStatus(rst.getString(7));
					data.add(afb);
					
				}
				rst.close();
				ps.close();
			//rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.DailyListStatus " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}	
		
		return data;
	}		
	
	
	public int AddBudSal (LoginFormBean lfb, Connection con)
	{
		int i=0;
		int uid=0;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		String query=null;
		String query1=null;
		String query2=null;		  
		try {
			query ="insert into daily_targetho (depo_code,division,month,sales_bud,mkt_year) values (?,?,?,?,?)";
			query1="Update daily_targetho set sales_bud=? where depo_code=? and division=? and month=? and mkt_year=? ";
			query2="select depo_code from daily_targetho where depo_code=? and division=? and month=? and mkt_year=? ";

			ps2=con.prepareStatement(query2);
			ps2.setInt(1, lfb.getCode());
			ps2.setString(2,lfb.getAccess_t());
			ps2.setString(3,lfb.getMnth());
			ps2.setInt(4, lfb.getEyear());
			rs2 = ps2.executeQuery();
			
			if (rs2.next())
			{
				ps1=con.prepareStatement(query1);
				ps1.setDouble(1, lfb.getSales_bud());
				ps1.setInt(2, lfb.getCode());
				ps1.setString(3, lfb.getAccess_t());
				ps1.setString(4,lfb.getMnth());
				ps1.setInt(5,lfb.getEyear());
				i=ps1.executeUpdate();
				ps1.close();
			}
			else
			{
				ps = con.prepareStatement(query);
			    ps.setInt(1,lfb.getCode());
			    ps.setString(2,lfb.getAccess_t());
			    ps.setString(3,lfb.getMnth());
				ps.setDouble(4,lfb.getSales_bud());
	            ps.setInt(5,lfb.getEyear());
	            i=ps.executeUpdate();
				ps.close();
			}

				rs2.close();
				ps2.close();
			
				
			System.out.println(i);
		} catch (SQLException e) {
			System.out.println("-------------Exception in SQLLoginDAO.AddBudSal "+e);
			i=-1;
		}
		finally {
			try {
		           if(ps != null){ps.close();}
		           if(ps1 != null){ps1.close();}
		           if(ps2 != null){ps2.close();}
		           if(rs2 != null){rs2.close();}
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		return uid;
	}	
		
	public int AddBudCol (LoginFormBean lfb, Connection con)
	{
		int i=0;
		int uid=0;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		String query=null;
		String query1=null;
		String query2=null;		  
		try {
			query ="insert into daily_targetho (depo_code,division,month,coll_bud,mkt_year) values (?,?,?,?,?)";
			query1="Update daily_targetho set coll_bud=? where depo_code=? and division=? and month=? and mkt_year=? ";
			query2="select depo_code from daily_targetho where depo_code=? and division=? and month=? and mkt_year=? ";

			ps2=con.prepareStatement(query2);
			ps2.setInt(1, lfb.getCode());
			ps2.setString(2,lfb.getAccess_t());
			ps2.setString(3,lfb.getMnth());
			ps2.setInt(4, lfb.getEyear());
			rs2 = ps2.executeQuery();
			
			if (rs2.next())
			{
				ps1=con.prepareStatement(query1);
				ps1.setDouble(1, lfb.getCollc());
				ps1.setInt(2, lfb.getCode());
				ps1.setString(3, lfb.getAccess_t());
				ps1.setString(4,lfb.getMnth());
				ps1.setInt(5,lfb.getEyear());
				i=ps1.executeUpdate();
				ps1.close();
			}
			else
			{
				ps = con.prepareStatement(query);
			    ps.setInt(1,lfb.getCode());
			    ps.setString(2,lfb.getAccess_t());
			    ps.setString(3,lfb.getMnth());
				ps.setDouble(4,lfb.getCollc());
	            ps.setInt(5,lfb.getEyear());
	            i=ps.executeUpdate();
				ps.close();
			}
				rs2.close();
				ps2.close();
			System.out.println(i);
		} catch (SQLException e) {
			System.out.println("-------------Exception in SQLLoginDAO.AddBudSal "+e);
			i=-1;
		}
		finally {
			try {
		           if(ps != null){ps.close();}
		           if(ps1 != null){ps1.close();}
		           if(ps2 != null){ps2.close();}
		           if(rs2 != null){rs2.close();}
		           if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.Connection.close "+e);
			  }
		}	
		return uid;
	}	
			
	
	public List getUserStatus(Connection con,String st,String sr) { 
		 
		LoginFormBean afb;
		PreparedStatement ps=null;
		ResultSet rst=null; 
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try { 
			String query=null;
			
			if (sr==null)
	         {	
	          query=" select login_name,title,f_name,l_name,department,location,access_t,status," +
			  "last_ldate,last_ltime from login where type<>? and status=?" ;
			 }
	         else
	         {
		      query=" select login_name,title,f_name,l_name,department,location,access_t,status," +
		      "last_ldate,last_ltime from login where type<>? and status=? and login_name like '"+sr+"%' ";
	         }   
			
			ps = con.prepareStatement(query);
			ps.setString(1,"Admin");
			ps.setString(2,st);
			rst = ps.executeQuery();
			while (rst.next()) 
			{
				afb = new LoginFormBean();
				afb.setLogin_name(rst.getString(1));
				afb.setF_name(rst.getString(2)+" "+rst.getString(3));
				afb.setL_name(rst.getString(4));
				afb.setDepartment(rst.getString(5));
				afb.setLocation(rst.getString(6));
				afb.setAccess_t(rst.getString(7));
				afb.setStatus(rst.getString(8));
				afb.setLast_ldate(rst.getString(9) +" | "+rst.getString(10));

				data.add(afb);
			}
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getUserStatusAdmin " + e);
		}
		
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.UserStatusConnection.close "+e);
			  }
		}	
		
		return data;
	}	
	
	

	public String getLastNo(Connection con) { 
		 
		PreparedStatement ps=null;
		ResultSet rst=null;
		String msg=" ";
		SimpleDateFormat sdf = null;
		try { 
			
			sdf = new SimpleDateFormat("dd/MM/yyyy"); 
			String query = "select a.locationid,a.dt,max(b.documentno) from f_trans b," +
			"(select locationid,max(dcdate) dt from f_trans group by locationid) a " +
			" where a.locationid = b.locationid and a.dt=b.dcdate group by a.locationid,a.dt ";
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next())
			{
//				msg=msg + rst.getString(1) +" - " + rst.getString(3)+" ";
				msg=msg + rst.getString(1) +" - " + sdf.format(rst.getDate(2))+" ";
			}
		
			ps.close();
			rst.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLLoginDAO.getLastNo. " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLLoginDAO.LastNo.Connection.close "+e);
			  }
		}	
		
		return msg;
	}	
	
	
}