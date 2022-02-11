package com.example.cache.vo;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO implements Serializable {
	public UserVO(String email, String name) {
		this.email = email;
		this.name = name;
	}
	public UserVO() {

	}

    private String email;

    private String name;
}
