package com.aristo.dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo7DAO {

	public List getAllrepo(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		   
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;

		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {                 
            String tblnm=null;
  //          String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;   
            String terrec=null; 
            String query3=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;

            int gsalqty=0;
            int gsalbqty=0;
            int gexpqty=0;

            float salval=0f;
            float salbval=0f;
            float expval=0f;

            float gsalval=0f;
            float gsalbval=0f;
            float gexpval=0f;
            
            if (smon>emon)
            	emon=smon;

        	
        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();

        	
        	
        	txt2=" H.Q. WISE SALES DETAIL FROM "; 
             
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
    	   if (utype.equals("PMT"))
    	   {
    	   terrec = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code=? group by gp_code order by gp_code";    		   
    	   }
    	   else
    	   {
//    	   terrec = "Select distinct(gp_code),gp_name from "+tblnm1+" where gp_code=? and mkt_year=? group by gp_code order by gp_code";
    	   terrec = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code=?  order by gp_code";
    	   }
    	   ts1 = con.prepareStatement(terrec);
    	   
    	   ts1.setInt(1,code);
    	   if (!utype.equals("PMT"))
    	   {
//    	   ts1.setInt(2,eyear);
    	   }
    	   trec = ts1.executeQuery();
    			if (trec.next())
                    txt1="GROUP-> "+trec.getString(2);
            
            String query1 = "Select ter_code,ter_name from "+tblnm2+" where depo_code=? and mkt_year=? order by ter_code";
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,depo_code);
			ps1.setInt(2,eyear);
			rst1 = ps1.executeQuery();
              
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
            		        
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
            salval=0f;
            salbval=0f;
            expval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 		 	  
		 	            if (mrec.isLast())
			 			  txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);
		 	
		 	  if (utype.equals("PMT"))
		 	  {
		      query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where tr_cd=? "+
		      "  and grp_cd=? and depo_code=? and mkt_year=? group by grp_cd order by ar_cd,rg_cd,tr_cd,grp_cd";
		 	  }
		 	  else
		 	  {
/*		      query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where tr_cd=? "+
		      "  and mgrp_cd=? and depo_code=? and mkt_year=? group by mgrp_cd order by ar_cd,rg_cd,tr_cd,mgrp_cd";
*/
		      query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where tr_cd=? "+
		      "  and grp_cd=? and depo_code=? and mkt_year=? group by grp_cd order by ar_cd,rg_cd,tr_cd,grp_cd";
		 	  }
		      
		 	  ps3 = con.prepareStatement(query3); 
		      ps3.setInt(1,rst1.getInt(1)); 
		      ps3.setInt(2,code);
		      ps3.setInt(3,depo_code);
		      ps3.setInt(4,mrec.getInt(4));
		      rst3 = ps3.executeQuery(); 
		      if(rst3.next())
			        {
			         		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getFloat(7);
			        		 salbval=salbval+rst3.getFloat(8);
			        		 expval=expval+(rst3.getFloat(9)+rst3.getFloat(10)+rst3.getFloat(11)+rst3.getFloat(12));
			         }	 
				        
			        }      
				     
	        		 rfb.setQty2(salqty);
	        		 rfb.setQty3(salbqty);
	        		 rfb.setQty4(expqty);
	        		 rfb.setQty5(salqty-salbqty-expqty);
	        		 
	        		 rfb.setVal2(salval);
	        		 rfb.setVal3(salbval);
	        		 rfb.setVal4(expval);
	        		 rfb.setVal5(salval-salbval-expval);
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);

	        		 gsalqty = gsalqty  + salqty;
	        		 gsalbqty= gsalbqty + salbqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	 				
	                 data.add(rfb); 				
				} 
			 
			
					 rfb = new Repo2FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setQty2(gsalqty);
	        		 rfb.setQty3(gsalbqty);
	        		 rfb.setQty4(gexpqty);
	        		 rfb.setQty5(gsalqty-gsalbqty-gexpqty);
	        		 
	        		 rfb.setVal2(gsalval);
	        		 rfb.setVal3(gsalbval);
	        		 rfb.setVal4(gexpval);
	        		 rfb.setVal5(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
			    
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo7DAO.getAllHQrepo6 " + e); 
		}
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("--Exception in SQLRepo7DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////

	public List getAllRegion(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {               
            String tblnm=null;
//            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String txt6=null;
            String terrec=null;
            String query3=null; 
            int salqty=0;
            int salbqty=0;
            int expqty=0;

            int gsalqty=0;
            int gsalbqty=0;
            int gexpqty=0;

            float salval=0f;
            float salbval=0f;
            float expval=0f;

            float gsalval=0f;
            float gsalbval=0f;
            float gexpval=0f;

            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_region_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();

         	
         	
        	txt2=" REGION WISE SALES DETAIL FROM "; 
             
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
            if (utype.equals("PMT"))
            {
	    	   terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? order by gp_code";
            }
            else
            {
 	    	   terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? order by gp_code";
            }
            
	    	   ts1 = con.prepareStatement(terrec);
	    	   ts1.setInt(1, code);
	    	   if (!utype.equals("PMT"))
	    	   {
//	    		   ts1.setInt(2,eyear);
	    	   }
        	   trec = ts1.executeQuery();
        			if (trec.next())
                        txt1="GROUP-> "+trec.getString(2);			
            
    			String query1 = "Select reg_code,name from "+tblnm2+" where depo_code=? and mkt_year=? order by reg_code";
    			ps1 = con.prepareStatement(query1); 
    			ps1.setInt(1,depo_code);
    			ps1.setInt(2,eyear);
    			rst1 = ps1.executeQuery();
			
				while (rst1.next())   
				{
					rfb = new Repo2FormBean();
					rfb.setMname(rst1.getString(2));
            		        
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
		            salval=0f;
		            salbval=0f;
		            expval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
			 			  txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);
		 	        
		 	        if (utype.equals("PMT"))
		 	        {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where rg_cd=? "+
			            " and grp_cd=? and depo_code=? and mkt_year=? group by rg_cd order by ar_cd,rg_cd,tr_cd";
		 	        }
		 	        else
		 	        {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where rg_cd=? "+
			            " and grp_cd=?  and depo_code=? and mkt_year=? group by rg_cd order by ar_cd,rg_cd,tr_cd";
		 	        }
			        
		        	ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst1.getInt(1));
			        ps3.setInt(2,code); 
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getFloat(7);
			        		 salbval=salbval+rst3.getFloat(8);
			        		 expval=expval+(rst3.getFloat(9)+rst3.getFloat(10)+rst3.getFloat(11)+rst3.getFloat(12));
			         }	 
			        }      
				     
	        		 rfb.setQty2(salqty);
	        		 rfb.setQty3(salbqty);
	        		 rfb.setQty4(expqty);
	        		 rfb.setQty5(salqty-salbqty-expqty);
	        		 
	        		 rfb.setVal2(salval);
	        		 rfb.setVal3(salbval);
	        		 rfb.setVal4(expval);
	        		 rfb.setVal5(salval-salbval-expval);
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);

	        		 gsalqty = gsalqty  + salqty;
	        		 gsalbqty= gsalbqty + salbqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	 				
	                 data.add(rfb); 				
				} 
					 rfb = new Repo2FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setQty2(gsalqty);
	        		 rfb.setQty3(gsalbqty);
	        		 rfb.setQty4(gexpqty);
	        		 rfb.setQty5(gsalqty-gsalbqty-gexpqty);

	        		 rfb.setVal2(gsalval);
	        		 rfb.setVal3(gsalbval);
	        		 rfb.setVal4(gexpval);
	        		 rfb.setVal5(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo7DAO.getAllRegion " + e); 
		}
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("--Exception in SQLRepo7DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List getAllArea(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {                 
            String tblnm=null;
//            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String txt6=null;
            String terrec=null;
            String query3=null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;

            int gsalqty=0;
            int gsalbqty=0;
            int gexpqty=0;

            float salval=0f;
            float salbval=0f;
            float expval=0f;

            float gsalval=0f;
            float gsalbval=0f;
            float gexpval=0f;
            
            if (smon>emon)
            	emon=smon;
            
   	        
        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_area_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();

   	        
        	txt2=" AREA WISE SALES DETAIL FROM "; 
            
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
			if (utype.equals("PMT"))
			{
				 terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? order by gp_code";
			}
			else
			{
		    	 terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=?  order by gp_code";
			}
	    	   ts1 = con.prepareStatement(terrec);
	    	   ts1.setInt(1, code);
	    	   if (!utype.equals("PMT"))
	    	   {
//	    		   ts1.setInt(2,eyear);
	    	   }
         	   trec = ts1.executeQuery();
         			if (trec.next())
                         txt1="GROUP-> "+trec.getString(2);			
       		
    			String query1 = "Select area_cd,area_name from "+tblnm2+" where depo_code=? and mkt_year=? order by area_cd";
    			ps1 = con.prepareStatement(query1); 
    			ps1.setInt(1,depo_code);
    			ps1.setInt(2,eyear);
    			rst1 = ps1.executeQuery();
    		
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMname(rst1.getString(2));

				///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_code";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		            salqty=0;
		            salbqty=0;
		            expqty=0;
		            salval=0f;
		            salbval=0f;
		            expval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);

		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);
		 	        
		 	      if (utype.equals("PMT"))
		 	      {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where ar_cd=? "+
			            "  and grp_cd=?  and depo_code=? and mkt_year=? group by ar_cd order by ar_cd,rg_cd,tr_cd";
		 	      }
		 	      else
		 	      {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where ar_cd=? "+
			            "  and grp_cd=?  and depo_code=? and mkt_year=? group by ar_cd order by ar_cd,rg_cd,tr_cd";
		 	      }
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst1.getInt(1));
			        ps3.setInt(2,code); 
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getFloat(7);
			        		 salbval=salbval+rst3.getFloat(8);
			        		 expval=expval+(rst3.getFloat(9)+rst3.getFloat(10)+rst3.getFloat(11)+rst3.getFloat(12));
			         }	 
  
			        }      
				     
	        		 rfb.setQty2(salqty);
	        		 rfb.setQty3(salbqty);
	        		 rfb.setQty4(expqty);
	        		 rfb.setQty5(salqty-salbqty-expqty);
	        		 
	        		 rfb.setVal2(salval);
	        		 rfb.setVal3(salbval);
	        		 rfb.setVal4(expval);
	        		 rfb.setVal5(salval-salbval-expval);
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);

	        		 gsalqty = gsalqty  + salqty;
	        		 gsalbqty= gsalbqty + salbqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	 				
	                 data.add(rfb); 				
				} 
					 rfb = new Repo2FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setQty2(gsalqty);
	        		 rfb.setQty3(gsalbqty);
	        		 rfb.setQty4(gexpqty);
	        		 rfb.setQty5(gsalqty-gsalbqty-gexpqty);

	        		 rfb.setVal2(gsalval);
	        		 rfb.setVal3(gsalbval);
	        		 rfb.setVal4(gexpval);
	        		 rfb.setVal5(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo7DAO.getAllArea " + e); 
		}
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("--Exception in SQLRepo7DAO.Connection.close "+e);
			  }
		}

		
		return data;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////

	public List getAllBranch(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
     //       String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String txt6=null;
            String terrec=null;
            String query3=null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;

            int gsalqty=0;
            int gsalbqty=0;
            int gexpqty=0;

            float salval=0f;
            float salbval=0f;
            float expval=0f;

            float gsalval=0f;
            float gsalbval=0f;
            float gexpval=0f;
            
            if (smon>emon)
            	emon=smon;
            

        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();


            txt2="   BRANCH WISE SALES DETAIL FROM "; 
            
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
               
			 if (utype.equals("PMT"))
			 {
				   terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? order by gp_code";
			 }
			 else
			 {
		    	   terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? order by gp_code";
			 }
	    	   ts1 = con.prepareStatement(terrec);
	    	   ts1.setInt(1, code);
	    	   if (!utype.equals("PMT"))
	    	   {
//	    	   ts1.setInt(2,eyear);
	    	   }
         	   trec = ts1.executeQuery();
         			if (trec.next())
                         txt1="GROUP-> "+trec.getString(2);			
            
    			String query1 = "Select depo_code,ter_name from "+tblnm2+" where depo_code=? order by depo_code";
    			ps1 = con.prepareStatement(query1); 
    			ps1.setInt(1,depo_code);
    			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMname(rst1.getString(2));
		        
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
		             salval=0f;
		             salbval=0f;
		             expval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);
		 	            	
		 	      if (utype.equals("PMT"))
		 	      {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
			            " where grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code";
		 	      }
		 	      else
		 	      {
			        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
			            " where grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code";
		 	      }
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code);
			        ps3.setInt(2,depo_code);
			        ps3.setInt(3,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getFloat(7);
			        		 salbval=salbval+rst3.getFloat(8);
			        		 expval=expval+(rst3.getFloat(9)+rst3.getFloat(10)+rst3.getFloat(11)+rst3.getFloat(12));
			         }	 
 
			        }      
				     
	        		 rfb.setQty2(salqty);
	        		 rfb.setQty3(salbqty);
	        		 rfb.setQty4(expqty);
	        		 rfb.setQty5(salqty-salbqty-expqty);
	        		 
	        		 rfb.setVal2(salval);
	        		 rfb.setVal3(salbval);
	        		 rfb.setVal4(expval);
	        		 rfb.setVal5(salval-salbval-expval);
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);

	        		 gsalqty = gsalqty  + salqty;
	        		 gsalbqty= gsalbqty + salbqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	 				
	                 data.add(rfb); 				
				} 
					 rfb = new Repo2FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setQty2(gsalqty);
	        		 rfb.setQty3(gsalbqty);
	        		 rfb.setQty4(gexpqty);
	        		 rfb.setQty5(gsalqty-gsalbqty-gexpqty);

	        		 rfb.setVal2(gsalval);
	        		 rfb.setVal3(gsalbval);
	        		 rfb.setVal4(gexpval);
	        		 rfb.setVal5(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo7DAO.getAllBranch " + e); 
		}
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("--Exception in SQLRepo7DAO.Connection.close "+e);
			  }
		}

		
		return data;
	}

//////////////////////////////////////////////////////////////////////////////////////////////	
	
}  
 