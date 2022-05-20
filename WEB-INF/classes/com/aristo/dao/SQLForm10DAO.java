package com.aristo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm10DAO {
/////////////////////////////////////// HQ Coding Start here///////////////////////////////////////////
	public List getAllHQ(Connection con, int uv,int sale,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
		PreparedStatement ps12 = null;
		ResultSet rst12 = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		PreparedStatement ms1 = null;
		ResultSet mrec = null;
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try { 
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            String txt7=null;

/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double umontar=0.00;
            double ucumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double monsd=0.00;
            double cumsd=0.00;
            double monqty=0.00;
            double cumqty=0.00;
///////////////Group Total ke liye/////////////////
//            double ggmontar=0.00;
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            int gimonsd=0;
            int gicumsd=0;
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            int timonsd=0;
            int ticumsd=0; 
            int imonsd=0;
            int icumsd=0;
            
            if (smon>emon)
            	emon=smon;

    	    tblnm=(tp+"_repfinal").toLowerCase();
                String qg=null;
                String qg1=null;
                String qg2=null;
       	        
                if (uv==1) 
                {
                	txt6="UNIT-WISE";
                }
                
                if (uv==2)
                {
                	txt6="VALUE-WISE";
                }
            
	            if (uv==3)
	            {
	            	txt6="UNIT/VALUE - WISE";
	            }

	            qg=",sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
		    	qg1=",sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval  ";
		    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv  ";
            
            
            	txt6=txt6+" NET SALE";
                txt2="     PRODUCT WISE "+txt6+" TARGET SALE COMPARISON FROM "; 
                
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
		String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
		ps12 = con.prepareStatement(query12);
		ps12.setInt(1,depo_code);
		ps12.setString(2,"HQT09"); 
		ps12.setString(3,tp); 
		rst12 = ps12.executeQuery();
		
		if (rst12.next())
		txt7= rst12.getString(1)+", TIME: "+rst12.getString(2);
		
		rst12.close();
		ps12.close();
        		                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
			ms1 = con.prepareStatement(month);
			ms1.setInt(1,smon);
			ms1.setInt(2,emon);
			ms1.setInt(3,eyear);
			
			mrec = ms1.executeQuery();
			mrec.beforeFirst();
			while (mrec.next()) // Month file Loop Starts/////////////////////////
			{	
 			    if (mrec.isFirst())
			     {
				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
			      txt4 = mrec.getString(3)+" "+mrec.getInt(2);
			      txt3 = mrec.getString(3)+" "+mrec.getInt(2);
			     }
		 	  
	            if (mrec.isLast())
	             {
		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
		          txt4 = txt4+ " TO "+mrec.getString(3)+" "+mrec.getInt(2);
	             }
			  
        }  // Month file Loop End here///////////////////////// 
			
			mrec.close();
			ms1.close();
		
	
///////////////////// Group master ki query/////////////////////
					gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////
        	if (utype.equals("PMT"))
        	{
	    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.ter_name,a.grp_cd "+
	    		"from( "+
	    		"select depo_code,ter_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
	    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and tr_cd=? group by mcode) as a "+
	    		"left join "+
	    		"(select depo_code,ter_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg1+ 
	    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and tr_cd=? group by mcode) as b "+
	    		"on a.mcode = b.mcode where a.grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by a.mgrp,a.mcode ";
	    		}
	    	else
	    	{
	    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.ter_name "+
	    		"from( "+
	    		"select depo_code,ter_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
	    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and tr_cd=? group by mcode) as a "+
	    		"left join "+
	    		"(select depo_code,ter_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+ 
	    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and tr_cd=? group by mcode) as b "+
	    		"on a.mcode = b.mcode  order by a.mgrp,a.mcode ";
	    	}
	    			
	    		
	    		ps1 = con.prepareStatement(query1); 
			    ps1.setInt(1,eyear);
			    ps1.setInt(2,emon);
			    ps1.setInt(3,depo_code);
			    ps1.setInt(4,code);
			    ps1.setInt(5,eyear);
			    ps1.setInt(6,smon);
			    ps1.setInt(7,emon);
			    ps1.setInt(8,depo_code);
			    ps1.setInt(9,code);
			    
				rst1 = ps1.executeQuery();
	    	    boolean hprint=false;
	    	    boolean first=true;
	    	    int grp=0;
	    	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;

					if (first)
					{
						grp=rst1.getInt(2);
						grnm=rst1.getString(3);
						first=false;
			            txt1="HQ-> "+rst1.getString(15);
					}
		        
					if (grp!=rst1.getInt(2))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);

		                  if ((uv==1)|| (uv==3))
		                  {
				             rfb.setQty2((int)(gmontar+.50));
			        		 rfb.setQty3((int)gmonsal);
			        		 rfb.setQty4(gimonsd);
			        		 rfb.setQty5((int)(gcumtar+.50));
			        		 rfb.setQty6((int)gcumsal);
			        		 rfb.setQty7(gicumsd);
		                  }
		                  if ((uv==2)|| (uv==3))
		                  {
			        		 rfb.setDval2(gmontar);
			        		 rfb.setDval3(gmonsal);
			        		 rfb.setDval4(gimonsd);
			        		 rfb.setDval5(gcumtar);
			        		 rfb.setDval6(gcumsal);
			        		 rfb.setDval7(gicumsd);
		                  }	      

	 	 				 rfb.setNm3(txt1+txt2+txt5);
	 	 				 data.add(rfb);
						 grp=rst1.getInt(2);
						 grnm=rst1.getString(3);
	
						gmontar=0.00;
			            gcumtar=0.00;
			            gmonsal=0.00;
			            gcumsal=0.00;
			            gimonsd=0;
			            gicumsd=0;
					}

		 			montar=0.00;
		            monsal=0.00;
		            monsd=0.00;
		            ucumtar=0.00;
		            cumqty=0.00;
		            icumsd=0;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumsd=0.00;
