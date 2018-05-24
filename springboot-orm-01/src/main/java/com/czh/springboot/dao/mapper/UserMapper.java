package com.czh.springboot.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.czh.springboot.dao.UserDao;

@Mapper
public interface UserMapper {

	UserDao getOne(Integer id);

	UserDao getOneForUpdate(Integer id);

	UserDao getOneLockShare(Integer id);

	int updateUserOfNameById(UserDao user);

	void lockUser();

	void unlockUser();

}
