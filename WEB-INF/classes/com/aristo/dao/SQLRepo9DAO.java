package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo9FormBean;

public class SQLRepo9DAO {

	public List getAllStk(Connection con, String code,int uv, int rep,int emon,int eyear,int depo_code,String tp) { 
		  
		Repo9FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo9FormBean> data = new ArrayList<Repo9FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt5 =null;
            String wtxt=null;
            String query3=null;
            
            int index=emon+1; 

            double hqty =0.00;
            double hval=0.00;
            double[] vqty;
            double[] vval;
            
        	tblnm=(tp+"_stockiest08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_account08").toLowerCase();
 
   	        
   	        
            if (rep==1)
            	wtxt="EXPIRY - ";
            if (rep==2)
            	wtxt="BREAKAGE - ";
            if (rep==3)
            	wtxt="SALABLE - ";
            if (rep==4)
            	wtxt="SPOILED - ";
            if (rep==5)
            	wtxt="PRICE DIFF - ";
            if (rep==6)
            	wtxt="EXPIRY/BREAKAGE/SALABLE/SPOILED/PD - ";
            
			 if (uv==1)
                txt2="     "+wtxt+"    PRODUCT WISE UNIT TREND FROM "; 
			 if (uv==2)
	            txt2="     "+wtxt+"    PRODUCT WISE VALUE TREND FROM "; 
			 if (uv==3)
	            txt2="     "+wtxt+"    PRODUCT WISE UNIT/VALUE TREND FROM "; 

		vqty=new double[index]; 
		vval=new double[index];
		
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
	     String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
	     ps12 = con.prepareStatement(query12);
	     ps12.setInt(1,depo_code);
	     ps12.setString(2,"STK09"); 
	     ps12.setString(3,tp); 
	     rst12 = ps12.executeQuery();
	       if (rst12.next())
	      	  txt3= rst12.getString(1)+", TIME: "+rst12.getString(2);
		        
		 rst12.close();
		 ps12.close();			 
			 
////////////////////////////////////Account Master Query///////////////////////////////////////////                
          String terrec = "Select mac_code,mac_name from "+tblnm2+" where mac_code=? and mkt_year=? ";  
    	  ts1 = con.prepareStatement(terrec);
    	  ts1.setString(1, code);
    	  ts1.setInt(2,eyear);
    	  trec = ts1.executeQuery();
    	  if (trec.next())
              txt1="STOCKIEST: "+trec.getString(2);
    	   
/////////////////////////////////////Month File Query////////////////////////////////////////////////
          String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
 		  ms1 = con.prepareStatement(month);
 		  ms1.setInt(1,emon);
 		  ms1.setInt(2,eyear);
  		  mrec = ms1.executeQuery();    	   
  		  
//////////////////////////////////////Product Master Query///////////////////////////////////////////            
          String query1 = "Select distinct(mcode),mname,pack from "+tblnm1+" where mkt_year=? group by mcode order by mname";
		  ps1 = con.prepareStatement(query1); 
		  ps1.setInt(1,eyear);
		  rst1 = ps1.executeQuery();
			  
			while (rst1.next()) ////////////////////Product Loop Start/////////////////////   
			{
				rfb = new Repo9FormBean();
                int k=0;  
                hqty=0.00;
                hval=0.00;

 		  			mrec.beforeFirst();
		 			while (mrec.next())         //////////////////Month Loop Start///////////////////////
		 			{	
		 			     if (mrec.isFirst())	
		 				     txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			             txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
		 			        unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);
		
        if (rep==1)		 			        
		    query3 ="Select sum(e"+unm+"),sum(e"+vnm+") from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=? and mkt_year=? " +
		    "group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
         
        if (rep==2)		 			        
    		query3 ="Select sum(b"+unm+"),sum(b"+vnm+") from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=?  and mkt_year=? " +
    		"group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
        
        if (rep==3)		 			        
    		query3 ="Select sum(r"+unm+"),sum(r"+vnm+") from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=?  and mkt_year=? " +
    		"group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
        
        if (rep==4)		 			        
    		query3 ="Select sum(s"+unm+"),sum(s"+vnm+") from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=?  and mkt_year=? " +
    		"group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
 
        if (rep==5)		 			        
    		query3 ="Select sum(p"+unm+"),sum(p"+vnm+") from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=?  and mkt_year=? " +
    		"group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
        
        if (rep==6)		 			        
    		query3 ="Select sum(e"+unm+"),sum(e"+vnm+"),sum(b"+unm+"),sum(b"+vnm+"),sum(r"+unm+"),sum(r"+vnm+")," +
    				"sum(s"+unm+"),sum(s"+vnm+"),sum(p"+unm+"),sum(p"+vnm+") " +
    				"from "+tblnm+" where stk_code=? and mpr_code=? and depo_code=? and mkt_year=? " +
    		        "group by stk_code order by ar_cd,rg_cd,tr_cd,stk_code";
        
       	ps3 = con.prepareStatement(query3); 
        ps3.setString(1,code); 
        ps3.setInt(2,rst1.getInt(1));
        ps3.setInt(3,depo_code);
        ps3.setInt(4,mrec.getInt(4));
        rst3 = ps3.executeQuery();
        
			        if(rst3.next())
			        {
						rfb.setName(rst1.getString(2));
		                rfb.setMon(index);
		                rfb.setUv(uv);
		                
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setUhead(k, mrec.getString(3)+" UNITS");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setVhead(k, mrec.getString(3)+" VALUE");
		 			     
			         if (rep<6)
			         {
		 			     if ((uv==1) || (uv==3))
		 			     {	 
                            rfb.setQty1(k, rst3.getDouble(1));
                            hqty=hqty+rst3.getDouble(1);
                            vqty[k]=vqty[k]+rst3.getDouble(1);
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
                            rfb.setVal1(k, rst3.getDouble(2));
                            hval=hval+rst3.getDouble(2);
                            vval[k]=vval[k]+rst3.getDouble(2);
		 			     }
			         }
			         if (rep==6)
			         {
		 			     if ((uv==1) || (uv==3))
		 			     {	 
                            rfb.setQty1(k, (rst3.getDouble(1)+rst3.getDouble(3)+rst3.getDouble(5)+rst3.getDouble(7)+rst3.getDouble(9)));
                            hqty=hqty+(rst3.getDouble(1)+rst3.getDouble(3)+rst3.getDouble(5)+rst3.getDouble(7)+rst3.getDouble(9));
                            vqty[k]=vqty[k]+(rst3.getDouble(1)+rst3.getDouble(3)+rst3.getDouble(5)+rst3.getDouble(7)+rst3.getDouble(9));
		 			     }   

		 			     if ((uv==2) || (uv==3))
		 			     {	 
                            rfb.setVal1(k, (rst3.getDouble(2)+rst3.getDouble(4)+rst3.getDouble(6)+rst3.getDouble(8)+rst3.getDouble(10)));
                            hval=hval+(rst3.getDouble(2)+rst3.getDouble(4)+rst3.getDouble(6)+rst3.getDouble(8)+rst3.getDouble(10));
                            vval[k]=vval[k]+(rst3.getDouble(2)+rst3.getDouble(4)+rst3.getDouble(6)+rst3.getDouble(8)+rst3.getDouble(10));
		 			     }
			         }
			         
			         
			        }	 
				        
                    k++; 
			        }    //////////////End of Month loop////////////////////      
				     
	 			     if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setUhead(k, "TOTAL UNITS");
	 			    	 rfb.setQty1(k, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setVhead(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			     }	 

	 			     rfb.setHead1(txt1+txt2+txt5);
	 			     rfb.setLupdate(txt3);
	 			     if ((hqty!=0) || (hval!=0))
	 			    	 data.add(rfb); 				
				} ///////////////////HQ Loop End/////////////////////////
			 		 rfb = new Repo9FormBean();
 					 rfb.setName("TOTAL :");
 					 hqty=0.00;
 					 hval=0.00;
 					 int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, vqty[z]);
		   					hqty=hqty+vqty[z];
 		 			     }
 		 			     
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     }
	   				 }
		 			     if ((uv==1) || (uv==3))
 		 			     {
		   					rfb.setQty1(z, hqty);
 		 			     }
 		 			     
 		 			     if ((uv==2) || (uv==3))
 		 			     {
 		   					rfb.setVal1(z, hval);
 		 			     }
 					 
	                 data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo9DAO.getAllStockiest9 " + e); 
		}
		
		finally 
		{
		try {
	           if(trec != null){ trec.close();}
	           if(ts1 != null){ ts1.close();}
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo9DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
}
