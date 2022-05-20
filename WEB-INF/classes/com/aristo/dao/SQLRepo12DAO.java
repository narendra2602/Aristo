package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo12FormBean;

public class SQLRepo12DAO {

	
	public List getAllStk(Connection con, String code,int smon,int emon,int eyear,int depo_code,String tp) { 
		  
		Repo12FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;

		String unm=null;
        String vnm=null;		
		List<Repo12FormBean> data = new ArrayList<Repo12FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5 =null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;
            double salval=0.00;
            double salbval=0.00;
            double expval=0.00;

            double gsalval=0.00;
            double gsalbval=0.00;
            double gexpval=0.00;

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_stockiest08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_account08").toLowerCase();
            
            txt2="     PRODUCT WISE SALES DETAIL FROM "; 
            
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
                
/////////////////////////////////////////////Account Master//////////////////////////////////////////                
            String terrec = "Select mac_name from "+tblnm2+" where depo_code=? and mac_code=? and mkt_year=? ";  
  			ts1 = con.prepareStatement(terrec);
   			ts1.setInt(1,depo_code);
   			ts1.setString(2,code);
   			ts1.setInt(3,eyear);
   			
   			trec = ts1.executeQuery();
   			if (trec.next())
              txt1="STOCKIEST:- "+trec.getString(1);
			
    		trec.close();
    		ts1.close();
////////////////////////////////////////////////Product Master////////////////////////////////////            
            String query1 = "Select distinct(mcode),mname,pack,trd_rt1 from "+tblnm1+" where mcode<>? and mkt_year=? group by mcode order by mcode";
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2,eyear);
			rst1 = ps1.executeQuery();
              
			while (rst1.next())   ///////////////////////////Start//////////////////////////////
			{
				rfb = new Repo12FormBean();
				rfb.setMcode(rst1.getString(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
			    rfb.setRate(rst1.getFloat(4));  
		        
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		            salqty=0;
		            salbqty=0;
		            expqty=0;
		            salval=0.00;
		            salbval=0.00;
		            expval=0.00;
		             
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			            txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
 			        unm="qty"+mrec.getString(3);
 	            	vnm="val"+mrec.getString(3);
		 	            	
  	     String query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where stk_code=? "+
		 " and mpr_code=?  and depo_code=? and mkt_year=? group by mpr_code order by ar_cd,rg_cd,tr_cd,mpr_code"; 
		        ps3 = con.prepareStatement(query3); 
		        ps3.setString(1,code); 
		        ps3.setInt(2,rst1.getInt(1));
		        ps3.setInt(3,depo_code);
		        ps3.setInt(4,mrec.getInt(4));
		        rst3 = ps3.executeQuery(); 
		        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getDouble(7);
			        		 salbval=salbval+rst3.getDouble(8);
			        		 expval=expval+(rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 
				        
			        }      
				     
	        		 rfb.setSqty(salqty);
	        		 rfb.setSlqty(salbqty);
	        		 rfb.setPqty(expqty);
	        		 rfb.setNetqty(salqty-salbqty-expqty);
	        		 
	        		 rfb.setSval(salval);
	        		 rfb.setSlval(salbval);
	        		 rfb.setPpval(expval);
	        		 rfb.setNetval(salval-salbval-expval);
 	 				 rfb.setHead1(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt3);
	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	                 data.add(rfb); 				
				} 
					 rfb = new Repo12FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setSval(gsalval);
	        		 rfb.setSlval(gsalbval);
	        		 rfb.setPpval(gexpval);
	        		 rfb.setNetval(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo12DAO.getAllStockiest " + e); 
		}
		
		finally {
			try 
			{
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo12DAO.Connection.close "+e);
		    }
		}		
		
		return data;
	}
/////////////////////////////////// HQ Coding End Here///////////////////////////////////////
	
	
} 