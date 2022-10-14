package project.management.userManagement.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


/**
 * Annotation to execute Aspect on method.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiLog {
	
	/**
	 * to define log on Url.
	 */
	 String uri() default "";
	
}
