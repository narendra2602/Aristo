package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo1FormBean;
import com.aristo.valueobject.MktFormBean;

public class SQLForm5DAO {

	public List getAllHQ(Connection con, int uv,int code,int tl,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null; 
        ResultSet mrec=null;
 	    int k=0;
 	    String head[] = new String[12];
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
            double monsal=0.00;
            double lstsal=0.00;
            double gglstsal=0.00;

            double ggmontar=0.00;
            double ggmonsal=0.00;
            double gmontar[]=new double[12];   
            double gmonsal[]=new double[12];
            double glstsal[]=new double[12];

            double tmontar[]=new double[12];
            double tmonsal[]=new double[12];
            double tlstsal[]=new double[12];

        	tblnm=(tp+"_report").toLowerCase();
            
            if (uv==1) 
            {
            	txt3="UNIT WISE ";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE ";
            }
                txt2="   PRODUCT WISE "+txt3+" TARGET/SALES TREND FOR THE YEAR "; 
                
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
        		

//////////////Month File Loop Starts to accumulate data///////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
		ms1 = con.prepareStatement(month);
		ms1.setInt(1,eyear);
		mrec = ms1.executeQuery();
		k=0;
        while (mrec.next())/////////////////Monthfile Loop Start///////////////////
		{	
	           txt4=mrec.getString(5); 
	           head[k] = mrec.getString(3);
	           k++;
        }  ///////////////////////Monthfile Loop End here//////////////////////
        ms1.close();
		mrec.close();
		
///////////////////// Group master ki query/////////////////////   
            ggmontar=0.00;
            ggmonsal=0.00;
                			
//////////////////////////// Product master ki query/////////////////////////////
		if (utype.equals("PMT"))
		{
//        query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and pd_group=? and mkt_year=? group by mcode order by mcode";		
        query1= "select tr_cd,ter_name,mgrp,grp_name,mcode,pname,pack, "+
        " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
        " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
        " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
        " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
        " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
        " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
        " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
        " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
        " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
        " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
        " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
        " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
        " from "+tblnm+" where mkt_year = ? and depo_CODE =? and tr_cd=?  "+
        " and grp_cd in  "+
        " (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+ 
        " group by tr_cd,mcode";
        }
		else
		{
//		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";			
        query1= "select tr_cd,ter_name,mgrp,grp_name,mcode,pname,pack, "+
        " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
        " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
        " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
        " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
        " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
        " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
        " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
        " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
        " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
        " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
        " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
        " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
        " from "+tblnm+" where mkt_year = ? and depo_CODE =? and tr_cd=?  "+
        " group by tr_cd,mcode";
		}
		ps1 = con.prepareStatement(query1); 
		ps1.setInt(1,eyear);
		ps1.setInt(2,depo_code);
		ps1.setInt(3,code);
		rst1 = ps1.executeQuery();
		
	   // boolean hprint=false;
	    boolean first=true;
	    int gr=0;
	    String grnm=null;

		while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
		{
			//hprint=true;
			if (first)
			{
				gr=rst1.getInt(3);
				grnm=rst1.getString(4);
	            txt1="H.Q. WISE-> "+rst1.getString(2);
				first=false;
			}
			
			if (gr!=rst1.getInt(3))
			{
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
					for (int i=0; i<12; i++)
					{ 
					
						if (tl==1) 
						{
							if(gmontar[i]>0)
		 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
		 					
		 					if(gmontar[i]<0)
		 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
							
							rfb.setQty1(i, (int)(gmonsal[i]+.50));
						}// end of if(t1==1)
						
						if (tl==2)
						{
							if(glstsal[i]>0)
							   rfb.setQty1(i, (int)(glstsal[i]+.50));
						
							if(glstsal[i]<0)
								   rfb.setQty1(i, (int)(((glstsal[i]*-1)+.50)*-1));
						}// end of if(t1==2)

						if (tl==3) 
						{
							if(glstsal[i]>0)
							   rfb.setQty0(i, (int)(glstsal[i]+.50));
							
							if(glstsal[i]<0)
							   rfb.setQty0(i, (int)(((glstsal[i]*-1)+.50)*-1));
							
							if(gmonsal[i]>0)
							   rfb.setQty1(i, (int)(gmonsal[i]+.50));
							
							if(gmonsal[i]<0)
							   rfb.setQty1(i, (int)(((gmonsal[i]*-1)+.50)*-1));
						} // end of if(t1==3)
		
						
							gmontar[i]=0.00;
					        gmonsal[i]=0.00;
					        glstsal[i]=0.00;
					 } // end of for loop
							gr=rst1.getInt(3);
							grnm=rst1.getString(4);
				            ggmontar=0.00;
				            ggmonsal=0.00;

			 				data.add(rfb);
			 } // end of group (if gr!=rst1.getInt(2))
			
			rfb = new MktFormBean();
			rfb.setMcode(rst1.getInt(5));
			rfb.setMname(rst1.getString(6));
			rfb.setPack(rst1.getString(7));
			rfb.setUv(tl);
			
			montar=0.00;
			monsal=0.00;
	        lstsal=0.00;
			ggmontar=0.00;
	        ggmonsal=0.00;
	        gglstsal=0.00;
	        k=0;
        
//////////////////////// Target master ki query///////////////////////////////
	 			     
            for (k=0; k<12;k++)
            {
			            ggmontar = rst1.getDouble(k+k+32);
			            ggmonsal = rst1.getDouble(k+k+33);
//			            gglstsal = rst1.getDouble(3);
			        	montar = rst1.getInt(k+k+8);
			            monsal = rst1.getInt(k+k+9);
//			            lstsal = rst1.getDouble(6);
				    	
			        	
					 if (tl==1)
					   {
					     if (uv==1)			        
					        {
					    		if(montar>0)
			 						  rfb.setQty0(k, (int)(montar+.50));
			 					
			 					if(montar<0)
			 						  rfb.setQty0(k, (int)(((montar*-1)+.50)*-1));
			 						
		                	    rfb.setQty1(k, (int)monsal);
		                	 
					        }
					     if (uv==2)
					        {
					        rfb.setQty0(k, (int)(ggmontar+.50));
					        rfb.setQty1(k, (int)(ggmonsal+.50));
					        }
					   }
					 
						 if (tl==2)
						   {
						     if (uv==1)			        
						        {
						    	rfb.setQty1(k, (int)lstsal);
						    	}
						     if (uv==2)
						        {
						    	 rfb.setQty1(k, (int)gglstsal);
						        }
						   }

							 if (tl==3)
							   {
							     if (uv==1)			        
							        {
				              	 rfb.setQty0(k, (int)lstsal);
				              	 rfb.setQty1(k, (int)monsal);
							        }
							     if (uv==2)
							        {
							        rfb.setQty0(k, (int)gglstsal);
							        rfb.setQty1(k, (int)ggmonsal);
							        }
							   }
			 
			 
					 		gmontar[k] = gmontar[k]+ggmontar;
					        gmonsal[k] = gmonsal[k]+ggmonsal;
					        glstsal[k] = glstsal[k]+gglstsal;
					        tmontar[k] = tmontar[k]+ggmontar;
					        tmonsal[k] = tmonsal[k]+ggmonsal;
					        tlstsal[k] = tlstsal[k]+gglstsal;
					        rfb.setNm1(k, head[k]);
            } // end of for loop
			    
			        rfb.setLupdate(txt6);
			    	
			    	

////////////////////////////Month File Loop End here//////////////////////////////////
		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
	                 data.add(rfb);

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
///////////////////////////// grand total ke liye//////////////////////////
			            

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
				
		             for (int i=0; i<12; i++)
						{ 
							if (tl==1)
							{
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
								
				 				rfb.setQty1(i, (int)(tmonsal[i]+.50));
							}
							if (tl==2)
							{
								if(tlstsal[i]>0)
									   rfb.setQty1(i, (int)(tlstsal[i]+.50));
								
									if(tlstsal[i]<0)
										   rfb.setQty1(i, (int)(((tlstsal[i]*-1)+.50)*-1));
								
							}
							if (tl==3) 
							{
								if(tlstsal[i]>0)
								   rfb.setQty0(i, (int)(tlstsal[i]+.50));
									
								if(tlstsal[i]<0)
								   rfb.setQty0(i, (int)(((tlstsal[i]*-1)+.50)*-1));
									
								if(tmonsal[i]>0)
								   rfb.setQty1(i, (int)(tmonsal[i]+.50));
									
								if(tmonsal[i]<0)
								   rfb.setQty1(i, (int)(((tmonsal[i]*-1)+.50)*-1));								
							}
						}		             
		             
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm5DAO.getForm5 " + e); 
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
				  System.out.println("-------------Exception in SQLForm5DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Headquater coding Close here/////////////////////////////////////
	
	
/////////////////////////////////////////Region coding Start here/////////////////////////////////////	

	public List getMRegion(Connection con, int uv,int code,int tl,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
 	    int k=0;
 	    String head[] = new String[12];

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
            double monsal=0.00;
            double lstsal=0.00;
            double gglstsal=0.00;
            
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            double gmontar[]=new double[12];
            double gmonsal[]=new double[12];
            double glstsal[]=new double[12];
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double tmonsal[]=new double[12];
            double tlstsal[]=new double[12];

///////////////////////////////////////////////
/*        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_region_master08";
   	        tblnm3=tp+"_group_mkt08";
   	        tblnm4=tp+"_groupsales08";   	        
*/
   	        tblnm=(tp+"_report").toLowerCase();
            
            if (uv==1) 
            {
            	txt3="UNIT WISE ";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE ";
            }
                txt2="   PRODUCT WISE "+txt3+" TARGET/SALES TREND FOR THE YEAR "; 
                
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

//////////////  Month File Loop Starts to accumulate data///////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
        		ms1 = con.prepareStatement(month);
        		ms1.setInt(1,eyear);
        		mrec = ms1.executeQuery();
        		k=0;
                while (mrec.next())/////////////////Monthfile Loop Start///////////////////
        		{	
        	           txt4=mrec.getString(5); 
        	           head[k] = mrec.getString(3);
        	           k++;
                }  ///////////////////////Monthfile Loop End here//////////////////////
                ms1.close();
        		mrec.close();
        		
			
///////////////////// Group master ki query/////////////////////            
	            ggmontar=0.00;
	            ggmonsal=0.00;

        		if (utype.equals("PMT"))
        		{
//                query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and pd_group=? and mkt_year=? group by mcode order by mcode";		
                query1= "select rg_cd,reg_name,mgrp,grp_name,mcode,pname,pack, "+
                " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
                " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
                " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
                " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
                " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
                " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
                " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
                " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
                " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
                " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
                " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
                " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
                " from "+tblnm+" where mkt_year = ? and depo_CODE =? and rg_cd=?  "+
                " and grp_cd in  "+
                " (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+ 
                " group by rg_cd,mcode";
                }
        		else
        		{
//        		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";			
                query1= "select rg_cd,reg_name,mgrp,grp_name,mcode,pname,pack, "+
                " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
                " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
                " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
                " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
                " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
                " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
                " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
                " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
                " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
                " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
                " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
                " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
                " from "+tblnm+" where mkt_year = ? and depo_CODE =? and rg_cd=?  "+
                " group by rg_cd,mcode";
        		}
			
        		ps1 = con.prepareStatement(query1); 
        		ps1.setInt(1,eyear);
        		ps1.setInt(2,depo_code);
        		ps1.setInt(3,code);
        		rst1 = ps1.executeQuery();
		                			
//////////////////////////// Product master ki query/////////////////////////////
        	    boolean first=true;
        	    int gr=0;
        	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					if (first)
					{
						gr=rst1.getInt(3);
						grnm=rst1.getString(4);
			            txt1="REGION WISE-> "+rst1.getString(2);
						first=false;
					}

					if (gr!=rst1.getInt(3))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);
							for (int i=0; i<12; i++)
							{ 
							
								if (tl==1) 
								{
									if(gmontar[i]>0)
				 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
				 					
				 					if(gmontar[i]<0)
				 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
									
									rfb.setQty1(i, (int)(gmonsal[i]+.50));
								}// end of if(t1==1)
								
								if (tl==2)
								{
									if(glstsal[i]>0)
									   rfb.setQty1(i, (int)(glstsal[i]+.50));
								
									if(glstsal[i]<0)
										   rfb.setQty1(i, (int)(((glstsal[i]*-1)+.50)*-1));
								}// end of if(t1==2)

								if (tl==3) 
								{
									if(glstsal[i]>0)
									   rfb.setQty0(i, (int)(glstsal[i]+.50));
									
									if(glstsal[i]<0)
									   rfb.setQty0(i, (int)(((glstsal[i]*-1)+.50)*-1));
									
									if(gmonsal[i]>0)
									   rfb.setQty1(i, (int)(gmonsal[i]+.50));
									
									if(gmonsal[i]<0)
									   rfb.setQty1(i, (int)(((gmonsal[i]*-1)+.50)*-1));
								} // end of if(t1==3)
				
								
									gmontar[i]=0.00;
							        gmonsal[i]=0.00;
							        glstsal[i]=0.00;
							 } // end of for loop
									gr=rst1.getInt(3);
									grnm=rst1.getString(4);
						            ggmontar=0.00;
						            ggmonsal=0.00;

					 				data.add(rfb);
					 } // end of group (if gr!=rst1.getInt(2))
					
					
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(5));
					rfb.setMname(rst1.getString(6));
					rfb.setPack(rst1.getString(7));
					rfb.setUv(tl);
					
		 			
		 			montar=0.00;
		            monsal=0.00;
		            lstsal=0.00;
		 			ggmontar=0.00;
		            ggmonsal=0.00;
		            gglstsal=0.00;
		            k=0;
		            

////////////////////////Target master ki query///////////////////////////////
	 			     
		            for (k=0; k<12;k++)
		            {
					            
					            ggmontar = rst1.getDouble(k+k+32);
					            ggmonsal = rst1.getDouble(k+k+33);
					        	montar = rst1.getInt(k+k+8);
					            monsal = rst1.getInt(k+k+9);
					            
					            
					            
			        	
							 if (tl==1)
							   {
							     if (uv==1)			        
							        {
							    		if(montar>0)
					 						  rfb.setQty0(k, (int)(montar+.50));
					 					
					 					if(montar<0)
					 						  rfb.setQty0(k, (int)(((montar*-1)+.50)*-1));
					 						
				                	    rfb.setQty1(k, (int)monsal);
				                	 
							        }
							     if (uv==2)
							        {
							        rfb.setQty0(k, (int)(ggmontar+.50));
							        rfb.setQty1(k, (int)(ggmonsal+.50));
							        }
							   }
							 
							 if (tl==2)
							   {
							     if (uv==1)			        
							        {
							    	rfb.setQty1(k, (int)lstsal);
							    	}
							     if (uv==2)
							        {
							    	 rfb.setQty1(k, (int)gglstsal);
							        }
							   }

							 if (tl==3)
							   {
							     if (uv==1)			        
							        {
				              	 rfb.setQty0(k, (int)lstsal);
				              	 rfb.setQty1(k, (int)monsal);
							        }
							     if (uv==2)
							        {
							        rfb.setQty0(k, (int)gglstsal);
							        rfb.setQty1(k, (int)ggmonsal);
							        }
							   }
							 
							 		gmontar[k] = gmontar[k]+ggmontar;
							        gmonsal[k] = gmonsal[k]+ggmonsal;
							        glstsal[k] = glstsal[k]+gglstsal;
							        tmontar[k] = tmontar[k]+ggmontar;
							        tmonsal[k] = tmonsal[k]+ggmonsal;
							        tlstsal[k] = tlstsal[k]+gglstsal;
							        rfb.setNm1(k, head[k]);
		            } // end of for loop

							        rfb.setLupdate(txt6);
			    	
		            
		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
	                 data.add(rfb);

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
///////////////////////////// grand total ke liye//////////////////////////
			            

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
				
		             for (int i=0; i<12; i++)
						{ 
							if (tl==1)
							{
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
								
				 				rfb.setQty1(i, (int)(tmonsal[i]+.50));
							}
							if (tl==2)
							{
								if(tlstsal[i]>0)
									   rfb.setQty1(i, (int)(tlstsal[i]+.50));
								
									if(tlstsal[i]<0)
									   rfb.setQty1(i, (int)(((tlstsal[i]*-1)+.50)*-1));
								
							}
							if (tl==3) 
							{
								if(tlstsal[i]>0)
								   rfb.setQty0(i, (int)(tlstsal[i]+.50));
									
								if(tlstsal[i]<0)
								   rfb.setQty0(i, (int)(((tlstsal[i]*-1)+.50)*-1));
									
								if(tmonsal[i]>0)
								   rfb.setQty1(i, (int)(tmonsal[i]+.50));
									
								if(tmonsal[i]<0)
								   rfb.setQty1(i, (int)(((tmonsal[i]*-1)+.50)*-1));								
							}
						}		             
		             
 	 				data.add(rfb);				
				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm5DAO.getForm5 " + e); 
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
				  System.out.println("-------------Exception in SQLForm5DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Region coding Close here/////////////////////////////////////
	
/////////////////////////////////////////Area coding Start here/////////////////////////////////////	

	public List getMArea(Connection con, int uv,int code,int tl,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
		
 	    int k=0;
 	    String head[] = new String[12];
 	    
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
            double monsal=0.00;
            double lstsal=0.00;
            double gglstsal=0.00;
            
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            double gmontar[]=new double[12];
            double gmonsal[]=new double[12];
            double glstsal[]=new double[12];
            
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double tmonsal[]=new double[12];
            double tlstsal[]=new double[12];

///////////////////////////////////////////////
/*        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_area_master08";
   	        tblnm3=tp+"_group_mkt08";
   	        tblnm4=tp+"_groupsales08";
*/
   	        tblnm=(tp+"_report").toLowerCase();
        
            if (uv==1) 
            {
            	txt3="UNIT WISE ";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE ";
            }
                txt2="   PRODUCT WISE "+txt3+" TARGET/SALES TREND FOR THE YEAR "; 
                
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
        		
//////////////  Month File Loop Starts to accumulate data///////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
        		ms1 = con.prepareStatement(month);
        		ms1.setInt(1,eyear);
        		mrec = ms1.executeQuery();
        		k=0;
                while (mrec.next())/////////////////Monthfile Loop Start///////////////////
        		{	
        	           txt4=mrec.getString(5); 
        	           head[k] = mrec.getString(3);
        	           k++;
                }  ///////////////////////Monthfile Loop End here//////////////////////
                ms1.close();
        		mrec.close();
			
///////////////////// Group master ki query/////////////////////            
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            
		                			
//////////////////////////// Product master ki query/////////////////////////////
		    		if (utype.equals("PMT"))
		    		{
//		            query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and pd_group=? and mkt_year=? group by mcode order by mcode";		
		            query1= "select ar_cd,area_name,mgrp,grp_name,mcode,pname,pack, "+
		            " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
		            " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
		            " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
		            " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
		            " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
		            " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
		            " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
		            " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
		            " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
		            " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
		            " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
		            " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
		            " from "+tblnm+" where mkt_year = ? and depo_CODE =? and ar_cd=?  "+
		            " and grp_cd in  "+
		            " (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+ 
		            " group by ar_cd,mcode";
		            }
		    		else
		    		{
//		    		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";			
		            query1= "select ar_cd,area_name,mgrp,grp_name,mcode,pname,pack, "+
		            " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
		            " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
		            " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
		            " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
		            " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
		            " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
		            " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
		            " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
		            " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
		            " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
		            " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
		            " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
		            " from "+tblnm+" where mkt_year = ? and depo_CODE =? and ar_cd=?  "+
		            " group by ar_cd,mcode";
		    		}
		    		ps1 = con.prepareStatement(query1); 
		    		ps1.setInt(1,eyear);
		    		ps1.setInt(2,depo_code);
		    		ps1.setInt(3,code);
		    		rst1 = ps1.executeQuery();
		    		
		    	    boolean first=true;
		    	    int gr=0;
		    	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					
					if (first)
					{
						gr=rst1.getInt(3);
						grnm=rst1.getString(4);
			            txt1="AREA WISE-> "+rst1.getString(2);
						first=false;
					}
					
					if (gr!=rst1.getInt(3))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);
							for (int i=0; i<12; i++)
							{ 
							
								if (tl==1) 
								{
									if(gmontar[i]>0)
				 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
				 					
				 					if(gmontar[i]<0)
				 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
									
									rfb.setQty1(i, (int)(gmonsal[i]+.50));
								}// end of if(t1==1)
								
								if (tl==2)
								{
									if(glstsal[i]>0)
									   rfb.setQty1(i, (int)(glstsal[i]+.50));
								
									if(glstsal[i]<0)
										   rfb.setQty1(i, (int)(((glstsal[i]*-1)+.50)*-1));
								}// end of if(t1==2)

								if (tl==3) 
								{
									if(glstsal[i]>0)
									   rfb.setQty0(i, (int)(glstsal[i]+.50));
									
									if(glstsal[i]<0)
									   rfb.setQty0(i, (int)(((glstsal[i]*-1)+.50)*-1));
									
									if(gmonsal[i]>0)
									   rfb.setQty1(i, (int)(gmonsal[i]+.50));
									
									if(gmonsal[i]<0)
									   rfb.setQty1(i, (int)(((gmonsal[i]*-1)+.50)*-1));
								} // end of if(t1==3)
				
								
									gmontar[i]=0.00;
							        gmonsal[i]=0.00;
							        glstsal[i]=0.00;
						} // end of for loop
							
									gr=rst1.getInt(3);
									grnm=rst1.getString(4);
						            ggmontar=0.00;
						            ggmonsal=0.00;

					 				data.add(rfb);
					 } // end of group (if gr!=rst1.getInt(2))
					
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(5));
					rfb.setMname(rst1.getString(6));
					rfb.setPack(rst1.getString(7));
					rfb.setUv(tl);
					
////////////// Month File Loop Starts to accumulate data///////////////////////
		 			montar=0.00;
		            monsal=0.00;
		            lstsal=0.00;
		 			ggmontar=0.00;
		            ggmonsal=0.00;
		            gglstsal=0.00;
		            k=0;
		            
//////////////////////// Target master ki query///////////////////////////////
		            for (k=0; k<12;k++)
		            {
					            ggmontar = rst1.getDouble(k+k+32);
					            ggmonsal = rst1.getDouble(k+k+33);
					        	montar = rst1.getInt(k+k+8);
					            monsal = rst1.getInt(k+k+9);
	 			           
			        	
								 if (tl==1)
								   {
								     if (uv==1)			        
								        {
								    		if(montar>0)
						 						  rfb.setQty0(k, (int)(montar+.50));
						 					
						 					if(montar<0)
						 						  rfb.setQty0(k, (int)(((montar*-1)+.50)*-1));
						 						
					                	    rfb.setQty1(k, (int)monsal);
					                	 
								        }
								     if (uv==2)
								        {
								        rfb.setQty0(k, (int)(ggmontar+.50));
								        rfb.setQty1(k, (int)(ggmonsal+.50));
								        }
								   }
								 
								 if (tl==2)
								   {
								     if (uv==1)			        
								        {
								    	rfb.setQty1(k, (int)lstsal);
								    	}
								     if (uv==2)
								        {
								    	 rfb.setQty1(k, (int)gglstsal);
								        }
								   }

								 if (tl==3)
								   {
								     if (uv==1)			        
								        {
					              	 rfb.setQty0(k, (int)lstsal);
					              	 rfb.setQty1(k, (int)monsal);
								        }
								     if (uv==2)
								        {
								        rfb.setQty0(k, (int)gglstsal);
								        rfb.setQty1(k, (int)ggmonsal);
								        }
								   }
								 
							 		gmontar[k] = gmontar[k]+ggmontar;
							        gmonsal[k] = gmonsal[k]+ggmonsal;
							        glstsal[k] = glstsal[k]+gglstsal;
							        tmontar[k] = tmontar[k]+ggmontar;
							        tmonsal[k] = tmonsal[k]+ggmonsal;
							        tlstsal[k] = tlstsal[k]+gglstsal;
							        rfb.setNm1(k, head[k]);
		            } // end of for loop

