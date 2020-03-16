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
        <span style="color: red">${msg }</span>
        <form id="form1">
        
            <div class="gorm-group"> 
                <label for="username">用户名</label>
                <input id="username" type="text" class="form-control" name="username" >
            </div>
             <div class="gorm-group"> 
                <label for="password">密码</label>
                <input id="password" type="password" class="form-control" name="password">
            </div>
 		    <div class="gorm-group" style="margin-top: 10px"> 
                <button type="submit" class="btn btn-info">登陆</button>
                <button type="reset" class="btn btn-warning">重置</button>
                <span id="msg"></span>
            </div>
        </form>
  </div>
  <script type="text/javascript">
     $(function(){
    	   $("#form1").validate({
    		   
    		   //定义规则
    		   rules:{
    			   /* username:{
    				   required:true,//用户名不能为空  
    			   },
    			   password:{
    				   required:true,//密码不能为空 
    			   },  */
    		   },
    		   //2.验证  消息提示
			   messages:{
				  /*  username:{
    				   required:"用户名不能为空",   
    			   },
    			   password:{
    				   required:"密码不能为空", 
    			   },  */
			   },submitHandler:function(flag){
				   //如果校验通过。则执行注册
				   $.post("/passport/login",$("#form1").serialize(),function(result){
					    if(result.code==200){
					    	 //$("#msg").html("<font color='red'>恭喜登陆成功</font>")
					    	 location.href="/";//刷新回到首页
					    }else{
					    	$("#msg").html("<font color='red'>"+result.msg+"</font>")
					    }
					    
				   })
			   } 
    	       
    	   })
     })
  </script>
</body>
</html>