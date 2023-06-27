package io.philo.framework.keel.command;

import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.common.Response;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Just send Command to CommandBus,
 *
 * @author fulan.zjf 2017年10月24日 上午12:47:18
 */
@Component
public class CommandBus {

    @Resource
    private CommandHub commandHub;

    public Response send(Command cmd) {
        return commandHub.getCommandInvocation(cmd.getClass()).invoke(cmd);
    }

}
