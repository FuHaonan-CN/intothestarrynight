package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.comm.config.JwtUtils;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.repository.UserRepo;
//import com.hzvtc.starrynight.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.hzvtc.starrynight.service.UserService;

import java.util.Optional;
import java.util.Set;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "my-redis-starry")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final UserRepo userRepo;

//    @Autowired
//    private RedisService redisService;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 新增一个用户
     * @param user .
     * @return User
     */
    @Override
    public User save(User user){
        return userRepo.save(user);
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
    public int deleteByIdFalse(Long id) {
        return userRepo.deleteByIdFalse(id);
    }

    @Override
    public User loginCheck(User user) {
        return user;
    }
    /**
     * cacheNames 与 value 定义一样，设置了 value 的值，则类的 cacheNames 配置无效。<br>
     * 使用 keyGenerator ，注意是否在config文件中定义好。
     * key ="#p0" 表示以第1个参数作为 key
     */
    @Override
//    @Cacheable(value="user", key ="#p0")
//    @Cacheable(cacheNames = "111", key = "111")
//    @Cacheable(value = "user")
    public User findByUserName(String username) {
//        String key = "user_" + username;
//
//        boolean hasKey = redisUtil.hasKey(key);
//        if (hasKey) {
//            User user = (User) redisUtil.get(key);
//
//            System.out.println("==========从缓存中获得数据=========");
//            System.out.println(user.toString());
//            System.out.println("==============================");
//        } else {
//            User user = userRepo.findByUserName(username);
//            System.out.println("==========从数据表中获得数据=========");
//            System.out.println(user.toString());
//            System.out.println("==============================");
//
//            // 写入缓存
//            redisUtil.set(key, user.toString());
//            return user;
//        }
//        return userRepo.findByUserName(username);

        /*String key = "user:" + username;

        boolean hasKey = redisService.stringHasKey(key);
        if (hasKey) {
            User user = (User) redisService.getString(key);

            System.out.println("==========从缓存中获得数据=========");
            System.out.println(user.toString());
            System.out.println("==============================");
        } else {
            User user = userRepo.findByUserName(username);
            System.out.println("==========从数据表中获得数据=========");
            System.out.println(user.toString());
            System.out.println("==============================");

            // 写入缓存
            redisService.cacheSet(key, user);
            return user;
        }*/
        return userRepo.findByUserName(username);

    }

    @Override
    public User findByPhoneNum(String phoneNum) {
        return userRepo.findByPhoneNum(phoneNum);
    }

//    @Override
//    public boolean checkRegister(String phoneNum, String userName) throws Exception{
//        boolean flag = true;
//        if (isNotEmpty(userRepo.findByPhoneNum(phoneNum))){
//            throw new UserException(EmExceptionMsg.PhoneUsed);
//        } else {
//            flag = false;
//        }
//        if (isNotEmpty(userRepo.findByUserName(userName))){
//            throw new UserException(EmExceptionMsg.UserNameUsed);
//        } else {
//            return flag;
//        }
//        return flag;
//    }

    /**
     * key ="#p0" 表示以第1个参数作为 key
     */
    @Override
//    @Cacheable(key = "#p0")
    public User findById(Long userId) {
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        Optional<User> user = userRepo.findById(userId);
        return user.orElse(null);
//        return Optional.ofNullable(user).get().orElse(null);
    }

    @Override
    public Set<User> findUsersByRId(Long roleId) {
        return userRepo.findUsersByRId(roleId);
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
     * 将salt保存到数据库或者缓存中
     * @param username .
     */
    @Override
    public String generateJwtToken(String username) {
//        redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
        User user = findByUserName(username);
        //生成jwt token，设置过期时间为1小时
        return JwtUtils.sign(username, user.getSalt(), 3600);
//        return null;
    }


}