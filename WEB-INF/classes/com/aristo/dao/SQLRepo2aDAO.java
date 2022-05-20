package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo2aDAO {
	public List getAllrepo(Connection con,int code,int smon,int emon,int rs, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		   
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
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
           // String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;   
            String query1=null;
            String query3=null;            
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            String wtxt="";
            float lacs=0.00f;
            float salval=0f; 
            float gval[];    

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
        //	tblnm1=tp+"_group_mkt08";
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();
   	        
        	txt3="VALUE WISE /";
            
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT SALE FROM  ";
            if(saletp==3)
            	txt4="NET SALE FOR FROM  ";
        
            txt2="GROUP WISE /";
            txt1="H.Q. WISE /";
            
	         int t=0;
	         
	         if (rs==2)
	        	 wtxt=" - (IN LACS) " ;
            
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
			    gval = new float[t];
			    
			    trec.close();
			    ts1.close();
             
//////////////////////////////Headquater ki Query/////////////////////////////////
            String query2 = "Select ter_code,txt from "+tblnm2+" where depo_code=? and ter_pt=? and ter_code=? and mkt_year=? order by area_code,regn_code,ter_code";
	        ps2 = con.prepareStatement(query2);
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp); 
	        ps2.setInt(3,code);
	        ps2.setInt(4,eyear);
	        rst2 = ps2.executeQuery();

////////////////////////////Month File Loop Starts to accumulate data/////////////////////
	         String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		 ms1 = con.prepareStatement(month);
	 		 ms1.setInt(1,smon);
	 		 ms1.setInt(2,emon);
	 		 ms1.setInt(3,eyear); 
	 		 mrec = ms1.executeQuery();
		     
//////////////////////////////////Group Master Query/////////////////////////////////////
				if (utype.equals("PMT"))
				{
		         if (sr==null)
		         {	
		 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
			        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_code";
		         }
		         else
		         {	
		 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
			        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and gp_name like '"+sr+"%' order by gp_code";
			     }
				}
				
				else
				{
		         if (sr==null)
		         {	
//		             query1 = "Select distinct(gp_code),gp_name from "+tblnm1+" where gp_code<>?  and mkt_year=? group by gp_code order by gp_code";
		             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  order by gp_code";
		         }
		         else
		         {	
//		             query1 = "Select distinct(gp_code),gp_name from "+tblnm1+" where gp_code<>?  and mkt_year=? and gp_name like '"+sr+"%' group by gp_code order by gp_code";	        	 
		             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  and gp_name like '"+sr+"%'  order by gp_code";	        	 
			     }
				}
            ps1 = con.prepareStatement(query1); 
			
			if (!utype.equals("PMT"))
			{
			ps1.setInt(1,0);
//	        ps1.setInt(2, eyear);
			}
			
			rst1 = ps1.executeQuery();

			while (rst1.next())      //////////////////Group Master Loop Start//////////////////   
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
                rfb.setQty2(t);
	            int k=0;  
	            
	            rst2.beforeFirst();
	            while (rst2.next())         ///////////////////////HQ Master Loop Start////////////////////
				{
		        	rfb.setNm1(k,rst2.getString(2)); 
                    salval=0f;
                    lacs=0.0f;
                    
                    mrec.beforeFirst();
                    while (mrec.next())    ///////////////////////Month File Loop Start/////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			        
 			            if (mrec.isLast())
	 			           txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	             uvnm="val"+mrec.getString(3);
		        	
///////////////////////////HQ Detail Query////////////////////////////////////////// 
		 	if (utype.equals("PMT"))
		 	{
           	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where tr_cd=? "+
            " and grp_cd=?  and depo_code=? and mkt_year=? group by grp_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd";
		 	}
		 	else 
		 	{
/*		 	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where tr_cd=? "+
	        " and mgrp_cd=?  and depo_code=? and mkt_year=? group by mgrp_cd order by depo_code,ar_cd,rg_cd,tr_cd,mgrp_cd";
*/	
		 	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where tr_cd=? "+
	        " and grp_cd=?  and depo_code=? and mkt_year=? group by grp_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd";
		 	}
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,rst2.getInt(1));
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,depo_code);
	        ps3.setInt(4,mrec.getInt(4));
	        rst3 = ps3.executeQuery();
	        
			        if(rst3.next())   ///////////////////HQ Detail Start///////////////
			        {
			         if (saletp==1)
			        		 salval = salval+rst3.getFloat(1);
			         if (saletp==2)
			        		 salval = salval +(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));
			         if (saletp==3)
			        		 salval = salval+rst3.getFloat(1)-(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));

			        } //////////////////HQ Detail End///////////////
				     
			        rst3.close();
			        ps3.close();
			        
		 		  } ///////////////////////Month File Loop End/////////////////////
			        	 lacs=(salval/100000);
			        	 
                    	 if (rs==1)
		        		 rfb.setVal1(k,salval);
                    	 else 
                    	 rfb.setVal1(k,lacs);
                         gval[k]=gval[k]+salval;                    	 
  		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5+ wtxt);
  		        	     rfb.setLupdate(txt6);
		        	     k++;
		        	     
			     }  ///////////////////////HQ Master Loop End////////////////////
	            		
	                    rfb.setNm1(k,"TOTAL");
	                    if (rs==1)
		        		rfb.setVal1(k,salval);
                    	else 
                    	rfb.setVal1(k,lacs);	            		
            		    data.add(rfb);
	            
			 } //////////////////Group Master Loop End//////////////////
			
			mrec.close();
			ms1.close();
			rst2.close();
			ps2.close();
			rst1.close();
			ps1.close();
			
			rfb = new Repo2FormBean();
   			rfb.setMname("GRAND TOTAL :");
   			int z=0;
   			salval=0;
   			lacs=0.00f;
   			
   				for (z=0; z<t-1;z++)
   				{
   				  lacs=(gval[z]/100000);	
   				  if (rs==1)	
   				  {
   				  rfb.setVal1(z,gval[z]);
   				  salval=salval+gval[z];
   				  }
   				  else
   				  {
   				  rfb.setVal1(z,lacs);   					  
   				  salval=salval+lacs;
   				  }
   				}
 				  rfb.setVal1(z,salval);
   
            data.add(rfb); 				 
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2DAO.getAllGHQ " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

	
//////////////////////////////////////////////////HQ End here///////////////////////////////////////////////
	
	public List getAllGRegion(Connection con,int code,int smon,int emon,int rs, int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		 
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
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
//            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;   
            String query1=null;
            String query3=null;            
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            String wtxt="";
            double lacs=0.00;
            double salval=0.00;
            double gsalval=0.00;
            double gval[];            
            
            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
  //      	tblnm1=tp+"_group_mkt08";
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();
   	        
           	txt3="VALUE WISE /";
            
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT SALE FROM  ";
            if(saletp==3)
            	txt4="NET SALE FOR FROM  ";
            
                txt2="GROUP WISE /";
                txt1="REGION WISE /";
            
            if (rs==2)
           	   wtxt=" - (IN LACS) " ;
                
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
		    
////////////// Month File Loop Starts to accumulate data/////////////////////////////////
            String month= "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
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
	        ps2.setInt(3,code);
	        ps2.setInt(4,eyear);
	        rst2 = ps2.executeQuery();
		        
//////////////////////////////Group Master ki Query/////////////////////////////////
			if (utype.equals("PMT"))
			{
	         if (sr==null)
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_code";
	         }
	         else
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and gp_name like '"+sr+"%' order by gp_code";
		     }
			}
			
			else
			{
	         if (sr==null)
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  order by gp_code";
	         }
	         else
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?   and gp_name like '"+sr+"%'  order by gp_code";	        	 
		     }
			}
            ps1 = con.prepareStatement(query1); 
			
			if (!utype.equals("PMT"))
			{
			ps1.setInt(1,0);
//			ps1.setInt(2, eyear);
	        }            
	        rst1 = ps1.executeQuery();
			  
			while (rst1.next())  /////// Group Master Loop Starts //////////////////
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
                rfb.setQty2(t);
	            int k=0;  

                rst2.beforeFirst();
	            while (rst2.next()) /////// HQ Loop Starts ////////////////////////////
				{
		        	rfb.setNm1(k,rst2.getString(2)); 
                    salval=0.00;
                    lacs=0.00;
                    mrec.beforeFirst();
		 			while (mrec.next())  /////////////// Month Loop Starts //////////
		 			{	 
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast())
		 			      txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			   
		 	            	uvnm="val"+mrec.getString(3);
///////////////////////////////////////////HQ Detail///////////////////////////////////////////		        	 
            if (utype.equals("PMT"))
   			{
   			query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where tr_cd=? "+
            " and grp_cd=?  and depo_code=? and mkt_year=? group by tr_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd"; 
   			}
   			else
   			{
   			query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where tr_cd=? "+
            " and grp_cd=?  and depo_code=? and mkt_year=? group by tr_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd";
   			}
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,rst2.getInt(1));
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,depo_code);
	        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())  ////////// HQ Detail Starts ///////////////////////
			        {
			         if (saletp==1)
			        		 salval = salval+rst3.getDouble(1);
			        	 
			         if (saletp==2)
			        		 salval = salval +(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6));

			         if (saletp==3)
			        		 salval = salval+rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6));
			         	
			        }   //////// HQ Detail Ends //////////////////////////////////   
				     
		 		  }///////Month Loop End Here  /////////////////////// 

                         lacs=(salval/100000);
			        	 
                    	 if (rs==1)
		        		 rfb.setVal1(k,(float) salval);
                    	 else 
                    	 rfb.setVal1(k, (float)lacs);
 			         	 gval[k]=gval[k]+salval;                    	 
  		        	     rfb.setNm3(txt1+txt2+txt3+txt4+txt5+ wtxt);
  		        	     rfb.setLupdate(txt6);
		        	     k++;		        	     
				         gsalval=gsalval+salval;

				  }///////////////////HQ Loop End Here ////////////////////////
	            
	        	 rfb.setNm1(k,"TOTAL"); 
                 lacs=(gsalval/100000);
	        	 
	           	 if (rs==1)
	       		 rfb.setVal1(k,(float)gsalval);
	           	 else 
	           	 rfb.setVal1(k,(float) lacs);
	           	 
	   			 gsalval=0;
	             data.add(rfb); 				
	             
	        }///////////////////////// Group Master Loop Ends ////////////////////////////
		    rfb = new Repo2FormBean();
   			rfb.setMname("GRAND TOTAL :");
   			int z=0;
   			salval=0.00;
   			lacs=0.00;
   				for (z=0; z<t-1;z++)
   				{
				  lacs=(gval[z]/100000);	
   				  if (rs==1)	
   				  {
   				  rfb.setVal1(z,(float) gval[z]);
   				  salval=salval+gval[z];
   				  }
   				  else
   				  {
   				  rfb.setVal1(z,(float) lacs);   					  
   				  salval=salval+lacs;
   				  }   					 
   				}
					rfb.setVal1(z,(float) salval);
                    data.add(rfb); 				 
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2aDAO.getAllGRegion " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
	         } 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}



