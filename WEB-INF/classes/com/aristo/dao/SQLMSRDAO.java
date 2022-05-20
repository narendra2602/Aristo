package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.aristo.valueobject.MSRFormBean;

public class SQLMSRDAO implements MSRDAO {
	
	public List getAllMSR(Connection con) { 
		 
		MSRFormBean mfb;
	    PreparedStatement ps=null;
	    ResultSet rst=null;

		List<MSRFormBean> data = new ArrayList<MSRFormBean>();
		try { 
			
            String typ="P";
            String tblnm=null;
            if (typ.equals("P"))
            	tblnm="a_msr_master08";
            if (typ.equals("T"))
            	tblnm="t_msr_master08";
            if (typ.equals("G"))
            	tblnm="g_msr_master08";
			
			String query = "select * from " + tblnm;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				mfb = new MSRFormBean();
				mfb.setDepo_code(rst.getInt(1));
				mfb.setRm_cd(rst.getInt(2));
				mfb.setMsr_cd(rst.getInt(3));
				mfb.setMsr_name(rst.getString(4));
				mfb.setSlct(rst.getString(5));
				mfb.setSt_cd(rst.getInt(6));
				mfb.setAr_cd(rst.getInt(7));
				mfb.setRg_cd(rst.getInt(8));
				mfb.setTr_cd(rst.getInt(9));
				mfb.setJoining_mm(rst.getInt(10));
				data.add(mfb);
			}
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLMSRDAO.getAllMSR " + e);
		}
		finally {
			try 
			{
	           if(rst != null){ rst.close();}
	           if(ps != null){ps.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLMRDAO.Connection.close "+e);
			  }
		}			
		return data;
	}


 public int updateMSR(String typ,List msr,Connection con)
   {
	   int i=0;
	   MSRFormBean m=null;;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null;
	   PreparedStatement ps2=null;
	   ResultSet rs=null; 
		try {
            String tblnm=null;
           	tblnm=(typ+"_msr_master08").toLowerCase();
       
            String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and Msr_Cd=? and mkt_year=?";
    		ps1 = con.prepareStatement(query1);
    		
			String query ="update "+tblnm+" set RM_CD=?,MSR_NAME=?,SLCT=?,ST_CD=?,AR_CD=?,RG_CD=?,TR_CD=?,JOINING_MM=? where DEPO_CODE=? and MSR_CD=? and mkt_year=?";
			ps = con.prepareStatement(query);
        
			String query2 ="insert into "+tblnm+" values (?,?,?,?,?,?,?,?,?,?,?)";
			ps2 = con.prepareStatement(query2);
			
			con.setAutoCommit(false);
            Iterator it = msr.iterator();
            
	         while(it.hasNext()) 
	       	 {
	           	m = (MSRFormBean) it.next();        
				ps1.setInt(1,m.getDepo_code());
				ps1.setInt(2, m.getMsr_cd());
				ps1.setInt(3, m.getMkt_year());
				
				rs = ps1.executeQuery();
				
				if (rs.next())
				{
					ps.setInt(1,m.getRm_cd());
					ps.setString(2, m.getMsr_name());
					ps.setString(3, m.getSlct());
					ps.setInt(4,m.getSt_cd());
					ps.setInt(5,m.getAr_cd());
					ps.setInt(6,m.getRg_cd());
					ps.setInt(7,m.getTr_cd());
					ps.setInt(8,m.getJoining_mm());
					ps.setInt(9,m.getDepo_code());
					ps.setInt(10, m.getMsr_cd());
					ps.setInt(11, m.getMkt_year());
					i= i + ps.executeUpdate();
				}
				else
				{
					ps2.setInt(1,m.getDepo_code());
					ps2.setInt(2, m.getRm_cd());
					ps2.setInt(3, m.getMsr_cd());
					ps2.setString(4, m.getMsr_name());
					ps2.setString(5, m.getSlct());
					ps2.setInt(6, m.getSt_cd());
					ps2.setInt(7, m.getAr_cd());
					ps2.setInt(8, m.getRg_cd());
					ps2.setInt(9, m.getTr_cd());
					ps2.setInt(10, m.getJoining_mm());
					ps2.setInt(11, m.getMkt_year());
					i= i + ps2.executeUpdate();
			}
				rs.close();
	      }
	         
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
				System.out.println("-------------Exception in SQLMsrDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLMsrDAO.Connection.close "+e);
				  }
			}
		
		return i;
	   
	   
   }


}
