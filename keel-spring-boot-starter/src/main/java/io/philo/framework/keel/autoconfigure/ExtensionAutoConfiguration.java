package io.philo.framework.keel.autoconfigure;

import io.philo.framework.keel.bootstrap.Bootstrap;
import io.philo.framework.keel.command.CommandBus;
import io.philo.framework.keel.command.CommandHub;
import io.philo.framework.keel.extension.ExtensionExecutor;
import io.philo.framework.keel.extension.ExtensionRepository;
import io.philo.framework.keel.helper.ApplicationContextHelper;
import io.philo.framework.keel.register.CommandRegister;
import io.philo.framework.keel.register.ExtensionRegister;
import io.philo.framework.keel.register.PostInterceptorRegister;
import io.philo.framework.keel.register.PreInterceptorRegister;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtensionAutoConfiguration {



    @Bean(initMethod = "init")
    @ConditionalOnMissingBean(Bootstrap.class)
    public Bootstrap bootstrap() {
        return new Bootstrap();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionRepository.class)
    public ExtensionRepository repository() {
        return new ExtensionRepository();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionExecutor.class)
    public ExtensionExecutor executor() {
        return new ExtensionExecutor();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionRegister.class)
    public ExtensionRegister extensionRegister() {
        return new ExtensionRegister();
    }

    @Bean
    @ConditionalOnMissingBean(CommandRegister.class)
    public CommandRegister commandRegister() {
        return new CommandRegister();
    }

    @Bean
    @ConditionalOnMissingBean(CommandHub.class)
    public CommandHub commandHub() {
        return new CommandHub();
    }

    @Bean
    @ConditionalOnMissingBean(CommandBus.class)
    public CommandBus commandBus() {
        return new CommandBus();
    }

    @Bean
    @ConditionalOnMissingBean(PreInterceptorRegister.class)
    public PreInterceptorRegister preInterceptorRegister() {
        return new PreInterceptorRegister();
    }

    @Bean
    @ConditionalOnMissingBean(PostInterceptorRegister.class)
    public PostInterceptorRegister postInterceptorRegister() {
        return new PostInterceptorRegister();
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationContextHelper.class)
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}
