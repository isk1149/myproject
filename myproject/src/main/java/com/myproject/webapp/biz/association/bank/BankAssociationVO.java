package com.myproject.webapp.biz.association.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANK_ASSOCIATION_TB")
public class BankAssociationVO {

	@Id @Column(name="BANK_CD")
	private String code;
	
	@Column(name="BANK_NM")
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
