package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HOForm1FormBean;
import com.aristo.valueobject.HOForm2FormBean;
import com.aristo.valueobject.HOForm4FormBean;


public class SQLHOMktDAO {

	
//////////////////////////////Get All Product Coding Start Here///////////////////////////
	
	public List getAllProduct(Connection con, String tp,int year,int uid,String utype) { 
		 
		HOForm1FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;
		List<HOForm1FormBean> data = new ArrayList<HOForm1FormBean>();
		try {     
            
            String tblnm=null;
            String txt1=null;
            String query2=null;
   	        tblnm=(tp+"_product08").toLowerCase();
            txt1="PRODUCT";
            
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
					  rfb = new HOForm1FormBean();
		        	  rfb.setPcode(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
		              rfb.setHead1(txt1);
                      data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOMktDAO.getAllProduct " + e);
		}
		finally {
			try 
			{
		           if(rst2 != null){ rst2.close();}
		           if(ps2 != null){ps2.close();}
		           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOMktDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Product Coding End Here///////////////////////////
	

//////////////////////////////Get All Group Coding Start Here///////////////////////////
	
	public List getAllGroup(Connection con, String tp,int eyear,int uid,String utype) { 
		 
		HOForm2FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;

		List<HOForm2FormBean> data = new ArrayList<HOForm2FormBean>();
		try {     

        String tblnm=null;
        String txt1=null;
        String query2=null;
        
        tblnm=(tp+"_group_mkt08");
        String tblnm1=(tp+"_product08");
        txt1="GROUP";
        
        if (utype.equals("PMT"))
        {
        //tblnm=(tp+"_groupsales08").toLowerCase();
       // tblnm=(tp+"_group_mkt08").toLowerCase();
       // query2 = "Select gp_code,gp_name from "+tblnm+" where gp_code in " +
       // " (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by gp_name";
        
        query2 = "select gp_code,gp_name from "+tblnm+" where  mkt_year = ? having gp_code in ("+
        		"select distinct mgrp from "+tblnm1+" where mkt_year = ? and pd_group " +
        		"in (select gp_code from pmt_group where user_id="+uid+" and access_t=? and status='Y')) "+
        		" ";
        }
        else
        {
        tblnm=(tp+"_group_mkt08").toLowerCase();
//        tblnm=tp+"_groupsales08";
        query2 = "Select gp_code,gp_name from "+tblnm+"  where mkt_year=? order by gp_name";
        }
        ps2 = con.prepareStatement(query2);
        if (!utype.equals("PMT"))
        {
         ps2.setInt(1, eyear);	
        }
        else
        {
            ps2.setInt(1, eyear);	
            ps2.setInt(2, eyear);	
            ps2.setString(3, tp);	
        	
        }
        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new HOForm2FormBean();
		        	  rfb.setPcode(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)); 
		              rfb.setHead1(txt1);
	                    data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOOptDAO.getAllGroup " + e);
		}
		finally {
			try {
		           if(rst2 != null){ rst2.close();}
		           if(ps2 != null){ps2.close();}
		           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}

//////////////////////////////Get All Group Coding End Here///////////////////////////			
	
//////////////////////////////Get All Form Product Coding Start Here///////////////////////////
	
	public List getHOForm4Product(Connection con, String tp,int year,int uid,String utype) { 
		 
		HOForm4FormBean rfb;
		PreparedStatement ps2=null;
		ResultSet rst2 =null;
		
		List<HOForm4FormBean> data = new ArrayList<HOForm4FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String query2=null;
   	        tblnm=(tp+"_product08").toLowerCase();
            txt1="PRODUCT";
        
            
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
			  rfb = new HOForm4FormBean();
        	  rfb.setPcode(rst2.getInt(1));
        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
              rfb.setHead1(txt1);
              data.add(rfb); 				
			 }
            
            ps2.close();
            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHOMktDAO.getHOForm4Product " + e);
		}
		finally {
			try 
			{
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHOMktDAO.Connection.close "+e);
			  }
		}
		return data;
	}
//////////////////////////////Get All Form4 Product Coding End Here///////////////////////////	
	
}
