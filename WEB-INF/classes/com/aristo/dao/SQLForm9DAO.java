package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm9DAO {

	public List getAllRegion(Connection con, int smon, int emon, int eyear,int depo_code , String tp,int uid,String utype)  { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;

    	int mon=0;
    	String wname=null;
    	
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String query1=null;
            String txt1=null;
            String txt5 =null;
            String txt6=null;
            double salval[];
            int tval=0;
            double gtval=0.00;
            int gval[];
            int trep=0;
            int grep=0;
            double pmr= 0.00;
        
            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_target08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3=(tp+"_region_master08").toLowerCase();
            
            txt1=" PRODUCT WISE PMR IN UNITS ";   
            
            int t=0;
            
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
    		
////////////////////////////////////////Region Count Query////////////////////////////////////////            
            String terrec = "Select count(*) from "+tblnm3+" where typ=? and depo_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1);
		    gval = new int[t];
		    salval=new double [t];
		    
			    trec.close();
			    ts1.close();
		    
////////////////////////////////////////Region Master Query////////////////////////////////////////		    
            String query2 = "Select reg_code, name from "+tblnm3+" where depo_code=? and typ=? and mkt_year=? order by reg_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp);
	        ps2.setInt(3,eyear);
	        rst2 = ps2.executeQuery();
	        
////////////////////////////////////////Month File Query////////////////////////////////////////	        
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
////////////////////////////////////////Product Master Query////////////////////////////////////////		    
            if (utype.equals("PMT"))
            {
     		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
     		" and pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
     		" group by mcode order by mcode";            	
            }
            else
            {
 			query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
 			" group by mcode order by mcode";
            }
            ps1 = con.prepareStatement(query1); 
            ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();
		 
            
			 while (rst1.next())   ////////////////////////Product Master Loop Start here///////////////////
			 {
				rfb = new MktFormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
             
                int k=0; 
	            tval=0;	

                rst2.beforeFirst();
                while (rst2.next())////////////////////////Region Master Loop Start////////////////////
				{	
		        	  rfb.setNm1(k,(rst2.getString(2)));
		        	  
		        	  //////////////// Initialization/////////////////
                    mrec.beforeFirst();
		 			while (mrec.next())////////////// Month File Loop Start//////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);

		 			    if (mrec.isLast())
				 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    mon=mrec.getInt(4);
		 			    wname=mrec.getString(3);
		 			        if (wname.equals("DEC"))
		 			        	wname="DECM";
		        	
////////////////////////////////////////Region Master Query////////////////////////////////////////		    
	            String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? and regn_code=? and mkt_year=? group by regn_code order by regn_code";
		        psq = con.prepareStatement(queryq);
   	            psq.setInt(1,depo_code);
		 		psq.setString(2,tp);
		 		psq.setInt(3,rst2.getInt(1));
		 		psq.setInt(4,mrec.getInt(5));
		 		rstq = psq.executeQuery();
		 			        
		 	    if (rstq.next())
		 	    {
		 	    	trep=trep+rstq.getInt(1);		
		 	    	grep=grep+rstq.getInt(1);
		 	    }

		 	    rstq.close();
		 	    psq.close();
		 	    
	            String query3 = "Select sum(ta"+mon+") from "+tblnm+ " where " +
		        " rg_cd=? and pr_code=? and depo_code=? and mkt_year=? group by rg_cd order by ar_cd,rg_cd,tr_cd,pr_code";
			        
		            ps3 = con.prepareStatement(query3);
		            ps3.setInt(1,rst2.getInt(1));
		            ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(5));
			        rst3 = ps3.executeQuery(); 
			        
			          if(rst3.next())/////////////Target Master Start//////////////
			          {
			        		 salval[k] =salval[k]+rst3.getDouble(1);
			        		 gval[k]=gval[k]+rst3.getInt(1);
			        		 tval=tval+rst3.getInt(1);
			        		 
 			           }//////////////////Target Master End//////////////////

			          rst3.close();
			          ps3.close();
			          
		 		}//////////////////////////Month File Loop End///////////////////
		 		 
		 		     if (trep!=0)
		 		     {	 
                       if (salval[k]<0)
   		 		    	pmr = ((((salval[k]/trep)*-1)+.50)*-1);
                       else
		 		    	pmr = ((salval[k]/trep)+.50);
                       
		            	rfb.setQty0(k, (int)pmr);
		 		     }
		        	     rfb.setNm3(txt1+txt5);
		        	     rfb.setLupdate(txt6);
		        	     salval[k]=0;
		        	     trep=0;
		        	     k++;
				}///////////////Region Master Loop End//////////////	

                gtval=gtval+tval;
	            if (grep!=0)
	            	rfb.setQty3(tval/grep);
	           
	            
                grep=0;
	            if(t!=0) 
		        data.add(rfb); 				
			}/////////////////Product Master Loop end///////////////// 
			
				 

