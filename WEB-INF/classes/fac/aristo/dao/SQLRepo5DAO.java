package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fac.aristo.valueobject.FacRepo5FormBean;

public class SQLRepo5DAO {

	public List getRepo5(Connection con, String typ, Date sdate, Date edate,int br,int chc,String loc,String sch) { 
		 
		FacRepo5FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo5FormBean> data = new ArrayList<FacRepo5FormBean>();

		try { 
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (chc==1)
    		{
            query=" select p.pcode,p.pname,p.pack,b.depo_name,t.batchno,t.expdate,t.lrdate,sum(t.totalqty) " +
            " from f_trans as t,f_branch as b, (SELECT DISTINCT PCODE,PNAME,PACK,DIVISION FROM F_PRODUCT WHERE TYPE='"+sch+"') as p "+ 
            " where p.pcode=t.pcode and t.branchid = b.brn_id and " +
            " t.depo_code=b.depo_code and despatchdate between '"+sdt+"' and '"+edt+"' and t.pcode=? " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.pcode,t.batchno,t.lrdate order by p.pname,t.depo_code,t.lrdate ";            	
    		}
    		if (chc==2)
    		{
            query=" select p.pcode,p.pname,p.pack,b.depo_name,t.batchno,t.expdate,t.lrdate,sum(t.totalqty) " +
            " from f_trans as t,f_branch as b, (SELECT DISTINCT PCODE,PNAME,PACK,DIVISION FROM F_PRODUCT WHERE TYPE='"+sch+"') as p where p.pcode=t.pcode " +
            " and t.branchid = b.brn_id and t.depo_code=b.depo_code and despatchdate between '"+sdt+"' and '"+edt+"' " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.pcode,t.batchno,t.lrdate order by p.pname,t.depo_code,t.lrdate ";            	
    		}
			ps = con.prepareStatement(query);
			
    		if (chc==1)
    		{
			  ps.setInt(1,br);
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
				if (rst.getInt(1)!=pcode)
				  {
					rfb = new FacRepo5FormBean();
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
				pcode=rst.getInt(1);				   
				rfb = new FacRepo5FormBean();
				rfb.setPcode(pcode);
				rfb.setPname(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
			   }
			   
			  if (rst.getInt(1)==pcode)
			  {
				rfb = new FacRepo5FormBean();
				rfb.setDepo_name(rst.getString(4));
				rfb.setBatchno(rst.getString(5));
				rfb.setExpdate(rst.getDate(6));
				rfb.setLrdate(rst.getDate(7));
				rfb.setQuantity(rst.getInt(8));
				tqty=tqty+rst.getInt(8); 
				data.add(rfb);
			  }

			}
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo5DAO.getRepo5 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo5DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	
	

	// Product wise Dispatch (For Branch under Factory Tab)
	public List getBranchRepo5(Connection con, String typ, Date sdate, Date edate,int br,int chc,String loc,String sch,int depo) 
	{ 
		 
		FacRepo5FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<FacRepo5FormBean> data = new ArrayList<FacRepo5FormBean>();

		try { 
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (chc==1)
    		{
            query=" select p.pcode,p.pname,p.pack,b.depo_name,t.batchno,t.expdate,t.lrdate,sum(t.totalqty) " +
            " from f_trans as t,f_branch as b, (SELECT DISTINCT PCODE,PNAME,PACK,DIVISION FROM F_PRODUCT WHERE TYPE='"+sch+"') as p "+ 
            " where p.pcode=t.pcode and t.branchid = b.brn_id and " +
            " t.depo_code=? and b.depo_code=? and despatchdate between '"+sdt+"' and '"+edt+"' and t.pcode=? " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.pcode,t.batchno,t.lrdate order by p.pname,t.depo_code,t.lrdate ";            	
    		}
    		if (chc==2)
    		{
            query=" select p.pcode,p.pname,p.pack,b.depo_name,t.batchno,t.expdate,t.lrdate,sum(t.totalqty) " +
            " from f_trans as t,f_branch as b, (SELECT DISTINCT PCODE,PNAME,PACK,DIVISION FROM F_PRODUCT WHERE TYPE='"+sch+"') as p where p.pcode=t.pcode " +
            " and t.branchid = b.brn_id and t.depo_code=? and b.depo_code=? and despatchdate between '"+sdt+"' and '"+edt+"' " +
            " and t.division='"+typ+"' and p.division='"+typ+"' and t.locationid='"+loc+"' " +
            " group by t.depo_code,t.pcode,t.batchno,t.lrdate order by p.pname,t.depo_code,t.lrdate ";            	
    		}
			ps = con.prepareStatement(query);
			
			ps.setInt(1,depo);
			ps.setInt(2,depo);
    		if (chc==1)
    		{
			  ps.setInt(3,br);
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
				if (rst.getInt(1)!=pcode)
				  {
					rfb = new FacRepo5FormBean();
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
				pcode=rst.getInt(1);				   
				rfb = new FacRepo5FormBean();
				rfb.setPcode(pcode);
				rfb.setPname(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
			   }
			   
			  if (rst.getInt(1)==pcode)
			  {
				rfb = new FacRepo5FormBean();
				rfb.setDepo_name(rst.getString(4));
				rfb.setBatchno(rst.getString(5));
				rfb.setExpdate(rst.getDate(6));
				rfb.setLrdate(rst.getDate(7));
				rfb.setQuantity(rst.getInt(8));
				tqty=tqty+rst.getInt(8); 
				data.add(rfb);
			  }

			}
			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLRepo5DAO.getRepo5 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLRepo5DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

}
