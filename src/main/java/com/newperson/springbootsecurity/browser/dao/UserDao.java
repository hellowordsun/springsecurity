package com.newperson.springbootsecurity.browser.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newperson.springbootsecurity.browser.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author sunlianhui
 * @email sunlightcs@gmail.com
 * @date 2019-11-26 18:44:37
 */
public interface UserDao extends BaseMapper<User> {

    User getUserAndRole(@Param("param") String param);
	
}
