package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm4DAO {

	public List getAllHQ(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        	   

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm3=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;

///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;

            int gimonsd=0;
            int gicumsd=0;
            
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            double tlstsal=0.00;
            double tmonach=0.00;
            double tcumach=0.00;
            double tcumgth=0.00;
            double tpmr=0.00;

            int timonsd=0;
            int ticumsd=0; 
            int trep=0;
            int imonsd=0;
            int icumsd=0;
            
            if (smon>emon)
            	emon=smon;

   	 	    tblnm=(tp+"_repfinal").toLowerCase();
 	        tblnm3=(tp+"_tr_pmr").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
            String qg3=null;
   	        
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }

            qg=",sum(lysqty) lysqty,sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(lysqty) clysqty,sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval,sum(lysval) clysval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,b.clysqty clys,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv,b.clysval clysv  ";
	    	qg3=",c.mtar,c.msal,c.ctar,c.csal,c.clys,c.mtarv,c.msalv,c.ctarv,c.csalv,c.clysv  ";
            
            txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM "; 
            
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
	 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
	 			    
		            if (mrec.isLast())
			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
 			  
	        }  // Month file Loop End here///////////////////////// 
 			
 			mrec.close();
 			ms1.close();
    		
			
///////////////////// Group master ki query/////////////////////   
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            glstsal=0.00;
		            gmonach=0.00;
		            gcumach=0.00;
		            gcumgth=0.00;
		            gpmr=0.00;

		            gimonsd=0;
		            gicumsd=0;
				    
 //////////////////////////// Product master ki query/////////////////////////////
//				query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";
				query1 = "SELECT c.depo_code,c.mgrp,c.mgrp_name,c.mcode,c.pname,c.pack"+qg3+",D.REP,c.ter_name "+
				"FROM "+
				"(select a.depo_code,a.ter_name,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+
				" from( "+
				"select depo_code,ter_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+
				" where mkt_year = ? and  mnth_code = ? and depo_CODE =? and tr_cd=? "+
				"group by mcode) as a "+
				"left join "+
				"(select depo_code,ter_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+"from "+tblnm+" where mkt_year = ? "+
				"and  mnth_code >= ? and  mnth_code <= ? and depo_CODE =? and tr_cd=? "+
				"group by mcode) as b "+
				"on a.mcode = b.mcode) C, "+
				"(SELECT depo_code,ter_code,SUM(REP) REP FROM "+tblnm3+" "+
				"WHERE MKT_YEAR = ? AND MNTH_CODE >= ? AND MNTH_CODE <= ? and depo_code=? and ter_code=? "+
				"GROUP BY depo_code,ter_code) D "+
				"WHERE  "+
				"C.depo_code = D.depo_CODE order by c.mgrp,c.mgrp_name,c.mcode,c.pname ";
				
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
			    ps1.setInt(10,eyear);
			    ps1.setInt(11,smon);
			    ps1.setInt(12,emon);
			    ps1.setInt(13,depo_code);
			    ps1.setInt(14,code);
			    
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
			            txt1="HQ-> "+rst1.getString(18);
					}
	                
					if (grp!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
	        		 rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)(gmonsal+.50));
	        		 rfb.setQty4((int)gmonach);
	        		 rfb.setQty5(gimonsd);
	        		 rfb.setQty6((int)(gcumtar+.50));
	        		 rfb.setQty7((int)(gcumsal+.50));
	        		 rfb.setQty8((int)gcumach);
	        		 rfb.setQty9(gicumsd);
	        		 rfb.setQty10((int)(glstsal+.50));
	        		 rfb.setDval1(gcumgth);
	        		 rfb.setQty11((int)(gpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
					 grp=rst1.getInt(2);
					 grnm=rst1.getString(3);

	 		         gmontar=0.00;
			         gcumtar=0.00;
			         gmonsal=0.00;
			         gcumsal=0.00;
			         glstsal=0.00;
			         gmonach=0.00;
			         gcumach=0.00;
			         gcumgth=0.00;
			         gpmr=0.00;
			         gimonsd=0;
			         gicumsd=0;
					}

		        
		 			trep=0;
		 			montar=0.00;
		            monsal=0.00;
		            monach=0.00;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumach=0.00;
		            lstsal=0.00;
		            cumgth=0.00;
		            pmr=0.00;
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            
	 			           
//////////////////////// Target master ki query///////////////////////////////
	 			           
/*					      ggmontar = rst1.getDouble(11);
					      ggmonsal = rst1.getDouble(12);
 					      gcumtar = gcumtar+rst1.getDouble(13);
					      gcumsal = gcumsal+rst1.getDouble(14);
					      glstsal = glstsal+rst1.getDouble(15);
*/					      
		            	  trep=rst1.getInt(17);
					      
						    if (uv==1)
						    {
						      montar = rst1.getDouble(7);
						      monsal = rst1.getDouble(8);
	 					      cumtar = rst1.getDouble(9);
						      cumsal = rst1.getDouble(10);
						      lstsal = rst1.getDouble(11);
						      
						    }
						    if (uv==2)
						    {						    
						      montar = rst1.getDouble(12);
						      monsal = rst1.getDouble(13);
	 					      cumtar = rst1.getDouble(14);
						      cumsal = rst1.getDouble(15);
						      lstsal = rst1.getDouble(16);
						    }
					    
			        
			      			        
		 			if (montar!=0)
		 				monach = (monsal/montar)*100;
		 			if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		               pmr = cumsal/trep;
		            
		            if (monach>0)
		            	monach=monach+.50;
		            else
		            {
		               monach=((monach*-1)+.50)*-1;	
		            }
		            
		            if (cumach>0)
		            	cumach=cumach+.50;
		            else
		            {
		               cumach=((cumach*-1)+.50)*-1;	
		            }
		            
		             imonsd=(int)monsal-(int)(montar+.50); 
		             icumsd=(int)cumsal-(int)(cumtar+.50);
		             
			          if (hprint)
			          {
						rfb = new MktFormBean();
						rfb.setMcode(rst1.getInt(4));
						rfb.setMname(rst1.getString(5));
						rfb.setPack(rst1.getString(6));
						rfb.setUv(uv);
			            rfb.setColor(1);
		        		rfb.setQty2((int)(montar+.50));
		        		rfb.setQty3((int)monsal);
		        		rfb.setQty4((int)monach);
		        		rfb.setQty5(imonsd);
		        		rfb.setQty6((int)(cumtar+.50));
		        		rfb.setQty7((int)cumsal);
		        		rfb.setQty8((int)cumach);
		        		rfb.setQty9(icumsd);
		        		rfb.setQty10((int)lstsal);
		        		rfb.setDval1(cumgth);
		        		rfb.setQty11((int)(pmr+.50));
	 	 				rfb.setNm3(txt1+txt2+txt5);
	 	 				rfb.setLupdate(txt6);
 	 				 
	 	 				data.add(rfb);
			          }

/*			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+ggmonsal;
*/
				      montar = rst1.getDouble(12);
				      monsal = rst1.getDouble(13);
					  cumtar = rst1.getDouble(14);
				      cumsal = rst1.getDouble(15);
				      lstsal = rst1.getDouble(16);

				      gcumtar = gcumtar+cumtar;
			          gcumsal = gcumsal+cumsal;
			          glstsal = glstsal+lstsal;		
 	 				  gmontar = gmontar+montar;
				      gmonsal = gmonsal+monsal;

				
	 			if (gmontar!=0)
	 				gmonach = (gmonsal/gmontar)*100;
	 			if (gcumtar!=0)
	            	gcumach = (gcumsal/gcumtar)*100;
	            if (glstsal!=0)
	            	gcumgth = ((gcumsal/glstsal)*100)-100;
	            if (trep!=0)
	               gpmr = gcumsal/trep;
	            
	            if (gmonach>0)
	            	gmonach=gmonach+.50;
	            else
	            {
	               gmonach=((gmonach*-1)+.50)*-1;	
	            }
	            
	            if (gcumach>0)
	            	gcumach=gcumach+.50;
	            else
	            {
	               gcumach=((gcumach*-1)+.50)*-1;	
	            }
	            
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
			            tlstsal = tlstsal+lstsal;		
 	 				    tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
		}///////////////////// Main Query Loop End here////////////////////////// 

				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
        		 rfb.setQty2((int)(gmontar+.50));
        		 rfb.setQty3((int)(gmonsal+.50));
        		 rfb.setQty4((int)gmonach);
        		 rfb.setQty5(gimonsd);
        		 rfb.setQty6((int)(gcumtar+.50));
        		 rfb.setQty7((int)(gcumsal+.50));
        		 rfb.setQty8((int)gcumach);
        		 rfb.setQty9(gicumsd);
        		 rfb.setQty10((int)(glstsal+.50));
        		 rfb.setDval1(gcumgth);
        		 rfb.setQty11((int)(gpmr+.50));
 				 rfb.setNm3(txt1+txt2+txt5);
 				 data.add(rfb);

 		         gmontar=0.00;
		         gcumtar=0.00;
		         gmonsal=0.00;
		         gcumsal=0.00;
		         glstsal=0.00;
		         gmonach=0.00;
		         gcumach=0.00;
		         gcumgth=0.00;
		         gpmr=0.00;
		         gimonsd=0;
		         gicumsd=0;
		         
		         ps1.close();
		         rst1.close();
