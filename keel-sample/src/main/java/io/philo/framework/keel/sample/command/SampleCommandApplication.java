package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "io.philo.framework.keel.sample")
public class SampleCommandApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SampleCommandApplication.class, args);;

        CommandBus commandBus = applicationContext.getBean(CommandBus.class);

        System.out.println(commandBus.send(new EchoCommand()));
    }
}
