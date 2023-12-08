package phoenix.AM_PM.global.config.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);

    Claims getClaims(String token);

    boolean isValid(String token);

    String getId(String token);
}