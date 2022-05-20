package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm6DAO {  

	public List getAllHQ(Connection con, int uv,int code,int depo_code,int eyear,String tp,int uid,String utype) { 
		  
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
 	    int k=0;
 	     	    
 	    List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
            String tblnm=null;
            double montar=0.00;
            double amontar=0.00;
            double rate=0.00;
            double ggmontar=0.00;
            double gmontar[]=new double[12];
            double aggmontar=0.00;
            double bggmontar=0.00;
            double tmontar[]=new double[12];
            double cggmontar=0.00;
            String mnth[]=new String[12];
        	tblnm=(tp+"_report").toLowerCase();
   	        
            if (uv==1) 
            {
            	txt3="UNIT WISE";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE";
            }
            
            txt2="   PRODUCT WISE "+txt3+" TARGET ALLOCATION FOR THE YEAR "; 
         
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
                

    		
///////////////////// Month File Loop ki query/////////////////////  
			String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,eyear);
 			mrec = ms1.executeQuery();
 			k=0;
            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
 			{	
	           txt4=mrec.getString(5);
	           mnth[k]=mrec.getString(3);
	           k++;
	        }  ///////////////////////Monthfile Loop End here//////////////////////
            ms1.close();
 			mrec.close();
    		
///////////////////// Group master ki query/////////////////////  
		            ggmontar=0.00;
		            aggmontar=0.00;    	
		            bggmontar=0.00;
		          
//////////////////////////// Product master ki query/////////////////////////////            
			if (utype.equals("PMT"))
			{
				query1="select ter_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
				" sum(tq12) decmq,sum(tq1) janq, "+
				" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
				" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
				" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
				" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
				" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
				" where mkt_year=? and depo_code=? and tr_cd=?  "+
				" and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+
				" group by mcode order by mcode ";
			}
			else
			{
				query1="select ter_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
				" sum(tq12) decmq,sum(tq1) janq, "+
				" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
				" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
				" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
				" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
				" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
				" where mkt_year=? and depo_code=? and tr_cd=?  "+
				" group by mcode order by mcode ";
			}
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,eyear);
			ps1.setInt(2,depo_code);
			ps1.setInt(3,code);
			rst1= ps1.executeQuery();

    	    boolean hprint=false;
    	    boolean first=true;
    	    int gr=0;
    	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;
					if (first)
					{
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
		                txt1="H.Q. WISE -> "+rst1.getString(1);
						first=false;
					}
					if (gr!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
						for (int i=0; i<12; i++)
						{ 
								if(gmontar[i]>0)
			 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
			 					
			 					if(gmontar[i]<0)
			 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
								gmontar[i]=0.00;
						}
						rfb.setQty3((int)(bggmontar+.50));	
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
						data.add(rfb);
			            ggmontar=0.00;
			            aggmontar=0.00;    	
			            bggmontar=0.00;
					}
					

		 			montar=0.00;
		         	ggmontar=0.00;
		         	amontar=0.00;
		            aggmontar=0.00;
		            k=0;
		            
//////////////////////// Target master ki query///////////////////////////////
		            
		            if(hprint)
		            {
						rfb = new MktFormBean();
						rfb.setMcode(rst1.getInt(4));
						rfb.setMname(rst1.getString(5));
						rfb.setPack(rst1.getString(6));
						rfb.setUv(uv);
				        rfb.setDval1(rate);
			
						for (int i=0; i<12; i++)
						{ 
				            ggmontar = rst1.getDouble(i+19);
				        	montar = rst1.getDouble(i+7);
//				            rate = rst3.getDouble(3);
					     if (uv==1)			        
					        {
					    		if(montar>0)
			 					  rfb.setQty0(i, (int)(montar+.50));
			 					
			 					if(montar<0)
			 					  rfb.setQty0(i, (int)(((montar*-1)+.50)*-1));
			 		        }
					     if (uv==2)
					        {
					        rfb.setQty0(i, (int)(ggmontar+.50));
					        
					        }
				 
					 		gmontar[i] = gmontar[i]+ggmontar;
					        tmontar[i] = tmontar[i]+ggmontar;
					        amontar = amontar+montar;
					        aggmontar = aggmontar+ggmontar;
					        bggmontar = bggmontar+ggmontar;
					        cggmontar = cggmontar+ggmontar;
	//				        k++;
						    rfb.setNm1(i, mnth[i]);
						}
				    	
	
			             rfb.setColor(1);
	 	 				 rfb.setNm3(txt1+txt2+" "+txt4);
	 	 				 rfb.setQty2((int)(amontar+.50));
	 	 				 rfb.setQty3((int)(aggmontar+.50));
					     rfb.setLupdate(txt6);
	 	 				 
		                 data.add(rfb);
		            }

