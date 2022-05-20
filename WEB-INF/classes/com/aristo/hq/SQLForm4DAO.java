package com.aristo.hq;

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
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps5=null; 
        ResultSet rst5=null;
        PreparedStatement ps6=null; 
        ResultSet rst6=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psg =null;
        ResultSet rstg=null;
        	
     	String wname=null;
 	    int mon=0;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String queryg=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
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

        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
   	        tblnm3=tp+"_group_mkt08";
            
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }
            
            txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM ";
            String ccode=""+code;
            depo_code=Integer.parseInt(ccode.substring(0,2));
            code=Integer.parseInt(ccode.substring(2));

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
                
///////////////////////// Headquater master ki query///////////////////// 
            String terrec="Select t.ter_name,t.oct,t.nov,t.decm,t.jan,t.feb,t.mar,t.apr,t.may," +
            " t.jun,t.jul,t.aug,t.sep from "+tblnm2+" as t,user_ter as u " +
            " where t.ter_pt=u.access_t and t.ter_pt=? and u.access_t=? and t.depo_code=? and u.depo_code=? and " +
            " t.ter_code=? and u.ter_code=? and t.mkt_year="+eyear+" and u.user_id="+uid+" ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setString(2,tp);
			ts1.setInt(3,depo_code);
			ts1.setInt(4,depo_code);
			ts1.setInt(5,code);
			ts1.setInt(6,code);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="H.Q.-> "+trec.getString(1);
			
///////////////////// Group master ki query/////////////////////   
            queryg = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>? and mkt_year=? group by gp_code order by gp_code";				
			psg = con.prepareStatement(queryg); 
			psg.setInt(1,0);
			psg.setInt(2,eyear);
			rstg = psg.executeQuery();
		
				while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
				{
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
				query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";					
				ps1 = con.prepareStatement(query1); 
				ps1.setInt(1,0);
				ps1.setInt(2,rstg.getInt(1));
				ps1.setInt(3,eyear);
				rst1 = ps1.executeQuery();
			
				// call hq_mktrep2(10, 15,  2017 ,1,'M',6,9113,0);
				
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(1));
					rfb.setMname(rst1.getString(2));
					rfb.setPack(rst1.getString(3));
					rfb.setUv(uv);
		        
////////////// Month File Loop Starts to accumulate data///////////////////////
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		 			trep=0;
		 			montar=0.00;
		 			amontar=0.00;
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
		            
		            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
 			      
		 			       wname= mrec.getString(3);
	 			           mon=mrec.getInt(4);
	 			           
	 			            if (wname.equals("OCT"))
	 			            	trep= trec.getInt(2);
	 			            if (wname.equals("NOV"))
	 			            	trep= trep+trec.getInt(3);
	 			            if (wname.equals("DEC"))
	 			            	trep= trep+trec.getInt(4);
	 			            if (wname.equals("JAN"))
	 			            	trep= trep+trec.getInt(5);
	 			            if (wname.equals("FEB"))
	 			            	trep= trep+trec.getInt(6);
	 			            if (wname.equals("MAR"))
	 			            	trep= trep+trec.getInt(7);
	 			            if (wname.equals("APR"))
	 			            	trep= trep+trec.getInt(8);
	 			            if (wname.equals("MAY"))
	 			            	trep= trep+trec.getInt(9);
	 			            if (wname.equals("JUN"))
	 			            	trep= trep+trec.getInt(10);
	 			            if (wname.equals("JUL"))
	 			            	trep= trep+trec.getInt(11);
	 			            if (wname.equals("AUG"))
	 			            	trep= trep+trec.getInt(12);
	 			            if (wname.equals("SEP"))
	 			            	trep= trep+trec.getInt(13);		 			
	 			           
//////////////////////// Target master ki query///////////////////////////////
	 			           
	 		String query3=null;
	 		
	              query3 = "Select (((tt"+mon+"*ttarget)/100)*tmrp),ra"+mon+",rl"+mon+"," +
	              " ((tt"+mon+"*ttarget)/100),ta"+mon+",la"+mon+",budmnth,budper,budtar,budval from "+tblnm+" " +
	              " where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? group by pr_code " +
	              " order by ar_cd,rg_cd,tr_cd,pr_code"; 	 			           
	              
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code); 
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(5));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())/////////////////Target Master Start///////////////////
			        {
					      ggmontar = rst3.getDouble(1);
					      ggmonsal = rst3.getDouble(2);
 					      gcumtar = gcumtar+rst3.getDouble(1);
					      gcumsal = gcumsal+rst3.getDouble(2);
					      glstsal = glstsal+rst3.getDouble(3);
					      
						    if (uv==1)
						    {
						      montar = rst3.getDouble(4);
						      monsal = rst3.getDouble(5);
	 					      cumtar = cumtar+rst3.getDouble(4);
						      cumsal = cumsal+rst3.getDouble(5);
						      lstsal = lstsal+rst3.getDouble(6);
						    	if (mrec.getInt(6)>=rst3.getInt(7))
						    	{
						    	 amontar=((rst3.getDouble(9)*rst3.getDouble(8))/100);
						    	 cumtar=cumtar+((rst3.getDouble(9)*rst3.getDouble(8))/100);
						    	}			            
						      
						    }
						    if (uv==2)
						    {						    
						      montar = rst3.getDouble(1);
						      monsal = rst3.getDouble(2);
	 					      cumtar = cumtar+rst3.getDouble(1);
						      cumsal = cumsal+rst3.getDouble(2);
						      lstsal = lstsal+rst3.getDouble(3);
						      if (mrec.getInt(6)>=rst3.getInt(7))
						      {
						      amontar=((rst3.getDouble(10)*rst3.getDouble(8))/100);
						      cumtar=cumtar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
						      }			            
						    }
					    
				    	if (mrec.getInt(6)>=rst3.getInt(7))
				    	{
				    	 ggmontar=ggmontar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
				    	 gcumtar=gcumtar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
				    	}			            
					    
					    
			         }	 /////////////////////Target Master End//////////////////////
			        
			        ps3.close();
			        rst3.close();	 
			      			        
			        }  ///////////////////////Monthfile Loop End here//////////////////////
		            ms1.close();
		 			mrec.close();
		 			montar=montar+amontar;
