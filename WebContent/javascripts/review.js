/**
 * 
 */
function addcomment(){
	var comment = document.getElementById("comment").value;
	if(comment !== "" && comment !== undefined){
		//alert("hello :"+comment);
		document.getElementById("commentSubmit").submit();
	}
	else{
		document.getElementById("err").innerHTML = "Please write somthing in comment section.";
	}
}
