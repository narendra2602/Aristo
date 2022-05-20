package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.RegionFormBean;

public class SQLRegionDAO implements RegionDAO {

	public List getAllRegion(Connection con,int depo,String typ,int eyear) { 
		 
		RegionFormBean rfb;
	    PreparedStatement ps = null;
	    ResultSet rst =null;
		
		
		List<RegionFormBean> data = new ArrayList<RegionFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
        	tblnm=(typ+"_Region_master08").toLowerCase();
    	    tblnm1=(typ+"_area_master08").toLowerCase();
			
			String query = "select r.reg_code,r.name,a.area_name from " + tblnm +" as r, "+tblnm1 
			+" as a where r.area_code=a.area_cd and r.depo_code=a.depo_code and r.depo_code=? and r.mkt_year=? and a.mkt_year=? ";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, depo);
			ps.setInt(2, eyear); 
			ps.setInt(3, eyear);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				rfb = new RegionFormBean();
				rfb.setReg_code(rst.getInt(1));
				rfb.setName(rst.getString(2));
				rfb.setTxt(rst.getString(3));
				data.add(rfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRegionDAO.getAllRegion " + e);
		}
		
		finally {
			try {
				   if(rst != null){rst.close();}  
				   if(ps != null){ ps.close();}
				   if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRegionDAO.Connection.close "+e);
			  }
		}		
		
		
		return data;
	}


 public int updateRegion(String typ,List reg,Connection con)
   {
	   int i=0;
	   RegionFormBean r=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null;
	   PreparedStatement ps2=null;
	   ResultSet rs=null;
		try {
            String tblnm=null;
            tblnm=(typ+"_region_master08").toLowerCase();

        String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and Reg_Code=? and mkt_year=? ";
        ps1 = con.prepareStatement(query1);
        
    	String query ="update "+tblnm+" set NAME=?,AREA_CODE=?,TXT=?,SLCT=?,TYP=? where DEPO_CODE=? and Reg_Code=? and mkt_year=?";
		ps = con.prepareStatement(query);

		String query2 ="insert into "+tblnm+" values (?,?,?,?,?,?,?,?)";
		ps2 = con.prepareStatement(query2);
   		con.setAutoCommit(false);
        Iterator it = reg.iterator();
       	while(it.hasNext()) 
       	 {
        	r = (RegionFormBean) it.next();        
        	ps1.setInt(1,r.getDepo_code());
			ps1.setInt(2, r.getReg_code());
			ps1.setInt(3, r.getMkt_year());
			rs = ps1.executeQuery();
			
			if (rs.next())
			{
				ps.setString(1, r.getName());
				ps.setInt(2,r.getArea_code());
				ps.setString(3, r.getTxt());
				ps.setString(4, r.getSlct());
				ps.setString(5, r.getTyp());
				ps.setInt(6,r.getDepo_code());
				ps.setInt(7, r.getReg_code());
				ps.setInt(8, r.getMkt_year());
				i= i + ps.executeUpdate();
			}
			else
			{
				ps2.setInt(1,r.getDepo_code());
				ps2.setInt(2, r.getReg_code());
				ps2.setString(3, r.getName());
				ps2.setInt(4, r.getArea_code());
				ps2.setString(5, r.getTxt());
				ps2.setString(6, r.getSlct());
				ps2.setString(7, r.getTyp());
				ps2.setInt(8, r.getMkt_year());
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
			System.out.println("-------------Exception in SQLRegionDAO.update " + ex);
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
				System.out.println("-------------Exception in SQLRegionDAO.Connection.close "+e);
			  }
		}
		
		return i;
	   
	   
   }

	
}
