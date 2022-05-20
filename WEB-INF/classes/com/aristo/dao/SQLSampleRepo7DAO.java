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

import com.aristo.valueobject.SampleRepo7FormBean;

public class SQLSampleRepo7DAO {

	public List getRepo7(Connection con, String typ, Date sdate, Date edate, int dp) { 
		 
		SampleRepo7FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<SampleRepo7FormBean> data = new ArrayList<SampleRepo7FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            tblnm=(typ+"_sinvfst").toLowerCase();
           	tblnm1=(typ+"_sfaacms2").toLowerCase();
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());

            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
    		query=" select s.inv_no,s.inv_date,f.mac_name,f.mcity,s.inv_type,s.mtr_no,s.mtr_dt,s.transport " +
			" from "+tblnm+" as s inner join "+tblnm1+" as f on s.party_code=f.mac_code " +
			" where s.depo_code="+dp+" and s.doc_type=40 and inv_date between '"+sdt+"' and '"+edt+"' " ;    		
    		
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				rfb = new SampleRepo7FormBean();
				rfb.setInv_no(rst.getInt(1));
				rfb.setInv_dt(rst.getDate(2));
				rfb.setName(rst.getString(3));
				rfb.setCity(rst.getString(4));
				rfb.setInv_type(rst.getString(5));
				rfb.setLr_no(rst.getString(6));
			    if (rst.getDate(7).getTime()>ndt.getTime())
				rfb.setLr_dt(rst.getDate(7));
				rfb.setTransport(rst.getString(8));
				data.add(rfb);
			}
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLSampleRepo7DAO.getRepo7 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo7DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
		
	
}