package com.myproject.webapp.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

import com.myproject.webapp.biz.user.UserVO;

public class AfterReturningAdvice {
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + " 로그인 하였습니다.(Admin)");
			}
		}
		System.out.println("[AfterReturning Advice] " + method + "() 메소드 리턴값 : " + returnObj.toString());
	}
}
