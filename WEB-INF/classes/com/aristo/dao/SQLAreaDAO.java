package com.aristo.dao;

import java.sql.Connection;  

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import com.aristo.valueobject.AreaFormBean;


public class SQLAreaDAO implements AreaDAO{
	
	public List getAllarea(Connection con, int depo, String typ,int eyear) { 
			 
			AreaFormBean afb;
			PreparedStatement ps = null;
			ResultSet rst=null;

			List<AreaFormBean> data = new ArrayList<AreaFormBean>();
			try { 
				
                String tblnm=null;
            	tblnm=(typ+"_area_master08").toLowerCase();
				String query = "select * from " + tblnm +" where depo_code=? and mkt_year=? ";
				ps = con.prepareStatement(query);
				ps.setInt(1, depo);
				ps.setInt(2, eyear);
				rst = ps.executeQuery();
				
				while (rst.next())
				{
					afb = new AreaFormBean();
					afb.setDepo_code(rst.getInt(1));
					afb.setArea_cd(rst.getInt(2));
					afb.setArea_name(rst.getString(3));
					afb.setSlct(rst.getString(4));
					afb.setTyp(rst.getString(5));
					data.add(afb);
				}
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
				
			} catch (Exception e) {
				
				System.out.println("========Exception in SQLAreaDAO.getAllArea " + e);
			}
			
			finally {
				try {
		             if(rst != null){ rst.close();}
		             if(ps != null){ ps.close();}
		             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLAreaDAO.Connection.close "+e);
				  }
			}		
			return data;
		}

	 public int updateArea(String typ,List ar,Connection con)
	   {
		   PreparedStatement ps = null;  
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   
		   ResultSet rs = null;
		   int i=0;
		   AreaFormBean a;
		   try {
                String tblnm=null;
               	tblnm=(typ+"_area_master08").toLowerCase();
                String query1 ="select depo_code from "+tblnm+" where DEPO_CODE=? and AREA_CD=? and MKT_YEAR=?";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm+" set AREA_NAME=?,TYP=? where DEPO_CODE=? and AREA_CD=? and MKT_YEAR=?";
				ps = con.prepareStatement(query);

				String query2 ="insert into "+tblnm+" (depo_code,area_cd,area_name,typ,mkt_year) values (?,?,?,?,?)";
				ps2 = con.prepareStatement(query2);
				
                con.setAutoCommit(false);
                
		       	Iterator it = ar.iterator();
		       	while(it.hasNext()) 
		           	 {
		        		a = (AreaFormBean) it.next();        
						ps1.setInt(1,a.getDepo_code());
						ps1.setInt(2,a.getArea_cd());
						ps1.setInt(3,a.getMkt_year());
						rs = ps1.executeQuery();
						if (rs.next())
						{ 
							ps.setString(1, a.getArea_name());
							ps.setString(2, a.getTyp());
							ps.setInt(3,a.getDepo_code());
							ps.setInt(4, a.getArea_cd());
							ps.setInt(5, a.getMkt_year());
							i= i + ps.executeUpdate();
						}
						else
						{
							ps2.setInt(1,a.getDepo_code());
							ps2.setInt(2,a.getArea_cd());
							ps2.setString(3, a.getArea_name());
							ps2.setString(4, a.getTyp());
							ps2.setInt(5, a.getMkt_year());
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
				System.out.println("-------------Exception in SQLAreaDAO.update " + ex);
				i=-1;
			}
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(rs != null){ rs.close();}
			             if(ps != null){ ps.close();}
			             if(ps1 != null){ps1.close();}
			             if(ps2 != null){ps2.close();}
			             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLAreaDAO.Connection.close "+e);
				  }
			}
			
			return i;
	   }
	 

	 public int updateTrig(String typ,AreaFormBean a,Connection con)
	   {
		   PreparedStatement ps2 = null;
		   
		   int i=0;
		   try {
			    String tblnm=null;
			    tblnm=(typ+"_reptrig").toLowerCase();
				String query2 ="insert into "+tblnm+" (mkt_year,depo_code,seqn) values (?,?,?)";
				ps2 = con.prepareStatement(query2);
				con.setAutoCommit(false);
				ps2.setInt(1, a.getMkt_year());
				ps2.setInt(2,a.getDepo_code());
				ps2.setInt(3,0);
				i= i + ps2.executeUpdate();

				con.commit();
	       		con.setAutoCommit(true);
	       		ps2.close();
		   
			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLAreaDAO.updateTrig " + ex);
				i=-1;
			}
			finally {
				try {
					   System.out.println("No. of Records Update/Insert : "+i);
			             if(ps2 != null){ps2.close();}
			             if(con != null){con.close();}
				} catch (SQLException e) {
					System.out.println("-------------Exception in SQLAreaDAO.Trig.Connection.close "+e);
				  }
			}
			
			return i;
	   }
	 

}
 