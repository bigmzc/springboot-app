<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-search',
            text: "专辑详情",
            handler: function () {
                //alert('编辑按钮')
                //$("#addBannerDialog").dialog("open");
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-add',
            handler: function () {
                //获取选中行

            }
        }, '-', {
            text: "添加音频",
            iconCls: 'icon-add',
            handler: function () {
                //alert('帮助按钮')

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
            title: "添加轮播图",
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            modal: true,
            href: "${pageContext.request.contextPath}/"
        });
    })
</script>
<table id="albumdatagrid"></table>
<div id="addAlbumDialog"></div>
