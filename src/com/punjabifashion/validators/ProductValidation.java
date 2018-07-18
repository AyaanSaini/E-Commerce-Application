package com.punjabifashion.validators;

import com.punjabifashion.beans.Product;

public class ProductValidation {
	
	public String isValidProduct(Product product){
		String message = "Fine";
		if(product.getGender() == null)
			message = "gender";
		if(product.getCategory() == null)
			message = "category";
		if(product.getColors() == null)
			message = "colors";
		if(product.getDescription() == null)
			message = "description";
		if(product.getName() == null)
			message = "name";
		/*if(product.getPhoto() == null)
			message = "image";*/
		if(product.getMrp() == null)
			message = "price";
		if(product.getDiscount() == null)
			message = "discount";
		
		return message;
		
	}

}
