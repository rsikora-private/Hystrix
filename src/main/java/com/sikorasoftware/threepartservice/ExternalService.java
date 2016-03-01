package com.sikorasoftware.threepartservice;

import com.sikorasoftware.model.Message;

/**
 * Created by robertsikora on 01.03.2016.
 */
public interface ExternalService {

    Message methodWithLongResponseCausedTimeout();

}
