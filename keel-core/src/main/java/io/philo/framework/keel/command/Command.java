package io.philo.framework.keel.command;

import io.philo.framework.keel.common.DTO;
import io.philo.framework.keel.context.KeelContext;

public abstract class Command extends DTO {
    private KeelContext context;

    public Command(KeelContext context) {
        this.context = context;
    }

    public KeelContext getContext() {
        return context;
    }

    public void setContext(KeelContext context) {
        this.context = context;
    }
}
