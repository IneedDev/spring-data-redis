package com.example.service;

import java.util.List;

public interface RedisService {

    void generateRedisData (int version, int volume);

    List<String> getDataFromRedisByKeyMatchingPattern(String pattern);

    void flushDatabase();
}
