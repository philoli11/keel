package io.philo.framework.keel.extension;

import io.philo.framework.keel.context.KeelContext;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public abstract class AbstractComponentExecutor {

    /**
     * Execute extension with Response
     *
     * @param targetClz   扩展点接口类
     * @param context     上下文
     * @param exeFunction 方法逻辑
     * @param <R>         Response Type
     * @param <T>         Parameter Type
     * @return 执行结果
     */
    public <R, T> R execute(Class<T> targetClz, KeelContext context, Function<T, R> exeFunction) {
        List<T> components = locateComponent(targetClz, context);
        if (components.size() > 1) {
            throw new RuntimeException("Can not execute multi extensions with return value");
        }
        return exeFunction.apply(components.get(0));
    }

    /**
     * Execute extension without Response
     *
     * @param targetClz   扩展点接口类
     * @param context     上下文
     * @param exeFunction 方法逻辑
     * @param <T>         Parameter Type
     */
    public <T> void executeVoid(Class<T> targetClz, KeelContext context, Consumer<T> exeFunction) {
        List<T> component = locateComponent(targetClz, context);
        for (T t : component) {
            exeFunction.accept(t);
        }
    }

    protected abstract <C> List<C> locateComponent(Class<C> targetClz, KeelContext context);
}
