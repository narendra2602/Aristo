package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cen.aristo.valueobject.InvFstFormBean;



public class SQLInvFstDAO implements InvFstDAO{

////////////////////////////////////////////Update InvFst//////////////////////////////////////	
	public int updateInvFst(List inv,Connection con, String typ)
	{
		   int i=0;
		   InvFstFormBean ifb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
	            String tblnm=null;
	            tblnm=typ+"_invfst";
                String query1 ="select depo_code from "+tblnm.toLowerCase()+" where depo_code=? and doc_type=? and inv_no=? ";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm.toLowerCase()+" set inv_lo=?,inv_yr=?,pinv_no=?,pinv_date=?,entry_dt=?,chl_no=?" +
						",chl_dt=?,mtr_no=?,mtr_dt=?,cases=?,due_Days=?,due_dt=?,transport=?,bank=?,drug_lc1=?,drug_lc2=?,gross_amt=?,tax_1=?" +
						",tax_2=?,stat_cd=?,bill_amt=?,lsale1=?,lsale2=?,lsale3=?,ltax1_per=?,ltax1_amt=?,ltax2_per=?,ltax2_amt=?,ltax3_per=?" +
						",ltax3_amt=?,ctax1_per=?,ctax1_amt=?,ctax2_per=?,ctax2_amt=?,ctax3_per=?,ctax3_amt=?,octroi=?,mfg_amt=?,inv_type=?" +
						",mr_mfg=?,vehicle_no=?,permit_no=?,permit_dt=?,trn_Cd=?,full_truck=?,dcm_truck=?,rate_perkg=?,load_type=?,tdst_cd=?" +
						",tot_weight=?,actual_wet=?,party_code=?,inv_date=? where depo_code=? and doc_type=? and inv_no=? ";   
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm.toLowerCase()+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				ps2 = con.prepareStatement(query2);
				con.setAutoCommit(false);
				Date javadt = new Date();
       	Iterator it = inv.iterator();
       	while(it.hasNext()) 
           	 {
       			ifb = (InvFstFormBean) it.next();        
				 
				ps1.setInt(1,ifb.getDepo_code());
				ps1.setInt(2, ifb.getDoc_type());
				ps1.setInt(3, ifb.getInv_no());
				
					
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					
					ps.setString(1, ifb.getInv_lo());
					ps.setInt(2, ifb.getInv_yr());
					ps.setString(3, ifb.getPinv_no());
					
					javadt = ifb.getPinv_date();
					ps.setDate(4,new java.sql.Date (javadt.getTime()));
					
					javadt = ifb.getEntry_dt();
					ps.setDate(5,new java.sql.Date (javadt.getTime()));

					ps.setString(6,ifb.getChl_no());
					javadt = ifb.getChl_dt();
					ps.setDate(7,new java.sql.Date (javadt.getTime()));

					ps.setString(8, ifb.getMtr_no());
					
					javadt = ifb.getMtr_dt();
					ps.setDate(9,new java.sql.Date (javadt.getTime()));

					ps.setInt(10, ifb.getCases());
					ps.setInt(11, ifb.getDue_days());
					
					javadt = ifb.getDue_dt();
					ps.setDate(12,new java.sql.Date (javadt.getTime()));
					
					
					ps.setString(13,ifb.getTransport());
					ps.setString(14,ifb.getBank());
					ps.setString(15,ifb.getDrug_lc1());
					ps.setString(16,ifb.getDrug_lc2());
					ps.setDouble(17,ifb.getGross_amt());
					ps.setDouble(18,ifb.getTax_1());
					ps.setDouble(19,ifb.getTax_2());
					ps.setInt(20, ifb.getStat_cd());
					ps.setDouble(21,ifb.getBill_amt());
					ps.setDouble(22,ifb.getLsale1());
					ps.setDouble(23,ifb.getLsale2());
					ps.setDouble(24,ifb.getLsale3());
					ps.setDouble(25,ifb.getLtax1_per());
					ps.setDouble(26,ifb.getLtax1_amt());
					ps.setDouble(27,ifb.getLtax2_per());
					ps.setDouble(28,ifb.getLtax2_amt());
					ps.setDouble(29,ifb.getLtax3_per());
					ps.setDouble(30,ifb.getLtax3_amt());
					ps.setDouble(31,ifb.getCtax1_per());
					ps.setDouble(32,ifb.getCtax1_amt());
					ps.setDouble(33,ifb.getCtax2_per());
					ps.setDouble(34,ifb.getCtax2_amt());
					ps.setDouble(35,ifb.getCtax3_per());
					ps.setDouble(36,ifb.getCtax3_amt());
					ps.setDouble(37,ifb.getOctroi());
					ps.setDouble(38,ifb.getMfg_amt());
					ps.setString(39,ifb.getInv_type());
					ps.setDouble(40, ifb.getMr_mfg());
					ps.setString(41,ifb.getVehicle_no());
					ps.setString(42,ifb.getPermit_no());
					javadt = ifb.getPermit_dt();
					ps.setDate(43,new java.sql.Date (javadt.getTime()));
					ps.setInt(44, ifb.getTrn_cd());
					ps.setDouble(45,ifb.getFull_truck());
					ps.setDouble(46,ifb.getDcm_truck());
					ps.setDouble(47,ifb.getRate_perkg());
					ps.setString(48,ifb.getLoad_typ());
					ps.setInt(49, ifb.getTdst_cd());
					ps.setDouble(50, ifb.getTot_weight());
					ps.setDouble(51, ifb.getActual_wet());
					ps.setString(52, ifb.getParty_code());
					javadt = ifb.getInv_date();
					ps.setDate(53,new java.sql.Date (javadt.getTime()));
					ps.setInt(54,ifb.getDepo_code());
					ps.setInt(55, ifb.getDoc_type());
					ps.setInt(56, ifb.getInv_no());
					 
					i= i + ps.executeUpdate();
			  	  
				} 
				else
				{ 
					

					ps2.setInt(1,ifb.getDepo_code());
					ps2.setInt(2, ifb.getDoc_type());
					ps2.setString(3, ifb.getParty_code());
					ps2.setInt(4, ifb.getInv_no());
					ps2.setString(5, ifb.getInv_lo());
					ps2.setByte(6, (byte) ifb.getInv_yr());
					javadt = ifb.getInv_date();
					ps2.setDate(7,new java.sql.Date (javadt.getTime()));
					
					ps2.setString(8, ifb.getPinv_no());
					
					javadt = ifb.getPinv_date();
					ps2.setDate(9,new java.sql.Date (javadt.getTime()));

					javadt = ifb.getEntry_dt();
					ps2.setDate(10,new java.sql.Date (javadt.getTime()));
					
					ps2.setString(11,ifb.getChl_no());
					
					javadt = ifb.getChl_dt();
					ps2.setDate(12,new java.sql.Date (javadt.getTime()));

					
					ps2.setString(13, ifb.getMtr_no());
					
					javadt = ifb.getMtr_dt();
					ps2.setDate(14,new java.sql.Date (javadt.getTime()));
					
					ps2.setInt(15, ifb.getCases());
					ps2.setInt(16, ifb.getDue_days());
					
					javadt = ifb.getDue_dt();
					ps2.setDate(17,new java.sql.Date (javadt.getTime()));
					
					ps2.setString(18,ifb.getTransport());
					ps2.setString(19,ifb.getBank());
					ps2.setString(20,ifb.getDrug_lc1());
					ps2.setString(21,ifb.getDrug_lc2());
					ps2.setDouble(22,ifb.getGross_amt());
					ps2.setDouble(23,ifb.getTax_1());
					ps2.setDouble(24,ifb.getTax_2());
					ps2.setInt(25, ifb.getStat_cd());
					ps2.setDouble(26,ifb.getBill_amt());
					ps2.setDouble(27,ifb.getLsale1());
					ps2.setDouble(28,ifb.getLsale2());
					ps2.setDouble(29,ifb.getLsale3());
					ps2.setDouble(30,ifb.getLtax1_per());
					ps2.setDouble(31,ifb.getLtax1_amt());
					ps2.setDouble(32,ifb.getLtax2_per());
					ps2.setDouble(33,ifb.getLtax2_amt());
					ps2.setDouble(34,ifb.getLtax3_per());
					ps2.setDouble(35,ifb.getLtax3_amt());
					ps2.setDouble(36,ifb.getCtax1_per());
					ps2.setDouble(37,ifb.getCtax1_amt());
					ps2.setDouble(38,ifb.getCtax2_per());
					ps2.setDouble(39,ifb.getCtax2_amt());
					ps2.setDouble(40,ifb.getCtax3_per());
					ps2.setDouble(41,ifb.getCtax3_amt());
					ps2.setDouble(42,ifb.getOctroi());
					ps2.setDouble(43,ifb.getMfg_amt());
					ps2.setString(44,ifb.getInv_type());
					ps2.setDouble(45, ifb.getMr_mfg());
					ps2.setString(46,ifb.getVehicle_no());
					ps2.setString(47,ifb.getPermit_no());
					
					javadt = ifb.getPermit_dt();
					ps2.setDate(48,new java.sql.Date (javadt.getTime()));
					
					ps2.setInt(49, ifb.getTrn_cd());
					ps2.setDouble(50,ifb.getFull_truck());
					ps2.setDouble(51,ifb.getDcm_truck());
					ps2.setDouble(52,ifb.getRate_perkg());
					ps2.setString(53,ifb.getLoad_typ());
					ps2.setInt(54, ifb.getTdst_cd());
					ps2.setDouble(55, ifb.getTot_weight());
					ps2.setDouble(56, ifb.getActual_wet());

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
				System.out.println("-------------Exception in SQLInvFSTDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLINVFSTDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
	} 
	
	
}