///////////////////////////// All Statment Close/////////////////////////////
				
	 			if (tmontar!=0)
	 				tmonach = (tmonsal/tmontar)*100;
	 			if (tcumtar!=0)
	            	tcumach = (tcumsal/tcumtar)*100;
	            if (tlstsal!=0)
	            	tcumgth = ((tcumsal/tlstsal)*100)-100;
	            if (trep!=0)
	               tpmr = tcumsal/trep;
	            
	            if (tmonach>0)
	            	tmonach=tmonach+.50;
	            else
	            {
	               tmonach=((tmonach*-1)+.50)*-1;	
	            }
	            if (tcumach>0)
	            	tcumach=tcumach+.50;
	            else
	            {
	               tcumach=((tcumach*-1)+.50)*-1;	
	            }
	            
	            
	            
	            
	            
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
	        		 rfb.setQty2((int)(tmontar+.50));
	        		 rfb.setQty3((int)(tmonsal+.50));
	        		 timonsd=(int)((tmonsal-tmontar)+.50);
	        		 timonsd=(int)((tmonsal-tmontar)+.50);
	        		 timonsd=(int)((tmonsal-tmontar)+.50);
	        		 timonsd=(int)((tmonsal-tmontar)+.50);
	        		 timonsd=(int)((tmonsal-tmontar)+.50);
	        		 rfb.setQty4((int)tmonach);
	        		 rfb.setQty5(timonsd);
	        		 rfb.setQty6((int)(tcumtar+.50));
	        		 rfb.setQty7((int)(tcumsal+.50));
	        		 rfb.setQty8((int)tcumach);
	        		 rfb.setQty9(ticumsd);
	        		 rfb.setQty10((int)(tlstsal+.50));
	        		 rfb.setDval1(tcumgth);
	        		 rfb.setQty11((int)(tpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm4DAO.HQWise.getForm4 " + e); 
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
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}  

//////////////////////////////////////////Region ke liye Start here/////////////////////////////////////	
	public List getMRegion(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
		
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;


		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;

///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;

            int gimonsd=0;
            int gicumsd=0;
            
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            double tlstsal=0.00;
            double tmonach=0.00;
            double tcumach=0.00;
            double tcumgth=0.00;
            double tpmr=0.00;

            int timonsd=0;
            int ticumsd=0; 
            
            int trep=0;
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;

   	 	    tblnm=(tp+"_repfinal").toLowerCase();
 	        tblnm3=(tp+"_tr_pmr").toLowerCase();

            String qg=null;
            String qg1=null;
            String qg2=null;
            String qg3=null;
   	        
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }

            qg=",sum(lysqty) lysqty,sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(lysqty) clysqty,sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval,sum(lysval) clysval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,b.clysqty clys,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv,b.clysval clysv  ";
	    	qg3=",c.mtar,c.msal,c.ctar,c.csal,c.clys,c.mtarv,c.msalv,c.ctarv,c.csalv,c.clysv  ";
            
            
            txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM "; 

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
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			    
	            if (mrec.isLast())
		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
			  
        }  // Month file Loop End here///////////////////////// 
			
			mrec.close();
			ms1.close();
		

    			
