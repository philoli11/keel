package io.philo.framework.keel.extension;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Extension {

    /**
     * @return SpEl条件表达式
     */
    String cond();

    /**
     *
     * @return 扩展点类型
     */
    ExtensionType type() default ExtensionType.EXCLUSIVE;

    /**
     *
     * @return 扩展点顺序
     */
    int order() default Integer.MIN_VALUE;
}
