package com.myproject.webapp.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	@AfterThrowing(pointcut="PointcutCommon.allPointcut()", throwing="exceptionObj")
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName();
		
		System.out.println("[AfterThrowing Advice] " + method + "() 메소드 수행 중 예외 발생");
		
		if (exceptionObj instanceof IllegalArgumentException) {
			System.out.println("부적합한 값 입력");
		} else if (exceptionObj instanceof NumberFormatException) {
			System.out.println("숫자 형식의 값이 아님");
		} else if (exceptionObj instanceof Exception) {
			System.out.println("문제 발생");
		}
	}
}
