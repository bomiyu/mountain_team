package org.zerock.domain.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Acriteria {
	private int pageNo;
	private int amount;
	private String type;
	private String keyword;

	public Acriteria() {
		this(1, 5);
	}

	public Acriteria(int pageNo, int amount) {
		this.pageNo = pageNo;
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
