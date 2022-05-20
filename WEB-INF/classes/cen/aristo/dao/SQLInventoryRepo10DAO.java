package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.InventoryRepo9FormBean;

public class SQLInventoryRepo10DAO {
	
	public List getRepo10(Connection con, String typ, Date sdate, Date edate) { 
		 
		InventoryRepo9FormBean rfb;
		
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		
		Date sdt= null;
		Date edt= null;
		String month= null;

		List<InventoryRepo9FormBean> data = new ArrayList<InventoryRepo9FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            tblnm=typ+"_invsnd";
           	tblnm1=typ+"_prdmsfl";
           	tblnm2=typ+"_prdopng";
           	tblnm3="monthfl";

//			double rval=0.00;
//			double ival=0.00;
			double open=0.00;
			double clos=0.00;

           	
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    
    		String query1=" select mnth_abbr from "+tblnm3.toLowerCase()+" where mnth_no=month('"+sdt+"') "; 
			ps1 = con.prepareStatement(query1);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1);
			}
			rst1.close();
			ps1.close();


/*           	
 *              select sum(p.opngoct*prd.trd_rt1) as opening from a_prdmsfl as prd
                left join a_prdopng as p on prd.pcode=p.opcode;
                
				select s.sinv_dt, sum(receipt*p.trd_rt1) as rqty, sum(issue*p.trd_rt1) as iqty from 
				(select sprd_cd,sinv_dt,0 as Receipt, 
				sum(sqty+sfree_qty) as Issue from a_invsnd where sdoc_type in(40,41,67) and sinv_dt between '2009-10-01' and '2009-10-31'
				group by sprd_cd,sinv_dt 
				union all 
				select sprd_cd,sinv_dt,sum(sqty+sfree_qty) as Receipt, 0 as Issue
				from a_invsnd where sdoc_type in(60,61,62) and sinv_dt between '2009-10-01' and '2009-10-31'
				group by sprd_cd,sinv_dt) s  inner join a_prdmsfl as p on s.sprd_cd=p.pcode
				group by s.sinv_dt; 
                
	
  */         	
			
			   String psum = "select sum(p.opng"+month+"*prd.trd_rt1) as opening from "+tblnm1.toLowerCase()+" as prd " +
			   		"left join "+tblnm2.toLowerCase()+" as p on prd.pcode=p.opcode";

				ps1 = con.prepareStatement(psum);
				rst1 = ps1.executeQuery();
				if (rst1.next())
				{
					open=rst1.getDouble(1);
				}
				rst1.close();
				ps1.close();
			   
			   String query="select s.sinv_dt, sum(receipt*p.trd_rt1) as rqty, sum(issue*p.trd_rt1) as iqty from" +
			   		" (select sprd_cd,sinv_dt,0 as Receipt, sum(sqty+sfree_qty) as Issue from " +
			   		" "+tblnm.toLowerCase()+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
			   		" group by sprd_cd,sinv_dt union all select sprd_cd,sinv_dt,sum(sqty+sfree_qty) as Receipt," +
			   		" 0 as Issue from "+tblnm.toLowerCase()+" where sdoc_type in(60,61,62) and " +
			   		"sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
			   		" group by sprd_cd,sinv_dt) s  inner join "+tblnm1.toLowerCase()+" as p on s.sprd_cd=p.pcode group by s.sinv_dt";  

           	         
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			while (rst.next())   
			{
				
				clos=open+rst.getDouble(2)-rst.getDouble(3);
				rfb = new InventoryRepo9FormBean();
				rfb.setInv_dt(rst.getDate(1));
				rfb.setTotal(clos);
				open=clos;
				data.add(rfb);
				
			}
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo10DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo10DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	
	

}
