package com.kmejka.authserver.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("facebook")
@Component
public class FacebookClientConfig {

    private AuthorizationCodeResourceDetails client;

    private ResourceServerProperties resource;

}