///////////////////// Group master ki query/////////////////////      
		
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            glstsal=0.00;
		            gmonach=0.00;
		            gcumach=0.00;
		            gcumgth=0.00;
		            gpmr=0.00;

		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////            
					String query1 = "SELECT c.depo_code,c.mgrp,c.mgrp_name,c.mcode,c.pname,c.pack"+qg3+",D.REP,c.reg_name "+
					"FROM "+
					"(select a.depo_code,a.reg_name,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+
					" from( "+
					"select depo_code,reg_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+
					" where mkt_year = ? and  mnth_code = ? and depo_CODE =? and rg_cd=? "+
					"group by mcode) as a "+
					"left join "+
					"(select depo_code,reg_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+"from "+tblnm+" where mkt_year = ? "+
					"and  mnth_code >= ? and  mnth_code <= ? and depo_CODE =? and rg_cd=? "+
					"group by mcode) as b "+
					"on a.mcode = b.mcode) C, "+
					"(SELECT depo_code,regn_code,SUM(REP) REP FROM "+tblnm3+" "+
					"WHERE MKT_YEAR = ? AND MNTH_CODE >= ? AND MNTH_CODE <= ? and depo_code=? and regn_code=? "+
					"GROUP BY depo_code,regn_code) D "+
					"WHERE  "+
					"C.depo_code = D.depo_CODE order by c.mgrp,c.mgrp_name,c.mcode,c.pname ";
					
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
				    ps1.setInt(10,eyear);
				    ps1.setInt(11,smon);
				    ps1.setInt(12,emon);
				    ps1.setInt(13,depo_code);
				    ps1.setInt(14,code);
	
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
			            txt1="REGION-> "+rst1.getString(18);
					}
					
					if (grp!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
	        		 rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)(gmonsal+.50));
	        		 rfb.setQty4((int)gmonach);
	        		 rfb.setQty5(gimonsd);
	        		 rfb.setQty6((int)(gcumtar+.50));
	        		 rfb.setQty7((int)(gcumsal+.50));
	        		 rfb.setQty8((int)gcumach);
	        		 rfb.setQty9(gicumsd);
	        		 rfb.setQty10((int)(glstsal+.50));
	        		 rfb.setDval1(gcumgth);
	        		 rfb.setQty11((int)(gpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
 	 				 
 	 				 grp=rst1.getInt(2);
					 grnm=rst1.getString(3);

	 		         gmontar=0.00;
			         gcumtar=0.00;
			         gmonsal=0.00;
			         gcumsal=0.00;
			         glstsal=0.00;
			         gmonach=0.00;
			         gcumach=0.00;
			         gcumgth=0.00;
			         gpmr=0.00;
			         gimonsd=0;
			         gicumsd=0;
					}

					trep=0;
		 			montar=0.00;
		            monsal=0.00;
		            monach=0.00;
		 			cumtar=0.00;
		            cumsal=0.00;
		            cumach=0.00;
		            lstsal=0.00;
		            cumgth=0.00;
		            pmr=0.00;
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            
/*				      ggmontar = rst1.getDouble(11);
				      ggmonsal = rst1.getDouble(12);
					  gcumtar = gcumtar+rst1.getDouble(13);
				      gcumsal = gcumsal+rst1.getDouble(14);
				      glstsal = glstsal+rst1.getDouble(15);
*/
				      trep=rst1.getInt(17);
				      
					    if (uv==1)
					    {
					      montar = rst1.getDouble(7);
					      monsal = rst1.getDouble(8);
 					      cumtar = rst1.getDouble(9);
					      cumsal = rst1.getDouble(10);
					      lstsal = rst1.getDouble(11);
					      
					    }
					    if (uv==2)
					    {						    
					      montar = rst1.getDouble(12);
					      monsal = rst1.getDouble(13);
 					      cumtar = rst1.getDouble(14);
					      cumsal = rst1.getDouble(15);
					      lstsal = rst1.getDouble(16);
					    }
				    
							    
			        
			      			        
		 			
		 			if (montar!=0)
		 				monach = (monsal/montar)*100;
		 			if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		               pmr = cumsal/trep;
		            
		            if (monach>0)
		            	monach=monach+.50;
		            else
		            {
		               monach=((monach*-1)+.50)*-1;	
		            }
		            
		            if (cumach>0)
		            	cumach=cumach+.50;
		            else
		            {
		               cumach=((cumach*-1)+.50)*-1;	
		            }
		            
		             imonsd=(int)monsal-(int)(montar+.50); 
		             icumsd=(int)cumsal-(int)(cumtar+.50);
		          if (hprint)
		          {
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(4));
					 rfb.setMname(rst1.getString(5));
					 rfb.setPack(rst1.getString(6));
					 rfb.setUv(uv);
		             rfb.setColor(1);
	        		 rfb.setQty2((int)(montar+.50));
	        		 rfb.setQty3((int)monsal);
	        		 rfb.setQty4((int)monach);
	        		 rfb.setQty5(imonsd);
	        		 rfb.setQty6((int)(cumtar+.50));
	        		 rfb.setQty7((int)cumsal);
	        		 rfb.setQty8((int)cumach);
	        		 rfb.setQty9(icumsd);
	        		 rfb.setQty10((int)lstsal);
	        		 rfb.setDval1(cumgth);
	        		 rfb.setQty11((int)(pmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);
 	 				 
	                 data.add(rfb);
		          }

				      montar = rst1.getDouble(12);
				      monsal = rst1.getDouble(13);
				      cumtar = rst1.getDouble(14);
				      cumsal = rst1.getDouble(15);
				      lstsal = rst1.getDouble(16);

		        	  gcumtar = gcumtar+cumtar;
		              gcumsal = gcumsal+cumsal;
		              glstsal = glstsal+lstsal;		
 				      gmontar = gmontar+montar;
			          gmonsal = gmonsal+monsal;

				
				
	 			if (gmontar!=0)
	 				gmonach = (gmonsal/gmontar)*100;
	 			if (gcumtar!=0)
	            	gcumach = (gcumsal/gcumtar)*100;
	            if (glstsal!=0)
	            	gcumgth = ((gcumsal/glstsal)*100)-100;
	            if (trep!=0)
	               gpmr = gcumsal/trep;
	            
	            if (gmonach>0)
	            	gmonach=gmonach+.50;
	            else
	            {
	               gmonach=((gmonach*-1)+.50)*-1;	
	            }
	            
	            if (gcumach>0)
	            	gcumach=gcumach+.50;
	            else
	            {
	               gcumach=((gcumach*-1)+.50)*-1;	
	            }
	            
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
			            tlstsal = tlstsal+lstsal;		
 	 				    tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
        		 rfb.setQty2((int)(gmontar+.50));
        		 rfb.setQty3((int)(gmonsal+.50));
        		 rfb.setQty4((int)gmonach);
        		 rfb.setQty5(gimonsd);
        		 rfb.setQty6((int)(gcumtar+.50));
        		 rfb.setQty7((int)(gcumsal+.50));
        		 rfb.setQty8((int)gcumach);
        		 rfb.setQty9(gicumsd);
        		 rfb.setQty10((int)(glstsal+.50));
        		 rfb.setDval1(gcumgth);
        		 rfb.setQty11((int)(gpmr+.50));
 				 rfb.setNm3(txt1+txt2+txt5);
 				 data.add(rfb);
	 				 

 		         gmontar=0.00;
		         gcumtar=0.00;
		         gmonsal=0.00;
		         gcumsal=0.00;
		         glstsal=0.00;
		         gmonach=0.00;
		         gcumach=0.00;
		         gcumgth=0.00;
		         gpmr=0.00;
		         gimonsd=0;
		         gicumsd=0;

		         ps1.close();
				 rst1.close();
				
///////////////////////////// All Statment Close/////////////////////////////

	 			if (tmontar!=0)
	 				tmonach = (tmonsal/tmontar)*100;
	 			if (tcumtar!=0)
	            	tcumach = (tcumsal/tcumtar)*100;
	            if (tlstsal!=0)
	            	tcumgth = ((tcumsal/tlstsal)*100)-100;
	            if (trep!=0)
	               tpmr = tcumsal/trep;
	            
	            if (tmonach>0)
	            	tmonach=tmonach+.50;
	            else
	            {
	               tmonach=((tmonach*-1)+.50)*-1;	
	            }
	            
	            if (tcumach>0)
	            	tcumach=tcumach+.50;
	            else
	            {
	               tcumach=((tcumach*-1)+.50)*-1;	
	            }
	            
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
	        		 rfb.setQty2((int)(tmontar+.50));
	        		 rfb.setQty3((int)(tmonsal+.50));
	        		 rfb.setQty4((int)tmonach);
	        		 rfb.setQty5(timonsd);
	        		 rfb.setQty6((int)(tcumtar+.50));
	        		 rfb.setQty7((int)(tcumsal+.50));
	        		 rfb.setQty8((int)tcumach);
	        		 rfb.setQty9(ticumsd);
	        		 rfb.setQty10((int)(tlstsal+.50));
	        		 rfb.setDval1(tcumgth);
	        		 rfb.setQty11((int)(tpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
				
 	 				data.add(rfb);
 	 				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm4DAO.getForm4 " + e); 
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
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}

//////////////////////////////////////////Region ke liye End here/////////////////////////////////////		
	
//////////////////////////////////////////Area ke liye Start here/////////////////////////////////////	
	public List getMArea(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;

///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;

            int gimonsd=0;
            int gicumsd=0;
            
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            double tlstsal=0.00;
            double tmonach=0.00;
            double tcumach=0.00;
            double tcumgth=0.00;
            double tpmr=0.00;

            int timonsd=0;
            int ticumsd=0; 
            
            int trep=0;
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;

    	    tblnm=(tp+"_repfinal").toLowerCase();
     	    tblnm3=(tp+"_tr_pmr").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
            String qg3=null;
   	        
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }

            qg=",sum(lysqty) lysqty,sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(lysqty) clysqty,sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval,sum(lysval) clysval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,b.clysqty clys,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv,b.clysval clysv  ";
	    	qg3=",c.mtar,c.msal,c.ctar,c.csal,c.clys,c.mtarv,c.msalv,c.ctarv,c.csalv,c.clysv  ";

            
                txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM  "; 
    
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
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			    
	            if (mrec.isLast())
		          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
			  
        }  // Month file Loop End here///////////////////////// 
			
			mrec.close();
			ms1.close();
		
///////////////////// Group master ki query/////////////////////
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            glstsal=0.00;
		            gmonach=0.00;
		            gcumach=0.00;
		            gcumgth=0.00;
		            gpmr=0.00;

		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////            
					String query1 = "SELECT c.depo_code,c.mgrp,c.mgrp_name,c.mcode,c.pname,c.pack"+qg3+",D.REP,c.area_name "+
					"FROM "+
					"(select a.depo_code,a.area_name,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+
					" from( "+
					"select depo_code,area_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+
					" where mkt_year = ? and  mnth_code = ? and depo_CODE =? and ar_cd=? "+
					"group by mcode) as a "+
					"left join "+
					"(select depo_code,area_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+"from "+tblnm+" where mkt_year = ? "+
					"and  mnth_code >= ? and  mnth_code <= ? and depo_CODE =? and ar_cd=? "+
					"group by mcode) as b "+
					"on a.mcode = b.mcode) C, "+
					"(SELECT depo_code,area_code,SUM(REP) REP FROM "+tblnm3+" "+
					"WHERE MKT_YEAR = ? AND MNTH_CODE >= ? AND MNTH_CODE <= ? and depo_code=? and area_code=? "+
					"GROUP BY depo_code,area_code) D "+
					"WHERE  "+
					"C.depo_code = D.depo_CODE order by c.mgrp,c.mgrp_name,c.mcode,c.pname ";
					
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
				    ps1.setInt(10,eyear);
				    ps1.setInt(11,smon);
				    ps1.setInt(12,emon);
				    ps1.setInt(13,depo_code);
				    ps1.setInt(14,code);
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
			            txt1="AREA-> "+rst1.getString(18);
					}

					if (grp!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
	        		 rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)(gmonsal+.50));
	        		 rfb.setQty4((int)gmonach);
	        		 rfb.setQty5(gimonsd);
	        		 rfb.setQty6((int)(gcumtar+.50));
	        		 rfb.setQty7((int)(gcumsal+.50));
	        		 rfb.setQty8((int)gcumach);
	        		 rfb.setQty9(gicumsd);
	        		 rfb.setQty10((int)(glstsal+.50));
	        		 rfb.setDval1(gcumgth);
	        		 rfb.setQty11((int)(gpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
					 grp=rst1.getInt(2);
					 grnm=rst1.getString(3);

	 		         gmontar=0.00;
			         gcumtar=0.00;
			         gmonsal=0.00;
			         gcumsal=0.00;
			         glstsal=0.00;
			         gmonach=0.00;
			         gcumach=0.00;
			         gcumgth=0.00;
			         gpmr=0.00;
			         gimonsd=0;
			         gicumsd=0;
					}

	               
		        
		 			trep=0;
		 			montar=0.00;
		            monsal=0.00;
		            monach=0.00;
		            cumtar=0.00;
		            cumsal=0.00;
		            cumach=0.00;
		            lstsal=0.00;
		            cumgth=0.00;
		            pmr=0.00;
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            
/*				      ggmontar = rst1.getDouble(11);
				      ggmonsal = rst1.getDouble(12);
				      gcumtar = gcumtar+rst1.getDouble(13);
				      gcumsal = gcumsal+rst1.getDouble(14);
				      glstsal = glstsal+rst1.getDouble(15);
*/
				      trep=rst1.getInt(17);
				      
					    if (uv==1)
					    {
					      montar = rst1.getDouble(7);
					      monsal = rst1.getDouble(8);
 					      cumtar = rst1.getDouble(9);
					      cumsal = rst1.getDouble(10);
					      lstsal = rst1.getDouble(11);
					      
					    }
					    if (uv==2)
					    {						    
					      montar = rst1.getDouble(12);
					      monsal = rst1.getDouble(13);
 					      cumtar = rst1.getDouble(14);
					      cumsal = rst1.getDouble(15);
					      lstsal = rst1.getDouble(16);
					    }
				    
			            
		 			

		 			if (montar!=0)
		 				monach = (monsal/montar)*100;
		 			if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		               pmr = cumsal/trep;
		            
		            if (monach>0)
		            	monach=monach+.50;
		            else
		            {
		               monach=((monach*-1)+.50)*-1;	
		            }
		            
		            if (cumach>0)
		            	cumach=cumach+.50;
		            else
		            {
		               cumach=((cumach*-1)+.50)*-1;	
		            }
		            
		             imonsd=(int)monsal-(int)(montar+.50); 
		             icumsd=(int)cumsal-(int)(cumtar+.50);
		             
		          if (hprint)
		          {
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(4));
		 			 rfb.setMname(rst1.getString(5));
	  				 rfb.setPack(rst1.getString(6));
 	 				 rfb.setUv(uv);
		             rfb.setColor(1);
	        		 rfb.setQty2((int)(montar+.50));
	        		 rfb.setQty3((int)monsal);
	        		 rfb.setQty4((int)monach);
	        		 rfb.setQty5(imonsd);
	        		 rfb.setQty6((int)(cumtar+.50));
	        		 rfb.setQty7((int)cumsal);
	        		 rfb.setQty8((int)cumach);
	        		 rfb.setQty9(icumsd);
	        		 rfb.setQty10((int)lstsal);
	        		 rfb.setDval1(cumgth);
	        		 rfb.setQty11((int)(pmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);
	                 data.add(rfb);
		          }

			      montar = rst1.getDouble(12);
			      monsal = rst1.getDouble(13);
			      cumtar = rst1.getDouble(14);
			      cumsal = rst1.getDouble(15);
			      lstsal = rst1.getDouble(16);

	        	  gcumtar = gcumtar+cumtar;
	              gcumsal = gcumsal+cumsal;
	              glstsal = glstsal+lstsal;		
				  gmontar = gmontar+montar;
		          gmonsal = gmonsal+monsal;
				
	 			if (gmontar!=0)
	 				gmonach = (gmonsal/gmontar)*100;
	 			if (gcumtar!=0)
	            	gcumach = (gcumsal/gcumtar)*100;
	            if (glstsal!=0)
	            	gcumgth = ((gcumsal/glstsal)*100)-100;
	            if (trep!=0)
	               gpmr = gcumsal/trep;
	            
	            if (gmonach>0)
	            	gmonach=gmonach+.50;
	            else
	            {
	               gmonach=((gmonach*-1)+.50)*-1;	
	            }
	            
	            if (gcumach>0)
	            	gcumach=gcumach+.50;
	            else
	            {
	               gcumach=((gcumach*-1)+.50)*-1;	
	            }
	            
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
			            tlstsal = tlstsal+lstsal;		
 	 				    tmontar = tmontar+montar;
				        tmonsal = tmonsal+monsal;
 	 				
		}///////////////////// Main Query Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
        		 rfb.setQty2((int)(gmontar+.50));
        		 rfb.setQty3((int)(gmonsal+.50));
        		 rfb.setQty4((int)gmonach);
        		 rfb.setQty5(gimonsd);
        		 rfb.setQty6((int)(gcumtar+.50));
        		 rfb.setQty7((int)(gcumsal+.50));
        		 rfb.setQty8((int)gcumach);
        		 rfb.setQty9(gicumsd);
        		 rfb.setQty10((int)(glstsal+.50));
        		 rfb.setDval1(gcumgth);
        		 rfb.setQty11((int)(gpmr+.50));
 				 rfb.setNm3(txt1+txt2+txt5);
 				 data.add(rfb);

 		         gmontar=0.00;
		         gcumtar=0.00;
		         gmonsal=0.00;
		         gcumsal=0.00;
		         glstsal=0.00;
		         gmonach=0.00;
		         gcumach=0.00;
		         gcumgth=0.00;
		         gpmr=0.00;
		         gimonsd=0;
		         gicumsd=0;

		         ps1.close();
				 rst1.close();
