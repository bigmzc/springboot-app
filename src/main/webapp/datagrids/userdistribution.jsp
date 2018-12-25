<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<script src="../js/echarts.min.js"></script>
<script src="../js/china.js"></script>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main2" style="width: 700px;height:500px;" align="center"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('main2'));

    option = {
        title: {
            text: '持明法洲APP用户分布图',
            subtext: '2018年12月25统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['用户']
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
                name: '用户',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
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
    myChart2.setOption(option);

    $.post("${pageContext.request.contextPath }/statistics/distribution", function (data) {
        console.log(data);
        myChart2.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '用户',
                data: data.data
            }]
        });
    }, "json");
</script>
