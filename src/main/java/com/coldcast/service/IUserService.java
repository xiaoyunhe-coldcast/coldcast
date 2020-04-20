package com.coldcast.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coldcast.bean.User;

public interface IUserService {
	
	public <T> Page<T> selectPage(Page<T> page);
	
	/**
	 *@{author} jifeng
	 *@{date} 2020年4月20日
	 *@{tags} 查询用户所有信息
	 */
	public List<User> findAll();
	
	/**
	 *@{author} jifeng
	 *@{date} 2020年4月20日
	 *@{tags} 新增用户
	 */
	public void add(User user);
	
	/**
	 *@{author} jifeng
	 *@{date} 2020年4月20日
	 *@{tags} 新增用户信息
	 */
	public void updateByUser(User user);
	
	/**
	 *@{author} jifeng
	 *@{date} 2020年4月20日
	 *@{tags} 删除用户
	 */
	public void deleteById(Long id);
	
	
	/**
	 *@{author} jifeng
	 *@{date} 2020年4月20日
	 *@{tags} @param name
	 *@{tags} 根据用户名查询用户
	 */
	List<User> getUserByName(String name);
	
	
}
