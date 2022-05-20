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

import com.aristo.valueobject.Cen1FormBean;

public class SQLCen1DAO {

	public List getRepo1(Connection con, String typ, Date sdate, Date edate,int depo) { 
		 
		Cen1FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;		
		Date sdt= null;
		Date edt= null;
		String query=null;
		String query1=null;		
		List<Cen1FormBean> data = new ArrayList<Cen1FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String br=null;            
            tblnm=(typ+"_invfst").toLowerCase();
           	tblnm1=(typ+"_faacms2)").toLowerCase();
           	
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());

    		query1 = "select mac_code from "+tblnm1+" where musr_cd=? ";
    		ps1=con.prepareStatement(query1);
    		ps1.setInt(1, depo);
    		rst1=ps1.executeQuery();
    		if (rst1.next())
    		{
    			br=rst1.getString(1);
    		}
    		rst1.close();
    		ps1.close();
    		
            query= "select i.inv_no,i.inv_date,m.mcity,i.bill_amt,i.transport,i.mtr_no,i.mtr_dt," +
            " i.vehicle_no from "+tblnm+" as i inner join "+tblnm1+" as m on i.party_code=m.mac_code " +
            " where i.doc_type in (40,41,67) and i.inv_date between '"+ sdt+ "' and '"+edt+"' " +
            " and i.party_code=? order by i.inv_date,i.inv_no ";
    		
			ps = con.prepareStatement(query);
  		    ps.setString(1,br);
			rst = ps.executeQuery();
			int sno=0;	
			
			while (rst.next())
			{
				rfb = new Cen1FormBean();
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
			rst1=null;
			ps1=null;
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLCen1DAO.getRepo1 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo1DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
	
}
