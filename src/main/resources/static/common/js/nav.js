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

