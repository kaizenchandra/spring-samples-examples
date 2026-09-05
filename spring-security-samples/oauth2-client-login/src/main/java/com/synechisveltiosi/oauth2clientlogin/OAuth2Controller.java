package com.synechisveltiosi.oauth2clientlogin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class OAuth2Controller {


    @GetMapping
    public String hello(){
        return "Hello, User";
    }

    @GetMapping("secured")
    public String secured(){
        return "Hello User, You Are Secured";
    }

    @GetMapping("/currentUser")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken authenticationToken){
        return authenticationToken.getPrincipal().getAttributes();
    }

    @GetMapping("/oauth2ClientUser")
    public ResponseEntity<Map<OAuth2AuthorizedClient, OAuth2User>> index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
       return ResponseEntity.ok(Map.of(authorizedClient, oauth2User));
    }
}
