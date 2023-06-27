package io.philo.framework.keel.command;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command Hub holds all the important information about Command
 *
 * @author fulan.zjf 2017-10-24
 */
@SuppressWarnings("rawtypes")
@Component
public class CommandHub {

    /**
     * 全局通用的PreInterceptors
     */
    private final List<CommandInterceptor> globalPreInterceptors = new ArrayList<>();

    /**
     * 全局通用的PostInterceptors
     */
    private final List<CommandInterceptor> globalPostInterceptors = new ArrayList<>();

    /**
     * key:CommandClz
     */
    private final Map<Class<?>, CommandInvocation> commandRepository = new HashMap<>();

    /**
     * This Repository is used for return right response type on exception scenarios
     * key: commandClass, value: ResponseClz
     */
    private final Map<Class<?>, Class<?>> responseRepository = new HashMap<>();

    public CommandInvocation getCommandInvocation(Class cmdClass) {
        CommandInvocation commandInvocation = commandRepository.get(cmdClass);
        if (commandRepository.get(cmdClass) == null)
            throw new RuntimeException(cmdClass + " is not registered in CommandHub, please register first");
        return commandInvocation;
    }

    public List<CommandInterceptor> getGlobalPreInterceptors() {
        return globalPreInterceptors;
    }


    public List<CommandInterceptor> getGlobalPostInterceptors() {
        return globalPostInterceptors;
    }

    public Map<Class<?>, CommandInvocation> getCommandRepository() {
        return commandRepository;
    }

    public Map<Class<?>, Class<?>> getResponseRepository() {
        return responseRepository;
    }
}
