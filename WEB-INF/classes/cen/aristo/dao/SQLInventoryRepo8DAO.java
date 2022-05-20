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

import cen.aristo.valueobject.InventoryRepo8FormBean;

public class SQLInventoryRepo8DAO {
	public List getRepo8(Connection con, String typ, Date sdate, Date edate) { 
		 
		InventoryRepo8FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		Date sdt= null;
		Date edt= null;

		List<InventoryRepo8FormBean> data = new ArrayList<InventoryRepo8FormBean>();
		try { 
            
            String tblnm1=null;
            String tblnm2=null;
            
           	tblnm1=typ+"_faacms2";
           	tblnm2=typ+"_invfst";
           	
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
			
           	String query="select f.mac_name,f.mcity,i.pinv_no,i.pinv_date,i.bill_amt,i.ctax1_amt," +
           			" (i.bill_amt+i.ctax1_amt) as Total, i.cases,i.mtr_no,i.mtr_dt,i.transport," +
           			" i.vehicle_no from "+tblnm1.toLowerCase()+" as f inner join "+tblnm2.toLowerCase()+" as i on f.mac_code=i.party_code " +
           			" where i.doc_type in (60,61,62) and i.inv_date between '"+ sdt+ "' and '"+edt+"' order by i.inv_date ";
           	        
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next())
			{
				rfb = new InventoryRepo8FormBean();
				rfb.setName(rst.getString(1));
				rfb.setCity(rst.getString(2));
				rfb.setInv_no(rst.getString(3));
				rfb.setInv_dt(rst.getDate(4));
				rfb.setInv_val(rst.getDouble(5));
				rfb.setCst_amt(rst.getDouble(6)); 
				rfb.setTotal(rst.getDouble(7));
				rfb.setCases(rst.getInt(8));
				rfb.setLr_no(rst.getString(9));
				if (rst.getDate(10).getTime()>ndt.getTime())
					rfb.setLr_dt(rst.getDate(10));
				rfb.setTransport(rst.getString(11));
				rfb.setVehicle_no(rst.getString(12));
				data.add(rfb);
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo8DAO.getRepo8 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo8DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

	
}
