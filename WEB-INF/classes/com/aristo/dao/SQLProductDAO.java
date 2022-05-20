package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aristo.valueobject.ProductFormBean;

public class SQLProductDAO implements ProductDAO{

	public List getAllProduct(Connection con, String typ,int eyear) { 
		 
		ProductFormBean pfb;
	    PreparedStatement ps = null;
	    ResultSet rst =null;

		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try { 
			
            String tblnm=null;
            String tblnm1=null;
        	tblnm=(typ+"_Product08").toLowerCase();
       	    tblnm1=(typ+"_groupsales08").toLowerCase(); 
		
			String query = "select p.pcode,p.pname,p.pack,p.pd_group,g.gp_name,p.mcode," +
		       "p.mgrp from " +tblnm +" as p, "+tblnm1+
		       " as g where p.pd_group=g.gp_code and p.mkt_year=? order by p.pcode,p.pname";

			
			ps = con.prepareStatement(query);
			ps.setInt(1,eyear);
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				pfb = new ProductFormBean();
				pfb.setPcode(rst.getInt(1));
				pfb.setPname(rst.getString(2));
				pfb.setPack(rst.getString(3));
				pfb.setPd_group(rst.getInt(4));
				pfb.setMname(rst.getString(5));
				pfb.setMcode(rst.getInt(6));
				pfb.setMgrp(rst.getInt(7));
				pfb.setLink1("Edit");
				data.add(pfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLProductDAO.getAllProduct " + e);
		}
		finally {
			try {
				   if(rst != null){rst.close();}  
				   if(ps != null){ ps.close();}
				   if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProductDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}


	public int AddProduct(ProductFormBean b, Connection con,String typ,int eyear) { 
		
	    PreparedStatement ps = null;
	    PreparedStatement ps1 = null;
	    ResultSet rst =null;
	    int i=0;
		try { 
            String tblnm=null;
            String query=null;
            String query1=null;            
        	tblnm=(typ+"_Product08").toLowerCase();
        	query1 = " select * from "+tblnm+" where pcode=? and mkt_year=? ";
        	ps1 = con.prepareStatement(query1);
        	ps1.setInt(1,b.getPcode());
        	ps1.setInt(2,eyear);
        	rst = ps1.executeQuery();
        	        	
			if (rst.next())
			{
				return 2;
			}
        	rst.close();
			ps1.close();
        	
			query =" insert into " +tblnm +" (pcode,pname,pack,pd_group,mcode,mname,mgrp,mkt_year,tn,pd_type) value (?,?,?,?,?,?,?,?,?,?) ";
			ps = con.prepareStatement(query);
			ps.setInt(1,b.getPcode());
			ps.setString(2,b.getPname());			
			ps.setString(3,b.getPack());
			ps.setInt(4,b.getPd_group());			
			ps.setInt(5,b.getMcode());
			ps.setString(6,b.getMname());
			ps.setInt(7,b.getMgrp());
			ps.setInt(8,eyear);
			ps.setString(9,b.getTn());
			ps.setString(10,typ);
			i=ps.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLProductDAO.AddProduct " + e);
		}
		finally {
			try {
				   if(rst != null){ rst.close();}
				   if(ps1 != null){ps1.close();}				   	
				   if(ps != null){ ps.close();}
				   if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProductDAO.Connection.close "+e);
			  }
		}
		
		return i;
	}	
	
	public List getGlist(Connection con, String typ,int eyear) { 
		 
		ProductFormBean pfb;
	    PreparedStatement ps = null;
	    ResultSet rst =null;
		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try { 
            String tblnm1=null;
       	    tblnm1=(typ+"_groupsales08").toLowerCase(); 
		
			String query = "select gp_code,gp_name from " +tblnm1+" order by gp_name";
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next())
			{
				pfb = new ProductFormBean();
				pfb.setGcode(rst.getInt(1));
				pfb.setGname(rst.getString(2));
				data.add(pfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLProductDAO.getGlist " + e);
		}
		finally {
			try {
				   if(rst != null){rst.close();}  
				   if(ps != null){ ps.close();}
				   if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProductDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}	
	
	public List getGplist(Connection con, String typ,int eyear) { 
		 
		ProductFormBean pfb;
	    PreparedStatement ps = null;
	    ResultSet rst =null;
		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try { 
            String tblnm1=null;
       	    tblnm1=(typ+"_group_mkt08").toLowerCase(); 
		
			String query = "select gp_code,gp_name from " +tblnm1+" where mkt_year=? order by gp_name";
			ps = con.prepareStatement(query);
			ps.setInt(1,eyear);
			rst = ps.executeQuery();
			 
			while (rst.next())
			{
				pfb = new ProductFormBean();
				pfb.setGpcode(rst.getInt(1));
				pfb.setGpname(rst.getString(2));
				data.add(pfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLProductDAO.getGplist " + e);
		}
		finally {
			try {
				   if(rst != null){rst.close();}  
				   if(ps != null){ ps.close();}
				   if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLProductDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}	
	
	public ProductFormBean EditProduct(Connection con,String typ, int pcode)
	{
		ProductFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		try {
			String tblnm=null;
			tblnm=(typ+"_product08").toLowerCase();			
			ps = con.prepareStatement("Select pcode,pname,pack,pd_group,mcode,mname,mgrp,mkt_year,tn,pd_type from "+tblnm+" where pcode=? ");
			ps.setInt(1,pcode);
			rs = ps.executeQuery();   
			if (rs.next())
			{
                lf = new ProductFormBean();
            	lf.setPcode(rs.getInt(1));
    			lf.setPname(rs.getString(2));			
    			lf.setPack(rs.getString(3));
    			lf.setPd_group(rs.getInt(4));			
    			lf.setMcode(rs.getInt(5));
    			lf.setMname(rs.getString(6));
    			lf.setMgrp(rs.getInt(7));
    			lf.setTn(rs.getString(9));    			
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
				System.out.println("-------------Exception in SQLProductDAO-EditProduct.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}	
	
	public int UpdateProduct(Connection con,ProductFormBean tf, String typ)
	{
		PreparedStatement ps =null;
		int i=0;
		try {
			String tblnm=null;
			tblnm=(typ+"_product08").toLowerCase();
			ps = con.prepareStatement("update "+tblnm+" set pname=?,pack=?,pd_group=?,mcode=?,mname=?,mgrp=?,tn=? where pcode=? ");
			ps.setString(1,tf.getPname());
			ps.setString(2,tf.getPack());
			ps.setInt(3,tf.getPd_group());
			ps.setInt(4,tf.getMcode());
			ps.setString(5,tf.getMname());
			ps.setInt(6,tf.getMgrp());
			ps.setString(7,tf.getTn());
			ps.setInt(8,tf.getPcode());			
			
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
				System.out.println("-------------Exception in SQLProductDAO-UpdateProduct.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}		
	
	
	
}
