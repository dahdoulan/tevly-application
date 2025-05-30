package org.group15.tveely.dao;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.AllArgsConstructor;
import org.group15.tveely.dto.EncodedVideoDto;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AzureUploadDaoImpl implements UploadDao<EncodedVideoDto>{

    private final BlobServiceClient blobServiceClient;

    @Override
    public void uploadVideo(EncodedVideoDto video) {
        throw new IllegalStateException("AzureUploadDao Does NOT have an Implementation of uploadVideo");
    }

    @Override
    public String uploadVideoToObjectStorage(String processingPath, String title) {
        BlobClient blobClient = retrieveClient(title);
        blobClient.uploadFromFile(processingPath);
        return blobClient.getBlobUrl();
    }

    private BlobClient retrieveClient(String title) {
        BlobContainerClient container = blobServiceClient.getBlobContainerClient(System.getenv("AZURE_CONTAINER_NAME"));
        container.createIfNotExists();
        BlobClient blobClient = container.getBlobClient(title);
        return blobClient;
    }

}
