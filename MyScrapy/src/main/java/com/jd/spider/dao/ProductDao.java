package com.jd.spider.dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;

import com.jd.spider.item.Product;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ProductDao {
	public static ComboPooledDataSource dataSource = null;
	
	static {
		
		dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://192.168.162.128:3306/jd_spider?characterEncoding=utf-8");
		dataSource.setMaxPoolSize(20);
		
	}

	
	public void save2db(Product product) throws SQLException{
		QueryRunner qr = new QueryRunner(dataSource);	
		
		String sql = "INSERT INTO `jd_spider`.`jd_product` (`id`, `name`, `price`, `url`) VALUES (?,?,?,?)";
		qr.update(sql,product.getId(), product.getName(), product.getPrice(), product.getUrl());
	
	}
	

}
