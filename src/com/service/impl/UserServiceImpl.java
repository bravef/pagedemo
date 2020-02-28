package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.PageBean;
import com.entity.User;
import com.service.UserService;

/**
 * User类业务层实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	 @Resource
	    private UserDao userDao;
	    @Override
	    public int deleteByPrimaryKey(Integer id) {
	        return userDao.deleteByPrimaryKey(id);
	    }

	    @Override
	    public int insertSelective(User record) {

	        return userDao.insertSelective(record);
	    }

	    @Override
	    public User selectByPrimaryKey(Integer id) {

	        return userDao.selectByPrimaryKey(id);
	    }

	    @Override
	    public int updateByPrimaryKeySelective(User record) {
	        return userDao.updateByPrimaryKeySelective(record);
	    }

	    @Override
	    public List<User> selectUserList() {

	        return userDao.selectUserList();
	    }

	    @Override
	    public int selectCount() {
	        return userDao.selectCount();
	    }

	    @Override
	    public User loginByUserNameAndPassword(User record) {

	        return userDao.loginByUserNameAndPassword(record);
	    }

	    @Override
	    public PageBean<User> findByPage(int currentPage) {
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        PageBean<User> pageBean = new PageBean<User>();

	        //封装当前页数
	        pageBean.setCurrPage(currentPage);

	        //每页显示的数据
	        int pageSize=2;
	        pageBean.setPageSize(pageSize);

	        //封装总记录数
	        int totalCount = userDao.selectCount();
	        pageBean.setTotalCount(totalCount);

	        //封装总页数
	        double tc = totalCount;
	        Double num =Math.ceil(tc/pageSize);//向上取整
	        pageBean.setTotalPage(num.intValue());

	        map.put("start",(currentPage-1)*pageSize);
	        map.put("size", pageBean.getPageSize());
	        //封装每页显示的数据
	        List<User> lists = userDao.findByPage(map);
	        pageBean.setLists(lists);

	        return pageBean;
	    }

}
