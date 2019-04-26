/**
 * 
 */


var genderG;
var categoryG;
var fitG;
var sizeG;
var colorG;

$(function () { $('#size1').multiselect({ }); });
$(function () { $('#size2').multiselect({ }); });
$(function () { $('#size3').multiselect({ }); });
$(function () { $('#size4').multiselect({ }); });

 function genderFun(gender){
	 //alert(gender);
	 genderG = gender;
	 //alert(gender+"Selected");
	 
	 document.getElementById("category").selectedIndex = 0;
	 
	 
	 if(gender == 'men' || gender == 'kids'){
		 //alert(gender);
		 document.getElementById("category").options[4].hidden = true;
		 document.getElementById("category").options[5].hidden = true;
		 
	 }
	 else{
		 document.getElementById("category").options[4].hidden = false;
		 document.getElementById("category").options[5].hidden = false;
	 }
	 if(gender == 'kids'){
		 /*document.getElementById("fitdiv").options[1].hidden = true;
		 document.getElementById("fitdiv").options[2].hidden = true;
		 document.getElementById("fitdiv").options[3].hidden = true;*/
		 
	 }
	 //document.getElementById("category").disabled = false;
	 
 }
 
 function stitchedFun(){
	 var stitched = document.getElementById("sel-stitched").value;
	 if(stitched == 'true'){
		 document.getElementById("fitdiv").style.display = 'none';
	 }
	 else{
		 document.getElementById("fitdiv").style.display = 'block';
	 }
 }
 
 function categoryFun(){
	 //alert(genderG);
	 var category = document.getElementById("category").value;
	 
	 if(genderG == 'kids' && category == 'footwear' ){
		 //alert("kids footwear");	 
		 document.getElementById("kids-clothing-sizes").style.display = 'none';
		 document.getElementById("clothing-sizes").style.display = 'none';
		 document.getElementById("kids-footwear-sizes").style.display = 'block';
		 document.getElementById("footwear-sizes").style.display = 'none';
		 document.getElementById("stitched").style.display = 'none';
		 document.getElementById("fitdiv").style.display = 'none';
		 document.getElementById("color").style.display = 'block';
	 }
	 
	 else if(genderG == 'kids' && category == 'clothing'){
		 //alert("kids clothing");
		 document.getElementById("kids-clothing-sizes").style.display = 'block';
		 document.getElementById("clothing-sizes").style.display = 'none';
		 document.getElementById("kids-footwear-sizes").style.display = 'none';
		 document.getElementById("footwear-sizes").style.display = 'none';
		 document.getElementById("stitched").style.display = 'none';
		 document.getElementById("fitdiv").style.display = 'none';
		 document.getElementById("color").style.display = 'block';
	 }
	 else if(category == 'footwear'){
		// alert("adult footwear");
		 document.getElementById("kids-clothing-sizes").style.display = 'none';
		 document.getElementById("clothing-sizes").style.display = 'none';
		 document.getElementById("kids-footwear-sizes").style.display = 'none';
		 document.getElementById("footwear-sizes").style.display = 'block';
		 document.getElementById("stitched").style.display = 'none';
		 document.getElementById("fitdiv").style.display = 'none';
		 document.getElementById("color").style.display = 'block';
	 }
	 else if(category == 'gottaPatti'){
		 //alert("gota patti");
		 document.getElementById("kids-clothing-sizes").style.display = 'none';
		 document.getElementById("clothing-sizes").style.display = 'none';
		 document.getElementById("kids-footwear-sizes").style.display = 'none';
		 document.getElementById("footwear-sizes").style.display = 'none';
		 document.getElementById("stitched").style.display = 'none';
		 document.getElementById("fitdiv").style.display = 'none';
		 document.getElementById("color").style.display = 'block';
		 
	 }
	 else if(category == 'jewellary'||category == 'bangles'){
		// alert("jewellay bangles");
		 document.getElementById("kids-clothing-sizes").style.display = 'none';
		 document.getElementById("clothing-sizes").style.display = 'none';
		 document.getElementById("kids-footwear-sizes").style.display = 'none';
		 document.getElementById("footwear-sizes").style.display = 'none';
		 document.getElementById("stitched").style.display = 'none';
		 document.getElementById("fitdiv").style.display = 'none';
		 document.getElementById("color").style.display = 'none';
	 }
	 else if(category == 'clothing'){
		// alert("adult clothing");
		 document.getElementById("kids-clothing-sizes").style.display = 'none';
		 document.getElementById("clothing-sizes").style.display = 'block';
		 document.getElementById("kids-footwear-sizes").style.display = 'none';
		 document.getElementById("footwear-sizes").style.display = 'none';
		 document.getElementById("stitched").style.display = 'block';
		 document.getElementById("fitdiv").style.display = 'block';
		 document.getElementById("color").style.display = 'block';
	 }
	 else{
		// alert("else");
		 document.getElementById("fitdiv").hidden = false;
			// document.getElementById("sizes").hidden = false;
			 document.getElementById("color").hidden = false;
			 document.getElementById("stitched").hidden = true;
			 document.getElementById("fitdiv").hidden = true;
			 document.getElementById("clothing-sizes").hidden = false;
			 document.getElementById("kids-clothing-sizes").hidden = true;
			 document.getElementById("footwear-sizes").hidden = true;
			 document.getElementById("kids-footwear-sizes").hidden = true;
	 }
 }

 
 function addProductSubmit(){
	 var photopath = document.getElementById("photoPathDisplay").value;
	 //alert("Photo Path = "+photopath);
	 if(photopath == null || photopath == undefined || photopath ==""){
		 alert("Photo must be selected to add a product.");
	 }
	 else{
		 document.getElementById("addProductForm").submit();
	 }	 
 }
 