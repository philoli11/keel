package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandExe;
import io.philo.framework.keel.command.CommandExecutor;
import io.philo.framework.keel.common.Response;
import io.philo.framework.keel.context.KeelContext;
import io.philo.framework.keel.extension.ExtensionExecutor;
import io.philo.framework.keel.sample.extension.SampleExtensionPoint;
import io.philo.framework.keel.sample.extension.SampleNamedDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@CommandExe
public class EchoCommandExecutor implements CommandExecutor<Response, EchoCommand> {

    private static final Logger logger = LoggerFactory.getLogger(EchoCommandExecutor.class);

    @Resource
    private ExtensionExecutor extensionExecutor;

    @Override
    public Response execute(EchoCommand cmd) {
        KeelContext keelContext = KeelContext.create();
        keelContext.with(SampleNamedDefine::getId).set(2L);
        keelContext.with(SampleNamedDefine::getName).set("abc");
        extensionExecutor.executeVoid(SampleExtensionPoint.class, keelContext, SampleExtensionPoint::echo);
        return Response.buildSuccess();
    }
}
