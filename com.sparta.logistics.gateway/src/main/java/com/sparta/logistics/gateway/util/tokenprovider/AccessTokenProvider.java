package com.sparta.logistics.gateway.util.tokenprovider;

import com.sparta.logistics.gateway.exception.exceptions.UnAuthorizedException;
import com.sparta.logistics.gateway.util.dategnerator.DateGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenProvider {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String CLAIM_NAME_ACTOR_ID = "actorId";
    private static final String CLAIM_NAME_ROLE = "role";

    private final SecretKey secretKey;
    private final DateGenerator dateGenerator;

    public AccessTokenProvider(
        @Value("${service.jwt.secret-key}") String secretKey,
        DateGenerator dateGenerator
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.dateGenerator = dateGenerator;
    }

    public Actor readToken(String rawData) {
        String token = extractToken(rawData);
        Claims payload = parsePayload(token);
        validateExpirationDate(payload.getExpiration());

        Long userId = payload.get(CLAIM_NAME_ACTOR_ID, Long.class);
        String role = payload.get(CLAIM_NAME_ROLE, String.class);

        return new Actor(userId, role);
    }

    private String extractToken(String rawData) {
        if (rawData == null || !rawData.startsWith(TOKEN_PREFIX)) {
            throw new UnAuthorizedException("유효하지 않은 토큰 정보");
        }
        return rawData.replaceAll(TOKEN_PREFIX, "").trim();
    }

    private Claims parsePayload(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload();
        } catch (JwtException exception) {
            throw new UnAuthorizedException("유효하지 않은 토큰 정보");
        }
    }

    private void validateExpirationDate(Date expirationDate) {
        Date currentDate = dateGenerator.getCurrentDate();
        if (currentDate.after(expirationDate)) {
            throw new UnAuthorizedException("만료된 토큰");
        }
    }
}
