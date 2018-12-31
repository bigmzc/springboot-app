<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<script src="../js/echarts.min.js"></script>


<%--为echarts准备基本的DOM--%>
<div id="main" style="width: 600px;height:400px;" align="center"></div>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'App活跃用户'
        },
        tooltip: {},
        legend: {
            data: ['活跃用户']
        },
        xAxis: {
            data: ["7天", "15天", "30天", "90天", "半年", "一年"]
        },
        yAxis: {},
        series: [{
            name: '活跃用户',
            type: 'bar',
            data: []
        }]
    };

    myChart.setOption(option);

    // 使用刚指定的配置项和数据显示图表。
    $.post("${pageContext.request.contextPath}/statistics/activeUser", function (data) {
        console.log(data);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '活跃用户',
                data: data.data
            }]
        });
    }, "json");

    //GoEasy仅做监听，不做数据加载，触发时机为--用户最近的活跃度统计Controller触发
    var goEasy = new GoEasy({
        appkey: 'BS-ac7793383fab46969783184c9242a540'
    });

    goEasy.subscribe({
        channel: "",
        onMessage: function (message) {
            //console.log(message);
            var res = $.parseJSON(message.content);
            //console.log(res.data);
            //console.log(message.content.data);
            myChart.setOption({
                series: [{
                    name: '活跃用户',
                    data: res.data
                }]
            });
        }
    });


    // 异步加载统计信息
    /*$.post("/statistics/activeUser", function (data) {
        console.log(data);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '活跃用户',
                data: data.data
            }]
        });
    }, "json");*/
</script>
