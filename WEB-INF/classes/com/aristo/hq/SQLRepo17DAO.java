package com.aristo.hq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo17FormBean;

public class SQLRepo17DAO {
	
	public List getAllrepo(Connection con, int code,int smon,int emon,int eyear,int depo_code,String tp,int uid) { 
		  
		Repo17FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
		
		String unm=null;
        String vnm=null;		
		List<Repo17FormBean> data = new ArrayList<Repo17FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String txt6=null;
            int salqty=0;
            int salbqty=0;
            int expqty=0;

            int gsalqty=0;
            int gsalbqty=0;
            int gexpqty=0;

            float salval=0f;
            float salbval=0f;
            float expval=0f;

            float gsalval=0f;
            float gsalbval=0f;
            float gexpval=0f;

            if (smon>emon)
            	emon=smon;
 
        	tblnm=tp+"_stockiest08";
        	tblnm1=tp+"_product08";
   	        tblnm2=tp+"_account08";
       	    
                txt2="     STOCKIEST WISE SALES DETAIL FROM ";
                
//////////////////////////////Date & time Updation ke liye////////////////////////////////			 
			String query12 = "Select u_date,u_time  from upload where depo_code=? AND substr(file_path,4,5)=? and substr(typ,1,1)=? order by depo_code";
			ps12 = con.prepareStatement(query12);
			ps12.setInt(1,depo_code);
			ps12.setString(2,"STK09"); 
			ps12.setString(3,tp); 
			rst12 = ps12.executeQuery();
			
			if (rst12.next())
			txt6= rst12.getString(1)+", TIME: "+rst12.getString(2);
			
			rst12.close();
			ps12.close();
//////////////////////////////////////////////////////////////////////////////////////////
            String terrec = "Select pname,pack from "+tblnm1+" where pcode=? and mkt_year=?";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		if (trec.next())
                txt1="PRODUCT-> "+trec.getString(1)+", "+trec.getString(2);
			
            String query1="Select a.mac_code,a.mac_name,a.mcity,a.depo_code from "+tblnm2+" as a," +
            " user_ter as u where a.depo_code=u.depo_code and a.mterr_code=u.ter_code " +
            " and u.user_id="+uid+" and a.mkt_year="+eyear+" order by a.mac_name "  ;            
            ps1 = con.prepareStatement(query1); 
			rst1 = ps1.executeQuery();
 	        
			String month = "Select mnth_name,mnth_year,mnth_abbr,mkt_year from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 	ms1 = con.prepareStatement(month);
             
			while (rst1.next())   
			{
            ///// Month File Loop Starts to accumulate data
 		 	ms1.setInt(1, smon);
		 	ms1.setInt(2, emon);
		 	ms1.setInt(3,eyear);		
		 	mrec = ms1.executeQuery();
		            salqty=0;
		            salbqty=0;
		            expqty=0;
		            salval=0f;
		            salbval=0f;
		            expval=0f;
		 			while (mrec.next())
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
     			        if (mrec.isLast())
 	 			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 			        unm="qty"+mrec.getString(3);
		 	            	vnm="val"+mrec.getString(3);

		 	            	
		    String query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where stk_code=? "+
		    "  and pr_code=? and depo_code=? and mkt_year=? group by stk_code order by stk_code"; 
			ps3 = con.prepareStatement(query3); 
			ps3.setString(1,rst1.getString(1)); 
			ps3.setInt(2,code);
			ps3.setInt(3,depo_code);
			ps3.setInt(4,mrec.getInt(4));			        
			rst3 = ps3.executeQuery(); 
			        if(rst3.next())
			        {
			        		 salqty = salqty+rst3.getInt(1);
			        		 salbqty = salbqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 salval = salval+rst3.getFloat(7);
			        		 salbval=salbval+rst3.getFloat(8);
			        		 expval=expval+(rst3.getFloat(9)+rst3.getFloat(10)+rst3.getFloat(11)+rst3.getFloat(12));
			         }	 
			        	 
			        }      
				  
		 			if ((salqty-salbqty-expqty)!=0)
		 			{
					 rfb = new Repo17FormBean();
					 rfb.setPname(rst1.getString(2)+ "," + rst1.getString(3));
	        		 rfb.setSqty(salqty);
	        		 rfb.setSlqty(salbqty);
	        		 rfb.setPqty(expqty);
	        		 rfb.setNetqty(salqty-salbqty-expqty);
	        		 
	        		 rfb.setSval(salval);
	        		 rfb.setSlval(salbval);
	        		 rfb.setPpval(expval);
	        		 rfb.setNetval(salval-salbval-expval);
 	 				 rfb.setHead1(txt1+txt2+txt5);
 	 				 rfb.setLupdate(txt6);

	        		 gsalqty = gsalqty  + salqty;
	        		 gsalbqty= gsalbqty + salbqty;
	        		 gexpqty = gexpqty  + expqty;

	        		 gsalval = gsalval  + salval;
	        		 gsalbval= gsalbval + salbval;
	        		 gexpval = gexpval  + expval;
	 				
	                 data.add(rfb);
		 			}
				} 
			 
					 rfb = new Repo17FormBean();
 					 rfb.setPname("TOTAL :");
	        		 rfb.setSqty(gsalqty);
	        		 rfb.setSlqty(gsalbqty);
	        		 rfb.setPqty(gexpqty);
	        		 rfb.setNetqty(gsalqty-gsalbqty-gexpqty);
	        		 
	        		 rfb.setSval(gsalval);
	        		 rfb.setSlval(gsalbval);
	        		 rfb.setPpval(gexpval);
	        		 rfb.setNetval(gsalval-gsalbval-gexpval);
	        		 
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			System.out.println("========Exception in HQ-SQLRepo17DAO.getAllStockiest17 " + e); 
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
	           if(rst12 != null){ rst12.close();}
	           if(ps12 != null){ps12.close();}
	           if(con != null){con.close();}
			} 
			catch (SQLException e) {
				System.out.println("-------------Exception in HQ-SQLRepo17DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
}  