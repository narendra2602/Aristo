package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cen.aristo.valueobject.CentralRepo4FormBean;



public class SQLDispatchDAO implements DispatchDAO{
	
	public List getAllBranch(Connection con,String tp) { 
		 
		CentralRepo4FormBean rfb;
		
		List<CentralRepo4FormBean> data = new ArrayList<CentralRepo4FormBean>();
		try {     
            
            String tblnm=null;
       	    tblnm=tp+"_faacms2";
		        
	        String query2 = "Select mac_code,mcity from "+tblnm.toLowerCase()+" where mgrp_code=32100 order by mcity";
		    PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new CentralRepo4FormBean();
		        	  rfb.setBrval(rst2.getString(1));
		        	  rfb.setBrname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLDispatchDAO.getAllBranch " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLDispatchDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
		
}
