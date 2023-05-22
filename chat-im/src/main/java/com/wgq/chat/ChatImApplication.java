package com.wgq.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.Max;

@SpringBootApplication
@MapperScan("com.wgq.chat.mapper")
public class ChatImApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatImApplication.class, args);
	}

}