///////////////////////////// All Statment Close/////////////////////////////

	 			if (tmontar!=0)
	 				tmonach = (tmonsal/tmontar)*100;
	 			if (tcumtar!=0)
	            	tcumach = (tcumsal/tcumtar)*100;
	            if (tlstsal!=0)
	            	tcumgth = ((tcumsal/tlstsal)*100)-100;
	            if (trep!=0)
	               tpmr = tcumsal/trep;
	            
	            if (tmonach>0)
	            	tmonach=tmonach+.50;
	            else
	            {
	               tmonach=((tmonach*-1)+.50)*-1;	
	            }
	            
	            if (tcumach>0)
	            	tcumach=tcumach+.50;
	            else
	            {
	               tcumach=((tcumach*-1)+.50)*-1;	
	            }
	            
                    timonsd=(int)((tmonsal-tmontar)+.50);
	            	ticumsd=(int)((tcumsal-tcumtar)+.50);
				
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
	        		 rfb.setQty2((int)(tmontar+.50));
	        		 rfb.setQty3((int)(tmonsal+.50));
	        		 rfb.setQty4((int)tmonach);
	        		 rfb.setQty5(timonsd);
	        		 rfb.setQty6((int)(tcumtar+.50));
	        		 rfb.setQty7((int)(tcumsal+.50));
	        		 rfb.setQty8((int)tcumach);
	        		 rfb.setQty9(ticumsd);
	        		 rfb.setQty10((int)(tlstsal+.50));
	        		 rfb.setDval1(tcumgth);
	        		 rfb.setQty11((int)(tpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
				
 	 				data.add(rfb);				
				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm4DAO.getForm4 " + e); 
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
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}

//////////////////////////////////////////Area ke liye End here/////////////////////////////////////		

//////////////////////////////////////////Branch ke liye Start here/////////////////////////////////////	
	public List getMBranch(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
  		

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
             
            String tblnm=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;

///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;

            int gimonsd=0;
            int gicumsd=0;
            
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            double tlstsal=0.00;
            double tmonach=0.00;
            double tcumach=0.00;
            double tcumgth=0.00;
            double tpmr=0.00;

            int timonsd=0;
            int ticumsd=0; 
            int trep=0;
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;
            
    	    tblnm=(tp+"_repfinal").toLowerCase();
     	    tblnm3=(tp+"_tr_pmr").toLowerCase();
            String qg=null;
            String qg1=null;
            String qg2=null;
            String qg3=null;
   	        
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }

            qg=",sum(lysqty) lysqty,sum(tarqty) tarqty,sum(salqty) salqty,sum(tarval) tarval,sum(salval) salval  ";
	    	qg1=",sum(lysqty) clysqty,sum(tarqty) ctarqty,sum(salqty) csalqty,sum(tarval) ctarval,sum(salval) csalval,sum(lysval) clysval  ";
	    	qg2=",a.tarqty mtar,a.salqty msal,b.ctarqty ctar,b.csalqty csal,b.clysqty clys,a.tarval mtarv,a.salval msalv,b.ctarval ctarv,b.csalval csalv,b.clysval clysv  ";
	    	qg3=",c.mtar,c.msal,c.ctar,c.csal,c.clys,c.mtarv,c.msalv,c.ctarv,c.csalv,c.clysv  ";

            
                txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM ";
                
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
			String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
			ms1.setInt(1,smon);
			ms1.setInt(2,emon);
			ms1.setInt(3,eyear);
			mrec = ms1.executeQuery();
			
            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
 			{	
 			    if (mrec.isFirst())	
 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			    
		            if (mrec.isLast())
			           txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
	        }  ///////////////////////Monthfile Loop End here//////////////////////
 			mrec.close();
            ms1.close();
		

    			
///////////////////// Group master ki query///////////////////// 
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            glstsal=0.00;
		            gmonach=0.00;
		            gcumach=0.00;
		            gcumgth=0.00;
		            gpmr=0.00;

		            gimonsd=0;
		            gicumsd=0;
    			
//////////////////////////// Product master ki query/////////////////////////////            
					String query1 = "SELECT c.depo_code,c.mgrp,c.mgrp_name,c.mcode,c.pname,c.pack"+qg3+",D.REP,c.br_name "+
					"FROM "+
					"(select a.depo_code,a.br_name,a.mgrp,a.mgrp_name,a.mcode,a.pname,a.pack"+qg2+
					" from( "+
					"select depo_code,br_name,mgrp,mgrp_name,mcode,pname,pack"+qg+" from "+tblnm+
					" where mkt_year = ? and  mnth_code = ? and depo_CODE =?  "+
					"group by mcode) as a "+
					"left join "+
					"(select depo_code,br_name,mgrp,mgrp_name,mcode,pname,pack"+qg1+"from "+tblnm+" where mkt_year = ? "+
					"and  mnth_code >= ? and  mnth_code <= ? and depo_CODE =?  "+
					"group by mcode) as b "+
					"on a.mcode = b.mcode) C, "+
					"(SELECT depo_code,SUM(REP) REP FROM "+tblnm3+" "+
					"WHERE MKT_YEAR = ? AND MNTH_CODE >= ? AND MNTH_CODE <= ? and depo_code=? "+
					"GROUP BY depo_code) D "+
					"WHERE  "+
					"C.depo_code = D.depo_CODE order by c.mgrp,c.mgrp_name,c.mcode,c.pname ";
					
					ps1 = con.prepareStatement(query1);
				    ps1.setInt(1,eyear);
				    ps1.setInt(2,emon);
				    ps1.setInt(3,depo_code);
				    ps1.setInt(4,eyear);
				    ps1.setInt(5,smon);
				    ps1.setInt(6,emon);
				    ps1.setInt(7,depo_code);
				    ps1.setInt(8,eyear);
				    ps1.setInt(9,smon);
				    ps1.setInt(10,emon);
				    ps1.setInt(11,depo_code);
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
			            txt1="BRANCH-> "+rst1.getString(18);
					}

					if (grp!=rst1.getInt(2))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
	        		 rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)(gmonsal+.50));
	        		 rfb.setQty4((int)gmonach);
	        		 rfb.setQty5(gimonsd);
	        		 rfb.setQty6((int)(gcumtar+.50));
	        		 rfb.setQty7((int)(gcumsal+.50));
	        		 rfb.setQty8((int)gcumach);
	        		 rfb.setQty9(gicumsd);
	        		 rfb.setQty10((int)(glstsal+.50));
	        		 rfb.setDval1(gcumgth);
	        		 rfb.setQty11((int)(gpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
					 grp=rst1.getInt(2);
					 grnm=rst1.getString(3);

	 		         gmontar=0.00;
			         gcumtar=0.00;
			         gmonsal=0.00;
			         gcumsal=0.00;
			         glstsal=0.00;
			         gmonach=0.00;
			         gcumach=0.00;
			         gcumgth=0.00;
			         gpmr=0.00;
			         gimonsd=0;
			         gicumsd=0;
					}

		        
		 			trep=0;
		 			montar=0.00;
		            monsal=0.00;
		            monach=0.00;
		 			cumtar=0.00;
		            cumsal=0.00;
		            cumach=0.00;
		            lstsal=0.00;
		            cumgth=0.00;
		            pmr=0.00;
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            

/*				      ggmontar = rst1.getDouble(11);
				      ggmonsal = rst1.getDouble(12);
				      gcumtar = gcumtar+rst1.getDouble(13);
				      gcumsal = gcumsal+rst1.getDouble(14);
				      glstsal = glstsal+rst1.getDouble(15);
*/
				      trep=rst1.getInt(17);
				      
					    if (uv==1)
					    {
					      montar = rst1.getDouble(7);
					      monsal = rst1.getDouble(8);
					      cumtar = rst1.getDouble(9);
					      cumsal = rst1.getDouble(10);
					      lstsal = rst1.getDouble(11);
					      
					    }
					    if (uv==2)
					    {						    
					      montar = rst1.getDouble(12);
					      monsal = rst1.getDouble(13);
 					      cumtar = rst1.getDouble(14);
					      cumsal = rst1.getDouble(15);
					      lstsal = rst1.getDouble(16);
					    }
						      
	 			           
	 			           

			      			        
		 			if (montar!=0)
		 				monach = (monsal/montar)*100;
		 			if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		               pmr = cumsal/trep;
		            
		            if (monach>0)
		            	monach=monach+.50;
		            else
		            {
		               monach=((monach*-1)+.50)*-1;	
		            }
		            
		            if (cumach>0)
		            	cumach=cumach+.50;
		            else
		            {
		               cumach=((cumach*-1)+.50)*-1;	
		            }
		            
		             imonsd=(int)monsal-(int)(montar+.50); 
		             icumsd=(int)cumsal-(int)(cumtar+.50);
		             
		          if (hprint)
		          {
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(4));
					 rfb.setMname(rst1.getString(5));
					 rfb.setPack(rst1.getString(6));
					 rfb.setUv(uv);
		             rfb.setColor(1);
	        		 rfb.setQty2((int)(montar+.50));
	        		 rfb.setQty3((int)monsal);
	        		 rfb.setQty4((int)monach);
	        		 rfb.setQty5(imonsd);
	        		 rfb.setQty6((int)(cumtar+.50));
	        		 rfb.setQty7((int)cumsal);
	        		 rfb.setQty8((int)cumach);
	        		 rfb.setQty9(icumsd);
	        		 rfb.setQty10((int)lstsal);
	        		 rfb.setDval1(cumgth);
	        		 rfb.setQty11((int)(pmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);
 	 				 
	                 data.add(rfb);
		          }

			      montar = rst1.getDouble(12);
			      monsal = rst1.getDouble(13);
			      cumtar = rst1.getDouble(14);
			      cumsal = rst1.getDouble(15);
			      lstsal = rst1.getDouble(16);

	        	  gcumtar = gcumtar+cumtar;
	              gcumsal = gcumsal+cumsal;
	              glstsal = glstsal+lstsal;		
				  gmontar = gmontar+montar;
		          gmonsal = gmonsal+monsal;

//			  }///////////////////// Product Master Loop End here////////////////////////// 
				
	 			if (gmontar!=0)
	 				gmonach = (gmonsal/gmontar)*100;
	 			if (gcumtar!=0)
	            	gcumach = (gcumsal/gcumtar)*100;
	            if (glstsal!=0)
	            	gcumgth = ((gcumsal/glstsal)*100)-100;
	            if (trep!=0)
	               gpmr = gcumsal/trep;
	            
	            if (gmonach>0)
	            	gmonach=gmonach+.50;
	            else
	            {
	               gmonach=((gmonach*-1)+.50)*-1;	
	            }
	            
	            if (gcumach>0)
	            	gcumach=gcumach+.50;
	            else
	            {
	               gcumach=((gcumach*-1)+.50)*-1;	
	            }
	            
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
		             tlstsal = tlstsal+lstsal;		
 				     tmontar = tmontar+montar;
			         tmonsal = tmonsal+monsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
        		 rfb.setQty2((int)(gmontar+.50));
        		 rfb.setQty3((int)(gmonsal+.50));
        		 rfb.setQty4((int)gmonach);
        		 rfb.setQty5(gimonsd);
        		 rfb.setQty6((int)(gcumtar+.50));
        		 rfb.setQty7((int)(gcumsal+.50));
        		 rfb.setQty8((int)gcumach);
        		 rfb.setQty9(gicumsd);
        		 rfb.setQty10((int)(glstsal+.50));
        		 rfb.setDval1(gcumgth);
        		 rfb.setQty11((int)(gpmr+.50));
 				 rfb.setNm3(txt1+txt2+txt5);
 				 data.add(rfb);

 		         gmontar=0.00;
		         gcumtar=0.00;
		         gmonsal=0.00;
		         gcumsal=0.00;
		         glstsal=0.00;
		         gmonach=0.00;
		         gcumach=0.00;
		         gcumgth=0.00;
		         gpmr=0.00;
		         gimonsd=0;
		         gicumsd=0;

		         ps1.close();
				 rst1.close();

///////////////////////////// All Statment Close/////////////////////////////

	 			if (tmontar!=0)
	 				tmonach = (tmonsal/tmontar)*100;
	 			if (tcumtar!=0)
	            	tcumach = (tcumsal/tcumtar)*100;
	            if (tlstsal!=0)
	            	tcumgth = ((tcumsal/tlstsal)*100)-100;
	            if (trep!=0)
	               tpmr = tcumsal/trep;
	            
	            if (tmonach>0)
	            	tmonach=tmonach+.50;
	            else
	            {
	               tmonach=((tmonach*-1)+.50)*-1;	
	            }
	            
	            if (tcumach>0)
	            	tcumach=tcumach+.50;
	            else
	            {
	               tcumach=((tcumach*-1)+.50)*-1;	
	            }
	            
                     timonsd=(int)((tmonsal-tmontar)+.50);
	             	 ticumsd=(int)((tcumsal-tcumtar)+.50);
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
	        		 rfb.setQty2((int)(tmontar+.50));
	        		 rfb.setQty3((int)(tmonsal+.50));
	        		 rfb.setQty4((int)tmonach);
	        		 rfb.setQty5(timonsd);
	        		 rfb.setQty6((int)(tcumtar+.50));
	        		 rfb.setQty7((int)(tcumsal+.50));
	        		 rfb.setQty8((int)tcumach);
	        		 rfb.setQty9(ticumsd);
	        		 rfb.setQty10((int)(tlstsal+.50));
	        		 rfb.setDval1(tcumgth);
	        		 rfb.setQty11((int)(tpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
 	 				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm4DAO.getForm4 " + e); 
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
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				} 
			}
		return data;
	}