//		            ggmontar=0.00;
		            
//////////////////////// Target master ki query///////////////////////////////
	 			           
//					         ggmontar = rst1.getDouble(11);
//					         gcumtar = gcumtar+rst1.getDouble(13);
					            
					         if ((uv==1)||(uv==3))
					         {
					            umontar = rst1.getInt(7);
					            ucumtar = ucumtar+rst1.getInt(9);
				        		monqty =rst1.getInt(8);
				        		cumqty =cumqty+rst1.getInt(10);
					         }
					         if ((uv==2)||(uv==3))
					         {
//					            montar = rst1.getDouble(11);
//					            cumtar = cumtar+rst1.getDouble(13);
//				        		monsal =rst1.getDouble(12);
//				        		cumsal =cumsal+rst1.getDouble(14);
					         }
				        		monsal =rst1.getDouble(12);
				        		cumsal =rst1.getDouble(14);
					            montar = rst1.getDouble(11);
					            cumtar = rst1.getDouble(13);
					          		            
				        	 
			        
			      			        
		 			
////////////////////////////Month File Loop End here//////////////////////////////////
		 			
		            if ((uv==1)||(uv==3))
		            {
/*		             imonsd=(int)monqty-(int)(umontar+.50); 
		             icumsd=(int)cumqty-(int)(ucumtar+.50);
*/	
		             imonsd=(int)monqty-(int)(umontar); 
		             icumsd=(int)cumqty-(int)(ucumtar);
		            }

		            if ((uv==2)||(uv==3))
		            {
		             monsd=monsal-montar; 
		             cumsd=cumsal-cumtar;
		            }
		             
		        if (hprint)
		        {
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
		            rfb.setColor(1);
		             
                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(umontar+.50));
	        		 rfb.setQty3((int)monqty);
	        		 rfb.setQty4(imonsd);
	        		 rfb.setQty5((int)(ucumtar+.50));
	        		 rfb.setQty6((int)cumqty);
	        		 rfb.setQty7(icumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(montar);
	        		 rfb.setDval3(monsal);
	        		 rfb.setDval4(monsd);
	        		 rfb.setDval5(cumtar);
	        		 rfb.setDval6(cumsal);
	        		 rfb.setDval7(cumsd);
                  }	      
	      
		             rfb.setNm2(txt3);
		             rfb.setNm4(txt4);
                     rfb.setNm3(txt1+txt2+txt5);
                     rfb.setLupdate(txt7);
 	 				 
	                 data.add(rfb);
		        }

/*			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
//			         gcumtar = gcumtar+cumtar;
*/			         
			         gmontar = gmontar+montar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
			         gcumtar = gcumtar+cumtar;

				    
	            if ((gmonsal-gmontar)>0)
	                 gimonsd=(int)((gmonsal-gmontar)+.50);
	            else
		             gimonsd=(int)((((gmonsal-gmontar)*-1)+.50)*-1);
	            
	            if ((gcumsal-gcumtar)>0)
	            	gicumsd=(int)((gcumsal-gcumtar)+.50);
	            else
		            gicumsd=(int)((((gcumsal-gcumtar)*-1)+.50)*-1);
				

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+cumtar;
			            tcumsal = tcumsal+cumsal;
			            tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
 	 				
			}///////////////////// Main Loop  End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);

                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)gmonsal);
	        		 rfb.setQty4(gimonsd);
	        		 rfb.setQty5((int)(gcumtar+.50));
	        		 rfb.setQty6((int)gcumsal);
	        		 rfb.setQty7(gicumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(gmontar);
	        		 rfb.setDval3(gmonsal);
	        		 rfb.setDval4(gimonsd);
	        		 rfb.setDval5(gcumtar);
	        		 rfb.setDval6(gcumsal);
	        		 rfb.setDval7(gicumsd);
                  }	      

 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);

					gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
		            
			ps1.close();
			rst1.close();

///////////////////////////// All Statment Close/////////////////////////////

	            
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);

	                  if ((uv==1)|| (uv==3))
	                  {
			             rfb.setQty2((int)(tmontar+.50));
		        		 rfb.setQty3((int)tmonsal);
		        		 rfb.setQty4(timonsd);
		        		 rfb.setQty5((int)(tcumtar+.50));
		        		 rfb.setQty6((int)tcumsal);
		        		 rfb.setQty7(ticumsd);
	                  }
	                  if ((uv==2)|| (uv==3))
	                  {
		        		 rfb.setDval2(tmontar);
		        		 rfb.setDval3(tmonsal);
		        		 rfb.setDval4(timonsd);
		        		 rfb.setDval5(tcumtar);
		        		 rfb.setDval6(tcumsal);
		        		 rfb.setDval7(ticumsd);
	                  }	      

 	 				 rfb.setNm3(txt1+txt2+txt5);
				
 	 				data.add(rfb);
 	 				
		 
		}
		catch (Exception e) {
			
			System.out.println("========Exception in SQLForm10DAO.getHQ " + e); 
		}
		  finally 
			{
			  try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ps1.close();}
		             if(mrec != null){ mrec.close();}
		             if(ms1 != null){ms1.close();}
		             if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm10DAO.Connection.close "+e);
				}
			}
		return data;
	}	
