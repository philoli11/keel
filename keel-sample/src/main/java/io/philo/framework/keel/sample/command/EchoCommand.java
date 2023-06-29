package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.Command;
import io.philo.framework.keel.context.KeelContext;

public class EchoCommand extends Command {
    public EchoCommand(KeelContext context) {
        super(context);
    }
}
