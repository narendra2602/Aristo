package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import cen.aristo.valueobject.BatchMasterFormBean;



public class SQLBatchDAO implements BatchDAO{

/////////////////////////////////////////Get All Acount////////////////////////////////////////	
	public List  getAllbatch(Connection con,String typ,int pcode ) { 
		 
		BatchMasterFormBean bfb;
		PreparedStatement ps = null;
		ResultSet rst =null;
		
		List<BatchMasterFormBean> data = new ArrayList<BatchMasterFormBean>();
		try { 
            String tblnm=null;
          	tblnm=typ+"_batmst";
            	
///////////////////////////////////Batch Master Query///////////////////////////////////////////
            String query = "select bat_pcode,bat_no,bat_mfgdt,bat_expdt,bat_netrt," +
            "bat_netrt1,bat_trdrt,bat_trdrt1,bat_mrprt,bat_mrprt1,bat_mfgrt,bat_hosrt," +
            "bat_excrt,bat_indct,bat_opng,bat_clos,bat_purrt,bat_clos2,bat_tag," +
            "slct,ecess from "+tblnm.toLowerCase()+" where bat_pcode=? ";
			ps = con.prepareStatement(query);
			ps.setInt(1,pcode);
			
			rst = ps.executeQuery();
			while (rst.next())
			{ 
				bfb = new BatchMasterFormBean();
				bfb.setBat_pcode(rst.getInt(1));
				bfb.setBat_no(rst.getString(2));
				bfb.setBat_mfgdt(rst.getString(3));
				bfb.setBat_expdt(rst.getString(4));
				bfb.setBat_netrt(rst.getDouble(5));
				bfb.setBat_netrt1(rst.getDouble(6));
				bfb.setBat_trdrt(rst.getDouble(7));
				bfb.setBat_trdrt1(rst.getDouble(8));
				bfb.setBat_mrprt(rst.getDouble(9));
				bfb.setBat_mrprt1(rst.getDouble(10));
				bfb.setBat_mfgrt(rst.getDouble(11));
				bfb.setBat_hosrt(rst.getDouble(12));
				bfb.setBat_excrt(rst.getDouble(13));
				bfb.setBat_indct(rst.getString(14));
				bfb.setBat_opng(rst.getInt(15));
				bfb.setBat_clos(rst.getInt(16));
				bfb.setBat_purrt(rst.getDouble(17));
				bfb.setBat_clos2(rst.getInt(17));
				bfb.setBat_tag(rst.getString(18));
				bfb.setSlct(rst.getString(19));
				bfb.setEcess(rst.getDouble(20));
				data.add(bfb); 
			}
			rst.close();
			ps.close();
			ps = null;
			rst =null;	
		}
		catch (Exception e) 
		{
		System.out.println("========Exception in SQLBatchDAO.getAllbatch " + e);
		}
		finally 
		{
		  try 
		    {
			   con.close();
			} 
		      catch (SQLException e) 
		          {
				   System.out.println("-------------Exception in SQLBatchDAO.Connection.close "+e);
			      } 
		}
		return data;
	}	
	
////////////////////////////////////////////Update Batch//////////////////////////////////////	
	public int updatebatch(List bat,Connection con,String typ)
	{
		   int i=0;
		   BatchMasterFormBean bfb;
		   PreparedStatement ps = null;
		   PreparedStatement ps1 = null;
		   PreparedStatement ps2 = null;
		   ResultSet rs =null;
			try {
			 
				con.setAutoCommit(false); 
	            String tblnm=null;
	            tblnm=typ+"_batmst";
                String query1 ="select depo_code from "+tblnm.toLowerCase()+" where depo_code=? and bat_pcode=? and bat_no=? and case_size=?";
                ps1 = con.prepareStatement(query1);
                
				String query ="update "+tblnm.toLowerCase()+" set bat_mfgdt=?,bat_expdt=?,bat_netrt=?,bat_netrt1=?,bat_trdrt=?,bat_trdrt1=?,bat_mrprt=?," +
						"bat_mrprt1=?,bat_mfgrt=?,bat_hosrt=?,bat_excrt=?,bat_indct=?,bat_opng=?,bat_clos=?,bat_purrt=?,bat_clos2=?,bat_tag=?,slct=?," +
						"ecess=?,weight_box=?,bat_recdt=?,box=?,first_issu=?,last_issu=?,expiry=? where depo_code=? and bat_pcode=? and bat_no=? and case_size=?";   
				
				ps = con.prepareStatement(query);  
               
				String query2 ="insert into "+tblnm.toLowerCase()+"  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				ps2 = con.prepareStatement(query2);
		System.out.println(bat.size());		
       	Iterator it = bat.iterator();
		Date javadt = new Date();
		    	while(it.hasNext()) 
           	 {
       			bfb = (BatchMasterFormBean) it.next();        
				 
				ps1.setInt(1,bfb.getDepo_code());
				ps1.setInt(2, bfb.getBat_pcode());
				ps1.setString(3, bfb.getBat_no());
				ps1.setInt(4, bfb.getCase_size());
				
				rs = ps1.executeQuery(); 
				if (rs.next())
				{ 
					ps.setString(1,bfb.getBat_mfgdt());
					ps.setString(2,bfb.getBat_expdt());
					ps.setDouble(3,bfb.getBat_netrt());
					ps.setDouble(4,bfb.getBat_netrt1());
					ps.setDouble(5,bfb.getBat_trdrt());
					ps.setDouble(6,bfb.getBat_trdrt1());
					ps.setDouble(7,bfb.getBat_mrprt());
					ps.setDouble(8,bfb.getBat_mrprt1());
					ps.setDouble(9,bfb.getBat_mfgrt());
					ps.setDouble(10,bfb.getBat_hosrt());
					ps.setDouble(11,bfb.getBat_excrt());
					ps.setString(12,bfb.getBat_indct());
					ps.setInt(13,bfb.getBat_opng());
					ps.setInt(14,bfb.getBat_clos());
					ps.setDouble(15,bfb.getBat_purrt());
					ps.setInt(16,bfb.getBat_clos2());
					ps.setString(17,bfb.getBat_tag());
					ps.setString(18,bfb.getSlct());
					ps.setDouble(19,bfb.getEcess());
					ps.setDouble(20,bfb.getWeight_box());
					javadt = bfb.getBat_recdt();
					ps.setDate(21,new java.sql.Date (javadt.getTime()));
					ps.setInt(22,bfb.getBox());
					javadt = bfb.getFirst_issu();
					ps.setDate(23,new java.sql.Date (javadt.getTime()));
					javadt = bfb.getLast_issu();
					ps.setDate(24,new java.sql.Date (javadt.getTime()));
					javadt = bfb.getExpiry();
					ps.setDate(25,new java.sql.Date (javadt.getTime()));
					ps.setInt(26,bfb.getDepo_code());
					ps.setInt(27,bfb.getBat_pcode());
					ps.setString(28,bfb.getBat_no());
					ps.setInt(29,bfb.getCase_size());
					i= i + ps.executeUpdate();
			  	  
				} 
				else
				{ 

					ps2.setInt(1,bfb.getDepo_code());
					ps2.setInt(2,bfb.getBat_pcode());
					ps2.setString(3,bfb.getBat_no());
					ps2.setString(4,bfb.getBat_mfgdt());
					ps2.setString(5,bfb.getBat_expdt());
					ps2.setDouble(6,bfb.getBat_netrt());
					ps2.setDouble(7,bfb.getBat_netrt1());
					ps2.setDouble(8,bfb.getBat_trdrt());
					ps2.setDouble(9,bfb.getBat_trdrt1());
					ps2.setDouble(10,bfb.getBat_mrprt());
					ps2.setDouble(11,bfb.getBat_mrprt1());
					ps2.setDouble(12,bfb.getBat_mfgrt());
					ps2.setDouble(13,bfb.getBat_hosrt());
					ps2.setDouble(14,bfb.getBat_excrt());
					ps2.setString(15,bfb.getBat_indct());
					ps2.setInt(16,bfb.getBat_opng());
					ps2.setInt(17,bfb.getBat_clos());
					ps2.setDouble(18,bfb.getBat_purrt());
					ps2.setInt(19,bfb.getBat_clos2());
					ps2.setString(20,bfb.getBat_tag());
					ps2.setString(21,bfb.getSlct());
					ps2.setDouble(22,bfb.getEcess());
					ps2.setDouble(23,bfb.getWeight_box());
					
					javadt = bfb.getBat_recdt();
					ps2.setDate(24,new java.sql.Date (javadt.getTime()));
					ps2.setInt(25,bfb.getCase_size());
					ps2.setInt(26,bfb.getBox());
					javadt = bfb.getFirst_issu();
					ps2.setDate(27,new java.sql.Date (javadt.getTime()));
					javadt = bfb.getLast_issu();
					ps2.setDate(28,new java.sql.Date (javadt.getTime()));
					javadt = bfb.getExpiry();
					ps2.setDate(29,new java.sql.Date (javadt.getTime()));
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
				System.out.println("-------------Exception in SQLBatchDAO.update " + ex);
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
					System.out.println("-------------Exception in SQLBatchDAO.Connection.close "+e);
				  }
			}
			return i;
		   
	} 
	
	public List getAllProduct(Connection con, String tp) { 
		 
		BatchMasterFormBean rfb;
		
		List<BatchMasterFormBean> data = new ArrayList<BatchMasterFormBean>();
		try {     
            
            String tblnm=null;
            tblnm=tp+"_prdmsfl"; 
               
            String query2 = "Select pcode,pname,pack from "+tblnm.toLowerCase()+" order by pname";
	        PreparedStatement ps2 = con.prepareStatement(query2);
	        ResultSet rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
					  rfb = new BatchMasterFormBean();
		        	  rfb.setPval(rst2.getInt(1));
		        	  rfb.setPname(rst2.getString(2)+","+rst2.getString(3)); 
	                  data.add(rfb); 
	                  
  				 } 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLBatchDAO.getAllProduct " + e);
		}
		finally {
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLBatchDAO.Connection.close "+e);
			  }
		}
	
		return data;
	}
	

	public String getProduct(Connection con, String tp,int pcode) { 
        String pname=null;		
		try {     

            String tblnm=null;
            tblnm=tp+"_prdmsfl";
            
            String query2 = "Select pname,pack from "+tblnm.toLowerCase()+"  where pcode=? ";
	        PreparedStatement ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,pcode);
	        ResultSet rst2 = ps2.executeQuery();

	            if (rst2.next())
				{
					pname=rst2.getString(1) + "," + rst2.getString(2);  
  				} 
	            ps2.close();
	            rst2.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLBatchDAO.getProduct " + e);
		}
		finally {
			try {

		        	con.close();
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLBatchDAO.Connection.close "+e);
			  }
		}
		return pname;
	}

	
}
 