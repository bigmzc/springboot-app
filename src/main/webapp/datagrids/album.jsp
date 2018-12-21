<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-search',
            text: "专辑详情",
            handler: function () {
                //获取选中行
                var row2 = $("#albumdatagrid").treegrid("getSelected");
                //console.log(row2);
                if (row2 != null) {
                    //编辑指定行，行下标
                    var index2 = row2.id;
                    globalId = index2;
                    $("#albumInfoDialog").dialog("open");
                } else {
                    alert("请先选中行")
                }

                //
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-add',
            handler: function () {
                $("#addAlbumDialog").dialog("open");
            }
        }, '-', {
            text: "添加音频",
            iconCls: 'icon-add',
            handler: function () {
                var row3 = $("#albumdatagrid").treegrid("getSelected");
                console.log(row3);
                if (row3 != null) {
                    globalId2 = row3.id;
                    console.log(globalId2);
                    $("#addAudioDialog").dialog("open");
                } else {
                    alert('请选中行');
                }
            }
        }, '-', {
            text: "音频下载",
            iconCls: 'icon-save',
            handler: function () {

            }
        }];


        $('#albumdatagrid').treegrid({
            //method:"get", 405error
            url: '${pageContext.request.contextPath}/album/queryByPage',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名称', width: 60},
                {field: 'url', title: '下载路径', width: 60},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'duration', title: '章节时长', width: 80}
            ]],
            fit: true,
            fitColumns: true,
            toolbar: toolbar,
            pagination: true,
            pageSize: 5,
            pageList: [3, 5, 7, 10],
        });

        //初始化添加轮播图
        $("#addAlbumDialog").dialog({
            title: "添加专辑",
            width: 500,
            height: 450,
            closed: true,
            cache: false,
            modal: true,
            href: "${pageContext.request.contextPath}/datagrids/addalbum.jsp"
        });

        //初始化专辑详情Dialog
        $("#albumInfoDialog").dialog({
            title: "专辑详情",
            width: 200,
            height: 150,
            closed: true,
            cache: false,
            modal: true,
            href: "${pageContext.request.contextPath}/datagrids/albuminfo.jsp"
        });

        $("#addAudioDialog").dialog({
            title: "添加音频",
            width: 400,
            height: 350,
            closed: true,
            cache: false,
            modal: true,
            href: "${pageContext.request.contextPath}/datagrids/addaudio.jsp"
        })


    })
</script>
<table id="albumdatagrid"></table>
<div id="addAlbumDialog"></div>
<div id="albumInfoDialog"></div>
<div id="addAudioDialog"></div>
