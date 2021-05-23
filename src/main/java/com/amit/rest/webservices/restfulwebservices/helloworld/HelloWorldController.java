package com.amit.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

// controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    // GET Method
    // URI /hello
    // @RequestMapping(method = RequestMethod.GET,path = "/hello")
    @GetMapping(path = "/helloworld")
    public String helloWord() {
        return "Hello World";
    }

    // GET Method
    // URI /helloworldbean
    @GetMapping(path = "/helloworldbean")
    public HelloWorldBean helloWordBean() {
        return new HelloWorldBean("success",200);
    }

    // GET Method
    // URI /helloworldbean
    @GetMapping(path = "/helloworld-internationalized")
    public String helloWordGoodMorning(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return  messageSource.getMessage("good.morning.message",null,locale);
    }
}
