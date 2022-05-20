package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo8FormBean;
import com.aristo.valueobject.Repo18FormBean;

public class SQLHORepo11DAO {

	public List getAllBranch(Connection con, int smon, int emon,String tp,int loginid,int eyear) { 
		 
		HORepo8FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        String query22=null;
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            
            if (smon>emon)
            	emon=smon;
            
            int index = emon-smon+2;
            int k=0;
            int z=0;            
            int mon=0;
            double hval=0.00;
            double[] vval = new double[index];
            double[] bvval = new double[index];
            String [] head = {"OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP"};
    		tblnm=(tp+"_hqsale").toLowerCase();
        	tblnm1=(tp+"_branch08").toLowerCase();
        	tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3="user_branch08";   	        
   	        
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            query22="select br.ter_name,hm.ter_name,hq.* from "+tblnm+" as hq,"+tblnm2+" as hm," +
            " "+tblnm1+" as br,"+tblnm3+" as ub where hq.depo_code=br.depo_code and " +
            " hm.depo_code=br.depo_code and hm.depo_code=hq.depo_code and hq.depo_code=ub.depo_code and " +
            " br.depo_code=ub.depo_code and hm.depo_code=ub.depo_code and hq.tr_cd=hm.ter_code and " +
            " hq.mkt_year=? and hm.mkt_year=? and ub.user_id=? and ub.status=? " +
            " group by hq.depo_code,hq.tr_cd,hq.mkt_year order by br.ter_name,hq.tr_cd ";
            
			ps1 = con.prepareStatement(query22);
			ps1.setInt(1, eyear);
			ps1.setInt(2, eyear);
			ps1.setInt(3,loginid); 
			ps1.setString(4, "Y");
			rst1 = ps1.executeQuery();            
			int dcode=0;
			String bname=null;
			boolean flag=true;
			while (rst1.next())   ////////////////USer Branch Master Loop Start////////////////////////
			{
				if (flag)
				{
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
					flag=false;
				}
				if (dcode!=rst1.getInt(3))
				{
					 rfb = new HORepo8FormBean();
 					 rfb.setName(bname);
 					 rfb.setColor(2);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, bvval[z]);
		   					hval=hval+bvval[z];
		   					bvval[z]=0.00;
	   				 }
                    ////////////////////Branch Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
	 			    	 
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
				}
				
				rfb = new HORepo8FormBean();
				rfb.setName(rst1.getString(2));
				rfb.setColor(1);				
				rfb.setMon(index);
                k=0;  
                hval=0.00;
                mon=5+(smon-1);      
                      while (k<index-1)
                      {
                          rfb.setVhead(k, head[smon-1+k]);
                          rfb.setVal1(k, (rst1.getDouble(mon+k)));
                          hval=hval+(rst1.getDouble(mon+k));
                          vval[k]=vval[k]+(rst1.getDouble(mon+k));
                          bvval[k]=bvval[k]+(rst1.getDouble(mon+k));
			              k++;
                      }
				     
			 			 rfb.setVhead(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			    	 data.add(rfb); 				
				}         ////////////////////////User Branch Loop End///////////////////////
			
			         rst1.close();
			         ps1.close();
			         
			         		 rfb = new HORepo8FormBean();
		 					 rfb.setName(bname);
		 					 rfb.setColor(2);
		 					 hval=0.00;
		 					 for (z=0; z<index-1;z++)
			   				 {
		 		   					rfb.setVal1(z, bvval[z]);
				   					hval=hval+bvval[z];
				   					bvval[z]=0.00;
			   				 }
		                    ////////////////////Branch Total Ke liye/////////////////
					 			 rfb.setVhead(z, "TOTAL VALUE");
			 			    	 rfb.setVal1(z, hval);
			 			    	 data.add(rfb); 				
 
