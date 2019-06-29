package com.sd.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import com.sd.demo.support.UserRedis;

@Service
@ContextConfiguration(classes = {RedisConfiguration.class,UserRedis.class} )
public class UserRedisService {
    @Autowired
    private UserRedis userRedis;

    public void addUserSession(String username,String sessionId) {
		userRedis.addSession("USER : "+username,3600L, sessionId);
	}
    public String getUserSession(String username) {
		return userRedis.getSession("USER : "+username);
	}
}
