package com.hzvtc.starrynight.mapper;

import com.hzvtc.starrynight.entity.UserEntity;

import java.util.List;

/**
 * @Title: UserMapper
 * @Package: com.hzvtc.starrynight.mapper
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/14 16:38
 */
public interface UserMapper {
    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
