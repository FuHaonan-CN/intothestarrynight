package com.hzvtc.starrynight.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis配置类
 * Configuration 用于定义配置类，可替换xml配置文件.
 * EnableAutoConfiguration 启用Spring应用程序上下文的自动配置
 * ConfigurationProperties 用于读取配置文件的信息
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/12 22:17
 */
@Configuration
@EnableCaching
@EnableAutoConfiguration
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 缓存对象集合中，缓存是以 key-value 形式保存的。
     * 当不指定缓存的 key 时，SpringBoot 会使用 SimpleKeyGenerator 生成 key。
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        // 配置key的生成规则
        // key前缀，用于区分不同项目的缓存，建议每个项目单独设置
        final String PRE_KEY = "starryNight";
        final char sp = ':';
        // custom cache key
        final int NO_PARAM_KEY = 0;
        final int NULL_PARAM_KEY = 53;

        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(PRE_KEY);
                sb.append(sp);
                sb.append(target.getClass().getSimpleName());
                sb.append(sp);
                sb.append(method.getName());
                sb.append(sp);
                if (params.length == 0) {
                    return sb.append(NO_PARAM_KEY).toString();
                }
                for (Object param : params) {
                    if (param == null) {
                        log.warn("input null param for Spring cache, use default key={}", NULL_PARAM_KEY);
                        sb.append(NULL_PARAM_KEY);
                    } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                        int length = Array.getLength(param);
                        for (int i = 0; i < length; i++) {
                            sb.append(Array.get(param, i));
                            sb.append(',');
                        }
                    } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                        sb.append(param);
                    } else {
                        log.warn("Using an object as a cache key may lead to unexpected results. " +
                                "Either use @Cacheable(key=..) or implement CacheKey. Method is " + target.getClass() + "#" + method.getName());
                        sb.append(param.hashCode());
                    }
                    sb.append('-');
                }
                return sb.toString();
            }
        };
    }

    /** 缓存管理器配置*/
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        // 更改值的序列化方式，否则在Redis可视化软件中会显示乱码。默认为JdkSerializationRedisSerializer
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());

        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存的默认过期时间，也是使用Duration设置
        config = config.entryTtl(Duration.ofMinutes(10))
                // 设置序列化方式
                .serializeValuesWith(pair)
                // 不缓存空值
                .disableCachingNullValues();

        // 设置一个初始化的缓存空间set集合
        // 后续类上的缓存名称 得用这边定义好的 才不会乱码？？？
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("my-redis-starry");
        cacheNames.add("my-redis-starry2");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(16);
        configMap.put("my-redis-starry", config);
        configMap.put("my-redis-starry2", config.entryTtl(Duration.ofSeconds(120)));

        // 使用自定义的缓存配置初始化一个cacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        // 启用事务 可能有未知错误 有事务陷阱
        template.setEnableTransactionSupport(true);
        return template;
    }
}
