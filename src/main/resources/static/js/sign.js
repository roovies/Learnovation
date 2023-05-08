// 휴대폰 번호 입력 부분
/*function changePhone1(){
	const phone1 = document.getElementById("phone1").value // 010
	if(phone1.length === 3){
		document.getElementById("phone2").focus();
	}
}
function changePhone2(){
	const phone2 = document.getElementById("phone2").value // 010
	if(phone2.length === 4){
		document.getElementById("phone3").focus();
	}
}*/
function changePhone3(){
	const phone_number = document.getElementById("phone_number").value // 010
	if(phone_number.length === 11){
		document.getElementById("sendMessage").focus();
		document.getElementById("sendMessage").setAttribute("style","background-color:yellow;")
		document.getElementById("sendMessage").disabled = false;
	}
}




// 가입부분 체크

function signUpCheck(){

	let email = document.getElementById("email").value
	let name = document.getElementById("name").value
	let password = document.getElementById("password").value
	let passwordCheck = document.getElementById("passwordCheck").value
	let nickname = document.getElementById("nickname").value
	let check = true;

	// 이메일확인
	if(email.includes('@')){
		let emailId = email.split('@')[0]
		let emailServer = email.split('@')[1]
		if(emailId === "" || emailServer === ""){
			document.getElementById("emailError").innerHTML="이메일이 올바르지 않습니다."
			check = false
		}
		else{
			document.getElementById("emailError").innerHTML=""
		}
	}else{
		document.getElementById("emailError").innerHTML="이메일이 올바르지 않습니다."
		check = false
	}


	// 이름확인
	if(name===""){
		document.getElementById("nameError").innerHTML="이름이 올바르지 않습니다."
		check = false
	}else{
		document.getElementById("nameError").innerHTML=""
	}


	// 비밀번호 확인
	if(password !== passwordCheck){
		document.getElementById("passwordError").innerHTML=""
		document.getElementById("passwordCheckError").innerHTML="비밀번호가 동일하지 않습니다."
		check = false
	}else{
		document.getElementById("passwordError").innerHTML=""
		document.getElementById("passwordCheckError").innerHTML=""
	}

	if(password===""){
		document.getElementById("passwordError").innerHTML="비밀번호를 입력해주세요."
		check = false
	}else{
		//document.getElementById("passwordError").innerHTML=""
	}
	if(passwordCheck===""){
		document.getElementById("passwordCheckError").innerHTML="비밀번호를 다시 입력해주세요."
		check = false
	}else{
		//document.getElementById("passwordCheckError").innerHTML=""
	}

	// 닉네임확인
	if(nickname===""){
		document.getElementById("nicknameError").innerHTML="닉네임이 올바르지 않습니다."
		check = false
	}else{
		document.getElementById("nicknameError").innerHTML=""
	}

	// 번호확인
	if(name===""){
		document.getElementById("phoneError").innerHTML="번호가 올바르지 않습니다."
		check = false
	}else{
		document.getElementById("phoneError").innerHTML=""
	}



	if(check){
		document.getElementById("emailError").innerHTML=""
		document.getElementById("passwordError").innerHTML=""
		document.getElementById("passwordCheckError").innerHTML=""
		document.getElementById("nameError").innerHTML=""
		document.getElementById("nicknameError").innerHTML=""
		document.getElementById("phoneError").innerHTML=""
	}
	return check;
}
