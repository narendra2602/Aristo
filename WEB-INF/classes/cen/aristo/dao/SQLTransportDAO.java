package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cen.aristo.valueobject.TransportMFormBean;



public class SQLTransportDAO implements TransportDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List getAllTransport(Connection con , String typ) { 
		 
		TransportMFormBean tfb;
		PreparedStatement ps = null;
		ResultSet rst =null;
		
		List<TransportMFormBean> data = new ArrayList<TransportMFormBean>();
		try { 
            String tblnm=null;
          	tblnm=(typ+"_transmst").toLowerCase(); 

            ///////////////////////////////////Account Master Query///////////////////////////////////////////
            
            String query = "select tr_code,tr_name,tr_add1,tr_add2,tr_city,telephone_no,tr_person,tin_no  from "+tblnm;
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				tfb = new TransportMFormBean();
				tfb.setTr_code(rst.getInt(1));
				tfb.setTr_name(rst.getString(2));
				tfb.setTr_add1(rst.getString(3));
				tfb.setTr_add2(rst.getString(4));
				tfb.setTr_city(rst.getString(5));
				tfb.setTelphone_no(rst.getString(6));
				tfb.setTr_person(rst.getString(7));
				tfb.setTin_no(rst.getString(8));
				data.add(tfb);
			}
			
			rst.close();
			ps.close();
			ps = null;
			rst =null;	
		
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLTransportDAO.getAllTransport " + e);
		}
		finally {
			try {
				   con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLTransportDAO.Connection.close "+e);
			  } 
		}
		return data;
	}	
	 

	
////////////////////////////////////////////Update Account//////////////////////////////////////	
	public int updateTransport(List dst,Connection con, String typ)
	{
		   int i=0;
		   TransportMFormBean tfb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
	            String tblnm=null;
	            tblnm=(typ+"_transmst").toLowerCase();
                String query1 ="select depo_code from "+tblnm+" where depo_code=? and tr_code=?"; 
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm+" set tr_name=?,tr_add1=?,tr_add2=?,tr_city=?,telephone_no=?,tr_person=?,tin_no=? where depo_code=? and tr_code=?" ;   
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm+"  values (?,?,?,?,?,?,?,?,?)";
				ps2 = con.prepareStatement(query2);
				con.setAutoCommit(false);
       	Iterator it = dst.iterator();
       	while(it.hasNext()) 
           	 {
       			tfb = (TransportMFormBean) it.next();        
				 
				ps1.setInt(1,tfb.getDepo_code());
				ps1.setInt(2, tfb.getTr_code());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,tfb.getTr_name()); 
					ps.setString(2,tfb.getTr_add1());
					ps.setString(3,tfb.getTr_add2());
					ps.setString(4,tfb.getTr_city());
					ps.setString(5,tfb.getTelphone_no());
					ps.setString(6,tfb.getTr_person());
					ps.setString(7,tfb.getTin_no());
					ps.setInt(8,tfb.getDepo_code());
					ps.setInt(9,tfb.getTr_code());
					 
					i= i + ps.executeUpdate();
			  	  
				} 
				else
				{
					ps2.setInt(1,tfb.getDepo_code());
					ps2.setInt(2,tfb.getTr_code());
					ps2.setString(3,tfb.getTr_name()); 
					ps2.setString(4,tfb.getTr_add1());
					ps2.setString(5,tfb.getTr_add2());
					ps2.setString(6,tfb.getTr_city());
					ps2.setString(7,tfb.getTelphone_no());
					ps2.setString(8,tfb.getTr_person());
					ps2.setString(9,tfb.getTin_no());
					
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
				System.out.println("-------------Exception in SQLTransportTDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLTransportDAO.Connection.close "+e);
				  }
			}
			return i;
		   
	}
	
	
}
