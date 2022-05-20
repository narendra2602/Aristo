package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm13DAO {

	public List getAllHQ(Connection con, int uv,int code, int smon,int emon,int eyear,int depo_code,String tp,int uid) { 
		 
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
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            String query3=null;
            int trep=0;
            int grep=0;
            
            if (smon>emon) 
            	emon=smon;
            
            int index =emon;
            
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double tlyr=0.00;
            double glyr=0.00;
            double tulyr=0.00;
            double tval=0.00;
            double thval=0.00;
            double atarget=0.00;
            double autarget=0.00;

            int []utarget;
            int []usale;
            int []uach;
            int []ugth;
            int []upmr;
            int []ulsale;

            double []tutarget;
            int []tusale;
            int []tuach;
            int []tugth;
            int []tupmr;
            int []tulsale;
            int []tulst;
            
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
            tblnm3=tp+"_product08";
        
			 if (uv==1)
	             txt2="     H.Q. WISE UNIT WISE SALES ANALYSIS FROM "; 
			 if (uv==2)
	             txt2="     H.Q. WISE VALUE WISE SALES ANALYSIS FROM "; 
			 if (uv==3)
	             txt2="     H.Q. WISE UNIT/VALUE WISE SALES ANALYSIS FROM "; 

     		utarget=new int [index];
            usale=new int [index];
            uach=new int [index];
            ugth=new int [index];
            upmr=new int [index];
            ulsale=new int [index];

            tutarget=new double [index];
            tusale=new int [index];
            tuach=new int [index];
            tugth=new int [index];
            tupmr=new int [index];
            tulsale=new int [index];
            tulst=new int [index];
            
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
			 
////////////////////////////////////////Product Master////////////////////////////
            String terrec = "Select mcode,mname,pack from "+tblnm3+" where pcode=? and mkt_year=? group by mcode order by mname";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		   
    		if (trec.next())
    		{
                  txt1="PRODUCT -> "+trec.getString(2)+","+trec.getString(3);
                  code=trec.getInt(1);
    		}
    			txt2=txt1+txt2;
               
             trec.close();
             ts1.close();
             
////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
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
                rfb.setQty3(uv);
                
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

		 	//query3 = "Select sum(((tt"+mon+"*tvalue)/100)), sum(ra"+mon+"),sum(rl"+mon+"),sum(((tt"+mon+"*ttarget)/100)), sum(ta"+mon+"),sum(la"+mon+"),max(budmnth),sum((budtar*budper)/100),sum((budval*budper)/100) from "+tblnm+" where tr_cd=? "+
            //" and depo_code=? and pr_code=?  and mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd,pr_code";
		 	
		 	 query3 = "Select sum(tt"+mon+"),sum(ra"+mon+"),sum(rl"+mon+"),sum(tq"+mon+"),sum(ta"+mon+"),sum(la"+mon+"),0 as budmnth,0 as budper,0 as budval from "+tblnm+" where tr_cd=? "+
             " and depo_code=? and mcode=? and mkt_year=? group by mcode order by ar_cd,rg_cd,tr_cd,mcode"; 

		 	
		 	ps3 = con.prepareStatement(query3); 
			ps3.setInt(1,rst1.getInt(1)); 
			ps3.setInt(2,rst1.getInt(4));
			ps3.setInt(3,code);
			ps3.setInt(4,mrec.getInt(5));
			rst3 = ps3.executeQuery(); 
			
			   if(rst3.next())/////////////// Target Master Start/////////////
		        {
				       ///////////////////////////Value ke liye////////////////////
			   		   target[k] = (rst3.getDouble(1));
			   		   lyr=lyr+(rst3.getDouble(3));
			   		   gtarget[k] = gtarget[k]+(rst3.getDouble(1));
			   		   glst[k] = glst[k]+(rst3.getDouble(3));
			   		   sale[k] = (rst3.getDouble(2));
			   		   lsale[k] = (rst3.getDouble(3));
			   		   gsale[k]=gsale[k]+sale[k];
                       glsale[k]=glsale[k]+lsale[k];
                       


				    	if (mrec.getInt(6)>=rst3.getInt(7))
				    	 {
				    		atarget = rst3.getDouble(1)+rst3.getDouble(9);
				    		gtarget[k] =gtarget[k]+rst3.getDouble(9);						    		
				    	 }
				    	else
				    	{
				    		atarget = (rst3.getDouble(1));
				    	}

				   	   target[k] = atarget;

                       
			   		   //////////////////////////Unit ke liye//////////////////////
			   		   tutarget[k] =tutarget[k]+(rst3.getDouble(4));
			   		   tulst[k]= tulst[k]+(rst3.getInt(6));
  			   		   usale[k] = (rst3.getInt(5));
			   		   ulsale[k] = (rst3.getInt(6));
			   		   tusale[k]=tusale[k]+usale[k];
                       tulsale[k]=tulsale[k]+ulsale[k];
                     
				    	if (mrec.getInt(6)>=rst3.getInt(7))
				    	 {
				    		autarget = rst3.getDouble(4)+rst3.getDouble(8);
				    		tutarget[k] =tutarget[k]+rst3.getDouble(8);						    		
				    	 }
				    	else
				    	{
				    		autarget = (rst3.getDouble(4));
				    	}

				   	   utarget[k] = (int) (autarget+.50);
            
	                   ///////////////Value ke liye//////////////////////////                       
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
                       ///////////////////Unit Wise Ke Liye/////////////////
                        if (utarget[k]!=0) 
	                        uach[k] = (int) ((usale[k]*100.00/utarget[k]));
	                       else
	                        uach[k] = 0;
	                       
	                       if (rst3.getInt(6)!=0)
	                       {
	                        ugth[k] = (int)((((usale[k]*100.00/rst3.getInt(6)))-100.00)+.50);
	                       
	                            if (ugth[k]<0)
		   		                   ugth[k]=ugth[k]-1;	   		            
	                       }
	                       else
	                     	  ugth[k]=0;
	                       
	                       if (trep!=0)
	                       {
	                    	   if (usale[k]>0)	
	                    	      upmr[k] = (int)((usale[k]*1.00/trep)+0.50);
	                    	   else 
	                    	      upmr[k] = (int)((((usale[k]*1.00/trep)*-1)+0.50)*-1);
	                       }
	                       else
	                     	upmr[k]=0;

                      ////////////Value le liye///////////
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
		 			  
		      if ((uv==1) || (uv==3))
		      {
			      rfb.setQty0(y, utarget[y]);
			      hval=hval+utarget[y];
              }
		 			  
              if ((uv==2) || (uv==3))
              {
	          	  rfb.setDval0(y, target[y]);
	          	  hval=hval+target[y];
              }
            }
                if ((uv==1) || (uv==3))
	                rfb.setQty2((int)hval);  
	            if ((uv==2) || (uv==3))
	                rfb.setDval1(hval);
            
                data.add(rfb);
                
            /////////////////Sales///////////////////    
            rfb = new MktFormBean();
            gval=0.00;
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" SALES");
		      if ((uv==1) || (uv==3))
		      {
			      rfb.setQty0(y, usale[y]);
			      gval=gval+usale[y];
	          }
				 			  
	          if ((uv==2) || (uv==3))
	          {
	          	  rfb.setDval0(y, sale[y]);
	          	  gval=gval+sale[y];
	          }
		 			 
            }
	            if ((uv==1) || (uv==3))
	                rfb.setQty2((int)gval);  
	            if ((uv==2) || (uv==3))
	                rfb.setDval1(gval);
                data.add(rfb);
                
	          /////////////////Last Year Sales///////////////////
                lyr=0.00;
	            rfb = new MktFormBean();
                for( int y=0;y<index;y++)
                {
		 			 rfb.setNm1(y, rst1.getString(2)+" LYR SALES");
		 			 if ((uv==1) || (uv==3))
				     {
					     rfb.setQty0(y, ulsale[y]);
					     lyr=lyr+ulsale[y];
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			         	  rfb.setDval0(y, lsale[y]);
			         	  lyr=lyr+lsale[y];
			         }
                 }
                
			            if ((uv==1) || (uv==3))
			                rfb.setQty2((int)lyr);  
			            if ((uv==2) || (uv==3))
			                rfb.setDval1(lyr);
	                   
			            data.add(rfb); 
                
            /////////////////Achievement///////////////////
			rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" ACH.");
		 			 if ((uv==1) || (uv==3))
				     {
					     rfb.setQty0(y, uach[y]);
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			         	  rfb.setDval0(y, ach[y]);
			         }
            }
            
		            if ((uv==1) || (uv==3))
		            {
		            	if (hval!=0) 
		                rfb.setQty2((int)((gval/hval)*100.00));
		                
		            }
		            if ((uv==2) || (uv==3))
		            {
		            	if (hval!=0)   
		            	rfb.setDval1((gval/hval)*100.00);
		            }
		            
		              data.add(rfb);
            	  
	        /////////////////Growth///////////////////                        
		  	rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" GROWTH");
		 			 
		 			 if ((uv==1) || (uv==3))
				     {
					     rfb.setQty0(y, ugth[y]);
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			         	  rfb.setDval0(y, gth[y]);
			         }
            }
		            if ((uv==1) || (uv==3))
		            {
		            	if (lyr!=0) 
		                rfb.setQty2((int)(((gval/lyr)*100.00)-100.00));
		            }
		            if ((uv==2) || (uv==3))
		            {
		            	if (lyr!=0)   
		            	rfb.setDval1(((gval/lyr)*100.00)-100.00);
		            }
		            
                    data.add(rfb);
				 
            /////////////////PMR///////////////////
				 
			  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 			 rfb.setNm1(y, rst1.getString(2)+" PMR");
		 			 if ((uv==1) || (uv==3))
				     {
					     rfb.setQty0(y, upmr[y]);
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			         	  rfb.setDval0(y, pmr[y]);
			         }
            }
		            if ((uv==1) || (uv==3))
		            {
		            	if (grep!=0) 
		                rfb.setQty2((int)(gval/grep));
		            }
		            if ((uv==2) || (uv==3))
		            {
		            	if (grep!=0)   
		            	rfb.setDval1(gval/grep);
		            }
		            	data.add(rfb);
				 
            /////////////////FS///////////////////
			  rfb = new MktFormBean();
            for( int y=0;y<index;y++)
            {
		 		 rfb.setNm1(y, rst1.getString(2)+" FS");
	 			 if ((uv==1) || (uv==3))
			     {
				     rfb.setQty0(y, (int)fs[y]);
		         }
					 			  
		         if ((uv==2) || (uv==3))
		         {
		         	  rfb.setDval0(y, fs[y]);
		         }
            }
				 if ((uv==1) || (uv==3))
			     {
					 rfb.setQty2((int)grep);
		         }
					 			  
		         if ((uv==2) || (uv==3))
		         {
		        	 rfb.setDval1(grep);
		         }
	          
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
		 		  thval=0.00;
                 for( int y=0;y<index;y++)
                 {
               	      rfb.setColor(3);
 		 			  rfb.setNm1(y, "TOTAL TARGET");
 				      if ((uv==1) || (uv==3))
 				      {
 					      rfb.setQty0(y, (int)tutarget[y]);
 					      thval=thval+(int)tutarget[y];
 		              }
 				 			  
 		              if ((uv==2) || (uv==3))
 		              {
 			          	  rfb.setDval0(y, gtarget[y]);
 			          	  hval=hval+gtarget[y];
 		              } 		 			  
                 }
						 if ((uv==1) || (uv==3))
					     {
							 rfb.setQty2((int)thval);
				         }
							 			  
				         if ((uv==2) || (uv==3))
				         {
				        	 rfb.setDval1(hval);
				         }
  
	                  data.add(rfb);
	                  
	              /////////////////Sales///////////////////    
	              rfb = new MktFormBean();
                  gval=0.00;
                  tval=0.00;
	              for( int y=0;y<index;y++)
                 {
	            	 rfb.setColor(3);
 		 			 rfb.setNm1(y, "TOTAL SALES");
 		 			 
				      if ((uv==1) || (uv==3))
 				      {
 					      rfb.setQty0(y, tusale[y]);
 					      tval=tval+tusale[y];
 		              }
 				 			  
 		              if ((uv==2) || (uv==3))
 		              {
 			          	  rfb.setDval0(y, gsale[y]);
 			          	  gval=gval+gsale[y];
 		              } 		 			  
                 }
					 if ((uv==1) || (uv==3))
				     {
						 rfb.setQty2((int)tval);
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			        	 rfb.setDval1(gval);
			         }
	                  data.add(rfb); 
	                  
               /////////////////Last Yr Sales///////////////////
	                  lyr=0.00;
	                  tlyr=0.00;
		              rfb = new MktFormBean();
		              for( int y=0;y<index;y++)
	                  {
		            	 rfb.setColor(3);
	  		 			 rfb.setNm1(y, "TOTAL LYR SALES");
	  		 			 
					      if ((uv==1) || (uv==3))
	 				      {
	 					      rfb.setQty0(y, tulsale[y]);
	 					      tlyr=tlyr+tulsale[y];
	 		              }
	 				 			  
	 		              if ((uv==2) || (uv==3))
	 		              {
	 			          	  rfb.setDval0(y, glsale[y]);
	 			          	  lyr=lyr+glsale[y];
	 		              } 		 			  
	                  }
						 if ((uv==1) || (uv==3))
					     {
							 rfb.setQty2((int)tlyr);
				         }
							 			  
				         if ((uv==2) || (uv==3))
				         {
				        	 rfb.setDval1(lyr);
				         }
		               
		                  data.add(rfb);  		                  
	             /////////////////Achievement///////////////////
	 			 rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL ACH.");
				      if ((uv==1) || (uv==3))
 				      {
	                      if (tutarget[y]!=0)
	                      {	  
	                    	 if (tusale[y]>0)
	                    		 
	 	                        tuach[y] = (int)((tusale[y]*100.00/tutarget[y])+0.50);
	                    	    
	                    	 else
	                    		tuach[y] = (int)(((((tusale[y]*100.00/tutarget[y])*-1)+0.50)*-1));
	                      }	 
	                         rfb.setQty0(y, tuach[y]);
 		              }
 				 			  
 		              if ((uv==2) || (uv==3))
 		              {
 		                   if (gtarget[y]!=0) 
 		                       gach[y] = (gsale[y]/gtarget[y])*100.00;
 		    		 		   rfb.setDval0(y, gach[y]);
 		              }
                 }

						 if ((uv==1) || (uv==3))
					     {
							 if (thval!=0) 
			                     rfb.setQty2((int)((tval/thval)*100.00));
				         }
							 			  
				         if ((uv==2) || (uv==3))
				         {
				        	 if (hval!=0) 
			                     rfb.setDval1((gval/hval)*100.00);
				         }
                 	  data.add(rfb);
                 	  
  	             /////////////////Growth///////////////////                        
	 		  	 rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	     rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL GROWTH");
				      if ((uv==1) || (uv==3))
 				      {
	 		            	 if (tulst[y]!=0)
	 		            		 if ((((tusale[y]*100.00/tulst[y]))-100.00)>0)
	 		                	   tugth[y] = (int)((((tusale[y]*100.00/tulst[y]))-100.00)+.50);
	 		            		 else
		 		                   tugth[y] = (int)((((((tusale[y]*100.00/tulst[y]))-100.00)*-1)+.50)*-1);
	 		                       tulyr=tulyr+tulst[y];
	 		                       rfb.setQty0(y, tugth[y]);
 		              }
 				 			  
 		              if ((uv==2) || (uv==3))
 		              {
 		            	 if (glst[y]!=0)
 		                	   ggth[y] = ((gsale[y]/glst[y])*100.00)-100.00;
 		                       glyr=glyr+glst[y];
 		                       rfb.setDval0(y, ggth[y]);
 		              }
                 }
                 
							 if ((uv==1) || (uv==3))
						     {
			                     if (tulyr!=0)
			                     {
			                    	 if ((((tval/tulyr)*100.00)-100.00)>0)
				                	    rfb.setQty2((int)((((tval/tulyr)*100.00)-100.00)+.50));
			                    	 else
			                    	    rfb.setQty2((int)((((((tval/tulyr)*100.00)-100.00)*-1)+.50)*-1));
			                     }
					         }
								 			  
					         if ((uv==2) || (uv==3))
					         {
			                     if (glyr!=0)
				                	  rfb.setDval1(((gval/glyr)*100.00)-100.00);
					         }
                 
	                      data.add(rfb);
	 				 
	              /////////////////PMR///////////////////
	 			  grep=0;	 
	 			  rfb = new MktFormBean();
	 			  for( int y=0;y<index;y++)
                 {
	 				 rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL PMR");
				      if ((uv==1) || (uv==3))
 				      {
				    	  if (ggrep[y]!=0)
				    	  {
				    		  if (tusale[y]>0)
				                  tupmr[y] = (int)((tusale[y]*1.00/ggrep[y])+0.50);
				    		  else 
				    		      tupmr[y] = (int)((((tusale[y]*1.00/ggrep[y])*-1)+0.50)*-1);  
				    	  }
			  		 		  grep=grep+ggrep[y];
			  		 		  rfb.setQty0(y, (int)tupmr[y]);
 		              }
 				 			  
 		              if ((uv==2) || (uv==3))
 		              {
 		            	 if (ggrep[y]!=0)
 			                 gpmr[y] = gsale[y]/ggrep[y];
 		  		 		     grep=grep+ggrep[y];
 		  		 			 rfb.setDval0(y, gpmr[y]);
 		              } 		 			  
                 }
                   
					 if ((uv==1) || (uv==3))
				     {
						 if (grep!=0)
							 if (tval>0)
							   	 rfb.setQty2((int)((tval*1.00/grep)+0.50));
							 else
								 rfb.setQty2((int)((((tval*1.00/grep)*-1)+0.50)*-1));
			         }
						 			  
			         if ((uv==2) || (uv==3))
			         {
			        	 if (grep!=0)
			                   	rfb.setDval1(gval/grep);
			         }
	 			   	data.add(rfb);
	 				 
	              /////////////////FS///////////////////
	 			  rfb = new MktFormBean();
                 for( int y=0;y<index;y++)
                 {
               	     rfb.setColor(3);
  		 			 rfb.setNm1(y, "TOTAL FS");
   		 		     rfb.setDval0(y, gfs[y]);
   		 		     rfb.setQty0(y, (int)gfs[y]);
                 }
                 	   rfb.setDval1(grep); 
                	   rfb.setQty2(grep);
	 				   data.add(rfb); 
                ///////////////////Fs Close////////////   
 					 
		} catch (Exception e) 
		      {
			  System.out.println("===Exception in HQ-SQLForm13DAO.getAllHQ " + e); 
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
					System.out.println("-------------Exception in HQ-SQLForm13DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
}
