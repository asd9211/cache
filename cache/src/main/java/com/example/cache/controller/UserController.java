package com.example.cache.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.repository.UserRepository;
import com.example.cache.vo.UserVO;

@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	UserRepository userRepo;

	@GetMapping("/user/nocache/{name}")
	public UserVO getNoCacheUser(@PathVariable String name) {

		long start = System.currentTimeMillis();
		UserVO userVO = userRepo.findByNameNoCache(name);
		long end = System.currentTimeMillis();

		logger.info("NoCache 수행시간 : " + Long.toString(end - start));

		return userVO;
	}

	@GetMapping("/user/cache/{name}")
	public UserVO getCacheUserVO(@PathVariable String name) {

		long start = System.currentTimeMillis();
		UserVO UserVO = userRepo.findByNameCache(name);
		long end = System.currentTimeMillis();

		logger.info("Cache 수행시간 : " + Long.toString(end - start));

		return UserVO;
	}

	@GetMapping("/user/refresh/{name}")
	public String refresh(@PathVariable String name) {
		userRepo.refresh(name); // 캐시제거
		return "cache clear!";
	}

}
