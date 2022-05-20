package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
import java.util.List;
 

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo2DAO {

	public List getAllrepo(Connection con,int code, int uv, int smon,int emon, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		  
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        
		String uvnm=null;
		String uvnm1=null;
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1 =null;            
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6 =null;
            int salqty=0;
            int gsalqty=0;
            
            double salval=0.00;
            double gsalval=0.00;
            double gval[];

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
       	    
            if (uv==1)
            {
            	txt3="UNIT-WISE/ ";
            }
            if (uv==2)
            {
            	txt3="VALUE-WISE/ ";
            }
            if (uv==3)
            {
            	txt3="UNIT/VALUE WISE/ ";
            }
            if(saletp==1)
            	txt4=" GROSS SALE FROM ";
            if(saletp==2)
            	txt4=" CREDIT NOTE FROM ";
            if(saletp==3)
            	txt4=" NET SALE FROM ";
            
                txt2="PRODUCT WISE /";
                txt1="H.Q. WISE /";
          
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
            
//////////////////////////////HQ Count Ki Query/////////////////////////////////
            String terrec = "Select count(*) from "+tblnm2+" where ter_pt=? and depo_code=? and ter_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,code);
			ts1.setInt(4,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1)+1;
			    gval = new double[t];
			    
			    trec.close();
			    ts1.close();
			    
//////////////////////////////Month File ki Query/////////////////////////////////			    
		    String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	 		
//////////////////////////////Headquater ki Query/////////////////////////////////
            String query2 = "Select ter_code,txt from "+tblnm2+" where depo_code=? and ter_pt=? and ter_code=? and mkt_year=? order by area_code,regn_code,ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3,code);
	        ps2.setInt(4,eyear);
	        rst2 = ps2.executeQuery();
        
//////////////////////////////Product Master ki Query/////////////////////////////////    
	        if (utype.equals("PMT"))
	        {
	         if (sr==null)
	         {	
		        query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by mcode";
	         }
	         else
	         {
	            query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
	            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and pname like '"+sr+"%' order by mcode";
	         }
	        }
	        else
	        {
	         if (sr==null)
	         {	
	        	 query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? group by pcode order by mcode";
	         }
	         else
	         {
	        	query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pname like '"+sr+"%' group by pcode order by mcode";	         
	         }
	        }
	        
	        ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2, eyear);
			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
                rfb.setQty3(uv);
	            int k=0;  
                gsalqty=0;
                gsalval=0;
		        
                rst2.beforeFirst();	
	            while (rst2.next())
				{	  
			        	if ((uv==1) || (uv==3))
			        	{	
			        	  rfb.setNm1(k,(rst2.getString(2)+" UNITS"));
			        	}
			        	if ((uv==2) || (uv==3)) 
			        	{	
			        	  rfb.setNm9(k,(rst2.getString(2)+" VALUE"));
			        	}
		        
                    ///// Month File Loop Starts to accumulate data
                    salqty=0;
                    salval=0.00f;
                    float f = 0.00f;
	                mrec.beforeFirst();
                    while (mrec.next()) // monthfile ka loop starts
		 			{	
		 				if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		
		 			    uvnm="qty"+mrec.getString(3);
		 	            uvnm1="val"+mrec.getString(3);
		        	 
//////////////////////////////HQ Detail Ki Query/////////////////////////////////			    
		 	   	   String query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+")," +
		 	       " sum("+uvnm1+"),sum(e"+uvnm1+"),sum(b"+uvnm1+"),sum(r"+uvnm1+"),sum(s"+uvnm1+"),sum(p"+uvnm1+") from "+tblnm+ 
		 	       " where  tr_cd=? and pr_code=? and depo_code=? and mkt_year=? group by mpr_code order by depo_code,ar_cd,rg_cd,tr_cd,mpr_code";
		 	   	   ps3 = con.prepareStatement(query3); 	
		 	   	   ps3.setInt(1,rst2.getInt(1));
			       ps3.setInt(2,rst1.getInt(1));
			       ps3.setInt(3,depo_code);
			       ps3.setInt(4,mrec.getInt(4)); 
			       rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {  
			         if (saletp==1)
			         {	 
			        		 salqty = salqty+rst3.getInt(1);
			        		 salval = salval+rst3.getDouble(7);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
			        		 salqty = salqty +(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 salval = salval +(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 
			         if (saletp==3)
			         {	 
			        		 salqty = salqty +rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 salval = salval+ rst3.getDouble(7)-(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 

			         f = (float) (Math.round(salval*100.0f)/100.0f);

                        
/*			        	   String que = "update "+tblnm+ " set pr_type =? where  tr_cd=? and mpr_code=? and depo_code=? ";
                            System.out.println(que);
					        PreparedStatement psu = con.prepareStatement(que); 
					        psu.setString(1,"X");
					        psu.setInt(2,rst2.getInt(1));
					        psu.setInt(3,rst1.getInt(1));
					        psu.setInt(4,depo_code);
					        psu.executeUpdate();
					        psu.close();
*/	 		        	   
			        }
			        rst3.close();
			        ps3.close();
		 		  }  // end of monthfile loop
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 
		        	 	 gval[k]=gval[k]+f;
		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5);
		        	     rfb.setLupdate(txt6);
		        	 k++;
			      }
	        	 	if ((uv==1) || (uv==3))
	        	 	{	 
	            		rfb.setNm1(k,"TOTAL UNITS");
	            		rfb.setQty1(k,gsalqty);
	            		
	        	 	}		
	                  
	        	 	if ((uv==2) || (uv==3))
	        	 	{	 
	            		rfb.setNm9(k,"TOTAL VALUE");
	            		rfb.setDval0(k,gsalval);
	            		
	        	 	}		
//                    gval[k]=gsalval;
	            data.add(rfb); 				
				} 
			    rfb = new Repo2FormBean();
	   			rfb.setMname("GRAND TOTAL :");
	   			int z=0;
	   			gsalval=0;
	   				for (z=0; z<t-1;z++)
	   				{
	   				  if (uv==1)
		   					rfb.setQty1(z,(int) gval[z]);
	   				  else
	   					  	rfb.setDval0(z,gval[z]);
	   				  gsalval=gsalval+gval[z];
	   				  
	   				}
	   				  if (uv==1)
		   					rfb.setQty1(z,(int) gsalval);
	   				  else
	   					  	rfb.setDval0(z,gsalval);
	   
                data.add(rfb); 				
				
				ms1.close();
				mrec.close();
				ps2.close();
				rst2.close();
				rst1.close();
				ps1.close();
					
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2DAO.getAllrepo2 " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List getAllregion(Connection con,int code, int uv, int smon,int emon, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr)  { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;		
		String uvnm=null;
        String uvnm1=null;		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            int salqty=0;
            int gsalqty=0;
            double salval=0.00;
            double gval[];
            double gsalval=0.00;
            
            if (smon>emon)
            	emon=smon;
            
   	        tblnm=(tp+"_hqdetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();

   	        
            if (uv==1)
            {
            	txt3="UNIT-WISE/";
            }
            if (uv==2)
            {
            	txt3="VALUE-WISE/";
            }
            if (uv==3)
            {
            	txt3="UNIT/VALUE WISE/";
            }
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT NOTE SALE FROM ";
            if(saletp==3)
            	txt4="NET SALE FROM ";
            
            txt2="PRODUCT WISE/";
            txt1="REGION WISE/";
      
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

//////////////////////////////HQ Count Ki Query/////////////////////////////////
            String terrec = "Select count(*) from "+tblnm2+" where ter_pt=? and depo_code=? and regn_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,code);
			ts1.setInt(4,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1)+1;
			    gval = new double[t];
			    
			    trec.close();
			    ts1.close();	   
			    
///////////////////////////////// Month File Master Query //////////////////////////////////               
            String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
		 	ms1.setInt(3,eyear);		
			mrec = ms1.executeQuery();
    		
//////////////////////////////Headquater ki Query/////////////////////////////////
            String query2 = "Select ter_code,txt from "+tblnm2+" where depo_code=? and ter_pt=? and regn_code=? and mkt_year=? order by area_code,regn_code,ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3, code);
	        ps2.setInt(4,eyear);
	        rst2 = ps2.executeQuery();
			
///////////////////////////////// Product Master Query //////////////////////////////////			
	        if (utype.equals("PMT"))
	        {
	         if (sr==null)
	         {	
		        query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by mcode";
	         }
	         else
	         {
	            query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
	            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and pname like '"+sr+"%' order by mcode";
	         }
	        }
	        else
	        {
	         if (sr==null)
	         {	
	        	 query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? group by pcode order by mcode";
	         }
	         else
	         {
	        	query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pname like '"+sr+"%' group by pcode order by mcode";	         
	         }
	        }
	        
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2,eyear);
			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
                rfb.setQty3(uv);
                gsalqty=0;
                gsalval=0;
         
	            int k=0;  

                rst2.beforeFirst();
	            while (rst2.next())
				{
  
		        	if ((uv==1) || (uv==3))
		        	{	
		        	  rfb.setNm1(k,(rst2.getString(2)+" UNITS"));
		        	  
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{	
		        	  rfb.setNm9(k,(rst2.getString(2)+" VALUE"));
		        	}
		        	
                    ///// Month File Loop Starts to accumulate data
                    salqty=0;
                    salval=0f;
		 			mrec.beforeFirst();
                    while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
	 	            	uvnm="qty"+mrec.getString(3);
	 	            	uvnm1="val"+mrec.getString(3);
		        	
//////////////////////////////////////// HQ Detail Query ////////////////////////////////////////
  	              String query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+")," +
		          "sum("+uvnm1+"),sum(e"+uvnm1+"),sum(b"+uvnm1+"),sum(r"+uvnm1+"),sum(s"+uvnm1+"),sum(p"+uvnm1+") from "+tblnm+ 
			      " where  tr_cd=? and pr_code=? and depo_code=? and mkt_year=? group by tr_cd order by depo_code,ar_cd,rg_cd,tr_cd,mpr_code";
		          ps3 = con.prepareStatement(query3);
			      ps3.setInt(1,rst2.getInt(1));
			      ps3.setInt(2,rst1.getInt(1));
			      ps3.setInt(3,depo_code);
			      ps3.setInt(4,mrec.getInt(4));
			      rst3 = ps3.executeQuery(); 
			        
			        if(rst3.next())
  			        {
				         if (saletp==1)
				         {	 
				        		 salqty = salqty +rst3.getInt(1);
				        		 salval = salval+rst3.getDouble(7);
				         }	 
				        	 
				         if (saletp==2)
				         {	 
				        		 salqty = salqty + (rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
				        		 salval = salval +(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
				         }	 
				         if (saletp==3)
				         {	 
				        		 salqty = salqty + rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
				        		 salval = salval + rst3.getDouble(7)-(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
				         }	 
	
			        }
			        rst3.close();
			        ps3.close();
		 		 }

		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 

		        	 	 gval[k]=gval[k]+salval;
		        	 
		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5);
		        	     rfb.setLupdate(txt6);
		        	     k++;
     			}	
        	 	if ((uv==1) || (uv==3))
        	 	{	 
            		rfb.setNm1(k,"TOTAL UNITS");
            		rfb.setQty1(k,gsalqty);
        	 	}		
                  
        	 	if ((uv==2) || (uv==3))
        	 	{	 
            		rfb.setNm9(k,"TOTAL VALUE");
            		rfb.setDval0(k,gsalval);
        	 	}		
	            
	            if(t!=0) 
		        data.add(rfb); 				
				} 
			
	   			rfb = new Repo2FormBean();
 	   			rfb.setMname("TOTAL :");
 	   			gsalval=0;
 	   			int z=0;
 	   				for (z=0; z<t-1;z++)
 	   				{
 		   				  if (uv==1)
 			   					rfb.setQty1(z,(int) gval[z]);
 		   				  else
 		   					  	rfb.setDval0(z,gval[z]);
 		   				  gsalval=gsalval+(int)gval[z];
    	            }
	   				  if (uv==1)
		   					rfb.setQty1(z,(int) gsalval);
	   				  else
	   					  	rfb.setDval0(z,gsalval);
    	   
                    data.add(rfb); 				

                    ms1.close();
					mrec.close();
					ps2.close();
					rst2.close();
					rst1.close();
					ps1.close();		
		} 
		 
		
			catch (Exception e) 
			  {
				System.out.println("========Exception in SQLRepo2DAO.getAllregion " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
   			  }   
			  catch (SQLException e) 
			  {
				System.out.println("-------------Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
 
//////////////////////////////////////////////////////////////////////////////////////////////////////


	public List getAllArea(Connection con,int code, int uv, int smon,int emon, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		String uvnm=null;
		String uvnm1=null;
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            int salqty=0;
            int gsalqty=0;
            double salval=0.00;
            double gval[];
            double gsalval=0.00;

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_region_master08").toLowerCase();

   	        
            if (uv==1)
            {
            	txt3="UNIT-WISE/";
            }
            if (uv==2)
            {
            	txt3="VALUE-WISE/";
            }
            if (uv==3)
            {
            	txt3="UNIT/VALUE WISE/";
            }
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT NOTE SALE FROM ";
            if(saletp==3)
            	txt4="NET SALE FROM ";
            
                txt2="PRODUCT WISE/";
                txt1="AREA WISE/";
          
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
	        
///////////////////////////////// Region Count Query //////////////////////////////////
            String terrec = "Select count(*) from "+tblnm2+" where typ=?  and depo_code=? and area_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,code);
			ts1.setInt(4,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1)+1;
			    gval = new double[t];
                
			trec.close();
            ts1.close();
               
///////////////////////////////////////////////////////////////////////////////////////    
	        if (utype.equals("PMT"))
	        {
	         if (sr==null)
	         {	
		        query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by mcode";
	         }
	         else
	         {
	            query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
	            " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and pname like '"+sr+"%' order by mcode";
	         }
	        }
	        else
	        {
	         if (sr==null)
	         {	
	        	 query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? group by pcode order by mcode";
	         }
	         else
	         {
	        	query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and mname like '"+sr+"%' group by pcode order by mcode";	         
	         }
	        }

	        ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2,eyear);
			rst1 = ps1.executeQuery();
			//int i =1;
            
			while (rst1.next())   
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
                rfb.setQty3(uv);
                gsalqty=0;
                gsalval=0.00;                 
	            int k=0;  

	            String query2 = "Select reg_code,name from "+tblnm2+" where depo_code=? and typ=? and area_code=? and mkt_year=? order by reg_code";
		        ps2 = con.prepareStatement(query2); 
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp);
		        ps2.setInt(3,code);
		        ps2.setInt(4,eyear);
		        rst2 = ps2.executeQuery();

		        while (rst2.next())
				{
		        	if ((uv==1) || (uv==3))
		        	{	
		        	  rfb.setNm1(k,(rst2.getString(2)+" UNITS"));
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{	
		        	  rfb.setNm9(k,(rst2.getString(2)+" VALUE"));
		        	}
		        	
		        	
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
                    salqty=0;
                    salval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 				 	  
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
	 			    	
	 	            	uvnm="qty"+mrec.getString(3);
	 	            	uvnm1="val"+mrec.getString(3);
	        	
		        	
		            String query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+")," +
             		" sum("+uvnm1+"),sum(e"+uvnm1+"),sum(b"+uvnm1+"),sum(r"+uvnm1+"),sum(s"+uvnm1+"),sum(p"+uvnm1+") from "+tblnm+ 
	                " where  rg_cd=? and pr_code=? and depo_code=? and mkt_year=? group by rg_cd order by depo_code,ar_cd,rg_cd,tr_cd,mpr_code";
		            ps3 = con.prepareStatement(query3);
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        
			        if(rst3.next())
			        {
			        	 
			         if (saletp==1)
			         {	 
			        		 salqty = salqty +rst3.getInt(1);
			        		 salval = salval+rst3.getDouble(7);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
			        		 salqty = salqty+(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 salval = salval+(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 


			         if (saletp==3)
			         {	 
			        		 salqty = salqty+rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 salval = salval+rst3.getDouble(7)-(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 

			         // System.out.println(rst3.getDouble(2)); 
			        }      
   	 		   }
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 
		        	 
		        	 	 gval[k]=gval[k]+salval; 
		        	 
		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5);
		        	     rfb.setLupdate(txt6);
		        	     k++;
				          
			      }	
	            
			            if ((uv==1) || (uv==3))
		        	 	{	 
		            		rfb.setNm1(k,"TOTAL UNITS");
		            		rfb.setQty1(k,gsalqty);
		        	 	}		
		                  
		        	 	if ((uv==2) || (uv==3))
		        	 	{	 
		            		rfb.setNm9(k,"TOTAL VALUE");
		            		rfb.setDval0(k,gsalval);
		        	 	}		
		          //      gval[k]=gval[k]+gsalval;

	            
	            
		        data.add(rfb); 				
				} 
			
     	   			rfb = new Repo2FormBean();
     	   			rfb.setMname("TOTAL :");
     	   			int z=0;
     	   			gsalval=0;
     	   				for (z=0; z<t-1;z++)
     	   				{
     		   				  if (uv==1)
     			   					rfb.setQty1(z,(int) gval[z]);
     		   				  else
     		   					  	rfb.setDval0(z,gval[z]);
     		   				  gsalval=gsalval+gval[z];
	    	   
     	   				}
		   				  if (uv==1)
			   					rfb.setQty1(z,(int) gsalval);
		   				  else
		   					  	rfb.setDval0(z,gsalval);
	    	   
		   		    data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2DAO.getAllregion " + e);
		}
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}	
		
		return data; 
	}


//////////////////////////////////// Branch /////////////////////////////	
	public List getAllBranch(Connection con, int uv, int smon, int emon, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		 
		Repo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;

		String uvnm=null;
		String uvnm1=null;
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            int salqty=0;
            double salval=0.00;
            double gval[];
            int gsalqty=0;
            double gsalval=0.00;
            int k=0;

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_area_master08").toLowerCase();

   	        
            if (uv==1)
            {
            	txt3="UNIT-WISE/";
            }
            if (uv==2)
            {
            	txt3="VALUE-WISE/";
            }
            if (uv==3)
            {
            	txt3="UNIT/VALUE WISE/";
            }
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT NOTE SALE FROM ";
            if(saletp==3)
            	txt4="NET SALE FROM ";
            
                txt2="PRODUCT WISE/";
                txt1="BRANCH WISE/";
          
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
            
//////////////////////// Month File Loop Starts to accumulate data
            String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
//////////////////////////////////////////////////////////////////////////////////////////////////////             
            String terrec = "Select count(*) from "+tblnm2+" where typ=? and depo_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1)+1;
			
			  gval = new double[t];
			  
			  trec.close();
			  ts1.close();
			
	        if (utype.equals("PMT"))
	        {
	         if (sr==null)
	         {	
		        query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by mcode";
	         }
	         else
	         {	
		        query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pd_group in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and pname like '"+sr+"%' order by mcode";
		     }
	        }

	        else
	        {  
	         if (sr==null)
	         {	
		        	query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? order by mcode";
	         }
	         else
	         {	
		        	query1 = "Select pcode,pname,pack from "+tblnm1+" where pcode<>? and mkt_year=? and pname like '"+sr+"%'  order by mcode";
		     }
	        }

	        ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2,eyear);
			rst1 = ps1.executeQuery();
              
			while (rst1.next())   ////////////////// Product Master Loop Starts ////////////////////
			{	
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setQty2(t);
                rfb.setQty3(uv);
                gsalqty=0;
                gsalval=0.00;
	            k=0;  

	            String query2 = "Select area_cd,area_name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
		        ps2 = con.prepareStatement(query2); 
		        ps2.setInt(1,depo_code);
		        ps2.setString(2,tp);
		        ps2.setInt(3,eyear);
		        rst2 = ps2.executeQuery();
		        
              //  rst2.beforeFirst();
	            while (rst2.next())    /////////Branch Master loop starts
				{
		        	if ((uv==1) || (uv==3))
		        	{	
		        	  rfb.setNm1(k,(rst2.getString(2)+" UNITS"));
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{	
		        	  rfb.setNm9(k,(rst2.getString(2)+" VALUE"));
		        	}
		        	
                    salqty=0;
                    salval=0.00;
                    mrec.beforeFirst();
		 			while (mrec.next())  ///////// Month file loop starts
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
	 	            	uvnm="qty"+mrec.getString(3);
	 	            	uvnm1="val"+mrec.getString(3);
		        	
		            String query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+")," +
		            " sum("+uvnm1+"),sum(e"+uvnm1+"),sum(b"+uvnm1+"),sum(r"+uvnm1+"),sum(s"+uvnm1+"),sum(p"+uvnm1+") from "+tblnm+ 
  		            " where ar_cd=? and  pr_code=? and depo_code=? and mkt_year=? group by ar_cd order by depo_code,ar_cd,rg_cd,tr_cd,mpr_code";
		            ps3 = con.prepareStatement(query3);
			        ps3.setInt(1,rst2.getInt(1));
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        
			        if(rst3.next())   // HQ Detail Read Single Records (sum kiya hai)
			        {
			        	 
			         if (saletp==1)
			         {	 
		        		 salqty = salqty+rst3.getInt(1);
	        		     salval=salval+rst3.getDouble(7);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
		        		 salqty = salqty+(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
		        		 salval=salval+(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 

			         if (saletp==3)
			         {	 
		        		 salqty=salqty+(rst3.getInt(1)-(rst3.getInt(2)+rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6)));
		        		 salval=salval+(rst3.getDouble(7)-(rst3.getDouble(8)+rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12)));
			         }	 

			         }      ////////////// HQ Detail if ka close/////////////////////
   	 		   }  ////////////// End of Month file loop//////////////////////////// 
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	  
		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5);
		        	     rfb.setLupdate(txt6);
			             gval[k] = gval[k]+salval;
		        	     k++;
			        	 
			      }	/////////// End of Branch Master loop///////////////////
			            if ((uv==1) || (uv==3))
		        	 	{	 
		            		rfb.setNm1(k,"TOTAL UNITS");
		            		rfb.setQty1(k,gsalqty);
		        	 	}		
		                  
		        	 	if ((uv==2) || (uv==3))
		        	 	{	 
		            		rfb.setNm9(k,"TOTAL VALUE");
		            		rfb.setDval0(k,gsalval);
		        	 	}		
//		                gval[k]=gval[k]+salval;
 	                    data.add(rfb); 				
				} //////////// End of Product Master Loop//////////////////////
			
            	   rfb = new Repo2FormBean();
    			   rfb.setMname("TOTAL :");
                   int z=0;
                   gsalval=0;
			       for (z=0; z<t-1;z++)
			       {
		   				  if (uv==1)
			   					rfb.setQty1(z,(int) gval[z]);
		   				  else
		   					  	rfb.setDval0(z,gval[z]);
		   				  gsalval=gsalval+gval[z];
			       }
	   				  if (uv==1)
		   					rfb.setQty1(z,(int) gsalval);
	   				  else
	   					  	rfb.setDval0(z,gsalval);
			    	   
			        data.add(rfb); 				
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLRepo2DAO.getAllregion " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}		
		return data; 
	}


} 