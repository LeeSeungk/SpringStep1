package com.example.demo.controller;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;




@RestController
public class HelloController {
    private Logger LOGGER = (Logger) LoggerFactory.getLogger(HelloController.class);
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)

    @RequestMapping("/hello")
    public String hello() { return "Hello World!"; }

    @PostMapping("log-test")
    public void logTest(){

        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }
}
