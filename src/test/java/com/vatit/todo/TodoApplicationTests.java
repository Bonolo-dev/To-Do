package com.vatit.todo;

import com.vatit.todo.controllers.ToDosController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
	private ToDosController toDosController;

	@Test
	void contextLoads() {
		assertThat(this.toDosController).isNotNull();
	}

}
