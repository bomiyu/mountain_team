package org.zerock.domain.member;

import lombok.Data;

@Data
public class MLocDTO {
	private String postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	
	// ##주소 부분 나누기
	public void locSplit(String loc) {
		String locDiv[] = loc.split("@");
		
		if(locDiv !=null && locDiv.length >= 2 ) {
			postcode = locDiv[0];
			address = locDiv[1];
			detailAddress = locDiv[2];
			extraAddress = locDiv[3];
		}
		
	}
}
