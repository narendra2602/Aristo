package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HORepo5FormBean;


public class SQLHORepo5DAO {

///////////////////////////////// Branch Coding Start Here/////////////////////////////////
	public List getAllBranch(Connection con, int smon,int emon,int rs,int depo_code,String tp,String typ,int code1, int loginid,int eyear) { 
	  	 
		HORepo5FormBean rfb;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps2=null; 
        ResultSet rst2=null;		
        
 		
		List<HORepo5FormBean> data = new ArrayList<HORepo5FormBean>();
		try {     
            String tblnm=null;
            String txt1=null;
            String txt5 =null;
            String wtxt ="";
            
            double budget=0.00;
            double gross=0.00;
            double credit=0.00;
            double net =0.00;
            double ach=0.00;
            double surp = 0.00;
            
            double gbudget=0.00;
            double ggross=0.00;
            double gcredit=0.00;
            double gnet =0.00;
            double gach=0.00;
            double gsurp = 0.00;
           
            
            if (smon>emon)
            	emon=smon;

/*            tblnm3="user_branch08";	
            
        	tblnm=tp+"_HQDetail08";
        	tblnm1=tp+"_target08";
        	tblnm2=tp+"_branch08";
*/        	tblnm=(tp+"_repfinal").toLowerCase();

            if(rs==2)
            wtxt= " - (IN LACS)";
            
            txt1="BRANCH WISE/GROSS/CREDIT/NET SALE FOR THE MONTH OF ";       	    
	        
/////////////////////////////////// Month File Loop Starts to accumulate data///////////////////////
            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
            while (mrec.next())
 			{	
	 			    if (mrec.isFirst())	
		 				  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
	 			    
				 	if (mrec.isLast())
	 			      txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
	 	   } 
			mrec.close();
			ms1.close();
 			
//////////////////////////////User Branch Master ki Query/////////////////////////////////
	             String query2 = "select depo_code,br_name, sum(tarval) tarval,sum(grval) grval," +
	  	             " sum(cnval) cnval,sum(salval) salval from "+tblnm+ 
	  	             " where mkt_year = ?  and mnth_code >= ? and  mnth_code <= ? " +
	  	             " and depo_code in (select depo_code from user_branch08 where user_id=?)  " +
	  	             "  group by depo_code ";
//            String query22 = "Select d.depo_code,t.ter_name from "+tblnm3+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? ";
	  	            ps2 = con.prepareStatement(query2);
 	  			    ps2.setInt(1,eyear);
 	  	  			ps2.setInt(2,smon);
 	  		 		ps2.setInt(3,emon);
 	  		 		ps2.setInt(4, loginid);
 	  			    rst2 = ps2.executeQuery();


	            while (rst2.next())/////////////// Branch Master Loop Start///////////////////
				{
	            	rfb = new HORepo5FormBean();
	            	rfb.setName(rst2.getString(2)); 
                    budget=0.00;
                    gross=0.00;
                    credit=0.00;
                    net=0.00;
                    ach=0.00;
                    surp=0.00;
                    

///////////////////////////////////////Target Master Query/////////////////////////////////////		 	           
		    	 budget =  rst2.getDouble(3);
		    	 budget = (Math.round(budget*100.0)/100.0);
/////////////////////////////////////HQ Detail Query//////////////////////////////////				     
			        	     gross = rst2.getDouble(4);
			        		 gross = (Math.round(gross*100.0)/100.0);
			        		 credit = rst2.getDouble(5);
			        		 credit = (Math.round(credit*100.0)/100.0);
			        		 net = gross-credit;
			        	     surp =  net-budget;
		        		 	 if (budget!=0)
			        	     ach=(net/budget)*100.00;
		        		 	 
                    
	        		 	 
	                     gbudget = gbudget+budget; 	 
	                     ggross = ggross+gross;
	        		 	 gcredit = gcredit+credit;
	        		 	 gnet = gnet+net;
	        		 	 if (gbudget!=0)
		        		 	 gach = (gnet/gbudget)*100.00;
	        		 	 gsurp = gsurp+surp;
	        		 	 
	        		 	 if(rs==1)
	        		 	 {
		        		 rfb.setBudget(budget);
		        		 rfb.setGross(gross);
		        		 rfb.setCredit(credit);
		        		 rfb.setNet(net);
		        		 rfb.setAch(ach);
		        		 rfb.setSurp(surp);
	        		 	 }
	        		 	 else
	        		 	 {
		        		 rfb.setBudget(budget/100000);
		        		 rfb.setGross(gross/100000);
		        		 rfb.setCredit(credit/100000);
		        		 rfb.setNet(net/100000);
		        		 rfb.setAch(ach);
		        		 rfb.setSurp(surp/100000);
	        		 	 }

		        		 rfb.setHead1(txt1+txt5+wtxt);
		        		  
 		                 data.add(rfb); 				
 				} ////////////////BRANCH Master Loop End//////////////////

	            	 rst2.close();
	            	 ps2.close();
	            	 
////////////////////////////////////////////////////////////////////////////////
	            	 
		             rfb = new HORepo5FormBean();
	            	 rfb.setName("TOTAL : ");
	            	 if (rs==1)
	            	 {
         		     rfb.setBudget(gbudget);
	        		 rfb.setGross(ggross);
	        		 rfb.setCredit(gcredit);
	        		 rfb.setNet(gnet);
	        		 rfb.setAch(gach);
	        		 rfb.setSurp(gsurp);
	            	 }
	            	 else
	            	 {
         		     rfb.setBudget(gbudget/100000);
	        		 rfb.setGross(ggross/100000);
	        		 rfb.setCredit(gcredit/100000);
	        		 rfb.setNet(gnet/100000);
	        		 rfb.setAch(gach);
	        		 rfb.setSurp(gsurp/100000);
	            	 }
	        		data.add(rfb); 				
	        		
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLHORepo5DAO.getAllBranch " + e);
		}

		finally 
		{
		try 
		{
           if(mrec != null){mrec.close();}
           if(ms1 != null){ ms1.close();}
           if(rst2 != null){ rst2.close();}
           if(ps2 != null){ ps2.close();}
           if(con != null){con.close();}
		}
		
		catch (SQLException e) {
				System.out.println("--Exception in SQLRepo5DAO.Connection.close "+e);
			  }
		}		
		return data;
	}
///////////////////////////////// Headquater Coding End Here/////////////////////////////////

	

