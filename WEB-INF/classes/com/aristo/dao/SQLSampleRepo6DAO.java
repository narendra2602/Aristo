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

import com.aristo.valueobject.SampleRepo6FormBean;

public class SQLSampleRepo6DAO {

	public List getRepo6(Connection con, String typ, Date sdate, Date edate,int dp) { 
		 
		SampleRepo6FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<SampleRepo6FormBean> data = new ArrayList<SampleRepo6FormBean>();
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
    		
    		query=" select s.inv_no,s.inv_date,f.mac_name,f.mcity,s.mtr_no,s.mtr_dt,s.transport " +
			" from "+tblnm+" as s inner join "+tblnm1+" as f on s.party_code=f.mac_code " +
			" where s.depo_code="+dp+" and s.doc_type=67 and s.inv_date between '"+sdt+"' and '"+edt+"' order by s.inv_no ";    		
    		
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				rfb = new SampleRepo6FormBean();
				rfb.setInv_no(rst.getInt(1));
				rfb.setInv_dt(rst.getDate(2));
				rfb.setName(rst.getString(3));
				rfb.setCity(rst.getString(4));
				rfb.setLr_no(rst.getString(5));
			    if (rst.getDate(6).getTime()>ndt.getTime())
				rfb.setLr_dt(rst.getDate(6));
				rfb.setTransport(rst.getString(7));
				data.add(rfb);
			}
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLSampleRepo6DAO.getRepo6 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo6DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
	
}