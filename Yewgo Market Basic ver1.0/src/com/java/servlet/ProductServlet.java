package com.java.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.java.bean.Product;
import com.java.service.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if("findAll".equals(method)) {
			findAll(request,response);
		}else if("addUI".equals(method)){
			addUI(request,response);
		}else if("add".equals(method)) {
			add(request,response);
		}else if("edit".equals(method)) {
			edit(request,response);
		}else if("update".equals(method)) {
			update(request,response);
		}else if("delete".equals(method)) {
			deletePro(request,response);
		}else if("delCheck".equals(method)) {
			delCheck(request,response);
		}else if("search".equals(method)) {
			search(request,response);
		}
	}


	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String name = request.getParameter("name");
			String kw = request.getParameter("kw");
			ProductService ps = new ProductService();
			List<Product>list= ps.search(name,kw);
			request.setAttribute("list", list);
			request.setAttribute("name", name);
			request.setAttribute("kw", kw);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void delCheck(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String[] ids = request.getParameterValues("id");
		    ProductService ps = new ProductService();
			ps.delCheck(ids);
			
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void deletePro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String pid = request.getParameter("pid");
			ProductService ps = new ProductService();
			ps.deletePro(pid);
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Product product = new Product();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(product, map);
			ProductService ps = new ProductService();
			ps.updatePro(product);
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("id");
			ProductService ps = new ProductService();
			Product pro=ps.getProById(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}



	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Map<String, String[]> map = request.getParameterMap();
			Product pro = new Product();
			BeanUtils.populate(pro, map);
			pro.setPid(UUIDCls.getId());
			pro.setPdate(new Date().toLocaleString());
			ProductService ps = new ProductService();
			ps.savePro(pro);
			request.getRequestDispatcher("/product?method=findAll").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void addUI(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("/product.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProductService ps = new ProductService();
		try {
			List<Product> list=ps.findAllProducts();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getAttribute("e");
			try {
				request.setAttribute("msg","≤È—Ø“≥√Ê¥ÌŒÛ");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}