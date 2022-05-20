package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fac.aristo.valueobject.FacRepo1FormBean;
import fac.aristo.valueobject.FacRepo5FormBean;

public class SQLFOptDAO {

	public List getAllBranch(Connection con,String tp) { 
		 
		FacRepo1FormBean rfb;
		
		List<FacRepo1FormBean> data = new ArrayList<FacRepo1FormBean>();
		try {     
            
            String tblnm=null;
       	    tblnm="f_branch";
		        
	        String query2 = "Select distinct(depo_code),depo_name from "+tblnm+" order by depo_name" ;
		    PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new FacRepo1FormBean();
		        	  rfb.setBrval(rst2.getString(1));
		        	  rfb.setBrname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLFOptDAO.getAllBranch " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLFOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
		
	
	public List getAllProduct(Connection con,String tp) { 
		 
		FacRepo5FormBean rfb;
		
		List<FacRepo5FormBean> data = new ArrayList<FacRepo5FormBean>();
		try {     
            
            String tblnm=null;
       	    tblnm="f_product";
		        
	        String query2 = "Select distinct(pcode),pname from "+tblnm+" where division='"+tp+"' order by pname" ;
		    PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new FacRepo5FormBean();
		        	  rfb.setBrval(rst2.getInt(1));
		        	  rfb.setBrname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLFOptDAO.getAllProduct " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLFOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}		
	

	
	public List getProduct(Connection con,String tp,String typ,String loc) { 
		 
		FacRepo5FormBean rfb;
		
		List<FacRepo5FormBean> data = new ArrayList<FacRepo5FormBean>();
		try {     
            
            String tblnm=null;
       	    tblnm="f_product";
		        
	        String query2;
	        if (loc!=null)
	        {
		     query2="select distinct pcode,pname from "+tblnm+
		     " where pcode in (select distinct(pcode) from f_trans where division='"+tp+"' and locationid='"+loc+"')"+
	         " and division='"+tp+"' and type='"+typ+"' order by pname";
	        }
	        else
	        {
	         query2= "Select distinct(pcode),pname from "+tblnm+" where division='"+tp+"' and type='"+typ+"' order by pname" ;
	        }
		         
	        	
		    PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new FacRepo5FormBean();
		        	  rfb.setBrval(rst2.getInt(1));
		        	  rfb.setBrname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLFOptDAO.getProduct " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLFOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}		
	
	

	public List getLProduct(Connection con,String tp,String loc) { 
		 
		FacRepo5FormBean rfb;
		
		List<FacRepo5FormBean> data = new ArrayList<FacRepo5FormBean>();
		try {     
            
            String tblnm=null;
       	    tblnm="f_product";
		        
//	        String query2 = "Select distinct(pcode),pname from "+tblnm+" where division='"+tp+"' and type='"+typ+"' order by pname" ;
	        String query2="select distinct pcode,pname from "+tblnm+ 
	        " where pcode in (select distinct(pcode) from f_trans where division='"+tp+"' and locationid='"+loc+"')"+
	        " and division='"+tp+"' and type='P' order by pname";
	        PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new FacRepo5FormBean();
		        	  rfb.setBrval(rst2.getInt(1));
		        	  rfb.setBrname(rst2.getString(2)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLFOptDAO.getAllProduct " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLFOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}		
	

	
	public List getAllLocation(Connection con,String tp,String fac) { 
		 
		FacRepo1FormBean rfb;
		
		List<FacRepo1FormBean> data = new ArrayList<FacRepo1FormBean>();
		try {     
	        String query2 = "select loc_city,loc_id from f_location " +
	        " where loc_city is not null and comp_code='"+fac+"'order by loc_city ";
			
//	        String query2 = "select loc_city,loc_id from f_location " +
//	        " where loc_city is not null order by comp_code ";
		    PreparedStatement ps2 = con.prepareStatement(query2);
		    ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new FacRepo1FormBean();
		        	  rfb.setLcval(rst2.getString(2));
		        	  rfb.setLcname(rst2.getString(1)); 
	                  data.add(rfb); 				
  				 } 
	            ps2.close(); 
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLFOptDAO.getAllLocation " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLFOptDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}	
			
	
	
	
}
