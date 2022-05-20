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

import cen.aristo.valueobject.CentralRepo4FormBean;

public class SQLRepo4DAO {

	public List getRepo4(Connection con, String typ, Date sdate, Date edate,String br,int chc) { 
		 
		CentralRepo4FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<CentralRepo4FormBean> data = new ArrayList<CentralRepo4FormBean>();
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
            query="select a.mac_name,a.mcity,b.sprd_cd,b.pname,b.pack,b.sbatch_no,b.sinv_no," +
            " b.sinv_dt,b.qty,b.srate_mrp,b.sexp_dt, a.mtr_no,a.mtr_dt,a.transport,b.sinv_no,b.sinv_dt,b.sdoc_type, " +
            " a.party_code,b.sprt_cd from (select f.mac_name,f.mcity,i.inv_no,i.inv_date,i.cases,i.mtr_no,i.mtr_dt,i.transport,i.party_code, " +
            " i.vehicle_no from "+tblnm1+" as f  inner join  "+tblnm+" as i on " +
            " f.mac_code=i.party_code where i.doc_type in (40,41,67) and i.party_code=? and " +
            " i.inv_date between '"+ sdt+ "' and '"+edt+"') as a inner join " +
            " (select p.pname,p.pack,s.sprd_cd,s.sinv_no,s.sbatch_no,s.sinv_dt,s.srate_mrp," +
            " s.sexp_dt,sum(s.sqty+s.sfree_qty) as qty,s.sdoc_type,s.sprt_cd from "+tblnm3+" as p inner join " +
            " "+tblnm2+" as s on p.pcode=s.sprd_cd where s.sdoc_type in (40,41,67) and s.sprt_cd=? and " +
            " s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' " +
            " group by s.sinv_no,sprd_cd,s.sbatch_no) as b on a.inv_no=b.sinv_no and a.party_code=b.sprt_cd " +
            " order by b.sinv_dt,b.sinv_no,b.sdoc_type,a.mtr_no"; 
            }

            
            if (chc==2)
            {
            query="select a.mac_name,a.mcity,b.sprd_cd,b.pname,b.pack,b.sbatch_no,b.sinv_no," +
            " b.sinv_dt,b.qty,b.srate_mrp,b.sexp_dt, a.mtr_no,a.mtr_dt,a.transport,b.sinv_no,b.sinv_dt,b.sdoc_type, " +
            " a.party_code,b.sprt_cd from (select f.mac_name,f.mcity,i.inv_no,i.inv_date,i.cases,i.mtr_no,i.mtr_dt,i.transport,i.party_code, " +
            " i.vehicle_no from "+tblnm1+" as f  inner join  "+tblnm+" as i on " +
            " f.mac_code=i.party_code where i.doc_type in (40,41,67) and " +
            " i.inv_date between '"+ sdt+ "' and '"+edt+"') as a inner join " +
            " (select p.pname,p.pack,s.sprd_cd,s.sinv_no,s.sbatch_no,s.sinv_dt,s.srate_mrp," +
            " s.sexp_dt,sum(s.sqty+s.sfree_qty) as qty,s.sdoc_type,s.sprt_cd from "+tblnm3+" as p inner join " +
            " "+tblnm2+" as s on p.pcode=s.sprd_cd where s.sdoc_type in (40,41,67) and " +
            " s.sinv_dt  between '"+ sdt+ "' and '"+edt+"' " +
            " group by s.sinv_no,sprd_cd,s.sbatch_no) as b on a.inv_no=b.sinv_no and a.party_code=b.sprt_cd " +
            " order by b.sinv_dt,b.sinv_no,b.sdoc_type,a.mtr_no"; 
            }

			ps = con.prepareStatement(query);
            if (chc==1)
            {
            	ps.setString(1,br);
            	ps.setString(2,br);            	
            }
			rst = ps.executeQuery();
			boolean print=true;
			boolean tprint=false;
			String lrno=null;

			while (rst.next())
			{
				if (tprint)
				{
					if(!rst.getString(12).equals(lrno))
						print=true;
				}
				
				if (print)
				{
					lrno=rst.getString(12);
					rfb = new CentralRepo4FormBean();
					rfb.setName(rst.getString(1)+", " +rst.getString(2));
					rfb.setMtr_no(rst.getString(12));
					if (rst.getDate(13).getTime()>ndt.getTime())
						rfb.setMtr_dt(rst.getDate(13));
					rfb.setTransport(rst.getString(14));
					rfb.setColour(4);
					data.add(rfb);
					print=false;
					tprint=true;
				}
				
			   if (rst.getString(12).equals(lrno))
			   {
				rfb = new CentralRepo4FormBean();
				rfb.setPcode(rst.getInt(3));
				rfb.setName(rst.getString(4));
				rfb.setPack(rst.getString(5));
				rfb.setSbatch_no(rst.getString(6));
				rfb.setSinv_no(rst.getInt(7));
				rfb.setSinv_dt(rst.getDate(8));
				rfb.setQty(rst.getInt(9));
				rfb.setMrp(rst.getDouble(10));
				rfb.setExpiry_dt(rst.getString(11));
				data.add(rfb);
			   }
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo4DAO.getRepo4 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo4DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

}
