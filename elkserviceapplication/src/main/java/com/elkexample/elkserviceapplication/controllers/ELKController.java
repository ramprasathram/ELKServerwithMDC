package com.elkexample.elkserviceapplication.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@Slf4j
@EnableAutoConfiguration
@RestController
class ELKController {
    private static final Logger LOG = LoggerFactory.getLogger(ELKController.class.getName());


    @Autowired
    RestTemplate restTemplete;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elkdemo")
    public String helloWorld() {
        String response = "Hello user ! " + new Date();
        LOG.info("/elkdemo - &gt; " + response);

        return response;
    }

    @RequestMapping(value = "/elk")
    public String helloWorld1() {
        String response = (String) restTemplete.getForObject("http://localhost:9090/elkdemo", String.class );
        LOG.info( "/elk - &gt; " + response);

        try {
            String exceptionrsp = (String) restTemplete.getForObject("http://localhost:9090/exception",String.class );

            LOG.info("/elk trying to print exception - &gt; " + exceptionrsp);
            response = response + " === " + exceptionrsp;
        } catch (Exception e) {
            // exception should not reach here. Really bad practice :)
        }

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String rsp = "";
        try {
            LOG.info("Exception Method>>>>>>>");
            int i = 1 / 0;

            // should get exception
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error(e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            LOG.error("Exception As String :: - &gt; " + sStackTrace);

            rsp = sStackTrace;
        }

        return rsp;
    }
}