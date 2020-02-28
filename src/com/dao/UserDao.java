package com.dao;

import java.util.HashMap;
import java.util.List;

import com.entity.User;

public interface UserDao {
	   /**
     * ��������ɾ��һ���û�����
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * ����һ���û����� �����Բ�����
     * @param record
     * @return
     */
    int insertSelective(User record);
    /**
     * ����������ѯһ���û�����
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);
    /**
     * �û���¼
     * @param record
     * @return
     */
    User loginByUserNameAndPassword(User record);
    /**
     * ������������һ���û����ݣ����Բ����� -
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * ��ѯ�����û�����
     * @return
     */
    List<User> selectUserList();
    /**
     * ��ѯ�û���¼����
     * @return
     */
    int selectCount();
    /**
     * ��ҳ����������findByPage limit��ҳ����
     * @param map
     * @return
     */
    List<User> findByPage(HashMap<String,Object> map);

}
