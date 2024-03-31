package com.javawide.book.blackbook1.ch04.webservice;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebMethod {
	int level() default 1;
}
