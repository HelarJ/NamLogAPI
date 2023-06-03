package com.jaadla.namlogapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class WebSecurityConfig {
    @Bean
    open fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity.authorizeHttpRequests {
        it.requestMatchers("/api/log/*")
                .permitAll()
                .anyRequest()
                .authenticated()
    }.build()

}
