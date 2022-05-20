package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class SQLForm3DAO {

	public List getForm3(Connection con, int smon, int emon,int eyear,int depo_code,String tp) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps1=null;   
        ResultSet rst1=null;
        PreparedStatement ms1=null;
		ResultSet mrec=null;
        
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm3=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;
            String txt6=null;
            double montar=0.00;
            double cumtar=0.00;
            double monsal=0.00;
            double cumsal=0.00;
            double lstsal=0.00;
            double monach=0.00;
            double cumach=0.00;
            double cumgth=0.00;
            double pmr=0.00;
            double tarper=0.00;
            double surp=0.00;
            
            double gmontar=0.00;
            double gcumtar=0.00;
            double gmonsal=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;
            int gtrep=0;
            double gsurp=0.00;

            double rmontar=0.00;
            double rcumtar=0.00;
            double rmonsal=0.00;
            double rcumsal=0.00;
            double rlstsal=0.00;
            double rmonach=0.00;
            double rcumach=0.00;
            double rcumgth=0.00;
            double rpmr=0.00;
            int rtrep=0;
            double rsurp=0.00;
            
            double amontar=0.00;
            double acumtar=0.00;
            double amonsal=0.00;
            double acumsal=0.00;
            double alstsal=0.00;
            double amonach=0.00;
            double acumach=0.00;
            double acumgth=0.00;
            double apmr=0.00;
            int atrep=0;
            double asurp=0.00;
            if (smon>emon)
            	emon=smon;

   	 	    tblnm=(tp+"_repfinal").toLowerCase();
 	        tblnm3=(tp+"_tr_pmr").toLowerCase();

            txt2="     ACHIEVEMENT UPTO  ";
            
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
           	                       			

//////////////////Month File Loop Starts to accumulate data/////////////////////////
        String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where  mnth_ord<=? and mkt_year=? order by mnth_ord";  
		ms1 = con.prepareStatement(month);
		ms1.setInt(1,emon);
		ms1.setInt(2,eyear);
		mrec = ms1.executeQuery();
		mrec.beforeFirst();
		
			while (mrec.next()) // Month file Loop Starts/////////////////////////
			{	
			    if (mrec.isFirst())
			    {	
			    	  txt5 = mrec.getString(1)+" "+mrec.getInt(2);
		          txt4 = mrec.getString(3)+" "+mrec.getInt(2);
		          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
			    }  
		        if (mrec.isLast())
		           {   
			          txt5 = txt5 + " To " +mrec.getString(1)+" "+mrec.getInt(2);
			          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
			          txt4 = txt4+ " To "+mrec.getString(3)+" "+mrec.getInt(2);
			       }  
			  
			}  // Month file Loop End here///////////////////////// 
			
			mrec.close();
			ms1.close();
		
			
////////////////////////////Headquater Master Ki Query//////////////////////////////////
			String query1 = "SELECT c.ar_cd,c.area_name,c.rg_cd,c.reg_name,c.tr_cd,c.ter_name,C.MTAR,C.MSAL,C.CTAR,C.CSAL,C.CLYS,D.REP "+
			" FROM "+
			" (select a.ar_cd,a.area_name,a.rg_cd,a.reg_name,a.tr_cd,a.ter_name,a.tarval mtar,a.salval msal,b.ctarval ctar,b.csalval csal,b.clysval clys "+
			" from( "+
			" select ar_cd,area_name,rg_cd,reg_name,tr_cd,ter_name,mcode,sum(lysval) lysval, sum(tarval) tarval,sum(salval) salval from "+tblnm+"  "+ 
			" where mkt_year = ?  and  mnth_code = ?  "+
			" and depo_CODE = ? "+
			" group by ar_cd,area_name,rg_cd,reg_name,tr_cd,ter_name) as a "+
			" left join "+
			" (select ar_cd,area_name,rg_cd,reg_name,tr_cd,ter_name,sum(lysval) clysval,sum(tarval) ctarval,sum(salval) csalval  "+
			" from "+tblnm+" where mkt_year = ? and  mnth_code <= ?   "+
			" and depo_CODE =? "+
			" group by ar_cd,area_name,rg_cd,reg_name,tr_cd,ter_name) as b "+
			" on a.tr_cd = b.tr_cd) C, "+
			" (SELECT ter_code,SUM(REP) REP FROM "+tblnm3+" "+
			" WHERE MKT_YEAR = ?  AND MNTH_CODE <= ? and depo_code=? "+
			" GROUP BY ter_code) D "+
			" WHERE  C.tr_cd = D.ter_code order by c.ar_cd,c.rg_cd,c.tr_cd";
			
			
			ps1 = con.prepareStatement(query1);
		    ps1.setInt(1,eyear);
		    ps1.setInt(2,emon);
		    ps1.setInt(3,depo_code);
		    ps1.setInt(4,eyear);
		    ps1.setInt(5,emon);
		    ps1.setInt(6,depo_code);
		    ps1.setInt(7,eyear);
		    ps1.setInt(8,emon);
		    ps1.setInt(9,depo_code);

		    rst1 = ps1.executeQuery();

			int trep=0;
    	    boolean hprint=false;
    	    boolean first=true;
    	    int ar=0;
    	    int rg=0;
    	    String rgnm=null;
    	    String arnm=null;
			
			while (rst1.next())  // HQ Master Loop Starts/////////////////////////   
			{	
				
				hprint=true;

				if (first)
				{
					ar=rst1.getInt(1);
					rg=rst1.getInt(3);
					arnm=rst1.getString(2);
					rgnm=rst1.getString(4);
					first=false;
				}
                
				
				if (rg!=rst1.getInt(3))
				{
					rfb = new MktFormBean();
					rfb.setMcode(2);
					rfb.setMname("TOTAL: "+rgnm);
		              
					if (rmontar!=0)
		            	rmonach = (rmonsal/rmontar)*100;
		            if (rcumtar!=0)
		            	rcumach = (rcumsal/rcumtar)*100;
		            if (rlstsal!=0)
		            	rcumgth = ((rcumsal/rlstsal)*100)-100;
		            if (rtrep!=0)
		              rpmr = rcumsal/rtrep;
		
		             rfb.setQty2((int)(rmontar+.50));
		             rfb.setQty3((int)(rmonsal+.50));
		             rfb.setDval4(rmonach);
		             rfb.setQty5((int)(rcumtar+.50));
		             rfb.setQty6((int)(rcumsal+.50));
		             rfb.setQty7((int)(rlstsal+.50));
		             rfb.setDval8(rcumach);
		             rfb.setDval9(rcumgth);
		             rfb.setDval10(rpmr);
		             rfb.setQty8((int)(rsurp+.50));
		             

		             data.add(rfb); 			
	 				 rg=rst1.getInt(3);
					 rgnm=rst1.getString(4);
		             rmontar= 0;
		             rcumtar= 0;
		             rmonsal= 0;
		             rcumsal= 0;
		             rlstsal= 0;
		             rtrep = 0;
	 				// hprint=false;
				}
				
				
				if (ar!=rst1.getInt(1))
				{
					rfb = new MktFormBean();
					rfb.setMcode(3);
					rfb.setMname("TOTAL: "+arnm);
		              
					if (amontar!=0)
		            	amonach = (amonsal/amontar)*100;
		            if (acumtar!=0)
		            	acumach = (acumsal/acumtar)*100;
		            if (alstsal!=0)
		            	acumgth = ((acumsal/alstsal)*100)-100;
		            if (atrep!=0)
		              apmr = acumsal/atrep;
		
		             rfb.setQty2((int)(amontar+.50));
		             rfb.setQty3((int)(amonsal+.50));
		             rfb.setDval4(amonach);
		             rfb.setQty5((int)(acumtar+.50));
		             rfb.setQty6((int)(acumsal+.50));
		             rfb.setQty7((int)(alstsal+.50));
		             rfb.setDval8(acumach);
		             rfb.setDval9(acumgth);
		             rfb.setDval10(apmr);
		             rfb.setQty8((int)(asurp+.50));
		             
		             data.add(rfb); 			
		             ar=rst1.getInt(1);
 					 arnm=rst1.getString(2);
		             amontar= 0;
		             acumtar= 0;
		             amonsal= 0;
		             acumsal= 0;
		             alstsal= 0;
		             atrep = 0;
		            // hprint=false;
				}
				
		 			           
	 			montar=0.00;
	 			monsal=0.00;
	 			monach=0.00;
	 			cumtar=0.00;
	 			cumsal=0.00;
	 			lstsal=0.00;
	 			cumach=0.00;
	 			cumgth=0.00;
	 			pmr=0.00;

				            montar = rst1.getDouble(7);
				            monsal = rst1.getDouble(8);
				            
				            cumtar = cumtar+rst1.getDouble(9);
				            cumsal = cumsal+rst1.getDouble(10);
				            lstsal = lstsal+rst1.getDouble(11);
				            trep=rst1.getInt(12);
			        				        
		 			


		            if (montar!=0)
		            	monach = (monsal/montar)*100;
		            if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		              pmr = cumsal/trep;
	        	    
		            surp=cumsal-cumtar;
		            
		          if (hprint)
		          {
					 rfb = new MktFormBean();
					 rfb.setMcode(1);
					 rfb.setMname(rst1.getString(6));
		             rfb.setDval1(tarper);
		             rfb.setQty2((int)(montar+.50));
		             rfb.setQty3((int)(monsal+.50));
		             rfb.setDval4(monach);
		             rfb.setQty5((int)(cumtar+.50));
		             rfb.setQty6((int)(cumsal+.50));
		             rfb.setQty7((int)(lstsal+.50));
		             rfb.setDval8(cumach);
		             rfb.setDval9(cumgth);
		             rfb.setDval10(pmr);
		             rfb.setQty8((int)(surp+.50));
		             
		             rfb.setNm2(txt3);
		             rfb.setNm3(txt2+txt3);
		             rfb.setNm4(txt4);
		             rfb.setLupdate(txt6);
		             data.add(rfb);
		          }
		        
		             gmontar= gmontar +montar;
		             gcumtar= gcumtar +cumtar;
		             gmonsal= gmonsal + monsal;
		             gcumsal= gcumsal +cumsal;
		             glstsal= glstsal +lstsal;
		             gtrep = gtrep+trep;
		             gsurp=gcumsal-gcumtar;
		             
		             amontar= amontar +montar;
		             acumtar= acumtar +cumtar;
		             amonsal= amonsal +monsal;
		             acumsal= acumsal +cumsal;
		             alstsal= alstsal +lstsal;
		             atrep = atrep+trep;
		             asurp=acumsal-acumtar;

		             rmontar= rmontar +montar;
		             rcumtar= rcumtar +cumtar;
		             rmonsal= rmonsal +monsal;
		             rcumsal= rcumsal +cumsal;
		             rlstsal= rlstsal +lstsal;
		             rtrep = rtrep+trep;
		             rsurp=rcumsal-rcumtar;
		             
				
			 }  ////////////// HQ Master Loop End here/////////////////////////
			
					// Region Total Print
					rfb = new MktFormBean();
					rfb.setMcode(2);
					rfb.setMname("TOTAL: "+rgnm);
		              
					if (rmontar!=0)
		            	rmonach = (rmonsal/rmontar)*100;
		            if (rcumtar!=0)
		            	rcumach = (rcumsal/rcumtar)*100;
		            if (rlstsal!=0)
		            	rcumgth = ((rcumsal/rlstsal)*100)-100;
		            if (rtrep!=0)
		              rpmr = rcumsal/rtrep;
		
		             rfb.setQty2((int)(rmontar+.50));
		             rfb.setQty3((int)(rmonsal+.50));
		             rfb.setDval4(rmonach);
		             rfb.setQty5((int)(rcumtar+.50));
		             rfb.setQty6((int)(rcumsal+.50));
		             rfb.setQty7((int)(rlstsal+.50));
		             rfb.setDval8(rcumach);
		             rfb.setDval9(rcumgth);
		             rfb.setDval10(rpmr);
		             rfb.setQty8((int)(rsurp+.50));
		             
		
		             data.add(rfb); 			
			

		            // Area Total Print 
					rfb = new MktFormBean();
					rfb.setMcode(3);
					rfb.setMname("TOTAL: "+arnm);
		              
					if (amontar!=0)
		            	amonach = (amonsal/amontar)*100;
		            if (acumtar!=0)
		            	acumach = (acumsal/acumtar)*100;
		            if (alstsal!=0)
		            	acumgth = ((acumsal/alstsal)*100)-100;
		            if (atrep!=0)
		              apmr = acumsal/atrep;
		
		             rfb.setQty2((int)(amontar+.50));
		             rfb.setQty3((int)(amonsal+.50));
		             rfb.setDval4(amonach);
		             rfb.setQty5((int)(acumtar+.50));
		             rfb.setQty6((int)(acumsal+.50));
		             rfb.setQty7((int)(alstsal+.50));
		             rfb.setDval8(acumach);
		             rfb.setDval9(acumgth);
		             rfb.setDval10(apmr);
		             rfb.setQty8((int)(asurp+.50));
		             
		             data.add(rfb); 			
		             
		             
				            
					 rfb = new MktFormBean();
					 rfb.setMcode(4);
 					 rfb.setMname("GRAND TOTAL :");

 					if (gmontar!=0)
		            	gmonach = (gmonsal/gmontar)*100;
		            if (gcumtar!=0)
		            	gcumach = (gcumsal/gcumtar)*100;
		            if (glstsal!=0)
		            	gcumgth = ((gcumsal/glstsal)*100)-100;
		            if (gtrep!=0)
		              gpmr = gcumsal/gtrep;

		             rfb.setQty2((int)(gmontar+.50));
		             rfb.setQty3((int)(gmonsal+.50));
		             rfb.setDval4(gmonach);
		             rfb.setQty5((int)(gcumtar+.50));
		             rfb.setQty6((int)(gcumsal+.50));
		             rfb.setQty7((int)(glstsal+.50));
		             rfb.setDval8(gcumach);
		             rfb.setDval9(gcumgth);
		             rfb.setDval10(gpmr);
		             rfb.setQty8((int)(gsurp+.50));
		            
		            data.add(rfb); 				
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLForm1DAO.getForm3 " + e); 
		}
		  finally 
		{
			try
				{
	             if(rst12 != null){ rst12.close();}
	             if(ps12 != null){ ps12.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
	             if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLForm1DAO.Connection.close "+e);
					}
		}
		return data;
	}
} 