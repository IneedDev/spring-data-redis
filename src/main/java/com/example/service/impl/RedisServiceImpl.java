package com.example.service.impl;

import com.example.logger.Logger;
import com.example.model.DataRedisInput;
import com.example.service.RedisService;
import com.example.utils.Generator;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanIterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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
    public void generateRedisData(int version, int volume) {
        redisTemplate.opsForHash().put("phone:" + defaultPhoneNumber + ":" + defaultProfile, "MULTI_COMPARTMENT_DENIED", "true");

        switch (version) {
            case 2:
                generateDataStructureVersion2(volume);
                break;
            case 3:
                generateDataStructureVersion3(volume);
                break;
            case 5:
                generateDataStructureVersion5(volume);
                break;
        }
    }

    private void generateDataStructureVersion5(int volume) {
        Map<String, String> valueMap2 = new HashMap<>();
        valueMap2.put(defaultBoxMachineName, "P2");

        for (int i = 0; i < volume; i++) {
            if (i % 100000 == 0) {
                System.out.print("|");
            }

            redisTemplate.opsForHash().put("phone5:" + getDataRedisInputBuilder().getPhoneNumber()  + ":" + getDataRedisInputBuilder().getProfileName(), "MULTI_COMPARTMENT_DENIED", "true");

            redisTemplate.opsForHash().putAll("phone5:" + getDataRedisInputBuilder().getPhoneNumber()  + ":profile:" + getDataRedisInputBuilder().getPickupHour(), valueMap2);
        }
    }

    private void generateDataStructureVersion3(int volume) {
        Map<String, String> valueMap2 = new HashMap<>();
        valueMap2.put("MULTI_COMPARTMENT_DENIED", "true");
        valueMap2.put("GODZINA_ODBIORU" + defaultBoxMachineName, "P2");

        for (int i = 0; i < volume; i++) {
            if (i % 100000 == 0) {
                System.out.print("|");
            }
            redisTemplate.opsForHash().putAll("phone3:" + getDataRedisInputBuilder().getPhoneNumber() + ":" + getDataRedisInputBuilder().getProfileName(), valueMap2);
        }
        System.out.println();
    }

    private void generateDataStructureVersion2(int volume) {
        Map<String, String> valueMap2 = new HashMap<>();
        valueMap2.put("GODZINA_ODBIORU", "P2");

        for (int i = 0; i < volume; i++) {
            redisTemplate.opsForHash().put("phone2:" + getDataRedisInputBuilder().getPhoneNumber() + ":" + getDataRedisInputBuilder().getProfileName(), "MULTI_COMPARTMENT_DENIED", "true");
            redisTemplate.opsForHash().putAll("phone2:" + getDataRedisInputBuilder().getPhoneNumber() + ":box:" + getDataRedisInputBuilder().getBoxMachineName()+ ":" + getDataRedisInputBuilder().getProfileName(), valueMap2);
        }
    }

    private DataRedisInput getDataRedisInputBuilder() {
        return DataRedisInput.builder()
                .phoneNumber(phoneNumberGenerator())
                .profileName("testProfile")
                .pickupHour("KRK132").build();
    }

    @Override
    public List<String> getDataFromRedisByKeyMatchingPattern(String pattern) {
//        ScanParams scanParams = new ScanParams().count(10).match(pattern);
//        Jedis jedis = new Jedis("localhost");
//        String cur = SCAN_POINTER_START;
//        List<String> list = new ArrayList<>();
//        do {
//            ScanResult<String> scanResult = jedis.scan(cur, scanParams);
//            cur = scanResult.getCursor();
//            scanResult.getResult().stream().forEachOrdered(list :: add);
//        } while (!cur.equals(SCAN_POINTER_START));
//        return list;

        ScanOptions options = ScanOptions.scanOptions().match("phone3:767857180:testProfile").count(100).build();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection conn = factory.getConnection();
        Cursor<byte[]> cursor = conn.scan(options);
        List<String> result = new ArrayList<String>();
        while(cursor.hasNext()){
            String key=new String((byte[]) cursor.next());
            System.out.println(key);
        }
        return new ArrayList<>();
    }

    @Override
    public void flushDatabase() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}
