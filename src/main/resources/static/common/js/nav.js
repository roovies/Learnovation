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