	///////////////////////////////// Branch Coding Start Here/////////////////////////////////
	public List getNewBranch(Connection con, int grp_code,int smon,int emon,int eyear,int depo_code,int div_code, int loginid,int utype) { 
		HORepo5FormBean rfb;
		CallableStatement cs=null;
		ResultSet rst2=null;	
        PreparedStatement ps1=null;
        ResultSet rs1=null;



		List<HORepo5FormBean> data = new ArrayList<HORepo5FormBean>();
		try {     
			String txt1=null;
			String txt5 =null;
			String wtxt ="";

			double budget=0.00;
			double gross=0.00;
			double credit=0.00;
			double net =0.00;
			double ach=0.00;
			double surp = 0.00;
			double pi = 0.00;

			double gbudget=0.00;
			double ggross=0.00;
			double gcredit=0.00;
			double gnet =0.00;
			double gach=0.00;
			double gsurp = 0.00;
			double gpi = 0.00;


			if (smon>emon)
				emon=smon;


			String procedureWithParameters="{call web_report_24(?,?,?,?,?,?,?,?)}";


			 if(depo_code>0 && utype==4)
				 utype=41;
			 else if(depo_code>0)
				 utype=1;

			 
			 String branchname = "Select depo_name from branch_comp where depo_code=? ";
			 ps1=con.prepareStatement(branchname);
			 ps1.setInt(1, depo_code);
			 rs1=ps1.executeQuery();
			 if(rs1.next())
			 {
				 txt1=rs1.getString(1)+ " Branch:  GROSS/CREDIT/NET SALE FROM ";

			 }
			 else
				 txt1="All India:  GROSS/CREDIT/NET SALE FROM ";

			 rs1.close();
			 ps1.close();

			 
			 //////////////////////////////User Branch Master ki Query/////////////////////////////////
			 cs = con.prepareCall(procedureWithParameters);
			 cs.setInt(1,eyear);
			 cs.setInt(2,div_code);
			 cs.setInt(3,depo_code);
			 cs.setInt(4, smon);
			 cs.setInt(5, emon);
			 cs.setInt(6, utype);
			 cs.setInt(7, loginid);
			 cs.setInt(8, 0);
			 rst2 = cs.executeQuery();

			 boolean first=true;
			 while (rst2.next())/////////////// Branch Master Loop Start///////////////////
			 {
				 if(first)
				 {
					 wtxt=rst2.getString(10)+" TO "+rst2.getString(11);
				 }
				 rfb = new HORepo5FormBean();
				 rfb.setName(rst2.getString(5)); 
				 budget=0.00;
				 gross=0.00;
				 credit=0.00;
				 net=0.00;
				 ach=0.00;
				 surp=0.00;
				 pi=0.00;

				  

				 ///////////////////////////////////////Target Master Query/////////////////////////////////////		 	           
				 budget =  rst2.getDouble(7);
				 /////////////////////////////////////HQ Detail Query//////////////////////////////////				     
				 gross = rst2.getDouble(6);
				 credit = rst2.getDouble(13);
				 pi = rst2.getDouble(14);
				 net = rst2.getDouble(6)-rst2.getDouble(13);
				 surp =  net-rst2.getDouble(7);
				 if (rst2.getDouble(7)!=0)
					 ach=(net/rst2.getDouble(7))*100.00;



				 gbudget = gbudget+budget; 	 
				 ggross = ggross+gross;
				 gcredit = gcredit+credit;
				 gpi = gpi+pi;
				 gnet = gnet+net;
				 if (gbudget!=0)
					 gach = (gnet/gbudget)*100.00;
				 gsurp = gsurp+surp;

				 rfb.setBudget(budget);
				 rfb.setGross(gross);
				 rfb.setCredit(credit);
				 rfb.setNet(net);
				 rfb.setAch(ach);
				 rfb.setSurp(surp);
				 rfb.setPi(pi);
				 rfb.setHead1(txt1+wtxt);

				 data.add(rfb); 				
			 } ////////////////BRANCH Master Loop End//////////////////

			 rst2.close();
			 cs.close();

			 ////////////////////////////////////////////////////////////////////////////////

			 rfb = new HORepo5FormBean();
			 rfb.setName("TOTAL : ");
			 rfb.setBudget(gbudget);
			 rfb.setGross(ggross);
			 rfb.setCredit(gcredit);
			 rfb.setNet(gnet);
			 rfb.setAch(gach);
			 rfb.setSurp(gsurp);
			 rfb.setPi(gpi);
			 data.add(rfb); 				

		} catch (Exception e) {

			System.out.println("========Exception in SQLHORepo5DAO.getNewBranch " + e);
		}

		finally 
		{
			try 
			{
				if(rst2 != null){ rst2.close();}
				if(cs != null){ cs.close();}
				if(rs1 != null){ rs1.close();}
				if(ps1 != null){ ps1.close();}
				if(con != null){con.close();}
			}

			catch (SQLException e) {
				System.out.println("--Exception in SQLHORepo5DAO.Connection.close "+e);
			}
		}		
		return data;
	}
	///////////////////////////////// Headquater Coding End Here/////////////////////////////////




}
