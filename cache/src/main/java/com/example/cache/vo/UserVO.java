package com.example.cache.vo;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO implements Serializable {
	public UserVO(int idx, String email, String name) {
		this.idx = idx;
		this.email = email;
		this.name = name;
	}
	public UserVO() {

	}
    private int idx;

    private String email;

    private String name;
}
