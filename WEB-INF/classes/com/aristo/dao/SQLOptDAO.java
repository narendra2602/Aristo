
package com.aristo.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.aristo.valueobject.LoginFormBean;
import com.aristo.valueobject.MktFormBean;
import com.aristo.valueobject.ProductFormBean;
import com.aristo.valueobject.Repo10FormBean;
import com.aristo.valueobject.Repo12FormBean;
import com.aristo.valueobject.Repo13FormBean;
import com.aristo.valueobject.Repo15FormBean;
import com.aristo.valueobject.Repo16FormBean;
import com.aristo.valueobject.Repo17FormBean;
import com.aristo.valueobject.Repo18FormBean;
import com.aristo.valueobject.Repo2FormBean;
import com.aristo.valueobject.Repo9FormBean;
import com.aristo.valueobject.SampleRepo5FormBean;

public class SQLOptDAO {
////////////////////////////// Get All Headquater///////////////////////////
	
	public List getAllrepo(Connection con,int depo_code,String tp) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
   	        tblnm=(tp+"_hq_master08").toLowerCase();
            txt1="H.Q.";
		        
            String query2 = "Select distinct(ter_code),ter_name from "+tblnm+" where depo_code=? and ter_pt=? order by area_code,regn_code,ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
			  rfb = new Repo2FormBean();
        	  rfb.setQty2(rst2.getInt(1));
        	  rfb.setNm2(rst2.getString(2)); 
              rfb.setNm3(txt1);
              data.add(rfb);
			} 
			
	            ps2.close();
	            rst2.close();
	        
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllHQ " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
	        }
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}

		return data;
	}

	
//////////////////////////////Get All Headquater coding end here ///////////////////////////	
	

//////////////////////////////Get All Region Start Here ///////////////////////////////////////	
	
	public List getAllRegion(Connection con,int depo_code,String tp) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            	
       	    tblnm=(tp+"_region_master08").toLowerCase();
            
             txt1="Region";
		        
            String query2 = "Select distinct(reg_code),name from "+tblnm+" where depo_code=? and typ=? order by area_code,reg_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo2FormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllRegion " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Region Coding end here///////////////////////////

//////////////////////////////Get All Area Coding Start Here///////////////////////////
	
	public List getAllArea(Connection con,int depo_code,String tp) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
       	    tblnm=(tp+"_area_master08").toLowerCase();
            txt1="Area";
		        
            String query2 = "Select distinct(area_cd),area_name from "+tblnm+" where depo_code=? and typ=? order by area_cd";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo2FormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllRegion " + e);
		}
		finally {
			try 
			{
            if(rst2 != null){rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Area Coding end Here///////////////////////////
	
//////////////////////////////Get All Branch Coding Start Here///////////////////////////
	
	public List getAllBranch(Connection con,int depo_code,String tp) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=tp+"_branch08";
            
/*            if (tp.equals("A"))
            {	
       	        tblnm="a_branch08";
            }	
            if (tp.equals("T"))
            {
        	    tblnm="t_branch08";
            }
            
            if (tp.equals("G"))
            {	
       	        tblnm="g_branch08";
            }   
*/            
             txt1="Branch";
		        
	            String query2 = "Select depo_code,ter_name from "+tblnm+" where depo_code=? and typ=? order by depo_code";
		        ps2 = con.prepareStatement(query2);
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp); 
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo2FormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                    data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();

			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllBranch " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}

//////////////////////////////Get All Branch Coding End Here///////////////////////////

//////////////////////////////Get All Product Coding Start Here///////////////////////////
	
	public List getAllProduct(Connection con, String tp,int year,int uid,String utype) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
 
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
            tblnm=(tp+"_product08").toLowerCase();
            txt1="PRODUCT";
            if (utype.equals("PMT"))
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and pd_group in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
            }
            else
            {
		    query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";
            }
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1, year);
	        rst2 = ps2.executeQuery();

            while (rst2.next())
			{
			  rfb = new Repo2FormBean();
        	  rfb.setQty2(rst2.getInt(1));
        	  rfb.setNm2(rst2.getString(2)+","+rst2.getString(3)); 
              rfb.setNm3(txt1);
              data.add(rfb); 				
			} 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllProduct " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Product Coding End Here///////////////////////////
	

//////////////////////////////Get All Group Coding Start Here///////////////////////////
	
	public List getAllGroup(Connection con, String tp,int eyear,int uid,String utype) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String query2=null;
            String txt1=null;
	        txt1="Group";
            
  	        if (utype.equals("PMT"))
  	        {
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" where gp_code in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_name";
  	        } 
  	        else
  	        {
/*  	        tblnm=tp+"_group_mkt08";
            query2 = "Select gp_code,gp_name from "+tblnm+" where mkt_year=? order by gp_name";
*/
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+"  order by gp_name";
 	        
  	        }
	        ps2 = con.prepareStatement(query2);
	        if (!utype.equals("PMT"))
	        {
//	        ps2.setInt(1, eyear);	
	        }
	        rst2 = ps2.executeQuery();

            while (rst2.next())
			{
				  rfb = new Repo2FormBean();
	        	  rfb.setQty2(rst2.getInt(1));
	        	  rfb.setNm2(rst2.getString(2)); 
	              rfb.setNm3(txt1);
                  data.add(rfb); 				
			 } 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllGroup " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}

//////////////////////////////Get All Group Coding End Here///////////////////////////	

