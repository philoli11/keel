package com.xiaohongshu.fls.crm.keel.context;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

//函数式接口注解
@FunctionalInterface
public interface NamedFunction<T, R> extends Function<T, R>, Serializable {

    //这个方法返回的SerializedLambda是重点
    default SerializedLambda getSerializedLambda() throws Exception {
        //writeReplace改了好像会报异常
        Method write = this.getClass().getDeclaredMethod("writeReplace");
        write.setAccessible(true);
        return (SerializedLambda) write.invoke(this);
    }

    default String getImplClass() {
        try {
            return getSerializedLambda().getImplClass().replaceAll("/", ".");
        } catch (Exception e) {
            return null;
        }
    }

    default String getImplMethodName() {
        try {
            return getSerializedLambda().getImplMethodName();
        } catch (Exception e) {
            return null;
        }
    }

    default String getFieldName() {
        String fieldName = getImplMethodName().substring("get".length());
        fieldName = fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), (String.valueOf(fieldName.charAt(0))).toLowerCase());
        return fieldName;
    }
}
