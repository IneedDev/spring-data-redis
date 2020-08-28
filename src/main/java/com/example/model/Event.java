package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Event")
@Data
@AllArgsConstructor
public class Event {

    private String id;
    private String phoneNumber;
    private boolean stackingDenied;
    private String boxMachineName;
    private int preferredPickupDate;
}
