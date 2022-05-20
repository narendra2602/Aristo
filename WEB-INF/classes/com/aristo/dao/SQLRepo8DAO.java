package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo8DAO {

	public List getAllHQ(Connection con, int code,int uv,int emon,int stype,int eyear,int depo_code,String tp) { 
		  
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
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String wtxt=null;
            String query3=null;
            
            int index=emon+1;
            int k=0;
            int hqty =0;
            float hval=0.0f;
            int[] vqty ; 
            float[] vval;
            int fs=0;
            
   	        tblnm=tp+"_hqdetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";

   	        
            if (stype==1)
            	wtxt=" (GROSS) ";
            if (stype==2)
            	wtxt=" (CREDIT) ";
            if (stype==3)
            	wtxt=" (NET) ";
            if (stype==4)
            	wtxt=" (EXPIRY) ";
            if (stype==5)
            	wtxt=" (SALABLE RETURN) ";
            if (stype==6)
            	wtxt=" (BREAKAGE/SPOILED) ";
            
			if (uv==1)
               txt2="     H.Q. WISE UNIT SALES TREND "+wtxt+ "FROM "; 
			if (uv==2)
	           txt2="     H.Q. WISE VALUE SALES TREND "+wtxt+ "FROM "; 
			if (uv==3)
	           txt2="     H.Q. WISE UNIT/VALUE SALES TREND "+wtxt+ " FROM "; 
			 
		vqty  = new int[index]; 
		vval = new float[index];
			
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
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
	      String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
		  ts1 = con.prepareStatement(terrec);
		  ts1.setInt(1, code);
		  ts1.setInt(2, eyear);
		  trec = ts1.executeQuery();
		  if (trec.next())
	          txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
		   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		  ms1 = con.prepareStatement(month);
 		  ms1.setInt(1,emon);
 		  ms1.setInt(2,eyear);
  		  mrec = ms1.executeQuery();    	   
			
//////////////////////////////////////Head Quater Query///////////////////////////////////////////            
         // String query1 = "Select ter_code,ter_name,no_of_rep from "+tblnm2+" where depo_code=? and ter_pt=? and mkt_year=? order by ter_code";
          String query1 = "Select ter_code,ter_name,oct,nov,decm,jan,feb,mar,apr,may,jun,jul,aug,sep from "+tblnm2+" where depo_code=? and ter_pt=? and mkt_year=? order by ter_code";
		  ps1 = con.prepareStatement(query1); 
		  ps1.setInt(1,depo_code);
		  ps1.setString(2, tp);
		  ps1.setInt(3,eyear);
		  rst1 = ps1.executeQuery();
			  
		    int fs1=0;
		    int col=0;
			while (rst1.next()) ////////////////////HQ Loop Start/////////////////////   
			{
				rfb = new Repo2FormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
                rfb.setNo_of_mr(rst1.getInt(3));
                rfb.setQty2(uv);
                k=0;  
                hqty=0;
                hval=0.0f;
                
                    col=3;
                    fs1=0;
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 				 fs1=fs1+rst1.getInt(col);
		 				 fs=fs+rst1.getInt(col);
		 				 col++;
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			             txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
 	           
		 			        unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);
		if (stype==1)
		{
		query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		"group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		if (stype==2)
		{
		query3="Select sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
			         " sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
			         " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
	// expiry, salable and brk/spoiled option added 22/06/2012	
		if (stype==4)
		{
		query3="Select sum(e"+unm+"),sum(e"+vnm+")" +
			         " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==5)
		{
		query3="Select sum(r"+unm+"),sum(r"+vnm+")" +
			         " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==6)
		{
		query3="Select sum(b"+unm+"),sum(s"+unm+"),sum(b"+vnm+"),sum(s"+vnm+") " +
			         " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
	// 4 expiry, 5 salable and 6 brk/spoiled option added 22/06/2012	
		
		if (stype==3)
		{
		query3="Select sum("+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
                     " sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
                     " from "+tblnm+" where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
                     " group by tr_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
       	ps3 = con.prepareStatement(query3); 
        ps3.setInt(1,rst1.getInt(1)); 
        ps3.setInt(2,code);
        ps3.setInt(3,depo_code);
        ps3.setInt(4,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
			        	 if ((uv==1) || (uv==3))
		 			     {	 
			        		if (stype==1)
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==2)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            hqty=hqty+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            vqty[k]=vqty[k]+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
			        		}

			        		if (stype==3)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6))));
                            hqty=hqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
                            vqty[k]=vqty[k]+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
			        		}

			        		if (stype==4)
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==5)
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(1)+rst3.getInt(2)));
                            hqty=hqty+(rst3.getInt(1)+rst3.getInt(2));
                            vqty[k]=vqty[k]+(rst3.getInt(1)+rst3.getInt(2));
			        		}
		 			     
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
				        	if (stype==1)
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
                            
				        	if (stype==2)
				        	{
                            rfb.setVal1(k, (rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)));
                            hval=hval+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
                            vval[k]=vval[k]+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
				        	}
                            
				        	if (stype==3)
				        	{
                            rfb.setVal1(k, (rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            hval=hval+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            vval[k]=vval[k]+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
				        	}

				        	if (stype==4)
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
				        	
				        	if (stype==5)
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
				        	
				        	if (stype==6)
				        	{
                            rfb.setVal1(k, (rst3.getInt(3)+rst3.getInt(4)));
                            hval=hval+(rst3.getInt(3)+rst3.getInt(4));
                            vval[k]=vval[k]+(rst3.getInt(3)+rst3.getInt(4));
				        	}

		 			     }   
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
	                rfb.setNo_of_mr(fs1);

	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb); 				
				} ///////////////////HQ Loop End/////////////////////////
			 		 rfb = new Repo2FormBean();
			 		 hqty=0;
			 		 hval=0;
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
 					 
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }

	                 data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo8DAO.getAllHQrepo8 " + e); 
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

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo8DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public List getAllRegion(Connection con, int code,int uv,int emon,int stype,int eyear,int depo_code,String tp) { 
		 
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
        PreparedStatement ps1hq=null; 
        ResultSet rst1hq=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String wtxt=null;
            String query3=null;
            
            int index =emon+1;
            int hqty =0;
            float hval=0.0f;
            int[] vqty;
            float[] vval;
            int fs=0;
            
 
   	        tblnm=tp+"_hqdetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_region_master08";
   	        tblnm3=tp+"_hq_master08";

   	        
   	        
            if (stype==1)
            	wtxt=" (GROSS) ";
            if (stype==2)
            	wtxt=" (CREDIT) ";
            if (stype==3)
            	wtxt=" (NET) ";
            if (stype==4)
            	wtxt=" (EXPIRY) ";
            if (stype==5)
            	wtxt=" (SALABLE RETURN) ";
            if (stype==6)
            	wtxt=" (BREAKAGE/SPOILED) ";
            
			if (uv==1)
               txt2="    REGION WISE UNIT SALES TREND "+wtxt+ "FROM  "; 
			if (uv==2)
	           txt2="    REGION WISE VALUE SALES TREND "+wtxt+ "FROM "; 
			if (uv==3)
	           txt2="    REGION WISE UNIT/VALUE SALES TREND "+wtxt+ " FROM "; 
			
		vqty = new int[index]; 
		vval = new float[index];
			
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
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
	    String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
		ts1 = con.prepareStatement(terrec);
		ts1.setInt(1, code);
		ts1.setInt(2, eyear);
    	trec = ts1.executeQuery();
    	if (trec.next())
            txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
    	   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		ms1 = con.prepareStatement(month);
 		ms1.setInt(1,emon);
 		ms1.setInt(2,eyear);
  		mrec = ms1.executeQuery();    	   
//////////////////////////////////////HQ Master Quater Query///////////////////////////////////////////            
//        String queryhq = "Select sum(no_of_rep) from "+tblnm3+" where depo_code=? and regn_code=? and ter_pt=? and mkt_year=? group by regn_code order by regn_code";
        String queryhq = "Select sum(oct),sum(nov),sum(decm),sum(jan),sum(feb),sum(mar),sum(apr),sum(may),sum(jun),sum(jul),sum(aug),sum(sep) from "+tblnm3+" where depo_code=? and regn_code=? and ter_pt=? and mkt_year=? group by regn_code order by regn_code";
		ps1hq = con.prepareStatement(queryhq); 
  		  
//////////////////////////////////////Region Master Quater Query///////////////////////////////////////////            
        String query1 = "Select reg_code,name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by reg_code";
		ps1 = con.prepareStatement(query1); 
		ps1.setInt(1,depo_code);
		ps1.setString(2,tp);
		ps1.setInt(3,eyear);
		rst1 = ps1.executeQuery();
			  
		    int fs1=0;
		    int col=1;
			while (rst1.next()) ////////////////////Region Loop Start/////////////////////   
			{
			    ps1hq.setInt(1,depo_code);
			    ps1hq.setInt(2,rst1.getInt(1));
			    ps1hq.setString(3,tp);
			    ps1hq.setInt(4,eyear);
			    rst1hq = ps1hq.executeQuery();
			    
				rfb = new Repo2FormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
			 if (rst1hq.next())
			 {
                rfb.setNo_of_mr(rst1hq.getInt(1));
               // fs=fs+rst1hq.getInt(1);
			 }
                rfb.setQty2(uv);
                int k=0;  
                hqty=0;
                hval=0.0f;
                	col=1;
                	fs1=0;
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			     
		 				 fs1=fs1+rst1hq.getInt(col);
		 				 fs=fs+rst1hq.getInt(col);
		 				 col++;
		 				 
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			             txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
		 			        unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);
		if (stype==1)
		{
		query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		"group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		if (stype==2)
		{
		query3="Select sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
			         " sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
			         " from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		// expiry, salable and brk/spoiled option added 22/06/2012	
		if (stype==4)
		{
		query3="Select sum(e"+unm+"),sum(e"+vnm+")" +
			         " from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==5)
		{
		query3="Select sum(r"+unm+"),sum(r"+vnm+")" +
			         " from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==6)
		{
		query3="Select sum(b"+unm+"),sum(s"+unm+"),sum(b"+vnm+"),sum(s"+vnm+") " +
			         " from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
	// 4 expiry, 5 salable and 6 brk/spoiled option added 22/06/2012	
		
		if (stype==3)
		{
		query3="Select sum("+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
                     " sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
                     " from "+tblnm+" where rg_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
                     " group by rg_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
       	ps3 = con.prepareStatement(query3); 
        ps3.setInt(1,rst1.getInt(1)); 
        ps3.setInt(2,code);
        ps3.setInt(3,depo_code);
        ps3.setInt(4,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
			        	 if ((uv==1) || (uv==3))
		 			     {	 
			        		if ((stype==1) || (stype==4) || (stype==5))
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==2)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            hqty=hqty+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            vqty[k]=vqty[k]+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
			        		}

			        		if (stype==3)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6))));
                            hqty=hqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
                            vqty[k]=vqty[k]+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
			        		}
			        		
			        		
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(1)+rst3.getInt(2)));
                            hqty=hqty+(rst3.getInt(1)+rst3.getInt(2));
                            vqty[k]=vqty[k]+(rst3.getInt(1)+rst3.getInt(2));
			        		}
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
				        	if ((stype==1) || (stype==4) || (stype==5))
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
                            
				        	if (stype==2)
				        	{
                            rfb.setVal1(k, (rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)));
                            hval=hval+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
                            vval[k]=vval[k]+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
				        	}
                            
				        	if (stype==3)
				        	{
                            rfb.setVal1(k, (rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            hval=hval+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            vval[k]=vval[k]+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
				        	}
				        	
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(3)+rst3.getInt(4)));
                            hqty=hqty+(rst3.getInt(3)+rst3.getInt(4));
                            vqty[k]=vqty[k]+(rst3.getInt(3)+rst3.getInt(4));
			        		}
		 			     }   
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
		 			rfb.setNo_of_mr(fs1);
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb); 				
				} ///////////////////Region Loop End/////////////////////////
			 		 rfb = new Repo2FormBean();
			 		 hqty=0;
			 		 hval=0;
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
 					 
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }
 		 			     
	                 data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo8DAO.getAllRegionRepo8 " + e); 
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
	           if(rst1hq != null){ rst1hq.close();}
	           if(ps1hq != null){ps1hq.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo8DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List getAllArea(Connection con, int code,int uv,int emon,int stype,int eyear,int depo_code,String tp) { 
		 
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
        PreparedStatement ps1hq=null; 
        ResultSet rst1hq=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String wtxt=null;
            String query3=null;

            int index =emon+1;
            int hqty =0;
            float hval=0.0f;
            int[] vqty;
            float[] vval;
            int fs=0;
            
   	        tblnm=tp+"_hqdetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_area_master08";
   	        tblnm3=tp+"_hq_master08";

   	        
   	        
            if (stype==1)
            	wtxt=" (GROSS) ";
            if (stype==2)
            	wtxt=" (CREDIT) ";
            if (stype==3)
            	wtxt=" (NET) ";
            if (stype==4)
            	wtxt=" (EXPIRY) ";
            if (stype==5)
            	wtxt=" (SALABLE RETURN) ";
            if (stype==6)
            	wtxt=" (BREAKAGE/SPOILED) ";
            
            
			if (uv==1)
               txt2="     AREA WISE UNIT SALES TREND "+wtxt+ " FROM "; 
			if (uv==2)
	           txt2="     AREA WISE VALUE SALES TREND "+wtxt+ " FROM "; 
			if (uv==3)
	           txt2="     AREA WISE UNIT/VALUE SALES TREND "+wtxt+ " FROM "; 

		vqty = new int[index]; 
		vval = new float[index];
			
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
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
	      String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
		  ts1 = con.prepareStatement(terrec);
		  ts1.setInt(1, code);
		  ts1.setInt(2, eyear);
    	  trec = ts1.executeQuery();
    	  if (trec.next())
            txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
    	   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		ms1 = con.prepareStatement(month);
 		ms1.setInt(1,emon);
 		ms1.setInt(2,eyear);
  		mrec = ms1.executeQuery();    	   
//////////////////////////////////////HQ Master Quater Query///////////////////////////////////////////            
        String queryhq = "Select sum(oct),sum(nov),sum(decm),sum(jan),sum(feb),sum(mar),sum(apr),sum(may),sum(jun),sum(jul),sum(aug),sum(sep) from "+tblnm3+" where depo_code=? and area_code=? and ter_pt=? and mkt_year=? group by area_code order by area_code";
		ps1hq = con.prepareStatement(queryhq); 
//////////////////////////////////////Region Master Quater Query///////////////////////////////////////////            
        String query1 = "Select area_cd,area_name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
		ps1 = con.prepareStatement(query1); 
		ps1.setInt(1,depo_code);
		ps1.setString(2, tp);
		ps1.setInt(3,eyear);
		rst1 = ps1.executeQuery();
			  
			int col=1;
			int fs1=0;
			while (rst1.next()) ////////////////////Region Loop Start/////////////////////   
			{
			    ps1hq.setInt(1,depo_code);
			    ps1hq.setInt(2,rst1.getInt(1));
			    ps1hq.setString(3, tp);
			    ps1hq.setInt(4,eyear);
			    rst1hq = ps1hq.executeQuery();
			    
				rfb = new Repo2FormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
			 if (rst1hq.next())
			 {
                rfb.setNo_of_mr(rst1hq.getInt(1));
                //fs=fs+rst1hq.getInt(1);
			 }
                rfb.setQty2(uv);
                int k=0;  
                hqty=0;
                hval=0.0f;
                	col=1;
                	fs1=0;
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 					fs1=fs1+rst1hq.getInt(col);
		 					fs=fs+rst1hq.getInt(col);
		 					col++;
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			     if (mrec.isLast())
			 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			        unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);
		 			        
		if (stype==1)
		{
		query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=?  " +
		"group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		if (stype==2)
		{
		query3="Select sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
			         " sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
			         " from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
		// expiry, salable and brk/spoiled option added 22/06/2012	
		if (stype==4)
		{
		query3="Select sum(e"+unm+"),sum(e"+vnm+")" +
			         " from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==5)
		{
		query3="Select sum(r"+unm+"),sum(r"+vnm+")" +
			         " from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		if (stype==6)
		{
		query3="Select sum(b"+unm+"),sum(s"+unm+"),sum(b"+vnm+"),sum(s"+vnm+") " +
			         " from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
		             " group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
	// 4 expiry, 5 salable and 6 brk/spoiled option added 22/06/2012	
		
		if (stype==3)
		{
		query3="Select sum("+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
                     " sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
                     " from "+tblnm+" where ar_cd=? and pr_code=? and depo_code=? and mkt_year=? " +
                     " group by ar_cd order by ar_cd,rg_cd,tr_cd"; 
		}
		
       	ps3 = con.prepareStatement(query3); 
        ps3.setInt(1,rst1.getInt(1)); 
        ps3.setInt(2,code);
        ps3.setInt(3,depo_code);
        ps3.setInt(4,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
			        	 if ((uv==1) || (uv==3))
		 			     {	 
			        		if ((stype==1) || (stype==4) || (stype==5))
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==2)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            hqty=hqty+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            vqty[k]=vqty[k]+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
			        		}

			        		if (stype==3)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6))));
                            hqty=hqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
                            vqty[k]=vqty[k]+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
			        		}
			        		
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(1)+rst3.getInt(2)));
                            hqty=hqty+(rst3.getInt(1)+rst3.getInt(2));
                            vqty[k]=vqty[k]+(rst3.getInt(1)+rst3.getInt(2));
			        		}
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
			        		if ((stype==1) || (stype==4) || (stype==5))
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
                            
				        	if (stype==2)
				        	{
                            rfb.setVal1(k, (rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)));
                            hval=hval+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
                            vval[k]=vval[k]+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
				        	}
                            
				        	if (stype==3)
				        	{
                            rfb.setVal1(k, (rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            hval=hval+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            vval[k]=vval[k]+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
				        	}
				        	
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(3)+rst3.getInt(4)));
                            hqty=hqty+(rst3.getInt(3)+rst3.getInt(4));
                            vqty[k]=vqty[k]+(rst3.getInt(3)+rst3.getInt(4));
			        		}
		 			     }   
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
		 			 rfb.setNo_of_mr(fs1);
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb); 				
				} ///////////////////Region Loop End/////////////////////////
			 		 rfb = new Repo2FormBean();
			 		 hqty=0;
			 		 hval=0;
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
 					 
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }
 		 			     
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo8DAO.getAllAreaRepo8 " + e); 
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
	           if(rst1hq != null){ rst1hq.close();}
	           if(ps1hq != null){ps1hq.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo8DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}		

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List getAllBranch(Connection con, int code,int uv,int emon,int stype,int eyear,int depo_code,String tp) { 
		 
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
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String wtxt=null;
            String query3=null;
            

            int index=emon+1;
            int hqty =0;
            float hval=0.0f;
            int[] vqty;
            float[] vval;
            int fs=0;
            
        	tblnm=tp+"_HQDetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_branch08";       	        
 
   	        tblnm=tp+"_hqdetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_branch08";

   	        
            if (stype==1)
            	wtxt=" (GROSS) ";
            if (stype==2)
            	wtxt=" (CREDIT) ";
            if (stype==3)
            	wtxt=" (NET) ";
            if (stype==4)
            	wtxt=" (EXPIRY) ";
            if (stype==5)
            	wtxt=" (SALABLE RETURN) ";
            if (stype==6)
            	wtxt=" (BREAKAGE/SPOILED) ";
            
			if (uv==1)
               txt2="     BRANCH WISE UNIT SALES TREND "+wtxt+ " FROM "; 
			if (uv==2)
	           txt2="     BRANCH WISE VALUE SALES TREND "+wtxt+ " FROM "; 
			if (uv==3)
	           txt2="     BRANCH WISE UNIT/VALUE SALES TREND "+wtxt+ " FROM "; 
			
		vqty = new int[index]; 
		vval = new float[index];
			
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
	 
////////////////////////////////////Product Master Query///////////////////////////////////////////                
	      String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
		  ts1 = con.prepareStatement(terrec);
		  ts1.setInt(1, code);
		  ts1.setInt(2, eyear);
          trec = ts1.executeQuery();
    	  if (trec.next())
           txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
    	  
/////////////////////////////////////Month File Query////////////////////////////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		ms1 = con.prepareStatement(month);
 		ms1.setInt(1,emon);
 		ms1.setInt(2,eyear);
  		mrec = ms1.executeQuery();    	   
  		 
//////////////////////////////////////Branch Master Quater Query///////////////////////////////////////////            
        String query1 = "Select depo_code,ter_name,no_of_rep from "+tblnm2+" where depo_code=? and typ=?  order by depo_code";
		ps1 = con.prepareStatement(query1); 
		ps1.setInt(1,depo_code);
		ps1.setString(2, tp);
		rst1 = ps1.executeQuery();
			  
			while (rst1.next()) ////////////////////Region Loop Start/////////////////////   
			{
				rfb = new Repo2FormBean();
				rfb.setName(rst1.getString(2));
                rfb.setMcode(index);
			    rfb.setNo_of_mr(rst1.getInt(3));
                fs=fs+rst1.getInt(3);
			    rfb.setQty2(uv);
                int k=0;  
                hqty=0;
                hval=0.0f;

 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			    
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setNm1(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setNm9(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
	 			     
	 			        unm="qty"+mrec.getString(3);
	 			        vnm="val"+mrec.getString(3);
		if (stype==1)
		{
		query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
		"group by depo_code order by depo_code,pr_code"; 
		}
		
		if (stype==2)
		{
		query3="Select sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
			         " sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
			         " from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
		             " group by depo_code order by depo_code,pr_code"; 
		}
		
		// expiry, salable and brk/spoiled option added 22/06/2012	
		if (stype==4)
		{
		query3="Select sum(e"+unm+"),sum(e"+vnm+")" +
			         " from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
		             " group by depo_code order by depo_code,pr_code"; 
		}
		if (stype==5)
		{
		query3="Select sum(r"+unm+"),sum(r"+vnm+")" +
			         " from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
		             " group by depo_code order by depo_code,pr_code"; 
		}
		if (stype==6)
		{
		query3="Select sum(b"+unm+"),sum(s"+unm+"),sum(b"+vnm+"),sum(s"+vnm+") " +
			         " from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
		             " group by depo_code order by depo_code,pr_code"; 
		}
	// 4 expiry, 5 salable and 6 brk/spoiled option added 22/06/2012	
		if (stype==3)
		{
		query3="Select sum("+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(r"+unm+"),sum(s"+unm+"),sum(p"+unm+")," +
                     " sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") " +
                     " from "+tblnm+" where pr_code=? and depo_code=? and mkt_year=? " +
                     " group by depo_code order by depo_code,pr_code";
		}
		
       	ps3 = con.prepareStatement(query3); 
        ps3.setInt(1,code);
        ps3.setInt(2,depo_code);
        ps3.setInt(3,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
			        	 if ((uv==1) || (uv==3))
		 			     {	 
			        		if ((stype==1) || (stype==4) || (stype==5))
			        		{
                            rfb.setQty1(k, rst3.getInt(1));
                            hqty=hqty+rst3.getInt(1);
                            vqty[k]=vqty[k]+rst3.getInt(1);
			        		}
			        		
			        		if (stype==2)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            hqty=hqty+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
                            vqty[k]=vqty[k]+((rst3.getInt(1)+rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)));
			        		}

			        		if (stype==3)
			        		{
                            rfb.setQty1(k,(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6))));
                            hqty=hqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
                            vqty[k]=vqty[k]+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
			        		}
			        		
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(1)+rst3.getInt(2)));
                            hqty=hqty+(rst3.getInt(1)+rst3.getInt(2));
                            vqty[k]=vqty[k]+(rst3.getInt(1)+rst3.getInt(2));
			        		}
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
			        		if ((stype==1) || (stype==4) || (stype==5))
				        	{
                            rfb.setVal1(k, rst3.getInt(2));
                            hval=hval+rst3.getInt(2);
                            vval[k]=vval[k]+rst3.getInt(2);
				        	}
                            
				        	if (stype==2)
				        	{
                            rfb.setVal1(k, (rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)));
                            hval=hval+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
                            vval[k]=vval[k]+(rst3.getInt(6)+rst3.getInt(7)+rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10));
				        	}
                            
				        	if (stype==3)
				        	{
                            rfb.setVal1(k, (rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            hval=hval+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
                            vval[k]=vval[k]+((rst3.getInt(7)-(rst3.getInt(8)+rst3.getInt(9)+rst3.getInt(10)+rst3.getInt(11)+rst3.getInt(12))));
				        	}
				        	
			        		if (stype==6)
			        		{
                            rfb.setQty1(k, (rst3.getInt(3)+rst3.getInt(4)));
                            hqty=hqty+(rst3.getInt(3)+rst3.getInt(4));
                            vqty[k]=vqty[k]+(rst3.getInt(3)+rst3.getInt(4));
			        		}
		 			     }   
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setNm1(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setNm9(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setNm3(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt6);
	 				
	                 data.add(rfb); 				
				} ///////////////////Region Loop End/////////////////////////
			 		 rfb = new Repo2FormBean();
			 		 hqty=0;
			 		 hval=0;
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
 					 
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }
 		 			     
	                 data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo8DAO.getAllBranchRepo8 " + e); 
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

			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo8DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}			
	
	
}
 