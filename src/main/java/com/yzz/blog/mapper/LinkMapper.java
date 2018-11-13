package com.yzz.blog.mapper;

import com.yzz.blog.entity.Link;

public interface LinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);
}