							        rfb.setLupdate(txt6);
			    	
		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
	                 data.add(rfb);

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
				
		             for (int i=0; i<12; i++)
						{ 
							if (tl==1)
							{
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
								
				 				rfb.setQty1(i, (int)(tmonsal[i]+.50));
							}
							if (tl==2)
							{
								if(tlstsal[i]>0)
									   rfb.setQty1(i, (int)(tlstsal[i]+.50));
								
									if(tlstsal[i]<0)
									   rfb.setQty1(i, (int)(((tlstsal[i]*-1)+.50)*-1));
								
							}
							if (tl==3) 
							{
								if(tlstsal[i]>0)
								   rfb.setQty0(i, (int)(tlstsal[i]+.50));
									
								if(tlstsal[i]<0)
								   rfb.setQty0(i, (int)(((tlstsal[i]*-1)+.50)*-1));
									
								if(tmonsal[i]>0)
								   rfb.setQty1(i, (int)(tmonsal[i]+.50));
									
								if(tmonsal[i]<0)
								   rfb.setQty1(i, (int)(((tmonsal[i]*-1)+.50)*-1));								
							}
						}		             
		             
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm5DAO.getForm5 " + e); 
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
				  System.out.println("-------------Exception in SQLForm5DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Area coding Close here/////////////////////////////////////
	
	
/////////////////////////////////////////Branch coding Start here/////////////////////////////////////	

	public List getMBranch(Connection con, int uv,int code,int tl,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
 	    int k=0;
 	    String head[] = new String[12];
 	    
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
            double monsal=0.00;
            double lstsal=0.00;
            double gglstsal=0.00;
            
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            double gmontar[]=new double[12];
            double gmonsal[]=new double[12];
            double glstsal[]=new double[12];
            
////////////////Grand Total Ke liye//////////////
            double tmontar[]=new double[12];
            double tmonsal[]=new double[12];
            double tlstsal[]=new double[12];

///////////////////////////////////////////////
/*        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_branch08";
   	        tblnm3=tp+"_group_mkt08";
   	        tblnm4=tp+"_groupsales08";
*/   	        
        	tblnm=(tp+"_report").toLowerCase();
            
            if (uv==1) 
            {
            	txt3="UNIT WISE ";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE ";
            }
                txt2="   PRODUCT WISE "+txt3+" TARGET/SALES TREND FOR THE YEAR "; 
                
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
        		
//////////////  Month File Loop Starts to accumulate data///////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
        		ms1 = con.prepareStatement(month);
        		ms1.setInt(1,eyear);
        		mrec = ms1.executeQuery();
        		k=0;
                while (mrec.next())/////////////////Monthfile Loop Start///////////////////
        		{	
        	           txt4=mrec.getString(5); 
        	           head[k] = mrec.getString(3);
        	           k++;
                }  ///////////////////////Monthfile Loop End here//////////////////////
                ms1.close();
        		mrec.close();
			
///////////////////// Group master ki query/////////////////////            
		            ggmontar=0.00;
		            ggmonsal=0.00;
		                			
//////////////////////////// Product master ki query/////////////////////////////            
		    		if (utype.equals("PMT"))
		    		{
//		            query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and pd_group=? and mkt_year=? group by mcode order by mcode";		
		            query1= "select depo_code,br_name,mgrp,grp_name,mcode,pname,pack, "+
		            " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
		            " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
		            " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
		            " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
		            " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
		            " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
		            " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
		            " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
		            " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
		            " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
		            " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
		            " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
		            " from "+tblnm+" where mkt_year = ? and depo_CODE =?   "+
		            " and grp_cd in  "+
		            " (select gp_code from pmt_group where user_id="+uid+" and status='Y') "+ 
		            " group by depo_code,mcode";
		            }
		    		else
		    		{
//		    		query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";			
		            query1= "select depo_code,br_name,mgrp,grp_name,mcode,pname,pack, "+
		            " sum(tq10) octy, sum(ta10) octs,sum(tq11) novy, sum(ta11) novs, "+  
		            " sum(tq12) decy, sum(ta12) decs, sum(tq1) jany, sum(ta1) jans, "+  
		            " sum(tq2) feby, sum(ta2) febs,sum(tq3) mary, sum(ta3) mars, "+  
		            " sum(tq4) apry, sum(ta4) aprs,sum(tq5) mayy, sum(ta5) mays, "+  
		            " sum(tq6) juny, sum(ta6) juns,sum(tq7) july, sum(ta7) juls, "+  
		            " sum(tq8) augy, sum(ta8) augs,sum(tq9) sepy, sum(ta9) seps, "+  
		            " sum(tt10) octt, sum(ra10) octv,sum(tt11) novt, sum(ra11) novv, "+  
		            " sum(tt12) dect, sum(ra12) decv, sum(tt1) jant, sum(ra1) janv, "+  
		            " sum(tt2) febt, sum(ra2) febv,sum(tt3) mart, sum(ra3) marv, "+  
		            " sum(tt4) aprt, sum(ra4) aprv,sum(tt5) mayt, sum(ra5) mayv, "+  
		            " sum(tt6) junt, sum(ra6) junv,sum(tt7) jult, sum(ra7) julv, "+  
		            " sum(tt8) augt, sum(ra8) augv,sum(tt9) sept, sum(ra9) sepv "+  
		            " from "+tblnm+" where mkt_year = ? and depo_CODE =?   "+
		            " group by depo_code,mcode";
		    		}
		    		ps1 = con.prepareStatement(query1); 
		    		ps1.setInt(1,eyear);
		    		ps1.setInt(2,depo_code);
		    		rst1 = ps1.executeQuery();
			
		    	    boolean first=true;
		    	    int gr=0;
		    	    String grnm=null;
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					if (first)
					{
						gr=rst1.getInt(3);
						grnm=rst1.getString(4);
			            txt1="BRANCH WISE-> "+rst1.getString(2);
						first=false;
					}

					if (gr!=rst1.getInt(3))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);
							for (int i=0; i<12; i++)
							{ 
							
								if (tl==1) 
								{
									if(gmontar[i]>0)
				 					   rfb.setQty0(i, (int)(gmontar[i]+.50));
				 					
				 					if(gmontar[i]<0)
				 					   rfb.setQty0(i, (int)(((gmontar[i]*-1)+.50)*-1));
									
									rfb.setQty1(i, (int)(gmonsal[i]+.50));
								}// end of if(t1==1)
								
								if (tl==2)
								{
									if(glstsal[i]>0)
									   rfb.setQty1(i, (int)(glstsal[i]+.50));
								
									if(glstsal[i]<0)
										   rfb.setQty1(i, (int)(((glstsal[i]*-1)+.50)*-1));
								}// end of if(t1==2)

								if (tl==3) 
								{
									if(glstsal[i]>0)
									   rfb.setQty0(i, (int)(glstsal[i]+.50));
									
									if(glstsal[i]<0)
									   rfb.setQty0(i, (int)(((glstsal[i]*-1)+.50)*-1));
									
									if(gmonsal[i]>0)
									   rfb.setQty1(i, (int)(gmonsal[i]+.50));
									
									if(gmonsal[i]<0)
									   rfb.setQty1(i, (int)(((gmonsal[i]*-1)+.50)*-1));
								} // end of if(t1==3)
				
								
									gmontar[i]=0.00;
							        gmonsal[i]=0.00;
							        glstsal[i]=0.00;
							 } // end of for loop
									gr=rst1.getInt(3);
									grnm=rst1.getString(4);
						            ggmontar=0.00;
						            ggmonsal=0.00;

					 				data.add(rfb);
					 } // end of group (if gr!=rst1.getInt(2))
					
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(5));
					rfb.setMname(rst1.getString(6));
					rfb.setPack(rst1.getString(7));
					rfb.setUv(tl);
					
		 			
		 			montar=0.00;
		            monsal=0.00;
		            lstsal=0.00;
		 			ggmontar=0.00;
		            ggmonsal=0.00;
		            gglstsal=0.00;
		            k=0;
		            
