package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.utils.Generator.phoneNumberGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataRedisInput {

    private String phoneNumber = phoneNumberGenerator();
    private String profileName = "testProfile";
    private String boxMachineName = "KRK132";
    private String pickupHour = "P1";

}
