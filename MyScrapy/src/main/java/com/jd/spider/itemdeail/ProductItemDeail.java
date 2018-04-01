package com.jd.spider.itemdeail;


import java.sql.SQLException;

import com.jd.spider.base.ItemDeail;
import com.jd.spider.dao.ProductDao;
import com.jd.spider.item.Product;

public class ProductItemDeail implements ItemDeail<Product> {

	private ProductDao dao = new ProductDao();

	public void deailItem(Product t) {
		System.out.println(t);
		if(t!=null)
			try {
				dao.save2db(t);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	

}
