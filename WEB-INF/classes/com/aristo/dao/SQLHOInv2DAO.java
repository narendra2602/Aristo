package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Inv2FormBean;

public class SQLHOInv2DAO {

	
	public List getHOInv2(Connection con,int type,int smon, int emon, String tp,String typ,int code1,int loginid,int eyear) { 
		 
		Inv2FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        
		List<Inv2FormBean> data = new ArrayList<Inv2FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String txt5=null;
            String txt2=null;
        	
        	double tmonqty=0.00;
        	double tmonval=0.00;
        	double ymonqty=0.00;
        	double ymonval=0.00;

        	double qtot=0.00;
        	double vtot=0.00;
        	double yqtot=0.00;
        	double yvtot=0.00;
        	
        	double gtmonval=0.00;
        	double gymonval=0.00;

        	if (smon>emon)
            	emon=smon;

        	tblnm3="user_branch08";
        	
        	tblnm=(tp+"_product08").toLowerCase();
        	tblnm1=(tp+"_stock08").toLowerCase();
        	tblnm2=(tp+"_branch08").toLowerCase();
                
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
	 		   
	 		String mqty="";
	 		String mfree="";
	 		String mval="";
	 		String mfval="";
	 		String cqty="";
	 		String cfree="";
	 		String cval="";
	 		String cfval="";
	 		   
	    	String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
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
	    	   cqty+="+s.qty"+mrec.getString(3);
	    	   cfree+="+s.free"+mrec.getString(3);
	    	   cval+="+s.val"+mrec.getString(3);
	    	   cfval+="+s.fval"+mrec.getString(3);
	    	   
	    	   mqty="s.qty"+mrec.getString(3);
	    	   mfree="s.free"+mrec.getString(3);
	    	   mval="s.val"+mrec.getString(3);
	    	   mfval="s.fval"+mrec.getString(3);
	    	  }
	    		
	    	  if (first)	
	    	  {
	    	   cqty="s.qty"+mrec.getString(3);
	    	   cfree="s.free"+mrec.getString(3);
	    	   cval="s.val"+mrec.getString(3);
	    	   cfval="s.fval"+mrec.getString(3);
	    	   
	    	   mqty="s.qty"+mrec.getString(3);
	    	   mfree="s.free"+mrec.getString(3);
	    	   mval="s.val"+mrec.getString(3);
	    	   mfval="s.fval"+mrec.getString(3);
	    	   flag=true;
	    	   first=false;
	    	  }
	    	  
	    	}
	
            mrec.close();
 			ms1.close();

////////////////////////////Multiple Query for Product/Stock/Branch//////////////////////////////////////////
					String query1=null;
				  if (type==60)
				  {
						 query1= "select p.pcode,p.pname,p.pack,"+mqty+","+mfree+","+mval+","+mfval+","+cqty+","+cfree+","+cval+","+cfval+"," +
	       	   	 	     "u.depo_code,t.ter_name from "+tblnm+" as p inner join "+tblnm1+" as s" +
	       			     " on p.pcode=s.item_code inner join "+tblnm2+" as t on s.depo_code=t.depo_code inner join "+tblnm3+" as u " +
	       			     " on u.depo_code=t.depo_code where u.user_id=? and u.status=? and s.doc_type in (?,?,?) and s.mkt_year=? and p.mkt_year=? order by p.pname,t.ter_name,s.doc_type";
				  }
				  else
				  {
					 query1= "select p.pcode,p.pname,p.pack,"+mqty+","+mfree+","+mval+","+mfval+","+cqty+","+cfree+","+cval+","+cfval+"," +
		       	   	 	     "u.depo_code,t.ter_name from "+tblnm+" as p inner join "+tblnm1+" as s" +
		       			     " on p.pcode=s.item_code inner join "+tblnm2+" as t on s.depo_code=t.depo_code inner join "+tblnm3+" as u " +
		       			     " on u.depo_code=t.depo_code where u.user_id=? and u.status=? and s.doc_type=? and s.mkt_year=? and p.mkt_year=? order by p.pname,t.ter_name,s.doc_type";
				  }
			ps1 = con.prepareStatement(query1); 
	        ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        
		    if (type==60)
		    {
		    	 ps1.setInt(3,type);
			     ps1.setInt(4,61);
			     ps1.setInt(5,62);
			     ps1.setInt(6,eyear);
			     ps1.setInt(7, eyear);
		    }
		    else 
		    {
		        ps1.setInt(3,type);
		        ps1.setInt(4,eyear);
		        ps1.setInt(5, eyear);
		    }
			rst1 = ps1.executeQuery();
			
            flag = false;
            first = false;
            boolean print=false;
            int pcode=0;
            String pname=null;
            String pack=null;
            int depo_code=0;
            String depo_name=null;


			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	

	                if (!first)
	                {
		        	 	  pcode=rst1.getInt(1);
		        	 	  pname=rst1.getString(2);
		        	 	  pack=rst1.getString(3);
		        	 	  depo_code=rst1.getInt(12);
		        	 	  depo_name = rst1.getString(13);
	                }
	                
					if (first)
		        	 {
		        	  if (pcode!=rst1.getInt(1))
		        	  {
		        		  flag=true;
		        		  print=true;
		        	  }
		        	  
		        		  if(depo_code!=rst1.getInt(12))
		        			  print=true;
		        		  
		        	  
		        	 }

				     if (print)
				     {
				        if (ymonqty!=0)
				        {
							rfb = new Inv2FormBean();
							rfb.setCode(pcode);
							rfb.setName(pname);
							rfb.setPack(pack);
			                rfb.setBranch_code(depo_code);
			                rfb.setBranch_name(depo_name);
			                rfb.setTmonqty(tmonqty);
			                rfb.setTmonval(tmonval);
			                rfb.setYmonqty(ymonqty);
			                rfb.setYmonval(ymonval);
				            rfb.setHead1(txt2+txt5);
				            data.add(rfb);
				            
				        	tmonqty=0.00;
				        	tmonval=0.00;
				        	ymonqty=0.00;
				        	ymonval=0.00;
				        }
				        	print=false;
					   	 	 depo_code=rst1.getInt(12);
						 	 depo_name = rst1.getString(13);
				     }
					
			   	   	 if (flag)
			    	 {
				        if (yvtot!=0)
				        {
						 rfb = new Inv2FormBean();
						 rfb.setCode(0);			 
						 rfb.setName("PRODUCT TOTAL :");
						 rfb.setTmonqty(qtot);
						 rfb.setTmonval(vtot);
						 rfb.setYmonqty(yqtot);
						 rfb.setYmonval(yvtot);
			        	 qtot=0.00;
			        	 vtot=0.00;
			        	 yqtot=0.00;
			        	 yvtot=0.00;
			             data.add(rfb); 				
				        }	  
			          	 flag=false;
				         pcode=rst1.getInt(1);
		        	 	 pname=rst1.getString(2);
		        	 	 pack=rst1.getString(3);
			          	 
			    	 }

		        	      first=true;
	        	
					 	                
