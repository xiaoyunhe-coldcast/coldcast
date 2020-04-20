package com.coldcast.service.imple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coldcast.bean.User;
import com.coldcast.mapper.UserMapper;
import com.coldcast.service.IUserService;

@Service
public class IUserServiceImple implements IUserService{
	
	@Autowired
	private UserMapper mapper;

	@Override
	public <T> Page<T> selectPage(Page<T> page) {
		
		return null;
	}
	
	@Override
	public List<User> findAll() {
	    QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
	    return mapper.selectList(queryWrapper);
	}
	
	/**
	 * 用户信息新增
	 */
	@Override
	public void add(User user) {
	    mapper.insert(user);
	}
	
	@Override
	public void updateByUser(User user) {
		mapper.updateById(user);
	}
	
	@Override
	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public List<User> getUserByName(String name) {
		return mapper.selectList(new QueryWrapper<User>()
				.eq("name" , name)
				.between("age", 18, 60)
				.or()
				.isNotNull("age"));
	}

}
