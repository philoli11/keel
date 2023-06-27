package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandInterceptor;
import io.philo.framework.keel.command.PostInterceptor;
import io.philo.framework.keel.command.PreInterceptor;
import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@PreInterceptor(order = 2)
@PostInterceptor(order = 2)
@Component
public class SampleCommandInterceptor implements CommandInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SampleCommandInterceptor.class);

    @Override
    public void preIntercept(Command command) {
        logger.info("pre interceptor 1");
    }

    @Override
    public void postIntercept(Command command, Response response) {
        logger.info("post interceptor 1");
    }
}
