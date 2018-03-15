package com.kmejka.bookings.client.flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Configuration
public class FlightsClientConfiguration {

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    /** This means that we inject an interceptor which, before a request is made, will extract an oauth2 access token from
     *  the authenticated user context. If the access token cannot be extracted from the context the application will try
     *  to get the token using the `authorization_code` grant, as defined by the second parameter, the {@link OAuth2AuthenticationDetails},
     *  in this case it's {@link AuthorizationCodeResourceDetails}
     */
    @Bean
    public OAuth2FeignRequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, new AuthorizationCodeResourceDetails());
    }
}
