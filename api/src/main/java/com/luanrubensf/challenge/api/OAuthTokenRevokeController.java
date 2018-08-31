package com.luanrubensf.challenge.api;

import com.luanrubensf.challenge.configuration.oauth.OAuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token/revoke")
public class OAuthTokenRevokeController {

    @Autowired
    private TokenStore tokenStore;

    private static final String SUCCESS_REVOKE_TOKEN = "{\"status\":\"success\"}";

    @RequestMapping(method = RequestMethod.POST)
    public String removeTokenInternal(@RequestParam Map<String, String> parameters) {
        String token = parameters.get(OAuthConstants.TOKEN_PROPERTY);
        String refreshToken = parameters.get(OAuthConstants.REFRESH_TOKEN_PROPERTY);

        if (token != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }

        if (refreshToken != null) {
            OAuth2RefreshToken oauthRefreshToken = tokenStore.readRefreshToken(refreshToken);
            tokenStore.removeRefreshToken(oauthRefreshToken);
        }

        return SUCCESS_REVOKE_TOKEN;
    }
}
