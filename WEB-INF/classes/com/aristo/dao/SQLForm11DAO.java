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

public class SQLForm11DAO {

///////////////////////////////Headquater Coding Start Here//////////////////////////////////
	 
	public List getAllHQ(Connection con, int smon,int emon,int eyear,int depo_code,String tp) { 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12 =null;
		PreparedStatement ms1 =null;
		ResultSet mrec =null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1 =null;
		int mon=0;
		int k=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int grep=0;
            if (smon>emon) 
            	emon=smon;
            
            int index=emon-smon+1;
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double glyr=0.00;
            
            double []gtarget;
            double []gsale;
            double []gach;
            double []ggth;
            double []gpmr;
            double []gfs;
            double []glst;   
            double []glsale;
            int [] ggrep;
            String mnth[]=new String[12];
            
        	tblnm=(tp+"_report").toLowerCase();
            tblnm2=(tp+"_hq_master08").toLowerCase();
           
            txt2="     H.Q. WISE RUPEES WISE SALES ANALYSIS FROM ";
			
            
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
    		
        	String q1="";
        	String q2="";
        	String q3="";
        	String q4="";
        	String q5="";
        	String q6="";
        	String q7="";
        	String q8="";
        	String tq1="";
        	String tq2="";
        	String tq3="";
        	String tq4="";
        	String tq5="";

////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
        	mrec.beforeFirst();
 			while (mrec.next())///////////// Month Loop Start
 			{	
			        mon=mrec.getInt(4);
 			        wname=mrec.getString(3);
 			        if (wname.equals("DEC"))
 			        	wname="DECM";
 			     if (mrec.isFirst())
 			     {
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			     	tq1=tq1+"tt"+mon;
 			     	tq2=tq2+"ra"+mon;
 			     	tq3=tq3+"rl"+mon;
 			     	tq4=tq4+"t."+wname;
 			     	tq5=tq5+wname;
 			     }
 			     else
 			     {
				     tq1=tq1+"+tt"+mon;
 			     	 tq2=tq2+"+ra"+mon;
 			     	 tq3=tq3+"+rl"+mon;
  			     	 tq4=tq4+"+t."+wname;
  			     	 tq5=tq5+"+"+wname;
 			     }

 			    
				     if (mrec.isLast())
				    	 txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);		 				       
			      
	 			   //     rfb.setNm9(k, mrec.getString(3));
				     mnth[k]=mrec.getString(3);
				     k++;
// 			        mon=mrec.getInt(4);
// 			        wname=mrec.getString(3);
				     q1=q1+",round(sum(tt"+mon+"),2) "+wname;
				     q2=q2+",round(sum(ra"+mon+"),2) "+wname;
				     q3=q3+",round(sum(rl"+mon+"),2) "+wname;
				     q4=q4+",round((sum(ra"+mon+")/sum(tt"+mon+"))*100,2) " +wname;
				     q5=q5+",round((sum(ra"+mon+")/sum(rl"+mon+"))*100,2)-100 "+wname;
				     q6=q6+",round((sum(a.ra"+mon+")/t."+wname+"),2) "+wname;
				     q7=q7+","+wname;
				     q8=q8+",a."+wname;
				     

 			}//End of Month loop///////////////////////     

 					q1=q1+",round(sum("+tq1+"),2) total";
 					q2=q2+",round(sum("+tq2+"),2) total";
 					q3=q3+",round(sum("+tq3+"),2) total";
 					q4=q4+",round((sum("+tq2+")/sum("+tq1+"))*100,2) total";
 					q5=q5+",round((sum("+tq2+")/sum("+tq3+"))*100,2)-100  total";
				    q6=q6+",round((sum("+tq2+")/"+tq4+"),2) total";
				    q7=q7+","+tq5+" total";
				    q8=q8+",a.total";
				    mrec.close();
				    ms1.close();

				    
				    System.out.println("**** "+q1);
				    
////////////////////////HQ  Master Query/////////////////////////////////            
//            String query1 = "Select ter_code,ter_name,no_of_rep from "+tblnm2+" where depo_code=? and ter_pt=? and mkt_year=? order by ter_code";
            String query1 = " select a.tr_cd,a.ter_name,a.txt,a.seq"+q8+" from "+ 
            "(select tr_cd,ter_name,1 seq,'Target' txt"+q1+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by tr_cd "+
            "union all  "+
            "select tr_cd,ter_name,2 seq,'Sale' txt"+q2+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by tr_cd "+
            "union all  "+
            "select tr_cd,ter_name,3 seq, 'Lys' txt"+q3+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by tr_cd "+
            "union all  "+
            "select tr_cd,ter_name,4 seq,'Ach' txt"+q4+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by tr_cd "+
            "union all "+
            "select ter_code,ter_name,7 seq,'F.S.' txt"+q7+" from "+tblnm2+" "+ 
            "where mkt_year=? and depo_code=? "+
            "union all  "+
            "select tr_cd,ter_name,5 seq,'Gth' txt"+q5+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by tr_cd "+
            "union all "+ 
            "select a.tr_cd,a.ter_name,6 seq,'PMR' txt"+q6+" from "+tblnm+" "+ 
            "a,"+tblnm2+" t "+
            "where a.mkt_year=? and a.depo_code=? "+ 
            "and t.mkt_year=? and t.depo_code=? "+
            "and a.tr_cd=t.ter_code "+
            "group by a.tr_cd) a order by tr_cd,seq "; 

			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,eyear);
			ps1.setInt(2,depo_code);
			ps1.setInt(3,eyear);
			ps1.setInt(4,depo_code);
			ps1.setInt(5,eyear);
			ps1.setInt(6,depo_code);
			ps1.setInt(7,eyear);
			ps1.setInt(8,depo_code);
			ps1.setInt(9,eyear);
			ps1.setInt(10,depo_code);
			ps1.setInt(11,eyear);
			ps1.setInt(12,depo_code);
			ps1.setInt(13,eyear);
			ps1.setInt(14,depo_code);
			ps1.setInt(15,eyear);
			ps1.setInt(16,depo_code);

			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
                rfb.setMcode(index);
