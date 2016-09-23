<%--
  Created by IntelliJ IDEA.
  User: sunshiwang
  Date: 16/6/5
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
    <%@include file="common/head.jsp" %>
</head>
<body>


<!--登陆弹出层 输入电话-->
<form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">
    <fieldset>
        <legend><small>用户注册</small></legend>
        <div class="control-group">
            <label for="login_name" class="control-label">登录名:</label>
            <div class="controls">
                <input type="text" id="login_name" name="login_name" class="input-large required" minlength="3"/>
            </div>
        </div>
        <div class="control-group">
            <label for="name" class="control-label">用户名:</label>
            <div class="controls">
                <input type="text" id="name" name="name" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="plainPassword" class="control-label">密码:</label>
            <div class="controls">
                <input type="password" id="plainPassword" name="plainPassword" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="confirmPassword" class="control-label">确认密码:</label>
            <div class="controls">
                <input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#plainPassword"/>
            </div>
        </div>
        <div class="form-actions">
            <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
        </div>
    </fieldset>
</form>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>


<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-validate/1.15.0/jquery.validate.min.js"></script>

<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>



<script src="/resources/script/seckill.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        //聚焦第一个输入框
        $("#login_name").focus();
        //为inputForm注册validate函数
        $("#inputForm").validate({
            rules: {
                login_name: {
                    remote: "${ctx}/register/checkLoginName"
                }
            },
            messages: {
                login_name: {
                    remote: "用户登录名已存在"
                }
            }
        });
        //el表达shi传入参数
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},
            endTime:${seckill.endTime.time}
        })
    })
</script>
</body>
</html>

