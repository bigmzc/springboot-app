<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>董某人的聊天室</title>

    <link href="css/base.css" rel="stylesheet" type="text/css"/>
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
    <link href="css/index_iphone.css" rel="stylesheet" type="text/css"/>
    <link href="css/index_1000.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <style>
        .overlay {
            position: fixed;
            _position: absolute;
            z-index: 1000;
            width: 100%;
            height: 100%;
            _height: 1000px;
            top: 0;
            left: 0;
            filter: alpha(opacity=80);
            opacity: 0.8;
            overflow: hidden;
            background-color: #000;
        }

        #loading {
            z-index: 9999;
            position: fixed;
            _position: absolute;
            top: 50%;
            _top: 50%;
            left: 50%;
        }
    </style>
    <style>
        table, table tr td {
            border: none;
            padding: 0 0 0 0;
        }

        .bg-success .gritter-item {
            padding: 0 0 10px;
            background: white;
            min-height: 86px;
            _margin-left: 0;
            font-weight: normal;
            font-size: 14px;
            width: 360px;
        }

        .bg-success .gritter-image {
            width: 60px;
            height: auto;
            margin-top: 15px;
            margin-left: 20px;
            margin-right: 20px;
        }

        .gritter-item .gritter-with-image p {
            color: #474865;
            margin-left: 15px;
            margin-top: 15px;
        }

        .bg-success .gritter-with-image {
            width: 230px;
            float: left;
            min-height: 86px;
            background: white;
        }

        .bg-success .gritter-bottom {
            display: none;
        }


    </style>
    <script type="text/javascript">

        var goEasy = new GoEasy({
            appkey: "BC-14da083e8c24437aaaf20f47343417d4"
        });

        //订阅消息
        goEasy.subscribe({
            channel: "messageChannel",
            onMessage: function (message) {
                //id选择器span显示消息
                console.log(message);
                $("#messageLoad").text(message.content);
            }
        });

        //发送消息
        function publishMessage() {
            var messageContent = $("#publishMessages").val();
            //alert(messageContent);
            goEasy.publish({
                channel: "messageChannel",
                message: messageContent
            });
        }
    </script>
</head>
<body>

<div class="demos chatdiv">
    <div class="demos_con">
        <div id="chatScroll" class="chat J_chat">
            <div id="subscribeMessages" class="chat_auto J_chat_auto">
                &nbsp;
                <span id="messageLoad"></span>
            </div>
        </div>

        <div class="demos_condsend clearfix">
            <input class="demos_condsend_1 J_demos_condsend_1 fl" id="publishMessages" type="text"/>
            <button class="demos_condsend_2 fr" onclick="publishMessage();">发送</button>
        </div>
    </div>
</div>

<div class="partfw J_partfw">
    <div class="partf">
        <p class="partfp">© 2015-2018 DHG, All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp; Em
            <span style="display: none;">e</span>mail:<span style="display: none;">m</span> mzc
            <span style="display: none;">1</span>1997@<span style="display: none;">g</span>mai
            <span style="display: none;">l</span>.com</p>
    </div>
</div>

</body>
</html>
