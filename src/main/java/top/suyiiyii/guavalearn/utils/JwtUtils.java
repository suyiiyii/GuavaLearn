package top.suyiiyii.guavalearn.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public final class JwtUtils {

    public static final int ACCESS_EXPIRE = 60 * 60 * 24 * 7;
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    public static String generateToken(String subject, String secretKey) {
        return Jwts.builder()
                .subject(subject)
                .expiration(Date.from(new Date().toInstant().plusSeconds(ACCESS_EXPIRE)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), ALGORITHM)
                .compact();
    }

    public static String parseToken(String token, String secretKey) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