//////////////////////// Target master ki query///////////////////////////////
		            for (k=0; k<12;k++)
		            {
					            ggmontar = rst1.getDouble(k+k+32);
					            ggmonsal = rst1.getDouble(k+k+33);
					        	montar = rst1.getInt(k+k+8);
					            monsal = rst1.getInt(k+k+9);
			        
			        	
							 if (tl==1)
							   {
							     if (uv==1)			        
							        {
							    		if(montar>0)
					 						  rfb.setQty0(k, (int)(montar+.50));
					 					
					 					if(montar<0)
					 						  rfb.setQty0(k, (int)(((montar*-1)+.50)*-1));
					 						
				                	    rfb.setQty1(k, (int)monsal);
				                	 
							        }
							     if (uv==2)
							        {
							        rfb.setQty0(k, (int)(ggmontar+.50));
							        rfb.setQty1(k, (int)(ggmonsal+.50));
							        }
							   }
							 
								 if (tl==2)
								   {
								     if (uv==1)			        
								        {
								    	rfb.setQty1(k, (int)lstsal);
								    	}
								     if (uv==2)
								        {
								    	 rfb.setQty1(k, (int)gglstsal);
								        }
								   }

								 if (tl==3)
								   {
								     if (uv==1)			        
								        {
					              	 rfb.setQty0(k, (int)lstsal);
					              	 rfb.setQty1(k, (int)monsal);
								        }
								     if (uv==2)
								        {
								        rfb.setQty0(k, (int)gglstsal);
								        rfb.setQty1(k, (int)ggmonsal);
								        }
								   }
								 
							 		gmontar[k] = gmontar[k]+ggmontar;
							        gmonsal[k] = gmonsal[k]+ggmonsal;
							        glstsal[k] = glstsal[k]+gglstsal;
							        tmontar[k] = tmontar[k]+ggmontar;
							        tmonsal[k] = tmonsal[k]+ggmonsal;
							        tlstsal[k] = tlstsal[k]+gglstsal;
							        rfb.setNm1(k, head[k]);
		            } // end of for loop
			        rfb.setLupdate(txt6);
			    	
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
	                 data.add(rfb);

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				

