package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.MktFormBean;

public class NewSQLForm3DAO {

	public List getForm3(Connection con, int smon, int emon,int eyear,int depo_code,String tp) { 
		 
		MktFormBean rfb;
        PreparedStatement ps12=null; 
        ResultSet rst12=null;
        PreparedStatement ps5=null; 
        ResultSet rst5=null;
        PreparedStatement ps6=null; 
        ResultSet rst6=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
		ResultSet mrec=null;
		PreparedStatement ps3=null; 
        ResultSet rst3=null;
        
        int mon=0;
		List<MktFormBean> data = new ArrayList<MktFormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
            String tblnm4=null;
            String tblnm5=null;
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
            int trep=0;
            double asurp=0.00;
            int rgcd=0;
            int arcd=0;
            if (smon>emon)
            	emon=smon;
            
            tblnm=(tp+"_target").toLowerCase();
            tblnm1=(tp+"_lysale").toLowerCase();
   	        tblnm2=(tp+"_hq_master08").toLowerCase();
   	 	    tblnm3=(tp+"_area_master08").toLowerCase();
   	 	    tblnm4=(tp+"_region_master08").toLowerCase();
   	 	    tblnm5=(tp+"_hqdetail08").toLowerCase();
   	 	    String cutar="";
   	 	    String cusal="";  
   	 	    String culsal="";
   	 	    String motar="";
   	 	    String mosal="";
 			String wname=null;

            String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year,mnth_ord from monthfl where mnth_ord>=? and mnth_ord<=? and mkt_year=? order by mnth_ord";  
 			ms1 = con.prepareStatement(month);
 			ms1.setInt(1,smon);
 			ms1.setInt(2,emon);
 			ms1.setInt(3,eyear);
 			mrec = ms1.executeQuery();
   	 	    boolean first=true;
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
			          txt5 = txt5 + " TO " +mrec.getString(1)+" "+mrec.getInt(2);
			          txt3 = mrec.getString(3)+" "+mrec.getInt(2);
			          txt4 = txt4+ " TO "+mrec.getString(3)+" "+mrec.getInt(2);
		            }
	    	        wname = mrec.getString(3);
		            mon=mrec.getInt(4);
		            if (first)
		            {
			            cutar = cutar+"ROUND(SUM((TT"+mon+"*TTARGET/100)*TMRP),0)";
			            cusal = cusal+"SUM(VAL"+wname+"-eVAL"+wname+"-sVAL"+wname+"-rVAL"+wname+"-bVAL"+wname+"-pVAL"+wname+")";
			            culsal = culsal+"SUM(RL"+mon+")";
			            first=false;
		            }
		            else
		            {
			            cutar = cutar+"+ROUND(SUM((TT"+mon+"*TTARGET/100)*TMRP),0)";
			            cusal = cusal+"+SUM(VAL"+wname+"-eVAL"+wname+"-sVAL"+wname+"-rVAL"+wname+"-bVAL"+wname+"-pVAL"+wname+")";
			            culsal = culsal+"+SUM(RL"+mon+")";
		            	
		            }
		            motar = "ROUND(SUM((TT"+mon+"*TTARGET/100)*TMRP),0)";
		            mosal = "SUM(VAL"+wname+"-eVAL"+wname+"-sVAL"+wname+"-rVAL"+wname+"-bVAL"+wname+"-pVAL"+wname+")";
 			}


            String query ="SELECT TSS.*,FSC.FS FROM (select F.mkt_year,F.depo_code,F.ter_code, sum(F.fs) fs "+
       		" from fs F where F.mkt_year="+eyear+" and F.depo_code="+depo_code+" and F.mnth between "+smon+" and "+emon+" "+
            " group by F.mkt_year,F.depo_code,F.ter_code) FSC "+
            " LEFT JOIN ("+			 
            " SELECT TSL.DEPO_CODE,TSL.AR_CD,H.AREA_NAME,TSL.RG_CD,H.NAME,TSL.TR_CD,H.TER_NAME, "+
            " TSL.TARGET,TSL.CTARGET,TSL.SALE,TSL.CSALE,TSL.LYSALE FROM ("+
            " SELECT H.DEPO_CODE,H.TER_CODE,H.TER_NAME,A.AREA_NAME,R.NAME FROM "+tblnm2+" H ,"+
            " "+tblnm3+" A, "+tblnm4+" R WHERE"+
            " H.MKT_YEAR = "+eyear+" AND H.DEPO_CODE = "+depo_code+""+
            " AND A.MKT_YEAR = "+eyear+" AND H.DEPO_CODE = "+depo_code+""+ 
            " AND A.AREA_CD=H.AREA_CoDe"+
            " AND R.MKT_YEAR = "+eyear+" AND R.DEPO_CODE = "+depo_code+""+
            " AND H.REGN_CODE = R.REG_CODE) H LEFT JOIN"+
            " (SELECT TS.DEPO_CODE,TS.AR_CD,TS.RG_CD,TS.TR_CD,"+
            " IFNULL(TS.TARGET,0) TARGET,IFNULL(TS.CTARGET,0) CTARGET,IFNULL(TS.SALE,0) SALE,IFNULL(TS.CSALE,0) CSALE,IFNULL(LS.LYSALE,0) LYSALE FROM ("+
            " SELECT T.DEPO_CODE,T.AR_CD,T.RG_CD,T.TR_CD,T.TARGET,T.CTARGET,S.SALE,S.CSALE FROM ("+
            " SELECT DEPO_CODE,AR_CD,RG_CD,TR_CD,"+
            " "+motar+" TARGET, "+cutar+" CTARGET "+  
            " FROM "+tblnm+" WHERE MKT_YEAR = "+eyear+" AND DEPO_CODE = "+depo_code+" "+
            " GROUP BY DEPO_CODE,AR_CD,RG_CD,TR_CD ) T LEFT JOIN("+
            " SELECT DEPO_CODE,AR_CD,RG_CD,TR_CD,"+
            " "+mosal+" SALE,"+cusal+" CSALE "+ 
            " FROM "+tblnm5+" WHERE MKT_YEAR = "+eyear+" AND DEPO_CODE = "+depo_code+" "+
            " GROUP BY DEPO_CODE,AR_CD,RG_CD,TR_CD) S ON "+
            " T.DEPO_CODE = S.DEPO_CODE AND"+
            " T.TR_CD = S.TR_CD) TS LEFT JOIN"+
            " (SELECT DEPO_CODE,AR_CD,RG_CD,TR_CD, "+culsal+" LYSALE "+
            " FROM "+tblnm1+" WHERE MKT_YEAR = "+eyear+" AND DEPO_CODE = "+depo_code+" "+
            " GROUP BY DEPO_CODE,AR_CD,RG_CD,TR_CD) LS"+
            " ON TS.DEPO_CODE = LS.DEPO_CODE AND TS.TR_CD = LS.TR_CD) TSL ON"+
            " H.DEPO_CODE = TSL.DEPO_CODE AND H.TER_CODE = TSL.TR_CD) TSS ON"+
            " FSC.DEPO_CODE=TSS.DEPO_CODE AND FSC.TER_CODE=TSS.TR_CD ";           
   	 	    
            
            System.out.println(query);
   	 	    txt2=" ACHIEVEMENT UPTO  ";
            
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
           	                       			
