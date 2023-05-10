//태그 모음
const regionTags = document.querySelectorAll('[name=region]'); // 1. 지역
const occupationTags = document.querySelectorAll('[name=occupation]'); // 2. 업종
const salTpTags = document.querySelectorAll('[name=salTp]'); // 3. 급여
const minPayTag = document.querySelector('[name=minPay]'); // 3-1. 최소 급여
const maxPayTag = document.querySelector('[name=maxPay]'); // 3-2. 최대 급여
const careerTags = document.querySelectorAll('[name=career]'); // 4. 경력
const minCareerMTag = document.querySelector('[name=minCareerM]'); // 4-1. 최소 경력
const maxCareerMTag = document.querySelector('[name=maxCareerM]'); // 4-2. 최대 경력
const workerCntTags = document.querySelectorAll('[name=workerCnt]'); // 5. 사원수



// 급여 항목의 [연봉] 체크 이벤트
salTpTags[1].addEventListener("click", ()=>{
    minPayTag.removeAttribute("readonly");
    maxPayTag.removeAttribute("readonly");
});
salTpTags[0].addEventListener("click", ()=>{
    minPayTag.value = "";
    maxPayTag.value = "";
    minPayTag.setAttribute("readonly", true);
    maxPayTag.setAttribute("readonly", true);
});

// 경력 항목의 [경력] 체크 이벤트
careerTags[2].addEventListener("click", ()=>{
    minCareerMTag.removeAttribute("readonly");
    maxCareerMTag.removeAttribute("readonly");
});
careerTags[0].addEventListener("click", ()=>{
    minCareerMTag.value = "";
    maxCareerMTag.value = "";
    minCareerMTag.setAttribute("readonly", true);
    maxCareerMTag.setAttribute("readonly", true);
});
careerTags[1].addEventListener("click", ()=>{
    minCareerMTag.value = "";
    maxCareerMTag.value = "";
    minCareerMTag.setAttribute("readonly", true);
    maxCareerMTag.setAttribute("readonly", true);
});

// 검색하기 수행
document.querySelector('.jobposting-search-btn').addEventListener("click", ()=>{
    // 1. 지역 필터링
    let region = "";
    let regionChecked = false;
    for (let i = 0; i<regionTags.length; i++){
        if (regionTags[i].checked){
            regionChecked = true;
            region += (regionTags[i].value + "|");
        }
    }
    if (!regionChecked) {
        alert("지역을 최소 1개 이상 선택해주세요.");
        return;
    }

    // 2. 업종 필터링
    let occupation = "024|026";
    let occupationChecked = false;
    if (occupationTags[0].checked && !occupationTags[1].checked){
        occupation = "024";
    } else if (occupationTags[1].checked && !occupationTags[0].checked){
        occupation = "026";
    }
    for (let i = 0; i < occupationTags.length; i++) {
        if (occupationTags[i].checked) {
            occupationChecked = true;
            break;
        }
    }
    if (!occupationChecked) {
        alert("업종을 최소 1개 이상 선택해주세요.");
        return;
    }

    // 3. 급여 필터링
    let salTp = "null";
    let minPay = "null";
    let maxPay = "null";
    let salTpChecked = false;
    if (salTpTags[1].checked){
        salTp = "Y";
        //금액 입력 검증
        if (minPayTag.value==""){
            alert("최소 금액을 입력하세요.");
            return false;
        } else if (maxPayTag.value == ""){
            alert("최대 금액을 입력하세요.");
            return false;
        }
        //최소 금액
        minPay = minPayTag.value;
        //최대금액
        maxPay = maxPayTag.value;
    }



    // 4. 경력 필터링
    let career = "Z";
    let minCareerM = "null";
    let maxCareerM = "null";
    if (careerTags[1].checked){
        career = "N";
    }
    if (careerTags[2].checked){
        career = "E";
        if (minCareerMTag.value == ""){
            alert("최소 경력을 입력하세요.");
            return false;
        } else if (maxCareerMTag.value == ""){
            alert("최대 경력을 입력하세요.");
            return false;
        }
        //최소 경력
        minCareerM = minCareerMTag.value;
        //최대 경력
        maxCareerM = maxCareerMTag.value;
    }

    // 5. 사원수 필터링
    let workerCnt = "null";
    if (workerCntTags[1].checked){
        workerCnt = "W100";
    }

    console.log("선택 지역: " + region);
    console.log("선택 업종: " + occupation);
    console.log("선택 급여: " + salTp);
    console.log("[연봉 선택] 최소 급여: " + minPay +"최대 급여: " + maxPay)
    console.log("선택 경력: " + career);
    console.log("[경력 선택] 최소 경력: " + minCareerM + "최대 경력: " + maxCareerM);
    console.log("선택 사원 수: " + workerCnt);

    const params = new URLSearchParams(window.location.search);
    params.set('region', region);
    params.set('startPage', "1");
    params.set('occupation', occupation);
    params.set('salTp', salTp);
    params.set('minPay', minPay);
    params.set('maxPay', maxPay);
    params.set('career', career);
    params.set('minCareerM', minCareerM);
    params.set('maxCareerM', maxCareerM);
    params.set('workerCnt', workerCnt);
    const newUrl = `/jobposting/list?${params.toString()}`;
    console.log(newUrl);
    window.location.href = newUrl;
});

document.querySelector('.jobposting-initialize-btn').addEventListener("click", ()=>{
    location.href="/jobposting/list";
});
