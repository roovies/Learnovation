const swipeImgs = [...document.querySelectorAll('.swipe-img-box')];
var timer;
const swipeNavIndex = document.getElementById('swipeNavIndex');
swipeNavIndex.innerHTML = `1 / ${swipeImgs.length}`
swipeImgs.forEach(function(data, index){
    data.style.left = `${index * 1920 }px`;
})
let swipeIndex = 0;
function nextSwipe(){
    if(swipeIndex < swipeImgs.length - 1){
        swipeIndex += 1;
        swipeImgs.forEach(function(element,index){
            element.style.transform = `translate(${swipeIndex * -1920}px)`;
            swipeNavIndex.innerHTML = `${(swipeIndex + 1)} / ${swipeImgs.length}`
        });
    }else{
        swipeIndex = 0;
        const nextFirst = document.getElementById('nextFirst');
        const endPage = document.getElementById('endPage');
        nextFirst.style.zIndex = 6;
        endPage.style.zIndex = 5;
        nextFirst.style.transform = 'translate(-1920px)';
        swipeImgs.forEach(function(element,index){
            element.style.transform = `translate(${swipeIndex * -1920}px)`;
            swipeNavIndex.innerHTML = `${(swipeIndex + 1)} / ${swipeImgs.length}`
            setTimeout(function(){
                nextFirst.style.zIndex = 3;
                endPage.style.zIndex = 3;
                nextFirst.style.transform = 'translate(1920px)';
            },1000);
        });
    }
}
function prevSwipe(){
    if(swipeIndex > 0){
        swipeIndex -= 1;
        swipeImgs.forEach(function(element,index){
            element.style.transform = `translate(${swipeIndex * -1920}px)`;
            swipeNavIndex.innerHTML = `${(swipeIndex + 1)} / ${swipeImgs.length}`
        });
    }else{
        swipeIndex = swipeImgs.length - 1;
        const preEnd = document.getElementById('preEnd');
        const firstPage = document.getElementById('firstPage');
        preEnd.style.zIndex = 6;
        firstPage.style.zIndex = 5;
        preEnd.style.transform = 'translate(1920px)';
        swipeImgs.forEach(function(element,index){
            element.style.transform = `translate(${swipeIndex * -1920}px)`;
            swipeNavIndex.innerHTML = `${(swipeIndex + 1)} / ${swipeImgs.length}`
            setTimeout(function(){
                preEnd.style.zIndex = 3;
                firstPage.style.zIndex = 3;
                preEnd.style.transform = 'translate(-1920px)';
            },1000);
        });
    }
}
let interval = setInterval(function(){
    nextSwipe();
},4000);
document.getElementById('swipeNavAction').addEventListener('click',function(e){
    e.preventDefault();
    if(interval != null){
        clearInterval(interval);
        interval = null;
        document.getElementById('swipeNavAction').className = 'stop';
    }else{
        interval = setInterval(function(){
            nextSwipe();
        },4000);
        document.getElementById('swipeNavAction').className = 'action';
    }
})
document.getElementById('prev-swipe').addEventListener('click',function(e){
    document.getElementById('swipeNavIndex')
    e.preventDefault();
    if(!timer){
        prevSwipe();
        timer = setTimeout(function(){
            timer = null;
        }, 1000);
    }
})
document.getElementById('next-swipe').addEventListener('click', function(e){
    e.preventDefault();
    if(!timer){
        nextSwipe();
        timer = setTimeout(function(){
            timer = null;
        }, 1000);
    }
})

const reviewContentBox = [...document.getElementsByClassName('review-content-box')];
let reviewSwipeIndex = 0;
reviewContentBox.forEach(function(data, index){
    data.style.transform = `translate(${index * 700}px)`
})
let click = false;
let start = 0;
const reviewSwipeBox = document.getElementById('reviewSwipeBox');
reviewSwipeBox.addEventListener('mousedown',function(e){
    click = true;
    start = e.clientX;
});
reviewSwipeBox.addEventListener('mousemove',function(e){
    if(click==true){
        let move = start - e.clientX;
        reviewContentBox.forEach(function(data,index){
            data.style.transform = `translate(${(reviewSwipeIndex + index) * 700 - (move * 2)}px)`
        });
    }
});
reviewSwipeBox.addEventListener('mouseup',function(e){
    click = false;
    let drag = e.clientX - start;
    if(Math.abs(drag) > 500){
        if(drag > 0){
            if(reviewSwipeIndex > -1){
                reviewSwipeIndex = -11
            }else{
                reviewSwipeIndex +=1
            }
            reviewContentBox.forEach(function(data,index){
                data.style.transform = `translate(${(reviewSwipeIndex + index) * 700}px)`
            })
        }else{
            if(Math.abs(reviewSwipeIndex) < reviewContentBox.length -2){
                reviewSwipeIndex -=1
            }else{
                reviewSwipeIndex = 0;
            }
            reviewContentBox.forEach(function(data,index){
                data.style.transform = `translate(${(reviewSwipeIndex + index) * 700}px)`
            })
        }
    }
    else{
        reviewContentBox.forEach(function(data,index){
            data.style.transform = `translate(${(reviewSwipeIndex + index) * 700}px)`
        })
    }
});