//////////////////////////////////////////Branch ke liye End here/////////////////////////////////////			



	public List getNewBranch(Connection con, int uv,int hq_code,int smon,int emon,int eyear,int depo_code,int div_code,int uid,int utype) { 
		 
		MktFormBean rfb;
        CallableStatement cs=null;
        ResultSet rst1=null;
  		

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
             
            String tblnm=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;

///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
            double ggmonsal=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;

            int gimonsd=0;
            int gicumsd=0;
            
////////////////Grand Total Ke liye//////////////
            double tmontar=0.00;
            double tcumtar=0.00;
            double tmonsal=0.00;
            double tcumsal=0.00;
            double tlstsal=0.00;
            double tmonach=0.00;
            double tcumach=0.00;
            double tcumgth=0.00;
            double tpmr=0.00;

            int timonsd=0;
            int ticumsd=0; 
            int trep=0;
            int imonsd=0;
            int icumsd=0;

            if (smon>emon)
            	emon=smon;
            
            String procedureWithParameters="{call web_mkt_2(?,?,?,?,?,?,?,?)}";
            if(depo_code==0 && utype==3) // pmt user all
            	procedureWithParameters="{call web_mkt_2allpmt(?,?,?,?,?,?,?,?)}";
            else if(hq_code>0 && utype==3) // pmt user selective Branch/hq
            	procedureWithParameters="{call web_mkt_2hqpmt(?,?,?,?,?,?,?,?)}";
            else if(utype==3) // pmt user
            	procedureWithParameters="{call web_mkt_2pmt(?,?,?,?,?,?,?,?)}";
            else if(depo_code==0 && utype==2) //HO user all branches
            	procedureWithParameters="{call web_mkt_2all(?,?,?,?,?,?,?,?)}";
            else if(depo_code==0 && utype==5) // User with selective branch
            	procedureWithParameters="{call web_mkt_5(?,?,?,?,?,?,?,?)}";
            else if(depo_code==0 && utype==4) // user with selective hq
            	procedureWithParameters="{call web_mkt2hq4(?,?,?,?,?,?,?,?)}";
            else if(utype==4) //  user hq with selective branch 
            	procedureWithParameters="{call web_mkt2depo4(?,?,?,?,?,?,?,?)}";
            else if(hq_code>0) // all hq of selective branch 
            	procedureWithParameters="{call web_mkt_2hq(?,?,?,?,?,?,?,?)}";
            
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }

            
              txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM ";
                
              System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+uid+" hqcode "+hq_code);
              System.out.println(procedureWithParameters);
              

    			
