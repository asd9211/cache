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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	UserRepository userRepo;

	@GetMapping("/user/nocache/{name}")
    @ResponseBody
    public UserVO getNoCacheUser(@PathVariable String name){

        long start = System.currentTimeMillis(); // ����ð� ����
        UserVO userVO = userRepo.findByNameNoCache(name); // db ��ȸ
        long end = System.currentTimeMillis();

        logger.info(name+ "�� NoCache ����ð� : "+ Long.toString(end-start));

        return userVO;
    }

    @GetMapping("/user/cache/{name}")
    @ResponseBody
    public UserVO getCacheUserVO(@PathVariable String name){

        long start = System.currentTimeMillis(); // ����ð� ����
        UserVO UserVO = userRepo.findByNameCache(name); // db ��ȸ
        long end = System.currentTimeMillis();

        logger.info(name+ "�� Cache ����ð� : "+ Long.toString(end-start));

        return UserVO;
    }

    @GetMapping("/user/refresh/{name}")
    @ResponseBody
    public String refresh(@PathVariable String name){
    	userRepo.refresh(name); // ĳ������
        return "cache clear!";
    }


}
