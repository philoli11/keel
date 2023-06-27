package io.philo.framework.keel.bootstrap;

import io.philo.framework.keel.command.CommandExe;
import io.philo.framework.keel.command.CommandInterceptor;
import io.philo.framework.keel.command.PostInterceptor;
import io.philo.framework.keel.command.PreInterceptor;
import io.philo.framework.keel.command.CommandExecutor;
import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionPoint;
import io.philo.framework.keel.register.CommandRegister;
import io.philo.framework.keel.register.ExtensionRegister;
import io.philo.framework.keel.register.PostInterceptorRegister;
import io.philo.framework.keel.register.PreInterceptorRegister;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
public class Bootstrap implements ApplicationContextAware {

    @Resource
    private ExtensionRegister extensionRegister;

    @Resource
    private CommandRegister commandRegister;

    @Resource
    private PreInterceptorRegister preInterceptorRegister;

    @Resource
    private PostInterceptorRegister postInterceptorRegister;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Map<String, Object> extensionBeans = applicationContext.getBeansWithAnnotation(Extension.class);
        extensionBeans.values().forEach(
                extension -> extensionRegister.doRegistration((ExtensionPoint) extension)
        );

        Map<String, Object> commandExeBeans = applicationContext.getBeansWithAnnotation(CommandExe.class);
        commandExeBeans.values().forEach(
                commandExe -> commandRegister.doRegistration((CommandExecutor<?, ?>) commandExe, applicationContext)
        );

        Map<String, Object> preInterceptorBeans = applicationContext.getBeansWithAnnotation(PreInterceptor.class);
        preInterceptorBeans.values().forEach(
                preInterceptor -> preInterceptorRegister.doRegistration((CommandInterceptor) preInterceptor)
        );

        Map<String, Object> postInterceptorBeans = applicationContext.getBeansWithAnnotation(PostInterceptor.class);
        postInterceptorBeans.values().forEach(
                postInterceptor -> postInterceptorRegister.doRegistration((CommandInterceptor) postInterceptor)
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
