package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo14FormBean;

public class SQLRepo14DAO {

	public List getAllstk(Connection con, int smon,int emon,int eyear,int depo_code,String tp,String utype,int uid) { 
	  	  
		Repo14FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
 		
		List<Repo14FormBean> data = new ArrayList<Repo14FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt3=null;
            String txt5 =null;
            String query2=null;
            int gno=0;
            int sno=0;
            int eno=0;
            int bno=0;
            int xno=0;
            int tercd=0;
            String ternm=null;
            boolean flag=true;

            double grossval=0.00;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            
            double ggrossval=0.00;
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;

            double tgrossval=0.00;
            double tgsalval=0.00;
            double tgesalval=0.00;
            double tgbsalval=0.00;

            if (smon>emon)
            	emon=smon;

        	tblnm=tp+"_stockiest08";
        	tblnm1=tp+"_hq_master08";
        	tblnm2=tp+"_account08";
        	
            txt1="STOCKIEST WISE GROSS/CREDIT/NET FROM ";
                
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
          String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
          ps12 = con.prepareStatement(query12);
          ps12.setInt(1,depo_code);
          ps12.setString(2,"STK09"); 
          ps12.setString(3,tp); 
          rst12 = ps12.executeQuery();
             if (rst12.next())
          	     txt3= rst12.getString(1)+", TIME: "+rst12.getString(2);
               		        
          rst12.close();
          ps12.close();	                          
                
/////////////////////////////////////////Month File Query////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
   		  ms1 = con.prepareStatement(month);
   		  ms1.setInt(1,smon);
   		  ms1.setInt(2,emon);
   		  ms1.setInt(3,eyear);
   		  mrec = ms1.executeQuery();
            			  
   		  
 
      	if (utype.equals("PMT"))
    	{
		 	query2= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
		 	"sum(s.valoct),sum(s.rvaloct),sum(s.evaloct),sum((s.bvaloct+s.svaloct+s.pvaloct)),"+
		 	"sum(s.valnov),sum(s.rvalnov),sum(s.evalnov),sum((s.bvalnov+s.svalnov+s.pvalnov)),"+
		 	"sum(s.valdec),sum(s.rvaldec),sum(s.evaldec),sum((s.bvaldec+s.svaldec+s.pvaldec)),"+
		 	"sum(s.valjan),sum(s.rvaljan),sum(s.evaljan),sum((s.bvaljan+s.svaljan+s.pvaljan)),"+
		 	"sum(s.valfeb),sum(s.rvalfeb),sum(s.evalfeb),sum((s.bvalfeb+s.svalfeb+s.pvalfeb)),"+
		 	"sum(s.valmar),sum(s.rvalmar),sum(s.evalmar),sum((s.bvalmar+s.svalmar+s.pvalmar)),"+
		 	"sum(s.valapr),sum(s.rvalapr),sum(s.evalapr),sum((s.bvalapr+s.svalapr+s.pvalapr)),"+
		 	"sum(s.valmay),sum(s.rvalmay),sum(s.evalmay),sum((s.bvalmay+s.svalmay+s.pvalmay)),"+
		 	"sum(s.valjun),sum(s.rvaljun),sum(s.evaljun),sum((s.bvaljun+s.svaljun+s.pvaljun)),"+
		 	"sum(s.valjul),sum(s.rvaljul),sum(s.evaljul),sum((s.bvaljul+s.svaljul+s.pvaljul)),"+
		 	"sum(s.valaug),sum(s.rvalaug),sum(s.evalaug),sum((s.bvalaug+s.svalaug+s.pvalaug)),"+
		 	"sum(s.valsep),sum(s.rvalsep),sum(s.evalsep),sum((s.bvalsep+s.svalsep+s.pvalsep))"+	
		 	" FROM "+tblnm2+" a, "+tblnm1+" h,"+tblnm+" s WHERE a.MKT_YEAR = ? " +
		 	" AND a.DEPO_CODE = ? 	and h.mkt_year = ? and h.depo_code = ? and a.depo_code = h.depo_code " +
		 	" and a.mterr_code = h.ter_code and s.mkt_year = ? and s.depo_code = ? and 	a.mac_code = s.stk_code " +
		 	" and a.depo_code = s.depo_code and a.mkt_year = s.mkt_year and h.ter_code = s.tr_cd and h.mkt_year = s.mkt_year " +
		 	" and s.grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
		 	" group by s.stk_code order by a.mterr_code,a.mac_name";
    	}
      	else
      	{
		 	query2= "SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
		 	"sum(s.valoct),sum(s.rvaloct),sum(s.evaloct),sum((s.bvaloct+s.svaloct+s.pvaloct)),"+
		 	"sum(s.valnov),sum(s.rvalnov),sum(s.evalnov),sum((s.bvalnov+s.svalnov+s.pvalnov)),"+
		 	"sum(s.valdec),sum(s.rvaldec),sum(s.evaldec),sum((s.bvaldec+s.svaldec+s.pvaldec)),"+
		 	"sum(s.valjan),sum(s.rvaljan),sum(s.evaljan),sum((s.bvaljan+s.svaljan+s.pvaljan)),"+
		 	"sum(s.valfeb),sum(s.rvalfeb),sum(s.evalfeb),sum((s.bvalfeb+s.svalfeb+s.pvalfeb)),"+
		 	"sum(s.valmar),sum(s.rvalmar),sum(s.evalmar),sum((s.bvalmar+s.svalmar+s.pvalmar)),"+
		 	"sum(s.valapr),sum(s.rvalapr),sum(s.evalapr),sum((s.bvalapr+s.svalapr+s.pvalapr)),"+
		 	"sum(s.valmay),sum(s.rvalmay),sum(s.evalmay),sum((s.bvalmay+s.svalmay+s.pvalmay)),"+
		 	"sum(s.valjun),sum(s.rvaljun),sum(s.evaljun),sum((s.bvaljun+s.svaljun+s.pvaljun)),"+
		 	"sum(s.valjul),sum(s.rvaljul),sum(s.evaljul),sum((s.bvaljul+s.svaljul+s.pvaljul)),"+
		 	"sum(s.valaug),sum(s.rvalaug),sum(s.evalaug),sum((s.bvalaug+s.svalaug+s.pvalaug)),"+
		 	"sum(s.valsep),sum(s.rvalsep),sum(s.evalsep),sum((s.bvalsep+s.svalsep+s.pvalsep))"+	
		 	" FROM "+tblnm2+" a, "+tblnm1+" h,"+tblnm+" s WHERE a.MKT_YEAR = ? " +
		 	" AND a.DEPO_CODE = ? 	and h.mkt_year = ? and h.depo_code = ? and a.depo_code = h.depo_code " +
		 	" and a.mterr_code = h.ter_code and s.mkt_year = ? and s.depo_code = ? and 	a.mac_code = s.stk_code " +
		 	" and a.depo_code = s.depo_code and a.mkt_year = s.mkt_year and h.ter_code = s.tr_cd and h.mkt_year = s.mkt_year " +
		 	" group by s.stk_code order by a.mterr_code,a.mac_name";
      	}
/////////////////////////////////////Account Master Query////////////////////////////////////////
//          String query2 = "Select distinct(mac_code),mac_name,mcity from "+tblnm2+" where depo_code=? and mterr_code=? and mkt_year=? order by mterr_code,mac_name";	      
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,eyear);
	        ps2.setInt(2,depo_code);
	        ps2.setInt(3,eyear);
	        ps2.setInt(4,depo_code);
	        ps2.setInt(5,eyear);
	        ps2.setInt(6,depo_code);
	        rst2 = ps2.executeQuery();
          while (rst2.next()) ///// HQ Loop Start
	        {

		    	if (flag)
		    	{
		    		tercd=rst2.getInt(1);
		    		ternm=rst2.getString(2);
		    		flag=false;
		    	}
		    	
		    	if (tercd!=rst2.getInt(1)) // HQ Total on code change 
		    	{
					 rfb = new Repo14FormBean();
					 rfb.setName(ternm);
					 rfb.setColor(2);
					 rfb.setGrossval(tgrossval);
	            	 rfb.setSalval(tgsalval);
		             rfb.setExpval(tgesalval);
		             rfb.setBrkval(tgbsalval);
		             rfb.setTotal(tgrossval-(tgsalval+tgesalval+tgbsalval));
					 data.add(rfb);
		    	
		        	 tgrossval=0.00;
					 tgsalval=0.00;
		             tgesalval=0.00;
		             tgbsalval=0.00;
			    	 tercd=rst2.getInt(1);
			    	 ternm=rst2.getString(2);
		             
		    	}
		    	
	                grossval=0.00;
		    		salval=0.00;
	                esalval=0.00;
	                bsalval=0.00;
	    		    gno=4;
	                sno=5;
	    		    eno=6;
	    		    bno=7;
	    		    xno=7;
	          
	                mrec.beforeFirst();
                    while (mrec.next())    ////////////////////////Month Loop Start///////////////////
		 			{	
		 			    if (mrec.isFirst())
		 			    {
		 				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 				   gno=gno*mrec.getInt(5);
				    	   xno=gno+3;
		 			    }
		 				 	  
				        if (mrec.isLast())
					        txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
/*				        	gno=gno+mrec.getInt(5);
		 	            	sno=sno+mrec.getInt(5);
		 	            	eno=eno+mrec.getInt(5);
		 	            	bno=bno+mrec.getInt(5);*/

				        	gno=gno+1;
		 	            	sno=gno+1;
		 	            	eno=gno+2;
		 	            	bno=gno+3;
		 	            	
			        		 grossval = grossval+rst2.getDouble(gno);
			        		 salval = salval+rst2.getDouble(sno);
			        		 esalval = esalval+rst2.getDouble(eno);
			        		 bsalval = bsalval+rst2.getDouble(bno);
				             
				     		 gno=xno+1;
				     		 sno=gno+1;
				    		 eno=gno+2;
				    		 bno=gno+3;
				    		 xno=bno;
				     
		 		   }    ///////////////////////////Month Loop End/////////////////////////// 
			        	  
                    	 ggrossval=ggrossval+grossval;
	        		 	 gsalval = gsalval+salval;
	        		 	 gesalval = gesalval+esalval;
	        		 	 gbsalval = gbsalval+bsalval;

                    	 tgrossval=tgrossval+grossval;
	        		 	 tgsalval = tgsalval+salval;
	        		 	 tgesalval = tgesalval+esalval;
	        		 	 tgbsalval = tgbsalval+bsalval;
	        		 	 
	        		 	 if (grossval-(salval+esalval+bsalval)!=0)
	        		 	 {
	 	            	 rfb = new Repo14FormBean();
		            	 rfb.setName(rst2.getString(3)+", "+rst2.getString(4)); 
		            	 rfb.setColor(1);
		            	 rfb.setGrossval(grossval);
	        		 	 rfb.setSalval(salval);
		        		 rfb.setExpval(esalval);
		        		 rfb.setBrkval(bsalval);
		        		 rfb.setTotal(grossval-(salval+esalval+bsalval));
		        	     rfb.setHead1(txt1+txt5); 
		        	     rfb.setLupdate(txt3);
 		                 data.add(rfb); 			
	        		 	 }
	        		 	 
			
	        } /////////////////////HQ Loop End///////////////////////////////
          
					 rfb = new Repo14FormBean();
					 rfb.setName(ternm);
					 rfb.setColor(2);
					 rfb.setGrossval(tgrossval);
		        	 rfb.setSalval(tgsalval);
		             rfb.setExpval(tgesalval);
		             rfb.setBrkval(tgbsalval);
		             rfb.setTotal(tgrossval-(tgsalval+tgesalval+tgbsalval));
					 data.add(rfb);
					 
	            	rfb = new Repo14FormBean();
	            	rfb.setName("TOTAL : "); 
					rfb.setGrossval(ggrossval);
	            	rfb.setSalval(gsalval);
	            	rfb.setExpval(gesalval);
	            	rfb.setBrkval(gbsalval);
	            	rfb.setTotal(ggrossval-(gsalval+gesalval+gbsalval));
	            	data.add(rfb); 				
				

		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo14DAO.getAllStockiest " + e);
		}
		
		finally {
			try 
			{
		           if(mrec != null){mrec.close();}
		           if(ms1 != null){ ms1.close();}
		           if(rst12 != null){ rst12.close();}
		           if(ps12 != null){ ps12.close();}
		           if(rst2 != null){ rst2.close();}
		           if(ps2 != null){ps2.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo14DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}

	
	
	
}
   