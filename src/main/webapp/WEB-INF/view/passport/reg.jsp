<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册用户</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<LINK href="/resource/index.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resource/jquery/screen.css" />
<script src="/resource/jquery-3.2.1.js" type="text/javascript"></script>
<script src="/resource/jquery.validate.js" type="text/javascript"></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
</head>
</head>
<body>
<div class="container">
        <form id="form1">
        
            <div class="gorm-group"> 
                <label for="username">用户名</label>
                <input id="username" type="text" class="form-control" name="username">
            </div>
            
             <div class="gorm-group"> 
                <label for="password">密码</label>
                <input id="password" type="password" class="form-control" name="password">
            </div>
            
            <div class="gorm-group"> 
                <label for="repassword">确认密码</label>
                <input id="repassword" type="password" class="form-control" name="repassword">
            </div>
            
            <div class="form-group form-inline">
				<label for="gender">性别</label>
				 <input id="gender" type="radio" class="form-check-input" name="gender" value="1" checked>男 
				 <input id="gender" type="radio" class="form-check-input" name="gender" value="0">女
			</div>
            
            <div class="gorm-group"> 
                <button type="submit" class="btn btn-info">注册</button>
                <button type="reset" class="btn btn-warning">重置</button>
            </div>
        </form>
  </div>
  <script type="text/javascript">
     $(function(){
    	   $("#form1").validate({
    		   
    		   //定义规则
    		   rules:{
    			  username:{
    				  /*  required:true,//用户名不能为空
    				   rangelength:[2,10],//用户名长度在2-10之间
    				   remote:{//校验用户名是否存在
    					   type:"post",
    					   data:{
    						   username:function(){
    							   return $("#username").val();
    						   }
    					   },
    					   url:"/passport/checkname"
    				   }, */
    			   },
    			  /*  password:{
    				   required:true,//密码不能为空
    				   rangelength:[6,10],//密码长度在6-10之间
    			   },
    			   repassword:{//确认密码和密码一致
    				   equalTo:"#password",
    			   }  */ 
    		   },
    		   //2.验证  消息提示
			   messages:{
				   username:{
    				  /*  required:"用户名不能为空",
    				   rangelength:"用户名长度在2-10之间", 
    				   remote:"用户名已被注册!"
    			   },
    			   password:{
    				   required:"密码不能为空",
    				   rangelength:"密码长度在6-10之间",
    			   },
    			   repassword:{
    				   equalTo:"两次密码不一致",*/
    			   }, 
    			   
    				   
    			   
			   },submitHandler:function(flag){
				   //如果校验通过。则执行注册
				   $.post("/passport/reg",$("#form1").serialize(),function(result){
					    if(result.code==200){//注册成功 跳转到登陆页面
					    	$("#title").html("<font color='red'>注册成功，请登录！</font>")
					    	 $("#passport").load("/passport/login")
					    }else{
					    	 alert(result.msg)
					    }
					    
				   })
			   } 
    	       
    	   })
     })
  </script>
</body>
</html>