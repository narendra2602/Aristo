package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.AccountFormBean;


public class SQLAccountDAO implements AccountDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List getAllAccount(Connection con, int depo, String typ,int eyear,String utype,int uid) { 
		 
		AccountFormBean afb;
		PreparedStatement ps = null;
		ResultSet rst=null;
		List<AccountFormBean> data = new ArrayList<AccountFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            
        	tblnm=(typ+"_account08").toLowerCase();
    	    tblnm1=(typ+"_hq_master08").toLowerCase();
    	    tblnm2=(typ+"_msr_master08").toLowerCase();
			
///////////////////////////////////Account Master Query///////////////////////////////////////////
    	    String query = null;
    	    System.out.println(utype);
    	    System.out.println(uid);
    	    System.out.println(depo);
   		if (utype.equals("HQ"))
   		{
        
    		query= "select a.mac_code,ifnull(a.mac_name,'No Party Name') as mac_name,ifnull(a.mcity,'CITY NA') as mcity,h.ter_name,m.msr_name "+ 
   			" from "+tblnm+" a, "+
   			" (select depo_code,ter_code from user_ter where user_id=?) t, "+
   			" (select mkt_year,depo_code,ter_code,ter_name from "+tblnm1+" where mkt_year = ?) h, "+
   			" (select mkt_year,depo_code,msr_cd,msr_name from "+tblnm2+" where mkt_year = ?) m "+
   			" where "+
   			" a.depo_code = t.depo_code "+ 
   			" and a.mterr_code = t.ter_code "+
   			" and h.depo_code = t.depo_code  "+
   			" and h.ter_code = t.ter_code "+
   			" and a.mkt_year = ? "+
   			" and a.msr_code = m.msr_cd "+ 
   			" and a.depo_code = m.depo_code ";
   			
   			/*query= " select a.mac_code,ifnull(a.mac_name,'No Party Name') as mac_name,ifnull(a.mcity,'CITY NA') as mcity,h.ter_name,m.msr_name " + 
            " from a_account08 as a,a_hq_master08 as h,a_msr_master08 as m " +
            " where a.depo_code in (select distinct depo_code from user_ter where user_id=?) " +
            " and h.depo_code in (select distinct depo_code from user_ter where user_id=?) " +
            " and m.depo_code in (select distinct depo_code from user_ter where user_id=?) and a.mkt_year=? and h.mkt_year=? and m.mkt_year=? and " + 
            " a.mterr_code in (select ter_code from user_ter where user_id=? and depo_code in (select distinct depo_code from user_ter where user_id=?)) " +
            " and  h.ter_code in (select ter_code from user_ter where user_id=? and depo_code in (select distinct depo_code from user_ter where user_id=?)) and a.msr_code=m.msr_cd and a.mac_code is not null"; */
   		}
   		else
   		{
            query = "select a.mac_code,ifnull(a.mac_name,'No Party Name') as mac_name,ifnull(a.mcity,'CITY NA') as mcity,h.ter_name,m.msr_name " +
    		"from "+tblnm+" as a,"+tblnm1+" as h,"+tblnm2+" as m " +
    		"where a.depo_code=? and h.depo_code=? and m.depo_code=? and a.mkt_year=? and h.mkt_year=? and m.mkt_year=? and " +
    		" a.mterr_code=h.ter_code and a.msr_code=m.msr_cd and a.mac_code is not null";
            

   		}
   		
            ps = con.prepareStatement(query);
			
	   		if (utype.equals("HQ"))
	   		{
				ps.setInt(1, uid);
				ps.setInt(2, eyear);
				ps.setInt(3, eyear);
				ps.setInt(4, eyear);

	   		}
	   		else
	   		{
				ps.setInt(1, depo);
				ps.setInt(2, depo);
				ps.setInt(3, depo);
				ps.setInt(4, eyear);
				ps.setInt(5, eyear);
				ps.setInt(6, eyear);

	   		}
			
			rst = ps.executeQuery();
			

			
			//String a=null;
			char b=0;
			while (rst.next())
			{
				afb = new AccountFormBean();
				afb.setMac_code(rst.getString(1));
				b= rst.getString(2).charAt(0);
				if ((b>64) && (b<91))
				afb.setMac_name(rst.getString(2)+", "+rst.getString(3));
				else
				afb.setMac_name("N.A.");	
//				afb.setMcity(rst.getString(3).replace(':',' '));
	//			a=rst.getString(3).s
				//System.out.println(a);
				if (rst.getString(4).equals(null))
				 b= rst.getString(4).charAt(0);
				if ((b>64) && (b<91))
				 afb.setMadd1(rst.getString(4));
				else
				 afb.setMadd1("N.A.");	
				
				if (rst.getString(5).equals(null))
				b= rst.getString(5).charAt(0);
				if ((b>64) && (b<91))
					afb.setMadd2(rst.getString(5));
				else
					 afb.setMadd1("N.A.");	
				
					
				data.add(afb);
			}
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLAccountDAO.getAllAccount " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
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
		   
