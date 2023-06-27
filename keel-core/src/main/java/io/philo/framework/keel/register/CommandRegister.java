/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package io.philo.framework.keel.register;

import io.philo.framework.keel.command.CommandExecutor;
import io.philo.framework.keel.command.CommandHub;
import io.philo.framework.keel.command.CommandInvocation;
import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.constant.KeelConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;


/**
 * CommandRegister
 *
 * @author fulan.zjf 2017-11-04
 */

@Component
public class CommandRegister {

    @Resource
    private CommandHub commandHub;

    public void doRegistration(CommandExecutor<?, ?> commandExecutor, ApplicationContext applicationContext) {
        Class<? extends Command> commandClz = getCommandFromExecutor(commandExecutor);
        CommandInvocation commandInvocation = applicationContext.getAutowireCapableBeanFactory().createBean(CommandInvocation.class);
        commandInvocation.setCommandExecutor(commandExecutor);
        commandInvocation.setPreInterceptors(commandHub.getGlobalPreInterceptors());
        commandInvocation.setPostInterceptors(commandHub.getGlobalPostInterceptors());
        commandHub.getCommandRepository().put(commandClz, commandInvocation);
    }

    private Class<? extends Command> getCommandFromExecutor(CommandExecutor<?, ?> commandExecutor) {
        Class<?> commandExecutorClz = commandExecutor.getClass();
        Method[] methods = commandExecutorClz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)) {
                Class<? extends Command> commandClz = checkAndGetCommandParamType(method);
                commandHub.getResponseRepository().put(commandClz, method.getReturnType());
                return commandClz;
            }
        }
        throw new RuntimeException(" There is no " + KeelConstant.EXE_METHOD + "() in " + commandExecutorClz);
    }

    private boolean isExecuteMethod(Method method) {
        return KeelConstant.EXE_METHOD.equals(method.getName()) && !method.isBridge();
    }

    private Class<? extends Command> checkAndGetCommandParamType(Method method) {
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0) {
            throw new RuntimeException("Execute method in " + method.getDeclaringClass() + " should at least have one parameter");
        }
        if (!Command.class.isAssignableFrom(exeParams[0])) {
            throw new RuntimeException("Execute method in " + method.getDeclaringClass() + " should be the subClass of Command");
        }
        return (Class<? extends Command>) exeParams[0];
    }

}
