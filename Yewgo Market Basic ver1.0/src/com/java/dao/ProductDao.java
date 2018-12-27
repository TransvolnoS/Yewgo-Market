package com.java.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.java.bean.Product;
import com.java.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public void savePro(Product pro) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product values (?,?,?,?,?,?,?)";
		qr.update(sql, pro.getPid(),pro.getPname(),pro.getMarket_price(),pro.getShop_price(),pro.getPimage(),pro.getPdate(),pro.getPdesc());
	}

	public Product getProById(String id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid=?";
		Product pro = qr.query(sql, new BeanHandler<Product>(Product.class),id);
		return pro;
	}

	public void updatePro(Product product) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update product set pname=?,market_price=?,shop_price=?,pdesc=? where pid=?";
		qr.update(sql,product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPdesc(),product.getPid());
	}

	public void deletePro(String id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid=?";
		qr.update(sql,id);
	}

	public void delCheck(String id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		String sql="delete from product where pid=?";
		qr.update(DataSourceUtils.getConnection(), sql, id);
	}

	public List<Product> search(String name, String kw) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	   StringBuilder sd = new StringBuilder("select * from product where 1=1");
	   List <String>list=new ArrayList<>();
	   if(name!="") {
		   sd.append(" and pname like ?");
		   list.add("%"+name+"%");
	   }
	   if(kw!="") {
		   sd.append(" and pdesc like ?");
		   list.add("%"+kw+"%");
	   }
	   return qr.query(sd.toString(), new BeanListHandler<Product>(Product.class),list.toArray());
		
	}
}
