package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Event")
@Data
public class Event {

    private int id;
    private String phoneNumber;
    private boolean stackingDenied;
    private String boxMachineName;
    private int preferredPickupDate;

    public Event(int id, String phoneNumber, boolean stackingDenied, String boxMachineName, int preferredPickupDate) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.stackingDenied = stackingDenied;
        this.boxMachineName = boxMachineName;
        this.preferredPickupDate = preferredPickupDate;
    }
}
