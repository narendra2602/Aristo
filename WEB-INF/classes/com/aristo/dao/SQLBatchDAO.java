package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.aristo.valueobject.BatchMasterFormBean;

public class SQLBatchDAO {

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List  getAllbatch(Connection con,String typ,int pcode ) { 
		 
		BatchMasterFormBean bfb;
		PreparedStatement ps = null;
		ResultSet rst =null;
		
		List<BatchMasterFormBean> data = new ArrayList<BatchMasterFormBean>();
		try { 
            String tblnm=null;
          	tblnm=(typ+"_sbatmsfl").toLowerCase();
            	
///////////////////////////////////Batch Master Query///////////////////////////////////////////
            String query = "select bat_pcode,bat_no,bat_expdt,bat_indct,bat_clos,bat_tag " +
            " from "+tblnm+" where bat_pcode=? ";
			ps = con.prepareStatement(query);
			ps.setInt(1,pcode);
			
			rst = ps.executeQuery();
			while (rst.next())
			{ 
				bfb = new BatchMasterFormBean();
				bfb.setBat_pcode(rst.getInt(1));
				bfb.setBat_no(rst.getString(2));
				bfb.setBat_expdt(rst.getString(3));
				bfb.setBat_indct(rst.getString(4));
				bfb.setBat_clos(rst.getInt(5));
				bfb.setBat_tag(rst.getString(6));
				data.add(bfb); 
			}
			rst.close();
			ps.close();
			ps = null;
			rst =null;	
		}
		catch (Exception e) 
		{
		System.out.println("========Exception in SQLSampleBatchDAO.getAllbatch " + e);
		}
		finally 
		{
		  try 
		    {
	             if(rst != null){ rst.close();}
	             if(ps != null){ ps.close();}
	             if(con != null){con.close();}
			} 
		      catch (SQLException e) 
		          {
				   System.out.println("-------------Exception in SQLSampleBatchDAO.Connection.close "+e);
			      } 
		}
		return data;
	}	
	
////////////////////////////////////////////Update Batch//////////////////////////////////////	
	public int updatebatch(List bat,Connection con,String typ)
	{
		   int i=0;
		   BatchMasterFormBean bfb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
			 
				con.setAutoCommit(false); 
	            String tblnm=null;
	            tblnm=(typ+"_sbatmsfl").toLowerCase();
                String query1 ="select depo_code from "+tblnm+" where depo_code=? and bat_pcode=? and bat_no=? ";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm+" set bat_expdt=?,bat_indct=?,bat_clos=?,bat_tag=?,expiry=? " +
						" where depo_code=? and bat_pcode=? and bat_no=? ";   
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?)"; 
				ps2 = con.prepareStatement(query2);
				Date javadt = new Date();

		       	Iterator it = bat.iterator();
		    	while(it.hasNext()) 
		    	{
       			bfb = (BatchMasterFormBean) it.next();        
				 
				ps1.setInt(1,bfb.getDepo_code());
				ps1.setInt(2, bfb.getBat_pcode());
				ps1.setString(3, bfb.getBat_no());
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,bfb.getBat_expdt());
					ps.setString(2,bfb.getBat_indct());
					ps.setInt(3,bfb.getBat_clos());
					ps.setString(4,bfb.getBat_tag());
					javadt = bfb.getExpiry();
					ps.setDate(5,new java.sql.Date (javadt.getTime()));
					ps.setInt(6,bfb.getDepo_code());
					ps.setInt(7,bfb.getBat_pcode());
					ps.setString(8,bfb.getBat_no());
					i= i + ps.executeUpdate();
					
				} 
				else
				{ 

					ps2.setInt(1,bfb.getDepo_code());
					ps2.setInt(2,bfb.getBat_pcode());
					ps2.setString(3,bfb.getBat_no());
					ps2.setString(4,bfb.getBat_expdt());
					ps2.setString(5,bfb.getBat_indct());
					ps2.setInt(6,bfb.getBat_clos());
					ps2.setString(7,bfb.getBat_tag());
					javadt = bfb.getExpiry();
					ps2.setDate(8,new java.sql.Date (javadt.getTime()));
					i= i + ps2.executeUpdate();
				}
				rs.close(); 
       	 }	
		        con.commit();
		        con.setAutoCommit(true);

       			ps1.close();
       			ps2.close();
       			ps.close();

       			ps1=null;
       			ps2=null;
       			ps=null; 
       			
       			rs=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLSampleBatchDAO.update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(rs != null){ rs.close();}
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			            // if(con != null){con.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLSampleBatchDAO.Connection.close "+e);
				  }
			}
			return i;
		   
	} 

	public int updateBat(List bat,Connection con,String typ)
	{
		   int i=0;
		   BatchMasterFormBean bfb;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
			try {
			 
				con.setAutoCommit(false); 
	            String tblnm=null;
	            tblnm=(typ+"_batch08").toLowerCase();
                String query1 ="delete from "+tblnm+" where depo_code=? ";
               
				String query2 ="insert into "+tblnm+" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				ps2 = con.prepareStatement(query2);
				Date javadt = new Date();

		       	Iterator it = bat.iterator();
		       	boolean flag=true;
		    	while(it.hasNext()) 
		    	{
		    		bfb = (BatchMasterFormBean) it.next();
	       			if (flag)
	       			{
	                ps1 = con.prepareStatement(query1);
					ps1.setInt(1,bfb.getDepo_code());
					ps1.executeUpdate();
					flag=false;
	       			}
				 
					ps2.setInt(1,bfb.getDepo_code());
					ps2.setInt(2,bfb.getBat_pcode());
					ps2.setString(3,bfb.getBat_no());
					ps2.setString(4,bfb.getBat_mfgdt());
					ps2.setString(5,bfb.getBat_expdt());
					ps2.setDouble(6,bfb.getBat_netrt());
					ps2.setDouble(7,bfb.getBat_trdrt());
					ps2.setDouble(8,bfb.getBat_mrprt());
					ps2.setDouble(9,bfb.getBat_mfgrt());
					ps2.setDouble(10,bfb.getBat_hosrt());
					ps2.setDouble(11,bfb.getBat_excrt());
					ps2.setString(12,bfb.getBat_indct());
					ps2.setInt(13,bfb.getBat_clos());
					ps2.setDouble(14,bfb.getBat_purrt());					
					ps2.setString(15,bfb.getBat_tag());
					javadt = bfb.getBat_rcpdt();
					ps2.setDate(16,new java.sql.Date (javadt.getTime()));
					javadt = bfb.getExpiry();
					ps2.setDate(17,new java.sql.Date (javadt.getTime()));
					ps2.setInt(18,bfb.getGift_code());
					ps2.setInt(19,bfb.getFact_code());
					ps2.setString(20,bfb.getRemark());
					
					i= i + ps2.executeUpdate();
		    	}	
		        con.commit();
		        con.setAutoCommit(true);

       			ps1.close();
       			ps2.close();

       			ps1=null;
       			ps2=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in COM.SQLBatchDAO.updateBat " + ex);
				i=-1;
			}
			
			finally {
				try {
					     System.out.println("No. of Records Delete/Insert : "+i);
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in COM.SQLBatchDAO.updateBat.Connection.close "+e);
				  }
			}
			return i;
		   
	} 	
	
	
	
	
} 