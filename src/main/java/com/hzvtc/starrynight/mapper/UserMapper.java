package com.hzvtc.starrynight.mapper;

import com.hzvtc.starrynight.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Title: UserMapper
 * @Package: com.hzvtc.starrynight.mapper
 * @Description: 数据库user表相关操作
 * @Author: fhn
 * @Date: 2018/12/14 16:38
 */
public interface UserMapper {
    /**
     * @Description: 模糊查询
     * @Return: java.util.List<com.hzvtc.starrynight.entity.UserEntity>
     */
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

    /**
     * @Description: 根据id查询
     * @Param: [id]
     * @Return: com.hzvtc.starrynight.entity.UserEntity
     */
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
