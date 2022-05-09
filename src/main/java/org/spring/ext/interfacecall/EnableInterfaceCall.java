package org.spring.ext.interfacecall;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * springmvc 集成需要的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ImportCallBeanDefinitionRegistrar.class)
public @interface EnableInterfaceCall {
    String[] basePackage() default {};

    String[] locations() default {};


    Class<? extends ApiRestTemplate> restTemplateClass() default ApiRestTemplate.class;

}
