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

import com.aristo.valueobject.SampleRepo3FormBean;

public class SQLInv5DAO {

	public List getInv5(Connection con, String typ, Date sdate,int dp,int eyear ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
              
           	tblnm1=(typ+"_product08").toLowerCase();
           	tblnm2=(typ+"_batch08").toLowerCase();

    		sdt= new java.sql.Date (sdate.getTime());
/*            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());
*/    		int diffInDays;
    		
           	String query="select b.bat_pcode,p.pname,p.pack,b.bat_no,b.bat_rcpdt,b.expiry,b.bat_clos,"+
           	" TO_DAYS('"+sdt+"')-TO_DAYS(b.bat_rcpdt) as ndays "+
           	" from "+tblnm1+" as p, "+tblnm2+" as b where p.pcode=b.bat_pcode "+ 
           	" and b.depo_code="+dp+"  and b.bat_clos<>0 and p.mkt_year="+eyear+" order by b.bat_pcode,b.expiry";		
           	         
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
//			    if (rst.getDate(5).getTime()>ndt.getTime())
//			    {
					rfb = new SampleRepo3FormBean();
					rfb.setPcode(rst.getInt(1));
					rfb.setPname(rst.getString(2));
					rfb.setPack(rst.getString(3));
					rfb.setBat_no(rst.getString(4));
					rfb.setRcpdate(rst.getDate(5));
					rfb.setExpiry(rst.getDate(6));
					rfb.setStock(rst.getString(7));
					
			        diffInDays = rst.getInt(8);
			        
			         if (diffInDays<60)
							rfb.setEstock(rst.getInt(8));
					
			         if ((diffInDays>=60) && (diffInDays<90))
							rfb.setEstock90(rst.getInt(8));
					
			         if (diffInDays>=90) 
							rfb.setEstock360(rst.getInt(8));

			        data.add(rfb);
//			    }
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv5DAO.getInv5 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInv5DAO.Inv5.Connection.close "+e);
			  }
		}		
		return data;
	}	
		

	public List getHOInv5(Connection con, String typ, Date sdate,int dp,int eyear,int pcode,int uid ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;            
           	tblnm1=(typ+"_product08").toLowerCase();
           	tblnm2=(typ+"_batch08").toLowerCase();
           	tblnm3=(typ+"_branch08").toLowerCase();           	

    		sdt= new java.sql.Date (sdate.getTime());
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		Date ndt= new java.sql.Date (ndate.getTime());
    		int diffInDays;
    		
           	String query=" select br.ter_name,b.bat_no,b.bat_rcpdt,b.expiry,b.bat_clos, "+
           	" TO_DAYS('"+sdt+"')-TO_DAYS(b.bat_rcpdt) as ndays"+
       		" from "+tblnm1+" as p, "+tblnm2+" as b, "+tblnm3+" as br "+
       		" where b.depo_code=br.depo_code and b.depo_code in (select depo_code from user_branch08 where user_id="+uid+")"+
       		" and p.mkt_year="+eyear+" and p.pcode=b.bat_pcode and p.pcode="+pcode+" and b.bat_pcode="+pcode+" " +
       		" and b.bat_clos<>0 order by b.depo_code,b.bat_pcode,b.expiry";           		

           	ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
			    if (rst.getDate(3).getTime()>ndt.getTime())
			    {
					rfb = new SampleRepo3FormBean();
					rfb.setPname(rst.getString(1));
					rfb.setBat_no(rst.getString(2));
					rfb.setRcpdate(rst.getDate(3));
					rfb.setExpiry(rst.getDate(4));
					rfb.setStock(rst.getString(5));
					
			        diffInDays = rst.getInt(6);
			        
			         if (diffInDays<60)
							rfb.setEstock(rst.getInt(6));
					
			         if ((diffInDays>=60) && (diffInDays<90))
							rfb.setEstock90(rst.getInt(6));
					
			         if (diffInDays>=90) 
							rfb.setEstock360(rst.getInt(6));

			        data.add(rfb);
			    }
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv5DAO.getHO-Inv5 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInv5DAO.Inv5.Connection.close "+e);
			  }
		}		
		return data;
	}		
	

	
////////// Factory Wise Stock As On Date (Report No. 6 Inventory)////////////////////////////////////////////	
	public List getInv6(Connection con, String typ, Date sdate,int dp,int eyear ) { 
		 
		SampleRepo3FormBean rfb;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		
		List<SampleRepo3FormBean> data = new ArrayList<SampleRepo3FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
              
           	tblnm1=(typ+"_product08").toLowerCase();
           	tblnm2=(typ+"_batch08").toLowerCase();
           	tblnm3="factmast";
           	tblnm4=(typ+"_giftmast").toLowerCase();
           	
    		sdt= new java.sql.Date (sdate.getTime());
    		int diffInDays;
    		

    		String query="select bat.*, ifnull(fac.cmp_name,'No Fac Code'),gift.gift_name from "+ 
           	"(select b.bat_pcode,p.pname,p.pack,b.bat_no,b.expiry,b.bat_clos, "+
           	"TO_DAYS(b.expiry)-TO_DAYS('"+sdt+"') as ndays,b.fact_code,b.gift_code "+ 
           	"from "+tblnm1+" as p, "+tblnm2+" as b  where p.pcode=b.bat_pcode "+
           	"and b.depo_code="+dp+"  and b.bat_clos<>0 and p.mkt_year="+eyear+") bat left join  "+tblnm3+" as fac "+
           	"on bat.fact_code=fac.cmp_code "+
           	"left join "+tblnm4+" as gift "+
           	"on bat.gift_code=gift.gift_code "+
           	"order by bat.bat_pcode,bat.expiry";           	

//         	" and b.depo_code="+dp+"  and b.bat_clos<>0 and p.mkt_year="+eyear+" order by b.bat_pcode,b.expiry";		
    		
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
					rfb = new SampleRepo3FormBean();
					rfb.setPcode(rst.getInt(1));
					rfb.setPname(rst.getString(2));
					rfb.setPack(rst.getString(3));
					rfb.setBat_no(rst.getString(4));
					rfb.setExpiry(rst.getDate(5));
					rfb.setStock(rst.getString(6));
					rfb.setFac_name(rst.getString(10));
					rfb.setGift_name(rst.getString(11));
					diffInDays = rst.getInt(7);
			        
			         if (diffInDays<=0)
							rfb.setRemark("EXPIRED");
					

			        data.add(rfb);
			} 
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;

			
		} catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv5DAO.getInv5 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInv5DAO.Inv5.Connection.close "+e);
			  }
		}		
		return data;
	}	
	
	
}
