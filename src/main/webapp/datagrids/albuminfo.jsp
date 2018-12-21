<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function () {
        //页面加载完后 查一个
        $("#albumInfoForm").form("load", "${pageContext.request.contextPath}/album/queryOneById?id=" + globalId);

        //初始化表单元素
        $("#addAlbumFormName").textbox({
            editable: false
        });


        $("#albumInfoFormEmail").textbox({
            editable: false
        });
    });
</script>

<h3 align="center">专辑详情</h3>
<div id="content" align="center">
    <form id="albumInfoForm">
        <table cellpediting="0" cellspacing="0" border="0">
            <tr>
                <td>
                    专辑名称:<input id="addAlbumFormName" name="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    专辑数量:<input id="addAlbumFormCount" name="count"/>
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    封面路径:<input id="addAlbumFormFilePath" name="coverImg"/>
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    专辑评分:<input id="addAlbumFormScore" name="score"/>
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
        </table>
    </form>
</div>

