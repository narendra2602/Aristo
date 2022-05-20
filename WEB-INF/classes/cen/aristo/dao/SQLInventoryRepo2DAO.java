package cen.aristo.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.InventoryRepo2FormBean;
 
public class SQLInventoryRepo2DAO {

	public List getRepo2(Connection con, String typ, Date sdate ) { 
		 
		InventoryRepo2FormBean rfb;
		
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		
		Date sdt= null;
		
		List<InventoryRepo2FormBean> data = new ArrayList<InventoryRepo2FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            
            
           	tblnm1=typ+"_prdmsfl";
           	tblnm2=typ+"_batmst";

    		sdt= new java.sql.Date (sdate.getTime());
    		
	        long diffInMilleseconds;
    		long diffInSeconds;
    		long diffInMinutes;
    		long diffInHours;
    		long diffInDays;
    		
           	String query=" select b.bat_pcode,p.pname,p.pack,b.bat_no,b.bat_netrt,b.expiry,b.bat_clos,b.bat_clos*b.bat_netrt " +
           			     " from "+tblnm1.toLowerCase()+" as p inner join "+tblnm2.toLowerCase()+" as b on p.pcode=b.bat_pcode " +
           			     " where TO_DAYS(b.expiry) - TO_DAYS('"+sdt+"') < 365 and b.bat_clos<>0 order by b.bat_pcode,b.expiry"; 
           	         
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
				
				rfb = new InventoryRepo2FormBean();
				rfb.setPcode(rst.getInt(1));
				rfb.setPname(rst.getString(2)+ "," + rst.getString(3));
				rfb.setBat_no(rst.getString(4));
				rfb.setBat_netrt(rst.getDouble(5));   
				rfb.setExpiry(rst.getDate(6));

				diffInMilleseconds =  rst.getDate(6).getTime()-sdt.getTime();
		        diffInSeconds = diffInMilleseconds/1000;
		        diffInMinutes = diffInSeconds/60;
		        diffInHours = diffInMinutes/60;
		        diffInDays = diffInHours/24;
		        
		         if (diffInDays<0)
						rfb.setEstock(rst.getInt(7));
				
		         if ((diffInDays>=0) && (diffInDays<=180))
						rfb.setEstock90(rst.getInt(7));
				
		         if ((diffInDays>180) && (diffInDays<360))
						rfb.setEstock360(rst.getInt(7));
				
		         rfb.setTvalue(rst.getInt(8));

				data.add(rfb);
                
                
                
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo2DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo2DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	

	
}
