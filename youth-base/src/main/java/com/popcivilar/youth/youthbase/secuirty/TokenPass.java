package com.popcivilar.youth.youthbase.secuirty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @ClassName TokenPass
 * @Description 用来跳过验证的TokenPass
 * @Author zhagnch
 * @Date 2019/7/18 18:11
 * @Version 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenPass {
    boolean required() default true;
}
