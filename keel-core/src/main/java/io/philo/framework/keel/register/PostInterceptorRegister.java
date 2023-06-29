/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package io.philo.framework.keel.register;

import io.philo.framework.keel.command.CommandHub;
import io.philo.framework.keel.command.CommandInterceptor;
import io.philo.framework.keel.command.PostInterceptor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * PostInterceptorRegister
 *
 * @author fulan.zjf 2017-11-04
 */
@Component
public class PostInterceptorRegister {

    @Resource
    private CommandHub commandHub;

    public void doRegistration(CommandInterceptor commandInterceptor) {
        registerInterceptor(commandInterceptor);
    }

    private void registerInterceptor(CommandInterceptor commandInterceptor) {
        commandHub.getGlobalPostInterceptors().add(commandInterceptor);
        order(commandHub.getGlobalPostInterceptors());
    }

    private void order(List<CommandInterceptor> interceptorList) {
        if (interceptorList == null || interceptorList.size() <= 1) {
            return;
        }
        interceptorList.sort(Comparator.comparingInt(o -> AnnotationUtils.findAnnotation(o.getClass(), PostInterceptor.class).order()));
    }
}
