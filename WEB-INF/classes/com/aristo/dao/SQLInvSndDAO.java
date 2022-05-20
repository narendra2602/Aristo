package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.aristo.valueobject.InvSndFormBean;

public class SQLInvSndDAO {

////////////////////////////////////////////Update InvSnd//////////////////////////////////////	
	public int updateInvSnd(List inv,Connection con, String typ)
	{
		    int i=0;
		    InvSndFormBean ifb;
		    PreparedStatement ps = null;
		    PreparedStatement ps1 = null;
		    PreparedStatement ps2 = null;
		    ResultSet rs =null;
			try 
			{
            String tblnm=null;
            tblnm=(typ+"_sinvsnd").toLowerCase();
            
            String query1 ="select sdepo_code from "+tblnm+" where sdepo_code=? and skno=? ";
            ps1 = con.prepareStatement(query1);
            
			String query ="update "+tblnm+" set sdoc_type=?,sinv_no=?,sinv_lo=?,sinv_yr=?,sinv_dt=?,strn_tp=?" +
			",sprt_cd=?,sprd_cd=?,spd_gp=?,sale_type=?,sbatch_no=?,smfg_dt=?,sexp_dt=?,sqty=?,sfree_qty=?,oct_type=?,octroi=?" +
			",sdel_tg=?,smr_cd=?,stat_cd=?,area_cd=?,inv_dsm=?,terr_cd=?,inv_dist=?,br_tag=?,ndays=?,sunit_cd=? " +
			" where sdepo_code=? and skno=?";     
	
			ps = con.prepareStatement(query);  
           
			String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
			ps2 = con.prepareStatement(query2);
			con.setAutoCommit(false);
			Date javadt = new Date();
   	        Iterator it = inv.iterator();
   	        while(it.hasNext()) 
       	    {
       			ifb = (InvSndFormBean) it.next();        
				 
				ps1.setInt(1,ifb.getSdepo_code());
				ps1.setInt(2,ifb.getSkno());
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setInt(1, ifb.getSdoc_type());
					ps.setInt(2, ifb.getSinv_no());
					ps.setString(3, ifb.getSinv_lo());
					ps.setInt(4, ifb.getSinv_yr());
					javadt = ifb.getSinv_dt();
					ps.setDate(5,new java.sql.Date (javadt.getTime()));
					ps.setString(6,ifb.getStrn_tp());
					ps.setString(7, ifb.getSprt_cd());					
					ps.setInt(8,ifb.getSprd_cd());
					ps.setInt(9,ifb.getSpd_gp());
					ps.setInt(10,ifb.getSale_type());
					ps.setString(11,ifb.getSbatch_no());
					ps.setString(12,ifb.getSmfg_dt());
					ps.setString(13,ifb.getSexp_dt());
					ps.setInt(14,ifb.getSqty());
					ps.setInt(15,ifb.getSfree_qty());
					ps.setString(16,ifb.getOct_type());
					ps.setDouble(17,ifb.getOctroi());
					ps.setString(18,ifb.getSdel_tg());
					ps.setInt(19,ifb.getSmr_cd());
					ps.setInt(20,ifb.getStat_cd());
					ps.setInt(21,ifb.getArea_cd());
					ps.setInt(22,ifb.getInv_dsm());
					ps.setInt(23,ifb.getTerr_cd());
					ps.setInt(24,ifb.getInv_dist());
					ps.setString(25,ifb.getBr_tag());
					ps.setInt(26,ifb.getNdays());
					ps.setInt(27,ifb.getSunit_cd());					
					ps.setInt(28,ifb.getSdepo_code());
					ps.setInt(29, ifb.getSkno());
					i= i + ps.executeUpdate();
				} 
				else
				{ 
					ps2.setInt(1,ifb.getSdepo_code());
					ps2.setInt(2, ifb.getSdoc_type());
					ps2.setInt(3, ifb.getSinv_no());
					ps2.setString(4, ifb.getSinv_lo());
					ps2.setInt(5, ifb.getSinv_yr());
					javadt = ifb.getSinv_dt();
					ps2.setDate(6,new java.sql.Date (javadt.getTime()));
					ps2.setString(7,ifb.getStrn_tp());
					ps2.setString(8, ifb.getSprt_cd());
					ps2.setInt(9,ifb.getSprd_cd());
					ps2.setInt(10,ifb.getSpd_gp());
					ps2.setInt(11,ifb.getSale_type());
					ps2.setString(12,ifb.getSbatch_no());
					ps2.setString(13,ifb.getSmfg_dt());
					ps2.setString(14,ifb.getSexp_dt());
					ps2.setInt(15,ifb.getSqty());
					ps2.setInt(16,ifb.getSfree_qty());
					ps2.setString(17,ifb.getOct_type());
					ps2.setDouble(18,ifb.getOctroi());
					ps2.setString(19,ifb.getSdel_tg());
					ps2.setInt(20,ifb.getSmr_cd());
					ps2.setInt(21,ifb.getStat_cd());
					ps2.setInt(22,ifb.getArea_cd());
					ps2.setInt(23,ifb.getInv_dsm());					
					ps2.setInt(24,ifb.getTerr_cd());					
					ps2.setInt(25,ifb.getInv_dist());					
					ps2.setString(26,ifb.getBr_tag());
					ps2.setInt(27,ifb.getNdays());
					ps2.setInt(28,ifb.getSkno());
					ps2.setInt(29,ifb.getSunit_cd());
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
				System.out.println("-------------Exception in SQLSampleInvSNDDAO.update " + ex);
				i=-1;
			}
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			//             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLSAMPLEINVSNDDAO.Connection.close "+e);
				  }
			}
			
			return i;
	} 
	
}   