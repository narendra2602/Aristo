package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm12DAO {

	public List getAllHQ(Connection con, int code, int smon,int emon,int eyear,int depo_code,String tp,int uid,String utype) { 
		MktFormBean rfb; 
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		PreparedStatement ms1=null;
		ResultSet mrec=null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement psq=null; 
        ResultSet rstq=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        
       	int mon=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String tblnm3=null;
            String terrec=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String query3=null;
            int trep=0;
            int grep=0;
           
            if (smon>emon) 
            	emon=smon;
            int index=emon;
            
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double glyr=0.00;
            double atarget=0.00;
            
            double []target;
            double []sale;
            double []ach;
            double []gth;
            double []pmr;
            double []fs;
            double []lsale;
            
            double []gtarget;
            double []gsale;
            double []gach;
            double []ggth;
            double []gpmr;
            double []gfs;
            double []glst;   
            double []glsale;
            int [] ggrep;
            
//        	tblnm=tp+"_target08";
        	tblnm=tp+"_report";

            tblnm2=tp+"_hq_master08";
            tblnm3=tp+"_group_mkt08";

            txt2=" H.Q. WISE RUPEES WISE SALES ANALYSIS FROM ";
			
            target=new double [index];
            sale=new double [index];
            ach=new double [index];
            gth=new double [index];
            pmr=new double [index];
            fs=new double [index];
            lsale=new double [index];
            
            gtarget=new double [index];
            gsale=new double [index];
            gach=new double [index];
            ggth=new double [index];
            gpmr=new double [index];
            gfs=new double [index];
            glst=new double [index];   
            glsale=new double [index];
            ggrep=new int [index];
            
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
			
////////////////////////////////////////Group Master////////////////////////////
			terrec = "Select gp_code,gp_name from "+tblnm3+" where gp_code=? and mkt_year=? order by gp_code";
            ts1 = con.prepareStatement(terrec);
            ts1.setInt(1, code);
            ts1.setInt(2,eyear);
            trec = ts1.executeQuery();
            if (trec.next())
                txt1="GROUP -> "+trec.getString(2);
                txt2=txt1+txt2;
               
             trec.close();
             ts1.close();
             
////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1, smon);
	 		ms1.setInt(2, emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
    		
////////////////////////HQ  Master Query/////////////////////////////////            
            String query1="Select h.ter_code,h.ter_name,h.no_of_rep,u.depo_code from "+tblnm2+" as h, " +
            " user_ter as u where h.depo_code=u.depo_code and h.ter_pt=u.access_t and h.ter_code=u.ter_code " +
            " and h.mkt_year="+eyear+" and u.user_id="+uid+" order by h.ter_code ";            
            ps1 = con.prepareStatement(query1); 
			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
                rfb.setMcode(index);
                
                int k=0;  
                hval=0.0f;
                    ///// Month File Loop Starts to accumulate data

                	mrec.beforeFirst();
		 			while (mrec.next())///////////// Month Loop Start
		 			{	
		 			     if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 				         if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);		 				       
		 			      
   		 			        rfb.setNm9(k, mrec.getString(3));
		 			        mon=mrec.getInt(4);
		 			        wname=mrec.getString(3);
		 			        if (wname.equals("DEC"))
		 			        	wname="DECM";

////////////////////////////////////HQ Master Query/////////////////////////////		    
	          String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? and ter_code=? and mkt_year=? order by ter_code";
	          psq = con.prepareStatement(queryq);
		      psq.setInt(1,rst1.getInt(4));
		      psq.setString(2,tp);
		 	  psq.setInt(3,rst1.getInt(1));
		 	  psq.setInt(4,eyear);
		 	  rstq = psq.executeQuery();
		 			 			        
		 			 	    if (rstq.next())
		 			 	    {
		 			 	    	trep=rstq.getInt(1);		
		 			 	    	grep=grep+rstq.getInt(1);
		 			 	    }
		 			 		
		 		  rstq.close();
		 		  psq.close();		 			        

//////////////////////////////////// Target Master Ki Query/////////////////
//		 	query3 = "Select sum(((tt"+mon+"*tvalue)/100)), sum(ra"+mon+"),sum(rl"+mon+"),budmnth,budper,sum(budval) from "+tblnm+" where tr_cd=? "+
//            " and depo_code=? and grp_cd=?  and mkt_year=? group by grp_cd order by ar_cd,rg_cd,tr_cd,grp_cd";
		 	
		 	 query3 = "Select sum(tt"+mon+"),sum(ra"+mon+"),sum(rl"+mon+"),0 as budmnth,0 as budper,0 as budval from "+tblnm+" where tr_cd=? "+
             " and depo_code=? and mgrp=? and mkt_year=? group by grp_cd order by ar_cd,rg_cd,tr_cd,grp_cd"; 

		    ps3 = con.prepareStatement(query3); 
			ps3.setInt(1,rst1.getInt(1)); 
			ps3.setInt(2,rst1.getInt(4));
			ps3.setInt(3,code);
			ps3.setInt(4,mrec.getInt(5));
			rst3 = ps3.executeQuery(); 
			
			   if(rst3.next())/////////////// Target Master Start/////////////
		        {
			   		   lyr=lyr+(rst3.getDouble(3));
			   		   
			   		   gtarget[k] = gtarget[k]+(rst3.getDouble(1));
			   		   glst[k] = glst[k]+(rst3.getDouble(3));


				    	if (mrec.getInt(6)>=rst3.getInt(4))
				    	 {
				    		atarget = rst3.getDouble(1)+((rst3.getDouble(6)*rst3.getDouble(5))/100);
				    		gtarget[k] =gtarget[k]+((rst3.getDouble(6)*rst3.getDouble(5))/100);						    		
				    	 }
				    	else
				    	{
				    		atarget = (rst3.getDouble(1));
				    	}

				   	   target[k] = atarget;
			   		   sale[k] = (rst3.getDouble(2));
			   		   lsale[k] = (rst3.getDouble(3));
                       
                    gsale[k]=gsale[k]+sale[k];
                    glsale[k]=glsale[k]+lsale[k];
                     
                    if (target[k]!=0) 
                     ach[k] = (sale[k]/target[k])*100.00;
                    else
                     ach[k] = 0.00;
                    
                    if (rst3.getDouble(3)!=0)
                     gth[k] = ((sale[k]/rst3.getDouble(3))*100.00)-100.00;
                    else
                  	  gth[k]=0;
                    
                    if (trep!=0)
		                pmr[k] = sale[k]/trep;
                    else
                  	pmr[k]=0;
       
                      fs[k]=trep;
                      gfs[k]=gfs[k]+trep;
                      
                      ggrep[k]=ggrep[k]+trep;
                      
                      
		        }/////////////Target Master End/////////////////	 

	 			rst3.close();
		   		ps3.close();
		   
              k++; 
		        }//End of Month loop///////////////////////     
	 			rfb.setNm3(txt2+txt5);
	 			rfb.setLupdate(txt6);
	 			
            /////////////////Target///////////////////
	 		  hval=0.00;
            for( int y=0;y<index;y++)
            {
          	  rfb.setColor(1);
		 			  rfb.setNm1(y, rst1.getString(2)+" TARGET");
          	  rfb.setDval0(y, target[y]);
          	  hval=hval+target[y];
            }
                rfb.setDval1(hval);  
                data.add(rfb);
                
            /////////////////Sales///////////////////    
            rfb = new MktFormBean();
            gval=0.00;
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" SALES");
		 			 rfb.setDval0(y, sale[y]);
		 		     gval=gval+sale[y];
            }
                rfb.setDval1(gval); 
                data.add(rfb);
                
	          /////////////////Last Year Sales///////////////////    
	              rfb = new MktFormBean();
                for( int y=0;y<index;y++)
                {
		 			 rfb.setNm1(y, rst1.getString(2)+" LYR SALES");
 		 			 rfb.setDval0(y, lsale[y]);
                }
	                  rfb.setDval1(lyr); 
	                  data.add(rfb); 
                
            /////////////////Achievement///////////////////
			  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" ACH.");
		 		 rfb.setDval0(y, ach[y]);
            }

            if (hval!=0) 
                rfb.setDval1((gval/hval)*100.00);
            	  data.add(rfb);
            	  
	              /////////////////Growth///////////////////                        
		  	  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" GROWTH");
		 		 rfb.setDval0(y, gth[y]);
            }
                if (lyr!=0)
              	  rfb.setDval1(((gval/lyr)*100.00)-100.00);
                    data.add(rfb);
				 
            /////////////////PMR///////////////////
				 
			  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" PMR");
		 		 rfb.setDval0(y, pmr[y]);
            }
                 if (grep!=0)
              	rfb.setDval1(gval/grep);
              	data.add(rfb);
				 
            /////////////////FS///////////////////
			  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" FS");
		 		 rfb.setDval0(y, fs[y]);
            }
            	   rfb.setDval1(grep); 
				   data.add(rfb); 
           ///////////////////Fs Close////////////    
				 lyr=0.00;
				 grep=0;
				 
			     trep=0;
			   
				 hval=0.00f;
			} 
		 	mrec.close();
		 	ms1.close();
		 	rst1.close();
		 	ps1.close();
		 	
		         rfb = new MktFormBean();
