package com.skplanet.otx.vaultclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.support.SslConfiguration;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration{
    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint ve = new VaultEndpoint();
        ve.setScheme("http");
        ve.setPort(8200);
        return ve;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication("s.kFG2oTvSescQz8B6UcnWm9lN");
    }

    @Override
    public SslConfiguration sslConfiguration() {
        return SslConfiguration.unconfigured();
    }

}

