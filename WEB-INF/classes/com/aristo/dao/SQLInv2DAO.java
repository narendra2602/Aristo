package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Inv2FormBean;

public class SQLInv2DAO {

	public List getInv2(Connection con,String branch,int depo, int type,int smon, int emon,int eyear, String tp,int uid,String utype) { 
		 
		Inv2FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;

		List<Inv2FormBean> data = new ArrayList<Inv2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String wname=null;
            String query3=null;
            String query1=null;
            String txt5=null;
            String txt2=null;
        	
        	double tmonqty=0.00;
        	double tmonval=0.00;
        	double ymonqty=0.00;
        	double ymonval=0.00;
 
        	double gtmonval=0.00;
        	double gymonval=0.00;
        	
            if (smon>emon)
            	emon=smon;
        	
        	tblnm=(tp+"_product08").toLowerCase();
        	tblnm1=(tp+"_stock08").toLowerCase();
                
	 		   if (type==60)	
	 			  txt2=" Company Receipt from Factory For the Month "; 
	 		   if (type==63)	
	 			  txt2=" Stockiest Salable Analysis For the Month ";
	 		   if (type==64)	
	 			  txt2=" Stockiest Un-Salable Analysis For the Month ";
	 		   if (type==69)	
	 			  txt2=" Stockiest (Expiry) For the Month ";
	 		   if (type==71)	
	 			  txt2=" Stockiest (Price Diff) For the Month ";
	 		   if (type==65)	
	 			  txt2=" Sales to Sample For the Month ";
	 		   if (type==66)	
	 			  txt2=" Written Off For the Month ";
	 		   if (type==80)	
	 			  txt2=" Free Issue For the Month ";
	 		   if (type==70)	
	 			  txt2=" Institutional Supply For the Month ";
	 		   if (type==67)	
		 	      txt2=" Transfer from Branches For the Month ";

	 		   
//////////////////Month File Loop Starts to accumulate data/////////////////////////
	    	String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
	    	ms1 = con.prepareStatement(month);
	    	ms1.setInt(1, smon);
	    	ms1.setInt(2, emon);
	    	ms1.setInt(3,eyear);
	    	mrec = ms1.executeQuery();
		 			
 ////////////////////////////Product Master Ki Query//////////////////////////////////
	        if (utype.equals("PMT"))
	        {
            query1 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? and " +
            " pd_group in (select gp_code from pmt_group where user_id="+uid+" and status='Y') order by pname";
	        }
	        else
	        {
            query1 = "Select pcode,pname,pack from "+tblnm+" where mkt_year=? order by pname";	        	
	        }
	    	ps1 = con.prepareStatement(query1); 
			ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();

			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	
	        	tmonqty=0.00;
	        	tmonval=0.00;
	        	ymonqty=0.00;
	        	ymonval=0.00;
	        	
	        	mrec.beforeFirst();
	        	while (mrec.next()) //////////// Month File Loop Starts//////////////
	        	{
	        		  if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
		 			      String wcode = txt5.substring(0, 3);
		 			    
		 			      if (!wcode.equals(mrec.getString(3)))
		 			       {	  
		 			           if (mrec.isLast())
			 			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			       }  
		 			      wname = mrec.getString(3);
 	                
//////////////////////////////// Stock  Master ki Query/////////////////////////////////////
		         
			     if (type==60)
			     {
			    	query3 = "Select sum(qty"+wname+"),sum(free"+wname+"),sum(val"+wname+"),sum(fval"+wname+") from "+tblnm1+" where  "+
			    	"  doc_type in (?,?,?) and item_code=? and depo_code=? and mkt_year=? order by doc_type,item_code,depo_code ";
			     }
			     else
			     {
			    	query3 = "Select qty"+wname+",free"+wname+",val"+wname+",fval"+wname+" from "+tblnm1+" where  "+
			        "  doc_type=? and item_code= ? and depo_code=?  and mkt_year=? order by doc_type,item_code,depo_code ";
			     }
 		         
 		        ps3 = con.prepareStatement(query3); 
			    if (type==60)
			    {
				  
  			      ps3.setInt(1,type);
  			      ps3.setInt(2,61);
  			      ps3.setInt(3,62);
				  ps3.setInt(4,rst1.getInt(1));
				  ps3.setInt(5,depo);
				  ps3.setInt(6,eyear);

			    }
			    else 
			    {
	 			    ps3.setInt(1,type);
				    ps3.setInt(2,rst1.getInt(1));
				    ps3.setInt(3,depo);
				    ps3.setInt(4,eyear);
			    }
			    
			    
			    
			    rst3 = ps3.executeQuery(); 
			        while (rst3.next())  /////// Stock Master Start/////////////////////////
			        {

                          if (type==80)
                          {
                        	tmonqty=rst3.getDouble(2);
                    	    tmonval=rst3.getDouble(4);
                        	ymonqty=ymonqty+rst3.getDouble(2);
                    	    ymonval=ymonval+rst3.getDouble(4);
                          }
                          else
                          {
                          	tmonqty=rst3.getDouble(1);
                      	    tmonval=rst3.getDouble(3);
                          	ymonqty=ymonqty+rst3.getDouble(1);
                      	    ymonval=ymonval+rst3.getDouble(3);
                          }
                        
			        }	 /////////////////// Stock Master while loop End ///////////////////////
			      
			        rst3.close();
			        ps3.close();
	        	}/// Month file Loop Ends here//////////////////////////			        
	        	  
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
		        if (ymonqty!=0)
		        {
					 rfb = new Inv2FormBean();
					 rfb.setCode(rst1.getInt(1));
					 rfb.setName(rst1.getString(2));
					 rfb.setPack(rst1.getString(3));
	                 rfb.setBranch_code(depo);
	                 rfb.setBranch_name(branch);
                     rfb.setTmonqty(tmonqty);
                     rfb.setTmonval(tmonval);
                     rfb.setYmonqty(ymonqty);
                     rfb.setYmonval(ymonval);
		             rfb.setHead1(txt2+txt5);
 		             
		             data.add(rfb); 				
			
		             gtmonval=gtmonval+tmonval;
		             gymonval=gymonval+ymonval;
		        } 
		             
			 }  ////////////// Product Master Loop End here/////////////////////////
			
			 rfb = new Inv2FormBean();
			 rfb.setName("GRAND TOTAL :");
             rfb.setTmonval(gtmonval);
             rfb.setYmonval(gymonval);
             data.add(rfb); 				
			
 			mrec.close();
 			ms1.close();
	        rst1.close();
	        ps1.close();

////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv2DAO.getSQLInv2DAO " + e); 
		}
		  finally 
		{
			try
				{
	           if(mrec != null){mrec.close();}
	           if(ms1 != null){ ms1.close();}
	           if(rst1 != null){ rst1.close();}
	           if(ps1 != null){ ps1.close();}
	           if(rst3 != null){ rst3.close();}
	           if(ps3 != null){ps3.close();}
	           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLInv2DAO.Connection.close "+e);
					}
		}
		return data;
	}
	
	
}
