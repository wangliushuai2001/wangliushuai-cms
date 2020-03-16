<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条-管理员中心</title>
<LINK href="/resource/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="/resource/jquery-3.2.1.js" type="text/javascript"></script>
<script type="text/javascript"   src="/resource/popper.min.js" ></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="background-color: #009FD9; height: 65px">
			<div class="clo-md-12">
				<img alt="" src="/resource/images/xuan.JPG"
					style="height: 65px; width: 85px" class="rounded"> 
			   <font color="white">今日头条-管理员中心</font>
			  
			   
			</div>
		</div>
		<div class="row" style="padding-top: 6px ;padding-left: 5px">
			<!-- 左侧菜单 -->
			<div class="col-md-2 rounded" style="text-align: center; ">
               	<nav class="nav flex-column">
					<a class="list-group-item active" href="#" data="/admin/articles">文章审核</a> 
					<a class="list-group-item " href="#" data="/admin/users">用户管理</a> 
				    <a class="list-group-item " href="#">栏目管理</a>
					<a class="list-group-item " href="#">分类管理</a>
					<a class="list-group-item " href="#">系统设置</a>
				</nav>
			</div>
			<!-- 右侧具体内容 -->
			<div class="clo-md-10" id="center">
			
			</div>
			
		</div>
	</div>
<script type="text/javascript">
 
     //默认显示我的文章
     $("#center").load("/admin/articles");
     
     //为li添加点击事件
     $(function(){
    	 $("a").click(function(){
    		  var url =$(this).attr("data");
    		 //去除样式
    		  $("a").removeClass("active");
    		  //让当前点击的li  添加选中样式
    		  $(this).addClass("list-group-item active")
    		  //在中间区域显示url的内容
    		  $("#center").load(url);
    	 })
    	 
     })
 </script>
</body>
</html>