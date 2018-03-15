package com.kmejka.authserver.config;

import java.util.Collection;
import java.util.Optional;

import com.sun.tools.javac.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

@Slf4j
public class FacebookUserAuthenticationService implements ResourceServerTokenServices {

    private static final String NULL_CREDENTIALS = "N/A";

    private final String clientId;

    static final String FACEBOOK_OBJECT_ID = "me";
    static final String[] FACEBOOK_PROFILE_FIELDS = {"id", "email", "gender", "first_name", "last_name"};

    public FacebookUserAuthenticationService(final String clientId) {
        this.clientId = clientId;
    }

    @Override
    public OAuth2AccessToken readAccessToken(@NonNull final String accessToken) {
        log.error("Unsupported operation called");
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    @Override
    public OAuth2Authentication loadAuthentication(@NonNull final String accessToken)
            throws AuthenticationException, InvalidTokenException {
        log.debug("Creating authentication for facebook user from FB access token: '{}'", accessToken);

        final Facebook facebookConnection = new FacebookTemplate(accessToken);

        final User userProfile = getFacebookUserProfile(facebookConnection)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve data from facebook auth provider"));
        log.debug("Found facebook user data: '{}'", userProfile);

        final String email = getUserEmail(userProfile);
        final String userId = getUserId(email);

        final Collection<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_FACEBOOK_USER"));

        final UsernamePasswordAuthenticationToken userAuthentication =
                new UsernamePasswordAuthenticationToken(userId, NULL_CREDENTIALS, authorities);

        return new OAuth2Authentication(constructOauthClientRequest(), userAuthentication);
    }

    public Optional<User> getFacebookUserProfile(@NonNull final Facebook facebook) {
        log.debug("Retrieving user facebook profile based on facebook connection: '{}'", facebook);

        return Optional.ofNullable(facebook.fetchObject(FACEBOOK_OBJECT_ID, User.class, FACEBOOK_PROFILE_FIELDS));
    }

    private String getUserEmail(final User facebookUserProfile) {
        return Optional.ofNullable(facebookUserProfile.getEmail())
                    .filter(StringUtils::isNotBlank)
                    .orElseThrow(() -> new RuntimeException("Email was missing from user profile data"));
    }

    private String getUserId(final String email) {
        return "admin";
    }

    private OAuth2Request constructOauthClientRequest() {
        return new OAuth2Request(null, this.clientId, null, true, null,
                null, null, null, null);
    }
}
