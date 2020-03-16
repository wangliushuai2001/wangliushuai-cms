<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的文章</title>
<LINK href="/resource/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="/resource/jquery-3.2.1.js"  type="text/javascript"></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
  function qx(id){
	  $.post("/deleteCollect",{id:id},function(flag){
		 
		  if(flag){
			   alert("取消收藏成功");
			   window.location.reload();
		   }else{
			   alert("取消收藏失败，需要登陆后才能！")
		   }
	  })
  }
</script>
</head>
<body>
<c:forEach items="${info.list }" var="collect">
    
	<div class="media">
	  <div class="media-body">
	    <h5 class="mt-0">${collect.text }</h5>
	    收藏时间：<fmt:formatDate value="${collect.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
	        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	       <button type="button" class="btn btn-info btn-sm" 
	       onclick="qx(${collect.id})" style="padding-right: 10px">取消收藏</button>
	       
	     <div style="float: right;padding-top: 40px">
	     
	       <!-- Button trigger modal -->
		<%-- <button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalScrollable">
		  详情
		</button> --%>
	     </div>
	     
	  </div>
	</div>
	<hr/>
</c:forEach>
 <jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>

<script type="text/javascript">

   function goPage(page){
	   //在中间区域加载
	   $("#center").load("/my/collect?page="+page);
   }
   
   
</script>
</body>
</html>