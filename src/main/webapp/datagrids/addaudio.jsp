<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function () {
        //初始化表单元素
        $("#addAudioFormName").textbox({
            required: true
        });

        $("#addAudioDate").datebox({
            editable: false,
            required: true,
            formatter: function (date) {
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                var d = date.getDate();
                return y + "-" + m + "-" + d;
            },
            parser: function (s) {
                var t = Date.parse(s);
                if (!isNaN(t)) {
                    return new Date(t);
                } else {
                    return new Date();
                }
            }
        });

        $("#addAudioFormSaveBtn").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                //提交表单
                $("#addAudioForm").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/uploadFile?albumId=" + globalId2,
                    onSubmit: function () {
                        //表单验证
                        return $("#addAudioForm").form("validate");
                    },
                    success: function () {
                        //成功关闭对话框(大小页面资源共享)
                        $("#addAudioDialog").dialog("close");
                        $.messager.show({
                            title: "tips",
                            msg: "add success!"
                        });
                        //$("#albumdatagrid").edatagrid("reload");
                        var tab = $("#tt").tabs("getSelected");
                        //添加后刷新panel
                        tab.panel("refresh");
                    }
                });
            }
        });

        $("#addAudioFormResetBtn").linkbutton({
            iconCls: "icon-undo"
        });
    });
</script>

<div id="content" align="center">
    <form id="addAudioForm" method="post" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>
                    音频名称:<input id="addAudioFormName" name="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    音频文件:<input type="file" name="file" size="3">
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    发布日期:<input id="addAudioDate" name="uploadDate"/>
                </td>
            </tr>
            <tr>
                <td>
                    <a id="addAudioFormSaveBtn">提交</a>
                    <a id="addAudioFormResetBtn">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>