package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.CentralRepo1FormBean;

public class SQLRepo1DAO {

	public List getRepo1(Connection con, String typ, Date sdate, Date edate,String br,int chc) { 
		 
		CentralRepo1FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<CentralRepo1FormBean> data = new ArrayList<CentralRepo1FormBean>();

		try { 
			
            String tblnm=null;
            String tblnm1=null;
            tblnm=(typ+"_invfst").toLowerCase();
           	tblnm1=(typ+"_faacms2").toLowerCase();
           	
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
    		if (chc==1)
    		{
            query= "select i.inv_no,i.inv_date,m.mcity,i.bill_amt,i.transport,i.mtr_no,i.mtr_dt," +
            " i.vehicle_no from "+tblnm+" as i inner join "+tblnm1+" as m on i.party_code=m.mac_code " +
            " where i.doc_type in (40,41,67) and i.inv_date between '"+ sdt+ "' and '"+edt+"' " +
            " and i.party_code=? order by i.inv_date,i.inv_no ";            	
    		}
    		if (chc==2)
    		{
            query= "select i.inv_no,i.inv_date,m.mcity,i.bill_amt,i.transport,i.mtr_no,i.mtr_dt," +
            " i.vehicle_no from "+tblnm+" as i inner join "+tblnm1+" as m on i.party_code=m.mac_code " +
            " where i.doc_type in (40,41,67) and i.inv_date between '"+ sdt+ "' and '"+edt+"' " +
            " order by i.inv_date,i.inv_no ";            	
    		}
    		
			ps = con.prepareStatement(query);
			
    		if (chc==1)
    		{
			  ps.setString(1,br);
    		}
    		
			rst = ps.executeQuery();
			int sno=0;	
			
			while (rst.next())
			{
				rfb = new CentralRepo1FormBean();
				sno++;
				rfb.setSno(sno);
				rfb.setInv_no(rst.getInt(1));
				rfb.setInv_date(rst.getDate(2));
				rfb.setBranch(rst.getString(3));
				rfb.setAmount(rst.getDouble(4));
				rfb.setTransporter(rst.getString(5));
				rfb.setLr_no(rst.getString(6));
				if (rst.getDate(7).getTime()>ndt.getTime())
					rfb.setLr_date(rst.getDate(7));
				rfb.setTruck_no(rst.getString(8));
					
				data.add(rfb);
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo1DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo1DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

	
	
	
}
