package org.zerock.domain.conquest;

import java.util.Date;

import lombok.Data;

@Data 
public class ConquestVO { 
	private Long no;
	private Long member_no; //회원번호(pk)
	private int conquestcnt; //추가됨 -> 산별 정복 카운트
	private Long mountain_no;//산 번호(pk) pk니까 long
    private Date condate; // (==regdate ) 점령날짜 현재날짜  
    private int checkCnt; //누가 어디산 실제 레코드갯수 0 or 0아니면 확인용
}
