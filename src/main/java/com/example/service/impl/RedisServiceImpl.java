package com.example.service.impl;

import com.example.logger.Logger;
import com.example.service.RedisService;
import com.example.utils.Generator;
import io.lettuce.core.ScanIterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.IOException;
import java.util.*;

import static com.example.utils.Generator.phoneNumberGenerator;
import static redis.clients.jedis.ScanParams.SCAN_POINTER_START;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String defaultPhoneNumber = "888000888";
    private static final String defaultBoxMachineName = "KRK132";
    private static final String defaultProfile = "P0";

    @Override
    public void generateRedisData() {
        redisTemplate.opsForHash().put("phone:" + defaultPhoneNumber + ":" + defaultProfile, "MULTI_COMPARTMENT_DENIED", "true");

        for (int i = 0; i < 10; i++) {
            String phoneNumber = phoneNumberGenerator();
            String profileName = "testProfile";
            String boxMachineName = "KRK132";

            redisTemplate.opsForHash().put("phone2:" + phoneNumber + ":" + profileName, "MULTI_COMPARTMENT_DENIED", "true");

            Map<String, String> valueMap2 = new HashMap<>();
            valueMap2.put("GODZINA_ODBIORU", "P2");

            redisTemplate.opsForHash().putAll("phone2:" + phoneNumber + ":box:" + boxMachineName+ ":" + profileName, valueMap2);
        }

        for (int i = 0; i < 10; i++) {
            String phoneNumber = phoneNumberGenerator();
            String profileName = "testProfile";
            String boxMachineName = "KRK132";

            Map<String, String> valueMap2 = new HashMap<>();
            valueMap2.put("MULTI_COMPARTMENT_DENIED", "true");
            valueMap2.put("GODZINA_ODBIORU" + boxMachineName, "P2");

            redisTemplate.opsForHash().putAll("phone3:" + phoneNumber + ":" + profileName, valueMap2);
        }

        for (int i = 0; i < 10; i++) {
            String phoneNumber = phoneNumberGenerator();
            String profileName = "testProfile";
            String boxMachineName = "KRK132";
            String pickupHour = "P1";

            Map<String, String> valueMap2 = new HashMap<>();
            valueMap2.put(boxMachineName, "P2");

            redisTemplate.opsForHash().put("phone5:" + phoneNumber + ":" + profileName, "MULTI_COMPARTMENT_DENIED", "true");

            redisTemplate.opsForHash().putAll("phone5:" + phoneNumber + ":profile:" + pickupHour, valueMap2);
        }
    }

    @Override
    public List<String> getDataFromRedis(String pattern) {
//
//        127.0.0.1:6379> scan 0 match phone:888000888*
//        1) "6"
//        2) (empty array)
//        127.0.0.1:6379> scan 6 match phone:888000888*
//        1) "23"
//        2) 1) "phone:888000888:P0"
//        127.0.0.1:6379>
//        https://stackoverflow.com/questions/44187569/spring-redis-get-values-by-wildcard-keys


//        hscan "phone:888000888:P0" 0 match MULTI*

//        There is no Redis command to scan by values on a hash. HSCAN scans the field-names, not the values.

//        Cursor<Map.Entry<String, String>> cursor = redisTemplate.opsForHash().scan("phoneNumber2:153201657*", ScanOptions.scanOptions().match("MULTI*").count(6).build());
//
//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//
//        ScanOptions scanOptions = ScanOptions.scanOptions().match("phoneNumber2:153201657*").count(100).build();

        System.out.println(pattern);
        ScanParams scanParams = new ScanParams().count(10).match(pattern);
        Jedis jedis = new Jedis("localhost");
        String cur = SCAN_POINTER_START;
        List<String> list = new ArrayList<>();

        do {
            ScanResult<String> scanResult = jedis.scan(cur, scanParams);
//            System.out.println("Lista: " + scanResult.getResult());
            scanResult.getResult().stream().forEach(System.out::println);
            cur = scanResult.getCursor();
            scanResult.getResult().stream().forEachOrdered(list :: add);

        } while (!cur.equals(SCAN_POINTER_START));

        System.out.println(list.size());

//
//        System.out.println(" redisTemplae" + cursor.getPosition());
//
////        System.out.println(redisTemplate.keys("phone:888000888*"));
//            System.out.println(cursor.getCursorId());
//            System.out.println(cursor);
//            if (cursor.hasNext()) {
//                while (cursor.hasNext()) {
//                    Map.Entry<String, String> entry = cursor.next();
//                    System.out.println(entry.getValue());
//                }
//            }


            return list;
    }

    @Override
    public void flushDatabase() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}
