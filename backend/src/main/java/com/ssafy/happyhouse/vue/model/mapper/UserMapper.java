package com.ssafy.happyhouse.vue.model.mapper;

import com.ssafy.happyhouse.vue.model.User;

public interface UserMapper {
	int userInsert(User user);
	User userSelect(String userId);
	int userUpdate(User user);
	int findPass(User user);
	int userDelete(String userId);
	User login(User user);
	User userInfo(String userId);
	
}
