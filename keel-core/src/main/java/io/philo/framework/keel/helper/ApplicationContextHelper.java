package io.philo.framework.keel.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHelper
 *
 * @author Frank Zhang
 * @date 2018-01-07 12:30 PM
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> targetClz) {
        T beanInstance = null;
        //优先按type查
        beanInstance = applicationContext.getBean(targetClz);
        return beanInstance;
    }

    public static <T> T createBean(Class<T> clazz) {
        return applicationContext.getAutowireCapableBeanFactory().createBean(clazz);
    }

    public static Object getBean(String claz) {
        return ApplicationContextHelper.applicationContext.getBean(claz);
    }
}