//////////////////////////////////// Target Master Ki Query/////////////////
		 			rfb.setNm3(txt2+txt5);
		 			rfb.setLupdate(txt6);

		 		  hval=0.00;
		 		  int y=0;
                  for(y=0;y<index;y++)
                  {
                	if(rst1.getString(3).equals("Target"))  
                	  rfb.setColor(1);
  		 			  rfb.setNm1(y, rst1.getString(2)+"  "+rst1.getString(3));
                	  rfb.setDval0(y, rst1.getDouble(y+5));
   	 			      rfb.setNm9(y, mnth[y]);
   	 			      
	   	 			      switch(rst1.getInt(4))
	   	 			      {
	   	 			      case 1 :
	   	 			      		gtarget[y] = gtarget[y]+(rst1.getDouble(y+5));
	   	 			      		break;
	   	 			      case 2 :
	 	 			      		gsale[y] = gsale[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 3 :
	 	 			      		glsale[y] = glsale[y]+(rst1.getDouble(y+5));
	 				   		    glst[y] = glst[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 7 :
	                            gfs[y]=gfs[y]+(rst1.getInt(y+5));
	                            ggrep[y]=ggrep[y]+(rst1.getInt(y+5));
	                            break;
	   	 			      } 

                  } // end of for loop//////////////////////
                  
	                  rfb.setDval1(rst1.getDouble(y+5));  
	                  data.add(rfb);
	                  
				} // end of main query loop 
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
			  System.out.println("===Exception in SQLForm11DAO.getAllHQ " + e); 
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
		             if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in SQLForm11DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////// Headquater Coding End Here//////////////////////////////////	
	
	
///////////////////////////////Region Coding Start Here//////////////////////////////////
	
	public List getAllRegion(Connection con, int smon,int emon,int eyear,int depo_code,String tp) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12 =null;
		PreparedStatement ms1 =null;
		ResultSet mrec =null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1 =null;
		
       	int mon=0;
		int k=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
//            String tblnm3=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int grep=0;

            
            if (smon>emon) 
            	emon=smon;
            int index=emon-smon+1;
            
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double glyr=0.00;
            
            
            double []gtarget;
            double []gsale;
            double []gach;
            double []ggth;
            double []gpmr;
            double []gfs;
            double []glst;   
            double []glsale;
            int [] ggrep;
            String mnth[]=new String[12];
            
        	tblnm=(tp+"_report").toLowerCase();
            tblnm2=(tp+"_hq_master08").toLowerCase();
  //          tblnm3=tp+"_region_master08";
            
            txt2=" REGION WISE RUPEES WISE SALES ANALYSIS FROM "; 

            
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
        	String q1="";
        	String q2="";
        	String q3="";
        	String q4="";
        	String q5="";
        	String q6="";
        	String q7="";
        	String q8="";
        	String tq1="";
        	String tq2="";
        	String tq3="";
        	String tq4="";
        	String tq5="";

////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
        	mrec.beforeFirst();
 			while (mrec.next())///////////// Month Loop Start
 			{	
			        mon=mrec.getInt(4);
 			        wname=mrec.getString(3);
 			        if (wname.equals("DEC"))
 			        	wname="DECM";
 			     if (mrec.isFirst())
 			     {
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			     	tq1=tq1+"tt"+mon;
 			     	tq2=tq2+"ra"+mon;
 			     	tq3=tq3+"rl"+mon;
 			     	tq4=tq4+"sum(t."+wname+")";
 			     	tq5=tq5+"sum("+wname+")";
 			     }
 			     else
 			     {
				     tq1=tq1+"+tt"+mon;
 			     	 tq2=tq2+"+ra"+mon;
 			     	 tq3=tq3+"+rl"+mon;
  			     	 tq4=tq4+"+sum(t."+wname+")";
 			     	 tq5=tq5+"+sum("+wname+")";
 			     }

 			    
				     if (mrec.isLast())
				    	 txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);		 				       
			      
	 			   //     rfb.setNm9(k, mrec.getString(3));
				     mnth[k]=mrec.getString(3);
				     k++;
// 			        mon=mrec.getInt(4);
// 			        wname=mrec.getString(3);
				     q1=q1+",round(sum(tt"+mon+"),2) "+wname;
				     q2=q2+",round(sum(ra"+mon+"),2) "+wname;
				     q3=q3+",round(sum(rl"+mon+"),2) "+wname;
				     q4=q4+",round((sum(ra"+mon+")/sum(tt"+mon+"))*100,2) " +wname;
				     q5=q5+",round((sum(ra"+mon+")/sum(rl"+mon+"))*100,2)-100 "+wname;
				     q6=q6+",round((sum(a.ra"+mon+")/sum(t."+wname+")),2) "+wname;
				     q7=q7+",sum("+wname+")";
				     q8=q8+",a."+wname;
				     

 			}//End of Month loop///////////////////////     

 					q1=q1+",round(sum("+tq1+"),2) total";
 					q2=q2+",round(sum("+tq2+"),2) total";
 					q3=q3+",round(sum("+tq3+"),2) total";
 					q4=q4+",round((sum("+tq2+")/sum("+tq1+"))*100,2) total";
 					q5=q5+",round((sum("+tq2+")/sum("+tq3+"))*100,2)-100  total";
				    q6=q6+",round((sum("+tq2+")/"+tq4+"),2) total";
				    q7=q7+","+tq5+" total";
				    q8=q8+",a.total";
				    mrec.close();
				    ms1.close();

    		
