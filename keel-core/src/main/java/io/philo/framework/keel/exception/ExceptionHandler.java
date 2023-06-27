package io.philo.framework.keel.exception;

import io.philo.framework.keel.common.Command;
import io.philo.framework.keel.common.Response;

/**
 * ExceptionHandlerI provide a backdoor that Application can override the default Exception handling
 *
 * @author Frank Zhang
 * @date 2019-01-02 11:25 PM
 */
public interface ExceptionHandler {
    void handleException(Command cmd, Response response, Exception exception);
}
