package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.punjabifashion.beans.Product;
import com.punjabifashion.service.ProductService;
import com.punjabifashion.service.ProductServiceImpl;
import com.punjabifashion.validators.ProductValidation;

@WebServlet("/AddProduct")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class AddProductsController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String message;
	ProductValidation productValidation;
	ProductService productService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("-->AddproductController");
		try{
			session = request.getSession();
		}catch(Exception e){
			message = "Session is null please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		if(session.getAttribute("session_user_role") == null){
			message = "User is not a admin please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		else if(session.getAttribute("session_user_role").toString().equalsIgnoreCase("admin")){
			Product product = null;
			int res;
			boolean stitched = false;
			try{
			product = new Product();
			product.setName(request.getParameter("name"));
			product.setDescription(request.getParameter("description"));
			product.setCategory(request.getParameter("category"));
			if(request.getParameter("stitched").equals("true"))
				stitched = true;
			else if(request.getParameter("stitched").equals("false"))
				stitched = false;
			product.setStitched(stitched);
			product.setFit(request.getParameter("fit"));
			product.setMrp(Double.parseDouble(request.getParameter("price")));
			product.setDiscount(Double.parseDouble(request.getParameter("discount")));
			product.setGenders(request.getParameter("gender"));
			String[] a  = request.getParameterValues("size[]");
			product.setSizes(request.getParameterValues("size[]"));
			
			System.out.println("Product_size = "+a[0]);
			
			product.setColors(request.getParameter("color"));
			Part filePart = request.getPart("img[]");
			System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
			product.setPhoto(filePart.getInputStream());
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				if(product != null){
					productValidation = new ProductValidation();
					String validProduct = productValidation.isValidProduct(product);
					System.out.println("AddProductController:Product Validation = "+validProduct);
					if(validProduct.equals("Fine")){
						productService = new ProductServiceImpl();
						res = productService.addProduct(product);
						if(res == 1){
							System.out.println("Product Successfully Added with code"+res);
							message = "Product Successfully Added";
							response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						}
						else if(res == 1){
							System.out.println("Product is Not Successfully Added with code"+res);
							message = "Product Not Added Successfully, Contact Database Admin";
							response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
						}
						else{
							System.out.println("Product is Not Successfully Added with code"+res);
							message = "Product Not Added Successfully,Contact System Admin";
							response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
						}						
					}else{
						System.out.println("Product details are not proper"+validProduct);
						message = "Product Not Added Successfully. Please provide "+validProduct+" field value properly";
						response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
					}
				}
				else{
					System.out.println("Product is null");
					message = "Please provide Product details carefully";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			
		}else{
			message = "Permission Denied";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
	}
}
