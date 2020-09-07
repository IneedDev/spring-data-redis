package com.example.service;

import java.util.List;

public interface RedisService {

    void generateRedisData ();

    List<String> getDataFromRedis(String pattern);

    void flushDatabase();
}
