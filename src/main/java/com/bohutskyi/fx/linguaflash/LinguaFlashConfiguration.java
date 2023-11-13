package com.bohutskyi.fx.linguaflash;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Import(WebClientAutoConfiguration.class)
@ConfigurationPropertiesScan
@EnableAsync
@Configuration
public class LinguaFlashConfiguration {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // Mark the thread as a daemon thread
            return t;
        });
    }
}