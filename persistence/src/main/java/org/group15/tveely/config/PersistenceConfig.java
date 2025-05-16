package org.group15.tveely.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    @Bean
    public BlobServiceClient blobServiceClient() {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=dahdoulan;AccountKey=xDszwiRear5pb4fAjOK1FAPQUwoX40M17qy18nqT03IlG9YA3yo4GxX7EV/Rgs6U11evM/S6hQ/f+AStrnPCHA==;EndpointSuffix=core.windows.net";
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }
}
