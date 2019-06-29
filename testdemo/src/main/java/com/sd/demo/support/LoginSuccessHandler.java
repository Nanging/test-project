package com.sd.demo.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString("登录成功") + "}";
        out.write(s);
        out.flush();
        out.close();
		
		
//		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        String path = request.getContextPath() ;
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//        for (String string : roles) {
//        	System.out.println("Permission : "+string);
//		}
//        if (roles.contains("AdminIndex")){
//            response.sendRedirect(basePath+"admin/index");
//            return;
//        }
////        response.sendRedirect(basePath+"user/index");
//        response.sendRedirect(basePath+"product/list");
	}

}
