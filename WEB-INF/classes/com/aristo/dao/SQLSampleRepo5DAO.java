package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.SampleRepo5FormBean;

public class SQLSampleRepo5DAO {

	public List getRepo5(Connection con, String typ, Date sdate, Date edate,String pcode,int chc,int dp) { 
		 
		SampleRepo5FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		Date sdt= null;
		Date edt= null;
		String query=null;
		List<SampleRepo5FormBean> data = new ArrayList<SampleRepo5FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;            
            tblnm=(typ+"_sinvsnd").toLowerCase();
           	tblnm1=(typ+"_sprdmsfl").toLowerCase();
           	tblnm2=(typ+"_sfaacms2").toLowerCase();
           	
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (chc==1)
    		{
    		query= "select t.sprd_cd,f.mac_code,f.mac_name,f.mcity,t.pname,t.pack,t.sbatch_no,t.sinv_no," +
			" t.sinv_dt,t.sqty,t.sfree_qty,t.sunit_cd from " +
			" (select s.sprd_cd,p.pname,p.pack,s.sbatch_no,s.sinv_no,s.sinv_dt,s.sqty," +
			" s.sfree_qty,s.sunit_cd,s.sprt_cd from "+tblnm+" as s inner join "+tblnm1+" as p " +
			" on s.sprd_cd=p.pcode where s.sdepo_code="+dp+" and s.sdoc_type=40 and s.sprt_cd=? and s.sinv_dt between '"+ sdt+ "' and '"+edt+"') t " +
			" inner join "+tblnm2+" as f on t.sprt_cd=f.mac_code " ;
    		}
    		
    		if (chc==2)
    		{
    		query= "select t.sprd_cd,f.mac_code,f.mac_name,f.mcity,t.pname,t.pack,t.sbatch_no,t.sinv_no," +
			" t.sinv_dt,t.sqty,t.sfree_qty,t.sunit_cd from " +
			" (select s.sprd_cd,p.pname,p.pack,s.sbatch_no,s.sinv_no,s.sinv_dt,s.sqty," +
			" s.sfree_qty,s.sunit_cd,s.sprt_cd from "+tblnm+" as s inner join "+tblnm1+" as p " +
			" on s.sprd_cd=p.pcode where s.sdepo_code="+dp+" and s.sdoc_type=40 and s.sinv_dt between '"+ sdt+ "' and '"+edt+"') t " +
			" inner join "+tblnm2+" as f on t.sprt_cd=f.mac_code " ;
    		}
    		
			ps = con.prepareStatement(query);
			
			if (chc==1)
			{
			ps.setString(1,pcode);
			}
			
			rst = ps.executeQuery();
			
			boolean print=true;
			boolean tprint=true;
			String code="";
			while (rst.next())
			{
				if (!code.equals(rst.getString(2)))
				{
					print=true;
				}
				if (print)
				{
				rfb = new SampleRepo5FormBean();
				rfb.setCode(rst.getString(2));
				rfb.setName(rst.getString(3)+", "+ rst.getString(4));
				code=rst.getString(2);
				rfb.setColor(2);
				data.add(rfb);
				tprint=true;
				}
				if (tprint)
				{
				rfb = new SampleRepo5FormBean();
				rfb.setCode(rst.getString(1));
				rfb.setInv_no(rst.getInt(1));
				rfb.setName(rst.getString(5) +", "+ rst.getString(6));
				rfb.setBatch(rst.getString(7));
				rfb.setInv_no(rst.getInt(8));
				rfb.setInv_dt(rst.getDate(9));
				rfb.setQty(rst.getInt(10));
				rfb.setFqty(rst.getInt(11));
				rfb.setIssue(rst.getInt(12));
				data.add(rfb);
				print=false;
				}
				
			}
			
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo5DAO.getRepo5 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo5DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}

		
	
	
}
