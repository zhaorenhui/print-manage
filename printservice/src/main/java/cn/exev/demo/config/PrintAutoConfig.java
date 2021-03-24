package cn.exev.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PrintServiceAutoConfiguration.class})
public class PrintAutoConfig {
}
