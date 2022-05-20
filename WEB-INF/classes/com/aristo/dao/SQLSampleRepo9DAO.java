package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.SampleRepo9FormBean;

public class SQLSampleRepo9DAO {

	public List getRepo9(Connection con, String typ, Date sdate, Date edate,int dp) { 
		 
		SampleRepo9FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		String query1=null;
		List<SampleRepo9FormBean> data = new ArrayList<SampleRepo9FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            
            tblnm=(typ+"_sinvsnd").toLowerCase();
           	tblnm1=(typ+"_sprdmsfl").toLowerCase();
           	tblnm2=(typ+"_stermst").toLowerCase();
           	
           	int fs=0;
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		query1=" select sum(no_of_rep) from "+tblnm2+" where depo_code="+dp+" ";
			ps1 = con.prepareStatement(query1);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
			fs=rst1.getInt(1);	
			}
    		rst1.close();
    		ps1.close();
    		
    		query=" select p.pname,p.pack,sum(s.alloc),sum(s.issue) from "+tblnm1+" as p " +
    		" inner join (select sprd_cd,0 as alloc,sqty as issue  from "+tblnm+" where sdepo_code="+dp+" and sdoc_type=40 " +
    		" and sinv_dt between '"+sdt+"' and '"+edt+"' union all select sprd_cd,sqty as alloc,0 as issue  " +
			" from "+tblnm+" where sdepo_code="+dp+" and sdoc_type=70 and sinv_dt between '"+sdt+"' and '"+edt+"')" +
    		" as s on p.pcode=s.sprd_cd group by s.sprd_cd ";
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			
			while (rst.next())
			{
				rfb = new SampleRepo9FormBean();
				rfb.setPname(rst.getString(1));
				rfb.setPack(rst.getString(2));
				rfb.setAllot(rst.getInt(3));
				rfb.setIssue(rst.getInt(4));
				rfb.setPending(rst.getInt(4)-(rst.getInt(3)*fs));
				rfb.setFs(fs);
					
				data.add(rfb);
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo9DAO.getRepo9 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo9DAO.Connection.close "+e);
			  }
		}		
		return data;
	}

	
	
	
}
