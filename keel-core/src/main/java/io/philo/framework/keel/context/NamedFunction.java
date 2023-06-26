package io.philo.framework.keel.context;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

@FunctionalInterface
public interface NamedFunction<T, R> extends Function<T, R>, Serializable {

    default SerializedLambda getSerializedLambda() throws Exception {
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
