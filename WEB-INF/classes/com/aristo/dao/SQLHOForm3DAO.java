package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HOForm3FormBean;

public class SQLHOForm3DAO {

	
	public List getAllBranch(Connection con, int smon, int emon, String tp,String typ,int code,int loginid,int eyear) { 
		 
		HOForm3FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;

		List<HOForm3FormBean> data = new ArrayList<HOForm3FormBean>();
		try {     
            String tblnm=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;
            String txt5 =null;

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

 
            if (smon>emon)
            	emon=smon;

   	        tblnm4="user_branch08";
        	tblnm=(tp+"_repfinal").toLowerCase();
   	        tblnm3=(tp+"_br_pmr").toLowerCase();
            

            txt2="     ACHIEVEMENT FROM  "; 
//            txt2="     ACHIEVEMENT UPTO  "; 
                
//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
    		ms1 = con.prepareStatement(month);
    		ms1.setInt(1,smon);
    		ms1.setInt(2,emon);
    		ms1.setInt(3,eyear);
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
 			}     
			
 			mrec.close();
 			ms1.close();

	        gmontar=0.00;
            gcumtar=0.00;
            gmonsal=0.00;
            gcumsal=0.00;
            glstsal=0.00;
            gmonach=0.00;
            gcumach=0.00;
            gcumgth=0.00;
            gpmr=0.00;
            gtrep=0;
            gsurp=0.00;

			String query1 = "SELECT C.DEPO_CODE,C.BR_NAME,C.MTAR,C.MSAL,C.CTAR,C.CSAL,C.CLYS,D.REP "+
			" FROM "+
			" (select a.depo_code,a.br_name,a.tarval mtar,a.salval msal,b.ctarval ctar,b.csalval csal,b.clysval clys "+
			" from( "+
			" select depo_code,br_name,mcode,sum(lysval) lysval, sum(tarval) tarval,sum(salval) salval from "+tblnm+"  "+ 
			" where mkt_year = ?   and  mnth_code = ?    "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,br_name) as a "+
			" left join "+
			" (select depo_code,br_name,mcode,sum(lysval) clysval,sum(tarval) ctarval,sum(salval) csalval  "+
			" from "+tblnm+" where mkt_year = ? and  mnth_code >= ? and mnth_code <= ?   "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,br_name) as b "+
			" on a.depo_code = b.depo_code) C, "+
			" (SELECT MKT_YEAR,DEPO_CODE,SUM(REP) REP FROM "+tblnm3+" "+
			" WHERE MKT_YEAR = ?  and mnth_code >= ? AND MNTH_CODE <= ? "+
			" GROUP BY MKT_YEAR,DEPO_CODE) D "+
			" WHERE  C.DEPO_CODE = D.DEPO_CODE "	;
			
			ps1 = con.prepareStatement(query1);
		    ps1.setInt(1,eyear);
		    ps1.setInt(2,emon);
		    ps1.setInt(3,loginid);
		    ps1.setInt(4,eyear);
		    ps1.setInt(5,smon);
		    ps1.setInt(6,emon);
		    ps1.setInt(7,loginid);
		    ps1.setInt(8,eyear);
		    ps1.setInt(9,smon);
		    ps1.setInt(10,emon);

			rst1 = ps1.executeQuery();
 			int trep=0;
            
            
			while (rst1.next())  // Branch Master Loop Starts/////////////////////////   
			{	
				rfb = new HOForm3FormBean();
				rfb.setName(rst1.getString(2));
			 	montar=0.00;
			 	monsal=0.00;
			 	monach=0.00;
			 	cumtar=0.00;
			 	cumsal=0.00;
			 	lstsal=0.00;
			 	cumach=0.00;
			 	cumgth=0.00;
			 	pmr=0.00;
			 	surp=0.00;
	            trep=rst1.getInt(8);  			
				
	 			 
	        	montar = rst1.getDouble(3);
	            monsal = rst1.getDouble(4);
	         
	            cumtar = rst1.getDouble(5);
	            cumsal = rst1.getDouble(6);
	            lstsal = rst1.getDouble(7);

		 	
		            if (montar!=0)
		            	monach = (monsal/montar)*100;
		            if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		              pmr = cumsal/trep;
	        	    
		            surp=cumsal-cumtar;
		            
					 rfb.setPer(tarper);
		             rfb.setMtarget((int)(montar+.50));
		             rfb.setMsale((int)(monsal+.50));
		             rfb.setMach(monach);
		             rfb.setCumtarget((int)(cumtar+.50));
		             rfb.setCumsale((int)(cumsal+.50));
		             rfb.setLyear((int)(lstsal+.50));
		             rfb.setCumach(cumach);
		             rfb.setCumgth(cumgth);
		             rfb.setPmr(pmr);
		             rfb.setSurdeff((int)(surp+.50));
		             
		             rfb.setNm2(txt3);
		             rfb.setHead1(txt2+txt3);
		             rfb.setNm4(txt4);
		        
		             gmontar= gmontar +montar;
		             gcumtar= gcumtar +cumtar;
		             gmonsal= gmonsal + monsal;
		             gcumsal= gcumsal +cumsal;
		             glstsal= glstsal +lstsal;
		             gtrep = gtrep+trep;
		             gsurp=gcumsal-gcumtar;
		             
 		             
		             data.add(rfb); 				
				
			 }  ////////////// Branch Master Loop End here/////////////////////////
			
				             
                  rst1.close();
                  ps1.close();
                  
					 rfb = new HOForm3FormBean();
					 rfb.setName("GRAND TOTAL :");

 					if (gmontar!=0)
		            	gmonach = (gmonsal/gmontar)*100;
		            if (gcumtar!=0)
		            	gcumach = (gcumsal/gcumtar)*100;
		            if (glstsal!=0)
		            	gcumgth = ((gcumsal/glstsal)*100)-100;
		            if (gtrep!=0)
		              gpmr = gcumsal/gtrep;

		             rfb.setMtarget((long)(gmontar+.50));
		             rfb.setMsale((long)(gmonsal+.50));
		             rfb.setMach(gmonach);
		             rfb.setCumtarget((long)(gcumtar+.50));
		             rfb.setCumsale((long)(gcumsal+.50));
		             rfb.setLyear((long)(glstsal+.50));
		             rfb.setCumach(gcumach);
		             rfb.setCumgth(gcumgth);
		             rfb.setPmr(gpmr);
		             rfb.setSurdeff((long)(gsurp+.50));

		            data.add(rfb); 				
		            
