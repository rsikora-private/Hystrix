package com.sikorasoftware.command;

import com.netflix.hystrix.*;
import com.sikorasoftware.model.Message;
import lombok.extern.slf4j.Slf4j;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
public class ConcurrentUserCommand extends HystrixCommand<Message> {

    public ConcurrentUserCommand() {
        super(Setter
                .withGroupKey(asKey("random.org"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("generateIntegers"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(5)
                                .withMaxQueueSize(95)
                                .withQueueSizeRejectionThreshold(100)
                )
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationThreadTimeoutInMilliseconds(2000))
        );
    }

    @Override
    protected Message run() throws Exception {
        log.info(Thread.currentThread().getName());

        return new Message("Concurrent User");
    }

    @Override
    protected Message getFallback() {
        log.info("fallback");
        throw new RuntimeException("Server error !!!");
    }
}
