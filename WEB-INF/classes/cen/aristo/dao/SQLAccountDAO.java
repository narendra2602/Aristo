package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cen.aristo.valueobject.AccountMasterFormBean;



public class SQLAccountDAO implements AccountCentralDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List getAllAccount(Connection con, String typ) { 
		 
		AccountMasterFormBean afb;
		PreparedStatement ps = null;
		ResultSet rst =null;
		
		List<AccountMasterFormBean> data = new ArrayList<AccountMasterFormBean>();
		try { 
            String tblnm=null;
          	tblnm=typ+"_faacms2";
          	
///////////////////////////////////Account Master Query///////////////////////////////////////////
            String query = "select mac_code,mac_name,madd1,madd2,mcity,mpin from "+tblnm.toLowerCase()+" order by mac_code " ;
			ps = con.prepareStatement(query);
		 
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				afb = new AccountMasterFormBean();
				afb.setMac_code(rst.getString(1));
				afb.setMac_name(rst.getString(2));
				afb.setMadd1(rst.getString(3));
				afb.setMadd2(rst.getString(4));
				afb.setMcity(rst.getString(5));
				afb.setMpin(rst.getString(6));				
				data.add(afb);
			}
			
			rst.close();
			ps.close();
			ps = null;
			rst =null;	
		
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLAccountDAO.getAllAccount " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
			try {
				   con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLAccountDAO.Connection.close "+e);
			  }
		}
		return data;
	}	
	 

	
////////////////////////////////////////////Update Account//////////////////////////////////////	
	public int updateAccount(List acc,Connection con, String typ)
	{
		   int i=0;
		   AccountMasterFormBean t;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
				
	            String tblnm=null;
	            
	            tblnm=typ+"_faacms2";
	            
                String query1 ="select depo_code from "+tblnm.toLowerCase()+" where DEPO_CODE=? and MAC_CODE=?";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm.toLowerCase()+" set MCMP_CODE=?,MUSR_CD=?,MGRP_CODE=?," +
				"MAC_TYPE=?,MAC_PRFX=?,MAC_NAME=?,MADD1=?,MADD2=?,MADD3=?,MCITY=?,MPIN=?,MPHONE=?," +
				"MCONTCT=?,MDLNO1=?,MDLNO2=?,MTRANSPT=?,MPST_NO=?," +
				"MCST_NO=? where depo_code=? and mac_code=?";  
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm.toLowerCase()+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps2 = con.prepareStatement(query2);
				con.setAutoCommit(false);
       	Iterator it = acc.iterator();
       	while(it.hasNext()) 
           	 {
       		
       		    t = (AccountMasterFormBean) it.next();        
				ps1.setInt(1,t.getDepo_code());
				ps1.setString(2, t.getMac_code());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setInt(1,t.getMcmp_code());
					ps.setInt(2,t.getMusr_cd());
					ps.setInt(3,t.getMgrp_code());
					ps.setString(4,t.getMac_type());
					ps.setString(5,t.getMac_prfx());
					ps.setString(6,t.getMac_name());
					ps.setString(7,t.getMadd1());
					ps.setString(8,t.getMadd2());
					ps.setString(9,t.getMadd3());
					ps.setString(10,t.getMcity());
					ps.setString(11,t.getMpin());
					ps.setString(12,t.getMphone());
					ps.setString(13,t.getMcontct());
					ps.setString(14,t.getMdlno1());
					ps.setString(15,t.getMdlno2());
					ps.setString(16,t.getMtranspt());
					ps.setString(17,t.getMpst_no());
					ps.setString(18,t.getMcst_no());
					ps.setInt(19,t.getDepo_code());
					ps.setString(20,t.getMac_code());
					 
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
					ps2.setString(18,t.getMtranspt());
					ps2.setString(19,t.getMpst_no());
					ps2.setString(20,t.getMcst_no());
					
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
				// TODO Auto-generated catch block
				try {
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLAccountDAO.update " + ex);
				i=-1;
			}
			
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
					   if(rs != null){rs.close();}  
					   if(ps != null){ ps.close();}
			           if(ps1 != null){ps1.close();}
			           if(ps2 != null){ps2.close();}
			           if(con != null){con.close();}

				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLAccountDAO.Connection.close "+e);
				  }
			}
			
						
			return i;
		   
	}
	
	
}
