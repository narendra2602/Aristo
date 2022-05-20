package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm11DAO {

	public List getAllHQ(Connection con, int smon,int emon,int eyear,int depo_code,String tp,int uid) { 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12 =null;
		PreparedStatement ms1 =null; 
		ResultSet mrec =null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1 =null;
        PreparedStatement psq=null; 
        ResultSet rstq =null;
        PreparedStatement ps3=null; 
        ResultSet rst3 =null;
		int mon=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
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
            
        	//tblnm=tp+"_target08";
        	tblnm=tp+"_report";
            tblnm2=tp+"_hq_master08";
           
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
        		                   
////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
    		
////////////////////////HQ  Master Query/////////////////////////////////            
            String query1 ="Select h.ter_code,h.ter_name,h.no_of_rep,u.depo_code from "+tblnm2+" as h,user_ter as u " +
            " where h.depo_code=u.depo_code and h.ter_code=u.ter_code and user_id="+uid+" and ter_pt=? " +
            " and h.mkt_year=? order by h.ter_code ";
			ps1 = con.prepareStatement(query1); 
			ps1.setString(1,tp);
			ps1.setInt(2,eyear);
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
	          String queryq = "Select sum("+wname+") from "+tblnm2+" where depo_code=? and ter_pt=? " +
	          " and ter_code=? and mkt_year=? order by ter_code";
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

	//	 	 query3 = "Select sum(((tt"+mon+"*tvalue)/100)),sum(ra"+mon+"),sum(rl"+mon+"),budmnth,budper,sum(budval) from "+tblnm+" where tr_cd=? "+
    //         " and depo_code=? and mkt_year=? group by tr_cd order by ar_cd,rg_cd,tr_cd"; 

		 	 query3 = "Select sum(tt"+mon+"),sum(ra"+mon+"),sum(rl"+mon+"),0 as budmnth,0 as budper,0 as budval from "+tblnm+" where tr_cd=? "+
             " and depo_code=? and mkt_year=? group by tr_cd order by ar_cd,rg_cd,tr_cd"; 

		 	 
		    ps3 = con.prepareStatement(query3); 
			ps3.setInt(1,rst1.getInt(1)); 
			ps3.setInt(2,rst1.getInt(4));
			ps3.setInt(3,mrec.getInt(5));
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
			  System.out.println("===Exception in HQ-SQLForm11DAO.getAllHQ " + e); 
		      }

		  finally 
			{
				try
				{
		             if(rst12 != null){ rst12.close();}
		             if(ps12 != null){ ps12.close();}
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
					System.out.println("-------------Exception in HQ-SQLForm11DAO.Connection.close "+e);
				}
			}
		return data;
	}

	
//////////////////////////////////////HQ Coding Start here ///////////////////////////////////////	
	public List getAllHQBrand(Connection con,int code, int smon, int emon, int eyear, int depo_code , String tp,int uid,String utype,int pg)  { 
		 
		MktFormBean rfb=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String query1=null;
            String txt1=null;
            String txt5 =null;
            String txt6=null;
            double salval[];
            double tval=0.00;
            double gtval=0.00;
            double gval[];
            double grval[];
            int tqty=0;
            int gqty[];
            int grqty[];
            int gtqty=0;

            double tsal=0.00;
            double ttar=0.00;
            double gtsal[];
            double gttar[];
            
            int pmr[];
            int k=0;
            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_repfinal");
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3=(tp+"_tr_pmr");
   	        
   	     
   	        if(code==1)
   	        	txt1="BRAND PERFORMANCE HQ WISE Sales -  ";
   	        else if(code==2)
   	        	txt1="BRAND PERFORMANCE HQ WISE Target -  ";
   	        else if(code==3)
   	        	txt1="BRAND PERFORMANCE HQ WISE Last Year -  ";
   	        else if(code==4)
   	        	txt1="BRAND PERFORMANCE HQ WISE Achievement -  ";
   	        else if(code==5)
   	        	txt1="BRAND PERFORMANCE HQ WISE Growth -  ";
   	        else if(code==6)
   	        	txt1="BRAND PERFORMANCE HQ WISE PMR -  ";
   	        else if(code==7)
   	        	txt1="BRAND PERFORMANCE HQ WISE SURPLUS/DEFICIT -  ";
          	 
            int t=0;
            int trep=0;
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
    		
///////////////////////////////////////Month File Query////////////////////////////////////////	        
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
 			while (mrec.next())////////////// Month File Loop Start//////////////////////
 			{	
 			    if (mrec.isFirst())	
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);

 			    if (mrec.isLast())
		 			   txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);

 			}
 			mrec.close();
 			ms1.close();

    		            
