package com.beerhouse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses = Application.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}