package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.aristo.valueobject.Cen4FormBean;

public class SQLCen4DAO {
	
	public List getRepo4(Connection con, String typ, Date sdate, Date edate,int depo) { 
		 
		Cen4FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		
		Date sdt= null;
		Date edt= null;
		String query=null;
		String query1=null;
		List<Cen4FormBean> data = new ArrayList<Cen4FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String br=null;
            
            tblnm=(typ+"_invsnd").toLowerCase();
           	tblnm1=(typ+"_prdmsfl").toLowerCase();
           	tblnm2=(typ+"_faacms2").toLowerCase();
           	sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		query1 = "select mac_code from "+tblnm2+" where musr_cd=? ";
    		ps1=con.prepareStatement(query1);
    		ps1.setInt(1, depo);
    		rst1=ps1.executeQuery();
    		if (rst1.next())
    		{
    			br=rst1.getString(1);
    		}
    		rst1.close();
    		ps1.close();

           	query= "select p.pcode,p.pname,p.pack,f.mcity,sum(s.sqty),sum(s.sfree_qty) from " + tblnm1 + "  as p " +
   			"inner join "+tblnm+"  as s on p.pcode=s.sprd_cd inner join "+tblnm2+" as f" +
   			" on s.sprt_cd=f.mac_code where s.sdoc_type in (40,41,67) and s.sprt_cd=? and s.sinv_dt " +
   			" between '"+ sdt+ "' and '"+edt+"' " +
   			" group by s.sprd_cd,s.sprt_cd order by p.pname,f.mcity";

			ps = con.prepareStatement(query);
    		ps.setString(1,br);
    		
			rst = ps.executeQuery();
			while (rst.next())
			{
					rfb = new Cen4FormBean();
					rfb.setPname(rst.getString(2));
					rfb.setPack(rst.getString(3));
					rfb.setQty(rst.getInt(5)+rst.getInt(6));
					data.add(rfb);
			}

			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLCen4DAO.getRepo4 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLCen4DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	
	
}