//////////////////////////// All Statement Close here////////////////////////////
				ms1.close();
				mrec.close();
				ps2.close();
				rst2.close();
				rst1.close();
				ps1.close(); 
	     }
		
		catch (Exception e) 
		  {
			System.out.println("========Exception in SQLForm9DAO.getAllRegion " + e);
		  }
		finally 
		{
		try {
            if(rst12 != null){ rst12.close();}
            if(ps12 != null){ ps12.close();}
            if(trec != null){ trec.close();}
            if(ts1 != null){ ts1.close();}
            if(mrec != null){mrec.close();}
            if(ms1 != null){ ms1.close();}
            if(rst1 != null){ rst1.close();}
            if(ps1 != null){ ps1.close();}
            if(rstq != null){ rstq.close();}
            if(psq != null){ psq.close();}	            
            if(rst3 != null){ rst3.close();}
            if(ps3 != null){ps3.close();}
            if(rst2 != null){ rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("--Exception in SQLForm9DAO.Connection.close "+e);
			  }
		}
		return data;
	}
	
////////////////////////////////////// Region Coding End here ///////////////////////////////////////

//////////////////////////////////////Area Coding Start here ///////////////////////////////////////	
	public List getAllArea(Connection con, int smon, int emon, int eyear, int depo_code , String tp,int uid,String utype)  { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
    	int mon=0;
    	String wname=null;
    	
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String query1=null;
            String txt1=null;
            String txt5 =null;
            String txt6=null;
            double salval[];
            int tval=0;
            double gtval=0.00;
            int gval[];
            int trep=0;
            int grep=0;
            double pmr=0.00;

            if (smon>emon)
            	emon=smon;

            tblnm=(tp+"_target08").toLowerCase();
            tblnm1=(tp+"_product08").toLowerCase();
       	    tblnm2=(tp+"_hq_master08").toLowerCase();
       	    tblnm3=(tp+"_area_master08").toLowerCase();
            
            txt1=" PRODUCT WISE PMR IN UNITS ";   
            
            int t=0;
            
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
    		
////////////////////////////////////////Area Count Query////////////////////////////////////////            
            String terrec = "Select count(*) from "+tblnm3+" where typ=? and depo_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1);
		    gval = new int[t];
		    salval=new double [t];
		    
			    trec.close();
			    ts1.close();
		    
////////////////////////////////////////Area Master Query////////////////////////////////////////		    
            String query2 = "Select area_cd, area_name from "+tblnm3+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp);
	        ps2.setInt(3,eyear);
	        rst2 = ps2.executeQuery();
	        
////////////////////////////////////////Month File Query////////////////////////////////////////	        
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
////////////////////////////////////////Product Master Query////////////////////////////////////////		    
            if (utype.equals("PMT"))
            {
     		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
     		" and pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
     		" group by mcode order by mcode";            	
            }
            else
            {
 			query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
 			" group by mcode order by mcode";
            }
            ps1 = con.prepareStatement(query1); 
            ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();
			int i =1;
            
			 while (rst1.next())   ////////////////////////Product Master Loop Start here///////////////////
			 {
				rfb = new MktFormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
             
                int k=0; 
	            tval=0;	

                rst2.beforeFirst();
                while (rst2.next())////////////////////////Area Master Loop Start////////////////////
				{	
		        	  rfb.setNm1(k,(rst2.getString(2)));
		        	  
		        	  //////////////// Initialization/////////////////
                    mrec.beforeFirst();
		 			while (mrec.next())////////////// Month File Loop Start//////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);

		 			    if (mrec.isLast())
				 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);

		 			        mon=mrec.getInt(4);
		 			        wname=mrec.getString(3);
		 			        if (wname.equals("DEC"))
		 			        	wname="DECM";
		        	
////////////////////////////////////////HQ Master Query////////////////////////////////////////		    
	            String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? and area_code=? and mkt_year=? group by area_code order by area_code";
		        psq = con.prepareStatement(queryq);
   	            psq.setInt(1,depo_code);
		 		psq.setString(2,tp);
		 		psq.setInt(3,rst2.getInt(1));
		 		psq.setInt(4,mrec.getInt(5));
		 		rstq = psq.executeQuery();
		 			        
		 	    if (rstq.next())
		 	    {
		 	    	trep=trep+rstq.getInt(1);		
		 	    	grep=grep+rstq.getInt(1);
		 	    }

		 	    rstq.close();
		 	    psq.close();
		 	    
            String query3 = "Select sum(ta"+mon+") from "+tblnm+ " where " +
	        " ar_cd=? and pr_code=? and depo_code=? and mkt_year=? group by ar_cd order by ar_cd,rg_cd,tr_cd,pr_code";
            ps3 = con.prepareStatement(query3);
            ps3.setInt(1,rst2.getInt(1));
            ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,depo_code);
	        ps3.setInt(4, eyear);
	        rst3 = ps3.executeQuery(); 
	        
		          if(rst3.next())/////////////Target Master Start//////////////
			          {
			        		 salval[k] =salval[k]+rst3.getDouble(1);
			        		 gval[k]=gval[k]+rst3.getInt(1);
			        		 tval=tval+rst3.getInt(1);
			        		 
 			           }//////////////////Target Master End//////////////////

			          rst3.close();
			          ps3.close();
			          
		 		}//////////////////////////Month File Loop End///////////////////
		 		 
		 		     if (trep!=0)
		 		     {	 
	                       if (salval[k]<0)
	      		 		    	pmr = ((((salval[k]/trep)*-1)+.50)*-1);
	                          else
	                        	pmr = ((salval[k]/trep)+.50);
	                       
	                       		rfb.setQty0(k, (int) pmr);
		 		     }
		        	     rfb.setNm3(txt1+txt5);
		        	     rfb.setLupdate(txt6);
		        	     salval[k]=0;
		        	     trep=0;
		        	     k++;
				}///////////////HQ Master Loop End//////////////	

                gtval=gtval+tval;
	            if (grep!=0)
	            rfb.setQty3(tval/grep);
	           
	            
                grep=0;
	            if(t!=0) 
		        data.add(rfb); 				
			}/////////////////Product Master Loop end///////////////// 
			
				System.out.println("hum yaha per hai "+ i++);

