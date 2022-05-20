package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo2FormBean;

public class SQLRepo4DAO {

	public List getAllrepo(Connection con, int bm,int code,int smon,int emon,int eyear, int depo_code,String tp,int uid,String utype) { 
		Repo2FormBean rfb;
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
		List<Repo2FormBean> data = new ArrayList<Repo2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String txt6=null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;
            double salval=0.00;
            double salbval=0.00;
            double expval=0.00;

            double gsalval=0.00;
            double gsalbval=0.00;
            double gexpval=0.00;
            
            if (smon>emon)
            	emon=smon;

        	tblnm=tp+"_HQDetail08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_hq_master08";

            txt2=" PRODUCT WISE SALES DETAIL FROM ";
            
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
//////////////////////////////////////////////////////////////////////////////////////////                
    			
            String terrec = "Select ter_name from "+tblnm2+" where ter_pt=? and depo_code=? and ter_code=? and mkt_year=?";  
			ts1 = con.prepareStatement(terrec);
			ts1.setString(1,tp);
			ts1.setInt(2,depo_code);
			ts1.setInt(3, code);
			ts1.setInt(4, eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="H.Q.-> "+trec.getString(1);
		
			trec.close();
			ts1.close();
			
           	query1 = "Select pcode,pname,pack,trd_rt1 from "+tblnm1+" where pcode<>? and mkt_year=? order by mcode";            	
            ps1 = con.prepareStatement(query1); 
			ps1.setInt(1,0);
			ps1.setInt(2, eyear);
			rst1 = ps1.executeQuery();
              
			while (rst1.next())  ///////////////////////////Product Master KA loop////////////////////// 
			{
				rfb = new Repo2FormBean();
				rfb.setMcode(rst1.getInt(1));
				rfb.setMname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
			    rfb.setRate(rst1.getFloat(4));  
		        
                    ///// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord ";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1,smon);
		 			ms1.setInt(2,emon);
		 			ms1.setInt(3,eyear);
		 			mrec = ms1.executeQuery();
		            salqty=0;
		            salbqty=0;
		            expqty=0;
		            salval=0.00;
		            salbval=0.00;
		            expval=0.00;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 				 	  
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 	           
		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);
		 	            	
  	        	    String query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where tr_cd=? "+
		            " and pr_code=?  and depo_code=? and mkt_year=? group by pr_code order by ar_cd,rg_cd,tr_cd,pr_code"; 
			        ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code); 
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,depo_code);
			        ps3.setInt(4,mrec.getInt(4));
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getDouble(7);
			        		 salbval=salbval+rst3.getDouble(8);
			        		 expval=expval+(rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			         }	 
				        
			        }      
				     
	        		 rfb.setQty2(salqty);
	        		 rfb.setQty3(salbqty);
	        		 rfb.setQty4(expqty);
	        		 rfb.setQty5(salqty-salbqty-expqty);
	        		 
	        		 rfb.setDval2(salval);
	        		 rfb.setDval3(salbval);
	        		 rfb.setDval4(expval);
	        		 rfb.setDval5(salval-salbval-expval);
 	 				 rfb.setNm3(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);
	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	                 data.add(rfb); 				
				} 
					 rfb = new Repo2FormBean();
 					 rfb.setMname("TOTAL :");
	        		 rfb.setDval2(gsalval);
	        		 rfb.setDval3(gsalbval);
	        		 rfb.setDval4(gexpval);
	        		 rfb.setDval5(gsalval-gsalbval-gexpval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in HQ-SQLRepo4DAO.getAllrepo4 " + e); 
		}
		
		finally {
//			enclose this in a finally block to make
//			sure the connection is closed
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
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in HQ-SQLRepo4DAO.Connection.close "+e);
		    }
		}		
		
		return data;
	}

}  
    