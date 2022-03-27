package kr.eric.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kr.eric.api.exception.NotLoggedInException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String getAccountIdFromToken(HttpServletRequest request) throws NotLoggedInException {
        String authorizationHeaderValue = request.getHeader("jwt");
        return getClaimFromToken(authorizationHeaderValue, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new NotLoggedInException();
        }
    }
}
