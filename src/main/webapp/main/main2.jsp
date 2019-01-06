<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        var globalId;
        var globalId2;
        $(function () {
            $.post("${pageContext.request.contextPath}/menu/queryMainMenu", function (data) {
                //console.log(data);
                $.each(data, function (i, n) {
                    var id = n.id;
                    var text = n.title;
                    if (i == 0) {
                        $("#LeftAccordion").accordion("add", {
                            title: n.title,
                            iconCls: n.iconcls,
                            selected: true,
                            content: "<ul id='tree" + id + "' ></ul>"
                        });
                    } else {
                        //用户角色判定
                        if (text == "管理员模块") {
                            <shiro:hasRole name="super">
                            $("#LeftAccordion").accordion("add", {
                                title: n.title,
                                iconCls: n.iconcls,
                                selected: false,
                                content: "<ul id='tree" + id + "'></ul>"
                            });
                            </shiro:hasRole>
                        } else {
                            $("#LeftAccordion").accordion("add", {
                                title: n.title,
                                iconCls: n.iconcls,
                                selected: false,
                                content: "<ul id='tree" + id + "'></ul>"
                            });
                        }

                    }

                    $.ajax({
                        type: 'POST',
                        async: false,
                        dataType: "json",
                        url: '${pageContext.request.contextPath}/menu/querySubMenu?parentId=' + id,
                        success: function (data) {
                            //console.log(data[index].iconcls);
                            $("#tree" + id).tree({
                                data: data,
                                animate: true,
                                //iconCls:data[i].iconcls,
                                //在树节点加图片
                                formatter: function (node) {
                                    return node.title;
                                },
                                lines: true, //显示虚线效果
                                onClick: function (node) { // 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs
                                    //if(node.url){//判断url是否存在，存在则创建tabs
                                    if (node) {
                                        addTab(node);
                                    }
                                }

                            });
                        }

                    });
                })
            }, "json")
        });

        //初始化选项卡
        $('#tt').tabs({
            border: false
        });

        // add a new tab panel
        function addTab(node) {
            var tabExitOrNot = $('#tt').tabs('exists', node.title);//判断此选项卡是否已存在
            if (tabExitOrNot) {
                $('#tt').tabs('select', node.title);
            } else {
                //添加选项卡
                $('#tt').tabs('add', {
                    title: node.title,
                    iconCls: "icon-search",
                    closable: true,
                    selected: true,
                    href: "${pageContext.request.contextPath}" + node.url
                });
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:<shiro:principal></shiro:principal>
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="LeftAccordion" class="easyui-accordion" data-options="fit:true,border:false,nimate:true,lines:true">

    </div>
</div>

<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;">
        </div>
    </div>
</div>


<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 dhg@zparkhr.com.cn</div>
</div>
</body>
</html>