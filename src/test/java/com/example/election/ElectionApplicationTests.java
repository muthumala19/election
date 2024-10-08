package com.example.election;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectionApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void setup() {
		loadEnv();
	}

	public static void loadEnv() {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

}
