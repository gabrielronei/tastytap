package br.com.fiap.tastytap.insfraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenService.class);

    @Value("${token.default}")
    private String SECRET_KEY;

    public Optional<String> getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            DecodedJWT decodedToken = JWT.require(algorithm)
                    .build()
                    .verify(token);

            String cpf = decodedToken.getClaim("cpf").asString();
            return Optional.ofNullable(cpf);
        } catch (JWTVerificationException exception) {
            LOGGER.warn(exception.getMessage());
            return Optional.empty();
        }
    }

}