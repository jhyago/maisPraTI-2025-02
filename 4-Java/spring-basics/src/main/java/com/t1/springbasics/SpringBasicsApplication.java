package com.t1.springbasics;

import com.t1.springbasics.exercicioIoC.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.nio.file.Path;

@SpringBootApplication
public class SpringBasicsApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBasicsApplication.class, args);
//    }
//
//    @Bean
//    public MessageSink messageSink() {
//        return new ConsoleMessageSink();
//    }
//
//    @Bean
//    @Profile("!file")
//    public TotalStore inMemoryStore() {
//      return new InMemoryTotalStore();
//    }
//
//    @Bean
//    @Profile("file")
//    public TotalStore fileStore() {
//        return new FileTotalStore(Path.of("data", "total.txt"));
//    }
//
//    @Bean
//    public CounterService counterService(TotalStore store, MessageSink messageSink) {
//        return new CounterService(store, messageSink);
//    }
//
//    @Bean
//    public CommandLineRunner demonstration(CounterService counterService) {
//        return args -> {
//            counterService.add(new BigDecimal("10"));
//            counterService.add(new BigDecimal("57"));
//        };
//    }
}
