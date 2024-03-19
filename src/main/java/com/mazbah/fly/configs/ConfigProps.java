package com.mazbah.fly.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
public class ConfigProps {
    String jwtSecret = "FlyUnsafeDefaultSecret";
    Long jwtTokenValidity = 42000L;
    String jwtTokenIssuer = "FLY_SECURITY";
    Long jwtTokenExpireAt = null;
    String jwtAudience = "FS_APP";

    public Long getJwtTokenExpireAt() {
        return jwtTokenExpireAt;
    }

    public String getJwtAudience() {
        return jwtAudience;
    }

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
