package com.hzvtc.starrynight.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description: BaseService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
@Transactional
public interface BaseService {

    /**
     * 判断是否为空
     * @param object
     * @return boolean
     */
    public boolean isEmpty(Object object);


    /**
     * 判断是否不为空
     * @param object
     * @return boolean
     */
    public boolean isNotEmpty(Object object);


}
