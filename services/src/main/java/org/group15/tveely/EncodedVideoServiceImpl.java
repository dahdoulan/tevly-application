package org.group15.tveely.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import lombok.AllArgsConstructor;
import org.group15.tveely.dao.EncodedVideoDao;
import org.group15.tveely.models.EncodedVideoAdapter;
import org.group15.tveely.spi.EncodedVideoService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EncodedVideoServiceImpl implements EncodedVideoService {

    private final EncodedVideoDao encodedVideoRepository;
    private final BlobServiceClient blobServiceClient;

    @Override
    public List<EncodedVideoAdapter> getEncodedVideo(Long id) {
        List<EncodedVideoAdapter> videos = encodedVideoRepository.getVideosById(id);
        return videos.stream()
                .peek(video -> {
                    String sasToken = generateSas(video);
                    video.setUrl(video.getUrl() + "?" + sasToken);
                })
                .collect(Collectors.toList());
    }

    private String generateSas(EncodedVideoAdapter video) {
        BlobClient blobClient = retrieveClient(video.getTitle());
        BlobSasPermission sasPermission = new BlobSasPermission()
                .setReadPermission(true);
        OffsetDateTime expiryTime = OffsetDateTime.now().plusYears(10);
        BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, sasPermission)
                .setStartTime(OffsetDateTime.now().minusMinutes(5))
                .setProtocol(SasProtocol.HTTPS_ONLY);
        return blobClient.generateSas(sasValues);
    }

    private BlobClient retrieveClient(String title) {
        BlobContainerClient container = blobServiceClient.getBlobContainerClient("tveely");
        container.createIfNotExists();
        return container.getBlobClient(title);
    }
}
