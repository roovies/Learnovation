const modeBtn = document.getElementById('mode-button');
modeBtn.addEventListener('click', function(){
    modeChange();
});
let a = true;
function modeChange(){
    if(a == true){
        modeBtn.style.transform = 'translate(32px)'
        document.getElementById('modeBox').style.backgroundColor = '#374151';
        document.body.style.backgroundColor = '#374151';
    } 
    if(a == false){
        modeBtn.style.transform = 'translate(0px)'
        document.getElementById('modeBox').style.backgroundColor = '#0090F0';
        document.body.style.backgroundColor = 'white';
    }
    a = !a;
}
let num = 0;
const userIconBox = document.getElementById('userIconBox');
const userModal = document.getElementById('infoModal');
if(userIconBox != null && userModal !=null){
    userIconBox.addEventListener('mouseenter',function(){
        userModal.style.display = "block";
        document.getElementById("userHidden").style.display = "block";
        document.getElementById("userIcon").style.color ="#0090F0"
    });
    userIconBox.addEventListener('mouseleave',function(){
        userModal.style.display = "none";
        document.getElementById("userHidden").style.display = "none";
        document.getElementById("userIcon").style.color ="darkgray";
    });

    const cartIconBox = document.getElementById('cartIconBox');
    const cartModal = document.getElementById('navCartModal');

    cartIconBox.addEventListener('mouseenter',function(){
        cartModal.style.display = "block";
        document.getElementById("cartHidden").style.display = "block";
        document.getElementById("cartIcon").style.color ="#0090F0"
    })
    cartIconBox.addEventListener('mouseleave', function(){
        cartModal.style.display = "none";
        document.getElementById("cartHidden").style.display = "none";
        document.getElementById("cartIcon").style.color ="darkgray";
    })
}
const courseLink = document.getElementById('aNavCourse');
const courseLinkModal = document.getElementById('aNavCourseModal');
courseLink.addEventListener('mouseenter', function(){
    courseLinkModal.style.display = "block";
    document.getElementById("aNavCourseHidden").style.display = "block";
    document.getElementById("navCourse").style.color ="#0090F0"
});
courseLink.addEventListener('mouseleave', function(){
    courseLinkModal.style.display = "none";
    document.getElementById("aNavCourseHidden").style.display = "none";
    document.getElementById("navCourse").style.color ="black"
});
const communityLink = document.getElementById('aNavCommunity');
const communityLinkModal = document.getElementById('aNavCommunityModal');
communityLink.addEventListener('mouseenter', function(){
    communityLinkModal.style.display = "block";
    document.getElementById("aNavCommuninyHidden").style.display = "block";
    document.getElementById("navComu").style.color ="#0090F0"
});
communityLink.addEventListener('mouseleave', function(){
    communityLinkModal.style.display = "none";
    document.getElementById("aNavCommuninyHidden").style.display = "none";
    document.getElementById("navComu").style.color ="black"
});




// Chatbot
const chatbotBox = document.querySelector(".chat-bot-box");

// 1. 챗봇 모달 open
chatbotBox.addEventListener("click", ()=>{
    $(".chat-bot-box").hide();
    $(".chat-bot-modal-chatting-input").hide();
    $(".chat-bot-modal").show('slow');
    setTimeout(()=>{
        document.querySelector(".chat-bot-modal-notice").style.display = "block";
    },1000);
    setTimeout(()=>{
        $(".chat-bot-modal-qna").show('faster');
    },1500);
    setTimeout(()=>{
        $(".chat-bot-modal-start").show('faster');
    },2000);
});

// 2. 소켓 연결 전 챗봇 모달 close
document.querySelector("#chat-bot-close-btn").addEventListener("click", ()=>{
    $(".chat-bot-modal").hide();
    $(".chat-bot-modal-chatting").hide();
    $(".chat-bot-box").show();
});


// 3. 챗봇 시작
document.querySelector("#startChat").addEventListener("click", ()=>{
    setTimeout(()=>{
        $(".chat-bot-modal-start").hide('faster');
    }, 500);
    setTimeout(()=>{
        $(".chat-bot-modal-qna").hide('faster');
    }, 1000);
    setTimeout(()=>{
        document.querySelector(".chat-bot-modal-notice").style.display = "none";
    }, 1500);
    setTimeout(()=>{
        document.querySelector(".chat-bot-modal-chatting").style.display = "block";
        document.querySelector(".chat-bot-modal-chatting-input").style.display = "flex";
    }, 2000);

    // 2. 챗봇 소켓 연결
    chatBotSocket();
});