		   ResultSet rs = null;
		   try {
				String tblnm=null;
	            tblnm=(typ+"_account08").toLowerCase();

                String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and MAC_CODE=? and MKT_YEAR=? and MGRP_CODE=?";
                ps1 = con.prepareStatement(query1);
              
				String query ="update "+tblnm+" set MCMP_CODE=?,MUSR_CD=?," +
				"MAC_TYPE=?,MAC_PRFX=?,MAC_NAME=?,MADD1=?,MADD2=?,MADD3=?,MCITY=?,MPIN=?,MPHONE=?," +
				"MCONTCT=?,MDLNO1=?,MDLNO2=?,MBANKER=?,MBANK_ADD1=?,MBANK_ADD2=?,MTRANSPT=?,MPST_NO=?," +
				"MCST_NO=?,MFIX_DISC1=?,MFIX_DISC2=?,MFIX_TAX1=?,MFIX_TAX2=?,MSTAT_CODE=?,MAREA_CODE=?," +
				"MAREA_CD1=?,MREGION_CD=?,MREGIO_CD1=?,MTERR_CODE=?,MTERR_CD1=?,MDIST_CD=?,MTYPE=?," +
				"MDAYS=?,OCTROI1=?,OCTROI2=?,OCTROI3=?,CST_TYPE=?,MSR_CODE=?,MSR_CODE1=?,MCURR_BAL=?," +
				"MCURR_DB=?,MOPNG_BAL=?,MOPNG_DB=?,MLOPNG_BAL=?,MLOPNG_DB=?,MLCLOS_BAL=?,MLCLOS_DB=?," +
				"MNTH_BAL=?,MNTH_DR=?,MNTH_CR=?,MNTH_DB=?,PTYPE=?,MDIST1=?,SLCT=? " +
				" where depo_code=? and mac_code=? and mkt_year=? and mgrp_code=? " ;  
                ps = con.prepareStatement(query);  

				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps2 = con.prepareStatement(query2);
              
              con.setAutoCommit(false);
       	      Iterator it = acc.iterator();
       	      while(it.hasNext()) 
           	  
       	      {
       			t = (AccountFormBean) it.next();        
				ps1.setInt(1,t.getDepo_code());
				ps1.setString(2, t.getMac_code());
				ps1.setInt(3,t.getMkt_year());
				ps1.setInt(4,t.getMgrp_code());
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setInt(1,t.getMcmp_code());
					ps.setInt(2,t.getMusr_cd());
					ps.setString(3,t.getMac_type());
					ps.setString(4,t.getMac_prfx());
					ps.setString(5,t.getMac_name());
					ps.setString(6,t.getMadd1());
					ps.setString(7,t.getMadd2());
					ps.setString(8,t.getMadd3());
					ps.setString(9,t.getMcity());
					ps.setString(10,t.getMpin());
					ps.setString(11,t.getMphone());
					ps.setString(12,t.getMcontct());
					ps.setString(13,t.getMdlno1());
					ps.setString(14,t.getMdlno2());
					ps.setString(15,t.getMbanker());
					ps.setString(16,t.getMbank_add1());
					ps.setString(17,t.getMbank_add2());
					ps.setString(18,t.getMtranspt());
					ps.setString(19,t.getMpst_no());
					ps.setString(20,t.getMcst_no());
					ps.setFloat(21,t.getMfix_disc1());
					ps.setFloat(22,t.getMfix_disc2());
					ps.setFloat(23,t.getMfix_tax1());
					ps.setFloat(24,t.getMfix_tax2());
					ps.setInt(25,t.getMstat_code());
					ps.setInt(26,t.getMarea_code());
					ps.setInt(27,t.getMarea_cd1());
					ps.setInt(28,t.getMregion_cd());
					ps.setInt(29,t.getMregio_cd1());
					ps.setInt(30,t.getMterr_code());
					ps.setInt(31,t.getMterr_cd1());
					ps.setInt(32,t.getMdist_cd());
					ps.setString(33,t.getMtype());
					ps.setInt(34,t.getMdays());
					ps.setFloat(35,t.getOctroi1());
					ps.setFloat(36,t.getOctroi2());
					ps.setFloat(37,t.getOctroi3());
					ps.setString(38,t.getCst_type());
					ps.setInt(39,t.getMsr_code());
					ps.setInt(40,t.getMsr_code1());
					ps.setFloat(41,t.getMcurr_bal());
					ps.setString(42,t.getMcurr_db());
					ps.setFloat(43,t.getMopng_bal());
					ps.setString(44,t.getMopng_db());
					ps.setFloat(45,t.getMlopng_bal());
					ps.setString(46,t.getMlopng_db());
					ps.setFloat(47,t.getMlclos_bal());
					ps.setString(48,t.getMlclos_db());
					ps.setFloat(49,t.getMnth_bal());
					ps.setFloat(50,t.getMnth_dr());
					ps.setFloat(51,t.getMnth_cr());
					ps.setString(52,t.getMnth_db());
					ps.setString(53,t.getPtype());
					ps.setInt(54,t.getMdist1());
					ps.setString(55,t.getSlct());
					ps.setInt(56,t.getDepo_code());
					ps.setString(57,t.getMac_code());
					ps.setInt(58,t.getMkt_year());
					ps.setInt(59,t.getMgrp_code());					
					i= i + ps.executeUpdate();
				}
				else
				{
					ps2.setInt(1,t.getDepo_code());
					ps2.setInt(2,t.getMcmp_code());
					ps2.setInt(3,t.getMusr_cd());
					ps2.setInt(4,t.getMgrp_code());
					ps2.setString(5,t.getMac_code());
					ps2.setString(6,t.getMac_type());
					ps2.setString(7,t.getMac_prfx());
					ps2.setString(8,t.getMac_name());
					ps2.setString(9,t.getMadd1());
					ps2.setString(10,t.getMadd2());
					ps2.setString(11,t.getMadd3());
					ps2.setString(12,t.getMcity());
					ps2.setString(13,t.getMpin());
					ps2.setString(14,t.getMphone());
					ps2.setString(15,t.getMcontct());
					ps2.setString(16,t.getMdlno1());
					ps2.setString(17,t.getMdlno2());
					ps2.setString(18,t.getMbanker());
					ps2.setString(19,t.getMbank_add1());
					ps2.setString(20,t.getMbank_add2());
					ps2.setString(21,t.getMtranspt());
					ps2.setString(22,t.getMpst_no());
					ps2.setString(23,t.getMcst_no());
					ps2.setFloat(24,t.getMfix_disc1());
					ps2.setFloat(25,t.getMfix_disc2());
					ps2.setFloat(26,t.getMfix_tax1());
					ps2.setFloat(27,t.getMfix_tax2());
					ps2.setInt(28,t.getMstat_code());
					ps2.setInt(29,t.getMarea_code());
					ps2.setInt(30,t.getMarea_cd1());
					ps2.setInt(31,t.getMregion_cd());
					ps2.setInt(32,t.getMregio_cd1());
					ps2.setInt(33,t.getMterr_code());
					ps2.setInt(34,t.getMterr_cd1());
					ps2.setInt(35,t.getMdist_cd());
					ps2.setString(36,t.getMtype());
					ps2.setInt(37,t.getMdays());
					ps2.setFloat(38,t.getOctroi1());
					ps2.setFloat(39,t.getOctroi2());
					ps2.setFloat(40,t.getOctroi3());
					ps2.setString(41,t.getCst_type());
					ps2.setInt(42,t.getMsr_code());
					ps2.setInt(43,t.getMsr_code1());
					ps2.setFloat(44,t.getMcurr_bal());
					ps2.setString(45,t.getMcurr_db());
					ps2.setFloat(46,t.getMopng_bal());
					ps2.setString(47,t.getMopng_db());
					ps2.setFloat(48,t.getMlopng_bal());
					ps2.setString(49,t.getMlopng_db());
					ps2.setFloat(50,t.getMlclos_bal());
					ps2.setString(51,t.getMlclos_db());
					ps2.setFloat(52,t.getMnth_bal());
					ps2.setFloat(53,t.getMnth_dr());
					ps2.setFloat(54,t.getMnth_cr());
					ps2.setString(55,t.getMnth_db());
					ps2.setString(56,t.getPtype());
					ps2.setInt(57,t.getMdist1());
					ps2.setString(58,t.getSlct());					
					ps2.setInt(59,t.getMkt_year());
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
    			System.out.println("-------------Exception in SQLAccountDAO.update " + ex);
    			i=-1;
    		}
    		finally {
    			try {
    				   System.out.println("No. of Records Update/Insert : "+i);
    		             if(rs != null){ rs.close();}
    		             if(ps != null){ ps.close();}
    		             if(ps1 != null){ps1.close();}
    		             if(ps2 != null){ps2.close();}
    		  //           if(con != null){con.close();}
    			} catch (SQLException e) {
    				System.out.println("-------------Exception in SQLAccountDAO.Connection.close "+e);
    			  }
    		}
			return i;
	}
	

		
	
	
} 