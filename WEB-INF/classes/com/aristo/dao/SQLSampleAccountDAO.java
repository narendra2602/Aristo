package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.AccountFormBean;


public class SQLSampleAccountDAO implements AccountDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List getAllAccount(Connection con, int depo, String typ,int eyear,String utype,int uid) { 
		 
		AccountFormBean afb;
		PreparedStatement ps = null;
		ResultSet rst=null;
		List<AccountFormBean> data = new ArrayList<AccountFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
        	tblnm=(typ+"_sfaacms2").toLowerCase();
    	    tblnm1=(typ+"_stermst").toLowerCase();
    	    
    	    String query="select a.emp_code,a.mac_name,a.mbanker,a.mphone,a.mcontact,t.ter_name from "+tblnm+" " +
    	    " as a inner join "+tblnm1+" as t on a.mterr_code=t.ter_code " +
    	    " where a.mac_type='Y' and a.depo_code=? and t.depo_code=? and a.mkt_year=? and t.mkt_year=? ";

    	    ps = con.prepareStatement(query);
			ps.setInt(1,depo);
			ps.setInt(2,depo);
			ps.setInt(3,eyear);
			ps.setInt(4,eyear);
			rst = ps.executeQuery();
			while (rst.next())
			{
				afb = new AccountFormBean();
				afb.setMac_code(rst.getString(1));
				afb.setMac_name(rst.getString(2));
				afb.setMbanker(rst.getString(3));
				afb.setMphone(rst.getString(4));
				afb.setMcontct(rst.getString(5));
				afb.setMadd1(rst.getString(6));
				data.add(afb);
			}
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLAccountDAO.getAllFS " + e);
		}
		finally {
			try {
		             if(rst != null){ rst.close();}
		             if(ps != null){ ps.close();}
		             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLAccountDAO.Connection.close "+e);
			  }
		}
		return data;
	}	
	
////////////////////////////////////////////Update Account//////////////////////////////////////	
	public int updateAccount(String typ,List acc,Connection con)
	{
		   int i=0;
		   AccountFormBean t=null;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 =null;
		   PreparedStatement ps2 = null;
		   Date javadt = new Date();
		   ResultSet rs = null;
		   try {
				String tblnm=null;
	            tblnm=(typ+"_sfaacms2").toLowerCase();

                String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and MAC_CODE=? and MKT_YEAR=?";
                ps1 = con.prepareStatement(query1);
              
				String query ="update "+tblnm+" set MGRP_CODE=?," +
				" MAC_TYPE=?,MAC_PRFX=?,MAC_NAME=?,MADD1=?,MADD2=?,MADD3=?,MCITY=?,MPIN=?,MPHONE=?," +
				" MCONTACT=?,MDLNO1=?,MDLNO2=?,MTRANSPT=?,MPST_NO=?," +
				" MCST_NO=?,MSTAT_CODE=?,MAREA_CODE=?,MREGION_CD=?,MTERR_CODE=?,MDIST_CD=?,MTYPE=?,MBANKER=?,JOINING_DT=?,RESIGNA_DT=? " +
				" where depo_code=? and mac_code=? and mkt_year=?";  
                ps = con.prepareStatement(query);  

				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps2 = con.prepareStatement(query2);
              
                con.setAutoCommit(false);
       	        Iterator it = acc.iterator();
       	        while(it.hasNext()) 
       	        {
					t = (AccountFormBean) it.next();        
					ps1.setInt(1,t.getDepo_code());
					ps1.setString(2, t.getMac_code());
					ps1.setInt(3,t.getMkt_year());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setInt(1,t.getMgrp_code());
					ps.setString(2,t.getMac_type());
					ps.setString(3,t.getMac_prfx());
					ps.setString(4,t.getMac_name());
					ps.setString(5,t.getMadd1());
					ps.setString(6,t.getMadd2());
					ps.setString(7,t.getMadd3());
					ps.setString(8,t.getMcity());
					ps.setString(9,t.getMpin());
					ps.setString(10,t.getMphone());
					ps.setString(11,t.getMcontct());
					ps.setString(12,t.getMdlno1());
					ps.setString(13,t.getMdlno2());
					ps.setString(14,t.getMtranspt());
					ps.setString(15,t.getMpst_no());
					ps.setString(16,t.getMcst_no());
					ps.setInt(17,t.getMstat_code());
					ps.setInt(18,t.getMarea_code());
					ps.setInt(19,t.getMregion_cd());
					ps.setInt(20,t.getMterr_code());
					ps.setInt(21,t.getMdist_cd());
					ps.setString(22,t.getMtype());
					ps.setString(23, t.getMbanker());
					javadt = t.getJoining_dt();
					ps.setDate(24,new java.sql.Date (javadt.getTime()));
					javadt = t.getResigna_dt();
					ps.setDate(25,new java.sql.Date (javadt.getTime()));
					ps.setInt(26,t.getDepo_code());
					ps.setString(27,t.getMac_code());
					ps.setInt(28,t.getMkt_year());
					
					i= i + ps.executeUpdate();
				}
				else
				{
					ps2.setInt(1,t.getDepo_code());
					ps2.setInt(2,t.getMgrp_code());
					ps2.setString(3,t.getMac_code());
					ps2.setString(4,t.getMac_type());
					ps2.setString(5,t.getMac_prfx());
					ps2.setString(6,t.getMac_name());
					ps2.setString(7,t.getMadd1());
					ps2.setString(8,t.getMadd2());
					ps2.setString(9,t.getMadd3());
					ps2.setString(10,t.getMcity());
					ps2.setString(11,t.getMpin());
					ps2.setString(12,t.getMphone());
					ps2.setString(13,t.getMcontct());
					ps2.setString(14,t.getMdlno1());
					ps2.setString(15,t.getMdlno2());
					ps2.setString(16,t.getMtranspt());
					ps2.setString(17,t.getMpst_no());
					ps2.setString(18,t.getMcst_no());
					ps2.setInt(19,t.getMstat_code());
					ps2.setInt(20,t.getMarea_code());
					ps2.setInt(21,t.getMregion_cd());
					ps2.setInt(22,t.getMterr_code());
					ps2.setInt(23,t.getMdist_cd());
					ps2.setString(24,t.getMtype());
					ps2.setInt(25,t.getMkt_year());
					ps2.setString(26, t.getMbanker());
					javadt = t.getJoining_dt();
					ps2.setDate(27,new java.sql.Date (javadt.getTime()));
					javadt = t.getResigna_dt();
					ps2.setDate(28,new java.sql.Date (javadt.getTime()));
					ps2.setString(29,t.getMbank_add1());
					i= i + ps2.executeUpdate();
				}
				
				rs.close(); 
       	 }//// End of while//////////////////////////	
		
      		con.commit();
       		con.setAutoCommit(true);
       		ps.close();
       		ps1.close();
       		ps2.close();

    		} catch (SQLException ex) {
    			try {
    				con.rollback();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    			System.out.println("-------------Exception in SQLSampleAccountDAO.pARTYmASTER " + ex);
    			i=-1;
    		}
    		finally {
    			try {
    				   System.out.println("No. of Records Update/Insert : "+i);
    		             if(rs != null){ rs.close();}
    		             if(ps != null){ ps.close();}
    		             if(ps1 != null){ps1.close();}
    		             if(ps2 != null){ps2.close();}    		             
    		    //         if(con != null){con.close();}
    			} catch (SQLException e) {
    				System.out.println("-------------Exception in SQLSampleAccountDAO.Connection.close "+e);
    			  }
    		}
			return i;
	}
	
}