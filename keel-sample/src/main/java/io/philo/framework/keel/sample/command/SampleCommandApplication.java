package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandBus;
import io.philo.framework.keel.context.KeelContext;
import io.philo.framework.keel.sample.extension.SampleNamedDefine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "io.philo.framework.keel.sample")
public class SampleCommandApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SampleCommandApplication.class, args);

        CommandBus commandBus = applicationContext.getBean(CommandBus.class);
        KeelContext keelContext = KeelContext.create();
        keelContext.with(SampleNamedDefine::getId).set(1L);
        keelContext.with(SampleNamedDefine::getName).set("a");
        keelContext.with(SampleNamedDefine::getName).set("abc");

        System.out.println(commandBus.send(new EchoCommand(keelContext)));
        System.out.println(commandBus.send(new SampleCommand(keelContext)));
    }
}
