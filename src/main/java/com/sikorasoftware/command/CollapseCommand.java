package com.sikorasoftware.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sikorasoftware.model.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Slf4j
public class CollapseCommand extends HystrixCommand<Message> {

    protected CollapseCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Message run() throws Exception {
        throw new IllegalStateException("Not supported yet!");
    }

}