///////////////////////////// grand total ke liye//////////////////////////
			            

///////////////////////////// All Statment Close/////////////////////////////
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
				
		             for (int i=0; i<12; i++)
						{ 
							if (tl==1)
							{
								if(tmontar[i]>0)
				 				   rfb.setQty0(i, (int)(tmontar[i]+.50));
				 					
				 				if(tmontar[i]<0)
				 				   rfb.setQty0(i, (int)(((tmontar[i]*-1)+.50)*-1));								
								
				 				rfb.setQty1(i, (int)(tmonsal[i]+.50));
							}
							if (tl==2)
							{
								if(tlstsal[i]>0)
									   rfb.setQty1(i, (int)(tlstsal[i]+.50));
								
									if(tlstsal[i]<0)
									   rfb.setQty1(i, (int)(((tlstsal[i]*-1)+.50)*-1));
								
							}
							if (tl==3) 
							{
								if(tlstsal[i]>0)
								   rfb.setQty0(i, (int)(tlstsal[i]+.50));
									
								if(tlstsal[i]<0)
								   rfb.setQty0(i, (int)(((tlstsal[i]*-1)+.50)*-1));
									
								if(tmonsal[i]>0)
								   rfb.setQty1(i, (int)(tmonsal[i]+.50));
									
								if(tmonsal[i]<0)
								   rfb.setQty1(i, (int)(((tmonsal[i]*-1)+.50)*-1));								
							}
						}		             
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			System.out.println("========Exception in SQLForm5DAO.getForm5 " + e); 
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
				  System.out.println("-------------Exception in SQLForm5DAO.Connection.close "+e);
				}
			}
		return data;
	}
