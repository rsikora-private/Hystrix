package com.sikorasoftware.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.ExternalService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
public class CircuitBreakerCommand extends HystrixCommand<Message> {

    private final ExternalService externalService;
    private final float rateError;

    public CircuitBreakerCommand(final ExternalService externalService, final float rateError) {
        super(HystrixCommandGroupKey.Factory.asKey(ExternalService.EXTERNAL_SERVICE));
        this.externalService = externalService;
        this.rateError = rateError;
    }

    @Override
    protected Message run() throws Exception {
        return externalService.methodForCircleBreaker(rateError);
    }

    @Override
    protected Message getFallback() {
        log.info("fallback");
        throw new RuntimeException("Server error !!!");
    }
}
