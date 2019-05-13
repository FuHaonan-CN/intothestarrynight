package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * TODO .
 *
 * @author FHN
 * @date 2019/4/6 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;
    /* 普通redis引用 */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    /* 通过redis工具类使用 */
//    @Autowired
//    private RedisUtil redisUtil;

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    /**
     * 测试saveOrEdit
     */
    @Test
    public void saveOrEdit() throws Exception {
        /* add */
        User user = new User();
        user.setUserName("admin2");
        user.setUserPassWord("admin2");
        User save = userRepo.save(user);
        System.out.println("id = " + save.getId());
        System.out.println("save = " + save.toString());

        /* edit */
//        User user = userRepo.findById(10);
//        user.setUserName("admin33");
//        user.setUserPassWord("admin33");
//        User save = userRepo.save(user);
//
//        System.out.println("id = " + save.getId());
//        System.out.println("save = " + save.toString());
    }

    /**
     * 测试fingById
     */
    @Test
    public void fingById() throws Exception {
        Optional<User> user = userRepo.findById(1L);

        User save = user.orElse(null);
        System.out.println("id = " + save.getId());
        System.out.println("IsDel = " + save.getIsDel());
        System.out.println("save = " + save.toString());
    }

    /**
     * 测试deleteById
     */
    @Test
    @Transactional
    public void deleteById() throws Exception {
        /* 逻辑删除 */
//        User user = userRepo.findById(1);
//        user.setIsDel(1);
//        User save = userRepo.save(user);

//        System.out.println("id = " + save.getId());
//        System.out.println("save = " + save.toString());

        int b = userRepo.deleteByIdFalse(1);
        System.out.println("b = " + b);


        /* 物理删除 */
        // userRepo.deleteById(11);
    }

    @Test
    public void test() throws Exception {
//        redisUtil.set("bbb", "bbb");
//        System.out.println("redisUtil.get(\"bbb\") = " + redisUtil.get("bbb"));
//        Assert.assertEquals("bbb", redisUtil.get("bbb"));

//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    @Test
    public void redisTest() throws Exception {
//        redisUtil.set("aaa", "111");
//        Assert.assertEquals("111", redisUtil.get("aaa"));

        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
//        User user=new User();
//        ValueOperations<String, User> operations=redisTemplate.opsForValue();
//        operations.set("com.neox", user);
//        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
//        Thread.sleep(1000);
//        //redisTemplate.delete("com.neo.f");
//        boolean exists=redisTemplate.hasKey("com.neo.f");
//        if(exists){
//            System.out.println("exists is true");
//        }else{
//            System.out.println("exists is false");
//        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

}