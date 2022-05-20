package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm5DAO {

	public List getAllHQ(Connection con, int uv,int code,int tl,int eyear,int depo_code,String tp,int uid,String utype) { 
		 
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
        PreparedStatement psg =null;
        ResultSet rstg=null;
     	int mon=0;
 	    int k=0;
 	    
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

        	tblnm=tp+"_target08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";
   	        tblnm3=tp+"_group_mkt08";
            
            if (uv==1) 
            {
            	txt3="UNIT WISE ";
            }
            if (uv==2)
            {
            	txt3="VALUE WISE ";
            }
                txt2="   PRODUCT WISE "+txt3+" TARGET/SALES TREND FOR THE YEAR "; 
                
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
            txt1="H.Q. WISE-> "+trec.getString(1);
	
///////////////////// Group master ki query/////////////////////   
        queryg = "Select distinct(gp_code),gp_name from "+tblnm3+" where gp_code<>? and mkt_year=? group by gp_code order by gp_code";			
		psg = con.prepareStatement(queryg);
		psg.setInt(1,0);
		psg.setInt(2,eyear);
		rstg = psg.executeQuery();
	
		while (rstg.next())/////////////////Group Master Loop Start Here///////////////////   
		{
            ggmontar=0.00;
            ggmonsal=0.00;
                			
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
			rfb.setUv(tl);
			
////////////// Month File Loop Starts to accumulate data///////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,myear,mnth_ord from monthfl where mkt_year=? order by mnth_ord";  
		ms1 = con.prepareStatement(month);
		ms1.setInt(1,eyear);
		mrec = ms1.executeQuery();
		montar=0.00;
		monsal=0.00;
        lstsal=0.00;
		ggmontar=0.00;
        ggmonsal=0.00;
        gglstsal=0.00;
        k=0;
        
        while (mrec.next())/////////////////Monthfile Loop Start///////////////////
		{	
	           mon=mrec.getInt(4);
	           txt4=mrec.getString(5);    
//////////////////////// Target master ki query///////////////////////////////
	 			           
	 		String query3=null;
	 		
	              query3 = "Select (((tt"+mon+"*ttarget)/100)*tmrp),ra"+mon+",rl"+mon+",((tt"+mon+"*ttarget)/100),ta"+mon+",la"+mon+",budmnth,budper,budtar,budval from "+tblnm+" where tr_cd=? "+
	 			  " and pr_code=? and depo_code=? and  mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd,pr_code"; 	 			           
	 			           
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code); 
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,eyear);
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())/////////////////Target Master Start///////////////////
			        {
			            ggmontar = rst3.getDouble(1);
			            ggmonsal = rst3.getDouble(2);
			            gglstsal = rst3.getDouble(3);
			        	montar = rst3.getDouble(4);
			            monsal = rst3.getDouble(5);
			            lstsal = rst3.getDouble(6);
				    	if (mrec.getInt(6)>=rst3.getInt(7))
				    	 {
				    		montar = montar+((rst3.getDouble(9)*rst3.getDouble(8))/100);
				    		ggmontar = ggmontar+((rst3.getDouble(10)*rst3.getDouble(8))/100);				    		
				    	 }
				    	
			         }	 /////////////////////Target Master End//////////////////////
			        
			        ps3.close();
			        rst3.close();	
			        	
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
			    
			        rfb.setNm1(k, mrec.getString(3));
			        rfb.setLupdate(txt6);
			    	
			        k++;
			    	
			        }  ///////////////////////Monthfile Loop End here//////////////////////
		            ms1.close();
		 			mrec.close();

////////////////////////////Month File Loop End here//////////////////////////////////
		 		     
		             rfb.setColor(1);
 	 				 rfb.setNm3(txt1+txt2+txt4);
	                 data.add(rfb);

			  }///////////////////// Product Master Loop End here////////////////////////// 
				ps1.close();
				rst1.close();
				
				 rfb = new MktFormBean();
					 rfb.setMname("VALUE OF "+rstg.getString(2));
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
					}
					if (tl==2)
					{
						if(glstsal[i]>0)
						   rfb.setQty1(i, (int)(glstsal[i]+.50));
					
						if(glstsal[i]<0)
							   rfb.setQty1(i, (int)(((glstsal[i]*-1)+.50)*-1));
					}
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
					}

					
						gmontar[i]=0.00;
				        gmonsal[i]=0.00;
				        glstsal[i]=0.00;
				}
 	 				data.add(rfb);

///////////////////////////// grand total ke liye//////////////////////////
			            
			}///////////////////// Group Master Loop End here////////////////////////// 
				psg.close();
				rstg.close();
				ts1.close();
				trec.close();

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
			
			System.out.println("========Exception in HQ-SQLForm5DAO.getForm5 " + e); 
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
				  System.out.println("-------------Exception in HQ-SQLForm5DAO.Connection.close "+e);
				}
			}
		return data;
	}

}    