////////////////////////////Month File Loop End here//////////////////////////////////
		 			
/*		            if (wname.equals("OCT"))
		            	trep= trec.getInt(2);
		            if (wname.equals("NOV"))
		            	trep= trec.getInt(2)+trec.getInt(3);
		            if (wname.equals("DEC"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4);
		            if (wname.equals("JAN"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+trec.getInt(5);
		            if (wname.equals("FEB"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+trec.getInt(5)+trec.getInt(6);
		            if (wname.equals("MAR"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7);
		            if (wname.equals("APR"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8);
		            if (wname.equals("MAY"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9);
		            if (wname.equals("JUN"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10);
		            if (wname.equals("JUL"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11);
		            if (wname.equals("AUG"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11)+trec.getInt(12);
		            if (wname.equals("SEP"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11)+trec.getInt(12)+trec.getInt(13);		 			
*/		 			
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

			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+ggmonsal;

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
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
				
				 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+rstg.getString(2));
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

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+gcumtar;
			            tcumsal = tcumsal+gcumsal;
			            tlstsal = tlstsal+glstsal;		
 	 				    tmontar = tmontar+gmontar;
				        tmonsal = tmonsal+gmonsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				psg.close();
				rstg.close();
				ts1.close();
				trec.close();

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
	             if(trec != null){ trec.close();}
	             if(ts1 != null){ ts1.close();}
	             if(rstg != null){ rstg.close();}
	             if(psg != null){ psg.close();}
	             if(rst5 != null){ rst5.close();}
	             if(ps5 != null){ps5.close();}
	             if(rst6 != null){ rst6.close();}
	             if(ps6 != null){ps6.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
	             if(rst3 != null){ rst3.close();}
	             if(ps3 != null){ps3.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}


	
	public List getAllHQNew(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps5=null; 
        ResultSet rst5=null;
        PreparedStatement ps6=null; 
        ResultSet rst6=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement psg =null;
        ResultSet rstg=null;
        	
     	String wname=null;
 	    int mon=0;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String queryg=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
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

        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
   	        tblnm3=tp+"_group_mkt08";
            
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }
            
            txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM ";
            String ccode=""+code;
//            depo_code=Integer.parseInt(ccode.substring(0,2));
//            code=Integer.parseInt(ccode.substring(2));

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
                
///////////////////////// Headquater master ki query///////////////////// 
/*            String terrec="Select t.ter_name,t.oct,t.nov,t.decm,t.jan,t.feb,t.mar,t.apr,t.may," +
            " t.jun,t.jul,t.aug,t.sep from "+tblnm2+" as t,user_ter as u " +
            " where t.ter_pt=u.access_t and t.ter_pt=? and u.access_t=? and t.depo_code=? and u.depo_code=? and " +
            " t.ter_code=u.ter_code and t.mkt_year="+eyear+" and u.user_id="+uid+" ";  
*/            
/*    		String terrec="Select t.ter_name,sum(t.oct),sum(t.nov),sum(t.decm),sum(t.jan),sum(t.feb),sum(t.mar),sum(t.apr),sum(t.may), "+ 
            " sum(t.jun),sum(t.jul),sum(t.aug),sum(t.sep) from "+tblnm2+" as t,user_ter as u  "+
            " where t.ter_pt=u.access_t and t.ter_pt=? and u.access_t=?  and  "+
            " t.ter_code=u.ter_code and t.mkt_year=? and u.user_id=? and t.depo_code=u.depo_code group by u.user_id "; 
*/

    		String terrec="Select t.ter_name,sum(t.oct),sum(t.nov),sum(t.decm),sum(t.jan),sum(t.feb),sum(t.mar),sum(t.apr),sum(t.may), "+ 
            " sum(t.jun),sum(t.jul),sum(t.aug),sum(t.sep) from "+tblnm2+" as t,user_ter as u  "+
            " where   "+
            " t.ter_code=u.ter_code and t.mkt_year=? and u.user_id=? and t.depo_code=u.depo_code group by u.user_id "; 

//			ts1.setString(1,tp);
//			ts1.setString(2,tp);
    		
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1,eyear);
			ts1.setInt(2,uid);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="H.Q.-> ALL";
			