////////////////////////////////////////HQ Count Query////////////////////////////////////////            
 
            String terrec ="Select count(*)  from "+tblnm2+" as h,user_ter as u " +
            " where h.depo_code=u.depo_code and h.ter_code=u.ter_code and user_id="+uid+" and ter_pt=? " +
            " and h.mkt_year=? order by h.ter_code ";

 			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
				t = trec.getInt(1);
		    gval = new double[t];
		    salval=new double [t];
		    pmr = new int[t];
		    grval = new double[t];
		    gqty = new int[t];
		    grqty = new int[t];
		    gtsal = new double[t];
		    gttar = new double[t];

		    trec.close();
			    ts1.close();
			    ts1=null;
////////////////////////////////////////HQ NO OF REP Query////////////////////////////////////////   			    
			    String pmrQuery ="select p.depo_code,p.ter_code,sum(p.rep) from "+tblnm3+" p,(SELECT DEPO_CODE," +
			    " TER_CODE FROM USER_TER WHERE USER_ID = ? ) H "+ 
			    " where p.mkt_year=? AND p.DEPO_CODE = H.DEPO_CODE AND p.Ter_Code = H.TER_CODE " +
			    " and mnth_code between ? and ? group by p.depo_code,p.ter_code";
			    
				ts1 = con.prepareStatement(pmrQuery);
				ts1.setInt(1,uid);
				ts1.setInt(2,eyear);
				ts1.setInt(3,smon);
				ts1.setInt(4,emon);
				trec = ts1.executeQuery();
				while (trec.next())
				{
					System.out.println("trec "+trec.getInt(3));
					pmr[k] = trec.getInt(3);
					trep+= trec.getInt(3);
					k++;
				}
			    
				    trec.close();
				    ts1.close();

			    
	        
