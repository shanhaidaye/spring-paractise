
<%--
  Created by IntelliJ IDEA.
  User: sunshiwang
  Date: 16/6/5
  Time: 下午3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>

    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.css"/>

    <!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
    <!--<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" />-->

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>


    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery-validate/1.15.0/jquery.validate.min.js"></script>

    <script type="text/javascript" src="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <!-- form: -->
        <section>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>Login</h2>
                </div>

                <form id="defaultForm" method="post" class="form-horizontal" action="target.php">


                    <div class="form-group">
                        <label class="col-lg-3 control-label">Username</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" name="userName" id="userName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Password</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="password" id="password"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-lg-3 control-label" id="captchaOperation">captcha</label>
                        <div class="col-lg-2">
                            <input type="text" class="form-control" name="rand" maxlength="4" id="rand"/>
                            <img src="/captcha" onclick='$(this).attr("src","/captcha?r="+Math.random())'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-primary" name="signIn" value="Sign In">Sign In</button>
                            <%--<button type="submit" class="btn btn-primary" name="signup2" value="Sign up 2">Sign up 2</button>--%>
                            <%--<button type="button" class="btn btn-info" id="validateBtn">Manual validate</button>--%>
                            <%--<button type="button" class="btn btn-info" id="resetBtn">Reset form</button>--%>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        // Generate a simple captcha
        function randomNumber(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        };
//        $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
        $('#defaultForm').bootstrapValidator({
//        live: 'disabled',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {

                userName: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: 'The username is required and cannot be empty'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: 'The username must be more than 6 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        },
//                        remote: {
//                            type: 'POST',
//                            url: 'remote.php',
//                            message: 'The username is not available'
//                        },
                        different: {
                            field: 'password,confirmPassword',
                            message: 'The username and password cannot be the same as each other'
                        }
                    }
                },

                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
                        },
                        different: {
                            field: 'username',
                            message: 'The password cannot be the same as username'
                        }
                    }
                },

                rand: {
                    validators: {
                        notEmpty: {
                            message: 'The confirm rand is required and cannot be empty'
                        }
//                        callback: {
//                            message: 'Wrong answer',
//                            callback: function(value, validator) {
//                                var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
//                                return value == sum;
//                            }
//                        }
                    }
                }
            }
        }).on('success.form.bv', function (e) {
// Prevent form submission
            e.preventDefault();
            signIn();
            console.log('ceshi');

        });;

        var signIn=function(){
            var url='/user/login';
            var userName=$("#userName").val();
            var password=$("#password").val();
            var rand=$("#rand").val();
            $.post(url,{userName:userName,password:password,rand:rand,t:Math.random()},function(result){
                if(result&&result['success']){
                    var killResult=result['data'];
                    var state=killResult['state'];
                    var stateInfo=killResult['stateInfo'];
                    alert(stateInfo);
                    //3显示秒杀结果
//                    node.html('<span class="label label-success">'+stateInfo+'</span>');

                }
            })
        };

        // Validate the form manually
        $('#validateBtn').click(function() {
            $('#defaultForm').bootstrapValidator('validate');
        });
        $('#resetBtn').click(function() {
            $('#defaultForm').data('bootstrapValidator').resetForm(true);
        });
    });
</script>
</body>
</html>
