package com.sd.demo.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.SysRoleDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Apply;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.SysRole;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.ApplyService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.service.UserService;
import com.sd.demo.web.ApplyItem;
import com.sd.demo.web.PlaceItem;
import com.sd.demo.web.Result;
import com.sd.demo.web.ResultFactory;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private SysRoleDao roleDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	UserRedisService userRedisService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	ApplyService applyService;

	@Override
	public void addUserSession(String username, String sessionId) {
		userRedisService.addUserSession(username, sessionId);
	}
	@Override
	public List<ApplyItem> getUserApply(SysUser user) {
		Set<Apply> applies =  user.getApplies();
		List<ApplyItem> resultList = new ArrayList<>();
		for (Apply apply : applies) {
			ApplyItem item = new ApplyItem();
			item.setId(apply.getId().intValue());
			item.setApplier(apply.getApplier().getUsername());
			item.setPlacename(apply.getPlace().getName());
			item.setPlaceid(apply.getPlace().getId().intValue());
			item.setStartTime(apply.getStartTime());
			item.setState(apply.getState().getState());
			item.setTime(apply.getTime());
			resultList.add(item);
		}
		return resultList;
	}
	@Override
	public List<ApplyItem> getOtherUserApply(SysUser user) {
		List<ApplyItem> resultList = new ArrayList<>();
		for (Place place : user.getPlaces()) {
			resultList.addAll(applyService.getApplyByPlace(place.getId().intValue()));
		}
		return resultList;
	}
	public boolean confirm(SysUser user) {
		return false;
	}
	public boolean refuse(SysUser user) {
		return false;
	}
	public List<PlaceItem> getUserPlace(SysUser user) {
		return null;
	}
	
	@Override
	public SysUser modifyUser(SysUser user) {
		user.setPassword(user.getPassword());
		userDao.saveAndFlush(user);
		return userDao.getOne(user.getId());
	}
	
	@Override
	public SysUser addNewUser(String username,String password,String tel) {
		SysUser newUser = new SysUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setPhonenumber(tel);
		SysRole userRole = roleDao.findByCode("ROLE_USER");
		newUser.addRole(userRole);
		userDao.saveAndFlush(newUser);
		System.out.println(newUser.getId());
		SysRole newRole = new SysRole();
		newRole.setCode("USER_"+newUser.getId());
		newRole.setName("用户"+newUser.getId());
		newUser.addRole(newRole);
		userDao.saveAndFlush(newUser);
		return userDao.findByUsername(username);
	}
	@Override
	public boolean addFavorite(int userid,int placeid) {
		if(!userDao.existsById((long)userid) || !placeDao.existsById((long)placeid))return false;
		SysUser user = userDao.getOne((long)userid);
		Place place = placeService.getPlaceDetail(placeid);
		user.getPlaceList().add(place);
		userDao.saveAndFlush(user);
		return true;
	}
	@Override
	public boolean removeFavorite(int userid,int placeid) {
		if(!userDao.existsById((long)userid) || !placeDao.existsById((long)placeid))return false;
		SysUser user = userDao.getOne((long)userid);
		Place place = placeService.getPlaceDetail(placeid);
		user.getPlaceList().remove(place);
		userDao.saveAndFlush(user);
		return true;
	}
	
	@Cacheable(cacheNames = "authority",key = "#username")
	@Override
	public SysUser getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	@Override
	public boolean userLogin(String username,String password) {
		if(username == null || password == null)return false;
		if(username.equals("") || username.length()>16) return false;
		if(password.equals("")) return false;
		SysUser user = getUserByName(username);
		if (user == null) {
			return false;
		}
		System.out.println(user);
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	@Override
	public Result userRegister(String username,String password,String phonenumber) {
		if(username == null || password == null || phonenumber == null)return ResultFactory.buildFailResult("fail");
		if(username.equals("") || username.length()>16) return ResultFactory.buildFailResult("fail");
		if(password.equals("")) return ResultFactory.buildFailResult("fail");
		if(phonenumber.length()!=11)return ResultFactory.buildFailResult("fail");
		SysUser user = getUserByName(username);
		if (user != null) {
			return ResultFactory.buildFailResult("user exists");
		}
		SysUser newUser = addNewUser(username, password, phonenumber);
		if (newUser != null) {
			return ResultFactory.buildSuccessResult("success");
		}
		return ResultFactory.buildFailResult("error");
	}
	
	@Override
	public boolean modify(int userid,String oldPassword,String password) {
		if(!userDao.existsById((long)userid))return false;
		if(oldPassword == null || password == null || userid <= 0
				|| oldPassword.equals("") || password.equals(""))return false;
		SysUser user = userDao.findById((long)userid).get();
		if (!user.getPassword().equals(oldPassword)) {
			return false;
		} 
		user.setPassword(password);
		userDao.saveAndFlush(user);
		return true;
	}
	@Override
	public SysUser getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						String[] token = cookie.getValue().split("_");
						String username = token[0];
						SysUser user = this.getUserByName(username);
						String password = token[1];
						if (!password.equals(user.getPassword())) {
							Cookie loginStatus = new Cookie("loginStatus", null);
							loginStatus.setPath("/");
							loginStatus.setMaxAge(0);
							response.addCookie(loginStatus);
							break;
						}
						HttpSession session = request.getSession();
						String sessionId = session.getId();
						String currentSessionID = userRedisService.getUserSession(cookie.getValue());
						System.out.println("[currentSessionID:" + currentSessionID + "]");
						System.out.println("[sessionId:" + sessionId + "]");
						if (sessionId.equals(currentSessionID)) {
							return user;
						} else {
							return null;
						}
					}
				}
			}
		}
		return null;
	}
	
}