////////////////////////////////////////////////////////////////////////////////////////////////////////////s	
	public List getAllGArea(Connection con,int code,int smon,int emon, int rs ,int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		 
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
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
    //        String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;   
            String query1=null;
            String query3=null;  
            
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            String wtxt="";
            float salval=0f;
            float lacs=0f;
            float gsalval=0f;
            float gval[];

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
      //  	tblnm1=tp+"_group_mkt08";
   	        tblnm2=(tp+"_region_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();
            
          	txt3="VALUE WISE /";
            
            if(saletp==1)
            	txt4="GROSS SALE FROM ";
            if(saletp==2)
            	txt4="CREDIT SALE FROM ";
            if(saletp==3)
            	txt4="NET SALE FROM ";
            
                txt2="GROUP WISE /";
                txt1="AREA WISE /";
            
                if (rs==2)
                	   wtxt=" - (IN LACS) " ;
                
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
			    gval = new float[t];
                
			trec.close();
            ts1.close();
///////////////////////////////Region Master Query////////////////////////////////////////            
            String query2 = "Select reg_code,name from "+tblnm2+" where depo_code=? and typ=? and area_code=? and mkt_year=? order by reg_code";
	        ps2 = con.prepareStatement(query2); 
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp);
	        ps2.setInt(3,code);
	        ps2.setInt(4,eyear);
	        rst2 = ps2.executeQuery();
	        
///////////////////////// Month File Loop Starts to accumulate data/////////////////////////////
            String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
///////////////////////////////Group Master Query////////////////////////////////////////            
			if (utype.equals("PMT"))
			{
	         if (sr==null)
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_code";
	         }
	         else
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and gp_name like '"+sr+"%' order by gp_code";
		     }
			}
			
			else
			{
	         if (sr==null)
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  order by gp_code";
	         }
	         else
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  and gp_name like '"+sr+"%' order by gp_code";	        	 
		     }
			}
            ps1 = con.prepareStatement(query1); 
			
			
			if (!utype.equals("PMT"))
			{
			ps1.setInt(1,0);
//			ps1.setInt(2, eyear);
			}            
			rst1 = ps1.executeQuery();
			
			while (rst1.next())  ////////////////////////Group Master Loop Start//////////////////// 
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
                rfb.setQty2(t);
	            int k=0;  
 		        
	            rst2.beforeFirst();
	            while (rst2.next())  ////////////////////////Region Master Loop Start////////////////////
				{
		        	rfb.setNm1(k,rst2.getString(2)); 
                    salval=0f;
                    lacs=0.0f;
                    
                    mrec.beforeFirst();
		 			while (mrec.next())	////////////////////////Month Loop Start////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		        	 
///////////////////////////////////HQ Deatil Query////////////////////////////////////		 	            	
   			if (utype.equals("PMT"))
			{
          	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where rg_cd=? "+
            " and grp_cd=? and depo_code=? and mkt_year=? group by rg_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd"; 
			}
			else
			{
           	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+" where rg_cd=? "+
            " and grp_cd=? and depo_code=? and mkt_year=? group by rg_cd order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd"; 
			}
            	
		 	            	
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,rst2.getInt(1));
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,depo_code);
	        ps3.setInt(4,mrec.getInt(4));
	        rst3 = ps3.executeQuery();
	        
			        if(rst3.next())     ////////////////////////HQ Detail Start////////////////////
			        {
			         if (saletp==1)
			        		 salval = salval+rst3.getFloat(1);
			        	 
			         if (saletp==2)
			        		 salval = salval +(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));

			         if (saletp==3)
			        		 salval = salval+rst3.getFloat(1)-(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));


			         	
			        }  ////////////////////////HQ Detail End ////////////////////    
				     
			        rst3.close();
			        ps3.close();
			        
		 		  }//////////////////////// Month Loop End////////////////////
					    	lacs=(salval/100000);
						 	if (rs==1)
						   	    rfb.setVal1(k,salval);
					 		else 
						 	    rfb.setVal1(k,lacs);
				         	    gval[k]=gval[k]+salval;						 	
					 		    rfb.setNm3(txt1+txt2+txt3+txt4+txt5+ wtxt);
						        rfb.setLupdate(txt6);
						        k++;
						        gsalval=gsalval+salval;
						        
			      } ///////////////////////////HQ Loop End//////////////////////////////
	            
				        	 rfb.setNm1(k,"TOTAL"); 
			                 lacs=(gsalval/100000);
				        	 
				           	 if (rs==1)
				       		 rfb.setVal1(k,gsalval);
				           	 else 
				           	 rfb.setVal1(k,lacs);
				           	 
				   			 gsalval=0;
				             data.add(rfb); 			
				             
				} ///////////////////////////Group Master Loop End//////////////////////////////
			
							mrec.close();
							ms1.close();
							rst2.close();
							ps2.close();
							rst1.close();
							ps1.close();
							
