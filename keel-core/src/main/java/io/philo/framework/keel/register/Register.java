package io.philo.framework.keel.register;

import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

public interface Register<T> {
    void doRegistration(ApplicationContext applicationContext);

    default List<T> scan(ApplicationContext applicationContext, Class<? extends Annotation> anno) {
        return (List<T>) applicationContext.getBeansWithAnnotation(anno).values().stream().collect(Collectors.toList());
    }
}
