package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.InvFstFormBean;

public class SQLInvFstDAO{

////////////////////////////////////////////Update InvFst//////////////////////////////////////	
	public int updateInvFst(List inv,Connection con, String typ)
	{
		    int i=0;
		    InvFstFormBean ifb;
		    PreparedStatement ps = null;
		    PreparedStatement ps1 = null;
		    PreparedStatement ps2 = null;
		    ResultSet rs =null;
		    
			try 
			{
            String tblnm=null;
            tblnm=(typ+"_sinvfst").toLowerCase();
            String query1 ="select depo_code from "+tblnm+" where depo_code=? and doc_type=? and inv_no=? ";
            ps1 = con.prepareStatement(query1);
                
			String query ="update "+tblnm+" set inv_lo=?,inv_yr=?,pinv_no=?,pinv_date=?,chl_no=?" +
			", chl_dt=?,mtr_no=?,mtr_dt=?,order_no=?,order_dt=?,cases=?,due_days=?,due_dt=?,transport=?,bank=?" +
			", drug_lc1=?,drug_lc2=?,mr_cd=?,stat_cd=?,area_cd=?,regn_cd=?,terr_cd=?,dist_cd=?,bill_amt=?, " +
			"  inv_type=?,rem1=?,party_code=?,inv_date=? where depo_code=? and doc_type=? and inv_no=? ";   
	
			ps = con.prepareStatement(query);  
               
			String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			ps2 = con.prepareStatement(query2);
			con.setAutoCommit(false);
			Date javadt = new Date();
          	Iterator it = inv.iterator();
          	
       	    while(it.hasNext()) 
           	 {
       			ifb = (InvFstFormBean) it.next();        
				 
				ps1.setInt(1,ifb.getDepo_code());
				ps1.setInt(2,ifb.getDoc_type());
				ps1.setInt(3,ifb.getInv_no());
					
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,ifb.getInv_lo());
					ps.setInt(2,ifb.getInv_yr());
					ps.setString(3,ifb.getPinv_no());
					javadt = ifb.getPinv_date();
					ps.setDate(4,new java.sql.Date (javadt.getTime()));
					ps.setString(5,ifb.getChl_no());
					javadt = ifb.getChl_dt();
					ps.setDate(6,new java.sql.Date (javadt.getTime()));
					ps.setString(7,ifb.getMtr_no());
					javadt = ifb.getMtr_dt();
					ps.setDate(8,new java.sql.Date (javadt.getTime()));
					ps.setString(9,ifb.getOrder_no());
					javadt = ifb.getOrder_dt();
					ps.setDate(10,new java.sql.Date (javadt.getTime()));
					ps.setInt(11,ifb.getCases());
					ps.setInt(12,ifb.getDue_days());
					javadt = ifb.getDue_dt();
					ps.setDate(13,new java.sql.Date (javadt.getTime()));
					ps.setString(14,ifb.getTransport());
					ps.setString(15,ifb.getBank());
					ps.setString(16,ifb.getDrug_lc1());
					ps.setString(17,ifb.getDrug_lc2());
					ps.setInt(18,ifb.getMr_cd());					
					ps.setInt(19,ifb.getStat_cd());
					ps.setInt(20,ifb.getArea_cd());
					ps.setInt(21,ifb.getRegn_cd());					
					ps.setInt(22,ifb.getTerr_cd());
					ps.setInt(23,ifb.getDist_cd());					
					ps.setDouble(24,ifb.getBill_amt());
					ps.setString(25,ifb.getInv_type());
					ps.setString(26,ifb.getRem1());
					ps.setString(27,ifb.getParty_code());
					javadt = ifb.getInv_date();
					ps.setDate(28,new java.sql.Date (javadt.getTime()));
					ps.setInt(29,ifb.getDepo_code());
					ps.setInt(30,ifb.getDoc_type());
					ps.setInt(31,ifb.getInv_no());
					i= i + ps.executeUpdate();
			  	  
				} 
				else
				{ 
					ps2.setInt(1,ifb.getDepo_code());
					ps2.setInt(2,ifb.getDoc_type());
					ps2.setString(3,ifb.getParty_code());
					ps2.setInt(4,ifb.getInv_no());
					ps2.setString(5,ifb.getInv_lo());
					ps2.setByte(6,(byte) ifb.getInv_yr());
					javadt = ifb.getInv_date();
					ps2.setDate(7,new java.sql.Date (javadt.getTime()));
					ps2.setString(8,ifb.getPinv_no());
					javadt = ifb.getPinv_date();
					ps2.setDate(9,new java.sql.Date (javadt.getTime()));
					ps2.setString(10,ifb.getChl_no());
					javadt = ifb.getChl_dt();
					ps2.setDate(11,new java.sql.Date (javadt.getTime()));
					ps2.setString(12,ifb.getMtr_no());
					javadt = ifb.getMtr_dt();
					ps2.setDate(13,new java.sql.Date (javadt.getTime()));
					ps2.setString(14,ifb.getOrder_no());
					javadt = ifb.getOrder_dt();
					ps2.setDate(15,new java.sql.Date (javadt.getTime()));
					ps2.setInt(16, ifb.getCases());
					ps2.setInt(17, ifb.getDue_days());
					javadt = ifb.getDue_dt();
					ps2.setDate(18,new java.sql.Date (javadt.getTime()));
					ps2.setString(19,ifb.getTransport());
					ps2.setString(20,ifb.getBank());
					ps2.setString(21,ifb.getDrug_lc1());
					ps2.setString(22,ifb.getDrug_lc2());
					ps2.setInt(23,ifb.getMr_cd());
					ps2.setInt(24,ifb.getStat_cd());
					ps2.setInt(25,ifb.getArea_cd());
					ps2.setInt(26,ifb.getRegn_cd());
					ps2.setInt(27,ifb.getTerr_cd());
					ps2.setInt(28,ifb.getDist_cd());					
					ps2.setDouble(29,ifb.getBill_amt());
					ps2.setString(30,ifb.getInv_type());
					ps2.setString(31,ifb.getRem1());
					i= i + ps2.executeUpdate();
					
				}
				
				rs.close(); 
		 }	

