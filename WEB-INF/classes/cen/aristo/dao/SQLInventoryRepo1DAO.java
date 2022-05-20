package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.InventoryRepo1FormBean;

public class SQLInventoryRepo1DAO {
	
	public List getRepo1(Connection con, String typ, Date sdate, Date edate,Date odate) { 
		 
		InventoryRepo1FormBean rfb;
		
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		
		Date sdt= null;
		Date edt= null;
		Date odt= null;
		String month= null;

		List<InventoryRepo1FormBean> data = new ArrayList<InventoryRepo1FormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            tblnm=typ+"_invsnd";
           	tblnm1=typ+"_prdmsfl";
           	tblnm2=typ+"_prdopng";
           	tblnm3="monthfl";

    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		odt= new java.sql.Date (odate.getTime());
    
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
			String query="select b.pname,b.pack,a.openqty,b.receipt,(a.openqty+ifnull(b.receipt,0)) "+ 
			" as Total,b.despatches,(a.openqty+ifnull(b.receipt,0)-ifnull(b.despatches,0)) as Closing,"+
			" ((a.openqty+ifnull(b.receipt,0)-ifnull(b.despatches,0))*b.trd_rt1) as Closval from "+
			" (select prd.pcode,(p.opngoct+ifnull(b.rcp,0)-ifnull(b.isu,0)) as openqty "+
			" from "+tblnm1+" as prd left join "+tblnm2+" as p on prd.pcode=p.opcode "+
			" left join (select sprd_cd,sum(receipt)as rcp,sum(issue) as isu "+
			" from (select sprd_cd,0 as Receipt,sum(sqty+sfree_qty) as Issue "+
			" from "+tblnm+" where sdoc_type in(40,41,67) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "'"+ 
			" group by sprd_cd union all select sprd_cd,sum(sqty+sfree_qty) "+
			" as Receipt,0 as Issue from "+tblnm+" where sdoc_type in(60,61,62) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' "+  
			" group by sprd_cd) a group by sprd_cd) b on p.opcode = b.sprd_cd) a "+
			" inner join "+
			" (select prd.pcode,prd.pname,prd.pack,prd.trd_rt1,b.rcp as Receipt, b.isu as Despatches "+
			" from "+tblnm1+" as prd  "+
			" left join (select sprd_cd,sum(receipt)as rcp,sum(issue) as isu "+ 
			" from (select sdoc_type,sprd_cd,0 as Receipt,sum(sqty+sfree_qty) as Issue "+ 
			" from "+tblnm+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+ 
			" group by sprd_cd union all select sdoc_type,sprd_cd,sum(sqty+sfree_qty) "+
			" as Receipt,0 as Issue from "+tblnm+" where sdoc_type in(60,61,62) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+ 
			" group by sprd_cd) a group by sprd_cd) b on prd.pcode = b.sprd_cd) b "+
			" on a.pcode=b.pcode order by b.pname "+ ;
			
			 
 
 */			
			String quer=" select sum(((p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0))*prd.trd_rt1)) as opening,sum(ifnull(b.rval,0)) as Rvalue, " +
			" sum(ifnull(b.ival,0)) as Ivalue, " +
			" sum((((p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0))+ifnull(b.rqty,0)-ifnull(b.iqty,0))*prd.trd_rt1)) as closval " +
			" from "+tblnm1.toLowerCase()+" as prd  " +
			" left join "+tblnm2.toLowerCase()+" as p on prd.pcode=p.opcode " + 
			" left join " +
			" (select sprd_cd,sum(orqty) as oprqty, sum(oiqty) as opiqty, sum(receipt) as rqty,sum(rvalue) as rval,sum(issue) as " + 
			" iqty,sum(ivalue) as ival " +
			" from " +
			" (select sdoc_type,sprd_cd,0 as orqty, 0 as oiqty,0 as Receipt,0.00 as Rvalue,sum(sqty+sfree_qty) as Issue,sum(sqty*srate_mfg) " + 
			" as Ivalue " +  
			" from "+tblnm.toLowerCase()+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
			" group by sprd_cd " +
			" union all " +
			" select sdoc_type,sprd_cd,0 as orqty, 0 as oiqty,sum(sqty+sfree_qty) " +
			" as Receipt,sum(sqty*srate_mfg)  " +
			" as Rvalue, 0 as Issue,0.00 as Ivalue from "+tblnm.toLowerCase()+" where sdoc_type in(60,61,62) and sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
			" group by sprd_cd " +
			" union all " +
			" select sdoc_type,sprd_cd,0 as orqty, sum(sqty+sfree_qty) as oiqty,0 as Receipt,0.00 as Rvalue,0 as Issue,0.00 as Ivalue " +  
			" from "+tblnm.toLowerCase()+" where sdoc_type in(40,41,67) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' " +
			" group by sprd_cd " +
			" union all  " +
			" select sdoc_type,sprd_cd,sum(sqty+sfree_qty) as orqty, 0 as oiqty,0 as Receipt,0.00 as Rvalue,0 as Issue,0.00 as Ivalue " +  
			" from "+tblnm.toLowerCase()+" where sdoc_type in(60,61,62) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' " +
			" group by sprd_cd) a group by sprd_cd) b on p.opcode=b.sprd_cd order by prd.pname ";
			


/*			String query="select b.pname,b.pack,a.openqty,b.receipt,(a.openqty+ifnull(b.receipt,0)) "+ 
			" as Total,b.despatches,(a.openqty+ifnull(b.receipt,0)-ifnull(b.despatches,0)) as Closing,"+
			" ((a.openqty+ifnull(b.receipt,0)-ifnull(b.despatches,0))*b.trd_rt1) as Closval from "+
			" (select prd.pcode,(p.opngoct+ifnull(b.rcp,0)-ifnull(b.isu,0)) as openqty "+
			" from "+tblnm1+" as prd left join "+tblnm2+" as p on prd.pcode=p.opcode "+
			" left join (select sprd_cd,sum(receipt)as rcp,sum(issue) as isu "+
			" from (select sprd_cd,0 as Receipt,sum(sqty+sfree_qty) as Issue "+
			" from "+tblnm+" where sdoc_type in(40,41,67) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "'"+ 
			" group by sprd_cd union all select sprd_cd,sum(sqty+sfree_qty) "+
			" as Receipt,0 as Issue from "+tblnm+" where sdoc_type in(60,61,62) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' "+  
			" group by sprd_cd) a group by sprd_cd) b on p.opcode = b.sprd_cd) a "+
			" inner join "+
			" (select prd.pcode,prd.pname,prd.pack,prd.trd_rt1,b.rcp as Receipt, b.isu as Despatches "+
			" from "+tblnm1+" as prd  "+
			" left join (select sprd_cd,sum(receipt)as rcp,sum(issue) as isu "+ 
			" from (select sdoc_type,sprd_cd,0 as Receipt,sum(sqty+sfree_qty) as Issue "+ 
			" from "+tblnm+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+ 
			" group by sprd_cd union all select sdoc_type,sprd_cd,sum(sqty+sfree_qty) "+
			" as Receipt,0 as Issue from "+tblnm+" where sdoc_type in(60,61,62) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+ 
			" group by sprd_cd) a group by sprd_cd) b on prd.pcode = b.sprd_cd) b "+
			" on a.pcode=b.pcode order by b.pname " ; 
*/  

			
			String query="select prd.pname,prd.pack,(p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0)) as opening,ifnull(b.rqty,0) as Recept, "+
			" (p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0)+ifnull(b.rqty,0)) as Total,ifnull(b.iqty,0) as Issu, "+
			" ((p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0))+ifnull(b.rqty,0)-ifnull(b.iqty,0)) as closqty, "+
			" ((p.opng"+month+"+ifnull(b.oprqty,0)-ifnull(b.opiqty,0))+ifnull(b.rqty,0)-ifnull(b.iqty,0))*prd.trd_rt1 as closval "+  
			" from "+tblnm1.toLowerCase()+" as prd   "+
			" left join "+tblnm2.toLowerCase()+" as p on prd.pcode=p.opcode "+  
			" left join  "+
			" (select sprd_cd,sum(orqty) as oprqty, sum(oiqty) as opiqty, sum(receipt) as rqty,sum(rvalue) as rval,sum(issue) as "+ 
			" iqty,sum(ivalue) as ival  "+
			" from  "+
			" (select sdoc_type,sprd_cd,0 as orqty, 0 as oiqty,0 as Receipt,0.00 as Rvalue,sum(sqty+sfree_qty) as Issue,sum(sqty*srate_mfg) "+ 
			" as Ivalue  "+
			" from "+tblnm.toLowerCase()+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+ 
			" group by sprd_cd  "+
			" union all  "+
			" select sdoc_type,sprd_cd,0 as orqty, 0 as oiqty,sum(sqty+sfree_qty) "+   
			" as Receipt,sum(sqty*srate_mfg)   "+
			" as Rvalue, 0 as Issue,0.00 as Ivalue from "+tblnm.toLowerCase()+" where sdoc_type in(60,61,62) and sinv_dt between '"+ sdt+ "' and '"+edt+"' "+  
			" group by sprd_cd "+ 
			" union all  "+
			" select sdoc_type,sprd_cd,0 as orqty, sum(sqty+sfree_qty) as oiqty,0 as Receipt,0.00 as Rvalue,0 as Issue,0.00 as Ivalue "+  
			" from "+tblnm.toLowerCase()+" where sdoc_type in(40,41,67) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' "+ 
			" group by sprd_cd  "+
			" union all   "+
			" select sdoc_type,sprd_cd,sum(sqty+sfree_qty) as orqty, 0 as oiqty,0 as Receipt,0.00 as Rvalue,0 as Issue,0.00 as Ivalue "+ 
			" from "+tblnm.toLowerCase()+" where sdoc_type in(60,61,62) and sinv_dt >= '"+ odt+ "' and sinv_dt< '"+ sdt+ "' "+ 
			" group by sprd_cd) a group by sprd_cd) b on p.opcode=b.sprd_cd order by prd.pname " ;
		 
 			
			
			
			
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();  
			while (rst.next())   
			{
				
                if ((rst.getInt(3)+rst.getInt(4)+rst.getInt(6))!=0)
                {
				rfb = new InventoryRepo1FormBean();
				rfb.setPname(rst.getString(1));
				rfb.setPack(rst.getString(2));
				rfb.setOpng(rst.getInt(3));
				rfb.setRecp(rst.getInt(4));
				rfb.setTotal(rst.getInt(5));
				rfb.setIssu(rst.getInt(6));
				rfb.setClos(rst.getInt(7));
				rfb.setClosval(rst.getDouble(8));

				data.add(rfb);
                }
			}     
			rst.close();
			ps.close();
			
			rst=null;
			ps=null;

 
			ps = con.prepareStatement(quer);
			rst = ps.executeQuery();
			
			if (rst.next())
			{
				rfb = new InventoryRepo1FormBean();
				rfb.setPname("T0TAL");
				rfb.setOpng(rst.getInt(1));
				rfb.setRecp(rst.getInt(2));
				rfb.setIssu(rst.getInt(3));
				rfb.setClosval(rst.getInt(4));
				data.add(rfb);
			}
			
			rst.close();
			ps.close(); 
			
			rst=null;
			ps=null;
			rst1=null;
			ps1=null;

			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo1DAO.getRepo1 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo1DAO.Connection.close "+e);
			  }
		}		
		
		
		
		return data;
	}	

}
