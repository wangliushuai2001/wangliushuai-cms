<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的文章</title>
<LINK href="/resource/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="/resource/jquery-3.2.1.js"  type="text/javascript"></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>



 <jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
<!-- Modal -->
<div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalScrollableTitle"><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
    
   //文章详情
   function articleDetail(id){
	   
	   $.get("/my/articleDetail",{id:id},function(article){
		    $("#title").append(article.title);
		    $("#content").append(article.content);
	   })
	   
   }
   

   function goPage(page){
	   //在中间区域加载
	   $("#center").load("/my/articles?page="+page);
   }
   
   
</script>
</body>
</html>