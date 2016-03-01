package com.sikorasoftware.threepartservice;

import com.sikorasoftware.model.Message;
import org.springframework.stereotype.Service;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Service
public class DummyExternalService implements ExternalService {

    @Override
    public Message methodWithLongResponseCausedTimeout() {
        return null;
    }
}
