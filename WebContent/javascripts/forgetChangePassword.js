function validateUser(formType){
	//alert("Hello from validate user");
	if(formType === 'signup' ){
		//alert("signup");
		var pwd = document.getElementById('pwd').value;
		var cnfpwd = document.getElementById('cnfpwd').value;
		var q1 = document.getElementById('q1').value;
		var q2 = document.getElementById('q2').value;
		//alert(pwd+" "+cnfpwd+" "+q1+" "+q2);
		if(pwd === cnfpwd){
			//alert("password are same");
			if(q1 !== q2){
				//alert("ready to submit Signup");
				document.getElementById("signup").submit();
			}
			else{
				//alert("questions");
				document.getElementById("err").innerHTML = 'Both the questions should not be same';
			}
		}else{
			//alert("password");
			document.getElementById("err").innerHTML = 'Password and Confirm Password must be same';
		}
	}
	
	if(formType === 'changePwd'){
		//alert("changePwd");
		var pwd = document.getElementById('pwd').value;
		var cnfpwd = document.getElementById('cnfpwd').value;
		if(pwd === cnfpwd){
			//alert("ready to submit changePwd");
			document.getElementById("changePwd").submit();
		}
		else{
			//alert("password");
			document.getElementById("err").innerHTML = 'Password and Confirm Password must be same';
		}
	}
	if(formType === 'forgetPwd'){
		//alert("forgetPwd");
		var q1 = document.getElementById('q1').value;
		var q2 = document.getElementById('q2').value;
		if(q1 !== q2){
			//alert("ready to submit forgetPwd");
			document.getElementById("forgetPwd").submit();
		}
		else{
			//alert("fgghhjjkll");
			document.getElementById("err").innerHTML = 'Both the questions should not be same';
		}
	}
	
	if(formType === 'forgetChangePwd'){
		//alert("forgetChangePwd");
		var pwd = document.getElementById('newpwd').value;
		var cnfpwd = document.getElementById('cnfnewpwd').value;
		if(pwd === cnfpwd){
			//alert("ready to submit forgetchangePwd");
			document.getElementById("forgetChangePwd").submit();
		}
		else{
			document.getElementById("err").innerHTML = 'Password and Confirm Password must be same';
		}
	}
	//alert("outside");
}