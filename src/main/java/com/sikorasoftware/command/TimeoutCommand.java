package com.sikorasoftware.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
public class TimeoutCommand extends HystrixCommand<Message> {

    private final static int TIMEOUT = 100;

    private final ExternalService externalService;
    private final int timeout;

    public TimeoutCommand(final ExternalService externalService, final int timeout) {
        super(HystrixCommandGroupKey.Factory.asKey(ExternalService.EXTERNAL_SERVICE), TIMEOUT);
        this.externalService = externalService;
        this.timeout = timeout;
    }

    @Override
    protected Message run() throws Exception {
        log.info("start running ...");
        final Message message = externalService.methodWithLongResponseCausedTimeout((Integer)
                ObjectUtils.defaultIfNull(timeout, TIMEOUT));
        log.info("end running ...");
        return message;
    }

    @Override
    protected Message getFallback() {
        log.info("fallback");
        throw new RuntimeException("Server error !!!");
    }
}
