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
import com.aristo.valueobject.Cen2FormBean;

public class SQLCen2DAO {

	public List getRepo2(Connection con, String typ, Date sdate, Date edate,int depo) { 
		 
		Cen2FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		String query1=null;		
		List<Cen2FormBean> data = new ArrayList<Cen2FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String br=null;
            tblnm=(typ+"_invfst").toLowerCase();
           	tblnm1=(typ+"_faacms2").toLowerCase();
           	tblnm2=(typ+"_invsnd").toLowerCase();
           	tblnm3=(typ+"_prdmsfl").toLowerCase();
    		
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
    		
/*            query= "select b.pname,b.pack,b.qty,a.mac_name,a.mcity,a.cases,a.mtr_no,a.mtr_dt,a.transport,a.vehicle_no,a.mac_code,a.inv_no " +
            " from (select f.mac_code,f.mac_name,f.mcity,i.inv_no,i.cases,i.mtr_no,i.mtr_dt,i.transport, i.vehicle_no " +
            " from "+tblnm1+" as f  inner join  "+tblnm+" as i on  f.mac_code=i.party_code " +
            " where i.doc_type in (40,41,67) and i.party_code=? and i.inv_date between '"+ sdt+ "' and '"+edt+"') " +
            " as a inner join (select p.pname,p.pack,s.sinv_no,s.sbatch_no,sum(s.sqty+s.sfree_qty) " +
            " as qty from "+tblnm3+" as p inner join "+tblnm2+" as s on p.pcode=s.sprd_cd " +
            " where s.sdoc_type in (40,41,67) and s.sprt_cd=? and s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' " +
            " group by s.sinv_no,sprd_cd) as b on a.inv_no=b.sinv_no" ;  
*/

    		query="select ifnull(y.mac_name,'Fac Name Not Available'),ifnull(y.mcity,'NA'),x.* from " +
    		" (select b.pcode,b.sbatch_no,b.pname,b.pack,b.qty,a.mac_name,a.mcity,a.cases,a.mtr_no,a.mtr_dt,"+
    		" a.transport,a.vehicle_no,a.mac_code,a.inv_no "+
    		" from ("+
    		" select f.mac_code,f.mac_name,f.mcity,i.inv_no,i.cases,i.mtr_no,i.mtr_dt,i.transport, i.vehicle_no"+ 
    		" from "+tblnm1+" as f  inner join  "+tblnm+" as i on  f.mac_code=i.party_code "+ 
    		" where i.doc_type in (40,41,67) and i.party_code=? "+ 
    		" and i.inv_date  between '"+ sdt+ "' and '"+edt+"') as a"+ 
    		" inner join (select p.pcode,p.pname,p.pack,s.sinv_no,s.sbatch_no,sum(s.sqty+s.sfree_qty) as qty"+ 
    		" from "+tblnm3+" as p inner join "+tblnm2+" as s on p.pcode=s.sprd_cd "+ 
    		" where s.sdoc_type in (40,41,67) and s.sprt_cd= ? "+ 
    		" and s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' "+
    		" group by s.sinv_no,s.sprd_cd,s.sbatch_no) as b on a.inv_no=b.sinv_no) x"+
    		" left join "+
    		" (select distinct i.sdoc_type,i.sprt_cd,p.mac_name,p.mcity,i.sprd_cd,i.sbatch_no"+ 
    		" from "+tblnm2+" i , "+tblnm1+" p  "+
    		" where i.sdoc_type in (61,62,60) and musr_cd = 99 "+
    		" and i.sprt_cd = p.mac_code) y "+
    		" on x.pcode = y.sprd_cd and  x.sbatch_no = y.sbatch_no ";
    		            
            ps = con.prepareStatement(query);
			ps.setString(1, br);
			ps.setString(2, br);
		
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
            
            int invno=0;
			while (rst.next())
			{

				   if (tprint)
				   {
					  if (rst.getInt(16)!=invno)
						print=true;
				   }		
				   
				   if (print)	
				   {
					 
					invno=rst.getInt(16);
					rfb = new Cen2FormBean();
					rfb.setPname((rst.getString(8)+", " +rst.getString(9)));
					rfb.setCases(rst.getString(10));
					rfb.setMtr_no(rst.getString(11));
					if (rst.getDate(12).getTime()>ndt.getTime())
						rfb.setMtr_dt(rst.getDate(12));
					rfb.setTransport(rst.getString(13));
					rfb.setVehicle_no(rst.getString(14));
					rfb.setColour(2);				
					data.add(rfb);
					print=false;
					tprint=true;
				   }				

				  if (rst.getInt(16)==invno)
				  {
					rfb = new Cen2FormBean();
					rfb.setPname(rst.getString(5)+", "+rst.getString(6));
					rfb.setQty(rst.getInt(7));
					rfb.setMtr_no(rst.getString(4));
					rfb.setTransport((rst.getString(1)+", " +rst.getString(2)));
					data.add(rfb);
				  }
			}
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLCen2DAO.getRepo2 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLCen2DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
}