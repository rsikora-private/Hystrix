package com.sikorasoftware.threepartservice;

import com.sikorasoftware.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
@Service
public class DummyExternalService implements ExternalService {

    public final static AtomicLong counter = new AtomicLong();

    @Override
    public Message methodWithLongResponseCausedTimeout(final Integer time) {

        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return new Message("Timeout message");
    }

    @Override
    public Message methodForCache(final String arg) {
        log.info("Count : " + counter.incrementAndGet());

        return new Message("Cache message");
    }

    @Override
    public Message methodForCircleBreaker(final float errorRate) {
        log.info("Count : " + counter.incrementAndGet());

        if(counter.get() > 1000 * errorRate) {
            throw new RuntimeException("External Service exception");
        }
        return new Message("CB message");
    }
}
