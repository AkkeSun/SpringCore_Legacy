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
	
    // �ش� Ŭ���� ��� �ż��忡 ����
    @Pointcut("execution(* a.b.c.*.*(..))")
    public void point1(){};
    
    // Ÿ�� �޼��带 ���μ� ����
    @Around("point1()")
    public Object logAspect(ProceedingJoinPoint pjp) throws Throwable{

        //---------Ÿ�� �޼��� ���� ��----------
        String param = Arrays.toString(pjp.getArgs());

        System.out.println("REQUEST <======= " + pjp.getSignature().getDeclaringTypeName() + "/"
                               + pjp.getSignature().getName()  + " : " + param );

        //------------------------------------
        Object retVal = pjp.proceed();
        //------------------------------------

        //---------Ÿ�� �޼��� ���� ��-----------
        System.out.println("RESPONSE =======> " + pjp.getSignature().getDeclaringTypeName() + "/"
                                      + pjp.getSignature().getName() + " : " + retVal);

        return retVal;
    }
    
}
