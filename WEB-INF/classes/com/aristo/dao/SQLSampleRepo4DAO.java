package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.SampleRepo2FormBean;

public class SQLSampleRepo4DAO {

	public List getRepo4(Connection con, int div,int depo,String cat,int mon,int myear,String party,int choice){ 
		 
		SampleRepo2FormBean rfb=null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		String query=null;
		List<SampleRepo2FormBean> data = null;
		try { 

    		query="	select Branch_name,sprd_cd,product_name,pack,doc_no,doc_date,party_name,party_city,dispatch," +
    		"concat(monthname(doc_Date),'-',year(doc_date)) mnthname,ifnull(hq_name,''),ifnull(prd_rate,0.00),ifnull(prd_value,0.00) from zz_sample_analysis "+ 
    		"where div_code=? and sdepo_code=? and identity=? and mnth_code in (select mnth_code from monthfl where mkt_year=? and mnth_no=?) " +
    		"and dispatch<>0 order by product_name,doc_no,doc_date" ;   			
    		
    		if(party!=null)
    		{
    			if(choice==1)
    				
    			{
	        		query="	select Branch_name,sprd_cd,product_name,pack,doc_no,doc_date,party_name,party_city,dispatch," +
	        		"concat('FOR THE MONTH ',upper(monthname(doc_Date)),'-',year(doc_date)) mnthname,ifnull(hq_name,''),ifnull(prd_rate,0.00),ifnull(prd_value,0.00) from zz_sample_analysis "+ 
		    		"where div_code=? and sdepo_code=? and identity=? and mnth_code in (select mnth_code from monthfl where mkt_year=? and mnth_no=?) " +
		    		"and dispatch<>0 and sprt_cd=? and div_typ='Issue to FS' order by doc_date,doc_no,product_name " ;
    			}
    			else
    			{
            		query="	select Branch_name,sprd_cd,product_name,pack,doc_no,doc_date,party_name,party_city,dispatch," +
            		"concat('FROM OCT TO ',upper(monthname(doc_Date)),'-',year(doc_date)) mnthname,ifnull(hq_name,''),ifnull(prd_rate,0.00),ifnull(prd_value,0.00) from zz_sample_analysis "+ 
    	    		"where div_code=? and sdepo_code=? and identity=? and mnth_code <= (select mnth_code from monthfl where mkt_year=? and mnth_no=?) " +
    	    		"and dispatch<>0 and sprt_cd=? and div_typ='Issue to FS' order by doc_date,doc_no,product_name " ;   			

    			}
    		}
    		
     		
			ps = con.prepareStatement(query);
			ps.setInt(1,div);
			ps.setInt(2,depo);
			ps.setString(3, cat);
			ps.setInt(4, myear);
			ps.setInt(5, mon);
			if(party!=null)
				ps.setString(6, party);
			
			rst = ps.executeQuery();
			boolean first=true;
			double totvalue=0.00;
			String monname="";
			while (rst.next())
			{
				
				if(first)
				{
					data = new ArrayList<SampleRepo2FormBean>();
					first=false;
				}
				rfb = new SampleRepo2FormBean();
				rfb.setBrname(rst.getString(1)) ;
				rfb.setCode(rst.getInt(2));
				rfb.setPname(rst.getString(3)) ;
				rfb.setPack(rst.getString(4)) ;
				rfb.setInv_no(rst.getString(5));
				rfb.setInv_dt(rst.getDate(6));
				rfb.setName(rst.getString(7)) ;
				rfb.setCity(rst.getString(8));
				rfb.setIqty(rst.getInt(9));
				rfb.setBat_no(rst.getString(10)); // monthname 
				rfb.setHq_name(rst.getString(11));
				rfb.setRate(rst.getDouble(12));
				rfb.setTvalue(rst.getDouble(13));
				totvalue+=rst.getDouble(13);
				rfb.setColour(1);
				data.add(rfb);
			}

			monname=rfb.getBat_no();
			
			rfb = new SampleRepo2FormBean();
			rfb.setBrname("") ;
			rfb.setTvalue(totvalue);
			rfb.setBat_no(monname);
			rfb.setColour(2);
			data.add(rfb);

			rst.close();
			ps.close();
			rst=null;
			ps=null;
			
		} catch (Exception e) {
			System.out.println("========Exception in SQLSampleRepo4DAO.getRepo4 " + e);
		}
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo4DAO.Connection.close "+e);
			  }
		}		
		return data;
	}

}