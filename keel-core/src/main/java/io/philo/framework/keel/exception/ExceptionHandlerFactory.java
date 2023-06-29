package io.philo.framework.keel.exception;

import io.philo.framework.keel.helper.ApplicationContextHelper;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * ExceptionHandlerFactory
 *
 * @author Frank Zhang
 * @date 2019-01-08 9:51 AM
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler getExceptionHandler() {
        try {
            return ApplicationContextHelper.getBean(ExceptionHandler.class);
        } catch (NoSuchBeanDefinitionException ex) {
            return DefaultExceptionHandler.singleton;
        }
    }

}
