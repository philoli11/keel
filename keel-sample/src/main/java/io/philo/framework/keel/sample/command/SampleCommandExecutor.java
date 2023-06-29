package io.philo.framework.keel.sample.command;

import io.philo.framework.keel.command.CommandExe;
import io.philo.framework.keel.command.CommandExecutor;
import io.philo.framework.keel.common.Response;
import io.philo.framework.keel.context.KeelContext;
import io.philo.framework.keel.extension.ExtensionExecutor;
import io.philo.framework.keel.sample.extension.SampleExtensionPoint;
import io.philo.framework.keel.sample.extension.SampleReturnValueExtPt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@CommandExe
public class SampleCommandExecutor implements CommandExecutor<Response, SampleCommand> {

    private static final Logger logger = LoggerFactory.getLogger(SampleCommandExecutor.class);

    @Resource
    private ExtensionExecutor extensionExecutor;

    @Override
    public Response execute(SampleCommand cmd) {
        KeelContext context = cmd.getContext();
        String result = extensionExecutor.execute(SampleReturnValueExtPt.class, context, SampleReturnValueExtPt::echo);
        Response response = Response.buildSuccess();
        response.setErrMessage(result);
        return response;
    }
}
