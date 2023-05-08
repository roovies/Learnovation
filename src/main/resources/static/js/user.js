let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
			if(signUpCheck())
				this.save();
		});
		$("#btn-update").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.update();
		});		
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			email: $("#email").val(),
			password: $("#password").val(),
			name: $("#name").val(),
			nickname: $("#nickname").val(),
			phoneNumber: $("#phone_number1").val() + $("#phone_number2").val() + $("#phone_number3").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로
		}).done(function(resp){
			console.log(resp);
			alert("회원가입이 완료되었습니다.");
			location.href = "/"; //    원래 / => 임시로 url입력
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	
	update: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			id: $("#id").val(),

			email: $("#email").val(),
			password: $("#password").val(),
			name: $("#name").val(),
			nickname: $("#3").val(),
			phoneNumber: $("#phoneNumber").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},	
}

index.init();