package com.example.cache.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.example.cache.vo.UserVO;

@Repository
public class UserRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepository.class);


    public UserVO findByNameNoCache(String name) {
        slowQuery(2000);
        return new UserVO(name+"@naver.com", name);
    }


    @Cacheable(value="findUserInfoCache", key="#name")
    public UserVO findByNameCache(String name) {
        slowQuery(2000);
        return new UserVO(name+ "@naver.com", name);
    }


    @CacheEvict(value = "findUserInfoCache", key="#name")
    public void refresh(String name) {
        logger.info(name + "ÀÇ Cache Clear!");
    }

    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
