package ru.geekbrains.dubandro.SpringDataFront.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ru.geekbrains.dubandro.SpringDataFront")
public class AppConfig {
}
