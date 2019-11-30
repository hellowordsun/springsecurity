package com.newperson.springbootsecurity.browser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * 
 * @author sunlianhui
 * @email sunlightcs@gmail.com
 * @date 2019-11-26 19:56:47
 */
@TableName("role")
@Data
@NoArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String roleName;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 
	 */
	private Integer userId;


}
