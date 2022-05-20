package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cen.aristo.valueobject.CentralRepo6FormBean;

public class SQLRepo6DAO {
	public List getRepo6(Connection con, String typ, Date sdate, Date edate,int ch,int pcd) { 
		 
		CentralRepo6FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		Date sdt= null;
		Date edt= null;

		List<CentralRepo6FormBean> data = new ArrayList<CentralRepo6FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String query=null;
            
           	tblnm1=(typ+"_faacms2").toLowerCase();
           	tblnm2=(typ+"_invsnd").toLowerCase();
           	tblnm3=(typ+"_prdmsfl").toLowerCase();
           	
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
    		
    		if (ch==1)
    		{
           	 query="select prd.pcode,prd.pname,prd.pack,ss.mcity,ss.sbatch_no,ss.sexp_dt,ss.saleqty," +
           	 		"ss.salefree from "+tblnm3+" as prd inner join (select f.mcity,snd.sprd_cd,snd.sbatch_no," +
           	 		"snd.sexp_dt, snd.saleqty,snd.salefree from "+tblnm1+" as f" +
           	 		" inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sqty as saleqty,sfree_qty as salefree " +
           	 		" from "+tblnm2+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' )" +
           	 		" as snd on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd " +
           	 		" where prd.pcode="+pcd+" order by prd.pcode ";
    		}
    		if (ch==2)
    		{
              	 query="select prd.pcode,prd.pname,prd.pack,ss.mcity,ss.sbatch_no,ss.sexp_dt,ss.saleqty," +
        	 		"ss.salefree from "+tblnm3+" as prd inner join (select f.mcity,snd.sprd_cd,snd.sbatch_no," +
        	 		"snd.sexp_dt, snd.saleqty,snd.salefree from "+tblnm1+" as f" +
        	 		" inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sqty as saleqty,sfree_qty as salefree " +
        	 		" from "+tblnm2+" where sdoc_type in(40,41,67) and sinv_dt between '"+ sdt+ "' and '"+edt+"' )" +
        	 		" as snd on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd " +
        	 		" order by prd.pcode ";
    		}
    		
			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			boolean print= true;
			boolean tprint=false;
			int pcode=0;
			int stot=0;
			int ftot=0;
			
			while (rst.next())
			{
				
				
			   if (tprint)
			   {
				  if (rst.getInt(1)!=pcode)
				  {
					rfb = new CentralRepo6FormBean();
					rfb.setExp_dt("TOTAL:");
					rfb.setSqty(stot);
					rfb.setSfree(ftot);
					rfb.setColour(4);					
					stot=0;
					ftot=0;
					data.add(rfb);
					  
					print=true;
				  }
				   
			   }
			   
			   if (print)	
			   {
				pcode=rst.getInt(1);
				rfb = new CentralRepo6FormBean();
				rfb.setPcode(rst.getString(1));
				rfb.setName(rst.getString(2));
				rfb.setPack(rst.getString(3));
				rfb.setColour(2);				
				data.add(rfb);
				print=false;
				tprint=true;
			   }
			   
			  if (rst.getInt(1)==pcode)
			  {  
				rfb = new CentralRepo6FormBean();
				rfb.setCity(rst.getString(4));
				rfb.setBat_no(rst.getString(5));
				rfb.setExp_dt(rst.getString(6));
				rfb.setSqty(rst.getInt(7));
				rfb.setSfree(rst.getInt(8));
				stot=stot+rst.getInt(7);
				ftot=ftot+rst.getInt(8);
				data.add(rfb);
			  }
			  

			} 
			
				rfb = new CentralRepo6FormBean();
				rfb.setExp_dt("TOTAL:");
				rfb.setSqty(stot);
				rfb.setSfree(ftot);
				rfb.setColour(4);					
				data.add(rfb);
				  
				
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo6DAO.getRepo6 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo6DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

	
	
}
