package io.philo.framework.keel.sample.extension;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#id == 1 && #name == 'abc'", type = ExtensionType.SHARDED, order = 1)
public class HelloSampleReturnValueExt implements SampleReturnValueExtPt {
    @Override
    public String echo() {
        return "Hello";
    }
}
