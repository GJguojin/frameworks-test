package com.gj.test.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.frameworks.cache.CommonCache;
import com.gj.test.WebTestConfiguration;
import com.gj.test.user.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebTestConfiguration.class)
public class UserServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private CommonCache commonCache;

	@Test
	public void testGet() throws JsonProcessingException {
		User user = userService.get(1L);
		String writeValueAsString = objectMapper.writeValueAsString(user);
		logger.info(writeValueAsString);
		
		commonCache.cache(""+user.getId(), user, 60);
	}
	
	@Test
	public void testInsert() throws JsonProcessingException {
		User user = new User();
		user.setName("王五");
		user.setPassword("111111");
		User create = userService.create(user);
		logger.info(objectMapper.writeValueAsString(create));
	}

}
