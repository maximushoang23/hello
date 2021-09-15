package com.example.vssemployee.model;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author manhdt14
 * created in 9/6/2021 12:05 PM
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
