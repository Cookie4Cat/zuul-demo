package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/***
 * @author zhenyou.guo
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    //@Scheduled(cron = "0/5 * * * * ?")
    public void scheduler() {
        System.out.println("scheduled hello hello hello");
    }
}