//////////////////////////////Get All Marketing Product Coding Start Here///////////////////////////
	
	public List getFormProduct(Connection con, String tp,int year,int uid,String utype) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     

			String tblnm=null;
            String txt1=null;
            String query2=null;
            tblnm=(tp+"_product08").toLowerCase();
            txt1="Product";
            if (utype.equals("PMT"))
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and tn<>'D' and pd_group in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
            }
            else
            {
         	query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and tn<>'D' order by pname";
            }
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1, year);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new MktFormBean();
					  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)+","+rst2.getString(3)); 
		              rfb.setNm3(txt1);
	                    data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getFormProduct " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
	
//////////////////////////////Get All Marketing Product Coding End Here///////////////////////////

	
//////////////////////////////Get All Marketing Group Coding Start Here///////////////////////////
	
	public List getAllFormGroup(Connection con, String tp,int eyear,int uid,String utype) { 
		 
			MktFormBean rfb;
			PreparedStatement ps2 =null;
			ResultSet rst2 =null;		

			List<MktFormBean> data = new ArrayList<MktFormBean>();		
			
			try {     

            String tblnm=null;
            String txt1=null;
            String query2=null;
            
            txt1="GROUP";
            
  	        if (utype.equals("PMT"))
  	        {
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" where gp_code in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_name";
  	        }
  	        else
  	        {
  	        tblnm=(tp+"_group_mkt08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" where mkt_year=? order by gp_name";
  	        }
	        ps2 = con.prepareStatement(query2);
	        if (!utype.equals("PMT"))
	        {
	        ps2.setInt(1, eyear);	
	        }
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
			  rfb = new MktFormBean();
        	  rfb.setQty2(rst2.getInt(1));
        	  rfb.setNm2(rst2.getString(2)); 
              rfb.setNm3(txt1);
              data.add(rfb); 				
			 } 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllGroupMarketing " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Marketing Group Coding End Here///////////////////////////	
	
//////////////////////////////Get All Depo Coding Start Here///////////////////////////
	
	public List getAlldepo(Connection con) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            
            String tblnm=null;
      	    tblnm="a_branch08";
  	        
	            String query2 = "Select depo_code,ter_name from "+tblnm+"  order by ter_name";
		        ps2 = con.prepareStatement(query2);
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new LoginFormBean();
					  System.out.println(rst2.getString(2));  
		        	  rfb.setDcode(rst2.getInt(1));
		        	  rfb.setDname(rst2.getString(2)); 
	                    data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
 
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getAllDepo..550 " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
//////////////////////////////Get All Depo Coding End Here///////////////////////////
	
	
//////////////////////////////Get All DGM for Create User Screen Coding Start Here///////////////////////////
	
	public List getAllDGMCreate(Connection con) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            
            String tblnm=null;
      	    tblnm="a_dgm_master08";
  	        
	            String query2 = "Select dgm_code,dgm_name from "+tblnm+"  order by dgm_name";
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
			
			System.out.println("========Exception in SQLOptDAO.getAllDGM " + e);
		}
	
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
//////////////////////////////End of Get All DGM for Create User Screen Coding ///////////////////////////
	

//////////////////////////////Get All ZM for Create User Screen Coding Start Here///////////////////////////
	
	public List getAllZMCreate(Connection con) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            
            String tblnm=null;
      	    tblnm="a_zm_master08";
  	        
	            String query2 = "Select zm_code,zm_name from "+tblnm+"  order by zm_name";
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
			
			System.out.println("========Exception in SQLOptDAO.getAllZM " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
//////////////////////////////End of Get All ZM for Create User Screen Coding ///////////////////////////
	
	
	/////////////////////////////// GET DGM Code Start here //////////////////////////////////////	
	
	public List getAllDGM(Connection con, int code, String tp) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            
            String tblnm=null;
            
            
  /*          if (tp.equals("A"))
            {	
       	        tblnm="a_branch08";
            }	
            if (tp.equals("T"))
            {
        	    tblnm="t_branch08";
            }
            if (tp.equals("G"))
            {	
       	        tblnm="g_branch08";
            }   

*/            tblnm=tp+"_branch08";

            
            String query2 = "Select depo_code,ter_name from "+tblnm+"  where dgm_code=? order by ter_name";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1, code);
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
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getAllDepo..719 " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
	
/////////////////////////////// GET DGM Code end here //////////////////////////////////////
	
/////////////////////////////// GET Branch Name Start here/////////////////////////////	
	
	public String getBranchName(Connection con, int code) { 
		 
        String data=null;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
        
		try {     
            String tblnm=null;
      	    tblnm="a_branch08";
      	    String query2 = "Select ter_name from "+tblnm+"  where depo_code=? order by depo_code";
		    ps2 = con.prepareStatement(query2);
		    ps2.setInt(1, code);
	        rst2 = ps2.executeQuery();
	        if (rst2.next())
				{
		        	  data= rst2.getString(1); 
  				} 
            ps2.close();
            rst2.close();
            
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLOptDAO.getBranchName " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}

/////////////////////////////// GET Branch Name End here/////////////////////////////	

/////////////////////////////// GET MSG Start here/////////////////////////////	
	
	public String getMsg(Connection con,String tp, int code) { 
		 
        String data=null;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		try {     
            String tblnm=null;
    		Date ndate=new java.util.Date();	    	
    		Date ndt= new java.sql.Date (ndate.getTime());
      	    tblnm="msg_master";
      	    String query2 = "Select msg from "+tblnm+"  where depo_code=? and type=? and date=? order by depo_code";
		    ps2 = con.prepareStatement(query2);
		    ps2.setInt(1,code);
		    ps2.setString(2,tp);
		    ps2.setDate(3,(java.sql.Date)ndt);
	        rst2 = ps2.executeQuery();
	    	data="";
	        if (rst2.next())
				{
		        	  data= rst2.getString(1); 
  				} 
            ps2.close();
            rst2.close();
	
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLOptDAO.getMsg " + e);
		}
		finally {
			try
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}	
	
////////////////////////////// All Headquater Marketing ke liye Start here///////////////////////////
	
	public List getAllHQ(Connection con,int depo_code,String tp) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     

            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_hq_master08").toLowerCase();
       	    
             txt1="H.Q.";
		        
	            String query2 = "Select distinct(ter_code),ter_name from "+tblnm+" where depo_code=? and ter_pt=? order by area_code,regn_code,ter_code";
		        ps2 = con.prepareStatement(query2);
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp); 
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new MktFormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                  data.add(rfb); 				
  				 } 
			
	            ps2.close();
	            rst2.close();
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllHQ " + e);
		}
		
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}

		return data;
	}

	
