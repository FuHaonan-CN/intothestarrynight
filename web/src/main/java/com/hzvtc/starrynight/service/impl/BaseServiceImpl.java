package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description: BaseServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class BaseServiceImpl implements BaseService {
//    @Autowired
//    protected  RedisUtil redisUtil;

    /**
     * 判断对象是否存在
     * @param object .
     * @return boolean
     */
    @Override
    public boolean isEmpty(Object object){
        boolean flag = false;
        if (StringUtils.isEmpty(object)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

}