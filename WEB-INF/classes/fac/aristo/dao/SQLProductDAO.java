package fac.aristo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fac.aristo.valueobject.ProductFormBean;

public class SQLProductDAO {

	public List getAllProduct(Connection con, String typ,String sr) { 
		 
		ProductFormBean pfb;
		PreparedStatement ps = null;
		ResultSet rst=null;
		
		List<ProductFormBean> data = new ArrayList<ProductFormBean>();
		try
		{ 
        String tblnm=null;
        String query=null;
       	tblnm="f_product";

       	if (sr==null)
        {	
 		query = "select * from "+tblnm+" where substring(f_pcode,4,1) <> 'E' and (division='"+typ+"' or division is null) order by pname ";
        }
        else
        {
        query = "select * from "+tblnm+" where pname like '"+sr+"%' and substring(f_pcode,4,1) <> 'E' and (division='"+typ+"' or division is null) order by pname ";
        }         	
		ps = con.prepareStatement(query);
		rst = ps.executeQuery();
		while (rst.next())
			{
			pfb = new ProductFormBean(); 
			pfb.setF_pcode(rst.getString(1));
			pfb.setF_pname(rst.getString(2));
			pfb.setPcode(rst.getInt(3));
			pfb.setPname(rst.getString(4));
			pfb.setPack(rst.getString(5));
			pfb.setLink("Edit");
			data.add(pfb); 
			}
			
		rst.close();
		ps.close();
			
		} catch (Exception e) {
			
			System.out.println("========Exception in Factory SQLProductDAO.getAllProduct " + e);
		}
		finally {
			try {
	             if(rst != null){ rst.close();}
	             if(ps != null){ ps.close();}
	             if(con != null){con.close();}
			} catch (SQLException e) {
				System.out.println("-------------Exception in Factory SQLProductDAO.Connection.close "+e);
			  }
		}	
		return data;
	}
	
	public ProductFormBean EditProduct(Connection con,String typ, String fpcode)
	{
		ProductFormBean lf = null;
		PreparedStatement ps =null;
		ResultSet rs =null;		
		try {
			String tblnm=null;
			tblnm="f_product";			
			ps = con.prepareStatement("Select * from "+tblnm+" where f_pcode=? ");
			ps.setString(1,fpcode);
			rs = ps.executeQuery();   
			if (rs.next())
			{
                lf = new ProductFormBean();
            	lf.setF_pcode(rs.getString(1));
    			lf.setF_pname(rs.getString(2));			
    			lf.setPcode(rs.getInt(3));
    			lf.setPname(rs.getString(4));    						
    			lf.setPack(rs.getString(5));
    			lf.setDivision(rs.getString(6));
    			lf.setTp(rs.getString(7));
			}
						
			rs.close();
			ps.close();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		finally {
			try 
			{
		           if(ps != null){ps.close();}
		           if(rs != null){ rs.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in FAC-SQLProductDAO-EditProduct.Connection.close "+e);
			  }
		}	
		  
		return lf ; 
		
	}	
	
	public int UpdateProduct(Connection con,ProductFormBean tf, String typ)
	{
		PreparedStatement ps =null;
		int i=0;
		try {
			String tblnm=null;
			String tblnm1=null;
			tblnm="f_product";
			tblnm1="f_trans";

			// Product Master Update ////////////////////////////////
			ps = con.prepareStatement("update "+tblnm+" set pcode=?,pname=?,pack=?,division=?,type=? where f_pcode=? ");
			ps.setInt(1,tf.getPcode());
			ps.setString(2,tf.getPname());
			ps.setString(3,tf.getPack());
			ps.setString(4,tf.getDivision());
			ps.setString(5,tf.getTp());
			ps.setString(6,tf.getF_pcode());			
			i=ps.executeUpdate();
			ps.close();

			// Transaction File Update /////////////////////
			ps = con.prepareStatement("update "+tblnm1+" set pcode=?,division=? where productid=? ");
			ps.setInt(1,tf.getPcode());
			ps.setString(2,tf.getDivision());
			ps.setString(3,tf.getF_pcode());			
			i=ps.executeUpdate();
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try 
			{
		           if(ps != null){ps.close();}
		           if(con != null){con.close();}
			}
			catch (SQLException e) {
				System.out.println("-------------Exception in Fac-SQLProductDAO-UpdateProduct.Connection.close "+e);
			  }
		}	
		  
		return i ; 
		
	}		
	
	
	
}
