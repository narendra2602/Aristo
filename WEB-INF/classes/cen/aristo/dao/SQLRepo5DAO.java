package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cen.aristo.valueobject.CentralRepo5FormBean;

public class SQLRepo5DAO {

	
	public List getRepo1(Connection con, String typ, Date sdate, Date edate,String br,int chc) { 
		 
		CentralRepo5FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<CentralRepo5FormBean> data = new ArrayList<CentralRepo5FormBean>();

		try { 
			
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            tblnm=(typ+"_invsnd").toLowerCase();
           	tblnm1=(typ+"_prdmsfl").toLowerCase();
           	tblnm2=(typ+"_faacms2").toLowerCase();
           	sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (chc==1)
    		{
           	query= "select p.pcode,p.pname,p.pack,f.mcity,sum(s.sqty),sum(s.sfree_qty) from " + tblnm1 + "  as p " +
   			"inner join "+tblnm+"  as s on p.pcode=s.sprd_cd inner join "+tblnm2+" as f" +
   			" on s.sprt_cd=f.mac_code where s.sdoc_type in (40,41,67) and s.sprt_cd=? and s.sinv_dt " +
   			" between '"+ sdt+ "' and '"+edt+"' " +
   			" group by s.sprd_cd,s.sprt_cd order by p.pname,f.mcity";
    		}
    		if (chc==2)
    		{
           	query= "select p.pcode,p.pname,p.pack,f.mcity,sum(s.sqty),sum(s.sfree_qty) from " + tblnm1 + "  as p " +
   			"inner join "+tblnm+"  as s on p.pcode=s.sprd_cd inner join "+tblnm2+" as f" +
   			" on s.sprt_cd=f.mac_code where s.sdoc_type in (40,41,67) and s.sinv_dt " +
   			" between '"+ sdt+ "' and '"+edt+"' " +
   			" group by s.sprd_cd,s.sprt_cd order by p.pname,f.mcity";
    		}

			ps = con.prepareStatement(query);
    		if (chc==1)
    		{
    			ps.setString(1,br);
    		}			
    		
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
			int pcode=0;
			int stot=0;
			
			while (rst.next())
			{
				   if (tprint)
				   {
					  if (rst.getInt(1)!=pcode)
					  {
						rfb = new CentralRepo5FormBean();
						rfb.setPack("TOTAL:");
						rfb.setQty(stot);
						rfb.setColour(4);					
						stot=0;
						data.add(rfb);
						  
						print=true;
					  }
				   }				
				   if (print)	
				   {
					pcode=rst.getInt(1);
					rfb = new CentralRepo5FormBean();
					rfb.setPname(rst.getString(2));
					rfb.setPack(rst.getString(3));
					rfb.setColour(2);				
					data.add(rfb);
					print=false;
					tprint=true;
				   }				
				
				  if (rst.getInt(1)==pcode)
				  {  
					rfb = new CentralRepo5FormBean();
					rfb.setPname(rst.getString(4));
					rfb.setQty(rst.getInt(5)+rst.getInt(6));
					stot=stot+rst.getInt(5)+rst.getInt(6);
					data.add(rfb);
				  }
			}

			rfb = new CentralRepo5FormBean();
			rfb.setPack("TOTAL:");
			rfb.setQty(stot);
			rfb.setColour(4);					
			data.add(rfb);

			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLDispatchRepo1DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLDispatchRepo1DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	
	
}
