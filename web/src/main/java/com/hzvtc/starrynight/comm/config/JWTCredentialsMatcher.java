package com.hzvtc.starrynight.comm.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hzvtc.starrynight.entity.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 跟controller登录不一样，shiro并没有实现JWT的Matcher，需要我们自己来实现
 */
public class JWTCredentialsMatcher implements CredentialsMatcher {
	
	private final Logger log = LoggerFactory.getLogger(JWTCredentialsMatcher.class);
    /**
     * Matcher中直接调用工具包中的verify方法即可
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();
        Object stored = authenticationInfo.getCredentials();
        String salt = stored.toString();

        User user = (User)authenticationInfo.getPrincipals().getPrimaryPrincipal();
        try {
            Algorithm algorithm = Algorithm.HMAC256(salt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", user.getUserName())
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
//            UnsupportedEncodingException
            log.error("Token Error:{}", e.getMessage());
        }

        return false;
    }

}
