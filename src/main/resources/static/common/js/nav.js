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

    // 현재 사용자 정보
    const idTag = document.querySelector(".userId");
    const roomId = "chatbot";
    const userId = idTag.dataset.id;

    // 2-1. Web Socket 연결
    const websocket = new WebSocket("ws://localhost:9999/ws/chat");
    websocket.onmessage = onMessage; // 소켓이 메시지를 받을 때
    websocket.onopen = onOpen;      // 소켓이 생성될 때 (클라이언트 접속)
    websocket.onclose = onClose;    // 소켓이 닫힐 때 (클라이언트 접속 해제)

    websocket.addEventListener('open', (event) => {
        const sessionId = event.target._socket._sessionID;
        console.log(`WebSocket connected with sessionId ${sessionId}`);
    });

    function send(){ // 메시지 전송 기능
        let content = document.querySelector("#chatbotMsg").value;
        let msg = JSON.stringify({
            'type' : 'TALK',
            'senderId' : userId,
            'message' : content
        });
        websocket.send(msg);
    }
    function onOpen(event){ // 연결 시
        let msg = JSON.stringify({
            'type' : 'ENTER',
            'senderId' : userId,
            'message' : '연결되었습니다.'
        });
        websocket.send(msg);
    }
    function onClose(event){ // 연결해제 시
        let msg = JSON.stringify({
            'type' : 'ENTER',
            'senderId' : userId,
            'message' : '연결이 종료되었습니다.'
        });
        websocket.send(msg);
        console.log("종료됐다")
    }
    function onMessage(msg){
        let content = msg.data;

        let tags = `<div class="chat-bot-modal-chatting-receive">
                        <img class="chat-bot-modal-chatting-receiverImg" src="/common/image/logo.png" alt="">
                        <div class="chat-bot-modal-chatting-receiverMsg">
                            `+content+`
                        </div>
                    </div>`;
        $(".chat-bot-modal-chatting").append(tags);
    }

    // 2-2. 메시지 전송
    document.querySelector(".chat-bot-modal-chatting-sendIcon").addEventListener("click", ()=>{
        let content = document.querySelector("#chatbotMsg").value;
        let tags = `<div class="chat-bot-modal-chatting-send">
                        <div class="chat-bot-modal-chatting-senderMsg">
                          <p>`+content+`</p>
                        </div>
                    </div>`;
        $(".chat-bot-modal-chatting").append(tags);
        send();
        document.querySelector("#chatbotMsg").value = "";
    });
    // 3. 소켓 연결 중 챗봇 모달 close
    document.querySelector("#chat-bot-close-btn").addEventListener("click", ()=>{
        websocket.close();
        $(".chat-bot-modal").hide();
        $(".chat-bot-modal-chatting").hide();
        $(".chat-bot-box").show();
    });

});
