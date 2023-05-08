function validateEmail() {
	var email = document.getElementById("email");
	var regExp = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+$/;  // 이메일 주소 정규식

	if (!regExp.test(email.value)) {
		alert("이메일 형식에 맞게 입력해주세요.");
		email.value = "";
		return false;
	}
	return true;
}

