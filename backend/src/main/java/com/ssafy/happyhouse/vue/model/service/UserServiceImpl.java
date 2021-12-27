package com.ssafy.happyhouse.vue.model.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.vue.controller.UserController;
import com.ssafy.happyhouse.vue.model.User;
import com.ssafy.happyhouse.vue.model.mapper.UserMapper;

@Service

public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int userInsert(User user) {
		return sqlSession.getMapper(UserMapper.class).userInsert(user);
	}

	@Override
	public User userSelect(String userId) {
		return sqlSession.getMapper(UserMapper.class).userSelect(userId);
	}

	@Override
	public boolean userUpdate(User user) {
//		logger.debug("{}", sqlSession.getMapper(UserMapper.class).userSelect(user.getUserId()).getUserPass());
//		logger.debug("바꾼거 : {}", user.getUserPass());
		if(sqlSession.getMapper(UserMapper.class).userSelect(user.getUserId()).getUserPass().equals(user.getUserPass())) {
			return sqlSession.getMapper(UserMapper.class).userUpdate(user) == 1;
		}
		else
			return false;
	}
	
	@Override
	public boolean findPass(User user) {
//		logger.debug("{}", sqlSession.getMapper(UserMapper.class).userSelect(user.getUserId()).getUserPass());
//		logger.debug("바꾼거 : {}", user.getUserPass());
		return sqlSession.getMapper(UserMapper.class).userUpdate(user) == 1;
	}

	@Override
	public boolean userDelete(String userId) {
		return sqlSession.getMapper(UserMapper.class).userDelete(userId) == 1;

	}

	@Override
	public User login(User user) throws Exception{
		if(user.getUserId() == null || user.getUserPass() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(user);
			
	}
	
	@Override
	public User userInfo(String userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userId);
	}

}