//////////////////////////////All Headquater Marketing ke liye end here ///////////////////////////	
	
//////////////////////////////Get All Region Start Here ///////////////////////////////////////	
	
	public List getMRegion(Connection con,int depo_code,String tp) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_region_master08").toLowerCase();
       	    
             txt1="REGION";
		        
	            String query2 = "Select distinct(reg_code),name from "+tblnm+" where depo_code=? and typ=? order by area_code,reg_code";
		        ps2 = con.prepareStatement(query2);
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp); 
		        rst2 = ps2.executeQuery();
	            while (rst2.next())
				{
					  rfb = new MktFormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                  data.add(rfb); 				
  				} 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getMRegion " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Region Coding end here///////////////////////////
	
//////////////////////////////Get All Area Start Here ///////////////////////////////////////	
	

	public List getMArea(Connection con,int depo_code,String tp) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_area_master08").toLowerCase();
            
             txt1="AREA";
		        
	            String query2 = "Select distinct(area_cd),area_name from "+tblnm+" where depo_code=? and typ=? order by area_cd";
		        ps2 = con.prepareStatement(query2);
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp); 
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new MktFormBean();
		        	  rfb.setQty2(rst2.getInt(1));
		        	  rfb.setNm2(rst2.getString(2)); 
		              rfb.setNm3(txt1);
	                    data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();

			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getMArea " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}	
	
	

//////////////////////////////Get All Area Coding end here///////////////////////////
	
//////////////////////////////Get All Branch Coding Start Here///////////////////////////
	
	public List getMBranch(Connection con,int depo_code,String tp) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_branch08").toLowerCase();
            
             txt1="BRANCH";
		        
            String query2 = "Select distinct(depo_code),ter_name from "+tblnm+" where depo_code=? and typ=? order by depo_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
				  rfb = new MktFormBean();
	        	  rfb.setQty2(rst2.getInt(1));
	        	  rfb.setNm2(rst2.getString(2)); 
	              rfb.setNm3(txt1);
                    data.add(rfb); 				
			 } 
            ps2.close();
            rst2.close();

		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getMBranch " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}

//////////////////////////////Get All Branch Coding End Here///////////////////////////
	