////////////////////////////Area Master Ki Query//////////////////////////////////
		boolean rfirst=false;
		boolean rprint=false;
		boolean aprint=false;
		String reg_name="";
		String ar_name="";
		ps5 = con.prepareStatement(query); 
		rst5 = ps5.executeQuery();
		while (rst5.next())   
	   	{

             	                          
				// HQ Local Variables
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
	 			trep=0;
	 			 if (!rfirst)
                {
	        	 	  rgcd=rst5.getInt(4);
	        	 	  reg_name=rst5.getString(5);
	        	 	  arcd=rst5.getInt(2);
	        	 	  ar_name=rst5.getString(3);
                }
	 			
	 			if (rfirst)
	 			{
	 			 if (rgcd!=rst5.getInt(4))
		 			rprint=true;
	 			 if (arcd!=rst5.getInt(2))
			 		aprint=true;
	 			}
	 				

	 			if (rprint)
	 			{
				rfb = new MktFormBean();
				rfb.setMcode(2);
				rfb.setMname("TOTAL: "+reg_name);
	              
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
	             rprint=false;
	             // Region Local Variables
	             rmontar=0.00;
	             rcumtar=0.00;
	             rmonsal=0.00;
	             rcumsal=0.00;
	             rlstsal=0.00;
	             rmonach=0.00;
	             rcumach=0.00;
	             rcumgth=0.00;
	             rpmr=0.00;
	             rtrep=0;
	             rsurp=0.00;
	       	 	 rgcd=rst5.getInt(4);
	    	 	 reg_name=rst5.getString(5);

	 			}


	 			if (aprint)
	 			{
					rfb = new MktFormBean();
					rfb.setMcode(3);
					rfb.setMname("TOTAL: "+ar_name);
		              
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
		             aprint=false; 
	                 /// Area Local Variables
					 amontar=0.00;
		             acumtar=0.00;
		             amonsal=0.00;
		             acumsal=0.00;
		             alstsal=0.00;
		             amonach=0.00;
		             acumach=0.00;
		             acumgth=0.00;
		             apmr=0.00;
		             atrep=0;
		             asurp=0.00; 
	        	 	 arcd=rst5.getInt(2);
	        	 	 ar_name=rst5.getString(3);

	 				
	 			}
	 			rfirst=true; 
	 			montar=rst5.getDouble(8);
	 			cumtar=rst5.getDouble(9);
	 			monsal=rst5.getDouble(10);
	 			cumsal=rst5.getDouble(11);
	 			lstsal=rst5.getDouble(12);
	 			trep=rst5.getInt(13);
	 			

	            if (montar!=0)
	            	monach = (monsal/montar)*100;
	            if (cumtar!=0)
	            	cumach = (cumsal/cumtar)*100;
	            if (lstsal!=0)
	            	cumgth = ((cumsal/lstsal)*100)-100;
	            if (trep!=0)
	              pmr = cumsal/trep;
	        	    
	            surp=cumsal-cumtar;
		            
				 rfb = new MktFormBean();
				 rfb.setMname(rst5.getString(7));
	             rfb.setMcode(1);
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
		             
		             data.add(rfb); 				
				
			       
				
				      
		}  ///////////////////////Area Master Loop End here/////////////////////////
	     			
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
////////////////////////////////////////////////////////////////////////////
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
	             if(rst5 != null){ rst5.close();}
	             if(ps5 != null){ps5.close();}
	             if(rst6 != null){ rst6.close();}
	             if(ps6 != null){ps6.close();}
	             if(rst1 != null){ rst1.close();}
	             if(ps1 != null){ ps1.close();}
	             if(mrec != null){mrec.close();}
	             if(ms1 != null){ ms1.close();}
	             if(rst3 != null){ rst3.close();}
	             if(ps3 != null){ps3.close();}
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