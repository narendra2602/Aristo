package cen.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aristo.valueobject.ProductFormBean;

public class SQLProductDAO {
	
	public List getAllProduct(Connection con, String typ) { 
		 
		ProductFormBean pfb;
		
		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try { 
			
            String tblnm=null;
            String tblnm1=null;
        	tblnm=(typ+"_prdmsfl").toLowerCase();
       	    tblnm1=(typ+"_groupsales08").toLowerCase();
		
			String query = "select Distinct(p.pcode),p.pname,p.pack,p.pd_group,g.gp_name,p.trd_rt1," +
		       "p.net_rt1,p.mrp_rt1 from " +tblnm +" as p, "+tblnm1+
		       " as g where p.pd_group=g.gp_code  order by p.pname"; 

			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rst = ps.executeQuery();
			 
			while (rst.next())
			{
				pfb = new ProductFormBean(); 
				pfb.setPcode(rst.getInt(1));
				pfb.setPname(rst.getString(2));
				pfb.setPack(rst.getString(3));
				pfb.setPd_group(rst.getInt(4));
				pfb.setMname(rst.getString(5));
				pfb.setTrd_rt1(rst.getFloat(6));
				pfb.setNet_rt1(rst.getFloat(7));
				pfb.setMrp_rt1(rst.getFloat(8));
			
				data.add(pfb);
			}
			
		} catch (Exception e) {
			
			System.out.println("========Exception in SQLProductDAO.getAllProduct " + e);
		}
		
		return data;
	}


}
  