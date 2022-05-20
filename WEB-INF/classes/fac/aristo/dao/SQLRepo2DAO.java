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

import fac.aristo.valueobject.FacRepo2FormBean;

public class SQLRepo2DAO {

	public List getRepo2(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo2FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo2FormBean> data = new ArrayList<FacRepo2FormBean>();

		try { 
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    		Date ndate=dateformat.parse("01/01/1901");
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		Date ndt= new java.sql.Date (ndate.getTime());
    		if (chc==1)
    		{
            query= " select t.location,l.depo_name,p.pname,sum(t.totalqty),t.boxes,t.lrno," +
            " t.lrdate,t.transporteid,t.vehicleno,t.invoiceno,t.noofcase,t.noofloosecase from f_trans as t, f_branch as l,f_product as p " +
            " where t.depo_code=l.depo_code and l.brn_id=t.branchid and p.F_pcode=t.productid and t.lrdate between '"+sdt+"' and '"+edt+"' " +
            " and t.depo_code=? and l.depo_code=? and t.division='"+typ+"' and p.division='"+typ+"' " +
            " and  t.locationid='"+loc+"' group by t.depo_code,t.invoiceno,t.pcode  order by t.depo_code,t.invoiceno,t.pcode ";            	
    		}
    		if (chc==2)
    		{
            query= " select t.location,l.depo_name,p.pname,sum(t.totalqty),t.boxes,t.lrno," +
            " t.lrdate,t.transporteid,t.vehicleno,t.invoiceno,t.noofcase,t.noofloosecase from f_trans as t, f_branch as l,f_product as p " +
            " where t.depo_code=l.depo_code and l.brn_id=t.branchid and p.F_pcode=t.productid and t.lrdate between '"+sdt+"' and '"+edt+"' " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.invoiceno,t.pcode order by t.depo_code,t.invoiceno,t.pcode ";            	
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
				  if (rst.getInt(10)!=invno)
				  print=true;
			   }
			   
			   if (print)	
			   {
				invno=rst.getInt(10);				   
				rfb = new FacRepo2FormBean();
				rfb.setLocation(rst.getString(2));
				rfb.setBoxes(rst.getInt(5));
				rfb.setLrno(rst.getString(6));
				if (rst.getDate(7).getTime()>ndt.getTime())
					rfb.setLrdate(rst.getDate(7));
				rfb.setTransporterid(rst.getString(8));
				rfb.setVehicleno(rst.getString(9));
				rfb.setInvoice(rst.getInt(10));				
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
				
			   }
			   
			  if (rst.getInt(10)==invno)
			  {
				rfb = new FacRepo2FormBean();
				rfb.setLocation(rst.getString(3));
				rfb.setQuantity(rst.getInt(4));
				rfb.setNcase(rst.getInt(11));
				rfb.setLcase(rst.getInt(12));
				
				data.add(rfb);
			  }
			   
				
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo2DAO.getRepo2 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo2DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

	
}
