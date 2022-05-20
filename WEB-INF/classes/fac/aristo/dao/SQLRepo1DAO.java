package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fac.aristo.valueobject.FacRepo1FormBean;

public class SQLRepo1DAO {

	public List getRepo1(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo1FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo1FormBean> data = new ArrayList<FacRepo1FormBean>();
		
		try {
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
    		if (chc==1)
    		{
            query= "select distinct(t.invoiceno),t.despatchdate,b.depo_name,t.value," +
            " t.transporteid,t.lrno,t.lrdate,t.vehicleno,t.boxes from f_trans as t, " +
            " f_branch as b where t.depo_code=b.depo_code and t.depo_code=? and t.locationid='"+loc+"' and " +
            " t.despatchdate between '"+sdt+"' and '"+edt+"' and t.division='"+typ+"' order by t.invoiceno ";            	
    		}
    		if (chc==2)
    		{
            query= "select distinct(t.invoiceno),t.despatchdate,b.depo_name,t.value," +
            " t.transporteid,t.lrno,t.lrdate,t.vehicleno,t.boxes from f_trans as t, " +
            " f_branch as b where t.depo_code=b.depo_code and t.locationid='"+loc+"' and " +
            " t.despatchdate between '"+sdt+"' and '"+edt+"' and t.division='"+typ+"' order by t.invoiceno ";            	
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
				rfb = new FacRepo1FormBean();
				sno++;
				rfb.setSno(sno);
				rfb.setInvoiceno(rst.getInt(1));
				rfb.setDespatchdate(rst.getDate(2));
				rfb.setDepo_name(rst.getString(3));
				rfb.setValue(rst.getDouble(4));
				rfb.setTransporteid(rst.getString(5));
				rfb.setLrno(rst.getString(6));
				if (rst.getDate(7).getTime()>ndt.getTime())
					rfb.setLrdate(rst.getDate(7));
				rfb.setVehicleno(rst.getString(8));
				rfb.setBoxes(rst.getInt(9));					
				data.add(rfb);
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo1DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo1DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

	
	
	
}