/////////////////////////////////////// HQ Coding End here///////////////////////////////////////////	
	
/////////////////////////////////////// Region Coding Start here///////////////////////////////////////////
	
	public List getAllRegion(Connection con, int uv,int sale,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
		PreparedStatement ps12 = null;
		ResultSet rst12 = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		PreparedStatement ms1 = null;
		ResultSet mrec = null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try { 
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
            String txt5=null;
            String txt7=null;

/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double umontar=0.00;
            double ucumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double monsd=0.00;
            double cumsd=0.00;
            double monqty=0.00;
            double cumqty=0.00;
///////////////Group Total ke liye/////////////////
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            int gimonsd=0;
            int gicumsd=0;
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            int timonsd=0;
            int ticumsd=0; 
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;
    	    tblnm=(tp+"_repfinal").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
   	        
            if (uv==1) 
            {
            	txt6="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt6="VALUE-WISE";
            }
        
            if (uv==3)
            {
            	txt6="UNIT/VALUE - WISE";
            }

            qg=",sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv  ";
        

            	txt6=txt6+" NET SALE";
                txt2="     PRODUCT WISE "+txt6+" TARGET SALE COMPARISON FROM ";
                
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
        		String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
        		ps12 = con.prepareStatement(query12);
        		ps12.setInt(1,depo_code);
        		ps12.setString(2,"HQT09"); 
        		ps12.setString(3,tp); 
        		rst12 = ps12.executeQuery();
        		
        		if (rst12.next())
        		txt7= rst12.getString(1)+", TIME: "+rst12.getString(2);
        		
        		rst12.close();
        		ps12.close();
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
        			ms1 = con.prepareStatement(month);
        			ms1.setInt(1,smon);
        			ms1.setInt(2,emon);
        			ms1.setInt(3,eyear);
        			
        			mrec = ms1.executeQuery();
        			mrec.beforeFirst();
        			while (mrec.next()) // Month file Loop Starts/////////////////////////
        			{	
         			    if (mrec.isFirst())
        			     {
        				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
        			      txt4 = mrec.getString(3)+" "+mrec.getInt(2);
        			      txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        			     }
        		 	  
        	            if (mrec.isLast())
        	             {
        		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
        		          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        		          txt4 = txt4+ " TO "+mrec.getString(3)+" "+mrec.getInt(2);
        	             }
        			  
                }  // Month file Loop End here///////////////////////// 
        			
        			mrec.close();
        			ms1.close();

			
///////////////////// Group master ki query/////////////////////            
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////
		        	if (utype.equals("PMT"))
		        	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.reg_name,a.grp_cd "+
			    		"from( "+
			    		"select depo_code,reg_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and rg_cd=? group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,reg_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and rg_cd=? group by mcode) as b "+
			    		"on a.mcode = b.mcode where a.grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by a.mgrp,a.mcode ";
			    		}
			    	else
			    	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.reg_name "+
			    		"from( "+
			    		"select depo_code,reg_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and rg_cd=? group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,reg_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and rg_cd=? group by mcode) as b "+
			    		"on a.mcode = b.mcode  order by a.mgrp,a.mcode ";
			    	}
			    			
			    		
			    		ps1 = con.prepareStatement(query1); 
					    ps1.setInt(1,eyear);
					    ps1.setInt(2,emon);
					    ps1.setInt(3,depo_code);
					    ps1.setInt(4,code);
					    ps1.setInt(5,eyear);
					    ps1.setInt(6,smon);
					    ps1.setInt(7,emon);
					    ps1.setInt(8,depo_code);
					    ps1.setInt(9,code);
					    
						rst1 = ps1.executeQuery();
			    	    boolean hprint=false;
			    	    boolean first=true;
			    	    int grp=0;
			    	    String grnm=null;
				rst1 = ps1.executeQuery();
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;

					if (first)
					{
						grp=rst1.getInt(2);
						grnm=rst1.getString(3);
						first=false;
			            txt1="REGION-> "+rst1.getString(15);
					}
		        
					if (grp!=rst1.getInt(2))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);

		                  if ((uv==1)|| (uv==3))
		                  {
				             rfb.setQty2((int)(gmontar+.50));
			        		 rfb.setQty3((int)gmonsal);
			        		 rfb.setQty4(gimonsd);
			        		 rfb.setQty5((int)(gcumtar+.50));
			        		 rfb.setQty6((int)gcumsal);
			        		 rfb.setQty7(gicumsd);
		                  }
		                  if ((uv==2)|| (uv==3))
		                  {
			        		 rfb.setDval2(gmontar);
			        		 rfb.setDval3(gmonsal);
			        		 rfb.setDval4(gimonsd);
			        		 rfb.setDval5(gcumtar);
			        		 rfb.setDval6(gcumsal);
			        		 rfb.setDval7(gicumsd);
		                  }	      

	 	 				 rfb.setNm3(txt1+txt2+txt5);
	 	 				 data.add(rfb);
						 grp=rst1.getInt(2);
						 grnm=rst1.getString(3);
	
						gmontar=0.00;
			            gcumtar=0.00;
			            gmonsal=0.00;
			            gcumsal=0.00;
			            gimonsd=0;
			            gicumsd=0;
					}

	               
		 			montar=0.00;
		            monsal=0.00;
		            monsd=0.00;
		            ucumtar=0.00;
		            cumqty=0.00;
		            icumsd=0;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumsd=0.00;
				