//			  }///////////////////// Product Master Loop End here////////////////////////// 
				
			            
			}///////////////////// Group Master Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
					for (int i=0; i<12; i++)
					{ 
							if(gmontar[i]>0)
		 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
		 					
		 					if(gmontar[i]<0)
		 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
							gmontar[i]=0.00;
					}
					rfb.setQty3((int)(bggmontar+.50));	
					data.add(rfb);
		            ggmontar=0.00;
		            aggmontar=0.00;    	
		            bggmontar=0.00;

		            ps1.close();
					rst1.close();

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
		             				
		             for (int i=0; i<12; i++)
						{ 
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
						}		             
		                 rfb.setQty3((int)(cggmontar+.50));
		                 data.add(rfb);				
				
		} catch (Exception e) {
			System.out.println("========Exception in SQLForm6DAO.getForm6 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
			     if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm6DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////////////////Headquater coding Close here/////////////////////////////////////
	
/////////////////////////////////////////Region coding Start here/////////////////////////////////////	

	public List getMRegion(Connection con, int uv,int code,int depo_code,int eyear,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
 	    int k=0;
 	     	    
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
            double rate=0.00;
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double gmontar[]=new double[12];
            double aggmontar=0.00;
            double bggmontar=0.00;
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double cggmontar=0.00;
///////////////////////////////////////////////
            String mnth[]=new String[12];
        	tblnm=(tp+"_report").toLowerCase();
   	        
            if (uv==1) 
            {
            	txt3="UNIT WISE";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE";
            }
            
            txt2="   PRODUCT WISE "+txt3+" TARGET ALLOCATION FOR THE YEAR "; 
         
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
    		
///////////////////// Month File Loop ki query/////////////////////  
			String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,eyear);
 			mrec = ms1.executeQuery();
 			k=0;
            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
 			{	
	           txt4=mrec.getString(5);
	           mnth[k]=mrec.getString(3);
	           k++;
	        }  ///////////////////////Monthfile Loop End here//////////////////////
 			mrec.close();
            ms1.close();
    		
			
///////////////////// Group master ki query/////////////////////
	            ggmontar=0.00;
	            aggmontar=0.00;    	
	            bggmontar=0.00;
		          
//////////////////////////// Product master ki query/////////////////////////////
				if (utype.equals("PMT"))
				{
					query1="select reg_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
					" sum(tq12) decmq,sum(tq1) janq, "+
					" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
					" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
					" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
					" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
					" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
					" where mkt_year=? and depo_code=? and rg_cd=?  "+
					" and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+
					" group by mcode order by mcode ";
				}
				else
				{
					query1="select reg_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
					" sum(tq12) decmq,sum(tq1) janq, "+
					" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
					" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
					" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
					" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
					" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
					" where mkt_year=? and depo_code=? and rg_cd=?  "+
					" group by mcode order by mcode ";
				}
				ps1 = con.prepareStatement(query1); 
				ps1.setInt(1,eyear);
				ps1.setInt(2,depo_code);
				ps1.setInt(3,code);
				rst1= ps1.executeQuery();

	    	    boolean hprint=false;
	    	    boolean first=true;
	    	    int gr=0;
	    	    String grnm=null;

			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;
					if (first)
					{
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
		                txt1="REGION WISE -> "+rst1.getString(1);
						first=false;
					}
					
					if (gr!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
						for (int i=0; i<12; i++)
						{ 
								if(gmontar[i]>0)
			 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
			 					
			 					if(gmontar[i]<0)
			 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
								gmontar[i]=0.00;
						}
						rfb.setQty3((int)(bggmontar+.50));	
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
						data.add(rfb);
			            ggmontar=0.00;
			            aggmontar=0.00;    	
			            bggmontar=0.00;
					}
					
////////////// Month File Loop Starts to accumulate data///////////////////////
 			montar=0.00;
         	ggmontar=0.00;
         	amontar=0.00;
            aggmontar=0.00;
            k=0;
		            
            	if(hprint)
            	{
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
					for (int i=0; i<12; i++)
					{ 
			            ggmontar = rst1.getDouble(i+19);
			        	montar = rst1.getDouble(i+7);
//			            rate = rst3.getDouble(3);
					     if (uv==1)			        
					        {
					    		if(montar>0)
			 					  rfb.setQty0(i, (int)(montar+.50));
			 					
			 					if(montar<0)
			 					  rfb.setQty0(i, (int)(((montar*-1)+.50)*-1));
			 		        }
					     if (uv==2)
					        {
					        rfb.setQty0(i, (int)(ggmontar+.50));
					        
					        }
			 
				        rfb.setDval1(rate);
				 		gmontar[i] = gmontar[i]+ggmontar;
				        tmontar[i] = tmontar[i]+ggmontar;
				        amontar = amontar+montar;
				        aggmontar = aggmontar+ggmontar;
				        bggmontar = bggmontar+ggmontar;
				        cggmontar = cggmontar+ggmontar;
				        rfb.setLupdate(txt6);
				        //k++;
					    rfb.setNm1(i, mnth[i]);
					}
			    	

		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
 	 				 rfb.setQty2((int)(amontar+.50));
 	 				 rfb.setQty3((int)(aggmontar+.50));
 	 				 
	                 data.add(rfb);
            	}

			  }///////////////////// Product Master Loop End here////////////////////////// 
				
				 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
				for (int i=0; i<12; i++)
				{ 
						if(gmontar[i]>0)
	 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
	 					
	 					if(gmontar[i]<0)
	 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
						gmontar[i]=0.00;
				}
				 rfb.setQty3((int)(bggmontar+.50));	
		            ggmontar=0.00;
		            aggmontar=0.00;    	
		            bggmontar=0.00;
				
				data.add(rfb);
				rst1.close();
				ps1.close();

///////////////////////////// grand total ke liye //////////////////////////
			            
//			}///////////////////// Group Master Loop End here////////////////////////// 
	
///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
		             				
		             for (int i=0; i<12; i++)
						{ 
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
						}		             
		                 rfb.setQty3((int)(cggmontar+.50));
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm6DAO.getForm6 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
			     if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm6DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Region coding Close here/////////////////////////////////////

	
/////////////////////////////////////////Area coding Start here/////////////////////////////////////	

	public List getMArea(Connection con, int uv,int code,int depo_code,int eyear,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;

        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
 	    int k=0;
 	     	    
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
            double rate=0.00;
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double gmontar[]=new double[12];
            double aggmontar=0.00;
            double bggmontar=0.00;
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double cggmontar=0.00;
///////////////////////////////////////////////
            String mnth[]=new String[12];
        	tblnm=(tp+"_report").toLowerCase();
   	        
            if (uv==1) 
            {
            	txt3="UNIT WISE";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE";
            }
            
            txt2="   PRODUCT WISE "+txt3+" TARGET ALLOCATION FOR THE YEAR "; 
         
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
		
///////////////////// Month File Loop ki query/////////////////////  
		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
			ms1.setInt(1,eyear);
			mrec = ms1.executeQuery();
			k=0;
	        while (mrec.next())/////////////////Monthfile Loop Start///////////////////
			{	
	           txt4=mrec.getString(5);
	           mnth[k]=mrec.getString(3);
	           k++;
			}  ///////////////////////Monthfile Loop End here//////////////////////
        	ms1.close();
			mrec.close();
		
	
///////////////////// Group master ki query/////////////////////            
            ggmontar=0.00;
            aggmontar=0.00;    	
            bggmontar=0.00;
		          
//////////////////////////// Product master ki query/////////////////////////////            
			if (utype.equals("PMT"))
			{
				query1="select area_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
				" sum(tq12) decmq,sum(tq1) janq, "+
				" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
				" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
				" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
				" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
				" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
				" where mkt_year=? and depo_code=? and ar_cd=?  "+
				" and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+
				" group by mcode order by mcode ";
			}
			else
			{
				query1="select area_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
				" sum(tq12) decmq,sum(tq1) janq, "+
				" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
				" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
				" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
				" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
				" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
				" where mkt_year=? and depo_code=? and ar_cd=?  "+
				" group by mcode order by mcode ";
			}
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,eyear);
			ps1.setInt(2,depo_code);
			ps1.setInt(3,code);
			rst1= ps1.executeQuery();

    	    boolean hprint=false;
    	    boolean first=true;
    	    int gr=0;
    	    String grnm=null;
		while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
		{
			hprint=true;
			if (first)
			{
				gr=rst1.getInt(2);
				grnm=rst1.getString(3);
                txt1="AREA WISE -> "+rst1.getString(1);
				first=false;
			}
			
			if (gr!=rst1.getInt(2))
			{
			 rfb = new MktFormBean();
			 rfb.setMname("VALUE OF "+grnm);
             rfb.setColor(2);
				for (int i=0; i<12; i++)
				{ 
						if(gmontar[i]>0)
	 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
	 					
	 					if(gmontar[i]<0)
	 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
						gmontar[i]=0.00;
				}
				rfb.setQty3((int)(bggmontar+.50));	
				gr=rst1.getInt(2);
				grnm=rst1.getString(3);
				data.add(rfb);
	            ggmontar=0.00;
	            aggmontar=0.00;    	
	            bggmontar=0.00;
			}
					
		
		montar=0.00;
     	ggmontar=0.00;
     	amontar=0.00;
        aggmontar=0.00;
        
        k=0;
		            
				if(hprint)
				{
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
						for (int i=0; i<12; i++)
						{ 
				            ggmontar = rst1.getDouble(i+19);
				        	montar = rst1.getDouble(i+7);
	//			            rate = rst3.getDouble(3);
						     if (uv==1)			        
						        {
						    		if(montar>0)
				 					  rfb.setQty0(i, (int)(montar+.50));
				 					
				 					if(montar<0)
				 					  rfb.setQty0(i, (int)(((montar*-1)+.50)*-1));
				 		        }
						     if (uv==2)
						        {
						        rfb.setQty0(i, (int)(ggmontar+.50));
						        
						        }
				 
						        rfb.setDval1(rate);
						 		gmontar[i] = gmontar[i]+ggmontar;
						        tmontar[i] = tmontar[i]+ggmontar;
						        amontar = amontar+montar;
						        aggmontar = aggmontar+ggmontar;
						        bggmontar = bggmontar+ggmontar;
						        cggmontar = cggmontar+ggmontar;
						        rfb.setLupdate(txt6);
							    rfb.setNm1(i, mnth[i]);
	//					        k++;
				    	
				        }  ///////////////////////End of for Loop End here//////////////////////

		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
 	 				 rfb.setQty2((int)(amontar+.50));
 	 				 rfb.setQty3((int)(aggmontar+.50));
 	 				 
	                 data.add(rfb);
			}

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
						for (int i=0; i<12; i++)
						{ 
								if(gmontar[i]>0)
			 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
			 					
			 					if(gmontar[i]<0)
			 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
								gmontar[i]=0.00;
						}
					rfb.setQty3((int)(bggmontar+.50));	
				
					data.add(rfb);

///////////////////////////// grand total ke liye //////////////////////////
			            
//			}///////////////////// Group Master Loop End here////////////////////////// 

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
		             				
		             for (int i=0; i<12; i++)
						{ 
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
						}		             
		                 rfb.setQty3((int)(cggmontar+.50));
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm6DAO.getForm6 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
			     if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm6DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Area coding Close here/////////////////////////////////////	

/////////////////////////////////////////Branch coding Start here/////////////////////////////////////	

	public List getMBranch(Connection con, int uv,int code,int depo_code,int eyear,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;

 	    int k=0;
 	     	    
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
            double rate=0.00;
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double gmontar[]=new double[12];
            double aggmontar=0.00;
            double bggmontar=0.00;
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double cggmontar=0.00;
///////////////////////////////////////////////
            String mnth[]=new String[12];
        	tblnm=(tp+"_report").toLowerCase();
   	        
            if (uv==1) 
            {
            	txt3="UNIT WISE";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE";
            }
            
            txt2="   PRODUCT WISE "+txt3+" TARGET ALLOCATION FOR THE YEAR "; 
         
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
        		
///////////////////// Month File Loop ki query/////////////////////  
    			String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
    			ms1 = con.prepareStatement(month);
    			ms1.setInt(1,eyear);
    			mrec = ms1.executeQuery();
    			k=0;
    	        while (mrec.next())/////////////////Monthfile Loop Start///////////////////
    			{	
    	           txt4=mrec.getString(5);
    	           mnth[k]=mrec.getString(3);
    	           k++;
    			}  ///////////////////////Monthfile Loop End here//////////////////////
    			mrec.close();
            	ms1.close();
			
///////////////////// Group master ki query/////////////////////            
		            ggmontar=0.00;
		            aggmontar=0.00;    	
		            bggmontar=0.00;
		          
//////////////////////////// Product master ki query/////////////////////////////            
					if (utype.equals("PMT"))
					{
						query1="select br_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
						" sum(tq12) decmq,sum(tq1) janq, "+
						" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
						" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
						" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
						" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
						" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
						" where mkt_year=? and depo_code=? "+
						" and grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+
						" group by mcode order by mcode ";
					}
					else
					{
						query1="select br_name,mgrp,mgrp_name,mcode,pname,pack,sum(tq10) octq, sum(tq11) novq," +
						" sum(tq12) decmq,sum(tq1) janq, "+
						" sum(tq2) febq,sum(tq3) marq, sum(tq4) aprq, sum(tq5) mayq, sum(tq6) junq, "+
						" sum(tq7) julq,sum(tq8) augq, sum(tq9) sepq, "+
						" sum(tt10) oct, sum(tt11) nov, sum(tt12) decm,sum(tt1) jan, "+
						" sum(tt2) feb,sum(tt3) mar, sum(tt4) apr, sum(tt5) may, sum(tt6) jun, "+
						" sum(tt7) jul,sum(tt8) aug, sum(tt9) sep from "+tblnm+
						" where mkt_year=? and depo_code=?  "+
						" group by mcode order by mcode ";
					}
					ps1 = con.prepareStatement(query1); 
					ps1.setInt(1,eyear);
					ps1.setInt(2,depo_code);
					rst1= ps1.executeQuery();

		    	    boolean hprint=false;
		    	    boolean first=true;
		    	    int gr=0;
		    	    String grnm=null;
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;
					if (first)
					{
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
		                txt1="BRANCH WISE -> "+rst1.getString(1);
						first=false;
					}
					
					if (gr!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
						for (int i=0; i<12; i++)
						{ 
								if(gmontar[i]>0)
			 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
			 					
			 					if(gmontar[i]<0)
			 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
								gmontar[i]=0.00;
						}
						rfb.setQty3((int)(bggmontar+.50));	
						gr=rst1.getInt(2);
						grnm=rst1.getString(3);
						data.add(rfb);
			            ggmontar=0.00;
			            aggmontar=0.00;    	
			            bggmontar=0.00;
					}
					
		 			
 			montar=0.00;
         	ggmontar=0.00;
         	amontar=0.00;
            aggmontar=0.00;
            k=0;
            
			
				if(hprint)
				{
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
						for (int i=0; i<12; i++)
						{ 
				            ggmontar = rst1.getDouble(i+19);
				        	montar = rst1.getDouble(i+7);
	//			            rate = rst3.getDouble(3);
						     if (uv==1)			        
						        {
						    		if(montar>0)
				 					  rfb.setQty0(i, (int)(montar+.50));
				 					
				 					if(montar<0)
				 					  rfb.setQty0(i, (int)(((montar*-1)+.50)*-1));
				 		        }
						     if (uv==2)
						        {
						        rfb.setQty0(i, (int)(ggmontar+.50));
						        
						        }
					 
					        rfb.setDval1(rate);
					 		gmontar[i] = gmontar[i]+ggmontar;
					        tmontar[i] = tmontar[i]+ggmontar;
					        amontar = amontar+montar;
					        aggmontar = aggmontar+ggmontar;
					        bggmontar = bggmontar+ggmontar;
					        cggmontar = cggmontar+ggmontar;
						    rfb.setNm1(i, mnth[i]);
					        rfb.setLupdate(txt6);
					        //k++;
						} //End of For Loop ///////////////////////
			    	
		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
 	 				 rfb.setQty2((int)(amontar+.50));
 	 				 rfb.setQty3((int)(aggmontar+.50));
 	 				 
	                 data.add(rfb);
				}

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
				 	 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
						for (int i=0; i<12; i++)
						{ 
								if(gmontar[i]>0)
			 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
			 					
			 					if(gmontar[i]<0)
			 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
								gmontar[i]=0.00;
						}
					 rfb.setQty3((int)(bggmontar+.50));	
					 data.add(rfb);

///////////////////////////// grand total ke liye //////////////////////////
			            
	
///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
		             				
		             for (int i=0; i<12; i++)
						{ 
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
						}		             
		                 rfb.setQty3((int)(cggmontar+.50));
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm6DAO.getForm6 " + e); 
		}
		  finally 
			{
			  try 
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
			     if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm6DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Branch coding Close here/////////////////////////////////////				
}