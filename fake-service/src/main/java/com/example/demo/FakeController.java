package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @PostMapping(value = "/mixc/fake", produces = "application/json")
    public String mixcFakeService(@RequestBody String body) {
        JSONObject result = new JSONObject();
        result.put("ErrCode", "ERR007");
        result.put("ErrDesc", "Ops,Error!");
        result.put("Result", JSON.parse(body));
        return result.toJSONString();
    }
}