/////////////////////////////////////////Branch coding Close here/////////////////////////////////////
	
	////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getNewBranch(Connection con, int uv,int hq_code, int smon, int emon,int eyear,int depo_code,int div_code,int loginid, int utype,int rep_type,int pg) { 
		
		MktFormBean rfb;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		int saletp=3;
		CallableStatement cs=null;
		ResultSet rst1=null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
			String txt1=null;
			String txt2=null;
			String txt3=null; 
			String txt4=null;
			String txt5 =null;
			double salqty=0.00;
			double salval=0.00;
			double gval[];
			double grval[];
			double gsalqty=0.00;
			double gsalval=0.00;
			int nrep[];
			int mcode[];
			String month_name[];
			String procedureWithParameters="{call web_report_net_trend(?,?,?,?,?,?,?,?,?)}";

			//utype=4;
			//loginid=360;

			int doctp=saletp;

			if(depo_code>0 && (utype==2 || utype==3 || utype==5))
				utype=1;



			if (smon>emon)
				emon=smon;


			if (uv==1)
			{
				txt3="UnitWise/";
			}

			if (uv==2)
			{
				txt3="ValueWise/";
			}

			 
			txt1="";

			if(pg==2)
				txt2=" Group Wise/";
			else
				txt2=" Product Wise/";
			
			txt4="Sales Trend";
			if(rep_type==2)
			{
				procedureWithParameters="{call web_report_target_trend(?,?,?,?,?,?,?,?,?)}";
				txt4="Target Trend";
			}
			
			
			int t=13;
			int w=12;

			gval = new double[13]; 
			grval = new double[13]; 
			mcode = new int[12];
			month_name = new String[12];
			nrep = new int[12];


            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            if(hq_code>0)
            	branchname = "Select ter_name from hqmast where mkt_year=? and div_code=? and  depo_code=? and ter_code=?";
            ps1=con.prepareStatement(branchname);
            if(hq_code>0)
            {
            	ps1.setInt(1, eyear);
            	ps1.setInt(2, div_code);
            	ps1.setInt(3, depo_code);
            	ps1.setInt(4, hq_code);
            }
            else
             ps1.setInt(1, depo_code);
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
            	txt1=(hq_code>0?"HQ :-> ":"Branch :-> ")+rs1.getString(1);
            }
            else
            	txt1="All India";
           
            rs1.close();
            ps1.close();

			
			
			String monthrec = "Select mnth_code,mnth_abbr from monthfl where mkt_year=? ";



			System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid+" saletype "+saletp+" doctype "+doctp);

			ps = con.prepareStatement(monthrec);
			ps.setInt(1,eyear);

			rs = ps.executeQuery();
			int i=0;
			while (rs.next())
			{
				mcode[i] = rs.getInt(1);
				month_name[i] = rs.getString(2);
				i++;
			}
			rs.close();
			ps.close();
			/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;


			cs  = con.prepareCall(procedureWithParameters);
			cs.setInt(1, eyear);
			cs.setInt(2, div_code);
			cs.setInt(3, depo_code);
			cs.setInt(4, smon);
			cs.setInt(5, emon);
			cs.setInt(6, utype);
			cs.setInt(7, loginid);
			cs.setInt(8, pg);
			cs.setInt(9, hq_code);



			rst1 = cs.executeQuery();   
			int k=0;  
			gsalqty=0.00;
			gsalval=0.00;

			boolean hrprint = true;	
			boolean hprint = true;	
			int pcode=0;
			String name=null;
			String pack=null;
    	    int grp=0;
    	    String grnm=null;

			rfb = new MktFormBean();

			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
					grp=rst1.getInt(8);
					grnm=rst1.getString(16);

					pcode=rst1.getInt(7);
					name = rst1.getString(10);
					pack=rst1.getString(11);
					hrprint=false;
				}

				

				
				if (pcode!=rst1.getInt(7))
				{
					rfb.setMcode(pcode);
					rfb.setMname(name);
					rfb.setPack(pack);
					//rfb.setBr(t);
					rfb.setUv(uv);

					for(int q=k;q<w;q++)
					{
						if (hprint)
						{
							rfb.setNm1(k, month_name[q]);
						}
						rfb.setQty1(k,0);
						rfb.setDval0(k,0.00);
						k++;

					}



					if(k==w)
					{	                

						if ((uv==1) || (uv==3))
						{	 
							rfb.setNm1(k, "TOTAL");
							rfb.setQty0(k, (int) gsalqty);
						}		

						if ((uv==2) || (uv==3))
						{	 
							rfb.setNm1(k, "TOTAL");
							rfb.setDval0(k,gsalval);
						}		
						gval[k]=gval[k]+gsalval;
					}
					k=0;
					gsalqty=0;
					gsalval=0.00;
					hprint=false;
					data.add(rfb);
					pcode=rst1.getInt(7);
					name = rst1.getString(10);
					pack=rst1.getString(11);
					rfb = new MktFormBean();

				} //end of if(pcode)

				if (grp!=rst1.getInt(8))
				{
					rfb.setMcode(grp);
					rfb.setMname(grnm);
					rfb.setPack("");
					//rfb.setBr(t);
					rfb.setUv(uv);
					rfb.setColor(3);

					gsalval=0.00;

					for (k=0; k<w;k++)
					{
						if (uv==1)
						{
							rfb.setQty0(k,(int) grval[k]);
						}
						else
						{
							rfb.setDval0(k,grval[k]);
						}
						gsalval+=grval[k];
					}

					rfb.setNm1(k, "TOTAL");
					if (uv==1)
						rfb.setQty0(k,(int) gsalval);
					else
						rfb.setDval0(k,gsalval);

					
					k=0;
					gsalqty=0;
					gsalval=0.00;
					hprint=false;
					data.add(rfb);
					grp=rst1.getInt(8);
					grnm = rst1.getString(16);
					grval = new double[13];
					rfb = new MktFormBean();

				} // end if (grp)

				
				
				for(int q=k;q<w;q++)
				{
					if(mcode[q]==rst1.getInt(5))
						break;
					else
					{

						if (hprint)
						{
							rfb.setNm1(k, month_name[q]);
						}
						
						rfb.setQty1(k,0);
						rfb.setDval0(k,0.00);
						k++;

					}
				}

				if (hprint)
				{
						rfb.setNm1(k, rst1.getString(6));
				}


				salqty=0.00;
				salval=0.00;


				if (saletp==1)
				{	 
					salqty = rst1.getDouble(12);
					salval=rst1.getDouble(15);
				}	 

				if (saletp==2)
				{	 
					salqty = rst1.getDouble(12);;
					salval=rst1.getDouble(15);;
				}	 

				if (saletp==3)
				{	 
					salqty=rst1.getDouble(12);;
					salval=rst1.getDouble(15);;
				}


				salqty=rst1.getDouble(12);;
				salval=rst1.getDouble(15);;


				if (saletp==8)
				{	 
					if(nrep[k]!=0)
					{
						salqty=rst1.getDouble(12)/nrep[k];;
						salval=rst1.getDouble(15)/nrep[k];;
					}
				}



				gval[k] = gval[k]+salval;
				grval[k] = grval[k]+salval;



				if (uv==1)
				{	 
					rfb.setQty0(k,(int) salqty);
					gsalqty=gsalqty+salqty;
				}	 
				if (uv==2)
				{	 
					rfb.setDval0(k,salval);
					gsalval=gsalval+salval;
				} 	 
				if (uv==3)
				{	 
					rfb.setQty0(k,(int) salqty);
					rfb.setDval0(k,salval);
					gsalqty=gsalqty+salqty;
					gsalval=gsalval+salval;
				}	 

				rfb.setNm3(txt1+txt2+txt3+txt4);
				k++;



			} //////////////////////// End of Query Loop///////////////////////

			rfb.setMcode(pcode);
			rfb.setMname(name);
			rfb.setPack(pack);
			rfb.setUv(uv);

			if(k==w)
			{	                
				if ((uv==1) || (uv==3))
				{	 
					rfb.setNm1(k,"TOTAL");
					rfb.setQty0(k,(int) gsalqty);
				}		

				if ((uv==2) || (uv==3))
				{	 
					rfb.setNm1(k,"TOTAL");
					rfb.setDval0(k,gsalval);
				}		
				gval[k]=gval[k]+gsalval;
			}
			data.add(rfb); 	

			rfb = new MktFormBean();

			rfb.setMname(grnm);
			rfb.setColor(2);

			for (int z=0; z<t;z++)
			{
				if (uv==1)
					rfb.setQty0(z,(int) grval[z]);
				else
					rfb.setDval0(z,grval[z]); 
			}
			data.add(rfb); 				


			rfb = new MktFormBean();

			rfb.setMname("Total :");
			rfb.setColor(3);

			for (int z=0; z<t;z++)
			{
				if (uv==1)
					rfb.setQty0(z,(int) gval[z]);
				else
					rfb.setDval0(z,gval[z]); 
			}
			data.add(rfb); 				

			
		} catch (Exception e) {

			System.out.println("========Exception in SQLForm5DAO.getNewBranch " + e);
		}

		finally
		{
			try 
			{
				if(rst1 != null){ rst1.close();}
				if(cs != null){ cs.close();}
				if(rs != null){ rs.close();}
				if(ps != null){ ps.close();}
				if(rs1 != null){ rs1.close();}
				if(ps1 != null){ ps1.close();}
				if(con != null){con.close();}
			}
			catch (SQLException e) 
			{
				System.out.println("-------Exception in SQLForm5DAO.Connection.close "+e);
			}
		}		
		return data; 
	}


	////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getNewBranch7(Connection con, int grp_code, int smon, int emon,int eyear,int depo_code,int div_code,int loginid, int utype,int rep_type) { 
		
		MktFormBean rfb;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int saletp=3;
		CallableStatement cs=null;
		ResultSet rst1=null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
			String txt1=null;
			String txt2=null;
			String txt4=null;
			double salval=0.00;
			double gval[];
			double grval[];
			double gsalval=0.00;
			int mcode[];
			String month_name[];
			int trep=0;
			int nrep=0;
			String procedureWithParameters="{call web_report_hq_trend(?,?,?,?,?,?,?,?)}";

			//utype=4;
			//loginid=360;

			int doctp=saletp;

			if(depo_code>0 && (utype==2 || utype==3 || utype==5))
				utype=1;



			if (smon>emon)
				emon=smon;



			 
			txt1=depo_code==0?" Branch Wise ":" HQ Wise ";

			txt4="Sales Trend";
			
			if(rep_type==2)
			{
				txt4="Target Trend";
				procedureWithParameters="{call web_report_hq_target_trend(?,?,?,?,?,?,?,?)}";
			}
			
			int t=13;
			int w=12;

			gval = new double[13]; 
			grval = new double[13]; 
			mcode = new int[12];
			month_name = new String[12];


			String monthrec = "Select mnth_code,mnth_abbr from monthfl where mkt_year=? ";



			System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid+" saletype "+saletp+" doctype "+doctp);

			ps = con.prepareStatement(monthrec);
			ps.setInt(1,eyear);

			rs = ps.executeQuery();
			int i=0;
			while (rs.next())
			{
				mcode[i] = rs.getInt(1);
				month_name[i] = rs.getString(2);
				i++;
			}
			rs.close();
			ps.close();
			/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;


			cs  = con.prepareCall(procedureWithParameters);
			cs.setInt(1, eyear);
			cs.setInt(2, div_code);
			cs.setInt(3, depo_code);
			cs.setInt(4, smon);
			cs.setInt(5, emon);
			cs.setInt(6, utype);
			cs.setInt(7, loginid);
			cs.setInt(8, grp_code);



			rst1 = cs.executeQuery();   
			int k=0;  
			gsalval=0.00;

			boolean hrprint = true;	
			boolean hprint = true;	
			int tcode=0;
			String name=null;
    	    String grnm=null; 

			rfb = new MktFormBean();

			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
					txt2="Group -> "+rst1.getString(10);
					grnm=rst1.getString(10);
					tcode=rst1.getInt(6);
					name = rst1.getString(7);
					hrprint=false;
				}

				

				
				if (tcode!=rst1.getInt(6))
				{
					rfb.setMname(name);
					//rfb.setBr(t);
					rfb.setNo_of_mr(nrep);

					for(int q=k;q<w;q++)
					{
						if (hprint)
						{
							rfb.setNm1(k, month_name[q]);
						}
						rfb.setQty1(k,0);
						rfb.setDval0(k,0.00);
						k++;

					}



					if(k==w)
					{	                

						rfb.setNm1(k, "TOTAL");
						rfb.setDval0(k,gsalval);
						gval[k]=gval[k]+gsalval;
					}
					k=0;
					gsalval=0.00;
					hprint=false;
					nrep=0;
					data.add(rfb);
					tcode=rst1.getInt(6);
					name = rst1.getString(7);
					rfb = new MktFormBean();

				} //end of if(tcode)


				
				
				for(int q=k;q<w;q++)
				{
					if(mcode[q]==rst1.getInt(4))
						break;
					else
					{

						if (hprint)
						{
							rfb.setNm1(k, month_name[q]);
						}
						
						rfb.setQty1(k,0);
						rfb.setDval0(k,0.00);
						k++;

					}
				}

				if (hprint)
				{
						rfb.setNm1(k, rst1.getString(5));
				}


				salval=0.00;
				salval=rst1.getDouble(9);;





				gval[k] = gval[k]+salval;
				grval[k] = grval[k]+salval;



				rfb.setDval0(k,salval);
				gsalval=gsalval+salval;

				rfb.setNm3(txt2+txt1+txt4);
				nrep+=rst1.getInt(8);
				trep+=rst1.getInt(8);
				k++;



			} //////////////////////// End of Query Loop///////////////////////

			rfb.setMname(name);
			rfb.setNo_of_mr(nrep);

			for(int q=k;q<w;q++)
			{
				if (hprint)
				{
					rfb.setNm1(k, month_name[q]);
				}
				rfb.setQty1(k,0);
				rfb.setDval0(k,0.00);
				k++;

			}

			
			if(k==w)
			{	                

				rfb.setNm1(k,"TOTAL");
				rfb.setDval0(k,gsalval);
				gval[k]=gval[k]+gsalval;
			}
			data.add(rfb); 	


			rfb = new MktFormBean();
			rfb.setMname("Total :");
			rfb.setNo_of_mr(trep);

			for (int z=0; z<t;z++)
			{
					rfb.setDval0(z,gval[z]); 
			}
			data.add(rfb); 				

			
		} catch (Exception e) {

			System.out.println("========Exception in SQLForm5DAO.getNewBranch7 " + e);
		}

		finally
		{
			try 
			{
				if(rst1 != null){ rst1.close();}
				if(cs != null){ cs.close();}
				if(rs != null){ rs.close();}
				if(ps != null){ ps.close();}
				if(con != null){con.close();}
			}
			catch (SQLException e) 
			{
				System.out.println("-------Exception in SQLForm5DAO.Connection.close "+e);
			}
		}		
		return data; 
	}
	

	////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getNewBranch10(Connection con, int grp_code, int smon, int emon,int eyear,int depo_code,int div_code,int loginid, int utype,int hq_code,int uv) { 
		
		MktFormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int saletp=3;
		CallableStatement cs=null;
		ResultSet rst1=null;

		PreparedStatement ps1=null;
		ResultSet rs1=null;

		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
			String txt1=null;
			String txt2=null;
			String txt4=null;
			double salval=0.00;
			double gval[];
			double grval[];
			double gsalval=0.00;
			int mcode[];
			String month_name[];
			int trep=0;
			
			double tarval[];
			double lysval[];
			double saleval[];
			double incrval[];
			int nrep[];

			
			String procedureWithParameters="{call web_report_group_product_summary(?,?,?,?,?,?,?,?,?)}";

			//utype=4;
			//loginid=360;

			int doctp=saletp;

			if(depo_code>0 && (utype==2 || utype==3 || utype==5))
				utype=1;



			if (smon>emon)
				emon=smon;



			 
