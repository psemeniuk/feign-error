package com.example.feignerror;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableFeignClients
public class FeignErrorApplicationTests {

  @Autowired
  EchoClient client;

  @Test
  public void feignWorksWithIterablesAsArguments() {
    System.out.println(client.echo(HashSet.of("a", "b")));
  }

  @FeignClient(name = "echo-service", url = "https://postman-echo.com")
  interface EchoClient {

    @GetMapping("/get")
    String echo(@RequestParam Set<String> foo);
  }
}

