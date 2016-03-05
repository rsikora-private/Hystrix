package com.sikorasoftware.threepartservice;

import com.sikorasoftware.model.Message;

/**
 * Created by robertsikora on 01.03.2016.
 */
public interface ExternalService {

    String EXTERNAL_SERVICE = "Ext1";

    Message methodWithLongResponseCausedTimeout(int time);

    Message methodForCache(String arg);

    Message methodForCircleBreaker(float errorRate);

}
