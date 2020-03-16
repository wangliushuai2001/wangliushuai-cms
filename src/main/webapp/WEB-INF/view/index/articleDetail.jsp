<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章详情</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<LINK href="/resource/index.css" type="text/css" rel="stylesheet">
<script src="/resource/jquery-3.2.1.js" type="text/javascript"></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px"></font>
			</div>

		</div>
		
		<div class="row" style="margin-top: 10px">
		   <div class="col-md-1" ></div>
		    <div class="col-md-7">
		         <h2>${article.title }</h2>
		         <p>${article.user.username }
		           <fmt:formatDate value="${article.created }"
							  pattern="yyyy-MM-dd HH:mm:ss"/>
		         </p>
		         <c:if test="${collect!=null }">
		            <button type="button" class="btn btn-link" onclick="deleteCollect(-1)">★&nbsp;取消收藏</button>
		         </c:if>
		         <c:if test="${collect==null }">
		         	<button type="button" class="btn btn-link" onclick="collect(1)">☆&nbsp;未收藏</button>
		         </c:if>
		         <hr>
		         ${article.content }
		         <hr>
		          <!--文章评论--> 
		          <c:if test="${sessionScope.user!=null }">
		          <div>
			         <h5>文章评论:</h5>
			         <textarea rows="8" cols="20" style="width: 753px" name="content"></textarea> 
			         <button type="button" onclick="addComment()" class="btn btn-info">提交</button>
		          </div>
		          </c:if>
		          <div>
		             <!-- 显示评论内容 -->
		             
		             <c:forEach items="${info.list }" var="comment">
		               <h5>${comment.user.username } <fmt:formatDate value="${comment.created }"
							  pattern="yyyy-MM-dd HH:mm:ss"/></h5>
		                ${comment.content }
		                 <hr>
		             </c:forEach>
		          </div>
		    </div>
		    
		    <div class="col-md-4">
		        <div class="card" style="wideth:18rem;margin-top:6px">
                    <div class="card-header">评论排行榜</div>
                    <div class="card-body">
                       <c:forEach items="${info2.list }" var="article" varStatus="1">
                            <p>${i.count } ${article.title }</p>
                          <hr>
                       </c:forEach>
                       
                    </div>
                 </div>
		    </div>
		    
		</div>
          
                
 </div>
	<script type="text/javascript">
	  
	    //收藏 
	    function collect(){
	    	 //文章标题
	    	 var title ='${article.title}';
	    	 //文章地址
	    	 var url=window.location.href;
	    	 $.post("/collect",{text:title,url:url},function(flag){
	    		 if(flag){
	    			   alert("收藏成功");
	    			   window.location.reload();
	    		   }else{
	    			   alert("收藏失败，需要登陆后才能评论！")
	    		   }
	    	 })
	    }
	
	    
	  // 取消收藏
	    function deleteCollect(){
	    	 //文章标题
	    	 var id ='${collect.id}';
	    	 $.post("/deleteCollect",{id:id},function(flag){
	    		 if(flag){
	    			   alert("取消收藏成功");
	    			   window.location.reload();
	    		   }else{
	    			   alert("取消收藏失败，需要登陆后才能！")
	    		   }
	    	 })
	    }
	    //提交评论
	    function addComment(){
	    	 var articleId='${article.id }'
	    	 var content=$("[name='content']").val();
	    	 $.post("/addComment",{articleId:articleId,content:content},function(flag){
	    		   if(flag){
	    			   alert("评论成功");
	    			   window.location.reload();
	    		   }else{
	    			   alert("评论失败，需要登陆后才能评论！")
	    		   }
	    	 }) 
	    }
	</script>
</body>
</html>