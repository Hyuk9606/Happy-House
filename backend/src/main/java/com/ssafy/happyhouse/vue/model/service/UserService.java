package com.ssafy.happyhouse.vue.model.service;

import com.ssafy.happyhouse.vue.model.User;

public interface UserService {
	
	int userInsert(User user);
	User userSelect(String userId);
	boolean userUpdate(User user);
	boolean findPass(User user);
	boolean userDelete(String userId);
	User login(User user) throws Exception;
	User userInfo(String userId) throws Exception;
	
}
