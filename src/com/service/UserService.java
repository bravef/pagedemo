package com.service;

import java.util.List;

import com.entity.PageBean;
import com.entity.User;

/**
 * User类业务层接口
 */
public interface UserService {
	 int deleteByPrimaryKey(Integer id);

	    int insertSelective(User record);

	    User selectByPrimaryKey(Integer id);

	    User loginByUserNameAndPassword(User record);

	    List<User> selectUserList();

	    int selectCount();

	    int updateByPrimaryKeySelective(User record);

	    PageBean<User> findByPage(int currentPage);

}
