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
public class CacheCommand extends HystrixCommand<Message> {

    private final ExternalService externalService;
    private final String arg;

    public CacheCommand(final ExternalService externalService, final String arg) {
        super(HystrixCommandGroupKey.Factory.asKey(ExternalService.EXTERNAL_SERVICE));
        this.externalService = externalService;
        this.arg = arg;
    }

    @Override
    protected Message run() throws Exception {
        log.info("start running ...");
        final Message message = externalService.methodForCache(arg);
        log.info("end running ...");
        return message;
    }

    @Override
    protected Message getFallback() {
        log.info("fallback");
        throw new RuntimeException("Server error !!!");
    }

    @Override
    protected String getCacheKey() {
        return arg;
    }
}
