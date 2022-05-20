package com.aristo.hq;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm10DAO {

	public List getAllHQ(Connection con, int uv,int sale,int code,int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
		MktFormBean rfb;
 	    int mon=0;
 	    String uvnm=null;
		String uvnm1=null;
		PreparedStatement ps12 = null;
		ResultSet rst12 = null;
		PreparedStatement ts1 = null;
		ResultSet trec = null;
		PreparedStatement psg = null;
		ResultSet rstg = null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;
		PreparedStatement ms1 = null;
		ResultSet mrec = null;
		PreparedStatement ps3 = null;
		ResultSet rst3 = null;
		PreparedStatement ps33 = null;
		ResultSet rst33 = null;
		
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try { 
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String queryg=null;
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
            double amontar=0.00;
            double cumtar=0.00;
            double umontar=0.00;
            double aumontar=0.00;            
            double ucumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double monsd=0.00;
            double cumsd=0.00;
            double monqty=0.00;
            double cumqty=0.00;
///////////////Group Total ke liye/////////////////
            double ggmontar=0.00;
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

	    	tblnm=tp+"_target08";
	    	tblnm1=tp+"_product08";
	        tblnm2=tp+"_hq_master08";
	        tblnm3=tp+"_group_mkt08";
   	        tblnm4=tp+"_hqdetail08";
   	        
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
            if (sale==1) 
            {
            	txt6=txt6+" GROSS SALE";
            }
            
            if (sale==2)
            {
            	txt6=txt6+" CREDIT SALE";
            }
            if (sale==3)
            {
            	txt6=txt6+" NET SALE";
            }
            txt2="     PRODUCT WISE "+txt6+" TARGET SALE COMPARISON FROM "; 
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
		txt7= rst12.getString(1)+", TIME: "+rst12.getString(2);
		
		rst12.close();
		ps12.close();
        		                
///////////////////////// Headquater master ki query///////////////////// 
        String terrec="Select t.ter_name from "+tblnm2+" as t,user_ter as u " +
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
            txt1="H.Q. -> "+trec.getString(1);
		
		trec.close();
		ts1.close();
	
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
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
 			amontar=0.00;
 			montar=0.00;
            monsal=0.00;
            monsd=0.00;
            aumontar=0.00;
            ucumtar=0.00;
            cumqty=0.00;
            icumsd=0;
            cumtar=0.00;
            cumsal=0.00;
            cumsd=0.00;
            ggmontar=0.00;
		            
		            while (mrec.next())/////////////////Monthfile Loop Start///////////////////
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
	 			       mon=mrec.getInt(4);
	 			           
//////////////////////// Target master ki query///////////////////////////////
	 			           
	 		String query3=null;
	        query3 = "Select sum(((tt"+mon+"*ttarget)/100)*tmrp),sum(((tt"+mon+"*ttarget)/100)),budmnth,sum((budtar*budper)/100),sum((budval*budper)/100) from "+tblnm+" where tr_cd=? "+
	 		" and pr_code=? and depo_code=? and mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd,pr_code"; 	 			           
	 			           
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code); 
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(5));	
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())/////////////////Target Master Start///////////////////
			        {
					         ggmontar = rst3.getDouble(1);
					         gcumtar = gcumtar+rst3.getDouble(1);
					            
					         if ((uv==1)||(uv==3))
					         {
					            umontar = rst3.getDouble(2);
					            ucumtar = ucumtar+rst3.getDouble(2);
					            
						    	if (mrec.getInt(6)>=rst3.getInt(3))
						    	 {
						    		aumontar =rst3.getDouble(4);
						    		ucumtar =ucumtar+rst3.getDouble(4);						    		
						    	 }
					            
					         }
					         if ((uv==2)||(uv==3))
					         {
					            montar = rst3.getDouble(1);
					            cumtar = cumtar+rst3.getDouble(1);
					            
						    	if (mrec.getInt(6)>=rst3.getInt(3))
						    	 {
						    		amontar =rst3.getDouble(5);
						    		cumtar =cumtar+rst3.getDouble(5);						    		
						    	 }

					         }
					          		            
			         }	 /////////////////////Target Master End//////////////////////
			        
			        ps3.close();
			        rst3.close();	 
			        
			         uvnm="qty"+mrec.getString(3);
 	            	 uvnm1="val"+mrec.getString(3);

                     //////////////////////////////HQ Detail Ki Query/////////////////////////////////			    
 	            	 
 	            	 query3 = "Select sum("+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum(r"+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+")," +
			 	      " sum("+uvnm1+"),sum(e"+uvnm1+"),sum(b"+uvnm1+"),sum(r"+uvnm1+"),sum(s"+uvnm1+"),sum(p"+uvnm1+") from "+tblnm4+ 
			 	      " where  tr_cd=? and mpr_code=? and depo_code=? and mkt_year=? group by mpr_code order by ar_cd,rg_cd,tr_cd,mpr_code";

			 	   	   ps33 = con.prepareStatement(query3); 	
			 	   	   ps33.setInt(1,code);
				       ps33.setInt(2,rst1.getInt(1));
				       ps33.setInt(3,depo_code);
				       ps33.setInt(4,mrec.getInt(5)); 
				       rst33 = ps33.executeQuery(); 
				        if(rst33.next())
				        {
				         if (sale==1)
				         {	 
				        		 monqty =rst33.getInt(1);
				        		 monsal =rst33.getDouble(7);
				        		 cumqty =cumqty+rst33.getInt(1);
				        		 cumsal =cumsal+rst33.getDouble(7);
				         }	 
				        	 
				         if (sale==2)
				         {	 
				        	     monqty =(rst33.getInt(2)+rst33.getInt(3)+rst33.getInt(4)+rst33.getInt(5)+rst33.getInt(6));
				        	     monsal =(rst33.getDouble(8)+rst33.getDouble(9)+rst33.getDouble(10)+rst33.getDouble(11)+rst33.getDouble(12));
				        	     cumqty =cumqty+(rst33.getInt(2)+rst33.getInt(3)+rst33.getInt(4)+rst33.getInt(5)+rst33.getInt(6));
				        	     cumsal =cumsal+(rst33.getDouble(8)+rst33.getDouble(9)+rst33.getDouble(10)+rst33.getDouble(11)+rst33.getDouble(12));
				         }	 
				         if (sale==3)
				         {	 
				        	     monqty = rst33.getInt(1)-(rst33.getInt(2)+rst33.getInt(3)+rst33.getInt(4)+rst33.getInt(5)+rst33.getInt(6));
				        		 monsal = rst33.getDouble(7)-(rst33.getDouble(8)+rst33.getDouble(9)+rst33.getDouble(10)+rst33.getDouble(11)+rst33.getDouble(12));
				        	     cumqty = cumqty+rst33.getInt(1)-(rst33.getInt(2)+rst33.getInt(3)+rst33.getInt(4)+rst33.getInt(5)+rst33.getInt(6));
				        		 cumsal = cumsal+rst33.getDouble(7)-(rst33.getDouble(8)+rst33.getDouble(9)+rst33.getDouble(10)+rst33.getDouble(11)+rst33.getDouble(12));
				         }
				        }
			        
			      			        
			        }  ///////////////////////Monthfile Loop End here//////////////////////
		            ms1.close();
		 			mrec.close();
		 			ggmontar=ggmontar+amontar;
		 			umontar=umontar+aumontar;
		 			montar=montar+amontar;
		 			
