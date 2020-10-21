package com.zzx.sentinel.orderweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zzx.sentinel.orderweb"})
public class SentinelOrderWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelOrderWebApplication.class, args);
	}

}