//			txt1=depo_code==0?" Branch Wise ":" HQ Wise ";

			txt4=" Trend Unit Wise";
			if(uv==2)
				txt4=" Trend Value Wise";
			
			
			int t=13;
			int w=12;

			gval = new double[13]; 
			grval = new double[13]; 
			mcode = new int[12];
			month_name = new String[12];
			
			nrep = new int[w];
			saleval = new double[w]; 
			tarval = new double[w]; 
			lysval = new double[w]; 
			incrval = new double[w]; 


			
            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            if(hq_code>0)
            	branchname = "Select ter_name from hqmast where mkt_year=? and div_code=? and  depo_code=? and ter_code=?";

            
            ps1=con.prepareStatement(branchname);
            if(hq_code>0)
            {
            	ps1.setInt(1, eyear);
            	ps1.setInt(2, div_code);
            	ps1.setInt(3, depo_code);
            	ps1.setInt(4, hq_code);
            }
            else
             ps1.setInt(1, depo_code);

            rs1=ps1.executeQuery();
            if(rs1.next())
            {
//            	txt1=rs1.getString(1)+ " Branch  ";
            	txt1=(hq_code>0?"HQ :-> ":"Branch :-> ")+rs1.getString(1);
            }
            else
            	txt1="All India  ";
           
            rs1.close();
            ps1.close();

			

			String monthrec = "Select mnth_code,mnth_abbr from monthfl where mkt_year=? ";



			System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid+" saletype "+saletp+" doctype "+doctp);

			ps = con.prepareStatement(monthrec);
			ps.setInt(1,eyear);

			rs = ps.executeQuery();
			int i=0;
			while (rs.next())
			{
				mcode[i] = rs.getInt(1);
				month_name[i] = rs.getString(2);
				i++;
			}
			rs.close();
			ps.close();
			/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;


			cs  = con.prepareCall(procedureWithParameters);
			cs.setInt(1, eyear);
			cs.setInt(2, div_code);
			cs.setInt(3, depo_code);
			cs.setInt(4, smon);
			cs.setInt(5, emon);
			cs.setInt(6, utype);
			cs.setInt(7, loginid);
			cs.setInt(8, grp_code);
			cs.setInt(9, hq_code);



			rst1 = cs.executeQuery();   
			int k=0;  
			gsalval=0.00;

			boolean hrprint = true;	
			boolean hprint = true;	
			int pcode=0;
			String name=null;
    	    String grnm=null; 

    	    
			double ggval=0.00;
			double sval=0.00;
			double tval=0.00;
			double lval=0.00;
			int fs=0;

    	    

			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
					
					grnm="Group : "+rst1.getString(17)+" - ";
					pcode=rst1.getInt(6);
					name = rst1.getString(9)+","+rst1.getString(10);
					hrprint=false;
				}

				

				
				if (pcode!=rst1.getInt(6))
				{
					rfb = new MktFormBean();
					rfb.setMname(name);
					//rfb.setBr(t);


					
					ggval=0.00;
					sval=0.00;
					tval=0.00;
					lval=0.00;
					fs=0;
					
					for( int y=0;y<w;y++)
					{
						rfb.setNm9(y, month_name[y]);
						rfb.setDval0(y, nrep[y]);
						ggval=ggval+nrep[y];
					}
					rfb.setColor(3); 
					rfb.setNm3(grnm+txt1+txt4);
					rfb.setMcode(w);
					rfb.setUv(uv);
					rfb.setNm1(0, "FS");
					rfb.setDval0(w, ggval);
					rfb.setNm9(w, "Total");
					//rfb.setDval1(ggval); 
					data.add(rfb); 


					
					ggval=0.00;
					rfb = new MktFormBean();
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "TARGET");
						rfb.setDval0(y, tarval[y]);
						ggval=ggval+tarval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 

					
					
					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "SALES");
						rfb.setDval0(y, saleval[y]);
						ggval=ggval+saleval[y];
					}
					rfb.setDval0(w, ggval);
					data.add(rfb); 
		                
					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "LYSALES");
						rfb.setDval0(y, lysval[y]);
						ggval=ggval+lysval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 


					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "INCR");
						rfb.setDval0(y, incrval[y]);
						ggval=ggval+incrval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 



					rfb = new MktFormBean();
					sval=0.00;
					tval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "%ACH");
						rfb.setDval0(y, (tarval[y]==0?0.00:(saleval[y]/tarval[y])*100));
						sval=sval+saleval[y];
						tval=tval+tarval[y];
						
					}