// 챗봇 소켓 연결
let chatBotSocket = ()=>{
    // Web Socket 연결 (챗봇)
    const websocket = new WebSocket("ws://localhost:9999/ws/chatbot");
    websocket.onmessage = onMessage; // 소켓이 메시지를 받을 때
    websocket.onopen = onOpen;      // 소켓이 생성될 때 (클라이언트 접속)
    websocket.onclose = onClose;    // 소켓이 닫힐 때 (클라이언트 접속 해제)

    // 현재 사용자 정보
    let type = "AUTO";
    let sessionId = null;
    const idTag = document.querySelector(".userId");
    const userId = idTag.dataset.id;
    const name = idTag.dataset.name;
    console.log(name);

    function send(){ // 메시지 전송 기능
        let message = document.querySelector("#chatbotMsg").value;
        message.replace("/\n/g", "");
        let toSendMessage = JSON.stringify({
            'type' : type,
            'sessionId' : sessionId,
            'senderId' : userId,
            'message' : message,
            'name' : name
        });
        websocket.send(toSendMessage);
    }
    function onOpen(event){ // 연결 시
        // let msg = JSON.stringify({
        //     'type' : 'ENTER',
        //     'sessionId' : sessionId,
        //     'senderId' : userId,
        //     'message' : '연결되었습니다.'
        // });
        // websocket.send(msg);
        console.log("챗봇 서버에 연결 완료");
    }
    function onClose(event){ // 연결해제 시
        console.log("챗봇 서버와의 연결 종료");
    }
    function onMessage(msg){
        console.log(msg)
        console.log(msg.data)
        let rowMsg = msg.data;
        let jsonMsg = JSON.parse(rowMsg.replace(/\n/g, ''));
        if(jsonMsg.type == "CONNECTION_REQ"){
            type = "TALK";
            sessionId = jsonMsg.sessionId;
            let msg = JSON.stringify({
                'type' : 'CLIENT_ENTER',
                'senderId' : userId,
                'message' : name + '님이 1:1문의를 신청하였습니다.',
                'name' : name
            });
            websocket.send(msg);
        } else if (jsonMsg.type == "CONNECTION_FIN"){
            type = "AUTO";
            sessionId = null;
        }
        let tags = `<div class="chat-bot-modal-chatting-receive">
                        <img class="chat-bot-modal-chatting-receiverImg" src="/common/image/logo.png" alt="">
                        <div class="chat-bot-modal-chatting-receiverMsg">
                            `+jsonMsg.message+`
                        </div>
                    </div>`;
        $(".chat-bot-modal-chatting").append(tags);
        scrollDown();
    }

    // 2-2. 메시지 전송
    document.querySelector(".chat-bot-modal-chatting-sendIcon").addEventListener("click", (event)=>{
        let content = document.querySelector("#chatbotMsg").value;
        if (content == ""){
            alert("메시지를 입력해주세요.");
            return false;
        } else if(content == " "){
            alert("메시지를 입력해주세요.");
            return false;
        }
        let tags = `<div class="chat-bot-modal-chatting-send">
                        <div class="chat-bot-modal-chatting-senderMsg">
                          <p>`+content+`</p>
                        </div>
                    </div>`;
        $(".chat-bot-modal-chatting").append(tags);
        if(scrollDown()){
            send();
            document.querySelector("#chatbotMsg").value = "";
        }
    });

    // 2-3. 엔터키 메시지 전송
    document.querySelector("#chatbotMsg").addEventListener("keyup", (event) =>{
        if(event.keyCode == 13){
            if(!event.shiftKey){
                let content = document.querySelector("#chatbotMsg").value;
                if (content == ""){
                    alert("메시지를 입력해주세요.");
                    return false;
                } else if(content == " "){
                    alert("메시지를 입력해주세요.");
                    return false;
                }
                let tags = `<div class="chat-bot-modal-chatting-send">
                        <div class="chat-bot-modal-chatting-senderMsg">
                          <p>`+content+`</p>
                        </div>
                    </div>`;
                $(".chat-bot-modal-chatting").append(tags);
                send();
                document.querySelector("#chatbotMsg").value = "";
            }
        }
    });

    // 3. 소켓 연결 중 챗봇 모달 close
    document.querySelector("#chat-bot-close-btn").addEventListener("click", ()=>{
        websocket.close();
        $(".chat-bot-modal").hide();
        $(".chat-bot-modal-chatting").hide();
        $(".chat-bot-box").show();
    });

}
//스크롤 자동 내리기
let scrollDown = () =>{
    let scrollDiv = document.querySelector(".chat-bot-modal-chatting");
    scrollDiv.scrollTop = scrollDiv.scrollHeight;
    return true;
}


