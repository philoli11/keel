package io.philo.framework.keel.command;


import io.philo.framework.keel.common.Response;

/**
 * CommandExecutorI
 *
 * @author fulan.zjf 2017年10月21日 下午11:01:05
 */
public interface CommandExecutor<R extends Response, C extends Command> {

    R execute(C cmd);
}
