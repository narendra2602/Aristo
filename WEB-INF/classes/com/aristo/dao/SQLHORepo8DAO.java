package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo8FormBean;

public class SQLHORepo8DAO {

	public List getAllBranch(Connection con, int code,int uv,int rs, int emon,String tp,String typ,int loginid,int code1,int eyear) { 
		 
		HORepo8FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        
		String unm=null;
        String vnm=null;		
		List<HORepo8FormBean> data = new ArrayList<HORepo8FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String wtxt="";
            
            int index = emon+1;
            int fs=0;
            double lacs=0.00;
            double hqty=0.00;
            double hval=0.00;
            double[] vqty = new double[index];
            double[] vval = new double[index];
            
            tblnm3="user_branch08";
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
            
			 if (uv==1)
                txt2=" Branch Wise Unit Sales Trend (Gross) for the Month of "; 
			 if (uv==2)
	            txt2=" Branch Wise Value Sales Trend (Gross) for the Month of "; 
			 if (uv==3)
	            txt2=" Branch Wise Unit/Value Sales Trend (Gross) for the Month of ";
			 
			 if((rs==2)&&((uv==2)||(uv==3)))
				wtxt=" - (IN LACS) "; 
            
//////////////////////////////////Product Master/////////////////////////////////
            String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			ts1.setInt(2,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="Product-> "+trec.getString(1)+","+trec.getString(2);
		 
			trec.close();
			ts1.close();
    			
//////////////////////// Month File Loop Starts to accumulate data////////////////////////
   	        String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
   		 	ms1 = con.prepareStatement(month);
   		 	ms1.setInt(1,emon);
   		    ms1.setInt(2,eyear);
   		 	mrec = ms1.executeQuery();
    		   		 	
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name,t.no_of_rep from "+tblnm3+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
	        ps1 = con.prepareStatement(query22);
	        ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        rst1 = ps1.executeQuery();
			 
			while (rst1.next())   ////////////////USer Branch Master Loop Start////////////////////////
			{
				rfb = new HORepo8FormBean();
				rfb.setName(rst1.getString(2));
				rfb.setNo_of_mr(rst1.getInt(3));
                fs=fs+rst1.getInt(3);
				rfb.setMon(index);
                rfb.setUv(uv);
                int k=0;  
                hqty=0.00;
                hval=0.00;
                lacs=0.00;
                mrec.beforeFirst();
                while (mrec.next())   ////////////////////Month File Loop Start///////////////////
		 			{	
		 			     if ((uv==1) || (uv==3))
		 			    	 rfb.setUhead(k, mrec.getString(3)+" UNIT");
		 			     if ((uv==2) || (uv==3))
		 			    	 rfb.setVhead(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 				 	     if (mrec.isLast())
		 			       txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	                unm="qty"+mrec.getString(3);
		 			        vnm="val"+mrec.getString(3);

//////////////////////////////////HQ Detail Query///////////////////////////////////////    	
	    	 String query3 = "Select sum("+unm+"),sum("+vnm+") from "+tblnm+" where "+
	        "  pr_code=? and depo_code=? and mkt_year=? group by depo_code order by depo_code";
        	ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,code);
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,eyear);
	        rst3 = ps3.executeQuery();
			        
			        if(rst3.next())      //////////////////////////HQ Detail Start//////////////////
			        {
		 			     if ((uv==1) || (uv==3))
		 			     {	 
                            rfb.setQty1(k, rst3.getDouble(1));
                            hqty=hqty+rst3.getDouble(1);
                            vqty[k]=vqty[k]+rst3.getDouble(1);
		 			     }   
		 			     if ((uv==2) || (uv==3))
		 			     {	 
		 			    	lacs=rst3.getDouble(2); 
		 			    	if (rs==2)
		 			    	lacs=(rst3.getDouble(2)/100000);
		 			    	
                            rfb.setVal1(k, lacs);
                            hval=hval+lacs;
                            vval[k]=vval[k]+lacs;
		 			     }   
			        }	 //////////////////////////HQ Detail End//////////////////
			        	 
                    k++; 
                    
                    rst3.close();
                    ps3.close();
                    
			        }  ////////////////End of Month loop////////////////////////
				     
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

	 			     rfb.setHead1(txt1+txt2+txt5+wtxt);
	 				
	                 data.add(rfb); 				
				}         ////////////////////////Branch Loop End///////////////////////
			
			         mrec.close();
			         ms1.close();
			         ps1.close();
			         rst1.close();
			         ps1.close();
			         
			 /////////////////Total Ke Liye//////////////////////////////
					 rfb = new HORepo8FormBean();
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 
 					 hqty=0.00;
 					 hval=0.00;
 					 int z;
 					 for (z=0; z<emon;z++)
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
                    ////////////////////Grand Total Ke liye/////////////////
 					 if ((uv==1) || (uv==3))
	 			     {	 
			 			 rfb.setUhead(z, "TOTAL UNITS");
	 			    	 rfb.setQty1(z, hqty);
	 			     }	 
	 			     if ((uv==2) || (uv==3))
	 			     {	 
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			     }	 
 					 
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo8DAO.getAllBranch " + e); 
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
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo8DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
	
	
}
 