/////////////////////// Stock  Master ki Query/////////////////////////////////////
		 			      
		 			      
	                          if (type==80)
	                          {
	                        	tmonqty=rst1.getDouble(5);
	                    	    tmonval=rst1.getDouble(7);
	                        	ymonqty=ymonqty+rst1.getDouble(9);
	                    	    ymonval=ymonval+rst1.getDouble(11);
	                    	    
	                    	    qtot=qtot+rst1.getDouble(5);
	                    	    vtot=vtot+rst1.getDouble(7);
	                    	    yqtot=yqtot+rst1.getDouble(9);
	                    	    yvtot=yvtot+rst1.getDouble(11);
	                    	    gtmonval=gtmonval+rst1.getDouble(7);
	                    	    gymonval=gymonval+rst1.getDouble(11);
	                    	    
	                          }
	                          else
	                          {
	 		                     if ((type==60) || (type==61) || (type==62))
			                     {
		                          	tmonqty+=rst1.getDouble(4);
		                      	    tmonval+=rst1.getDouble(6);
		                          	ymonqty=ymonqty+rst1.getDouble(8);
		                      	    ymonval=ymonval+rst1.getDouble(10);
		                      	    
		                    	    qtot=qtot+rst1.getDouble(4);
		                    	    vtot=vtot+rst1.getDouble(6);
		                    	    yqtot=yqtot+rst1.getDouble(8);
		                    	    yvtot=yvtot+rst1.getDouble(10);
		                    	    gtmonval=gtmonval+rst1.getDouble(6);
		                    	    gymonval=gymonval+rst1.getDouble(10);
			               	    
			                     }
	 		                     else
	 		                     {
		                          	tmonqty=rst1.getDouble(4);
		                      	    tmonval=rst1.getDouble(6);
		                          	ymonqty=ymonqty+rst1.getDouble(8);
		                      	    ymonval=ymonval+rst1.getDouble(10);
		                      	    
		                    	    qtot=qtot+rst1.getDouble(4);
		                    	    vtot=vtot+rst1.getDouble(6);
		                    	    yqtot=yqtot+rst1.getDouble(8);
		                    	    yvtot=yvtot+rst1.getDouble(10);
		                    	    gtmonval=gtmonval+rst1.getDouble(6);
		                    	    gymonval=gymonval+rst1.getDouble(10);
		                    	    print=true;
	 		                     }
	                          }
  
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
			        				        		            
		             
			 }  ////////////// Product Master Loop End here/////////////////////////
			
			 rfb = new Inv2FormBean();
			 rfb.setCode(2);
			 rfb.setName("GRAND TOTAL :");
             rfb.setTmonval(gtmonval);
             rfb.setYmonval(gymonval);
             data.add(rfb); 				
			
	        rst1.close();
	        ps1.close();

////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLHOInv2DAO.getSQLHOInv2DAO " + e); 
		}
		  finally 
		{
			try
				{
		           if(mrec != null){mrec.close();}
		           if(ms1 != null){ ms1.close();}
		           if(rst1 != null){ rst1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLHOInv2DAO.Connection.close "+e);
					}
		}
		return data;
	}
	
	
	
	
	
	
	
	
}