//////////////////////////// All Statement Close here////////////////////////////
				ms1.close();
				mrec.close();
				ps2.close();
				rst2.close();
				rst1.close();
				ps1.close(); 
	     }
		
		catch (Exception e) 
		  {
			System.out.println("========Exception in SQLForm9DAO.getAllAREA " + e);
		  }

		finally 
		{
		try {
            if(rst12 != null){ rst12.close();}
            if(ps12 != null){ ps12.close();}
            if(trec != null){ trec.close();}
            if(ts1 != null){ ts1.close();}
            if(mrec != null){mrec.close();}
            if(ms1 != null){ ms1.close();}
            if(rst1 != null){ rst1.close();}
            if(ps1 != null){ ps1.close();}
            if(rstq != null){ rstq.close();}
            if(psq != null){ psq.close();}	            
            if(rst3 != null){ rst3.close();}
            if(ps3 != null){ps3.close();}
            if(rst2 != null){ rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("--Exception in SQLForm9DAO.Connection.close "+e);
			  }
		}

		return data;
	}
 
////////////////////////////////////// Area Coding End here ///////////////////////////////////////
	
//////////////////////////////////////HQ Coding Start here ///////////////////////////////////////	
	public List getAllHQ(Connection con,int code, int smon, int emon,int eyear,int depo_code , String tp,int uid,String utype)  { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
    	int mon=0;
    	String wname=null;
    	
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1=null;
            String txt1=null;
            String txt5 =null;
            String txt6=null;
            double salval[];
            int tval=0;
            double gtval=0.00;
            int gval[];
            int trep=0;
            int grep=0;
            double pmr=0.00;

            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_target08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
            
            txt1=" PRODUCT WISE PMR IN UNITS ";   
            System.out.println(eyear);
            int t=0;
            
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
    		
////////////////////////////////////////HQ Count Query////////////////////////////////////////            
            String terrec = "Select count(*) from "+tblnm2+" where ter_pt=? and depo_code=? and regn_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,code);
			ts1.setInt(4, eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1);
		    gval = new int[t];
		    salval=new double [t];
		    
		        trec.close();
			    ts1.close();
		    
////////////////////////////////////////HQ Master Query////////////////////////////////////////		    
            String query2 = "Select ter_code, ter_name from "+tblnm2+" where depo_code=? and ter_pt=? and regn_code=? and mkt_year=? order by ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp);
	        ps2.setInt(3,code);
	        ps2.setInt(4, eyear);
	        rst2 = ps2.executeQuery();
	        
////////////////////////////////////////Month File Query////////////////////////////////////////	        
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
////////////////////////////////////////Product Master Query////////////////////////////////////////		    
            if (utype.equals("PMT"))
            {
     		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
     		" and pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') " +
     		" group by mcode order by mcode";            	
            }
            else
            {
 			query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>0 and mkt_year=? " +
 			" group by mcode order by mcode";
            }

            ps1 = con.prepareStatement(query1); 
            ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();
			int i =1;
            
			 while (rst1.next())   ////////////////////////Product Master Loop Start here///////////////////
			 {
				rfb = new MktFormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
             
                int k=0; 
	            tval=0;	

                rst2.beforeFirst();
                while (rst2.next())////////////////////////HQ Master Loop Start////////////////////
				{	
		        	  rfb.setNm1(k,(rst2.getString(2)));
		        	  
		        	  //////////////// Initialization/////////////////
                    mrec.beforeFirst();
		 			while (mrec.next())////////////// Month File Loop Start//////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);

		 			    if (mrec.isLast())
				 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			      
		 			        mon=mrec.getInt(4);
		 			        wname=mrec.getString(3);
		 			        if (wname.equals("DEC"))
		 			        	wname="DECM";
		        	
////////////////////////////////////////HQ Master Query////////////////////////////////////////		    
	            String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? and ter_code=? and mkt_year=? order by ter_code";
		        psq = con.prepareStatement(queryq);
   	            psq.setInt(1,depo_code);
		 		psq.setString(2,tp);
		 		psq.setInt(3,rst2.getInt(1));
		 		psq.setInt(4,mrec.getInt(5));
		 		rstq = psq.executeQuery();
		 			        
		 	    if (rstq.next())
		 	    {
		 	    	trep=trep+rstq.getInt(1);		
		 	    	grep=grep+rstq.getInt(1);
		 	    	
		 	    }
		 		
		 	    rstq.close();
		 	    psq.close();
		 			        
	            String query3 = "Select sum(ta"+mon+") from "+tblnm+ " where " +
		        " tr_cd=? and pr_code=? and depo_code=? and mkt_year=? group by pr_code order by ar_cd," +
		        " rg_cd,tr_cd,pr_code";
	            ps3 = con.prepareStatement(query3);
	            ps3.setInt(1,rst2.getInt(1));
	            ps3.setInt(2,rst1.getInt(1));
		        ps3.setInt(3,depo_code);
		        ps3.setInt(4, eyear);
		        rst3 = ps3.executeQuery(); 
			        
			          if(rst3.next())/////////////Target Master Start//////////////
			          {
			        		 salval[k] =salval[k]+rst3.getDouble(1);
			        		 gval[k]=gval[k]+rst3.getInt(1);
			        		 tval=tval+rst3.getInt(1);
			        		 
 			           }//////////////////Target Master End//////////////////

			          rst3.close();
			          ps3.close();
			          
		 		}//////////////////////////Month File Loop End///////////////////
		 		 
		 		     if (trep!=0)
		 		     {	 
	                       if (salval[k]<0)
	      		 		    	pmr = ((((salval[k]/trep)*-1)+.50)*-1);
	                          else
	                            pmr = ((salval[k]/trep)+.50);
	                       
	                       rfb.setQty0(k, (int) pmr);
		 		     }
		        	     rfb.setNm3(txt1+txt5);
		        	     rfb.setLupdate(txt6);
		        	     salval[k]=0;
		        	     trep=0;
		        	     k++;
				}///////////////HQ Master Loop End//////////////	

                gtval=gtval+tval;
	            if (grep!=0)
	            rfb.setQty3(tval/grep);
	           
	            
                grep=0;
	            if(t!=0) 
		        data.add(rfb); 				
			}/////////////////Product Master Loop end///////////////// 
			
				System.out.println("hum yaha per hai "+ i++);

//////////////////////////// All Statement Close here////////////////////////////
				ms1.close();
				mrec.close();
				ps2.close();
				rst2.close();
				rst1.close();
				ps1.close(); 
	     }
		
		catch (Exception e) 
		  {
			System.out.println("========Exception in SQLForm9DAO.getAllHQ " + e);
		  }

		finally 
		{
		try {
            if(rst12 != null){ rst12.close();}
            if(ps12 != null){ ps12.close();}
            if(trec != null){ trec.close();}
            if(ts1 != null){ ts1.close();}
            if(mrec != null){mrec.close();}
            if(ms1 != null){ ms1.close();}
            if(rst1 != null){ rst1.close();}
            if(ps1 != null){ ps1.close();}
            if(rstq != null){ rstq.close();}
            if(psq != null){ psq.close();}	            
            if(rst3 != null){ rst3.close();}
            if(ps3 != null){ps3.close();}
            if(rst2 != null){ rst2.close();}
            if(ps2 != null){ps2.close();}
            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("--Exception in SQLForm9DAO.Connection.close "+e);
			  }
		}

		return data;
	}
 
////////////////////////////////////// HQ Coding End here ///////////////////////////////////////
	
}
