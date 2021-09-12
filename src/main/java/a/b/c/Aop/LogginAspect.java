package a.b.c.Aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
public class LogginAspect {
	
    // 해당 클래스 모든 매서드에 적용
    @Pointcut("execution(* a.b.c.*.*(..))")
    public void point1(){};
    
    // 타겟 메서드를 감싸서 실행
    @Around("point1()")
    public Object logAspect(ProceedingJoinPoint pjp) throws Throwable{

        //---------타겟 메서드 실행 전----------
        String param = Arrays.toString(pjp.getArgs());

        System.out.println("REQUEST <======= " + pjp.getSignature().getDeclaringTypeName() + "/"
                               + pjp.getSignature().getName()  + " : " + param );

        //------------------------------------
        Object retVal = pjp.proceed();
        //------------------------------------

        //---------타겟 메서드 실행 후-----------
        System.out.println("RESPONSE =======> " + pjp.getSignature().getDeclaringTypeName() + "/"
                                      + pjp.getSignature().getName() + " : " + retVal);

        return retVal;
    }
    
}
