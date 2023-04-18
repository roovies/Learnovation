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
                                <input type='file' name="videoFile">
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
                            <input type='file' name="videoFile">
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