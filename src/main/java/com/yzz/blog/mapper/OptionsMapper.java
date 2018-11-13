package com.yzz.blog.mapper;

import com.yzz.blog.entity.Options;

public interface OptionsMapper {
    int deleteByPrimaryKey(Integer optionId);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Integer optionId);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);
}
