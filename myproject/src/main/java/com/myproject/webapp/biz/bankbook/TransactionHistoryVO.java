package com.myproject.webapp.biz.bankbook;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myproject.webapp.biz.bank.BankVO;

@Entity
@Table(name="TRANSACTION_HISTORY_TB")
public class TransactionHistoryVO {
	
	@Id @Column(name="TRANSACTION_HISTORY_ID")
	private Long id;
	
	
	@ManyToOne @JoinColumn(name="ACCT_NO")
	private AccountVO account;
	
	@Column(name="TX_DT")
	private Date txDate;
	
	@ManyToOne @JoinColumn(name="TX_BANK_CD")
	private BankVO txBank;
	
	@Column(name="TX_BANK_NM")
	private String txBankName;
	
	@Column(name="TX_ACCT_NO")
	private String txAccountNo;
	
	@Column(name="TX_AMT")
	private Long txAmount;
	
	@Column(name="IO_GB")
	private String IO;
	
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
	public Date getTxDate() {
		return txDate;
	}
	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}
	public BankVO getTxBank() {
		return txBank;
	}
	public void setTxBank(BankVO txBank) {
		this.txBank = txBank;
	}
	public String getTxBankName() {
		return txBankName;
	}
	public void setTxBankName(String txBankName) {
		this.txBankName = txBankName;
	}
	public String getTxAccountNo() {
		return txAccountNo;
	}
	public void setTxAccountNo(String txAccountNo) {
		this.txAccountNo = txAccountNo;
	}
	public Long getTxAmount() {
		return txAmount;
	}
	public void setTxAmount(Long txAmount) {
		this.txAmount = txAmount;
	}
	public String getIO() {
		return IO;
	}
	public void setIO(String iO) {
		IO = iO;
	}
}
