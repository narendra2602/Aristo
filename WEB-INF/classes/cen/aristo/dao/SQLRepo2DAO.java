package cen.aristo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cen.aristo.valueobject.CentralRepo2FormBean;

public class SQLRepo2DAO {
	
	public List getRepo2(Connection con, String typ) { 
		 
		CentralRepo2FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		List<CentralRepo2FormBean> data = new ArrayList<CentralRepo2FormBean>();

		try { 
            String tblnm=null;
            String tblnm1=null;
            tblnm=(typ+"_batmst").toLowerCase();
           	tblnm1=(typ+"_prdmsfl").toLowerCase();
            	
            String query= "select b.bat_no,b.bat_clos,b.bat_mrprt,b.bat_expdt,p.pname,p.pack,b.case_size," +
            		" b.weight_box,b.bat_pcode from "+tblnm+" as b inner join "+tblnm1+" as p on b.bat_pcode=p.pcode" +
            		" where b.bat_clos<>0 order by p.pname "; 	
            
            
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			boolean print=true;
			boolean tprint=false;
			String pname = null;
			String pack = null;
			int pcode = 0;
			int gtotal=0;
			while (rst.next())
			{
			   if(tprint)
			   {
				if (rst.getInt(9)!=pcode)
				{
					rfb = new CentralRepo2FormBean();
					rfb.setPacking("TOTAL");
					rfb.setQty(gtotal);
					rfb.setColour(2);
					gtotal=0;
					data.add(rfb);
					print=true;
				}
					
			   }
			   
				rfb = new CentralRepo2FormBean();
				if (print)
				{
					pname=rst.getString(5);
					pack = rst.getString(6);
					pcode= rst.getInt(9);
					rfb.setColour(4);					
					print=false;
					tprint=true;
				}
				rfb.setProduct(pname);
				rfb.setPacking(pack);
				rfb.setBatch_no(rst.getString(1));
				rfb.setQty(rst.getInt(2));
				rfb.setMrp(rst.getDouble(3));
				rfb.setExpiry(rst.getString(4));
				rfb.setQtypc(rst.getInt(7));
				rfb.setGross_wt(rst.getDouble(8));
				gtotal=gtotal+rst.getInt(2);
				pname="";
				pack = "";
				
				data.add(rfb);
			}
			
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2DAO.getRepo2 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}		
		return data;
	}	
	
}