		       	con.commit();
		        con.setAutoCommit(true);

       			ps.close();
       			ps1.close();
       			ps2.close();

       			ps=null;
       			ps1=null;
       			ps2=null; 
       			
       			rs=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLSampleInvFSTDAO.update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			             if(con != null){con.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLSAMPLEINVFSTDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
	} 
	

	public int updateFstFl(List inv,Connection con, String typ,int depo)
	{
		    int i=0;
		    InvFstFormBean ifb;
		    PreparedStatement ps = null;
		    PreparedStatement ps1 = null;
		    PreparedStatement ps2 = null;
		    PreparedStatement ps3 = null;
		    PreparedStatement ps4 = null;
		    ResultSet rs =null;
		    String br=null;
			try 
			{
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            tblnm=(typ+"_fstfl").toLowerCase();
            tblnm1=(typ+"_invfst").toLowerCase();
            tblnm2=(typ+"_faacms2").toLowerCase();
            tblnm3=(typ+"f_trans").toLowerCase();

    		String query11 = "select mac_code from "+tblnm2+" where musr_cd=? ";
    		ps=con.prepareStatement(query11);
    		ps.setInt(1, depo);
    		rs=ps.executeQuery();
    		if (rs.next())
    		{
    			br=rs.getString(1);
    		}
    		
    		rs.close();
    		ps.close();
    		
			String query4 ="update "+tblnm3+" set transit='Y' where depo_code=? and lrno=? and lrdate=? ";   
            ps4 = con.prepareStatement(query4);

            String query3 ="update "+tblnm1+" set inv_type='Y' where party_code=? and mtr_no=? and mtr_dt=? ";   
            ps3 = con.prepareStatement(query3);
            
            String query1 ="select depo_code from "+tblnm+" where depo_code=? and doc_type=? and inv_no=? ";
            ps1 = con.prepareStatement(query1);
                
			String query ="update "+tblnm+" set pinv_no=?,pinv_date=?,mtr_no=?,mtr_dt=?,bill_amt=?, " +
			"  inv_date=? where depo_code=? and doc_type=? and inv_no=? ";   
	
			ps = con.prepareStatement(query);  
               
			String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?)"; 
			ps2 = con.prepareStatement(query2);
			con.setAutoCommit(false);
			Date javadt = new Date();
          	Iterator it = inv.iterator();
          	
       	    while(it.hasNext()) 
           	 {
       			ifb = (InvFstFormBean) it.next();        
				

       			ps4.setInt(1, ifb.getDepo_code());
       			ps4.setString(2, ifb.getMtr_no());
				javadt = ifb.getMtr_dt();
				ps4.setDate(3,new java.sql.Date (javadt.getTime()));
				i= i + ps4.executeUpdate();

       			ps3.setString(1, br);
       			ps3.setString(2, ifb.getMtr_no());
				javadt = ifb.getMtr_dt();
				ps3.setDate(3,new java.sql.Date (javadt.getTime()));
				i= i + ps3.executeUpdate();
       			
				ps1.setInt(1,ifb.getDepo_code());
				ps1.setInt(2,ifb.getDoc_type());
				ps1.setInt(3,ifb.getInv_no());
					
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,ifb.getPinv_no());
					javadt = ifb.getPinv_date();
					ps.setDate(2,new java.sql.Date (javadt.getTime()));
					ps.setString(3,ifb.getMtr_no());
					javadt = ifb.getMtr_dt();
					ps.setDate(4,new java.sql.Date (javadt.getTime()));
					ps.setDouble(5,ifb.getBill_amt());
					javadt = ifb.getInv_date();
					ps.setDate(6,new java.sql.Date (javadt.getTime()));
					ps.setInt(7,ifb.getDepo_code());
					ps.setInt(8,ifb.getDoc_type());
					ps.setInt(9,ifb.getInv_no());
					i= i + ps.executeUpdate();
				} 
				else
				{ 
					ps2.setInt(1,ifb.getDepo_code());
					ps2.setInt(2,ifb.getDoc_type());
					ps2.setInt(3,ifb.getInv_no());
					javadt = ifb.getInv_date();
					ps2.setDate(4,new java.sql.Date (javadt.getTime()));
					ps2.setString(5,ifb.getPinv_no());
					javadt = ifb.getPinv_date();
					ps2.setDate(6,new java.sql.Date (javadt.getTime()));
					ps2.setString(7,ifb.getMtr_no());
					javadt = ifb.getMtr_dt();
					ps2.setDate(8,new java.sql.Date (javadt.getTime()));
					ps2.setDouble(9,ifb.getBill_amt());
					i= i + ps2.executeUpdate();
				}
				
				rs.close(); 
		 }	
		       	con.commit();
		        con.setAutoCommit(true);

       			ps.close();
       			ps1.close();
       			ps2.close();
       			ps=null;
       			ps1=null;
       			ps2=null; 
       			rs=null;
       			ps3=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in (SQLINVFSTDAO)SQLFSTFLDAO.update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			             if(ps3 != null){ps3.close();}
			//             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLFSFLTDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
	} 
		
	
	
}
