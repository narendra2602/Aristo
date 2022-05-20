package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo2FormBean;
 
public class SQLHORepo2DAO {

	public List getAllBranch(Connection con, int smon,int emon, int saletp,String tp,String typ,int code1,int loginid,int eyear) { 
		 
		HORepo2FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;

		
		List<HORepo2FormBean> data = new ArrayList<HORepo2FormBean>();
		try {     
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String tblnm5=null;
            String query1=null;
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            double salval=0.00;
            double gval[];
            double gsalval=0.00;

            if (smon>emon)
            	emon=smon;
            
   	        tblnm3="user_branch08";
        	tblnm1=(tp+"_groupsales08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
   	        tblnm4=(tp+"_brsale").toLowerCase();
   	        tblnm5="pmt_group";

			
            	txt3="ValueWise/";
            
            if(saletp==1)
            	txt4="Gross Sale For the Month of ";
            if(saletp==2)
            	txt4="Credit Note Sale For the Month of ";
            if(saletp==3)
            	txt4="Net Sale For the Month of ";
            
                txt2="Group Wise/";
                txt1="Branch Wise/";
			
                int t=0;
                int w=0;

////////////////////////////////////////     Branch Master Count/////////////////////////////////// 
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

                
                
                
//////////////  / ////////////////////////////////Multiple Query Starts here ///////////////////////////////			

                if (typ.equals("PMT"))
     		   {
                	
     			   query1="Select prd.*, " +
     			   " ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) " +
     			   " from (select g.gp_code grp_code, g.gp_name grp_name,b.depo_code br_code,b.txt br_name " +
     			   " from "+tblnm1+" g, "+tblnm2+" b where g.gp_code <> 0 and g.gp_code in " +
     			   " (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?)) " +
     			   " prd left join (select depo_code,grp_cd, " +
     			   " sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) " +
     			   " net_v from "+tblnm4+" where month>=? and month<=? and mkt_year=? and grp_cd<>0 " +
     			   " and grp_cd in (select gp_code from "+tblnm5+" where user_id=? and access_t='"+tp.toUpperCase()+"' and status=?) " +
     			   " group by depo_code,grp_cd) sale on prd.grp_code = sale.grp_cd" +
     			   " and prd.br_code = sale.depo_code inner join "+tblnm3+" u on u.depo_code=prd.br_code " +
     			   " where u.user_id=? and u.status=? order by grp_code,br_code ";
     		   }
                 
     		   else
     		   {
     				query1="select * from (Select prd.*," +
     				" ifnull(sale.gr_v,0),ifnull(sale.cr_v,0),ifnull(sale.net_v,0) from " +
     				" (select g.gp_code grp_code, g.gp_name grp_name,b.depo_code br_code,b.txt br_name" +
     				" from "+tblnm1+" g, "+tblnm2+" b where g.gp_code <> 0  and b.depo_code in " +
     				" (select  depo_code from user_branch08 where  user_id = ? and status = ?)) prd left join" + 
     				" (select depo_code,grp_cd," +
     				" sum(gross_val) gr_v,sum(credit_val) cr_v,sum(net_val) net_v from "+tblnm4+
     				" where month>=? and month<=? and mkt_year=? and grp_cd<>0 group by depo_code,grp_cd) sale on" + 
     				" prd.grp_code = sale.grp_cd and prd.br_code = sale.depo_code ) a order by a.grp_code,a.br_code" ;     				
     		   }

                
               ps1 = con.prepareStatement(query1);
     		   if (typ.equals("PMT"))
     		   {
                ps1.setInt(1,loginid); 
     	       	ps1.setString(2, "Y");
                ps1.setInt(3,smon);
                ps1.setInt(4,emon);
                ps1.setInt(5,eyear);
	     	    ps1.setInt(6,loginid); 
	     	    ps1.setString(7, "Y");
	     	    ps1.setInt(8,loginid); 
	     	    ps1.setString(9, "Y");
     		   }
     		   else
     		   {
     	       	ps1.setInt(1,loginid); 
     	       	ps1.setString(2, "Y");
                ps1.setInt(3,smon);
                ps1.setInt(4,emon);
                ps1.setInt(5,eyear);
     		   }
     		   	rst1 = ps1.executeQuery();   
                int k=0;  
                gsalval=0.00;

                
                boolean hrprint = true;	
                boolean hprint = true;	
                int gpcode=0;
                String gname=null;
                rfb = new HORepo2FormBean();
                
     			while (rst1.next())    ///////////////////////Query  Loop Start///////////////////  
     			{	
     				if (hrprint)
     				{
     				  gpcode=rst1.getInt(1);
     				  gname = rst1.getString(2);
     				  hrprint=false;
     				}
     				
     	 			if (gpcode!=rst1.getInt(1))
     	 			{
     					rfb.setGcode(gpcode);
     					rfb.setGname(gname);
     	                rfb.setBr(t);
     	                
     			        if(k==w)
     			        {	                
     	            		rfb.setVhead(k,"TOTAL VALUE");
     	            		rfb.setVal1(k,gsalval);
     	            		gval[k]=gval[k]+gsalval;
     			        }

     	 				k=0;
     		        	gsalval=0.00;
     		        	hprint=false;
     		        	data.add(rfb);
     					gpcode=rst1.getInt(1);
     					gname = rst1.getString(2);
     		        	rfb = new HORepo2FormBean();
     	                
     	 			}
                      if (hprint)
                      {
     		        	  rfb.setVhead(k,(rst1.getString(4)+" Value"));
                      }


                      salval=0.00;
               

     			        	 
     			         if (saletp==1)
     			         {	 
      		        		salval=rst1.getDouble(5);
     			         }	 
     			        	 
     			         if (saletp==2)
     			         {	 
     			        	 salval=rst1.getDouble(6);;
     			         }	 

     			         if (saletp==3)
     			         {	 
     			        	 salval=rst1.getDouble(7);;
     			         }	 

     			             gval[k] = gval[k]+salval;

     			         
     		 			
     		        		 rfb.setVal1(k,salval);
     		        	 	 gsalval=gsalval+salval;
     		      
     		        	     rfb.setHead1(txt1+txt2+txt3+txt4+txt5);
     		        	     k++;
     				          
     			        	 
     				} //////////////////////// End of Query Loop///////////////////////
     			
                
          	  // rfb = new HORepo1FormBean();

					rfb.setGcode(gpcode);
 					rfb.setGname(gname);
 	                rfb.setBr(t);
 	                
 			        if(k==w)
 			        {	                
 	            		rfb.setVhead(k,"TOTAL VALUE");
 	            		rfb.setVal1(k,gsalval);
 	            		gval[k]=gval[k]+gsalval;
 			        }

 		        	data.add(rfb);
 		        	
 		       	rfb = new HORepo2FormBean();
     			
     			rfb.setGname("Total :");

			       for (int z=0; z<t;z++)
			       {
		   			  	 rfb.setVal1(z,gval[z]); 
			       }
			        data.add(rfb); 				
			    
			
				 
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo2DAO.getAllGBranch " + e);
		}
		  finally 
			{
			  try
				{
		           if(trec != null){ trec.close();}
		           if(ts1 != null){ ts1.close();}
		           if(rst1 != null){ rst1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				} 
			  catch (SQLException e)
				{
				  System.out.println("-------------Exception in SQLHORepo2DAO.Connection.close "+e);
				}
			}
		
		return data;
	}
	
}
  