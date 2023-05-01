
function navScroll(target){
    window.scrollTo({top : window.scrollY + target.getBoundingClientRect().top - 70, behavior : "smooth"});
    target.animate([
        { transform: 'translate(0, -15px)'},
        { transform: 'translate(0, -8px)'},
        { transform: 'translate(0, -4px)'},
        { transform: 'translate(0, -6px)'},
        { transform: 'translate(0, -3px)'},
        { transform: 'translate(0, -6px)'},
        { transform: 'translate(0, -3px)'},
        { transform: 'translate(0, -1px)'},
        { transform: 'translate(0, 0px)'},
        ], 1000);
    }

document.getElementById('outLine').addEventListener('click', function(){
    let outline = document.getElementById('doWork');
    navScroll(outline);
})
document.getElementById('explore').addEventListener('click', function(){
    let explore = document.getElementById('ready');
    navScroll(explore);
})
document.getElementById('graph').addEventListener('click', function (){
    let graph = document.getElementById('salary');
    navScroll(graph);
})
const eduChart = document.getElementById('eduChart');
const deptChart = document.getElementById('deptChart');
const satisficationChart = document.getElementById('satisfication');
const satisfication = satisficationChart.dataset.satisfication;
const edu = eduChart.dataset.edu.split(",");
const eduData = eduChart.dataset.staticstis.split(",");
const major = deptChart.dataset.major.split(",");
const deptData = deptChart.dataset.staticstis.split(",");

new Chart(satisficationChart, {
    type: 'bar',
    data: {
        labels: ['만족도'],
        datasets: [{
        data: [satisfication],
        }]
    },
    options: {
        plugins : {
            legend : {
            display : false
            }
        },
        indexAxis: 'y',
        scales : {
            xAxis : {
            suggestedMax: 100,
            }
        }
    }
});

new Chart(deptChart, {
    type: 'pie',
    data: {
        labels: major,
        datasets: [{
        data: deptData,
        backgroundColor: ['#84A9FF', '#6690FF', '#3366FF', '#254EDB', '#1939B7', '#102693', '#091A7A'],
        borderWidth: 1
        }]
    },
    options : {
        plugins :{
            title : {
                display : true,
                text : '전공계열',
                position : 'top',
            },
            legend : {
                padding : 10,
                position : 'bottom'
            }
        }
    }
});
new Chart(eduChart, {
    type: 'pie',
    data: {
        labels: edu,
        datasets: [{
            data: eduData,
            backgroundColor: ['#89FAF1', '#6AF5F5', '#3BE0EF', '#2BB2CD', '#1D88AC', '#12638A', '#0B4872'],
            borderWidth: 1
        }]
    },
    options : {
    plugins :{
        title : {
            display : true,
            text : '학력분포',
            position : 'top',
        },
        legend : {
            padding : 10,
            position : 'bottom'
        }
    }
    }
});
