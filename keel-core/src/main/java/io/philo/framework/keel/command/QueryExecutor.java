package io.philo.framework.keel.command;


import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.common.Response;

public interface QueryExecutor<R extends Response, C extends Command> extends CommandExecutor<R,C> {

}
