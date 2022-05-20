package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

import com.aristo.valueobject.ProductFormBean;

public interface ProductDAO {
	public List getAllProduct(Connection con, String tp,int eyear);
	public int AddProduct(ProductFormBean b, Connection con,String typ,int eyear);	
}
