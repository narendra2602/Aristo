package com.aristo.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.SampleRepo3FormBean;
 
public class SQLSampleRepo3DAO {

	public List getRepo3(Connection con, String typ, Date sdate,int dp ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            
           	tblnm1=(typ+"_sprdmsfl").toLowerCase();
           	tblnm2=(typ+"_sbatmsfl").toLowerCase();

    		sdt= new java.sql.Date (sdate.getTime());
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());

    		
	        long diffInMilleseconds;
    		long diffInSeconds;
    		long diffInMinutes;
    		long diffInHours;
    		long diffInDays;
    		
/*           	String query=" select b.bat_pcode,p.pname,p.pack,b.bat_no,b.expiry,b.bat_clos,b.bat_clos " +
		    " from "+tblnm1+" as p inner join "+tblnm2+" as b on p.pcode=b.bat_pcode " +
		    " where b.depo_code="+dp+" and TO_DAYS(b.expiry) - TO_DAYS('"+sdt+"') < 365 and b.bat_clos<>0 and p.pd_type='M' " +
    		" order by b.bat_pcode,b.expiry "; 
*/           	         
           	String query=" select b.bat_pcode,p.pname,p.pack,b.bat_no,b.expiry,b.bat_clos,b.bat_clos " +
		    " from "+tblnm1+" as p inner join "+tblnm2+" as b on p.pcode=b.bat_pcode " +
		    " where b.depo_code="+dp+"  and b.bat_clos<>0 and p.pd_type='M' " +
    		" order by b.bat_pcode,b.expiry ";
           	
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
			    if (rst.getDate(5).getTime()>ndt.getTime())
			    {
					rfb = new SampleRepo3FormBean();
					rfb.setPcode(rst.getInt(1));
					rfb.setPname(rst.getString(2)+ "," + rst.getString(3));
					rfb.setBat_no(rst.getString(4));
				    rfb.setExpiry(rst.getDate(5));
		
					diffInMilleseconds =  rst.getDate(5).getTime()-sdt.getTime();
			        diffInSeconds = diffInMilleseconds/1000;
			        diffInMinutes = diffInSeconds/60;
			        diffInHours = diffInMinutes/60;
			        diffInDays = diffInHours/24;
			        
			         if (diffInDays<0)
							rfb.setEstock(rst.getInt(6));
					
			         if ((diffInDays>=0) && (diffInDays<180))
							rfb.setEstock90(rst.getInt(6));
					
//			         if ((diffInDays>=90) && (diffInDays<360))
			         if (diffInDays>=180) 
							rfb.setEstock360(rst.getInt(6));
					
			         rfb.setTvalue(rst.getInt(7));

					data.add(rfb);
			    }
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLSampleRepo3DAO.getRepo3 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo3DAO.Connection.close "+e);
			  }
		}		
		return data;
	}	
	
}