////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLHOForm3DAO.getForm3 " + e); 
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
						System.out.println("-------------Exception in SQLHOForm3DAO.Connection.close "+e);
					}
		}

		
		return data;
	}
	
	

	
	
	public List getNewBranch(Connection con, int grp_code,int smon, int emon,int eyear, int depo_code,int div_code,int loginid,int utype, int rank) { 
				 
		HOForm3FormBean rfb;
        ResultSet rst1=null;
        CallableStatement cs=null;
        PreparedStatement ps1=null;
        ResultSet rs1=null;
        
        
		List<HOForm3FormBean> data = new ArrayList<HOForm3FormBean>();
		try {     
            String txt1=null;
            String txt2=null;
            String txt3=null;
            String txt4=null;

            
            double gcumtar=0.00;
            double gcumsal=0.00;
            double glstsal=0.00;
            double gmonach=0.00;
            double gcumach=0.00;
            double gcumgth=0.00;
            double gpmr=0.00;
            int gtrep=0;
            double gsurp=0.00;

 
            if (smon>emon)
            	emon=smon;

            
   	        
			String procedureWithParameters="{call web_report_top_branch(?,?,?,?,?,?,?,?,?)}";

             

			
            
            String branchname = "Select depo_name from branch_comp where depo_code=? ";
            ps1=con.prepareStatement(branchname);
             ps1.setInt(1, depo_code);
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
            	txt2="    Top HQ of "+rs1.getString(1)+" Branch on  "+(rank==1?"PMR":"ACH%");
            }
            else
            	txt2="   All India Top Branches on  "+(rank==1?"PMR":"ACH%");
            
           
            rs1.close();
            ps1.close();


			if(depo_code==-1)
			{
				procedureWithParameters="{call web_report_top_hq(?,?,?,?,?,?,?,?,?)}";
				depo_code=0;
	            txt2="    ALl India Top 25 HQ on  "+(rank==1?"PMR":"ACH%"); 

			}

            

            gcumtar=0.00;
            gcumsal=0.00;
            glstsal=0.00;
            gmonach=0.00;
            gcumach=0.00;
            gcumgth=0.00;
            gpmr=0.00;
            gtrep=0;
            gsurp=0.00;

			
			cs = con.prepareCall(procedureWithParameters);
		    cs.setInt(1,eyear);
		    cs.setInt(2,div_code);
		    cs.setInt(3,depo_code);
		    cs.setInt(4,smon);
		    cs.setInt(5,emon);
		    cs.setInt(6,utype);
		    cs.setInt(7,loginid);
		    cs.setInt(8,grp_code);
		    cs.setInt(9,rank);

			rst1 = cs.executeQuery();
            
			int srno=1;
			boolean first=true;
			while (rst1.next())  // Branch Master Loop Starts/////////////////////////   
			{	
				if(first)
				{
					txt3=" From "+rst1.getString(10)+" To "+rst1.getString(11);
					first=false;
				}
				rfb = new HOForm3FormBean();
				rfb.setRank(srno);
				rfb.setName(rst1.getString(5));
				rfb.setFs(rst1.getInt(9));
				rfb.setCumach(rst1.getDouble(13));
				rfb.setCumgth(rst1.getDouble(14));
				rfb.setPmr(rst1.getDouble(15));
				

				rfb.setNm2(txt3);
				rfb.setHead1(rst1.getString(12)!=null?"Group -> "+rst1.getString(12)+": "+txt2+txt3:"All Group "+txt2+txt3);
				rfb.setNm4(String.valueOf(rank));

				gcumsal+= rst1.getDouble(6);
				gcumtar+= rst1.getDouble(7);
				glstsal+= rst1.getDouble(8);
				gtrep+= rst1.getDouble(9);

				srno++;
				data.add(rfb); 				
				
			 }  ////////////// Branch Master Loop End here/////////////////////////
			
				             
                  rst1.close();
                  cs.close();

		             if (gcumtar!=0)
		            	 gcumach = (gcumsal/gcumtar)*100;
		             if (glstsal!=0)
		            	 gcumgth = ((gcumsal/glstsal)*100)-100;
		             if (gtrep!=0)
		            	 gpmr = gcumsal/gtrep;

					 rfb = new HOForm3FormBean();
					 rfb.setName(depo_code==0?"ALL INDIA :":"BRANCH :");
					 rfb.setFs(gtrep);
		             rfb.setCumach(gcumach);
		             rfb.setCumgth(gcumgth);
		             rfb.setPmr(gpmr);

		            data.add(rfb); 				
		            
////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLHOForm3DAO.getForm3 " + e); 
		}
		  finally 
		{
			try
				{
		           if(rst1 != null){ rst1.close();}
		           if(cs != null){ cs.close();}
		           if(rs1 != null){ rs1.close();}
		           if(ps1 != null){ ps1.close();}
		           if(con != null){con.close();}
				} 
				catch (SQLException e)
					{
						System.out.println("-------------Exception in SQLHOForm3DAO.Connection.close "+e);
					}
		}

		
		return data;
	}
	
	
}
