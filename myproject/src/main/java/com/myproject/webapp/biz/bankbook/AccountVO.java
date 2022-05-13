package com.myproject.webapp.biz.bankbook;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myproject.webapp.biz.user.UserVO;

@Entity
@Table(name="ACCOUNT_TB")
public class AccountVO {
	
    @Id @Column(name="ACCT_NO")
    private String accountNo;
    
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserVO user;
    
    @Column(name="CREATE_DT")
    private Date createDate;
    
    private Long deposit;
    
    private Double rate;
    
    @Column(name="RATE_LIMIT_AMT")
    private Long rateLimitAmount;
    
    @Column(name="OVER_RATE")
    private Double overRate;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Long getRateLimitAmount() {
		return rateLimitAmount;
	}

	public void setRateLimitAmount(Long rateLimitAmount) {
		this.rateLimitAmount = rateLimitAmount;
	}

	public Double getOverRate() {
		return overRate;
	}

	public void setOverRate(Double overRate) {
		this.overRate = overRate;
	}
}
