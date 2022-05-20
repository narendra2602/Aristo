package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo9FormBean;

public class SQLHORepo9DAO {

	public List getAllStk(Connection con,int emon,int loginid,int rs,int st,String tp,int eyear) { 
		 
		HORepo9FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;

        String vnm=null;		
		List<HORepo9FormBean> data = new ArrayList<HORepo9FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;

            String txt2=null;
            String txt5 =null;
            String wtxt= "";
            int index = emon+1;
            double lacs=0.00;
            
            double hval=0.0f;
            double[] vval = new double[index];
            double[] tval = new double[index];
            
            tblnm2="user_branch08";
            
        	tblnm=(tp+"_hqdetail08").toLowerCase();
        	tblnm1=(tp+"_branch08").toLowerCase();
            
            if (st==1)
                txt2="     BRANCH WISE RUPEE WISE GROSS SALES TREND FOR THE MONTH OF "; 
            if (st==2)
                txt2="     BRANCH WISE RUPEE WISE CREDIT SALES TREND FOR THE MONTH OF "; 
            if (st==3)
                txt2="     BRANCH WISE RUPEE WISE NET SALES TREND FOR THE MONTH OF ";
   
            if (rs==2)
            	wtxt=" - (IN LACS)";
            
            
///////////////////////////////////////Month File Query//////////////////////////////////////////////
            String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord<=? and mkt_year=? order by mnth_ord";  
	        ms1 = con.prepareStatement(month);
            ms1.setInt(1, emon);
            ms1.setInt(2, eyear);
 		 	mrec = ms1.executeQuery();
 		 	
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name from "+tblnm2+" as d, "+tblnm1+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
	        ps2 = con.prepareStatement(query22);
	        ps2.setInt(1,loginid); 
	        ps2.setString(2, "Y");
	        rst2 = ps2.executeQuery();
 		 	
	        while (rst2.next()) ///// User Branch Master Loop Start
	        {
                hval=0.00;
                int k=0;  
				rfb = new HORepo9FormBean();
                rfb.setName(rst2.getString(2));
                rfb.setMon(index);
                
                    mrec.beforeFirst();
		 			while (mrec.next())  //// Month Loop Start////////////
		 			{	
		 			     rfb.setVhead(k, mrec.getString(3));
		 			 
		 			     if (mrec.isFirst())	
		 				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			             if (mrec.isLast())
	 			           txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
			 				
		 			        vnm="val"+mrec.getString(3);

/////////////////////////////////////////Stockiest//////////////////////////////////////////////////
         String query3=null;
         lacs=0.00;
         
       	 query3 = "Select sum("+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(r"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+ 
 	     " where depo_code=? and mkt_year=? group by depo_code order by depo_code";
    	 ps3 = con.prepareStatement(query3); 
         ps3.setInt(1, rst2.getInt(1));
         ps3.setInt(2, eyear);
         rst3 = ps3.executeQuery(); 
			        if(rst3.next())  /////// HQ Detail Start///////////////////
			        {
			           if (st==1)
			           {
			        	lacs=(rst3.getDouble(1)/100000);
			        	if (rs==1)
			        	 rfb.setVal1(k,rst3.getDouble(1));
			        	else
				         rfb.setVal1(k,lacs);
			        	
                        hval=hval+rst3.getDouble(1);
                        vval[k]=vval[k]+rst3.getDouble(1);
                        tval[k]=tval[k]+rst3.getDouble(1);
			           }
			           if (st==2)
			           {
			        	lacs=((rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6))/100000);
			        	if (rs==1)
			        	 rfb.setVal1(k,(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)));
			        	else
					     rfb.setVal1(k,lacs);
			        		
                        hval=hval+(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6));
                        vval[k]=vval[k]+(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6));
                        tval[k]=tval[k]+(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6));
			           }
			           if (st==3)
			           {
			        	lacs=((rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)))/100000);
			        	if (rs==1)
			        	 rfb.setVal1(k,(rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6))));
			        	else
					     rfb.setVal1(k,lacs);

                        hval=hval+(rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)));
                        vval[k]=vval[k]+(rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)));
                        tval[k]=tval[k]+(rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)));
			           }
			        }	 //////////////  HQ Detail End///////////////////////
			        	 
                    k++; 
                    
                    rst3.close();
                    ps3.close();
                    
			        }////////////////End of Month loop///////////////////////////      
				     
		 			rfb.setVhead(k, "TOTAL");
		 			if (rs==1)
			    	 rfb.setVal1(k, hval);
		 			else
			    	rfb.setVal1(k, (hval/100000));

			    	rfb.setHead1(txt2 + txt5 + wtxt);
	                data.add(rfb); 				

			} /////////////////////////Branch Master Loop End here///////////////// 
			 		
					mrec.close();
					ms1.close();
					rst2.close();
					ps2.close();
					ps2.close();

			/////////////////////////////////////////////////////////////////////////////
					
					 rfb = new HORepo9FormBean();
 					 rfb.setName("GRAND TOTAL:");
                     hval=0.00;
                     lacs=0.00;
                     int z=0;
 					 for (z=0; z<index-1;z++)
	   				 {
 						    lacs=(vval[z]/100000);
 						    if (rs==1)
 						    {
 		 					 rfb.setVal1(z, vval[z]);
 		 					 hval=hval+vval[z];
 						    }
 						    else
 						    {
 	 		 				 rfb.setVal1(z, lacs);
 	 		 				 hval=hval+lacs;
 						    }
	   				 }
 					 		rfb.setVal1(z, hval);
	                        data.add(rfb); 				
				
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo9DAO.getAllBranch9 " + e); 
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
				System.out.println("-------------Exception in SQLHORepo9DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
	
} 