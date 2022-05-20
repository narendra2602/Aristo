package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean; 

public class SQLRepo3DAO {

	public List getAllrepo(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		Repo2FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		String uvnm=null;
 		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String query3=null; 
            String txt1=null;
            String txt5=null;
            String txt6=null;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double grosssal=0.00;
            double pdsal=0.00;
            double spoilsal=0.00;
            
            
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double ggrosssal=0.00;
            double gpdsal=0.00;
            double gspoilsal=0.00;

            if (smon>emon)
            	emon=smon;

        	tblnm=tp+"_HQDetail08";
        	tblnm2=tp+"_hq_master08";
   	    
            txt1="H.Q. WISE /EXPIRY/BREAKAGE/SALABLE FROM ";
       	    
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
            String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
 	        ps12 = con.prepareStatement(query12);
 	        ps12.setInt(1,depo_code);
 	        ps12.setString(2,"HQT09"); 
 	        ps12.setString(3,tp); 
 	        rst12 = ps12.executeQuery();
 	        
 	        if (rst12.next())
 	        	txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);
 	        
 	        rst12.close();
 	        ps12.close();
//////////////////////////////////////////////////////////////////////////////////     	        
            String query2 ="Select h.ter_code,h.ter_name,u.depo_code from "+tblnm2+" as h,user_ter as u " +
            " where h.depo_code=u.depo_code and h.ter_code=u.ter_code and user_id="+uid+" and ter_pt=? " +
            " and h.mkt_year=? order by h.ter_code ";
            ps2 = con.prepareStatement(query2);
	        ps2.setString(1,tp); 
	        ps2.setInt(2,eyear);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
	            	rfb = new Repo2FormBean();
		        	rfb.setNm2(rst2.getString(2)); 
		        
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
                    salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    grosssal=0.00;
                    pdsal=0.00;
                    spoilsal=0.00;
                    
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast())
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		 	            	
		 	      //  query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where tr_cd=? "+
		          //  "  and depo_code=? and mkt_year=? group by tr_cd order by ar_cd,rg_cd,tr_cd "; 
		 	        
		 	        
		 	        query3 = "Select round(sum(r"+uvnm+"),2),round(sum(e"+uvnm+"),2),round(sum(b"+uvnm+"),2),round(sum("+uvnm+"),2),round(sum(s"+uvnm+"),2),round(sum(p"+uvnm+"),2) from "+tblnm+
		            " where tr_cd=? and depo_code=? and mkt_year=? group by tr_cd order by ar_cd,rg_cd,tr_cd";

		 	        
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,rst2.getInt(3));
			        ps3.setInt(3, mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
		        		 salval = salval+rst3.getDouble(1);
		        		 esalval = esalval+rst3.getDouble(2);
		        		 bsalval = bsalval+rst3.getDouble(3);
		        		 grosssal=grosssal+rst3.getDouble(4);
		        		 spoilsal=spoilsal+rst3.getDouble(5);
		        		 pdsal=pdsal+rst3.getDouble(6);
		        		 
		        		// gsalval = gsalval+rst3.getFloat(1);
		        		// gesalval = gesalval+rst3.getFloat(2);
		        		// gbsalval = gbsalval+rst3.getFloat(3);
		        		// ggrosssal=ggrosssal+rst3.getFloat(4);
		        		// gspoilsal=gspoilsal+rst3.getFloat(5);
		        		 //gpdsal=gpdsal+rst3.getFloat(6);
		        		 
		        		 
			        }      
				     
		 		  } 
        		 	 gsalval = gsalval+salval;
        		 	 gesalval = gesalval+esalval;
        		 	 gbsalval = gbsalval+bsalval;
        		 	 
        		 	 ggrosssal=ggrosssal+grosssal;
		    		 gspoilsal=gspoilsal+spoilsal;
		    		 gpdsal=gpdsal+pdsal;
        		 	 
		    		 System.out.println(" grosssal "+grosssal+ " gggrosssal "+ggrosssal);
		    		 
	        		 rfb.setGsale(grosssal);
        		 	 rfb.setSalable(salval);
	        		 rfb.setExpiry(esalval);
	        		 rfb.setBreakage(bsalval);
		    		 rfb.setSpoil(spoilsal);
		    		 rfb.setPd(pdsal);
		    		 rfb.setTotal(grosssal-(salval+esalval+bsalval+spoilsal+pdsal));
		    		 
	        		 //rfb.setVal5(salval+esalval+bsalval);
	        	     rfb.setNm3(txt1+txt5); 
	        	     rfb.setLupdate(txt6);
	      
      	             data.add(rfb); 				
 				} 
	            	rfb = new Repo2FormBean();
	            	rfb.setNm2("TOTAL : "); 
	            	rfb.setSalable(gsalval);
	            	rfb.setExpiry(gesalval);
	            	rfb.setBreakage(gbsalval);
	            	
	            	 rfb.setGsale(ggrosssal);
		    		 rfb.setSpoil(gspoilsal);
		    		 rfb.setPd(gpdsal);
		    		 rfb.setTotal(ggrosssal-(gsalval+gesalval+gbsalval+gspoilsal+gpdsal));
		    		 
	            	//rfb.setVal5(gsalval+gesalval+gbsalval);
   
	            	data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLRepo3DAO.getAllrepo3 " + e);
		}
		finally 
		{
		try {
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
		catch (SQLException e) {
				System.out.println("--Exception in HQ-SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}
	
} 