//-------------------------------- 그룹 톡 -------------------------------
let showGroupModal = false
if(document.getElementById('switchGroupModal') != null){
    document.getElementById('switchGroupModal').addEventListener('click', function (e){
        e.preventDefault();
        e.stopPropagation();
        if(!showGroupModal){
            document.getElementById('groupChatDiamond').style.display = 'block'
            document.getElementById('groupChatDiamond').style.backgroundColor = '#0090F0'
            document.getElementById('groupChatBox').style.display = 'block'
            document.getElementById('groupIcon').classList.add("group-blue");
            let chatBox = document.getElementById('groupRoomBox');
            chatBox.style.display = 'none'
        }else{
            document.getElementById('groupChatDiamond').style.display = 'none'
            document.getElementById('groupChatBox').style.display = 'none'
            document.getElementById('groupIcon').classList.remove('group-blue');
        }
        showGroupModal = !showGroupModal;
    })
}

if(document.getElementById('groupName') != null){
    groupChatList();
}
if(document.getElementById("groupCreateBtn") !=null){
    document.getElementById("groupCreateBtn").addEventListener("click", function (e){
        e.preventDefault();
        e.stopPropagation();
        let groupInfo = document.getElementById('groupName')
        let groupName = groupInfo.value
        let userNo = groupInfo.dataset.id;
        if(groupName == ""){
            alert("그룹이름을 입력해주세요");
        }else{
            $.ajax({
                data : {groupName : groupName, userNo : userNo},
                type : "POST",
                url : "/meeting/create",
                success : function(data) {
                    groupChatList();
                    groupInfo.value = "";
                    alert('그룹 생성 완료')
                },error : function (data){

                }
            });
        }
    })
}


function groupChatList(){
    let groupInfo = document.getElementById('groupName');
    let userNo = groupInfo.dataset.id;
    $.ajax({
        data : {userNo : userNo},
        type : "GET",
        url : "/meeting/list",
        success : function(data) {
            if(data != "fail"){
                let mList = JSON.parse(data);
                let listBox = document.getElementById("groupChatList");
                listBox.innerHTML = "";
                for(let i = 0; i < mList.length; i++){
                    let groupChatContent = document.getElementById("groupChatContent").cloneNode(true);
                    groupChatContent.style.display = "flex";
                    groupChatContent.querySelector(".group-chat-title").innerText = mList[i].name;
                    groupChatContent.querySelector(".group-chat-title").dataset.id = mList[i].id;
                    listBox.append(groupChatContent);
                }
            }
        },error : function (data){

        }
    });
}

function exitGroup(btn){
    let groupNo = btn.parentElement.previousElementSibling.firstElementChild.dataset.id;
    $.ajax({
        data : {groupNo : groupNo},
        type : "GET",
        url : "/meeting/exit",
        success : function(data) {
           // console.log(data);
            groupChatList();
        },error : function (data){

        }
    });
}

