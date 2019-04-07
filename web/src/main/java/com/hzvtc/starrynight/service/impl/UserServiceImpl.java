package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.comm.config.JwtUtils;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.hzvtc.starrynight.service.UserService;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 新增一个用户
     * @Param: User
     */
    @Override
    public void save(User user){
        userRepo.save(user);
    }


    /**
     * 根据name删除一个用户高
     * @Param: name
     */
    @Override
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public User loginCheck(User user) {
        return user;
    }

    @Override
    public User findByUserName(String username) {
        return userRepo.findByUserName(username);
    }

    @Override
    public User findByPhoneNumOrUserName(String phonrNum, String userName) {
        return userRepo.findByPhoneNumOrUserName(phonrNum, userName);
    }

    @Override
    public Page<User> findUsersByKey(int page, int pageSize, String key) {
//        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
//        Page<User> userPage = userRepo.findAll(new Specification<User>(){
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
////                Predicate p1 = criteriaBuilder.equal(root.get("key").as(String.class), key);
////                query.where(criteriaBuilder.and(p1));
//                return query.getRestriction();
//            }
//        },pageable);
        return userRepo.findAllByUserNameLike("%"+key+"%", PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    @Override
    public User getJwtTokenInfo(String username) {
        /**
         * 从数据库或者缓存中取出jwt token生成时用的salt
         * salt = redisTemplate.opsForValue().get("token:"+username);
         */
        User user = findByUserName(username);
        return user;
    }
    /**
     * 保存user登录信息，返回token
     * @param username
     */
    @Override
    public String generateJwtToken(String username) {
        /**
         * @todo 将salt保存到数据库或者缓存中
         * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
         */
        User user = findByUserName(username);
        return JwtUtils.sign(username, user.getSalt(), 3600); //生成jwt token，设置过期时间为1小时
    }
}