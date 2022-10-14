package project.management.userManagement.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * Class to define an aspect log for each excuted method.
 */
@Aspect
@Component
public class ApiLogAspect {
	
	
	
	 private final Logger log = LoggerFactory.getLogger(this.getClass());

	    /**
	     * Pointcut that matches all repositories, services and Web REST endpoints.
	     */
	    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
	        " || within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
	    public void springBeanPointcut() {
	        // Method is empty as this is just a Pointcut, the implementations are in the advices.
	    }

	    /**
	     * Pointcut that matches all Spring beans in the application's main packages.
	     */
	    @Pointcut("within(project.management.userManagement..*)" +
	        " || within(project.management.userManagement.service..*)" +
	        " || within(project.management.userManagement.controller..*)")
	    public void applicationPackagePointcut() {
	        // Method is empty as this is just a Pointcut, the implementations are in the advices.
	    }

	    /**
	     * Advice that logs methods throwing exceptions.
	     *
	     * @param joinPoint join point for advice
	     * @param e exception
	     */
	    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	    }

	    /**
	     * Advice that logs when a method is entered and exited.
	     *
	     * @param joinPoint join point for advice
	     * @return result
	     * @throws Throwable throws IllegalArgumentException
	     */
	    @Around("applicationPackagePointcut() && springBeanPointcut()")
	    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	        
	            log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	     
	        try {
	            Object result = joinPoint.proceed();
	           
	                log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                    joinPoint.getSignature().getName(), result);
	          
	            return result;
	        } catch (IllegalArgumentException e) {
	            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
	                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	            throw e;
	        }
	    }
	    
	    /**
	     * Advice that logs when a method annotated with ApiLog is entered and exited.
	     *
	     * @param joinPoint join point for advice
	     * @return result
	     * @throws Throwable throws Exception
	     */
	
	@Around(value = "@annotation(apiLogAnnotation)")
    public Object apiLog(ProceedingJoinPoint joinPoint, ApiLog apiLogAnnotation) throws Throwable {
		long startTime = System.nanoTime();
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String uri = apiLogAnnotation.uri();
        String requestType = getRequestType(joinPoint);
        log.info("Starting {} in {}", requestType, uri);
       
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.error("Unhandled Exception: {}", ex.getMessage(), ex);
            throw new Exception(ex);
        } finally {
            double timeDifferenceInMs = (System.nanoTime() - startTime) / 1000000d;
            log.info("Time used in ms: {}", timeDifferenceInMs);
        }
    }
	
	/**
     * Method return a request Type of an Api.
     *
     * @param joinPoint join point for advice
     * @return result
     */
	
	private String getRequestType(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return "GET";
        }

        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return "DELETE";
        }

        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return "POST";
        }

        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return "PUT";
        }

        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            return "PATCH";
        }

        return "N/A";
    }

}
