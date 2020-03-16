<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章审核</title>
<LINK href="/resource/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="/resource/jquery-3.2.1.js"  type="text/javascript"></script>
<script src="/resource/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
    <div class="form-group form-inline">
           用户名: <input type="text" name="username" class="form-control form-control-sm" value="${user.username }">
            用户状态：
     <select name="locked" class="form-control form-control-sm col-sm-2">
         <option value="0"  ${user.locked==0?"selected":"" }>正常</option>
         <option value="1"  ${user.locked==1?"selected":"" }>禁用</option>
     </select>
     &nbsp;
     <button type="button" onclick="query()" class="bth btn-primary">查看</button>
    </div>
	<table class="table table-bordered table-hover ">
	   <tr>
	     <td>序号</td>
	     <td>用户名</td>
	     <td>昵称</td>
	     <td>性别</td>
	     <td>生日</td>
	     <td>注册时间</td>
	     <td>用户状态</td>
	   </tr>
	   <c:forEach items="${info.list }" var="user" varStatus="i">
	       <tr>
		     <td>${i.count }</td>
		     <td>${user.username }</td>
		     <td>${user.nickname }</td>
		     <td>${user.gender==0?"男":"女" }</td>
		     <td>${user.birthday }</td>
		     <td><fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH-mm-dd"/></td>
		     <td> 
		         <!-- 用户正常状态 -->
		         <c:if test="${user.locked==0 }">
                    <button type="button" class="btn btn-info btn-sm"  onclick="update(${user.id},this)">正常</button>
                 </c:if>
                 <!-- 用户禁用状态 -->
                 <c:if test="${user.locked==1 }">
                    <button type="button" class="btn btn-danger btn-sm" onclick="update(${user.id},this)">禁用</button>
                 </c:if>
		     </td>

	       </tr>
	   </c:forEach>
	</table>
	
<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>


<script type="text/javascript">
        
	 //改变用户状态  如果是  0：正常 1：禁用
	 function update(id,obj){
		 alert(id)
		 var locked=$(obj).text()=="正常"?1:0		
			alert(locked)	 
		 $.post("/admin/updateUser",{id:id,locked:locked},function(flag){
			  alert(flag)
			  if(flag){
				  $(obj).text($(obj).text()=="正常"?"禁用":"正常")//改变按钮内容
				  $(obj).attr("class",locked==0?"btn btn-info btn-sm":"btn btn-danger btn-sm");//改变按钮颜色
			  }
		 },"json")
	 }
	 
	 
	//模糊查询 加条件\
	
     function query(){
    	 var username=$("[name=username]").val();
    	 var locked=$("[name=locked]").val();
    	 $("#center").load("/admin/users?username="+username+"&locked="+locked);
     }
	 
     function goPage(page){
		   //在中间区域加载
		   $("#center").load("/admin/users?page="+page);
	 }
</script>
</body>
</html>