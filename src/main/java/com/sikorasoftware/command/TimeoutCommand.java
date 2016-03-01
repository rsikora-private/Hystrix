package com.sikorasoftware.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.ExternalService;
import org.apache.commons.lang.ObjectUtils;

/**
 * Created by robertsikora on 01.03.2016.
 */
public class TimeoutCommand extends HystrixCommand<Message> {

    private final static int TIMEOUT = 100;

    private ExternalService externalService;
    private int timeout;

    public TimeoutCommand(final ExternalService externalService, final int timeout) {
        super(HystrixCommandGroupKey.Factory.asKey(ExternalService.EXTERNAL_SERVICE), TIMEOUT);
        this.externalService = externalService;
        this.timeout = timeout;
    }

    @Override
    protected Message run() throws Exception {
        return externalService.methodWithLongResponseCausedTimeout((Integer) ObjectUtils.defaultIfNull(timeout, TIMEOUT));
    }

    @Override
    protected Message getFallback() {
        return new Message("Something went really wrong !");
    }
}
