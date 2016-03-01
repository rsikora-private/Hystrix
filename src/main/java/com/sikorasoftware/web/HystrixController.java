package com.sikorasoftware.web;

import com.sikorasoftware.command.TimeoutCommand;
import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.DummyExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by robertsikora on 01.03.2016.
 */

@Controller
@RequestMapping("/hystrix")
@Slf4j
public class HystrixController {

    private final DummyExternalService externalService;

    @Autowired
    public HystrixController(final DummyExternalService dummyExternalService) {
        this.externalService = dummyExternalService;
    }
    
    @RequestMapping(method= RequestMethod.GET, path = "/timeout")
    public @ResponseBody Message timeout(@RequestParam(value="param", required=false) final String name) {
        log.info("Invoking timeout");

        return (Message) new TimeoutCommand(externalService, Integer.parseInt(name)).execute();
    }
}
