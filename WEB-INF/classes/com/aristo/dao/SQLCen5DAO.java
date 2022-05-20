package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aristo.valueobject.Cen5FormBean;

public class SQLCen5DAO {
	public List getRepo5(Connection con, String typ, Date sdate, Date edate,int depo,int ch,int pcd) { 
		 
		Cen5FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1=null;
		ResultSet rst1=null;
		Date sdt= null;
		Date edt= null;
		String output="";
		List<Cen5FormBean> data = new ArrayList<Cen5FormBean>();
		try { 
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String query=null;
            String query1=null;
            String br=null;
            
           	tblnm1=(typ+"_faacms2").toLowerCase();
           	tblnm2=(typ+"_invsnd").toLowerCase();
           	tblnm3=(typ+"_prdmsfl").toLowerCase();
           	
    		sdt= new java.sql.Date (sdate.getTime());
    		edt= new java.sql.Date (edate.getTime());
			DecimalFormat myFormatter = new DecimalFormat("####0.00");
    		
    		query1 = "select mac_code from "+tblnm1+" where musr_cd=? ";
    		ps1=con.prepareStatement(query1);
    		ps1.setInt(1, depo);
    		rst1=ps1.executeQuery();
    		if (rst1.next())
    		{
    			br=rst1.getString(1);
    		}
    		
    		rst1.close();
    		ps1.close();
    		
    		if (ch==1)
    		{
           	query="select prd.pcode,prd.pname,prd.pack,ss.mcity,ss.sbatch_no,ss.sexp_dt,ss.saleqty," +
   	 		" ss.salefree,ss.srate_net,ss.srate_trd,ss.srate_mrp,ss.srate_mfg,ss.srate_exc "+ 
           	" from "+tblnm3+" as prd inner join (select f.mcity,snd.sprd_cd,snd.sbatch_no," +
   	 		" snd.sexp_dt, snd.saleqty,snd.salefree,snd.srate_net,snd.srate_trd,snd.srate_mrp,snd.srate_mfg,snd.srate_exc "+ 
   	 		" from "+tblnm1+" as f  " +
   	 		" inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sqty as saleqty,sfree_qty as salefree, " +
	 		" srate_net,srate_trd,srate_mrp,srate_mfg,srate_exc "+ 
   	 		" from "+tblnm2+" where sdoc_type in(40,41,67) and sprt_cd=? and sinv_dt between '"+ sdt+ "' and '"+edt+"' )" +
   	 		" as snd on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd " +
   	 		" where prd.pcode="+pcd+" order by prd.pcode ";
		    }
    		if (ch==2)
    		{
    		query="select prd.pcode,prd.pname,prd.pack,ss.mcity,ss.sbatch_no,ss.sexp_dt,ss.saleqty," +
	 		" ss.salefree,ss.srate_net,ss.srate_trd,ss.srate_mrp,ss.srate_mfg,ss.srate_exc "+ 
    		" from "+tblnm3+" as prd inner join (select f.mcity,snd.sprd_cd,snd.sbatch_no," +
	 		" snd.sexp_dt, snd.saleqty,snd.salefree,snd.srate_net,snd.srate_trd,snd.srate_mrp,snd.srate_mfg,snd.srate_exc "+
	 		" from "+tblnm1+" as f " +
	 		" inner join (select sprt_cd,sprd_cd,sbatch_no,sexp_dt,sqty as saleqty,sfree_qty as salefree, " +
	 		" srate_net,srate_trd,srate_mrp,srate_mfg,srate_exc "+ 
	 		" from "+tblnm2+" where sdoc_type in(40,41,67)  and sprt_cd=? and sinv_dt between '"+ sdt+ "' and '"+edt+"' )" +
	 		" as snd on f.mac_code=snd.sprt_cd) as ss on prd.pcode=ss.sprd_cd " +
	 		" order by prd.pcode ";
    		}
    		
			ps = con.prepareStatement(query);
        	ps.setString(1,br);

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
					rfb = new Cen5FormBean();
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
				rfb = new Cen5FormBean();
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
				rfb = new Cen5FormBean();
				rfb.setCity(rst.getString(4));
				rfb.setBat_no(rst.getString(5));
				rfb.setExp_dt(rst.getString(6));
				rfb.setSqty(rst.getInt(7));
				rfb.setSfree(rst.getInt(8));
				output = ""+myFormatter.format(rst.getFloat(9));
				rfb.setNet(output);

				output = ""+myFormatter.format(rst.getFloat(10));
				rfb.setTrd(output);

				output = ""+myFormatter.format(rst.getFloat(11));
				rfb.setMrp(output);

				output = ""+myFormatter.format(rst.getFloat(12));
				rfb.setMfg(output);

				output = ""+myFormatter.format(rst.getFloat(13));
				rfb.setExc(output);

				stot=stot+rst.getInt(7);
				ftot=ftot+rst.getInt(8);
				data.add(rfb);
			  }

			} 
				rfb = new Cen5FormBean();
				rfb.setExp_dt("TOTAL:");
				rfb.setSqty(stot);
				rfb.setSfree(ftot);
				rfb.setColour(4);					
				data.add(rfb);
				  
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
				rst1=null;
				ps1=null;				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLCen5DAO.getRepo5 " + e);
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLCen5DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	

	
	
}
