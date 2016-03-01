package com.sikorasoftware.web;

import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.DummyExternalService;
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
public class HystrixController {

    private final DummyExternalService dummyExternalService;

    @Autowired
    public HystrixController(DummyExternalService dummyExternalService) {
        this.dummyExternalService = dummyExternalService;
    }

    @RequestMapping(method= RequestMethod.GET, path = "/timeout")
    public @ResponseBody Message sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {

        return dummyExternalService.methodWithLongResponseCausedTimeout();
    }
}
