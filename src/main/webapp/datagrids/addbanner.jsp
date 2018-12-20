<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        //初始化表单元素
        $("#addBannerFormName").textbox({
            required: true
        });

        $("#addBannerReleDate").datebox({
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

        $("#addBannerFormSaveBtn").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                //提交表单
                $("#addBannerForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/fileupload",
                    onSubmit: function () {
                        //表单验证
                        return $("#addBannerForm").form("validate");
                    },
                    success: function () {
                        //成功关闭对话框(大小页面资源共享)
                        $("#addBannerDialog").dialog("close");
                        $.messager.show({
                            title: "tips",
                            msg: "add success!"
                        });
                        var tab = $("#tt").tabs("getSelected");
                        //添加后刷新panel
                        tab.panel("refresh");
                    }
                });
            }
        });

        $("#addBannerFormResetBtn").linkbutton({
            iconCls: "icon-undo"
        });
    });
</script>

<div id="content" align="center">
    <form id="addBannerForm" method="post" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>
                    图片名称:<input id="addBannerFormName" name="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    选择文件:<input type="file" name="file" size="3">
                    <br>
                    <%--文件路径:<input id="addBannerFilePath" name="imgPath"/>--%>
                </td>
            </tr>
            <tr>
                <td>
                    是否使用:
                    <select name="status" id="addBannerStatus">
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    发布日期:<input id="addBannerReleDate" name="pubDate"/>
                </td>
            </tr>
            <tr>
                <td>
                    图片描述:<input id="addBannerDescription" name="description"/>
                </td>
            </tr>
            <tr>
                <td><a id="addBannerFormSaveBtn">提交</a>
                    <a id="addBannerFormResetBtn">重置</a></td>
            </tr>
        </table>
    </form>
</div>