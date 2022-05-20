package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean; 

public class SQLRepo5DAO {
  
///////////////////////////////// Headquter Coding Start Here/////////////////////////////////
	public List getAllrepo(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
		Repo2FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        
 		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String txt5=null;
            String txt6=null;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double budget =0.00;
            double ach=0.00;
            double surp = 0.00;
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double gbudget =0.00;
            double gach=0.00;
            double gsurp = 0.00;
           ////////////////Region/////////// 	
            double rsalval=0.00;
            double resalval=0.00;
            double rbsalval=0.00;
            double rbudget =0.00;
            double rach=0.00;
            double rsurp = 0.00;

           ////////////////Area/////////// 	
            double asalval=0.00;
            double aesalval=0.00;
            double absalval=0.00;
            double abudget =0.00;
            double aach=0.00;
            double asurp = 0.00;

            if (smon>emon)
            	emon=smon;

            	tblnm=(tp+"_repfinal").toLowerCase();
            
            txt1="H.Q. WISE/GROSS/CREDIT/NET SALE FROM  ";
       	    
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
//////////////////////////////////////////////////////////////////////////////////////////

			
////////////////Month File Loop Starts to accumulate data
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
            while (mrec.next())
 			{	
 			    if (mrec.isFirst())	
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
				 	  
			        if (mrec.isLast())
 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
	 	   } 
			mrec.close();
			ms1.close();
			
		        	asalval=0.00;
                    aesalval=0.00;
                    absalval=0.00;
                    abudget=0.00;
                    asurp=0.00;
                    aach=0.00;
                
		        	rsalval=0.00;
                    resalval=0.00;
                    rbsalval=0.00;
                    rbudget=0.00;
                    rsurp=0.00;
                    rach=0.00;
  
 	               String query2=null; 
 	  	         if (utype.equals("PMT"))
 	  	         {
 	  	             query2 = "select depo_code,ar_cd,area_name,rg_cd,reg_name,ter_name, sum(tarval) tarval,sum(grval) grval," +
 	  	             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
 	  	             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? " +
 	  	             " and grp_cd in  (select gp_code from pmt_group where user_id=163 and status='Y') group by ar_cd,rg_cd,tr_cd ";
 	  	         }
 	  	         else
 	  	         {
 	  	             query2 = "select depo_code,ar_cd,area_name,rg_cd,reg_name,ter_name, sum(tarval) tarval,sum(grval) grval," +
 	  	             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
 	  	             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? " +
 	  	             "  group by ar_cd,rg_cd,tr_cd ";
 	  	         } 
 	  	        	 
 	  	            ps2 = con.prepareStatement(query2);
 	  			    ps2.setInt(1,eyear);
 	  	  			ps2.setInt(2,smon);
 	  		 		ps2.setInt(3,emon);
 	  	  	        ps2.setInt(4,depo_code);
 	  			    rst2 = ps2.executeQuery();

 	  			    boolean hprint=false;
 	  	    	    boolean first=true;
 	  	    	    int rg=0;
 	  	    	    String rgnm=null;
 	  	    	    int ar=0;
 	  	    	    String arnm=null;
                    

	            while (rst2.next())/////////////// HQ Master Loop Start///////////////////
				{
					hprint=true;

					if (first)
					{
						ar=rst2.getInt(2);
						arnm=rst2.getString(3);
						rg=rst2.getInt(4);
						rgnm=rst2.getString(5);
						first=false;
					}

					if (rg!=rst2.getInt(4))
					{
		                  ////////////// Region ke liye/////////////////////////
			    		rfb = new Repo2FormBean();
		        		rfb.setNm2(rgnm); 
		        		rfb.setMcode(2);
		        		rfb.setDval2(rsalval);
		        		rfb.setDval3(resalval);
		        		rfb.setDval4(rbsalval);
		        		rfb.setDval5(rbudget);
		        		rfb.setDval7(rach);
		        		rfb.setDval6(rsurp);
						rg=rst2.getInt(4);
						rgnm=rst2.getString(5);

						rsalval=0.00;
		                resalval=0.00;
		                rbsalval=0.00;
		                rbudget=0.00;
		                rsurp=0.00;
		                rach=0.00;
		
		        		data.add(rfb);
					}
					
					if (ar!=rst2.getInt(2))
					{
		                ////////////// Area ke liye/////////////////////////
			    		rfb = new Repo2FormBean();
		        		rfb.setNm2(arnm); 
		        		rfb.setMcode(3);
		        		rfb.setDval2(asalval);
		        		rfb.setDval3(aesalval);
		        		rfb.setDval4(absalval);
		        		rfb.setDval5(abudget);
		        		rfb.setDval7(aach);
		        		rfb.setDval6(asurp);
						ar=rst2.getInt(2);
						arnm=rst2.getString(3);

			        	asalval=0.00;
	                    aesalval=0.00;
	                    absalval=0.00;
	                    abudget=0.00;
	                    asurp=0.00;
	                    aach=0.00;
		
		        		data.add(rfb);
					}
		        
                    ///// Month File Loop Starts to accumulate data
                    salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    budget=0.00;
                    surp=0.00;
                    ach=0.00;
		 	           
					    	 budget =  budget+rst2.getDouble(7);
					    	 budget = (Math.round(budget*100.0)/100.0);
						     salval = rst2.getDouble(8);
					         esalval = rst2.getDouble(9);
					         bsalval = salval-esalval;
					         surp = bsalval-budget;
					         
		        		 	 if (budget!=0)
			        	     ach=(bsalval/budget)*100.00;

       		 	 //////////////Region ke liye/////////////////////////
                    
			         	 rsalval = rsalval+salval;
	        		 	 resalval = resalval+esalval;
	        		 	 rbsalval = rbsalval+bsalval;
	        		 	 rbudget = rbudget+budget;
	        		 	 rsurp = rsurp+surp;
	        		 	 if (rbudget!=0)
	        		 	 rach = (rbsalval/rbudget)*100.00;

	        		     //////////////Area ke liye/////////////////////////
	                     
			         	 asalval = asalval+salval;
	        		 	 aesalval = aesalval+esalval;
	        		 	 absalval = absalval+bsalval;
	        		 	 abudget = abudget+budget;
	        		 	 asurp = asurp+surp;
	        		 	 if (abudget!=0)
	        		 	 aach = (absalval/abudget)*100.00;
	        		 	 
	        		 	 
			         	 gsalval = gsalval+salval;
	        		 	 gesalval = gesalval+esalval;
	        		 	 gbsalval = gbsalval+bsalval;
	        		 	 gbudget = gbudget+budget;
	        		 	 gsurp = gsurp+surp;
	        		 	 if (gbudget!=0)
	        		 	 gach = (gbsalval/gbudget)*100.00;
	        		 	 
	        		 if(hprint)
	        		 {
	 					 rfb = new Repo2FormBean();
		            	 rfb.setNm2(rst2.getString(6)); 
		            	 rfb.setMcode(1);
	        		 	 rfb.setDval2(salval);
		        		 rfb.setDval3(esalval);
		        		 rfb.setDval4(bsalval);
		        		 rfb.setDval5(budget);
		        		 rfb.setDval7(ach);
		        		 rfb.setDval6(surp);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		                 data.add(rfb);
	        		 }

	            

            
  		} ////////////////Main query  Loop End//////////////////
		        
		    		rfb = new Repo2FormBean();
		    		rfb.setNm2(rgnm); 
		    		rfb.setMcode(2);
		    		rfb.setDval2(rsalval);
		    		rfb.setDval3(resalval);
		    		rfb.setDval4(rbsalval);
		    		rfb.setDval5(rbudget);
		    		rfb.setDval7(rach);
		    		rfb.setDval6(rsurp);
		
					rsalval=0.00;
		            resalval=0.00;
		            rbsalval=0.00;
		            rbudget=0.00;
		            rsurp=0.00;
		            rach=0.00;
		
		    		data.add(rfb);

		    		rfb = new Repo2FormBean();
	        		rfb.setNm2(arnm); 
	        		rfb.setMcode(3);
	        		rfb.setDval2(asalval);
	        		rfb.setDval3(aesalval);
	        		rfb.setDval4(absalval);
	        		rfb.setDval5(abudget);
	        		rfb.setDval7(aach);
	        		rfb.setDval6(asurp);

		        	asalval=0.00;
                    aesalval=0.00;
                    absalval=0.00;
                    abudget=0.00;
                    asurp=0.00;
                    aach=0.00;
	
	        		data.add(rfb);
		    		
		    		
		            rfb = new Repo2FormBean();
	            	rfb.setNm2("TOTAL : ");
	            	rfb.setMcode(4);
	            	rfb.setDval2(gsalval);
	            	rfb.setDval3(gesalval);
	            	rfb.setDval4(gbsalval);
	        		rfb.setDval5(gbudget);
	        		rfb.setDval7(gach);
	        		rfb.setDval6(gsurp);
	        		data.add(rfb); 				
	            	
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo5DAO.getAllHQ " + e);
		}

