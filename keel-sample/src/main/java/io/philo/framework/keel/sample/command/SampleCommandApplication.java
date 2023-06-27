package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandBus;
import io.philo.framework.keel.context.KeelContext;
import io.philo.framework.keel.extension.ExtensionExecutor;
import io.philo.framework.keel.sample.extension.SampleExtensionPoint;
import io.philo.framework.keel.sample.extension.SampleNamedDefine;
import io.philo.framework.keel.sample.extension.SampleReturnValueExtPt;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "io.philo.framework.keel.sample")
public class SampleCommandApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder().sources(SampleCommandApplication.class).run(args);

        CommandBus commandBus = applicationContext.getBean(CommandBus.class);

        System.out.println(commandBus.send(new EchoCommand()));
    }
}
