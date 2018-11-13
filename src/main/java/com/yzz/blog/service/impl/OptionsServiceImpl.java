package com.yzz.blog.service.impl;

import com.yzz.blog.entity.Options;
import com.yzz.blog.entity.custom.OptionsCustom;
import com.yzz.blog.mapper.OptionsMapper;
import com.yzz.blog.mapper.custom.OptionsMapperCustom;
import com.yzz.blog.service.OptionsService;
import com.yzz.blog.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 言曌 on 2017/9/7.
 */
public class OptionsServiceImpl implements OptionsService {

	@Autowired
	private OptionsMapperCustom optionsMapperCustom;

	@Autowired
	private OptionsMapper optionsMapper;

	@Autowired
	private RedisUtil<Options> redisUtil;


	@Override
	public OptionsCustom getOptions(){


		Options options = redisUtil.get("options");

		OptionsCustom optionsCustom = new OptionsCustom();

		if(options == null) {
			options = optionsMapperCustom.getOptions();

			if(null != options){
				redisUtil.set("options",options);
				BeanUtils.copyProperties(options, optionsCustom);
			}
		}else {
			BeanUtils.copyProperties(options, optionsCustom);
		}
		return optionsCustom;
	}

	@Override
	public void insertOptions(Options options) throws Exception {
		optionsMapper.insertSelective(options);
	}

	@Override
	public void updateOptions(Options options) throws Exception {
		redisUtil.del("options");
		optionsMapper.updateByPrimaryKeySelective(options);
	}
}
