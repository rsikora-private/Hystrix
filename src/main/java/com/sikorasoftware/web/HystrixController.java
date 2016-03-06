package com.sikorasoftware.web;

import com.sikorasoftware.command.CacheCommand;
import com.sikorasoftware.command.CircuitBreakerCommand;
import com.sikorasoftware.command.ConcurrentUserCommand;
import com.sikorasoftware.command.TimeoutCommand;
import com.sikorasoftware.model.Message;
import com.sikorasoftware.threepartservice.DummyExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robertsikora on 01.03.2016.
 */

@RestController
@Slf4j
public class HystrixController {


    private final DummyExternalService externalService;

    @Autowired
    public HystrixController(final DummyExternalService dummyExternalService) {
        this.externalService = dummyExternalService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ping")
    public String ping() {

        //System.out.println(Thread.currentThread().getName());
        return "Hello !";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/concurrent")
    public Message concurrentUser() {
        final ConcurrentUserCommand concurrentUserCommand = new ConcurrentUserCommand();
        return concurrentUserCommand.execute();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/timeout")
    public Message timeout(@RequestParam(value = "param", required = false) final String param) {
        return externalService.methodWithLongResponseCausedTimeout(Integer.parseInt(param));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hystrix/timeout")
    public Message timeoutWithHystrix(@RequestParam(value = "param", required = false) final String param) {
        final TimeoutCommand command = new TimeoutCommand(externalService, Integer.parseInt(param));
        return command.execute();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/cache")
    public Message cache(@RequestParam(value = "param", required = false) final String param) {
        externalService.methodForCache(param);
        externalService.methodForCache(param);
        return externalService.methodForCache(param);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hystrix/cache")
    public Message cacheWithHystrix(@RequestParam(value = "param", required = false) final String param) {
        final CacheCommand command1 = new CacheCommand(externalService, param);
        command1.execute();
        final CacheCommand command2 = new CacheCommand(externalService, param);
        command2.execute();
        final CacheCommand command3 = new CacheCommand(externalService, param);
        return command3.execute();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/circuitbreaker")
    public Message circuitbreaker(@RequestParam(value = "param", required = false) final String param) {
        return externalService.methodForCircleBreaker(Float.parseFloat(param));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hystrix/circuitbreaker")
    public Message circuitbreakerWithHystrix(@RequestParam(value = "param", required = false) final String param) {
        final CircuitBreakerCommand command = new CircuitBreakerCommand(externalService, Float.parseFloat(param));
        return command.execute();
    }
}
