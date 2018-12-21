<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function () {
        //初始化表单元素
        $("#addAlbumFormName").textbox({
            required: true
        });

        $("#addAlbumReleDate").datebox({
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

        $("#addAlbumFormSaveBtn").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                //提交表单
                $("#addAlbumForm").form("submit", {
                    url: "${pageContext.request.contextPath}/album/addOneAlbum",
                    onSubmit: function () {
                        //表单验证
                        return $("#addAlbumForm").form("validate");
                    },
                    success: function () {
                        //成功关闭对话框(大小页面资源共享)
                        $("#addAlbumDialog").dialog("close");
                        $.messager.show({
                            title: "tips",
                            msg: "add success!"
                        });
                        //var tab = $("#tt").tabs("getSelected");
                        //添加后刷新panel
                        //tab.panel("refresh");
                        $("#albumdatagrid").edatagrid("reload");
                    }
                });
            }
        });

        $("#addAlbumFormResetBtn").linkbutton({
            iconCls: "icon-undo"
        });
    });
</script>

<div id="content" align="center">
    <form id="addAlbumForm" method="post" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>
                    专辑名称:<input id="addAlbumFormName" name="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    封面文件:<input type="file" name="file" size="3">
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    专辑评分:
                    <select name="score" id="addAlbumScore">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    专辑作者:<input id="addAlbumFormAuthor" name="author"/>
                </td>
            </tr>
            <tr>
                <td>
                    播音作者:<input id="addAlbumFormBrocast" name="broadcast"/>
                </td>
            </tr>
            <tr>
                <td>
                    专辑简介:<input id="addAlbumDescription" name="brief"/>
                </td>
            </tr>
            <tr>
                <td>
                    发布日期:<input id="addAlbumReleDate" name="pubDate"/>
                </td>
            </tr>

            <tr>
                <td><a id="addAlbumFormSaveBtn">提交</a>
                    <a id="addAlbumFormResetBtn">重置</a></td>
            </tr>
        </table>
    </form>
</div>