////////////////////////Region  Master Query/////////////////////////////////            
//            String query1 = "Select reg_code,name from "+tblnm3+" where depo_code=? and typ=? and mkt_year=? order by reg_code";
            String query1 = " select a.rg_cd,a.reg_name,a.txt,a.seq"+q8+" from "+ 
            "(select rg_cd,reg_name,1 seq,'Target' txt"+q1+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by rg_cd "+
            "union all  "+
            "select rg_cd,reg_name,2 seq,'Sale' txt"+q2+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by rg_cd "+
            "union all  "+
            "select rg_cd,reg_name,3 seq, 'Lys' txt"+q3+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by rg_cd "+
            "union all  "+
            "select rg_cd,reg_name,4 seq,'Ach' txt"+q4+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by rg_cd "+
            "union all "+
            "select regn_code,ter_name,7 seq,'F.S.' txt"+q7+" from "+tblnm2+" "+ 
            "where mkt_year=? and depo_code=? group by regn_code "+
            "union all  "+
            "select rg_cd,reg_name,5 seq,'Gth' txt"+q5+" from "+tblnm+" "+
            "where mkt_year=? and depo_code=? group by rg_cd "+
            "union all "+ 
            "select a.rg_cd,a.reg_name,6 seq,'PMR' txt"+q6+" from "+tblnm+" "+ 
            "a,"+tblnm2+" t "+
            "where a.mkt_year=? and a.depo_code=? "+ 
            "and t.mkt_year=? and t.depo_code=? "+
            "and a.rg_cd=t.regn_code "+
            "group by a.rg_cd) a order by rg_cd,seq "; 

			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,eyear);
			ps1.setInt(2,depo_code);
			ps1.setInt(3,eyear);
			ps1.setInt(4,depo_code);
			ps1.setInt(5,eyear);
			ps1.setInt(6,depo_code);
			ps1.setInt(7,eyear);
			ps1.setInt(8,depo_code);
			ps1.setInt(9,eyear);
			ps1.setInt(10,depo_code);
			ps1.setInt(11,eyear);
			ps1.setInt(12,depo_code);
			ps1.setInt(13,eyear);
			ps1.setInt(14,depo_code);
			ps1.setInt(15,eyear);
			ps1.setInt(16,depo_code);

			rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
                rfb.setMcode(index);
                

	 			rfb.setNm3(txt2+txt5);
	 			rfb.setLupdate(txt6);
	 			
             /////////////////Target///////////////////

		 		  hval=0.00;
		 		  int y=0;
                for(y=0;y<index;y++)
                {
	              	if(rst1.getString(3).equals("Target"))  
	              	  rfb.setColor(1);
	              	
		 			  rfb.setNm1(y, rst1.getString(2)+"  "+rst1.getString(3));
		 			  rfb.setDval0(y, rst1.getDouble(y+5));
 	 			      rfb.setNm9(y, mnth[y]);
 	 			      
	   	 			      switch(rst1.getInt(4))
	   	 			      {
	   	 			      case 1 :
	   	 			      		gtarget[y] = gtarget[y]+(rst1.getDouble(y+5));
	   	 			      		break;
	   	 			      case 2 :
	 	 			      		gsale[y] = gsale[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 3 :
	 	 			      		glsale[y] = glsale[y]+(rst1.getDouble(y+5));
	 				   		    glst[y] = glst[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 7 :
	                            gfs[y]=gfs[y]+(rst1.getInt(y+5));
	                            ggrep[y]=ggrep[y]+(rst1.getInt(y+5));
	                            break;
	   	 			      } 

                } // end of for loop//////////////////////
                
	                  rfb.setDval1(rst1.getDouble(y+5));  
	                  data.add(rfb);
                 
			}///  end of while loop  
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
			  System.out.println("===Exception in SQLForm11DAO.getAllRegion " + e); 
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
		             if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in SQLForm11DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////// Region Coding End Here//////////////////////////////////		
	
///////////////////////////////Region Coding Start Here//////////////////////////////////
	
	public List getAllArea(Connection con, int smon,int emon,int eyear,int depo_code,String tp) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12 =null;
		PreparedStatement ms1 =null;
		ResultSet mrec =null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1 =null;
        
       	int mon=0;
       	int k=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int grep=0;

            
            if (smon>emon) 
            	emon=smon;
            int index=emon-smon+1;
            
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double glyr=0.00;
            
            double []gtarget;
            double []gsale;
            double []gach;
            double []ggth;
            double []gpmr;
            double []gfs;
            double []glst;   
            double []glsale;
            int [] ggrep;
            
            String mnth[]=new String[12];
            
        	tblnm=(tp+"_report").toLowerCase();
            tblnm2=(tp+"_hq_master08").toLowerCase();
                        
//            tblnm3=tp+"_area_master08";

            txt2="     AREA WISE RUPEES WISE SALES ANALYSIS FROM ";
			
            
            gtarget=new double [index];
            gsale=new double [index];
            gach=new double [index];
            ggth=new double [index];
            gpmr=new double [index];
            gfs=new double [index];
            glst=new double [index];   
            glsale=new double [index];
            ggrep=new int [index];
			
/////////////////////////////Date & time Updation ke liye////////////////////////////////			 
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
        	String q1="";
        	String q2="";
        	String q3="";
        	String q4="";
        	String q5="";
        	String q6="";
        	String q7="";
        	String q8="";
        	String tq1="";
        	String tq2="";
        	String tq3="";
        	String tq4="";
        	String tq5="";

////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
        	mrec.beforeFirst();
 			while (mrec.next())///////////// Month Loop Start
 			{	
			        mon=mrec.getInt(4);
 			        wname=mrec.getString(3);
 			        if (wname.equals("DEC"))
 			        	wname="DECM";
 			     if (mrec.isFirst())
 			     {
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			     	tq1=tq1+"tt"+mon;
 			     	tq2=tq2+"ra"+mon;
 			     	tq3=tq3+"rl"+mon;
 			     	tq4=tq4+"sum(t."+wname+")";
 			     	tq5=tq5+"sum("+wname+")";
 			     }
 			     else
 			     {
				     tq1=tq1+"+tt"+mon;
 			     	 tq2=tq2+"+ra"+mon;
 			     	 tq3=tq3+"+rl"+mon;
  			     	 tq4=tq4+"+sum(t."+wname+")";
 			     	 tq5=tq5+"+sum("+wname+")";
 			     }

 			    
				     if (mrec.isLast())
				    	 txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);		 				       
			      
	 			   //     rfb.setNm9(k, mrec.getString(3));
				     mnth[k]=mrec.getString(3);
				     k++;
// 			        mon=mrec.getInt(4);
// 			        wname=mrec.getString(3);
				     q1=q1+",round(sum(tt"+mon+"),2) "+wname;
				     q2=q2+",round(sum(ra"+mon+"),2) "+wname;
				     q3=q3+",round(sum(rl"+mon+"),2) "+wname;
				     q4=q4+",round((sum(ra"+mon+")/sum(tt"+mon+"))*100,2) " +wname;
				     q5=q5+",round((sum(ra"+mon+")/sum(rl"+mon+"))*100,2)-100 "+wname;
				     q6=q6+",round((sum(a.ra"+mon+")/sum(t."+wname+")),2) "+wname;
				     q7=q7+",sum("+wname+")";
				     q8=q8+",a."+wname;
				     

 			}//End of Month loop///////////////////////     

 					q1=q1+",round(sum("+tq1+"),2) total";
 					q2=q2+",round(sum("+tq2+"),2) total";
 					q3=q3+",round(sum("+tq3+"),2) total";
 					q4=q4+",round((sum("+tq2+")/sum("+tq1+"))*100,2) total";
 					q5=q5+",round((sum("+tq2+")/sum("+tq3+"))*100,2)-100  total";
				    q6=q6+",round((sum("+tq2+")/"+tq4+"),2) total";
				    q7=q7+","+tq5+" total";
				    q8=q8+",a.total";
				    mrec.close();
				    ms1.close();

    		