//---------------------------------- 채팅방-------------------------------------
if(document.getElementById('userIconBox') != null){
    function openGroupChatting(btn){
        let meetingNo = btn.firstElementChild.dataset.id;
        let userNo = document.getElementById('groupName').dataset.id;
        $.ajax({
            data : {meetingNo : meetingNo},
            url : "/meeting/chat/room",
            type : "GET",
            success : function (data){
                let chatBox = document.getElementById('groupChatBox');
                chatBox.style.display = 'none'
                let roomBox = document.getElementById('groupRoomBox');
                roomBox.dataset.meetingno = meetingNo;
                let roomList = document.getElementById('groupChatRoomList');
                roomList.innerHTML = "";
                roomBox.style.display = 'block'
                document.getElementById('groupChatDiamond').style.backgroundColor = 'rgb(203, 232, 252)';
                showGroupModal = false;
                if(data != "empty"){
                    let mCList = JSON.parse(data);
                    for(let i = 0; i < mCList.length; i++){
                        if(mCList[i].user.id == userNo){
                            let myContentBox = roomBox.querySelector('.group-chat-room-myChatting-box').cloneNode(true);
                            myContentBox.style.display = 'block';
                            let myMessage = document.createElement("span");
                            myMessage.innerText = mCList[i].content;
                            myContentBox.querySelector('.group-chat-myMessage').innerHTML = "";
                            myContentBox.querySelector('.group-chat-myMessage').append(myMessage);
                            roomList.append(myContentBox);
                        }else{
                            let youContentBox = roomBox.querySelector('.group-chat-room-youChatting-box').cloneNode(true);
                            youContentBox.style.display = 'block';
                            let youMessage = document.createElement("span");
                            youMessage.innerText = mCList[i].content;
                            youContentBox.querySelector('.group-chat-message').innerHTML = "";
                            youContentBox.querySelector('.group-chat-room-nickname').innerText = mCList[i].user.nickname;
                            youContentBox.querySelector('.group-chat-message').append(youMessage);
                            roomList.append(youContentBox);
                        }
                    }
                    roomList.scrollTo(0,roomList.scrollHeight - roomList.clientHeight);
                }
            },
            error : function (data){

            }
        })
    }
}

if(document.getElementById('groupRoomBtn') != null){
    document.getElementById('groupRoomBtn').addEventListener('click', function (e){
        e.preventDefault();
        e.stopPropagation();
        let content = document.getElementById('groupChatInputContent').value;
        let userNo = document.getElementById('groupName').dataset.id;
        let groupNo = e.target.parentElement.parentElement.dataset.meetingno;
        $.ajax({
            data : {userNo : userNo, groupNo : groupNo, content : content},
            url : "/meeting/chat/insert",
            type : "POST",
            success : function (data){
                if(data == "success"){
                    getChatRoomList(groupNo, userNo);
                    document.getElementById('groupChatInputContent').value = "";
                    $.ajax({
                        data : {meetingNo : groupNo},
                        url : "/meeting/member/list",
                        type : "GET",
                        success : function (data){
                            let mList = JSON.parse(data);
                            for(let i = 0; i < mList.length; i++){
                                if(mList[i].meetingMemberPk.user.id != userNo){
                                    alarm.send(`{type: "chat", feeder: "${mList[i].meetingMemberPk.user.id}", meetingNo : "${groupNo}"}`);
                                }
                            }
                        },
                        error : function (data){

                        }
                    })

                }
            },
            error : function (data){

            }
        })
    })
}


function getChatRoomList(meetingNo, userNo){
    $.ajax({
        data : {meetingNo : meetingNo},
        url : "/meeting/chat/room",
        type : "GET",
        success : function (data){
            if(data != "empty"){
                let mCList = JSON.parse(data);
                let roomList = document.getElementById('groupChatRoomList');
                roomList.innerHTML = "";
                let roomBox = document.getElementById('groupRoomBox');
                for(let i = 0; i < mCList.length; i++){
                    if(mCList[i].user.id == userNo){
                        let myContentBox = roomBox.querySelector('.group-chat-room-myChatting-box').cloneNode(true);
                        myContentBox.style.display = 'block';
                        let myMessage = document.createElement("span");
                        myMessage.innerText = mCList[i].content;
                        myContentBox.querySelector('.group-chat-myMessage').innerHTML = "";
                        myContentBox.querySelector('.group-chat-myMessage').append(myMessage);
                        roomList.append(myContentBox);
                    }else{
                        let youContentBox = roomBox.querySelector('.group-chat-room-youChatting-box').cloneNode(true);
                        youContentBox.style.display = 'block';
                        let youMessage = document.createElement("span");
                        youMessage.innerText = mCList[i].content;
                        youContentBox.querySelector('.group-chat-message').innerHTML = "";
                        youContentBox.querySelector('.group-chat-room-nickname').innerText = mCList[i].user.nickname;
                        youContentBox.querySelector('.group-chat-message').append(youMessage);
                        roomList.append(youContentBox);
                    }
                }
                roomList.scrollTo(0,roomList.scrollHeight - roomList.clientHeight);
            }
        },
        error : function (data){

        }
    })
}

