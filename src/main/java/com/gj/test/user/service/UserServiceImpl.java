package com.gj.test.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gj.frameworks.data.service.BaseCrudService;
import com.gj.test.user.model.User;

@Service
@Transactional
public class UserServiceImpl extends BaseCrudService<User> implements UserService {
	
}