package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {
    @PostMapping("/echo/test1")
    public String echo(@RequestBody String content) {
        return content + "from echo1 service";
    }
    @PostMapping("/echo/test2")
    public String echo2(@RequestBody String content) {
        return content + "from echo2 service";
    }
}
