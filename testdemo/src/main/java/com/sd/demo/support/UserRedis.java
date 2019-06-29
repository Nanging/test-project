package com.sd.demo.support;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sd.demo.entity.SysUser;


@Repository
public class UserRedis {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    public void addSession(String key,Long time,String sessionId) {
		redisTemplate.opsForValue().set(key, sessionId);
	}
    public String getSession(String key) {
		return redisTemplate.opsForValue().get(key);
	}
    
    public void addUser(String key,Long time,SysUser user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(user),time,TimeUnit.MINUTES);
    }
    
    public void addUserList(String key,Long time,List<SysUser> userList){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(userList),time,TimeUnit.MINUTES);
    }
    
    public SysUser getUserByKey(String key){
        Gson gson = new Gson();
        SysUser user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
            user =  gson.fromJson(userJson, SysUser.class);
        }
        return user;
    }
    
    public List<SysUser> getUserListByKey(String key){
        Gson gson = new Gson();
        List<SysUser> userList = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
            userList =  gson.fromJson(userJson, new TypeToken<List<SysUser>>(){}.getType()    );
        }
        return userList;
    }
    
    public void deleteByKey(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}

