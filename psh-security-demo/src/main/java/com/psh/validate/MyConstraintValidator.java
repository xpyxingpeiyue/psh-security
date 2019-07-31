package com.psh.validate;

import com.psh.annotation.MyConstraint;
import com.psh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by peiyue.xing on 2019/7/29 21:16
 * 实现ConstraintValidator 接口
 * ConstraintValidator<A extends Annotation, T>
 *    A 标识自定义注解
 *    T 表示作用变量上的变量的类型 String Date 。。。。
 *
 *
 *  实现  ConstraintValidator 后spring容器自动将此类作为bean容器中 不需要在此类上添加注解
 * @version:
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    //spring 容器对象 可以使用次注解
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("init MyConstraint");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("value:"+value);
        String str = helloService.say(value);
        System.out.println(str);
        return false;
    }
}
