package com.xiaohongshu.fls.crm.keel.autoconfiger;

import com.xiaohongshu.fls.crm.keel.extension.ExtensionExecutor;
import com.xiaohongshu.fls.crm.keel.extension.ExtensionRepository;
import com.xiaohongshu.fls.crm.keel.extension.register.ExtensionBootstrap;
import com.xiaohongshu.fls.crm.keel.extension.register.ExtensionRegister;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtensionAutoConfiguration {

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean(ExtensionBootstrap.class)
    public ExtensionBootstrap bootstrap() {
        return new ExtensionBootstrap();
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
    public ExtensionRegister register() {
        return new ExtensionRegister();
    }

}
