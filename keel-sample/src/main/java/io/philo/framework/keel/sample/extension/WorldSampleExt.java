package io.philo.framework.keel.sample.extension;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#name == 'abc'", type = ExtensionType.SHARDED, order = 2)
public class WorldSampleExt implements SampleExtensionPoint {
    @Override
    public void echo() {
        System.out.println("World");
    }
}
