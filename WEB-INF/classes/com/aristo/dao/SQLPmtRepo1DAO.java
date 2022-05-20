package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo1FormBean;

public class SQLPmtRepo1DAO {

	public List getAllBranch(Connection con, int uv, int smon, int emon, int saletp,String tp,String typ,int code1,int loginid,int eyear) { 
		HORepo1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
		
		List<HORepo1FormBean> data = new ArrayList<HORepo1FormBean>();
		try {     
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String tblnm5=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            double salqty=0.00;
            double salval=0.00;
            double gval[];
            double gsalqty=0.00;
            double gsalval=0.00;

            if (smon>emon)
            	emon=smon;

   	        tblnm3="user_branch08";
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
   	        tblnm4=(tp+"_brsale").toLowerCase();
   	        tblnm5="pmt_group";   	        
       	    
           if (uv==1)
            {
            	txt3="UnitWise/";
            }
            
            if (uv==2)
            {
            	txt3="ValueWise/";
            }
            if (uv==3)
            {
            	txt3="Unit-ValueWise/";
            }
            
            if(saletp==1)
            	txt4="Gross Sale For the Month of ";
            if(saletp==2)
            	txt4="Credit Note Sale For the Month of ";
            if(saletp==3)
            	txt4="Net Sale For the Month of ";
            
                txt2="Product Wise/"; 
                txt1="Branch Wise/";
			
            int t=0;
            int w=0;
 
            String terrec = "Select count(*) from "+tblnm3+" where user_id=? and status=?";
 			ts1 = con.prepareStatement(terrec);
 			ts1.setInt(1,loginid);
 			ts1.setString(2,"Y");
			trec = ts1.executeQuery();
			if (trec.next())
			{
				t = trec.getInt(1)+1;
				w= trec.getInt(1);
			}
				gval = new double[t];
            	
            trec.close();
            ts1.close();
/////////////////////////////////Month File Loop Starts to accumulate data////////////////////////////////////////
	 		String qty="";
	 		String eqty="";
	 		String bqty="";
	 		String rqty="";
	 		String sqty="";
	 		String pqty="";
	 		
	 		String val="";
	 		String eval="";
	 		String bval="";
	 		String rval="";
	 		String sval="";
	 		String pval="";
	 		   
	    	String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	    	ms1 = con.prepareStatement(month);
	    	ms1.setInt(1,smon);
	    	ms1.setInt(2,emon);
	    	ms1.setInt(3,eyear);
	    	mrec = ms1.executeQuery();
	    	boolean flag=false;
	    	boolean first=true;
	    	while (mrec.next())
	    	{
     		   if (mrec.isFirst())	
				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
	           if (mrec.isLast())
		          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
	      
	    	  if (flag)	
	    	  {
	    	   qty+="+h.qty"+mrec.getString(3);
	    	   eqty+="+h.eqty"+mrec.getString(3);
	    	   bqty+="+h.bqty"+mrec.getString(3);
	    	   rqty+="+h.rqty"+mrec.getString(3);
	    	   sqty+="+h.sqty"+mrec.getString(3);
	    	   pqty+="+h.pqty"+mrec.getString(3);
	    	   
	    	   val+="+h.val"+mrec.getString(3);
	    	   eval+="+h.eval"+mrec.getString(3);
	    	   bval+="+h.bval"+mrec.getString(3);
	    	   rval+="+h.rval"+mrec.getString(3);
	    	   sval+="+h.sval"+mrec.getString(3);
	    	   pval+="+h.pval"+mrec.getString(3);
	    	  }
	    		
	    	  if (first)	
	    	  {
	    	   qty="h.qty"+mrec.getString(3);
	    	   eqty="h.eqty"+mrec.getString(3);
	    	   bqty="h.bqty"+mrec.getString(3);
	    	   rqty="h.rqty"+mrec.getString(3);
	    	   sqty="h.sqty"+mrec.getString(3);
	    	   pqty="h.pqty"+mrec.getString(3);
	    	   
	    	   val="h.val"+mrec.getString(3);
	    	   eval="h.eval"+mrec.getString(3);
	    	   bval="h.bval"+mrec.getString(3);
	    	   rval="h.rval"+mrec.getString(3);
	    	   sval="h.sval"+mrec.getString(3);
	    	   pval="h.pval"+mrec.getString(3);
	    	   flag=true;
	    	   first=false;
	    	  }
	    	  
	    	}
           mrec.close();
		   ms1.close();
		   
/////////////// ////////////////////////////////Multiple Query Starts here ///////////////////////////////			
	   
		   String query1="Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0), " +
		   " ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) " +
		   " from (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name " +
		   " from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=? and p.pd_group in " +
		   " (select gp_code from "+tblnm5+" where user_id=? and status=?)) " +
		   " prd left join (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) " +
		   " cr_u,sum(net_units) net_u, sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) " +
		   " net_v from "+tblnm4+" where month>=? and month<=? and mkt_year=? and pr_code<>0 " +
		   " and grp_cd in (select gp_code from "+tblnm5+" where user_id=? and status=?) " +
		   " group by depo_code,grp_cd,pr_code) sale on prd.prd_code = sale.pr_code" +
		   " and prd.br_code = sale.depo_code inner join "+tblnm3+" u on u.depo_code=prd.br_code " +
		   " where u.user_id=? and u.status=? order by prd_code,br_code ";		   
		   
/*	        String query1 = "Select prd.*,ifnull(sale.gr_u,0),ifnull(sale.cr_u,0),ifnull(sale.net_u,0)," +
	   		" ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) from " +
	   		" (select p.pcode prd_code, p.pname prd_name,p.pack prd_pack,b.depo_code br_code,b.txt br_name" +
	   		" from "+tblnm1+" p, "+tblnm2+" b where p.pcode <> 0 and p.mkt_year=?) prd left join " +
	   		" (select depo_code,pr_code,sum(gross_units) gr_u,sum(credit_units) cr_u,sum(net_units) net_u," +
	   		" sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) net_v from "+tblnm4+
	   		" where month>=? and month<=? and mkt_year=? and pr_code<>0 group by depo_code,pr_code) sale on " +
	   		" prd.prd_code = sale.pr_code and prd.br_code = sale.depo_code inner join user_branch08 u" +
	   		" on u.depo_code=prd.br_code where u.user_id=? and u.status=? order by prd_code,br_code";
*/
		   
		   
           ps1 = con.prepareStatement(query1); 
           ps1.setInt(1,eyear);
	       ps1.setInt(2,loginid); 
	       ps1.setString(3, "Y");
           ps1.setInt(4,smon);
           ps1.setInt(5,emon);
           ps1.setInt(6,eyear);
	       ps1.setInt(7,loginid); 
	       ps1.setString(8, "Y");
	       ps1.setInt(9,loginid); 
	       ps1.setString(10, "Y");
		   rst1 = ps1.executeQuery();   
           int k=0;  
           gsalqty=0.00;
           gsalval=0.00;
           
           boolean hrprint = true;		
           rfb = new HORepo1FormBean();
           
			while (rst1.next())    ///////////////////////Product Master Loop Start///////////////////  
			{	
				rfb.setPcode(rst1.getInt(1));
				rfb.setPname(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
                rfb.setBr(t);
                rfb.setUv(uv);
                 if (hrprint)
                 {
		        	if ((uv==1) || (uv==3))
		        	{	
		        	  rfb.setUhead(k,(rst1.getString(5)+" Units"));
		        	}
		        	if ((uv==2) || (uv==3)) 
		        	{	
		        	  rfb.setVhead(k,(rst1.getString(5)+" Value"));
		        	}
                 }


		   salqty=0.00;
           salval=0.00;
          

			        	 
			         if (saletp==1)
			         {	 
			        	salqty = rst1.getDouble(6);
 		        		salval=rst1.getDouble(9);
			         }	 
			        	 
			         if (saletp==2)
			         {	 
			        	 salqty = rst1.getDouble(7);;
			        	 salval=rst1.getDouble(10);;
			         }	 

			         if (saletp==3)
			         {	 
			        	 salqty=rst1.getDouble(8);;
			        	 salval=rst1.getDouble(11);;
			         }	 

			             gval[k] = gval[k]+salval;

			        
		 			
		        	 if (uv==1)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 gsalqty=gsalqty+salqty;
		        	 }	 
		        	 if (uv==2)
		        	 {	 
		        		 rfb.setDval0(k,salval);
		        	 	 gsalval=gsalval+salval;
		        	 } 	 
		        	 if (uv==3)
		        	 {	 
		        		 rfb.setQty1(k,salqty);
		        		 rfb.setDval0(k,salval);
		        		 gsalqty=gsalqty+salqty;
		        	 	 gsalval=gsalval+salval;
		        	 }	 
		      
		        	     rfb.setHead1(txt1+txt2+txt3+txt4+txt5);
		        	     k++;
				          
			        	 
				        if(k==w)
				        {
				            if ((uv==1) || (uv==3))
			        	 	{	 
			            		rfb.setUhead(k,"TOTAL UNIT");
			            		rfb.setQty1(k,gsalqty);
			        	 	}		
			                  
			        	 	if ((uv==2) || (uv==3))
			        	 	{	 
			            		rfb.setVhead(k,"TOTAL VALUE");
			            		rfb.setDval0(k,gsalval);
			        	 	}		
			                gval[k]=gval[k]+gsalval;

				        	k=0;
				        	gsalqty=0;
				        	gsalval=0.00;
				        	hrprint=false;
				        	data.add(rfb);
				        	rfb = new HORepo1FormBean();
				        }
				} //////////////////////// End of Product Master Loop///////////////////////
			
            	  // rfb = new HORepo1FormBean();
    			   rfb.setPname("Total :");

			       for (int z=0; z<t;z++)
			       {
		   			  if (uv==1)
			   		  	 rfb.setQty1(z,(int) gval[z]);
		   			  else
		   			  	 rfb.setDval0(z,gval[z]); 
			       }
			        data.add(rfb); 				
			    
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLPmtRepo1DAO.getAllBranch " + e);
		}
		
		finally
		{
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
				catch (SQLException e) 
					{
						System.out.println("-------Exception in SQLPmtRepo1DAO.Connection.close "+e);
					}
		}		
		return data; 
	}
	
/////////////////////////////////////////Branch Close here//////////////////////////////////////////////////////	
	
}   