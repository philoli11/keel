package io.philo.framework.keel.sample;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#name == 'abc'", type = ExtensionType.SHARDED, order = 1)
public class HelloSampleExt implements SampleExtensionPoint {
    @Override
    public void echo() {
        System.out.println("Hello");
    }
}
