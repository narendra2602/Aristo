package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fac.aristo.valueobject.FacRepo4FormBean;

public class SQLRepo4DAO {

	public List getRepo4(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo4FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo4FormBean> data = new ArrayList<FacRepo4FormBean>();

		try { 
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (chc==1)
    		{
            query=" select b.depo_name,t.pcode,p.pname,p.pack,sum(t.totalqty) from f_trans as t,f_branch as b," +
            " f_product as p where despatchdate between '"+sdt+"' and '"+edt+"' and t.depo_code=b.depo_code " +
            " and b.brn_id=t.branchid and p.f_pcode=t.productid and t.depo_code=? and b.depo_code=? and t.division='"+typ+"' and p.division='"+typ+"' " +
            " and t.locationid='"+loc+"' group by p.pcode,t.depo_code order by p.pname,b.depo_name ";            	
    		}
    		if (chc==2)
    		{
            query=" select b.depo_name,t.pcode,p.pname,p.pack,sum(t.totalqty) from f_trans as t,f_branch as b," +
            " f_product as p where despatchdate between '"+sdt+"' and '"+edt+"' and t.depo_code=b.depo_code " +
            " and b.brn_id=t.branchid and p.f_pcode=t.productid and t.division='"+typ+"' and p.division='"+typ+"' " +
            " and t.locationid='"+loc+"' group by p.pcode,t.depo_code order by p.pname,b.depo_name ";            	
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
			int pcode=0;
			int tqty=0;
			while (rst.next())
			{
				
			   if (tprint)
			   {
				if (rst.getInt(2)!=pcode)
				  {
					rfb = new FacRepo4FormBean();
					rfb.setPack("TOTAL:");
					rfb.setQuantity(tqty);
					rfb.setColour(4);					
					tqty=0;
					data.add(rfb);
					print=true;
				  }				
				
			   }
			   
			   if (print)	
			   {
				pcode=rst.getInt(2);				   
				rfb = new FacRepo4FormBean();
				rfb.setPname(rst.getString(3));
				rfb.setPack(rst.getString(4));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
			   }
			   
			  if (rst.getInt(2)==pcode)
			  {
				rfb = new FacRepo4FormBean();
				rfb.setPname(rst.getString(1));
				rfb.setQuantity(rst.getInt(5));
				tqty=tqty+rst.getInt(5);
				data.add(rfb);
			  }
			  
			  
			  
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo4DAO.getRepo4 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo4DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	
	


	public List getBranchRepo4(Connection con, String typ, Date sdate, Date edate,String br,int chc,String loc) { 
		 
		FacRepo4FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo4FormBean> data = new ArrayList<FacRepo4FormBean>();

		try { 
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
            query=" select b.depo_name,t.pcode,p.pname,p.pack,sum(t.totalqty) from f_trans as t,f_branch as b," +
            " f_product as p where despatchdate between '"+sdt+"' and '"+edt+"' and t.depo_code=b.depo_code " +
            " and b.brn_id=t.branchid and p.f_pcode=t.productid and t.depo_code=? and b.depo_code=? and t.division='"+typ+"' and p.division='"+typ+"' " +
            " and t.locationid='"+loc+"' group by p.pcode,t.depo_code order by p.pname,b.depo_name ";            	
			ps = con.prepareStatement(query);
			
			  ps.setString(1,br);
			  ps.setString(2,br);			  
			  rst = ps.executeQuery();

			while (rst.next())
			{
				rfb = new FacRepo4FormBean();
				rfb.setPname(rst.getString(3));
				rfb.setPack(rst.getString(4));
				rfb.setQuantity(rst.getInt(5));
				data.add(rfb);
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Branch Factory SQLRepo4DAO.getRepo4 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Branch Factory SQLRepo4DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

}
