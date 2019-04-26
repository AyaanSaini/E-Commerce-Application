var gender = null;
var category = null;
var sizes = null;
var titleLike = null;
var filter = null;
function genderSelect(genderClicked){
	gender =  genderClicked;
	sendForFilter();
}

function categorySelect(categoryClicked){
	category =  categoryClicked;
	sendForFilter();
}

function siezsSelect(){
	var size = document.forms["sizesForm"];
	for(var i=0;i<size.length;i++){
		if(size[i].checked){
			sizes = sizes+size[i].value+",";
		}
		////alert("sizes = "+sizes);
	}
	sendForFilter();
}

function searchTitle(){
	titleLike = document.getElementById("titleLike").value;
	//alert("Title = "+titleLike);
	sendForFilter();
}

function sendForFilter(){
	
	if(titleLike != null || titleLike != 'undefine'){
		document.getElementById("titleHidden").value = titleLike;
	}
	if(gender !=null || gender != 'undefined'){
		document.getElementById("genderHidden").value = gender;
	}
	if(category !=null || category != 'undefined'){
		document.getElementById("categoryHidden").value = category;
	}
	if(sizes !=null || sizes != 'undefined'){
		document.getElementById("sizesHidden").value = sizes;
	}
	
	//alert("title = "+document.getElementById("titleHidden").value +"\ngender = "+document.getElementById("genderHidden").value +"\ncategory = "+document.getElementById("categoryHidden").value +"\nsizes = "+document.getElementById("sizesHidden").value);
	//alert("ready to submit");
	document.getElementById("filterProductForm").submit();
	
	
	
}