			 /////////////////Branch Total Ke Liye//////////////////////////////
					 rfb = new HORepo8FormBean();
 					 rfb.setName("GRAND TOTAL :");
 					 rfb.setColor(3);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
	   				 }
                    ////////////////////Grand Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
					    	 
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo11DAO.getAllBranch " + e); 
		}
	
		finally {
			try 
			{
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo11DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
		
	public List getAllHQ(Connection con,int code, int smon, int emon,String tp,int loginid,int eyear,int uv) { 
		 
		HORepo8FormBean rfb;
		PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;            
            String txt1=null;
            String txt2=null;
            String txt5=null;
            
            if (smon>emon)
            	emon=smon;
            
            int index = emon-smon+2;
            int mon=0;
            int k=0;
            int z=0;
            double hval=0.00;
            double[] vval = new double[index];
            double[] bvval = new double[index];
            String query22=null;
            String [] head = {"OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP"};            
            tblnm=(tp+"_hqprsale").toLowerCase();
        	tblnm1=(tp+"_branch08").toLowerCase();
        	tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3="user_branch08"; 
   	        tblnm4=(tp+"_product08").toLowerCase();   	     
   	        
   	        if(uv==1)
   	        txt2=" HQ/BRANCH WISE UNIT WISE SALES TREND FROM - ";
   	        else
   	        txt2=" HQ/BRANCH WISE VALUE WISE SALES TREND FROM - ";
   	        
///////////////////////////////////////Product Master//////////////////////////////
            String terrec = "Select pname,pack from "+tblnm4+" where pcode=? and mkt_year=?";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1,code);
			ts1.setInt(2,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="PRODUCT-> "+trec.getString(1)+","+trec.getString(2);
			trec.close();
			ts1.close();
   	        
//////////////////////// Month File Loop Starts to accumulate data////////////////////////
   	        String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
   		 	ms1 = con.prepareStatement(month);
   		 	ms1.setInt(1,smon);
   		 	ms1.setInt(2,emon);
   		    ms1.setInt(3,eyear);
   		 	mrec = ms1.executeQuery();
		     if (mrec.first())	
				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
			    
		 	 if (mrec.last())
			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
    		mrec.close();
    		ms1.close();
    		
//////////////////////////////User Branch Master ki Query/////////////////////////////////
   		 	query22="select br.ter_name,hm.ter_name,hq.* from "+tblnm+" as hq,"+tblnm2+" as hm," +
   		 	" "+tblnm1+" as br,"+tblnm3+" as ub where hq.depo_code=br.depo_code and " +
   		 	" hm.depo_code=br.depo_code and hm.depo_code=hq.depo_code and hq.depo_code=ub.depo_code and " +
   		 	" br.depo_code=ub.depo_code and hm.depo_code=ub.depo_code and hq.tr_cd=hm.ter_code and " +
   		 	" hq.mkt_year=? and hm.mkt_year=? and ub.user_id=? and ub.status=? and hq.pr_code=? " +
   		 	" group by hq.depo_code,hq.tr_cd,hq.mkt_year order by br.ter_name,hq.tr_cd ";
         
			ps1 = con.prepareStatement(query22);
			ps1.setInt(1,eyear);
			ps1.setInt(2,eyear);
			ps1.setInt(3,loginid); 
			ps1.setString(4,"Y");
			ps1.setInt(5,code);
			rst1 = ps1.executeQuery();     		 	
   		 	
			int dcode=0;
			String bname=null;
			boolean flag=true;
			while (rst1.next())   ////////////////USer Branch Master Loop Start////////////////////////
			{
				if (flag)
				{
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
					flag=false;
				}
				if (dcode!=rst1.getInt(3))
				{
					 rfb = new HORepo8FormBean();
 					 rfb.setName(bname);
 					 rfb.setColor(2);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, bvval[z]);
		   					hval=hval+bvval[z];
		   					bvval[z]=0.00;
	   				 }
                    ////////////////////Branch Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
	 			    	 
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
				}
				
				rfb = new HORepo8FormBean();
				rfb.setName(rst1.getString(2));
				rfb.setColor(1);	
				rfb.setMon(index);
				rfb.setUv(uv);
                k=0;  
                hval=0.00;
                if (uv==1)
                   mon=7+(smon-1);
                else
                   mon=19+(smon-1);
                while (k<index-1)
                {
                    rfb.setVhead(k, head[smon-1+k]);
                    rfb.setVal1(k, (rst1.getDouble(mon+k)));
                    hval=hval+(rst1.getDouble(mon+k));
                    vval[k]=vval[k]+(rst1.getDouble(mon+k));
                    bvval[k]=bvval[k]+(rst1.getDouble(mon+k));
		            k++;
                }
                    rfb.setHead1(txt1+txt2+txt5);				     
		 			rfb.setVhead(k, "TOTAL VALUE");
 			    	rfb.setVal1(k, hval);
 			    	data.add(rfb); 				
				}         ////////////////////////Branch Loop End///////////////////////
			
			        rst1.close();
			        ps1.close();
			         
					rfb = new HORepo8FormBean();
 					rfb.setName(bname);
 					rfb.setColor(2);
 					hval=0.00;
 					for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, bvval[z]);
		   					hval=hval+bvval[z];
		   					bvval[z]=0.00;
	   				 }
                    ////////////////////Branch Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
			         
			         
			 /////////////////Branch Total Ke Liye//////////////////////////////
					 rfb = new HORepo8FormBean();
 					 rfb.setName("GRAND TOTAL :");
 					 rfb.setColor(3);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
	   				 }
                    ////////////////////Grand Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
					    	 
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo11DAO.getAllhq " + e); 
		}
	
		finally {
			try 
			{  
			   if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo11DAO.Connection.close "+e);
			  }
		}		
		return data;
	}

	public List getAllHQGroup(Connection con,int code, int smon, int emon,String tp,int loginid,int eyear) { 
		 
		HORepo8FormBean rfb;
		PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;            
            String txt1=null;
            String txt2=null;
            String txt5=null;
            
            if (smon>emon)
            	emon=smon;
            
            int index = emon-smon+2;
            int mon=0;
            int k=0;
            int z=0;
            double hval=0.00;
            double[] vval = new double[index];
            double[] bvval = new double[index];
            String query22=null;
            String [] head = {"OCT","NOV","DEC","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP"};            
            tblnm=(tp+"_hqprsale").toLowerCase();
        	tblnm1=(tp+"_branch08").toLowerCase();
        	tblnm2=(tp+"_hq_master08").toLowerCase();
   	        tblnm3="user_branch08";       
   	        tblnm4=(tp+"_groupsales08").toLowerCase();   	     
   	        
   	        txt2=" HQ/BRANCH WISE SALES TREND FROM - "; 
///////////////////////////////////////Product Master//////////////////////////////
            String terrec = "Select gp_name from "+tblnm4+" where gp_code=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="GROUP-> "+trec.getString(1);
			trec.close();
			ts1.close();
   	        
////////////////////////Month File Loop Starts to accumulate data////////////////////////
   	        String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
   		 	ms1 = con.prepareStatement(month);
   		 	ms1.setInt(1,smon);
   		 	ms1.setInt(2,emon);
   		    ms1.setInt(3,eyear);
   		 	mrec = ms1.executeQuery();
		     if (mrec.first())	
				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
			    
		 	 if (mrec.last())
			       txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
    		mrec.close();
    		ms1.close();
    		
//////////////////////////////User Branch Master ki Query/////////////////////////////////
   		 	query22="select br.ter_name,hm.ter_name,hq.depo_code,sum(hq.octval),sum(hq.novval),sum(hq.decval)," +
   		 	" sum(hq.janval),sum(hq.febval),sum(hq.marval),sum(hq.aprval),sum(hq.mayval),sum(hq.junval)," +
   		 	" sum(hq.julval),sum(hq.augval),sum(hq.sepval)  from "+tblnm+" as hq,"+tblnm2+" as hm," +
   		 	" "+tblnm1+" as br,"+tblnm3+" as ub where hq.depo_code=br.depo_code and " +
   		 	" hm.depo_code=br.depo_code and hm.depo_code=hq.depo_code and hq.depo_code=ub.depo_code and " +
   		 	" br.depo_code=ub.depo_code and hm.depo_code=ub.depo_code and hq.tr_cd=hm.ter_code and " +
   		 	" hq.mkt_year=? and hm.mkt_year=? and ub.user_id=? and ub.status=? and hq.grp_cd=? " +
   		 	" group by hq.depo_code,hq.tr_cd,hq.grp_cd,hq.mkt_year order by br.ter_name,hq.tr_cd ";
         
			ps1 = con.prepareStatement(query22);
			ps1.setInt(1,eyear);
			ps1.setInt(2,eyear);
			ps1.setInt(3,loginid); 
			ps1.setString(4,"Y");
			ps1.setInt(5,code);
			rst1 = ps1.executeQuery();     		 	
   		 	
			int dcode=0;
			String bname=null;
			boolean flag=true;
			while (rst1.next())   ////////////////USer Branch Master Loop Start////////////////////////
			{
				if (flag)
				{
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
					flag=false;
				}
				if (dcode!=rst1.getInt(3))
				{
					 rfb = new HORepo8FormBean();
 					 rfb.setName(bname);
 					 rfb.setColor(2);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, bvval[z]);
		   					hval=hval+bvval[z];
		   					bvval[z]=0.00;
	   				 }
                    ////////////////////Branch Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
	 			    	 
					dcode=rst1.getInt(3);
					bname=rst1.getString(1);
				}
				
				rfb = new HORepo8FormBean();
				rfb.setName(rst1.getString(2));
				rfb.setColor(1);	
				rfb.setMon(index);
                k=0;  
                hval=0.00;
                mon=4+(smon-1);
                while (k<index-1)
                {
                    rfb.setVhead(k, head[smon-1+k]);
                    rfb.setVal1(k, (rst1.getDouble(mon+k)));
                    hval=hval+(rst1.getDouble(mon+k));
                    vval[k]=vval[k]+(rst1.getDouble(mon+k));
                    bvval[k]=bvval[k]+(rst1.getDouble(mon+k));
		            k++;
                }
                    rfb.setHead1(txt1+txt2+txt5);				     
		 			rfb.setVhead(k, "TOTAL VALUE");
 			    	rfb.setVal1(k, hval);
 			    	data.add(rfb); 				
				}         ////////////////////////Branch Loop End///////////////////////
			
			        rst1.close();
			        ps1.close();
			         
					rfb = new HORepo8FormBean();
 					rfb.setName(bname);
 					rfb.setColor(2);
 					hval=0.00;
 					for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, bvval[z]);
		   					hval=hval+bvval[z];
		   					bvval[z]=0.00;
	   				 }
                    ////////////////////Branch Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
			         
			         
			 /////////////////Branch Total Ke Liye//////////////////////////////
					 rfb = new HORepo8FormBean();
 					 rfb.setName("GRAND TOTAL :");
 					 rfb.setColor(3);
 					 hval=0.00;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
	   				 }
                    ////////////////////Grand Total Ke liye/////////////////
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
					    	 
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo11DAO.getAllhqgroup " + e); 
		}
	
		finally {
			try 
			{  
			   if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo11DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
		
	
	public List getAllStkGroup(Connection con,int code, int smon, int emon,String tp,int loginid,int eyear) { 
		 
		HORepo8FormBean rfb;
		PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps22=null; 
        ResultSet rst22=null;
        
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
			String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String query22=null;
            int index =emon+1;
            int k=0;
            double hval=0.00;
            
            double thval=0.00;
            double dhval=0.00;
            double[] vval;
            double[] tvval;
            double[] dvval;
            double[] val;
            String[] head2;

            int a=0;
            int vno=0;
            int tercd=0;
            String ternm=null;
            int depocode=0;
            String deponm=null;
            boolean flag=true;
            boolean bflag=true;
            
        	tblnm=(tp+"_stockiest08").toLowerCase();
        	tblnm1=(tp+"_groupsales08").toLowerCase();
        	tblnm2=(tp+"_account08").toLowerCase();
        	tblnm3=(tp+"_hq_master08").toLowerCase();            	
        	tblnm4=(tp+"_branch08").toLowerCase();            	

            txt2=" STOCKIEST WISE GROUP WISE NET TREND FROM "; 
			 
	        vval = new double[index];
	
	        tvval = new double[index];
	        dvval = new double[index];
	
	        val = new double[index];
	        head2 = new String[index];

	 
////////////////////////////////////Group Master Query///////////////////////////////////////////                
            String terrec = "Select gp_name from "+tblnm1+" where gp_code=? ";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="GROUP-> "+trec.getString(1);
			trec.close();
			ts1.close();

	        

/////////////////////////////////////Month File Query////////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year,mnth_ord from monthfl where mnth_ord<=? and mkt_year=?  order by mnth_ord";  
 		  ms1 = con.prepareStatement(month);
 		  ms1.setInt(1,emon);
 		  ms1.setInt(2,eyear);
  		  mrec = ms1.executeQuery();

 		 query22= "SELECT b.depo_code,b.ter_name,a.mterr_code,h.ter_name,a.mac_name,a.mcity," +
 		 "sum(valoct)-(sum(s.evaloct)+sum(s.bvaloct)+sum(s.rvaloct)+sum(s.svaloct)+sum(s.pvaloct)) cvoct,"+
 		 "sum(valnov)-(sum(s.evalnov)+sum(s.bvalnov)+sum(s.rvalnov)+sum(s.svalnov)+sum(s.pvalnov)) cvnov,"+
 		 "sum(valdec)-(sum(s.evaldec)+sum(s.bvaldec)+sum(s.rvaldec)+sum(s.svaldec)+sum(s.pvaldec)) cvdec,"+
 		 "sum(valjan)-(sum(s.evaljan)+sum(s.bvaljan)+sum(s.rvaljan)+sum(s.svaljan)+sum(s.pvaljan)) cvjan,"+
 		 "sum(valfeb)-(sum(s.evalfeb)+sum(s.bvalfeb)+sum(s.rvalfeb)+sum(s.svalfeb)+sum(s.pvalfeb)) cvfeb,"+
 		 "sum(valmar)-(sum(s.evalmar)+sum(s.bvalmar)+sum(s.rvalmar)+sum(s.svalmar)+sum(s.pvalmar)) cvmar,"+
 		 "sum(valapr)-(sum(s.evalapr)+sum(s.bvalapr)+sum(s.rvalapr)+sum(s.svalapr)+sum(s.pvalapr)) cvapr,"+
 		 "sum(valmay)-(sum(s.evalmay)+sum(s.bvalmay)+sum(s.rvalmay)+sum(s.svalmay)+sum(s.pvalmay)) cvmay,"+
 		 "sum(valjun)-(sum(s.evaljun)+sum(s.bvaljun)+sum(s.rvaljun)+sum(s.svaljun)+sum(s.pvaljun)) cvjun,"+
 		 "sum(valjul)-(sum(s.evaljul)+sum(s.bvaljul)+sum(s.rvaljul)+sum(s.svaljul)+sum(s.pvaljul)) cvjul,"+
 		 "sum(valaug)-(sum(s.evalaug)+sum(s.bvalaug)+sum(s.rvalaug)+sum(s.svalaug)+sum(s.pvalaug)) cvaug,"+
 		 "sum(valsep)-(sum(s.evalsep)+sum(s.bvalsep)+sum(s.rvalsep)+sum(s.svalsep)+sum(s.pvalsep)) cvsep "+
 		 " FROM "+tblnm2+" a, "+tblnm3+" h,"+tblnm+" s,"+tblnm4+" b " +
   		 " where s.grp_cd=? and " + 
   		 " s.mkt_year=? and a.mkt_year=? and h.mkt_year=?  " +
   		 " and b.depo_code=h.depo_code and b.depo_code=a.depo_code and b.depo_code=s.depo_code "+
   		 " and a.mterr_code = h.ter_code  and h.ter_code = s.tr_cd and a.mterr_code=s.tr_cd " +
   		 " and a.mac_code = s.stk_code " + 
   		 " group by s.mkt_year,s.depo_code,s.tr_cd,s.stk_code order by a.depo_code,a.mterr_code,a.mac_name "; 		  

//////////////////////////////Headquater ki Query/////////////////////////////////
//          String query22 = "Select ter_code,ter_name from "+tblnm3+" where depo_code=? and ter_pt=? and mkt_year=?  order by area_code,regn_code,ter_code";
	        ps22 = con.prepareStatement(query22);
	        ps22.setInt(1,code);
	        ps22.setInt(2,eyear);
	        ps22.setInt(3,eyear);
	        ps22.setInt(4,eyear);
	        rst22 = ps22.executeQuery();
  		  
/////////////////////////////////////Account Master Query////////////////////////////////////////
		  
          while (rst22.next()) ///// Depo Loop Start
	        {

		    	if (bflag)
		    	{
		    		depocode=rst22.getInt(1);
		    		deponm=rst22.getString(2);
		    		bflag=false;
		    	}


		    	
        	    if (flag)
		    	{
		    		tercd=rst22.getInt(3);
		    		ternm=rst22.getString(4);
		    		flag=false;
		    	}

		    	if (tercd!=rst22.getInt(3))  // HQ Wise Total /////////////////////////
		    	{
		    		 a=0;
					 thval=0.00;
					 rfb = new HORepo8FormBean();
					 rfb.setPname(ternm); 
		             rfb.setColor(2);
			 			for (a=0; a<index-1;a++)
			 			{
	 		   					rfb.setVal1(a, tvval[a]);
			   					thval=thval+tvval[a];
			                    tvval[a]=0.00;
			 			}
			 			
				 			 rfb.setVhead(a, "TOTAL VALUE");
		 			    	 rfb.setVal1(a, thval);

		                 data.add(rfb);
				
        	  
		            thval=0.00;
		    		tercd=rst22.getInt(3);
		    		ternm=rst22.getString(4);
		    	}
  		    

		    	if (depocode!=rst22.getInt(1))  // Depo Wise Total /////////////////////////
		    	{
		    		 a=0;
					 dhval=0.00;
					 rfb = new HORepo8FormBean();
					 rfb.setPname(deponm);
		             rfb.setColor(2);
			 			for (a=0; a<index-1;a++)    
			 			{
	 		   					rfb.setVal1(a, dvval[a]);
			   					dhval=dhval+dvval[a];
			                    dvval[a]=0.00;
			 			}
			 			
				 			 rfb.setVhead(a, "TOTAL VALUE");
		 			    	 rfb.setVal1(a, dhval);

		                 data.add(rfb);
				
        	  
		            dhval=0.00;
		    		depocode=rst22.getInt(1);
		    		deponm=rst22.getString(2);
		    	}
		    	
		    	
		    	
		    	k=0;  
                hval=0.00;
                
 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			    	 head2[k]=mrec.getString(3)+" VALUE";

		 			     if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			     	 				 	  
 			             if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
 			                vno=mrec.getInt(5)+6;	 			             

		 			            val[k]=rst22.getDouble(vno);
		 			    		hval=hval+val[k];
		 			    		vval[k]=vval[k]+val[k];
		 			    		thval=thval+val[k];
		 			    		tvval[k]=tvval[k]+val[k];
		 			    		dhval=dhval+val[k];
		 			    		dvval[k]=dvval[k]+val[k];
			        
				        
                    k++; 
			      }    //////////////End of Month loop////////////////////
		 			
		 			
				      a=0;
				if (hval!=0)
				{
					rfb = new HORepo8FormBean();
		 			for (a=0; a<k;a++)
		 			{
						rfb.setPname(rst22.getString(5)+", "+rst22.getString(6));
		                rfb.setMon(index);
	                    rfb.setVal1(a, val[a]);
	 			    	rfb.setVhead(a,head2[a]);
		 			}
  		 			
			 			 rfb.setVhead(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);

	 			     rfb.setHead1(txt1+txt2+txt5);
	 				
	                 data.add(rfb);
				}
				
			
	     } ///////////////////Depo Loop End/////////////////////////

          
/////////////////////// HQ Total Printing For Last Record /////////////////////////////////          
	         a=0;
			 thval=0.00;
			 rfb = new HORepo8FormBean();
			 rfb.setPname(ternm);
			 rfb.setColor(2);
	 			for (a=0; a<index-1;a++)
	 			{
		   				rfb.setVal1(a, tvval[a]);
	   					thval=thval+tvval[a];  
	                    tvval[a]=0.00;
	 			}
	 			
		 			 rfb.setVhead(a, "TOTAL VALUE");
			    	 rfb.setVal1(a, thval);


              data.add(rfb);
          
/////////////////////// Depo Total Printing /////////////////////////////////          
	         a=0;
			 dhval=0.00;
			 rfb = new HORepo8FormBean();
			 rfb.setPname(deponm);
             rfb.setColor(2);
	 			for (a=0; a<index-1;a++)
	 			{
		   				rfb.setVal1(a, dvval[a]);
	   					dhval=dhval+dvval[a];  
	                    dvval[a]=0.00;
	 			}
	 			
		 			 rfb.setVhead(a, "TOTAL VALUE");
 			    	 rfb.setVal1(a, dhval);

 
                 data.add(rfb);
			
/////////////////////// Grand Total Printing /////////////////////////////////          
          
			 		 rfb = new HORepo8FormBean();
 					 rfb.setPname("TOTAL :");
 					 hval=0.00;
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
	   				 }
	 			    	 rfb.setVal1(z, hval);
	 			    	 data.add(rfb); 				
			    
						
					    	 
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo11DAO.getAllStockiestGroup " + e); 
		}
	
		finally {
			try 
			{  
			   if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo11DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
	
	
	
}