///////////////////////////////////////Alll Statement & ResultSet Close///////////////////////////////
							
		    rfb = new Repo2FormBean();
   			rfb.setMname("GRAND TOTAL :");
   			gsalval=0;
   			int z=0;
   			lacs=0.0f;

   				for (z=0; z<t-1;z++)
   				{
  				  lacs=(gval[z]/100000);	
   				  if (rs==1)	
   				  {
   				  rfb.setVal1(z,gval[z]);
   				  gsalval=gsalval+gval[z];
   				  }
   				  else
   				  {
   				  rfb.setVal1(z,lacs);   					  
   				  gsalval=gsalval+lacs;
   				  }   					 
   				}
				  rfb.setVal1(z,gsalval);
				  data.add(rfb); 				 
				
 			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2aDAO.getAllGArea " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List getAllGBranch(Connection con, int smon,int emon, int rs,int saletp,int eyear,int depo_code,String tp,int uid,String utype,String sr) { 
		 
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
		
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
//            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;   
            String query1=null;
            String query3=null;   
            
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            String wtxt="";
            float lacs=0f;
            float salval=0f;
            float gsalval=0f;
            float gval[];

            if (smon>emon)
            	emon=smon;

        	tblnm=(tp+"_hqdetail08").toLowerCase();
  //      	tblnm1=tp+"_group_mkt08";
   	        tblnm2=(tp+"_area_master08").toLowerCase();
   	        tblnm3=(tp+"_groupsales08").toLowerCase();       	    
          	txt3="VALUE WISE /";
            
            if(saletp==1)
            	txt4="GROSS SALE FOR THE MONTH OF ";
            if(saletp==2)
            	txt4="CREDIT SALE FOR THE MONTH OF  ";
            if(saletp==3)
            	txt4="NET SALE FOR THE MONTH OF  ";
            
                txt2="GROUP WISE /";
                txt1="BRANCH WISE /";
                if (rs==2)
             	   wtxt=" - (IN LACS) " ;

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
 	        
