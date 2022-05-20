package com.aristo.dao;

import java.sql.CallableStatement;
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
            float salval=0f;
            float esalval=0f;
            float bsalval=0f;
            
            float gsalval=0f;
            float gesalval=0f;
            float gbsalval=0f;

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();

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
            String query2 = "Select ter_code,ter_name from "+tblnm2+" where depo_code=? and ter_pt=? and mkt_year=? order by area_code,regn_code,ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3,eyear);
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
                    salval=0f;
                    esalval=0f;
                    bsalval=0f;
 
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast())
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		 	            	
		 	        if(utype.equals("PMT"))
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where tr_cd=? "+
		            " and depo_code=? and mkt_year=? and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') " +
		            " group by tr_cd order by ar_cd,rg_cd,tr_cd "; 
		 	        }
		 	        else
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where tr_cd=? "+
		            "  and depo_code=? and mkt_year=? group by tr_cd order by ar_cd,rg_cd,tr_cd "; 
		 	        }
		 	        
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,depo_code);
			        ps3.setInt(3, mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
		        		 salval = salval+rst3.getFloat(1);
		        		 esalval = esalval+rst3.getFloat(2);
		        		 bsalval = bsalval+rst3.getFloat(3);
			        }      
				     
		 		  } 
        		 	 gsalval = gsalval+salval;
        		 	 gesalval = gesalval+esalval;
        		 	 gbsalval = gbsalval+bsalval;

        		 	 rfb.setVal2(salval);
	        		 rfb.setVal3(esalval);
	        		 rfb.setVal4(bsalval);
	        		 rfb.setVal5(salval+esalval+bsalval);
	        	     rfb.setNm3(txt1+txt5); 
	        	     rfb.setLupdate(txt6);
	      
      	             data.add(rfb); 				
 				} 
	            	rfb = new Repo2FormBean();
	            	rfb.setNm2("TOTAL : "); 
	            	rfb.setVal2(gsalval);
	            	rfb.setVal3(gesalval);
	            	rfb.setVal4(gbsalval);
	            	rfb.setVal5(gsalval+gesalval+gbsalval);
   
	            	data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo3DAO.getAllrepo3 " + e);
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
				System.out.println("--Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}

	
	public List getAllRegion(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
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
            float salval=0f;
            float esalval=0f;
            float bsalval=0f;

            float gsalval=0f;
            float gesalval=0f;
            float gbsalval=0f;
            
            if (smon>emon)
            	emon=smon;

 
        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_region_master08").toLowerCase();

        	txt1="REGION WISE /EXPIRY/BREAKAGE/SALABLE FROM ";

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
            String query2 = "Select reg_code,name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by area_code,reg_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3,eyear);
	        rst2 = ps2.executeQuery();

	            while (rst2.next())
				{
	            	rfb = new Repo2FormBean();
		        	rfb.setNm2(rst2.getString(2)); 
		        
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
                    salval=0f;
                    esalval=0f;
                    bsalval=0f;
 
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast())
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		 	            	
		 	        if(utype.equals("PMT"))
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where rg_cd=? "+
		            " and depo_code=? and mkt_year=? and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') " +
		            " group by rg_cd order by ar_cd,rg_cd,tr_cd "; 
		 	        }
		 	        else
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where rg_cd=? "+
		            "  and depo_code=? and mkt_year=? group by rg_cd order by ar_cd,rg_cd,tr_cd ";
		 	        }
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,depo_code);
			        ps3.setInt(3,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
		        		 salval = salval+rst3.getFloat(1);
		        		 esalval = esalval+rst3.getFloat(2);
		        		 bsalval = bsalval+rst3.getFloat(3);
			        	 
			        }      
				     
		 		  } 
                    	 gsalval = gsalval   + salval;
                    	 gesalval = gesalval + esalval;
                    	 gbsalval = gbsalval + bsalval;
                    	 
		        		 rfb.setVal2(salval);
		        		 rfb.setVal3(esalval);
		        		 rfb.setVal4(bsalval);
		        		 rfb.setVal5(salval+esalval+bsalval);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		      
          	            data.add(rfb); 				
				} 
	            		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : "); 
	            		rfb.setVal2(gsalval);
	            		rfb.setVal3(gesalval);
	            		rfb.setVal4(gbsalval);
		        		rfb.setVal5(gsalval+gesalval+gbsalval);

	            		data.add(rfb); 				
				
			    
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo3DAO.getAllRegion " + e);
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
				System.out.println("--Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}


	
	public List getAllArea(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
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
            float salval=0f;
            float esalval=0f;
            float bsalval=0f;
            
            float gsalval=0f;
            float gesalval=0f;
            float gbsalval=0f;

            if (smon>emon)
            	emon=smon;

        	tblnm=tp+"_HQDetail08";
        	tblnm2=tp+"_area_master08";

        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_area_master08").toLowerCase();

        	
        	txt1="AREA WISE /EXPIRY/BREAKAGE/SALABLE FROM ";

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
            String query2 = "Select area_cd,area_name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3,eyear);
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
                    salval=0f;
                    esalval=0f;
                    bsalval=0f;
 
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast())
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		 	            	
		 	        if(utype.equals("PMT"))
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where ar_cd=? "+
		            " and depo_code=? and mkt_year=? and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') " +
		            " group by ar_cd order by ar_cd,rg_cd,tr_cd "; 
		 	        }
		 	        else
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where ar_cd=? "+
		            "  and depo_code=? and mkt_year=? group by ar_cd order by ar_cd,rg_cd,tr_cd ";
		 	        }
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,depo_code);
			        ps3.setInt(3,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
		        		 salval = salval+rst3.getFloat(1);
		        		 esalval = esalval+rst3.getFloat(2);
		        		 bsalval = bsalval+rst3.getFloat(3);
			        }      
				     
		 		  } 
                    	 gsalval = gsalval+salval;
                    	 gesalval = gesalval+esalval;
                    	 gbsalval = gbsalval+bsalval;

	        		 	 rfb.setVal2(salval);
		        		 rfb.setVal3(esalval);
		        		 rfb.setVal4(bsalval);
		        		 rfb.setVal5(salval+esalval+bsalval);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		      
          	            data.add(rfb); 				
				} 
	            		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : "); 
	            		rfb.setVal2(gsalval);
	            		rfb.setVal3(gesalval);
	            		rfb.setVal4(gbsalval);
		        		rfb.setVal5(gsalval+gesalval+gbsalval);
	            		data.add(rfb);
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo3DAO.getAllArea " + e);
		}
		finally 
		{
		try 
		{
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
				System.out.println("--Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}

	public List getAllBranch(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
	  	 
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
            String txt5 =null; 
            String txt6=null;
            float salval=0f;
            float esalval=0f;
            float bsalval=0f;
            
            float gsalval=0f;
            float gesalval=0f;
            float gbsalval=0f;
            
            if (smon>emon)
            	emon=smon;

 
        	tblnm=(tp+"_hqdetail08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();

    	    
            txt1="BRANCH WISE /EXPIRY/BREAKAGE/SALABLE FROM ";
            
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
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
                    salval=0f;
                    esalval=0f;
                    bsalval=0f;
 
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast()) 
		 			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);

		 	            	uvnm="val"+mrec.getString(3);

		 	        if(utype.equals("PMT"))
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+" where depo_code=? " +
		 	        " and mkt_year=? and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') " +
		            " group by depo_code order by depo_code "; 
		 	        }
		 	        else
		 	        {
		 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+") from "+tblnm+
		            " where depo_code=? and mkt_year=? group by depo_code order by depo_code ";
		 	        }
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,depo_code); 
			        ps3.setInt(2,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
		        		 salval = salval+rst3.getFloat(1);
		        		 esalval = esalval+rst3.getFloat(2);
		        		 bsalval = bsalval+rst3.getFloat(3);
			        }      
				     
		 		  } 
	        		 	 gsalval = gsalval+salval;
	        		 	 gesalval = gesalval+esalval;
	        		 	 gbsalval = gbsalval+bsalval;

	        		 	 rfb.setVal2(salval);
		        		 rfb.setVal3(esalval);
		        		 rfb.setVal4(bsalval);
		        		 rfb.setVal5(salval+esalval+bsalval);
		        	     rfb.setNm3(txt1+txt5); 
		        	     rfb.setLupdate(txt6);
 		      
          	            data.add(rfb); 				
				} 
	            		rfb = new Repo2FormBean();
	            		rfb.setNm2("TOTAL : "); 
	            		rfb.setVal2(gsalval);
	            		rfb.setVal3(gesalval);
	            		rfb.setVal4(gbsalval);
	            		rfb.setVal5(gsalval+gesalval+gbsalval);
	            		data.add(rfb); 				
			
		} catch (Exception e) { 
			
			System.out.println("========Exception in SQLRepo3DAO.getAllBranch " + e);
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
				System.out.println("--Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}
	
	
	
	
	public List getNewBranch(Connection con, int smon,int emon,int eyear,int depo_code,int div_code,int uid,int utype,int repo_type) { 
		
		Repo2FormBean rfb;
        ResultSet rst2=null;

		CallableStatement cs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;

        
 		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String txt1=null;
            String txt5 =null; 
            String txt6=null;
            
            float gsalval=0f;
            float gesalval=0f;
            float gbsalval=0f;
            
            if (smon>emon)
            	emon=smon;

 
			if(depo_code>0 && (utype==2 || utype==5))
				utype=1;
	
			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;

			
			String procedureWithParameters="{call web_report_hqwise_salable_exp_brk(?,?,?,?,?,?,?,?)}";

			if(repo_type==1)
				procedureWithParameters="{call web_report_hqwise_expiry_ratio(?,?,?,?,?,?,?,?)}";
			
            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            ps1=con.prepareStatement(branchname);
            ps1.setInt(1, depo_code);
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
            	txt1=rs1.getString(1)+ " Branch: "+(repo_type==1?"  EXPIRY RATIO FROM ":"  EXPIRY/BREAKAGE/SALABLE FROM ");

            }
            else
            	txt1="All India:  "+(repo_type==1?"  EXPIRY RATIO FROM ":"  EXPIRY/BREAKAGE/SALABLE FROM ");
           
            rs1.close();
            ps1.close();

			
    	    
            
             
	            
	            cs = con.prepareCall(procedureWithParameters);
				cs.setInt(1, eyear);
				cs.setInt(2, div_code);
				cs.setInt(3, depo_code);
				cs.setInt(4, smon);
				cs.setInt(5, emon);
				cs.setInt(6, utype);
				cs.setInt(7, uid);
				cs.setInt(8, 0);
		        rst2 = cs.executeQuery();

		        boolean first=true;
	            while (rst2.next())
				{
	            	if(first)
	            	{
	            		txt5= rst2.getString(10)+" To "+rst2.getString(11);
	            		first=false;
	            	}
	            	rfb = new Repo2FormBean();
	            	rfb.setNm2(rst2.getString(5)); 
	            	rfb.setVal2(rst2.getDouble(6));
	            	rfb.setVal3(rst2.getFloat(7));
	            	rfb.setVal4(rst2.getFloat(8));
	            	rfb.setVal5(rst2.getFloat(9));
	            	rfb.setNm3(txt1+txt5); 
	            	rfb.setLupdate(txt6);




	            	gsalval = gsalval+rst2.getFloat(6);
	            	gesalval = gesalval+rst2.getFloat(7);
	            	gbsalval = gbsalval+rst2.getFloat(8);


	            	data.add(rfb); 				
				}
	            
	            rfb = new Repo2FormBean();
	            rfb.setNm2("TOTAL : "); 
	            rfb.setVal2(gsalval);
	            rfb.setVal3(gesalval); 
	            if(repo_type==1)
	            	rfb.setVal4((gesalval/gsalval)*100);
	            else
	            	rfb.setVal4(gbsalval);
	            rfb.setVal5(gsalval+gesalval+gbsalval);
	            data.add(rfb); 				
			
		} catch (Exception e) { 
			
			System.out.println("========Exception in SQLRepo3DAO.getNewBranch " + e);
		}
		finally 
		{
		try {
           if(rst2 != null){ rst2.close();}
           if(cs != null){cs.close();}
           if(con != null){con.close();}
			} 
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo3DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}
		
} 