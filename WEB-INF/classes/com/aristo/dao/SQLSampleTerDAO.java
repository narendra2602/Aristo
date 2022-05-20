package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.TerFormBean;

public class SQLSampleTerDAO {
	
	public List getAllter(Connection con, int depo, String typ,int eyear) { 
		 
		TerFormBean tfb;
		PreparedStatement ps = null;
		ResultSet rst =null; 		
		List<TerFormBean> data = new ArrayList<TerFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
        	tblnm=(typ+"_stermst").toLowerCase();
        	tblnm1=(typ+"_sregmst").toLowerCase();
        	tblnm2=(typ+"_sareamst").toLowerCase();
        	
			String query = "select t.ter_code,t.ter_name,a.area_name,r.name from " + tblnm+ " " +
			" as t, "+tblnm2+" as a, "+tblnm1+" as r where t.area_code=a.area_cd and " +
			" t.regn_code=r.reg_code and t.depo_code=a.depo_code and t.depo_code=r.depo_code " +
			" and t.depo_code=? and t.mkt_year=? and a.mkt_year=? and r.mkt_year=?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1,depo);
			ps.setInt(2,eyear);
			ps.setInt(3,eyear);
			ps.setInt(4,eyear);
			
			rst = ps.executeQuery();
			while (rst.next())
			{
				tfb = new TerFormBean();
				tfb.setTer_code(rst.getInt(1));
				tfb.setTer_name(rst.getString(2));
				tfb.setTxt(rst.getString(3));
				data.add(tfb);
			}
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleTerDAO.getAllTer " + e);
		}
		finally {
			try {
				if(rst != null){ rst.close();}     
				if(ps != null){ ps.close();}
		        if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleTerDAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	 public int updateTer(String typ,List ter,Connection con)
	   {
		   int i=0;
		   TerFormBean t;
		   
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   
		   ResultSet rs = null;
		   try {
				String tblnm=null;
				tblnm=(typ+"_stermst").toLowerCase();
	            
		    String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and TER_CODE=? and mkt_year=?";
			ps1 = con.prepareStatement(query1);
			
			String query ="update "+tblnm+" set state_code=?,area_code=?,regn_code=?,ter_name=?,no_of_rep=?," +
		    " rep_cd1=?,rep_cd2=?,rep_cd3=?,rep_cd4=?,rep_cd5=?,rep_cd6=?,txt=?,ter_pt=? " +
		    " where depo_code=? and ter_code=? and mkt_year=? ";  
		    ps = con.prepareStatement(query);  
		
			String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps2 = con.prepareStatement(query2);
			con.setAutoCommit(false);
		
			Iterator it = ter.iterator();
			
			while(it.hasNext()) 
           	{
       			t = (TerFormBean) it.next();        
				ps1.setInt(1,t.getDepo_code());
				ps1.setInt(2,t.getTer_code());
				ps1.setInt(3,t.getMkt_year());
				rs = ps1.executeQuery();
				if (rs.next())
				{ 
					ps.setInt(1, t.getState_code());
					ps.setInt(2, t.getArea_code()); 
					ps.setInt(3,t.getRegn_code());
					ps.setString(4, t.getTer_name());
					ps.setInt(5, t.getNo_of_rep());
					ps.setInt(6, t.getRep_cd1());
					ps.setInt(7, t.getRep_cd2());
					ps.setInt(8, t.getRep_cd3());
					ps.setInt(9, t.getRep_cd4()); 
					ps.setInt(10, t.getRep_cd5());
					ps.setInt(11, t.getRep_cd6());
					ps.setString(12,t.getTxt()); 
					ps.setString(13, t.getTer_pt());
					ps.setInt(14, t.getDepo_code());   
					ps.setInt(15, t.getTer_code());
					ps.setInt(16, t.getMkt_year());
					i= i + ps.executeUpdate();
				}
				else
				{
					ps2.setInt(1,t.getDepo_code());
					ps2.setInt(2, t.getTer_code());
					ps2.setInt(3,t.getState_code());
					ps2.setInt(4,t.getArea_code());
					ps2.setInt(5, t.getRegn_code());
					ps2.setString(6, t.getTer_name());
					ps2.setInt(7, t.getNo_of_rep());
					ps2.setInt(8, t.getRep_cd1());
					ps2.setInt(9, t.getRep_cd2());
					ps2.setInt(10, t.getRep_cd3());
					ps2.setInt(11, t.getRep_cd4());
					ps2.setInt(12, t.getRep_cd5());
					ps2.setInt(13, t.getRep_cd6());
					ps2.setString(14,t.getTxt());
					ps2.setString(15, t.getTer_pt());
					ps2.setInt(16, t.getMkt_year());
					i= i + ps2.executeUpdate();
				}
				rs.close(); 

       	   } ///////////////////End of Loop While/////////////////////////
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
				System.out.println("-------------Exception in SQLSampleTerDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLSampleTerDAO.Connection.close "+e);
				  }
			}
			return i;
	   }
	 
}