////////////////////////////////////////Detail Query////////////////////////////////////////	
			    
			    if(pg==1)
			    {
			    	query1 = " SELECT pr_code,pname,pack,tr_cd,ter_name,sum(salval),sum(tarval)" +
			    	",sum(lysval),ifnull(round((sum(salval)/sum(tarval)*100),2),0.00) ach "+
			    	",ifnull(round((sum(salval)/sum(lysval)*100)-100,2),0.00) gwth" +
			    	",round((sum(salval)-sum(tarval)),2) surplus,mgrp,mgrp_name," +
			    	" sum(salqty),sum(tarqty),sum(lysqty) "+
			    	" FROM "+tblnm+"  R,(SELECT DEPO_CODE,TER_CODE FROM USER_TER WHERE USER_ID = ? ) H "+
			    	" WHERE R.MKT_YEAR = ? "+
			    	" AND R.DEPO_CODE = H.DEPO_CODE AND R.TR_CD = H.TER_CODE and mnth_code between ? and ? " +
			    	" group by r.pr_code,r.depo_code,r.tr_cd order by r.mgrp,r.pr_code,r.depo_code,r.tr_cd";
			    }
			    else if(pg==2)
			    {
			    	query1 = " SELECT mgrp,mgrp_name,'' pack,tr_cd,ter_name,sum(salval),sum(tarval)" +
			    	",sum(lysval),ifnull(round((sum(salval)/sum(tarval)*100),2),0.00) ach "+
			    	",ifnull(round((sum(salval)/sum(lysval)*100)-100,2),0.00) gwth" +
			    	",round((sum(salval)-sum(tarval)),2) surplus,mkt_seq,mkt_group, "+
			    	" sum(salqty),sum(tarqty),sum(lysqty) "+
			    	" FROM "+tblnm+"  R,(SELECT DEPO_CODE,TER_CODE FROM USER_TER WHERE USER_ID = ? ) H "+
			    	" WHERE R.MKT_YEAR = ? "+
			    	" AND R.DEPO_CODE = H.DEPO_CODE AND R.TR_CD = H.TER_CODE and mnth_code between ? and ? " +
			    	" group by r.mgrp,r.depo_code,r.tr_cd order by r.mkt_seq,r.mgrp,r.depo_code,r.tr_cd";
			    }
            
            ps1 = con.prepareStatement(query1);
            ps1.setInt(1, uid);
            ps1.setInt(2, eyear);
            ps1.setInt(3, smon);
            ps1.setInt(4, emon);
			rst1 = ps1.executeQuery();

            boolean first=true;
            int pcode=0;
            int gcode=0;
            String gname="";
            k=0; 
			 while (rst1.next())   ////////////////////////Product Master Loop Start here///////////////////
			 {
				 if(first)
				 {
					 pcode=rst1.getInt(1);
					 gcode=rst1.getInt(12);
					 gname=rst1.getString(13);
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(1));
					 rfb.setMname(rst1.getString(2));
					 rfb.setPack(rst1.getString(3));
					 rfb.setQty2(t);
		      	     rfb.setNm3(txt1+txt5);
			    	 rfb.setLupdate(txt6);
			    	 rfb.setColor(0);
			    	 rfb.setUv(pg);  // product group

					 first=false;
				 }
				 if(pcode!=rst1.getInt(1))
				 {
					 if(t!=0 ) 
					 {
						 
						 if(code==4 && ttar!=0 )
						 {
							 tval=(tsal/ttar)*100;
						 }
						 else if(code==5 && ttar!=0 )
						 {
							 tval=((tsal/ttar)*100)-100;
						 }
						 else if(code==6)
						 {
							 tval=tsal/ttar;
						 }
						 rfb.setQty3((int) tval);  // total val
						 rfb.setNo_of_mr(trep);
						 rfb.setLupdate(txt6);
						 rfb.setColor(0);
						 data.add(rfb);
						 tsal=0.00;
						 ttar=0.00;

					 }
					 
					 if(gcode!=rst1.getInt(12))
					 {
						 rfb = new MktFormBean();
						 rfb.setMcode(gcode);
						 rfb.setMname(gname);
						 rfb.setColor(3);
						 int z=0;
						 gtval=0;
						 for (z=0; z<t;z++)
						 {
							 if(code==4 )
							 {
								 grval[z] = (gtsal[z]/gttar[z])*100;
								 tsal=tsal+gtsal[z];
								 ttar=ttar+gttar[z];
								 rfb.setDval0(z, (grval[z]));
							 }
							 else if(code==5 )
							 {
								 grval[z] = ((gtsal[z]/gttar[z])*100)-100;
								 tsal=tsal+gtsal[z];
								 ttar=ttar+gttar[z];
								 rfb.setDval0(z, (grval[z]));
							 }
							 else if(code==6)
							 {
								 grval[z] = (gtsal[z]/gttar[z]);
								 tsal=tsal+gtsal[z];
								 ttar=ttar+gttar[z];
								 rfb.setDval0(z, (grval[z]));
							 }

							 else
							 {
								 rfb.setDval0(z, (grval[z]));
								 gtval+=grval[z];
							 }
							 //gtqty+=grqty[z];
						 }

						 
						 if(code==4 )
						 {
							 gtval = (tsal/ttar)*100;
						 }
						 else if(code==5 )
						 {
							 gtval = ((tsal/ttar)*100)-100;
						 }

						 else if(code==6)
						 {
							 gtval = tsal/ttar;
						 }

						 rfb.setQty3((int) gtval);
						 
						 data.add(rfb); 				
						 gcode=rst1.getInt(12);
						 gname=rst1.getString(13);
						 grval = new double[t];
						 grqty = new int[t];
						 gtsal = new double[t];
						 gttar = new double[t];

					 }
					 
					 
					 pcode=rst1.getInt(1);
					 rfb = new MktFormBean();
					 rfb.setMcode(rst1.getInt(1));
					 rfb.setMname(rst1.getString(2));
					 rfb.setPack(rst1.getString(3));
					 rfb.setQty2(t);
	      	         rfb.setNm3(txt1+txt5);
		    		 rfb.setLupdate(txt6);
			    	 rfb.setUv(pg);
					 rfb.setNo_of_mr(trep);
					 rfb.setColor(0);

					 k=0;
					 tval=0.00;	
					 tqty=0;
				 }
			
         
				rfb.setNm1(k,(rst1.getString(5)+" ["+pmr[k]+"]"));  // hq name
 
				if(code==1)  // sales
				{
					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(6)); 
						gval[k]=gval[k]+rst1.getDouble(6);
						tval=tval+rst1.getDouble(6);
						grval[k]=grval[k]+rst1.getDouble(6);
					}
					else
					{
						rfb.setDval0(k,rst1.getDouble(14)); 
						gval[k]=gval[k]+rst1.getDouble(14);
						tval=tval+rst1.getDouble(14);
						grval[k]=grval[k]+rst1.getDouble(6);
					}


				}
				else if(code==2)  // target
				{

					
					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(7)); 
						gval[k]=gval[k]+rst1.getDouble(7);
						tval=tval+rst1.getDouble(7);
						grval[k]=grval[k]+rst1.getDouble(7);
					}
					else
					{
						rfb.setDval0(k,rst1.getDouble(15)); 
						gval[k]=gval[k]+rst1.getDouble(15);
						tval=tval+rst1.getDouble(15);
						grval[k]=grval[k]+rst1.getDouble(7);
					}


				}
				else if(code==3)  // lastyear
				{

					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(8)); 
						gval[k]=gval[k]+rst1.getDouble(8);
						tval=tval+rst1.getDouble(8);
						grval[k]=grval[k]+rst1.getDouble(8);
					}
					else
					{
						rfb.setDval0(k,rst1.getDouble(16)); 
						gval[k]=gval[k]+rst1.getDouble(16);
						tval=tval+rst1.getDouble(16);
						grval[k]=grval[k]+rst1.getDouble(8);
					}


				}
				else if(code==4)  // achievement
				{
					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(9)); 
						gval[k]=gval[k]+rst1.getDouble(9);
						//tval=tval+rst1.getDouble(9);
						grval[k]=grval[k]+rst1.getDouble(9);

						
						gtsal[k]=gtsal[k]+rst1.getDouble(6);
						gttar[k]=gttar[k]+rst1.getDouble(7);

						tsal=tsal+rst1.getDouble(6);
						ttar=ttar+rst1.getDouble(7);

					}
					else
					{
						if(rst1.getDouble(15)!=0)
						{
							rfb.setDval0(k,(rst1.getDouble(14)/rst1.getDouble(15))*100); 
							gval[k]=gval[k]+(rst1.getDouble(14)/rst1.getDouble(15))*100;
							tsal=tsal+rst1.getInt(14);
							ttar=ttar+rst1.getInt(15);
							gtsal[k]=gtsal[k]+rst1.getDouble(6);
							gttar[k]=gttar[k]+rst1.getDouble(7);

							
					//		tval=tval+(rst1.getDouble(14)/rst1.getDouble(15))*100;
						}
						else
						{
							rfb.setDval0(k,0.00);
						}
						grval[k]=grval[k]+rst1.getDouble(9);
					}



					
				}
				else if(code==5)  // growth
				{
					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(10)); 
						gval[k]=gval[k]+rst1.getDouble(10);
						grval[k]=grval[k]+rst1.getDouble(10);

						gtsal[k]=gtsal[k]+rst1.getDouble(6);
						gttar[k]=gttar[k]+rst1.getDouble(8);  // last year

						tsal=tsal+rst1.getDouble(6);
						ttar=ttar+rst1.getDouble(8);  // last year

					}
					else
					{
						if(rst1.getDouble(16)!=0)
						{
							rfb.setDval0(k,((rst1.getDouble(14)/rst1.getDouble(16))*100)-100); 
							gval[k]=gval[k]+((rst1.getDouble(14)/rst1.getDouble(16))*100)-100;
							tsal=tsal+rst1.getInt(14);
							ttar=ttar+rst1.getInt(16);  // last year
							gtsal[k]=gtsal[k]+rst1.getDouble(6);
							gttar[k]=gttar[k]+rst1.getDouble(8);  // last year
						}
						else
						{
							rfb.setDval0(k,0.00);
						}
						grval[k]=grval[k]+rst1.getDouble(10);
					}


	
				}
				else if(code==6)  // pmr
				{
					if(pmr[k]>0)
					{
						if(pg==2)
						{
							rfb.setDval0(k,(rst1.getDouble(6)/pmr[k])); 
							gval[k]=gval[k]+(rst1.getDouble(6)/pmr[k]); 
							tsal=tsal+rst1.getDouble(6);
							ttar=ttar+pmr[k];   // no of fs
							//grval[k]=grval[k]+(rst1.getDouble(6)/pmr[k]); 
							gtsal[k]=gtsal[k]+rst1.getDouble(6);
							gttar[k]=gttar[k]+pmr[k];

						}
						else
						{
							rfb.setDval0(k,(rst1.getDouble(14)/pmr[k])); 
							gval[k]=gval[k]+(rst1.getDouble(14)/pmr[k]); 
							tsal=tsal+rst1.getDouble(14);
							ttar=ttar+pmr[k];   // no of fs
						//	grval[k]=grval[k]+(rst1.getDouble(6)/pmr[k]); 
							gtsal[k]=gtsal[k]+rst1.getDouble(6);
							gttar[k]=pmr[k];
							
						}
					}
					else
					{
						rfb.setDval0(k,0); 
					}
					
				}

				else if(code==7)  // surplus/deficit
				{
					if(pg==2)
					{
						rfb.setDval0(k,rst1.getDouble(11)); 
						gval[k]=gval[k]+rst1.getDouble(11);
						tval=tval+rst1.getDouble(11);
						grval[k]=grval[k]+rst1.getDouble(11);
					}
					else
					{
						rfb.setDval0(k,(rst1.getDouble(14)-rst1.getDouble(15))); 
						gval[k]=gval[k]+(rst1.getDouble(14)-rst1.getDouble(15));
						tval=tval+(rst1.getDouble(14)-rst1.getDouble(15));
						grval[k]=grval[k]+rst1.getDouble(11);
					}

				}

 	    		k++;

	         
 			}/////////////////Product Master Loop end///////////////// 
			
			 if(t!=0 ) 
			 {

				 
				 if(code==4 && ttar!=0 )
				 {
					 tval=(tsal/ttar)*100;
				 }
				 else if(code==5 && ttar!=0 )
				 {
					 tval=((tsal/ttar)*100)-100;
				 }
				 else if(code==6)
				 {
					 tval=tsal/ttar;
				 }

				 rfb.setQty3((int) tval);

				 rfb.setQty4(trep);
				 rfb.setLupdate(txt6);
				 rfb.setColor(0);
				 data.add(rfb); 
				 
				 rfb = new MktFormBean();
				 rfb.setMcode(gcode);
				 rfb.setMname(gname);
				 rfb.setColor(3);
				 int z=0;
				 gtval=0;
				 for (z=0; z<t;z++)
				 {
					 if((code==4 || code==5) && pg==1)
					 {
						 grval[z] = (gtsal[z]/gttar[z])*100;
						 tsal=tsal+gtsal[z];
						 ttar=ttar+gttar[z];
						 rfb.setDval0(z, (grval[z]));
					 }
					 else if(code==6)
					 {
						 grval[z] = (gtsal[z]/gttar[z]);
						 tsal=tsal+gtsal[z];
						 ttar=ttar+gttar[z];
						 rfb.setDval0(z, (grval[z]));
					 }

					 else
					 {
						 rfb.setDval0(z, (grval[z]));
						 gtval+=grval[z];
					 }
					 //gtqty+=grqty[z];
				 }

				 
				 if(code==4 && pg==1)
				 {
					 gtval = (tsal/ttar)*100;
				 }
				 else if(code==5 && pg==1)
				 {
					 gtval = ((tsal/ttar)*100)-100;
				 }
				 else if(code==6)
				 {
					 gtval = tsal/ttar;
				 }

				 rfb.setQty3((int) gtval);
				 
				 data.add(rfb); 				

			 }


			 
			 	rfb = new MktFormBean();
 	   			rfb.setMname("TOTAL :");
				 rfb.setColor(4);
 	   			 int z=0;
	 			 gtval=0;
 	   				for (z=0; z<t;z++)
 	   				{
 			            	rfb.setDval0(z, (gval[z]));
 			            	gtval+=gval[z];
    	   		    }
				    rfb.setQty3((int) gtval);
                    
 	   				data.add(rfb); 				

//////////////////////////// All Statement Close here////////////////////////////
				rst1.close();
				ps1.close(); 
	     }
		
		catch (Exception e) 
		  {
			System.out.println("========Exception in SQLForm11DAO.getAllbrand " + e);
			e.printStackTrace();
		  }

		finally 
		{
		try {
            if(rst12 != null){ rst12.close();}
            if(ps12 != null){ ps12.close();}
            if(trec != null){ trec.close();}
            if(ts1 != null){ ts1.close();}
            if(rst1 != null){ rst1.close();}
            if(ps1 != null){ ps1.close();}
            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("--Exception in SQLForm7DAO.Connection.close "+e);
			  }
		}

		return data;
	}
////////////////////////////////////// HQ Coding End here ///////////////////////////////////////	

	
}