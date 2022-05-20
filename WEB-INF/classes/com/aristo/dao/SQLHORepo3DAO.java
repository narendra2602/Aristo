package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo3FormBean;


public class SQLHORepo3DAO {

	public List getAllBranch(Connection con, int smon,int emon, int rs, String tp,String typ,int code1,int loginid,int eyear) { 
	  	 
		HORepo3FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;
        
        String uvnm=null;
 		
		List<HORepo3FormBean> data = new ArrayList<HORepo3FormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String tblnm3=null;
            String query3=null;
            String txt1=null;
            String txt5 =null; 
            String wtxt ="";
            double salval=0.00;
            double esalval=0.00;
            double bsalval=0.00;
            double grosssal=0.00;
            double pdsal=0.00;
            double spoilsal=0.00;
            

            
            double gsalval=0.00;
            double gesalval=0.00;
            double gbsalval=0.00;
            double ggrosssal=0.00;
            double gpdsal=0.00;
            double gspoilsal=0.00;
            
            if (smon>emon)
            	emon=smon;

        	tblnm3="user_branch08";
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
        	tblnm2=(tp+"_branch08").toLowerCase();
            
            txt1="BRANCH WISE/EXPIRY/BREAKAGE/SALABLE/ FOR THE MONTH OF ";
            
            if (rs==2)
            	wtxt=" - (IN LACS) ";

/////////////////////////////// Month File Loop Starts to accumulate data////////////////////////
            String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
         
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name from "+tblnm3+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=?";
	        ps2 = con.prepareStatement(query22);
	        ps2.setInt(1,loginid); 
	        ps2.setString(2, "Y");
	        rst2 = ps2.executeQuery();

	        while (rst2.next())///////////////////Branch Loop Start./////////////////////
				{
	            	rfb = new HORepo3FormBean();
	            	rfb.setName(rst2.getString(2)); 
		            salval=0.00;
                    esalval=0.00;
                    bsalval=0.00;
                    grosssal=0.00;
                    pdsal=0.00;
                    spoilsal=0.00;
                    
                    mrec.beforeFirst();
                    while (mrec.next())    ////////////////Month Loop Start///////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
	 			        if (mrec.isLast()) 
		 			       txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	            	uvnm="val"+mrec.getString(3);
		        	 
//////////////////////////////////HQ Detail Start//////////////////////////////////////////////
		 	if (typ.equals("PMT"))
		 	{
 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum("+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+
            " where depo_code=? and mkt_year=? and grp_cd in (select gp_code from pmt_group " +
            " where user_id="+loginid+" and access_t='"+tp.toUpperCase()+"' and status='Y') group by depo_code order by depo_code ";
		 	}
		 	else
		 	{
 	        query3 = "Select sum(r"+uvnm+"),sum(e"+uvnm+"),sum(b"+uvnm+"),sum("+uvnm+"),sum(s"+uvnm+"),sum(p"+uvnm+") from "+tblnm+
            " where depo_code=? and mkt_year=? group by depo_code order by depo_code ";
		 	}
	        ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,rst2.getInt(1)); 
	        ps3.setInt(2,eyear);
	        rst3 = ps3.executeQuery(); 
	        
	        if(rst3.next())
	        {
    		 salval = salval+rst3.getDouble(1);
    		 esalval = esalval+rst3.getDouble(2);
    		 bsalval = bsalval+rst3.getDouble(3);
    		 grosssal=grosssal+rst3.getDouble(4);
    		 spoilsal=spoilsal+rst3.getDouble(5);
    		 pdsal=pdsal+rst3.getDouble(6);
	        }      
		     
			rst3.close();
			ps3.close();
			
 		  }  ////////////////////////Month Loop End///////////////////////////////
			        	 
				 	 gsalval = gsalval+salval;
				 	 gesalval = gesalval+esalval;
				 	 gbsalval = gbsalval+bsalval;
		    		 ggrosssal=ggrosssal+grosssal;
		    		 gspoilsal=gspoilsal+spoilsal;
		    		 gpdsal=gpdsal+pdsal;
		
				 	 if (rs==1)
				 	 {
				 	 rfb.setSalable(salval);
		    		 rfb.setExpiry(esalval);
		    		 rfb.setBreakage(bsalval);
		    		 rfb.setGsale(grosssal);
		    		 rfb.setSpoil(spoilsal);
		    		 rfb.setPd(pdsal);
		    		 rfb.setTotal(grosssal-(salval+esalval+bsalval+spoilsal+pdsal));
				 	 }
				 	 
				 	 else 
				 	 {
				 	 rfb.setSalable(salval/100000);
		    		 rfb.setExpiry(esalval/100000);
		    		 rfb.setBreakage(bsalval/100000);
		    		 rfb.setGsale(grosssal/100000);
		    		 rfb.setSpoil(spoilsal/100000);
		    		 rfb.setPd(pdsal/100000);
		    		 rfb.setTotal((grosssal-(salval+esalval+bsalval+spoilsal+pdsal))/100000);
				 	 }
				 	 
		    		 rfb.setHead1(txt1+txt5+wtxt); 
		             data.add(rfb);
		             
      }  ///////////////////////Branch Loop End//////////////////////////////
			 
		   		     mrec.close();
				     ms1.close();
				     rst2.close();
				     ps2.close();
/////////////////////////////////////////////////////////////////////////////////////
				     
            		 rfb = new HORepo3FormBean();
            		 rfb.setName("TOTAL : "); 
            		 if (rs==1)
            		 {
        		 	 rfb.setSalable(gsalval);
	        		 rfb.setExpiry(gesalval);
	        		 rfb.setBreakage(gbsalval);
		    		 rfb.setGsale(ggrosssal);
		    		 rfb.setSpoil(gspoilsal);
		    		 rfb.setPd(gpdsal);
		    		 rfb.setTotal(ggrosssal-(gsalval+gesalval+gbsalval+gspoilsal+gpdsal));
            		 }
            		 else
            		 {
        		 	 rfb.setSalable(gsalval/100000);
	        		 rfb.setExpiry(gesalval/100000);
	        		 rfb.setBreakage(gbsalval/100000);
		    		 rfb.setGsale(ggrosssal/100000);
		    		 rfb.setSpoil(gspoilsal/100000);
		    		 rfb.setPd(gpdsal/100000);
		    		 rfb.setTotal((ggrosssal-(gsalval+gesalval+gbsalval+gspoilsal+gpdsal))/100000);
            		 }
        		     data.add(rfb); 				
 	        		    
		} catch (Exception e) { 
			
			System.out.println("========Exception in SQLHORepo3DAO.getAllBranch " + e);
		}
		
		finally {
			try 
			{
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(rst2 != null){ rst2.close();}
	           if(ps2 != null){ps2.close();}
	           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in SQLHORepo3DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}


	
	
}
 