/*				         ggmontar = rst1.getDouble(11);
				         gcumtar = gcumtar+rst1.getDouble(13);
*/				            
				         if ((uv==1)||(uv==3))
				         {
				            umontar = rst1.getInt(7);
				            ucumtar = ucumtar+rst1.getInt(9);
			        		monqty =rst1.getInt(8);
			        		cumqty =cumqty+rst1.getInt(10);
				         }
				         if ((uv==2)||(uv==3))
				         {
/*				            montar = rst1.getDouble(11);
				            cumtar = cumtar+rst1.getDouble(13);
			        		monsal =rst1.getDouble(12);
			        		cumsal =cumsal+rst1.getDouble(14);
*/				         }
			          		            
				            montar = rst1.getDouble(11);
				            cumtar = rst1.getDouble(13);
			        		monsal =rst1.getDouble(12);
			        		cumsal =rst1.getDouble(14);
		        	 
			        
			      			        

////////////////////////////Month File Loop End here//////////////////////////////////
		 			
		            if ((uv==1)||(uv==3))
		            {
		             imonsd=(int)monqty-(int)(umontar); 
		             icumsd=(int)cumqty-(int)(ucumtar);
		            }

		            if ((uv==2)||(uv==3))
		            {
		             monsd=monsal-montar; 
		             cumsd=cumsal-cumtar;
		            }
		             
	            if (hprint)
	            {
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
		             rfb.setColor(1);
		             
                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(umontar+.50));
	        		 rfb.setQty3((int)monqty);
	        		 rfb.setQty4(imonsd);
	        		 rfb.setQty5((int)(ucumtar+.50));
	        		 rfb.setQty6((int)cumqty);
	        		 rfb.setQty7(icumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(montar);
	        		 rfb.setDval3(monsal);
	        		 rfb.setDval4(monsd);
	        		 rfb.setDval5(cumtar);
	        		 rfb.setDval6(cumsal);
	        		 rfb.setDval7(cumsd);
                  }	      
		             rfb.setNm2(txt3);
		             rfb.setNm4(txt4);
                     rfb.setNm3(txt1+txt2+txt5);
                     rfb.setLupdate(txt7);
	                 data.add(rfb);
	            }

/*			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
*/
		        	 gcumtar = gcumtar+cumtar;
		             gcumsal = gcumsal+cumsal;
		             gmontar = gmontar+montar;
			         gmonsal = gmonsal+monsal;
	            
	            if ((gmonsal-gmontar)>0)
	                 gimonsd=(int)((gmonsal-gmontar)+.50);
	            else
		             gimonsd=(int)((((gmonsal-gmontar)*-1)+.50)*-1);
	            
	            if ((gcumsal-gcumtar)>0)
	            	gicumsd=(int)((gcumsal-gcumtar)+.50);
	            else
		            gicumsd=(int)((((gcumsal-gcumtar)*-1)+.50)*-1);
				

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+cumtar;
			            tcumsal = tcumsal+cumsal;
			            tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 

				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);

                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)gmonsal);
	        		 rfb.setQty4(gimonsd);
	        		 rfb.setQty5((int)(gcumtar+.50));
	        		 rfb.setQty6((int)gcumsal);
	        		 rfb.setQty7(gicumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(gmontar);
	        		 rfb.setDval3(gmonsal);
	        		 rfb.setDval4(gimonsd);
	        		 rfb.setDval5(gcumtar);
	        		 rfb.setDval6(gcumsal);
	        		 rfb.setDval7(gicumsd);
                  }	      

 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);

					gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
		            
			ps1.close();
			rst1.close();

///////////////////////////// All Statment Close/////////////////////////////
	            
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);

	                  if ((uv==1)|| (uv==3))
	                  {
			             rfb.setQty2((int)(tmontar+.50));
		        		 rfb.setQty3((int)tmonsal);
		        		 rfb.setQty4(timonsd);
		        		 rfb.setQty5((int)(tcumtar+.50));
		        		 rfb.setQty6((int)tcumsal);
		        		 rfb.setQty7(ticumsd);
	                  }
	                  if ((uv==2)|| (uv==3))
	                  {
		        		 rfb.setDval2(tmontar);
		        		 rfb.setDval3(tmonsal);
		        		 rfb.setDval4(timonsd);
		        		 rfb.setDval5(tcumtar);
		        		 rfb.setDval6(tcumsal);
		        		 rfb.setDval7(ticumsd);
	                  }	      

 	 				rfb.setNm3(txt1+txt2+txt5);
 	 				data.add(rfb);
		 
		}
		catch (Exception e) {
			
			System.out.println("========Exception in SQLForm10DAO.getRegion " + e); 
		}
		  finally 
			{
			  try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ps1.close();}
		             if(mrec != null){ mrec.close();}
		             if(ms1 != null){ms1.close();}
		             if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm10DAO.Connection.close "+e);
				}
			}
		return data;
	}		
	
	
	
/////////////////////////////////////// Region Coding End here///////////////////////////////////////////	
	
