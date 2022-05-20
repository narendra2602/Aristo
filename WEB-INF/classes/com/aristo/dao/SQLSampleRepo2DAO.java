package com.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.SampleRepo2FormBean;

public class SQLSampleRepo2DAO {

	public List getRepo2(Connection con, int div,int myear,int monno,int pcd,int choice) { 
		 
		SampleRepo2FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null;

	 
		String month= null;
		int mnth_code=0;

		List<SampleRepo2FormBean> data = null;
		try { 
             
            String query=null;
            
    		
    		String query1=" select concat(mnth_abbr,'-',mnth_year),mnth_code from monthfl where mkt_year=? and mnth_no=? "; 
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, myear);
			ps1.setInt(2, monno);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1);
				mnth_code=rst1.getInt(2);
			}
			rst1.close();
			ps1.close();

    	 
           	 query="select branch,opening_qty,(daman_qty+bhopal_qty+baddi_qty+other_qty) receipts,issue_qty issue,br_tr_qty transfer," +
           	 "closing_qty closing,depo_code,concat(product_name,' ',pack,' FOR THE MONTH '),prd_code,mnth_code from dist_sample where mkt_year=? and div_code=? " +
             "and mnth_code in (select mnth_code from monthfl where mkt_year=? and mnth_no=?) and prd_code=? ";
    		 
           	 if(choice==2)
           	 {
           		query="select branch,sum(opening_qty),sum(receipts),sum(issue),sum(transfer), "+
           		"sum(closing),depo_code,pname,prd_code,mnth_code from "+
           		"(select branch,0 opening_qty,sum(daman_qty+bhopal_qty+baddi_qty+other_qty) receipts,sum(issue_qty) issue,sum(br_tr_qty) transfer, "+
           		"sum(closing_qty) closing,depo_code,concat(product_name,' ',pack,' FROM OCT TO ') pname,prd_code,mnth_code from dist_sample where mkt_year=? and div_code=? "+ 
           		"and mnth_code <= (select mnth_code from monthfl where mkt_year=? and mnth_no=?)  and prd_code=?  group by depo_code  "+
           		"union all  "+
           		"select branch,opening_qty,0 receipts,0 issue,0 transfer,0 closing,depo_code,concat(product_name,' ',pack,' FROM OCT TO ') pname,prd_code,mnth_code "+
           		"from dist_sample where mkt_year=? and div_code=? and prd_code=? and  mnth_code in  "+
           		"(select min(mnth_code) from dist_sample where mkt_year=? and div_code=?  "+
           		"and mnth_code <= (select mnth_code from monthfl where mkt_year=? and mnth_no=?)  and prd_code=?)) a group by depo_code ";
           	 }
    	
    		
			ps = con.prepareStatement(query);
			ps.setInt(1, myear);
			ps.setInt(2, div);
			ps.setInt(3, myear);
			ps.setInt(4, monno);
			ps.setInt(5, pcd);
			if(choice==2)
			{
				ps.setInt(6, myear);
				ps.setInt(7, div);
				ps.setInt(8, pcd);
				ps.setInt(9, myear);
				ps.setInt(10, div);
				ps.setInt(11, myear);
				ps.setInt(12, monno);
				ps.setInt(13, pcd);
			}
			rst = ps.executeQuery();
			boolean first=true;
			while (rst.next())
			{
				 
				if(first)
				{
					data = new ArrayList<SampleRepo2FormBean>();
					first=false;
				}
				rfb = new SampleRepo2FormBean();
				rfb.setName(rst.getString(1));
				rfb.setOqty(rst.getInt(2));
				rfb.setRqty(rst.getInt(3));
				rfb.setIqty(rst.getInt(4));
				rfb.setTqty(rst.getInt(5));
				rfb.setCqty(rst.getInt(2)+rst.getInt(3)-rst.getInt(4)-rst.getInt(5));
				rfb.setDepo_code(rst.getInt(7)); // depo_code
				rfb.setPname(rst.getString(8)+month);
				rfb.setCode(rst.getInt(9));// pcode
				rfb.setFqty(mnth_code); // month_code
//				rfb.setPack(rst.getString(9));
				rfb.setColour(1);
				data.add(rfb);

			} 
			
				
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo2DAO.getRepo2 " + e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
				if(rst1 != null){rst1.close();} 
				if(ps1 != null){ ps1.close();}				
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo2DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	


	
	
	public List getRepo2a(Connection con, int div,int depo,int monno,int pcd,int choice,int myear) { 
		 
		SampleRepo2FormBean rfb=null;
		PreparedStatement ps=null;
		ResultSet rst=null;
		PreparedStatement ps1 = null;
		ResultSet rst1 = null; 
		String month= null;
		
		List<SampleRepo2FormBean> data = null;
		try { 
             
			
			System.out.println("monno is "+monno);
            String query=null;
        	String query1=" select concat(mnth_abbr,'-',mnth_year) from monthfl where mkt_year=? and mnth_code=? "; 
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, myear);
			ps1.setInt(2, monno);
			rst1 = ps1.executeQuery();
			if (rst1.next())
			{
				month=rst1.getString(1); 
			}
			rst1.close();
			ps1.close();
    	           	  
           	 query="select doc_no,doc_date,party_name,party_city,opening,receipt,dispatch," +
           	 "concat('BRANCH-',branch_name,'  ',product_name,' ',pack,' FOR THE MONTH ') " +
           	 " from zz_sample_analysis where mkt_year=? and div_code = ? and sdepo_code = ? and mnth_code = ? and sprd_cd = ? order by div_typ,doc_date,doc_no ";

           	 if(choice==2) // yearly
           	 {
	           	 query="select doc_no,doc_date,party_name,party_city,opening,receipt,dispatch," +
               	 "concat('BRANCH-',branch_name,'  ',product_name,' ',pack,' FROM OCT TO ') " +
               	 " from zz_sample_analysis where mkt_year=? and div_code = ? and sdepo_code = ? and mnth_code <= ? and sprd_cd = ? order by div_typ,doc_date,doc_no";
           	 }
           	 
    		  
    		
			ps = con.prepareStatement(query);
			ps.setInt(1, myear);
			ps.setInt(2, div);
			ps.setInt(3, depo);
			ps.setInt(4, monno);
			ps.setInt(5, pcd);
			rst = ps.executeQuery();
			boolean first=true;
			int pqty=0;
			while (rst.next())
			{
				 
				if(first)
				{
					data = new ArrayList<SampleRepo2FormBean>();
					rfb = new SampleRepo2FormBean();
					rfb.setName("OPENING BALANCE ***");
					rfb.setOqty(rst.getInt(5));
					rfb.setCqty(rst.getInt(5));
					rfb.setColour(1);
					rfb.setBrname(rst.getString(8)+month); // report heading
					data.add(rfb);
					first=false;
					pqty=rst.getInt(5);

				}
					pqty=pqty+rst.getInt(6)-rst.getInt(7);
					rfb = new SampleRepo2FormBean();
					rfb.setInv_no(rst.getString(1));
					rfb.setInv_dt(rst.getDate(2));
					rfb.setName(rst.getString(3));
					rfb.setCity(rst.getString(4));
					rfb.setRqty(rst.getInt(6));
					rfb.setIqty(rst.getInt(7));
					rfb.setCqty(pqty);
					rfb.setColour(1);
					data.add(rfb);

			} 
			
				
				rst.close();
				ps.close();
				
				rst=null;
				ps=null;
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLSampleRepo2DAO.getRepo2a " + e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rst != null){rst.close();} 
				if(ps != null){ ps.close();}
                if(con != null){con.close();}

			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLSampleRepo2DAO.Connection.close "+e);
			  }
		}		
		
		return data;
	}	
	
}
