package com.aristo.hq;

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
            int sno=0;
            int eno=0;
            int bno=0;
            int xno=0;
            int tercd=0;
            String ternm=null;
            boolean flag=true;

            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;

            double tgsalval=0.00;
            double tgesalval=0.00;
            double tgbsalval=0.00;

            if (smon>emon)
            	emon=smon;

        	tblnm=tp+"_stockiest08";
        	tblnm1=tp+"_hq_master08";
        	tblnm2=tp+"_account08";
        	
            txt1="STOCKIEST WISE EXPIRY/BREAKAGE/SALABLE FROM ";
                
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
 
   		  query2="SELECT a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
   		  " sum(s.rvaloct),sum(s.evaloct),sum(s.bvaloct),sum(s.rvalnov),sum(s.evalnov),sum(s.bvalnov)," +
   		  " sum(s.rvaldec),sum(s.evaldec),sum(s.bvaldec),sum(s.rvaljan),sum(s.evaljan),sum(s.bvaljan)," +
   		  " sum(s.rvalfeb),sum(s.evalfeb),sum(s.bvalfeb),sum(s.rvalmar),sum(s.evalmar),sum(s.bvalmar)," +
   		  " sum(s.rvalapr),sum(s.evalapr),sum(s.bvalapr),sum(s.rvalmay),sum(s.evalmay),sum(s.bvalmay)," +
   		  " sum(s.rvaljun),sum(s.evaljun),sum(s.bvaljun),sum(s.rvaljul),sum(s.evaljul),sum(s.bvaljul)," +
   		  " sum(s.rvalaug),sum(s.evalaug),sum(s.bvalaug),sum(s.rvalsep),sum(s.evalsep),sum(s.bvalsep) " +
   		  " from "+tblnm2+" a, "+tblnm1+" h, "+tblnm+" s,user_ter u " +
   		  " where a.mkt_year="+eyear+" and h.mkt_year="+eyear+" and s.mkt_year="+eyear+" " +
   		  " and a.depo_code=u.depo_code and a.depo_code=h.depo_code and a.depo_code=s.depo_code " +
   		  " and h.depo_code=u.depo_code and h.depo_code =s.depo_code and s.depo_code=u.depo_code " +
   		  " and a.mterr_code=h.ter_code and a.mac_code=s.stk_code and h.ter_code=s.tr_cd " +
   		  " and u.ter_code=a.mterr_code and u.ter_code=h.ter_code and u.user_id="+uid+" " +
   		  " group by s.stk_code order by a.mterr_code,a.mac_name" ;   		  
          ps2 = con.prepareStatement(query2);
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
	            	 rfb.setSalval(tgsalval);
		             rfb.setExpval(tgesalval);
		             rfb.setBrkval(tgbsalval);
		             rfb.setTotal(tgsalval+tgesalval+tgbsalval);
					 data.add(rfb);
		    	
		        	 tgsalval=0.00;
		             tgesalval=0.00;
		             tgbsalval=0.00;
			    	 tercd=rst2.getInt(1);
			    	 ternm=rst2.getString(2);
		             
		    	}
		    	
	                salval=0.00;
	                esalval=0.00;
	                bsalval=0.00;
	    		    sno=4;
	    		    eno=5;
	    		    bno=6;
	    		    xno=6;
	          
	                mrec.beforeFirst();
                    while (mrec.next())    ////////////////////////Month Loop Start///////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 				 	  
				        if (mrec.isLast())
					        txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	sno=sno+mrec.getInt(5);
		 	            	eno=eno+mrec.getInt(5);
		 	            	bno=bno+mrec.getInt(5);
		 	            	
			        		 salval = salval+rst2.getDouble(sno);
			        		 esalval = esalval+rst2.getDouble(eno);
			        		 bsalval = bsalval+rst2.getDouble(bno);
				             
				     		 sno=xno;
				    		 eno=sno+1;
				    		 bno=sno+2;
				    		 xno=bno;
				     
		 		   }    ///////////////////////////Month Loop End/////////////////////////// 
			        	  
	        		 	 gsalval = gsalval+salval;
	        		 	 gesalval = gesalval+esalval;
	        		 	 gbsalval = gbsalval+bsalval;

	        		 	 tgsalval = tgsalval+salval;
	        		 	 tgesalval = tgesalval+esalval;
	        		 	 tgbsalval = tgbsalval+bsalval;
	        		 	 
	        		 	 if ((salval+esalval+bsalval)!=0)
	        		 	 {
	 	            	 rfb = new Repo14FormBean();
		            	 rfb.setName(rst2.getString(3)+", "+rst2.getString(4)); 
		            	 rfb.setColor(1);
	        		 	 rfb.setSalval(salval);
		        		 rfb.setExpval(esalval);
		        		 rfb.setBrkval(bsalval);
		        		 rfb.setTotal(salval+esalval+bsalval);
		        	     rfb.setHead1(txt1+txt5); 
		        	     rfb.setLupdate(txt3);
 		                 data.add(rfb); 			
	        		 	 }
	        		 	 
			
	        } /////////////////////HQ Loop End///////////////////////////////
          
					 rfb = new Repo14FormBean();
					 rfb.setName(ternm);
					 rfb.setColor(2);
		        	 rfb.setSalval(tgsalval);
		             rfb.setExpval(tgesalval);
		             rfb.setBrkval(tgbsalval);
		             rfb.setTotal(tgsalval+tgesalval+tgbsalval);
					 data.add(rfb);
					 
	            	rfb = new Repo14FormBean();
	            	rfb.setName("TOTAL : "); 
	            	rfb.setSalval(gsalval);
	            	rfb.setExpval(gesalval);
	            	rfb.setBrkval(gbsalval);
	            	rfb.setTotal(gsalval+gesalval+gbsalval);
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
   