package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Xy01;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: UserMapper
 * @Package: com.hzvtc.starrynight.repository
 * @Description: 数据库user表相关操作
 * @Author: fhn
 * @Date: 2018/12/14 16:38
 */
@Component
public interface UserMapper {
    /**
     * @Description: 模糊查询
     * @Return: java.util.List<com.hzvtc.starrynight.entity.Xy01>
     */
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    List<Xy01> getAll();

    /**
     * @Description: 根据id查询
     * @Param: [id]
     * @Return: com.hzvtc.starrynight.entity.Xy01
     */
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    Xy01 getOne(Long id);

    @Insert("INSERT INTO xy01(xy0101,xy0102,xy0103) VALUES(#{xy0101}, #{xy0102}, #{xy0103})")
    void insert(Xy01 user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(Xy01 user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
