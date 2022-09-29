package com.zpepdi.eureka_client.config;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        //自主实现我们的redisTemplate方法。
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        //设置连接工厂
        redisTemplate.setConnectionFactory(factory);
        //序列化对象
        //简单的字符串序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //jdk序列化
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        //Json序列化
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer  = new GenericJackson2JsonRedisSerializer ();
        //设置String键的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //设置String值的序列化方式
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
