package com.myproject.webapp.biz.common;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		if (args.length > 0)
			System.out.println("[Before Advice] " + method + "() 메소드 args 정보 : " + args[0].toString());
	}
	
	@Before("PointcutCommon.getPointcut()")
	public void beforeGetLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		if (args.length > 0)
			System.out.println("[Before Advice] " + method + "() 메소드 args 정보 : " + args[0].toString());
	}
}
