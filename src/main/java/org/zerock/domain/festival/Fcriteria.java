package org.zerock.domain.festival;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Fcriteria {
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	public Fcriteria() {
		this(1, 5);
	}

	public Fcriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public String[] getTypeArr() {
		if (this.type == null) {
			return new String[] {};
		} else {
			return type.split("");
		}
	}
	
	
	
	
	
}