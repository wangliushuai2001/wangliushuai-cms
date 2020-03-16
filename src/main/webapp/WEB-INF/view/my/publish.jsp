<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>

</head>
<body>
	  
	<form id="form1">
	     <div class="form-group">
	        <label for="title">文章标题:</label>
	         <input id="title" type="text" name="title" class="form-control" style="width: 930px">
	     </div>
	     
	     <div class="form-group form-inline">
	        <label for="title" >所属栏目:</label>
	        <select  id="channels" name="channelId" 
	        class="form-control form-control-sm " style="width: 150px" >
	            <option>请选择</option>
	        </select>
	        
	        <label for="title" >所属分类:</label>
	        <select id="categorys" name="categoryId"
	        class="form-control form-control-sm" style="width: 150px">
	            <option></option>
	        </select>
	     </div>
	     
	     <div class="form-group ">
	             标题图片:<input type="file" name="file">	     
	     </div>
	     
		<textarea name="content1" cols="100" rows="8" style="width:928.45px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="button" onclick="publish()" name="button" value="提交内容" />
	</form>
</body>
<script type="text/javascript">
//发布文章
function publish(){
	 
	 var formData = new FormData($( "#form1" )[0]);
	 //获取富文本编辑器里面的html内容，并封装到formData中，
	 formData.set("content",editor1.html())
	 
	 
	 $.ajax({
		type:"post",
		url:"my/publish",
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		data:formData,
		success:function(flag){
			if(flag){
				alert("发布成功");
				//跳转到我的文章
				location.href="/my";
			}else{
				alert("发布失败！");
			}
		}

	 })
	  

}

//文档就绪函数
$(function(){
	 //为栏目添加内容 栏目和分类 耳机联动
	 $.get("/channel/channels",function(list){
		//为栏目添加option
		 for(var i in list){
			 $("#channels").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
		 }
           
	 }) 
	 //为栏目添加点击事件
	 $("#channels").change(function(){
		 //获取当前栏目的Id
		 var channelId=$(this).val();
		//根据栏目id  查询旗下的分类
		 $.get("/channel/selectsByChannelId",{channelId:channelId},function(list){
			     //先清空原有的分类
			     $("#categorys").empty();
				 for(var i in list){
					//为为分类添加option
					 $("#categorys").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
				 }
		           
			 }) 
		 
	 })
	 
})
</script>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>