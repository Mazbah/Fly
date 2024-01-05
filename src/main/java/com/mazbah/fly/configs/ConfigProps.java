package com.mazbah.fly.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
public class ConfigProps {
    String jwtSecret = "FlyUnsafeDefaultSecret";
    Long jwtTokenValidity = 42000L;
    String jwtTokenIssuer = "FLY_SECURITY";

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Long getJwtTokenValidity() {
        return jwtTokenValidity;
    }

    public String getJwtTokenIssuer() {
        return jwtTokenIssuer;
    }
}
