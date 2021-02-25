package com.jing.demo.feignconsumer.conf;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign config
 *
 * @author jcwang
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level logLevel() {
        return Logger.Level.FULL;
    }
}
