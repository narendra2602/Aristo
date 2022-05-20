package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.HOForm1FormBean;

public class SQLHOForm1DAO {

	public List getAllBranch(Connection con, int code, int emon,String tp,String typ,int code1,int loginid,int eyear) { 
		 
		HOForm1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps33=null; 
        ResultSet rst33=null;
		
		List<HOForm1FormBean> data = new ArrayList<HOForm1FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
//            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
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
            
   	        tblnm4="user_branch08";

        	tblnm=(tp+"_repfinal").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm3=(tp+"_br_pmr").toLowerCase();

   	        txt2="   ACHIEVEMENT UPTO  "; 
            
////////////////////////////Product Ki Query//////////////////////////////////
            String terrec = "Select mname,pack,mcode from "+tblnm1+" where pcode=? and mkt_year=?";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		if (trec.next()) 
    		{
               txt1="Product-> "+trec.getString(1)+","+trec.getString(2);
               code=trec.getInt(3);
    		}
    		trec.close();
			ts1.close();
			


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
            
			
			String query33 = "SELECT C.DEPO_CODE,C.BR_NAME,C.MTAR,C.MSAL,C.CTAR,C.CSAL,C.CLYS,D.REP "+
			" FROM "+
			" (select a.depo_code,a.br_name,a.tarval mtar,a.salval msal,b.ctarval ctar,b.csalval csal,b.clysval clys "+
			" from( "+
			" select depo_code,br_name,mcode,sum(lysval) lysval, sum(tarval) tarval,sum(salval) salval from "+tblnm+"  "+ 
			" where mkt_year = ?   and  mnth_code = ?  and mcode = ?  "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,br_name,mcode) as a "+
			" left join "+
			" (select depo_code,br_name,mcode,sum(lysval) clysval,sum(tarval) ctarval,sum(salval) csalval  "+
			" from "+tblnm+" where mkt_year = ? and  mnth_code <= ?  and mcode = ? "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,br_name,mcode) as b "+
			" on a.depo_code = b.depo_code) C, "+
			" (SELECT MKT_YEAR,DEPO_CODE,SUM(REP) REP FROM "+tblnm3+" "+
			" WHERE MKT_YEAR = ?  AND MNTH_CODE <= ? "+
			" GROUP BY MKT_YEAR,DEPO_CODE) D "+
			" WHERE  C.DEPO_CODE = D.DEPO_CODE "	;
			
			ps33 = con.prepareStatement(query33);
		    ps33.setInt(1,eyear);
		    ps33.setInt(2,emon);
		    ps33.setInt(3,code);
		    ps33.setInt(4,loginid);
		    ps33.setInt(5,eyear);
		    ps33.setInt(6,emon);
		    ps33.setInt(7,code);
		    ps33.setInt(8,loginid);
		    ps33.setInt(9,eyear);
		    ps33.setInt(10,emon);

			rst33 = ps33.executeQuery();
 			int trep=0;

 			while (rst33.next())  // Branch Master Loop Starts/////////////////////////   
			{
				rfb = new HOForm1FormBean();
				rfb.setName(rst33.getString(2));
				trep=0;
	            trep=rst33.getInt(8);  			
				
		 			montar=0.00;
		 			monsal=0.00;
		 			monach=0.00;
		 			cumtar=0.00;
		 			cumsal=0.00;
		 			lstsal=0.00;
		 			cumach=0.00;
		 			cumgth=0.00;
		 			pmr=0.00;
		 			 
		        	montar = rst33.getDouble(3);
		            monsal = rst33.getDouble(4);
		         
		            cumtar = rst33.getDouble(5);
		            cumsal = rst33.getDouble(6);
		            lstsal = rst33.getDouble(7);

	 			           
		        				        

		            if (montar!=0)
		            	monach = (monsal/montar)*100;
		            if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		              pmr = cumsal/trep;
	        		
					 rfb.setPer(tarper);
		             rfb.setMtarget(montar);
		             rfb.setMsale(monsal);
		             rfb.setMach(monach);
		             rfb.setCumtarget(cumtar);
		             rfb.setCumsale(cumsal);
		             rfb.setLyear(lstsal);
		             rfb.setCumach(cumach);
		             rfb.setCumgth(cumgth);
		             rfb.setPmr(pmr);
		             rfb.setNm2(txt3);
		             rfb.setHead1(txt1+txt2+txt3);
		             rfb.setNm4(txt4);
		        
		             gmontar= gmontar +montar;
		             gcumtar= gcumtar +cumtar;
		             gmonsal= gmonsal + monsal;
		             gcumsal= gcumsal +cumsal;
		             glstsal= glstsal +lstsal;
		             gtrep = gtrep+trep;
		             
		             
		             data.add(rfb); 				
				
			 }  //////////////BranchMaster Loop End here/////////////////////////
			
 			rst33.close();
 			ps33.close();
 			            
	     			
					 rfb = new HOForm1FormBean();
					 rfb.setName("GRAND TOTAL :");

 					if (gmontar!=0)
		            	gmonach = (gmonsal/gmontar)*100;
		            if (gcumtar!=0)
		            	gcumach = (gcumsal/gcumtar)*100;
		            if (glstsal!=0)
		            	gcumgth = ((gcumsal/glstsal)*100)-100;
		            if (gtrep!=0)
		              gpmr = gcumsal/gtrep;

		             rfb.setMtarget(gmontar);
		             rfb.setMsale(gmonsal);
		             rfb.setMach(gmonach);
		             rfb.setCumtarget(gcumtar);
		             rfb.setCumsale(gcumsal);
		             rfb.setLyear(glstsal);
		             rfb.setCumach(gcumach);
		             rfb.setCumgth(gcumgth);
		             rfb.setPmr(gpmr);
		            
		            data.add(rfb); 				
		} 
		  catch (Exception e) 
		{
		
			System.out.println("========Exception in SQLHOMkt1DAO.getAllHQForm1 " + e); 
		}
		  finally 
		{
			try
			{
	            if(trec != null){ trec.close();}
	            if(ts1 != null){ ts1.close();}
	            if(mrec != null){mrec.close();}
	            if(ms1 != null){ ms1.close();}
	            if(rst33 != null){ rst33.close();}
	            if(ps33 != null){ps33.close();}
	            if(con != null){con.close();}
			} 
			  catch (SQLException e)
			{
				System.out.println("-------------Exception in SQLHOMkt1DAO.Connection.close "+e);
			}
		}
		
		return data;
	}
	

	public List getAllHQ(Connection con, int code, int emon,String tp,String typ,int code1,int loginid,int eyear) { 
		 
		HOForm1FormBean rfb;
        PreparedStatement ts1=null; 
        ResultSet trec=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps33=null; 
        ResultSet rst33=null;
		
		List<HOForm1FormBean> data = new ArrayList<HOForm1FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
//            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String txt1=null;
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
            
            double bmontar=0.00;
            double bcumtar=0.00;
            double bmonsal=0.00;
            double bcumsal=0.00;
            double blstsal=0.00;
            double bmonach=0.00;
            double bcumach=0.00;
            double bcumgth=0.00;
            double bpmr=0.00;
            int btrep=0;

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
            
   	        tblnm4="user_branch08";

        	tblnm=(tp+"_repfinal").toLowerCase();
        	tblnm1=(tp+"_product08").toLowerCase();
   	        tblnm3=(tp+"_tr_pmr").toLowerCase();

   	        txt2="   ACHIEVEMENT UPTO  "; 
            
////////////////////////////Product Ki Query//////////////////////////////////
            String terrec = "Select mname,pack,mcode from "+tblnm1+" where pcode=? and mkt_year=?";  
    		ts1 = con.prepareStatement(terrec);
    		ts1.setInt(1, code);
    		ts1.setInt(2, eyear);
    		trec = ts1.executeQuery();
    		if (trec.next()) 
    		{
               txt1="Product-> "+trec.getString(1)+","+trec.getString(2);
               code=trec.getInt(3);
    		}
    		trec.close();
			ts1.close();
			


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
            
			
			String query33 = "SELECT C.DEPO_CODE,C.BR_NAME,c.tr_cd,c.ter_name,C.MTAR,C.MSAL,C.CTAR,C.CSAL,C.CLYS,D.REP "+
			" FROM "+
			" (select a.depo_code,a.br_name,a.tr_cd,a.ter_name,a.tarval mtar,a.salval msal,b.ctarval ctar,b.csalval csal,b.clysval clys "+
			" from( "+
			" select depo_code,br_name,tr_cd,ter_name,mcode,sum(lysval) lysval, sum(tarval) tarval,sum(salval) salval from "+tblnm+"  "+ 
			" where mkt_year = ?   and  mnth_code = ?  and mcode = ?  "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,tr_cd) as a "+
			" left join "+
			" (select depo_code,br_name,tr_cd,ter_name,mcode,sum(lysval) clysval,sum(tarval) ctarval,sum(salval) csalval  "+
			" from "+tblnm+" where mkt_year = ? and  mnth_code <= ?  and mcode = ? "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" group by depo_code,tr_cd) as b "+
			" on a.depo_code=b.depo_code and a.tr_cd = b.tr_cd) C, "+
			" (SELECT depo_code,ter_code,SUM(REP) REP FROM "+tblnm3+" "+
			" WHERE MKT_YEAR = ?  AND MNTH_CODE <= ? "+
			" and depo_CODE IN (SELECT DEPO_CODE FROM "+tblnm4+" WHERE USER_ID = ? AND STATUS = 'Y') "+
			" GROUP BY DEPO_CODE,ter_code) D "+
			" WHERE  C.tr_cd = D.ter_CODE and c.depo_code=d.depo_code order by c.depo_code,c.tr_cd"	;
			ps33 = con.prepareStatement(query33);
		    ps33.setInt(1,eyear);
		    ps33.setInt(2,emon);
		    ps33.setInt(3,code);
		    ps33.setInt(4,loginid);
		    ps33.setInt(5,eyear);
		    ps33.setInt(6,emon);
		    ps33.setInt(7,code);
		    ps33.setInt(8,loginid);
		    ps33.setInt(9,eyear);
		    ps33.setInt(10,emon);
		    ps33.setInt(11,loginid);

			rst33 = ps33.executeQuery();
 			int trep=0;
		    boolean hprint=true;
    	    boolean first=true;
    	    int br=0;
    	    String brnm=null;

 			while (rst33.next())  // Branch Master Loop Starts/////////////////////////   
			{
				if (first)
				{
					br=rst33.getInt(1);
					brnm=rst33.getString(2);
					first=false;
				}
				
				if (br!=rst33.getInt(1))
				{
					rfb = new HOForm1FormBean();
					rfb.setPcode(3);
					rfb.setName(brnm);
 					if (bmontar!=0)
		            	bmonach = (bmonsal/bmontar)*100;
		            if (bcumtar!=0)
		            	bcumach = (bcumsal/bcumtar)*100;
		            if (blstsal!=0)
		            	bcumgth = ((bcumsal/blstsal)*100)-100;
		            if (btrep!=0)
		              bpmr = bcumsal/btrep;

		             rfb.setMtarget(bmontar);
		             rfb.setMsale(bmonsal);
		             rfb.setMach(bmonach);
		             rfb.setCumtarget(bcumtar);
		             rfb.setCumsale(bcumsal);
		             rfb.setLyear(blstsal);
		             rfb.setCumach(bcumach);
		             rfb.setCumgth(bcumgth);
		             rfb.setPmr(bpmr);

		             br=rst33.getInt(1);
					 brnm=rst33.getString(2);
		            
		             bmontar=0.00;
		             bcumtar=0.00;
		             bmonsal=0.00;
		             bcumsal=0.00;
		             blstsal=0.00;
		             bmonach=0.00;
		             bcumach=0.00;
		             bcumgth=0.00;
		             bpmr=0.00;
		             btrep=0;
		             data.add(rfb); 				
					
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
					trep=0;
		 			 
		        	montar = rst33.getDouble(5);
		            monsal = rst33.getDouble(6);
		         
		            cumtar = rst33.getDouble(7);
		            cumsal = rst33.getDouble(8);
		            lstsal = rst33.getDouble(9);
		            trep=rst33.getInt(10);  			

	 			           
		        				        

		            if (montar!=0)
		            	monach = (monsal/montar)*100;
		            if (cumtar!=0)
		            	cumach = (cumsal/cumtar)*100;
		            if (lstsal!=0)
		            	cumgth = ((cumsal/lstsal)*100)-100;
		            if (trep!=0)
		              pmr = cumsal/trep;
	        		
        		 if(hprint)
        		 {
					 rfb = new HOForm1FormBean();
					 rfb.setName(rst33.getString(4));
					 rfb.setPer(tarper);
		             rfb.setMtarget(montar);
		             rfb.setMsale(monsal);
		             rfb.setMach(monach);
		             rfb.setCumtarget(cumtar);
		             rfb.setCumsale(cumsal);
		             rfb.setLyear(lstsal);
		             rfb.setCumach(cumach);
		             rfb.setCumgth(cumgth);
		             rfb.setPmr(pmr);
		             rfb.setNm2(txt3);
		             rfb.setHead1(txt1+txt2+txt3);
		             rfb.setNm4(txt4);
		             data.add(rfb);
        		 }
		        
		             bmontar= bmontar +montar;
		             bcumtar= bcumtar +cumtar;
		             bmonsal= bmonsal + monsal;
		             bcumsal= bcumsal +cumsal;
		             blstsal= blstsal +lstsal;
		             btrep = btrep+trep;
		             
		             gmontar= gmontar +montar;
		             gcumtar= gcumtar +cumtar;
		             gmonsal= gmonsal + monsal;
		             gcumsal= gcumsal +cumsal;
		             glstsal= glstsal +lstsal;
		             gtrep = gtrep+trep;
		             
				
			 }  //////////////BranchMaster Loop End here/////////////////////////
			
 			rst33.close();
 			ps33.close();
 			            
					rfb = new HOForm1FormBean();
					rfb.setPcode(3);
					rfb.setName(brnm);
						if (bmontar!=0)
		            	bmonach = (bmonsal/bmontar)*100;
		            if (bcumtar!=0)
		            	bcumach = (bcumsal/bcumtar)*100;
		            if (blstsal!=0)
		            	bcumgth = ((bcumsal/blstsal)*100)-100;
		            if (btrep!=0)
		              bpmr = bcumsal/btrep;
		
		             rfb.setMtarget(bmontar);
		             rfb.setMsale(bmonsal);
		             rfb.setMach(bmonach);
		             rfb.setCumtarget(bcumtar);
		             rfb.setCumsale(bcumsal);
		             rfb.setLyear(blstsal);
		             rfb.setCumach(bcumach);
		             rfb.setCumgth(bcumgth);
		             rfb.setPmr(bpmr);
		
		            
		             bmontar=0.00;
		             bcumtar=0.00;
		             bmonsal=0.00;
		             bcumsal=0.00;
		             blstsal=0.00;
		             bmonach=0.00;
		             bcumach=0.00;
		             bcumgth=0.00;
		             bpmr=0.00;
		             btrep=0;
		             data.add(rfb); 				
	     			
					 rfb = new HOForm1FormBean();
					 rfb.setName("GRAND TOTAL :");
					 rfb.setPcode(4);

 					if (gmontar!=0)
		            	gmonach = (gmonsal/gmontar)*100;
		            if (gcumtar!=0)
		            	gcumach = (gcumsal/gcumtar)*100;
		            if (glstsal!=0)
		            	gcumgth = ((gcumsal/glstsal)*100)-100;
		            if (gtrep!=0)
		              gpmr = gcumsal/gtrep;

		             rfb.setMtarget(gmontar);
		             rfb.setMsale(gmonsal);
		             rfb.setMach(gmonach);
		             rfb.setCumtarget(gcumtar);
		             rfb.setCumsale(gcumsal);
		             rfb.setLyear(glstsal);
		             rfb.setCumach(gcumach);
		             rfb.setCumgth(gcumgth);
		             rfb.setPmr(gpmr);
		            
		            data.add(rfb); 				
		} 
		  catch (Exception e) 
		{
		
			System.out.println("========Exception in SQLHOMkt1DAO.getAllHQForm1 " + e); 
		}
		  finally 
		{
			try
			{
	            if(trec != null){ trec.close();}
	            if(ts1 != null){ ts1.close();}
	            if(mrec != null){mrec.close();}
	            if(ms1 != null){ ms1.close();}
	            if(rst33 != null){ rst33.close();}
	            if(ps33 != null){ps33.close();}
	            if(con != null){con.close();}
			} 
			  catch (SQLException e)
			{
				System.out.println("-------------Exception in SQLHOMkt1DAO.Connection.close "+e);
			}
		}
		
		return data;
	}
	

}
