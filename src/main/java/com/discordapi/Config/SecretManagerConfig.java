package com.discordapi.Config;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SecretManagerConfig {

    @Bean
    public String discordToken(@Value("${discordTokenSecretName}") String secretName) throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            String secretVersionName = String.format("%s/versions/latest", secretName);
            AccessSecretVersionRequest accessRequest = AccessSecretVersionRequest.newBuilder()
                    .setName(secretVersionName)
                    .build();

            AccessSecretVersionResponse response = client.accessSecretVersion(accessRequest);
            return response.getPayload().getData().toStringUtf8();
        }
    }
}
