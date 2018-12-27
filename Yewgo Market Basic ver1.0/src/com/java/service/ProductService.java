package com.java.service;

import java.sql.SQLException;
import java.util.List;

import com.java.bean.Product;
import com.java.dao.ProductDao;
import com.java.utils.DataSourceUtils;

public class ProductService {

	public List<Product> findAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		List<Product>list=pd.findAllProducts();
		return list;
	}

	public void savePro(Product pro) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		pd.savePro(pro);
	}

	public Product getProById(String id) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		
		return pd.getProById(id);
	}

	public void updatePro(Product product) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		pd.updatePro(product);
	}

	public void deletePro(String id) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		pd.deletePro(id);
	}

	public void delCheck(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		try {
			DataSourceUtils.beginTransaction();
			ProductDao pd = new ProductDao();
			for (String id : ids) {
				pd.delCheck(id);
			}
			DataSourceUtils.commitAndClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	public List<Product> search(String name, String kw) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao pd = new ProductDao();
		return pd.search(name,kw);
	}

}
