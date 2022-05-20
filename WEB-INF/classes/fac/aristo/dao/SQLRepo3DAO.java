package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fac.aristo.valueobject.FacRepo3FormBean;

public class SQLRepo3DAO {

	public List getRepo3(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo3FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null; 
		String query=null;
		List<FacRepo3FormBean> data = new ArrayList<FacRepo3FormBean>();

		try { 
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
    		if (chc==1)
    		{
            query= "select p.pcode,p.pname,p.pack,t.location,b.depo_name,t.batchno,t.invoiceno," +
            " t.despatchdate,sum(t.totalqty),t.mrp,t.expdate,t.lrno,t.lrdate,t.transporteid " +
            " from f_trans as t, f_product as p, f_branch as b " +
            " where b.depo_code=t.depo_code and b.brn_id=t.branchid and p.f_pcode=t.productid" +
            " and t.despatchdate between '"+sdt+"' and '"+edt+"' and t.depo_code=? and b.depo_code=? " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.invoiceno,p.pcode,t.batchno order by t.depo_code,t.invoiceno,p.pname ";            	
    		}
    		if (chc==2)
    		{
            query= "select p.pcode,p.pname,p.pack,t.location,b.depo_name,t.batchno,t.invoiceno," +
            " t.despatchdate,sum(t.totalqty),t.mrp,t.expdate,t.lrno,t.lrdate,t.transporteid " +
            " from f_trans as t, f_product as p, f_branch as b " +
            " where b.brn_id=t.branchid and p.F_pcode=t.productid and b.depo_code=t.depo_code and " +
            " t.despatchdate between '"+sdt+"' and '"+edt+"' " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.invoiceno,p.pcode,t.batchno order by t.depo_code,t.invoiceno,p.pname ";            	
    		}
			ps = con.prepareStatement(query);
			
    		if (chc==1)
    		{
			  ps.setString(1,br);
			  ps.setString(2,br);			  
    		}
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
			int invno=0;
			
			while (rst.next())
			{
				
			   if (tprint)
			   {
				  if (rst.getInt(7)!=invno)
				  print=true;
			   }
			   
			   if (print)	
			   {
				invno=rst.getInt(7);				   
				rfb = new FacRepo3FormBean();

				rfb.setLocation(rst.getString(5));
				rfb.setLrno(rst.getString(12));
				if (rst.getDate(13).getTime()>ndt.getTime())
					rfb.setLrdate(rst.getDate(13));
				rfb.setTransportid(rst.getString(14));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
				
			   }
			   
			  if (rst.getInt(7)==invno)
			  {
				rfb = new FacRepo3FormBean();
				rfb.setPcode(rst.getInt(1));
				rfb.setLocation(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setBatchno(rst.getString(6));
				rfb.setInvoiceno(rst.getInt(7));
				rfb.setDespatchdate(rst.getDate(8));
				rfb.setQuantity(rst.getInt(9));
				rfb.setMrp(rst.getDouble(10));
				rfb.setExpdate(rst.getDate(11));

				data.add(rfb);
			  }
			   
				
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo3DAO.getRepo3 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}

///// Factory Goods in Transit (Report for Branch under Factory Tab) 
	public List getBranchRepo3(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo3FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo3FormBean> data = new ArrayList<FacRepo3FormBean>();

		try { 
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		
            query= "select p.pcode,p.pname,p.pack,t.location,b.depo_name,t.batchno,t.invoiceno," +
            " t.despatchdate,sum(t.totalqty),t.mrp,t.expdate,t.lrno,t.lrdate,t.transporteid " +
            " from f_trans as t, f_product as p, f_branch as b " +
            " where b.depo_code=t.depo_code and b.brn_id=t.branchid and p.f_pcode=t.productid" +
            " and t.despatchdate between '"+sdt+"' and '"+edt+"' and t.depo_code=? and b.depo_code=? " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' and transit is null " +
            " group by t.depo_code,t.invoiceno,p.pcode,t.batchno order by t.depo_code,t.invoiceno,p.pname ";            	
			ps = con.prepareStatement(query);
			
			  ps.setString(1,br);
			  ps.setString(2,br);			  

		    rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
			int invno=0;
			
			while (rst.next())
			{
				
			   if (tprint)
			   {
				  if (rst.getInt(7)!=invno)
				  print=true;
			   }
			   
			   if (print)	
			   {
				invno=rst.getInt(7);				   
				rfb = new FacRepo3FormBean();

				rfb.setLocation(rst.getString(5));
				rfb.setLrno(rst.getString(12));
				if (rst.getDate(13).getTime()>ndt.getTime())
					rfb.setLrdate(rst.getDate(13));
				rfb.setTransportid(rst.getString(14));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
				
			   }
			   
			  if (rst.getInt(7)==invno)
			  {
				rfb = new FacRepo3FormBean();
				rfb.setPcode(rst.getInt(1));
				rfb.setLocation(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setBatchno(rst.getString(6));
				rfb.setInvoiceno(rst.getInt(7));
				rfb.setDespatchdate(rst.getDate(8));
				rfb.setQuantity(rst.getInt(9));
				rfb.setMrp(rst.getDouble(10));
				rfb.setExpdate(rst.getDate(11));

				data.add(rfb);
			  }
			   
				
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo3DAO.getRepo3 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}
	
}
