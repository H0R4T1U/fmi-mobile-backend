package project.fmihub.backend.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestingController {

    @GetMapping("/userInfo")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("oid", jwt.getClaim("oid"));
        claims.put("email", jwt.getClaim("upn"));
        claims.put("scopes", jwt.getClaim("scp"));
        return claims;
    }

}