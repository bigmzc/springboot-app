<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<script src="../js/echarts.min.js"></script>
<script src="../js/china.js"></script>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main2" style="width: 700px;height:500px;" align="center"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('main2'));

    option2 = {
        title: {
            text: 'APP用户分布图',
            subtext: '2018年12月25统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);

    /*$.post("{pageContext.request.contextPath }/statistics/distribution", function (data) {
        console.log(data);
        myChart2.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '用户',
                data: data.data
            }]
        });
    }, "json");*/


    $.post("${pageContext.request.contextPath}/statistics/distribution2", function (data) {
        console.log(data);
        myChart2.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '男',
                data: data.data
            }]
        });
    }, "json");

    $.post("${pageContext.request.contextPath}/statistics/distribution3", function (data2) {
        console.log(data2);
        myChart2.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '女',
                data: data2.data
            }]
        });
    }, "json");


    //GoEasy仅做监听，不做数据加载  配合后台的触发时机--当用户注册时,触发该方法
    var goEasy2 = new GoEasy({
        appkey: 'BS-ac7793383fab46969783184c9242a540'
    });

    goEasy2.subscribe({
        channel: "",
        onMessage: function (message) {
            //console.log(message);
            var res2 = $.parseJSON(message.content);
            //console.log(res.data);
            //console.log(message.content.data);
            myChart2.setOption({
                series: [{
                    name: '活跃用户',
                    data: res2.data
                }]
            });
        }
    });

</script>