//////////////////////////////////Grand Total ke Liye//////////////////////////////////
				  /////////////////Target///////////////////
		 		  hval=0.00;
                 for( int y=0;y<index;y++)
                 {
               	  rfb.setColor(3);
 		 			  rfb.setNm1(y, "TOTAL TARGET");
               	  rfb.setDval0(y, gtarget[y]);
               	  hval=hval+gtarget[y];
                 }
	                  rfb.setDval1(hval);  
	                  data.add(rfb);
	                  
	              /////////////////Sales///////////////////    
	              rfb = new MktFormBean();
                 gval=0.00;
	              for( int y=0;y<index;y++)
                 {
	            	 rfb.setColor(3);
 		 			 rfb.setNm1(y, "TOTAL SALES");
  		 			 rfb.setDval0(y, gsale[y]);
  		 		     gval=gval+gsale[y];
                 }
	                  rfb.setDval1(gval); 
	                  data.add(rfb); 
	                  
               /////////////////Last Yr Sales///////////////////
	                  lyr=0.00;
		              rfb = new MktFormBean();
		              for( int y=0;y<index;y++)
	                  {
		            	 rfb.setColor(3);
	  		 			 rfb.setNm1(y, "TOTAL LYR SALES");
	   		 			 rfb.setDval0(y, glsale[y]);
	   		 		     lyr=lyr+glsale[y];
	                  }
		                  rfb.setDval1(lyr); 
		                  data.add(rfb);  		                  
	              /////////////////Achievement///////////////////
	 			  rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL ACH.");
                   if (gtarget[y]!=0) 
                       gach[y] = (gsale[y]/gtarget[y])*100.00;
  		 			 
   		 		 rfb.setDval0(y, gach[y]);
                 }

                 if (hval!=0) 
                     rfb.setDval1((gval/hval)*100.00);
                 	  data.add(rfb);
                 	  
  	              /////////////////Growth///////////////////                        
	 		  	  rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL GROWTH");
                   if (glst[y]!=0)
                       ggth[y] = ((gsale[y]/glst[y])*100.00)-100.00;
                       glyr=glyr+glst[y];
                       
   		 		 rfb.setDval0(y, ggth[y]);
                 }
                     if (glyr!=0)
	                	  rfb.setDval1(((gval/glyr)*100.00)-100.00);
	                      data.add(rfb);
	 				 
	              /////////////////PMR///////////////////
	 			  grep=0;	 
	 			  rfb = new MktFormBean();
	 			  for( int y=0;y<index;y++)
                 {
               	 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL PMR");
  		 		     if (ggrep[y]!=0)
	                 gpmr[y] = gsale[y]/ggrep[y];
  		 		     grep=grep+ggrep[y];
  		 			 rfb.setDval0(y, gpmr[y]);
                 }
                      if (grep!=0)
                   	rfb.setDval1(gval/grep);
                   	data.add(rfb);
	 				 
	              /////////////////FS///////////////////
	 			  rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL FS");
   		 		 rfb.setDval0(y, gfs[y]);
                 }
                 	   rfb.setDval1(grep); 
	 				   data.add(rfb); 
                ///////////////////Fs Close////////////   
 					 
		} catch (Exception e) 
		      {
			  System.out.println("===Exception in HQ-SQLForm12DAO.getAllHQ " + e); 
		      }

		  finally 
			{
				try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
		             if(trec != null){ trec.close();}
		             if(ts1 != null){ ts1.close();}
		             if(mrec != null){mrec.close();}
		             if(ms1 != null){ ms1.close();}
		             if(rst1 != null){ rst1.close();}
		             if(ps1 != null){ ps1.close();}
		             if(rstq != null){ rstq.close();}
		             if(psq != null){psq.close();}
		             if(rst3 != null){ rst3.close();}
		             if(ps3 != null){ps3.close();}
		             if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in HQ-SQLForm12DAO.Connection.close "+e);
				}
			}
		return data;
	}
	

}