//					rfb.setDval1((tval==0?0.00:(sval/tval)*100));
					rfb.setDval0(w, (tval==0?0.00:(sval/tval)*100));

					data.add(rfb); 

					rfb = new MktFormBean();
					sval=0.00;
					lval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "%GR");
						rfb.setDval0(y, (lysval[y]==0?0.00:(saleval[y]/lysval[y])*100-100));
						sval=sval+saleval[y];
						lval=lval+lysval[y];
					}
					//rfb.setDval1((lval==0?0.00:(sval/lval)*100-100));
					rfb.setDval0(w, (lval==0?0.00:(sval/lval)*100-100));

					data.add(rfb); 


					rfb = new MktFormBean();
					sval=0.00;
					fs=0;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "PMR");
						rfb.setDval0(y, (nrep[y]==0?0.00:(saleval[y]/nrep[y])));
						sval=sval+saleval[y];
						fs=fs+nrep[y];
					}
					//rfb.setDval1(fs==0?0.00:(sval/fs));
					rfb.setDval0(w, fs==0?0.00:(sval/fs));
					data.add(rfb); 


					rfb = new MktFormBean();
					sval=0.00;
					tval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "SUR./DEF.");
						rfb.setDval0(y, (saleval[y]-tarval[y]));
						sval=sval+saleval[y];
						tval=tval+tarval[y];
						
					}
					//rfb.setDval1((sval-tval));
					rfb.setDval0(w, sval-tval);

					data.add(rfb); 

					
					
					

					k=0;
					hprint=false;
					nrep = new int[w];
					saleval = new double[w]; 
					tarval = new double[w]; 
					lysval = new double[w]; 
					incrval = new double[w]; 

					pcode=rst1.getInt(6);
					name = rst1.getString(9)+","+rst1.getString(10);

				} //end of if(tcode)


				
				
				for(int q=k;q<w;q++)
				{
					if(mcode[q]==rst1.getInt(4))
						break;
					else
					{
						//do nothing
						k++;

					}
				}

				if(uv==1) // unit wise
				{
					saleval[k]=rst1.getDouble(19);
					tarval[k]=rst1.getDouble(20);
					lysval[k]=rst1.getDouble(21);
					nrep[k]=rst1.getInt(14);
					incrval[k]=rst1.getDouble(22);
				}
				else if(uv==2) // value wise
				{
					saleval[k]=rst1.getDouble(11);
					tarval[k]=rst1.getDouble(12);
					lysval[k]=rst1.getDouble(13);
					nrep[k]=rst1.getInt(14);
					incrval[k]=rst1.getDouble(18);
				}
				
				k++;



			} //////////////////////// End of Query Loop///////////////////////

			
			ggval=0.00;
			sval=0.00;
			tval=0.00;
			lval=0.00;
			fs=0;

			
			rfb = new MktFormBean();
			rfb.setMname(name);

			for( int y=0;y<w;y++)
			{
				rfb.setNm9(y, month_name[y]);
				rfb.setDval0(y, nrep[y]);
				ggval=ggval+nrep[y];
			}
			rfb.setColor(3);
			rfb.setNm3(txt1+grnm+txt4);
			rfb.setMcode(w);
			rfb.setUv(uv);
			rfb.setNm1(0, "FS");
			rfb.setDval0(w, ggval);
			rfb.setNm9(w, "Total");
			//rfb.setDval1(ggval); 
			data.add(rfb); 


			
			ggval=0.00;
			rfb = new MktFormBean();
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "TARGET");
				rfb.setDval0(y, tarval[y]);
				ggval=ggval+tarval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 

			
			
			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "SALES");
				rfb.setDval0(y, saleval[y]);
				ggval=ggval+saleval[y];
			}
			rfb.setDval0(w, ggval);
			data.add(rfb); 
                
			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "LYSALES");
				rfb.setDval0(y, lysval[y]);
				ggval=ggval+lysval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 


			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "INCR");
				rfb.setDval0(y, incrval[y]);
				ggval=ggval+incrval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 



			rfb = new MktFormBean();
			sval=0.00;
			tval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "%ACH");
				rfb.setDval0(y, (tarval[y]==0?0.00:(saleval[y]/tarval[y])*100));
				sval=sval+saleval[y];
				tval=tval+tarval[y];
				
			}
//			rfb.setDval1((tval==0?0.00:(sval/tval)*100));
			rfb.setDval0(w, (tval==0?0.00:(sval/tval)*100));

			data.add(rfb); 

			rfb = new MktFormBean();
			sval=0.00;
			lval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "%GR");
				rfb.setDval0(y, (lysval[y]==0?0.00:(saleval[y]/lysval[y])*100-100));
				sval=sval+saleval[y];
				lval=lval+lysval[y];
			}
			//rfb.setDval1((lval==0?0.00:(sval/lval)*100-100));
			rfb.setDval0(w, (lval==0?0.00:(sval/lval)*100-100));

			data.add(rfb); 


			rfb = new MktFormBean();
			sval=0.00;
			fs=0;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "PMR");
				rfb.setDval0(y, (nrep[y]==0?0.00:(saleval[y]/nrep[y])));
				sval=sval+saleval[y];
				fs=fs+nrep[y];
			}
			//rfb.setDval1(fs==0?0.00:(sval/fs));
			rfb.setDval0(w, fs==0?0.00:(sval/fs));
			data.add(rfb); 


			rfb = new MktFormBean();
			sval=0.00;
			tval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "SUR./DEF.");
				rfb.setDval0(y, (saleval[y]-tarval[y]));
				sval=sval+saleval[y];
				tval=tval+tarval[y];
				
			}
			//rfb.setDval1((sval-tval));
			rfb.setDval0(w, sval-tval);

			data.add(rfb); 

			
		} catch (Exception e) {

			System.out.println("========Exception in SQLForm5DAO.getNewBranch7 " + e);
		}

		finally
		{
			try 
			{
				if(rst1 != null){ rst1.close();}
				if(cs != null){ cs.close();}
				if(rs != null){ rs.close();}
				if(ps != null){ ps.close();}
				if(rs1 != null){ rs1.close();}
				if(ps1 != null){ ps1.close();}
				if(con != null){con.close();}
			}
			catch (SQLException e) 
			{
				System.out.println("-------Exception in SQLForm5DAO.Connection.close "+e);
			}
		}		
		return data; 
	}
		

	
	
	////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getNewBranch11(Connection con,  int smon, int emon,int eyear,int depo_code,int div_code,int loginid, int utype) { 
		
		MktFormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int saletp=3;
		CallableStatement cs=null;
		ResultSet rst1=null;

		PreparedStatement ps1=null;
		ResultSet rs1=null;

		PreparedStatement ps12=null;
		ResultSet rst12=null;
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
			String txt1=null;
			String txt2=null;
			String txt4=null;
			String txt6=null;
			int mcode[];
			String month_name[];
			int trep=0;
			
			double tarval[];
			double lysval[];
			double saleval[];
			double incrval[];
			int nrep[];

			
			String tp[]={"","A","T","G","","","","","","","M","","","","","","","","","","B","","","","","","","","","","F"};
			
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
String query12 = "Select u_date,u_time  from aristo.upload where depo_code=? and substr(typ,1,1)=? ";
ps12 = con.prepareStatement(query12);
ps12.setInt(1,depo_code);
ps12.setString(2,tp[div_code]); 
rst12 = ps12.executeQuery();


if (rst12.next())
txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);



rst12.close();
ps12.close();    


			
			String procedureWithParameters="{call web_report_br_hq_trend(?,?,?,?,?,?,?)}";

			//utype=4;
			//loginid=360;

			int doctp=saletp;

			if(depo_code>0 && (utype==2 || utype==3 || utype==5))
				utype=1;



			if (smon>emon)
				emon=smon;



			 
