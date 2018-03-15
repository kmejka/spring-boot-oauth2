package com.kmejka.authserver.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationProvider(final CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if ("admin".equals(email) && "admin".equals(password)) {
            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            final UsernamePasswordAuthenticationToken result =
                    new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
            result.setDetails(authentication.getDetails());
            return result;
        } else {
            throw new UsernameNotFoundException("No internal user found with email: " + email);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
