package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Description: BaseServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class BaseServiceImpl implements BaseService {
    /**
     * 判断是否为空
     * @param object .
     * @return boolean
     */
    @Override
    public boolean isPresent(Object object){
        boolean flag = false;
        if (object == null) {
            flag = true;
        }
        return flag;
    }


}