package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.data.category.DefaultCategoryDataset;


public class SQLGraphGenDAO
{

	public DefaultCategoryDataset getGraphGen(Connection con, int depo_code,int div_code,int eyear) { 
		
		
		CallableStatement cs = null;
		ResultSet rs = null;
		DefaultCategoryDataset dataset=null;	
		try { 
        
            // Create a simple Bar chart
            System.out.println("Setting dataset values");

            
            cs = con.prepareCall("{call web_graph_proc(?,?,?)}");
            cs.setInt(1, eyear);
            cs.setInt(2, div_code);
            cs.setInt(3, depo_code);
            
			rs = cs.executeQuery();
			
			
            dataset = new DefaultCategoryDataset();

            while (rs.next())
            {
               dataset.setValue(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            
			rs.close();
			cs.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLGraphGenDAO.getAllGroupSales " + e);
		}
		
		finally {
			try {
	            if(rs != null){ rs.close();}
	            if(cs != null){cs.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLGraphGenDAO.Connection.close "+e);
			  }
		}		
		
		return dataset;
	}
	

	public DefaultCategoryDataset getGraphGenOld(Connection con, int depo_code,String typ,int eyear) { 
		
		
		Statement st = null;
		ResultSet rs = null;
		DefaultCategoryDataset dataset=null;	
		try { 
        
            // Create a simple Bar chart
            System.out.println("Setting dataset values");
            /*String query = "select val,nm,ter_name from ( "+
            		" select sum(tarval) as val,'Target' as nm, ter_name from a_repfinal " +
            		" where depo_code in (select distinct depo_code from user_branch08 where user_id = 27) and mkt_year=2011 and mnth_code=2  group by tr_cd "+
            		" union all "+
            		" select sum(salval) as val,'Sale' as nm, ter_name from a_repfinal " +
            		" where depo_code  in (select distinct depo_code from user_branch08 where user_id = 27) and mkt_year=2011 and mnth_code=2  group by tr_cd) a "+
            		" order by ter_name ";
            */
            
			String tblnm=null;
        	tblnm=(typ+"_repfinal").toLowerCase();
        	
        	
/*            String query= " select a.val,a.nm,m.mnth_abbr from ( "+ 
            		      " select 1 as srno,mnth_code,sum(tarval) as val,'Target' as nm from "+tblnm+ 
            		      " where depo_code ="+depo_code+" and mkt_year="+eyear+"  group by mnth_code"+ 
            		      " union all "+
            		      " select 2 as srno, mnth_code,sum(salval) as val,'Sale' as nm from "+tblnm+ 
            		      " where depo_code ="+depo_code+" and mkt_year="+eyear+"  group by mnth_code) a, monthfl m"+
            		      " where a.mnth_code=m.mnth_ord and m.mkt_year="+eyear+
            		      " order by a.mnth_code,a.srno ";
*/
            String query= " select a.val,a.nm,m.mnth_abbr from ( "+ 
		      " select 1 as srno,mnth_code,sum(tarval) as val,'Target' as nm from "+tblnm+ 
		      " where depo_code ="+depo_code+" and mkt_year="+eyear+"  group by mnth_code"+ 
		      " union all "+
		      " select 2 as srno, mnth_code,sum(salval) as val,'Sale' as nm from "+tblnm+ 
		      " where depo_code ="+depo_code+" and mkt_year="+eyear+"  group by mnth_code" +
		      " union all "+
		      " select 3 as srno, mnth_code,sum(lysval) as val,'Last Year Sale' as nm from "+tblnm+ 
		      " where depo_code ="+depo_code+" and mkt_year="+eyear+"  group by mnth_code" +
		      ") a, monthfl m"+
		      " where a.mnth_code=m.mnth_ord and m.mkt_year="+eyear+
		      " order by a.mnth_code,a.srno ";

            
            st = con.createStatement();
			rs = st.executeQuery(query);
			
			
            dataset = new DefaultCategoryDataset();

            while (rs.next())
            {
               dataset.setValue(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            
			rs.close();
			st.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLGraphGenDAO.getGraphGen " + e);
		}
		
		finally {
			try {
	            if(rs != null){ rs.close();}
	            if(st != null){st.close();}
	            if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in SQLGraphGenDAO.Connection.close "+e);
			  }
		}		
		
		return dataset;
	}

}
