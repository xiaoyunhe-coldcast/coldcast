package com.coldcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.coldcast.netty.NettyServer;

@ComponentScan(basePackages = "com.coldcast.*")
@SpringBootApplication
public class NettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(String... args) {
        nettyServer.start();
    }
}

