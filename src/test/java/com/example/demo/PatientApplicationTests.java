package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Désactivé pour Github Actions : nécessite la configuration complète de l'application")
@SpringBootTest
class PatientApplicationTests {

	@Test
	void contextLoads() {
	}

}
