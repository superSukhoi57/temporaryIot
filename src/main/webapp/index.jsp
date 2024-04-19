<html>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
    <meta charset="UTF-8">
    <title>🫶登录页面🫰</title>
    <style>
        /*元素选择器*/
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-image: url('imgs/001.jpg');
            background-size: cover;
        }
        h1 {
            font-size: 5em;
            text-align: center;

        }
    </style>
</head>
<body>

<h1 >😚欢迎使用后端系统😘</h1>
<button onclick="setStop()">Set Stop</button>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    let stopValue = 0; //1就停止
    function setStop() {
       if(stopValue===0){
           stopValue=1;
           $.get("/set-stop/1", function (data) {
               alert(data);
           });
       }else{
           stopValue=0;
           $.get("/set-stop/0", function (data) {
               alert(data);
           });
       }
    }

</script>
</html>