////////////////////////Area  Master Query/////////////////////////////////            
//            String query1 = "Select area_cd,area_name from "+tblnm3+" where depo_code=? and typ=? and mkt_year=? order by area_cd";
		            String query1 = " select a.ar_cd,a.area_name,a.txt,a.seq"+q8+" from "+ 
		            "(select ar_cd,area_name,1 seq,'Target' txt"+q1+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by ar_cd "+
		            "union all  "+
		            "select ar_cd,area_name,2 seq,'Sale' txt"+q2+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by ar_cd "+
		            "union all  "+
		            "select ar_cd,area_name,3 seq, 'Lys' txt"+q3+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by ar_cd "+
		            "union all  "+
		            "select ar_cd,area_name,4 seq,'Ach' txt"+q4+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by ar_cd "+
		            "union all "+
		            "select area_code,ter_name,7 seq,'F.S.' txt"+q7+" from "+tblnm2+" "+ 
		            "where mkt_year=? and depo_code=? group by area_code "+
		            "union all  "+
		            "select ar_cd,area_name,5 seq,'Gth' txt"+q5+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by ar_cd "+
		            "union all "+ 
		            "select a.ar_cd,a.area_name,6 seq,'PMR' txt"+q6+" from "+tblnm+" "+ 
		            "a,"+tblnm2+" t "+
		            "where a.mkt_year=? and a.depo_code=? "+ 
		            "and t.mkt_year=? and t.depo_code=? "+
		            "and a.ar_cd=t.area_code "+
		            "group by a.ar_cd) a order by ar_cd,seq "; 

					ps1 = con.prepareStatement(query1); 
					ps1.setInt(1,eyear);
					ps1.setInt(2,depo_code);
					ps1.setInt(3,eyear);
					ps1.setInt(4,depo_code);
					ps1.setInt(5,eyear);
					ps1.setInt(6,depo_code);
					ps1.setInt(7,eyear);
					ps1.setInt(8,depo_code);
					ps1.setInt(9,eyear);
					ps1.setInt(10,depo_code);
					ps1.setInt(11,eyear);
					ps1.setInt(12,depo_code);
					ps1.setInt(13,eyear);
					ps1.setInt(14,depo_code);
					ps1.setInt(15,eyear);
					ps1.setInt(16,depo_code);

					rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
                rfb.setMcode(index);
                


