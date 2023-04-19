// 이메일 검증
document.querySelector("#email").addEventListener("focusout", ()=>{
    let email = document.querySelector("#email").value;
    $.ajax({
        type: 'POST',
        url: '/course/register/checkemail',
        contentType: 'application/json',
        data: JSON.stringify({
            'email': email
        }),
        success: function(user){
            if(user.length != 0){
                document.querySelector("#email-alert").innerHTML = "["+user.name+"] 회원님 확인되었습니다.";
                document.querySelector("#email-alert").style.color = "green";
            }
            else{
                document.querySelector("#email-alert").innerHTML = "존재하지 않는 회원입니다.";
                document.querySelector("#email-alert").style.color = "red";
            }
        }
    })
});

// 가격 콤마 메소드
document.querySelector("#price").addEventListener("input", (event) =>{
    var price = document.querySelector("#price").value;
    const koreanPattern = /[^0-9,]/;
    if(koreanPattern.test(price)){
        document.querySelector("#price").value = "";
        document.querySelector("#price-alert").style.display = "block";
    }
    else{
        document.querySelector("#price-alert").style.display = "none";
        price = price.replace(/[^0-9]/g, '');
        price = parseInt(price);
        if(!isNaN(price)){
            document.querySelector("#price").value = price.toLocaleString();
        }
        if(price>999999999){
            document.querySelector("#price").value = "999,999,999";
        }
    }
});

// Summer Note
$(document).ready(function() {
    //여기 아래 부분
    $('#summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        height: 600,
        disableResizeEditor: true,
        lang: "ko-KR",					// 한글 설정
        placeholder: "<br>강의 상세 페이지에 보여질 홍보물을 작성해주세요.<br>동영상 첨부는 아래 [목차추가] 항목을 이용해주세요.",
        toolbar: [ // 에디터 수정
            // [groupName, [list of button]]
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['forecolor','color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['picture','link','video']],
            ['view', ['fullscreen', 'help']]
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
    });
});

// 목차 추가
let chapter = 0;
let lesson = 0;
var chapterAdd = ()=>{
    chapter += 1;
    lesson = 0;
    let tags = `<div id="area`+chapter+`">
                            <div class='chapter-input'>
                                <input type='text' name='chapter' class='chapter' placeholder='`+(chapter+1)+`번째 목차를 입력하세요.'>
                                <span class='lesson-add' onclick="lessonAdd(`+chapter+`)">➕</span>
                                <span class="lesson-add" onclick="lessonRemove(`+chapter+`)">➖</span>
                            </div>
                            <div class='lesson-input'>
                                <input type='text' name='lesson[`+chapter+`][`+lesson+`]' class='lesson' placeholder='강의명을 입력하세요.'>
<!--                                <input type='file' name="uploadFile[`+chapter+`][`+lesson+`]">-->
                                <input type='file' name="videoFile" accept="video/*">
                            </div>
                        </div>`;
    $("#dynamic-area").append(tags);
}

// 목차 제거 (마지막으로 추가된 순으로)
var chapterRemove = ()=>{
    if(chapter<1){
        alert("최소 하나 이상의 목차를 작성해야 합니다.")
        return false;
    }
    document.querySelector("#dynamic-area").lastChild.remove();
    chapter -= 1;
}


// 강의 추가
var lessonAdd = (chapter)=>{
    let lessonTags = document.querySelectorAll("#area"+chapter+" .lesson");
    let count = lessonTags.length;
    let tags = `<div class='lesson-input'>
                            <input type='text' name='lesson[`+chapter+`][`+count+`]' class='lesson' placeholder='강의명을 입력하세요.'>
<!--                            <input type='file' name="uploadFile[`+chapter+`][`+count+`]">-->
                            <input type='file' name="videoFile" accept="video/*">
                        </div>
                        `;
    $("#area"+chapter).append(tags);
}

// 강의 제거 (마지막에 추가된 순으로)
var lessonRemove = (chapter)=>{
    let lessonTags = document.querySelectorAll("#area"+chapter+" .lesson");
    let count = lessonTags.length;
    if(count===1){
        alert("최소 하나 이상의 강의를 작성해야 합니다.")
        return false;
    }
    document.querySelector("#area"+chapter).lastChild.remove();
}

// 유효성 체크
var validCheck = ()=>{
    if(document.querySelector("#title").value === ""){
        alert("강의명을 입력하세요.")
        document.querySelector("#title").focus();
        return false;
    }
    else if(document.querySelector("#email").value === ""){
        alert("이메일을 입력하세요.");
        document.querySelector("#email").focus();
        return false;
    }
    else if(document.querySelector("#category").value === "default"){
        alert("강의 분류를 선택하세요.");
        document.querySelector("#category").focus();
        return false;
    }
    else if(document.querySelector("#level").value === "default"){
        alert("난이도를 선택하세요.");
        document.querySelector("#level").focus();
        return false;
    }
    else if(document.querySelector("#price").value === ""){
        alert("가격을 입력하세요.");
        document.querySelector("#price").focus();
        return false;
    }
    else if(document.querySelector("#summernote").value === ""){
        alert("강의 상세정보를 입력하세요.");
        document.querySelector("#summernote").focus();
        return false;
    }
    else if(!checkChapter()){
        return false;
    }
    else if(!checkLesson()){
        return false;
    }
    else if(!checkFileType()){
        return false;
    }
    alert("모든 조건 클리어");
    return false;
}

// 목차 체크
var checkChapter = () =>{
    let chapterTags = document.querySelectorAll(".chapter");
    for (let i = 0; i<chapterTags.length; i++){
        if(chapterTags[i].value===""){
            alert((i+1)+"번째 목차를 입력하세요.");
            chapterTags[i].focus();
            return false;
        }
    }
    return true;
}

// 강의명 체크
var checkLesson = () =>{
    let lessonTags = document.querySelectorAll(".lesson");
    for (let i = 0; i<lessonTags.length; i++){
        if(lessonTags[i].value===""){
            alert((i+1)+"번째 강의를 입력하세요.");
            lessonTags[i].focus();
            return false;
        }
    }
    return true;
}

// 파일 확장자 체크
var checkFileType = ()=> {
    const file = document.querySelector("[type=file]").files[0];
    const allowedTypes = ['video/mp4', 'video/mov', 'video/wmv', 'video/avi'];
    if (!allowedTypes.includes(file.type)) {
        alert('동영상 파일만 업로드 가능합니다.');
        document.querySelector("[type=file]").value = null;
        return false;
    }
    return true;
}