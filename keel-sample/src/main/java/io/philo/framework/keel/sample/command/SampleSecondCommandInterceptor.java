package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandInterceptor;
import io.philo.framework.keel.command.PostInterceptor;
import io.philo.framework.keel.command.PreInterceptor;
import io.philo.framework.keel.command.Command;
import io.philo.framework.keel.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@PreInterceptor(order = 1)
@PostInterceptor(order = 1)
@Component
public class SampleSecondCommandInterceptor implements CommandInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SampleSecondCommandInterceptor.class);

    @Override
    public void preIntercept(Command command) {
        logger.info("pre interceptor 2");
    }

    @Override
    public void postIntercept(Command command, Response response) {
        logger.info("post interceptor 2");
    }
}
