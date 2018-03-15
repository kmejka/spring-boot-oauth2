package com.kmejka.authserver.config;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;

@Configuration
public class SocialSSOConfig {

    private final OAuth2ClientContext oAuth2ClientContext;
    private final FacebookClientConfig facebookClientConfig;

    public SocialSSOConfig(final OAuth2ClientContext oAuth2ClientContext,
                           final FacebookClientConfig facebookClientConfig) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.facebookClientConfig = facebookClientConfig;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(final OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    public Filter ssoSocialFilter() {
        final OAuth2ClientAuthenticationProcessingFilter facebookFilter =
                new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");

        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(facebookClientConfig.getClient(), oAuth2ClientContext);
        facebookFilter.setRestTemplate(restTemplate);

        final UserInfoTokenServices tokenServices =
                new UserInfoTokenServices(facebookClientConfig.getResource().getUserInfoUri(), facebookClientConfig.getClient().getClientId());

        tokenServices.setRestTemplate(restTemplate);
        facebookFilter.setTokenServices(tokenServices);
        return facebookFilter;
    }
}

