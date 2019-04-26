function funDelete(title){
	var confirm = confirm("Are you sure want to delete "+title+"?");
	if(confirm){
		document.getElementById("deleteForm").submit();
	}
}

EditProductDetails

function funEdit(title,mrp,discount){
	var confirm = confirm("Are you sure want to Update MRP to "+mrp+"and Discount to"+discount+" of "+title+"?");
	if(confirm){
		document.getElementById("editForm").submit();
	}
}