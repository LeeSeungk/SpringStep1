package com.example.demo.controller;

import java.util.Map;

import com.example.demo.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {


    // http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
    return "Hello World";
    }
    // http://localhost:8080/api/v1/get-api/name
    @GetMapping(value = "/name")
    public String getName(){
        return "tmdrb";
    }
    // http://localhost:8080/api/v1/get-api/variable1/{String값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    // http://localhost:8080/api/v1/get-api/variable2/{String값}
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization){
        return name + " " + email + " " + organization;
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }


    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDTO memberDTO){
        return memberDTO.toString();
    }







}