///////////////////// Group master ki query/////////////////////   
            queryg = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>? and mkt_year=? group by gp_code order by gp_code";				
			psg = con.prepareStatement(queryg); 
			psg.setInt(1,0);
			psg.setInt(2,eyear);
			rstg = psg.executeQuery();
		
				while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
				{
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
				query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mcode<>? and mgrp=? and mkt_year=? group by mcode order by mcode";					
				ps1 = con.prepareStatement(query1); 
				ps1.setInt(1,0);
				ps1.setInt(2,rstg.getInt(1));
				ps1.setInt(3,eyear);
				rst1 = ps1.executeQuery();
			
				while (rst1.next())/////////////////Product Master Loop Start Here///////////////////   
				{
					rfb = new MktFormBean();
					rfb.setMcode(rst1.getInt(1));
					rfb.setMname(rst1.getString(2));
					rfb.setPack(rst1.getString(3));
					rfb.setUv(uv);
		        
////////////// Month File Loop Starts to accumulate data///////////////////////
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		 			trep=0;
		 			montar=0.00;
		 			amontar=0.00;
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
		            
		            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
 			      
		 			       wname= mrec.getString(3);
	 			           mon=mrec.getInt(4);
	 			           
	 			            if (wname.equals("OCT"))
	 			            	trep= trec.getInt(2);
	 			            if (wname.equals("NOV"))
	 			            	trep= trep+trec.getInt(3);
	 			            if (wname.equals("DEC"))
	 			            	trep= trep+trec.getInt(4);
	 			            if (wname.equals("JAN"))
	 			            	trep= trep+trec.getInt(5);
	 			            if (wname.equals("FEB"))
	 			            	trep= trep+trec.getInt(6);
	 			            if (wname.equals("MAR"))
	 			            	trep= trep+trec.getInt(7);
	 			            if (wname.equals("APR"))
	 			            	trep= trep+trec.getInt(8);
	 			            if (wname.equals("MAY"))
	 			            	trep= trep+trec.getInt(9);
	 			            if (wname.equals("JUN"))
	 			            	trep= trep+trec.getInt(10);
	 			            if (wname.equals("JUL"))
	 			            	trep= trep+trec.getInt(11);
	 			            if (wname.equals("AUG"))
	 			            	trep= trep+trec.getInt(12);
	 			            if (wname.equals("SEP"))
	 			            	trep= trep+trec.getInt(13);		 			
	 			           
//////////////////////// Target master ki query///////////////////////////////
	 			           
	 		String query3=null;
	 		
/*	              query3 = "Select (((tt"+mon+"*ttarget)/100)*tmrp),ra"+mon+",rl"+mon+"," +
	              " ((tt"+mon+"*ttarget)/100),ta"+mon+",la"+mon+",budmnth,budper,budtar,budval from "+tblnm+" " +
	              " where tr_cd=? and pr_code=? and depo_code=? and mkt_year=? group by pr_code " +
	              " order by ar_cd,rg_cd,tr_cd,pr_code"; 	 
*/	              
	              
	              query3="Select sum(((t.tt"+mon+"*t.ttarget)/100)*t.tmrp),sum(t.ra"+mon+"),sum(t.rl"+mon+")," +
	              " sum((t.tt"+mon+"*t.ttarget)/100),sum(t.ta"+mon+"),sum(t.la"+mon+"),t.budmnth,t.budper,t.budtar,t.budval from "+tblnm+" t,user_ter as u "+
	              " where t.mkt_year=?  and u.user_id=? and t.depo_code=u.depo_code and t.tr_cd=u.ter_code and t.pr_code=? group by t.pr_code  "+
	              " order by t.ar_cd,t.rg_cd,t.tr_cd,t.pr_code ";  			           

	              
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,mrec.getInt(5));
			        ps3.setInt(2,uid);
			        ps3.setInt(3,rst1.getInt(1));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())/////////////////Target Master Start///////////////////
			        {
					      ggmontar = rst3.getDouble(1);
					      ggmonsal = rst3.getDouble(2);
 					      gcumtar = gcumtar+rst3.getDouble(1);
					      gcumsal = gcumsal+rst3.getDouble(2);
					      glstsal = glstsal+rst3.getDouble(3);
					      
						    if (uv==1)
						    {
						      montar = rst3.getDouble(4);
						      monsal = rst3.getDouble(5);
	 					      cumtar = cumtar+rst3.getDouble(4);
						      cumsal = cumsal+rst3.getDouble(5);
						      lstsal = lstsal+rst3.getDouble(6);
						    	if (mrec.getInt(6)>=rst3.getInt(7))
						    	{
						    	 amontar=((rst3.getDouble(9)*rst3.getDouble(8))/100);
						    	 cumtar=cumtar+((rst3.getDouble(9)*rst3.getDouble(8))/100);
						    	}			            
						      
						    }
						    if (uv==2)
						    {						    
						      montar = rst3.getDouble(1);
						      monsal = rst3.getDouble(2);
	 					      cumtar = cumtar+rst3.getDouble(1);
						      cumsal = cumsal+rst3.getDouble(2);
						      lstsal = lstsal+rst3.getDouble(3);
						      if (mrec.getInt(6)>=rst3.getInt(7))
						      {
						      amontar=((rst3.getDouble(10)*rst3.getDouble(8))/100);
						      cumtar=cumtar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
						      }			            
						    }
					    
				    	if (mrec.getInt(6)>=rst3.getInt(7))
				    	{
				    	 ggmontar=ggmontar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
				    	 gcumtar=gcumtar+((rst3.getDouble(10)*rst3.getDouble(8))/100);
				    	}			            
					    
					    
			         }	 /////////////////////Target Master End//////////////////////
			        
			        ps3.close();
			        rst3.close();	 
			      			        
			        }  ///////////////////////Monthfile Loop End here//////////////////////
		            ms1.close();
		 			mrec.close();
		 			montar=montar+amontar;