		finally 
		{
		try 
		{
       if(mrec != null){mrec.close();}
       if(ms1 != null){ ms1.close();}
       if(rst2 != null){ rst2.close();}
       if(ps2 != null){ps2.close();}
       if(rst12 != null){ rst12.close();}
       if(ps12 != null){ps12.close();}
       if(con != null){con.close();}
		}
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo5DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
///////////////////////////////// Headquater Coding End Here/////////////////////////////////


///////////////////////////////// Region Coding Start Here/////////////////////////////////	
	public List getAllRegion(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
		Repo2FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String txt5 =null;
            String txt6=null;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double budget =0.00;
            double ach=0.00;
            double surp = 0.00;
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double gbudget =0.00;
            double gach=0.00;
            double gsurp = 0.00;

            if (smon>emon)
            	emon=smon;

    	    tblnm=(tp+"_repfinal").toLowerCase();
            
            txt1="REGION WISE/GROSS/CREDIT/NET SALE FROM ";
       	    
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
//////////////////////////////////////////////////////////////////////////////////////////
///// Month File Loop Starts to accumulate data
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
            while (mrec.next())
 			{	
 			    if (mrec.isFirst())	
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
				 	  
			        if (mrec.isLast())
 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
	 	   } 
               String query2=null; 
//             String query2 = "Select ter_code,ter_name from "+tblnm2+" where depo_code=? and ter_pt=? and regn_code=? and mkt_year=? order by area_code,regn_code,ter_code";
         if (utype.equals("PMT"))
         {
             query2 = "select depo_code,tr_cd,ter_name, sum(tarval) tarval,sum(grval) grval," +
             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? and rg_cd=? " +
             " and grp_cd in  (select gp_code from pmt_group where user_id=163 and status='Y')group by tr_cd ";
         }
         else
         {
             query2 = "select depo_code,tr_cd,ter_name, sum(tarval) tarval,sum(grval) grval," +
             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? " +
             " and rg_cd=? group by tr_cd ";
         } 
        	 

             ps2 = con.prepareStatement(query2);
		     ps2.setInt(1,eyear);
  			 ps2.setInt(2,smon);
	 		 ps2.setInt(3,emon);
  	         ps2.setInt(4,depo_code);
		     ps2.setInt(5,code); 
		     rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
	            	rfb = new Repo2FormBean();
	            	rfb.setNm2(rst2.getString(3)); 
		        
                    ///// Month File Loop Starts to accumulate data
                    salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    budget=0.00;
                    surp=0.00;
                    ach=0.00;

		 	            	
						    	 budget =  budget+rst2.getDouble(4);
						    	 budget = (Math.round(budget*100.0)/100.0);
		        	

				      salval = rst2.getDouble(5);
			          esalval = rst2.getDouble(6);
			          bsalval = salval-esalval;
			          surp = bsalval-budget;
			          if (budget!=0)
				    	 ach = (bsalval/budget)*100.00; 
				     
                    	 gsalval = gsalval   + salval;
                    	 gesalval = gesalval + esalval;
                    	 gbsalval = gbsalval + bsalval;
	        		 	 gbudget = gbudget+budget;
	        		 	 gsurp = gsurp+surp;
	        		 	 if (gbudget!=0)
		        		 gach = (gbsalval/gbudget)*100.00;
                    	 
		        		 rfb.setDval2(salval);
		        		 rfb.setDval3(esalval);
		        		 rfb.setDval4(bsalval);
		        		 rfb.setDval5(budget);
		          		 rfb.setDval6(surp);
		          		 rfb.setDval7(ach);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		      
          	            data.add(rfb); 				
				} 
	            		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : "); 
	            		rfb.setDval2(gsalval);
	            		rfb.setDval3(gesalval);
	            		rfb.setDval4(gbsalval);
		        		rfb.setDval5(gbudget);
		        		rfb.setDval7(gach);
		        		rfb.setDval6(gsurp);

	            		data.add(rfb); 				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo5DAO.getAllRegion " + e);
		}
		finally 
		{
		try {
		       if(mrec != null){mrec.close();}
		       if(ms1 != null){ ms1.close();}
		       if(rst2 != null){ rst2.close();}
		       if(ps2 != null){ps2.close();}
		       if(rst12 != null){ rst12.close();}
		       if(ps12 != null){ps12.close();}
		       if(con != null){con.close();}
			}
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo5DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
///////////////////////////////// Region Coding End Here/////////////////////////////////

	
///////////////////////////////// Branch Coding Start Here/////////////////////////////////	
	public List getAllArea(Connection con,int code, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
		Repo2FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String txt5=null;
            String txt6=null;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double budget =0.00;
            double ach=0.00;
            double surp = 0.00;
            
            double rsalval=0.00;
            double resalval=0.00;
            double rbsalval=0.00;
            double rbudget =0.00;
            double rach=0.00;
            double rsurp = 0.00;
            
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double gbudget =0.00;
            double gach=0.00;
            double gsurp = 0.00;

            if (smon>emon)
            	emon=smon;

            	tblnm=(tp+"_repfinal").toLowerCase();
                
                txt1="AREA WISE/GROSS/CREDIT/NET SALE FROM ";
           	    
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
				
//////////////// Month File Loop Starts to accumulate data
	            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 			ms1 = con.prepareStatement(month);
	 			ms1.setInt(1,smon);
	 			ms1.setInt(2,emon);
	 			ms1.setInt(3,eyear);
	 			mrec = ms1.executeQuery();
	            while (mrec.next())
	 			{	
	 			    if (mrec.isFirst())	
	 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
					 	  
				        if (mrec.isLast())
	 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 	   } 
				mrec.close();
				ms1.close();
	            
//////////////////////////////////////////////////////////////////////////////////////////            

	               String query2=null; 
//			String query2 = "Select reg_code,name from "+tblnm3+" where depo_code=? and typ=? and area_code=? and mkt_year=? order by area_code";
	         if (utype.equals("PMT"))
	         {
	             query2 = "select depo_code,rg_cd,reg_name,ter_name sum(tarval) tarval,sum(grval) grval," +
	             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
	             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? and ar_cd=? " +
	             " and grp_cd in  (select gp_code from pmt_group where user_id=163 and status='Y')group by rg_cd,tr_cd ";
	         }
	         else
	         {
	             query2 = "select depo_code,rg_cd,reg_name,ter_name, sum(tarval) tarval,sum(grval) grval," +
	             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
	             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ?  and depo_CODE =? " +
	             " and ar_cd=? group by rg_cd,tr_cd ";
	         } 
	        	 
	            ps2 = con.prepareStatement(query2);
			    ps2.setInt(1,eyear);
	  			ps2.setInt(2,smon);
		 		ps2.setInt(3,emon);
	  	        ps2.setInt(4,depo_code);
			    ps2.setInt(5,code); 
			    rst2 = ps2.executeQuery();

			    boolean hprint=false;
	    	    boolean first=true;
	    	    int rg=0;
	    	    String rgnm=null;

	    	    rsalval=0.00;
                resalval=0.00;
                rbsalval=0.00;
                rbudget=0.00;
                rsurp=0.00;
                rach=0.00;

	            while (rst2.next())//////////Region Master Ka Loop Start////////////////
				{
					hprint=true;

					if (first)
					{
						rg=rst2.getInt(2);
						rgnm=rst2.getString(3);
						first=false;
					}

					if (rg!=rst2.getInt(2))
					{
			    		rfb = new Repo2FormBean();
		        		rfb.setNm2(rgnm); 
		        		rfb.setMcode(2);
		        		rfb.setDval2(rsalval);
		        		rfb.setDval3(resalval);
		        		rfb.setDval4(rbsalval);
		        		rfb.setDval5(rbudget);
		        		rfb.setDval7(rach);
		        		rfb.setDval6(rsurp);
						rg=rst2.getInt(2);
						rgnm=rst2.getString(3);

						rsalval=0.00;
		                resalval=0.00;
		                rbsalval=0.00;
		                rbudget=0.00;
		                rsurp=0.00;
		                rach=0.00;
		
		        		data.add(rfb);
					}


		        
                    ///// Month File Loop Starts to accumulate data
                    salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    budget=0.00;
                    surp=0.00;
                    ach=0.00;

 	            	
					    	 budget =  budget+rst2.getDouble(5);
					    	 budget = (Math.round(budget*100.0)/100.0);
						     salval = rst2.getDouble(6);
					         esalval = rst2.getDouble(7);
					         bsalval = salval-esalval;
					         surp = bsalval-budget;
		 	            	
						       
			        	 if (budget!=0)
				        	 ach = (bsalval/budget)*100.00;

		               	 rsalval = rsalval+salval;
		            	 resalval = resalval+esalval;
		            	 rbsalval = rbsalval+bsalval;
		    		 	 rbudget = rbudget+budget;
		    		 	 rsurp = rsurp+surp;
		    		 	 if (rbudget!=0)
			        		 rach = (rbsalval/rbudget)*100.00;
                    
                    
                    	 gsalval = gsalval+salval;
                    	 gesalval = gesalval+esalval;
                    	 gbsalval = gbsalval+bsalval;
	        		 	 gbudget = gbudget+budget;
	        		 	 gsurp = gsurp+surp;
	        		 	 if (gbudget!=0)
			        		 gach = (gbsalval/gbudget)*100.00;

        		 	 if(hprint)
        		 	 {
	        		 	 rfb = new Repo2FormBean();
			        	 rfb.setNm2(rst2.getString(4));
			        	 rfb.setMcode(1);
	        		 	 rfb.setDval2(salval);
		        		 rfb.setDval3(esalval);
		        		 rfb.setDval4(bsalval);
		        		 rfb.setDval5(budget);
		        		 rfb.setDval7(ach);
		        		 rfb.setDval6(surp);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		      
          	            data.add(rfb);
        		 	 }
	            
				}  /////// Main Loops Ends Here ////////////////////////////////////////
	                    rst2.close();
	            		ps2.close();
	            
			    		rfb = new Repo2FormBean();
		        		rfb.setNm2(rgnm); 
		        		rfb.setMcode(2);
		        		rfb.setDval2(rsalval);
		        		rfb.setDval3(resalval);
		        		rfb.setDval4(rbsalval);
		        		rfb.setDval5(rbudget);
		        		rfb.setDval7(rach);
		        		rfb.setDval6(rsurp);
          	            data.add(rfb);
		
						rsalval=0.00;
		                resalval=0.00;
		                rbsalval=0.00;
		                rbudget=0.00;
		                rsurp=0.00;
		                rach=0.00;
	            
			    		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : ");
	            		rfb.setMcode(3);
	            		rfb.setDval2(gsalval);
	            		rfb.setDval3(gesalval);
	            		rfb.setDval4(gbsalval);
		        		rfb.setDval5(gbudget);
		        		rfb.setDval7(gach);
		        		rfb.setDval6(gsurp);

	            		data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo5DAO.getAllArea " + e);
		}
		finally 
		{
		try {
		       if(mrec != null){mrec.close();}
		       if(ms1 != null){ ms1.close();}
		       if(rst2 != null){ rst2.close();}
		       if(ps2 != null){ps2.close();}
		       if(rst12 != null){ rst12.close();}
		       if(ps12 != null){ps12.close();}
		       if(con != null){con.close();}
			}
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo5DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
///////////////////////////////// Area Coding End Here/////////////////////////////////

///////////////////////////////// Branch Coding Start Here/////////////////////////////////
	public List getAllBranch(Connection con, int smon,int emon,int year,int depo_code,String tp,int uid,String utype) { 
	  	 
		Repo2FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement pst=null; 
        ResultSet rstt=null;
		
		String uvnm=null;
 		String tt=null;
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String tblnm1=null;
            String query3=null;
            String tquery=null;
            String txt1=null;
            String txt5=null; 
            String txt6=null;
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double budget =0.00;
            double ach=0.00;
            double surp = 0.00;
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double gbudget =0.00;
            double gach=0.00;
            double gsurp = 0.00;
            
            if (smon>emon)
            	emon=smon;

            if (tp.equals("A"))
            {	
            	tblnm="A_HQDetail08";
            	tblnm2="a_branch08";
            	tblnm1="a_target08";
            }	
            if (tp.equals("T"))
            {
            	tblnm="T_HQDetail08";
        	    tblnm2="t_branch08";
            	tblnm1="t_target08";
            }
            
            if (tp.equals("G"))
            {	
            	tblnm="G_HQDetail08";  
       	        tblnm2="g_branch08";
            	tblnm1="g_target08";
            }   
            
            txt1="BRANCH WISE/GROSS/CREDIT/NET SALE FOR THE MONTH OF ";
       	    
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
//////////////////////////////////////////////////////////////////////////////////////////

                String query2 = "Select depo_code,ter_name from "+tblnm2+" where depo_code=? and typ=? order by depo_code";
	            ps2 = con.prepareStatement(query2);
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp); 
		        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
	            	rfb = new Repo2FormBean();
		        	rfb.setNm2(rst2.getString(2)); 
		        
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1, smon);
		 			ms1.setInt(2, emon);
		 			ms1.setInt(3, year);
		 			mrec = ms1.executeQuery();
                    salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    budget=0.00;
                    surp=0.00;
                    ach=0.00;
                    
                    while (mrec.next())
		 			{	

		 			    if (mrec.isFirst())	
			 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 				 	  
	 			        if (mrec.isLast())
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);

		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		 	            	tt = "tt"+mrec.getInt(4);
		 	            	
	 	         if (utype.equals("PMT"))
	 	         {
			     tquery = "Select sum(((ttarget*"+tt+")/100)*tmrp),budmnth,budper,sum(budval) " +
	             " from "+tblnm1+" where depo_code=? and mkt_year=? and gp_main in " +
	             " (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
	             " group by depo_code order by depo_code";
	 	         }
	 	         else
	 	         {
 			     tquery = "Select sum(((ttarget*"+tt+")/100)*tmrp),budmnth,budper,sum(budval)" +
	            " from "+tblnm1+" where depo_code=? and mkt_year=? group by depo_code order by depo_code";
 		         }
	 	         
					        pst = con.prepareStatement(tquery); 
						    pst.setInt(1,depo_code);
						    pst.setInt(2, year);
		 			        rstt = pst.executeQuery(); 
						       
						     if (rstt.next())
						     { 
						    	 budget =  budget+rstt.getDouble(1);
						    	 if (mrec.getInt(6)>=rstt.getInt(2))
						    	 {
						    		 budget = budget+((rstt.getDouble(4)*rstt.getDouble(3))/100);
						    	 }
						    	 
						    	 budget = (Math.round(budget*100.0)/100.0);

						     }

		    if (utype.equals("PMT"))
		    {
		    query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") " +
            " from "+tblnm+" where depo_code=? and mkt_year=? and grp_cd in " +
            " (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
            " group by depo_code order by depo_code";
		    }
		    else 
		    {
            query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") " +
	        " from "+tblnm+" where depo_code=? and mkt_year=? group by depo_code order by depo_code";
		    }		        	 
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,depo_code); 
	        ps3.setInt(2,year);
	        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salval = salval+rst3.getDouble(1);
			        		 esalval = esalval+rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6);
			        		 bsalval = salval-esalval;
			        		 surp = bsalval-budget;	
			        		 if (budget!=0)
				        		 ach = (bsalval/budget)*100.00;
				        
			        }       
				     
		 		  } 
			        	 gsalval = gsalval+salval;
	        		 	 gesalval = gesalval+esalval;
	        		 	 gbsalval = gbsalval+bsalval;
	        		 	 gbudget = gbudget+budget;
	        		 	 gsurp = gsurp+surp;
	        		 	 if (gbudget!=0)
			        		 gach = (gbsalval/gbudget)*100.00;

	        		 	 rfb.setDval2(salval);
		        		 rfb.setDval3(esalval);
		        		 rfb.setDval4(bsalval);
		        		 rfb.setDval5(budget);
		        		 rfb.setDval7(ach);
		        		 rfb.setDval6(surp);
		        		 rfb.setNm3(txt1+txt5);
		        		 rfb.setLupdate(txt6);
 		      
          	            data.add(rfb); 				
				} 
			
			    		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : "); 
	            		rfb.setDval2(gsalval);
	            		rfb.setDval3(gesalval);
	            		rfb.setDval4(gbsalval);
		        		rfb.setDval5(gbudget);
		        		rfb.setDval7(gach);
		        		rfb.setDval6(gsurp);

	            		data.add(rfb); 				
			
		} catch (Exception e) { 
			
			System.out.println("========Exception in SQLRepo5DAO.getAllBranch " + e);
		}
		finally 
		{
		try {
		       if(mrec != null){mrec.close();}
		       if(ms1 != null){ ms1.close();}
		       if(rstt != null){ rstt.close();}
		       if(pst != null){ pst.close();}
		       if(rst3 != null){ rst3.close();}
		       if(ps3 != null){ps3.close();}
		       if(rst2 != null){ rst2.close();}
		       if(ps2 != null){ps2.close();}
		       if(rst12 != null){ rst12.close();}
		       if(ps12 != null){ps12.close();}
		       if(con != null){con.close();}

			} 
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo5DAO.Connection.close "+e);
			  }
		}
		return data;
	}
///////////////////////////////// Branch Coding End Here/////////////////////////////////
	
} 