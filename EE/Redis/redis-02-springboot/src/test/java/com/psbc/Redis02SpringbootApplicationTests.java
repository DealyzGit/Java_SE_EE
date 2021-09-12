package com.psbc;

import com.psbc.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test() {
        redisUtil.set("名字", "陈小丹");
        System.out.println(redisUtil.get("名字"));
    }

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("myKey", "myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }
}
