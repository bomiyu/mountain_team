package org.zerock.domain.restaurant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RpageDTO {
	private int startPage;
	private int endPage;

	private boolean prev;
	private boolean next;

	private int total;

	private Rcriteria cri;

	public RpageDTO(Rcriteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) Math.ceil(cri.getPageNo() / 5.0) * 5;
		this.startPage = endPage - 4;
		
		int realEnd = (int) Math.ceil(total * 1.0 / cri.getAmount());
		
		endPage = Math.min(realEnd, endPage);
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEnd;
	}
}
