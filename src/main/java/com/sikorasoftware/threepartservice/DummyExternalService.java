package com.sikorasoftware.threepartservice;

import com.sikorasoftware.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
@Service
public class DummyExternalService implements ExternalService {

    @Override
    public Message methodWithLongResponseCausedTimeout(final Integer time) {

        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return new Message("Timeout message");
    }
}