//////////////////////////////////// Target Master Ki Query/////////////////

		   
	 			rfb.setNm3(txt2+txt5);
	 			rfb.setLupdate(txt6);
	 			
             /////////////////Target///////////////////
		 		  hval=0.00;
		 		  int y=0;
                for(y=0;y<index;y++)
                {
	              	if(rst1.getString(3).equals("Target"))  
	              	  rfb.setColor(1);
	              	
		 			  rfb.setNm1(y, rst1.getString(2)+"  "+rst1.getString(3));
		 			  rfb.setDval0(y, rst1.getDouble(y+5));
 	 			      rfb.setNm9(y, mnth[y]);
 	 			      
	   	 			      switch(rst1.getInt(4))
	   	 			      {
	   	 			      case 1 :
	   	 			      		gtarget[y] = gtarget[y]+(rst1.getDouble(y+5));
	   	 			      		break;
	   	 			      case 2 :
	 	 			      		gsale[y] = gsale[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 3 :
	 	 			      		glsale[y] = glsale[y]+(rst1.getDouble(y+5));
	 				   		    glst[y] = glst[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 7 :
	                            gfs[y]=gfs[y]+(rst1.getInt(y+5));
	                            ggrep[y]=ggrep[y]+(rst1.getInt(y+5));
	                            break;
	   	 			      } 

                } // end of for loop//////////////////////
                
	                  rfb.setDval1(rst1.getDouble(y+5));  
	                  data.add(rfb);
				 
			} 
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
			  System.out.println("===Exception in SQLForm11DAO.getAllArea " + e); 
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
		             if(con != null){con.close();}

				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in SQLForm11DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////// Area Coding End Here//////////////////////////////////		
	
///////////////////////////////Branch Coding Start Here//////////////////////////////////
	
	public List getAllBranch(Connection con, int smon,int emon,int eyear,int depo_code,String tp) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12 =null;
		PreparedStatement ms1 =null;
		ResultSet mrec =null;     			
        PreparedStatement ps1=null; 
        ResultSet rst1 =null;
		
       	int mon=0;
       	int k=0;
      	String wname=null;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int grep=0;
            
            if (smon>emon) 
            	emon=smon;
            int index=emon-smon+1;
            
            double hval=0.00;
            double gval=0.00;
            double lyr=0.00;
            double glyr=0.00;
            
            double []gtarget;
            double []gsale;
            double []gach;
            double []ggth;
            double []gpmr;
            double []gfs;
            double []glst;   
            double []glsale;
            int [] ggrep;
            
            String mnth[]=new String[12];
            
        	tblnm=(tp+"_report").toLowerCase();
            tblnm2=(tp+"_hq_master08").toLowerCase();
            
            txt2="     BRANCH WISE RUPEES WISE SALES ANALYSIS FROM "; 
			
            
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
        	String q1="";
        	String q2="";
        	String q3="";
        	String q4="";
        	String q5="";
        	String q6="";
        	String q7="";
        	String q8="";
        	String tq1="";
        	String tq2="";
        	String tq3="";
        	String tq4="";
        	String tq5="";

////////////////////////Month File Query/////////////////////////////////    		
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	 		ms1 = con.prepareStatement(month);
	 		ms1.setInt(1,smon);
	 		ms1.setInt(2,emon);
	 		ms1.setInt(3,eyear);
	 		mrec = ms1.executeQuery();
        	mrec.beforeFirst();
 			while (mrec.next())///////////// Month Loop Start
 			{	
			        mon=mrec.getInt(4);
 			        wname=mrec.getString(3);
 			        if (wname.equals("DEC"))
 			        	wname="DECM";
 			     if (mrec.isFirst())
 			     {
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 			     	tq1=tq1+"tt"+mon;
 			     	tq2=tq2+"ra"+mon;
 			     	tq3=tq3+"rl"+mon;
 			     	tq4=tq4+"sum(t."+wname+")";
 			     	tq5=tq5+"sum("+wname+")";
 			     }
 			     else
 			     {
				     tq1=tq1+"+tt"+mon;
 			     	 tq2=tq2+"+ra"+mon;
 			     	 tq3=tq3+"+rl"+mon;
  			     	 tq4=tq4+"+sum(t."+wname+")";
 			     	 tq5=tq5+"+sum("+wname+")";
 			     }

 			    
				     if (mrec.isLast())
				    	 txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);		 				       
			      
	 			   //     rfb.setNm9(k, mrec.getString(3));
				     mnth[k]=mrec.getString(3);
				     k++;
// 			        mon=mrec.getInt(4);
// 			        wname=mrec.getString(3);
				     q1=q1+",round(sum(tt"+mon+"),2) "+wname;
				     q2=q2+",round(sum(ra"+mon+"),2) "+wname;
				     q3=q3+",round(sum(rl"+mon+"),2) "+wname;
				     q4=q4+",round((sum(ra"+mon+")/sum(tt"+mon+"))*100,2) " +wname;
				     q5=q5+",round((sum(ra"+mon+")/sum(rl"+mon+"))*100,2)-100 "+wname;
				     q6=q6+",round((sum(a.ra"+mon+")/sum(t."+wname+")),2) "+wname;
				     q7=q7+",sum("+wname+")";
				     q8=q8+",a."+wname;
				     

 			}//End of Month loop///////////////////////     

 					q1=q1+",round(sum("+tq1+"),2) total";
 					q2=q2+",round(sum("+tq2+"),2) total";
 					q3=q3+",round(sum("+tq3+"),2) total";
 					q4=q4+",round((sum("+tq2+")/sum("+tq1+"))*100,2) total";
 					q5=q5+",round((sum("+tq2+")/sum("+tq3+"))*100,2)-100  total";
				    q6=q6+",round((sum("+tq2+")/"+tq4+"),2) total";
				    q7=q7+","+tq5+" total";
				    q8=q8+",a.total";
				    mrec.close();
				    ms1.close();

    		