/////////////////////////////////////// Area Coding Start here///////////////////////////////////////////
	
	public List getAllArea(Connection con, int uv,int sale,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
		PreparedStatement ps12 = null;
		ResultSet rst12 = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		PreparedStatement ms1 = null;
		ResultSet mrec = null;
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try { 
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
            String txt5 =null;
            String txt7=null;

/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double umontar=0.00;
            double ucumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double monsd=0.00;
            double cumsd=0.00;
            double monqty=0.00;
            double cumqty=0.00;
///////////////Group Total ke liye/////////////////
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            int gimonsd=0;
            int gicumsd=0;
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            int timonsd=0;
            int ticumsd=0; 
            int imonsd=0;
            int icumsd=0;
            
            if (smon>emon)
            	emon=smon;
    	    tblnm=(tp+"_repfinal").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
   	        
            if (uv==1) 
            {
            	txt6="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt6="VALUE-WISE";
            }
        
            if (uv==3)
            {
            	txt6="UNIT/VALUE - WISE";
            }

            qg=",sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv  ";
        

            	txt6=txt6+" NET SALE";

                txt2="     PRODUCT WISE "+txt6+" TARGET SALE COMPARISON FROM "; 

//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
        		String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
        		ps12 = con.prepareStatement(query12);
        		ps12.setInt(1,depo_code);
        		ps12.setString(2,"HQT09"); 
        		ps12.setString(3,tp); 
        		rst12 = ps12.executeQuery();
        		
        		if (rst12.next())
        		txt7= rst12.getString(1)+", TIME: "+rst12.getString(2);
        		
        		rst12.close();
        		ps12.close();

//////////////////Month File Loop Starts to accumulate data/////////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
        			ms1 = con.prepareStatement(month);
        			ms1.setInt(1,smon);
        			ms1.setInt(2,emon);
        			ms1.setInt(3,eyear);
        			
        			mrec = ms1.executeQuery();
        			mrec.beforeFirst();
        			while (mrec.next()) // Month file Loop Starts/////////////////////////
        			{	
         			    if (mrec.isFirst())
        			     {
        				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
        			      txt4 = mrec.getString(3)+" "+mrec.getInt(2);
        			      txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        			     }
        		 	  
        	            if (mrec.isLast())
        	             {
        		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
        		          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        		          txt4 = txt4+ " TO "+mrec.getString(3)+" "+mrec.getInt(2);
        	             }
        			  
                }  // Month file Loop End here///////////////////////// 
        			
        			mrec.close();
        			ms1.close();

                
			
///////////////////// Group master ki query/////////////////////
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////
		        	if (utype.equals("PMT"))
		        	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.area_name,a.grp_cd "+
			    		"from( "+
			    		"select depo_code,area_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and ar_cd=? group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,area_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and ar_cd=? group by mcode) as b "+
			    		"on a.mcode = b.mcode where a.grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by a.mgrp,a.mcode ";
			    	}
			    	else
			    	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.area_name "+
			    		"from( "+
			    		"select depo_code,area_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? and ar_cd=? group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,area_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =? and ar_cd=? group by mcode) as b "+
			    		"on a.mcode = b.mcode  order by a.mgrp,a.mcode ";
			    	}
			    			
			    		
			    		ps1 = con.prepareStatement(query1); 
					    ps1.setInt(1,eyear);
					    ps1.setInt(2,emon);
					    ps1.setInt(3,depo_code);
					    ps1.setInt(4,code);
					    ps1.setInt(5,eyear);
					    ps1.setInt(6,smon);
					    ps1.setInt(7,emon);
					    ps1.setInt(8,depo_code);
					    ps1.setInt(9,code);
					    
						rst1 = ps1.executeQuery();
			    	    boolean hprint=false;
			    	    boolean first=true;
			    	    int grp=0;
			    	    String grnm=null;
			    	    rst1 = ps1.executeQuery();
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;

					if (first)
					{
						grp=rst1.getInt(2);
						grnm=rst1.getString(3);
						first=false;
			            txt1="AREA-> "+rst1.getString(15);
					}

					if (grp!=rst1.getInt(2))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);

		                  if ((uv==1)|| (uv==3))
		                  {
				             rfb.setQty2((int)(gmontar+.50));
			        		 rfb.setQty3((int)gmonsal);
			        		 rfb.setQty4(gimonsd);
			        		 rfb.setQty5((int)(gcumtar+.50));
			        		 rfb.setQty6((int)gcumsal);
			        		 rfb.setQty7(gicumsd);
		                  }
		                  if ((uv==2)|| (uv==3))
		                  {
			        		 rfb.setDval2(gmontar);
			        		 rfb.setDval3(gmonsal);
			        		 rfb.setDval4(gimonsd);
			        		 rfb.setDval5(gcumtar);
			        		 rfb.setDval6(gcumsal);
			        		 rfb.setDval7(gicumsd);
		                  }	      

		 	 				 rfb.setNm3(txt1+txt2+txt5);
		 	 				 data.add(rfb);
							 grp=rst1.getInt(2);
							 grnm=rst1.getString(3);
	
							gmontar=0.00;
				            gcumtar=0.00;
				            gmonsal=0.00;
				            gcumsal=0.00;
				            gimonsd=0;
				            gicumsd=0;
					}

	               
		        
////////////// Month File Loop Starts to accumulate data///////////////////////

		 			montar=0.00;
		            monsal=0.00;
		            monsd=0.00;
		            ucumtar=0.00;
		            cumqty=0.00;
		            icumsd=0;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumsd=0.00;
				           		            
		           
		            
	 			           