// --------------------------------- 알람 웹소켓 --------------------------------

let alarm;
let alarmModal = false;
const alarmListBox = document.getElementById('alarmListBox');
if(document.getElementById('userIconBox') != null){
    getListAlarm();
    document.getElementById('navAlarmBtn').addEventListener("click", function (e){
        e.preventDefault();
        e.stopPropagation();
        if(!alarmModal){
            alarmListBox.style.transform = "translate(-400px)";
        }else{
            alarmListBox.style.transform = "translate(0px)";
        }
        alarmModal = !alarmModal;
    })
    function getListAlarm(){
        let userNo = document.getElementById('groupName').dataset.id;
        $.ajax({
            data : {userNo : userNo},
            type : "GET",
            url : "/alarm/list",
            success : function(data){
                let alarmListBox = document.getElementById("alarmListBox");
                alarmListBox.innerHTML = "";
                let alarmCloseBox = document.getElementById('alarmListClose').cloneNode(true);
                alarmCloseBox.style.display = "block";
                let alarmCount = document.getElementById('navBellCount');
                let iconAlarm = document.getElementById('userIconAlarm');
                if(data == "empty"){
                    alarmListBox.innerHTML = "알람이 없습니다."
                    alarmCount.classList.add('hidden-count');
                    iconAlarm.classList.add('hidden-count');
                }else{
                    let aList = JSON.parse(data);
                    for(let i = 0; i < aList.length; i++){
                        alarmCount.innerText = aList.length;
                        alarmCount.classList.remove('hidden-count');
                        iconAlarm.innerText = aList.length;
                        iconAlarm.classList.remove('hidden-count');
                        let alarmBox = document.getElementById("alarmContentBox").cloneNode(true);
                        alarmBox.style.display = 'flex';
                        alarmBox.querySelector('.alarm-content').innerText = aList[i].content;
                        alarmBox.querySelector('#alarmRemove').dataset.id = aList[i].id;
                        alarmListBox.append(alarmBox);
                    }
                }
                alarmListBox.prepend(alarmCloseBox);
            },error : function(data){
                console.log(data);
            }
        })
    }

    function removeAlarm(btn){
        let alarmNo = btn.dataset.id;
        $.ajax({
            data : {alarmNo : alarmNo},
            type : "GET",
            url : "/alarm/remove",
            success : function(data){
                if(data == "success"){
                    getListAlarm();
                }
            },error : function(data){
                console.log(data);
            }
        })
    }

    function closeAlarmBox(){
        let e = window.event;
        e.preventDefault();
        e.stopPropagation();
        alarmListBox.style.transform = "translate(0px)";
        alarmModal = false;
    }




    alarm = new WebSocket("ws://localhost:9999/ws/alarm");

    alarm.onopen = function(e) {
        //console.log('알람소켓 open');
        let userNo = document.getElementById('groupName').dataset.id;
        alarm.send(`{type: "create", sender: "${userNo}"}`);
    };

    alarm.onmessage = function(event) {
       // console.log(`[message] 서버로부터 전송받은 데이터: ${event.data}`);
        if(event.data == '새로운 접속'){
            // 같은 아이디 중복 접속
        }
        else if(event.data == 'newAlarm'){
            getListAlarm();
            groupChatList();
        }else{
            let obj = JSON.parse(event.data);
            if(document.getElementById('groupRoomBox') != null && document.getElementById('groupName') != null){
                let groupNo = document.getElementById('groupRoomBox').dataset.meetingno;
                let userNo = document.getElementById('groupName').dataset.id;
                if(groupNo == obj.meetingNo){
                    getChatRoomList(groupNo, userNo);
                }
            }
        }
    };


    alarm.onclose = function(event) {
        if (event.wasClean) {
            //console.log(`[close] 커넥션이 정상적으로 종료되었습니다(code=${event.code} reason=${event.reason})`);
        } else {
            // 예시: 프로세스가 죽거나 네트워크에 장애가 있는 경우
            // event.code가 1006이 됩니다.
           // console.log('[close] 커넥션이 죽었습니다.');
        }
    };

    alarm.onerror = function(error) {
        alert(`[error]`);
    };
}


//alarm.send(`{msg: "${msg}", sender: "${sender}"}`);
