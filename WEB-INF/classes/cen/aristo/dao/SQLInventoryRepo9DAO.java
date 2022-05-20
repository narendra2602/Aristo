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
import cen.aristo.valueobject.InventoryRepo9FormBean;

public class SQLInventoryRepo9DAO {
	
	public List getRepo9(Connection con, String typ, Date sdate, Date edate, int doc_type) { 
		 
		InventoryRepo9FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		Date sdt= null;
		Date edt= null;

		List<InventoryRepo9FormBean> data = new ArrayList<InventoryRepo9FormBean>();
		try { 
            String tblnm2=null;

            tblnm2=typ+"_invfst";
           	
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());

           	String query="select i.pinv_no,i.pinv_date,i.bill_amt,i.ctax1_amt,(i.bill_amt+i.ctax1_amt) " +
           			" as Total, i.cases,i.mtr_no,i.mtr_dt,i.transport,i.vehicle_no from " +
           			" "+tblnm2.toLowerCase()+" as i where i.doc_type=? and i.inv_date between '"+ sdt+ "' and '"+edt+"' order by i.inv_date ";            			
           			
			ps = con.prepareStatement(query);
			ps.setInt(1, doc_type);
			rst = ps.executeQuery();
			while (rst.next())
			{
				rfb = new InventoryRepo9FormBean();
				rfb.setInv_no(rst.getString(1));
				rfb.setInv_dt(rst.getDate(2));
				rfb.setInv_val(rst.getDouble(3));
				rfb.setCst_amt(rst.getDouble(4));
				rfb.setTotal(rst.getDouble(5));
				rfb.setCases(rst.getInt(6));
				rfb.setLr_no(rst.getString(7));
				if (rst.getDate(8).getTime()>ndt.getTime())
					rfb.setLr_dt(rst.getDate(8));
				rfb.setTransport(rst.getString(9));
				rfb.setVehicle_no(rst.getString(10));
				data.add(rfb);
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo9DAO.getRepo9 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo9DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	

	
}
 