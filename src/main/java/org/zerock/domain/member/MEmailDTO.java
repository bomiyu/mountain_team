package org.zerock.domain.member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MEmailDTO {
	private String emailFront;
	private String emailSelect;
	
	// ##이메일 부분 나누기
	public void emailSplit(String email) {
		
		String emailDiv[] = email.split("@");
		
		if(emailDiv != null && emailDiv.length >= 2) {
			emailFront = emailDiv[0];
			emailSelect = emailDiv[1];
		}
		
		//세션에 담아서 보내주기
		//언제?? 언제 보내줘??
	}

	
}
