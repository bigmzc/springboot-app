<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                //alert('编辑按钮')
                $("#addBannerDialog").dialog("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#bannerdatagrid").edatagrid("getSelected");
                //console.log(row);
                if (row != null) {
                    //编辑指定行，行下标
                    var index = $("#bannerdatagrid").edatagrid("getRowIndex", row);
                    $("#bannerdatagrid").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }
            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                <shiro:hasPermission name="banner:delete">
                var row = $("#bannerdatagrid").edatagrid("getSelected");
                if (row != null) {
                    $.post("${pageContext.request.contextPath}/banner/deleteOneBanner?bannerId=" + row.id, function (data) {
                        $.messager.show({
                            title: "tips",
                            msg: "delete success!"
                        });
                        $("#bannerdatagrid").edatagrid("reload");
                    });
                } else {
                    alert("请先选中行");
                }
                </shiro:hasPermission>
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#bannerdatagrid").edatagrid("saveRow");
                $("#bannerdatagrid").edatagrid("reload");
            }
        }];


        $('#bannerdatagrid').edatagrid({
            //url 加载分页数据-pagedto集合，默认使用post请求方式，
            // 如果加载静态资源，修改method:"GET"
            updateUrl: "${pageContext.request.contextPath}/banner/updateStatus",
            url: "${pageContext.request.contextPath}/banner/queryBannerByPage",
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },
                {field: 'imgPath', title: '路径', width: 100},
                {field: 'pubDate', title: '时间', width: 100},
            ]],
            toolbar: toolbar,
            fit: true,
            fitColumns: true,
            pagination: true,
            pageSize: 5,
            pageList: [3, 5, 7, 10],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });

        //初始化添加轮播图
        $("#addBannerDialog").dialog({
            title: "添加轮播图",
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            modal: true,
            href: "${pageContext.request.contextPath}/datagrids/addbanner.jsp"
        });
    })
</script>
<table id="bannerdatagrid"></table>
<div id="addBannerDialog"></div>
