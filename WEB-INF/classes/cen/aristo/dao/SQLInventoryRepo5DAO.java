package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.InventoryRepo5FormBean;

public class SQLInventoryRepo5DAO {

	public List getRepo5(Connection con, String typ, Date sdate, Date edate,int ch,int pcd) { 
		 
		InventoryRepo5FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;

		Date sdt= null;
		Date edt= null;
		String month= null;

		List<InventoryRepo5FormBean> data = new ArrayList<InventoryRepo5FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String tblnm5=null;
            String query=null;
            
           	tblnm1=typ+"_faacms2";
           	tblnm2=typ+"_invsnd";
           	tblnm3=typ+"_prdmsfl";
           	tblnm4=typ+"_prdopng";
           	tblnm5="monthfl";

    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		String query1=" select mnth_abbr from "+tblnm5.toLowerCase()+" where mnth_no=month('"+sdt+"') "; 
			ps1 = con.prepareStatement(query1);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1);
			}
			rst1.close();
			ps1.close();

    		if (ch==1)
    		{
           	 query=" select prd.pcode,prd.pname,prd.pack,prd.opng"+month+",ss.mac_name,ss.mcity," +
		     " ss.sbatch_no,ss.sexp_dt,ss.sinv_no,ss.sinv_dt,ss.rqty,ss.rfree,ss.saleqty," +
		     " ss.salefree,ss.srate_mfg,ss.total,ss.sprd_cd,prd.trd_rt1 from (select p.pcode,p.pname,p.pack,o.opng"+month+",p.trd_rt1 " +
		     " from "+tblnm3.toLowerCase()+" as p inner join "+tblnm4.toLowerCase()+" as o on p.pcode=o.opcode order by p.pcode) " +
		     " as prd left join (select f.mac_name,f.mcity,snd.sprd_cd,snd.sbatch_no,snd.sexp_dt," +
		     " snd.sinv_no,snd.sinv_dt,snd.rqty,snd.rfree,snd.saleqty,snd.salefree,snd.srate_mfg," +
		     " snd.total from "+tblnm1.toLowerCase()+" as f inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt," +
		     " sinv_no,sinv_dt,0 as rqty,0 as rfree,sqty as saleqty,sfree_qty as salefree,srate_mfg," +
		     " sqty*srate_mfg as total from "+tblnm2.toLowerCase()+" where sdoc_type in(40,41,67) " +
		     " and sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
		     " union all select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sinv_no,sinv_dt,sqty as " +
		     " rqty,sfree_qty as rfree, 0 as saleqty,0 as salefree,srate_mfg,sqty*srate_mfg  as total " +
		     " from "+tblnm2.toLowerCase()+" where sdoc_type in(60,61,62) " +
		     " and sinv_dt between '"+ sdt+ "' and '"+edt+"' order by sprd_cd,sinv_dt) as snd " +
		     " on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd where prd.pcode="+pcd+" order by prd.pcode ";
    		}
    		if (ch==2)
    		{
           	 query=" select prd.pcode,prd.pname,prd.pack,prd.opng"+month+",ss.mac_name,ss.mcity," +
		     " ss.sbatch_no,ss.sexp_dt,ss.sinv_no,ss.sinv_dt,ss.rqty,ss.rfree,ss.saleqty," +
		     " ss.salefree,ss.srate_mfg,ss.total,ss.sprd_cd,prd.trd_rt1 from (select p.pcode,p.pname,p.pack,o.opng"+month+",p.trd_rt1 " +
		     " from "+tblnm3.toLowerCase()+" as p inner join "+tblnm4.toLowerCase()+" as o on p.pcode=o.opcode order by p.pcode) " +
		     " as prd left join (select f.mac_name,f.mcity,snd.sprd_cd,snd.sbatch_no,snd.sexp_dt," +
		     " snd.sinv_no,snd.sinv_dt,snd.rqty,snd.rfree,snd.saleqty,snd.salefree,snd.srate_mfg," +
		     " snd.total from "+tblnm1.toLowerCase()+" as f inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt," +
		     " sinv_no,sinv_dt,0 as rqty,0 as rfree,sqty as saleqty,sfree_qty as salefree,srate_mfg," +
		     " sqty*srate_mfg as total from "+tblnm2.toLowerCase()+" where sdoc_type in(40,41,67) " +
		     " and sinv_dt between '"+ sdt+ "' and '"+edt+"' " +
		     " union all select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sinv_no,sinv_dt,sqty as " +
		     " rqty,sfree_qty as rfree, 0 as saleqty,0 as salefree,srate_mfg,sqty*srate_mfg  as total " +
		     " from "+tblnm2.toLowerCase()+" where sdoc_type in(60,61,62) " +
		     " and sinv_dt between '"+ sdt+ "' and '"+edt+"' order by sprd_cd,sinv_dt) as snd " +
		     " on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd order by prd.pcode ";
    		}
    		
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
			int pcode=0;
			int clos=0;
			int rtot=0;
			int itot=0;
			int ftot=0;
			double rate=0;
			
			while (rst.next())
			{
				
				
			   if (tprint)
			   {
				  if (rst.getInt(1)!=pcode)
				  {
					rfb = new InventoryRepo5FormBean();
					rfb.setInv_no("TOTAL:");
					rfb.setRqty(rtot);
					rfb.setSqty(itot);
					rfb.setFqty(ftot);					
					rfb.setColour(4);					
					data.add(rfb);
					  
					rfb = new InventoryRepo5FormBean();
					rfb.setExp_dt("CLOSING BALANCE:");
					rfb.setRqty(clos);
					rfb.setRate(rate);
					rfb.setTvalue(clos*rate);
					rfb.setColour(3);
					itot=0;
					rtot=0;
					clos=0;
					data.add(rfb);
					print=true;
				  }
				   
			   }
			   
			   if (print)	
			   {
				pcode=rst.getInt(1);
				rate=rst.getDouble(18);
				rfb = new InventoryRepo5FormBean();
				rfb.setPcode(rst.getString(1));
				rfb.setName(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setExp_dt("OPENING BALANCE:");
				rfb.setRqty(rst.getInt(4));
				rfb.setRate(rate);
				rfb.setTvalue(rst.getInt(4)*rate);
				rfb.setColour(2);				
				clos=clos+rst.getInt(4);
				rtot=rtot+rst.getInt(4);
				data.add(rfb);
				print=false;
				tprint=true;
			   }
			   
			  if (rst.getInt(17)==pcode)
			  {  
				rfb = new InventoryRepo5FormBean();
				rfb.setName(rst.getString(5));
				rfb.setCity(rst.getString(6));
				rfb.setBat_no(rst.getString(7));
				rfb.setExp_dt(rst.getString(8));
				rfb.setInv_no(rst.getString(9));
				rfb.setInv_dt(rst.getDate(10));
				rfb.setRqty(rst.getInt(11)+rst.getInt(12));
				rfb.setSqty(rst.getInt(13));
				rfb.setFqty(rst.getInt(14));
				rfb.setRate(rst.getDouble(15));
				rfb.setTvalue(rst.getDouble(16));
				rfb.setColour(1);
				clos=clos+(rst.getInt(11)+rst.getInt(12))-(rst.getInt(13)+rst.getInt(14));
				rtot=rtot+rst.getInt(11)+rst.getInt(12);
				itot=itot+rst.getInt(13);
				ftot=ftot+rst.getInt(14);
				data.add(rfb);
			  }
			  

			} 
			
				rfb = new InventoryRepo5FormBean();
				rfb.setInv_no("TOTAL:");
				rfb.setRqty(rtot);
				rfb.setSqty(itot);
				rfb.setFqty(ftot);
				rfb.setColour(4);					
				data.add(rfb);
				  
				rfb = new InventoryRepo5FormBean();
				rfb.setExp_dt("CLOSING BALANCE:");
				rfb.setRqty(clos);
				rfb.setRate(rate);
				rfb.setTvalue(clos*rate);
				rfb.setColour(3);
				itot=0;
				rtot=0;
				clos=0;
				data.add(rfb);
				
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLInventoryRepo8DAO.getRepo8 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLInventoryRepo8DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

	
}
