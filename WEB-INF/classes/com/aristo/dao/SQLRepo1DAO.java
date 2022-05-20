package com.aristo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.Repo1FormBean;


public class SQLRepo1DAO implements Repo1DAO {
	
	public List getAllrepo(Connection con, String typ,int loginid,int eyear) { 
		 
		Repo1FormBean rfb;
        PreparedStatement ps=null; 
        ResultSet rst=null;
        PreparedStatement ps1=null; 
        ResultSet rst1=null;
		
		List<Repo1FormBean> data = new ArrayList<Repo1FormBean>();
		try {     
            String tblnm=null;
            String tblnm2=null;
            String tblnm4=null;
            
            tblnm4="user_branch08";
            
        	tblnm=(typ+"_target08").toLowerCase();
    	    tblnm2=(typ+"_branch08").toLowerCase();
			String query = "select sum(p.ra10),sum(p.ra11),sum(p.ra12),sum(p.ra1),sum(p.ra2),sum(p.ra3)," +
			"sum(p.ra4),sum(p.ra5),sum(p.ra6)" +
			",sum(p.ra7),sum(p.ra8),sum(p.ra9)," +
			"p.depo_code  from "+tblnm+" as p " +
			" where mkt_year=? group by p.depo_code order by p.depo_code";  
			ps = con.prepareStatement(query);
			ps.setInt(1,eyear);
			rst = ps.executeQuery();
			
//////////////////////////////User Branch Master ki Query/////////////////////////////////
            String query22 = "Select d.depo_code,t.ter_name,t.no_of_rep from "+tblnm4+" as d, "+tblnm2+" as t where d.depo_code=t.depo_code and d.user_id=? and d.status=? and d.depo_code=?  ";
	        ps1 = con.prepareStatement(query22);
//			String query1 = "select t.ter_name,t.no_of_rep from a_branch08 as t where t.depo_code=? order by ter_name ";
//	        PreparedStatement ps1 = con.prepareStatement(query1); 
			  
			while (rst.next()) ///
			{
				rfb = new Repo1FormBean();
		        ps1.setInt(1,loginid);
		        ps1.setString(2, "Y");
				ps1.setInt(3, rst.getInt(13));
				
		        rst1 = ps1.executeQuery();
		        if(rst1.next())
		        {
		        rfb.setPool(rst1.getString(2));
		        rfb.setNo_of_mr(rst1.getInt(3)); 
		        rfb.setOct(rst.getInt(1));
		        rfb.setNov(rst.getInt(2));
		        rfb.setDec(rst.getInt(3));
		        rfb.setJan(rst.getInt(4));
		        rfb.setFeb(rst.getInt(5));
		        rfb.setMar(rst.getInt(6));
		        rfb.setApr(rst.getInt(7));
		        rfb.setMay(rst.getInt(8));
		        rfb.setJun(rst.getInt(9));
		        rfb.setJul(rst.getInt(10));
		        rfb.setAug(rst.getInt(11));
		        rfb.setSep(rst.getInt(12)); 
		        data.add(rfb);
		        
		        }
				
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo14DAO.getAllrepo14 " + e);
		}
		finally 
		{
		try {
			if(rst != null){ rst.close();}   
			if(ps != null){ps.close();}
			if(rst1 != null){ rst1.close();}			
			if(ps1 != null){ps1.close();}
            if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo14DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}

	public List getCheckList(Connection con, String typ,int loginid,int eyear) { 
		 
		Repo1FormBean rfb;
        PreparedStatement ps=null; 
        ResultSet rst=null;
		
		List<Repo1FormBean> data = new ArrayList<Repo1FormBean>();
		try {     
            String query=null;
			String tblnm=null;
            String tblnm1=null;
            String tblnm2=null;
            String tblnm3=null;
               
        	tblnm=typ+"_target08";
        	tblnm1=typ+"_stockiest08";        	
        	tblnm2=typ+"_hqdetail08";    	    
        	tblnm3=typ+"_branch08";
    	    
    	    query="SELECT B.TXT,A.* FROM (select depo_code,'STK' as rep, " +
    	    "ROUND(sum(valoct-EVALoct-BVALoct-RVALoct-SVALoct-PVALoct),0) october, " +
    	    "ROUND(sum(valnov-EVALNOV-BVALNOV-RVALNOV-SVALNOV-PVALNOV),0) november, " +
    	    "ROUND(sum(VALDEC-EVALDEC-BVALDEC-RVALDEC-SVALDEC-PVALDEC),0) december, " +
    	    "ROUND(sum(valjan-EVALJAN-BVALJAN-RVALJAN-SVALJAN-PVALJAN),0) january, " +
    	    "ROUND(sum(VALFEB-EVALFEB-BVALFEB-RVALFEB-SVALFEB-PVALFEB),0) februry, " +
    	    "ROUND(sum(valmar-EVALmar-BVALmar-RVALmar-SVALmar-PVALmar),0) march, " +
    	    "ROUND(sum(valapr-EVALapr-BVALapr-RVALapr-SVALapr-PVALapr),0) april, " +
    	    "ROUND(sum(valmay-EVALmay-BVALmay-RVALmay-SVALmay-PVALmay),0) may, " +
    	    "ROUND(sum(valjun-EVALjun-BVALjun-RVALjun-SVALjun-PVALjun),0) june, " +
    	    "ROUND(sum(valjul-EVALjul-BVALjul-RVALjul-SVALjul-PVALjul),0) july, " +
    	    "ROUND(sum(valaug-EVALaug-BVALaug-RVALaug-SVALaug-PVALaug),0) august, " +
    	    "ROUND(sum(valsep-EVALSEP-BVALSEP-RVALSEP-SVALSEP-PVALSEP),0) september " +
    	    "from "+tblnm1+" where mkt_year ="+eyear+" group by depo_code " +
    	    "union all "+

    	    "select depo_code,'HQT' as rep," +
    	    "ROUND(sum(valoct-EVALoct-BVALoct-RVALoct-SVALoct-PVALoct),0) october, " +
    	    "ROUND(sum(valnov-EVALNOV-BVALNOV-RVALNOV-SVALNOV-PVALNOV),0) november, " +
    	    "ROUND(sum(VALDEC-EVALDEC-BVALDEC-RVALDEC-SVALDEC-PVALDEC),0) december, " +
    	    "ROUND(sum(valjan-EVALJAN-BVALJAN-RVALJAN-SVALJAN-PVALJAN),0) january, " +
    		"ROUND(sum(VALFEB-EVALFEB-BVALFEB-RVALFEB-SVALFEB-PVALFEB),0) februry, " +
    		"ROUND(sum(valmar-EVALmar-BVALmar-RVALmar-SVALmar-PVALmar),0) march, " +
    		"ROUND(sum(valapr-EVALapr-BVALapr-RVALapr-SVALapr-PVALapr),0) april, " +
    		"ROUND(sum(valmay-EVALmay-BVALmay-RVALmay-SVALmay-PVALmay),0) may, " +
    		"ROUND(sum(valjun-EVALjun-BVALjun-RVALjun-SVALjun-PVALjun),0) june, " +
    		"ROUND(sum(valjul-EVALjul-BVALjul-RVALjul-SVALjul-PVALjul),0) july, " +
    		"ROUND(sum(valaug-EVALaug-BVALaug-RVALaug-SVALaug-PVALaug),0) august, " +
    		"ROUND(sum(valsep-EVALSEP-BVALSEP-RVALSEP-SVALSEP-PVALSEP),0) september " +
    		"from "+tblnm2+" where mkt_year ="+eyear+" group by depo_code " +

    		"UNION ALL " +
    		"select depo_code,'TAR' as rep, " +
    		"ROUND(sum(RA10),0) october,  " +
    		"ROUND(sum(RA11),0) november,  " +
    		"ROUND(sum(RA12),0) december,  " +
    		"ROUND(sum(RA1),0) january,  " +
    		"ROUND(sum(RA2),0) februry,  " +
    		"ROUND(sum(RA3),0) march,  " +
    		"ROUND(sum(RA4),0) april,  " +
    		"ROUND(sum(RA5),0) may,  " +
    		"ROUND(sum(RA6),0) june,  " +
    		"ROUND(sum(RA7),0) july,  " +
    		"ROUND(sum(RA8),0) august,  " +
    		"ROUND(sum(RA9),0) september  " +
    		"from "+tblnm+" where mkt_year = "+eyear+" group by depo_code " +
    		") A, "+tblnm3+" B WHERE a.DEPO_CODE = B.DEPO_CODE ORDER BY b.txt,a.rep";

			ps = con.prepareStatement(query);
			rst = ps.executeQuery();
			  
			while (rst.next())
			{
				rfb = new Repo1FormBean();
		        rfb.setBr_name(rst.getString(1));
		        rfb.setPool(rst.getString(3)); 
		        rfb.setOct(rst.getInt(4));
		        rfb.setNov(rst.getInt(5));
		        rfb.setDec(rst.getInt(6));
		        rfb.setJan(rst.getInt(7));
		        rfb.setFeb(rst.getInt(8));
		        rfb.setMar(rst.getInt(9));
		        rfb.setApr(rst.getInt(10));
		        rfb.setMay(rst.getInt(11));
		        rfb.setJun(rst.getInt(12));
		        rfb.setJul(rst.getInt(13));
		        rfb.setAug(rst.getInt(14));
		        rfb.setSep(rst.getInt(15)); 
		        data.add(rfb);
			}
			rst.close();
			ps.close();
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLRepo1DAO.getCheckList " + e);
		}
		finally 
		{
		try {
			if(rst != null){ rst.close();}   
			if(ps != null){ps.close();}
            if(con != null){con.close();}
			} 
		catch (SQLException e) 
			  {
				System.out.println("--Exception in SQLRepo1DAO.Connection.close "+e);
			  }
		}
		
		return data;
	}	
	
	
}
