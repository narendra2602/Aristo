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
import cen.aristo.valueobject.CentralRepo3FormBean;

public class SQLRepo3DAO {

	public List getRepo3(Connection con, String typ, Date sdate, Date edate,String br,int chc) { 
		 
		CentralRepo3FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<CentralRepo3FormBean> data = new ArrayList<CentralRepo3FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;

            tblnm=(typ+"_invfst").toLowerCase();
           	tblnm1=(typ+"_faacms2").toLowerCase();
           	tblnm2=(typ+"_invsnd").toLowerCase();
           	tblnm3=(typ+"_prdmsfl").toLowerCase();
    		
    		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");

           	sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
    		if (chc==1)
    		{
            query= "select b.pname,b.pack,b.qty,a.mac_name,a.mcity,a.cases,a.mtr_no,a.mtr_dt,a.transport,a.vehicle_no,a.mac_code,a.inv_no " +
            " from (select f.mac_code,f.mac_name,f.mcity,i.inv_no,i.cases,i.mtr_no,i.mtr_dt,i.transport, i.vehicle_no " +
            " from "+tblnm1+" as f  inner join  "+tblnm+" as i on  f.mac_code=i.party_code " +
            " where i.doc_type in (40,41,67) and i.party_code=? and i.inv_date between '"+ sdt+ "' and '"+edt+"') " +
            " as a inner join (select p.pname,p.pack,s.sinv_no,s.sbatch_no,sum(s.sqty+s.sfree_qty) " +
            " as qty from "+tblnm3+" as p inner join "+tblnm2+" as s on p.pcode=s.sprd_cd " +
            " where s.sdoc_type in (40,41,67) and s.sprt_cd=? and s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' " +
            " group by s.sinv_no,sprd_cd) as b on a.inv_no=b.sinv_no" ;  
    		}
    		
    		if (chc==2)
    		{
            query= "select b.pname,b.pack,b.qty,a.mac_name,a.mcity,a.cases,a.mtr_no,a.mtr_dt,a.transport,a.vehicle_no,a.mac_code,a.inv_no " +
            " from (select f.mac_code,f.mac_name,f.mcity,i.inv_no,i.cases,i.mtr_no,i.mtr_dt,i.transport, i.vehicle_no " +
            " from "+tblnm1+" as f  inner join  "+tblnm+" as i on  f.mac_code=i.party_code " +
            " where i.doc_type in (40,41,67) and i.inv_date between '"+ sdt+ "' and '"+edt+"') " +
            " as a inner join (select p.pname,p.pack,s.sinv_no,s.sbatch_no,sum(s.sqty+s.sfree_qty) " +
            " as qty from "+tblnm3+" as p inner join "+tblnm2+" as s on p.pcode=s.sprd_cd " +
            " where s.sdoc_type in (40,41,67) and s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' " +
            " group by s.sinv_no,sprd_cd) as b on a.inv_no=b.sinv_no" ;  
    		}
    		
			ps = con.prepareStatement(query);
    		if (chc==1)
    		 {
				ps.setString(1, br);
				ps.setString(2, br);
    		 }
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
            
            int invno=0;
			while (rst.next())
			{

				   if (tprint)
				   {
					  if (rst.getInt(12)!=invno)
						print=true;
				   }		
				   
				   if (print)	
				   {
					 
					invno=rst.getInt(12);
					rfb = new CentralRepo3FormBean();
					rfb.setPname((rst.getString(4)+", " +rst.getString(5)));
					rfb.setCases(rst.getString(6));
					rfb.setMtr_no(rst.getString(7));
					if (rst.getDate(8).getTime()>ndt.getTime())
						rfb.setMtr_dt(rst.getDate(8));
					rfb.setTransport(rst.getString(9));
					rfb.setVehicle_no(rst.getString(10));
					rfb.setColour(2);				
					data.add(rfb);
					print=false;
					tprint=true;
				   }				

				  if (rst.getInt(12)==invno)
				  {
					rfb = new CentralRepo3FormBean();
					rfb.setPname(rst.getString(1)+", "+rst.getString(2));
					rfb.setQty(rst.getInt(3));
					data.add(rfb);
				  }

			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo3DAO.getRepo3 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

	
	
	
}