///////////////////// Group master ki query///////////////////// 
		            gmontar=0.00;
		            gcumtar=0.00;
		            gmonsal=0.00;
		            gcumsal=0.00;
		            glstsal=0.00;
		            gmonach=0.00;
		            gcumach=0.00;
		            gcumgth=0.00;
		            gpmr=0.00;

		            gimonsd=0;
		            gicumsd=0;
		             
		            
					
					cs  = con.prepareCall(procedureWithParameters);
					cs.setInt(1, eyear);
					cs.setInt(2, div_code);
					cs.setInt(3, depo_code);
					cs.setInt(4, smon);
					cs.setInt(5, emon);
					cs.setInt(6, utype);
					cs.setInt(7, uid);
					cs.setInt(8, hq_code);


					rst1 = cs.executeQuery();

				    boolean hprint=false;
		    	    boolean first=true;
		    	    int grp=0;
		    	    String grnm=null;
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					hprint=true;

					if (first)
					{
						grp=rst1.getInt(6);
						grnm=rst1.getString(7);
						first=false;
			            txt1=(hq_code>0?"HQ-> ":"BRANCH-> ")+rst1.getString(25);
  			            txt5 = rst1.getString(23)+" TO "+rst1.getString(24);

					}

					if (grp!=rst1.getInt(6))
					{
					 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+grnm);
		             rfb.setColor(2);
	        		 rfb.setQty2((int)(gmontar+.50));
	        		 rfb.setQty3((int)(gmonsal+.50));
	        		 rfb.setQty4((int)gmonach);
	        		 rfb.setQty5(gimonsd);
	        		 rfb.setQty6((int)(gcumtar+.50));
	        		 rfb.setQty7((int)(gcumsal+.50));
	        		 rfb.setQty8((int)gcumach);
	        		 rfb.setQty9(gicumsd);
	        		 rfb.setQty10((int)(glstsal+.50));
	        		 rfb.setDval1(gcumgth);
	        		 rfb.setQty11((int)(gpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
 	 				 grp=rst1.getInt(6);
 	 				 grnm=rst1.getString(7);

					 
	 		         gmontar=0.00;
			         gcumtar=0.00;
			         gmonsal=0.00;
			         gcumsal=0.00;
			         glstsal=0.00;
			         gmonach=0.00;
			         gcumach=0.00;
			         gcumgth=0.00;
			         gpmr=0.00;
			         gimonsd=0;
			         gicumsd=0;
					}

		        
		 			trep=0;
		 			montar=0.00;
		            monsal=0.00;
		            monach=0.00;
		 			cumtar=0.00;
		            cumsal=0.00;
		            cumach=0.00;
		            lstsal=0.00;
		            cumgth=0.00;
		            pmr=0.00;
		            ggmontar=0.00;
		            ggmonsal=0.00;
		            

/*				      ggmontar = rst1.getDouble(11);
				      ggmonsal = rst1.getDouble(12);
				      gcumtar = gcumtar+rst1.getDouble(13);
				      gcumsal = gcumsal+rst1.getDouble(14);
				      glstsal = glstsal+rst1.getDouble(15);
*/
				      trep=rst1.getInt(26);
				      
					    if (uv==1)
					    {
					      montar = rst1.getDouble(15);
					      monsal = rst1.getDouble(11);
					      cumtar = rst1.getDouble(17);
					      cumsal = rst1.getDouble(13);
					      lstsal = rst1.getDouble(21);
					      
					    }
					    if (uv==2)
					    {						    
					      montar = rst1.getDouble(16);
					      monsal = rst1.getDouble(12);
 					      cumtar = rst1.getDouble(18);
					      cumsal = rst1.getDouble(14);
					      lstsal = rst1.getDouble(22);
					    }
						      
	 			           
	 			           

			      			        
		 			if (montar!=0)
		 				monach = (monsal/montar)*100;
		 			if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		               pmr = cumsal/trep;
		            
		            if (monach>0)
		            	monach=monach+.50;
		            else
		            {
		               monach=((monach*-1)+.50)*-1;	
		            }
		            
		            if (cumach>0)
		            	cumach=cumach+.50;
		            else
		            {
		               cumach=((cumach*-1)+.50)*-1;	
		            }
		            
		             imonsd=(int)monsal-(int)(montar+.50); 
		             icumsd=(int)cumsal-(int)(cumtar+.50);
		             
		          if (hprint)
		          {
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(8));
					 rfb.setMname(rst1.getString(9));
					 rfb.setPack(rst1.getString(10));
					 rfb.setUv(uv);
		             rfb.setColor(1);
	        		 rfb.setQty2((int)(montar+.50));
	        		 rfb.setQty3((int)monsal);
	        		 rfb.setQty4((int)monach);
	        		 rfb.setQty5(imonsd);
	        		 rfb.setQty6((int)(cumtar+.50));
	        		 rfb.setQty7((int)cumsal);
	        		 rfb.setQty8((int)cumach);
	        		 rfb.setQty9(icumsd);
	        		 rfb.setQty10((int)lstsal);
	        		 rfb.setDval1(cumgth);
	        		 rfb.setQty11((int)(pmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);
 	 				 
	                 data.add(rfb);
		          }

			      montar = rst1.getDouble(16);
			      monsal = rst1.getDouble(12);
			      cumtar = rst1.getDouble(18);
			      cumsal = rst1.getDouble(14);
			      lstsal = rst1.getDouble(22);

	        	  gcumtar = gcumtar+cumtar;
	              gcumsal = gcumsal+cumsal;
	              glstsal = glstsal+lstsal;		
				  gmontar = gmontar+montar;
		          gmonsal = gmonsal+monsal;

//			  }///////////////////// Product Master Loop End here////////////////////////// 
				
	 			if (gmontar!=0)
	 				gmonach = (gmonsal/gmontar)*100;
	 			if (gcumtar!=0)
	            	gcumach = (gcumsal/gcumtar)*100;
	            if (glstsal!=0)
	            	gcumgth = ((gcumsal/glstsal)*100)-100;
	            if (trep!=0)
	               gpmr = gcumsal/trep;
	            
	            if (gmonach>0)
	            	gmonach=gmonach+.50;
	            else
	            {
	               gmonach=((gmonach*-1)+.50)*-1;	
	            }
	            
	            if (gcumach>0)
	            	gcumach=gcumach+.50;
	            else
	            {
	               gcumach=((gcumach*-1)+.50)*-1;	
	            }
	            
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
		             tlstsal = tlstsal+lstsal;		
 				     tmontar = tmontar+montar;
			         tmonsal = tmonsal+monsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				 rfb = new MktFormBean();
				 rfb.setMname("VALUE OF "+grnm);
	             rfb.setColor(2);
        		 rfb.setQty2((int)(gmontar+.50));
        		 rfb.setQty3((int)(gmonsal+.50));
        		 rfb.setQty4((int)gmonach);
        		 rfb.setQty5(gimonsd);
        		 rfb.setQty6((int)(gcumtar+.50));
        		 rfb.setQty7((int)(gcumsal+.50));
        		 rfb.setQty8((int)gcumach);
        		 rfb.setQty9(gicumsd);
        		 rfb.setQty10((int)(glstsal+.50));
        		 rfb.setDval1(gcumgth);
        		 rfb.setQty11((int)(gpmr+.50));
 				 rfb.setNm3(txt1+txt2+txt5);
 				 data.add(rfb);

 		         gmontar=0.00;
		         gcumtar=0.00;
		         gmonsal=0.00;
		         gcumsal=0.00;
		         glstsal=0.00;
		         gmonach=0.00;
		         gcumach=0.00;
		         gcumgth=0.00;
		         gpmr=0.00;
		         gimonsd=0;
		         gicumsd=0;

		         cs.close();
				 rst1.close();

///////////////////////////// All Statment Close/////////////////////////////

	 			if (tmontar!=0)
	 				tmonach = (tmonsal/tmontar)*100;
	 			if (tcumtar!=0)
	            	tcumach = (tcumsal/tcumtar)*100;
	            if (tlstsal!=0)
	            	tcumgth = ((tcumsal/tlstsal)*100)-100;
	            if (trep!=0)
	               tpmr = tcumsal/trep;
	            
	            if (tmonach>0)
	            	tmonach=tmonach+.50;
	            else
	            {
	               tmonach=((tmonach*-1)+.50)*-1;	
	            }
	            
	            if (tcumach>0)
	            	tcumach=tcumach+.50;
	            else
	            {
	               tcumach=((tcumach*-1)+.50)*-1;	
	            }
	            
                     timonsd=(int)((tmonsal-tmontar)+.50);
	             	 ticumsd=(int)((tcumsal-tcumtar)+.50);
				     rfb = new MktFormBean();
					 rfb.setMname("GRAND TOTAL");
		             rfb.setColor(3);
	        		 rfb.setQty2((int)(tmontar+.50));
	        		 rfb.setQty3((int)(tmonsal+.50));
	        		 rfb.setQty4((int)tmonach);
	        		 rfb.setQty5(timonsd);
	        		 rfb.setQty6((int)(tcumtar+.50));
	        		 rfb.setQty7((int)(tcumsal+.50));
	        		 rfb.setQty8((int)tcumach);
	        		 rfb.setQty9(ticumsd);
	        		 rfb.setQty10((int)(tlstsal+.50));
	        		 rfb.setDval1(tcumgth);
	        		 rfb.setQty11((int)(tpmr+.50));
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 data.add(rfb);
 	 				
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLForm4DAO.getNewForm4 " + e);
			e.printStackTrace();
		}
		  finally 
			{
			  try
				{
	             if(rst1 != null){ rst1.close();}
	             if(cs != null){ cs.close();}
			     if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				} 
			}
		return data;
	}
//////////////////////////////////////////Branch ke liye End here/////////////////////////////////////			

}