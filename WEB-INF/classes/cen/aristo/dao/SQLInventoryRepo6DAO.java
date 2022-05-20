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

import cen.aristo.valueobject.InventoryRepo6FormBean;

public class SQLInventoryRepo6DAO {

	public List getRepo6(Connection con, String typ, Date sdate, Date edate, int doc_type) { 
		 
		InventoryRepo6FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;


		List<InventoryRepo6FormBean> data = new ArrayList<InventoryRepo6FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            
            tblnm=typ+"_invsnd";
           	tblnm1=typ+"_prdmsfl";
           	tblnm2=typ+"_invfst";
           	
           	
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
			
           	String query=" select p.pname,p.pack, s.sqty, s.sinv_no, s.sinv_dt,  " +
           			"i.mtr_no,i.mtr_dt,i.transport,i.pinv_no,i.pinv_date,(s.sqty * s.srate_net1),i.vehicle_no " +
           			"from "+tblnm1.toLowerCase()+" as p inner join "+tblnm.toLowerCase()+" as s on p.pcode=s.sprd_cd " +
           			"inner join "+tblnm2.toLowerCase()+" as i on s.sinv_no=i.inv_no  and s.sprt_cd=i.party_code " +
           			"where s.sdoc_type=? and s.sinv_dt between '"+ sdt+ "' and '"+edt+"'" +
           			" and i.inv_date between '"+ sdt+ "' and '"+edt+"' order by s.sinv_no ";
           	
           	
           	
/*         	select p.pname,p.pack,s.sqty,s.sinv_no,s.sinv_dt,
           	i.mtr_no,i.mtr_dt,i.transport,i.pinv_no,i.pinv_date,(s.sqty*s.srate_net1),i.vehicle_no 
           	from a_prdmsfl as p
           	inner join a_invsnd as s on p.pcode=s.sprd_cd
           	inner join a_invfst as i on s.sinv_no=i.inv_no and s.sprt_cd=i.party_code
           	where s.sdoc_type =61 and s.sinv_dt between '2009-10-01' and '2009-10-31'
           	and i.inv_date between '2009-10-01' and '2009-10-31'
           	order by s.sinv_no;
  */         	        
			ps = con.prepareStatement(query);
			ps.setInt(1, doc_type);
			rst = ps.executeQuery();
			while (rst.next())
			{
				
				rfb = new InventoryRepo6FormBean();
				rfb.setPname(rst.getString(1));
				rfb.setPack(rst.getString(2));
				rfb.setSqty(rst.getInt(3));
				rfb.setSinv_no(rst.getInt(4));
				rfb.setSinv_dt(rst.getDate(5));
				rfb.setMtr_no(rst.getString(6));
				if (rst.getDate(7).getTime()>ndt.getTime())
					rfb.setMtr_dt(rst.getDate(7));
				rfb.setTransport(rst.getString(8));
				rfb.setPinv_no(rst.getString(9));
				if (rst.getDate(10).getTime()>ndt.getTime())
					rfb.setPinv_date(rst.getDate(10));
				rfb.setGval(rst.getDouble(11));
				rfb.setVehicle_no(rst.getString(12));
				data.add(rfb);
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo6DAO.getRepo6 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo6DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	

	
	
	
}
