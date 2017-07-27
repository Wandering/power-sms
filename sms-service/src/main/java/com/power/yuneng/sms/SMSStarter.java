package com.power.yuneng.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;


/**
 * Created by gryang on 16/05/11.
 */
@SpringBootApplication
@MapperScan("com.power.yuneng.sms.dao")
@ImportResource("classpath:spring/dubbo.xml")
@EnableTransactionManagement
public class SMSStarter {
    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplateBuilder builder;
    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    private SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    @Bean
    public RestTemplate restTemplate() {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 48888);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        httpRequestFactory.setProxy(proxy);
        RestTemplate restTemplate = builder.requestFactory(httpRequestFactory).build();
        return restTemplate;
    }


    public static void main(String[] args) throws IOException {
        SpringApplication.run(SMSStarter.class, args);
        System.in.read();
    }
}

