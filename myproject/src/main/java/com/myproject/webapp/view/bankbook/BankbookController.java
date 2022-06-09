package com.myproject.webapp.view.bankbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.webapp.biz.association.bank.BankAssociationAccountService;
import com.myproject.webapp.biz.association.bank.BankAssociationAccountVO;
import com.myproject.webapp.biz.association.bank.BankAssociationService;
import com.myproject.webapp.biz.association.bank.BankAssociationVO;
import com.myproject.webapp.biz.bank.BankService;
import com.myproject.webapp.biz.bank.BankVO;
import com.myproject.webapp.biz.bankbook.AccountService;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestService;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.bankbook.TransactionHistoryService;
import com.myproject.webapp.biz.bankbook.TransactionHistoryVO;
import com.myproject.webapp.biz.user.UserVO;
import com.myproject.webapp.util.AccountNumberDashFormat;
import com.myproject.webapp.util.DateFormat;
import com.myproject.webapp.util.MoneyCommaFormat;

@Controller
public class BankbookController {
	@Autowired
	private BankService bankService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private TransactionHistoryService txHistoryService;
	@Autowired
	private BankAssociationService bankAssocService;
	@Autowired
	private BankAssociationAccountService bankAssocAcctService;
	
	@RequestMapping(value="/bankbook.do")
	public String getBankbook(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "login.do";;
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		model.addAttribute("account", account);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));
		model.addAttribute("deposit", MoneyCommaFormat.format(account.getDeposit()));
		
		InterestVO interest = interestService.getInterest(account);
		interestService.calculateInterest(account, interest);
		
		model.addAttribute("interestApplyCount", interest.getApplyCount());
		model.addAttribute("interest", MoneyCommaFormat.format(interest.getInterest()));
		
		List<TransactionHistoryVO> txList = txHistoryService.getTransactionList(account);
		List<TransactionHistoryVO> txFiveList = new ArrayList<>();
		
		int i = 0;
		for (TransactionHistoryVO vo : txList) {
			vo.setTxStringDate(DateFormat.dateFormatYearToSecond(vo.getTxDate()));
			String txAmount = MoneyCommaFormat.format(vo.getTxAmount());
			if (vo.getIO().equals("출금"))
				txAmount = "-" + txAmount;
			vo.setTxStringAmount(txAmount);
			txFiveList.add(vo);
			i++;
			if (i == 5) break;
		}
		
		model.addAttribute("txFiveList", txFiveList);
		
		return "/WEB-INF/view/bankbook.jsp";
	}
	
	@RequestMapping(value="/getInterest.do")
	public String getInterest(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		InterestVO interest = interestService.getInterest(account);
		
		// 이자 0인 경우 리턴
		if (interest.getInterest() < 1L)
			return "bankbook.do";
		
		accountService.receiveInterest(account, interest);
		
		BankVO bank = bankService.getBank();
		txHistoryService.receiveInterest(bank, account, interest);
		
		interestService.initInterest(interest);
		
		return "bankbook.do";
	}
	
	// 송금 시 은행 목록 설정
	@ModelAttribute("bankList")
	public List<BankAssociationVO> searchBankMap() {
		List<BankAssociationVO> list = bankAssocService.getBankList();
		return list;
	}
	
	@RequestMapping(value="/remit.do")
	public String remit(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));
		
		return "/WEB-INF/view/remit.jsp";
	}
	
	@RequestMapping(value="/remitProgress.do")
	public String remitAccountCheck(Model model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		
		Map<String, Boolean> error = new HashMap<>();
		model.addAttribute("error", error);
		
		String bank = request.getParameter("bank");
		
		String txAccountStr = request.getParameter("txAccountNo");
		if (txAccountStr == null || txAccountStr.equals("")) {
			error.put("emptyAccount", Boolean.TRUE);
			return "/WEB-INF/view/remit.jsp";
		}
		
		String amount = request.getParameter("txAmount");
		if (amount == null || amount.equals("")) {
			error.put("noAmount", Boolean.TRUE);
			return "/WEB-INF/view/remit.jsp";
		}
		
		BankAssociationAccountVO txAssocAcct = null;
		try {
			txAssocAcct = bankAssocAcctService.getAccount(bank, txAccountStr);
		} catch (NoResultException | NonUniqueResultException e) {
			//e.printStackTrace();
			error.put("noAccount", Boolean.TRUE);
			return "/WEB-INF/view/remit.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long txAmount = Long.parseLong(amount);
		if (txAmount == 0) {
			error.put("noAmount", Boolean.TRUE);
			return "/WEB-INF/view/remit.jsp";
		}
		
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		if (txAmount > account.getDeposit()) {
			error.put("lackDeposit", Boolean.TRUE);
			return "/WEB-INF/view/remit.jsp";
		}
		
		//BankVO userBank = bankService.getBank();
		
		/*
		model.addAttribute("userBank", userBank);
		model.addAttribute("account", account);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));
		model.addAttribute("txAccount", txAccount);
		model.addAttribute("txAccountNo", AccountNumberDashFormat.format(txAccount.getAccountNo()));
		model.addAttribute("txAmount", txAmount);
		model.addAttribute("txAmountFormat", MoneyCommaFormat.format(txAmount));
		*/
		TransactionHistoryVO txHistory = new TransactionHistoryVO();
		//txHistory.setAccount(account);
		txHistory.setTxBank(txAssocAcct.getBankCode());
		txHistory.setTxBankName(txAssocAcct.getBankName());
		txHistory.setTxAccountNo(txAssocAcct.getAccountNo());
		txHistory.setTxAmount(txAmount);
		model.addAttribute("transactionHistoryVO", txHistory);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));
		model.addAttribute("txAccountNo", AccountNumberDashFormat.format(txAssocAcct.getAccountNo()));
		model.addAttribute("txAmountFormat", MoneyCommaFormat.format(txAmount));
		
		return "/WEB-INF/view/remitProgress.jsp";
	}
	
	@RequestMapping(value="/remitCheck.do")
	public String remitCheck(TransactionHistoryVO vo, Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		TransactionHistoryVO txHistory = vo;
		txHistoryService.insertTxHistory(account, txHistory);
		
		return "bankbook.do";
	}
	
	@RequestMapping(value="/transactionHistory.do")
	public String getTransactionHistory(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		List<TransactionHistoryVO> txList = txHistoryService.getTransactionList(account);
		
		for (TransactionHistoryVO vo : txList) {
			vo.setTxStringDate(DateFormat.dateFormatYearToSecond(vo.getTxDate()));
			String txAmount = MoneyCommaFormat.format(vo.getTxAmount());
			if (vo.getIO().equals("출금"))
				txAmount = "-" + txAmount;
			vo.setTxStringAmount(txAmount);
		}
		
		model.addAttribute("txList", txList);
		
		return "/WEB-INF/view/transactionList.jsp";
	}
	
	@RequestMapping(value="/interestHistory.do")
	public String getInterestHistory(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		List<TransactionHistoryVO> txList = txHistoryService.getTransactionList(account);
		BankVO bank = bankService.getBank();
		
		List<TransactionHistoryVO> interestList = new ArrayList<>();
		for (TransactionHistoryVO vo : txList) {
			if (bank.getCode().equals(vo.getTxBank()) && vo.getTxAccountName().equals("이자")) {
				vo.setTxStringDate(DateFormat.dateFormatYearToSecond(vo.getTxDate()));
				vo.setTxStringAmount(MoneyCommaFormat.format(vo.getTxAmount()));
				interestList.add(vo);
			}
		}
		
		model.addAttribute("interestList", interestList);
		
		return "/WEB-INF/view/interestList.jsp";
	}
}
