package com.myproject.webapp.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AroundAdvice {
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		//StopWatch stopWatch = new StopWatch();
		//stopWatch.start();
		
		Object returnObj = pjp.proceed();
		//stopWatch.stop();
		
		System.out.println("[Around Advice] " + method + "() 메소드 실행");
		return returnObj;
	}
}