//////////////////////// Target master ki query///////////////////////////////
/*			         ggmontar = rst1.getDouble(11);
			         gcumtar = gcumtar+rst1.getDouble(13);
*/			            
			         if ((uv==1)||(uv==3))
			         {
			            umontar = rst1.getInt(7);
			            ucumtar = ucumtar+rst1.getInt(9);
		        		monqty =rst1.getInt(8);
		        		cumqty =cumqty+rst1.getInt(10);
			         }
			         if ((uv==2)||(uv==3))
			         {
/*			            montar = rst1.getDouble(11);
			            cumtar = cumtar+rst1.getDouble(13);
		        		monsal =rst1.getDouble(12);
		        		cumsal =cumsal+rst1.getDouble(14);
*/			         }
		          		            
			            montar = rst1.getDouble(11);
			            cumtar = rst1.getDouble(13);
		        		monsal =rst1.getDouble(12);
		        		cumsal =rst1.getDouble(14);
	 			           

////////////////////////////Month File Loop End here//////////////////////////////////
		            if ((uv==1)||(uv==3))
		            {
		             imonsd=(int)monqty-(int)(umontar); 
		             icumsd=(int)cumqty-(int)(ucumtar);
		            }

		            if ((uv==2)||(uv==3))
		            {
		             monsd=monsal-montar; 
		             cumsd=cumsal-cumtar;
		            }
		             
	            if (hprint)
	            {
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
		            rfb.setColor(1);
		             
                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(umontar+.50));
	        		 rfb.setQty3((int)monqty);
	        		 rfb.setQty4(imonsd);
	        		 rfb.setQty5((int)(ucumtar+.50));
	        		 rfb.setQty6((int)cumqty);
	        		 rfb.setQty7(icumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(montar);
	        		 rfb.setDval3(monsal);
	        		 rfb.setDval4(monsd);
	        		 rfb.setDval5(cumtar);
	        		 rfb.setDval6(cumsal);
	        		 rfb.setDval7(cumsd);
                  }	      
	      
		             rfb.setNm2(txt3);
		             rfb.setNm4(txt4);
                     rfb.setNm3(txt1+txt2+txt5);
                     rfb.setLupdate(txt7);
 	 				 
	                 data.add(rfb);
	            }

/*			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
*/			         
		        	 gcumtar = gcumtar+cumtar;
		             gcumsal = gcumsal+cumsal;
		             gmontar = gmontar+montar;
			         gmonsal = gmonsal+monsal;

	            
	            if ((gmonsal-gmontar)>0)
	                 gimonsd=(int)((gmonsal-gmontar)+.50);
	            else
		             gimonsd=(int)((((gmonsal-gmontar)*-1)+.50)*-1);
	            
	            if ((gcumsal-gcumtar)>0)
	            	gicumsd=(int)((gcumsal-gcumtar)+.50);
	            else
		            gicumsd=(int)((((gcumsal-gcumtar)*-1)+.50)*-1);
				

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+cumtar;
			            tcumsal = tcumsal+cumsal;
			            tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
 	 				
			}///////////////////// Main  Master Loop End here////////////////////////// 

					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);

	                  if ((uv==1)|| (uv==3))
	                  {
			             rfb.setQty2((int)(gmontar+.50));
		        		 rfb.setQty3((int)gmonsal);
		        		 rfb.setQty4(gimonsd);
		        		 rfb.setQty5((int)(gcumtar+.50));
		        		 rfb.setQty6((int)gcumsal);
		        		 rfb.setQty7(gicumsd);
	                  }
	                  if ((uv==2)|| (uv==3))
	                  {
		        		 rfb.setDval2(gmontar);
		        		 rfb.setDval3(gmonsal);
		        		 rfb.setDval4(gimonsd);
		        		 rfb.setDval5(gcumtar);
		        		 rfb.setDval6(gcumsal);
		        		 rfb.setDval7(gicumsd);
	                  }	      

	 	 				 rfb.setNm3(txt1+txt2+txt5);
	 	 				 data.add(rfb);

						gmontar=0.00;
			            gcumtar=0.00;
			            gmonsal=0.00;
			            gcumsal=0.00;
			            gimonsd=0;
			            gicumsd=0;
			            
				ps1.close();
				rst1.close();
				
///////////////////////////// All Statment Close/////////////////////////////
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);

	                  if ((uv==1)|| (uv==3))
	                  {
			             rfb.setQty2((int)(tmontar+.50));
		        		 rfb.setQty3((int)tmonsal);
		        		 rfb.setQty4(timonsd);
		        		 rfb.setQty5((int)(tcumtar+.50));
		        		 rfb.setQty6((int)tcumsal);
		        		 rfb.setQty7(ticumsd);
	                  }
	                  if ((uv==2)|| (uv==3))
	                  {
		        		 rfb.setDval2(tmontar);
		        		 rfb.setDval3(tmonsal);
		        		 rfb.setDval4(timonsd);
		        		 rfb.setDval5(tcumtar);
		        		 rfb.setDval6(tcumsal);
		        		 rfb.setDval7(ticumsd);
	                  }	      

 	 				 rfb.setNm3(txt1+txt2+txt5);
				
 	 				data.add(rfb);
		 
		}
		catch (Exception e) {
			
			System.out.println("========Exception in SQLForm10DAO.getAREA " + e); 
		}
		  finally 
			{
			  try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ps1.close();}
		             if(mrec != null){ mrec.close();}
		             if(ms1 != null){ms1.close();}
		             if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm10DAO.Connection.close "+e);
				}
			}
		return data;
	}		
	
	
	
/////////////////////////////////////// Area Coding End here///////////////////////////////////////////	
	
