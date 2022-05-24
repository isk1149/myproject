package com.myproject.webapp.biz.association.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANK_ASSOCIATION_ACCOUNT_TB")
public class BankAssociationAccountVO {
	
	@Id @GeneratedValue
	@Column(name="BANK_ASSOCIATION_ACCOUNT_ID")
	private Long id;
	
	@Column(name="BANK_CD")
	private String bankCode;
	
	@Column(name="BANK_NM")
	private String bankName;
	
	@Column(name="ACCT_NO")
	private String accountNo;
	
	@Column(name="ACCT_NM")
	private String accountHolder;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
}