////////////////////////////Month File Loop End here//////////////////////////////////
		 			
		            if ((uv==1)||(uv==3))
		            {
		             imonsd=(int)monqty-(int)(umontar+.50); 
		             icumsd=(int)cumqty-(int)(ucumtar+.50);
		            }

		            if ((uv==2)||(uv==3))
		            {
		             monsd=monsal-montar; 
		             cumsd=cumsal-cumtar;
		            }
		             
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

			         gmontar = gmontar+ggmontar;
			         gmonsal = gmonsal+monsal;
			         gcumsal = gcumsal+cumsal;
			         

			  }///////////////////// Product Master Loop End here////////////////////////// 
				rst1.close();
				ps1.close();
				    
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

///////////////////////////// grand total ke liye//////////////////////////
			        	tcumtar = tcumtar+gcumtar;
			            tcumsal = tcumsal+gcumsal;
			            tmontar = tmontar+gmontar;
				        tmonsal = tmonsal+gmonsal;
 	 				
 	 				
			}///////////////////// Group Master Loop End here////////////////////////// 
				rstg.close();
				psg.close();
				ts1.close();
				trec.close();

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
 	 				ps33.close();
 	 				rst33.close();
 	 				
		 
		}
		catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLForm10DAO.getHQ " + e); 
		}
		  finally 
			{
			  try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(trec != null){ trec.close();}
		             if(ts1 != null){ts1.close();}
		             if(rstg != null){ rstg.close();}
		             if(psg != null){ psg.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ps1.close();}
		             if(mrec != null){ mrec.close();}
		             if(ms1 != null){ms1.close();}
		             if(rst3 != null){ rst3.close();}
		             if(ps3 != null){ps3.close();}
		             if(rst33 != null){ rst33.close();}
		             if(ps33 != null){ps33.close();}
		             if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in HQ-SQLForm10DAO.Connection.close "+e);
				}
			}
		return data;
	}	

}