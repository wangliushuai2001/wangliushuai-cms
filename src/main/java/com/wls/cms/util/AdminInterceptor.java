package com.wls.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{
   
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception{
		      
		//如果注册用户已经登录，则不拦截
		   Object attribute = request.getSession().getAttribute("admin");
		   if(attribute!=null){
			   return true;//不拦截
		   }
		   request.setAttribute("msg", "请登陆管理员账户");
		   request.getRequestDispatcher("/WEB-INF/view/passport/adminLogin.jsp").forward(request, response);
		 return false;//拦截
	 }
}
