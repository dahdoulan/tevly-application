package org.group15.tveely.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    @Bean
    public BlobServiceClient blobServiceClient() {
        String connectionString = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }
}
