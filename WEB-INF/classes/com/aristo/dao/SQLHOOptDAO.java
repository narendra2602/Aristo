package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.aristo.valueobject.HORepo6FormBean;
import com.aristo.valueobject.HORepo7FormBean;
import com.aristo.valueobject.HORepo8FormBean;
import com.aristo.valueobject.SampleRepo3FormBean;


public class SQLHOOptDAO {

//////////////////////////////Get All Product Coding Start Here///////////////////////////
	
	public List getAllProduct(Connection con, String tp,int year,int uid,String utype) { 
		 
		HORepo6FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		List<HORepo6FormBean> data = new ArrayList<HORepo6FormBean>();
		try {     
            String tblnm=null;
            String query2=null;
            String txt1=null;
            tblnm=(tp+"_product08").toLowerCase();
            txt1="Product";
            
            if (utype.equals("PMT"))
            {
      		query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and " +
      		" pd_group in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
            }
            else 
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";            	
            }
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,year);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new HORepo6FormBean();
		        	  rfb.setPcode(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
		              rfb.setHead1(txt1);
                      data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllProduct " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		} 
	
		return data;
	}

	public List getAllProduct12(Connection con, String tp,int year,int uid,String utype) { 
		 
		HORepo8FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String query2=null;
            String txt1=null;
            tblnm=(tp+"_product08").toLowerCase();
            txt1="Product";
            
            if (utype.equals("PMT"))
            {
      		query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and " +
      		" pd_group in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
            }
            else 
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";            	
            }
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,year);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new HORepo8FormBean();
		        	  rfb.setPcode(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
		              rfb.setHead1(txt1);
                      data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllProduct " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		} 
	
		return data;
	}	
//////////////////////////////Get All Product Coding End Here///////////////////////////
	
//////////////////////////////Get All Group Coding Start Here///////////////////////////
	
	public List getAllGroup(Connection con, String tp,int eyear,int uid,String utype) { 
		  
		HORepo7FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		List<HORepo7FormBean> data = new ArrayList<HORepo7FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
            txt1="Group";
            if (utype.equals("PMT"))
  	        {
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" where gp_code in " +
            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_name";
  	        }
  	        else
  	        {
	  	        tblnm=(tp+"_groupsales08");
  //  	        tblnm=tp+"_group_mkt08";
//	            query2 = "Select gp_code,gp_name from "+tblnm+" where mkt_year=? order by gp_name";
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
			  rfb = new HORepo7FormBean();
        	  rfb.setGcode(rst2.getInt(1));
        	  rfb.setGname(rst2.getString(2)); 
              rfb.setHead1(txt1);
              data.add(rfb); 				
			 } 
	         ps2.close();
	         rst2.close();

		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllGroup " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

	public List getAllGroup13(Connection con, String tp,int eyear,int uid,String utype) { 
		  
		HORepo8FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
            txt1="Group";
            if (utype.equals("PMT"))
  	        {
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" where gp_code in " +
            " (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by gp_name";
  	        }
  	        else
  	        {
  	        tblnm=(tp+"_groupsales08").toLowerCase();
            query2 = "Select gp_code,gp_name from "+tblnm+" order by gp_name";
  	        }
	        ps2 = con.prepareStatement(query2);
	        
	        rst2 = ps2.executeQuery();

            while (rst2.next())
			{
			  rfb = new HORepo8FormBean();
        	  rfb.setPcode(rst2.getInt(1));
        	  rfb.setPname(rst2.getString(2)); 
              rfb.setHead1(txt1);
              data.add(rfb); 				
			 } 
	         ps2.close();
	         rst2.close();

		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllGroup13 " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
//////////////////////////////Get All Group Coding End Here///////////////////////////		
	
//////////////////////////////Get All Product Coding Start Here///////////////////////////
	
	public List getRepo8Product(Connection con, String tp,int year,int uid,String utype) { 
		 
		HORepo8FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
       	    tblnm=(tp+"_product08").toLowerCase();
       	    txt1="Product";
	       	if (utype.equals("PMT"))
	        {
	   		query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and " +
	   		" pd_group in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
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
				  rfb = new HORepo8FormBean();
	        	  rfb.setPcode(rst2.getInt(1));
	        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
	              rfb.setHead1(txt1);
                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllProduct " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

	
	public List getHOProduct(Connection con, String tp,int year,int uid,String utype) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try {     
            String tblnm=null;
            String query2=null;
            tblnm=(tp+"_product08").toLowerCase();
            if (utype.equals("PMT"))
            {
      		query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and " +
      		" pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by pname";
            }
            else 
            {
            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";            	
            }
            
//            query2 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";            	
            ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,year);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new SampleRepo3FormBean();
		        	  rfb.setPcode(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
                      data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getHO-Product " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO-HO.product.Connection.close "+e);
			  }
		} 
	
		return data;
	}	
	
	
	public String getHOPname(Connection con, String tp,int year,int pcode) { 
		 
		PreparedStatement ps2=null;
		ResultSet rst2 =null;		
		String pname=null;
		try {     
            String tblnm=null;
            String query2=null;
            tblnm=(tp+"_product08").toLowerCase();
            query2 = "Select pname,pack from "+tblnm+" where mkt_year=? and pcode=? order by pname";            	
            ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,year);
	        ps2.setInt(2,pcode);
	        rst2 = ps2.executeQuery();

            if (rst2.next())
			{
    	      pname=(rst2.getString(1)+","+rst2.getString(2)); 
			} 
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getHO-Pname " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO-HO.product.Connection.close "+e);
			  }
		} 
	
		return pname;
	}		
	
	
//////////////////////////////Get All Product Coding End Here///////////////////////////	


	
	
}
