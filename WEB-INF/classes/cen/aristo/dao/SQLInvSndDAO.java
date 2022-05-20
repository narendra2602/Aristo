package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cen.aristo.valueobject.InvSndFormBean;



public class SQLInvSndDAO implements InvSndDAO{

////////////////////////////////////////////Update InvSnd//////////////////////////////////////	
	public int updateInvSnd(List inv,Connection con, String typ,String ymon)
	{
		   int i=0;
		   InvSndFormBean ifb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   PreparedStatement ps3 = null;
		   ResultSet rs =null;
			try {
	            String tblnm=null;
	            tblnm=(typ+"_invsnd").toLowerCase();
				con.setAutoCommit(false);
	            
                String query3 ="update "+tblnm+" set sdel_tg='Y' where ymonth='"+ymon+"' ";
                ps3 = con.prepareStatement(query3);
                ps3.executeUpdate();
                con.commit();
                ps3.close();
	            
                String query1 ="select sdepo_code from "+tblnm+" where sdepo_code=? and skno=? ";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm+" set sdoc_type=?,sinv_no=?,sinv_lo=?,sinv_yr=?,sinv_dt=?,spinv_no=?,spinv_dt=?,sentry_dt=?,strn_tp=?" +
				",sprt_cd=?,sprd_cd=?,spd_gp=?,sale_type=?,sbatch_no=?,smfg_dt=?,sexp_dt=?,sqty=?,sfree_qty=?,stax_cd1=?,stax_cd2=?,oct_type=?,octroi=?" +
				",srate_net=?,srate_pur=?,srate_trd=?,srate_mrp=?,srate_hos=?,srate_mfg=?,srate_exc=?,samnt=?,sdel_tg=?,smr_cd=?,stat_cd=?" +
				",stax_type=?,secess=?,srate_net1=?,srate_trd1=?,srate_mrp1=?,actual_wet=?,ymonth=? " +
				" where sdepo_code=? and skno=?  ";     
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
				ps2 = con.prepareStatement(query2);
				Date javadt = new Date();
				System.out.println(inv.size());
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

					ps.setString(6, ifb.getSpinv_no());
				
					javadt = ifb.getSpinv_dt();
					ps.setDate(7,new java.sql.Date (javadt.getTime()));

					javadt = ifb.getSentry_dt();
					ps.setDate(8,new java.sql.Date (javadt.getTime()));
										
					ps.setString(9,ifb.getStrn_tp());
					ps.setString(10, ifb.getSprt_cd());					
					ps.setInt(11,ifb.getSprd_cd());
					ps.setInt(12,ifb.getSpd_gp());
					ps.setInt(13,ifb.getSale_type());
					ps.setString(14,ifb.getSbatch_no());
					ps.setString(15,ifb.getSmfg_dt());
					ps.setString(16,ifb.getSexp_dt());
					ps.setInt(17,ifb.getSqty());
					ps.setInt(18,ifb.getSfree_qty());
					ps.setDouble(19,ifb.getStax_cd1());
					ps.setDouble(20,ifb.getStax_cd2());
					ps.setString(21,ifb.getOct_type());
					ps.setDouble(22,ifb.getOctroi());
					ps.setDouble(23,ifb.getSrate_net());
					ps.setDouble(24,ifb.getSrate_pur());
					ps.setDouble(25,ifb.getSrate_trd());
					ps.setDouble(26,ifb.getSrate_mrp());
					ps.setDouble(27,ifb.getSrate_hos());
					ps.setDouble(28,ifb.getSrate_mfg());
					ps.setDouble(29,ifb.getSrate_exc());
					ps.setDouble(30,ifb.getSamnt()); 
					ps.setString(31,ifb.getSdel_tg());
					ps.setInt(32,ifb.getSmr_cd());
					ps.setInt(33,ifb.getStat_cd());					
					ps.setString(34,ifb.getStax_type());
					ps.setDouble(35,ifb.getSecess()); 
					ps.setDouble(36,ifb.getSrate_net1()); 
					ps.setDouble(37,ifb.getSrate_trd1()); 
					ps.setDouble(38,ifb.getSrate_mrp1()); 
					ps.setDouble(39,ifb.getActual_wet());  
					ps.setString(40, ifb.getYmonth());
					ps.setInt(41,ifb.getSdepo_code());
					ps.setInt(42, ifb.getSkno());
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
					
					ps2.setString(7, ifb.getSpinv_no());
					
					javadt = ifb.getSpinv_dt();
					ps2.setDate(8,new java.sql.Date (javadt.getTime()));

					javadt = ifb.getSentry_dt();
					ps2.setDate(9,new java.sql.Date (javadt.getTime()));
					
					ps2.setString(10,ifb.getStrn_tp());
					ps2.setString(11, ifb.getSprt_cd());
					ps2.setInt(12,ifb.getSprd_cd());
					ps2.setInt(13,ifb.getSpd_gp());
					ps2.setInt(14,ifb.getSale_type());
					ps2.setString(15,ifb.getSbatch_no());
					ps2.setString(16,ifb.getSmfg_dt());
					ps2.setString(17,ifb.getSexp_dt());
					ps2.setInt(18,ifb.getSqty());
					ps2.setInt(19,ifb.getSfree_qty());
					ps2.setDouble(20,ifb.getStax_cd1());
					ps2.setDouble(21,ifb.getStax_cd2());
					ps2.setString(22,ifb.getOct_type());
					ps2.setDouble(23,ifb.getOctroi());
					ps2.setDouble(24,ifb.getSrate_net());
					ps2.setDouble(25,ifb.getSrate_pur());
					ps2.setDouble(26,ifb.getSrate_trd());
					ps2.setDouble(27,ifb.getSrate_mrp());
					ps2.setDouble(28,ifb.getSrate_hos());
					ps2.setDouble(29,ifb.getSrate_mfg());
					ps2.setDouble(30,ifb.getSrate_exc());
					ps2.setDouble(31,ifb.getSamnt()); 
					ps2.setString(32,ifb.getSdel_tg());
					ps2.setInt(33,ifb.getSmr_cd());
					ps2.setInt(34,ifb.getStat_cd());					
					ps2.setString(35,ifb.getStax_type());
					ps2.setDouble(36,ifb.getSecess()); 
					ps2.setDouble(37,ifb.getSrate_net1()); 
					ps2.setDouble(38,ifb.getSrate_trd1()); 
					ps2.setDouble(39,ifb.getSrate_mrp1()); 
					ps2.setDouble(40,ifb.getActual_wet());  
					ps2.setInt(41, ifb.getSkno());
					ps2.setString(42, ifb.getYmonth());
					i= i + ps2.executeUpdate();
					
				}
				 
				rs.close(); 
       	 }	

/*		        query3 ="delete from "+tblnm+" where sdel_tg='Y' and ymonth='"+ymon+"' ";
		        ps3 = con.prepareStatement(query3);
                ps3.executeUpdate();
		        ps3.close();
*/
       	
		       	con.commit();
		        con.setAutoCommit(true);

       			ps.close();
       			ps1.close();
       			ps2.close();

       			ps=null;
       			ps1=null;
       			ps2=null; 
       			ps3=null; 
       			
       			rs=null;

			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLInvSNDDAO.update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			             if(ps3 != null){ps3.close();}
			             if(con != null){con.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLINVSNDDAO.Connection.close "+e);
				  }
			}
			
			return i;
		   
	} 
	
	
}
   