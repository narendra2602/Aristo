package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.SampleRepo2FormBean;

public class SQLSampleRepo1DAO {

	public List getRepo1(Connection con, int div,int depo,String cat, int mon, int myear,String type,int uid,String divnm) { 
		 
		SampleRepo2FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		String month= null;  
		int wdiv=0;

		List<SampleRepo2FormBean> data = null;
		try { 
			
			
			String query1=" select concat(mnth_abbr,'-',mnth_year) from monthfl where mkt_year=? and mnth_no=? "; 
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, myear);
			ps1.setInt(2, mon);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1);
			}
			rst1.close();
			ps1.close();
			
           	 String query=	"select product_name,pack,opening_qty,(daman_qty+bhopal_qty+baddi_qty+other_qty) rcpqty,(gross_qty+free_sales_qty) issue,br_tr_qty transfer, "+
           	 "closing_qty closing,prd_code,mnth_code,concat('BRANCH-',branch,' SAMPLE STOCK STATEMENT FOR THE MONTH ') from dist_sample where mkt_year=? and div_code=? and depo_code=? "+
           	 "and mnth_code in (select mnth_code from monthfl where mkt_year=? and mnth_no=?) and prd_identity=? order by product_name";
           	if(type.equalsIgnoreCase("PMT"))
            {
              	 query=	"select product_name,pack,opening_qty,(daman_qty+bhopal_qty+baddi_qty+other_qty) rcpqty,(gross_qty+free_sales_qty) issue,br_tr_qty transfer, "+
               	 "closing_qty closing,prd_code,mnth_code,concat('BRANCH-',branch,' SAMPLE STOCK STATEMENT FOR THE MONTH ') from dist_sample where mkt_year=? and div_code=? and depo_code=? "+
               	 "and mnth_code in (select mnth_code from monthfl where mkt_year=? and mnth_no=?) and prd_identity=? " +
               	 "and pd_group in (select depo_code from grpmast where div_code=? and gp_code<>0 and gp_code in "+  
               	 "(select gp_code from pmt_group where access_t=? and user_id=? and status='Y')) order by product_name";
              	 
              	 if(div>4 && div<10)
           		  wdiv=div-4;
              	 else if(div==11 || div==21)
           		  wdiv=div-1;
            }
           	
           	  
           	 
			 ps = con.prepareStatement(query);
			 ps.setInt(1, myear); 
			 ps.setInt(2, div);
			 ps.setInt(3, depo);
			 ps.setInt(4, myear);
			 ps.setInt(5, mon);
			 ps.setString(6, cat);
			 if(type.equalsIgnoreCase("PMT"))
			 {
				 ps.setInt(7, wdiv);
				 ps.setString(8, divnm);
				 ps.setInt(9, uid);
				 
			 }
			 
			 rst = ps.executeQuery();
			
			 boolean first=true;
			 while (rst.next())
			 {
				 if((rst.getInt(3)+rst.getInt(4)+rst.getInt(5)+rst.getInt(6)+rst.getInt(7))>0)
				 {
					 if(first)
					 {
						 data = new ArrayList<SampleRepo2FormBean>();
						 first=false;
					 }
					 rfb = new SampleRepo2FormBean();
					 rfb.setPname(rst.getString(1));
					 rfb.setPack(rst.getString(2));
					 rfb.setOqty(rst.getInt(3));
					 rfb.setDamanqty(rst.getInt(4)); // rcp qty
					 rfb.setIqty(rst.getInt(5));
					 rfb.setTqty(rst.getInt(6));
					 rfb.setCqty(rst.getInt(7));
					 rfb.setCode(rst.getInt(8));
					 rfb.setFqty(rst.getInt(9));
					 rfb.setBrname(rst.getString(10)+month);
					 data.add(rfb);
				 }
			} 
			 	 
				rst.close();
				ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo1DAO.getRepo1 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo1DAO.Connection.close "+e);
			  }
		}		
		return data;
	}		

	
	public List getRepo1Trend(Connection con, int div,int depo,String cat, int gpcode, int myear,String type,int uid,String divnm) { 
		 
		SampleRepo2FormBean rfb=null;
		CallableStatement cs=null;
		ResultSet rs =null;

		List<SampleRepo2FormBean> data = null;
		try { 
			
			cs=con.prepareCall("{call getFsTrend(?,?,?,?,?,?,?,?)}");
			cs.setInt(1, myear); 
			cs.setInt(2, div);
			cs.setInt(3, depo);
			cs.setString(4, cat);
			cs.setInt(5, gpcode);
			cs.setString(6, type);
			cs.setInt(7, uid);
			cs.setString(8, divnm);
			rs = cs.executeQuery();

			boolean first=true;
			double octval=0.00;
			double novval=0.00;
			double decval=0.00;
			double janval=0.00;
			double febval=0.00;
			double marval=0.00;
			double aprval=0.00;
			double mayval=0.00;
			double junval=0.00;
			double julval=0.00;
			double augval=0.00;
			double sepval=0.00;
			double totval=0.00;
			while (rs.next())
			{
				{
					if(first)
					{
						data = new ArrayList<SampleRepo2FormBean>();
						first=false;
					}
					rfb = new SampleRepo2FormBean();
					rfb.setCode(rs.getInt(1));
					rfb.setPname(rs.getString(2));
					if(gpcode==0)
					{
						rfb.setOctval(rs.getDouble(3));
						rfb.setNovval(rs.getDouble(4));
						rfb.setDecval(rs.getDouble(5));
						rfb.setJanval(rs.getDouble(6));
						rfb.setFebval(rs.getDouble(7));
						rfb.setMarval(rs.getDouble(8));
						rfb.setAprval(rs.getDouble(9));
						rfb.setMayval(rs.getDouble(10));
						rfb.setJunval(rs.getDouble(11));
						rfb.setJulval(rs.getDouble(12));
						rfb.setAugval(rs.getDouble(13));
						rfb.setSepval(rs.getDouble(14));
						rfb.setTvalue(rs.getDouble(15));
						rfb.setBrname(rs.getString(17));
					}
					else
					{
						rfb.setOctqty(rs.getInt(3));
						rfb.setNovqty(rs.getInt(4));
						rfb.setDecqty(rs.getInt(5));
						rfb.setJanqty(rs.getInt(6));
						rfb.setFebqty(rs.getInt(7));
						rfb.setMarqty(rs.getInt(8));
						rfb.setAprqty(rs.getInt(9));
						rfb.setMayqty(rs.getInt(10));
						rfb.setJunqty(rs.getInt(11));
						rfb.setJulqty(rs.getInt(12));
						rfb.setAugqty(rs.getInt(13));
						rfb.setSepqty(rs.getInt(14));
						rfb.setTqty(rs.getInt(15));
						rfb.setOctval(rs.getDouble(16));
						rfb.setNovval(rs.getDouble(17));
						rfb.setDecval(rs.getDouble(18));
						rfb.setJanval(rs.getDouble(19));
						rfb.setFebval(rs.getDouble(20));
						rfb.setMarval(rs.getDouble(21));
						rfb.setAprval(rs.getDouble(22));
						rfb.setMayval(rs.getDouble(23));
						rfb.setJunval(rs.getDouble(24));
						rfb.setJulval(rs.getDouble(25));
						rfb.setAugval(rs.getDouble(26));
						rfb.setSepval(rs.getDouble(27));
						rfb.setTvalue(rs.getDouble(28));
						rfb.setBrname(rs.getString(30));
						octval+=rs.getDouble(16);
						novval+=rs.getDouble(17);
						decval+=rs.getDouble(18);
						janval+=rs.getDouble(19);
						febval+=rs.getDouble(20);
						marval+=rs.getDouble(21);
						aprval+=rs.getDouble(22);
						mayval+=rs.getDouble(23);
						junval+=rs.getDouble(24);
						julval+=rs.getDouble(25);
						augval+=rs.getDouble(26);
						sepval+=rs.getDouble(27);
						totval+=rs.getDouble(28);
						
						
					}
					data.add(rfb);
				}
			} 
			
			if(gpcode!=0)
			{			 	 
				rfb = new SampleRepo2FormBean();
				rfb.setPname("TOTAL");
				rfb.setOctqty(0);
				rfb.setNovqty(0);
				rfb.setDecqty(0);
				rfb.setJanqty(0);
				rfb.setFebqty(0);
				rfb.setMarqty(0);
				rfb.setAprqty(0);
				rfb.setMayqty(0);
				rfb.setJunqty(0);
				rfb.setJulqty(0);
				rfb.setAugqty(0);
				rfb.setSepqty(0);
				rfb.setTqty(0);
				rfb.setOctval(octval);
				rfb.setNovval(novval);
				rfb.setDecval(decval);
				rfb.setJanval(janval);
				rfb.setFebval(febval);
				rfb.setMarval(marval);
				rfb.setAprval(aprval);
				rfb.setMayval(mayval);
				rfb.setJunval(junval);
				rfb.setJulval(julval);
				rfb.setAugval(augval);
				rfb.setSepval(sepval);
				rfb.setTvalue(totval);
				data.add(rfb);
			}
			
				rs.close();
				cs.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo1DAO.getRepo1Trend " + e);
		}
		finally {
			try {
				if(rs != null){rs.close();} 
				if(cs != null){ cs.close();}
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo1DAO.Connection.close "+e);
			  }
		}		
		return data;
	}		
	
	
	public String getMonth(Connection con,Date dt) { 
		 
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		Date sdt= null;
		String month= null;
		try { 
            String tblnm1=null;            
            tblnm1="monthfl";
    		sdt= new java.sql.Date (dt.getTime());
    		String query1=" select mnth_abbr from "+tblnm1+" where mnth_no=month('"+sdt+"') "; 
			ps1 = con.prepareStatement(query1);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1);
			}
			rst1.close();
			ps1.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo1DAO.getMonth " + e);
		}
		finally {
			try {
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}				
                if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo1DAO.Connection.close "+e);
			  }
		}		
		return month;
	}			
	
	
}
