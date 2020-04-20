package com.coldcast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldcast.bean.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	
}