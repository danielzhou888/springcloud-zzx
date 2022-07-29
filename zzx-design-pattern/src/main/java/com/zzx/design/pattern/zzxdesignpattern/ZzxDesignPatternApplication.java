package com.zzx.design.pattern.zzxdesignpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class ZzxDesignPatternApplication {

    public static final List<String> list = new ArrayList<>();
    public static final Map<String, Object> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(ZzxDesignPatternApplication.class, args);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        map.put("zzx1", "zzx1");
        map.put("zzx2", "zzx2");
        map.put("zzx3", list);
    }

}
