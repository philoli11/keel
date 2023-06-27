package io.philo.framework.keel.command;

import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.common.Response;
import io.philo.framework.keel.exception.ExceptionHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class CommandInvocation {

    private static final Logger logger = LoggerFactory.getLogger(CommandInvocation.class);

    private CommandExecutor commandExecutor;

    private List<CommandInterceptor> preInterceptors;

    private List<CommandInterceptor> postInterceptors;

    @Resource
    private CommandHub commandHub;


    public CommandInvocation() {

    }

    public CommandInvocation(CommandExecutor<?, ?> commandExecutor, List<CommandInterceptor> preInterceptors,
                             List<CommandInterceptor> postInterceptors) {
        this.commandExecutor = commandExecutor;
        this.preInterceptors = preInterceptors;
        this.postInterceptors = postInterceptors;
    }

    public Response invoke(Command command) {
        Response response = null;
        try {
            preIntercept(command);
            response = commandExecutor.execute(command);
        } catch (Exception e) {
            response = getResponseInstance(command);
            response.setSuccess(false);
            ExceptionHandlerFactory.getExceptionHandler().handleException(command, response, e);
        } finally {
            //make sure post interceptors performs even though exception happens
            postIntercept(command, response);
        }
        return response;
    }

    private void postIntercept(Command command, Response response) {
        try {
            for (CommandInterceptor postInterceptor : postInterceptors) {
                postInterceptor.postIntercept(command, response);
            }
        } catch (Exception e) {
            logger.error("postInterceptor error:" + e.getMessage(), e);
        }
    }

    private void preIntercept(Command command) {
        for (CommandInterceptor preInterceptor : preInterceptors) {
            preInterceptor.preIntercept(command);
        }
    }

    private Response getResponseInstance(Command cmd) {
        Class<?> responseClz = commandHub.getResponseRepository().get(cmd.getClass());
        try {
            return (Response) responseClz.newInstance();
        } catch (Exception e) {
            return new Response();
        }
    }

    public void setPreInterceptors(List<CommandInterceptor> preInterceptors) {
        this.preInterceptors = preInterceptors;
    }

    public void setPostInterceptors(List<CommandInterceptor> postInterceptors) {
        this.postInterceptors = postInterceptors;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
