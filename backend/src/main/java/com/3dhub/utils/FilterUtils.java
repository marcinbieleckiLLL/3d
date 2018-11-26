package com.greencrane.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface FilterUtils {
    boolean isTokenValid(String token);
    boolean isPreflightCorsRequest(HttpServletRequest request);
    boolean isCaptchaValid(String captchaResponse);
}

@Component
class FilterUtilsImpl implements FilterUtils {
    @Value("${subject}")
    private String tokenSubject;
    @Value("${secret}")
    private String tokenSecret;
    @Value("${issuer}")
    private String tokenIssuer;

    @Value("${captcha.secretKey}")
    String captchaSecretKey;
    @Value("${captcha.url}")
    String captchaUrl;


    @Autowired
    SendingUtils sendingUtils;

    @Override
    public boolean isTokenValid(String token) {
        return token != null && !token.equals("null") && new JwtDecoder(tokenSubject, tokenSecret, tokenIssuer).isTokenCorrect(token);
    }

    @Override
    public boolean isPreflightCorsRequest(HttpServletRequest request) {
        return new CorsRequestHelper().isPreflightCorsRequest(request);
    }

    @Override
    public boolean isCaptchaValid(String captchaResponse) {
        try {
            return new CaptchaValidator(captchaSecretKey, captchaUrl, sendingUtils).isValid(captchaResponse);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class JwtDecoder {
        private String subject;
        private String secret;
        private String issuer;

        public JwtDecoder(String subject, String secret, String issuer) {
            this.subject = subject;
            this.secret = secret;
            this.issuer = issuer;
        }

        public boolean isTokenCorrect(String token) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(issuer)
                        .withSubject(subject)
                        .build();
                verifier.verify(token);
                return true;
            } catch (JWTVerificationException | UnsupportedEncodingException exception){
                return false;
            }
        }
    }

    private static class CorsRequestHelper {
        private static final String CORS_HEADER = "Access-Control-Request-Method";
        private static final String[] METHODS = {"GET", "POST", "PUT", "DELETE"};

        public CorsRequestHelper() {}

        public boolean isPreflightCorsRequest(HttpServletRequest request) {
            return request != null && isCorrectHeader(getCorsHeader(request));
        }

        private boolean isCorrectHeader(String header) {
            return header != null && ListUtils.containsIgnoreCase(Arrays.asList(METHODS), header);
        }

        private String getCorsHeader(HttpServletRequest request) {
            return request.getHeader(CORS_HEADER);
        }
    }

    private static class CaptchaValidator {
        private String secretKey;
        private String captchaUrl;
        private SendingUtils sendingUtils;

        public CaptchaValidator(String secretKey, String captchaUrl, SendingUtils sendingUtils) {
            this.secretKey = secretKey;
            this.captchaUrl = captchaUrl;
            this.sendingUtils = sendingUtils;
        }

        public boolean isValid(String captchaResponse) throws IOException, JSONException {
            String googleResponse = sendingUtils.post(createCaptchaParams(captchaResponse), captchaUrl);
            JSONObject responseJson = new JSONObject(googleResponse);
            return responseJson.getBoolean("success");
        }

        private Map<String, String> createCaptchaParams(String captchaResponse) {
            Map<String, String> params = new HashMap<>();
            params.put("secret", secretKey);
            params.put("response", captchaResponse);
            return params;
        }
    }
}
