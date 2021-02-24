package org.zerock.domain.admin;

import lombok.Data;

@Data
public class AdminMemberVO {
	private Long no;
	private String id;
	private String name;
	private String nickname;
	private String password;
	private String email;
	private String loc;	
	private int manager;
}
