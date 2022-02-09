package com.example.cache.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class CacheManagerChk implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CacheManagerChk.class);

    private final CacheManager cacheManager;

    public CacheManagerChk(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("\n\n" + "=========================================================\n"
                + "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
                + "=========================================================\n\n");
	}

}