//////////////////////////////Get All Stokiest Coding Start Here///////////////////////////
	
	public List getAllStk(Connection con,int depo_code,String tp) { 
		 
		Repo12FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<Repo12FormBean> data = new ArrayList<Repo12FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            
       	        tblnm="a_account08";
            
             txt1="STOCKIEST";
		        
         String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
         ps2 = con.prepareStatement(query2);
         ps2.setInt(1,depo_code);
         ps2.setInt(2,0);
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo12FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2)+", "+rst2.getString(3)); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest " + e);
		}
		finally {
			try {

	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest Coding End Here///////////////////////////	
	
//////////////////////////////Get All Stokiest 13 Coding Start Here///////////////////////////
	
	public List getAllStk13(Connection con,int depo_code,String tp) { 
		 
		Repo13FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
	
		List<Repo13FormBean> data = new ArrayList<Repo13FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1=" STOCKIEST ";
            
         String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
         ps2 = con.prepareStatement(query2);
         ps2.setInt(1,depo_code);
         ps2.setInt(2,0);
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo13FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2)+", "+rst2.getString(3));
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest13 " + e);
		}
		finally {
			try {

	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest Coding End Here///////////////////////////		
	
//////////////////////////////Get All Stokiest 9 Coding Start Here///////////////////////////
	
	public List getAllStk9(Connection con,int depo_code,String tp) { 
		 
		Repo9FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo9FormBean> data = new ArrayList<Repo9FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
         ps2 = con.prepareStatement(query2);
         ps2.setInt(1,depo_code);
         ps2.setInt(2,0);
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo9FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest9 " + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest 9 Coding End Here///////////////////////////		
	
//////////////////////////////Get All Stokiest 10 Coding Start Here///////////////////////////
	
	public List getAllStk10(Connection con,int depo_code,String tp) { 
		 
		Repo10FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo10FormBean> data = new ArrayList<Repo10FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;

	        tblnm=(tp+"_account08").toLowerCase();
            
            txt1="STOCKIEST ";
   		        
            String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
            ps2 = con.prepareStatement(query2);
            ps2.setInt(1,depo_code);
            ps2.setInt(2,0);
            rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo10FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2)+", "+rst2.getString(3)); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest10 " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest 9 Coding End Here///////////////////////////		
	
//////////////////////////////Get All Stokiest 15 Coding Start Here///////////////////////////
	
	public List getAllStk15(Connection con,int depo_code,String tp) { 
		 
		Repo15FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<Repo15FormBean> data = new ArrayList<Repo15FormBean>();
		try {     
            
         String tblnm=null;
         String txt1=null;
   	     tblnm=(tp+"_account08").toLowerCase();
         txt1="STOCKIEST";
		        
         String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
         ps2 = con.prepareStatement(query2);
         ps2.setInt(1,depo_code);
         ps2.setInt(2,0);
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo15FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2)+", "+rst2.getString(3)); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest15 " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest 15 Coding End Here///////////////////////////		

//////////////////////////////Get All Stokiest 16 Coding Start Here///////////////////////////
	
	public List getAllStk16(Connection con,int depo_code,String tp) { 
		 
		Repo16FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<Repo16FormBean> data = new ArrayList<Repo16FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="Stockiest";
		        
         String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm+" where depo_code=? and mterr_code<>? order by depo_code";
         ps2 = con.prepareStatement(query2);
         ps2.setInt(1,depo_code);
         ps2.setInt(2,0);
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo16FormBean();
		        	  rfb.setMcode(rst2.getString(1));
		        	  rfb.setMname(rst2.getString(2)+", "+rst2.getString(3)); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getStockiest16 " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Stockiest 16 Coding End Here///////////////////////////	
	
//////////////////////////////Get All Product Repo 17 Coding Start Here///////////////////////////
	
	public List getRepo17Product(Connection con, String tp,int uid,String utype,int year) { 
		 
		Repo17FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<Repo17FormBean> data = new ArrayList<Repo17FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            String query2 =null;
       	    tblnm=(tp+"_product08").toLowerCase();
            
            txt1="Product";
            
            if (utype.equals("PMT"))
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and pd_group in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
            }
            else
            {
		    query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";
            }
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1, year);
	        rst2 = ps2.executeQuery();
    

            while (rst2.next())
			{
				  rfb = new Repo17FormBean();
				  rfb.setMcode(rst2.getInt(1));
	        	  rfb.setMname(rst2.getString(2)+","+rst2.getString(3)); 
	              rfb.setHead1(txt1);
                    data.add(rfb); 				
			 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllProduct17 " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Product repo 17 Coding End Here///////////////////////////	
	
	
//////////////////////////////Get All Product Repo 17 Coding Start Here///////////////////////////
	
	public List getRepo18Product(Connection con, String tp,int year,int uid,String utype) { 
		 
		Repo18FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo18FormBean> data = new ArrayList<Repo18FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
            
   	        tblnm=tp+"_product08";

/*            if (tp.equals("A"))
            {	
       	        tblnm="a_product08";
            }	
            if (tp.equals("T"))
            {
        	    tblnm="t_product08";
            }
            
            if (tp.equals("G"))  
            {	
       	        tblnm="g_product08";
            }   
*/            
             txt1="Product";
		        
             if (utype.equals("PMT"))
             {
             query2 = "Select distinct(pcode),pname,pack from "+tblnm+" where pd_group in " +
             " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and mkt_year=? order by pname";
             }
             else
             {
             query2 = "Select distinct(pcode),pname,pack from "+tblnm+" where mkt_year=? order by pname";
             }

             ps2 = con.prepareStatement(query2);
             ps2.setInt(1, year);
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
				  rfb = new Repo18FormBean();
				  rfb.setMcode(rst2.getInt(1));
	        	  rfb.setMname(rst2.getString(2)+","+rst2.getString(3)); 
	              rfb.setHead1(txt1);
                  data.add(rfb); 				
  				} 
	            ps2.close();
	            rst2.close();

		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getAllProduct18 " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Product repo 17 Coding End Here///////////////////////////	
	
//////////////////////////////Get All User Branch Coding Start Here///////////////////////////
	
	public List getUserBranch(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		
		PreparedStatement ps2 =null;   
		ResultSet rst2 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {       
            
            String tblnm=null;
            String tblnm1=null;  
      	    tblnm="user_branch08";
      	    tblnm1="a_branch08";
  	        
      	    
      	       String query1 = "Select depo_code from "+tblnm+" where user_id=? and status=?";
      	       ps1 = con.prepareStatement(query1);
      	       ps1.setInt(1, uid);
      	       ps1.setString(2, "Y");
      	       rst1 = ps1.executeQuery();
      	       
	           String query2 = "Select depo_code,ter_name from "+tblnm1+" where depo_code=? order by ter_name";
		       ps2 = con.prepareStatement(query2);
		       

		        while (rst1.next())
		        {
		        	  ps2.setInt(1, rst1.getInt(1));
		        	  rst2 = ps2.executeQuery();
		        	  
		        	  if (rst2.next())
		        	  {
						  rfb = new LoginFormBean();
			        	  rfb.setDcode(rst2.getInt(1));
			        	  rfb.setDname(rst2.getString(2)); 
		                  data.add(rfb); 				
		        		  
		        	  }
		        }
	            rst2.close();
	            ps2.close();
	            rst1.close();
	            ps1.close();
	            
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getAllDepo...1574 " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
	            
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}
//////////////////////////////Get All User Branch Coding End Here///////////////////////////
	
	
//////////////////////////////Get All User Menu Coding Start Here///////////////////////////
	
	public List getTabMenu(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		
		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try{     
            
           String tblnm=null;
           String tblnm1=null;
           tblnm="tab_master08";
      	   tblnm1="user_rights08";
  	        
  	       String query1 = "Select distinct(tab_id) from "+tblnm1+" where user_id=? and status=? order by tab_id ";
  	       ps1 = con.prepareStatement(query1);
  	       ps1.setInt(1, uid);
  	       ps1.setString(2, "Y");
  	       rst1 = ps1.executeQuery();
      	       
	       String query2 = "Select tabid,tab_name,tab_link,tab_width from "+tblnm+" where tabid=?";
		   ps2 = con.prepareStatement(query2);
		   
		        while (rst1.next())
		        {
		        	  ps2.setInt(1, rst1.getInt(1));
		        	  rst2 = ps2.executeQuery();
		        	  
		        	  if (rst2.next())
		        	  {
						  rfb = new LoginFormBean();
						  rfb.setTab_id(rst2.getInt(1));
			        	  rfb.setTab_name(rst2.getString(2));
			        	  rfb.setTab_link(rst2.getString(3)); 
			        	  rfb.setTab_width(rst2.getInt(4));
		                  data.add(rfb); 				
		        	  }
		        	  
		        }
		        
	            rst2.close();
	            ps2.close();
	            rst1.close();
	            ps1.close();
	            
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getTabMenu " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(rst1 != null){rst1.close();}
	            if(ps1 != null){ps1.close();}
	            if(con != null){con.close();}
	            
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
///////////////////////////////////////////////////CEntral //////////////////////////////////
	public List getCTabMenu(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try{     
           String tblnm=null;
           String tblnm1=null;
           tblnm="tab_master";
      	   tblnm1="user_rights";
  	        
  	       String query1 = "Select distinct(tab_id) from "+tblnm1+" where user_id=? and status=? order by tab_id ";
  	       ps1 = con.prepareStatement(query1);
  	       ps1.setInt(1, uid);
  	       ps1.setString(2,"Y");
  	       rst1 = ps1.executeQuery();
      	       
	       String query2 = "Select tabid,tab_name from "+tblnm+" where tabid=?";
		   ps2 = con.prepareStatement(query2);
		   
		        while (rst1.next())
		        {
		        	  ps2.setInt(1, rst1.getInt(1));
		        	  rst2 = ps2.executeQuery();
		        	  if (rst2.next())
		        	  {
						  rfb = new LoginFormBean();
						  rfb.setTab_id(rst2.getInt(1));
			        	  rfb.setTab_name(rst2.getString(2));
		                  data.add(rfb); 				
		        	  }
		        }
		        
	            rst2.close();
	            ps2.close();
	            rst1.close();
	            ps1.close();
		            
			} catch (Exception e) {
				System.out.println("========Exception in SQLOptDAO.getTabMenuCentral " + e);
			}
			finally {
				try 
				{
		            if(rst2 != null){rst2.close();}
		            if(ps2 != null){ps2.close();}
		            if(rst1 != null){rst1.close();}
		            if(ps1 != null){ps1.close();}
		            if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
				  }
			}
			return data;
		}
	
//////////////////////////////Get All User Menu Coding End Here///////////////////////////
	
	
//////////////////////////////Get All Repo Menu Coding Start Here///////////////////////////
	
	public List getRepoMenu(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try{     
            
           String tblnm=null;
           String tblnm1=null;
           tblnm="repo_master08";
      	   tblnm1="user_rights08";
  	        
  	       String query1 = "Select repo_id from "+tblnm1+" where user_id=? and status=? order by tab_id,repo_id ";
  	       ps1 = con.prepareStatement(query1);
  	       ps1.setInt(1, uid);
  	       ps1.setString(2, "Y");
  	       rst1 = ps1.executeQuery();
      	       
	       String query2 = "Select tab_id,repo_name,repo_link,repo_image from "+tblnm+" where repo_id=?";
		   ps2 = con.prepareStatement(query2);
	       String imgNotAvailable="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";

		        while (rst1.next())
		        {
		        	  ps2.setString(1, rst1.getString(1));
		        	  rst2 = ps2.executeQuery();
		        	  
		        	  if (rst2.next())
		        	  {
						  rfb = new LoginFormBean();
						  rfb.setTab_id(rst2.getInt(1));
			        	  rfb.setRepo_name(rst2.getString(2));
			        	  rfb.setRepo_link(rst2.getString(3)); 
			        	  
			        	  if(rst2.getBlob(4)!=null)
			        	  {
			        		  Blob blob = rst2.getBlob(4);
			        		  byte[] bdata = blob.getBytes(1, (int) blob.length());
			        		  String s = new String(bdata);

			        		  rfb.setRepo_image(s);
			        	  }
			        	  else
			        		  rfb.setRepo_image(null);
		                  data.add(rfb); 				
		        	  }
		        	  
		        }
	            rst2.close();
	            ps2.close();
	            rst1.close();
	            ps1.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getRepoMenu " + e);
		}
	
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(rst1 != null){rst1.close();}
	            if(ps1 != null){ps1.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
	
/////////////////////////////////////////////////CEntral//////////////////////////////////////////
	public List getCRepoMenu(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		PreparedStatement ps1=null;
		ResultSet rst1=null;		
		
		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try{
		
	       String tblnm=null;
	       String tblnm1=null;
	       tblnm="repo_master";
	  	   tblnm1="user_rights";
	        
	       String query1 = "Select repo_id from "+tblnm1+" where user_id=? and status=? order by tab_id,repo_id ";
	       ps1 = con.prepareStatement(query1);
	       ps1.setInt(1,uid);
	       ps1.setString(2,"Y");
	       rst1 = ps1.executeQuery();
	  	       
	       String query2 = "Select tab_id,repo_name,repo_link from "+tblnm+" where repo_id=?";
		   ps2 = con.prepareStatement(query2);
		   
		        while (rst1.next())
		        {
		        	  ps2.setString(1, rst1.getString(1));
		        	  rst2 = ps2.executeQuery();
		        	  
		        	  if (rst2.next())
		        	  {
						  rfb = new LoginFormBean();
						  rfb.setTab_id(rst2.getInt(1));
			        	  rfb.setRepo_name(rst2.getString(2));
			        	  rfb.setRepo_link(rst2.getString(3)); 
		                  data.add(rfb); 				
		        	  }
		        }
		        
	            rst2.close();
	            ps2.close();
	            rst1.close();
	            ps1.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getRepoMenuCentral " + e);
		}
	
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(rst1 != null){rst1.close();}
	            if(ps1 != null){ps1.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
	
//////////////////////////////Get All Repo Menu Coding End Here///////////////////////////
	
	public List getSProduct(Connection con, String tp) { 
		 
		ProductFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try {     
            
            String tblnm= tp+"_sprdmsfl";
               
            String query2 = "Select pcode,pname,pack from "+tblnm+"  order by pname";
	        ps2=con.prepareStatement(query2);
	        rst2=ps2.executeQuery();

	            while (rst2.next())
				{
				  rfb = new ProductFormBean();
	        	  rfb.setPval(rst2.getInt(1));
	        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
                  data.add(rfb); 
  				 }
	            
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getSampleProduct " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	

	
	public List getSampleProduct(Connection con, int div,int gpcode,String identity,String search) { 
		 
		ProductFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
 
		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try {     
            
               
            String query2 = "Select pcode,pname,concat(pack,':',ifnull(shelf_life,'2017-07-01')) pack from prd where div_code=? and pd_group=? and identity=? and pcode<>0" +
            " and pname is not null and pname like ? order by shelf_life desc";
	        ps2=con.prepareStatement(query2);
	        ps2.setInt(1, div);
	        ps2.setInt(2, gpcode);
	        ps2.setString(3, identity);
	        ps2.setString(4, "%"+search+"%");
	        rst2=ps2.executeQuery();

	            while (rst2.next())
				{
				  rfb = new ProductFormBean();
	        	  rfb.setPval(rst2.getInt(1));
	        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
                  data.add(rfb); 
  				 }
	            
	            
	            
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getSampleProduct " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	

	public List getSCategory(Connection con, String tp) { 
		 
		ProductFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try {     
            
           
               
            String query2 = "select doc_tp,doc_name from docmast";
	        ps2=con.prepareStatement(query2);
	        rst2=ps2.executeQuery();

	            while (rst2.next())
				{
				  rfb = new ProductFormBean();
	        	  rfb.setCat_code(rst2.getString(1));
	        	  rfb.setCatname(rst2.getString(2)); 
                  data.add(rfb); 
  				 }
	            
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getSCategory " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	

	
	public List getSGroup(Connection con, int div,String type,int uid) { 
		 
		ProductFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		

		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try {     
            
           
            String tp="A";
            switch(div)
            {
            	case 6:
            	    tp="T";
            		break;
            	case 7:
            		tp="G";
            		break;
            	case 11:
            		tp="M";
            		break;
            	case 21:
            		tp="B";
            		break;
            }
			
            String query2 = "select gp_code,gp_name from grpmast where div_code=? and gp_code<>0";
            if(type.equalsIgnoreCase("PMT"))
            {
            	query2= "select depo_code,gp_name from grpmast where div_code=? and gp_code<>0 and gp_code in " +
            			"(select gp_code from pmt_group where access_t=? and user_id=? and status='Y') ";
            	  if(div>4 && div<10)
            		  div-=4;
            	  else if(div==11 || div==21)
            		  div-=1;
            } 
            
	        ps2=con.prepareStatement(query2);
	        ps2.setInt(1, div);
	        if(type.equalsIgnoreCase("PMT"))
	        {
	        	ps2.setString(2, tp);
	        	ps2.setInt(3, uid);
	        }
	        rst2=ps2.executeQuery();

	            while (rst2.next())
				{
				  rfb = new ProductFormBean();
	        	  rfb.setGpcode(rst2.getInt(1));
	        	  rfb.setGpname(rst2.getString(2)); 
                  data.add(rfb); 
  				 }
	            
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getSGroup " + e);
		}
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	

	
	public List getSParty(Connection con, String tp,int depo) { 
		 
		SampleRepo5FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<SampleRepo5FormBean> data = new ArrayList<SampleRepo5FormBean>();
		try {     
            String tblnm=null;
            tblnm=(tp+"_sfaacms2").toLowerCase(); 
            String query2 = "Select mac_code,mac_name from "+tblnm+" where depo_code=? order by mac_name ";
	        ps2 = con.prepareStatement(query2);
            ps2.setInt(1,depo);
	        rst2 = ps2.executeQuery();
	        
            while (rst2.next()) 
			{
			  rfb = new SampleRepo5FormBean();
        	  rfb.setPval(rst2.getString(1));
        	  rfb.setPname(rst2.getString(2)); 
              data.add(rfb); 
			} 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLOptDAO.getSampleParty " + e);
		}
		finally {
			try 
			{
            if(rst2 != null){rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}		
	
	public List getEmployee(Connection con, int div,int depo,int year) { 
		 
		SampleRepo5FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		
		List<SampleRepo5FormBean> data = new ArrayList<SampleRepo5FormBean>();
		try {     
            String query2 = "Select emp_code,emp_name from empmast where mkt_year=? and div_code=? and depo_code=? and emp_code<>'' order by emp_name ";
	        ps2 = con.prepareStatement(query2);
            ps2.setInt(1,year);
            ps2.setInt(2,div);
            ps2.setInt(3,depo);
	        rst2 = ps2.executeQuery();
	        
            while (rst2.next()) 
			{
			  rfb = new SampleRepo5FormBean();
        	  rfb.setPval(rst2.getString(1));
        	  rfb.setPname(rst2.getString(2)); 
              data.add(rfb); 
			} 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLOptDAO.getSampleParty " + e);
		}
		finally {
			try 
			{
            if(rst2 != null){rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}		

	
	public List getHQ(Connection con,int depo_code,String tp,int uid) { 
		 
		MktFormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;            
   	        tblnm=(tp+"_hq_master08").toLowerCase();
            txt1="H.Q.";
	        
            query2="select distinct(t.ter_code),t.ter_name,u.depo_code from "+tblnm+" as t,user_ter as u " +
            " where t.ter_code=u.ter_code and t.depo_code=u.depo_code   and t.ter_pt=u.access_t " +
            " and u.user_id="+uid+" and status='Y' ";

//            and t.ter_pt=u.access_t 
            
            
            ps2 = con.prepareStatement(query2);
	        rst2 = ps2.executeQuery();
	        String abc=null;
            while (rst2.next())
			{
				  rfb = new MktFormBean();
				  abc=rst2.getInt(3)+""+rst2.getInt(1);
	        	  rfb.setQty2(Integer.parseInt(abc));
	        	  //rfb.setQty2(rst2.getInt(1));
	        	  rfb.setNm2(rst2.getString(2)); 
	              rfb.setNm3(txt1);
                  data.add(rfb); 				
			} 
			
	            ps2.close();
	            rst2.close();
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getHQ " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}

		return data;
	}

	
	public List getHQRepo(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;            
   	        tblnm=(tp+"_hq_master08").toLowerCase();
            txt1="H.Q.";
	        
            query2="select distinct(t.ter_code),t.ter_name,u.depo_code from "+tblnm+" as t,user_ter as u " +
            " where t.ter_code=u.ter_code and t.depo_code=u.depo_code  " +
            " and u.user_id="+uid+" and status='Y' ";

            ps2 = con.prepareStatement(query2);
	        rst2 = ps2.executeQuery();
	        String abc=null;
            while (rst2.next())
			{
				  rfb = new Repo2FormBean();
				  abc=rst2.getInt(3)+""+rst2.getInt(1);
	        	  rfb.setQty2(Integer.parseInt(abc));
	        	  rfb.setNm2(rst2.getString(2)); 
	              rfb.setNm3(txt1);
                  data.add(rfb); 				
			} 
			
	            ps2.close();
	            rst2.close();
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getHQRepo " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in HQ-Repo SQLOptDAO.Connection.close "+e);
			  }
		}

		return data;
	}	
	

	public List getHQRepoNew(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo2FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;            
   	        tblnm=(tp+"_hq_master08").toLowerCase();
            txt1="H.Q.";
	        
            query2="select distinct(t.ter_code),t.ter_name,u.depo_code from "+tblnm+" as t,user_ter as u " +
            " where t.ter_code=u.ter_code and t.depo_code=u.depo_code  " +
            " and u.user_id=? and u.depo_code=? and status='Y' ";

            ps2 = con.prepareStatement(query2);
            ps2.setInt(1, uid);
            ps2.setInt(2, depo_code);
	        rst2 = ps2.executeQuery();
            while (rst2.next())
			{
				  rfb = new Repo2FormBean();
	        	  rfb.setQty2(rst2.getInt(1));
	        	  rfb.setNm2(rst2.getString(2)); 
	              rfb.setNm3(txt1);
                  data.add(rfb); 				
			} 
			
	            ps2.close();
	            rst2.close();
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLOptDAO.getHQRepoNew " + e);
		}
		
		finally {
			try 
			{
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in HQ-RepoNew SQLOptDAO.Connection.close "+e);
			  }
		}

		return data;
	}	

	
	public List getStk(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo9FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo9FormBean> data = new ArrayList<Repo9FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo9FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest " + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
	
	public List getStk10(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo10FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo10FormBean> data = new ArrayList<Repo10FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo10FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest 10" + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}		
	
	public List getStk12(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo12FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo12FormBean> data = new ArrayList<Repo12FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo12FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest 12" + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in HQ-12-SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}			
	
	public List getStk13(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo13FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo13FormBean> data = new ArrayList<Repo13FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo13FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest 13" + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in HQ-13-SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}				
	
	public List getStk15(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo15FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo15FormBean> data = new ArrayList<Repo15FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo15FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest 15" + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in HQ-15-SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}					
	public List getStk16(Connection con,int depo_code,String tp,int uid) { 
		 
		Repo16FormBean rfb;
		PreparedStatement ps2 =null;
		ResultSet rst2 =null;		
		List<Repo16FormBean> data = new ArrayList<Repo16FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            
   	        tblnm=(tp+"_account08").toLowerCase();
            txt1="STOCKIEST ";
		        
         String query2="select distinct(a.mac_code),a.mac_name,a.mcity,u.depo_code from "+tblnm+" as a," +
         " user_ter as u where u.depo_code=a.depo_code and u.ter_code=a.mterr_code " +
         " and u.user_id="+uid+" order by a.depo_code,a.mac_name " ;            
         ps2 = con.prepareStatement(query2);
         String abc=null;         
         rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new Repo16FormBean();
					  abc=rst2.getInt(4)+""+rst2.getString(1);
		        	  rfb.setMcode(abc);
		        	  rfb.setMname(rst2.getString(2) +", "+rst2.getString(3) ); 
		              rfb.setHead1(txt1);
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLOptDAO.getStockiest 16" + e);
		}
		finally {
			try {
	            if(rst2 != null){rst2.close();}
	            if(ps2 != null){ps2.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in HQ-16-SQLOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}				
	
	
	
//////////////////////////////Get All User Branch Coding Start Here///////////////////////////
	
	public List getUserDivision(Connection con,int uid) { 
		 
		LoginFormBean rfb;
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     
            
  	        
      	    
      	       String query1 = "select div_code,div_name,sales_abbr from divmast where div_code in "+ 
      	    	   			   "(select div_code from userdiv where user_id=? and user_status='Y') order by div_code";
      	       ps1 = con.prepareStatement(query1);
      	       ps1.setInt(1, uid);
      	       rst1 = ps1.executeQuery();
		       

		        while (rst1.next())
		        {
				  rfb = new LoginFormBean();
	        	  rfb.setDcode(rst1.getInt(1));
	        	  rfb.setDname(rst1.getString(2)); 
	        	  rfb.setD_type(rst1.getString(3)); 
                  data.add(rfb); 				
		        		  
		        }
	            rst1.close();
	            ps1.close();
	            
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getAllDivision...line 2386 " + e);
		}
	
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
	            if(rst1 != null){rst1.close();}
	            if(ps1 != null){ps1.close();}
	            if(con != null){con.close();}
	            
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			  }
		}
		return data;
	}
//////////////////////////////Get All User Branch Coding End Here///////////////////////////
	
	
	
	
//////////////////////////////Get DailyEntryMaster Coding Start Here///////////////////////////
	
	public List getDailyEntryMaster(Connection con,int div,int depo) { 

		LoginFormBean rfb;
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		

		List<LoginFormBean> data = new ArrayList<LoginFormBean>();
		try {     



			String query1 = "select depo_code,br_name from daily_entry_master where div_code=? and depo_code=?";
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, div);
			ps1.setInt(2, depo);
			rst1 = ps1.executeQuery();


			while (rst1.next())
			{
				rfb = new LoginFormBean();
				rfb.setDcode(rst1.getInt(1));
				rfb.setDname(rst1.getString(2)); 
				data.add(rfb); 				

			}
			rst1.close();
			ps1.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getDailyEntryMaster...line 2433 " + e);
		}

		finally {
			//enclose this in a finally block to make
			//sure the connection is closed
			try {
				if(rst1 != null){rst1.close();}
				if(ps1 != null){ps1.close();}
				if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			}
		}
		return data;
	}
	//////////////////////////////Get DailyEntryMaster Coding End Here///////////////////////////
	
	
//////////////////////////////Get DailyEntryMaster Coding Start Here///////////////////////////
	
	public LoginFormBean getTarget(Connection con,int div,int depo,int myear,int mno,String edate) { 

		LoginFormBean rfb=null;  
		PreparedStatement ps1 =null;
		ResultSet rst1 =null;		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		String ndate=null;
		try {
			ndate = df.format(df1.parse(edate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {     


			String query1 = "select round(budget_per,2),round(budget_br,2),round(sales_today,2),round(cn100,2),round(sales_trade,2),round(ach,2),round(surdef,2),round(last_month,2),round(last_year,2),round(collection,2),round(collection_cumm,2),round(remit,2),round(remit_cumm,2),round(outstand,2),ddate,status " +
			" from daily_entry where mkt_year=? and div_code=? and depo_code=? and mnth_code=(select mnth_code from monthfl where mkt_year=? and mnth_no=?)  " +
			"and amd_no in (select max(amd_no) from daily_entry where mkt_year=?  and div_code=? and depo_code=? and mnth_code=(select mnth_code from monthfl where mkt_year=? and mnth_no=?)  )";
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, myear);
			ps1.setInt(2, div);
			ps1.setInt(3, depo);
			ps1.setInt(4, myear);
			ps1.setInt(5, mno);
			ps1.setInt(6, myear);
			ps1.setInt(7, div);
			ps1.setInt(8, depo);
			ps1.setInt(9, myear);
			ps1.setInt(10, mno);
			rst1 = ps1.executeQuery();


			if (rst1.next())
			{
				rfb = new LoginFormBean();
				rfb.setBud_per(rst1.getDouble(1));
				rfb.setSales_bud(rst1.getDouble(2));
				if(rst1.getString(15).equals(ndate))
				{
					rfb.setSale_today(rst1.getDouble(3));
					rfb.setCn100(rst1.getDouble(4));
					rfb.setTrd_sale(rst1.getDouble(5));
					rfb.setAch(rst1.getDouble(6));
					rfb.setSurdef(rst1.getDouble(7));
					rfb.setLmsale(rst1.getDouble(8));
					rfb.setLysale(rst1.getDouble(9));
					rfb.setCollc(rst1.getDouble(10));
					rfb.setCollcumm(rst1.getDouble(11));
					rfb.setRemit(rst1.getDouble(12));
					rfb.setRemitcumm(rst1.getDouble(13));
					rfb.setOutstnd(rst1.getDouble(14));
					rfb.setStatus(rst1.getString(16)); 
				}
				
			}
			rst1.close();
			ps1.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========Exception in SQLOptDAO.getTarget...line 2486 " + e);
		}

		finally {
			//enclose this in a finally block to make
			//sure the connection is closed
			try {
				if(rst1 != null){rst1.close();}
				if(ps1 != null){ps1.close();}
				if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLOptDAO.Connection.close "+e);
			}
		}
		return rfb;
	}
	//////////////////////////////Get DailyEntryMaster Coding End Here///////////////////////////	
	
}
  