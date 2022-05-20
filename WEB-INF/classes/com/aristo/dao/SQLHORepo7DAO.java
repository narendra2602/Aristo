package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo7FormBean;
import com.aristo.valueobject.HORepo8FormBean;

public class SQLHORepo7DAO {

///////////////////////////////////////Branch Coding Start Here/////////////////////////////////
	public List getAllBranch(Connection con, int code,int smon,int emon,String tp,String typ,int code1,int loginid,int eyear,int rtp) { 
		 
		HORepo7FormBean rfb;
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
		List<HORepo7FormBean> data = new ArrayList<HORepo7FormBean>();
		try {     
            String tblnm=null;
//            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
            String txt2=null;
            String txt5 =null;
            String terrec=null;
            String query3=null;
            double sqty=0.00;
        	double sval=0.00;
        	double slqty=0.00;
        	double slval=0.00;
        	double expqty=0.00;
        	double expval=0.00;
        	double netqty=0.00;
        	double netval=0.00;
            
            double gsqty=0.00;
        	double gsval=0.00;
        	double gslqty=0.00;
        	double gslval=0.00;
        	double gexpqty=0.00;
        	double gexpval=0.00;
        	double gnetqty=0.00;
        	double gnetval=0.00;
            
            if (smon>emon)
            	emon=smon;
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
  //      	tblnm1=tp+"_group_mkt08";
   	        tblnm2=(tp+"_branch08").toLowerCase();
            tblnm3="user_branch08";
        	tblnm4=(tp+"_groupsales08").toLowerCase();
   	        if (rtp==2)
            txt2=" Branch Wise Group Detail in (Lacs) for the Month of ";
   	        else
   	        txt2=" Branch Wise Group Detail for the Month of "; 
   	            
///////////////////////////////////Group Master///////////////////////////////////////////////
   	      if (typ.equals("PMT"))
   	      {
       	   terrec = "Select distinct(gp_code),gp_name from "+tblnm4.toLowerCase()+" where gp_code=? order by gp_code";
   	      }
   	      else
   	      {
//      	   terrec = "Select distinct(gp_code),gp_name from "+tblnm1+" where gp_code=? and mkt_year=? group by gp_code order by gp_code";
      	   terrec = "Select distinct(gp_code),gp_name from "+tblnm4+" where gp_code=?  order by gp_code";
      	  }
   	    	  
     	   ts1 = con.prepareStatement(terrec);
     	   ts1.setInt(1, code);
	      if (!typ.equals("PMT"))
   	      {
//     	   ts1.setInt(2,eyear);
   	      }
     	   trec = ts1.executeQuery();
     			if (trec.next())
                     txt1="Group-> "+trec.getString(2);			

     		trec.close();
			ts1.close();
		
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name from "+tblnm3+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
	        ps1 = con.prepareStatement(query22);
	        ps1.setInt(1,loginid); 
	        ps1.setString(2, "Y");
	        rst1 = ps1.executeQuery();
			
			while (rst1.next())  //////////////////Branch Master Loop Start//////////////////////// 
			{
				rfb = new HORepo7FormBean();
				rfb.setName(rst1.getString(2));
               		        
                    //////////////// Month File Loop Starts to accumulate data
  	                String month = "Select mnth_name,mnth_year,mnth_abbr from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
		 			ms1 = con.prepareStatement(month);
		 			ms1.setInt(1, smon);
		 			ms1.setInt(2, emon);
		 			ms1.setInt(3, eyear);
		 			mrec = ms1.executeQuery();
		 			sqty=0.00;
		        	sval=0.00;
		        	slqty=0.00;
		        	slval=0.00;
		        	expqty=0.00;
		        	expval=0.00;
		        	netqty=0.00;
		        	netval=0.00;
		        	
		 			while (mrec.next())    ////////////////////Month Loop Start////////////////////
		 			{	
		 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 			            if (mrec.isLast())
	 			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
 		       
	 			        unm="qty"+mrec.getString(3);
	 	            	vnm="val"+mrec.getString(3);
                    ///////////////////////HQ Detail Query///////////////////////////////////	
        	      if (typ.equals("PMT"))
          	      {
		        	query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
		            " where grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code"; 
          	      }
        	      else
          	      {
/* 		        	 query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
 		            " where mgrp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code"; 
*/
 		        	 query3 = "Select sum("+unm+"),sum(r"+unm+"),sum(e"+unm+"),sum(b"+unm+"),sum(s"+unm+"),sum(p"+unm+"),sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+
		            " where grp_cd=?  and depo_code=? and mkt_year=? group by depo_code order by depo_code"; 
          	      }
		        	
		        	ps3 = con.prepareStatement(query3); 
			        ps3.setInt(1,code);
			        ps3.setInt(2,rst1.getInt(1));
			        ps3.setInt(3,eyear);
			        rst3 = ps3.executeQuery(); 
			        if(rst3.next())           /////////////////HQ Detail Start/////////////////////
			        {
			        		 sqty = sqty+rst3.getInt(1);
			        		 slqty = slqty+rst3.getInt(2);
			        		 expqty = expqty+(rst3.getInt(3)+rst3.getInt(4)+rst3.getInt(5)+rst3.getInt(6));
			        		 
			        		 sval = sval+rst3.getDouble(7);
			        		 slval=slval+rst3.getDouble(8);
			        		 expval=expval+(rst3.getDouble(9)+rst3.getDouble(10)+rst3.getDouble(11)+rst3.getDouble(12));
			        		 
			         }	    /////////////////HQ Detail End/////////////////////

			        }      ////////////////////Month Loop End////////////////////
				     
		 			 if(rtp==2)
		 			 {
		 			  slval=slval/100000;	 
		 			  sval=sval/100000;
		 			  expval=expval/100000;		 			  
		 			 }
		 			 netqty = sqty-slqty-expqty;
		 			 netval = sval-slval-expval;
		 			 rfb.setSqty(sqty);
		 			 rfb.setSlqty(slqty);
		 			 rfb.setExpqty(expqty);
		 			 rfb.setSlval(slval);
		 			 rfb.setSval(sval);
		 			 rfb.setExpval(expval);
		 			 rfb.setNetqty(netqty);
		 			 rfb.setNetval(netval);
		 			
 	 				 rfb.setHead1(txt1+txt2+txt5);
		 			 
		 			 gsqty = gsqty +sqty;
 	 				 gslqty = gslqty+slqty;
	        		 gexpqty = gexpqty  + expqty;
	        		 gsval = gsval+ sval;
	        		 gslval = gslval + slval;
	        		 gexpval = gexpval + expval;
		 			 gnetqty = gsqty-gslqty-gexpqty;
		 			 gnetval = gsval-gslval-gexpval;
	                 data.add(rfb); 				
				} 
					 rfb = new HORepo7FormBean();
 					 rfb.setName("Total :");
		 			 rfb.setSqty(gsqty);
		 			 rfb.setSlqty(gslqty);
		 			 rfb.setExpqty(gexpqty);
		 			 rfb.setSlval(gslval);
		 			 rfb.setSval(gsval);
		 			 rfb.setExpval(gexpval);
		 			 rfb.setNetqty(gnetqty);
		 			 rfb.setNetval(gnetval);
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo7DAO.getAllBranch " + e); 
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
				System.out.println("-------------Exception in SQLHORepo6DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}
	
	
	
	public List getAllBranchPMT(Connection con, int code,int smon, int emon,String tp,String typ,int loginid,int code1,int eyear,int rs) { 
		 
		HORepo7FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        
		 
        String vnm=null;
        String enm=null;	
		List<HORepo7FormBean> data = new ArrayList<HORepo7FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
            String txt2=null;
            String txt5=null;
            String wtxt="";
            
            int index = emon+1;
            int fs=0;
            double lacs=0.00;
             
            double hval=0.00;
            
            double[] vval = new double[index];
            
            tblnm3="user_branch08";
            
        	tblnm=(tp+"_HQDetail08").toLowerCase();
        	tblnm1=(tp+"_group_mkt08").toLowerCase();
   	        tblnm2=(tp+"_branch08").toLowerCase();
   	        tblnm4=(tp+"_groupsales08").toLowerCase();
			  
			  
	            txt2=" Branch Wise Value Net Sales Trend  for the Month of "; 
			  
			 
			 if(rs==2)
				wtxt=" - (IN LACS) "; 
            
//////////////////////////////////Product Master/////////////////////////////////
           // String terrec = "Select gp_code,gp_name from "+tblnm1+" where gp_code=? and mkt_year=?";  
            String terrec = "Select distinct(gp_code),gp_name from "+tblnm4+" where gp_code=? order by gp_code";
			ts1 = con.prepareStatement(terrec);
			ts1.setInt(1, code);
			//ts1.setInt(2,eyear);
			trec = ts1.executeQuery();
			if (trec.next())
                txt1="Group-> "+trec.getString(1)+","+trec.getString(2);
		 
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
				rfb = new HORepo7FormBean();
				rfb.setName(rst1.getString(2));
				rfb.setNo_of_mr(rst1.getInt(3));
                fs=fs+rst1.getInt(3);
				rfb.setMon(index);
                 
                int k=0;  
                 
                hval=0.00;
                lacs=0.00;
                mrec.beforeFirst();
                while (mrec.next())   ////////////////////Month File Loop Start///////////////////
		 			{	
		 			    
		 			    	 rfb.setVhead(k, mrec.getString(3)+" VALUE");

		 			     if (mrec.isFirst())	
		 				   txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		 			    
 				 	     if (mrec.isLast())
		 			       txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
		 			    	
		 	                 
		 			        vnm="val"+mrec.getString(3);
		 			       
//////////////////////////////////HQ Detail Query///////////////////////////////////////    	
	    	 String query3 = "Select  sum("+vnm+"),sum(r"+vnm+"),sum(e"+vnm+"),sum(b"+vnm+"),sum(s"+vnm+"),sum(p"+vnm+") from "+tblnm+" where "+
	        "  grp_cd=? and depo_code=? and mkt_year=? group by depo_code order by depo_code";
        	ps3 = con.prepareStatement(query3); 
	        ps3.setInt(1,code);
	        ps3.setInt(2,rst1.getInt(1));
	        ps3.setInt(3,eyear);
	        rst3 = ps3.executeQuery();
			        
			        if(rst3.next())      //////////////////////////HQ Detail Start//////////////////
			        {
		 			     
		 			      
		 			    	lacs=rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6)); 
		 			    	if (rs==2)
		 			    	lacs=(rst3.getDouble(1)-(rst3.getDouble(2)+rst3.getDouble(3)+rst3.getDouble(4)+rst3.getDouble(5)+rst3.getDouble(6))/100000);
		 			    	
                            rfb.setVal1(k, lacs);
                            hval=hval+lacs;
                            vval[k]=vval[k]+lacs;
		 			        
			        }	 //////////////////////////HQ Detail End//////////////////
			        	 
                    k++; 
                    
                    rst3.close();
                    ps3.close();
                    
			        }  ////////////////End of Month loop////////////////////////
				     
	 			      
	 			       
			 			 rfb.setVhead(k, "TOTAL VALUE");
	 			    	 rfb.setVal1(k, hval);
	 			      	 

	 			     rfb.setHead1(txt1+txt2+txt5+wtxt);
	 				
	                 data.add(rfb); 				
				}         ////////////////////////Branch Loop End///////////////////////
			
			         mrec.close();
			         ms1.close();
			         ps1.close();
			         rst1.close();
			         ps1.close();
			         
			 /////////////////Total Ke Liye//////////////////////////////
					 rfb = new HORepo7FormBean();
 					 rfb.setName("TOTAL :");
 					 rfb.setNo_of_mr(fs);
 					 
 					 
 					 hval=0.00;
 					 int z;
 					 for (z=0; z<emon;z++)
	   				 {
 		 			      
 		 			    
 		   					rfb.setVal1(z, vval[z]);
		   					hval=hval+vval[z];
 		 			     
	   				 }
                    ////////////////////Grand Total Ke liye/////////////////
 					 
	 			      	 
			 			 rfb.setVhead(z, "TOTAL VALUE");
	 			    	 rfb.setVal1(z, hval);
	 			      	 
 					 
	                 data.add(rfb); 				
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLHORepo8DAO.getAllBranchPMT " + e); 
			e.printStackTrace();
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
		
///////////////////////////////////////Branch Coding End Here/////////////////////////////////
} 