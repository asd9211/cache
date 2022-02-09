package com.example.cache.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.example.cache.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepository.class);


    public UserVO findByNameNoCache(String name) {
        slowQuery(2000);
        return new UserVO(0, name+"@gmail.com", name);
    }


    @Cacheable(value="findUserInfoCache", key="#name")
    public UserVO findByNameCache(String name) {
        slowQuery(2000);
        return new UserVO(0, name+ "@gmail.com", name);
    }


    @CacheEvict(value = "findUserInfoCache", key="#name")
    public void refresh(String name) {
        logger.info(name + "의 Cache Clear!");
    }

    // 빅쿼리를 돌린다는 가정
    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