//			txt1=depo_code==0?" Branch Wise ":" HQ Wise ";

			txt4=" RUPEES WISE SALES ANALYSIS (Trend) ";
			
			
			int t=13;
			int w=12;

			mcode = new int[12];
			month_name = new String[12];
			
			nrep = new int[w];
			saleval = new double[w]; 
			tarval = new double[w]; 
			lysval = new double[w]; 
			incrval = new double[w]; 


			
            String branchname = "Select depo_name from branch_comp where depo_code=? ";

            
            ps1=con.prepareStatement(branchname);
             ps1.setInt(1, depo_code);

            rs1=ps1.executeQuery();
            if(rs1.next())
            {
//            	txt1=rs1.getString(1)+ " Branch  ";
            	txt1="Branch :-> "+rs1.getString(1);
            }
            else
            	txt1="All India  ";
           
            rs1.close();
            ps1.close(); 

			

			String monthrec = "Select mnth_code,mnth_abbr from monthfl where mkt_year=? ";



			System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid+" saletype "+saletp+" doctype "+doctp);

			ps = con.prepareStatement(monthrec);
			ps.setInt(1,eyear);

			rs = ps.executeQuery();
			int i=0;
			while (rs.next())
			{
				mcode[i] = rs.getInt(1);
				month_name[i] = rs.getString(2);
				i++;
			}
			rs.close();
			ps.close();
			/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

			if(utype==3 && depo_code>0)
				utype=31;
			else if(utype==4 && depo_code>0)
				utype=41;


			cs  = con.prepareCall(procedureWithParameters);
			cs.setInt(1, eyear);
			cs.setInt(2, div_code);
			cs.setInt(3, depo_code);
			cs.setInt(4, smon);
			cs.setInt(5, emon);
			cs.setInt(6, utype);
			cs.setInt(7, loginid);



			rst1 = cs.executeQuery();   
			int k=0;  

			boolean hrprint = true;	
			boolean hprint = true;	
			int tcode=0;
			String name=null;

    	    
			double ggval=0.00;
			double sval=0.00;
			double tval=0.00;
			double lval=0.00;
			int fs=0;

    	    

			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
					tcode=rst1.getInt(6);
					name = rst1.getString(7);
					hrprint=false;
				}

				

				
				if (tcode!=rst1.getInt(6))
				{
					rfb = new MktFormBean();
					rfb.setMname(name);
					//rfb.setBr(t);


					
					ggval=0.00;
					sval=0.00;
					tval=0.00;
					lval=0.00;
					fs=0;
					
					for( int y=0;y<w;y++)
					{
						rfb.setNm9(y, month_name[y]);
						rfb.setDval0(y, nrep[y]);
						ggval=ggval+nrep[y];
					}
					rfb.setColor(3); 
					rfb.setNm3(txt1+txt4);
					rfb.setMcode(w);
					rfb.setUv(2);
					rfb.setNm1(0, "FS");
					rfb.setDval0(w, ggval);
					rfb.setNm9(w, "Total");
					//rfb.setDval1(ggval); 
					rfb.setLupdate(txt6);
					System.out.println("txt6 inner "+txt6);

					data.add(rfb); 


					
					ggval=0.00;
					rfb = new MktFormBean();
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "TARGET");
						rfb.setDval0(y, tarval[y]);
						ggval=ggval+tarval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 
 
					
					
					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "SALES");
						rfb.setDval0(y, saleval[y]);
						ggval=ggval+saleval[y];
					}
					rfb.setDval0(w, ggval);
					data.add(rfb); 
		                
					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "LYSALES");
						rfb.setDval0(y, lysval[y]);
						ggval=ggval+lysval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 


					rfb = new MktFormBean();
					ggval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "INCR");
						rfb.setDval0(y, incrval[y]);
						ggval=ggval+incrval[y];
					}
					rfb.setDval0(w, ggval);

					data.add(rfb); 



					rfb = new MktFormBean();
					sval=0.00;
					tval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "%ACH");
						rfb.setDval0(y, (tarval[y]==0?0.00:(saleval[y]/tarval[y])*100));
						sval=sval+saleval[y];
						tval=tval+tarval[y];
						
					}
//					rfb.setDval1((tval==0?0.00:(sval/tval)*100));
					rfb.setDval0(w, (tval==0?0.00:(sval/tval)*100));

					data.add(rfb); 

					rfb = new MktFormBean();
					sval=0.00;
					lval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "%GR");
						rfb.setDval0(y, (lysval[y]==0?0.00:(saleval[y]/lysval[y])*100-100));
						sval=sval+saleval[y];
						lval=lval+lysval[y];
					}
					//rfb.setDval1((lval==0?0.00:(sval/lval)*100-100));
					rfb.setDval0(w, (lval==0?0.00:(sval/lval)*100-100));

					data.add(rfb); 


					rfb = new MktFormBean();
					sval=0.00;
					fs=0;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "PMR");
						rfb.setDval0(y, (nrep[y]==0?0.00:(saleval[y]/nrep[y])));
						sval=sval+saleval[y];
						fs=fs+nrep[y];
					}
					//rfb.setDval1(fs==0?0.00:(sval/fs));
					rfb.setDval0(w, fs==0?0.00:(sval/fs));
					data.add(rfb); 


					rfb = new MktFormBean();
					sval=0.00;
					tval=0.00;
					for( int y=0;y<w;y++)
					{
						rfb.setColor(0);
						rfb.setNm1(y, "SUR./DEF.");
						rfb.setDval0(y, (saleval[y]-tarval[y]));
						sval=sval+saleval[y];
						tval=tval+tarval[y];
						
					}
					//rfb.setDval1((sval-tval));
					rfb.setDval0(w, sval-tval);

					data.add(rfb); 

					
					
					

					k=0;
					hprint=false;
					nrep = new int[w];
					saleval = new double[w]; 
					tarval = new double[w]; 
					lysval = new double[w]; 
					incrval = new double[w]; 

					tcode=rst1.getInt(6);
					name = rst1.getString(7);

				} //end of if(tcode)


				
				
				for(int q=k;q<w;q++)
				{
					if(mcode[q]==rst1.getInt(4))
						break;
					else
					{
						//do nothing
						k++;

					}
				}


				saleval[k]=rst1.getDouble(8);
				tarval[k]=rst1.getDouble(9);
				lysval[k]=rst1.getDouble(10);
				nrep[k]=rst1.getInt(11);
				incrval[k]=rst1.getDouble(14);
				k++;



			} //////////////////////// End of Query Loop///////////////////////

			
			ggval=0.00;
			sval=0.00;
			tval=0.00;
			lval=0.00;
			fs=0;

			
			rfb = new MktFormBean();
			rfb.setMname(name);

			for( int y=0;y<w;y++)
			{
				rfb.setNm9(y, month_name[y]);
				rfb.setDval0(y, nrep[y]);
				ggval=ggval+nrep[y];
			}
			rfb.setColor(3);
			rfb.setNm3(txt1+txt4);
			rfb.setLupdate(txt6);
			rfb.setMcode(w);
			rfb.setUv(2);
			rfb.setNm1(0, "FS");
			rfb.setDval0(w, ggval);
			rfb.setNm9(w, "Total");
			//rfb.setDval1(ggval); 
			data.add(rfb); 


			
			ggval=0.00;
			rfb = new MktFormBean();
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "TARGET");
				rfb.setDval0(y, tarval[y]);
				ggval=ggval+tarval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 

			
			
			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "SALES");
				rfb.setDval0(y, saleval[y]);
				ggval=ggval+saleval[y];
			}
			rfb.setDval0(w, ggval);
			data.add(rfb); 
                
			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "LYSALES");
				rfb.setDval0(y, lysval[y]);
				ggval=ggval+lysval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 


			rfb = new MktFormBean();
			ggval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "INCR");
				rfb.setDval0(y, incrval[y]);
				ggval=ggval+incrval[y];
			}
			rfb.setDval0(w, ggval);

			data.add(rfb); 



			rfb = new MktFormBean();
			sval=0.00;
			tval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "%ACH");
				rfb.setDval0(y, (tarval[y]==0?0.00:(saleval[y]/tarval[y])*100));
				sval=sval+saleval[y];
				tval=tval+tarval[y];
				
			}
//			rfb.setDval1((tval==0?0.00:(sval/tval)*100));
			rfb.setDval0(w, (tval==0?0.00:(sval/tval)*100));

			data.add(rfb); 

			rfb = new MktFormBean();
			sval=0.00;
			lval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "%GR");
				rfb.setDval0(y, (lysval[y]==0?0.00:(saleval[y]/lysval[y])*100-100));
				sval=sval+saleval[y];
				lval=lval+lysval[y];
			}
			//rfb.setDval1((lval==0?0.00:(sval/lval)*100-100));
			rfb.setDval0(w, (lval==0?0.00:(sval/lval)*100-100));

			data.add(rfb); 


			rfb = new MktFormBean();
			sval=0.00;
			fs=0;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "PMR");
				rfb.setDval0(y, (nrep[y]==0?0.00:(saleval[y]/nrep[y])));
				sval=sval+saleval[y];
				fs=fs+nrep[y];
			}
			//rfb.setDval1(fs==0?0.00:(sval/fs));
			rfb.setDval0(w, fs==0?0.00:(sval/fs));
			data.add(rfb); 


			rfb = new MktFormBean();
			sval=0.00;
			tval=0.00;
			for( int y=0;y<w;y++)
			{
				rfb.setColor(0);
				rfb.setNm1(y, "SUR./DEF.");
				rfb.setDval0(y, (saleval[y]-tarval[y]));
				sval=sval+saleval[y];
				tval=tval+tarval[y];
				
			}
			//rfb.setDval1((sval-tval));
			rfb.setDval0(w, sval-tval);

			data.add(rfb); 

			
		} catch (Exception e) {

			System.out.println("========Exception in SQLForm5DAO.getNewBranch11 " + e);
		}

		finally
		{
			try 
			{
				if(rst1 != null){ rst1.close();}
				if(cs != null){ cs.close();}
				if(rs != null){ rs.close();}
				if(ps != null){ ps.close();}
				if(rs1 != null){ rs1.close();}
				if(ps1 != null){ ps1.close();}
				if(con != null){con.close();}
			}
			catch (SQLException e) 
			{
				System.out.println("-------Exception in SQLForm5DAO.Connection.close "+e);
			}
		}		
		return data; 
	}
		
}    