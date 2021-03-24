package cn.exev.demo.config;

import cn.exev.demo.service.PrintService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class PrintServiceAutoConfiguration {


    @Bean
    @ConditionalOnBean(RestTemplate.class)
    @ConditionalOnMissingBean
    public PrintService service(RestTemplate restTemplate) {
        return new PrintService(restTemplate);
    }


}
