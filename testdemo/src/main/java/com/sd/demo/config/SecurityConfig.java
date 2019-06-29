package com.sd.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sd.demo.support.AuthenticationAccessDeniedHandler;
import com.sd.demo.support.LoginSuccessHandler;
import com.sd.demo.support.MyAccessDecisionManager;
import com.sd.demo.support.MyFilterInvocationSecurityMetadataSource;
import com.sd.demo.support.MyUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    
	@Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
	@Autowired
	private  MyAccessDecisionManager myAccessDecisionManager;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
			System.out.println(" password " +new BCryptPasswordEncoder().encode("123"));
			http.authorizeRequests()
			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
	            @Override
	            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
	                o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
	                o.setAccessDecisionManager(myAccessDecisionManager);
	                return o;
	            }
	        })
			.antMatchers("/","/index","/register","/product/list","/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login").permitAll()
			.failureHandler(new AuthenticationFailureHandler() {
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					// TODO Auto-generated method stub
					response.setContentType("application/json;charset=utf-8");
	                PrintWriter out = response.getWriter();
	                StringBuffer sb = new StringBuffer();
	                sb.append("{\"status\":\"error\",\"msg\":\"");
	                if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
	                    sb.append("用户名或密码输入错误，登录失败!");
	                } else if (exception instanceof DisabledException) {
	                    sb.append("账户被禁用，登录失败，请联系管理员!");
	                } else {
	                    sb.append("登录失败!");
	                }
	                sb.append("\"}");
	                out.write(sb.toString());
	                out.flush();
	                out.close();
					
				}
	        })
			.successHandler(new LoginSuccessHandler())
			.and()
			.logout()
			.logoutSuccessUrl("/login")
			.and()
			.csrf()
			.disable()
			.exceptionHandling()
			.accessDeniedHandler(authenticationAccessDeniedHandler);
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
