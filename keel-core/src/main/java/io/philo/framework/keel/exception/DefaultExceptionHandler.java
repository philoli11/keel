package io.philo.framework.keel.exception;


import io.philo.framework.keel.command.Command;
import io.philo.framework.keel.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultExceptionHandler
 *
 * @author Frank Zhang
 * @date 2019-01-08 9:51 AM
 */
public class DefaultExceptionHandler implements ExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    public static DefaultExceptionHandler singleton = new DefaultExceptionHandler();

    @Override
    public void handleException(Command cmd, Response response, Exception exception) {
        buildResponse(response, exception);
        printLog(cmd, response, exception);
    }

    private void printLog(Command cmd, Response response, Exception exception) {
        if (exception instanceof BaseException) {
            //biz exception is expected, only warn it
            logger.warn(buildErrorMsg(cmd, response));
        } else {
            //sys exception should be monitored, and pay attention to it
            logger.error(buildErrorMsg(cmd, response), exception);
        }
    }

    private String buildErrorMsg(Command cmd, Response response) {
        return "Process [" + cmd + "] failed, errorCode: "
                + response.getErrCode() + " errorMsg:"
                + response.getErrMessage();
    }

    private void buildResponse(Response response, Exception exception) {
        response.setErrMessage(exception.getMessage());
        response.setSuccess(false);
    }
}
