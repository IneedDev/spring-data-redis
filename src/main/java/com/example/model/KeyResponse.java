package com.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KeyResponse {

    private List<String> list = new ArrayList<>();
}
