package com.sd.demo.support;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sd.demo.entity.SysPermission;
import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	public static String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SysUser sysUser = userService.getUserByName(username);
		 if (null == sysUser) {
			 System.out.println("username :"+username);
			throw new UsernameNotFoundException(username);
		}
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : sysUser.getRoleList()) {
            for (SysPermission permission : role.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }
        System.out.println("get user");
		return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
	}

}
