package com.myproject.webapp.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution( * com.myproject.webapp.biz..*Impl.*(..) )")
	public void allPointcut() {}
	
	@Pointcut("execution( * com.myproject.webapp.biz..*Impl.get*(..) )")
	public void getPointcut() {}

}