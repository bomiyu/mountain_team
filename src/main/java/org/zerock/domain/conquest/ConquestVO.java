package org.zerock.domain.conquest;

import java.util.Date;

import lombok.Data;

@Data
public class ConquestVO {
	private Long no;
	private Long member_no; //회원번호(pk)
	private int conquestcnt; //추가됨 -> 산별 정복 카운트
	private int mountain_no;//산 번호(pk)
    private Date condate; // (==regdate ) 점령날짜 현재날짜  
	
}