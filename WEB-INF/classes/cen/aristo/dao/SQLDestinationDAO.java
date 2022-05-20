package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cen.aristo.valueobject.DestinationMFormBean;



public class SQLDestinationDAO implements DestinationDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List getAllDestn(Connection con, String typ ) { 
		 
		DestinationMFormBean dfb;
		PreparedStatement ps = null;
		ResultSet rst =null;
		
		List<DestinationMFormBean> data = new ArrayList<DestinationMFormBean>();
		try { 
            String tblnm=null;
          	tblnm=typ+"_destination";
  
///////////////////////////////////Account Master Query///////////////////////////////////////////
            String query = "select dst_code,dst_name,distance from "+tblnm.toLowerCase();
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				dfb = new DestinationMFormBean();
				dfb.setDst_code(rst.getInt(1));
				dfb.setDst_name(rst.getString(2));
				dfb.setDistance(rst.getInt(3));
				data.add(dfb);
			}
			
			rst.close();
			ps.close();
			ps = null;
			rst =null;	
		
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLDestinationDAO.getAllDestn " + e);
		}
		finally {
			try {
	             if(ps != null){ ps.close();}
	             if(rst != null){rst.close();}
	             if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLDestinationDAO.Connection.close "+e);
			  } 
		}
		return data;
	}	
	 

	
////////////////////////////////////////////Update Account//////////////////////////////////////	
	public int updateDestn(List dst,Connection con,String typ)
	{
		   int i=0;
		   DestinationMFormBean dt;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
				con.setAutoCommit(false); 
	            String tblnm=null;
            	tblnm=typ+"_destination";
            	
	            String query1 ="select depo_code from "+tblnm.toLowerCase()+" where depo_code=? and dst_code=?";
                ps1 = con.prepareStatement(query1);
				
                String query ="update "+tblnm.toLowerCase()+" set dst_name=?,distance=? where depo_code=? and dst_code=?";  
				ps = con.prepareStatement(query);  

				String query2 ="insert into "+tblnm.toLowerCase()+"  values (?,?,?,?)";
				ps2 = con.prepareStatement(query2);
				
       	Iterator it = dst.iterator();
       	while(it.hasNext()) 
           	 {
       			dt = (DestinationMFormBean) it.next();        
				ps1.setInt(1,dt.getDepo_code());
				ps1.setInt(2, dt.getDst_code());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,dt.getDst_name());
					ps.setInt(2,dt.getDistance());
					ps.setInt(3,dt.getDepo_code());
					ps.setInt(4,dt.getDst_code());
					i= i + ps.executeUpdate();
				} 
				else
				{
					ps2.setInt(1,dt.getDepo_code());
					ps2.setInt(2,dt.getDst_code());
					ps2.setString(3,dt.getDst_name());
					ps2.setInt(4,dt.getDistance());
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
				System.out.println("-------------Exception in SQLDestinationDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLDestinationDAO.Connection.close "+e);
				  }
			}
			
			
			
			return i;
		   
	}
	
	
}