/////////////////////////////////////// Branch Coding Start here///////////////////////////////////////////
	
	public List getAllBranch(Connection con, int uv,int sale,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
		PreparedStatement ps12 = null;
		ResultSet rst12 = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		PreparedStatement ms1 = null;
		ResultSet mrec = null;
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try { 
            String tblnm=null;
            String query1=null; 
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt6=null;
            String txt5 =null;
            String txt7=null;

/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double umontar=0.00;
            double ucumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double monsd=0.00;
            double cumsd=0.00;
            double monqty=0.00;
            double cumqty=0.00;
///////////////Group Total ke liye/////////////////
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            int gimonsd=0;
            int gicumsd=0;
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            int timonsd=0;
            int ticumsd=0; 
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;

    	    tblnm=(tp+"_repfinal").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
   	        
            if (uv==1) 
            {
            	txt6="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt6="VALUE-WISE";
            }
        
            if (uv==3)
            {
            	txt6="UNIT/VALUE - WISE";
            }

            qg=",sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv  ";

        	txt6=txt6+" NET SALE";
	    	txt2="     PRODUCT WISE "+txt6+" TARGET SALE COMPARISON FROM "; 

//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
        		String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
        		ps12 = con.prepareStatement(query12);
        		ps12.setInt(1,depo_code);
        		ps12.setString(2,"HQT09"); 
        		ps12.setString(3,tp); 
        		rst12 = ps12.executeQuery();
        		
        		if (rst12.next())
        		txt7= rst12.getString(1)+", TIME: "+rst12.getString(2);
        		
        		rst12.close();
        		ps12.close();

//////////////////Month File Loop Starts to accumulate data/////////////////////////
                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
        			ms1 = con.prepareStatement(month);
        			ms1.setInt(1,smon);
        			ms1.setInt(2,emon);
        			ms1.setInt(3,eyear);
        			
        			mrec = ms1.executeQuery();
        			mrec.beforeFirst();
        			while (mrec.next()) // Month file Loop Starts/////////////////////////
        			{	
         			    if (mrec.isFirst())
        			     {
        				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
        			      txt4 = mrec.getString(3)+" "+mrec.getInt(2);
        			      txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        			     }
        		 	  
        	            if (mrec.isLast())
        	             {
        		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
        		          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
        		          txt4 = txt4+ " TO "+mrec.getString(3)+" "+mrec.getInt(2);
        	             }
        			  
                }  // Month file Loop End here///////////////////////// 
        			
        			mrec.close();
        			ms1.close();

        		
			
///////////////////// Group master ki query/////////////////////            
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////            
		        	if (utype.equals("PMT"))
		        	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.br_name,a.grp_cd "+
			    		"from( "+
			    		"select depo_code,br_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =? group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,br_name,mgrp,grp_cd,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =?  group by mcode) as b "+
			    		"on a.mcode = b.mcode where a.grp_cd in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by a.mgrp,a.mcode ";
			    	}
			    	else
			    	{
			    		query1="select a.depo_code,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+",a.br_name "+
			    		"from( "+
			    		"select depo_code,br_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+" "+ 
			    		"where mkt_year = ? and  mnth_code = ? and depo_CODE =?  group by mcode) as a "+
			    		"left join "+
			    		"(select depo_code,br_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+ 
			    		" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ? and depo_CODE =?  group by mcode) as b "+
			    		"on a.mcode = b.mcode  order by a.mgrp,a.mcode ";
			    	}
			    			
			    		
			    		ps1 = con.prepareStatement(query1); 
					    ps1.setInt(1,eyear);
					    ps1.setInt(2,emon);
					    ps1.setInt(3,depo_code);
					    ps1.setInt(4,eyear);
					    ps1.setInt(5,smon);
					    ps1.setInt(6,emon);
					    ps1.setInt(7,depo_code);
					    
						rst1 = ps1.executeQuery();
			    	    boolean hprint=false;
			    	    boolean first=true;
			    	    int grp=0;
			    	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;

					if (first)
					{
						grp=rst1.getInt(2);
						grnm=rst1.getString(3);
						first=false;
			            txt1="BRANCH-> "+rst1.getString(15);
					}
		        
					if (grp!=rst1.getInt(2))
					{
						 rfb = new MktFormBean();
						 rfb.setMname("VALUE OF "+grnm);
			             rfb.setColor(2);

		                  if ((uv==1)|| (uv==3))
		                  {
				             rfb.setQty2((int)(gmontar+.50));
			        		 rfb.setQty3((int)gmonsal);
			        		 rfb.setQty4(gimonsd);
			        		 rfb.setQty5((int)(gcumtar+.50));
			        		 rfb.setQty6((int)gcumsal);
			        		 rfb.setQty7(gicumsd);
		                  }
		                  if ((uv==2)|| (uv==3))
		                  {
			        		 rfb.setDval2(gmontar);
			        		 rfb.setDval3(gmonsal);
			        		 rfb.setDval4(gimonsd);
			        		 rfb.setDval5(gcumtar);
			        		 rfb.setDval6(gcumsal);
			        		 rfb.setDval7(gicumsd);
		                  }	      

		 	 				 rfb.setNm3(txt1+txt2+txt5);
		 	 				 data.add(rfb);
							 grp=rst1.getInt(2);
							 grnm=rst1.getString(3);
	
							gmontar=0.00;
				            gcumtar=0.00;
				            gmonsal=0.00;
				            gcumsal=0.00;
				            gimonsd=0;
				            gicumsd=0;
					}

		        

		 			montar=0.00;
		            monsal=0.00;
		            monsd=0.00;
		            ucumtar=0.00;
		            cumqty=0.00;
		            icumsd=0;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumsd=0.00;
				            		            
//////////////////////// Target master ki query///////////////////////////////
/*				         ggmontar = rst1.getDouble(11);
				         gcumtar = gcumtar+rst1.getDouble(13);
*/				            
				         if ((uv==1)||(uv==3))
				         {
				            umontar = rst1.getInt(7);
				            ucumtar = ucumtar+rst1.getInt(9);
			        		monqty =rst1.getInt(8);
			        		cumqty =cumqty+rst1.getInt(10);
				         }
				         if ((uv==2)||(uv==3))
				         {
/*				            montar = rst1.getDouble(11);
				            cumtar = cumtar+rst1.getDouble(13);
			        		monsal =rst1.getDouble(12);
			        		cumsal =cumsal+rst1.getDouble(14);
*/				         }
	 			           
				            montar = rst1.getDouble(11);
				            cumtar = rst1.getDouble(13);
			        		monsal =rst1.getDouble(12);
			        		cumsal =rst1.getDouble(14);
			        
			      			        

		 			
		            if ((uv==1)||(uv==3))
		            {
		             imonsd=(int)monqty-(int)(umontar); 
		             icumsd=(int)cumqty-(int)(ucumtar);
		            }

		            if ((uv==2)||(uv==3))
		            {
		             monsd=monsal-montar; 
		             cumsd=cumsal-cumtar;
		            }
		             
	            if (hprint)
	            {
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(4));
					rfb.setMname(rst1.getString(5));
					rfb.setPack(rst1.getString(6));
					rfb.setUv(uv);
		            rfb.setColor(1);
		             
                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(umontar+.50));
	        		 rfb.setQty3((int)monqty);
	        		 rfb.setQty4(imonsd);
	        		 rfb.setQty5((int)(ucumtar+.50));
	        		 rfb.setQty6((int)cumqty);
	        		 rfb.setQty7(icumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(montar);
	        		 rfb.setDval3(monsal);
	        		 rfb.setDval4(monsd);
	        		 rfb.setDval5(cumtar);
	        		 rfb.setDval6(cumsal);
	        		 rfb.setDval7(cumsd);
                  }	      
	      
		             rfb.setNm2(txt3);
		             rfb.setNm4(txt4);
                     rfb.setNm3(txt1+txt2+txt5);
                     rfb.setLupdate(txt7);
 	 				 
	                 data.add(rfb);
	            }