////////////////////////////Month File Loop End here//////////////////////////////////
		 			
/*		            if (wname.equals("OCT"))
		            	trep= trec.getInt(2);
		            if (wname.equals("NOV"))
		            	trep= trec.getInt(2)+trec.getInt(3);
		            if (wname.equals("DEC"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4);
		            if (wname.equals("JAN"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+trec.getInt(5);
		            if (wname.equals("FEB"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+trec.getInt(5)+trec.getInt(6);
		            if (wname.equals("MAR"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7);
		            if (wname.equals("APR"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8);
		            if (wname.equals("MAY"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9);
		            if (wname.equals("JUN"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10);
		            if (wname.equals("JUL"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11);
		            if (wname.equals("AUG"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11)+trec.getInt(12);
		            if (wname.equals("SEP"))
		            	trep= trec.getInt(2)+trec.getInt(3)+trec.getInt(4)+
		            	      trec.getInt(5)+trec.getInt(6)+trec.getInt(7)+
		            	      trec.getInt(8)+trec.getInt(9)+trec.getInt(10)+
		            	      trec.getInt(11)+trec.getInt(12)+trec.getInt(13);		 			
*/		 			
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

			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+ggmonsal;

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
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
				
				 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+rstg.getString(2));
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

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+gcumtar;
			            tcumsal = tcumsal+gcumsal;
			            tlstsal = tlstsal+glstsal;		
 	 				    tmontar = tmontar+gmontar;
				        tmonsal = tmonsal+gmonsal;
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				psg.close();
				rstg.close();
				ts1.close();
				trec.close();

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
			
			  e.printStackTrace();
			System.out.println("========Exception in SQLForm4DAO.getAllHQNewForm4 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(trec != null){ trec.close();}
	             if(ts1 != null){ ts1.close();}
	             if(rstg != null){ rstg.close();}
	             if(psg != null){ psg.close();}
	             if(rst5 != null){ rst5.close();}
	             if(ps5 != null){ps5.close();}
	             if(rst6 != null){ rst6.close();}
	             if(ps6 != null){ps6.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
	             if(rst3 != null){ rst3.close();}
	             if(ps3 != null){ps3.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}
	


	public List getAllHQNew1(Connection con, int uv,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
         
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
         
        
        CallableStatement cs=null;
        ResultSet rs=null;
        
        	
     	String wname=null;
 	    int mon=0;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String queryg=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5=null;
            String txt6=null;
/////////////////Product Ke liye////////////////////            
            double montar=0.00;
            double amontar=0.00;
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

        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
   	        tblnm3=tp+"_group_mkt08";
            
            if (uv==1) 
            {
            	txt3="UNIT-WISE";
            }
            
            if (uv==2)
            {
            	txt3="VALUE-WISE";
            }
            
            txt2="     PRODUCT WISE /"+txt3+ " DETAIL FROM ";
           
//            depo_code=Integer.parseInt(ccode.substring(0,2));
//            code=Integer.parseInt(ccode.substring(2));

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
                
///////////////////////// Headquater master ki query///////////////////// 

    		String terrec="Select t.ter_name,sum(t.oct),sum(t.nov),sum(t.decm),sum(t.jan),sum(t.feb),sum(t.mar),sum(t.apr),sum(t.may), "+ 
            " sum(t.jun),sum(t.jul),sum(t.aug),sum(t.sep) from "+tblnm2+" as t,user_ter as u  "+
            " where   "+
            " t.ter_code=u.ter_code and t.mkt_year=? and u.user_id=? and t.depo_code=u.depo_code group by u.user_id "; 

//			ts1.setString(1,tp);
//			ts1.setString(2,tp);
    		
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1,eyear);
			ts1.setInt(2,uid);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="H.Q.-> ALL";
			
			//`hq_mktrep2`(in_divCode INT, in_depoCode INT,  in_myear INT,in_fmon INT,in_tp CHAR(1),in_tmon INT,in_id INT,in_hqcode INT)
			
            String ccode=""+code;
            if(code!=0)
            {
            	depo_code=Integer.parseInt(ccode.substring(0,2));
            	code=Integer.parseInt(ccode.substring(2));
            }

            
            System.out.println(code+ "smon "+smon+ " emon"+emon+ " eyear"+eyear+" depo"+depo_code+" tp"+tp+" uid"+uid+" utype "+utype);
			
			cs = con.prepareCall("{call hq_mktrep2(?,?,?,?,?,?,?,?)}");
			cs.setInt(1, 0); //div
			cs.setInt(2, depo_code);
			cs.setInt(3, eyear);
			cs.setInt(4, smon);
			cs.setString(5, tp);
			cs.setInt(6, emon);
			cs.setInt(7, uid);
			cs.setInt(8, code); // hqcode
			 
			boolean result = cs.execute();
			if(result)
			{
				rs = cs.getResultSet();
				
				while (rs.next())
				{

					
					rfb = new MktFormBean();
					rfb.setMcode(rs.getInt(3));
					rfb.setMname(rs.getString(4));
					rfb.setPack(rs.getString(5));
					rfb.setUv(uv);
					
					if(rs.getInt(1)==999999)
					{
						 rfb.setMcode(0);
						 rfb.setMname("GRAND TOTAL");
			             rfb.setColor(3);
					}
					else if(rs.getLong(3)==9999999)
					{
						rfb.setColor(2);
						rfb.setMcode(0);
						rfb.setMname("VALUE OF "+rs.getString(2));
					}
					else
					{
						rfb.setColor(1);
					}
					rfb.setQty2(rs.getInt(6));
					rfb.setQty3(rs.getInt(7));
					rfb.setQty4(rs.getInt(8));
					rfb.setQty5(rs.getInt(9));
					rfb.setQty6(rs.getInt(10));
					rfb.setQty7(rs.getInt(11));
					rfb.setQty8(rs.getInt(12));
					rfb.setQty9(rs.getInt(13));
					rfb.setQty10(rs.getInt(14));
					rfb.setDval1(rs.getInt(15));
					rfb.setQty11(rs.getInt(16));
					rfb.setNm3(txt1+txt2+txt5);
					rfb.setLupdate(txt6);
	 				 
	                 data.add(rfb);
					
				}
				
			}
			
			
			 rs.close();
			 cs.close();
			 
			
		} catch (Exception e) {
			
			  e.printStackTrace();
			System.out.println("========Exception in SQLForm4DAO.getAllHQNewForm4 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(trec != null){ trec.close();}
	             if(ts1 != null){ ts1.close();}
	             if(rs != null){ rs.close();}
	             if(cs != null){cs.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  e.printStackTrace();
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
	
	
	
	public List getSalesReview(Connection con, int uv,int code,int smon,int emon,int myear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement psg =null;
        ResultSet rstg=null;
        	
     	String wname=null;
 	    int mon=0;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String queryg=null;
            String txt2=null;
            String txt3=null;
            String txt6=null;
            

        	tblnm=tp+"_repfinal";
   	        tblnm1=tp+"_hq_master08";
            
   	        txt3="(SA - 1 )";
   	        
 
            txt2="  REGION-WISE RUPEE SALES (MONTHLY & CUMULATIVE) "+txt3+" - ASE / Sr. ASE";


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
                
            
///////////////////// Main query/////////////////////   

    		queryg =" select "+
    		" b.mname,a.target,a.netsale,a.lysyear,"+
    		" ifnull(round(a.netsale/a.target*100,2),0) mach,"+ 
    		" ifnull(round(a.netsale/a.lysyear*100,2)-100,0) mgrt,"+
    		" round(a.netsale-a.target,2) mdec,"+
    		" b.ctarget,b.cnetsale,b.clysyear,"+
    		" ifnull(round(b.cnetsale/b.ctarget*100,2),0) cach,"+ 
    		" ifnull(round(b.cnetsale/b.clysyear*100,2)-100,0) cgrt,"+
    		" round(b.pmr,0) cpmr,"+
    		" ifnull(round(b.cnetsale-b.ctarget,2),0) cdec,"+
    		" ifnull(round(b.cnetsale-b.clysyear,2),0) incr,b.fs"+
    		" from ("+
    		" select mnth_code,round(sum(tarval)/100000,2) target,round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code "+
    		" group by mnth_code) a"+
    		" left join "+
    		" (select 1 mnth_code,round(sum(tarval)/100000,2) ctarget,"+
    		" round(sum(salval)/100000,2) cnetsale,round(sum(lysval)/100000,2) clysyear,t.fs,sum(salval)/fs pmr,'OCT' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct) fs"+
    		" from m_hq_master08 h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 1"+
    		" union all"+
    		" select 2 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'NOV' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov) fs"+
    		" from m_hq_master08 h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 2"+
    		" union all"+
    		" select 3 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'DEC' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 3"+
    		" union all"+
    		" select 4 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr ,'JAN' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 4"+
    		" union all"+
    		" select 5 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'FEB' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 5"+
    		" union all"+
    		" select 6 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'MAR' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 6"+
    		" union all"+
    		" select 7 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'APR' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 7"+
    		" union all"+
    		" select 8 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'MAY' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr+h.may) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ?  "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 8"+
    		" union all"+
    		" select 9 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'JUN' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr+h.may+h.jun) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 9"+
    		" union all"+
    		" select 10 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'JUL' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr+h.may+h.jun+h.jul) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 10"+
    		" union all"+
    		" select 11 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'AUG' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr+h.may+h.jun+h.jul+h.aug) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 11"+
    		" union all"+
    		" select 12 mnth_code,round(sum(tarval)/100000,2) target,"+
    		" round(sum(salval)/100000,2) netsale,round(sum(lysval)/100000,2) lysyear,t.fs,sum(salval)/fs pmr,'SEP' mname"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.oct+h.nov+h.decm+h.jan+h.feb+h.mar+h.apr+h.may+h.jun+h.jul+h.aug+h.sep) fs"+
    		" from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		" where r.mkt_year =? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= 12"+
    		" ) b "+
    		"on a.mnth_code = b.mnth_code";

    		
    		
 			psg = con.prepareStatement(queryg); 
 			int k=3;
 			psg.setInt(1,uid);
			psg.setInt(2,myear);
			for (int j=0;j<12;j++)
			{
				psg.setInt(k++,uid);
				psg.setInt(k++,uid);
				psg.setInt(k++,myear);
				psg.setInt(k++,myear);
			}
			rstg = psg.executeQuery();

    		
				while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
				{
    			
					rfb = new MktFormBean();
					rfb.setMname(rstg.getString(1));
					rfb.setUv(uv);
		             rfb.setColor(1);
		             for(int j=0;j<14;j++)
		             {
		            	 rfb.setDval0(j, rstg.getDouble((j+2)));
		             }

		             rfb.setNm3(txt2);
 	 				 rfb.setLupdate(txt6);
 	 				 
	                 data.add(rfb);
			}///////////////////// MAin query  Loop End here////////////////////////// 

				
				psg.close();
				rstg.close();

				
		} catch (Exception e) {
			
			  e.printStackTrace();
			System.out.println("========Exception in SQLForm4DAO.getSalesReview " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rstg != null){ rstg.close();}
	             if(psg != null){ psg.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
	public List getSalesReviewBP1(Connection con, int uv,int code,int smon,int emon,int myear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement psg =null;
        ResultSet rstg=null;
        	

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String queryg=null;
            String txt2=null;
            String txt3=null;
            String txt6=null;
            
            double gval[]=new double[17];
        	tblnm=tp+"_repfinal";
   	        tblnm1=tp+"_hqfs";
            
   	        txt3="(BP - 1 )";
   	        
 
            txt2="  BRAND PERFORMANCE (MONTHLY & CUMULATIVE) "+txt3+" - ASE / Sr. ASE";


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
                
            
///////////////////// Main query/////////////////////   

     		queryg =" select " +
    		" a.mkt_seq,a.mgrp,a.mkt_group,a.mgrp_name,a.mnth_code,a.target,a.netsale,a.lysyear, "+
    		" ifnull(round(a.netsale/a.target*100,2),0) mach, "+ 
    		" ifnull(round(a.netsale/a.lysyear*100,2)-100,0) mgrt,ifnull(round(a.netsale-a.lysyear,2),0) mincr,"+
    		" round(a.netsale-a.target,2) msurdef,a.mfs,a.mpmr,"+
    		" b.ctarget,b.cnetsale,b.clysyear,"+
    		" ifnull(round(b.cnetsale/b.ctarget*100,2),0) cach,"+ 
    		" ifnull(round(b.cnetsale/b.clysyear*100,2)-100,0) cgrt,"+
    		" round(b.pmr,0) cpmr,"+
    		" ifnull(round(b.cnetsale-b.ctarget,2),0) csurdef,"+
    		" ifnull(round(b.cnetsale-b.clysyear,2),0) incr,b.fs"+
    		"  from ("+
    		" select r.mkt_seq,r.mgrp,r.mkt_group,r.mgrp_name,mnth_code,round(sum(tarval)/100000,2) target,round(sum(salval)/100000,2) netsale,"+
    		" round(sum(lysval)/100000,2) lysyear,t.mfs,round(sum(salval)/t.mfs,0) mpmr"+
    		"  from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.fscnt) mfs"+
    		" from "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ?  and h.mnth_code =? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		"  where r.mkt_year = ?  and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code = ? "+
    		" group by r.mgrp,r.mkt_group,r.mgrp_name,r.mnth_code) a"+
    		" left join "+
    		" (select r.mgrp,r.mkt_group,r.mgrp_name,1 mnth_code,round(sum(tarval)/100000,2) ctarget,"+
    		" round(sum(salval)/100000,2) cnetsale,round(sum(lysval)/100000,2) clysyear,t.fs,sum(salval)/fs pmr "+
    		"  from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.fscnt) fs"+
    		" from "+tblnm1+"  h,(select * from user_ter where user_id=?  and status = 'Y') u"+
    		" where h.mkt_year = ?  and h.mnth_code <= ? "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code) t"+
    		"  where r.mkt_year = ?  and r.depo_code = u.depo_code and r.tr_cd = u.ter_code and r.mnth_code <= ? "+
    		" group by r.mgrp,r.mkt_group,r.mgrp_name"+
    		" ) b "+
    		" on  a.mgrp=b.mgrp " ;

    		
    	   		
    		
 			psg = con.prepareStatement(queryg); 
 			int k=3;
 			psg.setInt(1,uid);
			psg.setInt(2,uid);
			psg.setInt(3,myear);
			psg.setInt(4,emon);
			psg.setInt(5,myear);
			psg.setInt(6,emon);
			psg.setInt(7,uid);
			psg.setInt(8,uid);
			psg.setInt(9,myear);
			psg.setInt(10,emon);
			psg.setInt(11,myear);
			psg.setInt(12,emon);

 			
 			rstg = psg.executeQuery();

    		boolean first=true;
    		int gpcode=0;
    		int fs=0;
    		String gpname="";
				while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
				{
    			
					if(first)
					{
						gpcode=rstg.getInt(1);
						gpname=rstg.getString(3);
						first=false;
					}
					if(rstg.getInt(1)!=gpcode)
					{
						rfb = new MktFormBean();
						rfb.setMname(gpname);
			            rfb.setColor(2);
			            gval[3]=(gval[1]/gval[0]*100);
			            gval[4]=((gval[1]/gval[2]*100))-100;
			            if(fs>0)
			            {
			            	gval[7]=gval[1]/fs;
				            gval[16]=gval[9]/fs;
			            }
			            
			            gval[11]=(gval[9]/gval[8]*100);
			            gval[12]=(gval[9]/gval[10]*100)-100;
			            
			            rfb.setDval0(gval);
	                    data.add(rfb);

						gpcode=rstg.getInt(1);
						gpname=rstg.getString(3);
			            gval=new double[17];
						
					}
					
					rfb = new MktFormBean();
					rfb.setMname(rstg.getString(4));
					rfb.setUv(uv);
		            rfb.setColor(1);
	            	rfb.setDval0(0, rstg.getDouble((6)));
	            	rfb.setDval0(1, rstg.getDouble((7)));
	            	rfb.setDval0(2, rstg.getDouble((8)));
	            	rfb.setDval0(3, rstg.getDouble((9)));
	            	rfb.setDval0(4, rstg.getDouble((10)));
	            	rfb.setDval0(5, rstg.getDouble((11)));
	            	rfb.setDval0(6, rstg.getDouble((12)));
	            	rfb.setDval0(7, rstg.getDouble((14)));
	            	rfb.setDval0(8, rstg.getDouble((15)));
	            	rfb.setDval0(9, rstg.getDouble((16)));
	            	rfb.setDval0(10, rstg.getDouble((17)));
	            	rfb.setDval0(11, rstg.getDouble((18)));
	            	rfb.setDval0(12, rstg.getDouble((19)));
	            	rfb.setDval0(13, rstg.getDouble((22)));
	            	rfb.setDval0(14, rstg.getDouble((21)));
	            	rfb.setDval0(15, 0.00);
	            	rfb.setDval0(16, rstg.getDouble((20)));
	            	fs=rstg.getInt(23);
	            	gval[0]+= rstg.getDouble((6));
	            	gval[1]+= rstg.getDouble((7));
	            	gval[2]+= rstg.getDouble((8));
	            	gval[3]+= rstg.getDouble((9));
	            	gval[4]+= rstg.getDouble((10));
	            	gval[5]+= rstg.getDouble((11));
	            	gval[6]+= rstg.getDouble((12));
	            	gval[7]+= rstg.getDouble((14));
	            	gval[8]+= rstg.getDouble((15));
	            	gval[9]+= rstg.getDouble((16));
	            	gval[10]+= rstg.getDouble((17));
	            	gval[11]+= rstg.getDouble((18));
	            	gval[12]+= rstg.getDouble((19));
	            	gval[13]+= rstg.getDouble((22));
	            	gval[14]+= rstg.getDouble((21));
	            	gval[15]=0.00;
	            	gval[16]+= rstg.getDouble((20));


	            	
	            	rfb.setNm3(txt2);
 	 				 rfb.setLupdate(txt6);
 	 				 
	                 data.add(rfb);
			}///////////////////// MAin query  Loop End here////////////////////////// 

				rfb = new MktFormBean();
				rfb.setMname(gpname);
	            rfb.setColor(2);
	            rfb.setDval0(gval);
                data.add(rfb);

				psg.close();
				rstg.close();

				
		} catch (Exception e) {
			
			  e.printStackTrace();
			System.out.println("========Exception in SQLForm4DAO.getSalesReviewBP1 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rstg != null){ rstg.close();}
	             if(psg != null){ psg.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				  e.printStackTrace();
				}
			}
		return data;
	}
	
	public List getSalesReviewBP2(Connection con, int uv,int code,int smon,int emon,int myear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement psg =null;
        ResultSet rstg=null;
        	
     	String wname=null;
 	    int mon=0;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String queryg=null;
            String txt2=null;
            String txt3=null;
            String txt6=null;
            
            double gval[]=new double[15];
            double ggval[]=new double[15];
        	tblnm=tp+"_repfinal";
   	        tblnm1=tp+"_hqfs";
            
   	        txt3="(BP - 2 )";
   	        
 
            txt2="  BRAND PERFORMANCE MONTH WISE TREND "+txt3+" - ASE / Sr. ASE";


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
                
            
///////////////////// Main query/////////////////////   

    		
    		queryg =" select "+
    		" a.mkt_seq,a.mgrp,a.mkt_group,a.mgrp_name,a.mnth_code,'Sale' Typ,a.netsale"+
    		" from ("+
    		" select r.mkt_seq,r.mgrp,r.mkt_group,r.mgrp_name,mnth_code,round(sum(tarval)/100000,2) target,round(sum(salval)/100000,2) netsale,"+
    		" round(sum(lysval)/100000,2) lysyear,t.mfs,round(sum(salval)/t.mfs,0) mpmr"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select SUM(h.fscnt) mfs from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ? and h.mnth_code = ?"+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code"+
    		" ) t"+
    		" where r.mkt_year =? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code"+ 
    		" group by r.mnth_code,r.mgrp,r.mkt_group,r.mgrp_name,r.mnth_code) a"+
    		" union all"+
    		" select "+
    		" a.mkt_seq,a.mgrp,a.mkt_group,a.mgrp_name,0 mnth_code,'Target' Typ, a.target"+
    		" from ("+
    		" select r.mkt_seq,r.mgrp,r.mkt_group,r.mgrp_name,round(sum(tarval/12)/100000,2) target,round(sum(salval)/100000,2) netsale,"+
    		" round(sum(lysval)/100000,2) lysyear,t.mfs,round(sum(lysval)/t.mfs,0) lyspmr"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select ifnull(SUM(h.fscnt),0) mfs from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ?-1 "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code"+
    		" ) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code "+
    		" group by r.mgrp,r.mkt_group,r.mgrp_name) a"+
    		" union all"+
    		" select "+
    		" a.mkt_seq,a.mgrp,a.mkt_group,a.mgrp_name,-1 mnth_code,'Ly Sale' Typ, ifnull(a.lyspmr,0)"+
    		" from ("+
    		" select r.mkt_seq,r.mgrp,r.mkt_group,r.mgrp_name,round(sum(tarval/12)/100000,2) target,round(sum(salval)/100000,2) netsale,"+
    		" round(sum(lysval)/100000,2) lysyear,t.mfs,round(sum(lysval)/t.mfs,0) lyspmr"+
    		" from  "+tblnm+"  r,(select * from user_ter where user_id=? and status = 'Y') u,"+
    		" (select ifnull(SUM(h.fscnt),0) mfs from  "+tblnm1+"  h,(select * from user_ter where user_id=? and status = 'Y') u"+
    		" where h.mkt_year = ?-1 "+
    		" and h.depo_code = u.depo_code and h.ter_code = u.ter_code"+
    		" ) t"+
    		" where r.mkt_year = ? and r.depo_code = u.depo_code and r.tr_cd = u.ter_code "+
    		" group by r.mgrp,r.mkt_group,r.mgrp_name) a"+
    		" order by mkt_seq,mgrp,mnth_code "	;

    		
     
    		
    	   		
    		
 			psg = con.prepareStatement(queryg); 
 			int k=3;
 			psg.setInt(1,uid);
			psg.setInt(2,uid);
			psg.setInt(3,myear);
			psg.setInt(4,emon);
			psg.setInt(5,myear);
 			psg.setInt(6,uid);
			psg.setInt(7,uid);
			psg.setInt(8,myear);
			psg.setInt(9,myear);
 			psg.setInt(10,uid);
			psg.setInt(11,uid);
			psg.setInt(12,myear);
			psg.setInt(13,myear);
			

 			
 			rstg = psg.executeQuery();

    		boolean first=true;
    		int gpcode=0;
       		int pcode=0;
       		boolean pfirst=true;
       		String mname="";
       		String gpname="";
       		int j=0;
				while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
				{
    			
					if(first)
					{
						gpcode=rstg.getInt(1);
						pcode=rstg.getInt(2);
						gpname=rstg.getString(3);
						mname=rstg.getString(4);
						first=false;
					}

					if(rstg.getInt(2)!=pcode)
					{
						
						for(k=2;k<14;k++)
							gval[14]+=gval[k];
						
						rfb = new MktFormBean();
						rfb.setMname(mname);
						rfb.setUv(uv);
			            rfb.setColor(1);
			            rfb.setDval0(gval);
		            	rfb.setNm3(txt2);
 	 				    rfb.setLupdate(txt6);
	 	 				 
	                    data.add(rfb);

						pcode=rstg.getInt(2);
						mname=rstg.getString(4);
			            gval=new double[17];
			            j=0;
					}

					if(rstg.getInt(1)!=gpcode)
					{

						for(k=2;k<14;k++)
							ggval[14]+=ggval[k];
						
						rfb = new MktFormBean();
						rfb.setMname(gpname);
						rfb.setUv(uv);
			            rfb.setColor(2);
			            rfb.setDval0(ggval);
		            	rfb.setNm3(txt2);
 	 				    rfb.setLupdate(txt6);
	                    data.add(rfb);

						gpcode=rstg.getInt(1);
						gpname=rstg.getString(3);
			            ggval=new double[17];
						
					}
					

	            	gval[j]= rstg.getDouble((7));
	            	ggval[j]+= rstg.getDouble((7));
	            	j++;

	            	
			}///////////////////// MAin query  Loop End here////////////////////////// 

				rfb = new MktFormBean();
				rfb.setMname(gpname);
				rfb.setUv(uv);
	            rfb.setColor(2);
	            rfb.setDval0(ggval);
            	rfb.setNm3(txt2);
				rfb.setLupdate(txt6);
                data.add(rfb);

				psg.close();
				rstg.close();

				
		} catch (Exception e) {
			
			  e.printStackTrace();
			System.out.println("========Exception in SQLForm4DAO.getSalesReviewBP1 " + e); 
		}
		  finally 
			{
			  try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rstg != null){ rstg.close();}
	             if(psg != null){ psg.close();}
	             if(con != null){con.close();}

				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLForm4DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
}