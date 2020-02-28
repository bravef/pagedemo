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
 * User��ҵ���ʵ����
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

	        //��װ��ǰҳ��
	        pageBean.setCurrPage(currentPage);

	        //ÿҳ��ʾ������
	        int pageSize=2;
	        pageBean.setPageSize(pageSize);

	        //��װ�ܼ�¼��
	        int totalCount = userDao.selectCount();
	        pageBean.setTotalCount(totalCount);

	        //��װ��ҳ��
	        double tc = totalCount;
	        Double num =Math.ceil(tc/pageSize);//����ȡ��
	        pageBean.setTotalPage(num.intValue());

	        map.put("start",(currentPage-1)*pageSize);
	        map.put("size", pageBean.getPageSize());
	        //��װÿҳ��ʾ������
	        List<User> lists = userDao.findByPage(map);
	        pageBean.setLists(lists);

	        return pageBean;
	    }

}
