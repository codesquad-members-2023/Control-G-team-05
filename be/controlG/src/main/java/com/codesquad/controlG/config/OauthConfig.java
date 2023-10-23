package com.codesquad.controlG.config;

import com.codesquad.controlG.domain.auth.Oauth.InMemoryProviderRepository;
import com.codesquad.controlG.domain.auth.Oauth.OauthAdapter;
import com.codesquad.controlG.domain.auth.Oauth.OauthProperties;
import com.codesquad.controlG.domain.auth.Oauth.OauthProvider;
import java.util.Map;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {

    private final OauthProperties properties;

    public OauthConfig(OauthProperties properties) {
        this.properties = properties;
    }

    @Bean
    public InMemoryProviderRepository inMemoryProviderRepository() {
        Map<String, OauthProvider> providers = OauthAdapter.getOauthProviders(properties);
        return new InMemoryProviderRepository(providers);
    }
}
