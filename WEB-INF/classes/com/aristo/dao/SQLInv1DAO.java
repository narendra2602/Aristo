package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Inv1FormBean;

public class SQLInv1DAO {
	
	public List getInv1(Connection con, int depo, int emon,int eyear,String tp,int uid,String utype) { 
		 
		Inv1FormBean rfb;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
        PreparedStatement ms1=null;
        ResultSet mrec=null;
        PreparedStatement ps3=null; 
        ResultSet rst3=null;
        
		List<Inv1FormBean> data = new ArrayList<Inv1FormBean>();
		try {     
            String tblnm=null;
            String tblnm1=null;
            String wname=null;
            String query1=null;
            
            String txt5=null;
            String txt2=null;

        	double opnbal=0.00;
        	double dambal=0.00;
        	double badbal=0.00;
        	double othbal=0.00;
        	double bplbal=0.00;
        	double salqty=0.00;
        	double freqty=0.00;
        	double brkqty=0.00;
        	double expqty=0.00;
        	double sales=0.00;
        	double fissue=0.00;
        	double salsamp=0.00;
        	double woff=0.00;
        	double branches=0.00;
        	double cnf=0.00;
        	double closbal=0.00;
        	double mfgval=0.00;

        	double gopnbal=0.00;
        	double gdambal=0.00;
        	double gbadbal=0.00;
        	double gothbal=0.00;
        	double gbplbal=0.00;
        	double gsalqty=0.00;
        	double gfreqty=0.00;
        	double gbrkqty=0.00;
        	double gexpqty=0.00;
        	double gsales=0.00;
        	double gfissue=0.00;
        	double gsalsamp=0.00;
        	double gwoff=0.00;
        	double gbranches=0.00;
        	double gcnf=0.00;
        	double gclosbal=0.00;
        	double gmfgval=0.00;

        	tblnm=(tp+"_product08").toLowerCase();
            tblnm1=(tp+"_stock08").toLowerCase();

//////////////////Month File Loop Starts to accumulate data/////////////////////////
    		String month = "Select mnth_name,mnth_year,mnth_abbr,mnth_no,mkt_year from monthfl where mnth_ord=? and mkt_year=? order by mnth_ord";  
    		ms1 = con.prepareStatement(month);
    		ms1.setInt(1,emon);
    		ms1.setInt(2,eyear);
    	 	mrec = ms1.executeQuery();
            
			    if (mrec.next())
 			    {	
 				    txt5 = mrec.getString(1)+" "+mrec.getInt(2);
 	                wname = mrec.getString(3);
 			    }  
 			    	
	 			ms1.close();
	 			mrec.close();

                txt2="     STOCK & SALES FOR THE MONTH OF  "+txt5;
                
/////////////////////// Stock  Master ki Query/////////////////////////////////////
		           
        	String query3 = "Select doc_type,opng"+wname+",clos"+wname+",qty"+wname+",free"+wname+",val"+wname+",fval"+wname+" from "+tblnm1+" where  "+
            "  depo_code=? and item_code= ? and mkt_year=? order by item_code"; 
	        ps3 = con.prepareStatement(query3);
		        
 ////////////////////////////Product Master Ki Query//////////////////////////////////
	        if (utype.equals("PMT"))
	        {
            query1 = "Select pcode,pname,pack,trd_rt1 from "+tblnm+" where mkt_year=? and " +
            " pd_group in (select gp_code from pmt_group where user_id="+uid+" and access_t='"+tp.toUpperCase()+"' and status='Y') order by pname";
	        }
	        else
	        {
            query1 = "Select pcode,pname,pack,trd_rt1 from "+tblnm+" where mkt_year=? order by pname";	        	
	        }
			ps1 = con.prepareStatement(query1); 
			ps1.setInt(1, eyear);
			rst1 = ps1.executeQuery();

			while (rst1.next())  ////////////Product Master Loop Starts/////////////////////////   
			{	
				rfb = new Inv1FormBean();
				rfb.setProduct(rst1.getString(2));
				rfb.setPack(rst1.getString(3));
				opnbal=0.00;
	        	dambal=0.00;
	        	badbal=0.00;
	        	othbal=0.00;
	        	bplbal=0.00;
	        	salqty=0.00;
	        	freqty=0.00;
	        	brkqty=0.00;
	        	expqty=0.00;
	        	sales=0.00;
	        	fissue=0.00;
	        	salsamp=0.00;
	        	woff=0.00;
	        	branches=0.00;
	        	cnf=0.00;
	        	closbal=0.00;
	        	mfgval=0.00;
	        	
			    ps3.setInt(1,depo); 
			    ps3.setInt(2,rst1.getInt(1)); 
			    ps3.setInt(3,eyear);
			    
			    rst3 = ps3.executeQuery(); 
			        while (rst3.next())  /////// Stock Master Start/////////////////////////
			        {
                        if (rst3.getInt(1)==60)
                        {
                        	dambal=dambal+rst3.getDouble(4)+rst3.getDouble(5);
                    	    gdambal=gdambal+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==72)
                        {
                        	badbal=badbal+rst3.getDouble(4)+rst3.getDouble(5);
                    	    gbadbal=gbadbal+rst3.getDouble(6);
                        }

                        if (rst3.getInt(1)==61)
                        {
                        	othbal=othbal+rst3.getDouble(4)+rst3.getDouble(5);
                    	    gothbal=gothbal+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==62)
                        {
                        	bplbal=bplbal+rst3.getDouble(4)+rst3.getDouble(5);
                    	    gbplbal=gbplbal+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==63)
                        {
                        	salqty=salqty+rst3.getDouble(4);
                        	freqty=freqty+rst3.getDouble(5);
                        	gsalqty=gsalqty+rst3.getDouble(6);
                        	gfreqty=gfreqty+rst3.getDouble(7);
                        }
                        
                        if (rst3.getInt(1)==64)
                        {
                        	brkqty=brkqty+rst3.getDouble(4);
                        	gbrkqty=gbrkqty+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==69)
                        {
                        	expqty=expqty+rst3.getDouble(4);
                        	gexpqty=gexpqty+rst3.getDouble(6);
                        }
                        
                        if (rst3.getInt(1)==80)
                        {
                        	sales=sales+rst3.getDouble(4);
                        	fissue=fissue+rst3.getDouble(5);
                        	opnbal= opnbal + rst3.getDouble(2);
                        	closbal= closbal + rst3.getDouble(3);
                        	mfgval = mfgval + (rst3.getDouble(3)*rst1.getDouble(4));
                        	
                        	gsales=gsales+rst3.getDouble(6);
                        	gfissue=gfissue+rst3.getDouble(7);
                        	gopnbal=gopnbal +(rst3.getDouble(2)*rst1.getDouble(4));
                        	gclosbal=gclosbal+(rst3.getDouble(3)*rst1.getDouble(4));
                        	gmfgval=gmfgval+(rst3.getDouble(3)*rst1.getDouble(4));
                        }
                        
                        if (rst3.getInt(1)==65)
                        {
                        	salsamp=salsamp+rst3.getDouble(4);
                    	    gsalsamp=gsalsamp+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==66)
                        {
                        	woff=woff+rst3.getDouble(4);
                    	    gwoff=gwoff+rst3.getDouble(6);
                        }
                        if (rst3.getInt(1)==67)
                        {
                        	branches=branches+rst3.getDouble(4)+rst3.getDouble(5);
                        	gbranches=gbranches+rst3.getDouble(6);
                        	closbal=  closbal-rst3.getDouble(5);

                        }
                        if (rst3.getInt(1)==68)
                        {
                        	cnf=cnf+rst3.getDouble(4); 
                        	gcnf=gcnf+rst3.getDouble(6);
                        }
			        
			        }	 /////////////////// Target Master End ///////////////////////
			        
			        rst3.close();
			        
/////////////////////// Stock Master ki Query end here/////////////////////////////////////
			        				        		            
					 rfb.setOpnbal(opnbal);
					 rfb.setDambal(dambal);
					 rfb.setBadbal(badbal);
					 rfb.setOthbal(othbal);
					 rfb.setBplbal(bplbal);
					 rfb.setSalqty(salqty);
					 rfb.setFreqty(freqty);
					 rfb.setExpqty(expqty);
					 rfb.setBrkqty(brkqty);
					 rfb.setSales(sales);
					 rfb.setFissue(fissue);
					 rfb.setSalsamp(salsamp);
					 rfb.setWoff(woff);
					 rfb.setBranches(branches);
					 rfb.setCnf(cnf);
					 rfb.setClosbal(closbal);
					 rfb.setMfgval(mfgval);

		             rfb.setHead1(txt2);
 		             
		             data.add(rfb); 				
				
			 }  ////////////// Product Master Loop End here/////////////////////////
			 
				 rfb=new Inv1FormBean();
				 rfb.setProduct("GRAND TOTAL"); 
				 rfb.setOpnbal(gopnbal);
				 rfb.setDambal(gdambal);
				 rfb.setBadbal(gbadbal);
				 rfb.setOthbal(gothbal);
				 rfb.setBplbal(gbplbal);
				 rfb.setSalqty(gsalqty);
				 rfb.setFreqty(gfreqty);
				 rfb.setExpqty(gexpqty);
				 rfb.setBrkqty(gbrkqty);
				 rfb.setSales(gsales);
				 rfb.setFissue(gfissue);
				 rfb.setSalsamp(gsalsamp);
				 rfb.setWoff(gwoff);
				 rfb.setBranches(gbranches);
				 rfb.setCnf(gcnf);
				 rfb.setClosbal(gclosbal);
				 rfb.setMfgval(gmfgval);
		             
	             data.add(rfb); 				
				
		        ps3.close();
		        rst1.close();
		        ps1.close();

////////////////////////////////////////////////////////////////////////////
		} 
		  catch (Exception e) 
		{
			System.out.println("========Exception in SQLInv1DAO.getInv1 " + e); 
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
						System.out.println("-------------Exception in SQLInv1DAO.Connection.close "+e);
					}
		}
		return data;
	}
	
}