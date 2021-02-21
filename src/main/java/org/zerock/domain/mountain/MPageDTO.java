package org.zerock.domain.mountain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MPageDTO {
	private int total;// 총 게시물 수
	private MCriteria cri;// 현재 페이지, 한 페이지당 게시물 수
	
	private boolean prev;
	private boolean next;
	
	private int startPage;
	private int endPage;
	
	/* size는 5로 : 5 페이지씩 보이게 */
	public MPageDTO(int total, MCriteria cri) {
		this.total = total;
		this.cri = cri;
		
		int totalPages = (int) Math.ceil(total * 1.0 / cri.getAmount());
		
		this.startPage = (cri.getCurPage() - 1 ) / 5 * 5 + 1;
		this.endPage = Math.min(this.startPage + 4, totalPages); 
		
		this.prev = (this.startPage > 1);
		this.next = (this.endPage < totalPages);
	}
	
}