////////////////////////Branch Master Query/////////////////////////////////            
//            String query1 = "Select depo_code, ter_name from "+tblnm3+" where depo_code=? and typ=? order by depo_code";
		            String query1 = " select a.depo_code,a.br_name,a.txt,a.seq"+q8+" from "+ 
		            "(select depo_code,br_name,1 seq,'Target' txt"+q1+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all  "+
		            "select depo_code,br_name,2 seq,'Sale' txt"+q2+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all  "+
		            "select depo_code,br_name,3 seq, 'Lys' txt"+q3+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all  "+
		            "select depo_code,br_name,4 seq,'Ach' txt"+q4+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all "+
		            "select depo_code,ter_name,7 seq,'F.S.' txt"+q7+" from "+tblnm2+" "+ 
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all  "+
		            "select depo_code,br_name,5 seq,'Gth' txt"+q5+" from "+tblnm+" "+
		            "where mkt_year=? and depo_code=? group by depo_code "+
		            "union all "+ 
		            "select a.depo_code,a.br_name,6 seq,'PMR' txt"+q6+" from "+tblnm+" "+ 
		            "a,"+tblnm2+" t "+
		            "where a.mkt_year=? and a.depo_code=? "+ 
		            "and t.mkt_year=? and t.depo_code=? "+
		            "and a.depo_code=t.depo_code "+
		            "group by a.depo_code) a order by depo_code,seq "; 

					ps1 = con.prepareStatement(query1); 
					ps1.setInt(1,eyear);
					ps1.setInt(2,depo_code);
					ps1.setInt(3,eyear);
					ps1.setInt(4,depo_code);
					ps1.setInt(5,eyear);
					ps1.setInt(6,depo_code);
					ps1.setInt(7,eyear);
					ps1.setInt(8,depo_code);
					ps1.setInt(9,eyear);
					ps1.setInt(10,depo_code);
					ps1.setInt(11,eyear);
					ps1.setInt(12,depo_code);
					ps1.setInt(13,eyear);
					ps1.setInt(14,depo_code);
					ps1.setInt(15,eyear);
					ps1.setInt(16,depo_code);

					rst1 = ps1.executeQuery();
			  
			while (rst1.next())   ////////////////HQ Loop Start/////////////////
			{
				rfb = new MktFormBean();
                rfb.setMcode(index);
                


//////////////////////////////////// Target Master Ki Query/////////////////

	 			rfb.setNm3(txt2+txt5);
	 			rfb.setLupdate(txt6);
	 			
             /////////////////Target///////////////////
		 		  hval=0.00;
		 		  int y=0;
                for(y=0;y<index;y++)
                {
	              	if(rst1.getString(3).equals("Target"))  
	              	  rfb.setColor(1);
	              	
		 			  rfb.setNm1(y, rst1.getString(2)+"  "+rst1.getString(3));
		 			  rfb.setDval0(y, rst1.getDouble(y+5));
 	 			      rfb.setNm9(y, mnth[y]);
 	 			      
	   	 			      switch(rst1.getInt(4))
	   	 			      {
	   	 			      case 1 :
	   	 			      		gtarget[y] = gtarget[y]+(rst1.getDouble(y+5));
	   	 			      		break;
	   	 			      case 2 :
	 	 			      		gsale[y] = gsale[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 3 :
	 	 			      		glsale[y] = glsale[y]+(rst1.getDouble(y+5));
	 				   		    glst[y] = glst[y]+(rst1.getDouble(y+5));
	 	 			      		break;
	   	 			      case 7 :
	                            gfs[y]=gfs[y]+(rst1.getInt(y+5));
	                            ggrep[y]=ggrep[y]+(rst1.getInt(y+5));
	                            break;
	   	 			      } 

                } // end of for loop//////////////////////
                
	                  rfb.setDval1(rst1.getDouble(y+5));  
	                  data.add(rfb);
			}
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
			  System.out.println("===Exception in SQLForm11DAO.getAllBranch " + e); 
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
		             if(con != null){con.close();}
				} 
				  catch (SQLException e)
				{
					System.out.println("-------------Exception in SQLForm11DAO.Connection.close "+e);
				}
			}
		return data;
	}
	
