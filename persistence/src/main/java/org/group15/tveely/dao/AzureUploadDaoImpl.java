package org.group15.tveely.dao;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.AllArgsConstructor;
import org.group15.tveely.models.EncodedVideoAdapter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class AzureUploadDaoImpl implements UploadDao<EncodedVideoAdapter>{

    private final BlobServiceClient blobServiceClient;

    @Override
    public void uploadVideo(EncodedVideoAdapter video) {
        throw new IllegalStateException("AzureUploadDao Does NOT have an Implementation of uploadVideo");
    }

    @Override
    public String uploadVideoToObjectStorage(String processingPath, String title) {
        BlobClient blobClient = retrieveClient(title);
        blobClient.uploadFromFile(processingPath);
        return blobClient.getBlobUrl();
    }

    private BlobClient retrieveClient(String title) {
        BlobContainerClient container = blobServiceClient.getBlobContainerClient("tveely");
        container.createIfNotExists();
        BlobClient blobClient = container.getBlobClient(title);
        return blobClient;
    }

}
