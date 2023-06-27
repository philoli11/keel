package io.philo.framework.keel.sample.extension;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#name == 'abc'", type = ExtensionType.SHARDED, order = 1)
public class HelloSampleExt implements SampleExtensionPoint {

    private static final Logger logger = LoggerFactory.getLogger(HelloSampleExt.class);

    @Override
    public void echo() {
        logger.info("Hello");
    }
}
