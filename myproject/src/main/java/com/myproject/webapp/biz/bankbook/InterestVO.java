package com.myproject.webapp.biz.bankbook;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="INTEREST_TB")
public class InterestVO {

	@Id @Column(name="INTEREST_ID")
	private Long id;
	
	@OneToOne @JoinColumn(name="ACCT_NO")
	private AccountVO account;
	
	@Column(name="LAST_APPLY_DAY")
	private Date lastApplyDay;
	
	@Column(name="APPLY_CNT")
	private Integer applyCount;
	
	private Long interest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}
	
	public Date getLastApplyDay() {
		return lastApplyDay;
	}

	public void setLastApplyDay(Date lastApplyDay) {
		this.lastApplyDay = lastApplyDay;
	}

	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public Long getInterest() {
		return interest;
	}

	public void setInterest(Long interest) {
		this.interest = interest;
	}

}
