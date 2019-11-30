package com.newperson.springbootsecurity.browser.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author sunlianhui
 * @email sunlightcs@gmail.com
 * @date 2019-11-26 18:44:37
 */
@TableName("user")
@Data
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */

	@TableField(exist = false)
	private List<Role> roles;


}