/////////////////////////////// Branch Coding End Here//////////////////////////////////		
	

	////////////////////////////////////////////Branch ////////////////////////////////////////////////////	
	public List getNewBranch(Connection con, int grp_code,int smon, int emon,int eyear,int depo_code,int div_code,int loginid,int utype) { 
		MktFormBean rfb;
		PreparedStatement ts1=null; 
		ResultSet trec=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;

		PreparedStatement ps12=null;
		ResultSet rst12=null;

		
		CallableStatement cs=null;
		ResultSet rst1=null;

		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
			String tblnm3=null;
			String txt1=null;
			String txt4=null;
			String txt5 =null;
			String txt6 =null;
			double tarval[];
			double lysval[];
			double saleval[];
			double incrval[];
			int nrep[];
			String depo_name[];
			String procedureWithParameters="{call web_report_allvalues(?,?,?,?,?,?,?,?)}";

			//utype=4;
			//loginid=360;

			tblnm3="user_branch08";
			if(utype==4)
				tblnm3="user_ter";

			if(depo_code>0 && (utype==2  || utype==5))
				utype=1;


			if(grp_code>0)
				procedureWithParameters="{call web_report_allgroupvalue(?,?,?,?,?,?,?,?)}";
 
			if (smon>emon)
				emon=smon;


			System.out.println("mktyear "+eyear+" div "+div_code+" depo "+depo_code+" utype "+utype+" login id  "+loginid);
			System.out.println(procedureWithParameters);
			
			txt4="Rupeewise Performance For the Month of ";
			int t=0;
			int w=0;

			
            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            ps1=con.prepareStatement(branchname);
            ps1.setInt(1, depo_code);
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
            	txt1=rs1.getString(1)+ " Branch:  ";
            }
            else
            	txt1="All India:  ";
           
            rs1.close();
            ps1.close();

            
			String tp[]={"","A","T","G","","","","","","","M","","","","","","","","","","B","","","","","","","","","","F"};
			
			//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
			String query12 = "Select u_date,u_time  from aristo.upload where depo_code=? and substr(typ,1,1)=? ";
			ps12 = con.prepareStatement(query12);
			ps12.setInt(1,depo_code);
			ps12.setString(2,tp[div_code]); 
			rst12 = ps12.executeQuery();
			
			
			if (rst12.next())
			{
				System.out.println("1 "+rst12.getString(1));
			txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);
			}
			
			
			rst12.close();
			ps12.close();    
			

            
            
			
			////////////////////////////////////////Branch Master Count/////////////////////////////////// 
			String terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=?";
            if(div_code>=12 && div_code<=16)
                terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=? and depo_code=32";
            else if(div_code!=3)
            	terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=? and depo_code<>32";

			if(utype==4 && depo_code>0) 
				terrec = "Select count(*) from "+tblnm3+" where user_id=? and depo_code=? and status=?";
			else if(utype==1)
				terrec = "Select count(*)  from hqmast  where mkt_year=? and div_code=? and depo_code=? and ifnull(del_tag,'N')<>'D'";
			ts1 = con.prepareStatement(terrec);
			if(utype==4 && depo_code>0)
			{
				ts1.setInt(1,loginid);
				ts1.setInt(2,depo_code);
				ts1.setString(3,"Y");

			}
			else if(utype==1)
			{
				ts1.setInt(1,eyear);
				ts1.setInt(2,div_code);
				ts1.setInt(3,depo_code);

			}
			else
			{
				ts1.setInt(1,loginid);
				ts1.setString(2,"Y");
			}
			trec = ts1.executeQuery();
			if (trec.next())
			{
				t = trec.getInt(1)+1;
				w= trec.getInt(1);
			}
			nrep = new int[w];
			saleval = new double[w]; 
			tarval = new double[w]; 
			lysval = new double[w]; 
			depo_name= new String[w];
			incrval = new double[w]; 

			trec.close();
			ts1.close();




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

			boolean hrprint = true;	
			String gpname=null;

			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
			{	
				if (hrprint)
				{
					hrprint=false;
					txt5= rst1.getString(10)+" TO "+rst1.getString(11);
					gpname=grp_code>0?"Group -> "+rst1.getString(12)+ " ":"ALl Group -> ";
				}


				saleval[k]=rst1.getDouble(6);
				tarval[k]=rst1.getDouble(7);
				lysval[k]=rst1.getDouble(8);
				nrep[k]=rst1.getInt(9);
				depo_name[k]=rst1.getString(5);
				incrval[k]=rst1.getDouble(6)-rst1.getDouble(8);
				k++;



			} //////////////////////// End of Query Loop///////////////////////


			
			double ggval=0.00;
			double sval=0.00;
			double tval=0.00;
			double lval=0.00;
			int fs=0;
			
			rfb = new MktFormBean();
			for( int y=0;y<w;y++)
			{
				rfb.setNm9(y,depo_name[y]);
				rfb.setDval0(y, nrep[y]);
				ggval=ggval+nrep[y];
			}
			rfb.setColor(0);
			rfb.setNm3(txt1+gpname+txt4+txt5);
			rfb.setMcode(w);
			rfb.setUv(grp_code);
			rfb.setNm1(0, "FS");
			rfb.setLupdate(txt6);
			System.out.println("txt6 inner "+txt6);

			rfb.setDval1(ggval); 
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
			rfb.setDval1(ggval); 
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
			rfb.setDval1(ggval); 
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
			rfb.setDval1(ggval); 
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
			rfb.setDval1(ggval); 
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
			rfb.setDval1((tval==0?0.00:(sval/tval)*100)); 
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
			rfb.setDval1((lval==0?0.00:(sval/lval)*100-100)); 
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
			rfb.setDval1(fs==0?0.00:(sval/fs)); 
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
			rfb.setDval1((sval-tval)); 
			data.add(rfb); 



		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("========Exception in SQLForm11DAO.getAllBranch " + e);
		}

		finally
		{
			try 
			{
				if(trec != null){ trec.close();}
				if(ts1 != null){ ts1.close();}
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
				System.out.println("-------Exception in SQLHORepo1DAO.Connection.close "+e);
			}
		}		
		return data; 
	}



}