///////////////////////////////////////Area Count Query////////////////////////////////////////////
            String terrec = "Select count(*) from "+tblnm2+" where typ=? and depo_code=? and mkt_year=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
			   t = trec.getInt(1)+1;
			   gval = new float[t];

///////////////////////////////////////Area Master Query////////////////////////////////////////////			   
            String query2 = "Select area_cd,area_name from "+tblnm2+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
	        ps2 = con.prepareStatement(query2); 
	        ps2.setInt(1,depo_code);
	        ps2.setString(2,tp);
	        ps2.setInt(3,eyear);
	        rst2 = ps2.executeQuery();
			   
/////////////////////// Month File Loop Starts to accumulate data//////////////////////////////////////
            String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
	        
///////////////////////////////////////Group Master Query////////////////////////////////////////////
			if (utype.equals("PMT"))
			{
	         if (sr==null)
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by gp_code";
	         }
	         else
	         {	
	 	        query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code in " +
		        " (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') and gp_name like '"+sr+"%' order by gp_code";
		     }
			}
			
			else
			{
	         if (sr==null)
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?   order by gp_code";
	         }
	         else
	         {	
	             query1 = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>?  and gp_name like '"+sr+"%'  order by gp_code";	        	 
		     }
			}
			
            ps1 = con.prepareStatement(query1); 
			
			if (!utype.equals("PMT"))
			{
			ps1.setInt(1,0);
//			ps1.setInt(2, eyear);
			} 			
			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ///////////////////////////Group Master Start/////////////////////
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
                rfb.setQty2(t);
	            int k=0;  

	            rst2.beforeFirst();
	            while (rst2.next())
				{
		        	 rfb.setNm1(k,rst2.getString(2)); 
                     salval=0f;
                    
                     mrec.beforeFirst();
		 			 while (mrec.next())
		 			 {	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			    if (mrec.isLast())
			 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	          	uvnm="val"+mrec.getString(3);
//////////////////////////////////////HQ Detail Query////////////////////////////////////////////
			if (utype.equals("PMT"))
			{
        	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+
            " where ar_cd=? and grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd"; 
			}
			else
			{
        	query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+
            " where ar_cd=? and grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code,ar_cd,rg_cd,tr_cd,grp_cd"; 
			}
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,rst2.getInt(1));
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,depo_code);
	        ps3.setInt(4,mrec.getInt(4));
	        rst3 = ps3.executeQuery();
	        
			        if(rst3.next()) /////////////////////// HQ Detail Start //////////////////////////
			        {
			         if (saletp==1)
			        		 salval = salval+rst3.getFloat(1);
			        	 
			         if (saletp==2)
			        		 salval = salval +(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));

			         if (saletp==3)
			        		 salval = salval+rst3.getFloat(1)-(rst3.getFloat(2)+rst3.getFloat(3)+rst3.getFloat(4)+rst3.getFloat(5)+rst3.getFloat(6));


			        } ///////////////////HQ Detail End///////////////////////      
				    
			          rst3.close();
			          ps3.close();
			        
		 		  } /////////////////Month File Loop End/////////////////////
			        	 
			    	lacs=(salval/100000);
				 	if (rs==1)
				   	    rfb.setVal1(k,salval);
			 		else 
				 	    rfb.setVal1(k,lacs);
				 	
		         	    gval[k]=gval[k]+salval;				 	
			 		    rfb.setNm3(txt1+txt2+txt3+txt4+txt5+ wtxt);
				        rfb.setLupdate(txt6);
				        k++;
				        gsalval=gsalval+salval;
			      } ///////////////////////////HQ Loop End//////////////////////////////
	            
			        	 rfb.setNm1(k,"TOTAL"); 
		                 lacs=(gsalval/100000);
			        	 
			           	 if (rs==1)
			       		 rfb.setVal1(k,gsalval);
			           	 else 
			           	 rfb.setVal1(k,lacs);
			           	 
			   			 gsalval=0;
			             data.add(rfb); 		
	             				
			} ///////////////////////////Group Master Loop End//////////////////////////////
			
						mrec.close();
						ms1.close();
						rst2.close();
						ps2.close();
						rst1.close();
						ps1.close();
		
///////////////////////////////////////Alll Statement & ResultSet Close///////////////////////////////
			
				    rfb = new Repo2FormBean();
		   			rfb.setMname("GRAND TOTAL :");
		   			gsalval=0;
		   			int z=0;
		   			lacs=0.0f;
		
		   				for (z=0; z<t-1;z++)
		   				{
		  				  lacs=(gval[z]/100000);	
		   				  if (rs==1)	
		   				  {
		   				  rfb.setVal1(z,gval[z]);
		   				  gsalval=gsalval+gval[z];
		   				  }
		   				  else
		   				  {
		   				  rfb.setVal1(z,lacs);   					  
		   				  gsalval=gsalval+lacs;
		   				  }   					 
		   				}
						  rfb.setVal1(z,gsalval);
						  data.add(rfb); 					
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo2aDAO.getAllGBranch " + e);
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
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}			
	        } 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo2DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
} 