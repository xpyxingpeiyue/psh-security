package com.psh.annotation;

import com.psh.validate.MyConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by peiyue.xing on 2019/7/29 21:10
 * 自定义约束注解，统一校验
 *
 * @version:
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = MyConstraintValidator.class
)//标识这段逻辑的执行类是什么
public @interface MyConstraint {
    //自定义注解这三个必须有
    String message() default "message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
