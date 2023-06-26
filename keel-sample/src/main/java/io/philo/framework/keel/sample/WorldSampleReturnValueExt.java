package io.philo.framework.keel.sample;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#id == 2 && #name == 'abc'", type = ExtensionType.SHARDED, order = 2)
public class WorldSampleReturnValueExt implements SampleReturnValueExtPt {
    @Override
    public String echo() {
        return "World";
    }
}
