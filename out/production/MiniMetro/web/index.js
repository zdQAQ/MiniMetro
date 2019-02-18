
document.addEventListener("keydown",keydown);
//键盘监听，注意：在非ie浏览器和非ie内核的浏览器
//参数1：表示事件，keydown:键盘向下按；参数2：表示要触发的事件
function keydown(event){
    var name = document.getElementById('nameInput').value
    if(event.keyCode == 13 && name!=='') {
        document.getElementById('welcome').innerHTML = "你好 " + name + "! 今天感觉如何?";
        $('#welcome').fadeIn('slow',function(){})
        setTimeout(function(){
            $('#selectCity').fadeIn('slow',function(){})
        },2000)
    }
}


function choose(city){
    if(app){
        document.getElementById('test').innerHTML = '打开方法' + app.chooseCity
        app.chooseCity(city)
    } else {
        document.getElementById('test').innerHTML = 'app 加载失败'
    }
}