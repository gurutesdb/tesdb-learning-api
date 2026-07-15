package com.tesdb.learning.core.config;

import com.tesdb.learning.core.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig
{
    @Bean
    public AuditorAware<String> auditorProvider()
    {
        return new AuditorAwareImpl();
    }
}