/*			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
*/			         

		        	gcumtar = gcumtar+cumtar;
		            gcumsal = gcumsal+cumsal;
		            gmontar = gmontar+montar;
			        gmonsal = gmonsal+monsal;
				    
	            if ((gmonsal-gmontar)>0)
	                 gimonsd=(int)((gmonsal-gmontar)+.50);
	            else
		             gimonsd=(int)((((gmonsal-gmontar)*-1)+.50)*-1);
	            
	            if ((gcumsal-gcumtar)>0)
	            	gicumsd=(int)((gcumsal-gcumtar)+.50);
	            else
		            gicumsd=(int)((((gcumsal-gcumtar)*-1)+.50)*-1);
				

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+cumtar;
			            tcumsal = tcumsal+cumsal;
			            tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);

                  if ((uv==1)|| (uv==3))
                  {
		             rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)gmonsal);
	        		 rfb.setQty4(gimonsd);
	        		 rfb.setQty5((int)(gcumtar+.50));
	        		 rfb.setQty6((int)gcumsal);
	        		 rfb.setQty7(gicumsd);
                  }
                  if ((uv==2)|| (uv==3))
                  {
	        		 rfb.setDval2(gmontar);
	        		 rfb.setDval3(gmonsal);
	        		 rfb.setDval4(gimonsd);
	        		 rfb.setDval5(gcumtar);
	        		 rfb.setDval6(gcumsal);
	        		 rfb.setDval7(gicumsd);
                  }	      

	 				 rfb.setNm3(txt1+txt2+txt5);
			
	 				data.add(rfb);
				ps1.close();
				rst1.close();

///////////////////////////// All Statment Close/////////////////////////////
	            
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);

	                  if ((uv==1)|| (uv==3))
	                  {
			             rfb.setQty2((int)(tmontar+.50));
		        		 rfb.setQty3((int)tmonsal);
		        		 rfb.setQty4(timonsd);
		        		 rfb.setQty5((int)(tcumtar+.50));
		        		 rfb.setQty6((int)tcumsal);
		        		 rfb.setQty7(ticumsd);
	                  }
	                  if ((uv==2)|| (uv==3))
	                  {
		        		 rfb.setDval2(tmontar);
		        		 rfb.setDval3(tmonsal);
		        		 rfb.setDval4(timonsd);
		        		 rfb.setDval5(tcumtar);
		        		 rfb.setDval6(tcumsal);
		        		 rfb.setDval7(ticumsd);
	                  }	      

 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
		 
		}
		catch (Exception e) {
			
			System.out.println("========Exception in SQLForm10DAO.getBranch " + e); 
		}
		  finally 
			{
			  try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ps1.close();}
		             if(mrec != null){ mrec.close();}
		             if(ms1 != null){ms1.close();}
		             if(con != null){con.close();}
				}  
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm10DAO.Connection.close "+e);
				}
			}
		return data;
	}		
/////////////////////////////////////// Branch Coding End here///////////////////////////////////////////	
}