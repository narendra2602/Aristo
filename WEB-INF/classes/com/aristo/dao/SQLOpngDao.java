package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.aristo.valueobject.ProductOpngFormBean;

public class SQLOpngDao {

////////////////////////////////////////////Update Product Opening//////////////////////////////////////	
	public int updateopng(List popng,Connection con,String typ)
	{
		   int i=0;
		   ProductOpngFormBean bfb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
				con.setAutoCommit(false); 
	            String tblnm=null;
	            tblnm=(typ+"_sprdopng").toLowerCase();
                String query1 ="select depo_code from "+tblnm+" where depo_code=? and opcode=? ";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm+" set opngjan=?,opngfeb=?,opngmar=?,opngapr=?,opngmay=?,opngjun=?,opngjul=?," +
						"opngaug=?,opngsep=?,opngoct=?,opngnov=?,opngdec=? where depo_code=? and opcode=? ";   
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				ps2 = con.prepareStatement(query2);

				Iterator it = popng.iterator();

				while(it.hasNext()) 
           	    {
       			bfb = (ProductOpngFormBean) it.next();        
				 
				ps1.setInt(1,bfb.getDepo_code());
				ps1.setInt(2,bfb.getOpcode());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setInt(1,bfb.getOpngjan());
					ps.setInt(2,bfb.getOpngfeb());
					ps.setInt(3,bfb.getOpngmar());
					ps.setInt(4,bfb.getOpngapr());
					ps.setInt(5,bfb.getOpngmay());
					ps.setInt(6,bfb.getOpngjun());
					ps.setInt(7,bfb.getOpngjul());
					ps.setInt(8,bfb.getOpngaug());
					ps.setInt(9,bfb.getOpngsep());
					ps.setInt(10,bfb.getOpngoct());
					ps.setInt(11,bfb.getOpngnov());
					ps.setInt(12,bfb.getOpngdec());
					ps.setInt(13,bfb.getDepo_code());
					ps.setInt(14,bfb.getOpcode());
					i= i + ps.executeUpdate();
			  	  
				} 
				else
				{ 

					ps2.setInt(1,bfb.getDepo_code());
					ps2.setInt(2,bfb.getOpcode());
					ps2.setInt(3,bfb.getOpngjan());
					ps2.setInt(4,bfb.getOpngfeb());
					ps2.setInt(5,bfb.getOpngmar());
					ps2.setInt(6,bfb.getOpngapr());
					ps2.setInt(7,bfb.getOpngmay());
					ps2.setInt(8,bfb.getOpngjun());
					ps2.setInt(9,bfb.getOpngjul());
					ps2.setInt(10,bfb.getOpngaug());
					ps2.setInt(11,bfb.getOpngsep());
					ps2.setInt(12,bfb.getOpngoct());
					ps2.setInt(13,bfb.getOpngnov());
					ps2.setInt(14,bfb.getOpngdec());
					ps2.setInt(15,0);
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
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("-------------Exception in SQLSampleOpngDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLSampleOpngDAO.Connection.close "+e);
				  }
			}
			return i;
		   
	} 	
}
