package com.lgx.springbootredis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/8 16:45
 */
@RestController
public class RedisController {
    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;
    @GetMapping("redis/string")
    public String redisString(){//String 类型
        String key = "username";
        String value = "zhangsan";
        redisTemplate.opsForValue().set(key,value);
        return redisTemplate.opsForValue().get(key);
    }
    @GetMapping("redis/list")
    public List redisList() {//List  列表类型
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("course","java");
        listOperations.leftPush("course","c");
        listOperations.leftPush("course","c#");
        listOperations.leftPush("course","c++");
        List<String> course = listOperations.range("course", 0, -1);
        return course;
    }

    @GetMapping("/redis/set")
    public Set<String> redisSet(){
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("setTest","aaa","bbb","ccc","ddd","ddd");
        Set<String> setTest = setOperations.members("setTest");
        return setTest;
    }
    @GetMapping("/redis/zset")
    public Set<String> redisZset(){
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add("topn","aaaa",100);
        opsForZSet.add("topn","bbbb",80);
        opsForZSet.add("topn","cccc",120);
        opsForZSet.add("topn","dddd",60);
        Set<String> topn = opsForZSet.range("topn", 0, -1);
        return topn;
    }
    @GetMapping("/redis/hash")
    public Set<Object> redisHash(){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.put("user","name","liguixu");
        opsForHash.put("user","age","18");
        opsForHash.put("user","sex","1");
        opsForHash.put("user","hobby","basketball");
        Map<Object, Object> user = opsForHash.entries("user");
        Set<Object> objects = user.keySet();
        return objects;
    }
}
