package io.philo.framework.keel.bootstrap;

import io.philo.framework.keel.register.Register;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class Bootstrap implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Map<String, Register> registerMap = applicationContext.getBeansOfType(Register.class);
        registerMap.values().forEach(r -> r.doRegistration(applicationContext));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
